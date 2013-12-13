/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;

// TODO: Auto-generated Javadoc
/**
 * The Class MacroListFragment.
 */
public class MacroListFragment extends ListFragment
{
	protected Object actionMode;
	public int selectedItem = -1;
	
	
	/**
	 * The listener interface for receiving onMacroSelected events.
	 * The class that is interested in processing a onMacroSelected
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addOnMacroSelectedListener<code> method. When
	 * the onMacroSelected event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see OnMacroSelectedEvent
	 */
	public interface OnMacroSelectedListener
	{
		//TODO not dynamic
		/**
		 * On macro selected.
		 */
		public void onMacroSelected();
	}
	
	OnMacroSelectedListener listener;
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		
		try
		{
			listener = (OnMacroSelectedListener)activity;
		}
		catch (ClassCastException e)
		{
			throw new ClassCastException(activity.toString() + " must implement OnMacroSelectedListener");
		}
	}
	
	/* (non-Javadoc)
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_server_list, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		
		ListView list = this.getListView();
		list.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				if (actionMode != null)
				{
					return false;
				}
				selectedItem = position;
				
				actionMode = MacroListFragment.this.getActivity().startActionMode(actionModeCallback);
				view.setSelected(true);
				
				return true;
			}});
	}
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	/* (non-Javadoc)
	 * @see android.app.ListFragment#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		// TODO not dynamic
		listener.onMacroSelected();
	}
	
	
	private ActionMode.Callback actionModeCallback = new ActionMode.Callback() 
	{

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item)
		{
			switch (item.getItemId())
			{
			case R.id.action_delete_macro:
				
				mode.finish();
				return true;
			default:
				return false;
			}
		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu)
		{
			MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate(R.menu.server_list_context, menu);
			return true;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode)
		{
			actionMode = null;
			selectedItem = -1;
			
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu)
		{
			// TODO Auto-generated method stub
			return false;
		}
		
	};
	
	
	
}
