package com.FCI.SWE.Models;

public abstract class MessageObserver {

	private MessageSubject msgSubject;
	/**
	 * will insert in the database  
	 * */
	public abstract void update();
	
}
