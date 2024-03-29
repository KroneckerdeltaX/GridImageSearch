package com.codepath.gridimagesearch;

import java.util.List;

import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {
	public ImageResultArrayAdapter(Context context, List<ImageResult> images) {
		super(context, R.layout.item_image_result, images);
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) { //translate
		ImageResult imageInfo = this.getItem(position); //get that data
		SmartImageView ivImage; //create a field for smart image view
		if(convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			ivImage = (SmartImageView) inflator.inflate( R.layout.item_image_result, parent, false);
					
		} else {
			ivImage = (SmartImageView) convertView;
			ivImage.setImageResource(android.R.color.transparent);
			}
		ivImage.setImageUrl(imageInfo.getThumbUrl());
		return ivImage; //converting from a data source and translating to a view..
		
		
	}

}
