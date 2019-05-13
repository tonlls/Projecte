using DBBasic.Model;
using Nancy.Json;
using System;

namespace DBBasic
{
    public class info_atraccio : Serializable
    {
        public int id;
        public string nom;
        public string url_foto;
        public string nom_parc;
        public string url_foto_parc;
        public int codi_estat;
        public int temps_espera_minuts;
        public string descripcio_html;
        public int capacitat;
        public int alçada_minima;
        public int alçada_minima_acompanyant;

        public info_atraccio(int id, string nom, string url_foto, string nom_parc, string url_foto_parc, int codi_estat, int temps_espera_minuts, string descripcio_html, int capacitat, int alçada_minima, int alçada_minima_acompanyant)
        {
            this.id = id;
            this.nom = nom;
            this.url_foto = url_foto;
            this.nom_parc = nom_parc;
            this.url_foto_parc = url_foto_parc;
            this.codi_estat = codi_estat;
            this.temps_espera_minuts = temps_espera_minuts;
            this.descripcio_html = descripcio_html;
            this.capacitat = capacitat;
            this.alçada_minima = alçada_minima;
            this.alçada_minima_acompanyant = alçada_minima_acompanyant;
        }
    }
}