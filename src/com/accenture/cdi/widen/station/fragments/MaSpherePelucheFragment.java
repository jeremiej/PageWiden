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
	
	public int getVideoMode() {
		return getArguments().getInt("launchVideo", 0);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		v = inflater.inflate(R.layout.fragment_ma_sphere_peluche, container, false);	
		
		TextView tvLaunchVideo = (TextView)v.findViewById(R.id.peluche_launch_video);
		Button btAddScenario = (Button)v.findViewById(R.id.peluche_add_scenario);
//		final VideoView vvVideoPeluche = (VideoView) v.findViewById(R.id.peluche_video);

		ScenarioList scenarioList = ScenarioList.get(getActivity());
		if(!scenarioList.isDownloaded("Racontes moi ...")){
			tvLaunchVideo.setVisibility(8);
//			vvVideoPeluche.setVisibility(8);
			btAddScenario.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					ScenarioListDownloadable scenarioList = ScenarioListDownloadable.get(getActivity());
					if(!scenarioList.isScenarioInList("Racontes moi ..."))
						addScenarioPeluche();
					Intent i = new Intent(getActivity(), MainActivity.class);
					i.putExtra("targetTab", 2);
					startActivity(i);
				}
			});

		}else{
			if(getVideoMode()==1){		
//				tvLaunchVideo.setVisibility(8);
				btAddScenario.setVisibility(8);
//				LinearLayout llPeluche = (LinearLayout) v.findViewById(R.id.layout_peluche);
//				llPeluche.setBackgroundColor(Color.parseColor("#000000"));  
//			    String uriPath = "android.resource://com.example.pagewiden/"+R.raw.k;
//		        Uri uri = Uri.parse(uriPath);
//		        vvVideoPeluche.setVideoURI(uri);
//		        vvVideoPeluche.start();
//		        vvVideoPeluche.setOnTouchListener(new OnTouchListener() {
//					
//					@Override
//					public boolean onTouch(View v, MotionEvent event) {
//						if(vvVideoPeluche.isPlaying()){
//							vvVideoPeluche.pause();
//						}else{
//							vvVideoPeluche.start();
//						}
//						return false;
//					}
//				});
			}else{
//				vvVideoPeluche.setVisibility(8);
				btAddScenario.setVisibility(8);
			}			
		}
//		
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