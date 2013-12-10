package com.hinodesoftworks.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ServerManager
{
	private ArrayList<Server> servers;
	private static ServerManager _instance = null;
	private HashMap<Server, IRCConnection> connections;
	
	public static ServerManager getInstance()
	{
		if (_instance == null)
		{
			_instance = new ServerManager();
		}
		return _instance;
	}
	
	private ServerManager()
	{
		this.servers = new ArrayList<Server>();
		this.connections = new HashMap<Server, IRCConnection>();
	}
	
	public ArrayList<Server> getServers()
	{
		return servers;
	}
	
	public void setServers(ArrayList<Server> servers)
	{
		this.servers = servers;
	}

	public Server getServerAtPositon(int position)
	{
		return servers.get(position);
	}
	
	public void updateServers(ArrayList<Server> servers)
	{
		for (Server server : servers)
		{
			if (!this.servers.contains(server))
			{
				this.servers.add(server);
			}
		}
		
		//hacky solution to remove unneded duplicates.
		HashSet<Server> castSet = new HashSet<Server>(servers);
		this.servers = new ArrayList<Server>(castSet);	
	}
	
	
	
}
