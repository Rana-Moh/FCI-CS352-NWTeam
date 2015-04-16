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

import com.FCI.SWE.Models.MsgEntity;
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


public class MsgController 
{
	public static  ArrayList <String> friends;
	public static  ArrayList <String> selected;
	@GET
	@Path("/getFriends")
	public Response getFriends()
	{
		friends= UserEntity.getFriends();
		System.out.print(friends);
		
		return Response.ok(new Viewable("/jsp/getFriends")).build();		
		
	}
	@GET
	@Path("/reply")
	public Response reply()
	{	
		return Response.ok(new Viewable("/jsp/reply")).build();		
		
	}
	
	
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/msg")
	public Response MSG(@QueryParam("Email") List<String> email)
	{
				
		
		String serviceUrl = "http://localhost:8888/rest/MsgService";
		String urlParameters = "Emails="+email.toString(); 
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");
	
		return Response.ok(new Viewable("/jsp/msg")).build();
		
	}


	@GET
	@Path("/MsgName")
	public String sendMSG(@QueryParam("cname")String cname, @QueryParam("content") String content)
	{
		String serviceUrl = "http://localhost:8888/rest/SendMsg";
		String urlParameters = "cname="+cname+"&content="+content; 
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");
	
		
		
		try {

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("response").equals("msg is sent"))
				return "sent Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "failed";


	}
	
	
	@GET
	@Path("/replymsg")
	public Response replyToMSG(@QueryParam("cname")String cname)
	{
		
		String serviceUrl = "http://localhost:8888/rest/replyToMsg";
		String urlParameters = "cname="+cname; 
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");
	
		
		
		try {

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("response").equals("msg is sent"))
			{
				MsgEntity.getmsg(cname);
				return Response.ok(new Viewable("/jsp/viewConversation")).build();
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	
	@GET
	@Path("/viewmsg")
	public String replyMSG( @QueryParam("content") String content)
	{
		String serviceUrl = "http://localhost:8888/rest/SendMsg2";
		String urlParameters = "cname="+MsgEntity.currentActionCon+"&content="+content; 
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");
	
		
		
		try {

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("response").equals("msg is sent"))
				return "sent Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "failed";


	}

	@GET
	@Path("/add")
	public Response addmember()
	{
		return Response.ok(new Viewable("/jsp/add")).build();
	}
	
	@GET
	@Path("/addMember")
	public String addmember2(@QueryParam("email") String email, @QueryParam("cname")String cname)
	{
		String serviceUrl = "http://localhost:8888/rest/addMember";
		String urlParameters = "email="+email+"&cname="+cname; 
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");
	
		
		
		try {

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("response").equals("member is added"))
				return "added Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "failed";
	}
	

}
