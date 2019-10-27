package com.hit.services;

import java.util.HashMap;

import com.hit.exception.UnknownIdException;
//TODO import com.hit.exception.UnknownIdException;
import com.hit.gameAlgo.*;
import com.hit.gameHandler.*;
import come.hit.games.CatchTheBunnyRandom;
import come.hit.games.CatchTheBunnySmart;
import come.hit.games.TicTacTowRandom;
import come.hit.games.TicTacTowSmart;

public class GamesService {
	
	private static int id = 1; 
	private int capacity;
	private int numGamesRun;
	private HashMap<Integer ,BoardGameHandler> hashGames;
	
	public GamesService(int capacity) {
		this.capacity = capacity;
		this.numGamesRun = 0;
		this.hashGames = new HashMap<>();
		
	}
	
	public int newGame(String gameType, String opponent) {
	  if(numGamesRun < capacity)
	  {
		if(gameType.equals("TicTacTow"))
		{
			if(opponent.equals("Random")) {
				hashGames.put(id, new BoardGameHandler(new TicTacTowRandom()));
				numGamesRun ++;
				id ++;
				return id-1;
			}
			else if(opponent.equals("Smart"))
			{
				hashGames.put(id, new BoardGameHandler(new TicTacTowSmart()));
				numGamesRun ++;
				id ++;
				return id-1;
			}
		}
		
		//TODO Match the number of turns
		else if(gameType.equals("CatchTheBunny"))
		{
			if(opponent.equals("Random")) {
				hashGames.put(id, new BoardGameHandler(new CatchTheBunnyRandom(15)));
				numGamesRun ++;
				id ++;
				return id-1;
			}
			else if(opponent.equals("Smart"))
			{
				hashGames.put(id, new BoardGameHandler(new CatchTheBunnySmart(15)));
				numGamesRun ++;
				id ++;
				return id-1;
			}
		}
		return -1; //false command 
	  }
	  else return -1; //full capacity
	}
	//TODO Catch exception
	public String updateMove(Integer gameId, String playerMove) {
		
		BoardGameHandler game = hashGames.get(gameId);
		if(game != null) {
			String move = game.playOneRound(playerMove);
			return move;
		}
		return "ILLEGAL_PLAYER_MOVE";
	}
	
	public char[][] getBoardState(Integer gameId) {
		 
		if(hashGames.get(gameId) != null)
			return hashGames.get(gameId).getBoardState();
		return null;
	}
	


	
	public void endGame(java.lang.Integer gameId) {
		hashGames.remove(gameId);
		numGamesRun = hashGames.size(); 
	}
}
