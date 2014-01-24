package com.accenture.cdi.widen.station.model;

import android.content.Context;

public class User {
	private String mLogin;
	private static User sUser;
	@SuppressWarnings("unused")
	private Context mAppContext;
	
	private User(Context appContext){
		mAppContext = appContext;
	}
	
	public static User get(Context c){
		if(sUser == null){
			sUser = new User(c.getApplicationContext());
		}
		return sUser;
	}
	
	public String getLogin(){
		return mLogin;
	}
	
	public void setLogin(String login){
		mLogin = login;
	}
}
