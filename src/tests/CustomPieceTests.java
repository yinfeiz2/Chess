package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import chess.*;

public class CustomPieceTests {

	@Test
	public void GoldValidMove() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startCustomGame();
		assertEquals(currGame.move(true, 0, 1, 0, 2), true);
	}
	
	@Test
	public void GoldInValidMove() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startCustomGame();
		currGame.move(true, 0, 1, 1, 2);
		currGame.move(false, 4, 6, 4, 5);
		assertEquals(currGame.move(true, 1, 2, 0, 1), false);
	}
	
	@Test
	public void LanceValidMove() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startCustomGame();
		assertEquals(currGame.move(true, 7, 1, 7, 4), true);
	}
	
	@Test
	public void LanceInValidMove() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startCustomGame();
		currGame.move(true, 7, 1, 7, 4);
		currGame.move(false, 4, 6, 4, 5);
		assertEquals(currGame.move(true, 7, 4, 7, 2), false);
	}
	
}
