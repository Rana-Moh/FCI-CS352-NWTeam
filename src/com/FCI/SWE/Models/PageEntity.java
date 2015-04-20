package com.FCI.SWE.Models;

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


public class PageEntity {
	
	private String name;
	private String type;
	private String category;
	private String owner;
	
	public PageEntity() {
		this.name = "";
		this.type = "";
		this.category = "";
	}
	
	public PageEntity(String name, String type, String category) {
		this.name = name;
		this.type = type;
		this.category = category;
	}

	public String getName(){
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public String getCategory(){
		return category;
	}
	
	public String getOwner(){
		return owner;
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
	
	public void setOwner(String owner){
		this.owner = owner;
	}
	
	public Boolean savePage() {
		
		System.out.println("IN PAGE ENTITY (savePage)");
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("pages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		
		//System.out.println("Page exists: " + pageExists());
		
		if(pageExists() == true)
			return false;

		try {
		Entity page = new Entity("pages", list.size() + 1);

		page.setProperty("name", this.name);
		page.setProperty("type", this.type);
		page.setProperty("category", this.category);
		page.setProperty("owner",this.owner);
		
		datastore.put(page);
		txn.commit();
		
		System.out.println(this.name);
		System.out.println(this.type);
		System.out.println(this.category);
		System.out.println(this.owner);
		
		} finally{
			if (txn.isActive()) {
		        txn.rollback();
		    }
		}
		
		return true;

	}

	public static Vector <PageEntity> search4Pages(String pageName) {
		// TODO Auto-generated method stub
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gae = new Query("pages");
		PreparedQuery preparedQuery = datastore.prepare(gae);
		
		Vector <PageEntity> returnedPages = new Vector <PageEntity> ();
		
		for(Entity entity : preparedQuery.asIterable())
		{
			entity.getKey().getId();
			String currentPageName = entity.getProperty("name").toString();
			//System.out.println("Page name = " + currentPageName);
			if(currentPageName.contains(pageName))
			{
				PageEntity page = new PageEntity(entity.getProperty("name").toString(), 
						entity.getProperty("type").toString(), 
						entity.getProperty("category").toString());
				
				returnedPages.add(page);
			}
		}
		
		
		return returnedPages;
	}

	public static PageEntity parsePageInfo(String json) {
		
		JSONParser parser = new JSONParser();
		PageEntity page = new PageEntity();
		
		try{
			
			JSONObject object = (JSONObject) parser.parse(json);
			page.setName(object.get("name").toString());
			page.setType(object.get("type").toString());
			page.setCategory(object.get("category").toString());
			
			
		} catch(ParseException e){
			e.printStackTrace();
		}
		
		return page;
		
	}
	
	public Boolean pageExists(){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gae = new Query("pages");
		PreparedQuery preparedQuery = datastore.prepare(gae);
		
		for(Entity entity : preparedQuery.asIterable())
		{
			entity.getKey().getId();
			String currentPageName = entity.getProperty("name").toString();
			if(currentPageName.equals(name))
				return true;
		}
		
		return false;
	}
	
	public Boolean likeExists(String userEmail){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gae = new Query("likes");
		PreparedQuery preparedQuery = datastore.prepare(gae);
		
		for(Entity entity : preparedQuery.asIterable())
		{
			entity.getKey().getId();
			String currentPageName = entity.getProperty("pageName").toString();
			String currentUserEmail = entity.getProperty("userEmail").toString();
			if(currentPageName.equals(name) && currentUserEmail.equals(userEmail))
				return true;
		}
		
		return false;
	}
	
	public Boolean saveLike(String userEmail) {
		
		System.out.println("IN PAGE ENTITY /saveLike");
		System.out.println("userEmail: " + userEmail);
		System.out.println("page Name: " + name);
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("likes");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		
		//System.out.println("Like exists: " + likeExists());
		
		if(likeExists(userEmail) == true || pageExists() == false)
			return false;

		try {
		Entity like = new Entity("likes", list.size() + 1);

		like.setProperty("userEmail", userEmail);
		like.setProperty("pageName" , name);
		
		datastore.put(like);
		txn.commit();
		
		} finally{
			if (txn.isActive()) {
		        txn.rollback();
		    }
		}
		
		return true;

	}

	public static ArrayList<String> getLikers(String pageNamecurr) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		User u = User.getCurrentActiveUser();

		ArrayList<String >likers= new ArrayList<String >();
		
		Query gaeQuery = new Query("likes");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			
			if (entity.getProperty("pageName").toString().equals(pageNamecurr)) 
			{
				likers.add(entity.getProperty("pageName").toString());
				
			}
		}
				
		return likers;
	}
	
	  public static String getPageOwner(String pageName){
		    
		  System.out.println("IN PAGE ENTITY (getPageOwner)");
		    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		    Query gaeQuery = new Query("pages");
		    PreparedQuery pq = datastore.prepare(gaeQuery);
		    
		    for (Entity entity : pq.asIterable()) {
		    	
		      if (entity.getProperty("name").toString().equals(pageName)) 
		        return entity.getProperty("owner").toString();
		        
		    }
		        
		    return null; 
	  }


}
