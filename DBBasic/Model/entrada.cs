using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.Model
{
    public class entrada
    {
        public float preu;
        public int client;
        public string nif;
        public int categoria;
        public int dies;
        public int preu_id;

        public entrada(float preu, int client, string nif, int categoria, int dies, int preu_id)
        {
            this.preu = preu;
            this.client = client;
            this.nif = nif;
            this.categoria = categoria;
            this.dies = dies;
            this.preu_id = preu_id;
        }
    }
}
