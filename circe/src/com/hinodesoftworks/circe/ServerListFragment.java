package com.hinodesoftworks.circe;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ServerListFragment extends ListFragment
{
	public interface OnServerSelectedListener
	{
		//TODO: Not dynamic
		public void onServerSelected();
	}

	OnServerSelectedListener listener;
	
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
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_server_list, null);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		listener.onServerSelected();
	}
	
	
	
	
}
