package com.FCI.SWE.Models;

import java.util.HashMap;
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

public class SelectionOfFriendRequestNotification {

	/*
	 * will select from database
	 */
	
	private String currentActiveUserEmail;
	
	public String getCurrentActiveUserEmail() {
		return currentActiveUserEmail;
	}
	public void setCurrentActiveUserEmail(String currentActiveUserEmail) {
		this.currentActiveUserEmail = currentActiveUserEmail;
	}
	public SelectionOfFriendRequestNotification()
	{
		
	}
	public String selectFriendRequestNotification() throws ParseException {
		Vector<String> sendersOfFreindReqToU = new Vector<String>();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Notification");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		JSONArray returnedJson = new JSONArray();

		for (Entity entity : pq.asIterable()) {

			if (entity.getProperty("to").toString()
					.equals(currentActiveUserEmail) &&entity.getProperty("type").toString()
					.equals("FriendRequestNotification") ) {

				JSONParser parser = new JSONParser();
				String paramertsJson = (String) entity
						.getProperty("parameters");
				Object obj = parser.parse(paramertsJson);
				JSONObject object = (JSONObject) parser.parse(paramertsJson);
				String reqSender = object.get("from").toString();
				String time = object.get("time").toString();
				
				
				JSONObject objectJason = new JSONObject();
				object.put("reqSender", reqSender);
				object.put("time", time);
				object.put("type", object.get("type").toString());
				
				returnedJson.add(object);
				
				//sendersOfFreindReqToU.add(objectJason.toJSONString());

			}
		}
		return returnedJson.toString();

	}

}
