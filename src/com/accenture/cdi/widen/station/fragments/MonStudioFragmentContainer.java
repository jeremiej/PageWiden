package com.accenture.cdi.widen.station.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.accenture.cdi.widen.station.MainActivity;
import com.accenture.cdi.widen.station.R;

public class MonStudioFragmentContainer extends Fragment {

	public static final String ARG_MODE = "mode";

	public static final int STUDIO_MODE_VIEW_ALL = 0;
	public static final int STUDIO_MODE_VIEW_ONE = 1;
	public static final int STUDIO_MODE_VIEW_EDIT = 2;

	private int mode = -1;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_mon_studio_container, container, false);

		Integer rawMode = null;
		this.mode = STUDIO_MODE_VIEW_ALL;
		if (this.getArguments() != null) {
			rawMode = (Integer) this.getArguments().get(ARG_MODE);
			if (rawMode != null) {
				this.mode = rawMode.intValue();
			} else {
				this.mode = STUDIO_MODE_VIEW_ALL;
			}
		}

		switch (this.mode) {
		case STUDIO_MODE_VIEW_ALL:
			((MainActivity) getActivity()).monStudioViewAllScenarios();
			break;
		default:
			break;
		}

		return v;
	}

}
