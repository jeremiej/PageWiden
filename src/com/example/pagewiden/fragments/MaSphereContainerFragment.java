package com.example.pagewiden.fragments;

import java.util.ArrayList;

import com.example.pagewiden.R;
import com.example.pagewiden.model.MyObject;
import com.example.pagewiden.model.MyObjectList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class MaSphereContainerFragment extends Fragment {
	
	private MaSphereWebcamFragment mWebcamFragment = null;
	private MaSphereBarometreFragment mBarometreFragment = null;
	private MaSpherePorteFragment mPorteFragment = null;
	private MaSpherePelucheFragment mPelucheFragment = null;
	private MaSphereListScenarioFragment mListScenarioFragment = null;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_ma_sphere_container, container, false);
		
		this.mWebcamFragment = new MaSphereWebcamFragment();
		this.mBarometreFragment = new MaSphereBarometreFragment();
		this.mPorteFragment = new MaSpherePorteFragment();
		this.mListScenarioFragment = new MaSphereListScenarioFragment();
		
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();		
		transaction.add(R.id.webcam_container, this.mWebcamFragment);
		transaction.add(R.id.barometre_container, this.mBarometreFragment);
		transaction.add(R.id.porte_container, this.mPorteFragment);
		transaction.add(R.id.scenario_list_container, this.mListScenarioFragment);
		
		MyObjectList myObjectList = MyObjectList.get(getActivity());
		ArrayList<Object> objectArray = myObjectList.getMyObjectArray();
		MyObject peluche = (MyObject) objectArray.get(3);
		if(peluche.isInRealm()){
	    	Bundle arguments = new Bundle();
	    	arguments.putInt("launchVideo", 0);
			this.mPelucheFragment = new MaSpherePelucheFragment();
	    	mPelucheFragment.setArguments(arguments);
			transaction.add(R.id.peluche_container, this.mPelucheFragment);			
		}
		

			FrameLayout flVideo = (FrameLayout)v.findViewById(R.id.webcam_container);
			
			flVideo.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					MyObjectList myObjectList = MyObjectList.get(getActivity());
					ArrayList<Object> objectArray = myObjectList.getMyObjectArray();
					MyObject peluche = (MyObject) objectArray.get(3);
					if(!peluche.isInRealm()){
						displayDialog();
					}
					return false;
				}
			});
		
		if(mPelucheFragment!=null){
			FrameLayout flBarometre = (FrameLayout) v.findViewById(R.id.barometre_container);		
			flBarometre.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
			    	Bundle arguments = new Bundle();
			    	arguments.putInt("launchVideo", 1);
			    	mPelucheFragment = new MaSpherePelucheFragment();
			    	mPelucheFragment.setArguments(arguments);
					FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
					transaction.replace(R.id.peluche_container, mPelucheFragment);
					transaction.commit();
					return false;
				}
			});
		}
		
		transaction.commit();
	    
		return v;
	}
	
	public void displayDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

		alertDialogBuilder.setTitle("Nouvel objet d�tect�!");

		alertDialogBuilder
				.setMessage("L'objet peluche souhaite rejoindre votre royaume, voullez vous l'ajouter?")
				.setCancelable(false)
				.setPositiveButton("Oui",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								MyObjectList myObjectList = MyObjectList.get(getActivity());
								ArrayList<Object> objectArray = myObjectList.getMyObjectArray();
								MyObject peluche = (MyObject)objectArray.get(3);
								peluche.setInRealm(true);
								objectArray.set(3, peluche);
								myObjectList.setMyObjectArray(objectArray);
								
								FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
						    	Bundle arguments = new Bundle();
						    	arguments.putInt("launchVideo", 0);
								mPelucheFragment = new MaSpherePelucheFragment();
						    	mPelucheFragment.setArguments(arguments);								
								transaction.add(R.id.peluche_container, mPelucheFragment);	
								transaction.commit();																
							}
						})
				.setNegativeButton("Non", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
}
