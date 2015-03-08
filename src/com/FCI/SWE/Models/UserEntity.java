package com.FCI.SWE.Models;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.sun.org.apache.bcel.internal.generic.RETURN;

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
public class UserEntity {
	private String name;
	private String email;
	private String password;
	private long id;
	
	

	public static User currentUser = User.getCurrentActiveUser();

	public static User getCurrentActiveUser() {
		return currentUser;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

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

	public UserEntity() {

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

	// /**
	// *
	// * This static method will form UserEntity class using json format
	// contains
	// * user data
	// *
	// * @param json
	// * String in json format contains user data
	// * @return Constructed user entity
	// */
	// public static UserEntity getUser(String json) {
	//
	// JSONParser parser = new JSONParser();
	// try {
	// JSONObject object = (JSONObject) parser.parse(json);
	// return new UserEntity(object.get("name").toString(), object.get(
	// "email").toString(), object.get("password").toString());
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return null;
	//
	// }

//	public static UserEntity getFriendByEmail(String email) {
//
//		DatastoreService datastore = DatastoreServiceFactory
//				.getDatastoreService(); // create db
//
//		Query gaeQuery = new Query("users");
//		PreparedQuery pq = datastore.prepare(gaeQuery);
//		// get all users in the table then compare with the given parameters
//		for (Entity entity : pq.asIterable()) { // like iterator to traverse
//												// each record
//			// System.out.println(entity.getProperty("name").toString());
//			if (entity.getProperty("email").toString().equals(email)) {
//
//				UserEntity returnedFriend = new UserEntity(entity.getProperty(
//						"name").toString(), entity.getProperty("email")
//						.toString(), entity.getProperty("password").toString());
//				// when user found return the object
//				return returnedFriend;
//
//			}
//		}
//
//		return null;
//
//	}
	
	
	public static boolean getFriendByEmail(String email) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService(); // create db

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		// get all users in the table then compare with the given parameters
		for (Entity entity : pq.asIterable()) { // like iterator to traverse
												// each record
			// System.out.println(entity.getProperty("name").toString());
			if (entity.getProperty("email").toString().equals(email)) {

				
				return true;

			}
		}

		return false;

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

	public static UserEntity getUser(String name, String pass) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			System.out.println(entity.getProperty("name").toString());
			if (entity.getProperty("name").toString().equals(name)
					&& entity.getProperty("password").toString().equals(pass)) {

				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				return returnedUser;
			}
		}
		return null;
	}

	/**
	 * This method will be used to save user object in datastore
	 * 
	 * @return boolean if user is saved correctly or not
	 */
	public Boolean saveUser() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("users", list.size() + 1);

		employee.setProperty("name", this.name);
		employee.setProperty("email", this.email);
		employee.setProperty("password", this.password);
		datastore.put(employee);

		return true;

	}
	public static boolean checkRequestTable(String friendEmail, String senderEmail) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("requests");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			
			
			//System.out.println(entity.getProperty("name").toString());
			if (entity.getProperty("from").toString().equals(senderEmail)
					&& entity.getProperty("to").toString().equals(friendEmail) 
					&& entity.getProperty("Acceptance").toString().equals("0") ) 
			{
				//entity.setProperty("Acceptance", "1");

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
			
			
			//System.out.println(entity.getProperty("name").toString());
			if (entity.getProperty("from").toString().equals(femail)
					&& entity.getProperty("to").toString().equals(myEmail) 
					&& entity.getProperty("Acceptance").toString().equals("0") ) 
			{
				
				entity.setProperty("Acceptance","1");
				datastore.put(entity);
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
			
			datastore.put(addFriendRequest);

			return true;
		}
		
		return false;

	}
		
}