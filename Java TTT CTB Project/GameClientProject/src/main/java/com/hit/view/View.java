package com.hit.view;

import org.json.simple.JSONArray;

public interface View {
	
	void start();
	void updateViewNewGame(java.lang.Character[] board);
	void updateViewGameMove(int gameState, Character[] board);
	String getGameType();
	String getOpponent();
	String getStartPlayer();
	void Initialize();
	void Started();
	
}
