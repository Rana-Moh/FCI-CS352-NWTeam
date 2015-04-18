package com.FCI.SWE.Services;

import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.GroupEntity;
import com.FCI.SWE.Models.PageEntity;
import com.FCI.SWE.Models.UserEntity;
import com.google.appengine.labs.repackaged.org.json.JSONArray;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)

public class PageServices {

	@POST
	@Path("/CreatePageService")
	public String createPage(@FormParam("owner") String owner,
			@FormParam("name") String name,
			@FormParam("type") String type,
			@FormParam("category") String category) {
		
		System.out.println("IN PAGE SERVICES /CreatePageService");
		
		PageEntity pageEntity = new PageEntity();
		pageEntity.setName(name);
		pageEntity.setType(type);
		pageEntity.setCategory(category);
		pageEntity.setOwner(owner);
		
		JSONObject json = new JSONObject();
		
		//System.out.println("Save page: " + pageEntity.savePage());

		if(pageEntity.savePage())
			json.put("Status", "OK");
		else
			json.put("Status", "Failed");
		
		return json.toJSONString();
	}
	
	@POST
	@Path("/Search4PageService")
	public String createGroup(@FormParam("name") String name) {
		
		System.out.println("IN PAGE SERVICES /Search4PageService");
		
		Vector <PageEntity> pages = PageEntity.search4Pages(name);
		
		JSONArray returnedJson = new JSONArray();
	
		for(PageEntity page: pages)
		{
			JSONObject object = new JSONObject();
			object.put("name", page.getName());
			object.put("type", page.getType());
			object.put("category", page.getCategory());
			
			returnedJson.put(object);
		}

		return returnedJson.toString();
	}
	
	@POST
	@Path("/likePageService")
	public String likePage(@FormParam("userEmail") String userEmail,
			@FormParam("pageName") String pageName) {
		
		System.out.println("IN PAGE SERVICES /likePageService");
		System.out.println("userEmail: " + userEmail);
		System.out.println("page Name: " + pageName);
		
		
		PageEntity pageEntity = new PageEntity();
		pageEntity.setName(pageName);
		JSONObject json = new JSONObject();

		if(pageEntity.saveLike(userEmail) == true)
			json.put("Status", "OK");
		else
			json.put("Status", "Failed");
		
		return json.toJSONString();
	}
}
