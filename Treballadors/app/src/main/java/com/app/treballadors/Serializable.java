package com.app.treballadors;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public abstract class Serializable implements java.io.Serializable {
	public static final int HEADER_LEN=20;

	public static Object deserialize(byte[] in,Class clas) {
		/*ByteArrayInputStream bis=new ByteArrayInputStream(in);
		ObjectInputStream oin = new ObjectInputStream(bis);
		return oin.readObject();*/
		return new Gson().fromJson(in.toString(), clas);
	}
	public byte[] serialize(){
		/*ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);
		out.writeObject(this);
		out.flush();
		byte[] body=bos.toByteArray();*/
		byte[] body=new Gson().toJson(this).getBytes();
		byte[] msg=new byte[body.length+HEADER_LEN];
		String len=body.length+"";
		System.arraycopy(len.getBytes(),0,msg,0,len.getBytes().length);
		System.arraycopy(body,0,msg,HEADER_LEN,body.length);
		return msg;
	}
}
