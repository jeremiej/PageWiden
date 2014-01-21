package com.example.pagewiden.adapters;

import java.util.ArrayList;

import com.example.pagewiden.R;
import com.example.pagewiden.model.MyObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomObjectSpinnerAdapter extends ArrayAdapter<Object> {

	private Context context;
	int layoutResourceId;
	ArrayList<Object> data = new ArrayList<Object>();

	public CustomObjectSpinnerAdapter(Context context, int layoutResourceId,
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
		
		MyObject myObject = (MyObject)data.get(position);
		
		if (row == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RecordHolder();
			holder.objectName = (TextView) row.findViewById(R.id.object_name);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}

		holder.objectName.setText(myObject.getName());
		return row;
	}
	
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
    	View row = convertView;
		RecordHolder holder = null;
		
		MyObject myObject = (MyObject)data.get(position);
		
		if (row == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RecordHolder();
			holder.objectName = (TextView) row.findViewById(R.id.object_name);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}

		holder.objectName.setText(myObject.getName());
		return row;
    }
	
	static class RecordHolder {
		TextView objectName;
	}
}
