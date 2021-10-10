package chess;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class Chessview extends JPanel{
	
	private class Squareview extends JButton{
		private int x;
		private int y;
		
		Squareview(int inputX, int inputY) {
			x = inputX;
			y = inputY;
			
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					if (gameOver) {
						message.setText("Invalid command");
					} else {
						if (src == null) {
							src = squares[x][y];
						} else {
							
							dest = squares[x][y];
							if (currGame.move(turnPlayer, src.x, src.y, dest.x, dest.y)) {
								dest.setIcon(src.getIcon());
								src.setIcon(null);
								turnPlayer = !turnPlayer;
								String player;
								
								if (turnPlayer) {
									player = players[0];
								} else {
									player = players[1];
								}
								
								message.setText(player + " turn to move");
								src = null;
								dest = null;
								if (currGame.isCheckmate()) {
									String winner;
									if (turnPlayer) {
										winner = players[1];
										blackScore += 1;
										blackScoreLabel.setText(players[1] + ": " + blackScore);
									} else {
										winner = players[0];
										whiteScore += 1;
										whiteScoreLabel.setText(players[0] + ": " + whiteScore);
									}
									message.setText("Checkmate! " + winner + " won!");
									gameOver = true;
								}
								if (currGame.isStalemate()) {
									message.setText("Stalemate is reached!");
									gameOver = true;
								}
							} else {
								message.setText("Invalid move");
								src = null;
							}
							
						}
					}
					
				}
				
			});
		}
		
	}
	
	private JPanel ui = new JPanel(new BorderLayout(3, 3));
	private Squareview[][] squares = new Squareview[8][8];
	private JPanel board = new JPanel(new GridLayout(0, 9));
	private JLabel message = new JLabel("Ready");
	private Image[][] pieceImages = new Image[2][6];
	public static final int BLACK = 0, WHITE = 1;
	public static final int QUEEN = 0, KING = 1, ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
	public static final int[] FIRST_ROW = {ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK};
	
	private String[] players = {"White", "Black"}; /// player names
	private boolean turnPlayer = true; /// true if it is White's turn
	
	private JLabel whiteScoreLabel = new JLabel(players[0] + ": 0");
	private JLabel blackScoreLabel = new JLabel(players[1] + ": 0");
	
	private int whiteScore = 0; /// White player's score
	private int blackScore = 0; /// Black player's score
	
	private boolean gameOver = true; /// true if the game is over
	
	private Squareview src;
	private Squareview dest;
	
	Chessgame currGame;
	
	public Chessview() {
		setupUI();
	}
	
	public final JComponent getGui() {
        return ui;
    }
	
	/**
	 * load images for pieces
	 */
	private final void loadImages() {
		
		try {
			BufferedImage loadedImage = ImageIO.read(new File("images/Pieces.png"));
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 6; j++) {
                    pieceImages[i][j] = loadedImage.getSubimage(j * 60, i * 60, 60, 60);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
		
    }
	
	/**
	 * set icons for pieces
	 */
	private void setupBoard() {
		
		for (int i = 0; i < 8; i++) {
            squares[i][2].setIcon(null);
            squares[i][3].setIcon(null);
            squares[i][4].setIcon(null);
            squares[i][5].setIcon(null);
        }
		
        for (int i = 0; i < 8; i++) {
            squares[i][0].setIcon(new ImageIcon(pieceImages[WHITE][FIRST_ROW[i]]));
        }
        
        for (int i = 0; i < 8; i++) {
        	squares[i][1].setIcon(new ImageIcon(pieceImages[WHITE][PAWN]));
        }
        
        for (int i = 0; i < 8; i++) {
        	squares[i][6].setIcon(new ImageIcon(pieceImages[BLACK][PAWN]));
        }
        
        for (int i = 0; i < 8; i++) {
        	squares[i][7].setIcon(new ImageIcon(pieceImages[BLACK][FIRST_ROW[i]]));
        }
        
        message.setText(players[0] + " turn to move");
    	turnPlayer = true;
    	gameOver = false;
        currGame = new Chessgame();
    }
	
	/**
	 * setup the GUI
	 */
	public void setupUI() {
		loadImages();
		ui.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);
        ui.add(toolbar, BorderLayout.PAGE_START);
        
        Action newGame = new AbstractAction("New") {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setupBoard();
            }
        };
        toolbar.add(newGame);
        
        Action resignGame = new AbstractAction("Resign") {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if (!gameOver) {
            		String player;
                	
                	if (turnPlayer) {
                		player = players[0];
                		blackScore += 1;
                		blackScoreLabel.setText(players[1] + ": " + blackScore);
                	} else {
                		player = players[1];
                		whiteScore += 1;
                		whiteScoreLabel.setText(players[0] + ": " + whiteScore);
                	}
                	
                	message.setText(player + " resigned the game");
                	gameOver = true;
            	} else {
            		message.setText("Invalid command");
            	}
            	
            }
        };
        toolbar.add(resignGame);
        
        Action drawGame = new AbstractAction("Draw") {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if (!gameOver) {
            		String player;
            		String opponent;
                	
                	if (turnPlayer) {
                		player = players[0];
                		opponent = players[1];
                	} else {
                		player = players[1];
                		opponent = players[0];
                	}
                	
                	message.setText(player + " offers a draw");
                	int reply = JOptionPane.showConfirmDialog(null, "Do you agree to draw?", "A draw offer",
                            JOptionPane.YES_NO_OPTION);
                	
                	if (reply == JOptionPane.YES_OPTION) {
                		message.setText("Draw by agreement");
                		gameOver = true;
                	} else {
                		message.setText(opponent + " declined to draw");
                	}
                	
            	} else {
            		message.setText("Invalid command");
            	}
            	
            }
        };
        toolbar.add(drawGame);
        
        toolbar.addSeparator();
        toolbar.add(message);
        
        board.setBorder(new LineBorder(Color.BLACK));
        ui.add(board);
        
        Insets margin = new Insets(0,0,0,0);
        Color lightSquare = new Color(232, 235, 239);
        Color DarkSquare = new Color(125, 135, 150);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	Squareview curr = new Squareview(j, 7 - i);
                curr.setMargin(margin);
                curr.setBorderPainted(false);
                curr.setOpaque(true);
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                curr.setIcon(icon);
                if ((i + j) % 2 == 0) {
                	curr.setBackground(lightSquare);
                } else {
                	curr.setBackground(DarkSquare);
                }
                squares[j][7 - i] = curr;
            }
        }
        
        board.add(new JLabel(""));
        board.add(new JLabel("A", SwingConstants.CENTER));
        board.add(new JLabel("B", SwingConstants.CENTER));
        board.add(new JLabel("C", SwingConstants.CENTER));
        board.add(new JLabel("D", SwingConstants.CENTER));
        board.add(new JLabel("E", SwingConstants.CENTER));
        board.add(new JLabel("F", SwingConstants.CENTER));
        board.add(new JLabel("G", SwingConstants.CENTER));
        board.add(new JLabel("H", SwingConstants.CENTER));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 0) {
                    board.add(new JLabel("" + (8 - i), SwingConstants.CENTER));
                } else {
                    board.add(squares[j - 1][7 - i]);
                }
            }
        }
        
        JToolBar scorebar = new JToolBar();
        scorebar.setFloatable(false);
        ui.add(scorebar, BorderLayout.PAGE_END);
        scorebar.add(whiteScoreLabel);
        scorebar.add(Box.createHorizontalGlue());
        scorebar.add(blackScoreLabel);
        
	}

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
            	Chessview view = new Chessview();

                JFrame frame = new JFrame("Chess");
                frame.add(view.getGui());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationByPlatform(true);
                frame.pack();
                frame.setMinimumSize(frame.getSize());
                frame.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
    
}
