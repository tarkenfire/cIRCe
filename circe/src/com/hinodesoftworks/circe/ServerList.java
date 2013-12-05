package com.hinodesoftworks.circe;

import java.util.ArrayList;

import com.hinodesoftworks.utils.Server;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;

public class ServerList extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_server_list);
		
		//TODO: Static data for milestone 1. Remove for m2
		Server serv1 = new Server("Quakenet", "se.quakenet.org");
		Server serv2 = new Server("Dalnet", "halcyon.il.us.dal.net");
		Server serv3 = new Server("Geekshed", "irc.geekshed.net");
		
		serv1.setConnected(true);
		serv2.setConnected(true);
		serv3.setConnected(false);
		
		ArrayList<Server> demoAdapterList = new ArrayList<Server>();
		demoAdapterList.add(serv1);
		demoAdapterList.add(serv2);
		demoAdapterList.add(serv3);
		
		ServerListAdapter adapter = new ServerListAdapter(this, R.layout.row_server_list, demoAdapterList);
		
		//non demo code starts here
		
		//set title for screen
		Resources res = getResources();
		this.setTitle(res.getString(R.string.app_name) + " - " + res.getString(R.string.title_server_list));
		
		ServerListFragment serverFrag = (ServerListFragment) getFragmentManager().findFragmentById(R.id.server_list_fragment);
		serverFrag.setListAdapter(adapter);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.server_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		Intent i = null;
		
		switch(item.getItemId())
		{
			case R.id.action_add_server:
				i = new Intent(this, AddServerActivity.class);
				break;
			case R.id.action_settings:
				i = new Intent(this, SettingsActivity.class);
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		
		if (i != null)
			startActivity(i);
		
		return true;
	}

}
