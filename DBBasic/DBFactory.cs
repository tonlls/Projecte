using System;
using System.Collections.Generic;
using System.Reflection;
using System.Text;
using MySqlDriver;
namespace DBBasic
{
    public static class DBFactory
    {
        public static DBInterface getInstance(string clas)
        {
            Type type = Type.GetType(clas);
            if (type == null) throw new DBException("Error on getting factory class");
            ConstructorInfo ctor = type.GetConstructor(new[] { typeof(string) });
            return (DBInterface)ctor.Invoke(new object[] { Type.Missing });
        }
    }
}
