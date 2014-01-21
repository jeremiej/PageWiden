package com.example.pagewiden.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pagewiden.R;
import com.example.pagewiden.adapters.CustomListViewActivScenarioAdapter;
import com.example.pagewiden.model.Scenario;
import com.example.pagewiden.model.ScenarioList;

public class MaSphereListScenarioFragment extends Fragment {
	View v;
	CustomListViewActivScenarioAdapter activScenarioAdapter;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		v = inflater.inflate(R.layout.fragment_ma_sphere_list_scenario, container, false);	
		
		ScenarioList scenarioList = ScenarioList.get(getActivity());
		ArrayList<Object> scenarioArray = scenarioList.getScenarioArray();
		ArrayList<Object> activScenarioArray = new ArrayList<Object>();
		
		for (int i = 0; i < scenarioArray.size()-1; i++) {
			Scenario currentScenario = (Scenario)scenarioArray.get(i);
			if(currentScenario.getScenarioActivite().equals("Activé") || currentScenario.getScenarioActivite().equals("En cours")){
				activScenarioArray.add(currentScenario);
			}
		}
		
		ListView scenarioListView = (ListView)v.findViewById(R.id.scenario_listview);
		TextView noActivScenario = (TextView)v.findViewById(R.id.no_activ_scenario);
		
		if(activScenarioArray.size()>0){
			noActivScenario.setVisibility(8);
			this.activScenarioAdapter = new CustomListViewActivScenarioAdapter(getActivity(), 
					R.layout.activ_scenario_list_item, activScenarioArray);
			scenarioListView.setAdapter(this.activScenarioAdapter);
		}else{
			scenarioListView.setVisibility(8);
		}
						
		return v;
	}

	@Override
	public void onResume() {
		this.activScenarioAdapter.notifyDataSetChanged();
		super.onResume();
	}
}
