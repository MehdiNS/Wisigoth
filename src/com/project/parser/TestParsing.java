package com.project.parser;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.project.map.Poi;

import android.content.Context;

public class TestParsing  {

	public List<Poi> test(Context c) {
		List<Poi> listePoi = null;
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			Parser handler = new Parser();
			saxParser.parse(c.getAssets().open("grenoble.xml"), handler);
			listePoi = handler.getListePoi();
			for (Poi poi : listePoi)
				System.out.println(poi);
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e2) {
			e2.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		return listePoi;

	}

}