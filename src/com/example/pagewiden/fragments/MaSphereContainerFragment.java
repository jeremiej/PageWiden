package com.example.pagewiden.fragments;

import com.example.pagewiden.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
		this.mPelucheFragment = new MaSpherePelucheFragment();
		this.mListScenarioFragment = new MaSphereListScenarioFragment();
		
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();		
		transaction.add(R.id.webcam_container, this.mWebcamFragment);
		transaction.add(R.id.barometre_container, this.mBarometreFragment);
		transaction.add(R.id.porte_container, this.mPorteFragment);
		transaction.add(R.id.peluche_container, this.mPelucheFragment);
		transaction.add(R.id.scenario_list_container, this.mListScenarioFragment);
		transaction.commit();
	    
		return v;
	}
}
