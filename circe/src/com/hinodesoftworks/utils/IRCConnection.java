package com.hinodesoftworks.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Locale;

public class IRCConnection
{
	//TODO (Maybe): This class would not be safe a stand-alone library b/c
	//of the listener needing to be set before any calls are made and there
	//being no checking of the listener being set.
	
	public interface OnIRCMessageReceivedListener
	{
		public void onChannelMessageReceived(String channel, String message);
		public void onNetworkMessageReceived(String message);
		public void onPrivateMessageReceived(String sender, String message);
	}
	
	private OnIRCMessageReceivedListener listener = null;
	
	String username = "User";
	String password = "";
	
	//server connection utilities
	private Socket serverSocket; 
	private BufferedReader serverReader; 
	private BufferedWriter serverWriter;
	
	
	public void setOnIRCMessageReceivedListener(OnIRCMessageReceivedListener listener)
	{
		this.listener = listener;
	}
	
	//TODO: Re-encapsulate creation of object.
	public IRCConnection ()
	{}
	
	public void connectToServer(String serverAddress, int port, String username, String password) throws UnknownHostException, IOException
	{
		serverSocket = new Socket(serverAddress, port);
		serverReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		serverWriter = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));	
		
        serverWriter.write("NICK " + username + "\r\n");
        serverWriter.write("USER " + username + " 8 * "+ username +" \r\n");
        serverWriter.flush();
        
        ConnThread connThread = new ConnThread();
        connThread.run();
	}
	
	public boolean disconnectFromServer()
	{
		return false;
	}
	
	private void parseRawMessage(String raw)
	{
		listener.onNetworkMessageReceived(raw);
	}
	
	
	private class ConnThread extends Thread
	{
		public void run()
		{
			String line;
			try
			{
				while ((line = serverReader.readLine()) != null)
				{
					//always respond to pings.
					if (line.toLowerCase(Locale.US).startsWith("ping"))
					{
		                serverWriter.write("PONG " + line.substring(5) + "\r\n");
		                serverWriter.flush();
					}			
					
					parseRawMessage(line);
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}	
		}		
	}
	
}
