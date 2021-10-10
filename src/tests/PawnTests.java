package tests;

import chess.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class PawnTests {
	
	@Test
	public void PawnValidForwardMove() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(true, 4, 1, 4, 2), true);
	}
	
	@Test
	public void PawnValidFirstMove() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(true, 4, 1, 4, 3), true);
	}
	
	@Test
	public void PawnInValidMove1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(true, 4, 1, 4, 4), false);
	}
	
	@Test
	public void PawnInValidMove2() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(true, 4, 1, 3, 2), false);
	}
	
	@Test
	public void PawnInValidMove3() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 0, 1, 0, 3);
		currGame.move(false, 0, 6, 0, 4);
		assertEquals(currGame.move(true, 0, 3, 0, 4), false);
	}
	
	@Test
	public void PawnValidCapture() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 3);
		currGame.move(false, 3, 6, 3, 4);
		assertEquals(currGame.move(true, 4, 3, 3, 4), true);
	}

}
