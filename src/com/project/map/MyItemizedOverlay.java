package com.project.map;

import java.util.ArrayList;

import org.mapsforge.android.maps.overlay.ItemizedOverlay;
import org.mapsforge.android.maps.overlay.OverlayItem;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

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
			  +"\nDistance : " + ((Point) item).distanceTo(MyMapActivity.maPosition) + "m√®tres");

	  dialog.show();*/

		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage("Coordonnées : (" + item.getPoint().getLatitude() + ", " + item.getPoint().getLongitude() + 
				")\nInformations : \n\n"+item.getSnippet());
		dialog.setPositiveButton("Retourner à la carte", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
			}
		});
		dialog.setNegativeButton("Lien wikipedia", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Intent intent = new Intent(mContext,WebviewActivity.class);
				Bundle b = new Bundle();

				b.putString("url", ((Poi)item).getExternURL());
				intent.putExtras(b);
				mContext.startActivity(intent);
			}
		});
		dialog.setNeutralButton("Plus d'informations", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Intent intent = new Intent(mContext,WebviewActivity.class);
				Bundle b = new Bundle();

				b.putString("url", ((Poi)item).getFileHtml());
				intent.putExtras(b);
				mContext.startActivity(intent);
			}
		});
		
		int idImage = mContext.getResources().getIdentifier(((Poi)item).getImage(), "drawable", mContext.getPackageName());
		Drawable d = mContext.getResources().getDrawable(idImage);
		Bitmap b = ((BitmapDrawable)d).getBitmap();
	    Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 250, 250, false);
	    d = new BitmapDrawable(mContext.getResources(),bitmapResized);
		dialog.setIcon(d);
		
		dialog.show();

		return true;
	}

}