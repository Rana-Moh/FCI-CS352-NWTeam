package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Controller.UserController;
import com.FCI.SWE.Services.MsgServices;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Date;
 

/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 */

public class MsgEntity 
{
	public static String currentActionCon;
	
	public static ArrayList<String> getmsg(String cname)
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		MsgServices.sender.clear();
		MsgServices.msgs.clear();
		currentActionCon=cname;
		Query gaeQuery = new Query("conversation");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			
			if (entity.getProperty("cid").toString().equals(cname)) 
			{

				
					MsgServices.sender.add( entity.getProperty("from").toString());
					MsgServices.msgs.add(entity.getProperty("text").toString());
				
			}
		}
				
		return null;

	}
	public static boolean addMsg(String cname,String senderEmail,String text,ArrayList<String>recivers)
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		System.out.print("here1!");
        if(!checkExist(cname))
		{
    		java.util.Date date = new java.util.Date();	
    		Timestamp current = new Timestamp(date.getTime());
    		String time= current.toString();

        	addToCnameTable(cname);
        	addToLinkTable(cname,recivers,senderEmail);
        	Query gaeQuery = new Query("conversation");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			
			Entity con = new Entity("conversation", list.size() + 1);
			con.setProperty("from",senderEmail);
			con.setProperty("cid", cname);
			con.setProperty("text", text);
			con.setProperty("time", time);

			datastore.put(con);
			System.out.println("nnn");
			///////////////////////////////////////////////////////////////
			
			Query gaeQuery1 = new Query("Notification");
			PreparedQuery pq1 = datastore.prepare(gaeQuery);
			List<Entity> list1 = pq.asList(FetchOptions.Builder.withDefaults());
			
			
			
			
			
			JSONObject np= new JSONObject();
			np.put("from",senderEmail);
			np.put("cid", cname);
			np.put("text", text);
			np.put("time", time);
			StringWriter out = new StringWriter();
			try
			{

				np.writeJSONString(out);
				String txt = out.toString();
				
			
			
				for(int i=0; i<recivers.size();i++)
				{

		    		java.util.Date date1 = new java.util.Date();	
		    		Timestamp current1 = new Timestamp(date1.getTime());
		    		String time1= current1.toString();
					System.out.print(recivers.get(i));
					Entity addn = new Entity("Notification", list1.size() + i +1);
					addn.setProperty("type", "msg");
					addn.setProperty("parameters", txt);
					addn.setProperty("user", recivers.get(i));
					addn.setProperty("time", time1);
					datastore.put(addn);
				}	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			return true;
			
		}
        return false;
	}

	public static void addToLinkTable(String cname, ArrayList<String> recivers,String Senderemail) 
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
        
		Query gaeQuery = new Query("link");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		for(int i=0;i<=recivers.size();i++)
		{
			Entity link = new Entity("link", list.size() +i+1);
			
			if(i==recivers.size())
			{
				link.setProperty("useremail",Senderemail);
				link.setProperty("cid", cname);
				
				datastore.put(link);
				
			}
			else
			{
				link.setProperty("useremail",recivers.get(i));
				link.setProperty("cid", cname);
				datastore.put(link);
			}
			
		}
		
		
	}

	private static void addToCnameTable(String cname) 
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
        
		Query gaeQuery = new Query("cname");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		
			Entity cName = new Entity("cname", list.size() +1);
			cName.setProperty("cid", cname);
			datastore.put(cName);
		
		
		
	}

	public static boolean checkExist(String cname) 
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		System.out.print("here2!");

		Query gaeQuery = new Query("cname");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		System.out.print("here3!");
		for (Entity entity : pq.asIterable()) 
		{
			
			if (entity.getProperty("cid").toString().equals(cname))
			{		
					return true;
			}
		}

		return false;
	}


	public static boolean checkExists(String cname,String username) 
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("link");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) 
		{
			
			if ((entity.getProperty("cid").toString().equals(cname))
					&& (entity.getProperty("useremail").toString().equals(username)))
			{		
					return true;
			}
		}

		return false;
	}
	public static boolean addMsg2(String cname, String email, String content)
	{
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		System.out.print("here1!");
		
		java.util.Date date = new java.util.Date();	
		Timestamp current = new Timestamp(date.getTime());
		String time= current.toString();
        	Query gaeQuery = new Query("conversation");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			
			Entity con = new Entity("conversation", list.size() + 1);
			con.setProperty("from",email);
			con.setProperty("cid", cname);
			con.setProperty("text", content);
			con.setProperty("time", time);
			
			datastore.put(con);
			/////////////////////////////////////////////////////////////////////
			ArrayList<String>recievers= new ArrayList<String>();
			
			recievers=getreciveres(cname,email);
			
			Query gaeQuery1 = new Query("Notification");
			PreparedQuery pq1 = datastore.prepare(gaeQuery);
			List<Entity> list1 = pq.asList(FetchOptions.Builder.withDefaults());
			
			
			
			
			
			JSONObject np= new JSONObject();
			np.put("from",email);
			np.put("cid", cname);
			np.put("text", content);
			np.put("time", time);
			StringWriter out = new StringWriter();
			try
			{
				np.writeJSONString(out);
				String txt = out.toString();
			
			
				for(int i=0; i<recievers.size();i++)
				{
					Entity addn = new Entity("Notification", list1.size()+i + 1);
					addn.setProperty("type", "msg");
					addn.setProperty("parameters", txt);
					addn.setProperty("user", recievers.get(i));
					addn.setProperty("time", time);
					datastore.put(addn);
				}	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;
	
	}
	public static boolean addToLinkTable(String cname, String email) 
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
        
		Query gaeQuery = new Query("link");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			Entity link = new Entity("link", list.size()+1);
			
				link.setProperty("useremail",email);
				link.setProperty("cid", cname);
				if(datastore.put(link) != null)
					return true;
				return false;
		
	}
	public static ArrayList<String> getreciveres(String cname, String email)
	{
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		ArrayList<String> receivers= new ArrayList<String>();

		Query gaeQuery = new Query("link");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) 
		{
			if ((entity.getProperty("cid").toString().equals(cname))
					&& (!entity.getProperty("useremail").toString().equals(email)))
			{
				receivers.add(entity.getProperty("useremail").toString());
				
			}
		}

		return receivers;
	}
	
}
