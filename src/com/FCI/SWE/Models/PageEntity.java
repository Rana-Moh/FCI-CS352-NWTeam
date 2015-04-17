package com.FCI.SWE.Models;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;


public class PageEntity {
	
	private String name;
	private String type;
	private String category;
	private long ownerId;
	
	public String getName(){
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public String getCategory(){
		return category;
	}
	
	public long getOwnerId(){
		return ownerId;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setCategory(String category){
		this.category = category;
	}
	
	public void setOwnerId(long id){
		this.ownerId = id;
	}
	
	public Boolean savePage() {
		
		System.out.println("IN PAGE ENTITY (savePage)");
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("pages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		try {
		Entity page = new Entity("pages", list.size() + 1);

		page.setProperty("name", this.name);
		page.setProperty("type", this.type);
		page.setProperty("category", this.category);
		page.setProperty("owner_id",this.ownerId);
		
		datastore.put(page);
		txn.commit();
		
		System.out.println(this.name);
		System.out.println(this.type);
		System.out.println(this.category);
		System.out.println(this.ownerId);
		
		} finally{
			if (txn.isActive()) {
		        txn.rollback();
		    }
		}
		
		return true;

	}

}
