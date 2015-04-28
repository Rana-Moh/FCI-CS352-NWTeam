package com.FCI.SWE.Models;

import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
<<<<<<< HEAD
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
=======
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463

public class SelectiondOfConversationMessageNotification implements
		INotificationTypes {

	
<<<<<<< HEAD
	public static String stringToList(String e)
	{
		//e=e.substring(1,e.length()-1);
				System.out.println("in split "+ e);
				JSONParser parser = new JSONParser();
				try {
					JSONObject obj = (JSONObject)parser.parse(e);
					return obj.get("cid").toString();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				return null;//Arrays.asList(e.split(","));
				
=======
	public static List<String> stringToList(String e)
	{
		e=e.substring(1,e.length()-1);
		return Arrays.asList(e.split(", "));
		
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463
	}
	
	
	
<<<<<<< HEAD
=======
	@Override
	public INotificationTypes getNotification(String S) {

		INotificationTypes temp=null;
		
		try
		{
			temp=(INotificationTypes)Class.forName("com.FCI.SWE.Models.SelectiondOfConversationMessageNotification").newInstance();
			
		}
		catch(Exception e)
		{
				
		}
		
		return temp;
	
	}
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463

	@Override
	public String viewNotication(String S) 
	{
		
<<<<<<< HEAD
		
		//System.out.println("here2"+ S);
		JSONObject object = new JSONObject();
		String resKey= stringToList(S);
		System.out.println(resKey);		
		object.put("response",resKey);
		return object.toJSONString();
		
		
=======

		JSONObject object = new JSONObject();
		return object.put("response",stringToList(S).get(1)).toString();
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463

	}
}
