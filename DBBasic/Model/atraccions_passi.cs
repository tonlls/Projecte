namespace DBBasic.Model
{
    public class atraccions_passi : Serializable
    {
        public int num_usos_realitzats;
        public string nom_atraccio;
        public string estat;
        public string tipus_permis;

        public atraccions_passi(int num_usos_realitzats, string nom_atraccio, string estat, string tipus_permis)
        {
            this.num_usos_realitzats = num_usos_realitzats;
            this.nom_atraccio = nom_atraccio;
            this.estat = estat;
            this.tipus_permis = tipus_permis;
        }
    }
}