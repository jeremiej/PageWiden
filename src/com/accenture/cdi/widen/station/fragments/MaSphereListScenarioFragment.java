package com.accenture.cdi.widen.station.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accenture.cdi.widen.station.R;
import com.accenture.cdi.widen.station.adapters.CustomListViewActivScenarioAdapter;
import com.accenture.cdi.widen.station.model.Scenario;
import com.accenture.cdi.widen.station.model.ScenarioList;

public class MaSphereListScenarioFragment extends Fragment {
	View v;
	CustomListViewActivScenarioAdapter activScenarioAdapter;
	private ArrayList<Object> activScenarioArray;
	private ArrayList<Object> scenarioArray;
	private LinearLayout activeScenarioTable;
	private TextView noActivScenario;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		v = inflater.inflate(R.layout.fragment_ma_sphere_list_scenario, container, false);	
		
		ScenarioList scenarioList = ScenarioList.get(getActivity());
		scenarioArray = scenarioList.getScenarioArray();
		activScenarioArray = new ArrayList<Object>();
		noActivScenario = (TextView)v.findViewById(R.id.no_activ_scenario);
		activeScenarioTable = (LinearLayout) v.findViewById(R.id.scenario_table);

		fillActiveScenarioList();
		
		this.activScenarioAdapter = new CustomListViewActivScenarioAdapter(getActivity(), R.layout.activ_scenario_list_item, activScenarioArray);
		generateScenarioItemViews();

		return v;
	}

	private void fillActiveScenarioList() {
		activScenarioArray.clear();
		for (int i = 0; i < scenarioArray.size()-1; i++) {
			Scenario currentScenario = (Scenario)scenarioArray.get(i);
			if(currentScenario.getScenarioActivite().equals("Activ�") || currentScenario.getScenarioActivite().equals("En cours")){
				activScenarioArray.add(currentScenario);
			}
		}
	}

	private void generateScenarioItemViews() {
		activeScenarioTable.removeAllViews();
		View myView = null;
		if (this.activScenarioAdapter.getCount() == 0) {
			noActivScenario.setVisibility(View.VISIBLE);
		} else {
			for (int i = 0; i < this.activScenarioAdapter.getCount(); i++) {
				myView = this.activScenarioAdapter.getView(i, null, activeScenarioTable);
				activeScenarioTable.addView(myView);
			}
			noActivScenario.setVisibility(View.GONE);
		}
	}

	@Override
	public void onResume() {
		refreshScenarioList();
		super.onResume();
	}

	public void refreshScenarioList() {
		if (this.isResumed()) {
			fillActiveScenarioList();
			this.activScenarioAdapter.setData(activScenarioArray);
			generateScenarioItemViews();
		}
	}
}
