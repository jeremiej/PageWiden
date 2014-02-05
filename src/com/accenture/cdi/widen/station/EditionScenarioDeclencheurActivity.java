package com.accenture.cdi.widen.station;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.accenture.cdi.widen.station.adapters.CustomActionSpinnerAdapter;
import com.accenture.cdi.widen.station.adapters.CustomObjectSpinnerAdapter;
import com.accenture.cdi.widen.station.adapters.CustomParamSpinnerAdapter;
import com.accenture.cdi.widen.station.model.MyObject;
import com.accenture.cdi.widen.station.model.MyObjectAction;
import com.accenture.cdi.widen.station.model.MyObjectList;
import com.accenture.cdi.widen.station.model.MyObjectParam;
import com.accenture.cdi.widen.station.model.NewScenario;
import com.accenture.cdi.widen.station.model.Scenario;
import com.accenture.cdi.widen.station.model.ScenarioBlock;
import com.accenture.cdi.widen.station.model.ScenarioList;
import com.accenture.cdi.widen.station.model.User;

public class EditionScenarioDeclencheurActivity extends Activity {
	
	private Spinner mSpinnerObject, mSpinnerAction, mSpinnerParam;
	private NewScenario mNewScenario;
	private int scenarioNb;
	
	CustomObjectSpinnerAdapter customObjectSpinnerAdapter;
	CustomActionSpinnerAdapter customActionSpinnerAdapter;
	CustomParamSpinnerAdapter customParamSpinnerAdapter;
	ArrayList<Object> objectList;
	ArrayList<Object> actionList;
	ArrayList<Object> paramList;

	int positionObject;
	int positionAction;
	int positionParam;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_edition_scenario_declencheur);

		setupActionBar();

		Intent i = getIntent();
		scenarioNb = i.getIntExtra("scenarioNb", -1);
		
		mSpinnerAction = (Spinner)findViewById(R.id.scenario_edit_trigger_action_spinner);
		mSpinnerAction.setEnabled(false);
		mSpinnerAction.setClickable(false);
		
		mSpinnerParam = (Spinner)findViewById(R.id.scenario_edit_trigger_param_spinner);
		mSpinnerParam.setEnabled(false);
		mSpinnerParam.setClickable(false);

		addItemsObjectSpinner();
		addListenerOnObjectSpinnerItemSelection();

		Button validationButton = (Button)findViewById(R.id.scenario_edit_validation);
		validationButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {	
				RadioButton declenchementUtilisateur = (RadioButton) findViewById(R.id.declenchement_utilisateur);
				boolean isDeclenchementUtilisateur = declenchementUtilisateur.isChecked();
				
				if (scenarioNb != -1) {
					ScenarioList mScenarioList = ScenarioList.get(getApplicationContext());
					ArrayList<Object> scenarioArray = mScenarioList.getScenarioArray();
					Scenario currentScenario = (Scenario) scenarioArray.get(scenarioNb);
					
				    if (isDeclenchementUtilisateur){
				    	currentScenario.setScenarioActivite("Désactivé");
				    	currentScenario.setDeclenchementUtilisateur(true);
				    	Bitmap indicateurGris = BitmapFactory.decodeResource(getResources(), R.drawable.indicateur_gris);
				    	currentScenario.setScenarioIndicateur(indicateurGris);
				    	Bitmap declencheurUtilisateur = BitmapFactory.decodeResource(getResources(), R.drawable.utilisateur);
				    	currentScenario.setScenarioIcon(declencheurUtilisateur);
				    } else {
				    	currentScenario.setScenarioActivite("Activé");
				    	currentScenario.setDeclenchementUtilisateur(false);
				    	Bitmap indicateurBleu = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.indicateur_bleu);
				    	currentScenario.setScenarioIndicateur(indicateurBleu);
				    	
						MyObject selectedObject = (MyObject)mSpinnerObject.getSelectedItem();
						MyObjectAction selectedAction = (MyObjectAction)mSpinnerAction.getSelectedItem();
						MyObjectParam selectedParam = (MyObjectParam)mSpinnerParam.getSelectedItem();
	
						ScenarioBlock blockDeclencheur = new ScenarioBlock();
						blockDeclencheur.setMyObject(selectedObject);
						blockDeclencheur.setMyObjectAction(selectedAction);
						blockDeclencheur.setMyObjectParam(selectedParam);
						currentScenario.setDeclencheur(blockDeclencheur);
						currentScenario.setScenarioIcon(selectedObject.getIcon());
				    }

					Intent i = new Intent();
					i.putExtra("position", scenarioNb);
					setResult(RESULT_OK, i);
					finish();

				} else {															
					mNewScenario = NewScenario.get(getApplicationContext());
					
				    if (isDeclenchementUtilisateur) {
				    	mNewScenario.setScenarioActivite("Désactivé");			   
				    	mNewScenario.setDeclenchementUtilisateur(true);
				    	Bitmap indicateurGris = BitmapFactory.decodeResource(getResources(), R.drawable.indicateur_gris);
				    	mNewScenario.setScenarioIndicateur(indicateurGris);
				    	Bitmap declencheurUtilisateur = BitmapFactory.decodeResource(getResources(), R.drawable.utilisateur);
				    	mNewScenario.setScenarioIcon(declencheurUtilisateur);
				    } else {
				    	mNewScenario.setScenarioActivite("Activé");
				    	mNewScenario.setDeclenchementUtilisateur(false);
				    	Bitmap indicateurBleu = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.indicateur_bleu);
				    	mNewScenario.setScenarioIndicateur(indicateurBleu);
				    	
						MyObject selectedObject = (MyObject) mSpinnerObject.getSelectedItem();
						MyObjectAction selectedAction = (MyObjectAction) mSpinnerAction.getSelectedItem();
						MyObjectParam selectedParam = (MyObjectParam) mSpinnerParam.getSelectedItem();
	
						ScenarioBlock blockDeclencheur = new ScenarioBlock();
						blockDeclencheur.setMyObject(selectedObject);
						blockDeclencheur.setMyObjectAction(selectedAction);
						blockDeclencheur.setMyObjectParam(selectedParam);	
						mNewScenario.setDeclencheur(blockDeclencheur);		
						mNewScenario.setScenarioIcon(selectedObject.getIcon());
				    }

					Intent i = new Intent(getApplicationContext(), EditionScenarioBlockActivity.class);
					startActivityForResult(i, 0);

				}
			}
		});
				
		if (scenarioNb != -1){
			ScenarioList mScenarioList = ScenarioList.get(getApplicationContext());
			Scenario currentScenario = new Scenario();
			ArrayList<Object> scenarioArray = mScenarioList.getScenarioArray();
			currentScenario = (Scenario)scenarioArray.get(scenarioNb);
			ScenarioBlock declencheurBlock = new ScenarioBlock();
			declencheurBlock = currentScenario.getDeclencheur();

			if (declencheurBlock == null) {
				positionObject = 0;
				positionAction = 0;
				positionParam = 0;
			} else {
				positionObject = declencheurBlock.getMyObject().getId()-1;
				positionAction = declencheurBlock.getMyObjectAction().getId()-1;
				positionParam = declencheurBlock.getMyObjectParam().getId()-1;
			}
					
			mSpinnerObject.setSelection(positionObject);
			mSpinnerAction.setSelection(positionAction);
			mSpinnerParam.setSelection(positionParam);
		}
		
		RadioButton declencheurRadioButton1 = (RadioButton)findViewById(R.id.declenchement_utilisateur);
		declencheurRadioButton1.setChecked(false);
		RadioButton declencheurRadioButton2 = (RadioButton)findViewById(R.id.declenchement_objet);
		declencheurRadioButton2.setChecked(true);
	}
	
	public void addItemsObjectSpinner(){
		mSpinnerObject = (Spinner)findViewById(R.id.scenario_edit_trigger_object_spinner);
		objectList = MyObjectList.get(getApplicationContext()).getInRealmMyObjectArray();
		customObjectSpinnerAdapter = new CustomObjectSpinnerAdapter(getApplicationContext(), R.layout.row_spinner_object, objectList);
		customObjectSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinnerObject.setAdapter(customObjectSpinnerAdapter);
	}
	
	public void addListenerOnObjectSpinnerItemSelection(){
		mSpinnerObject = (Spinner)findViewById(R.id.scenario_edit_trigger_object_spinner);
		mSpinnerObject.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				customObjectSpinnerAdapter = new CustomObjectSpinnerAdapter(getApplicationContext(), R.layout.row_spinner_object, objectList);
				MyObject myObject = (MyObject)customObjectSpinnerAdapter.getItem(position);
				mSpinnerAction = (Spinner)findViewById(R.id.scenario_edit_trigger_action_spinner);
				actionList = (ArrayList<Object>) myObject.getActionList();
				customActionSpinnerAdapter = new CustomActionSpinnerAdapter(getApplicationContext(), R.layout.row_spinner_action, actionList);
				customActionSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				mSpinnerAction.setEnabled(true);
				mSpinnerAction.setClickable(true);
				mSpinnerAction.setAdapter(customActionSpinnerAdapter);			
				
				addListenerOnActionSpinnerItemSelection();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void addListenerOnActionSpinnerItemSelection(){
		mSpinnerAction = (Spinner)findViewById(R.id.scenario_edit_trigger_action_spinner);
		mSpinnerAction.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				customActionSpinnerAdapter = new CustomActionSpinnerAdapter(getApplicationContext(), R.layout.row_spinner_action, actionList);
				MyObjectAction myAction = (MyObjectAction)customActionSpinnerAdapter.getItem(position);
				mSpinnerParam = (Spinner)findViewById(R.id.scenario_edit_trigger_param_spinner);
				paramList = (ArrayList<Object>) myAction.getParamList();
				customParamSpinnerAdapter = new CustomParamSpinnerAdapter(getApplicationContext(), R.layout.row_spinner_param, paramList);
				customParamSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				mSpinnerParam.setEnabled(true);
				mSpinnerParam.setClickable(true);
				mSpinnerParam.setAdapter(customParamSpinnerAdapter);					
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void onRadioButtonClicked(View view) {

	    boolean checked = ((RadioButton) view).isChecked();
	    
	    switch(view.getId()) {
	        case R.id.declenchement_utilisateur:
	            if (checked)
	            	mSpinnerObject.setEnabled(false);
	            	mSpinnerObject.setClickable(false);
	            	mSpinnerAction.setEnabled(false);
	            	mSpinnerAction.setClickable(false);
	            	mSpinnerParam.setEnabled(false);
	            	mSpinnerParam.setClickable(false);
	            break;
	        case R.id.declenchement_objet:
	            if (checked)
	            	mSpinnerObject.setEnabled(true);
	            	mSpinnerObject.setClickable(true);
	            	mSpinnerAction.setEnabled(true);
	            	mSpinnerAction.setClickable(true);
	            	mSpinnerParam.setEnabled(true);
	            	mSpinnerParam.setClickable(true);
	            break;
	    }
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
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

	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
//			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		int position = -1;
		if (resultCode == RESULT_OK) {
			position = data.getIntExtra("position", -1);
			Intent i = new Intent();
			i.putExtra("position", position);
			setResult(RESULT_OK, i);
			finish();
		}
	}
}
