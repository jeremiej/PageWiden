package com.example.pagewiden.adapters;

import java.util.ArrayList;

import com.example.pagewiden.R;
import com.example.pagewiden.model.Scenario;
import com.example.pagewiden.model.ScenarioList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomGridViewAdapter extends ArrayAdapter<Object> {

    private Context context;
    int layoutResourceId;
    ArrayList<Object> data = new ArrayList<Object>();

    public CustomGridViewAdapter(Context context, int layoutResourceId, ArrayList<Object> data) {
    	super(context, layoutResourceId, data);
    	this.layoutResourceId = layoutResourceId;
    	this.context = context;
    	this.data = data;
    }

    public int getCount() {
    	ScenarioList scenarioList = ScenarioList.get(context);
        return scenarioList.nbScenario();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		RecordHolder holder = null;
		
		Scenario scenario = (Scenario)data.get(position);

		if (row == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new RecordHolder();
			ScenarioList scenarioList = ScenarioList.get(context);
			if(scenarioList.getSize()-1==position){
				holder.scenarioTitle = (TextView) row.findViewById(R.id.scenario_title);
				holder.scenarioIcon = (ImageView) row.findViewById(R.id.scenario_icon);
				holder.scenarioIcon.setAlpha(0.45f);
				holder.scenarioIcon.setPadding(180, 30, 0, 10);
				holder.scenarioDescription = (TextView) row.findViewById(R.id.scenario_description);
				holder.scenarioDescription.setPadding(90, 0, 0, 10);
				holder.scenarioDescription.setTextSize(25);
				holder.scenarioDescription.setTextColor(Color.parseColor("#7d8083"));
				holder.scenarioDescription.setAlpha(0.45f);
				holder.scenarioActivite = (TextView) row.findViewById(R.id.scenario_activite);
				holder.scenarioIndicateur = (ImageView) row.findViewById(R.id.scenario_indicateur);
			}else{
				holder.scenarioTitle = (TextView) row.findViewById(R.id.scenario_title);
				holder.scenarioTitle.setPadding(10, 0, 10, 0);
				holder.scenarioTitle.setBackgroundColor(Color.parseColor("#d2d2d2"));
				holder.scenarioIcon = (ImageView) row.findViewById(R.id.scenario_icon);
				holder.scenarioIcon.setPadding(10, 10, 10, 0);
				holder.scenarioDescription = (TextView) row.findViewById(R.id.scenario_description);
				holder.scenarioDescription.setPadding(10, 10, 10, 10);
				holder.scenarioActivite = (TextView) row.findViewById(R.id.scenario_activite);
				holder.scenarioActivite.setPadding(0, 0, 7, 10);
				holder.scenarioIndicateur = (ImageView) row.findViewById(R.id.scenario_indicateur);
				holder.scenarioIndicateur.setPadding(0, 0, 10, 11);
			}
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}
	
		holder.scenarioTitle.setText(scenario.getScenarioTitle());
		holder.scenarioIcon.setImageBitmap(scenario.getScenarioIcon());
		holder.scenarioDescription.setText(scenario.getScenarioDescription());
		holder.scenarioActivite.setText(scenario.getScenarioActivite());
		holder.scenarioIndicateur.setImageBitmap(scenario.getScenarioIndicateur());
		return row;
	}
	
	static class RecordHolder {
		TextView scenarioTitle;
		ImageView scenarioIcon;
		TextView scenarioDescription;
		TextView scenarioActivite;
		ImageView scenarioIndicateur;
	}
    
}