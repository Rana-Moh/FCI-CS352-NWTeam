package com.FCI.SWE.Services;

import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.HashTagEntity;
import com.FCI.SWE.Models.PageEntity;
import com.FCI.SWE.Models.PostLikeEntity;
import com.google.appengine.labs.repackaged.org.json.JSONArray;



@Path("/")
@Produces(MediaType.TEXT_PLAIN)

public class PostLikeService 
{
	@POST
	@Path("/postLike")	
	public String Like(@FormParam("id") String id)
	{
		JSONObject jsonObj = new JSONObject();
		if(PostLikeEntity.insert(id))
		{
			jsonObj.put("response", "liked");
			
		}
		else
			jsonObj.put("response", "failed");
			
		return jsonObj.toJSONString();
	}
	

}
