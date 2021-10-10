package tests;

import chess.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChessgameTests {

	@Test
	public void InValidTurn1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(false, 4, 6, 4, 4), false);
	}
	
	@Test
	public void NoPieceToMove1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(true, 0, 2, 0, 3), false);
	}
	
	@Test
	public void PieceOwnerError1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(true, 4, 6, 4, 4), false);
	}
	
	@Test
	public void NoMove1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		assertEquals(currGame.move(true, 4, 1, 4, 1), false);
	}
	
	@Test
	public void Checkmate1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 5, 1, 5, 2);
		currGame.move(false, 4, 6, 4, 4);
		currGame.move(true, 6, 1, 6, 3);
		currGame.move(false, 3, 7, 7, 3);
		assertEquals(currGame.move(true, 7, 1, 7, 2), false);
	}
	
	@Test
	public void Stalemate1() throws Exception {
		Chessgame currGame = new Chessgame();
		currGame.startGame();
		currGame.move(true, 4, 1, 4, 2);
		currGame.move(false, 0, 6, 0, 4);
		currGame.move(true, 3, 0, 7, 4);
		currGame.move(false, 0, 7, 0, 5);
		currGame.move(true, 7, 4, 0, 4);
		currGame.move(false, 7, 6, 7, 4);
		currGame.move(true, 7, 1, 7, 3);
		currGame.move(false, 0, 5, 7, 5);
		currGame.move(true, 0, 4, 2, 6);
		currGame.move(false, 5, 6, 5, 5);
		currGame.move(true, 2, 6, 3, 6);
		currGame.move(false, 4, 7, 5, 6);
		currGame.move(true, 3, 6, 1, 6);
		currGame.move(false, 3, 7, 3, 2);
		currGame.move(true, 1, 6, 1, 7);
		currGame.move(false, 3, 2, 7, 6);
		currGame.move(true, 1, 7, 2, 7);
		currGame.move(false, 5, 6, 6, 5);
		currGame.move(true, 2, 7, 4, 5);
		assertEquals(currGame.move(false, 6, 5, 6, 4), false);
	}
	
}
