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
import android.view.MenuItem;

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
		
		this.setResult(RESULT_CANCELED);
				
		//set title for screen
		Resources res = getResources();
		this.setTitle(res.getString(R.string.app_name) + " - " + res.getString(R.string.title_macro_list));
	}
		
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) 
	{
	    switch (menuItem.getItemId()) 
	    {
		    case android.R.id.home:
		      super.onBackPressed();
		      return true;
	    }
	    
	  return (super.onOptionsItemSelected(menuItem));
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		Intent rawData = new Intent();
		String raw = "";
		String dataIn = data.getStringExtra("input");
		
		switch (requestCode)
		{
			case 1:
				raw = "JOIN " + dataIn + "\r\n";
				break;
			case 2:
				raw = "PART " + dataIn + "\r\n";
				break;
			case 3:
				raw = "QUIT" + "\r\n";
				break;
			case 4:
				raw = "TOPIC " + dataIn + "\r\n";
				break;
		}
		rawData.putExtra("raw", raw);
		this.setResult(RESULT_OK, rawData);
		this.finish();
	}

	/* (non-Javadoc)
	 * @see com.hinodesoftworks.circe.MacroListFragment.OnMacroSelectedListener#onMacroSelected()
	 */
	@Override
	public void onMacroSelected(int selectedMacro)
	{
		Intent i = new Intent(this, MacroParamInputActivity.class);
		this.startActivityForResult(i, selectedMacro+1);
	}

}
