package com.project.map;


import org.mapsforge.android.maps.overlay.OverlayItem;
import org.mapsforge.core.GeoPoint;

import android.graphics.drawable.Drawable;


public class Point extends OverlayItem{
	
	

	public Point() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Point(GeoPoint point, String title, String snippet,
			Drawable marker) {
		super(point, title, snippet, marker);
		// TODO Auto-generated constructor stub
	}

	public Point(GeoPoint point, String title, String snippet) {
		super(point, title, snippet);
		// TODO Auto-generated constructor stub
	}

	public float distanceTo(OverlayItem o){
		return 0;
	}

	@Override
	public synchronized Drawable getMarker() {
		// TODO Auto-generated method stub
		return super.getMarker();
	}

	@Override
	public synchronized GeoPoint getPoint() {
		// TODO Auto-generated method stub
		return super.getPoint();
	}

	@Override
	public synchronized String getSnippet() {
		// TODO Auto-generated method stub
		return super.getSnippet();
	}

	@Override
	public synchronized String getTitle() {
		// TODO Auto-generated method stub
		return super.getTitle();
	}

	@Override
	public synchronized void setMarker(Drawable marker) {
		// TODO Auto-generated method stub
		super.setMarker(marker);
	}

	@Override
	public synchronized void setPoint(GeoPoint point) {
		// TODO Auto-generated method stub
		super.setPoint(point);
	}

	@Override
	public synchronized void setSnippet(String snippet) {
		// TODO Auto-generated method stub
		super.setSnippet(snippet);
	}

	@Override
	public synchronized void setTitle(String title) {
		// TODO Auto-generated method stub
		super.setTitle(title);
	}
}