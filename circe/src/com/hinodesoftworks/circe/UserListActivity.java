package com.hinodesoftworks.circe;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class UserListActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);
		
		//TODO Static data
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("cIRCe - #chat Users");
	}
}
