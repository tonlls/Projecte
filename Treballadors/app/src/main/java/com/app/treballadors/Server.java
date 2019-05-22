package com.app.treballadors;

import android.os.AsyncTask;

import com.app.treballadors.input_obj.canacces_obj;
import com.app.treballadors.model.Request;
import com.app.treballadors.model.Serializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Server extends AsyncTask<Request,Void,Object> {
	boolean cast=true;
	IActivity ma;
	Class clas;
	public Server(IActivity mainActivity,Class clas) {
		this.ma=mainActivity;
		this.clas=clas;
	}

	public Server(IActivity mainActivity, Class clas, boolean b) {
		this.ma=mainActivity;
		this.clas=clas;
		this.cast=b;
	}

	@Override
	protected Object doInBackground(Request... requests) {

		try(Socket sc = new Socket("10.0.2.2", 11000)){
		//try(Socket sc = new Socket("10.132.25.189", 11000)){
			DataOutputStream dOut = new DataOutputStream(sc.getOutputStream());
			DataInputStream dIn = new DataInputStream(sc.getInputStream());
			dOut.write(requests[0].serialize());
			byte [] intByte= new byte[4];
			dIn.read(intByte,0,4);
			ByteBuffer bb =ByteBuffer.wrap(intByte);
			bb.order(ByteOrder.LITTLE_ENDIAN);
			int len = bb.getInt();
			byte[] bod=new byte[len];
			dIn.read(bod,0,len);
			if(cast)return Serializable.deserialize(bod,clas);
			else return new String(bod);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Object serializable) {
		super.onPostExecute(serializable);
		ma.setResult(serializable,clas);
	}
	/*public static Serializable getServerInfo(Request req) throws IOException {
		Socket sc = new Socket("localhost", 11000);
		try(){
			DataOutputStream dOut = new DataOutputStream(sc.getOutputStream());
			DataInputStream dIn = new DataInputStream(sc.getInputStream());
			dOut.write(req.serialize());
			return null;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
