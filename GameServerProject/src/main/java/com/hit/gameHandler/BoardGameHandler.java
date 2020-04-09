package com.hit.gameHandler;

import com.hit.gameAlgo.*;
import java.util.Scanner;

public class BoardGameHandler {
	
	private IGameAlgo game;
	
	public BoardGameHandler(IGameAlgo game) {
		this.game = game; 
	}
	
	public String playOneRound(String playerMove) {
		if(!playerMove.equals("")) {
			boolean done = game.updatePlayerMove(playerMove);
			if(!done)
				return "ILLEGAL_PLAYER_MOVE";
		}

		int gameState = game.getGameState(); // 1 = WIN , 2 = LOSE , 3 = TIE , 4 = IN PROGRESS
		if(gameState == 4) {
			game.calcComputerMove();
			gameState = game.getGameState();
		}
		if(gameState == 4)
			return "IN PROGRESS";
		else if(gameState == 1)
			return "PLAYER WIN";
		else if(gameState == 2)
			return "PLAYER LOSE";
		else return "TIE";
	}
	
	public char[][] getBoardState() {
		return game.getBoardState();
	}
}
