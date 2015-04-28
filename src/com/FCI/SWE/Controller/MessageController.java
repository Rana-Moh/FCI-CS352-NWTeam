package com.FCI.SWE.Controller;

import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

@Path("/")
@Produces("text/html")
public class MessageController {

	// getAllNotification(in currentActiveUserEmail:String): WhichTypeToreturn?
	// sendIndividualMessage(in senderEmail:String, in receiverEmail:Vector =
	// String, in msgContent:String): void
	// sendConversationMessage(in senderEmail:String, in receiverEmail:Vector =
	// String, in msgContent:String): void
	//
	//
	/*
	 * sederEmail will be filled by currentActiveUser from User Class
	 */
	@POST
	@Path("/sendIndividualMessage")
	public String sendIndividualMessage(
			@FormParam("receiverEmail") String receiverEmail,
			@FormParam("msgContent") String msgContent) {
		return "";
	}

	/*
	 * sederEmail will be filled by currentActiveUser from User Class vector
	 * from the Jsp pages holds the Emails of thr receivers
	 */
	@POST
	@Path("/sendConversationMessage")
	public String sendConversationMessage(Vector<String> receiversEmails,
			@FormParam("msgContent") String msgContent) {
		return "";
	}

	@GET
	@Path("/sendingIndividualMessagePage")
	public Response sendingIndividualMessage() {
		return Response.ok(new Viewable("/jsp/sendingIndividualMessagePage"))
				.build();
	}

	@GET
	@Path("/sendConverstionMessagePage")
	public Response sendConverstionMessage() {
		return Response.ok(new Viewable("/jsp/sendConverstionMessagePage"))
				.build();
	}

}
