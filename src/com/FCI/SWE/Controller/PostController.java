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
public class PostController 
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
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/getCustomFriends")
	public Response MSG(@QueryParam("Email") List<String> email) {

		String serviceUrl = "http://localhost:8888/rest/fillCustomFriendPage";
		String urlParameters = "Emails=" + email.toString();
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		return Response.ok(new Viewable("/jsp/AllPosts")).build();

	}

	@GET
	@Path("/createPost")
	public Response createPostPage() {
		return Response.ok(new Viewable("/jsp/createPost")).build();

	}


	@GET
	@Path("/createPost1")
	public Response createPostPage(@QueryParam("Email")String email)
	{
		friendTimelineEmail=email;
		return Response.ok(new Viewable("/jsp/createPost1")).build();

		
	}
	@GET
	@Path("/createPostPage")
	public Response createPostPage1(@QueryParam("Email")String email)
	{
		pageNamecurr=email;
		return Response.ok(new Viewable("/jsp/createPostPage")).build();

		
	}

	@GET
	@Path("/postPlace")
	public Response postPlace() {
		return Response.ok(new Viewable("/jsp/postPlace")).build();

	}
	

/**
 * this a post on a friend profile
 * @param content
 * @param Feelings
 * @return
 */
	@POST
	@Path("/TimeLine1")
	public String TimeLine(@FormParam("content") String content,
			@FormParam("Feelings") String Feelings) {
		String serviceUrl = "http://localhost:8888/rest/CreatePost1";
		String urlParameters = "email="+friendTimelineEmail+"&content="+content+"&Feelings="+Feelings;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");
		
		try {

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("response").equals("postCreated"))
				return "posted Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "failed";



	}
	@GET
	@Path("/FriendList")
	public Response getFriends()
	{
		friends1= UserEntity.getFriends();
		//System.out.print(friends);
		
		return Response.ok(new Viewable("/jsp/FriendList")).build();		
		
	}
	@GET
	@Path("/PostOnPage")
	public Response getpages()
	{
		
		pageName=PostEntity.pages();
		
		return Response.ok(new Viewable("/jsp/PostOnPage")).build();		
		
	}
	
	@POST
	@Path("/AllPosts1")
	@Produces("text/html")
	public Response response() {
		
		
		posts=PostEntity.getPosts();
		System.out.print(posts);
		likes=PostEntity.getLikes();
		//System.out.print(likes);
			return Response.ok(new Viewable("/jsp/AllPosts1")).build();
		//return Response.ok(new Viewable("/jsp/createPost", "")).build();

	}
	

	@GET
	@Path("/TimeLine")
	public Response TimeLine() {
		return Response.ok(new Viewable("/jsp/TimeLine")).build();

	}
	
/**
 * 
 * this is for page post
 * @param content
 * @param privacy
 * @return
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

/**
 * this is my TimeLine post
 * @param postContent
 * @param privacy
 * @param postPlace
 * @param Feeling
 * @return
 */

	@POST
	@Path("/AllPosts")
	@Produces("text/html")
	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response response(@FormParam("postContent") String postContent,
			@FormParam("privacy") String privacy,
			@FormParam("postPlace") String postPlace,
			@FormParam("Feelings") String Feeling) {
		String writerEmail = User.getCurrentActiveUser().getEmail();
		String serviceUrl = "http://localhost:8888/rest/CreatePost";
		String urlParameters = "postContent=" + postContent + "&writerEmail="
				+ writerEmail + "&privacy=" + privacy + "&postPlace="
				+ User.getCurrentActiveUser().getEmail() + "&Feelings=" + Feeling;
		String result = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		if (privacy.equals("custom") && result.equals("postCreated")) 
		{
			friends=UserEntity.getFriends();
			return Response.ok(new Viewable("/jsp/customFriends", "")).build();
		}

		if (result.equals("postCreated"))
			return Response.ok(new Viewable("/jsp/AllPosts", "")).build();
		return Response.ok(new Viewable("/jsp/createPost", "")).build();

	}

	

}
