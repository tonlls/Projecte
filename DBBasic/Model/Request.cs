using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.Model
{
    public class Request : Serializable
    {
        private string function;

        public Request(string function)
        {
            this.function = function;
        }
    }
}
