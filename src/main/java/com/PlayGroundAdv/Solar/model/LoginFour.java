package com.PlayGroundAdv.Solar.model;

import java.util.TimeZone;

public class LoginFour {

	private String email;
	private String password;
	private String ipAdress;
	private String timeZone;

	public LoginFour() {
		super();
	}

	public LoginFour(String email, String password, String ipAdress, String timeZone) {
		super();
		this.email = email;
		this.password = password;
		this.ipAdress = ipAdress;
		this.timeZone = timeZone;
	}
//        
//        public LoginFour(String email, String password) {
//		super();
////		this.email = email;
////		this.password = password;
//		this.ipAdress = ipAdress;
//		this.timeZone = timeZone;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

}
