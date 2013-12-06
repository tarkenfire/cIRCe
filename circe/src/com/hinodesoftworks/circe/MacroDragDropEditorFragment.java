/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.hinodesoftworks.utils.DropMacro;

// TODO: Auto-generated Javadoc
/**
 * The Class MacroDragDropEditorFragment.
 */
public class MacroDragDropEditorFragment extends Fragment
{
	
	GridView grid;
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_drop_editor, container, false);
		
		//TODO: Make dynamic
		grid = (GridView) rootView.findViewById(R.id.macro_grid_view);
		
		ArrayList<DropMacro> macros = new ArrayList<DropMacro>();
		
		for (int i = 0; i < 20; ++i)
		{
			macros.add(new DropMacro());
		}
		
		DragMacroArrayAdapter adapter = new DragMacroArrayAdapter(getActivity(), R.layout.grid_macro_item, macros);
		grid.setAdapter(adapter);
		
		return rootView;
	}
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
}
