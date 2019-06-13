namespace DBBasic.Model
{
    public class client
    {
        public string nif;
        public string contra;
        public string nom;
        public string cognom1;
        public string cognom2;

        public client(string nif, string contra, string nom, string cognom1, string cognom2)
        {
            this.nif = nif;
            this.contra = contra;
            this.nom = nom;
            this.cognom1 = cognom1;
            this.cognom2 = cognom2;
        }
    }
}