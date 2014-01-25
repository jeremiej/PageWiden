package com.accenture.cdi.widen.station.model;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.accenture.cdi.widen.station.R;

public class MyObjectList {
	private Context mAppContext;
	private ArrayList<Object> mMyObjectArray;
	private static MyObjectList sMyObjectList;
	
	public MyObjectList(Context c){
		super();
		mAppContext = c;
		mMyObjectArray = new ArrayList<Object>();
		Resources res = mAppContext.getResources();
		
		// Création de l'objet Ampoule, ses actions et leurs paramètres
		MyObject oAmpoule = new MyObject();
		oAmpoule.setId(1);
		oAmpoule.setName("Ampoule");
		oAmpoule.setInRealm(true);
		Bitmap ampouleIcon = BitmapFactory.decodeResource(res, R.drawable.ampoule);
		oAmpoule.setIcon(ampouleIcon);
		
		MyObjectAction aAmpoule = new MyObjectAction();
		aAmpoule.setId(1);
		aAmpoule.setLabel("Allumer");
		
		MyObjectParam pAmpoule1 = new MyObjectParam();
		pAmpoule1.setId(1);
		pAmpoule1.setLabel("Immédiatement");
		aAmpoule.addParam(pAmpoule1);
		
		MyObjectParam pAmpoule2 = new MyObjectParam();
		pAmpoule2.setId(2);
		pAmpoule2.setLabel("Sur 30 secondes");
		aAmpoule.addParam(pAmpoule2);	
		
		MyObjectParam pAmpoule3 = new MyObjectParam();
		pAmpoule3.setId(3);
		pAmpoule3.setLabel("Sur 60 secondes");
		aAmpoule.addParam(pAmpoule3);	
		
		oAmpoule.addAction(aAmpoule);
		
		MyObjectAction aAmpoule2 = new MyObjectAction();
		aAmpoule2.setId(2);
		aAmpoule2.setLabel("Eteindre");
		
		MyObjectParam pAmpoule21 = new MyObjectParam();
		pAmpoule21.setId(1);
		pAmpoule21.setLabel("Immédiatement");
		aAmpoule2.addParam(pAmpoule21);
		
		MyObjectParam pAmpoule22 = new MyObjectParam();
		pAmpoule21.setId(2);
		pAmpoule22.setLabel("Sur 30 secondes");
		aAmpoule2.addParam(pAmpoule22);	
		
		MyObjectParam pAmpoule23 = new MyObjectParam();
		pAmpoule21.setId(3);
		pAmpoule23.setLabel("Sur 60 secondes");
		aAmpoule2.addParam(pAmpoule23);
		
		oAmpoule.addAction(aAmpoule2);
		mMyObjectArray.add(oAmpoule);
		
		// Création de l'objet Cafetière, ses actions et leurs paramètres 
		MyObject oCafetiere = new MyObject();
		oCafetiere.setId(2);
		oCafetiere.setName("Cafetière");
		oCafetiere.setInRealm(true);
		Bitmap tasseIcon = BitmapFactory.decodeResource(res, R.drawable.tasse);
		oCafetiere.setIcon(tasseIcon);
		
		MyObjectAction aCafetiere = new MyObjectAction();
		aCafetiere.setId(1);
		aCafetiere.setLabel("Servir");
		
		MyObjectParam pCafetiere1 = new MyObjectParam();
		pCafetiere1.setId(1);
		pCafetiere1.setLabel("Expresso");
		aCafetiere.addParam(pCafetiere1);
		
		MyObjectParam pCafetiere2 = new MyObjectParam();
		pCafetiere2.setId(2);
		pCafetiere2.setLabel("Café long");
		aCafetiere.addParam(pCafetiere2);
		
		oCafetiere.addAction(aCafetiere);
		
		MyObjectAction aCafetiere2 = new MyObjectAction();
		aCafetiere2.setId(2);
		aCafetiere2.setLabel("Purger");

		MyObjectParam pCafetiere3 = new MyObjectParam();
		pCafetiere3.setId(1);
		pCafetiere3.setLabel("");
		aCafetiere2.addParam(pCafetiere3);

		oCafetiere.addAction(aCafetiere2);
		mMyObjectArray.add(oCafetiere);
		
		// Création de l'objet Horloge, ses actions et leurs paramètres 
		MyObject oHorloge = new MyObject();
		oHorloge.setId(3);
		oHorloge.setName("Horloge");
		oHorloge.setInRealm(true);
		Bitmap horlogeIcon = BitmapFactory.decodeResource(res, R.drawable.horloge);
		oHorloge.setIcon(horlogeIcon);
		
		MyObjectAction aHorloge = new MyObjectAction();
		aHorloge.setId(1);
		aHorloge.setLabel("");
		
		MyObjectParam pHorloge0 = new MyObjectParam();
		pHorloge0.setId(1);
		pHorloge0.setLabel("00h");
		aHorloge.addParam(pHorloge0);
		
		MyObjectParam pHorloge1 = new MyObjectParam();
		pHorloge1.setId(2);
		pHorloge1.setLabel("01h");
		aHorloge.addParam(pHorloge1);
		
		MyObjectParam pHorloge2 = new MyObjectParam();
		pHorloge2.setId(3);
		pHorloge2.setLabel("02h");
		aHorloge.addParam(pHorloge2);
		
		MyObjectParam pHorloge3 = new MyObjectParam();
		pHorloge3.setId(4);
		pHorloge3.setLabel("03h");
		aHorloge.addParam(pHorloge3);
		
		MyObjectParam pHorloge4 = new MyObjectParam();
		pHorloge4.setId(5);
		pHorloge4.setLabel("04h");
		aHorloge.addParam(pHorloge4);
		
		MyObjectParam pHorloge5 = new MyObjectParam();
		pHorloge5.setId(6);
		pHorloge5.setLabel("05h");
		aHorloge.addParam(pHorloge5);
		
		MyObjectParam pHorloge6 = new MyObjectParam();
		pHorloge6.setId(7);
		pHorloge6.setLabel("06h");
		aHorloge.addParam(pHorloge6);
		
		MyObjectParam pHorloge7 = new MyObjectParam();
		pHorloge7.setId(8);
		pHorloge7.setLabel("07h");
		aHorloge.addParam(pHorloge7);
		
		MyObjectParam pHorloge8 = new MyObjectParam();
		pHorloge8.setId(9);
		pHorloge8.setLabel("08h");
		aHorloge.addParam(pHorloge8);
		
		MyObjectParam pHorloge9 = new MyObjectParam();
		pHorloge9.setId(10);
		pHorloge9.setLabel("09h");
		aHorloge.addParam(pHorloge9);
		
		MyObjectParam pHorloge10 = new MyObjectParam();
		pHorloge10.setId(11);
		pHorloge10.setLabel("10h");
		aHorloge.addParam(pHorloge10);
		
		MyObjectParam pHorloge11 = new MyObjectParam();
		pHorloge11.setId(12);
		pHorloge11.setLabel("11h");
		aHorloge.addParam(pHorloge11);
		
		MyObjectParam pHorloge12 = new MyObjectParam();
		pHorloge12.setId(13);
		pHorloge12.setLabel("12h");
		aHorloge.addParam(pHorloge12);
		
		MyObjectParam pHorloge13 = new MyObjectParam();
		pHorloge13.setId(14);
		pHorloge13.setLabel("13h");
		aHorloge.addParam(pHorloge13);
		
		MyObjectParam pHorloge14 = new MyObjectParam();
		pHorloge14.setId(15);
		pHorloge14.setLabel("14h");
		aHorloge.addParam(pHorloge14);
		
		MyObjectParam pHorloge15 = new MyObjectParam();
		pHorloge15.setId(16);
		pHorloge15.setLabel("15h");
		aHorloge.addParam(pHorloge15);
		
		MyObjectParam pHorloge16 = new MyObjectParam();
		pHorloge16.setId(17);
		pHorloge16.setLabel("16h");
		aHorloge.addParam(pHorloge16);
		
		MyObjectParam pHorloge17 = new MyObjectParam();
		pHorloge17.setId(18);
		pHorloge17.setLabel("17h");
		aHorloge.addParam(pHorloge17);
		
		MyObjectParam pHorloge18 = new MyObjectParam();
		pHorloge18.setId(19);
		pHorloge18.setLabel("18h");
		aHorloge.addParam(pHorloge18);
		
		MyObjectParam pHorloge19 = new MyObjectParam();
		pHorloge19.setId(20);
		pHorloge19.setLabel("19h");
		aHorloge.addParam(pHorloge19);
		
		MyObjectParam pHorloge20 = new MyObjectParam();
		pHorloge20.setId(21);
		pHorloge20.setLabel("20h");
		aHorloge.addParam(pHorloge20);
		
		MyObjectParam pHorloge21 = new MyObjectParam();
		pHorloge21.setId(22);
		pHorloge21.setLabel("21h");
		aHorloge.addParam(pHorloge21);
		
		MyObjectParam pHorloge22 = new MyObjectParam();
		pHorloge22.setId(23);
		pHorloge22.setLabel("22h");
		aHorloge.addParam(pHorloge22);
		
		MyObjectParam pHorloge23 = new MyObjectParam();
		pHorloge23.setId(24);
		pHorloge23.setLabel("23h");
		aHorloge.addParam(pHorloge23);
		
		oHorloge.addAction(aHorloge);

		mMyObjectArray.add(oHorloge);
		
		// Création de l'objet Peluche, ses actions et leurs paramètres 
		MyObject oPeluche = new MyObject();
		oPeluche.setId(4);
		oPeluche.setName("Peluche");
		oPeluche.setInRealm(false);
		Bitmap pelucheIcon = BitmapFactory.decodeResource(res, R.drawable.peluche);
		oPeluche.setIcon(pelucheIcon);
		
		MyObjectAction aPeluche = new MyObjectAction();
		aPeluche.setId(1);
		aPeluche.setLabel("Détecter");
		
		MyObjectParam pPeluche = new MyObjectParam();
		pPeluche.setId(1);
		pPeluche.setLabel("Livre connecté");
		aPeluche.addParam(pPeluche);
		
		oPeluche.addAction(aPeluche);
		
		MyObjectAction aPeluche2 = new MyObjectAction();
		aPeluche2.setId(2);
		aPeluche2.setLabel("Lire");
		
		MyObjectParam pPeluche2 = new MyObjectParam();
		pPeluche2.setId(1);
		pPeluche2.setLabel("Livre");
		aPeluche2.addParam(pPeluche2);		
		
		oPeluche.addAction(aPeluche2);
		mMyObjectArray.add(oPeluche);
	}
	
	public static MyObjectList get(Context c){
		if(sMyObjectList == null){
			sMyObjectList = new MyObjectList(c.getApplicationContext());
		}
		return sMyObjectList;
	}

	public ArrayList<Object> getMyObjectArray() {
		return mMyObjectArray;
	}
	
	public void setMyObjectArray(ArrayList<Object> myObjectArray) {
		mMyObjectArray = myObjectArray;
	}

	public ArrayList<Object> getInRealmMyObjectArray(){
		ArrayList<Object> inRealm = new ArrayList<Object>();
		for (int i = 0; i < this.mMyObjectArray.size(); i++) {
			MyObject currentObject = (MyObject)this.mMyObjectArray.get(i);
			if(currentObject.isInRealm())
				inRealm.add(currentObject);
		}
		return inRealm;
	}
		
}
