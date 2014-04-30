package com.codepath.gridimagesearch;

import java.io.Serializable;
import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2636389073439281291L;
	private String fullUrl;
	private String thumbUrl;
	
	public ImageResult(JSONObject json) { // want to describe which fields are going to be parsed and
		try {
			this.fullUrl = json.getString("url");
			this.thumbUrl = json.getString("tbUrl");
		} catch (JSONException e) {
			this.fullUrl = null;
			this.thumbUrl = null;
			
		}
	}
	//create 2 fields full and thumb
	public String getFullUrl() {
		return fullUrl;
		
	}
	
	public String getThumbUrl() {
		return thumbUrl;
		
	}
	public String toString() {
		return this.thumbUrl;
		
	}

	public static ArrayList<ImageResult> fromJSONArray(
			JSONArray array) {
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		for (int x= 0; x < array.length(); x++) {
			try {
				results.add(new ImageResult(array.getJSONObject(x)));
			}catch (JSONException e) {
				e.printStackTrace();
				
			}
		}
		return results;
			
	}

}
