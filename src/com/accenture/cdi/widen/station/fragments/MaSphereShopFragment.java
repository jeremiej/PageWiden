package com.accenture.cdi.widen.station.fragments;

import com.accenture.cdi.widen.station.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MaSphereShopFragment extends Fragment {
	View v;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {	
		v = inflater.inflate(R.layout.fragment_shop_recipe, container, false);	
		
		return v;
	}
}
