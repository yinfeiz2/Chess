package chess;

public class Chessgame {

	private Board board = new Board();
	private boolean whiteTurn = true; /// true if it is White's turn
	
	public void startGame() {
		board.setup();
	}
	
	public void startCustomGame() {
		board.setupCustom();
	}
	
	/**
	 * return true if checkmate is reached
	 */
	public boolean isCheckmate() {
		return board.isCheckmate(true) || board.isCheckmate(false);
	}
	
	/**
	 * return true if stalemate is reached
	 */
	public boolean isStalemate() {
		return board.isStalemate(true) || board.isStalemate(false);
	}
	
	/**
	 * return true if game is over
	 */
	public boolean isEnd() {
		return isCheckmate() || isStalemate();
	}
	
	public boolean move(boolean isWhitePlayer, int startX, int startY, int endX, int endY) {
		
		if (this.isEnd()) { // game over
			return false;
		}
		
		if (whiteTurn != isWhitePlayer) { // wrong turn
			return false;
		}
		
		Square start = board.getSquare(startX, startY);
		Square end = board.getSquare(endX, endY);
		
		Piece pieceAtStart = start.getPiece();
		Piece pieceAtEnd = end.getPiece();
		
		if (pieceAtStart == null) { // no piece to move
			return false;
		}
		
		if (pieceAtStart.getWhite() != isWhitePlayer) { // piece does not belong to the player
			return false;
		}
		
		if (!pieceAtStart.isValidMove(board, start, end)) { // move is not valid
			return false;
		}
		
		if (pieceAtEnd != null) {
			
			if (pieceAtEnd.getWhite() == pieceAtStart.getWhite()) { // move blocked by player's own piece
				return false;
			}
			
		}
		
		end.setPiece(pieceAtStart);
		start.setPiece(null);
		whiteTurn = !whiteTurn;
		
		if (pieceAtStart instanceof Piece.King) {
			
			if (pieceAtStart.getWhite()) {
				board.whiteKingX = endX;
				board.whiteKingY = endY;
			} else {
				board.blackKingX = endX;
				board.blackKingY = endY;
			}
			
		}
		
		return true;
		
	}
	
}
