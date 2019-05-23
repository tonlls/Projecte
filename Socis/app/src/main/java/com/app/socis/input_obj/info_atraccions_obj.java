package com.app.socis.input_obj;

import com.app.socis.model.Serializable;
import com.app.socis.model.atraccio;

import java.util.List;

public class info_atraccions_obj extends Serializable {
	public List<atraccio> estats_atraccions;
	public int num_atraccions;
	public info_atraccions_obj(List<atraccio> estats_atraccions,int num)
	{
		this.num_atraccions=num;
		this.estats_atraccions = estats_atraccions;
	}
}
