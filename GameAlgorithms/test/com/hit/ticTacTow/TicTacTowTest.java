package com.hit.ticTacTow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import come.hit.games.*;

public class TicTacTowTest {
	@Test
	public void randomTest()
	{
		TicTacTow tic = new TicTacTowRandom();	
		assertEquals(tic.getGameState(), 4);
		assertEquals(tic.updatePlayerMove("5"), true);
		tic.calcComputerMove();
		assertEquals(tic.getGameState(), 4);
	}
	
	@Test
	public void smartTest()
	{
		TicTacTow tic = new TicTacTowSmart();	
		assertEquals(tic.getGameState(), 4);
		assertEquals(tic.updatePlayerMove("5"), true);
		tic.calcComputerMove();
		assertEquals(tic.getGameState(), 4);
	}
}
