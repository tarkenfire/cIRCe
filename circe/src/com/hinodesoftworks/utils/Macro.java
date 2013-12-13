/* 
 * Date: Dec 13, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.utils
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.utils;

public class Macro
{
	private long id;
	private String macroName;
	private String macroStringValue;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getMacroName()
	{
		return macroName;
	}
	public void setMacroName(String macroName)
	{
		this.macroName = macroName;
	}
	
	public String getMacroStringValue()
	{
		return macroStringValue;
	}
	public void setMacroStringValue(String macroStringValue)
	{
		this.macroStringValue = macroStringValue;
	}

}
