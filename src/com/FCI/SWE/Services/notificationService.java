package com.FCI.SWE.Services;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;













//import org.datanucleus.sco.backed.Vector;
import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mortbay.util.ajax.JSON;

import com.FCI.SWE.Models.INotificationTypes;
import com.FCI.SWE.Models.NotificationEntity;
import com.FCI.SWE.Models.SelectionOfAcceptanceNotification;
import com.FCI.SWE.Models.SelectionOfFriendRequestNotification;
import com.FCI.SWE.Models.SelectiondOfConversationMessageNotification;
import com.FCI.SWE.Models.UserEntity;
import com.google.appengine.labs.repackaged.org.json.JSONArray;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces("text/html")

public class notificationService 
{
	@POST
	@Path("/getAllNotifications")
	public String getAllNotifications(@FormParam("currentUserEmail") String currentUserEmail) throws ParseException
	{
		NotificationEntity.getreciveres(currentUserEmail);
		return null;
		
		
	}
	
	
	@GET
	@Path("/parseNotification")
	public String notification(@QueryParam("type") String type, @QueryParam("paramters")String paramters)
	{
		INotificationTypes temp =null;
		
		//temp=temp.getNotification(type);
		if(type=="msg")
			temp=new SelectiondOfConversationMessageNotification();
		if(type=="FriendRequestNotification")
			temp=new SelectionOfFriendRequestNotification();
		if(type=="FriendAcceptanceNotification")
			temp= new SelectionOfAcceptanceNotification();
		
		String newN = temp.viewNotication(paramters);
		
		
		
		return newN;
	}

}
