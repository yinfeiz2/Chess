package tests;

import chess.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class BishopTests {

	@Test
	public void BishopValidMove1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 3);
		currGame.move(false, 4, 6, 4, 4);
		assertEquals(currGame.move(true, 5, 0, 1, 4), true);
	}

	@Test
	public void BishopValidMove2() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 3);
		currGame.move(false, 4, 6, 4, 4);
		currGame.move(true, 5, 0, 1, 4);
		currGame.move(false, 2, 6, 2, 5);
		assertEquals(currGame.move(true, 1, 4, 0, 3), true);
	}
	
	@Test
	public void BishopInValidMove1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(true, 2, 0, 6, 4), false);
	}
	
	@Test
	public void BishopInValidMove2() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 3);
		currGame.move(false, 4, 6, 4, 4);
		assertEquals(currGame.move(true, 5, 0, 3, 3), false);
	}
	
	@Test
	public void BishopValidCapture1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 3);
		currGame.move(false, 4, 6, 4, 4);
		currGame.move(true, 5, 0, 1, 4);
		currGame.move(false, 2, 6, 2, 5);
		assertEquals(currGame.move(true, 1, 4, 2, 5), true);
	}
	
}
