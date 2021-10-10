package tests;

import chess.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class RookTests {

	@Test
	public void RookValidMove1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 0, 1, 0, 3);
		currGame.move(false, 4, 6, 4, 4);
		assertEquals(currGame.move(true, 0, 0, 0, 2), true);
	}
	
	@Test
	public void RookValidMove2() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 0, 1, 0, 3);
		currGame.move(false, 4, 6, 4, 4);
		currGame.move(true, 0, 0, 0, 2);
		currGame.move(false, 4, 4, 4, 3);
		assertEquals(currGame.move(true, 0, 2, 4, 2), true);
	}
	
	@Test
	public void RookValidCapture1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 0, 1, 0, 3);
		currGame.move(false, 4, 6, 4, 4);
		currGame.move(true, 0, 0, 0, 2);
		currGame.move(false, 4, 4, 4, 3);
		currGame.move(true, 0, 2, 4, 2);
		currGame.move(false, 6, 7, 5, 5);
		assertEquals(currGame.move(true, 4, 2, 4, 3), true);
	}
	
	@Test
	public void RookInValidMove1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(true, 0, 0, 0, 4), false);
	}
	
	@Test
	public void RookInValidMove2() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 1, 1, 1, 3);
		currGame.move(false, 4, 6, 4, 4);
		assertEquals(currGame.move(true, 0, 0, 2, 2), false);
	}
	
}
