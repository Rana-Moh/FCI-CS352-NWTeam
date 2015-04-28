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
public class UserEntity 
{
	private String name;
	private String email;
	private String password;
	private long id;

	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user email
	 * @param password
	 *            user provided password
	 */
	public UserEntity(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	private void setId(long id){
		this.id = id;
	}
	
	public long getId(){
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}

	
	/**
	 * 
	 * This static method will form UserEntity class using user name and
	 * password This method will serach for user in datastore
	 * 
	 * @param name
	 *            user name
	 * @param pass
	 *            user password
	 * @return Constructed user entity
	 */

	public static UserEntity getUser(String email, String pass) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("email").toString().equals(email)
					&& entity.getProperty("password").toString().equals(pass)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				returnedUser.setId(entity.getKey().getId());
				return returnedUser;
			}
		}

		return null;
	}
	
	
	
	public static ArrayList<String> getFriends()
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		User u = User.getCurrentActiveUser();

		ArrayList<String >friends= new ArrayList<String >();
		
		Query gaeQuery = new Query("requests");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			
			if ((entity.getProperty("from").toString().equals(u.getEmail())
					|| entity.getProperty("to").toString().equals(u.getEmail()) )
					&& entity.getProperty("Acceptance").toString().equals("1") ) 
			{

				if(u.getEmail().equals(entity.getProperty("from").toString()))
				{
					friends.add(entity.getProperty("to").toString());
					
				}
				if(u.getEmail().equals(entity.getProperty("to").toString()))
				{
					friends.add( entity.getProperty("from").toString());
					
				}
			}
		}
				
		return friends;
	}

	/**
	 * This method will be used to save user object in datastore
	 * 
	 * @return boolean if user is saved correctly or not
	 */
	public Boolean saveUser() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		
		System.out.println("Size = " + list.size());
		
		try {
		Entity employee = new Entity("users", list.size() + 1);

		employee.setProperty("name", this.name);
		employee.setProperty("email", this.email);
		employee.setProperty("password", this.password);
		
		datastore.put(employee);
		txn.commit();
		}finally{
			if (txn.isActive()) {
		        txn.rollback();
		    }
		}
		return true;

	}
	
	public static boolean getFriendByEmail(String email) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService(); // create db

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		// get all users in the table then compare with the given parameters
		for (Entity entity : pq.asIterable()) { // like iterator to traverse
												// each record

			if (entity.getProperty("email").toString().equals(email)) {

				return true;

			}
		}

		return false;

	}
	
	public static boolean checkRequestTable(String friendEmail, String senderEmail) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("requests");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			
			if (entity.getProperty("from").toString().equals(senderEmail)
					&& entity.getProperty("to").toString().equals(friendEmail) 
					&& entity.getProperty("Acceptance").toString().equals("0") ) 
			{

				return false;
			}
		}
		return true;
	}
	
	
	public static boolean checkAcceptTable(String myEmail, String femail) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("requests");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			
			if (entity.getProperty("from").toString().equals(femail)
					&& entity.getProperty("to").toString().equals(myEmail) 
					&& entity.getProperty("Acceptance").toString().equals("0") ) 
			{

				java.util.Date date = new java.util.Date();
				Timestamp currentTimestamp = new Timestamp(date.getTime());
				String timpStampStr = currentTimestamp.toString();
				entity.setProperty("time", timpStampStr);
				
				entity.setProperty("Acceptance","1");
				datastore.put(entity);
							
			    Query queryForNotificationTable = new Query("Notification");
				PreparedQuery preparedQuery = datastore
						.prepare(queryForNotificationTable);

				List<Entity> listOfNotificationEntries = preparedQuery
						.asList(FetchOptions.Builder.withDefaults());
				
				Entity addNotification = new Entity("Notification",
						listOfNotificationEntries.size() + 1);
				addNotification.setProperty("type", "FriendAcceptanceNotification");
				JSONObject notificationParameters = new JSONObject();
				notificationParameters.put("from", femail);
				notificationParameters.put("to", myEmail);
				notificationParameters.put("Acceptance", "1");
				notificationParameters.put("time", timpStampStr);
				StringWriter out = new StringWriter();
				try {
					notificationParameters.writeJSONString(out);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String jsonText = out.toString();
				addNotification.setProperty("parameters", jsonText);
				addNotification.setProperty("user", femail);
				addNotification.setProperty("time", timpStampStr);
				datastore.put(addNotification);
					
				return true;
			}
		}
		return false;
	}

	public static Boolean addFriendRequestIDsFromAndTo(String friendEmail,
			String senderEmail) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		if(checkRequestTable(friendEmail, senderEmail))
		{
			Query gaeQuery = new Query("requests");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			
			Entity addFriendRequest = new Entity("requests", list.size() + 1);
			addFriendRequest.setProperty("from",senderEmail);
			addFriendRequest.setProperty("to", friendEmail);
			addFriendRequest.setProperty("Acceptance", "0");
			
			java.util.Date date = new java.util.Date();
			Timestamp currentTimestamp = new Timestamp(date.getTime());
			String timpStampStr = currentTimestamp.toString();
			addFriendRequest.setProperty("time", timpStampStr);
			
			
			datastore.put(addFriendRequest);

			

			Query queryForNotificationTable = new Query("Notification");
			PreparedQuery preparedQuery = datastore
					.prepare(queryForNotificationTable);

			List<Entity> listOfNotificationEntries = preparedQuery
					.asList(FetchOptions.Builder.withDefaults());
			Entity addNotification = new Entity("Notification",
					listOfNotificationEntries.size() + 1);
			addNotification.setProperty("type", "FriendRequestNotification");
			JSONObject notificationParameters = new JSONObject();
			notificationParameters.put("from", senderEmail);
			notificationParameters.put("to", friendEmail);
			notificationParameters.put("Acceptance", "0");
			notificationParameters.put("time", timpStampStr);
			StringWriter out = new StringWriter();
			try {
				notificationParameters.writeJSONString(out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String jsonText = out.toString();
			addNotification.setProperty("parameters", jsonText);
			addNotification.setProperty("user", friendEmail);
			addNotification.setProperty("time", timpStampStr);
			datastore.put(addNotification);
			return true;
		}
		
		return false;

	}

	public static Vector<UserEntity> searchUser(String uname) {
			// TODO Auto-generated method stub
			
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query gae = new Query("users");
			PreparedQuery preparedQuery = datastore.prepare(gae);
			
			Vector <UserEntity> returnedUsers = new Vector <UserEntity> ();
			
			for(Entity entity : preparedQuery.asIterable())
			{
				entity.getKey().getId();
				String currentName = entity.getProperty("name").toString();
				System.out.println("CNAME = " + currentName + ", uname = "+uname);
				if(currentName.contains(uname))
				{
					UserEntity user = new UserEntity(entity.getProperty("name").toString(), 
							entity.getProperty("email").toString(), 
							entity.getProperty("password").toString());
					
					user.setId(entity.getKey().getId());
					
					returnedUsers.add(user);
				}
			}
			
			
			return returnedUsers;
		}
}
