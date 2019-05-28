package com.app.socis;

public class Request extends Serializable {
	public String function;
	public Object[] args;

	public Request(String function, Object[] args)
	{
		this.function = function;
		this.args = args;
	}
}
