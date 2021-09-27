package com.drawgreen.corpcollector.dto;

import java.sql.Date;

public class MemberDTO {
	private String id;
	private String pw;
	private String pw_chk;
	private String name;
	private String email;
	private Date birth;
	private String gender;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPw_chk() {
		return pw_chk;
	}
	public void setPw_chk(String pw_chk) {
		this.pw_chk = pw_chk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
