package com.FCI.SWE.Services;


import javax.ws.rs.FormParam;

import java.sql.Timestamp;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.PostEntity;
import com.FCI.SWE.Models.User;
import com.FCI.SWE.Models.UserEntity;

@Path("/")
@Produces("text/html")

public class PagePostServices 
{
	
	/**
	 * this is page post
	 * @param postContent
	 * @param writerEmail
	 * @param privacy
	 * @param postPlace
	 * @return
	 */

	@POST
	@Path("/CreatePostPage")
	public String createPostServicePage(
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
		creatpost.setWhere("page");
		return creatpost.createPost();
	}



}
