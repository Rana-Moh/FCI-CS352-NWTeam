package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.sql.Timestamp;
import java.util.List;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.UserEntity;

@Path("/")
@Produces("text/html")
public class PostEntity 
{
	private String feeling;
	private String postContent;
    private String postPlace;
    private int numOfLikes;
    private int numOfSeens;
    private String privacy;
	public static  ArrayList <String> friends;
	private String postTimestamp;
	private String writerEmail;
	Vector<String> postHashTags = new Vector<String>();
	private static String postID;
	private boolean flag = false;
	private String Where;
	
	
	
	public static void addPostIDandCustomFreinds(
			List<String> friendEmails) {
		for (int i = 0; i < friendEmails.size(); i++) {
			
			addToCustomTable(postID, friendEmails.get(i),i);
			
		}
	}
	public static void addToCustomTable(String ID, String email, int i) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("CustomFriends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		Transaction txn = datastore.beginTransaction();
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity entity = new Entity("CustomFriends");

		try {

			entity.setProperty("ID", ID);
			entity.setProperty("friendEmail", email);
			datastore.put(entity);
			txn.commit();

		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}

	}
	public Vector<String> getPostsHashTags() {

		int postContSize = postContent.length() - 1;
		while (postContent.charAt(postContSize) == ' ') {
			postContent = postContent.substring(0, postContSize);
			postContSize--;
		}
		String[] postWords = postContent.split(" ");
		for (int i = 0; i < postWords.length; i++) {
			System.out.println("wordwordwordword   ");
			if (postWords[i].charAt(0) == '#') {
				postHashTags.add(postWords[i]);
			}
		}
		System.out.println("!!!!!!!!!!!!!!!   " + postHashTags.toString());
		return postHashTags;
	}

	public String convertHashTagVecToStr() {

		// String result = "";
		// for (int i = 0; i < postHashTags.size(); i++) {
		// result += (postHashTags.get(i)+"+");
		//
		// }
		// return result;
		JSONArray returnedJson = new JSONArray();
		JSONObject obj = new JSONObject();
		for (int i = 0; i < postHashTags.size(); i++) {

			obj.put("hashTag", postHashTags.get(i));
			returnedJson.put(obj);
		}
		return returnedJson.toString();

	}

	

	public void addToIDAndHashTable(String ID, String h, int i) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("postIDAndHashTag");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		Transaction txn = datastore.beginTransaction();
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity idAndhashEntity = new Entity("postIDAndHashTag");
		System.out.println("H " + h + " ID  = " + ID + "   list.size  "
				+ list.size() + "  i  " + i);
		try {

			idAndhashEntity.setProperty("ID", ID);
			idAndhashEntity.setProperty("hashTag", h);
			idAndhashEntity.setProperty("privacy", this.privacy);
			datastore.put(idAndhashEntity);
			txn.commit();

		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}

	}
	/**
	 * this is page and mytimeline post
	 */

	public String createPost() {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);

		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		String ID = "";
		try {

			Entity post = new Entity("posts", list.size() + 1);
			post.setProperty("postContent", postContent);
			post.setProperty("writerEmail", writerEmail);
			post.setProperty("likes", 0);
			post.setProperty("seens", 0);
			post.setProperty("postPlace", postPlace);
			post.setProperty("privacy", privacy);
			post.setProperty("time", postTimestamp);
			post.setProperty("feeling", feeling);
			post.setProperty("where", this.Where);
			getPostsHashTags();
			String hashVec = convertHashTagVecToStr();
			post.setProperty("hashTags", hashVec);
			datastore.put(post);
			postID=Long.toString(post.getKey().getId());
            
			
			ID = Long.toString(post.getKey().getId());

			System.out.println("++++=======================   " + ID);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}

		for (int i = 0; i < postHashTags.size(); i++) {
			addToIDAndHashTable(ID, postHashTags.get(i), i);
			addToHashTagCountersTable(ID,postHashTags.get(i),i);
		}

		return "postCreated";

	}

/**
 * this for a friend page post	
 * @param placeEmail
 * @param postContent
 * @param privacy
 * @param writer
 * @param feelings
 */
	public String createPost1(String placeEmail, String postContent,
			String privacy,String writer, String feelings) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		


		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		java.util.Date date = new java.util.Date();
		//postTimestamp = new Timestamp(date.getTime());
		//String timpStampStr = postTimestamp.toString();
		postPlace = placeEmail;
		numOfLikes = 0;
		numOfSeens = 0;
		this.privacy = privacy;
		if(!postPlace.equals(writer))
		{
			this.privacy="both";
			this.flag= true;
			
		}

		try {
			this.postContent=postContent;
			Entity post = new Entity("posts", list.size() + 1);
			post.setProperty("postContent", postContent);
			post.setProperty("writerEmail", writer);
			post.setProperty("likes", "0");
			post.setProperty("seens", "0");
			post.setProperty("postPlace", postPlace);
			post.setProperty("privacy", this.privacy);
			post.setProperty("where", this.Where);
			getPostsHashTags();
			String hashVec = convertHashTagVecToStr();
			post.setProperty("hashTags", hashVec);
			post.setProperty("feeling", feelings);
			post.setProperty("time", postTimestamp);
			datastore.put(post);
			postID=Long.toString(post.getKey().getId());
			//ID = Long.toString(post.getKey().getId());
		
			txn.commit();
		}finally{
			if (txn.isActive()) {
		        txn.rollback();
		    }
		}

		for (int i = 0; i < postHashTags.size(); i++) {
			addToIDAndHashTable(postID, postHashTags.get(i), i);
			addToHashTagCountersTable(postID,postHashTags.get(i),i);
			
		}
		if(this.flag==true)
		{
			addToCustomTableBoth(User.getCurrentActiveUser().getEmail(),postID);
			addToCustomTableBoth(this.postPlace,postID);
		}

	
		return "postCreated";

	}
	
	  private void addToCustomTableBoth(String email,String postID) 
	{
		  ArrayList<String>emails= new ArrayList<String>();
		  
		  emails=UserEntity.getFriends(email);
		  for (int i = 0; i < emails.size(); i++) 
		  {
			  addToCustomTable(postID,emails.get(i), i);
				
		  }
		  
		  emails.clear();
		  
		  emails=UserEntity.getFriends(email);
		  for (int i = 0; i < emails.size(); i++) 
		  {
			  // lw mwgod dont put it ! needs to implements
			  addToCustomTable(postID,emails.get(i), i);
				
		  }
				
	}
	private void addToHashTagCountersTable(String postID2, String hashtag, int i) 
	  {
		  boolean found=false;

			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			
			Query gaeQuery = new Query("hashCount");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			for (Entity entity : pq.asIterable()) 
			{
				if(entity.getProperty("hashtag").toString().equals(hashtag))
				{
					System.out.println("here !");
					found=true;
					String oldcount= entity.getProperty("count").toString();
					int newcount=Integer.parseInt(oldcount);
					newcount++;
					entity.setProperty("count",String.valueOf(newcount));
					datastore.put(entity);
					
					
				}
				
			}
			
			if(found==false)
			{
				Entity entity = new Entity("hashCount");
				entity.setProperty("count","1");
				entity.setProperty("hashtag",hashtag);
				datastore.put(entity);
				
			}
		  
		
	  }
	public static ArrayList<String> pages()
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		User u = User.getCurrentActiveUser();
		ArrayList<String >pageName= new ArrayList<String >();
		
		Query gaeQuery = new Query("pages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			
			if ((entity.getProperty("owner").toString().equals(u.getEmail())))
					
			{
					pageName.add( entity.getProperty("name").toString());
			
			}
		}
				
		return pageName;
	}

	
	public static ArrayList<String> getPosts()
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		User u = User.getCurrentActiveUser();

		ArrayList<String >posts= new ArrayList<String >();
		
		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			
		
			posts.add(entity.getProperty("postContent").toString());
				
			
		}
				
		return posts;
	}
	public static ArrayList<String> getLikes()
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		User u = User.getCurrentActiveUser();

		ArrayList<String >likes= new ArrayList<String >();
		
		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			
		
			likes.add(entity.getProperty("likes").toString());
				
			
		}
				
		return likes;
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
	
	public String getWhere() {
		return Where;
	}

	public void setWhere(String where) {
		this.Where = where;
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

	public Vector<String> getPostHashTags() {
		return postHashTags;
	}

	public void setPostHashTags(Vector<String> postHashTags) {
		this.postHashTags = postHashTags;
	}

	public String getFeeling() {
		return feeling;
	}

	public void setFeeling(String feeling) {
		this.feeling = feeling;
	}
	
	   public static Vector<PostEntity> viewPagePosts(String name) {
		    // TODO Auto-generated method stub
		   
		   System.out.println("IN POST ENTITY /viewPagePosts");
		      
		    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		    Query gae = new Query("posts");
		    PreparedQuery preparedQuery = datastore.prepare(gae);
		    
		    Vector <PostEntity> returnedPosts = new Vector <PostEntity> ();
		    
		    for(Entity entity : preparedQuery.asIterable()){
		    	
			      entity.getKey().getId();
			      String where = entity.getProperty("where").toString();
			      String pageOfPost = entity.getProperty("postPlace").toString();
			      
			      if(where.equals("page") && pageOfPost.equals(name)){
			    	  
			    	  	//System.out.println("IN LOOP");
				        PostEntity post = new PostEntity();
				        post.setPrivacy(entity.getProperty("privacy").toString());
				        post.setPostContent(entity.getProperty("postContent").toString());
				        System.out.println("found content: " + entity.getProperty("postContent").toString());
				        post.setPostTimestamp(entity.getProperty("time").toString());
				        String seens = entity.getProperty("seens").toString();
				        int intseens = Integer.parseInt(seens);
				        post.setNumOfSeens(intseens);
				        
				        returnedPosts.add(post);
			      }
		    }
		    
		    
	    return returnedPosts;
	   }
	   
	public static PostEntity parsePostInfo(String json) {
		// TODO Auto-generated method stub
		
		JSONParser parser = new JSONParser();
		PostEntity post = new PostEntity();
		
		try{
			
			JSONObject object = (JSONObject) parser.parse(json);
			post.setPostContent(object.get("content").toString());
			System.out.println("(parsing) POST CONTENT: " + object.get("content").toString());
			post.setPrivacy(object.get("privacy").toString());
			post.setPostTimestamp(object.get("time").toString());
			
			String seens = object.get("seens").toString();
	        int intseens = Integer.parseInt(seens);
	        post.setNumOfSeens(intseens);
			
			
		} catch(ParseException e){
			e.printStackTrace();
		}
		
		return post;
	
	}
	
	
	public static JSONObject getPost(String id)
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		JSONObject object = new JSONObject();
		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) 
		{
			if (Long.toString(entity.getKey().getId()).equals(id))	 
			{
				System.out.println("in get post !");
				object.put("content",entity.getProperty("postContent"));
				object.put("writer",entity.getProperty("writerEmail"));
				object.put("likes",entity.getProperty("likes"));
				object.put("seen",entity.getProperty("seens"));
				object.put("where",entity.getProperty("postPlace"));
				object.put("privacy",entity.getProperty("privacy"));
				object.put("type",entity.getProperty("where"));
				object.put("hashtags",entity.getProperty("hashTags"));
				object.put("feelings",entity.getProperty("feeling"));
				object.put("time",entity.getProperty("time"));
				
				System.out.println(object.get("privacy").toString());
				System.out.println(object);
				
				
			}
		}
		return object;
	}
}