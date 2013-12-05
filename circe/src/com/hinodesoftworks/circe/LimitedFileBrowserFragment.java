package com.hinodesoftworks.circe;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ListFragment;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LimitedFileBrowserFragment extends ListFragment
{
	
	OnFragmentChangeRequestListener listener;
	private ArrayList<File> logFileList;
	public static MimeTypeMap mtm = MimeTypeMap.getSingleton();
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		
		try
		{
			listener = (OnFragmentChangeRequestListener)activity;
		}
		catch (ClassCastException e)
		{
			throw new ClassCastException(activity.toString() + " must implement OnFragmentChangeRequestListener");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_server_list, null);
	}
	
	

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		final String dirToSearch = prefs.getString("pref_log_directory", Environment.getExternalStorageDirectory().getPath() + "/chatlogs");
		
		Log.i("DIR", dirToSearch);
		
		logFileList = new ArrayList<File>();
		
		//threaded for sake of safety. There might be a large number of log files
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				searchDirectoryRecursive(new File(dirToSearch));
				
				getActivity().runOnUiThread(new Runnable()
						{
							@Override
							public void run()
							{
								onFileSearchComplete();
							}
						});
			}
		}).start();
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		Toast.makeText(getActivity(), "Feature not implemented", Toast.LENGTH_SHORT).show();
	}
	
	//utility methods
	private void searchDirectoryRecursive(File dir)
	{
		File[] filesInDir = dir.listFiles();
		
		if (filesInDir != null)
		{
			File holder;
			for (int i = 0; i < filesInDir.length; i++)
			{
				holder = filesInDir[i];
				
				//it really shouldn't be a directory, but since files are stored on 
				//sd card, the user could futz around with it, and there could be sub-dirs
				if (holder.isDirectory()) //if directory, search sub folders
				{
					searchDirectoryRecursive(filesInDir[i]);
				}
				else //is file
				{
					String mimeType = this.getFileMimeType(holder);
					
					if (mimeType != null && mimeType.equals("text/plain"))
					{
						logFileList.add(holder);
					}
				}
				
			}
		}
	}
	
	/**
	 * Gets the extension of a string-form-uri/url.
	 *
	 * @param url the string version of the uri or url
	 * @return the extension of the file string
	 */
	private String getExtension(String url)
	{
		if (url != null && url.length() > 0)
		{
			int query = url.lastIndexOf('?');
			if (query > 0)
			{
				url = url.substring(0, query);
			}
			int filenamePos = url.lastIndexOf('/');
			String filename = 0 <= filenamePos ? url.substring(filenamePos + 1)
					: url;

			if (filename.length() > 0)
			{
				int dotPos = filename.lastIndexOf('.');
				if (0 <= dotPos)
				{
					return filename.substring(dotPos + 1);
				}
			}
		}

		return "";
	}
	
	/**
	 * Gets the file mime type.
	 *
	 * @param file the file
	 * @return the file mime type
	 */
	private String getFileMimeType(File file)
	{
		Uri fileUri = Uri.fromFile(file);
		String ext = this.getExtension(fileUri.toString());
		return mtm.getMimeTypeFromExtension(ext);
	}
	
	
	
	private void onFileSearchComplete()
	{
		//TODO: This is placeholder code that doesn't pass the file locations to
		//the list adapter.
		
		Log.i("SEARCH", "File search complete");
		
		String[] fileNames = new String[logFileList.size()];
		
		File holder;
		for (int i = 0; i < logFileList.size(); i++)
		{
			 holder = logFileList.get(i);
			 fileNames[i] = holder.getName();
			
		}
		
		Log.i("ACTIVITYNULL", getActivity() == null ? "True" : "False");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.single_row_list_item, fileNames);
		this.setListAdapter(adapter);
	}
	

	
}
