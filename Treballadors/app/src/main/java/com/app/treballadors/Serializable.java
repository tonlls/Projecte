package com.app.treballadors;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public abstract class Serializable implements java.io.Serializable {
	public static final int HEADER_LEN=4;

	public static Object deserialize(byte[] in,Class clas) throws IOException, ClassNotFoundException {
		/*ByteArrayInputStream bis=new ByteArrayInputStream(in);
		ObjectInputStream oin = new ObjectInputStream(bis);
		return oin.readObject();*/
		String x=new String(in,"UTF8");
		return new Gson().fromJson(x, clas);
	}
	public byte[] serialize() throws IOException {
		/*ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);
		out.writeObject(this);
		out.flush();
		byte[] body=bos.toByteArray();*/
		byte[] body=new Gson().toJson(this).getBytes();
		byte[] msg=new byte[body.length+HEADER_LEN];

		ByteBuffer bb =ByteBuffer.allocate(4);

		bb.order(ByteOrder.LITTLE_ENDIAN);
		byte[] len=bb.putInt(body.length).array();
		System.arraycopy(len,0,msg,0,len.length);
		System.arraycopy(body,0,msg,HEADER_LEN,body.length);
		return msg;
	}
}
