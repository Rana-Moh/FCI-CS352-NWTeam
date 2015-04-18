package com.FCI.SWE.Controller;

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

import com.FCI.SWE.Models.User;
//import com.FCI.SWE.Models.User;
import com.FCI.SWE.Models.PageEntity;

@Path("/")
@Produces("text/html")
public class PageController {
	
	@GET
	@Path("/page")
	public Response page() {
		
		System.out.println("IN PAGE CONTROLLER /page");

		if (User.getCurrentActiveUser() == null) {
			return Response.serverError().build();
		}
		return Response.ok(new Viewable("/jsp/createPage")).build();
	}
	
	@GET
	@Path("/pageSearch")
	public Response pageSearch() {
		
		System.out.println("IN PAGE CONTROLLER /pageSearch");

		if (User.getCurrentActiveUser() == null) {
			return Response.serverError().build();
		}
		return Response.ok(new Viewable("/jsp/searchForPages")).build();
	}

	
	@POST
	@Path("/CreatePage")
	public String createPage(@FormParam("name") String name,
			@FormParam("type") String type, @FormParam("category") String category) {
		
		System.out.println("IN PAGE CONTROLLER /CreatePage");
		

		String serviceUrl = "http://localhost:8888/rest/CreatePageService";
		String urlParameters = "owner=" + User.getCurrentActiveUser().getEmail()
				+ "&name=" + name + "&type=" + type + "&category=" + category;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		System.out.println("retjson: " + retJson);
		
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Page created Successfully.";
			else if(object.get("Status").equals("Failed"))
				return "Page name already exists!";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	@POST
	@Path("/Search4Page")
	public Response search4Page(@FormParam("name") String name){
		
		System.out.println("IN PAGE CONTROLLER /Search4Page");
		
		String serviceUrl = "http://localhost:8888/rest/Search4PageService/";
		String urlParameters = "name="+name;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");

		Map <String, Vector<PageEntity>> passedPages = new HashMap <String, Vector<PageEntity>> ();
		
		JSONParser parser = new JSONParser();
		
		try {
			
			JSONArray array = (JSONArray)parser.parse(retJson);
			Vector <PageEntity> pages = new Vector <PageEntity> ();
			
			for(int i=0; i<array.size(); i++) {
				JSONObject object;
				
				object = (JSONObject) array.get(i);
				
				pages.add(PageEntity.parsePageInfo(object.toJSONString()));
			}
			
			System.out.println("pages found size: " + pages.size());
			
			passedPages.put("pagesList", pages);
			return Response.ok(new Viewable("/jsp/showPages", passedPages)).build();
			
		} catch (ParseException e){
			e.printStackTrace();
		}
		
		return null;	
	}
	
	@POST
	@Path("/likePage")
	public String likePage(@FormParam("pageName") String name){
		
		System.out.println("IN PAGE CONTROLLER /likePage");
		System.out.println("---------> page Name: " + name);
		
		String serviceUrl = "http://localhost:8888/rest/likePageService/";
		String urlParameters = "userEmail=" + User.getCurrentActiveUser().getEmail() + "&pageName="+name;
		System.out.println("urlParameters: " + urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");

		System.out.println(retJson);
		
		JSONParser parser = new JSONParser();
		Object obj;
		
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			
			if (object.get("Status").equals("OK"))
				return "Page Liked Successfully.";
			else if(object.get("Status").equals("Failed"))
				return "This page is already liked.";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
}
