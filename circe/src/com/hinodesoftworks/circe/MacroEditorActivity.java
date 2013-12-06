package com.hinodesoftworks.circe;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MacroEditorActivity extends Activity implements TabListener
{
	MacroPagerAdapter macroAdapter;
	ViewPager viewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_macro_editor);	
		
		//set up action bar
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		//TODO: Make dynamic
		actionBar.setTitle("cIRCe - Edit Macro");
		
		//connect pager
		macroAdapter = new MacroPagerAdapter(getFragmentManager());
		
		viewPager = (ViewPager)findViewById(R.id.macro_view_pager);
		viewPager.setAdapter(macroAdapter);
		
		viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{
				actionBar.setSelectedNavigationItem(position);
			}
		});
		
		//static number of tabs, no loop needed
		Tab dragTabHolder = actionBar.newTab();
		dragTabHolder.setText("Drag and Drop");
		dragTabHolder.setTabListener(this);
		actionBar.addTab(dragTabHolder);
		
		Tab rawTabHolder = actionBar.newTab();
		rawTabHolder.setText("Raw Editor");
		rawTabHolder.setTabListener(this);
		actionBar.addTab(rawTabHolder);
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
	
	private class MacroPagerAdapter extends FragmentPagerAdapter
	{

		public MacroPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public Fragment getItem(int position)
		{
			switch (position)
			{
				case 0: //drag and drop
					return new MacroDragDropEditorFragment();
				case 1: //raw
					return new MacroRawEditorFragment();
			}
			
			
			return null;
		}

		@Override
		public int getCount()
		{
			// Is static
			return 2;
		}	
		
	}
}
