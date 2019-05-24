package com.app.socis.model;

public class atraccio extends Serializable {
	public int id;
	public String nom;
	public String url_foto;
	public int parc_id;
	//public string nom_parc;
	//public string url_foto_parc;
	public int codi_estat;
	public float temps_espera_minuts;
	public String descripcio_html;
	public int capacitat;
	public int alçada_minima;
	public int alçada_minima_acompanyant;

	public atraccio(int id, String nom, String url_foto, int parc_id, int codi_estat, float temps_espera_minuts, String descripcio_html, int capacitat, int alçada_minima, int alçada_minima_acompanyant) {
		this.id = id;
		this.nom = nom;
		this.url_foto = url_foto;
		this.parc_id = parc_id;
		this.codi_estat = codi_estat;
		this.temps_espera_minuts = temps_espera_minuts;
		this.descripcio_html = descripcio_html;
		this.capacitat = capacitat;
		this.alçada_minima = alçada_minima;
		this.alçada_minima_acompanyant = alçada_minima_acompanyant;
	}
}
