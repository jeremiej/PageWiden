package com.example.pagewiden.fragments;

import com.example.pagewiden.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MaSpherePelucheFragment extends Fragment {
	View v;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		v = inflater.inflate(R.layout.fragment_ma_sphere_peluche, container, false);	
		
		return v;
	}
}
