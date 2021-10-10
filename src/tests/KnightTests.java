package tests;

import chess.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class KnightTests {
	
	@Test
	public void KnightValidMove1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(true, 1, 0, 2, 2), true);
	}
	
	@Test
	public void KnightValidMove2() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 2);
		currGame.move(false, 4, 6, 4, 5);
		assertEquals(currGame.move(true, 6, 0, 4, 1), true);
	}
	
	@Test
	public void KnightValidCapture1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 3);
		currGame.move(false, 4, 6, 4, 4);
		currGame.move(true, 6, 0, 5, 2);
		currGame.move(false, 6, 7, 5, 5);
		assertEquals(currGame.move(true, 5, 2, 4, 4), true);
	}
	
	@Test
	public void KnightInValidMove1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(true, 1, 0, 1, 2), false);
	}
	
	@Test
	public void KnightInValidMove2() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(true, 1, 0, 3, 1), false);
	}

}
