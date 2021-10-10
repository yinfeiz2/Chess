package chess;

public abstract class Piece {
	
	private boolean white; /// true if the piece is white
	
	public Piece(boolean white) {
		this.white = white;
	}
	
	public boolean getWhite() {
		return this.white;
	}
	
	public void setWhite(boolean white) {
		this.white = white;
	}
	
	/**
	 * return true if the piece can move from start square to end square on board
	 */
	public boolean isValidMove(Board board, Square start, Square end) {
		
		if (isValidMoveWithoutCheck(board, start, end)) {
			Board afterMove = new Board(board);
			
			Square before = afterMove.getSquare(start.getX(), start.getY());
			Square after = afterMove.getSquare(end.getX(), end.getY());
			
			before.setPiece(null);
			after.setPiece(start.getPiece());
			
			if (start.getPiece() instanceof King) {
				
				if (start.getPiece().white) {
					afterMove.whiteKingX = end.getX();
					afterMove.whiteKingY = end.getY();
				} else {
					afterMove.blackKingX = end.getX();
					afterMove.blackKingY = end.getY();
				}
				
			}
			
			return !afterMove.ischeck(start.getPiece().white);
		}
		return false;
	}
	
	/** 
	 * return true if the piece can move from start square to end square on board, 
	 * regardless of whether the King will be checked
	 */
	public abstract boolean isValidMoveWithoutCheck(Board board, Square start, Square end);
	
	public static class King extends Piece {
		
		public King(boolean white) {
			super(white);
		}
		
		public boolean isValidMoveWithoutCheck(Board board, Square start, Square end) {
			int dx = end.getX() - start.getX();
			int dy = end.getY() - start.getY();
			
			if (dx == 0 && dy == 0) {
				return false;
			}
			
			if (Math.abs(dx) >= 2 || Math.abs(dy) >= 2) {
				return false;
			}
			
			return true;
		}
		
	}
	
	public static class Queen extends Piece {
		
		public Queen(boolean white) {
			super(white);
		}
		
		public boolean isValidMoveWithoutCheck(Board board, Square start, Square end) {
			int dx = end.getX() - start.getX();
			int dy = end.getY() - start.getY();
			
			if (dx == 0) {
				
				if (dy == 0) {
					return false; // no move
				}
				
				 /* vertical move */
				int x = start.getX();
				int yStart;
				int yEnd;
				
				if (dy < 0) {
					yStart = end.getY() + 1;
					yEnd = start.getY();
				} else {
					yStart = start.getY() + 1;
					yEnd = end.getY();
				}
				
				for (int i = yStart; i < yEnd; i++) {
					Square curr = board.getSquare(x, i);
					if (curr.getPiece() != null) {
						return false;
					}
				}
				
				return true;
				
			} else if (dy == 0) {
				/* horizontal move */
				int y = start.getY();
				int xStart;
				int xEnd;
				
				if (dx < 0) {
					xStart = end.getX() + 1;
					xEnd = start.getX();
				} else {
					xStart = start.getX() + 1;
					xEnd = end.getX();
				}
				
				for (int i = xStart; i < xEnd; i++) {
					Square curr = board.getSquare(i, y);
					if (curr.getPiece() != null) {
						return false;
					}
				}
				
				return true;
				
			} else {
				
				if (Math.abs(dx) != Math.abs(dy)) {
					return false;
				}
				
				/* diagonal move */
				int xStart;
				int xEnd;
				int yStart;
				
				if (dx < 0) {
					xStart = start.getX() - 1;
					xEnd = end.getX();
				} else {
					xStart = start.getX() + 1;
					xEnd = end.getX();
				}
				
				if (dy < 0) {
					yStart = start.getY() - 1;
				} else {
					yStart = start.getY() + 1;
				}
				
				while (xStart != xEnd) {
					Square curr = board.getSquare(xStart, yStart);
					
					if (curr.getPiece() != null) {
						return false;
					}
					
					if (dx < 0) {
						xStart -= 1;
					} else {
						xStart += 1;
					}
					
					if (dy < 0) {
						yStart -= 1;
					} else {
						yStart += 1;
					}
					
				}
				
				return true;
				
			}
		}
		
	}
	
	public static class Rook extends Piece {
		
		public Rook(boolean white) {
			super(white);
		}
		
		public boolean isValidMoveWithoutCheck(Board board, Square start, Square end) {
			int dx = end.getX() - start.getX();
			int dy = end.getY() - start.getY();
			
			if (dx == 0) {
				
				if (dy == 0) {
					return false; // no move
				}
				
				 /* vertical move */
				int x = start.getX();
				int yStart;
				int yEnd;
				
				if (dy < 0) {
					yStart = end.getY() + 1;
					yEnd = start.getY();
				} else {
					yStart = start.getY() + 1;
					yEnd = end.getY();
				}
				
				for (int i = yStart; i < yEnd; i++) {
					Square curr = board.getSquare(x, i);
					if (curr.getPiece() != null) {
						return false;
					}
				}
				
				return true;
				
			} else if (dy == 0) {
				/* horizontal move */
				int y = start.getY();
				int xStart;
				int xEnd;
				
				if (dx < 0) {
					xStart = end.getX() + 1;
					xEnd = start.getX();
				} else {
					xStart = start.getX() + 1;
					xEnd = end.getX();
				}
				
				for (int i = xStart; i < xEnd; i++) {
					Square curr = board.getSquare(i, y);
					if (curr.getPiece() != null) {
						return false;
					}
				}
				
				return true;
				
			} else {
				return false;
			}
		}
		
	}
	
	public static class Bishop extends Piece {
		
		public Bishop(boolean white) {
			super(white);
		}
		
		public boolean isValidMoveWithoutCheck(Board board, Square start, Square end) {
			int dx = end.getX() - start.getX();
			int dy = end.getY() - start.getY();
			
			if (dx == 0 && dy == 0) {
				return false;
			}
			
			if (Math.abs(dx) != Math.abs(dy)) {
				return false;
			}
			
			/* diagonal move */
			int xStart;
			int xEnd;
			int yStart;
			
			if (dx < 0) {
				xStart = start.getX() - 1;
				xEnd = end.getX();
			} else {
				xStart = start.getX() + 1;
				xEnd = end.getX();
			}
			
			if (dy < 0) {
				yStart = start.getY() - 1;
			} else {
				yStart = start.getY() + 1;
			}
			
			while (xStart != xEnd) {
				Square curr = board.getSquare(xStart, yStart);
				
				if (curr.getPiece() != null) {
					return false;
				}
				
				if (dx < 0) {
					xStart -= 1;
				} else {
					xStart += 1;
				}
				
				if (dy < 0) {
					yStart -= 1;
				} else {
					yStart += 1;
				}
				
			}
			
			return true;
			
		}
		
	}
	
	public static class Knight extends Piece {
		
		public Knight(boolean white) {
			super(white);
		}
		
		public boolean isValidMoveWithoutCheck(Board board, Square start, Square end) {
			int dx = end.getX() - start.getX();
			int dy = end.getY() - start.getY();
			return Math.abs(dx * dy) == 2;
		}
		
	}
	
	public static class Pawn extends Piece {
		
		public Pawn(boolean white) {
			super(white);
		}
		
		public boolean isValidMoveWithoutCheck(Board board, Square start, Square end) {
			int dx = end.getX() - start.getX();
			int dy = end.getY() - start.getY();
			
			/* diagonal capture */
			if (dx != 0) {
				if (Math.abs(dx) == 1) {
					
					if (this.getWhite()) {
						
						if (dy == 1 && end.getPiece() != null) {
							return !end.getPiece().getWhite();
						} else {
							return false;
						}
						
					} else {
						
						if (dy == -1 && end.getPiece() != null) {
							return end.getPiece().getWhite();
						} else {
							return false;
						}
					}
					
				} else {
					return false;
				}
			}
			
			/* forward move */
			Square next;
			if (this.getWhite()) {
				next = board.getSquare(start.getX(), start.getY() + 1);
			} else {
				next = board.getSquare(start.getX(), start.getY() - 1);
			}
			
			if (next.getPiece() != null || end.getPiece() != null) {
				return false;
			}
			
			if (this.getWhite()) {
				
				if (start.getY() == 1) {
					return dy == 1 || dy == 2;
				} else {
					return dy == 1;
				}
				
			} else {
				
				if (start.getY() == 6) {
					return dy == -1 || dy == -2;
				} else {
					return dy == -1;
				}
				
			}
		}
		
	}
	
	/**
	 * custom piece Gold from Shogi
	 * Gold moves one square orthogonally, or one square diagonally forward
	 */
	public static class Gold extends Piece {
		
		public Gold(boolean white) {
			super(white);
		}
		
		public boolean isValidMoveWithoutCheck(Board board, Square start, Square end) {
			int dx = end.getX() - start.getX();
			int dy = end.getY() - start.getY();
			
			if (dx == 0 && dy == 0) {
				return false;
			}
			
			if (Math.abs(dx) >= 2 || Math.abs(dy) >= 2) {
				return false;
			}
			
			if (Math.abs(dx) == Math.abs(dy)) {
				
				if (this.getWhite()) {
					return dy == 1;
				} else {
					return dy == -1;
				}
				
			}
			
			return true;
		}
		
	}
	
	/**
	 * custom piece Lance from Shogi
	 * Lance moves any number of squares forward
	 */
	public static class Lance extends Piece {
		
		public Lance(boolean white) {
			super(white);
		}
		
		public boolean isValidMoveWithoutCheck(Board board, Square start, Square end) {
			int dx = end.getX() - start.getX();
			int dy = end.getY() - start.getY();
			
			if (dx != 0 || dy == 0) {
				return false;
			}
			
			if (this.getWhite() == dy < 0) {
				return false;
			}
			
			int x = start.getX();
			int yStart;
			int yEnd;
			
			if (dy < 0) {
				yStart = end.getY() + 1;
				yEnd = start.getY();
			} else {
				yStart = start.getY() + 1;
				yEnd = end.getY();
			}
			
			for (int i = yStart; i < yEnd; i++) {
				Square curr = board.getSquare(x, i);
				if (curr.getPiece() != null) {
					return false;
				}
			}
			
			return true;
			
		}
		
	}
	
}
