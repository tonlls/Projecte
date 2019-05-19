package com.app.treballadors;

import java.io.Serializable;

public class parc implements Serializable {
	public String nom;
	public String foto;

	public parc(String nom, String foto) {
		this.nom = nom;
		this.foto = foto;
	}
}
