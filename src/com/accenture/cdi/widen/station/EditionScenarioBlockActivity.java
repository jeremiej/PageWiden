package com.accenture.cdi.widen.station;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
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

public class EditionScenarioBlockActivity extends Activity {

	private Spinner mSpinnerObject, mSpinnerAction, mSpinnerParam;
	private NewScenario mNewScenario;
	private ScenarioList mScenarioList;
	private String editMode;
	
	CustomObjectSpinnerAdapter customObjectSpinnerAdapter;
	CustomActionSpinnerAdapter customActionSpinnerAdapter;
	CustomParamSpinnerAdapter customParamSpinnerAdapter;
	ArrayList<Object> objectList;
	ArrayList<Object> actionList;
	ArrayList<Object> paramList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_edition_scenario_block);

		setupActionBar();
		
		Intent i = getIntent();
		editMode = i.getStringExtra("editMode");
		final int scenarioNb = i.getIntExtra("position", -1);
		final int blockNb = i.getIntExtra("blockNb", -1);
		
		if (editMode != null) {
			Button continueButton = (Button)findViewById(R.id.scenario_edit_continue);
			continueButton.setVisibility(8);
		}
		
		mSpinnerAction = (Spinner)findViewById(R.id.scenario_edit_block_action_spinner);
		mSpinnerAction.setEnabled(false);
		mSpinnerAction.setClickable(false);
		
		mSpinnerParam = (Spinner)findViewById(R.id.scenario_edit_block_param_spinner);
		mSpinnerParam.setEnabled(false);
		mSpinnerParam.setClickable(false);
				
		addItemsObjectSpinner();
		addListenerOnObjectSpinnerItemSelection();

		Button validationButton = (Button) findViewById(R.id.scenario_edit_validation);
		validationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mScenarioList = ScenarioList.get(getApplicationContext());
				
				if (editMode != null) {
					Scenario currentScenario = null;
					ArrayList<Object> scenarioArray = mScenarioList.getScenarioArray();
					currentScenario = (Scenario) scenarioArray.get(scenarioNb);
					
					MyObject selectedObject = (MyObject)mSpinnerObject.getSelectedItem();
					MyObjectAction selectedAction = (MyObjectAction)mSpinnerAction.getSelectedItem();
					MyObjectParam selectedParam = (MyObjectParam)mSpinnerParam.getSelectedItem();
													
					if(editMode.equals("editBlock")){
						ArrayList<Object> blockArray = currentScenario.getBlocks();
						ScenarioBlock currentBlock = (ScenarioBlock)blockArray.get(blockNb);
						currentBlock.setMyObject(selectedObject);
						currentBlock.setMyObjectAction(selectedAction);
						currentBlock.setMyObjectParam(selectedParam);
						blockArray.set(blockNb, currentBlock);
						currentScenario.setBlocks(blockArray);						
					} else {
						ScenarioBlock blockNew = new ScenarioBlock();
						
						blockNew.setMyObject(selectedObject);
						blockNew.setMyObjectAction(selectedAction);
						blockNew.setMyObjectParam(selectedParam);
						
						currentScenario.addBlocks(blockNew);
					}

					scenarioArray.set(scenarioNb, currentScenario);
					mScenarioList.setScenarioArray(scenarioArray);

					Intent i = new Intent();
					i.putExtra("position", scenarioNb);
					setResult(RESULT_OK, i);
					finish();

				} else {
					mNewScenario = NewScenario.get(getApplicationContext());
					MyObject selectedObject = (MyObject)mSpinnerObject.getSelectedItem();
					MyObjectAction selectedAction = (MyObjectAction)mSpinnerAction.getSelectedItem();
					MyObjectParam selectedParam = (MyObjectParam)mSpinnerParam.getSelectedItem();
					ScenarioBlock blockNew = new ScenarioBlock();
					blockNew.setMyObject(selectedObject);
					blockNew.setMyObjectAction(selectedAction);
					blockNew.setMyObjectParam(selectedParam);
					mNewScenario.addBlocks(blockNew);

					mScenarioList.removeLastScenario();
					mScenarioList.addScenario(mNewScenario);
					int position = mScenarioList.getNbScenario() - 1;
					mScenarioList.addButtonAddScenario();				
					
					mNewScenario.destroy();

					Intent i = new Intent();
					i.putExtra("position", position);
					setResult(RESULT_OK, i);
					finish();
				}
			}
		});
		
		if (editMode == null){
			Button continueButton = (Button)findViewById(R.id.scenario_edit_continue);
			continueButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					mScenarioList = ScenarioList.get(getApplicationContext());
					
					mNewScenario = NewScenario.get(getApplicationContext());
					MyObject selectedObject = (MyObject)mSpinnerObject.getSelectedItem();
					MyObjectAction selectedAction = (MyObjectAction)mSpinnerAction.getSelectedItem();
					MyObjectParam selectedParam = (MyObjectParam)mSpinnerParam.getSelectedItem();
					ScenarioBlock blockNew = new ScenarioBlock();
					blockNew.setMyObject(selectedObject);
					blockNew.setMyObjectAction(selectedAction);
					blockNew.setMyObjectParam(selectedParam);
					mNewScenario.addBlocks(blockNew);	

					Intent i = new Intent(getApplicationContext(), EditionScenarioBlockActivity.class);
					i.putExtra("position", scenarioNb);
	                startActivityForResult(i, 0);
				}
			});
		}
	}
	
	public void addItemsObjectSpinner(){
		mSpinnerObject = (Spinner)findViewById(R.id.scenario_edit_block_object_spinner);
		MyObjectList sMyObjectList = new MyObjectList(getApplicationContext());
		objectList = (ArrayList<Object>) sMyObjectList.getInRealmMyObjectArray();
		customObjectSpinnerAdapter = new CustomObjectSpinnerAdapter(getApplicationContext(), R.layout.row_spinner_object, objectList);
		customObjectSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinnerObject.setAdapter(customObjectSpinnerAdapter);
	}
	
	public void addListenerOnObjectSpinnerItemSelection(){
		mSpinnerObject = (Spinner)findViewById(R.id.scenario_edit_block_object_spinner);
		mSpinnerObject.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				customObjectSpinnerAdapter = new CustomObjectSpinnerAdapter(getApplicationContext(), R.layout.row_spinner_object, objectList);
				MyObject myObject = (MyObject)customObjectSpinnerAdapter.getItem(position);
				mSpinnerAction = (Spinner)findViewById(R.id.scenario_edit_block_action_spinner);
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
		mSpinnerAction = (Spinner)findViewById(R.id.scenario_edit_block_action_spinner);
		mSpinnerAction.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				customActionSpinnerAdapter = new CustomActionSpinnerAdapter(getApplicationContext(), R.layout.row_spinner_action, actionList);
				MyObjectAction myAction = (MyObjectAction)customActionSpinnerAdapter.getItem(position);
				mSpinnerParam = (Spinner)findViewById(R.id.scenario_edit_block_param_spinner);
				paramList = (ArrayList<Object>) myAction.getParamList();
				customParamSpinnerAdapter = new CustomParamSpinnerAdapter(getApplicationContext(), R.layout.row_spinner_param, paramList);
				customParamSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				mSpinnerParam.setEnabled(true);
				mSpinnerParam.setClickable(true);
				mSpinnerParam.setAdapter(customParamSpinnerAdapter);	
				//addListenerOnParamSpinnerItemSelection();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edition_scenario_declencheur, menu);
		return true;
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
