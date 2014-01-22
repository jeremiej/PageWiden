package com.example.pagewiden;

import java.util.Locale;

import org.qeo.EventReader;
import org.qeo.EventReaderListener;
import org.qeo.QeoFactory;
import org.qeo.android.QeoAndroid;
import org.qeo.android.QeoConnectionListener;
import org.qeo.exception.QeoException;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.accenture.cdi.widen.data.BookSensor;
import com.accenture.cdi.widen.data.TeddySensor;
import com.example.pagewiden.fragments.MaSphereContainerFragment;
import com.example.pagewiden.fragments.MonStoreFragmentContainer;
import com.example.pagewiden.fragments.MonStudioFragmentContainer;
import com.example.pagewiden.model.User;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

    private QeoFactory qeo = null;
    private WidenQeoConnectionListener wQCL = null;
    private EventReader<TeddySensor> eventReaderTeddyHere = null;
    private EventReader<BookSensor> eventReaderBookHere = null;

	MaSphereContainerFragment maSphere;

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
	
	int targetTab;

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
				getSupportFragmentManager());

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
		System.out.println("+++++++++++TEST");
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
		        	maSphere = (MaSphereContainerFragment) Fragment.instantiate(getApplicationContext(), MaSphereContainerFragment.class.getName());
		        	return maSphere;
		        case 1:
		        	return Fragment.instantiate(getApplicationContext(), MonStudioFragmentContainer.class.getName());
		        case 2:
		        	return Fragment.instantiate(getApplicationContext(), MonStoreFragmentContainer.class.getName());
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
       	if (eventReaderBookHere != null) {
       		eventReaderBookHere.close();
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
				eventReaderBookHere = qeo.createEventReader(BookSensor.class, new EventListenerBookHere());
				
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

	public class EventListenerBookHere implements EventReaderListener<BookSensor> {

		@Override
		public void onData(BookSensor bookHere) {
			onBookHere();
		}

		@Override
		public void onNoMoreData() {	
		}

	}

	private void onTeddyHere() {
		maSphere.onTeddyHere();
	}

	private void onBookHere() {
		maSphere.onBookHere();
	}

}
