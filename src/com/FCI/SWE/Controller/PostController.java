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
import com.FCI.SWE.Models.UserEntity;

/**
 * @author Esraa Salem
 * @version 1.4
 * @since 15/4/2015
 * 
 *        This class will be resposible for the posts creation and functions
 *        related to them
 * **/

@Path("/")
@Produces("text/html")
public class PostController {

	@GET
	@Path("/createPost")
	public Response createPostPage() {
		return Response.ok(new Viewable("/jsp/createPost")).build();

	}

	@GET
	@Path("/postPlace")
	public Response postPlace() {
		return Response.ok(new Viewable("/jsp/postPlace")).build();

	}

	@GET
	@Path("/TimeLine")
	public Response TimeLine() {
		return Response.ok(new Viewable("/jsp/TimeLine")).build();

	}

	@GET
	@Path("/FriendList")
	public Response FriendList() {
		return Response.ok(new Viewable("/jsp/FriendList")).build();

	}

	@POST
	@Path("/AllPosts")
	@Produces("text/html")
	public Response response(@FormParam("postContent") String postContent) {
		String privacy = "";
		String writerEmail = User.getCurrentActiveUser().getEmail();
		String serviceUrl = "http://localhost:8888/rest/CreatePost";
		String urlParameters = "postContent=" + postContent + "&writerEmail="
				+ writerEmail;
		String result = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		if (result.equals("postCreated"))
			return Response.ok(new Viewable("/jsp/AllPosts", "")).build();
		return Response.ok(new Viewable("/jsp/createPost", "")).build();

	}

}
