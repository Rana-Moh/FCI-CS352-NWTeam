package com.FCI.SWE.Models;

import java.sql.Timestamp;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class PostEntity {

	private String postContent;
	private String postPlace;
	private int numOfLikes;
	private int numOfSeens;
	private String privacy;
	private Timestamp postTimestamp;

	 
	public String createPost(String writerEmail, String postContent,
			String privacy) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		


		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		java.util.Date date = new java.util.Date();
		postTimestamp = new Timestamp(date.getTime());
		String timpStampStr = postTimestamp.toString();
		postPlace = "timeline/" + writerEmail;
		numOfLikes = 0;
		numOfSeens = 0;
		this.privacy = privacy;

		try {
			Entity post = new Entity("posts", list.size() + 1);
			post.setProperty("postContent", postContent);
			post.setProperty("writerEmail", writerEmail);
			post.setProperty("likes", "0");
			post.setProperty("seens", "0");
			post.setProperty("postPlace", postPlace);
			post.setProperty("privacy", privacy);
			post.setProperty("time", timpStampStr);
			datastore.put(post);
			System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNN");
			txn.commit();
		}finally{
			if (txn.isActive()) {
		        txn.rollback();
		    }
		}
	
		return "postCreated";

	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostPlace() {
		return postPlace;
	}

	public void setPostPlace(String postPlace) {
		this.postPlace = postPlace;
	}

	public int getNumOfLikes() {
		return numOfLikes;
	}

	public void setNumOfLikes(int numOfLikes) {
		this.numOfLikes = numOfLikes;
	}

	public int getNumOfSeens() {
		return numOfSeens;
	}

	public void setNumOfSeens(int numOfSeens) {
		this.numOfSeens = numOfSeens;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public Timestamp getPostTimestamp() {
		return postTimestamp;
	}

	public void setPostTimestamp(Timestamp postTimestamp) {
		this.postTimestamp = postTimestamp;
	}

}
