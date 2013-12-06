/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;

import java.util.ArrayList;

import com.hinodesoftworks.circe.ServerListFragment.OnServerSelectedListener;
import com.hinodesoftworks.utils.Server;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerList.
 */
public class ServerList extends Activity implements OnServerSelectedListener
{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
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

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.server_list, menu);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		Intent i = null;
		
		switch(item.getItemId())
		{
			case R.id.action_add_server:
				i = new Intent(this, AddServerActivity.class);
				startActivityForResult(i, 0);
				break;
			case R.id.action_settings:
				i = new Intent(this, SettingsActivity.class);
				startActivity(i);
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hinodesoftworks.circe.ServerListFragment.OnServerSelectedListener#onServerSelected()
	 */
	@Override
	public void onServerSelected()
	{
		// TODO non dynamic code
		Intent i = new Intent(this, ServerViewActivity.class);
		startActivity(i);
		
	}

}
