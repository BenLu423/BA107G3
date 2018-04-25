package com.evep.model;

	import java.sql.Date;

	public class EvepVO implements java.io.Serializable{
				
				private String mem_no;
				private String eve_no;
				private String evep_sts;
				private byte[] evep_qr;
				public String getMem_no() {
					return mem_no;
				}
				public void setMem_no(String mem_no) {
					this.mem_no = mem_no;
				}
				public String getEve_no() {
					return eve_no;
				}
				public void setEve_no(String eve_no) {
					this.eve_no = eve_no;
				}
				public String getEvep_sts() {
					return evep_sts;
				}
				public void setEvep_sts(String evep_sts) {
					this.evep_sts = evep_sts;
				}
				public byte[] getEvep_qr() {
					return evep_qr;
				}
				public void setEvep_qr(byte[] evep_qr) {
					this.evep_qr = evep_qr;
				}
				
				
				
				
				
				
			

		}




