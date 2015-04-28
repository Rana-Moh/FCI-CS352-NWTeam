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
import com.FCI.SWE.Models.PageEntity;


@Path("/")
@Produces("text/html")

public class SharingPostController {

	
	@POST
	@Path("/sharePost")
	public String SharePost(@FormParam("counter") String count) 
	{
		
		count= count.substring(1);
		int index=Integer.parseInt(count);
		index-=1;
		String id=HashTagController.ids.get(index);
		String serviceUrl = "http://localhost:8888/rest/sharePost/";
		String urlParameters = "id="+id;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");


		try {

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("response").equals("shared"))
				return "shared Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Sharing is failed";


		
	}
}
