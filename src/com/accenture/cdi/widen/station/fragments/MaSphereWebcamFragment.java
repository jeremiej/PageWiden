package com.accenture.cdi.widen.station.fragments;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.accenture.cdi.widen.station.R;

public class MaSphereWebcamFragment extends Fragment {

	public static final String VIDEO_URL = "rtsp://192.168.70.177/img/media.sav";
	public static final String VIDEO_URL_2 = "rtsp://media.smart-streaming.com/mytest/mp4:sample.mp4";

	private VideoView videoView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_ma_sphere_video, container, false);
		
		videoView = (VideoView) v.findViewById(R.id.video_stream);
		videoView.setMediaController(null);
		videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				return true;
			}
		});
		videoView.setVideoURI(Uri.parse(VIDEO_URL));
		return v;
	}

	private void startPlaying() {
		if (this.videoView != null) {
			this.videoView.start();
		}
	}

	@Override
	public void onResume() {
		if (!this.videoView.isPlaying()) {
			startPlaying();
		}
		super.onResume();
	}

}
