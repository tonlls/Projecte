using System;
using System.Collections.Generic;
using System.Reflection;
using System.Text;

namespace DBBasic
{
    public abstract class DBFactory
    {
        public static DBInterface createDBFactory(string clas)
        {
            Type type = Type.GetType(clas);
            ConstructorInfo ctor = type.GetConstructor(new[] { typeof(string) });
            return (DBInterface)ctor.Invoke(new object[] { 10 });
        }
    }
}
