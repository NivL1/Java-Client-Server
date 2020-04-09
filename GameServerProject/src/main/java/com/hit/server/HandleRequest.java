package com.hit.server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import org.json.simple.*;
import com.hit.exception.UnknownIdException;
import com.hit.services.GameServerController;

public class HandleRequest implements Runnable {
	private Socket socket;
    private GameServerController controller;
    
	public HandleRequest(Socket socket, GameServerController controller) {
		this.socket = socket;
		this.controller = controller; 
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void run() {	
		try {			
		ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
		ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
		char[][] board;
		String respMove;
		int gameId;

		JSONObject jsonIn = new JSONObject();
		JSONObject jsonOut = new JSONObject();
		jsonIn = (JSONObject) reader.readObject();
		
		JSONArray ja = new JSONArray() ;

		System.out.println(jsonIn.toString());
	
        
	        switch ((String)jsonIn.get("type"))
	    {
	        case "New-Game":
	        {
	           switch((String)jsonIn.get("game"))
	           {
	               case "Tic Tac Tow":
	                {  
	            	   switch ((String)jsonIn.get("opponent"))
	            	   {
	            	       case "Random":
	            	      {
	            	    	  gameId =  controller.newGame("TicTacTow", "Random");
	            	    	  if(gameId!=-1) {
	            	    		board = controller.getBoardState(gameId);
	            	    		ja = writeBoardToJA(board,3,3);
	            	    		jsonOut.put("board", ja);
	              	    	    jsonOut.put("ID", gameId);
	              	    	    jsonOut.put("type", "New-Game");	              	  
	              	    	    writer.writeObject(jsonOut);     	    	    
	              	    	  }
	            	    	  else {       	    		  
	            	    		jsonOut.put("ID", -1);
	            	    		jsonOut.put("type", "New-Game");
	            	    		writer.writeObject(jsonOut);
	            	    	  }
	            	    	  break;
	            	      }
	            	       case "Smart":
	            	      {
	            	    	  gameId = controller.newGame("TicTacTow", "Smart");
	            	    	  if(gameId!=-1) {
	            	    		board = controller.getBoardState(gameId);
		            	    	ja = writeBoardToJA(board,3,3);
		            	    	jsonOut.put("board", ja);
	            	    	    jsonOut.put("ID", gameId);
	            	    	    jsonOut.put("type", "New-Game");
	            	    	    writer.writeObject(jsonOut);
	            	    	  }
	            	    	  else {       	    		  
	            	    		jsonOut.put("ID", -1);
		            	    	jsonOut.put("type", "New-Game");
		            	    	writer.writeObject(jsonOut);
	              	    	  }
	            	    	  break;   
	            	      }               	                     	                         	   
	            	   }
	            	   break;
	                }
	               case "Catch The Bunny":
	               {
	            	   switch ((String)jsonIn.get("opponent"))
	            	   {
	            	      case "Random":
	         	         {
	            	          gameId = controller.newGame("CatchTheBunny", "Random");
	             	          if(gameId!=-1) {
	             	        	board = controller.getBoardState(gameId);
	             	        	ja = writeBoardToJA(board,9,9);
	             	        	jsonOut.put("board", ja);
	            	    	    jsonOut.put("ID", gameId);
	            	    	    jsonOut.put("type", "New-Game");
	            	    	    writer.writeObject(jsonOut);
	             	          }
	             	         else {       	    		  
	             	        	jsonOut.put("ID", -1);
	            	    		jsonOut.put("type", "New-Game");
	            	    		writer.writeObject(jsonOut);
	             	    	  }
	             	    	  break; 	          
	         	         }
	         	          case "Smart":
	         	         {
	        	        	  gameId = controller.newGame("CatchTheBunny", "Smart");
	            	          if(gameId!=-1) {
	            	        	board = controller.getBoardState(gameId);
		             	        ja = writeBoardToJA(board,9,9);
		             	        jsonOut.put("board", ja);
		             	        jsonOut.put("ID", gameId);
		             	        jsonOut.put("type", "New-Game");
		             	        writer.writeObject(jsonOut);
	            	          }
	            	          else {       	    		  
	            	         	jsonOut.put("ID", -1);
	            	        	jsonOut.put("type", "New-Game");
		            	    	writer.writeObject(jsonOut);
	              	    	  }
	            	    	  break;       	    	  
	         	         }
	             	   }    
	            	   break;                      
	               }                      
	           }
        	   break;
	        }
	        case "Update-Move":
	        {
	        	gameId = Integer.valueOf(jsonIn.get("ID").toString());
	        	respMove = controller.updateMove(gameId, (String)jsonIn.get("move"));                	                	  	
	        	board = controller.getBoardState(gameId);
	        	if(board != null) {
	     	        ja = writeBoardToJA(board,board.length,board[0].length);
	     	        jsonOut.put("board", ja);
	        	} else {
	        		jsonOut.put("board", null);
	        	}
	        	jsonOut.put("ID", gameId);
    	    	jsonOut.put("type", "Update-Move"); 
	        	switch(respMove) 
	         	{
	        	    case "ILLEGAL_PLAYER_MOVE" :{
	        	    	jsonOut.put("board", null);
	        	    	jsonOut.put("state", 0);      
	        	    	break;
	        	    }
	         	    case "PLAYER WIN":
         	    		jsonOut.put("state", 1);
         	    		controller.endGame(gameId);
         	    		break;
	         	    case "PLAYER LOSE":
         	    		jsonOut.put("state", 2);
         	    		controller.endGame(gameId);
	         	     	break;
	         	    case "TIE":
	         	    	jsonOut.put("state", 3);
         	    		controller.endGame(gameId);
	         	    	break;
	           	    case "IN PROGRESS":
	           	     jsonOut.put("state", 4);
	            	   break;
	         	}           	    	
    	    	writer.writeObject(jsonOut);
	         	break;                       	
	        }
	        case "Start-Game":
	        {
	          //TODO if computer starts only 
	        	gameId = Integer.valueOf(jsonIn.get("ID").toString());
	        	jsonOut.put("type", "Start-Game");
	        	jsonOut.put("ID", gameId);
	        	controller.updateMove(gameId, "");
	        	board = controller.getBoardState(gameId);
	        	ja = writeBoardToJA(board,board.length,board[0].length);
     	        jsonOut.put("board", ja);
     	       writer.writeObject(jsonOut);
	        	break;
	        }
	        case "Stop-Game":
	        {
	        	gameId = Integer.valueOf(jsonIn.get("ID").toString());
	        	controller.endGame(gameId);
	            break;
	        }
	    }
			
		
		
		}  catch (UnsupportedEncodingException e) {  
			e.printStackTrace();
		}  catch (IOException e1) {
		    e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		@SuppressWarnings("unchecked")
		public JSONArray writeBoardToJA(char[][] board,int raw, int col) {
			JSONArray ja = new JSONArray() ;		
			for(int i=0; i<raw; i++)	
				for(int j =0; j<col; j++)
					ja.add(board[i][j]);
			return ja;
			}
			
			
		
}
