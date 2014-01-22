package com.example.pagewiden.model;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class MyObject {
	private int mId;
	private String mName;
	private Bitmap mIcon;
	private ArrayList<Object> mActionList;
	private boolean isInRealm;
	
	public MyObject(){
		super();
		mActionList = new ArrayList<Object>();
	}

	public int getId() {
		return mId;
	}

	public void setId(int id) {
		mId = id;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public Bitmap getIcon() {
		return mIcon;
	}

	public void setIcon(Bitmap icon) {
		mIcon = icon;
	}

	public ArrayList<Object> getActionList() {
		return mActionList;
	}
	
	public void addAction(MyObjectAction a){
		mActionList.add(a);
	}

	public boolean isInRealm() {
		return isInRealm;
	}

	public void setInRealm(boolean isInRealm) {
		this.isInRealm = isInRealm;
	}
}
