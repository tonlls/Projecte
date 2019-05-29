package com.app.socis.input_obj;

import com.app.socis.Serializable;

public class login_obj extends Serializable {
	public int code;
	public login_obj(int code){
		this.code = code;
	}
}
