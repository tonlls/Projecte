using System;
using System.Collections.Generic;
using System.Runtime.Serialization;
using System.Text;

namespace DBBasic
{
    [Serializable]
    public class DBException : Exception
    {
        public DBException()
        {
        }

        public DBException(string message) : base(message)
        {
        }

        public DBException(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected DBException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}
