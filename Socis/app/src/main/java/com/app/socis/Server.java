package com.app.socis;
import android.os.AsyncTask;
import android.util.Log;

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
	private Request req;

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
		this.req=requests[0];
		return doRequest(req,clas,cast);
	}

	public static Object doRequest(Request request,Class clas,boolean cast) {
		try(Socket sc = new Socket("10.0.2.2", 11000)){
		//try(Socket sc = new Socket("10.132.25.189", 11000)){
			DataOutputStream dOut = new DataOutputStream(sc.getOutputStream());
			DataInputStream dIn = new DataInputStream(sc.getInputStream());
			dOut.write(request.serialize());
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
		try {

			super.onPostExecute(serializable);
			if(this.req.function=="getInfoAtraccions")ma.setResult(serializable,clas, (Integer) req.args[0]);
			else ma.setResult(serializable,clas,0);
		}
		catch (Exception e){
			Log.e("E","CACA",e);
		}
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

