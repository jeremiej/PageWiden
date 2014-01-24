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
import com.accenture.cdi.widen.station.model.ScenarioBlock;


public class CustomListViewScenarioBlockAdapter extends ArrayAdapter<Object> {

    private Context context;
    int layoutResourceId;
    ArrayList<Object> data = new ArrayList<Object>();

    public CustomListViewScenarioBlockAdapter(Context context, int layoutResourceId, ArrayList<Object> data) {
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
		
		ScenarioBlock scenarioBlock = (ScenarioBlock)data.get(position);

		if (row == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new RecordHolder();

			holder.objectName = (TextView) row.findViewById(R.id.scenario_obect_name);
			holder.objectIcon = (ImageView) row.findViewById(R.id.scenario_object_icon);
			holder.actionName = (TextView) row.findViewById(R.id.scenario_object_action);
			holder.paramName = (TextView) row.findViewById(R.id.scenario_object_param);
			
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}
	
		holder.objectName.setText(scenarioBlock.getMyObject().getName());
		holder.objectIcon.setImageBitmap(scenarioBlock.getMyObject().getIcon());
		holder.actionName.setText(scenarioBlock.getMyObjectAction().getLabel());
		holder.paramName.setText(scenarioBlock.getMyObjectParam().getLabel());
		return row;

	}
	
	static class RecordHolder {
		TextView objectName;
		ImageView objectIcon;
		TextView actionName;
		TextView paramName;
	}
    
}