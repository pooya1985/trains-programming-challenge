package com.tw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Hello world!
 *
 */
public class App 
{
	private ResourceBundle properties;
	
	public App() {
		try {
			properties = ResourceBundle.getBundle("com.tw.graph-challenge");
		} catch(MissingResourceException e) {
			System.err.println("Could not find graph-challenge.properties file");
			System.exit(1);
		}
	}
	
	public String getProperty(String name) {
		String s;
		try {
			s = properties.getString(name);
		} catch (MissingResourceException e) {
			System.err.println("WARNING: missing data: " + name);
			return "";
		}
		return s;
	}
	
	private static FileInputStream getFileInputStream(String name) {
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(name);
		} catch (FileNotFoundException e) {
    		System.err.println(name + ": File not found");
    		System.exit(1);
    	}
		return stream;
	}
	
    public static void main( String[] args )
    {
    	App app = new App();
    	
    	if (args.length != 2) {
    		System.err.println(app.getProperty("usage"));
    		System.exit(1);
    	}
    	
		String graphFilename    = args[0];
		String commandsFilename = args[1];
		
    	try {
        	Graph graph = new Graph(getFileInputStream(graphFilename));
    	    Actions actions = new Actions(getFileInputStream(commandsFilename));
    	    
    	    System.out.println(actions.execute(graph));
    	} catch (IOException e) {
    		System.err.println("FATAL: " + e.getMessage());
    		System.exit(1);
    	}
    }
}
