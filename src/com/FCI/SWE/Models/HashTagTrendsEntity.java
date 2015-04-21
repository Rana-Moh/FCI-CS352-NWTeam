package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class HashTagTrendsEntity 
{
	public static ArrayList<String> getTrends()
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("hashCount");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		Map map = new HashMap<String,String>();
		for (Entity entity : pq.asIterable()) 
		{
			map.put(entity.getProperty("hashtag").toString(), entity.getProperty("count").toString() );

		}
		System.out.print(map);
		map=sortByValue(map);
		System.out.println(map);
		ArrayList<String>top= new ArrayList<String>();
		top=getTop10(map);
		Collections.reverse(top);
		
		System.out.println(top);
		
		return top;
	}


	private static ArrayList<String> getTop10(Map map) 
	{
		ArrayList<String>top= new ArrayList<String>();
		Iterator<Entry<String, String>> i = map.entrySet().iterator(); 
		//int count=0;
		while(i.hasNext())
		{
			//count++;
			String key = i.next().getKey();
			top.add(key);
			
			//if(count==10)
			//{
				//return top;
			//}
			
		}
		return top;
	}
	
/*
	public static Map sortByValue(Map unsortedMap) 
	{
		Map sortedMap = new TreeMap(new ValueComparator(unsortedMap));
		sortedMap.putAll(unsortedMap);
		return sortedMap;
	}
 */


	public static Map sortByValue(Map unsortMap) 
	{	 
		List list = new LinkedList(unsortMap.entrySet());

		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	

	
}
