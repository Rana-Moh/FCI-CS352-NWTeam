package com.FCI.SWE.Services;
import java.sql.Timestamp;

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
			@FormParam("writerEmail") String writerEmail,
			@FormParam("privacy") String privacy,
			@FormParam("postPlace") String postPlace,
			@FormParam("Feelings") String Feeling) {
		java.util.Date date = new java.util.Date();
		Timestamp postTimestamp = new Timestamp(date.getTime());		
		PostEntity creatpost = new PostEntity();
		creatpost.setPostContent(postContent);
		creatpost.setWriterEmail(writerEmail);
		creatpost.setPrivacy(privacy);
		creatpost.setPostPlace(postPlace);
		creatpost.setPostTimestamp(postTimestamp.toString());
		creatpost.setNumOfSeens(0);
		creatpost.setNumOfLikes(0);
		creatpost.setFeeling(Feeling);
		
		return creatpost.createPost();
	}
}
