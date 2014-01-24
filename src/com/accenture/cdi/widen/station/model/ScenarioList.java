package com.accenture.cdi.widen.station.model;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.accenture.cdi.widen.station.R;

public class ScenarioList {
	private Context mAppContext;
	private ArrayList<Object> mScenarioArray;
	private static ScenarioList sScenarioList;
	
	public ScenarioList(Context c) {
		super();
		mAppContext = c;
		Resources res = mAppContext.getResources();
		mScenarioArray = new ArrayList<Object>();
		
		Bitmap horlogeIcon = BitmapFactory.decodeResource(res, R.drawable.horloge);
		Bitmap tasseIcon = BitmapFactory.decodeResource(res, R.drawable.tasse);
		Bitmap ampouleIcon = BitmapFactory.decodeResource(res, R.drawable.ampoule);
		
		Bitmap indicateurBleu = BitmapFactory.decodeResource(res, R.drawable.indicateur_bleu);
		
		String scenarioTitle1 = "R�veil en douceur";
		String scenarioDescription1 = "Commencez la journ�e tranquillement en vous r�veillant sur une lumi�re douce. Votre caf� est pr�t!";
		String scenarioActivite1 = "Activ�";
		
		Scenario scenario1 = new Scenario();
		scenario1.setScenarioTitle(scenarioTitle1);
		scenario1.setScenarioIcon(tasseIcon);
		scenario1.setScenarioDescription(scenarioDescription1);
		scenario1.setScenarioActivite(scenarioActivite1);
		scenario1.setScenarioIndicateur(indicateurBleu);
		
		ScenarioBlock scenarioDeclencheur = new ScenarioBlock();
		
		MyObject oHorloge = new MyObject();
		oHorloge.setId(3);
		oHorloge.setName("Horloge");
		oHorloge.setIcon(horlogeIcon);
		
		MyObjectAction aHorloge = new MyObjectAction();
		aHorloge.setId(1);
		aHorloge.setLabel("");
		
		MyObjectParam pHorloge0 = new MyObjectParam();
		pHorloge0.setId(7);
		pHorloge0.setLabel("08h");
		
		scenarioDeclencheur.setMyObject(oHorloge);
		scenarioDeclencheur.setMyObjectAction(aHorloge);
		scenarioDeclencheur.setMyObjectParam(pHorloge0);
		
		scenario1.setDeclencheur(scenarioDeclencheur);
		
		ScenarioBlock scenarioCafetiere = new ScenarioBlock();
		
		MyObject oCafetiere = new MyObject();
		oCafetiere.setId(2);
		oCafetiere.setName("Cafeti�re");
		oCafetiere.setIcon(tasseIcon);
		
		MyObjectAction aCafetiere = new MyObjectAction();
		aCafetiere.setId(1);
		aCafetiere.setLabel("Servir");
		
		MyObjectParam pCafetiere1 = new MyObjectParam();
		pCafetiere1.setId(1);
		pCafetiere1.setLabel("Expresso");
		
		scenarioCafetiere.setMyObject(oCafetiere);
		scenarioCafetiere.setMyObjectAction(aCafetiere);
		scenarioCafetiere.setMyObjectParam(pCafetiere1);
		
		scenario1.addBlocks(scenarioCafetiere);
		
		ScenarioBlock scenarioAmpoule = new ScenarioBlock();
		
		MyObject oAmpoule = new MyObject();
		oAmpoule.setId(1);
		oAmpoule.setName("Ampoule");
		oAmpoule.setIcon(ampouleIcon);
		
		MyObjectAction aAmpoule = new MyObjectAction();
		aAmpoule.setId(1);
		aAmpoule.setLabel("Allumer");
		
		MyObjectParam pAmpoule1 = new MyObjectParam();
		pAmpoule1.setId(2);
		pAmpoule1.setLabel("Progressivement sur 30secondes");
		
		scenarioAmpoule.setMyObject(oAmpoule);
		scenarioAmpoule.setMyObjectAction(aAmpoule);
		scenarioAmpoule.setMyObjectParam(pAmpoule1);
		
		scenario1.addBlocks(scenarioAmpoule);
		
		this.addScenario(scenario1);
				
//		String scenarioTitle2 = "Au boulot!";
//		String scenarioDescription2 = "Il ne faut pas �tre en retard, votre r�veil sonnera � sa puissance maximum pendant 5 minutes. Mais votre salle de bain sera d�j� chauff�e avant que vous n'y arriviez!";
//		String scenarioActivite2 = "D�sactiv�";
//		
//		Scenario scenario2 = new Scenario();
//		scenario2.setScenarioTitle(scenarioTitle2);
//		scenario2.setScenarioIcon(horlogeIcon);
//		scenario2.setScenarioDescription(scenarioDescription2);
//		scenario2.setScenarioActivite(scenarioActivite2);
//		scenario2.setScenarioIndicateur(indicateurGris);
//		
//		this.addScenario(scenario2);
//		
//		String scenarioTitle3 = "On ferme tout";
//		String scenarioDescription3 = "Quand vous partez en vacances ou simplement quand vous allez au lit, il faut penser � tout fermer.";
//		String scenarioActivite3 = "D�sactiv�";
//		
//		Scenario scenario3 = new Scenario();
//		scenario3.setScenarioTitle(scenarioTitle3);
//		scenario3.setScenarioIcon(verrouIcon);
//		scenario3.setScenarioDescription(scenarioDescription3);
//		scenario3.setScenarioActivite(scenarioActivite3);
//		scenario3.setScenarioIndicateur(indicateurGris);
//		
//		this.addScenario(scenario3);
//		
//		String scenarioTitle4 = "A table!";
//		String scenarioDescription4 = "Les estomacs grondent, il n'y a plus de temps � perdre. Le four est chaud et l'eau boue.";
//		String scenarioActivite4 = "En cours";
//		
//		Scenario scenario4 = new Scenario();
//		scenario4.setScenarioTitle(scenarioTitle4);
//		scenario4.setScenarioIcon(repasIcon);
//		scenario4.setScenarioDescription(scenarioDescription4);
//		scenario4.setScenarioActivite(scenarioActivite4);
//		scenario4.setScenarioIndicateur(indicateurVert);
//		
//		this.addScenario(scenario4);
		
		this.addButtonAddScenario();
	}	
	
	public Context getAppContext() {
		return mAppContext;
	}

	public static ScenarioList get(Context c){
		if(sScenarioList == null){
			sScenarioList = new ScenarioList(c.getApplicationContext());
		}
		return sScenarioList;
	}

	public ArrayList<Object> getScenarioArray() {
		return mScenarioArray;
	}
	
	public void setScenarioArray(ArrayList<Object> scenarioArray) {
		mScenarioArray = scenarioArray;
	}

	public void addScenario(Scenario s){
		mScenarioArray.add(s);
	}
	
	public int getNbScenario(){
		return this.getScenarioArray().size();
	}
	
	public void removeLastScenario(){
		ArrayList<Object> arrayList = this.getScenarioArray();
		arrayList.remove(this.getNbScenario()-1);
	}
	
	public void addButtonAddScenario(){
		Bitmap addIcon = BitmapFactory.decodeResource(this.getAppContext().getResources(), R.drawable.ic_action_new);
		String scenarioAddTitle = mAppContext.getResources().getString(R.string.scenario_add_title);
		Scenario scenarioAdd = new Scenario();
		scenarioAdd.setScenarioDescription(scenarioAddTitle);
		scenarioAdd.setScenarioIcon(addIcon);
		
		this.addScenario(scenarioAdd);
	}
	
	public int getSize(){
		return this.mScenarioArray.size();
	}
	
	public boolean isDownloaded(String scenarioName){
		for (int i = 0; i < mScenarioArray.size(); i++) {
			Scenario currentScenario = (Scenario)mScenarioArray.get(i);
			if(scenarioName.equals(currentScenario.getScenarioTitle())){
				return true;
			}
		}
		return false;
	}
}
