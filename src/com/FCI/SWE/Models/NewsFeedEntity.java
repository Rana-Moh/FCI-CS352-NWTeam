package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Controller.UserController;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class NewsFeedEntity 
{
	
	public NewsFeedEntity(String feeling, String postContent, String postPlace,
			int numOfLikes, String privacy, String postTimestamp,
			String writerEmail, String where, String id) 
	{
		super();
		this.feeling = feeling;
		this.postContent = postContent;
		this.postPlace = postPlace;
		this.numOfLikes = numOfLikes;
		this.privacy = privacy;
		this.postTimestamp = postTimestamp;
		this.writerEmail = writerEmail;
		this.where = where;
		this.id = id;
	}

	public NewsFeedEntity() {
		// TODO Auto-generated constructor stub
	}

	private String feeling;
	private String postContent;
    private String postPlace;
    private int numOfLikes;
    private String privacy;
	private String postTimestamp;
	private String writerEmail;
	public String where;
	private String id;
	
	public static Vector<NewsFeedEntity> viewNewsFeed()
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);

		Vector <NewsFeedEntity> returnedposts = new Vector <NewsFeedEntity> ();
		ArrayList<String>friends=null;
		friends=UserEntity.getFriends();
		ArrayList<String>pageLiked = null;
		pageLiked=getPageLiked();
		ArrayList<String>pageOwned = null;
		pageOwned=getPageOwned();
		
		for(Entity entity : pq.asIterable())
		{
			if(entity.getProperty("postPlace").toString().equals(User.getCurrentActiveUser().getEmail()))
			{

				NewsFeedEntity timeline = new NewsFeedEntity(entity.getProperty("feeling").toString(),
						entity.getProperty("postContent").toString(),
						entity.getProperty("postPlace").toString(),
						Integer.parseInt(entity.getProperty("likes").toString()),
						entity.getProperty("privacy").toString(),
						entity.getProperty("time").toString(),
						entity.getProperty("writerEmail").toString(),
						entity.getProperty("where").toString(),
						Long.toString(entity.getKey().getId()));
				
				returnedposts.add(timeline);
			
			}
			
			else if(friends.contains(entity.getProperty("postPlace").toString()) && 
					entity.getProperty("privacy").toString().equals("public"))
			{
				NewsFeedEntity timeline = new NewsFeedEntity(entity.getProperty("feeling").toString(),
						entity.getProperty("postContent").toString(),
						entity.getProperty("postPlace").toString(),
						Integer.parseInt(entity.getProperty("likes").toString()),
						entity.getProperty("privacy").toString(),
						entity.getProperty("time").toString(),
						entity.getProperty("writerEmail").toString(),
						entity.getProperty("where").toString(),
						Long.toString(entity.getKey().getId()));
				
				returnedposts.add(timeline);
			}
			
			
			else if(pageLiked.contains(entity.getProperty("postPlace").toString())
					&& entity.getProperty("privacy").toString().equals("public"))
			{
				NewsFeedEntity timeline = new NewsFeedEntity(entity.getProperty("feeling").toString(),
						entity.getProperty("postContent").toString(),
						entity.getProperty("postPlace").toString(),
						Integer.parseInt(entity.getProperty("likes").toString()),
						entity.getProperty("privacy").toString(),
						entity.getProperty("time").toString(),
						entity.getProperty("writerEmail").toString(),
						entity.getProperty("where").toString(),
						Long.toString(entity.getKey().getId()));
				
				returnedposts.add(timeline);
				
			}
			
			else if(pageOwned.contains(entity.getProperty("postPlace").toString()))
			{
				NewsFeedEntity timeline = new NewsFeedEntity(entity.getProperty("feeling").toString(),
						entity.getProperty("postContent").toString(),
						entity.getProperty("postPlace").toString(),
						Integer.parseInt(entity.getProperty("likes").toString()),
						entity.getProperty("privacy").toString(),
						entity.getProperty("time").toString(),
						entity.getProperty("writerEmail").toString(),
						entity.getProperty("where").toString(),
						Long.toString(entity.getKey().getId()));
				
				returnedposts.add(timeline);
			}
			
			
			else if(entity.getProperty("privacy").toString().equals("custom")||
					entity.getProperty("privacy").toString().equals("both"))
			{
				System.out.println("in privacy = custom");
				
				if(checkPrivacy(Long.toString(entity.getKey().getId()),User.getCurrentActiveUser().getEmail()))
				{
					NewsFeedEntity timeline = new NewsFeedEntity(entity.getProperty("feeling").toString(),
							entity.getProperty("postContent").toString(),
							entity.getProperty("postPlace").toString(),
							Integer.parseInt(entity.getProperty("likes").toString()),
							entity.getProperty("privacy").toString(),
							entity.getProperty("time").toString(),
							entity.getProperty("writerEmail").toString(),
							entity.getProperty("where").toString(),
							Long.toString(entity.getKey().getId()));
					
					returnedposts.add(timeline);
				}
				
			}

		}
		return returnedposts;
		
	}
	
	
	
	private static ArrayList<String> getPageLiked()
	{
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		ArrayList<String>pagesliked= new ArrayList<String>();
		Query gaeQuery = new Query("likes");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for(Entity entity : pq.asIterable())
		{
			if(entity.getProperty("userEmail").toString().equals(User.getCurrentActiveUser().getEmail()))
			{
				pagesliked.add(entity.getProperty("pagaName").toString());
			}
		}
	
		return pagesliked;
	}
	

	private static ArrayList<String> getPageOwned()
	{
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		ArrayList<String>pagesOwned= new ArrayList<String>();
		Query gaeQuery = new Query("pages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for(Entity entity : pq.asIterable())
		{
			if(entity.getProperty("owner").toString().equals(User.getCurrentActiveUser().getEmail()))
			{
				pagesOwned.add(entity.getProperty("name").toString());
			}
		}
	
		return pagesOwned;
	}
	
	
	private static boolean checkPrivacy(String currentpostid, String email) 
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("CustomFriends");
		PreparedQuery pq = datastore.prepare(gaeQuery);

		for(Entity entity : pq.asIterable())
		{
			System.out.println("in custom1"+ currentpostid +" "+ email);
			if((entity.getProperty("friendEmail").toString().equals(email))&& 
					(entity.getProperty("ID").toString().equals(currentpostid)))
			{
				System.out.println("in custom2 "+ currentpostid +" "+ email);
				return true;
			}
		}
		return false;
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

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	
	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}


	public String getPostTimestamp() {
		return postTimestamp;
	}

	public void setPostTimestamp(String postTimestamp) {
		this.postTimestamp = postTimestamp;
	}

	public String getWriterEmail() {
		return writerEmail;
	}

	public void setWriterEmail(String writerEmail) {
		this.writerEmail = writerEmail;
	}

	

	public String getFeeling() {
		return feeling;
	}
	
	public String getID() {
		return id;
	}

	public void setFeeling(String feeling) {
		this.feeling = feeling;
	}
	
	public void setpostid(String postid) {
		this.id = postid;
	}
	
	
	
public static NewsFeedEntity parseHashInfo(String json) 
{
		
		JSONParser parser = new JSONParser();
		NewsFeedEntity Hash= new NewsFeedEntity();
		
		try{
			
			JSONObject object = (JSONObject) parser.parse(json);
		//	System.out.println("in parsing writer "+object.get("writer").toString());
			Hash.setWriterEmail(object.get("writer").toString());
			Hash.setWhere(object.get("place").toString());
			
			if(!Hash.getWhere().equals("page"))
			{
				Hash.setFeeling(object.get("feelings").toString());
			}
			else
				Hash.setFeeling("there is no feelings in page posts");
			//System.out.println("in parsing feeling "+object.get("feelings").toString());
			//System.out.println("hash is "+ Hash.getFeeling());
			Hash.setPostContent(object.get("content").toString());
			Hash.setPostTimestamp(object.get("time").toString());
			Hash.setPostPlace(object.get("place1").toString());
			Hash.setNumOfLikes(Integer.parseInt(object.get("likes").toString()));
			Hash.setPrivacy(object.get("privacy").toString());
			Hash.setpostid(object.get("id").toString());
			
		} catch(ParseException e){
			e.printStackTrace();
		}
		
		return Hash;
	
}


}
