using DBBasic;
using DBBasic.Model;
using DBBasic.output_obj;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;

namespace MySqlDriver
{
    public class MySql : DBInterface
    {
        public static int SESS_ID = 0;
        //Dictionary
        MySqlConnection conn;
        MySqlTransaction trans;
        public MySql(string connstr= "Server=localhost;Uid=root;Pwd=;database=port_aventura")
        {
            conn = new MySqlConnection(connstr);
            conn.Open();
            trans = conn.BeginTransaction();
        }
        public void Rollback()
        {
            try
            {
                trans.Rollback();
            }
            catch(Exception e)
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
            catch(Exception e)
            {
                throw new DBException(e.Message);
            }
        }
        public login_obj login(string user,string pass)
        {
            MySqlCommand com = new MySqlCommand("SELECT contrasenya FROM client WHERE nif=@nif'", conn);
            DBUtils.CrearParametre("nif", user, com);
            if (pass == (string)com.ExecuteScalar()) return new login_obj(SESS_ID++);
            return new login_obj(-1);
        }
        public getpass_obj getPassis(int ses_id)
        {

            return null;
        }
        public info_obj getInfo()
        {
            var res = new List<info_atraccio>();
            //wrong query
            MySqlCommand com = new MySqlCommand("SELECT id,nom,url_foto,nom_parc,url_foto_parc,codi_estat,temps_espera,descripcioHTML,capacitat,alçada_maxima,alçada_maxima_acompanyant FROM atraccio", conn);
            using(var reader = com.ExecuteReader())
            {
                do
                {
                    res.Add(new info_atraccio((int)reader.GetValue(1),
                                              (string)reader.GetValue(2),
                                              (string)reader.GetValue(3),
                                              (string)reader.GetValue(4),
                                              (string)reader.GetValue(5),
                                              (int)reader.GetValue(6),
                                              (int)reader.GetValue(7),
                                              (string)reader.GetValue(8),
                                              (int)reader.GetValue(9),
                                              (int)reader.GetValue(10),
                                              (int)reader.GetValue(11)));
                }
                while (reader.NextResult());
            }
            return new info_obj(res);
        }

        public canacces_obj potAccedir(int passi, int atraccio)
        {
            throw new NotImplementedException();
        }

        public confirm_obj confirmarAcces(int passi, int atraccio)
        {
            MySqlCommand com = new MySqlCommand("SELECT id FROM info_utilitzacio WHERE passi_id=@passi AND atraccio_id=@atraccio", conn);
            MySqlCommand com1 = new MySqlCommand("UPDATE info_utilitzacio SET ", conn);
            MySqlCommand com2 = new MySqlCommand("INSERT ", conn);
            throw new NotImplementedException();
        }

        public void clear_sessions()
        {
            throw new NotImplementedException();
        }
    }
}
