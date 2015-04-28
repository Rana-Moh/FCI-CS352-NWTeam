package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.FCI.SWE.Models.TimelineEntity;



@Path("/")
@Produces(MediaType.TEXT_PLAIN)

public class SharingPostService {
	@POST
	@Path("/postLike")	
	public String share(@FormParam("id") String id)
	{
		TimelineEntity timeEntityObj = new TimelineEntity();
		timeEntityObj = timeEntityObj.getCertainPost(id);
		String writerEmail = timeEntityObj.getWriterEmail();
		// [esraa]I am still working here it is not completed 

	}
}
