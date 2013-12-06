/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerListFragment.
 */
public class ServerListFragment extends ListFragment
{
	
	/**
	 * The listener interface for receiving onServerSelected events.
	 * The class that is interested in processing a onServerSelected
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addOnServerSelectedListener<code> method. When
	 * the onServerSelected event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see OnServerSelectedEvent
	 */
	public interface OnServerSelectedListener
	{
		//TODO: Not dynamic
		/**
		 * On server selected.
		 */
		public void onServerSelected();
	}

	OnServerSelectedListener listener;
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		
		try
		{
			listener = (OnServerSelectedListener)activity;
		}
		catch (ClassCastException e)
		{
			throw new ClassCastException(activity.toString() + " must implement OnFragmentChangeRequestListener");
		}
	}
	
	/* (non-Javadoc)
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_server_list, null);
	}

	/* (non-Javadoc)
	 * @see android.app.ListFragment#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		listener.onServerSelected();
	}
	
	
	
	
}
