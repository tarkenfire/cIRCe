/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.hinodesoftworks.utils.IRCConnection;
import com.hinodesoftworks.utils.IRCConnection.OnIRCMessageReceivedListener;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;


// TODO: Auto-generated Javadoc
/**
 * The Class ServerViewActivity.
 */
public class ServerViewActivity extends Activity implements TabListener, OnIRCMessageReceivedListener
{
	ViewPager viewPager;
	ServerPagerAdapter servPagerAdapter;
	ArrayList<String> chatBuffer; 
	ArrayAdapter<String> adapter;
	
	ServerViewFragment svf;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_server_view);
		
		//set up action bar
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		//connect pager
		servPagerAdapter = new ServerPagerAdapter(getFragmentManager());
		
		viewPager = (ViewPager)findViewById(R.id.server_view_pager);
		viewPager.setAdapter(servPagerAdapter);
		viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{
				actionBar.setSelectedNavigationItem(position);
			}
		});
		
		//populate tabs
		for (int i = 0; i < servPagerAdapter.getCount(); i++)
		{
			Tab tabToAdd = actionBar.newTab();
			tabToAdd.setText(servPagerAdapter.getPageTitle(i));
			tabToAdd.setTabListener(this);
			actionBar.addTab(tabToAdd);
		}
		
		//svf = (ServerViewFragment)getFragmentManager().findFragmentByTag(getTabFragmentTag(viewPager.getId(), 0));
		IRCConnection testConn = new IRCConnection();
		testConn.setOnIRCMessageReceivedListener(this);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		svf.setListAdapter(adapter);
		
		try
		{
			testConn.connectToServer("irc.geekshed.net", 6667, "Tarkenmeh", "");
		}
		catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch(item.getItemId())
		{
			case android.R.id.home:
		        NavUtils.navigateUpFromSameTask(this);
		        return true;
			case R.id.action_show_user_list:
				Intent i = new Intent(this, UserListActivity.class);
				startActivity(i);
				return true;
			case R.id.action_open_macro_editor:
				Intent ii = new Intent(this, MacroListActivity.class);
				startActivity(ii);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNetworkMessageReceived(String message)
	{
		adapter.add(message);
		adapter.notifyDataSetChanged();	
	}

	@Override
	public void onPrivateMessageReceived(String sender, String message)
	{
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * The Class ServerPagerAdapter.
	 */
	private class ServerPagerAdapter extends FragmentPagerAdapter
	{
		SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
		
		
		/**
		 * Instantiates a new server pager adapter.
		 *
		 * @param fm the fm
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
			return new ServerViewFragment();
		}

		/* (non-Javadoc)
		 * @see android.support.v4.view.PagerAdapter#getCount()
		 */
		@Override
		public int getCount()
		{
			// TODO STATIC VALUE.
			return 3;
		}

		/* (non-Javadoc)
		 * @see android.support.v4.view.PagerAdapter#getPageTitle(int)
		 */
		@Override
		public CharSequence getPageTitle(int position)
		{
			//TODO: HARD CODED
			switch (position)
			{
				case 0:
					return "Status";
				case 1:
					return "#chat";
				case 2:
					return "#help";
			}
			return null;
		}
		
		
		
	}


}
