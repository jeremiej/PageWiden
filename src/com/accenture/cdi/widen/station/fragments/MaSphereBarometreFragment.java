package com.accenture.cdi.widen.station.fragments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.accenture.cdi.widen.station.utils.ServiceHandler;
import com.example.pagewiden.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MaSphereBarometreFragment extends Fragment {

	String hydrometrie = null;
	String temperature = null;
	View v;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		v = inflater.inflate(R.layout.fragment_ma_sphere_barometre, container, false);		
		new GetJson().execute();		
		return v;
	}
	
	private class GetJson extends AsyncTask<Void, Void, Void> {	
 
        @Override
        protected Void doInBackground(Void... arg0) {
            ServiceHandler sh = new ServiceHandler();
 
            String url = "http://192.168.70.1:47995/device_op?apiVersion=2&cmd=getDevices";
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
 
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray devices = jsonObj.getJSONArray("devices");   
                    for (int i = 0; i < devices.length(); i++) {
                    	JSONObject d = devices.getJSONObject(i);
                    	int deviceId = d.getInt("databaseId");
                    	if(deviceId == 35){
                    		JSONArray childrens = d.getJSONArray("children");
                    		for (int j = 0; j < childrens.length(); j++) {
								JSONObject c = childrens.getJSONObject(j);
								int childrenId = c.getInt("databaseId");
								if(childrenId == 38)
									hydrometrie = c.getInt("lastValue")+" %";
								if(childrenId == 39)
									temperature = (c.getInt("lastValue")/1000)+" °C";
							}
                    	}
					}
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            } 

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            
            TextView tvTemperature = (TextView)v.findViewById(R.id.temperature);
            tvTemperature.setText(temperature);
            TextView tvHydrometrie = (TextView)v.findViewById(R.id.hydrometrie);
            tvHydrometrie.setText(hydrometrie);
            
        }
    }
}
