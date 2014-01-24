package com.accenture.cdi.widen.station;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.accenture.cdi.widen.station.model.NewScenario;
import com.accenture.cdi.widen.station.model.Scenario;
import com.accenture.cdi.widen.station.model.ScenarioList;
import com.example.pagewiden.R;

public class EditionScenarioNomActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edition_scenario_nom);
		
		Intent i = getIntent();

		final int scenarioNb = i.getIntExtra("scenarioNb", 999);
		
		if(scenarioNb!=999){
			ScenarioList scenarioList = ScenarioList.get(getApplicationContext());
			ArrayList<Object> scenarioArray = scenarioList.getScenarioArray();
			Scenario currentScenario = (Scenario)scenarioArray.get(scenarioNb);
			EditText scenarioName = (EditText)findViewById(R.id.scenario_edit_name);
			scenarioName.setText(currentScenario.getScenarioTitle());
			EditText scenarioDescription = (EditText)findViewById(R.id.scenario_edit_description);
			scenarioDescription.setText(currentScenario.getScenarioDescription());
		}
		
		Button validationButton = (Button) findViewById(R.id.scenario_edit_validation);
		validationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText evName = (EditText)findViewById(R.id.scenario_edit_name);
				EditText evDescription = (EditText)findViewById(R.id.scenario_edit_description);
				if(scenarioNb!=999){
					ScenarioList scenarioList = ScenarioList.get(getApplicationContext());
					ArrayList<Object> scenarioArray = scenarioList.getScenarioArray();
					Scenario currentScenario = (Scenario)scenarioArray.get(scenarioNb);
					currentScenario.setScenarioTitle(evName.getText().toString());
					currentScenario.setScenarioDescription(evDescription.getText().toString());
					scenarioArray.set(scenarioNb, currentScenario);
					scenarioList.setScenarioArray(scenarioArray);

					// JGU
//					Intent i = new Intent(getApplicationContext(), ScenarioDetailsActivity.class);
//					i.putExtra("id", scenarioNb);
//					startActivity(i);
					setResult(RESULT_OK);
					finish();
					///JGU
				}else{
					NewScenario newScenario = NewScenario.get(getApplicationContext());
					newScenario.setScenarioTitle(evName.getText().toString());
					newScenario.setScenarioDescription(evDescription.getText().toString());

					// JGU
					Intent i = new Intent(getApplicationContext(), EditionScenarioDeclencheurActivity.class);
//					startActivity(i);
					startActivityForResult(i, 0);
					///JGU
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edition_scenario_nom, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			setResult(RESULT_OK);
			finish();
		}
	}
}
