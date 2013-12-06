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
import android.os.Bundle;

// TODO: Auto-generated Javadoc
/**
 * The Class UserListActivity.
 */
public class UserListActivity extends Activity
{
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
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
