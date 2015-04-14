package com.FCI.SWE.Controller;

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

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.User;
//import com.FCI.SWE.Models.User;
import com.FCI.SWE.Models.UserEntity;



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
public class UserController {
	
	
	//public static  String [] selected= new String [100];

	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@GET
	@Path("/signup")
	public Response signUp() {
		return Response.ok(new Viewable("/jsp/register")).build();
	}

	/**
	 * Action function to render home page of application, home page contains
	 * only signup and login buttons
	 * 
	 * @return enty point page (Home page of this application)
	 */
	@GET
	@Path("/")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}

	/**
	 * Action function to render login page this function will be executed using
	 * url like this /rest/login
	 * 
	 * @return login page
	 */
	@GET
	@Path("/login")
	public Response login() {
		return Response.ok(new Viewable("/jsp/login")).build();
	}
	
	@GET
	@Path("/search")
	public Response search() {
		return Response.ok(new Viewable("/jsp/search")).build();
	}
	
	@POST
	@Path("/doSearch")
	public Response usersList(@FormParam("uname") String uname) {
		
		String serviceUrl = "http://localhost:8888/rest/SearchService/";
		String urlParameters = "uname="+uname;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");

		Map <String, Vector<User>> passedUsers = new HashMap <String, Vector<User>> ();
		
		JSONParser parser = new JSONParser();
		
		try {
			
			JSONArray array = (JSONArray)parser.parse(retJson);
			Vector <User> users = new Vector <User> ();
			
			for(int i=0; i<array.size(); i++) {
				JSONObject object;
				
				object = (JSONObject) array.get(i);
				
				users.add(User.parseUserInfo(object.toJSONString()));
			}
			
			System.out.println("users found size: " + users.size());
			
			passedUsers.put("usersList", users);
			return Response.ok(new Viewable("/jsp/showUsers", passedUsers)).build();
			
		} catch (ParseException e){
			e.printStackTrace();
		}
		
		return null;
		
	}


	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */
	@POST
	@Path("/response")
	@Produces(MediaType.TEXT_PLAIN)
	public String response(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		
		String serviceUrl = "http://localhost:8888/rest/RegistrationService";
		String urlParameters = "uname=" + uname + "&email=" + email + "&password=" + pass;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");
		
		try {

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Registered Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Failed";
	}

	/**
	 * Action function to response to login request. This function will act as a
	 * controller part, it will calls login service to check user data and get
	 * user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return Home page view
	 */
	@POST
	@Path("/home")
	@Produces("text/html")
	public Response home(@FormParam("email") String email,
			@FormParam("password") String pass) {
		String serviceUrl = "http://localhost:8888/rest/LoginService";
		String urlParameters = "email=" + email + "&password=" + pass;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");
		
		try {

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			
			JSONObject object = (JSONObject) obj;
			
			if (object.get("Status").equals("Failed"))
				return null;
			
			Map<String, String> map = new HashMap<String, String>();
			
			User user = User.getUser(object.toJSONString());
			
			map.put("name", user.getName());
			map.put("email", user.getEmail());
			return Response.ok(new Viewable("/jsp/home",map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@GET
	@Path("/SendFriendRequest")
	public Response sendFriendRequest() {
		return Response.ok(new Viewable("/jsp/SendFriendRequest")).build();
	}
	
	@GET
	@Path("/AddFriend")
	public Response acceptRequest() 
	{
		return Response.ok(new Viewable("/jsp/addFriend")).build();
	}
	
	
	@GET
	@Path("/logout")
	public Response logout() {
		User u = User.getCurrentActiveUser();
		u.setCurrentActiveUserToNull();
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}
	
	//*********************************************************************
	@POST
	@Path("RequestAlreadySent")
	@Produces("text/html")
	public Response sendRequestResult(@FormParam("friendEmail") String friendEmail) throws ParseException {
		
		String serviceUrl = "http://localhost:8888/rest/RequestAlreadySent";
		
		User currentUserAtClientSide = User.getCurrentActiveUser();
		String senderEmail = currentUserAtClientSide.getEmail();
		String urlParameters = "friendEmail=" + friendEmail + "&senderEmail="+senderEmail;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");
		
		try {

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			
			
			if (object.get("response").equals("request is not sent"))
				return Response.ok(new Viewable("/jsp/unableToSendReq", "")).build();
			
			else if(object.get("response").equals("request was sent before"))
				return Response.ok(new Viewable("/jsp/sentBefore", "")).build();
				
			return Response.ok(new Viewable("/jsp/RequestAlreadySent", "")).build();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

		return null;		
	}
	@POST
	@Path("AcceptAlreadySent")
	@Produces("text/html")

	public Response AcceptRequestResult(@FormParam("friendEmail") String friendEmail) throws ParseException {
		
		String serviceUrl = "http://localhost:8888/rest/accept";
		
		User currentUserAtClientSide = User.getCurrentActiveUser();
		String currentEmail = currentUserAtClientSide.getEmail();
		String urlParameters = "friendEmail=" + friendEmail + "&MyEmail="+currentEmail;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");
		
		try {

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			
			
			if (object.get("response").equals("request accepted"))
				return Response.ok(new Viewable("/jsp/accepted", "")).build();
			
			else if(object.get("response").equals("either this user didnt send you a request or he is already a friend"))
				return Response.ok(new Viewable("/jsp/res1", "")).build();
				
			return Response.ok(new Viewable("/jsp/res2", "")).build();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;		
	}
}