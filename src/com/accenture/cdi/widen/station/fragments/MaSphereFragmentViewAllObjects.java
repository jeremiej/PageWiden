package com.accenture.cdi.widen.station.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.accenture.cdi.widen.station.R;
import com.accenture.cdi.widen.station.model.MyObject;
import com.accenture.cdi.widen.station.model.MyObjectList;

public class MaSphereFragmentViewAllObjects extends Fragment {
	
	private MaSphereWebcamFragment mWebcamFragment = null;
	private MaSphereBarometreFragment mBarometreFragment = null;
	private MaSpherePorteFragment mPorteFragment = null;
	private MaSpherePelucheFragment mPelucheFragment = null;
	private MaSphereListScenarioFragment mListScenarioFragment = null;
	private MaSphereTvFragment mTvFragment = null;

	// Qeo attributes
	public static final int TOAST_ID_TEDDY_HERE = 0;
	public static final int TOAST_ID_BOOK_HERE = 1;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_ma_sphere_view_all, container, false);
		
		this.mWebcamFragment = new MaSphereWebcamFragment();
		this.mBarometreFragment = new MaSphereBarometreFragment();
		this.mPorteFragment = new MaSpherePorteFragment();
		this.mListScenarioFragment = new MaSphereListScenarioFragment();
		this.mTvFragment = new MaSphereTvFragment();
		
		FragmentTransaction transaction = getFragmentManager().beginTransaction();		
		transaction.add(R.id.webcam_container, this.mWebcamFragment);
		transaction.add(R.id.barometre_container, this.mBarometreFragment);
		transaction.add(R.id.porte_container, this.mPorteFragment);
		transaction.add(R.id.scenario_list_container, this.mListScenarioFragment);
    	Bundle argumentsTvRecipe = new Bundle();
    	argumentsTvRecipe.putInt("newRecipe", 0);
    	mTvFragment.setArguments(argumentsTvRecipe);
		transaction.add(R.id.tv_container, this.mTvFragment);
		
		MyObjectList myObjectList = MyObjectList.get(getActivity());
		MyObject peluche = myObjectList.getMyObjectArray().get(3);
		if (peluche.isInRealm()){
	    	Bundle arguments = new Bundle();
	    	arguments.putInt("launchVideo", 0);
			this.mPelucheFragment = new MaSpherePelucheFragment();
	    	mPelucheFragment.setArguments(arguments);
			transaction.add(R.id.peluche_container, this.mPelucheFragment);			
		}
	
		transaction.commit();

		return v;
	}
	
	public void displayDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

		alertDialogBuilder.setTitle("Nouvel objet d�tect� !");

		alertDialogBuilder
				.setMessage(R.string.teddy_accept_deny)
				.setCancelable(false)
				.setPositiveButton("Oui",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								MyObject peluche = MyObjectList.get(getActivity()).getMyObjectArray().get(3);
								peluche.setInRealm(true);
								
								FragmentTransaction transaction = getFragmentManager().beginTransaction();
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

	public void onTeddyHere() {
		MyObjectList myObjectList = MyObjectList.get(getActivity());
		MyObject peluche = myObjectList.getMyObjectArray().get(3);
		if(!peluche.isInRealm()){
			displayDialog();
		}
	}

	public void onBookHere() {
    	Bundle arguments = new Bundle();
    	arguments.putInt("launchVideo", 1);
    	FragmentTransaction transaction = null;
    	mPelucheFragment = new MaSpherePelucheFragment();
    	mPelucheFragment.setArguments(arguments);
    	transaction = getFragmentManager().beginTransaction();
    	transaction.replace(R.id.peluche_container, mPelucheFragment);
		transaction.commitAllowingStateLoss();
	}

	public void refreshScenarioListFragment() {
		if (this.mListScenarioFragment != null) {
			this.mListScenarioFragment.refreshScenarioList();
		}
	}
	
	public void onTvRecipeHere(){
    	Bundle arguments = new Bundle();
    	arguments.putInt("newRecipe", 1);
    	FragmentTransaction transaction = null;
    	mTvFragment = new MaSphereTvFragment();
    	mTvFragment.setArguments(arguments);
    	transaction = getFragmentManager().beginTransaction();
    	transaction.replace(R.id.tv_container, mTvFragment);
		transaction.commitAllowingStateLoss();
	}
}
