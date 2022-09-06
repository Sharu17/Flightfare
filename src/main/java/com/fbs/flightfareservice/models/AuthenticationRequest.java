package com.fbs.flightfareservice.models;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {

	private String userName;
    private String password;

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //need default constructor for JSON Parsing
    public AuthenticationRequest()
    {

    }

    public AuthenticationRequest(String userName, String password) {
        this.setUsername(userName);
        this.setPassword(password);
    }
    
    @Override
	public String toString() {
		return "AuthenticationRequest [userName=" + userName + ", password=" + password + "]";
	}

}
