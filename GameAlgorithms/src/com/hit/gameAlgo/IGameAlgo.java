package com.hit.gameAlgo;

public interface IGameAlgo {

    public int getGameState();
    
    public char[][] getBoardState();

    public boolean updatePlayerMove(String move);

    public void calcComputerMove();
}
