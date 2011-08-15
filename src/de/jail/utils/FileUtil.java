package de.jail.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

import de.jail.geometry.schemas.Point;

/**
 * Util class for different file based operation like import of data as
 * well as export.
 * 
 * @author Christian Vogel
 */
public class FileUtil {
	
	/**
	 * Imports CSV based data as a collection of points. Header information will be ignored.
	 * 
	 * @param filename complete file path + filename
	 * @param seperator identifies the character which separates each dimension 
	 * @param containsHeader identifies whether the file contains header information or not ({@code true} means first line should be ignored)
	 * @return a collection of imported points
	 * @throws IOException thrown if reading file or data is unsuccessfully
	 * @throws NumberFormatException thrown if in the process of parsing string based data to double is unsuccessfully
	 */
	public static Collection<Point> importPointsFromCsv(final String filename, final String seperator, final boolean containsHeader) 
		throws IOException, NumberFormatException {
		if(filename == null) {
			throw new IllegalArgumentException("argument must not be null");
		}
		
		if(filename.equals("")) {
			throw new IllegalArgumentException("empty string");
		}
		
		File f = new File(filename);
		
		if(!f.exists()) {
			throw new FileNotFoundException("data file not found");
		}
		
		if(!f.isFile()) {
			// TODO: add exception
			return null;
		}

		FileInputStream fstream = new FileInputStream(f);

		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		String strLine;
		
		boolean ignoreFirstLine = containsHeader;
		
		Collection<Point> points = new ArrayList<Point>();

		while ((strLine = br.readLine()) != null)   {
			String[] columns = strLine.split(seperator);
			double[] vector = new double[columns.length];
			
			if(ignoreFirstLine) {
				ignoreFirstLine = false;
			} else {
				for(int i = 0; i < columns.length; i++) {
					vector[i] = Double.parseDouble(columns[i]);
				}
			}
			
			points.add(new Point(vector));
		}
		
		return points;
	}
}
