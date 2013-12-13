/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;

import java.util.ArrayList;

import com.hinodesoftworks.utils.Macro;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class MacroEditorActivity.
 */
public class MacroEditorActivity extends Activity implements TabListener
{
	MacroPagerAdapter macroAdapter;
	ViewPager viewPager;
	Macro currentMacro;
	ArrayList<String> macroList;
	MacroEditorListFragment macListFrag;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_macro_editor);	
		
		macroList = new ArrayList<String>();
		
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
		
		//get list fragment reference.
		macListFrag = (MacroEditorListFragment)getFragmentManager().findFragmentById(R.id.macro_editor_list);
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, macroList);
		macListFrag.setListAdapter(listAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.macro_editor, menu);
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
			case R.id.action_save_macro:
				
				break;
			case R.id.action_rename_macro:
				
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		
		return true;
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
	
	/**
	 * The Class MacroPagerAdapter.
	 */
	private class MacroPagerAdapter extends FragmentPagerAdapter
	{

		/**
		 * Instantiates a new macro pager adapter.
		 *
		 * @param fm the fm
		 */
		public MacroPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}

		/* (non-Javadoc)
		 * @see android.support.v13.app.FragmentPagerAdapter#getItem(int)
		 */
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

		/* (non-Javadoc)
		 * @see android.support.v4.view.PagerAdapter#getCount()
		 */
		@Override
		public int getCount()
		{
			// Is static
			return 2;
		}	
		
	}
}
