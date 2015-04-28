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

<<<<<<< HEAD
import com.FCI.SWE.Models.MsgEntity;
import com.FCI.SWE.Models.PostEntity;
=======
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463
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


public class notificationContoller 
{
<<<<<<< HEAD
	
	public static String accepted;
	public static String who;
	public static String content;
	public static String likes;
	public static String time;
	public static String writer;
	public static String type;
	public static String privacy;
	public static String feelings;
	public static String place;
=======
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463
	/*@GET
	@Path("/viewN")
	public Response viewN()
	{
		
	}*/
	@GET
	@Path("/AllNotification")
	public Response getAllNotifications() 
	{
	
		
		String serviceUrl = "http://localhost:8888/rest/getAllNotifications/";
		String currentUserEmail = User.getCurrentActiveUser().getEmail();
		String urlParameters = "currentUserEmail=" + currentUserEmail;
		
		String retJson = Connection.connect(serviceUrl, urlParameters, "GET",
				"application/x-www-form-urlencoded;charset=UTF-8");

		
		return Response.ok(new Viewable("/jsp/n")).build();
		
	}
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/parseNotification")
<<<<<<< HEAD
	public Response notification(@QueryParam("type") String type, @QueryParam("parameters")String parameters)
	{
		System.out.println(type + "  "+ parameters);
		String serviceUrl = "http://localhost:8888/rest/parseNotification/";
		String urlParameters = "type=" + type+"&parameters="+parameters;
		
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
=======
	public String notification(@QueryParam("type") String type, @QueryParam("paramters")String paramters)
	{
		String serviceUrl = "http://localhost:8888/rest/parseNotification/";
		String urlParameters = "type=" + type+"&paramters="+paramters;
		
		String retJson = Connection.connect(serviceUrl, urlParameters, "GET",
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		try {

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
				
<<<<<<< HEAD
			if(type.equals("FriendRequestNotification"))
			{
				String SUrl="http://localhost:8888/rest/accept";
				String url="friendEmail="+(String)object.get("response")+"&MyEmail="+User.getCurrentActiveUser();
				String retJSon1=Connection.connect(SUrl, url, "POST",				
						"application/x-www-form-urlencoded;charset=UTF-8");
				
				JSONParser parser1 = new JSONParser();
				Object obj1 = parser1.parse(retJSon1);
				JSONObject object1 = (JSONObject) obj1;
				
				
				if (object1.get("response").equals("request accepted"))
					return Response.ok(new Viewable("/jsp/accepted", "")).build();
				
				else if(object1.get("response").equals("either this user didnt send you a request or he is already a friend"))
					return Response.ok(new Viewable("/jsp/res1", "")).build();
					
				return Response.ok(new Viewable("/jsp/res2", "")).build();
				
			}
			else if(type.equals("msg"))
			{
				String SUrl="http://localhost:8888/rest/replyToMsg";
				String url="cname="+(String)object.get("response");
				String retJSon1=Connection.connect(SUrl, url, "POST", 				
						"application/x-www-form-urlencoded;charset=UTF-8");
				
				JSONParser parser1 = new JSONParser();
				Object obj1 = parser1.parse(retJSon1);
				JSONObject object1 = (JSONObject) obj1;
				
				if (object1.get("response").equals("msg is sent"))
				{
					MsgEntity.getmsg((String)object.get("response"));
					return Response.ok(new Viewable("/jsp/viewConversation")).build();
				}
				
			}
			
			else if(type.equals("postLike"))
			{
				JSONObject object1 = new JSONObject();
				object1= PostEntity.getPost(object.get("id").toString());
				who=object.get("who").toString();
				content = object1.get("content").toString();
				privacy=object1.get("privacy").toString();
				time=object1.get("time").toString();
				writer=object1.get("writer").toString();
				likes=object1.get("likes").toString();
				feelings=object1.get("feelings").toString();	
				place= object1.get("where").toString();
				
				
				System.out.println("in conr"+object1);
				
			    return Response.ok(new Viewable("/jsp/viewPost")).build();
			}
			
			
			else
			{
				accepted=(String)object.get("response");
				return Response.ok(new Viewable("/jsp/accepted1")).build();
			}
			
=======
			return (String) object.get("response");
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

<<<<<<< HEAD
}
=======
}
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463
