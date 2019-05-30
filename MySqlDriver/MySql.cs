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
                MySqlCommand com = new MySqlCommand("SELECT contrasenya,id FROM client WHERE nif=@nif", conn);
                DBUtils.CrearParametre("nif", user, com);
                var rd = com.ExecuteReader();
                String s =com.CommandText;
                rd.Read();
                int id = rd.GetInt32("id");
                string pas = rd.GetString("contrasenya");
                if (pass == pas)
                {
                    if (sessions.ContainsKey(id)) return new login_obj(sessions[id]);
                    sessions.Add(SESS_ID,id);
                    return new login_obj(SESS_ID++);
                }
                return new login_obj(-1);
            }
        }
        public getpass_obj getPassis(int ses_id)
        {
            using (var conn = new MySqlConnection(url))
            using (var conn1 = new MySqlConnection(url))
            {
                List<passi_expres> passis = new List<passi_expres>();
                int cli = sessions[ses_id];
                conn.Open();
                conn1.Open();
                MySqlCommand com = new MySqlCommand("select p.id id,t.id t_id,t.nom t_nom,p.data data from pasi_expres p JOIN tipus_pasi_expres t on t.id = p.tipus_id where client_id = @cli", conn);
                MySqlCommand com1 = new MySqlCommand("select a.nom nom,e.nom estat,iu.num_usos usos ,ta.nom tipus from atraccio a JOIN estat_operatiu e on e.id = a.estat_actual_id LEFT JOIN info_utilitzacio iu on iu.atraccio_id = a.id and iu.pasi_id = @pas LEFT JOIN tipus_acces ta on ta.id = (select tipus_acces_id from tipus_acces_atraccio where tipus_pasi_id = @t_pas and atraccio_id = a.id) ", conn1);
                DBUtils.CrearParametre("cli", cli, com);
                var reader = com.ExecuteReader();
                while (reader.Read())
                {
                    int id = reader.GetInt32("id");
                    int tipus = reader.GetInt32("t_id");
                    string tNom = reader.GetString("t_nom");
                    DateTime data = reader.GetDateTime("data");
                    List<atraccions_passi> atraccions = new List<atraccions_passi>();
                    com1.Parameters.Clear();
                    DBUtils.CrearParametre("pas", id, com1);
                    DBUtils.CrearParametre("t_pas", tipus, com1);
                    var reader1 = com1.ExecuteReader();
                    while (reader1.Read())
                    {
                        string nom = reader1.GetString("nom");
                        string estat = reader1.GetString("estat");
                        int usos = reader1.IsDBNull(2)?0:reader1.GetInt32("usos");
                        
                        string t_acces = reader1.GetString("tipus");
                        atraccions.Add(new atraccions_passi(usos, nom, estat, t_acces));
                    }
                    passis.Add(new passi_expres(id, tNom, data, atraccions));
                    reader1.Close();
                }
                reader.Close();
                return new getpass_obj(passis);
            }
        }
        public canacces_obj potAccedir(int passi, int atraccio)
        {
            using (var conn = new MySqlConnection(url))
            {
                conn.Open();
                MySqlCommand com = new MySqlCommand(
                    "SELECT p.id id,t.tipus_acces_id tipus,i.num_usos usos " +
                    "FROM pasi_expres p " +
                    "JOIN tipus_acces_atraccio t on t.tipus_pasi_id = p.tipus_id " +
                    "LEFT JOIN info_utilitzacio i on i.pasi_id = p.id and i.atraccio_id = t.atraccio_id " +
                    "WHERE DATE(p.data) = DATE(CURDATE()) AND p.id = @pasi AND t.atraccio_id = @atraccio", conn);
                DBUtils.CrearParametre("pasi", passi, com);
                DBUtils.CrearParametre("atraccio", atraccio, com);
                var reader = com.ExecuteReader();
                bool permited=reader.Read();
                string motiu = "";
                if (permited)
                {
                    int type = reader.GetInt32("tipus");
                    int usos= reader.IsDBNull(2)?-1:reader.GetInt32("usos");
                    //int usos = (tmp == null ? -1 : (int)tmp);
                    reader.Close();
                    if (type != (int)tipus_acces.ILIMITAT && type != (int)tipus_acces.ILIMITAT_UN_1A&&(usos!=-1 &&usos>=1))
                    {
                        motiu = "atraccio ja consumida";
                    }
                    else return new canacces_obj.acces_permes(type == (int)tipus_acces.UN_SOL_US_1A || type == (int)tipus_acces.ILIMITAT_UN_1A ? true : false, type == (int)tipus_acces.ILIMITAT_UN_1A || type == (int)tipus_acces.ILIMITAT ? true : false);
                }
                else if (!permited) motiu = "atraccio no permessa";
                return new canacces_obj.acces_denegat(motiu);
            }
        }

        public confirm_obj confirmarAcces(int passi, int atraccio)
        {
            using (var conn = new MySqlConnection(url))
            {
                conn.Open();
                MySqlCommand sel = new MySqlCommand("SELECT count(id) FROM info_utilitzacio WHERE pasi_id=@pasi AND atraccio_id=@atraccio", conn);
                DBUtils.CrearParametre("pasi", passi, sel);
                DBUtils.CrearParametre("atraccio", atraccio, sel);
                MySqlCommand upd = new MySqlCommand("UPDATE info_utilitzacio SET num_usos=num_usos+1 WHERE pasi_id=@pas AND atraccio_id=@atraccio", conn);
                DBUtils.CrearParametre("pasi", passi, upd);
                DBUtils.CrearParametre("atraccio", atraccio, upd);
                MySqlCommand ins = new MySqlCommand("INSERT INTO info_utilitzacio(pasi_id,atraccio_id,num_usos) VALUES(@pasi,@atraccio,1)", conn);
                DBUtils.CrearParametre("pasi", passi, ins);
                DBUtils.CrearParametre("atraccio", atraccio, ins);
                int ok = 0;
                long x = (long)sel.ExecuteScalar();
                try
                {
                    if (x > 0)
                    {
                        //ok = upd.ExecuteNonQuery();
                        upd.ExecuteNonQuery();
                    }
                    else
                    {
                        //ok = ins.ExecuteNonQuery();
                        ins.ExecuteNonQuery();
                    }
                }
                catch (Exception)
                {
                    ok = -1;
                }
                return new confirm_obj(ok);
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
                MySqlCommand com = new MySqlCommand("SELECT p.id,p.nom nom,p.url_foto url FROM parc p", conn);
                using (var reader = com.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        res.Add(new info_parc(reader.GetInt32("id"),reader.GetString("nom"), reader.GetString("url")));
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
                MySqlCommand com = new MySqlCommand("SELECT a.parc_id parc_id,a.id,a.nom,a.url_foto,a.estat_actual_id,(a.clients_cua/a.capacitat_maxima_ronda)*a.temps_per_ronda temps_espera,a.descripcioHTML,a.capacitat_maxima_ronda,a.alçada_minima alsada_min,a.alçada_minima_acompanyat alsada_min_acomp FROM atraccio a WHERE a.parc_id = @parc", conn);
                DBUtils.CrearParametre("parc", parc, com);
                using (var reader = com.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        res.Add(new info_atraccio((int)reader.GetInt32("id"),
                                                  (string)reader.GetString("nom"),
                                                  (string)reader.GetString("url_foto"),
                                                  (int)reader.GetInt32("parc_id"),
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
                MySqlCommand com = new MySqlCommand("SELECT a.id,a.parc_id parc_id,a.nom,a.url_foto,a.estat_actual_id,a.temps_per_ronda temps_espera,a.descripcioHTML,a.capacitat_maxima_ronda,a.alçada_minima alsada_min,a.alçada_minima_acompanyat alsada_min_acomp FROM atraccio a", conn);
                using (var reader = com.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        res.Add(new info_atraccio((int)reader.GetInt32("id"),
                                                  (string)reader.GetString("nom"),
                                                  (string)reader.GetString("url_foto"),
                                                  (int)reader.GetInt32("parc_id"),
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

        public List<preu> getPreus()
        {
            List<preu> ret = new List<preu>();
            using (var conn = new MySqlConnection(url))
            using (var conn1 = new MySqlConnection(url))
            {
                conn.Open();
                conn1.Open();
                MySqlCommand com = new MySqlCommand("SELECT id,dies,preu_adult,preu_nen_senior,preu_discapacitat FROM preu", conn);
                var re=com.ExecuteReader();
                while (re.Read())
                {
                    List<int> parcs = new List<int>();
                    int id=re.GetInt32("id");
                    MySqlCommand com1 = new MySqlCommand("SELECT parc_id FROM preu_parc WHERE preu_id=@preu", conn1);
                    DBUtils.CrearParametre("preu", id, com1);
                    var re1 = com1.ExecuteReader();
                    while (re1.Read())
                    {
                        parcs.Add(re1.GetInt32("parc_id"));
                    }
                    ret.Add(new preu(id,
                                     re.GetInt32("dies"),
                                     re.GetFloat("preu_adult"),
                                     re.GetFloat("preu_discapacitat"),
                                     re.GetFloat("preu_nen_senior"),
                                     parcs));
                }
                return ret;
            }
        }

        public List<categoria_entrada> getCategoriesEntrades()
        {
            throw new NotImplementedException();
        }
    }
}
