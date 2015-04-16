package com.FCI.SWE.Models;

import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;

public class SelectiondOfConversationMessageNotification implements
		INotificationTypes {

	
	public static List<String> stringToList(String e)
	{
		e=e.substring(1,e.length()-1);
		return Arrays.asList(e.split(", "));
		
	}
	
	
	
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

	@Override
	public String viewNotication(String S) 
	{
		

		JSONObject object = new JSONObject();
		return object.put("response",stringToList(S).get(1)).toString();

	}
}
