/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.utils
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.utils;

// TODO: Auto-generated Javadoc
/**
 * The Class Server.
 */
public class Server
{
	private String serverName;
	private String serverAddress;
	private boolean isConnected;
	
	/**
	 * Instantiates a new server.
	 *
	 * @param name the name
	 * @param address the address
	 */
	public Server(String name, String address)
	{
		this.serverName = name;
		this.serverAddress = address;
	}
	
	/**
	 * Gets the server name.
	 *
	 * @return the server name
	 */
	public String getServerName()
	{
		return serverName;
	}
	
	/**
	 * Sets the server name.
	 *
	 * @param serverName the new server name
	 */
	public void setServerName(String serverName)
	{
		this.serverName = serverName;
	}
	
	/**
	 * Gets the server address.
	 *
	 * @return the server address
	 */
	public String getServerAddress()
	{
		return serverAddress;
	}
	
	/**
	 * Sets the server address.
	 *
	 * @param serverAddress the new server address
	 */
	public void setServerAddress(String serverAddress)
	{
		this.serverAddress = serverAddress;
	}
	
	/**
	 * Checks if is connected.
	 *
	 * @return true, if is connected
	 */
	public boolean isConnected()
	{
		return isConnected;
	}
	
	/**
	 * Sets the connected.
	 *
	 * @param isConnected the new connected
	 */
	public void setConnected(boolean isConnected)
	{
		this.isConnected = isConnected;
	}
}
