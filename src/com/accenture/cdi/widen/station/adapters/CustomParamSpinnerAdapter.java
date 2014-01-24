package com.accenture.cdi.widen.station.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.accenture.cdi.widen.station.R;
import com.accenture.cdi.widen.station.model.MyObjectParam;

public class CustomParamSpinnerAdapter extends ArrayAdapter<Object> {

	private Context context;
	int layoutResourceId;
	ArrayList<Object> data = new ArrayList<Object>();

	public CustomParamSpinnerAdapter(Context context, int layoutResourceId,
			ArrayList<Object> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		RecordHolder holder = null;
		
		MyObjectParam myParam = (MyObjectParam)data.get(position);
		
		if (row == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RecordHolder();
			holder.paramName = (TextView) row.findViewById(R.id.param_name);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}

		holder.paramName.setText(myParam.getLabel());
		return row;
	}
	
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
    	View row = convertView;
		RecordHolder holder = null;
		
		MyObjectParam myParam = (MyObjectParam)data.get(position);
		
		if (row == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RecordHolder();
			holder.paramName = (TextView) row.findViewById(R.id.param_name);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}

		holder.paramName.setText(myParam.getLabel());
		return row;
    }
	
	static class RecordHolder {
		TextView paramName;
	}
}
