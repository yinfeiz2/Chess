package tests;

import chess.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class KingTests {

	@Test
	public void KingValidMove1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 3);
		currGame.move(false, 4, 6, 4, 4);
		assertEquals(currGame.move(true, 4, 0, 4, 1), true);
	}
	
	@Test
	public void KingValidMove2() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 3, 1, 3, 3);
		currGame.move(false, 4, 6, 4, 4);
		assertEquals(currGame.move(true, 4, 0, 3, 1), true);
	}
	
	@Test
	public void KingInValidMove1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(true, 4, 0, 4, 1), false);
	}
	
	@Test
	public void KingInValidMove2() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 3);
		currGame.move(false, 4, 6, 4, 4);
		assertEquals(currGame.move(true, 4, 0, 4, 2), false);
	}
	
	@Test
	public void KingValidCapture1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 0, 1, 0, 2);
		currGame.move(false, 6, 7, 5, 5);
		currGame.move(true, 0, 2, 0, 3);
		currGame.move(false, 5, 5, 6, 3);
		currGame.move(true, 0, 3, 0, 4);
		currGame.move(false, 6, 3, 5, 1);
		assertEquals(currGame.move(true, 4, 0, 5, 1), true);
	}
	
}
