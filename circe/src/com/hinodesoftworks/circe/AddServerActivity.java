/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

// TODO: Auto-generated Javadoc
/**
 * The Class AddServerActivity.
 */
public class AddServerActivity extends Activity
{
	EditText serverNameView;
	EditText serverAddressView;
	EditText portView;
	EditText usernameView;
	EditText passwordView;
	Switch loggingSwitch;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_server);
		
		//get ui handles
		serverNameView = (EditText)findViewById(R.id.add_server_name_entry);
		serverAddressView = (EditText)findViewById(R.id.add_server_address_entry);
		portView = (EditText)findViewById(R.id.add_server_port_entry);
		usernameView = (EditText)findViewById(R.id.add_server_default_username_entry);
		passwordView = (EditText)findViewById(R.id.add_server_password_entry);
		loggingSwitch = (Switch)findViewById(R.id.add_server_logging_entry);
		
		
		//set title for screen
		Resources res = getResources();
		this.setTitle(res.getString(R.string.app_name) + " - " + res.getString(R.string.title_add_server));
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_server, menu);
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
			case android.R.id.home:
		        NavUtils.navigateUpFromSameTask(this);
		        return true;
			case R.id.action_add_server:
				Intent returnIntent = createDataIntent();
				
				//if error, returnIntent == null, return.
				if (returnIntent == null) return false;
				
				this.setResult(RESULT_OK, returnIntent);
				
				this.finish();
				Toast.makeText(this, R.string.toast_server_created, Toast.LENGTH_LONG).show();
				return true;
		}
		return false;
	}
	
	//util methods
	public Intent createDataIntent()
	{
		Intent returnIntent = new Intent();
		
		Log.i("SERVER NAME", serverNameView.getText().toString());
		
		//check values
		if (serverNameView.getText().equals("") || serverNameView.getText().equals(" "))
		{
			//field is required. return null
			Toast.makeText(this, R.string.error_no_server_name, Toast.LENGTH_SHORT).show();
			return null;
		}
		else
		{
			returnIntent.putExtra("serv_name", serverNameView.getText().toString());
		}
		
		String addrRegex = "irc\\.+(\\w{2,12}\\.)+([a-z]{2,3})";
		
		if (serverAddressView.getText().toString().matches(addrRegex))
		{
			returnIntent.putExtra("serv_address", serverAddressView.getText().toString());
		}
		else
		{
			Toast.makeText(this, R.string.error_no_server_address, Toast.LENGTH_SHORT).show();
			return null;
		}
		
		if (usernameView.getText().equals("") || usernameView.getText().equals(" "))
		{
			Toast.makeText(this, R.string.error_no_username, Toast.LENGTH_SHORT).show();
			return null;
		}
		else
		{
			returnIntent.putExtra("username", usernameView.getText().toString());
		}
		
		String portString = portView.getText().toString();
		int port;
		
		try
		{
			port = Integer.parseInt(portString);
		}
		catch(NumberFormatException nfe)
		{
			Toast.makeText(this, R.string.error_bad_port, Toast.LENGTH_SHORT).show();
			port = 6666;
		}
		
		returnIntent.putExtra("port", port);
		
		//password is optional, just put whatever is there.
		returnIntent.putExtra("password", passwordView.getText().toString());
		returnIntent.putExtra("logging_flag", loggingSwitch.isActivated());
		
		return returnIntent;
		
	}
	
}
