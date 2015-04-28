package com.FCI.SWE.Models;

import java.util.Vector;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PublicPrivcy implements Privacy {

	@Override
	public Vector<PostEntity> select(String currentActiveUserEmail) {

		Vector<PostEntity> postVector = new Vector<PostEntity>();

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("privcy").equals("public")) {
				PostEntity p = new PostEntity();
				p.setFeeling(entity.getProperty("feeling").toString());
				p.setPostContent(entity.getProperty("postContent").toString());
				p.setNumOfLikes((int) entity.getProperty("likes"));
				p.setPostTimestamp(entity.getProperty("time").toString());
				p.setNumOfSeens((int) entity.getProperty("seens"));
				String ii = Long.toString(entity.getKey().getId());
			//	p.setPostID(ii);

				postVector.add(p);

			}
		}
		System.out.println("public Table " + postVector.toString());

		return postVector;

	}

}
