package com.FCI.SWE.Models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

//import jdk.nashorn.internal.parser.JSONParser;





import org.json.simple.JSONObject;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SelectionOfFriendRequestNotification implements INotificationTypes
{
	public static List<String> stringToList(String e)
	{
		e=e.substring(1,e.length()-1);
		return Arrays.asList(e.split(","));
		
	}
	

	@Override
	public INotificationTypes getNotification(String S) 
	{

		INotificationTypes temp=null;
		
		try
		{
			temp=(INotificationTypes)Class.forName("com.FCI.SWE.Models.SelectionOfFriendRequestNotification").newInstance();
			
		}
		catch(Exception e)
		{
				
		}
		
		return temp;
	
	}

	@Override
	public String viewNotication(String S) 
	{
		
		
		

		JSONObject object = new JSONObject();
		return object.put("response",stringToList(S).get(3)).toString();

	}

}
