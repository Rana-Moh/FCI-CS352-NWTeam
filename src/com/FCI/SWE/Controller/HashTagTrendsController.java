package com.FCI.SWE.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.HashTagEntity;
import com.FCI.SWE.Models.HashTagTrendsEntity;
import com.FCI.SWE.Models.NotificationEntity;
import com.FCI.SWE.Models.PageEntity;


@Path("/")
@Produces("text/html")


public class HashTagTrendsController 
{
	public static ArrayList<String>trend= new ArrayList<String>();
	@GET
	@Path("/trends")
	public Response getTrends()
	{
		//get trends lw das 3ala 2y wa7da go to search4astags!
		trend=HashTagTrendsEntity.getTrends();
		System.out.println(trend);
		
		return Response.ok(new Viewable("/jsp/trends")).build();
	}

}