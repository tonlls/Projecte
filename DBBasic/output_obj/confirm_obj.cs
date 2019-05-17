using DBBasic.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.output_obj
{
    public class confirm_obj : Serializable
    {
        public int codi;
        public confirm_obj(int codi)
        {
            this.codi = codi;
        }
    }
}
