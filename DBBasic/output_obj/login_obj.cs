using DBBasic.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.output_obj
{
    public class login_obj : Serializable
    {
        public int code;
        public login_obj(int code)
        {
            this.code = code;
        }
    }
}
