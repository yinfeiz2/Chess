package chess;

/**
 * represent a square on the chess board
 */
public class Square {
	
	private Piece piece; /// the piece in the square, null if there is no piece
	private int x;
	private int y; // position of the square
	
	public Square(Piece piece, int x, int y) {
		this.setPiece(piece);
        this.setX(x);
        this.setY(y);
	}
	
	public Square(Square square) {
		this.setPiece(square.piece);
		this.setX(square.x);
		this.setY(square.y);
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public int getX() {
        return this.x;
    }
	
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
}
