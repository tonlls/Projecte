using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.Model
{
    public class Request : Serializable
    {
        public string function;
        public object[] args;

        public Request(string function, object[] args)
        {
            this.function = function;
            this.args = args;
        }
    }
}
