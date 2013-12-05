package com.hinodesoftworks.circe;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class UserListFragment extends ListFragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_server_list, null);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		
		//TODO: static data
		String[] userNames = {"User1", "User2", "User3"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.single_row_list_item, userNames);
		this.setListAdapter(adapter);
	}
}
