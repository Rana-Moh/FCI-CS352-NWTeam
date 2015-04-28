package com.FCI.SWE.Models;
import java.util.Vector;
public class IndividualMessageSubject implements MessageSubject {
	
	private UserEntity receiverEntity;
	private UserEntity senderEntity;
	private String msgContent;
	public IndividualMessageSubject(UserEntity receiverEntity,
			UserEntity senderEntity, String msgContent) {
		super();
		this.receiverEntity = receiverEntity;
		this.senderEntity = senderEntity;
		this.msgContent = msgContent;
	}
	public IndividualMessageSubject() {
		super();
	}
	// *****************************************************
	
	
	public void attachMSGObserver(MessageObserver msgObserver) {
		// TODO Auto-generated method stub
		
	}
	public void notifyAllObservers()
	{
		
	}

	
	
	
	
	
	// *****************************************************	
	public UserEntity getReceiverEntity() {
		return receiverEntity;
	}
	public void setReceiverEntity(UserEntity receiverEntity) {
		this.receiverEntity = receiverEntity;
	}
	public UserEntity getSenderEntity() {
		return senderEntity;
	}
	public void setSenderEntity(UserEntity senderEntity) {
		this.senderEntity = senderEntity;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
	
	
}
