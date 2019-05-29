package com.app.socis.model;

import java.util.Date;
import java.util.List;

public class passi_expres {
	public int id;
	public String nom_tipus;
	public Date data;
	public List<atraccions_passi> atraccions;

	public passi_expres(int id, String nom_tipus, Date data, List<atraccions_passi> atraccions) {
		this.id = id;
		this.nom_tipus = nom_tipus;
		this.data = data;
		this.atraccions = atraccions;
	}
}
