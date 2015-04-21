package com.FCI.SWE.Services;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.Controller.HashTagTrendsController;
import com.FCI.SWE.Models.HashTagEntity;
import com.FCI.SWE.Models.HashTagTrendsEntity;
import com.FCI.SWE.Models.PageEntity;
import com.google.appengine.labs.repackaged.org.json.JSONArray;



@Path("/")
@Produces(MediaType.TEXT_PLAIN)

public class HashTagTrendsServices 
{
	@POST
	@Path("/trends")
	public String getTrend()
	{
		HashTagTrendsController.trend=HashTagTrendsEntity.getTrends();
		System.out.println(HashTagTrendsController.trend);
		
		return "done";
	}
	
}
