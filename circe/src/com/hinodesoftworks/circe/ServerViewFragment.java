/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;

import com.hinodesoftworks.utils.FragmentDataMapper;
import com.hinodesoftworks.utils.FragmentData;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerViewFragment.
 */
public class ServerViewFragment extends ListFragment implements OnClickListener
{
	private String fragmentName;
	FragmentDataMapper fragmentDataMapper;
	Button sendButton;
	EditText sendEntry;
	
	protected static ServerViewFragment createInstance(String fragName)
	{
		ServerViewFragment fragToReturn = new ServerViewFragment();
		
		Bundle args = new Bundle();
		args.putString("name", fragName);
		fragToReturn.setArguments(args);
		
		return fragToReturn;
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		Log.i("ONCREATE", "Fragment Created");
		return inflater.inflate(R.layout.fragment_server_view, null);
	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		fragmentName = getArguments() != null ? getArguments().getString("name") : "";
		fragmentDataMapper = FragmentDataMapper.getInstance();
	}
	
	
	  
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		
		sendButton = (Button) getActivity().findViewById(R.id.server_view_send_button);
		sendEntry = (EditText) getActivity().findViewById(R.id.server_view_chat_input);
		sendButton.setOnClickListener(this);
	}


	@Override
	public void onResume()
	{
		super.onResume();
		FragmentData data = fragmentDataMapper.getData(fragmentName);
		refresh(data);  
	}
	
	public void refresh(FragmentData data)
	{
		if (data != null)
		{
			if (data.getChatStrings() != null)
			{
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data.getChatStrings());
				this.setListAdapter(adapter);
			}
		}
	}


	@Override
	public void onClick(View v)
	{
		Log.i("ONCLICK", "CLICK");
		ServerViewActivity sva = (ServerViewActivity)getActivity();
		sva.sendServerMessage(sendEntry.getText().toString());
		sendEntry.setText("");
	}

}
