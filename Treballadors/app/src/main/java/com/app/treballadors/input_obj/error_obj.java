package com.app.treballadors.input_obj;

import com.app.treballadors.model.Serializable;

public class error_obj extends Serializable
{
	public int code;
	public String msg;

	public error_obj(int code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}
}