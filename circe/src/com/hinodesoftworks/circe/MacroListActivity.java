/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;

import java.util.ArrayList;

import com.hinodesoftworks.circe.MacroListFragment.OnMacroSelectedListener;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class MacroListActivity.
 */
public class MacroListActivity extends Activity implements OnMacroSelectedListener
{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_macro_list);
		
		ArrayList<String> macroNames = new ArrayList<String>();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, macroNames);
		
		//set title for screen
		Resources res = getResources();
		this.setTitle(res.getString(R.string.app_name) + " - " + res.getString(R.string.title_macro_list));
		
		MacroListFragment mlf = (MacroListFragment) getFragmentManager().findFragmentById(R.id.macro_list);
		mlf.setListAdapter(adapter);
	}
		
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{

		super.onActivityResult(requestCode, resultCode, data);
	}


	/* (non-Javadoc)
	 * @see com.hinodesoftworks.circe.MacroListFragment.OnMacroSelectedListener#onMacroSelected()
	 */
	@Override
	public void onMacroSelected()
	{
	}

}
