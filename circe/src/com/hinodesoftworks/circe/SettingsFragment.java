/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;

import com.hinodesoftworks.circe.OnFragmentChangeRequestListener.FragmentType;

import android.app.Activity;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Fragment;

public class SettingsFragment extends PreferenceFragment implements OnPreferenceClickListener
{	
	Preference logViewerPref;
	OnFragmentChangeRequestListener listener;
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		
		try
		{
			listener = (OnFragmentChangeRequestListener)activity;
		}
		catch (ClassCastException e)
		{
			throw new ClassCastException(activity.toString() + " must implement OnFragmentChangeRequestListener");
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		
		//get handles
		logViewerPref = (Preference)findPreference("pref_launch_log_viewer");
		
		//set listeners
		logViewerPref.setOnPreferenceClickListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View layout = inflater.inflate(R.layout.fragment_settings, null);
		return layout;
	}

	@Override
	public boolean onPreferenceClick(Preference preference)
	{
		
		//validate clicked pref, even if there is only one set to this listener,
		//for ease of future expandabilty
		if (preference.getKey().equals("pref_launch_log_viewer"))
		{
			listener.onFragmentChangeRequest(FragmentType.FRAGMENT_FILE_BROWSER);
		}
		
		return false;
	}
	
	
	
}
