package com.project.map;

import org.mapsforge.android.maps.overlay.OverlayItem;
import org.mapsforge.core.GeoPoint;

import android.graphics.drawable.Drawable;
import android.location.Location;

public class Poi extends Point{
	
	private int id = 0;
	private int triggering = 999999;
	private String fileHtml = "";
	private String Audio = "";
	
	public Poi (GeoPoint geopoint, String titre, String texte, int i, int trigger, String file) {
		super(geopoint, titre, texte);
		id = i;
		triggering = trigger;
		fileHtml = file;
	}
	
	public Poi() {
		// TODO Auto-generated constructor stub
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTriggering() {
		return triggering;
	}

	public void setTriggering(int triggering) {
		this.triggering = triggering;
	}

	public String getFileHtml() {
		return fileHtml;
	}

	public void setFileHtml(String fileHtml) {
		this.fileHtml = fileHtml;
	}

	public String getAudio() {
		return Audio;
	}

	public void setAudio(String audio) {
		Audio = audio;
	}
	
	public float distanceTo(OverlayItem o){
		Location locationA = new Location("point A");
		locationA.setLatitude(getPoint().getLatitude());
		locationA.setLongitude(getPoint().getLongitude());
		Location locationB = new Location("point B");
		locationB.setLatitude(o.getPoint().getLatitude());
		locationB.setLongitude(o.getPoint().getLongitude());
		return locationA.distanceTo(locationB);
	}

	
}