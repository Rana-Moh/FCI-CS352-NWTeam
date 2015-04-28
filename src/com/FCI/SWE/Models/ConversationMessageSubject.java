package com.FCI.SWE.Models;
import java.util.Vector;

public  class ConversationMessageSubject implements MessageSubject{

	
	private Vector<UserEntity> receiversList = new Vector<UserEntity>();
	private UserEntity senderEntity;
	private String msgContent;
	
	public ConversationMessageSubject()
	{
		super();
		
	}
	@Override
	public void attachMSGObserver(MessageObserver msgObserver) {
		// TODO Auto-generated method stub
		
	}
	public void notifyAllObservers()
	{
		
		
	}

}
