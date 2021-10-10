package tests;

import chess.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueenTests {
	
	@Test
	public void QueenValidMove1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 3);
		currGame.move(false, 4, 6, 4, 4);
		assertEquals(currGame.move(true, 3, 0, 5, 2), true);
	}
	
	@Test
	public void QueenValidMove2() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 3);
		currGame.move(false, 4, 6, 4, 4);
		currGame.move(true, 3, 0, 5, 2);
		currGame.move(false, 5, 6, 5, 5);
		assertEquals(currGame.move(true, 5, 2, 7, 2), true);
	}
	
	@Test
	public void QueenValidCapture1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 3);
		currGame.move(false, 4, 6, 4, 4);
		currGame.move(true, 3, 0, 5, 2);
		currGame.move(false, 5, 6, 5, 5);
		currGame.move(true, 5, 2, 7, 2);
		currGame.move(false, 1, 7, 2, 5);
		assertEquals(currGame.move(true, 7, 2, 7, 6), true);
	}
	
	@Test
	public void QueenValidCapture2() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 2);
		currGame.move(false, 4, 6, 4, 4);
		currGame.move(true, 3, 0, 5, 2);
		currGame.move(false, 1, 7, 2, 5);
		assertEquals(currGame.move(true, 5, 2, 2, 5), true);
	}
	
	@Test
	public void QueenInValidMove1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(true, 4, 1, 5, 3), false);
	}
	
	@Test
	public void QueenInValidMove2() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(true, 4, 1, 6, 3), false);
	}
	
}
