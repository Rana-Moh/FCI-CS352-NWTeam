package com.FCI.SWE.Models;

import java.util.Vector;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class CustomPrivcy implements Privacy {

	public Vector<PostEntity> select(String currentActiveUserEmail) {

		Vector<PostEntity> postVector = new Vector<PostEntity>();

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Vector<String> postIdUsercansee = new Vector<String>();
		Query gaeQuery = new Query("CustomFriends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("friendEmail")
					.equals(currentActiveUserEmail)) {

				postIdUsercansee.add((String) entity.getProperty(Long
						.toString(entity.getKey().getId())));

			}
		}

		DatastoreService datastore1 = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery1 = new Query("posts");
		PreparedQuery pq1 = datastore1.prepare(gaeQuery1);

		for (Entity entity : pq1.asIterable()) {

			String id = Long.toString(entity.getKey().getId());

			for (int i = 0; i < postIdUsercansee.size(); i++) {
				if (postIdUsercansee.get(i).equals(id)
						&& entity.getProperty("privcy").equals("custom")) {
					PostEntity p = new PostEntity();
					p.setFeeling(entity.getProperty("feeling").toString());
					p.setPostContent(entity.getProperty("postContent")
							.toString());
					p.setNumOfLikes((int) entity.getProperty("likes"));
					p.setPostTimestamp(entity.getProperty("time").toString());
					p.setNumOfSeens((int) entity.getProperty("seens"));
					String ii = Long.toString(entity.getKey().getId());
			//		p.setPostID(ii);
					// PostEntity.setPostID(Long.toString(entity.getKey().getId()));
					postVector.add(p);
				}
			}

		}

		System.out.println("Custom Table " + postVector.toString());
		return postVector;

	}

}
