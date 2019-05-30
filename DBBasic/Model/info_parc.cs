using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.Model
{
    public class info_parc
    {
        public int id;
        public string nom;
        public string url_foto;

        public override string ToString()
        {
            return nom;
        }

        public info_parc(int id, string nom, string url_foto)
        {
            this.id = id;
            this.nom = nom;
            this.url_foto = url_foto;
        }
    }
}
