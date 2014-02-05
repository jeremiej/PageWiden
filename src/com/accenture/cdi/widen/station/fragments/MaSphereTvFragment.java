package com.accenture.cdi.widen.station.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accenture.cdi.widen.station.MainActivity;
import com.accenture.cdi.widen.station.R;
import com.accenture.cdi.widen.station.ShopRecipeActivity;

public class MaSphereTvFragment extends Fragment {

	View v;
	LinearLayout llNewRecipe;
	LinearLayout llTvRecipe;
	TextView tvNoRecipe;

	public int getArg() {
		return getArguments().getInt("newRecipe", 0);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		v = inflater.inflate(R.layout.fragment_ma_sphere_tv, container, false);
		tvNoRecipe = (TextView) v.findViewById(R.id.no_recipe);
		llNewRecipe = (LinearLayout) v.findViewById(R.id.new_recipe);
		llTvRecipe = (LinearLayout) v.findViewById(R.id.tv_recipe);

		
		if (getArg() == 0){			
			stateNoNotification();
		} else {
			stateNotificationHere();
		}

		// TST
		stateShowRecipe();
		///TST

		Button btShowRecipe = (Button) v.findViewById(R.id.show_recipe);
		btShowRecipe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stateShowRecipe();
			}
		});
		
		Button btHideRecipe = (Button) v.findViewById(R.id.hide_recipe);
		btHideRecipe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stateNoNotification();
			}
		});
		
		Button btShopRecipe = (Button) v.findViewById(R.id.shop_recipe);
		btShopRecipe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).maSphereShop();
				Intent i = new Intent(getActivity(), ShopRecipeActivity.class);
				startActivity(i);
			}
		});
		
		return v;
	}

	private void stateNoNotification() {
		tvNoRecipe.setVisibility(View.VISIBLE);
		llNewRecipe.setVisibility(View.GONE);
		llTvRecipe.setVisibility(View.GONE);
	}

	private void stateNotificationHere() {
		tvNoRecipe.setVisibility(View.GONE);
		llNewRecipe.setVisibility(View.VISIBLE);
		llTvRecipe.setVisibility(View.GONE);
	}

	private void stateShowRecipe() {
		tvNoRecipe.setVisibility(View.GONE);
		llNewRecipe.setVisibility(View.GONE);
		llTvRecipe.setVisibility(View.VISIBLE);
	}
}
