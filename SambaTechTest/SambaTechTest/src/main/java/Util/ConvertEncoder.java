/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author Deyvison
 */


import java.io.*;
import java.net.*;
 
public class ConvertEncoder {
    
	private static int startEncodingWorkflow() {
		// replace ID and key with your own
		String userID = "98419";
		String userKey = "b9fc3d2026dd2db1966fdfdb9d4ca143";
		String mediaID = "1";
		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version='1.0'?>");
		xml.append("<query>");
		xml.append("<userid>"+ userID+ "</userid>");
		xml.append("<userkey>"+ userKey+ "</userkey>");
		xml.append("<action>GetMediaInfo</action>");
		xml.append("<mediaid>" +mediaID +"</mediaid>");
		xml.append("</query>");
 
		URL server = null;
 
		try {
			String url = "http://manage.encoding.com";
			
			server = new URL(url);
 
		} catch (MalformedURLException mfu) {
			mfu.printStackTrace();
			return 0;
		}
 
		try {
			String sRequest = "xml="+ URLEncoder.encode(xml.toString(), "UTF8");
			//System.out.println("Open new connection to tunnel");
			HttpURLConnection urlConnection = (HttpURLConnection) server.openConnection();
			urlConnection.setRequestMethod( "POST" );
			urlConnection.setDoOutput(true);
			urlConnection.setConnectTimeout(60000);
			urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			BufferedWriter out = new BufferedWriter( new OutputStreamWriter( urlConnection.getOutputStream() ) );
			out.write(sRequest);
			out.flush();
			out.close();
			urlConnection.connect();
			InputStream is = urlConnection.getInputStream();
			String str = urlConnection.getResponseMessage();
			System.out.println("Response:"+ urlConnection.getResponseCode());
			System.out.println("Response:"+urlConnection.getResponseMessage());
			StringBuffer strbuf = new StringBuffer();
			byte[] buffer = new byte[1024 * 4];
 
			try {
				int n = 0;
				while (-1 != (n = is.read(buffer))) {
					strbuf.append(new String(buffer, 0, n));
				}
 
				is.close();
 
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
 
			System.out.println(strbuf.toString()); 
 
		} catch (Exception exp) {
			exp.printStackTrace();
		}
 
		return 0;
	}
 
	public static void main (String[] args) {
		startEncodingWorkflow();
	}
}
	
