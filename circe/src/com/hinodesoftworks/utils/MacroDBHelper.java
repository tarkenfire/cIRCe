/* 
 * Date: Dec 13, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.utils
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MacroDBHelper extends SQLiteOpenHelper
{
	public static final String TABLE_MACROS = "macros";
	
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_MACRO_NAME = "macro_name";
	public static final String COLUMN_MACRO_STRING = "macro_body";
	
	public static final String CREATE_DATABASE = "create table " + TABLE_MACROS + "(" +
	COLUMN_ID +" integer primary key autoincrement, " + COLUMN_MACRO_NAME + " text not null, " +
			COLUMN_MACRO_STRING + " text not null);";
	
	private static final String DATABASE_NAME = "macros.db";
	private static final int DATABASE_VERSION = 1;
	
	public MacroDBHelper(Context context)
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
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_MACROS);
	    onCreate(db);
	}
}
