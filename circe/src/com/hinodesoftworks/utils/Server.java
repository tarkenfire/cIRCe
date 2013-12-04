/* 
 * Date: Dec 2, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.utils
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.utils;

public class Server
{
	private String serverName;
	private String serverAddress;
	private boolean isConnected;
	
	public Server(String name, String address)
	{
		this.serverName = name;
		this.serverAddress = address;
	}
	
	public String getServerName()
	{
		return serverName;
	}
	public void setServerName(String serverName)
	{
		this.serverName = serverName;
	}
	
	public String getServerAddress()
	{
		return serverAddress;
	}
	public void setServerAddress(String serverAddress)
	{
		this.serverAddress = serverAddress;
	}
	public boolean isConnected()
	{
		return isConnected;
	}
	public void setConnected(boolean isConnected)
	{
		this.isConnected = isConnected;
	}
}
