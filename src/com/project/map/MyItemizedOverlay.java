package com.project.map;

import java.util.ArrayList;

import org.mapsforge.android.maps.overlay.ItemizedOverlay;
import org.mapsforge.android.maps.overlay.OverlayItem;


import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	Context mContext;

	public MyItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	public MyItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	@Override
	protected boolean onTap(int index) {
	  OverlayItem item =  mOverlays.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage("Lat,Lon : " + item.getPoint().getLatitude() + " " + item.getPoint().getLongitude() + 
			  "\nMa position : Lat,Lon : " +  MyMapActivity.maPosition.getPoint().getLatitude() + " "+ MyMapActivity.maPosition.getPoint().getLongitude() 
			  +"\nDistance : " + ((Point) item).distanceTo(MyMapActivity.maPosition) + "mètres");
	  
	  dialog.show();
	  return true;
	}

}