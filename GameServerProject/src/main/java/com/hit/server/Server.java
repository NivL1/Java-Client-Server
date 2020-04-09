package com.hit.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.net.SocketException;

import com.hit.server.HandleRequest;
import com.hit.services.*;

public class Server implements PropertyChangeListener, Runnable {
	
    private boolean state;
    private HandleRequest requesterHandler;
	private ServerSocket sSocket;
	private int port;
	private int numConfig = 3; 
	Executor executor;
	
	public Server (int port) {
		this.port = port;
		state=false;
		 executor=null;//because its going to be inside a try later 
	}

	@Override
	public void run() {
	try
	{
         executor = Executors.newFixedThreadPool(numConfig);
        
         GameServerController controller = new GameServerController(numConfig);
        if(state) System.out.println("Server has been started");
       
            sSocket = new ServerSocket(port); 

            while (state) 
           //   if(sSocket.isClosed() == false)
                { 
                Socket socket = sSocket.accept();  
                requesterHandler = new HandleRequest(socket, controller);
                executor.execute(requesterHandler);  
                }
	}
	catch(SocketException  e) { }
	catch (IOException e) {
	e.printStackTrace();
	}
	finally
	{
		try
		{
		sSocket.close();
		}
		catch (IOException e) {
		e.printStackTrace();}
	}
	
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("START")) {
			System.out.println("> Running Server...");
			if(state == false) {
				state = true;
		 	    new Thread(this).start();
			}
			else System.out.println("Server is already running");
		}
		
		 else if (evt.getPropertyName().equals("CONFIG"))
		 {
			numConfig = (Integer)evt.getNewValue();
			state=false;
		 }
		  else if( evt.getPropertyName().equals("SHUTDOWN"))
		    {
			  if(state == false)
                  System.out.println("Server is already off\n");
              else
              {
                  state = false;
                  System.out.println("Server turned off");
              }
		   } 
		
	}

}
