/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;

import com.hinodesoftworks.circe.MacroListFragment.OnMacroSelectedListener;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

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
		
		//set title for screen
		Resources res = getResources();
		this.setTitle(res.getString(R.string.app_name) + " - " + res.getString(R.string.title_macro_list));
	}

	
	
	/* (non-Javadoc)
	 * @see com.hinodesoftworks.circe.MacroListFragment.OnMacroSelectedListener#onMacroSelected()
	 */
	@Override
	public void onMacroSelected()
	{
		//TODO static
		Intent i = new Intent(this, MacroEditorActivity.class);
		startActivity(i);
	}

}
