package com.example.pagewiden.fragments;

import java.util.ArrayList;

import com.example.pagewiden.MainActivity;
import com.example.pagewiden.R;
import com.example.pagewiden.model.MyObject;
import com.example.pagewiden.model.MyObjectAction;
import com.example.pagewiden.model.MyObjectList;
import com.example.pagewiden.model.MyObjectParam;
import com.example.pagewiden.model.Scenario;
import com.example.pagewiden.model.ScenarioBlock;
import com.example.pagewiden.model.ScenarioList;
import com.example.pagewiden.model.ScenarioListDownloadable;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MaSpherePelucheFragment extends Fragment {
	View v;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		v = inflater.inflate(R.layout.fragment_ma_sphere_peluche, container, false);	
		
		MyObjectList objectList = MyObjectList.get(getActivity());
		ArrayList<Object> objectArray = objectList.getMyObjectArray();
		
		MyObject peluche = (MyObject) objectArray.get(3);
		
		TextView tvLaunchVideo = (TextView)v.findViewById(R.id.peluche_launch_video);
		Button btAddScenario = (Button)v.findViewById(R.id.peluche_add_scenario);
		
		ScenarioListDownloadable scenarioListDownloadable = ScenarioListDownloadable.get(getActivity());
		
		if(!scenarioListDownloadable.isScenarioInList("Racontes moi une histoire")){
			tvLaunchVideo.setVisibility(8);
			btAddScenario.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					ScenarioListDownloadable scenarioList = ScenarioListDownloadable.get(getActivity());
					if(!scenarioList.isScenarioInList("Racontes moi une histoire"))
						addScenarioPeluche();
					Intent i = new Intent(getActivity(), MainActivity.class);
					i.putExtra("targetTab", 2);
					startActivity(i);
				}
			});
		}else{
			btAddScenario.setVisibility(8);
		}
		
		return v;
	}
	
	public void addScenarioPeluche(){
		
		String scenarioTitle = "Racontes moi ...";
		String scenarioDescription = "Laissez Zouzou l'ours lire ce livre � vos enfants";
		String scenarioActivite = "Activ�";
		
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
		aPeluche.setLabel("D�tecter");
		
		MyObjectParam pPeluche = new MyObjectParam();
		pPeluche.setId(1);
		pPeluche.setLabel("Livre connect�");
		
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
		aPeluche1.setLabel("Envoyer");
		
		MyObjectParam pPeluche1 = new MyObjectParam();
		pPeluche1.setId(1);
		pPeluche1.setLabel("Vid�o � tablette");
		
		scenarioBlock.setMyObject(oPeluche1);
		scenarioBlock.setMyObjectAction(aPeluche1);
		scenarioBlock.setMyObjectParam(pPeluche1);
		
		scenario.addBlocks(scenarioBlock);
		
		ScenarioListDownloadable scenarioList = ScenarioListDownloadable.get(getActivity());
		
		scenarioList.addScenario(scenario);
	}
}
