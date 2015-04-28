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

import com.FCI.SWE.Models.PageEntity;
import com.FCI.SWE.Models.PostEntity;
import com.FCI.SWE.Models.User;
import com.FCI.SWE.Models.UserEntity;



@Path("/")
@Produces("text/html")

public class PagePostController 
{
	

	public static  ArrayList <String> friends1;
	public static  ArrayList <String> posts;
	public static  ArrayList <String> likes;
	public static String friendTimelineEmail;
	public static  ArrayList <String> pageName;
	public static String pageNamecurr;
	public static ArrayList <String> Pagelikers;

	public static ArrayList<String> friends;
	public static ArrayList<String> selected;

	@GET
	@Path("/createPostPage")
	public Response createPostPage1(@QueryParam("Email")String email)
	{
		pageNamecurr=email;
		return Response.ok(new Viewable("/jsp/createPostPage")).build();

		
	}

	
	@GET
	@Path("/PostOnPage")
	public Response getpages()
	{
		
		pageName=PostEntity.pages();
		
		return Response.ok(new Viewable("/jsp/PostOnPage")).build();		
		
	}

	
/**
 * 
 * this is for page post
 * @param content
 * @param privacy
 */
	@POST
	@Path("/TimeLine11")
	@Produces("text/html")
	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response response1(@FormParam("content") String content,
			@FormParam("privacy") String privacy) {
		System.out.println("page "+ pageName);
		String writerEmail = User.getCurrentActiveUser().getEmail();
		String serviceUrl = "http://localhost:8888/rest/CreatePostPage";
		String urlParameters = "postContent=" + content + "&writerEmail="
				+ pageNamecurr + "&privacy=" + privacy + "&postPlace="
				+ pageNamecurr + "&Feelings=" + "";
		String result = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		if (privacy.equals("custom") && result.equals("postCreated")) 
		{
			Pagelikers= PageEntity.getLikers(pageNamecurr);
			return Response.ok(new Viewable("/jsp/customlikers", "")).build();
		}

		if (result.equals("postCreated"))
			return Response.ok(new Viewable("/jsp/AllPosts", "")).build();
		return Response.ok(new Viewable("/jsp/createPost", "")).build();

	}



}
