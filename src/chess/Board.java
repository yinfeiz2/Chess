package chess;

public class Board {
	
	public int width = 8; // width of chess board
	public int height = 8; // height of chess board
	private Square[][] grid = new Square[width][height]; // representation of chess board
	
	/* position of white and black King */
	public int whiteKingX = 4;
	public int whiteKingY = 0;
	public int blackKingX = 4;
	public int blackKingY = 7;
	
	public Board() {
        this.setup();
    }
	
	public Board(Board board) {
		this.width = board.width;
		this.height = board.height;
		this.grid = new Square[width][height];
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				grid[i][j] = new Square(board.getSquare(i, j));
			}
		}
		
		this.whiteKingX = board.whiteKingX;
		this.whiteKingY = board.whiteKingY;
		this.blackKingX = board.blackKingX;
		this.blackKingY = board.blackKingY;		
	}

	public Square getSquare(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return null;
		}
		
		return grid[x][y];
	}
	
	/**
	 * initial setup for chess board
	 */
	public void setup() {
		for (int i = 0; i < width; i++) {
			if (i == 0 || i == 7) {
				grid[i][0] = new Square(new Piece.Rook(true), i, 0);
				grid[i][7] = new Square(new Piece.Rook(false), i, 7);
			} else if (i == 1 || i == 6) {
				grid[i][0] = new Square(new Piece.Knight(true), i, 0);
				grid[i][7] = new Square(new Piece.Knight(false), i, 7);
			} else if (i == 2 || i == 5) {
				grid[i][0] = new Square(new Piece.Bishop(true), i, 0);
				grid[i][7] = new Square(new Piece.Bishop(false), i, 7);
			} else if (i == 3) {
				grid[i][0] = new Square(new Piece.Queen(true), i, 0);
				grid[i][7] = new Square(new Piece.Queen(false), i, 7);
			} else {
				grid[i][0] = new Square(new Piece.King(true), i, 0);
				grid[i][7] = new Square(new Piece.King(false), i, 7);
			}
			
			grid[i][1] = new Square(new Piece.Pawn(true), i, 1);
			grid[i][6] = new Square(new Piece.Pawn(false), i, 6);
			
			grid[i][2] = new Square(null, i, 2);
			grid[i][3] = new Square(null, i, 3);
			grid[i][4] = new Square(null, i, 4);
			grid[i][5] = new Square(null, i, 5);
		}
		
		whiteKingX = 4;
		whiteKingY = 0;
		blackKingX = 4;
		blackKingY = 7;
	}

	/**
	 * when white is true, return true if the white King is in check
	 * otherwise return true if the black King is in check
	 */
	public boolean ischeck(boolean white) {
		
		/* examine all squares on board */
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Square curr = getSquare(i, j);
				Piece attacking = curr.getPiece();
				
				if (attacking == null) { // no piece at the square
					continue;
				}
				
				if (attacking.getWhite() == white) { // the piece has the same color as the king
					continue;
				}
				
				Square king;
				
				if (white) {
					king = getSquare(whiteKingX, whiteKingY);
				} else {
					king = getSquare(blackKingX, blackKingY);
				}
				
				if (attacking.isValidMoveWithoutCheck(this, curr, king)) {
					return true;
				}
				
			}
		}
		
		return false;
	}
	
	public void setupCustom() {
		setup();
		grid[0][1].setPiece(new Piece.Gold(true));
		grid[0][6].setPiece(new Piece.Gold(false));
		grid[7][1].setPiece(new Piece.Lance(true));
		grid[7][6].setPiece(new Piece.Lance(false));
	}
	
	/**
	 * when white is true, return true if the white player has no valid move
	 * otherwise return true if the black player has no valid move
	 */
	public boolean noValidMove(boolean white) {
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Square curr = this.getSquare(i, j);
				Piece candidate = curr.getPiece();
				
				if (candidate == null) {
					continue;
				}
				
				if (candidate.getWhite() != white) {
					continue;
				}
				
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						
						Square dest = this.getSquare(x, y);
						if (candidate.isValidMove(this, curr, dest)) {
							
							if (dest.getPiece() != null) {
								
								if (dest.getPiece().getWhite() == white) {
									continue;
								}
								
							}
							
							return false;
							
						}
						
					}
				}
				
			}
		}
		
		return true;
	}
	
	/**
	 * when white is true, return true if the white King is checkmated
	 * otherwise return true if the black King is checkmated
	 */
	public boolean isCheckmate(boolean white) {
		
		if (this.noValidMove(white)) {
			return this.ischeck(white);
		}
		
		return false;
	}
	
	/**
	 * when white is true, return true if the white King is in stalemate
	 * otherwise return true if the black King is in stalemate
	 */
	public boolean isStalemate(boolean white) {
		
		if (this.noValidMove(white)) {
			return !this.ischeck(white);
		}
		
		return false;
	}
	
}
