package com.app.treballadors.input_obj;

import com.app.treballadors.Serializable;
import com.app.treballadors.parc;

import java.util.List;

public class info_parcs_obj extends Serializable {
	public List<parc> parcs;
	public info_parcs_obj(List<parc> parcs)
	{
		this.parcs = parcs;
	}
}
