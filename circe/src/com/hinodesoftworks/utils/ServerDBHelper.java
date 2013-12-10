/* 
 * Date: Dec 8, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.utils
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ServerDBHelper extends SQLiteOpenHelper
{
	//DB Variable Names
	public static final String TABLE_SERVERS = "servers";
			  
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_SERVER_NAME = "server_name";
	public static final String COLUMN_SERVER_ADDRESS = "server_address";
	public static final String COLUMN_SERVER_PORT= "server_port";
	public static final String COLUMN_SERVER_USERNAME = "server_username";
	public static final String COLUMN_SERVER_PASSWORD = "server_password";
			
	private static final String DATABASE_NAME = "servers.db";
	private static final int DATABASE_VERSION = 1;
	
	//create statement. 
	private static final String CREATE_DATABASE = "create table " + TABLE_SERVERS + "(" + 
	COLUMN_ID + " integer primary key autoincrement, " + COLUMN_SERVER_NAME + " text not null, " +
			COLUMN_SERVER_ADDRESS + " text not null, " + COLUMN_SERVER_PORT + " integer, " + 
			COLUMN_SERVER_USERNAME + " text not null, " + COLUMN_SERVER_PASSWORD + " text);";
	
	public ServerDBHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
	    db.execSQL(CREATE_DATABASE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		//TODO: Upgrading really shouldn't wipe out all of the data.
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVERS);
	    onCreate(db);
	}

}
