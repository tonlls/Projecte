package com.app.socis.model;

public class parc extends Serializable {
	public int id;
	public String nom;
	public String url_foto;

	public parc(int id, String nom, String url_foto)
	{
		this.id = id;
		this.nom = nom;
		this.url_foto = url_foto;
	}
}
