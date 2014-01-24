package com.accenture.cdi.widen.station.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.accenture.cdi.widen.station.MainActivity;
import com.accenture.cdi.widen.station.R;

public class MonStoreFragmentContainer extends Fragment {

	public static final String ARG_MODE = "mode";

	public static final int STORE_MODE_VIEW_ALL = 0;
	public static final int STORE_MODE_VIEW_ONE = 1;

	private int mode = -1;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_mon_store_container, container, false);

		Integer rawMode = null;
		this.mode = STORE_MODE_VIEW_ALL;
		if (this.getArguments() != null) {
			rawMode = (Integer) this.getArguments().get(ARG_MODE);
			if (rawMode != null) {
				this.mode = rawMode.intValue();
			} else {
				this.mode = STORE_MODE_VIEW_ALL;
			}
		}

		switch (this.mode) {
		case STORE_MODE_VIEW_ALL:
			((MainActivity) getActivity()).monStoreViewAllDownloadableScenarios();
			break;
		default:
			break;
		}

		return v;

	}

}
