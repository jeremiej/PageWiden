package com.accenture.cdi.widen.station.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.accenture.cdi.widen.station.R;
import com.accenture.cdi.widen.station.model.Scenario;

public class CustomListViewActivScenarioAdapter extends ArrayAdapter<Object> {
	private Context context;
    int layoutResourceId;
    ArrayList<Object> data = new ArrayList<Object>();

    public CustomListViewActivScenarioAdapter(Context context, int layoutResourceId, ArrayList<Object> data) {
    	super(context, layoutResourceId, data);
    	this.layoutResourceId = layoutResourceId;
    	this.context = context;
    	this.data = data;
    }

    public int getCount() {    	
        return data.size();
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

			holder.scenarioName = (TextView) row.findViewById(R.id.scenario_name);
			holder.activityIndicator = (ImageView) row.findViewById(R.id.activity_indicator);
			
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}
	
		holder.scenarioName.setText(scenario.getScenarioTitle());		
		holder.activityIndicator.setImageBitmap(scenario.getScenarioIndicateur());
		
		return row;
	}

	public void setData(ArrayList<Object> data) {
		this.data = data;
	}


	static class RecordHolder {
		TextView scenarioName;
		ImageView activityIndicator;
	}
}
