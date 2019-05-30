using System.Collections.Generic;

namespace DBBasic
{
    public class preu
    {
        public int id;
        public int dies;
        public float preu_adult;
        public float preu_nen_senior;
        public float preu_discapacitat;
        public List<int> parcs;
        public preu(int id, int dies, float preu_adult, float preu_nen_senior, float preu_discapacitat, List<int> parcs)
        {
            this.id = id;
            this.dies = dies;
            this.preu_adult = preu_adult;
            this.preu_nen_senior = preu_nen_senior;
            this.preu_discapacitat = preu_discapacitat;
            this.parcs = parcs;
        }
    }
}