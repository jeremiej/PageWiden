package com.example.pagewiden.fragments;

import java.util.ArrayList;

import org.qeo.EventReader;
import org.qeo.EventReaderListener;
import org.qeo.QeoFactory;
import org.qeo.android.QeoAndroid;
import org.qeo.android.QeoConnectionListener;
import org.qeo.exception.QeoException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.accenture.cdi.widen.data.BookSensor;
import com.accenture.cdi.widen.data.TeddySensor;
import com.example.pagewiden.R;
import com.example.pagewiden.model.MyObject;
import com.example.pagewiden.model.MyObjectList;

public class MaSphereContainerFragment extends Fragment {
	
	private MaSphereWebcamFragment mWebcamFragment = null;
	private MaSphereBarometreFragment mBarometreFragment = null;
	private MaSpherePorteFragment mPorteFragment = null;
	private MaSpherePelucheFragment mPelucheFragment = null;
	private MaSphereListScenarioFragment mListScenarioFragment = null;

	// Qeo attributes
	public static final int TOAST_DURATION = Toast.LENGTH_SHORT;
	public static final int TOAST_ID_TEDDY_HERE = 0;
	public static final int TOAST_ID_BOOK_HERE = 1;

    private QeoFactory qeo = null;
    private WidenQeoConnectionListener wQCL = null;
    private EventReader<TeddySensor> eventReaderTeddyHere = null;
    private EventReader<BookSensor> eventReaderBookHere = null;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_ma_sphere_container, container, false);
		
		this.mWebcamFragment = new MaSphereWebcamFragment();
		this.mBarometreFragment = new MaSphereBarometreFragment();
		this.mPorteFragment = new MaSpherePorteFragment();
		this.mListScenarioFragment = new MaSphereListScenarioFragment();
		
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();		
		transaction.add(R.id.webcam_container, this.mWebcamFragment);
		transaction.add(R.id.barometre_container, this.mBarometreFragment);
		transaction.add(R.id.porte_container, this.mPorteFragment);
		transaction.add(R.id.scenario_list_container, this.mListScenarioFragment);
		
		MyObjectList myObjectList = MyObjectList.get(getActivity());
		ArrayList<Object> objectArray = myObjectList.getMyObjectArray();
		MyObject peluche = (MyObject) objectArray.get(3);
		if(peluche.isInRealm()){
	    	Bundle arguments = new Bundle();
	    	arguments.putInt("launchVideo", 0);
			this.mPelucheFragment = new MaSpherePelucheFragment();
	    	mPelucheFragment.setArguments(arguments);
			transaction.add(R.id.peluche_container, this.mPelucheFragment);			
		}

//			FrameLayout flVideo = (FrameLayout)v.findViewById(R.id.webcam_container);
//
//			
//			flVideo.setOnTouchListener(new OnTouchListener() {
//				
//				@Override
//				public boolean onTouch(View v, MotionEvent event) {
//					onTeddyDetected();
//					return false;
//				}
//			});
		
//		if(mPelucheFragment!=null){
//			FrameLayout flBarometre = (FrameLayout) v.findViewById(R.id.barometre_container);		
//			flBarometre.setOnTouchListener(new OnTouchListener() {
//				
//				@Override
//				public boolean onTouch(View v, MotionEvent event) {
//					onBookDetected();
//					return false;
//				}
//			});
//		}
//		
		transaction.commit();

		// init Qeo
		initQeo();
		return v;
	}
	
	public void displayDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

		alertDialogBuilder.setTitle("Nouvel objet détecté!");

		alertDialogBuilder
				.setMessage("L'objet peluche souhaite rejoindre votre royaume, voullez vous l'ajouter?")
				.setCancelable(false)
				.setPositiveButton("Oui",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								MyObjectList myObjectList = MyObjectList.get(getActivity());
								ArrayList<Object> objectArray = myObjectList.getMyObjectArray();
								MyObject peluche = (MyObject)objectArray.get(3);
								peluche.setInRealm(true);
								objectArray.set(3, peluche);
								myObjectList.setMyObjectArray(objectArray);
								
								FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
						    	Bundle arguments = new Bundle();
						    	arguments.putInt("launchVideo", 0);
								mPelucheFragment = new MaSpherePelucheFragment();
						    	mPelucheFragment.setArguments(arguments);								
								transaction.add(R.id.peluche_container, mPelucheFragment);	
								transaction.commit();																
							}
						})
				.setNegativeButton("Non", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	// Qeo methods
	private void initQeo() {
		this.wQCL = new WidenQeoConnectionListener();
		QeoAndroid.initQeo(this.getActivity().getApplicationContext(), this.wQCL);
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
		MyObjectList myObjectList = MyObjectList.get(getActivity());
		ArrayList<Object> objectArray = myObjectList.getMyObjectArray();
		MyObject peluche = (MyObject) objectArray.get(3);
		if(!peluche.isInRealm()){
			displayDialog();
		}
	}

	private void onBookHere() {
    	Bundle arguments = new Bundle();
    	arguments.putInt("launchVideo", 1);
    	mPelucheFragment = new MaSpherePelucheFragment();
    	mPelucheFragment.setArguments(arguments);
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(R.id.peluche_container, mPelucheFragment);
		transaction.commit();
	}
}
