package com.FCI.SWE.Services;

import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.HashTagEntity;
import com.FCI.SWE.Models.NewsFeedEntity;
import com.FCI.SWE.Models.PageEntity;
import com.FCI.SWE.Models.TimelineEntity;
import com.google.appengine.labs.repackaged.org.json.JSONArray;



@Path("/")
@Produces(MediaType.TEXT_PLAIN)

public class NewsFeedService 
{
	
	@POST
	@Path("/viewMyNewsFeed")
	public String viewTimeline() {
		
		
		Vector <NewsFeedEntity> time = NewsFeedEntity.viewNewsFeed();
		
		JSONArray returnedJson = new JSONArray();
	
		for(NewsFeedEntity hashs: time)
		{
			JSONObject object = new JSONObject();
			object.put("writer", hashs.getWriterEmail());
			System.out.println("writer " + hashs.getWriterEmail());
			object.put("place", hashs.getWhere());
			object.put("feelings", hashs.getFeeling());
			object.put("likes", hashs.getNumOfLikes());
			object.put("content", hashs.getPostContent());
			object.put("time", hashs.getPostTimestamp());
			object.put("privacy", hashs.getPrivacy());
			//email of user or page name where post exists
			object.put("place1", hashs.getPostPlace());
			object.put("id", hashs.getID());
			
			
			returnedJson.put(object);
		}

		return returnedJson.toString();
	}
}
