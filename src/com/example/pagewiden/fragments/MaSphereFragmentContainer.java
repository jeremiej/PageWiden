package com.example.pagewiden.fragments;

import utils.ServiceHandler;

import com.example.pagewiden.R;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class MaSphereFragmentContainer extends Fragment {
	
	private ProgressDialog pDialog;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_ma_sphere_video, container, false);
        VideoView videoView = (VideoView)v.findViewById(R.id.video_stream);
        MediaController mc = new MediaController(getActivity().getApplicationContext());
        videoView.setMediaController(mc);
        videoView.setVideoURI(Uri.parse("rtsp://192.168.70.177/img/media.sav"));
        videoView.requestFocus();
        videoView.start();
        
        new GetJson().execute();
        //"http://192.168.70.1:47995/device_op?apiVersion=2&cmd=getDevices"
	    
		return v;
	}
	
	private class GetJson extends AsyncTask<Void, Void, Void> {
		 
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            // Showing progress dialog
//            pDialog = new ProgressDialog(getActivity());
//            pDialog.setMessage("Please wait...");
//            pDialog.setCancelable(false);
//            pDialog.show();
//        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
 
            // Making a request to url and getting response
            String url = "http://192.168.70.1:47995/device_op?apiVersion=2&cmd=getDevices";
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
 
            Log.d("Response: ", "> " + jsonStr);
            
//            if (pDialog.isShowing())
//                pDialog.dismiss();
            
            return null;
        }
    }
}
