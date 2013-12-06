/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;

import java.util.ArrayList;

import com.hinodesoftworks.utils.Server;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerListAdapter.
 */
public class ServerListAdapter extends ArrayAdapter<Server>
{
	ArrayList<Server> servers;
	Context ctx;
	
	/**
	 * Instantiates a new server list adapter.
	 *
	 * @param context the context
	 * @param resource the resource
	 * @param objects the objects
	 */
	public ServerListAdapter(Context context, int resource, ArrayList<Server> objects)
	{
		super(context, resource, objects);
		
		servers = objects;
		ctx = context;
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			LayoutInflater inflater =  (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.row_server_list, null);
		}
		
		Server serverForView = servers.get(position);
		
		TextView serverNameView = (TextView) convertView.findViewById(R.id.server_row_server_name);
		TextView serverAddressView = (TextView) convertView.findViewById(R.id.server_row_server_address);
		ImageView serverStatusView = (ImageView) convertView.findViewById(R.id.server_row_status_icon);
		
		serverNameView.setText(serverForView.getServerName());
		serverAddressView.setText(serverForView.getServerAddress());
		
		if (serverForView.isConnected())
			serverStatusView.setImageResource(R.drawable.ic_server_up);
		else
			serverStatusView.setImageResource(R.drawable.ic_server_down);
		
		
		
		return convertView;
	}
	
	

}
