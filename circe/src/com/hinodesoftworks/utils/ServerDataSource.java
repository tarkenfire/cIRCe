/* 
 * Date: Dec 8, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.utils
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.utils;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class ServerDataSource
{
	private SQLiteDatabase db;
	private ServerDBHelper dbHelper;
	private String[] allFields = {ServerDBHelper.COLUMN_ID, ServerDBHelper.COLUMN_SERVER_NAME, 
			ServerDBHelper.COLUMN_SERVER_ADDRESS, ServerDBHelper.COLUMN_SERVER_PORT, 
			ServerDBHelper.COLUMN_SERVER_USERNAME, ServerDBHelper.COLUMN_SERVER_PASSWORD};
	
	public ServerDataSource(Context context)
	{
		dbHelper = new ServerDBHelper(context);
	}
	
	public void openDatabase() throws SQLiteException
	{
		db = dbHelper.getWritableDatabase();
	}
	
	public void closeDatabase()
	{
		dbHelper.close();
	}
	
	//crud stuff
	public void createServer(String name, String address, int port, String username, String pass)
	{
		ContentValues cv = new ContentValues();
		cv.put(ServerDBHelper.COLUMN_SERVER_NAME, name);
		cv.put(ServerDBHelper.COLUMN_SERVER_ADDRESS, address);
		cv.put(ServerDBHelper.COLUMN_SERVER_PORT, port);
		cv.put(ServerDBHelper.COLUMN_SERVER_USERNAME, username);
		cv.put(ServerDBHelper.COLUMN_SERVER_PASSWORD, pass);
		
		db.insert(ServerDBHelper.TABLE_SERVERS, null, cv);
	} 
	
	public void createServer(Server serverToAdd)
	{
		ContentValues cv = new ContentValues();
		cv.put(ServerDBHelper.COLUMN_SERVER_NAME, serverToAdd.getServerName());
		cv.put(ServerDBHelper.COLUMN_SERVER_ADDRESS, serverToAdd.getServerAddress());
		cv.put(ServerDBHelper.COLUMN_SERVER_PORT, serverToAdd.getServerPort());
		cv.put(ServerDBHelper.COLUMN_SERVER_USERNAME, serverToAdd.getUserName());
		cv.put(ServerDBHelper.COLUMN_SERVER_PASSWORD, serverToAdd.getPassword());
		
		db.insert(ServerDBHelper.TABLE_SERVERS, null, cv);
	}
	
	
	public void updateServer(Server serverToUpdate)
	{
		
	}
	
	public void deleteServer(Server serverToDelete)
	{
		long curId = serverToDelete.getId();
		db.delete(ServerDBHelper.TABLE_SERVERS, ServerDBHelper.COLUMN_ID + " = " + curId, null);
	}
	
	
	//util methods
	public ArrayList<Server> getAllServers()
	{
		ArrayList<Server> servs = new ArrayList<Server>();
		
		Cursor cursor = db.query(ServerDBHelper.TABLE_SERVERS, allFields, null, null, null, null, null);
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast())
		{
			Server serv = new Server(cursor.getString(1), cursor.getString(2));
			
			serv.setId(cursor.getLong(0));
			serv.setServerPort(cursor.getInt(3));
			serv.setUserName(cursor.getString(4));
			serv.setPassword(cursor.getString(5));
			
			servs.add(serv);
			cursor.moveToNext();
		}
		
		cursor.close();
		return servs;
	}
	
}
