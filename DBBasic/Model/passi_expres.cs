using System;
using System.Collections.Generic;
using System.Text;

namespace DBBasic.Model
{
    public class passi_expres : Serializable
    {
        public int id;
        public string nom_tipus;
        public DateTime data;
        public List<atraccions_passi> atraccions;

        public passi_expres(int id, string nom_tipus, DateTime data, List<atraccions_passi> atraccions)
        {
            this.id = id;
            this.nom_tipus = nom_tipus;
            this.data = data;
            this.atraccions = atraccions;
        }
    }
}
