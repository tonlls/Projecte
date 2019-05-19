using DBBasic.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.output_obj
{
    public class getpass_obj : Serializable
    {
        public List<passi_expres> passis;
        public int num_passis;
        public getpass_obj(List<passi_expres> passis)
        {
            this.passis = passis;
        }
        public new byte[] serialize(bool header = true)
        {
            num_passis = passis.Count;
            return base.serialize();
        }
    }
}
