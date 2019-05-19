using DBBasic;
using DBBasic.Model;
using DBBasic.output_obj;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;

namespace MySqlDriver
{
    public class MySql : DBInterface
    {
        public static int SESS_ID = 0;
        private static Dictionary<int, int> sessions = new Dictionary<int, int>();
        private string url;
        MySqlTransaction trans;
        public MySql(string connstr = "Server=localhost;Uid=root;Pwd=;database=port_aventura")
        {
            this.url = connstr;
            //trans = conn.BeginTransaction();
        }
        public void Rollback()
        {
            try
            {
                trans.Rollback();
            }
            catch (Exception e)
            {
                throw new DBException(e.Message);
            }
        }
        public void Commit()
        {
            try
            {
                trans.Commit();
            }
            catch (Exception e)
            {
                throw new DBException(e.Message);
            }
        }
        public login_obj login(string user, string pass)
        {
            using (var conn = new MySqlConnection(url))
            {
                conn.Open();
                MySqlCommand com = new MySqlCommand("SELECT contrasenya,id FROM client WHERE nif=@nif'", conn);
                DBUtils.CrearParametre("nif", user, com);
                var reader = com.ExecuteReader();
                reader.Read();
                string pas = reader.GetString("contrassenya");
                int id = reader.GetInt32("id");
                if (pass == pas)
                {
                    if (sessions.ContainsKey(id)) return new login_obj(sessions[id]);
                    sessions.Add(id, SESS_ID);
                    return new login_obj(SESS_ID++);
                }
                return new login_obj(-1);
            }
        }
        public getpass_obj getPassis(int ses_id)
        {
            throw new NotImplementedException();
        }

        public canacces_obj potAccedir(int passi, int atraccio)
        {
            using (var conn = new MySqlConnection(url))
            {
                conn.Open();
                MySqlCommand com = new MySqlCommand("SELECT COUNT(a.*) count,p.tipus_id tipus FROM tipus_acces_atraccio a,passi_express p WHERE a.atraccio_id=@atraccio AND a.tipus_passi_id=p.tipus_id AND p.id=@passi)", conn);
                DBUtils.CrearParametre("passi", passi, com);
                DBUtils.CrearParametre("atraccio", atraccio, com);
                var reader = com.ExecuteReader();
                reader.Read();
                int count = reader.GetInt32("count");
                int type_pas = reader.GetInt32("tipus");
                MySqlCommand com0 = new MySqlCommand("SELECT tipus_acces_id FROM tipus_passi_express WHERE id=@tipus", conn);
                DBUtils.CrearParametre("tipus", type_pas, com0);
                int type = (int)com0.ExecuteScalar();
                string motiu = "";
                if (count == 1)
                {
                    MySqlCommand com1 = new MySqlCommand("SELECT num_usos FROM info_utilitzacio WHERE passi_id=@pas AND atraccio_id=@atraccio", conn);
                    DBUtils.CrearParametre("pas", passi, com1);
                    DBUtils.CrearParametre("atraccio", atraccio, com1);
                    if (type != (int)tipus_acces.ILIMITAT && type != (int)tipus_acces.ILIMITAT_UN_1A && ((int)com1.ExecuteScalar()) >= 1)
                    {
                        motiu = "atraccio ja consumida";
                    }
                    else if (count == 0) motiu = "atraccio no permessa";
                    else return new canacces_obj.acces_permes(type == (int)tipus_acces.UN_SOL_US_1A || type == (int)tipus_acces.ILIMITAT_UN_1A ? true : false, type == (int)tipus_acces.ILIMITAT_UN_1A || type == (int)tipus_acces.ILIMITAT ? true : false);
                }
                return new canacces_obj.acces_denegat(motiu);
            }
        }

        public confirm_obj confirmarAcces(int passi, int atraccio)
        {
            using (var conn = new MySqlConnection(url))
            {
                conn.Open();
                MySqlCommand sel = new MySqlCommand("SELECT count(id) FROM info_utilitzacio WHERE passi_id=@passi AND atraccio_id=@atraccio", conn);
                DBUtils.CrearParametre("passi", passi, sel);
                DBUtils.CrearParametre("atraccio", atraccio, sel);
                MySqlCommand upd = new MySqlCommand("UPDATE info_utilitzacio SET num_usos=num_usos+1 WHERE passi_id=@pass AND atraccio_id=@atraccio", conn);
                DBUtils.CrearParametre("passi", passi, upd);
                DBUtils.CrearParametre("atraccio", atraccio, upd);
                MySqlCommand ins = new MySqlCommand("INSERT INTO info_utilitzacio(passi_id,atraccio_id,num_usos) VALUES(@passi,@atraccio,1)", conn);
                DBUtils.CrearParametre("passi", passi, ins);
                DBUtils.CrearParametre("atraccio", atraccio, ins);
                int ok = 0;
                if (((int)sel.ExecuteScalar()) > 0)
                {
                    ok = upd.ExecuteNonQuery();
                }
                else
                {
                    ok = ins.ExecuteNonQuery();
                }
                return new confirm_obj(ok == 1 ? 0 : -1);
            }
        }

        public void clear_sessions()
        {
            sessions.Clear();
        }

        public info_parcs_obj getInfoParcs()
        {
            using (var conn = new MySqlConnection(url))
            {
                conn.Open();
                var res = new List<info_parc>();
                MySqlCommand com = new MySqlCommand("SELECT p.nom nom,p.url_foto url FROM parc p", conn);
                using (var reader = com.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        res.Add(new info_parc(reader.GetString("nom"), reader.GetString("url")));
                    }
                }
                return new info_parcs_obj(res);
            }
        }

        public info_atraccions_obj getInfoAtraccions(int parc)
        {
            using (var conn = new MySqlConnection(url))
            {
                conn.Open();
                var res = new List<info_atraccio>();
                //MySqlCommand com = new MySqlCommand("SELECT a.id,a.nom,a.url_foto,p.nom nom_parc,p.url_foto foto_parc,a.estat_actual_id,(a.clients_cua/a.capacitat_maxima_ronda)*a.temps_per_ronda temps_espera,a.descripcioHTML,a.capacitat_maxima_ronda,a.alçada_minima alsada_min,a.alçada_minima_acompanyat alsada_min_acomp FROM atraccio a, parc p, zona z WHERE a.zona_id = z.id and a.parc_id = z.parc_id and p.id = z.parc_id", conn);
                MySqlCommand com = new MySqlCommand("SELECT a.id,a.nom,a.url_foto,a.estat_actual_id,(a.clients_cua/a.capacitat_maxima_ronda)*a.temps_per_ronda temps_espera,a.descripcioHTML,a.capacitat_maxima_ronda,a.alçada_minima alsada_min,a.alçada_minima_acompanyat alsada_min_acomp FROM atraccio a WHERE a.parc_id = @parc", conn);
                DBUtils.CrearParametre("parc", parc, com);
                using (var reader = com.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        res.Add(new info_atraccio((int)reader.GetInt32("id"),
                                                  (string)reader.GetString("nom"),
                                                  (string)reader.GetString("url_foto"),
                                                  (int)reader.GetInt32("estat_actual_id"),
                                                  (float)reader.GetDecimal("temps_espera"),
                                                  (string)reader.GetString("descripcioHTML"),
                                                  (int)reader.GetInt32("capacitat_maxima_ronda"),
                                                  (int)reader.GetInt32("alsada_min"),
                                                  (int)reader.GetInt32("alsada_min_acomp")));
                        //var y = reader.GetSchemaTable();
                    }
                }
                return new info_atraccions_obj(res);
            }
        }

        public List<info_atraccio> getAtraccions()
        {
            using (var conn = new MySqlConnection(url))
            {
                conn.Open();
                var res = new List<info_atraccio>();
                //MySqlCommand com = new MySqlCommand("SELECT a.id,a.nom,a.url_foto,p.nom nom_parc,p.url_foto foto_parc,a.estat_actual_id,(a.clients_cua/a.capacitat_maxima_ronda)*a.temps_per_ronda temps_espera,a.descripcioHTML,a.capacitat_maxima_ronda,a.alçada_minima alsada_min,a.alçada_minima_acompanyat alsada_min_acomp FROM atraccio a, parc p, zona z WHERE a.zona_id = z.id and a.parc_id = z.parc_id and p.id = z.parc_id", conn);
                MySqlCommand com = new MySqlCommand("SELECT a.id,a.nom,a.url_foto,a.estat_actual_id,(a.clients_cua/a.capacitat_maxima_ronda)*a.temps_per_ronda temps_espera,a.descripcioHTML,a.capacitat_maxima_ronda,a.alçada_minima alsada_min,a.alçada_minima_acompanyat alsada_min_acomp FROM atraccio a", conn);
                using (var reader = com.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        res.Add(new info_atraccio((int)reader.GetInt32("id"),
                                                  (string)reader.GetString("nom"),
                                                  (string)reader.GetString("url_foto"),
                                                  (int)reader.GetInt32("estat_actual_id"),
                                                  (float)reader.GetDecimal("temps_espera"),
                                                  (string)reader.GetString("descripcioHTML"),
                                                  (int)reader.GetInt32("capacitat_maxima_ronda"),
                                                  (int)reader.GetInt32("alsada_min"),
                                                  (int)reader.GetInt32("alsada_min_acomp")));
                        //var y = reader.GetSchemaTable();
                    }
                }
                return res;
            }
        }

        public void updateCuaAtraccio(int atraccio, int cua)
        {
            using (var conn = new MySqlConnection(url))
            {
                conn.Open();
                MySqlCommand com = new MySqlCommand("UPDATE atraccio SET clients_cua=@cua WHERE id=@atraccio", conn);
                DBUtils.CrearParametre("atraccio", atraccio, com);
                DBUtils.CrearParametre("cua", cua, com);
                com.ExecuteNonQuery();
            }
        }

        public int getCua(int atraccio)
        {
            using (var conn = new MySqlConnection(url))
            {
                conn.Open();
                MySqlCommand com = new MySqlCommand("SELECT clients_cua FROM atraccio WHERE id=@atraccio", conn);
                DBUtils.CrearParametre("atraccio", atraccio, com);
                return (int)com.ExecuteScalar();
            }
        }
    }
}
