package com.FCI.SWE.Models;

/**
 * <h1>Message Subject class</h1>
 * <p>
 * This class will the invokers(message entity which will insert the message
 * information in the DB in two way first if msg is an individual Messagse.
 * Second if msg is a conversation message)
 * </p>
 *
 * @author Esraa Salem
 * @version 2.0
 * @since 2015-04-5
 */

public interface MessageSubject {

	/*
	 * this function will add a userEntity as an observer in a list of the
	 * receivers of the message
	 * 
	 * @Param msgObserver is a MessageObserver Object
	 */
	public void attachMSGObserver(MessageObserver msgObserver);

	/*
	 * this function will invoke the update method for all receivers(Observers)
	 * of the message that means update function will insert the message
	 * information in the database
	 */
	public void notifyAllObservers();

}
