package com.example.pagewiden.model;

import android.content.Context;

public class NewScenario extends Scenario{
	
	private static NewScenario sNewScenario;
	@SuppressWarnings("unused")
	private Context mAppContext;
	
	public NewScenario(Context c) {
		super();
		mAppContext = c;
	}
	
	public static NewScenario get(Context c){
		if(sNewScenario == null){
			sNewScenario = new NewScenario(c.getApplicationContext());
		}
		return sNewScenario;
	}
	
	public void destroy(){
		sNewScenario = null;
	}

}
