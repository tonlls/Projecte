using DBBasic.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.output_obj
{
    public class canacces_obj : Serializable
    {
        public bool can;
        public class acces_permes : canacces_obj
        {
            public bool primera;
            public bool es_ilimitat;

            public acces_permes(bool primera, bool es_ilimitat)
            {
                this.can = true;
                this.primera = primera;
                this.es_ilimitat = es_ilimitat;
            }
        }
        public class acces_denegat : canacces_obj
        {
            public string motiu;

            public acces_denegat(string motiu)
            {
                this.can = false;
                this.motiu = motiu;
            }
        }
    }
}
