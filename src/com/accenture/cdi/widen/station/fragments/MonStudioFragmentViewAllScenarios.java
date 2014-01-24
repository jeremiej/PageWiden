package com.accenture.cdi.widen.station.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.accenture.cdi.widen.station.MainActivity;
import com.accenture.cdi.widen.station.R;
import com.accenture.cdi.widen.station.adapters.CustomGridViewAdapter;
import com.accenture.cdi.widen.station.model.ScenarioList;

public class MonStudioFragmentViewAllScenarios extends Fragment {
	
	GridView gridView;
	ArrayList<Object> gridArray = new ArrayList<Object>();

	CustomGridViewAdapter customGridAdapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.studio_grid_layout, container, false);
		
		ScenarioList scenarioList = ScenarioList.get(getActivity().getApplicationContext());
		gridArray = scenarioList.getScenarioArray();
		
		gridView = (GridView) v.findViewById(R.id.gridview_id);
		gridView.setPadding(10, 15, 10, 15);
		final Context context = this.getActivity().getApplicationContext();
		customGridAdapter = new CustomGridViewAdapter(context, R.layout.studio_gridrow_layout, gridArray);
		gridView.setAdapter(customGridAdapter);
		
		gridView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

				if (position == (getCount() - 1)) {
					((MainActivity) getActivity()).monStudioStartScenarioCreationName();
				} else {
					((MainActivity) getActivity()).monStudioViewOneScenarioDetails(position);
				}
                
            }
		});
		return v;
	}
	
	public int getCount(){		
		return gridArray.size();
	}
	
	@Override
	public void onResume() {
		ScenarioList scenarioList = ScenarioList.get(getActivity());
		gridArray = scenarioList.getScenarioArray();
		customGridAdapter = new CustomGridViewAdapter(getActivity(), R.layout.studio_gridrow_layout, gridArray);
		gridView.setAdapter(customGridAdapter);
		super.onResume();
	}

}
