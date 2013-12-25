package com.project.map;

import java.io.File;
import java.util.List;

import org.mapsforge.android.maps.MapActivity;
import org.mapsforge.android.maps.MapView;
import org.mapsforge.android.maps.overlay.Overlay;
import org.mapsforge.core.GeoPoint;
import org.mapsforge.map.reader.header.FileOpenResult;

import com.project.parser.TestParsing;
import com.project.wisigoth.R;

import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

public class MyMapActivity extends MapActivity implements LocationListener {

	private static final File MAP_FILE = new File(Environment
			.getExternalStorageDirectory().getPath(), "grenoble.mp3");
	public static Point maPosition = new Point(new GeoPoint(0, 0),
			"Ma position", "");
	private List<Poi> listePoi;
	private MapView mapView;
	private LocationManager locationManager;
	@SuppressWarnings("unused")
	private String provider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		TestParsing t = new TestParsing();
		listePoi = t.test(this);
		super.onCreate(savedInstanceState);
		mapView = new MapView(this);
		mapView.setClickable(true);
		mapView.setBuiltInZoomControls(true);
		mapView.getController().setZoom(16);
		FileOpenResult fileOpenResult = mapView.setMapFile(MAP_FILE);
		if (!fileOpenResult.isSuccess()) {
			Toast.makeText(this, fileOpenResult.getErrorMessage(),
					Toast.LENGTH_LONG).show();
			finish();
		}
		setContentView(mapView);

		/** PROCESS for Get Longitude and Latitude **/
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		// Define a listener that responds to location updates

		// getting GPS status
		boolean isGPSEnabled = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);

		// check if GPS enabled
		if (isGPSEnabled) {
			Location location = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (location != null) {
				try {
					maPosition.setPoint(new GeoPoint((int) (location
							.getLatitude() * 1E6), (int) (location
							.getLongitude() * 1E6)));
					provider = LocationManager.GPS_PROVIDER;
				} catch (Exception e) {

				}

			} else {
				Criteria criteria = new Criteria();
				criteria.setAccuracy(Criteria.ACCURACY_FINE);
				criteria.setCostAllowed(true);
				provider = locationManager.getBestProvider(criteria, true);
				location = locationManager
						.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

				if (location != null) {
					try {
						maPosition.setPoint(new GeoPoint((int) (location
								.getLatitude() * 1E6), (int) (location
								.getLongitude() * 1E6)));
						provider = LocationManager.NETWORK_PROVIDER;
					} catch (Exception e) {

					}
				}
			}
		}

		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawableMaPos = this.getResources().getDrawable(
				R.drawable.my_position);
		Drawable drawableMarker = this.getResources().getDrawable(
				R.drawable.marker);
		MyItemizedOverlay itemizedoverlayPoi = new MyItemizedOverlay(
				drawableMarker, this);
		MyItemizedOverlay itemizedoverlayMaPosition = new MyItemizedOverlay(
				drawableMaPos, this);
		for (Poi p : listePoi)
			itemizedoverlayPoi.addOverlay(p);
		itemizedoverlayMaPosition.addOverlay(maPosition);
		
		mapOverlays.add(itemizedoverlayMaPosition);
		mapOverlays.add(itemizedoverlayPoi);
	}

	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				10000, 20, this);
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 10000, 20, this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		Toast.makeText(this, "PAUSE", Toast.LENGTH_SHORT).show();
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "DEBUT", Toast.LENGTH_SHORT).show();
		maPosition.setPoint(new GeoPoint(arg0.getLatitude(), arg0
				.getLongitude()));
		mapView.redrawTiles();

	}

	public void onProviderDisabled(String provider) {
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 10000, 20, this);

		} else {
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 10000, 20, this);
		}
	}

	@Override
	public void onProviderEnabled(String provider) {
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 10000, 20, this);
			Toast.makeText(this, "Enclenchement du " + provider,
					Toast.LENGTH_SHORT).show();
		} else {
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 10000, 20, this);
			Toast.makeText(this, "Enclenchement du " + provider,
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
	}

}