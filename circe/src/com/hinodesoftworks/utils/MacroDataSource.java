/* 
 * Date: Dec 13, 2013
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

public class MacroDataSource
{
	private SQLiteDatabase db;
	private MacroDBHelper dbHelper;
	private String[] allFields = {MacroDBHelper.COLUMN_ID, MacroDBHelper.COLUMN_MACRO_NAME,
			MacroDBHelper.COLUMN_MACRO_STRING};

	
	public MacroDataSource(Context context)
	{
		dbHelper = new MacroDBHelper(context);
	}
	
	//db methods 
	public void openDataBase() throws SQLiteException
	{
		db = dbHelper.getWritableDatabase();
	}
	
	public void closeDatabase()
	{
		dbHelper.close();
	}
	
	public void createMacro(Macro macroToAdd)
	{
		ContentValues cv = new ContentValues();
		cv.put(MacroDBHelper.COLUMN_MACRO_NAME, macroToAdd.getMacroName());
		cv.put(MacroDBHelper.COLUMN_MACRO_STRING, macroToAdd.getMacroStringValue());
		
		db.insert(MacroDBHelper.TABLE_MACROS, null, cv);
	}
	
	public void updateMacro(Macro macroToUpdate)
	{
		ContentValues cv = new ContentValues();
		cv.put(MacroDBHelper.COLUMN_MACRO_NAME, macroToUpdate.getMacroName());
		cv.put(MacroDBHelper.COLUMN_MACRO_STRING, macroToUpdate.getMacroStringValue());
		
		db.update(MacroDBHelper.TABLE_MACROS, cv, MacroDBHelper.COLUMN_ID + "=" + macroToUpdate.getId(), null);
	}
	
	public void deleteMacro(Macro macroToDelete)
	{
		long curId = macroToDelete.getId();
		db.delete(MacroDBHelper.TABLE_MACROS, ServerDBHelper.COLUMN_ID + " = " + curId, null);
	}
	
	public ArrayList<Macro> getAllMacros()
	{
		ArrayList<Macro> macros = new ArrayList<Macro>();
		
		Cursor cursor = db.query(MacroDBHelper.TABLE_MACROS, allFields, null, null, null, null, null);
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast())
		{
			Macro macro = new Macro();
			macro.setId(cursor.getLong(0));
			macro.setMacroName(cursor.getString(1));
			macro.setMacroStringValue(cursor.getString(2));
		}
		
		cursor.close();
		return macros;
	}
}
