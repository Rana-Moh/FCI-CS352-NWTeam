package com.FCI.SWE.Models;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class Posts 

{
	
	private String emailOfOwner;
	private String content;
	private int  numberOfLikes;
	private int pageID;
	public Posts(String emailOfOwner, String content, int numberOfLikes,int pageID) {
		this.emailOfOwner = emailOfOwner;
		this.content = content;
		this.numberOfLikes = numberOfLikes;
		this.pageID=pageID;
		
	}
	public Boolean savePost() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		
		//System.out.println("Size = " + list.size());
		
		try {
		Entity post = new Entity("posts", list.size() + 1);

		post.setProperty("owner", this.emailOfOwner);
		post.setProperty("content", this.content);
		post.setProperty("numberOfLikes", this.numberOfLikes);
	    post.setProperty("pageID",this.pageID);
		
		datastore.put(post);
		txn.commit();
		}finally{
			if (txn.isActive()) {
		        txn.rollback();
		    }
		}
		return true;

	}
	
	
}
