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
import com.hinodesoftworks.utils.ServerDataSource;
import com.hinodesoftworks.utils.ServerManager;

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
	ServerDataSource serverDataSource;
	ServerManager serverManager;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_server_list);
		

		
		
		//non demo code starts here
		//vars
		serverDataSource = new ServerDataSource(this);
		serverDataSource.openDatabase();
		
		serverManager = ServerManager.getInstance();
		
		//use the manager to properly update the server list, on create will normally 
		//get a fresh list, but in the case of rotation, don't reset all servers
		ArrayList<Server> allServs = serverDataSource.getAllServers();
		serverManager.updateServers(allServs);
		
		ArrayList<Server> managedServers = serverManager.getServers();
		ServerListAdapter adapter = new ServerListAdapter(this, R.layout.row_server_list, managedServers);
		
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
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK)
		{
			Server serv = new Server(data.getStringExtra("serv_name"), data.getStringExtra("serv_address"));
			serv.setServerPort(data.getIntExtra("port", 6666));
			serv.setUserName(data.getStringExtra("username"));
			serv.setPassword(data.getStringExtra("password"));
			
			serverDataSource.createServer(serv);
			
			ArrayList<Server> allServs = serverDataSource.getAllServers();
			serverManager.updateServers(allServs);
			
			ArrayList<Server> managedServers = serverManager.getServers();
			ServerListAdapter adapter = new ServerListAdapter(this, R.layout.row_server_list, managedServers);
			
			ServerListFragment serverFrag = (ServerListFragment) getFragmentManager().findFragmentById(R.id.server_list_fragment);
			serverFrag.setListAdapter(adapter);
		}
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

	@Override
	public void onServerDelete(int selectedItem)
	{
		Server server = serverManager.getServerAtPositon(selectedItem);
		serverDataSource.deleteServer(server);
		
		ArrayList<Server> allServs = serverDataSource.getAllServers();
		serverManager.updateServers(allServs);
		
		ArrayList<Server> managedServers = serverManager.getServers();
		ServerListAdapter adapter = new ServerListAdapter(this, R.layout.row_server_list, managedServers);
		
		ServerListFragment serverFrag = (ServerListFragment) getFragmentManager().findFragmentById(R.id.server_list_fragment);
		serverFrag.setListAdapter(adapter);
	}

	@Override
	public void onServerEdit(int selectedItem)
	{
		Server server = serverManager.getServerAtPositon(selectedItem);
		
		Intent sender = new Intent(this, AddServerActivity.class);
		sender.setAction(Intent.ACTION_EDIT);
		
		sender.putExtra("serv_name", server.getServerName());
		sender.putExtra("serv_address", server.getServerAddress());
		sender.putExtra("username", server.getUserName());
		sender.putExtra("port", server.getServerPort());
		sender.putExtra("password", server.getPassword());
		
		startActivityForResult(sender, 0);
		
	}

}
