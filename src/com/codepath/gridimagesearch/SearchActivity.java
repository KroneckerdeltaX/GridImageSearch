package com.codepath.gridimagesearch;

//import android.support.v7.app.ActionBar;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;



public class SearchActivity extends Activity {
	EditText etQuery;
	GridView gvResults;
	Button btnSearch;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>(); // now do a simple async request
	ImageResultArrayAdapter imageAdapter;
	String imsize = "";
	String imcolor = "";
	String imtype = "";
	String sitefilter = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupViews();
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position,
					long arg3) {
			Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
			ImageResult imageResult = imageResults.get(position);
			//i.putExtra("url", imageResult.getFullUrl());
			i.putExtra("result", imageResult);
			startActivity(i);
				}
		});
		gvResults.setOnScrollListener(new EndlessScrollListener() {
	        @Override
	        public void onLoadMore(int page, int totalItemsCount) {
	        	
	        	Log.d("DEBUG", "imhere");
	                // Triggered only when new data needs to be appended to the list
	                // Add whatever code is needed to append new items to your AdapterView
	            customLoadMoreDataFromApi(page); 
	            
	                // or customLoadMoreDataFromApi(totalItemsCount); 
	        }
	        });
	    }
	
	public void customLoadMoreDataFromApi(int offset) {
		String httpreqtext;
		String query = etQuery.getText().toString();
		if (imsize == "") {
			httpreqtext="https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" + 
					"start=" + String.valueOf(offset) + "&v=1.0&q=" + Uri.encode(query);
		} else {
			httpreqtext="https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" + 
				"imgsz=" + imsize + "&imgcolor=" + imcolor + "&imgtype=" + imtype +
				"&start=" + String.valueOf(offset) + "&v=1.0&q=" + Uri.encode(query + " site=" + sitefilter);
		}
		Log.d("DEBUG",httpreqtext);
		AsyncHttpClient client = new AsyncHttpClient();
		gvResults.smoothScrollToPosition(gvResults.getFirstVisiblePosition());
		client.get(httpreqtext, 
				new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
					JSONArray imageJsonResults = null;
					try {
						imageJsonResults = response.getJSONObject(
								"responseData").getJSONArray("results");
						//imageResults.clear();
						imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
						//Log.d("DEBUG", imageResults.toString());
					} catch (JSONException e) {
						e.printStackTrace();
					}
			}
		});
	
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	
	
	
	public void setupViews() {
		etQuery = (EditText) findViewById(R.id.etQuery);
		gvResults = (GridView) findViewById(R.id.gvResults);
		btnSearch = (Button) findViewById(R.id.btnSearch);	
	}
	
	
	
	

	
	public void onImageSearch(View v) {
		String query = etQuery.getText().toString();
		Toast.makeText(this, "Searching for " + query, Toast.LENGTH_SHORT)
		.show();
		AsyncHttpClient client = new AsyncHttpClient(); //video
		//https://ajax.googleapis.com/ajax/services/search/images?q=Android&v=1.0
		String httpreqtext;
		
		if (imsize == "") {
			httpreqtext="https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" + 
			"start=" + 0 + "&v=1.0&q=" + Uri.encode(query);

		} else {
			httpreqtext="https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" + 
					"imgsz=" + imsize + "&imgcolor=" + imcolor + "&imgtype=" + imtype +
					"&start=" + 0 + "&v=1.0&q=" + Uri.encode(query + " site=" + sitefilter);
		}

		Log.d("DEBUG",httpreqtext);
		
		client.get(httpreqtext, 
				new JsonHttpResponseHandler() {
		@Override
			public void onSuccess(JSONObject response) {
				JSONArray imageJsonResults = null;
				try {
					imageJsonResults = response.getJSONObject(
							"responseData").getJSONArray("results");
					imageResults.clear();
					imageAdapter.addAll(ImageResult
							.fromJSONArray(imageJsonResults));
					Log.d("DEBUG", imageResults.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}


private final int REQUEST_CODE = 20;
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items 
	  Intent i = new Intent ( SearchActivity.this, SecondActivity.class);
	  startActivityForResult(i, REQUEST_CODE);
	  return true;
	  
	           //return super.onOptionsItemSelected(item);
	    }
			
	
	

protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	imsize = data.getExtras().getString("spinval1"); //capture filter data as strings.. for field1, field2, use whatever names I gave this filter data in SecondActivity
	imcolor = data.getExtras().getString("spinval2");
	imtype = data.getExtras().getString("spinval3");
	sitefilter = data.getExtras().getString("et_filter");
	Log.d("DEBUG",data.getExtras().getString("et_filter")); //print field1 value to log, for debug purposes

	}
}
