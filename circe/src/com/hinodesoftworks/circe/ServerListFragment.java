package com.hinodesoftworks.circe;

import android.app.ListFragment;

public class ServerListFragment extends ListFragment
{
	
	
	public void onReceivedAdapter(ServerListAdapter adapter)
	{
		this.setListAdapter(adapter);
	}
	
	
}
