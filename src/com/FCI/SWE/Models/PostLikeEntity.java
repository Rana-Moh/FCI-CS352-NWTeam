package com.FCI.SWE.Models;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;


public class PostLikeEntity 
{
	public static boolean insert(String id)
	{
		if(!checkExist(id))
		{
			//insert into table

			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			Transaction txn = datastore.beginTransaction();
			Query gaeQuery = new Query("postLike");
			PreparedQuery pq = datastore.prepare(gaeQuery);

			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

			//System.out.println("Like exists: " + likeExists());

			try {
				Entity like = new Entity("postLike", list.size() + 1);
				java.util.Date date = new java.util.Date();	
				Timestamp current = new Timestamp(date.getTime());
				String time= current.toString();


				like.setProperty("id", id);
				like.setProperty("user" , User.getCurrentActiveUser().getEmail());
				like.setProperty("time", time);

				System.out.println("here");
				datastore.put(like);
				txn.commit();
				System.out.println("here");
				//increment likes in post entity
				incrementLikes(id);
				//notify writer search posts entity get writer send notification
				notifyOwner(id);
				return true;

			} finally{
				if (txn.isActive()) {
					txn.rollback();
				}
			}



		}

		return false;
	}

	private static boolean checkExist(String id)
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("postLike");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) 
		{

			if (entity.getProperty("id").toString().equals(id)
					&& entity.getProperty("user").toString().equals(User.getCurrentActiveUser().getEmail())) 
			{
				return true;
			}
		}

		return false;
	}

	private static boolean incrementLikes(String id)
	{

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) 
		{
			if (Long.toString(entity.getKey().getId()).equals(id))	 
			{
				String like=entity.getProperty("likes").toString();
				int increment=Integer.parseInt(like);
				increment++;
				entity.setProperty("likes", String.valueOf(increment));
				datastore.put(entity);
				return true;
			}
		}

		return false;
	}

	private static boolean notifyOwner(String id)
	{

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) 
		{
			if (Long.toString(entity.getKey().getId()).equals(id))	 
			{
				String writer=entity.getProperty("writerEmail").toString();


				Query queryForNotificationTable = new Query("Notification");
				PreparedQuery preparedQuery = datastore
						.prepare(queryForNotificationTable);

				List<Entity> listOfNotificationEntries = preparedQuery
						.asList(FetchOptions.Builder.withDefaults());

				java.util.Date date = new java.util.Date();	
				Timestamp current = new Timestamp(date.getTime());
				String time= current.toString();

				Entity addNotification = new Entity("Notification",
						listOfNotificationEntries.size() + 1);
				addNotification.setProperty("type", "postLike");
				
				JSONObject notificationParameters = new JSONObject();
				notificationParameters.put("who liked ur post",User.getCurrentActiveUser().getEmail());
				notificationParameters.put("time", time);
				notificationParameters.put("id", id);
				StringWriter out = new StringWriter();
				try {
					notificationParameters.writeJSONString(out);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String jsonText = out.toString();
				
				addNotification.setProperty("parameters", jsonText);
				addNotification.setProperty("user", writer);
				addNotification.setProperty("time", time);
				datastore.put(addNotification);



				//entity.setProperty("likes", String.valueOf(increment));
				//datastore.put(entity);
				return true;
			}
		}

		return false;
	}


}