package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Vector;


/**
 * This class contains REST services, also contains action function for web
 * application
 * This is the server side of the message part
 * @author Esraa Salem
 * @version 2.0
 * @since 2015-04-5
 *
 */
@Path("/")
@Produces("text/html")
public class MessgeService {

	/*
	 * here will convert the receiver String list to UserEntityList
	 * this service is to perform the function of sending indiviual msg  
	 * 
	 * */
	
	@POST
	@Path("/SendingIndividualMSG")
	public String sendIndividualMessageService(@FormParam("senderEmail") String senderEmail,
			@FormParam("msgContent") String msgContent, Vector<String> receiversEmails) {

		return ""; 
	}
	
	/*
	 * here will convert the receiver String list to UserEntityList
	 * this service is to perform the function of sending conversation msg  
	 * 
	 * */
	
	@POST
	@Path("/SendingConversationMSG")
	public String sendConversationMessageService(@FormParam("senderEmail") String senderEmail,
			@FormParam("msgContent") String msgContent, Vector<String> receiversEmails) {
		return "";
	}


}
