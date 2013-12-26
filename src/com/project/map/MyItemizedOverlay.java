package com.project.map;

import java.util.ArrayList;
import java.util.List;

import org.mapsforge.android.maps.overlay.ItemizedOverlay;
import org.mapsforge.android.maps.overlay.OverlayItem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

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
		final OverlayItem item =  mOverlays.get(index);
		/*AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage("Lat,Lon : " + item.getPoint().getLatitude() + " " + item.getPoint().getLongitude() + 
			  "\nMa position : Lat,Lon : " +  MyMapActivity.maPosition.getPoint().getLatitude() + " "+ MyMapActivity.maPosition.getPoint().getLongitude() 
			  +"\nDistance : " + ((Point) item).distanceTo(MyMapActivity.maPosition) + "mètres");

	  dialog.show();*/

		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage("Lat,Lon : " + item.getPoint().getLatitude() + " " + item.getPoint().getLongitude() + 
				"\n Informations : MERCI MEHDI de pas avoir pars� le texte");
		dialog.setPositiveButton("Retourner � la carte", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		dialog.setNegativeButton("Plus d'informations", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Intent intent = new Intent(mContext,WebviewActivity.class);
				Bundle b = new Bundle();

				b.putString("url", ((Poi)item).getFileHtml());
				intent.putExtras(b);
				mContext.startActivity(intent);
			}
		});
		dialog.show();

		return true;
	}

}