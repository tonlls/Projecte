package com.app.socis.model;

public class atraccions_passi {
	public int num_usos_realitzats;
	public String nom_atraccio;
	public String estat;
	public String tipus_permis;

	public atraccions_passi(int num_usos_realitzats, String nom_atraccio, String estat, String tipus_permis) {
		this.num_usos_realitzats = num_usos_realitzats;
		this.nom_atraccio = nom_atraccio;
		this.estat = estat;
		this.tipus_permis = tipus_permis;
	}
}
