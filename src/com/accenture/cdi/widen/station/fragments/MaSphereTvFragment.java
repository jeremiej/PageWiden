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

import com.accenture.cdi.widen.station.R;
import com.accenture.cdi.widen.station.ShopRecipeActivity;

public class MaSphereTvFragment extends Fragment {
	View v;
	
	public int getArg() {
		return getArguments().getInt("newRecipe", 0);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		v = inflater.inflate(R.layout.fragment_ma_sphere_tv, container, false);	
		final LinearLayout llNewRecipe = (LinearLayout)v.findViewById(R.id.new_recipe);
		final LinearLayout llTvRecipe = (LinearLayout)v.findViewById(R.id.tv_recipe);
		final TextView tvNoRecipe = (TextView)v.findViewById(R.id.no_recipe);
		
		if(getArg() == 0){			
			llNewRecipe.setVisibility(8);
			
			llTvRecipe.setVisibility(8);
		}else{
			tvNoRecipe.setVisibility(8);
			
			llTvRecipe.setVisibility(8);
		}
		
		Button btShowRecipe = (Button)v.findViewById(R.id.show_recipe);
		btShowRecipe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				llNewRecipe.setVisibility(8);
				
				llTvRecipe.setVisibility(0);
			}
		});
		
		Button btHideRecipe = (Button)v.findViewById(R.id.hide_recipe);
		btHideRecipe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				llNewRecipe.setVisibility(8);
				
				tvNoRecipe.setVisibility(0);
			}
		});
		
		Button btShopRecipe = (Button)v.findViewById(R.id.shop_recipe);
		btShopRecipe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), ShopRecipeActivity.class);
				startActivity(i);
			}
		});
		
		return v;
	}
	
}
