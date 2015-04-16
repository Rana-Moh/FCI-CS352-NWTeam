package com.FCI.SWE.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
public class Service {

	/*
	 * @GET
	 * 
	 * @Path("/index") public Response index() { return Response.ok(new
	 * Viewable("/jsp/entryPoint")).build(); }
	 */

	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		JSONObject object = new JSONObject();
		boolean flag=UserEntity.getFriendByEmail(email);
		if(!flag)
		{
		user.saveUser();
		object.put("Status", "OK");
		}
		else 
		{
			object.put("Status","no");
		}
		return object.toString();
	}

	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("email") String email,
			@FormParam("password") String pass) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(email, pass);
		if (user == null) {
			object.put("Status", "Failed");
		} else {
			object.put("Status", "OK");
			object.put("id", user.getId());
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
		}
		return object.toString();
	}

	// /ESraa Implemenation
	@POST
	@Path("/RequestAlreadySent")
	public String RequestAlreadySent(@FormParam("friendEmail") String email,@FormParam("senderEmail") String senderEmail) {

		JSONObject jsonObj = new JSONObject();
		boolean friendIsFound = UserEntity.getFriendByEmail(email);
		
		
		
		if (friendIsFound) {
			boolean flag2 = UserEntity.addFriendRequestIDsFromAndTo(email,
					senderEmail);
			if (flag2)
				jsonObj.put("response", "request is sent");
			else{
				jsonObj.put("response", "request was sent before");
			}
		}else{
			jsonObj.put("response", "request is not sent");
		}
		
		return jsonObj.toJSONString();
	}
	
	
	
	
	@POST
	@Path("/accept")
	public String AcceptRequest(@FormParam("friendEmail") String femail,@FormParam("MyEmail") String currentEmail) 
	{

		JSONObject jsonObj = new JSONObject();
		boolean friendIsFound = UserEntity.getFriendByEmail(femail);
		
		if(friendIsFound)
		{
		   			boolean flag2 = UserEntity.checkAcceptTable(currentEmail,
					femail);
					
					if(flag2)
					{
						jsonObj.put("response", "request accepted");
					}
					else
					{
						jsonObj.put("response", "either this user didnt send you a request or he is already a friend");
					}
					
					
		}
		else
		{
			jsonObj.put("response", "no such user");
		}
		
		return jsonObj.toJSONString();
		
	}	
	
	/**
	 * Search Rest service, this service will be called to search for a user that's
	 * in the data store
	 * @param email
	 *            provided friend's email to search for
	 * @return Status json
	 */
	
	@POST
	@Path("/SearchService")
	public String search(@FormParam("uname") String s){
		System.out.println(s);
		System.out.println("fel services "+ s);
		Vector <UserEntity> users = UserEntity.searchUser(s);
		JSONArray returnedJson = new JSONArray();
	
		for(UserEntity user: users)
		{
			JSONObject object = new JSONObject();
			object.put("id", user.getId());
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			
			returnedJson.put(object);
		}

		return returnedJson.toString();
	}

}