package com.FCI.SWE.Services;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;















//import org.datanucleus.sco.backed.Vector;
import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mortbay.util.ajax.JSON;

import com.FCI.SWE.Models.MsgEntity;
import com.FCI.SWE.Models.User;
import com.FCI.SWE.Models.MsgEntity;
import com.FCI.SWE.Models.UserEntity;
import com.google.appengine.labs.repackaged.org.json.JSONArray;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces("text/html")

public class MsgServices 
{
	

	public static List<String> msgs= new ArrayList<String>();
	public static List<String> sender= new ArrayList<String>();

	public static List<String> selected= new ArrayList<String>();

	public static List<String> stringToList(String e)
	{
		e=e.substring(1,e.length()-1);
		return Arrays.asList(e.split(", "));
		
	}
	
	@POST
	@Path("/MsgService")
	public String msgService(@FormParam("Emails") String email)
	{
		JSONObject jsonObj = new JSONObject();
		List<String>e= stringToList(email);
	
		
		for(int i=0; i<e.size();i++)
		{
			selected.add(e.get(i));
			System.out.print("filling selected");
		}
		return "response";
		
	}
	@POST
	@Path("/SendMsg")
	public static String sendMsg(@FormParam("cname")String cname, @FormParam("content") String content)
	{
		JSONObject jsonObj = new JSONObject();
		System.out.print("here0!");
		boolean msgadded= MsgEntity.addMsg(cname,User.getCurrentActiveUser().getEmail(), content, (ArrayList<String>) selected);
		selected.clear();
		if(msgadded)
		{
			System.out.println("here in if");
			jsonObj.put("response", "msg is sent");
		}
		else
		jsonObj.put("response", "msg wasn't sent");
		
		return jsonObj.toJSONString();
	}
	

	@POST
	@Path("/SendMsg2")
	public static String sendMsg2(@FormParam("cname")String cname, @FormParam("content") String content)
	{
		JSONObject jsonObj = new JSONObject();
		System.out.print("here0!");
		boolean msgadded= MsgEntity.addMsg2(cname,User.getCurrentActiveUser().getEmail(), content);
		selected.clear();
		if(msgadded)
		{
			System.out.println("here in if");
			jsonObj.put("response", "msg is sent");
		}
		else
		jsonObj.put("response", "msg wasn't sent");
		
		return jsonObj.toJSONString();
	}
	
	@POST
	@Path("/addMember")
	public static String addMember(@FormParam("email")String email, @FormParam("cname") String cname)
	{

		JSONObject jsonObj = new JSONObject();
	
		if(MsgEntity.checkExist(cname))
		{
			if(UserEntity.getFriendByEmail(email))
			{
				if(MsgEntity.addToLinkTable(cname, email))
					jsonObj.put("response", "member is added");

			}
			else
			{
				jsonObj.put("response", "member wasn't added");
			}
		}
		
		else
		{
			jsonObj.put("response", "member wasn't added");
		}
		
		
		return jsonObj.toJSONString();
	}
	
	
	

	@POST
	@Path("/replyToMsg")
	public static String replyToMsg(@FormParam("cname") String cname)
	{
		JSONObject jsonObj = new JSONObject();
		boolean msgsent=MsgEntity.checkExists(cname,User.getCurrentActiveUser().getEmail());
		
		
		if(msgsent)
		{
			jsonObj.put("response", "msg is sent");
		
		}
		else
			jsonObj.put("response", "msg wasn't sent");
			
			return jsonObj.toJSONString();
		
	}
	
}
