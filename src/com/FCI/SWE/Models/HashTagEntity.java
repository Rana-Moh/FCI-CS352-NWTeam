package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class HashTagEntity 
{
	public HashTagEntity(String feeling, String postContent, String postPlace,
			int numOfLikes, String privacy, String postTimestamp,
			String writerEmail, String where, String postid) {
		super();
		this.feeling = feeling;
		this.postContent = postContent;
		this.postPlace = postPlace;
		this.numOfLikes = numOfLikes;
		this.privacy = privacy;
		this.postTimestamp = postTimestamp;
		this.writerEmail = writerEmail;
		this.where = where;
		this.id=postid;
	}

	public HashTagEntity() {
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
	
	public static Vector <HashTagEntity> search4Hashtags(String hashtag) 
	{
		// TODO Auto-generated method stub
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gae = new Query("postIDAndHashTag");
		PreparedQuery preparedQuery = datastore.prepare(gae);
		
		Vector <HashTagEntity> returnedhashtags = new Vector <HashTagEntity> ();
		
		for(Entity entity : preparedQuery.asIterable())
		{
			entity.getKey().getId();
			String currenthashtag = entity.getProperty("hashTag").toString();
			String currentpostid = entity.getProperty("ID").toString();
			//postid=entity.getProperty("postid").toString();

			
			if(currenthashtag.contains(hashtag))
			{
				HashTagEntity hash = new HashTagEntity();
				hash=SearchPost(currentpostid);
				//set el 7aga
				if(entity.getProperty("privacy").toString().equals("public"))
				{
					returnedhashtags.add(hash);
				}
				
				else if(entity.getProperty("privacy").toString().equals("both")||entity.getProperty("privacy").toString().equals("custom"))
				{
					if(checkPrivacy(currentpostid,User.getCurrentActiveUser().getEmail())|| CheckOwner(currentpostid))
					{
						returnedhashtags.add(hash);
					}
				}
				else if(entity.getProperty("privacy").toString().equals("private"))
				{
					if(CheckOwner(currentpostid))
					{
						returnedhashtags.add(hash);
					}
				}
				
			}
		}
		//System.out.print("hash "+returnedhashtags.get(0).getPostContent());
		return returnedhashtags;

     }
	private static boolean CheckOwner(String currentpostid) 
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);

		for(Entity entity : pq.asIterable())
		{
			if(Long.toString(entity.getKey().getId()).equals(currentpostid))
			{
				if(entity.getProperty("writerEmail").toString().equals(User.getCurrentActiveUser().getEmail()))
				{
					return true;
				}
				else
					return false;
			}
		}


		return false;
	}

	//
	private static boolean checkPrivacy(String currentpostid, String email) 
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("CustomFriends");
		PreparedQuery pq = datastore.prepare(gaeQuery);

		for(Entity entity : pq.asIterable())
		{
			if((entity.getProperty("friendEmail").toString().equals(email))&& 
					(entity.getProperty("ID").toString().equals(currentpostid)))
			{
				return true;
			}
		}
		return false;
	}
	
	static HashTagEntity SearchPost(String postid)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gae = new Query("posts");
		PreparedQuery preparedQuery = datastore.prepare(gae);
		
		//HashTagEntity hash= new HashTagEntity();
		for(Entity entity : preparedQuery.asIterable())
		{
			if(Long.toString(entity.getKey().getId()).equals(postid))
			{
				HashTagEntity hash = new HashTagEntity(entity.getProperty("feeling").toString(),
						entity.getProperty("postContent").toString(),
						entity.getProperty("postPlace").toString(),
						Integer.parseInt(entity.getProperty("likes").toString()),
						entity.getProperty("privacy").toString(),
						entity.getProperty("time").toString(),
						entity.getProperty("writerEmail").toString(),
						entity.getProperty("where").toString(),
						postid);
				return hash;
			}
			
		}
		
		return null;
	}
	
	@Override
	public String toString(){
		return "{" + id + "}";
	}
	
public static HashTagEntity parseHashInfo(String json) 
{
		
		JSONParser parser = new JSONParser();
		HashTagEntity Hash= new HashTagEntity();
		
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
	
	private void setCategory(String string) {
	// TODO Auto-generated method stub
	
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
	
	
}
	
	