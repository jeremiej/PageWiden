package com.example.pagewiden.fragments;

import java.util.ArrayList;

import com.example.pagewiden.EditionScenarioNomActivity;
import com.example.pagewiden.R;
import com.example.pagewiden.ScenarioDetailsActivity;
import com.example.pagewiden.adapters.CustomGridViewAdapter;
import com.example.pagewiden.model.ScenarioList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MonStudioFragmentContainer extends Fragment {
	
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
				Intent i;
				if(getCount()-1 == position){
					i = new Intent(context, EditionScenarioNomActivity.class);
					i.putExtra("mode", ScenarioDetailsActivity.MODE_CREATE);
				}else{
					i = new Intent(context, ScenarioDetailsActivity.class);
	                i.putExtra("id", position);
				}                
                startActivityForResult(i, 0);
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

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
	}

}
