/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;


import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.FragmentManager;

// TODO: Auto-generated Javadoc
/**
 * The Class SettingsActivity.
 */
public class SettingsActivity extends Activity implements OnFragmentChangeRequestListener
{
	FragmentType currentType;
	SettingsFragment settingsFragment;
	LimitedFileBrowserFragment fileBrowserFragment;
	boolean hasBrowseFrag = true;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		currentType = FragmentType.FRAGMENT_SETTINGS;
		
		settingsFragment = new SettingsFragment();
		fileBrowserFragment = new LimitedFileBrowserFragment();
		getFragmentManager().beginTransaction().replace(android.R.id.content, settingsFragment, "settings").commit();
		
		//TODO Static data
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("cIRCe - Settings");
	}

	/* (non-Javadoc)
	 * @see com.hinodesoftworks.circe.OnFragmentChangeRequestListener#onFragmentChangeRequest(com.hinodesoftworks.circe.OnFragmentChangeRequestListener.FragmentType)
	 */
	@Override
	public void onFragmentChangeRequest(FragmentType type)
	{
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		
		currentType = type;
		
		if (fileBrowserFragment == null)
			fileBrowserFragment = new LimitedFileBrowserFragment();
		
		switch(type)
		{
			case FRAGMENT_FILE_BROWSER:
				transaction.replace(android.R.id.content, fileBrowserFragment, "file_browse");
				transaction.addToBackStack(null);
				break;
			case FRAGMENT_FILE_VIEWER:
				break;
			default:
				break;
		}
		
		transaction.commit();
		
	}
	
	
}
