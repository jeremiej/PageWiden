package com.accenture.cdi.widen.station;

import java.util.Locale;

import org.qeo.EventReader;
import org.qeo.EventReaderListener;
import org.qeo.QeoFactory;
import org.qeo.android.QeoAndroid;
import org.qeo.android.QeoConnectionListener;
import org.qeo.exception.QeoException;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.accenture.cdi.widen.data.TeddySensor;
import com.accenture.cdi.widen.data.TvRecipeSensor;
import com.accenture.cdi.widen.station.fragments.MaSphereFragmentContainer;
import com.accenture.cdi.widen.station.fragments.MaSphereFragmentShop;
import com.accenture.cdi.widen.station.fragments.MaSphereFragmentViewAllObjects;
import com.accenture.cdi.widen.station.fragments.MonStoreFragmentContainer;
import com.accenture.cdi.widen.station.fragments.MonStoreFragmentViewOneScenario;
import com.accenture.cdi.widen.station.fragments.MonStoreViewAllDownloadableScenarios;
import com.accenture.cdi.widen.station.fragments.MonStudioFragmentContainer;
import com.accenture.cdi.widen.station.fragments.MonStudioFragmentViewAllScenarios;
import com.accenture.cdi.widen.station.fragments.MonStudioFragmentViewOneScenario;
import com.accenture.cdi.widen.station.model.User;

public class MainActivity extends Activity implements
		ActionBar.TabListener {

	public static final int START_ACTIVITY_CREATE_SCENARIO = 0;
	public static final int START_ACTIVITY_EDIT_SCENARIO = 1;
	public static final int START_ACTIVITY_SHOP_RECIPE = 2;
	
    private QeoFactory qeo = null;
    private WidenQeoConnectionListener wQCL = null;
    private EventReader<TeddySensor> eventReaderTeddyHere = null;
    private EventReader<TvRecipeSensor> eventReaderTvRecipeHere = null;

    MaSphereFragmentContainer maSphereContainer;
	MaSphereFragmentViewAllObjects maSphereViewAllObjects;

	MonStudioFragmentContainer monStudioContainer;
	MonStudioFragmentViewAllScenarios monStudioViewAllScenarios;

	MonStoreFragmentContainer monStoreContainer;

	int targetTab;

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent intent = getIntent();
		targetTab = intent.getIntExtra("targetTab", 999);
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		
		if(targetTab!=999)
			actionBar.setSelectedNavigationItem(targetTab);

		// init Qeo
		initQeo();

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

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
	        switch (position) {
		        case 0:
		        	if (maSphereViewAllObjects == null) {
		        		maSphereViewAllObjects = (MaSphereFragmentViewAllObjects) Fragment.instantiate(getApplicationContext(), MaSphereFragmentViewAllObjects.class.getName());
		        	}
		        	return maSphereViewAllObjects;
//		        	if (maSphereContainer == null) {
//		        		maSphereContainer = (MaSphereFragmentContainer) Fragment.instantiate(getApplicationContext(), MaSphereFragmentContainer.class.getName());
//		        	}
//		        	return maSphereContainer;
		        case 1:
		        	if (monStudioContainer == null) {
		        		monStudioContainer = (MonStudioFragmentContainer) Fragment.instantiate(getApplicationContext(), MonStudioFragmentContainer.class.getName());
		        	}
		        	return monStudioContainer;
		        case 2:
		        	if (monStoreContainer == null) {
		        		monStoreContainer =  (MonStoreFragmentContainer) Fragment.instantiate(getApplicationContext(), MonStoreFragmentContainer.class.getName());
		        	}
		        	return monStoreContainer;
	        }
	        return null;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	// Qeo methods
	private void initQeo() {
		this.wQCL = new WidenQeoConnectionListener();
		QeoAndroid.initQeo(getApplicationContext(), this.wQCL);
	}

	public void onDestroy() {
		super.onDestroy();
		if (eventReaderTeddyHere != null) {
			eventReaderTeddyHere.close();
       	}
		if (eventReaderTvRecipeHere != null) {
			eventReaderTvRecipeHere.close();
       	}
        if (qeo != null) {
            qeo.close();
        }
    }

	private class WidenQeoConnectionListener extends QeoConnectionListener {

	    @Override
	    public void onQeoReady(QeoFactory curQeo)
	    {
	        // Will be called when the Android Qeo Service connection is established and is ready to be used.
	        // This can take a while depending on the security initialization.
	        qeo = curQeo;
	        // This is a good place to create readers and writers
	        try {
				eventReaderTeddyHere = qeo.createEventReader(TeddySensor.class, new EventListenerTeddyHere());
				eventReaderTvRecipeHere = qeo.createEventReader(TvRecipeSensor.class, new EventListenerTvRecipeHere());
				
			} catch (QeoException e) {
				e.printStackTrace();
			}
	    }
	
	    @Override
	    public void onQeoClosed(QeoFactory curQeo)
	    {
	    	// Will be called when the Android Qeo Service connection is lost
        }
	 
        @Override
        public void onQeoError(QeoException ex)
        {
            ex.printStackTrace();
	    }

	}

	// State and Event listeners
	public class EventListenerTeddyHere implements EventReaderListener<TeddySensor> {

		@Override
		public void onData(TeddySensor teddyHere) {
			onTeddyHere();
		}

		@Override
		public void onNoMoreData() {	
		}

	}

	private void onTeddyHere() {
		maSphereViewAllObjects.onTeddyHere();
	}
	
	// State and Event listeners tv recipe
	public class EventListenerTvRecipeHere implements EventReaderListener<TvRecipeSensor> {

		@Override
		public void onData(TvRecipeSensor tvRecipeHere) {
			onTvRecipeHere();
		}

		@Override
		public void onNoMoreData() {	
		}

	}

	private void onTvRecipeHere() {
		maSphereViewAllObjects.onTvRecipeHere();
	}

	// Manage fragments in "Ma Sph�re"
	public void maSphereViewAllObjects() {
		maSphereViewAllObjects = new MaSphereFragmentViewAllObjects();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.ma_sphere_container, maSphereViewAllObjects);
		transaction.commit();
		refreshMaSphereDisplayedScenarios();
	}

	public void maSphereShop() {
		MaSphereFragmentShop fragment = new MaSphereFragmentShop();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.ma_sphere_container, fragment);
		transaction.commit();
	}

	// Manage fragments in "Mon Studio"
	public void monStudioViewAllScenarios() {
		monStudioViewAllScenarios = new MonStudioFragmentViewAllScenarios();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.mon_studio_container, monStudioViewAllScenarios);
		transaction.commit();
		refreshMaSphereDisplayedScenarios();
	}

	public void monStudioViewOneScenarioDetails(int scenarioPosition) {
		MonStudioFragmentViewOneScenario fragment = new MonStudioFragmentViewOneScenario();
		Bundle args = new Bundle();
		args.putInt("id", scenarioPosition);
		fragment.setArguments(args);
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.mon_studio_container, fragment);
		transaction.commit();
	}

	public void monStudioStartScenarioCreationName() {
		Intent i = new Intent(this, EditionScenarioNomActivity.class);
		i.putExtra("mode", ScenarioDetailsActivity.MODE_CREATE);
		startActivityForResult(i, START_ACTIVITY_CREATE_SCENARIO);
	}

	// Manage fragments in "Mon Store"
	public void monStoreViewAllDownloadableScenarios() {
		MonStoreViewAllDownloadableScenarios fragment = new MonStoreViewAllDownloadableScenarios();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.mon_store_container, fragment);
		transaction.commit();
		refreshMaSphereDisplayedScenarios();
	}

	public void monStoreViewOneScenarioDetails(int scenarioPosition) {
		MonStoreFragmentViewOneScenario fragment = new MonStoreFragmentViewOneScenario();
		Bundle args = new Bundle();
		args.putInt("id", scenarioPosition);
		fragment.setArguments(args);
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.mon_store_container, fragment);
		transaction.commit();
	}

	public void refreshMaSphereDisplayedScenarios() {
		if (maSphereViewAllObjects != null) {
			maSphereViewAllObjects.refreshScenarioListFragment();
		}
	}

	public void refreshMonStudioDisplayedScenarios() {
		if (monStudioViewAllScenarios != null) {
			monStudioViewAllScenarios.refreshScenarioGrid();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		int position = -1;

		switch (resultCode) {
		case RESULT_OK:
			switch (requestCode) {
			case START_ACTIVITY_CREATE_SCENARIO:
				refreshMonStudioDisplayedScenarios();
				position = data.getIntExtra("position", -1);
				monStudioViewOneScenarioDetails(position);
				break;
			case START_ACTIVITY_EDIT_SCENARIO:
				refreshMonStudioDisplayedScenarios();
				position = data.getIntExtra("position", -1);
				monStudioViewOneScenarioDetails(position);
				break;
			default:
				break;
			}
		default:
			break;
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

}
