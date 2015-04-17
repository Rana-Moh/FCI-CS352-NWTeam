package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.PageEntity;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)

public class PageServices {

	@POST
	@Path("/CreatePageService")
	public String createGroup(@FormParam("user_id") String userId,
			@FormParam("name") String name,
			@FormParam("type") String type,
			@FormParam("category") String category) {
		
		System.out.println("IN PAGE SERVICES /CreatePageService");
		
		PageEntity pageEntity = new PageEntity();
		pageEntity.setName(name);
		pageEntity.setType(type);
		pageEntity.setCategory(category);
		pageEntity.setOwnerId(Long.parseLong(userId));
		
		JSONObject json = new JSONObject();

		if(pageEntity.savePage())
			json.put("Status", "OK");
		else
			json.put("Status", "Failed");
		
		return json.toJSONString();
	}
}
