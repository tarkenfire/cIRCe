package com.hinodesoftworks.circe;

import android.app.Fragment;

public interface OnFragmentChangeRequestListener
{
	public enum FragmentType{FRAGMENT_FILE_BROWSER, FRAGMENT_FILE_VIEWER, FRAGMENT_SETTINGS}
	public void onFragmentChangeRequest(FragmentType type);
}
