/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;

import java.util.ArrayList;

import com.hinodesoftworks.utils.FragmentDataMapper;
import com.hinodesoftworks.utils.FragmentData;
import com.hinodesoftworks.utils.IRCConnection;
import com.hinodesoftworks.utils.IRCConnection.OnIRCMessageReceivedListener;
import com.hinodesoftworks.utils.Server;
import com.hinodesoftworks.utils.ServerManager;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;


// TODO: Auto-generated Javadoc
/**
 * The Class ServerViewActivity.
 */
public class ServerViewActivity extends Activity implements TabListener, OnIRCMessageReceivedListener
{
	ViewPager viewPager;
	ServerPagerAdapter servPagerAdapter;
	
	ActionBar actionBar;
	
	int fragmentCount = 1;
	String currentChan = "";
	
	FragmentDataMapper fragmentDataMapper;
	IRCConnection connection;
	
	ServerManager serverManager;
	
	int selectedServer;
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_server_view);
		
		serverManager = ServerManager.getInstance();
		selectedServer = this.getIntent().getExtras().getInt("selected_server_index");
		
		//set up action bar
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		//connect pager
		servPagerAdapter = new ServerPagerAdapter(getFragmentManager());
		fragmentDataMapper = FragmentDataMapper.getInstance();
		
		viewPager = (ViewPager)findViewById(R.id.server_view_pager);
		viewPager.setAdapter(servPagerAdapter);
		viewPager.setOffscreenPageLimit(10);
		viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{
				actionBar.setSelectedNavigationItem(position);
			}
		});
		
		//populate tabs
		Tab tabToAdd = actionBar.newTab();
		tabToAdd.setText("Network");
		tabToAdd.setTabListener(this);
		actionBar.addTab(tabToAdd);
		
		Server connServer = serverManager.getServerAtPositon(selectedServer);
		connection = serverManager.getConnection(connServer);
		connection.setOnIRCMessageReceivedListener(this);
		
		if (!connection.isConnected())
		{
			serverManager.startConnection(connServer);
		}
			
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.server_view, menu);
		return true;
	}
	
	public void sendServerMessage(String msg)
	{
		if (connection.isConnected())
		{
			connection.sendMessage(msg);
		}
	}
	
	public void sendRawMessage(String raw)
	{
		if (connection.isConnected())
		{
			connection.sendRawMessage(raw);
		}
		
		if (raw.contains("JOIN"))
		{
			Tab tabToAdd = actionBar.newTab();
			tabToAdd.setText(raw.substring(raw.indexOf(" ") + 1 ));
			tabToAdd.setTabListener(this);
			actionBar.addTab(tabToAdd);
			fragmentCount++;
			servPagerAdapter.notifyDataSetChanged();
			
		}
		
		if (raw.contains("PART"))
		{
			Tab tabToRemove = actionBar.getSelectedTab();
			actionBar.removeTab(tabToRemove);
			fragmentCount--;
			servPagerAdapter.notifyDataSetChanged();
		}
		
		if (raw.contains("QUIT"))
		{
			connection.disconnectFromServer();
			
		}
		
	}
	
	public void sendChannelMessage(String msg)
	{
		this.sendRawMessage("PRIVMSG " + currentChan + " " + msg);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch(item.getItemId())
		{
			case android.R.id.home:
		        super.onBackPressed();
		        return true;
			case R.id.action_show_user_list:
				Intent i = new Intent(this, UserListActivity.class);
				startActivity(i);
				return true;
			case R.id.action_open_macro_editor:
				Intent ii = new Intent(this, MacroListActivity.class);
				startActivityForResult(ii, 0);
				return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see android.app.ActionBar.TabListener#onTabSelected(android.app.ActionBar.Tab, android.app.FragmentTransaction)
	 */
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction)
	{
		viewPager.setCurrentItem(tab.getPosition());
		currentChan = tab.getText().toString();
	}

	/* (non-Javadoc)
	 * @see android.app.ActionBar.TabListener#onTabReselected(android.app.ActionBar.Tab, android.app.FragmentTransaction)
	 */
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction)
	{
		// do nothing	
	}
	
	/* (non-Javadoc)
	 * @see android.app.ActionBar.TabListener#onTabUnselected(android.app.ActionBar.Tab, android.app.FragmentTransaction)
	 */
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction)
	{
		//do nothing
	}
	
	@Override
	public void onChannelMessageReceived(String channel, String message)
	{
		final String msg = message;
		final String chan = channel;
		
		Log.i("CHANNEL", chan);
		
		this.runOnUiThread(new Runnable()
		{
			@Override
			public void run()
			{
				FragmentData data = fragmentDataMapper.getData(chan);
				ServerViewFragment visFrag = servPagerAdapter.getFragment(getActionBar().getSelectedNavigationIndex());
				
				if (data == null)
				{
					
					ArrayList<String> chatStrings = new ArrayList<String>();
					chatStrings.add(msg);
					data = new FragmentData(chatStrings);
					
					if (visFrag != null)
						visFrag.refresh(data);
					
					fragmentDataMapper.updateData(chan, data);
				}
				else
				{
					ArrayList<String> chatStrings = data.getChatStrings();
					chatStrings.add(msg);
					data = new FragmentData(chatStrings);
					if (visFrag != null)
						visFrag.refresh(data);
					fragmentDataMapper.updateData(chan, data);
				}	
			}
		});
		
		Log.i("CHANHANDLER", message);
		
	}

	@Override
	public void onNetworkMessageReceived(String message)
	{
		final String msg = message;
		
		this.runOnUiThread(new Runnable()
		{
			@Override
			public void run()
			{
				Tab checkTab = actionBar.getTabAt(actionBar.getSelectedNavigationIndex());
				if (!checkTab.getText().toString().equals("Network"))
				{
					return;
				}
				
				FragmentData data = fragmentDataMapper.getData("Network");
				ServerViewFragment visFrag = servPagerAdapter.getFragment(getActionBar().getSelectedNavigationIndex());
				
				if (data == null)
				{
					ArrayList<String> chatStrings = new ArrayList<String>();
					chatStrings.add(msg);
					data = new FragmentData(chatStrings);
					
					if (visFrag != null)
						visFrag.refresh(data);
					
					fragmentDataMapper.updateData("Network", data);
				}
				else
				{
					ArrayList<String> chatStrings = data.getChatStrings();
					chatStrings.add(msg);
					data = new FragmentData(chatStrings);
					if (visFrag != null)
						visFrag.refresh(data);
					fragmentDataMapper.updateData("Network", data);
				}	
			}
		});

		Log.i("HANDLER", message);
	}

	@Override
	public void onPrivateMessageReceived(String sender, String message)
	{
		// TODO Auto-generated method stub
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == RESULT_OK)
		{
			String rawData = data.getStringExtra("raw");
			this.sendRawMessage(rawData);
		}
	}
	
	/**
	 * The Class ServerPagerAdapter.
	 */
	private class ServerPagerAdapter extends FragmentStatePagerAdapter
	{
		SparseArray<ServerViewFragment> registeredFragments = new SparseArray<ServerViewFragment>();
		
		
		/**
		 * Instantiates a new server pager adapter.
		 *
		 * @param fm the fragment manager
		 */
		public ServerPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}

		/* (non-Javadoc)
		 * @see android.support.v13.app.FragmentPagerAdapter#getItem(int)
		 */
		@Override
		public Fragment getItem(int position)
		{
			Tab curTab = actionBar.getTabAt(position);
			
			ServerViewFragment serverFrag = ServerViewFragment.createInstance(curTab.getText().toString());
			
			registeredFragments.put(position, serverFrag);
			Log.i("FRAGMENT", serverFrag == null ? "True" : "False");
			return serverFrag;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) 
		{
		    super.destroyItem(container, position, object);
		    registeredFragments.remove(position);
		}
		
		
		/* (non-Javadoc)
		 * @see android.support.v4.view.PagerAdapter#getCount()
		 */
		@Override
		public int getCount()
		{
			return fragmentCount;
		}	

		public ServerViewFragment getFragment(int position)
		{
			return registeredFragments.get(position);
		}

	}


}
