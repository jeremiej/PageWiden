package com.example.pagewiden.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.example.pagewiden.R;

public class MaSphereWebcamFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_ma_sphere_video, container, false);
		
		try {
			VideoView videoView = (VideoView)v.findViewById(R.id.video_stream);
			videoView.setMediaController(null);
			videoView.setVideoURI(Uri.parse("rtsp://192.168.70.177/img/media.sav"));
			videoView.start();	
		} catch (Exception e) {
			// TODO: handle exception
		}
				
		return v;
	}
}
