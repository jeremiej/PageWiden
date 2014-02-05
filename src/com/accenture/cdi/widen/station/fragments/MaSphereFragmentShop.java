package com.accenture.cdi.widen.station.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.accenture.cdi.widen.station.R;

public class MaSphereFragmentShop extends Fragment {
	View v;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {	
		v = inflater.inflate(R.layout.fragment_shop_recipe, container, false);	
		
		return v;
	}
}
