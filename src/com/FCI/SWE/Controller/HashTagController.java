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

import com.FCI.SWE.Models.HashTagEntity;
import com.FCI.SWE.Models.NotificationEntity;
import com.FCI.SWE.Models.PageEntity;


@Path("/")
@Produces("text/html")
public class HashTagController 
{
	
	public static ArrayList <String>ids= new ArrayList<String>();
	public static int count;
	/*
	@GET
	@Path("/ref")
	public void reflect()
	{
		NotificationEntity.insertdumb();
	}*/
	
	@GET
	@Path("/Hashtag")
	public Response searchHashtag() 
	{
		return Response.ok(new Viewable("/jsp/searchHashtag")).build();

	}
	
	@POST
	@Path("/searchHashtag")
	public Response search(@FormParam("hashtag") String hashtag)
	{
		System.out.print("here !"+ hashtag);

		String serviceUrl = "http://localhost:8888/rest/Search4HashTag/";
		String urlParameters = "name="+hashtag;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");

		Map <String, Vector<HashTagEntity>> passedhashtags = new HashMap <String, Vector<HashTagEntity>> ();
		
		JSONParser parser = new JSONParser();
		
		try {
			
			JSONArray array = (JSONArray)parser.parse(retJson);
			Vector <HashTagEntity> hash = new Vector <HashTagEntity> ();
			
			for(int i=0; i<array.size(); i++) {
				JSONObject object;
				
				object = (JSONObject) array.get(i);
				
				hash.add(HashTagEntity.parseHashInfo(object.toJSONString()));
			}
			
			System.out.println("hashtags found size: " + hash.size());
			count=hash.size();
			ids.clear();
			for(int i =0 ;i<hash.size();i++)
			{
				ids.add(hash.get(i).getID());
			}
			
			
			passedhashtags.put("hashtagsList", hash);
			
			return Response.ok(new Viewable("/jsp/showhashtags", passedhashtags)).build();
			
		} catch (ParseException e){
			e.printStackTrace();
		}
		

		
		return null;
	}

	
}