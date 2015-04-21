package com.FCI.SWE.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.NewsFeedEntity;
import com.FCI.SWE.Models.TimelineEntity;


@Path("/")
@Produces("text/html")
public class NewsFeedController 
{
	//public static ArrayList <String> id= new ArrayList<String>();
	public static int counter;
	
	
	@GET
	@Path("/viewMyNewsFeed")
	public Response viewTimeLine()
	{
		System.out.print("here !");
		String serviceUrl = "http://localhost:8888/rest/viewMyNewsFeed/";
		System.out.print("here !");
		String retJson = Connection.connect(serviceUrl, "", "POST", "application/x-www-form-urlencoded;charset=UTF-8");

		System.out.print("here !");
		Map <String, Vector<NewsFeedEntity>> passedhashtags = new HashMap <String, Vector<NewsFeedEntity>> ();
		
		JSONParser parser = new JSONParser();
		
		try {
			
			JSONArray array = (JSONArray)parser.parse(retJson);
			Vector <NewsFeedEntity> timeline = new Vector <NewsFeedEntity> ();
			
			for(int i=0; i<array.size(); i++) {
				JSONObject object;
				
				object = (JSONObject) array.get(i);
				
				timeline.add(NewsFeedEntity.parseHashInfo(object.toJSONString()));
			}
			
			System.out.println("hashtags found size: " + timeline.size());
			counter=timeline.size();
			HashTagController.ids.clear();
			for(int i =0 ;i<timeline.size();i++)
			{
				HashTagController.ids.add(timeline.get(i).getID());
			}
			
			
			passedhashtags.put("timeline", timeline);
			
			return Response.ok(new Viewable("/jsp/viewNewsFeed", passedhashtags)).build();
			
		} catch (ParseException e){
			e.printStackTrace();
		}
		
		return null;
	}


}
