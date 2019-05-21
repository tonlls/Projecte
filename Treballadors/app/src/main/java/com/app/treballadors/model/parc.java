package com.app.treballadors.model;

import java.io.Serializable;

public class parc implements Serializable {
	public int id;
	public String nom;
	public String url_foto;

	public parc(int id,String nom, String foto) {
		this.id=id;
		this.nom = nom;
		this.url_foto = foto;
	}
}
