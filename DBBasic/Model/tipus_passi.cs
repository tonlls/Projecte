using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.Model
{
    public class tipus_passi
    {
        public int id;
        public string nom;
        public float preu;

        public tipus_passi(int id, string nom, float preu)
        {
            this.id = id;
            this.nom = nom;
            this.preu = preu;
        }

        public override string ToString()
        {
            return nom;
        }
    }
}
