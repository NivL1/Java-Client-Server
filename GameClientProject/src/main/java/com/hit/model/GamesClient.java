package com.hit.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GamesClient {

	private int serverPort;
	private Socket socket;
	
	
	public GamesClient(int serverPort) throws UnknownHostException, IOException {
		this.serverPort = serverPort;
		
	}
	
	public JSONObject sendMessage(String message, boolean hasResponse) { 
		
		JSONParser parser;
		JSONObject jsonOut;
		ObjectOutputStream writer;
		
		try {
			socket = new Socket("localhost", serverPort);
			writer = new ObjectOutputStream(socket.getOutputStream());
			parser = new JSONParser();
			jsonOut = (JSONObject) parser.parse(message);
			writer.writeObject(jsonOut);
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		JSONObject jsonIn = null;
		
		if(hasResponse) {
			try {
				ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
				jsonIn = (JSONObject) reader.readObject();
				System.out.println(jsonIn.toJSONString());
				return jsonIn;
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		return new JSONObject();
	
	}
	
	
	
	
	public void connectToServer() {
		

		try {
			ServerSocket sSocket = new ServerSocket(serverPort);
			 
	        socket = sSocket.accept();    
	     
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void closeConnection() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
