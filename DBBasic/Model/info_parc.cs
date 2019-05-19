using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.Model
{
    public class info_parc
    {
        public string nom;
        public string url_foto;

        public info_parc(string nom, string url_foto)
        {
            this.nom = nom;
            this.url_foto = url_foto;
        }
    }
}
