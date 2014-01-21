package com.example.pagewiden.model;

import java.util.ArrayList;

public class MyObjectAction {
	private int mId;
	private String mLabel;
	private ArrayList<Object> mParamList;
	
	public MyObjectAction(){
		mParamList = new ArrayList<Object>();
	}
	
	public int getId() {
		return mId;
	}

	public void setId(int id) {
		mId = id;
	}

	public String getLabel() {
		return mLabel;
	}

	public void setLabel(String label) {
		mLabel = label;
	}

	public ArrayList<Object> getParamList() {
		return mParamList;
	}
	
	public void addParam(MyObjectParam p){
		mParamList.add(p);
	}
	
}
