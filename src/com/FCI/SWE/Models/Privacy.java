package com.FCI.SWE.Models;

import java.util.Vector;

public interface Privacy {
	
	public Vector<PostEntity> selectPosts(String currentActiveUserEmail);
	
}
