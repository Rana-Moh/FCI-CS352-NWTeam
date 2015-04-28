package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.apache.tools.ant.types.resources.First;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class SharingPostEntity {

	
	public void sharePostInDB(String currentUserEmail,String friendEmail, String postID)
	{

		ArrayList<String> matualFriends = new ArrayList<String>();
		matualFriends = getMatualFriends(currentUserEmail, friendEmail);
		for (int i = 0; i < matualFriends.size(); i++) {
			addToShareTable(matualFriends.get(i), postID);
		}
		
	}
	public void addToShareTable(String email, String ID)
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("shareTable");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		Transaction txn = datastore.beginTransaction();
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity entity = new Entity("shareTable");

		try {

			entity.setProperty("ID", ID);
			entity.setProperty("whoCanSee", email);
			datastore.put(entity);
			txn.commit();

		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
	}
	
	
	public ArrayList<String> getMatualFriends(String currentUserEmail,
			String friendEmail) {
		ArrayList<String> currentUserFriends = UserEntity
				.getFriends(currentUserEmail);
		ArrayList<String> friendsOfcurrUserFreind = UserEntity
				.getFriends(friendEmail);
		ArrayList<String> matualFriends = new ArrayList<String>();
		if (currentUserFriends.size() <= friendsOfcurrUserFreind.size()) {
			for (int i = 0; i < currentUserFriends.size(); i++) {
				for (int j = 0; j < friendsOfcurrUserFreind.size(); j++) {
					if (currentUserFriends.get(i).equals(
							friendsOfcurrUserFreind.get(j))) {
						matualFriends.add(currentUserFriends.get(i));
						break;
					}
				}
			}
		} else {
			for (int i = 0; i < friendsOfcurrUserFreind.size(); i++) {
				for (int j = 0; j < currentUserFriends.size(); j++) {
					if (currentUserFriends.get(j).equals(
							friendsOfcurrUserFreind.get(i))) {
						matualFriends.add(friendsOfcurrUserFreind.get(i));
						break;
					}
				}
			}
		}
		return matualFriends;
	}

	private static boolean isPostIdFound(String id) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("shareTable");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {

			if (entity.getProperty("id").toString().equals(id)) {
				return true;
			}
		}

		return false;
	}

}
