package com.hit.services;


public class GameServerController {
	private int capacity;
	private GamesService gs;
	
	public GameServerController(int capacity) {
		this.capacity = capacity;
		this.gs = new GamesService(capacity);
	}

	public int newGame(String gameType, String opponent) {
		return gs.newGame(gameType, opponent);
	}
	
	public void endGame(Integer gameId) {
		gs.endGame(gameId);
	}
	
	public String updateMove(Integer gameId, String playerMove) {
		return gs.updateMove(gameId, playerMove);
	}
	

	
	public char[][] getBoardState(Integer gameId) {
		return gs.getBoardState(gameId);
	}
	
		
	
	
}
