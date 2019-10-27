package com.hit.util;

import java.io.*;
import java.util.Scanner;
import java.beans.*;

import javax.swing.JFrame;

public class CLI implements Runnable {
	private InputStream in;
	private OutputStream out ;
	private   Scanner reader;
	private String command;
	private PropertyChangeSupport pcs;
	private int numConfig = 3;

	
	public CLI(InputStream in ,OutputStream out) {
		reader = new Scanner (new InputStreamReader(in));
		this.in = in;
		this.out = out;
		this.command = "";
		pcs = new PropertyChangeSupport(this);
	}
	
	@Override 
	public void run() {
		
		String newCommand = " ";
		PrintStream psOut = new PrintStream(out);
		boolean numeric = true;
		int newConfig = numConfig; 

		
		while(!newCommand.equals("SHUTDOWN")) {
			
			psOut.println("> Please enter your command");
			psOut.print("> ");
			newCommand = reader.nextLine();
			if(newCommand.equals("SHUTDOWN")) {
				pcs.firePropertyChange("SHUTDOWN", newCommand , command);
				command = newCommand;
				psOut.println("> Bye...");
			}
			else if(newCommand.equals("START")) {
				//TODO
				pcs.firePropertyChange("START", newCommand , command);
				command = newCommand;
			
			}
			else if( command.startsWith("GAME_SERVER_CONFIG {") && command.endsWith("}") ) {
				  try{
						newConfig = Integer.parseInt(command.substring(20,command.length()-1));	   
				   } catch (NumberFormatException e) {
		                numeric = false;
		                numConfig = -1;
		            }
				   if(numeric) {
					   //TODO ...
					   pcs.firePropertyChange("CONFIG", numConfig , newConfig);
					   numConfig = newConfig;
					   };
				   }
			}
		}
	
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		pcs.removePropertyChangeListener(pcl);
	
	}
	
	//API?
	public void writeResponse(String response) {
		((PrintStream)out).format("> %s", response);
		
	}

}
