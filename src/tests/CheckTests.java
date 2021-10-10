package tests;

import chess.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class CheckTests {

	@Test
	public void InValidMoveUnderCheck1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 5, 1, 5, 2);
		currGame.move(false, 4, 6, 4, 5);
		currGame.move(true, 0, 1, 0, 2);
		currGame.move(false, 3, 7, 7, 3);
		assertEquals(currGame.move(true, 1, 0, 2, 2), false);
	}
	
	@Test
	public void ValidMoveUnderCheck1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 5, 1, 5, 2);
		currGame.move(false, 4, 6, 4, 5);
		currGame.move(true, 0, 1, 0, 2);
		currGame.move(false, 3, 7, 7, 3);
		assertEquals(currGame.move(true, 6, 1, 6, 2), true);
	}
	
	@Test
	public void InValidMoveBecomeChecked1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 3);
		currGame.move(false, 4, 6, 4, 4);
		currGame.move(true, 0, 1, 0, 2);
		currGame.move(false, 3, 7, 7, 3);
		assertEquals(currGame.move(true, 5, 1, 5, 2), false);
	}
	
	@Test
	public void InValidMoveBecomeChecked2() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 3);
		currGame.move(false, 6, 7, 5, 5);
		currGame.move(true, 5, 1, 5, 2);
		currGame.move(false, 5, 5, 6, 3);
		assertEquals(currGame.move(true, 4, 0, 5, 1), false);
	}
	
}
