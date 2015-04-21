package com.FCI.SWE.Models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PostLikeNotification implements
INotificationTypes 
{
	
	public static String stringToList(String e)
	{
		//e=e.substring(1,e.length()-1);
				System.out.println("in split "+ e);
				JSONParser parser = new JSONParser();
				try {
					JSONObject obj = (JSONObject)parser.parse(e);
					return obj.get("who liked ur post").toString();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				return null;//Arrays.asList(e.split(","));
				
	}
	
	public static String getid(String e)
	{
		//e=e.substring(1,e.length()-1);
				System.out.println("in split "+ e);
				JSONParser parser = new JSONParser();
				try {
					JSONObject obj = (JSONObject)parser.parse(e);
					return obj.get("id").toString();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				return null;//Arrays.asList(e.split(","));
				
	}
	
	

	@Override
	public String viewNotication(String s) 
	{
		System.out.println(s);
		JSONObject object = new JSONObject();
		String resKey= stringToList(s);
		String resKey2= getid(s);
		System.out.println(resKey);
		System.out.println("key 2 !"+resKey2);
		object.put("who",resKey);
		object.put("id",resKey2);
		//get post and view id
		return object.toJSONString();
		
	}

}