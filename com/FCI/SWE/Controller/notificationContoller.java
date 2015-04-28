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


public class notificationContoller 
{
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

}
