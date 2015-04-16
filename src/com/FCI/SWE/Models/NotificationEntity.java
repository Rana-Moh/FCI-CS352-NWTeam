package com.FCI.SWE.Models;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Controller.UserController;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;







import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 */

public class NotificationEntity 
{
	public static ArrayList<String>type = new ArrayList<String>();
	public static ArrayList<String> parameters= new ArrayList<String>();
	
	public static String getreciveres( String email)
	{
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		type.clear();
		parameters.clear();
		
		Query gaeQuery = new Query("Notification");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) 
		{
			if  (entity.getProperty("user").toString().equals(email))
			{
				type.add(entity.getProperty("type").toString());
				parameters.add(entity.getProperty("parameters").toString());
				
			}
		}

		return null;
	}
	

	
	public static INotificationTypes getCommand(String Type) {

		INotificationTypes temp=null;
		String S= selectCommand(Type);
		try
		{
			temp=(INotificationTypes)Class.forName("com.FCI.SWE.Models."+S).newInstance();
			
		}
		catch(Exception e)
		{
				
		}
		
		return temp;
	
	}



	private static String selectCommand(String type2) 
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		
		Query gaeQuery = new Query("Map");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) 
		{
			if  (entity.getProperty("Type").toString().equals(type2))
			{
				return entity.getProperty("class").toString();
				
			}
		}

		
		return null;
	}


/*
	public static void insertdumb() 
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		
		Query gaeQuery = new Query("Map");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		
		Entity con = new Entity("Map", list.size() + 1);
		con.setProperty("Type","msg");
		con.setProperty("class", "SelectiondOfConversationMessageNotification");
		datastore.put(con);

		Query gaeQuery1 = new Query("Map");
		PreparedQuery pq1 = datastore.prepare(gaeQuery1);
		List<Entity> list1 = pq1.asList(FetchOptions.Builder.withDefaults());
		
		Entity con1= new Entity("Map", list1.size()+1);
		con1.setProperty("Type","FriendAcceptanceNotification");
		con1.setProperty("class", "SelectionOfAcceptanceNotification");
		datastore.put(con1);

		Query gaeQuery11 = new Query("Map");
		PreparedQuery pq11 = datastore.prepare(gaeQuery11);
		List<Entity> list11 = pq11.asList(FetchOptions.Builder.withDefaults());
		
		Entity con11= new Entity("Map", list11.size()+1);
		con1.setProperty("Type","FriendRequestNotification");
		con1.setProperty("class", "SelectionOfFriendRequestNotification");
		datastore.put(con11);
		
		System.out.println("nnn");

		
		
	}
*/

	

}
