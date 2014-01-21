package com.example.pagewiden.adapters;

import java.util.ArrayList;

import com.example.pagewiden.R;
import com.example.pagewiden.model.MyObjectAction;
import com.example.pagewiden.model.MyObjectParam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListViewMonStoreActionAdapter extends ArrayAdapter<Object> {
	private Context context;
    int layoutResourceId;
    ArrayList<Object> data = new ArrayList<Object>();

    public CustomListViewMonStoreActionAdapter(Context context, int layoutResourceId, ArrayList<Object> data) {
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

		if (row == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new RecordHolder();

			holder.actionLabel = (TextView) row.findViewById(R.id.action_label);
			holder.paramList = (TextView) row.findViewById(R.id.param_list);
			
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}
	
		MyObjectAction myObjectAction = (MyObjectAction)data.get(position);	
		
		if(myObjectAction.getLabel().equals("")){
			holder.actionLabel.setText("Aucune action disponible pour cet objet");
		}else{
			holder.actionLabel.setText(myObjectAction.getLabel());
			
			ArrayList<Object> paramList = myObjectAction.getParamList();
			if(paramList.size()>0){				
				String stringParamList = "- ";
				for (int i = 0; i < paramList.size(); i++) {
					MyObjectParam myObjectParam = (MyObjectParam)paramList.get(i);
					if(i==0){
						stringParamList+=myObjectParam.getLabel();
					}else{
						stringParamList+="\n"+"- "+myObjectParam.getLabel();
					}						
				}	
				holder.paramList.setText(stringParamList);
			}else{
				holder.paramList.setText("Aucun paramètre disponible pour cette action");
			}
		}
		
		return row;
	}
	
	static class RecordHolder {
		TextView actionLabel;
		TextView paramList;
	}
}
