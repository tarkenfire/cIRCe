package com.hinodesoftworks.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentDataMapper
{
	private Map<String, FragmentData> fragmentData = new HashMap<String, FragmentData>();
	private static FragmentDataMapper _instance = null;
	
	public static FragmentDataMapper getInstance()
	{
		if (_instance == null)
		{
			_instance = new FragmentDataMapper();
		}
		
		return _instance;
	}
	
	public void updateData(String fragId, FragmentData data)
	{
		fragmentData.put(fragId, data);
	}
	
	public FragmentData getData(String fragId)
	{
		return fragmentData.get(fragId);
	}
	
	public FragmentData createAndGetData(String fragId, ArrayList<String> data)
	{
		FragmentData fragData = new FragmentData(data);
		fragmentData.put(fragId, fragData);
		return fragmentData.get(fragId); 
	}
	
	

}
