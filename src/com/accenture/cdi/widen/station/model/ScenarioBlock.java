package com.accenture.cdi.widen.station.model;

public class ScenarioBlock {
	private MyObject mMyObject;
	private MyObjectAction mMyObjectAction;
	private MyObjectParam mMyObjectParam;
	
	public ScenarioBlock(){		
	}

	public MyObject getMyObject() {
		return mMyObject;
	}

	public void setMyObject(MyObject myObject) {
		mMyObject = myObject;
	}

	public MyObjectAction getMyObjectAction() {
		return mMyObjectAction;
	}

	public void setMyObjectAction(MyObjectAction myObjectAction) {
		mMyObjectAction = myObjectAction;
	}

	public MyObjectParam getMyObjectParam() {
		return mMyObjectParam;
	}

	public void setMyObjectParam(MyObjectParam myObjectParam) {
		mMyObjectParam = myObjectParam;
	}	
	
}
