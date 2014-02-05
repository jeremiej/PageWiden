package com.accenture.cdi.widen.station.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.accenture.cdi.widen.station.MainActivity;
import com.accenture.cdi.widen.station.R;
import com.accenture.cdi.widen.station.model.MyObject;
import com.accenture.cdi.widen.station.model.MyObjectAction;
import com.accenture.cdi.widen.station.model.MyObjectParam;
import com.accenture.cdi.widen.station.model.Scenario;
import com.accenture.cdi.widen.station.model.ScenarioBlock;
import com.accenture.cdi.widen.station.model.ScenarioList;
import com.accenture.cdi.widen.station.model.ScenarioListDownloadable;

public class MaSpherePelucheFragment extends Fragment {
	View v;
	TextView tvTeddyReady = null;
	Button btAddScenario = null;

	public int getVideoMode() {
		return getArguments().getInt("launchVideo", 0);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		v = inflater.inflate(R.layout.fragment_ma_sphere_peluche, container, false);	

		this.tvTeddyReady = (TextView) v.findViewById(R.id.peluche_launch_video);
		this.btAddScenario = (Button) v.findViewById(R.id.peluche_add_scenario);

		ScenarioList scenarioList = ScenarioList.get(getActivity());
		if (!scenarioList.isDownloaded("Racontes moi ...")){
			hideTeddyReadyText();
			showDownloadButton();
			btAddScenario.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					ScenarioListDownloadable scenarioList = ScenarioListDownloadable.get(getActivity());
					if (!scenarioList.isScenarioInList("Racontes moi ...")) {
						addScenarioPeluche();
					}

//					showTeddyReadyText();
//					hideDownloadButton();
					Intent i = new Intent(getActivity(), MainActivity.class);
					i.putExtra("targetTab", 2);
					startActivity(i);
				}
			});

		} else {
			showTeddyReadyText();
			hideDownloadButton();		
		}
	
		return v;
	}

	public void showTeddyReadyText() {
		if (this.tvTeddyReady != null) {
			this.tvTeddyReady.setVisibility(View.VISIBLE);
		}
	}

	public void hideTeddyReadyText() {
		if (this.tvTeddyReady != null) {
			this.tvTeddyReady.setVisibility(View.GONE);
		}
	}

	public void showDownloadButton() {
		if (this.btAddScenario != null) {
			this.btAddScenario.setVisibility(View.VISIBLE);
		}
	}

	public void hideDownloadButton() {
		if (this.btAddScenario != null) {
			this.btAddScenario.setVisibility(View.GONE);
		}
	}

	public void addScenarioPeluche(){
		
		String scenarioTitle = "Raconte-moi ...";
		String scenarioDescription = "Faites lire un livre à vos enfants par Furby !";
		String scenarioActivite = "Activé";
		
		Scenario scenario = new Scenario();
		scenario.setScenarioTitle(scenarioTitle);
		Bitmap pelucheIcon = BitmapFactory.decodeResource(getResources(), R.drawable.peluche);
		Bitmap indicateurBleu = BitmapFactory.decodeResource(getResources(), R.drawable.indicateur_bleu);
		scenario.setScenarioIcon(pelucheIcon);
		scenario.setScenarioDescription(scenarioDescription);
		scenario.setScenarioActivite(scenarioActivite);
		scenario.setScenarioIndicateur(indicateurBleu);
		
		ScenarioBlock scenarioDeclencheur = new ScenarioBlock();
		
		MyObject oPeluche = new MyObject();
		oPeluche.setId(4);
		oPeluche.setName("Peluche");
		oPeluche.setIcon(pelucheIcon);
		
		MyObjectAction aPeluche = new MyObjectAction();
		aPeluche.setId(1);
		aPeluche.setLabel("Détecter");

		MyObjectParam pPeluche = new MyObjectParam();
		pPeluche.setId(1);
		pPeluche.setLabel("Livre connecté");
		
		scenarioDeclencheur.setMyObject(oPeluche);
		scenarioDeclencheur.setMyObjectAction(aPeluche);
		scenarioDeclencheur.setMyObjectParam(pPeluche);
		
		scenario.setDeclencheur(scenarioDeclencheur);

		ScenarioBlock scenarioBlock = new ScenarioBlock();
		
		MyObject oPeluche1 = new MyObject();
		oPeluche1.setId(4);
		oPeluche1.setName("Peluche");
		oPeluche1.setIcon(pelucheIcon);
		
		MyObjectAction aPeluche1 = new MyObjectAction();
		aPeluche1.setId(2);
		aPeluche1.setLabel("Lire le livre");
		
		MyObjectParam pPeluche1 = new MyObjectParam();
		pPeluche1.setId(1);
		pPeluche1.setLabel("En entier");
		
		scenarioBlock.setMyObject(oPeluche1);
		scenarioBlock.setMyObjectAction(aPeluche1);
		scenarioBlock.setMyObjectParam(pPeluche1);

		scenario.addBlocks(scenarioBlock);

		// block 2
		scenarioBlock = new ScenarioBlock();

		MyObject oAmpoule1 = new MyObject();
		Bitmap ampouleIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ampoule);
		oAmpoule1.setId(1);
		oAmpoule1.setName("Ampoule");
		oAmpoule1.setIcon(ampouleIcon);
		
		MyObjectAction aAmpoule1 = new MyObjectAction();
		aAmpoule1.setId(2);
		aAmpoule1.setLabel("Eteindre");
		
		MyObjectParam pAmpoule1 = new MyObjectParam();
		pAmpoule1.setId(2);
		pAmpoule1.setLabel("Sur 30 secondes");
		
		scenarioBlock.setMyObject(oAmpoule1);
		scenarioBlock.setMyObjectAction(aAmpoule1);
		scenarioBlock.setMyObjectParam(pAmpoule1);

		scenario.addBlocks(scenarioBlock);

		//
		ScenarioListDownloadable scenarioList = ScenarioListDownloadable.get(getActivity());

		scenarioList.addScenario(scenario);
	}
}
