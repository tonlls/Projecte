using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Text;

namespace MySqlDriver
{
    public abstract class DBUtils
    {
        public static void CrearParametre(string pNom, object pValue, MySqlCommand pConsulta)
        {
            MySqlParameter param = pConsulta.CreateParameter();
            param.ParameterName = "@" + pNom;
            param.Value = pValue;
            pConsulta.Parameters.Add(param);
        }
    }
}
