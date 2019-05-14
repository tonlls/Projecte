using DBBasic;
using DBBasic.Model;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;

namespace MySqlDriver
{
    public class MySql : DBInterface
    {
        MySqlConnection conn;
        MySqlCommand comand;
        MySqlTransaction trans;
        public MySql(string connstr= "Server=localhost;Uid=root;Pwd=;database=port_aventura")
        {
            this.conn = new MySqlConnection(connstr);
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
        public int login(string user,string pass)
        {

            return -1;
        }
        public List<passi_expres> getPassis(int ses_id)
        {

            return null;
        }
        public List<info_atraccio> getInfo()
        {
            var res = new List<info_atraccio>();
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
            return res;
        }

        public bool potAccedir(int passi, int atraccio)
        {
            throw new NotImplementedException();
        }

        public int confirmarAcces(int passi, int atraccio)
        {
            throw new NotImplementedException();
        }
    }
}
