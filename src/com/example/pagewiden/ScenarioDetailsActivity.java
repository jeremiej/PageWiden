package com.example.pagewiden;

import java.util.ArrayList;

import com.example.pagewiden.adapters.CustomListViewScenarioBlockAdapter;
import com.example.pagewiden.model.Scenario;
import com.example.pagewiden.model.ScenarioList;
import com.example.pagewiden.model.User;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

public class ScenarioDetailsActivity extends Activity implements CompoundButton.OnCheckedChangeListener{
	
	private Scenario mScenario;
	private ArrayList<Object> mScenarioArray;
	private ScenarioList mScenarioList;
	private int positionScenario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scenario_details);
		Intent i = getIntent();		
		positionScenario = i.getExtras().getInt("id");
        
        mScenarioList = ScenarioList.get(getApplicationContext());        
        mScenarioArray = mScenarioList.getScenarioArray();        
        mScenario = (Scenario)mScenarioArray.get(positionScenario);
        
        TextView scenarioTitle = (TextView) findViewById(R.id.scenario_title);
        scenarioTitle.setText(mScenario.getScenarioTitle());
        
        TextView scenarioDesc = (TextView) findViewById(R.id.scenario_description);
        scenarioDesc.setText("\" "+mScenario.getScenarioDescription()+" \"");
        
        ImageView triggerIcon = (ImageView) findViewById(R.id.trigger_object_icon);
                
        TextView triggerName = (TextView) findViewById(R.id.trigger_object_name);        
        if(mScenario.getDeclencheur() == null){
        	TextView scenarioBlockObjectTitle = (TextView)findViewById(R.id.scenario_block_object_title);
        	scenarioBlockObjectTitle.setVisibility(8);        	
        	triggerName.setText("Utilisateur");
        	Bitmap iconUtilisateur = BitmapFactory.decodeResource(getResources(), R.drawable.utilisateur);
        	triggerIcon.setImageBitmap(iconUtilisateur);
        	TextView scenarioBlockParamTitle = (TextView)findViewById(R.id.scenario_block_param_title);
        	scenarioBlockParamTitle.setVisibility(8);
        }else{
        	String declencheurNom = mScenario.getDeclencheur().getMyObject().getName();
        	triggerName.setText(declencheurNom);
        	triggerIcon.setImageBitmap(mScenario.getDeclencheur().getMyObject().getIcon());
            TextView triggerParam = (TextView) findViewById(R.id.trigger_param);
            triggerParam.setText(mScenario.getDeclencheur().getMyObjectParam().getLabel());
        }
        
        ListView scenarioBlockList = (ListView)findViewById(R.id.scenario_blocks_listview);
        CustomListViewScenarioBlockAdapter customBlockAdapter = new CustomListViewScenarioBlockAdapter(getApplicationContext(), 
        		R.layout.list_scenario_blocks, mScenario.getBlocks());
        scenarioBlockList.setAdapter(customBlockAdapter);
        
        ImageView addScenarioBlock = (ImageView)findViewById(R.id.scenario_add_block_button);
        addScenarioBlock.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final int position = positionScenario;
				Intent i = new Intent(getApplicationContext(), EditionScenarioBlockActivity.class);
				i.putExtra("scenarioNb", position);
				i.putExtra("editMode", "addBlock");
				startActivity(i);
			}
		});
        
        ImageView editScenarioName = (ImageView)findViewById(R.id.edit_name);
        editScenarioName.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final int position = positionScenario;
				Intent i = new Intent(getApplicationContext(), EditionScenarioNomActivity.class);
				i.putExtra("scenarioNb", position);
				startActivity(i);
			}
		});
        
        ImageView editScenarioDeclencheur = (ImageView)findViewById(R.id.edit_trigger);
        editScenarioDeclencheur.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final int position = positionScenario;
				Intent i = new Intent(getApplicationContext(), EditionScenarioDeclencheurActivity.class);
				i.putExtra("scenarioNb", position);
				startActivity(i);
			}
		});
        
        registerForContextMenu(scenarioBlockList);
        
        scenarioBlockList.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				
				return false;
			}
		});
        
        Switch activationSwitch = (Switch)findViewById(R.id.switch_activation);
        
        if(mScenario.getScenarioActivite().equals("Désactivé")){
        	activationSwitch.setChecked(false);
        }else{
        	activationSwitch.setChecked(true);
        }
        
        activationSwitch.setOnCheckedChangeListener(this);
        
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
		getMenuInflater().inflate(R.menu.scenario_details, menu);
		return true;
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.scenario_blocks_listview) {
			menu.setHeaderTitle("Options");
			menu.add(Menu.NONE, 0, 0, "Modifier");
			menu.add(Menu.NONE, 1, 1, "Supprimer");
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		final int position = positionScenario;
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		if(item.getItemId() == 0){
			// Modifier
			Intent i = new Intent(getApplicationContext(), EditionScenarioBlockActivity.class);
			i.putExtra("scenarioNb", position);
			i.putExtra("blockNb", info.position);
			i.putExtra("editMode", "editBlock");
			startActivity(i);
		}else if(item.getItemId() == 1){
			ArrayList<Object> blockArray = mScenario.getBlocks();
			mScenario.removeBlock(info.position);						
			mScenario.setBlocks(blockArray);	
			mScenarioArray.set(positionScenario, mScenario);
			mScenarioList.setScenarioArray(mScenarioArray);
			
	        ListView scenarioBlockList = (ListView)findViewById(R.id.scenario_blocks_listview);
	        CustomListViewScenarioBlockAdapter customBlockAdapter = new CustomListViewScenarioBlockAdapter(getApplicationContext(), 
	        		R.layout.list_scenario_blocks, mScenario.getBlocks());
	        scenarioBlockList.setAdapter(customBlockAdapter);
	        customBlockAdapter.notifyDataSetChanged();
		}
		return true;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if(isChecked){
			mScenario.setScenarioActivite("Activé");
			Bitmap iconIndicateur = BitmapFactory.decodeResource(getResources(), R.drawable.indicateur_bleu);
			mScenario.setScenarioIndicateur(iconIndicateur);
			mScenarioArray.set(positionScenario, mScenario);
			mScenarioList.setScenarioArray(mScenarioArray);
		}else{
			mScenario.setScenarioActivite("Désactivé");
			Bitmap iconIndicateur = BitmapFactory.decodeResource(getResources(), R.drawable.indicateur_gris);
			mScenario.setScenarioIndicateur(iconIndicateur);
			mScenarioArray.set(positionScenario, mScenario);
			mScenarioList.setScenarioArray(mScenarioArray);
		}
	}
}
