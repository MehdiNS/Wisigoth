package com.project.parser;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.mapsforge.core.GeoPoint;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.project.map.Poi;


public class Parser extends DefaultHandler {

	private List<Poi> listePoi = new ArrayList<Poi>();
	private double tempLon;
	private double tempLat;

	public List<Poi> getListePoi() {
		return listePoi;
	}

	public void setListePoi(List<Poi> listePoi) {
		this.listePoi = listePoi;
	}

	private Poi poi;

	public Parser parsing(String file) throws Exception {
		XMLReader xr = XMLReaderFactory.createXMLReader();
		Parser handler = new Parser();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);

		// Parse each file provided on the
		// command line.
		FileReader r = new FileReader(file);
		xr.parse(new InputSource(r));
		return handler;
	}

	public Parser() {
		super();
	}

	// //////////////////////////////////////////////////////////////////
	// Event handlers.
	// //////////////////////////////////////////////////////////////////

	public void startDocument() {
		System.out.println("Start document");
	}

	public void endDocument() {
		System.out.println("End document");
	}

	public void startElement(String uri, String name, String qName,
			Attributes atts) throws SAXException {
		// Si balise node
		if (qName.equals("node")) {
			for (int index = 0; index < atts.getLength(); index++) {
				if (atts.getLocalName(index) == "id") {
					poi = new Poi();
					poi.setId(Integer.parseInt(atts.getValue(index)));
				} else if (atts.getLocalName(index) == "name") {
					poi.setTitle(atts.getValue(index));
				} else if (atts.getLocalName(index) == "lat") {
					tempLat = Double.parseDouble(atts.getValue(index));
				} else if (atts.getLocalName(index) == "lon") {
					tempLon = Double.parseDouble(atts.getValue(index));
				}

			}
		}
		// Sinon si balise tag
		else if (qName.equals("tag")) {
			if (atts.getValue(0).equals("text"))
				poi.setSnippet(atts.getValue(1));
			else if (atts.getValue(0).equals("audio"))
				poi.setAudio(atts.getValue(1));
			else if (atts.getValue(0).equals("local"))
				poi.setFileHtml(atts.getValue(1));
			else if (atts.getValue(0).equals("extern"))
				poi.setExternURL(atts.getValue(1));
			else if (atts.getValue(0).equals("triggering"))
				poi.setTriggering(Integer.parseInt(atts.getValue(1)));

		}
	}

	public void endElement(String uri, String name, String qName)
			throws SAXException {
		if (qName.equals("node")) {

			poi.setPoint(new GeoPoint(tempLat, tempLon));
			listePoi.add(poi);
		}
	}
}