package com.FCI.SWE.Services;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.PostEntity;
import com.FCI.SWE.Models.UserEntity;

@Path("/")
@Produces("text/html")
public class PostService {

	@POST
	@Path("/CreatePost")
	public String createPostService(
			@FormParam("postContent") String postContent,
			@FormParam("email") String email) {
		PostEntity creatpost = new PostEntity();
		return creatpost.createPost(email, postContent, "");
	}
}
