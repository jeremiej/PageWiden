package com.accenture.cdi.widen.station.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.accenture.cdi.widen.station.R;

public class MaSpherePorteFragment extends Fragment {
	View v;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		v = inflater.inflate(R.layout.fragment_ma_sphere_porte, container, false);	
			
		ToggleButton tbPorte = (ToggleButton)v.findViewById(R.id.toggle_porte);
		tbPorte.setChecked(true);

		final LinearLayout llPorte = (LinearLayout)v.findViewById(R.id.layout_porte);
		llPorte.setBackgroundColor(Color.parseColor("#d85142"));
		
		tbPorte.setOnCheckedChangeListener(new OnCheckedChangeListener() {			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		        	llPorte.setBackgroundColor(Color.parseColor("#d85142"));		        	
		        } else {
		        	llPorte.setBackgroundColor(Color.parseColor("#a3e78b"));
		        }
			}
		});
		
		return v;
	}
	
}
