using DBBasic.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.output_obj
{
    public enum ERROR_CODES
    {
        INVALID_PARAMETERS=10,
        INVALID_FUNCTION=11
    }
    public class error_obj : Serializable
    {
        public ERROR_CODES code;
        public string msg;

        
        public error_obj(ERROR_CODES code, string msg)
        {
            this.code = code;
            this.msg = msg;
        }
    }
}
