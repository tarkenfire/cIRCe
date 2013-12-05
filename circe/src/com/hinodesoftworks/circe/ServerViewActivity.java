package com.hinodesoftworks.circe;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class ServerViewActivity extends Activity implements TabListener
{

	ViewPager viewPager;
	ServerPagerAdapter servPagerAdapter;
	
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
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.server_view, menu);
		return true;
	}
	
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

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction)
	{
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction)
	{
		// do nothing	
	}
	
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction)
	{
		//do nothing
	}
	
	private class ServerPagerAdapter extends FragmentPagerAdapter
	{

		public ServerPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public Fragment getItem(int position)
		{
			return new ServerViewFragment();
		}

		@Override
		public int getCount()
		{
			// TODO STATIC VALUE.
			return 3;
		}

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
