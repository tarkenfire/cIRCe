package com.hinodesoftworks.circe;

import java.util.ArrayList;
import java.util.List;

import com.hinodesoftworks.utils.DropMacro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class DragMacroArrayAdapter extends ArrayAdapter<DropMacro>
{
	ArrayList<DropMacro> objects;
	Context ctx;
	
	public DragMacroArrayAdapter(Context context, int resource, ArrayList<DropMacro> objects)
	{
		super(context, resource, objects);
		
		this.objects = objects;
		this.ctx = context;
	}
	
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
