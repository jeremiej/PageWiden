package com.accenture.cdi.widen.station.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.accenture.cdi.widen.station.R;
import com.accenture.cdi.widen.station.adapters.CustomListViewMonStoreAdapter;
import com.accenture.cdi.widen.station.model.ScenarioListDownloadable;

public class MonStoreViewAllDownloadableScenarios extends Fragment {

	private MonStoreDetailsFragment nestedFragment = null;

	private ScenarioListDownloadable mScenarioDownloadableList;
	private ArrayList<Object> mScenarioDownloadableArray;

	private ListView lv = null;
	
	private CustomListViewMonStoreAdapter customListViewMonStoreAdapter;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_mon_store_view_all_downloadable_scenarios, container, false);
		this.lv = (ListView) v.findViewById(R.id.scenario_list);
		mScenarioDownloadableList = ScenarioListDownloadable.get(inflater.getContext());
		mScenarioDownloadableArray = mScenarioDownloadableList.getScenarioArray();
		
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
    	Bundle arguments = new Bundle();
    	arguments.putInt("scenarioId", 0);
    	this.nestedFragment = new MonStoreDetailsFragment();
    	this.nestedFragment.setArguments(arguments);
		transaction.add(R.id.item_detail_container, this.nestedFragment);
		transaction.commit();

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				Bundle arguments = new Bundle();
				arguments.putInt("scenarioId", position);
		    	nestedFragment = new MonStoreDetailsFragment();
		    	nestedFragment.setArguments(arguments);
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.item_detail_container, nestedFragment);
				transaction.commit();
			}
		});

		return v;

	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		customListViewMonStoreAdapter = new CustomListViewMonStoreAdapter(this.lv.getContext(), R.layout.store_list_item, mScenarioDownloadableArray);
		lv.setAdapter(customListViewMonStoreAdapter);
    }

	@Override
	public void onPause() {
		super.onPause();
		if (this.nestedFragment != null) {
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
			transaction.remove(this.nestedFragment);
			transaction.commit();
		}
	}
	
	@Override
	public void onResume() {		
		customListViewMonStoreAdapter.notifyDataSetChanged();
		super.onResume();
	}
}
