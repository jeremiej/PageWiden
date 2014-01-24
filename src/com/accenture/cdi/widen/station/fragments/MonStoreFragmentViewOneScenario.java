package com.accenture.cdi.widen.station.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.accenture.cdi.widen.station.EditionScenarioBlockActivity;
import com.accenture.cdi.widen.station.EditionScenarioDeclencheurActivity;
import com.accenture.cdi.widen.station.EditionScenarioNomActivity;
import com.accenture.cdi.widen.station.MainActivity;
import com.accenture.cdi.widen.station.R;
import com.accenture.cdi.widen.station.adapters.CustomListViewScenarioBlockAdapter;
import com.accenture.cdi.widen.station.model.Scenario;
import com.accenture.cdi.widen.station.model.ScenarioList;

public class MonStoreFragmentViewOneScenario extends Fragment implements CompoundButton.OnCheckedChangeListener{

	public static final int MODE_CREATE = 0;
	public static final int MODE_EDIT_PARTIAL = 1;
	public static final int MODE_EDIT_FULL = 2;
	
	private Scenario mScenario;
	private ArrayList<Object> mScenarioArray;
	private ScenarioList mScenarioList;
	private int positionScenario;
	private TextView scenarioTitle;
	private TextView scenarioDesc;
	private TextView scenarioBlockObjectTitle;
	private TextView scenarioBlockParamTitle;
	private ImageView triggerIcon;
	private TextView triggerName;
	private TextView triggerParam;
	private Button buttonOk;
	private ListView scenarioBlockList;
	private CustomListViewScenarioBlockAdapter customBlockAdapter;


	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_scenario_details, container, false);

		Bundle args = getArguments();
		positionScenario = args.getInt("id");
        mScenarioList = ScenarioList.get(getActivity().getApplicationContext());        
        mScenarioArray = mScenarioList.getScenarioArray();
        mScenario = (Scenario) mScenarioArray.get(positionScenario);
        
        scenarioTitle = (TextView) v.findViewById(R.id.scenario_title);
        scenarioDesc = (TextView) v.findViewById(R.id.scenario_description);
        scenarioBlockObjectTitle = (TextView) v.findViewById(R.id.scenario_block_object_title);
        scenarioBlockParamTitle = (TextView) v.findViewById(R.id.scenario_block_param_title);
        triggerIcon = (ImageView) v.findViewById(R.id.trigger_object_icon);
        buttonOk = (Button) v.findViewById(R.id.button_ok);
        triggerName = (TextView) v.findViewById(R.id.trigger_object_name);
        triggerParam = (TextView) v.findViewById(R.id.trigger_param);

        buttonOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).monStoreViewAllDownloadableScenarios();
			}
		});

        scenarioBlockList = (ListView) v.findViewById(R.id.scenario_blocks_listview);
        customBlockAdapter = new CustomListViewScenarioBlockAdapter(getActivity().getApplicationContext(), 
        		R.layout.list_scenario_blocks, mScenario.getBlocks());
        scenarioBlockList.setAdapter(customBlockAdapter);
        
        ImageView editScenarioName = (ImageView) v.findViewById(R.id.edit_name);
        editScenarioName.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final int position = positionScenario;
				Intent i = new Intent(getActivity().getApplicationContext(), EditionScenarioNomActivity.class);
				i.putExtra("mode", MODE_EDIT_PARTIAL);
				i.putExtra("scenarioNb", position);
				startActivityForResult(i, 0);
			}
		});
        
        ImageView editScenarioDeclencheur = (ImageView) v.findViewById(R.id.edit_trigger);
        editScenarioDeclencheur.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final int position = positionScenario;
				Intent i = new Intent(getActivity().getApplicationContext(), EditionScenarioDeclencheurActivity.class);
				i.putExtra("mode", MODE_EDIT_PARTIAL);
				i.putExtra("scenarioNb", position);
				startActivityForResult(i, 0);
			}
		});

        ImageView addScenarioBlock = (ImageView) v.findViewById(R.id.scenario_add_block_button);
        addScenarioBlock.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final int position = positionScenario;
				Intent i = new Intent(getActivity().getApplicationContext(), EditionScenarioBlockActivity.class);
				i.putExtra("position", position);
				i.putExtra("editMode", "addBlock");
				startActivityForResult(i, 0);
			}
		});

        registerForContextMenu(scenarioBlockList);
        
        scenarioBlockList.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				
				return false;
			}
		});
        
        Switch activationSwitch = (Switch) v.findViewById(R.id.switch_activation);
        
        if("Désactivé".equals(mScenario.getScenarioActivite())){
        	activationSwitch.setChecked(false);
        }else{
        	activationSwitch.setChecked(true);
        }
        
        activationSwitch.setOnCheckedChangeListener(this);

        loadData();

        return v;
	}
	
	private void loadData() {
        scenarioTitle.setText(mScenario.getScenarioTitle());
        scenarioDesc.setText("\" "+mScenario.getScenarioDescription()+" \"");
        if(mScenario.isDeclenchementUtilisateur()){
        	scenarioBlockObjectTitle.setVisibility(8);        	
        	scenarioBlockParamTitle.setVisibility(8);
        	triggerParam.setVisibility(8);
        	triggerName.setText("Utilisateur");
        	Bitmap iconUtilisateur = BitmapFactory.decodeResource(getResources(), R.drawable.utilisateur);
        	triggerIcon.setImageBitmap(iconUtilisateur);

        } else {
        	scenarioBlockObjectTitle.setVisibility(View.VISIBLE);        	
        	scenarioBlockParamTitle.setVisibility(View.VISIBLE);
        	triggerParam.setVisibility(View.VISIBLE);
        	String declencheurNom = mScenario.getDeclencheur().getMyObject().getName();
        	triggerName.setText(declencheurNom);
        	triggerIcon.setImageBitmap(mScenario.getDeclencheur().getMyObject().getIcon());
            triggerParam.setText(mScenario.getDeclencheur().getMyObjectParam().getLabel());
        }
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
		if (item.getItemId() == 0){
			Intent i = new Intent(getActivity().getApplicationContext(), EditionScenarioBlockActivity.class);
			i.putExtra("position", position);
			i.putExtra("blockNb", info.position);
			i.putExtra("editMode", "editBlock");
			startActivityForResult(i, 0);

		} else if(item.getItemId() == 1){
			ArrayList<Object> blockArray = mScenario.getBlocks();
			mScenario.removeBlock(info.position);						
			mScenario.setBlocks(blockArray);	
			mScenarioArray.set(positionScenario, mScenario);
			mScenarioList.setScenarioArray(mScenarioArray);

	        CustomListViewScenarioBlockAdapter customBlockAdapter = new CustomListViewScenarioBlockAdapter(getActivity().getApplicationContext(), 
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

	@Override
	public void onResume() {
		loadData();
		customBlockAdapter.notifyDataSetChanged();
		super.onResume();
	}

}
