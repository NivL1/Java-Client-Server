package com.hit.catchTheBunny;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import come.hit.games.CatchTheBunny;
import come.hit.games.CatchTheBunnyRandom;
import come.hit.games.CatchTheBunnySmart;

public class CatchTheBunnyTest {
	@Test
	public void randomTest()
	{
		CatchTheBunny bunny = new CatchTheBunnyRandom(9);	
		assertEquals(bunny.getGameState(), 4);
		assertEquals(bunny.updatePlayerMove("W"), true);
		bunny.calcComputerMove();
		assertEquals(bunny.getGameState(), 4);
	}
	
	@Test
	public void smartTest()
	{
		CatchTheBunny bunny = new CatchTheBunnySmart(9);	
		assertEquals(bunny.getGameState(), 4);
		assertEquals(bunny.updatePlayerMove("W"), true);
		bunny.calcComputerMove();
		assertEquals(bunny.getGameState(), 4);
	}
}
