package com.hinodesoftworks.circe;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.hinodesoftworks.utils.DropMacro;

public class MacroDragDropEditorFragment extends Fragment
{
	
	GridView grid;
	
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
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
}
