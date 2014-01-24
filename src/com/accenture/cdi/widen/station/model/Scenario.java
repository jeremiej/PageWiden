package com.accenture.cdi.widen.station.model;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class Scenario {
	String mScenarioTitle;
	Bitmap mScenarioIcon;
	String mScenarioDescription;
	String mScenarioActivite;
	Bitmap mScenarioIndicateur;
	boolean isDeclenchementUtilisateur;
	ScenarioBlock mDeclencheur;
	ArrayList<Object> mBlocks;
	
	public Scenario() {
		super();
		mBlocks = new ArrayList<Object>();
	}

	public String getScenarioTitle() {
		return mScenarioTitle;
	}

	public void setScenarioTitle(String scenarioTitle) {
		mScenarioTitle = scenarioTitle;
	}

	public Bitmap getScenarioIcon() {
		return mScenarioIcon;
	}

	public void setScenarioIcon(Bitmap scenarioIcon) {
		mScenarioIcon = scenarioIcon;
	}

	public String getScenarioDescription() {
		return mScenarioDescription;
	}

	public void setScenarioDescription(String scenarioDescription) {
		mScenarioDescription = scenarioDescription;
	}

	public String getScenarioActivite() {
		return mScenarioActivite;
	}

	public void setScenarioActivite(String scenarioActivite) {
		mScenarioActivite = scenarioActivite;
	}

	public Bitmap getScenarioIndicateur() {
		return mScenarioIndicateur;
	}

	public void setScenarioIndicateur(Bitmap scenarioIndicateur) {
		mScenarioIndicateur = scenarioIndicateur;
	}

	public boolean isDeclenchementUtilisateur() {
		return isDeclenchementUtilisateur;
	}

	public void setDeclenchementUtilisateur(boolean isDeclenchementUtilisateur) {
		this.isDeclenchementUtilisateur = isDeclenchementUtilisateur;
	}

	public ScenarioBlock getDeclencheur() {
		return mDeclencheur;
	}

	public void setDeclencheur(ScenarioBlock declencheur) {
		mDeclencheur = declencheur;
	}

	public ArrayList<Object> getBlocks() {
		return mBlocks;
	}	
	
	public void setBlocks(ArrayList<Object> blocks) {
		mBlocks = blocks;
	}

	public void addBlocks(ScenarioBlock s){
		mBlocks.add(s);
	}
	
	public int getBlockArraySize(){
		return mBlocks.size();
	}
	
	public void removeBlock(int position){
		mBlocks.remove(position);
	}
}
