package com.example.pagewiden.fragments;

import java.util.ArrayList;

import com.example.pagewiden.R;
import com.example.pagewiden.ScenarioDetailsActivity;
import com.example.pagewiden.adapters.CustomListViewScenarioBlockAdapter;
import com.example.pagewiden.model.MyObject;
import com.example.pagewiden.model.MyObjectParam;
import com.example.pagewiden.model.Scenario;
import com.example.pagewiden.model.ScenarioBlock;
import com.example.pagewiden.model.ScenarioList;
import com.example.pagewiden.model.ScenarioListDownloadable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MonStoreDetailsFragment extends Fragment {

	public int getShownIndex() {
		return getArguments().getInt("scenarioId", 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_store_detail, container, false);
		
		ScenarioListDownloadable scenarioListDwl = ScenarioListDownloadable.get(getActivity().getApplicationContext());
		final ArrayList<Object> scenarioArray = scenarioListDwl.getScenarioArray();
		final Scenario scenario = (Scenario)scenarioArray.get(getShownIndex());
		
		TextView tvScenarioName = (TextView)v.findViewById(R.id.scenario_name);
		tvScenarioName.setText(scenario.getScenarioTitle());	
		
		TextView tvScenarioDescription = (TextView)v.findViewById(R.id.scenario_description);
		tvScenarioDescription.setText("\" "+scenario.getScenarioDescription()+" \"");
		
		ScenarioBlock scenarioDeclencheur = (ScenarioBlock)scenario.getDeclencheur();
		MyObject declencheurObject = (MyObject)scenarioDeclencheur.getMyObject();
		
		TextView tvDeclencheurName = (TextView)v.findViewById(R.id.trigger_object_name);
		tvDeclencheurName.setText(declencheurObject.getName());
		
		ImageView ivDeclencheurIcon = (ImageView)v.findViewById(R.id.trigger_object_icon);
		ivDeclencheurIcon.setImageBitmap(declencheurObject.getIcon());
		
		MyObjectParam declencheurParam = (MyObjectParam)scenarioDeclencheur.getMyObjectParam();
		
		TextView tvDeclencheurParam = (TextView)v.findViewById(R.id.trigger_param);
		tvDeclencheurParam.setText(declencheurParam.getLabel());
		
		ListView lvBlocks = (ListView)v.findViewById(R.id.scenario_blocks_listview);
        CustomListViewScenarioBlockAdapter customBlockAdapter = new CustomListViewScenarioBlockAdapter(inflater.getContext(), 
        		R.layout.list_scenario_blocks, scenario.getBlocks());
        lvBlocks.setAdapter(customBlockAdapter);
        lvBlocks.setClickable(false);
        
        final Button buttonDownload = (Button)v.findViewById(R.id.download_button);
        
        final ScenarioList scenarioList = ScenarioList.get(inflater.getContext());
        if(scenarioList.isDownloaded(scenario.getScenarioTitle())){
        	buttonDownload.setEnabled(false);
        	buttonDownload.setClickable(false);
        }else{
        	buttonDownload.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					scenarioList.removeLastScenario();
					scenarioList.addScenario(scenario);
					scenarioList.addButtonAddScenario();	
					buttonDownload.setEnabled(false);
		        	buttonDownload.setClickable(false);
		        	
		        	Intent i = new Intent(getActivity().getApplicationContext(), ScenarioDetailsActivity.class);
		        	i.putExtra("id", scenarioList.getSize()-2);
		        	startActivity(i);
				}
			});
        }
		
	    return v;
	}
}
