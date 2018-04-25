package com.evem.model;
import java.sql.Date;
import java.sql.Timestamp;
public class EvemVO implements java.io.Serializable{
			
			private String evemes_no;
			private String eve_no;
			private String mem_no;
			private Timestamp evemes_time;
			private String evemes_cnt;
			private Timestamp evemes_restime;
			private String evemes_rescnt;
			
			public String getEvemes_no() {
				return evemes_no;
			}
			public void setEvemes_no(String evemes_no) {
				this.evemes_no = evemes_no;
			}
			public String getEve_no() {
				return eve_no;
			}
			public void setEve_no(String eve_no) {
				this.eve_no = eve_no;
			}
			public String getMem_no() {
				return mem_no;
			}
			public void setMem_no(String mem_no) {
				this.mem_no = mem_no;
			}
			public Timestamp getEvemes_time() {
				return evemes_time;
			}
			public void setEvemes_time(Timestamp evemes_time) {
				this.evemes_time = evemes_time;
			}
			public String getEvemes_cnt() {
				return evemes_cnt;
			}
			public void setEvemes_cnt(String evemes_cnt) {
				this.evemes_cnt = evemes_cnt;
			}
			public Timestamp getEvemes_restime() {
				return evemes_restime;
			}
			public void setEvemes_restime(Timestamp evemes_restime) {
				this.evemes_restime = evemes_restime;
			}
			public String getEvemes_rescnt() {
				return evemes_rescnt;
			}
			public void setEvemes_rescnt(String evemes_rescnt) {
				this.evemes_rescnt = evemes_rescnt;
			}
			
			

	}


