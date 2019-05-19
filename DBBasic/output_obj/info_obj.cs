using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.Model
{
    public class info_atraccions_obj : Serializable
    {
        public List<info_atraccio> estats_atraccions;
        public int num_atraccions;
        public info_atraccions_obj(List<info_atraccio> estats_atraccions)
        {
            this.estats_atraccions = estats_atraccions;
        }
        public new byte[] serialize(bool header = true)
        {
            num_atraccions = estats_atraccions.Count;
            return base.serialize();
        }
    }
}
