package com.accenture.cdi.widen.station;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.accenture.cdi.widen.station.model.User;

public class ShopRecipeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_recipe);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
	    MenuItem item = menu.findItem(R.id.action_user_name);
		User mUser;
		mUser = User.get(getApplicationContext());
	    item.setTitle(mUser.getLogin());
	    return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
