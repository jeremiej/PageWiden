package com.accenture.cdi.widen.station.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.accenture.cdi.widen.station.R;
import com.accenture.cdi.widen.station.model.Scenario;
import com.accenture.cdi.widen.station.model.ScenarioList;

public class CustomListViewMonStoreAdapter extends ArrayAdapter<Object> {
	private Context context;
    int layoutResourceId;
    ArrayList<Object> data = new ArrayList<Object>();

    public CustomListViewMonStoreAdapter(Context context, int layoutResourceId, ArrayList<Object> data) {
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

			holder.scenarioName = (TextView) row.findViewById(R.id.name);
			holder.isAlreadyDownloaded = (ImageView) row.findViewById(R.id.is_already_downloaded);
			
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}
	
		holder.scenarioName.setText(scenario.getScenarioTitle());
		
		ScenarioList scenarioList = ScenarioList.get(getContext());
		if(scenarioList.isDownloaded(scenario.getScenarioTitle())){
			Bitmap checkIcon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.check);
			holder.isAlreadyDownloaded.setImageBitmap(checkIcon);
		}
		
		return row;
	}
	
	static class RecordHolder {
		TextView scenarioName;
		ImageView isAlreadyDownloaded;
	}
}
