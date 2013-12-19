package com.hinodesoftworks.utils;

import java.util.ArrayList;

public class FragmentData
{
	private ArrayList<String> chatStrings;
	
	public FragmentData(ArrayList<String> data)
	{
		this.chatStrings = data;
	}
	
	public ArrayList<String> getChatStrings()
	{
		return chatStrings;
	}

	public void setChatStrings(ArrayList<String> chatStrings)
	{
		this.chatStrings = chatStrings;
	}
	
}