package com.app.socis.input_obj;

import com.app.socis.model.passi_expres;

import java.util.List;

public class getpass_obj {
	public List<passi_expres> passis;
	public int num_passis;

	public getpass_obj(List<passi_expres> passis, int num_passis) {
		this.passis = passis;
		this.num_passis = num_passis;
	}
}
