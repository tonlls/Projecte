package com.app.socis.input_obj;

import com.app.socis.Serializable;
import com.app.socis.model.parc;

import java.util.List;

public class info_parcs_obj extends Serializable {
	public List<parc> parcs;
	public info_parcs_obj(List<parc> parcs)
	{
		this.parcs = parcs;
	}
}
