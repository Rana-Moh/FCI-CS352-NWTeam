package com.FCI.SWE.Models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

//import jdk.nashorn.internal.parser.JSONParser;






import org.json.simple.JSONObject;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SelectionOfFriendRequestNotification implements INotificationTypes
{
	public static  String stringToList(String e)
	{
		//e=e.substring(1,e.length()-1);
		System.out.println("in split "+ e);
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject)parser.parse(e);
			return obj.get("from").toString();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;//Arrays.asList(e.split(","));
		
	}
	



	@Override
	public String viewNotication(String S) 
	{
		
		System.out.println("here2"+ S);
		JSONObject object = new JSONObject();
		String resKey= stringToList(S);
		System.out.println(resKey);		
		object.put("response",resKey);
		return object.toJSONString();

	}

}
