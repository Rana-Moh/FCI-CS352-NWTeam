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
public class PostService 
{

	public static List<String> selected= new ArrayList<String>();

	public static List<String> stringToList(String e)
	{
		e=e.substring(1,e.length()-1);
		return Arrays.asList(e.split(", "));
		
	}
	
	
	@POST
	@Path("/fillCustomFriendPage")
	public String msgService(@FormParam("Emails") String email)
	{
		JSONObject jsonObj = new JSONObject();
		List<String>e = stringToList(email);
	
		
		for(int i=0; i<e.size();i++)
		{
			selected.add(e.get(i));	
			System.out.print("filling selected");
		}
		
		PostEntity.addPostIDandCustomFreinds(selected);
		return "response";
		
	}
	/**
	 * this my timeline Post
	 * @param postContent
	 * @param writerEmail
	 * @param privacy
	 * @param postPlace
	 * @param Feeling
	 * @return
	 */
	
	
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
		creatpost.setWhere("myTimeline");
		
		return creatpost.createPost();
	}
/**
 * this is for a friendTimeline post
 * @param content
 * @param email
 * @param Feelings
 * @return
 */
	@POST
	@Path("/CreatePost1")
	public String createPostService1(
			@FormParam("content") String content,
			@FormParam("email") String email,
			@FormParam("Feelings")String Feelings) {
		PostEntity creatpost = new PostEntity();
		JSONObject jsonObj = new JSONObject();
		creatpost.setWhere("friendTimeline");
		java.util.Date date = new java.util.Date();
		Timestamp postTimestamp = new Timestamp(date.getTime());		
		creatpost.setPostTimestamp(postTimestamp.toString());
		String res=creatpost.createPost1(email, content, "",User.getCurrentActiveUser().getEmail(),Feelings);
		jsonObj.put("response", res);
		return jsonObj.toJSONString();
	}

	
}
