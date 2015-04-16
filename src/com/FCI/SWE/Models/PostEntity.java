package com.FCI.SWE.Models;

import java.util.List;
import java.util.Vector;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import org.json.simple.JSONObject;

public class PostEntity {

	private String feeling;
	private String postContent;
	private String postPlace;
	private int numOfLikes;
	private int numOfSeens;
	private String privacy;
	private String postTimestamp;
	private String writerEmail;
	Vector<String> postHashTags = new Vector<String>();

	public Vector<String> getPostsHashTags() {
		String[] postWords = postContent.split(" ");
		for (int i = 0; i < postWords.length; i++) {
			if (postWords[i].charAt(0) == '#') {
				postHashTags.add(postWords[i]);
			}
		}
		System.out.println("!!!!!!!!!!!!!!!   "+postHashTags.toString());
		return postHashTags;
	}

	public String convertHashTagVecToStr() {

//		String result = "";
//		for (int i = 0; i < postHashTags.size(); i++) {
//			result += (postHashTags.get(i)+"+");
//		
//		}
//		return result;
		JSONArray returnedJson = new JSONArray();
		JSONObject obj = new JSONObject();
		for (int i = 0; i < postHashTags.size(); i++) {

			obj.put("hashTag", postHashTags.get(i));
			returnedJson.put(obj);
		}
		return returnedJson.toString();
		
		
	}

	public String createPost() {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);

		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

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
			
			getPostsHashTags();
			String hashVec = convertHashTagVecToStr();
			post.setProperty("hashTags", hashVec);
			datastore.put(post);
			txn.commit();
		} finally {
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
}
