/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;

import java.util.ArrayList;

import com.hinodesoftworks.utils.DropMacro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class DragMacroArrayAdapter.
 */
public class DragMacroArrayAdapter extends ArrayAdapter<DropMacro>
{
	ArrayList<DropMacro> objects;
	Context ctx;
	
	/**
	 * Instantiates a new drag macro array adapter.
	 *
	 * @param context the context
	 * @param resource the resource
	 * @param objects the objects
	 */
	public DragMacroArrayAdapter(Context context, int resource, ArrayList<DropMacro> objects)
	{
		super(context, resource, objects);
		
		this.objects = objects;
		this.ctx = context;
	}
	
	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		//TODO: Make Dynamic
		if (convertView == null)
		{
			LayoutInflater inflater =  (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.grid_macro_item, null);
		}
		
		
		
		return convertView;
	}

}
