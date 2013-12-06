/* 
 * Date: Dec 5, 2013
 * Project: cIRCe
 * Package: com.hinodesoftworks.circe
 * @author Michael Mancuso
 *
 */
package com.hinodesoftworks.circe;


// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving onFragmentChangeRequest events.
 * The class that is interested in processing a onFragmentChangeRequest
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addOnFragmentChangeRequestListener<code> method. When
 * the onFragmentChangeRequest event occurs, that object's appropriate
 * method is invoked.
 *
 * @see OnFragmentChangeRequestEvent
 */
public interface OnFragmentChangeRequestListener
{
	
	/**
	 * The Enum FragmentType.
	 */
	public enum FragmentType{FRAGMENT_FILE_BROWSER, FRAGMENT_FILE_VIEWER, FRAGMENT_SETTINGS}
	
	/**
	 * On fragment change request.
	 *
	 * @param type the type
	 */
	public void onFragmentChangeRequest(FragmentType type);
}
