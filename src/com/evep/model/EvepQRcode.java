package com.evep.model;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class EvepQRcode {
	String mem_no;
	String eve_no;

	public EvepQRcode(String mem_no, String eve_no) {
		this.mem_no = mem_no;
		this.eve_no = eve_no;
	}

		public byte[] generate(){
		String imageUrl = "http://chart.apis.google.com/chart?cht=qr&chl=eve_no:"+eve_no+",mem_no:"+mem_no+"&chs=500x500";
System.out.println(imageUrl);
		InputStream is = null;
		ByteArrayOutputStream aos = null;
		try {
			URL url = new URL(imageUrl);
			is = url.openStream();
			aos = new ByteArrayOutputStream();
			byte[] b = new byte[8*1024];
			int length;
			while ((length = is.read(b)) != -1) {
				aos.write(b, 0, length);
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			try {
				aos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return aos.toByteArray();
	}
	
}
