package com.FCI.SWE.Models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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

public class SelectionOfAcceptanceNotification implements INotificationTypes {

	
	public static List<String> stringToList(String e)
	{
		e=e.substring(1,e.length()-1);
		return Arrays.asList(e.split(", "));
		
	}
	
	@Override
	public INotificationTypes getNotification(String S) {

		INotificationTypes temp=null;
		
		try
		{
			temp=(INotificationTypes)Class.forName("com.FCI.SWE.Models.SelectionOfAcceptanceNotification").newInstance();
			
		}
		catch(Exception e)
		{
				
		}
		
		return temp;
	
	}

	@Override
	public String viewNotication(String S) {
		// TODO Auto-generated method stub
		JSONObject object = new JSONObject();
		return object.put("response",stringToList(S).get(2)).toString();
	}

	

	 
//public String selectAcceptanceNotification(String currentActiveUserEmail)
//	{
//		
//		
//		Vector<String> sendersOfFreindReqToU = new Vector<String>();
//		DatastoreService datastore = DatastoreServiceFactory
//				.getDatastoreService();
//		Query gaeQuery = new Query("Notification");
//		PreparedQuery pq = datastore.prepare(gaeQuery);
//		JSONArray returnedJson = new JSONArray();
//
//		for (Entity entity : pq.asIterable()) {
//
//			if (entity.getProperty("to").toString()
//					.equals(currentActiveUserEmail)&&entity.getProperty("type").toString()
//					.equals("FreiendAcceptanceNotification")) {
//
//				JSONParser parser = new JSONParser();
//				String paramertsJson = (String) entity
//						.getProperty("parameters");
//				Object obj = parser.parse(paramertsJson);
//				JSONObject object = (JSONObject) parser.parse(paramertsJson);
//				String acceptor = object.get("to").toString();
//				String time = object.get("time").toString();
//				
//				
//				JSONObject objectJason = new JSONObject();
//				object.put("acceptor", acceptor);
//				object.put("time", time);
//				object.put("type", object.get("type").toString());
//				
//				returnedJson.add(object);
//				
//				//sendersOfFreindReqToU.add(objectJason.toJSONString());
//
//			}
//		}
//		return returnedJson.toString();
//
//	}
}

