using DBBasic.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.output_obj
{
    public class info_parcs_obj : Serializable
    {
        public List<info_parc> parcs;
        public info_parcs_obj(List<info_parc> parcs)
        {
            this.parcs = parcs;
        }
    }
}
