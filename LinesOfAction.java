package hw10;

//Programmers: Soumya Saxena, Nan-Kun Wu


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * @author Soumya Saxena and Nan-Kun-Wu
 *
 */
public class LinesOfAction extends JFrame implements Runnable {
	
	Board board;
  JPanel buttonPanel;
  JButton quitButton;
  JButton nextButton;
  JButton whiteButton;
  JButton clearButton;
  JButton autoPlay;
  JButton step;
  JButton blackFirst;
  JButton whiteFirst;
  JSlider speedSlider;
  final int maxSpeed = 20;
  final int minSpeed = 1;
  final int initSpeed = 10;
  LinesOfAction self;
  Piece testBlock;
  Random random = new Random();
  final int ROWS = 8;
  final int COLUMNS = 8;
  Nan nan = new Nan();
  Soumya soumya = new Soumya();
  CheckWin check = new CheckWin();
  ArrayList<RoundPiece> pieceList = new ArrayList<RoundPiece>();
  int first;
  
  
  /**
   * Main 
   * @param args
   */

	public static void main(String[] args) {
		LinesOfAction newGame = new LinesOfAction();		
		try {
			newGame.gameSetup();
			
		}
		catch (Exception e) {
          e.printStackTrace(System.out);
          newGame.board.dump();
      }
	}
	
	
	/**
	 * Create and display the board.
	 * Display dialogs 
	 * Install all buttons 
	 * 
	 */
	void gameSetup(){
		self = this;
		
		// Create a Board (a kind of JPanel) and add it to this Frame.
		board = new Board(ROWS, COLUMNS);
		
      JPanel display = board.getJPanel();
      getContentPane().add(display, BorderLayout.CENTER);
      
      // Install button panel
      buttonPanel = new JPanel();
      getContentPane().add(buttonPanel, BorderLayout.SOUTH);
      
      installStepButton();
      
      installQuitButton();
      installSpeedSlider();
      implementCloseBox();
       
      pack();
      setSize(560, 640);
      setVisible(true);
      createPiece();
      int mode = AutoOrStep();//auto=0, step=1
      first = chooseFirst();//black=0, white=1
      
      if (mode == 0){
      	step.setEnabled(false);
      	autoPlay(first);
      }
      if (mode == 1){       	
      	first = stepPlay(first);	
      }
      
	}
	
	
	/**
	 * Create black and white pieces
	 * Place them on the board
	 * Add them to pieceList
	 */
	public void createPiece(){
		RoundPiece wp1 = new RoundPiece(Color.WHITE);
		pieceList.add(wp1);
		board.place(wp1, 0, 1);
		RoundPiece wp2 = new RoundPiece(Color.WHITE);
		pieceList.add(wp2);
		board.place(wp2, 0, 2);
		RoundPiece wp3 = new RoundPiece(Color.WHITE);
		pieceList.add(wp3);
		board.place(wp3, 0, 3);
		RoundPiece wp4 = new RoundPiece(Color.WHITE);
		pieceList.add(wp4);
		board.place(wp4, 0, 4);
		RoundPiece wp5 = new RoundPiece(Color.WHITE);
		pieceList.add(wp5);
		board.place(wp5, 0, 5);
		RoundPiece wp6 = new RoundPiece(Color.WHITE);
		pieceList.add(wp6);
		board.place(wp6, 0, 6);
		RoundPiece wp7 = new RoundPiece(Color.WHITE);
		pieceList.add(wp7);
		board.place(wp7, 7, 1);
		RoundPiece wp8 = new RoundPiece(Color.WHITE);
		pieceList.add(wp8);
		board.place(wp8, 7, 2);
		RoundPiece wp9 = new RoundPiece(Color.WHITE);
		pieceList.add(wp9);
		board.place(wp9, 7, 3);
		RoundPiece wp10 = new RoundPiece(Color.WHITE);
		pieceList.add(wp10);
		board.place(wp10, 7, 4);
		RoundPiece wp11 = new RoundPiece(Color.WHITE);
		pieceList.add(wp11);
		board.place(wp11, 7, 5);
		RoundPiece wp12 = new RoundPiece(Color.WHITE);
		pieceList.add(wp12);
		board.place(wp12, 7, 6);
				
		RoundPiece bp1 = new RoundPiece(Color.BLACK);
		pieceList.add(bp1);
		board.place(bp1, 1, 0);
		RoundPiece bp2 = new RoundPiece(Color.BLACK);
		pieceList.add(bp2);
		board.place(bp2, 2, 0);
		RoundPiece bp3 = new RoundPiece(Color.BLACK);
		pieceList.add(bp3);
		board.place(bp3, 3, 0);
		RoundPiece bp4 = new RoundPiece(Color.BLACK);
		pieceList.add(bp4);
		board.place(bp4, 4, 0);
		RoundPiece bp5 = new RoundPiece(Color.BLACK);
		pieceList.add(bp5);
		board.place(bp5, 5, 0);
		RoundPiece bp6 = new RoundPiece(Color.BLACK);
		pieceList.add(bp6);
		board.place(bp6, 6, 0);
		RoundPiece bp7 = new RoundPiece(Color.BLACK);
		pieceList.add(bp7);
		board.place(bp7, 1, 7);
		RoundPiece bp8 = new RoundPiece(Color.BLACK);
		pieceList.add(bp8);
		board.place(bp8, 2, 7);
		RoundPiece bp9 = new RoundPiece(Color.BLACK);
		pieceList.add(bp9);
		board.place(bp9, 3, 7);
		RoundPiece bp10 = new RoundPiece(Color.BLACK);
		pieceList.add(bp10);
		board.place(bp10, 4, 7);
		RoundPiece bp11 = new RoundPiece(Color.BLACK);
		pieceList.add(bp11);
		board.place(bp11, 5, 7);
		RoundPiece bp12 = new RoundPiece(Color.BLACK);
		pieceList.add(bp12);
		board.place(bp12, 6, 7);
	}


	/**
	 * Black player makes a move.
	 * If color at target is white, kill it
	 */
	
	public void blackRound(){
		RoundPiece p = nan.pickPiece(pieceList);
		
		nan.makeMoveList(pieceList, p);
		int[] target = nan.pickMove(p);	
		
		for (int i=0; i<pieceList.size(); i++){
			if (pieceList.get(i).getRow() == target[0] && pieceList.get(i).getColumn() == target[1] && pieceList.get(i).color == Color.WHITE){
				board.remove(pieceList.get(i));
				pieceList.remove(pieceList.get(i));				
								
			}
		}
		p.moveTo(target[0], target[1]);			
		
		nan.clearMoveList();
	}
	
	

	/**
	 * White player makes a move.
	 * If color at target is black, kill it
	 */
	public void whiteRound(){
		RoundPiece p = soumya.pickPiece(pieceList);
		
		soumya.makeMoveList(pieceList, p);
		int[] target = soumya.pickBestMove(pieceList);	
		if (target == null){
			whiteRound();
		}
		
		
		for (int i=0; i<pieceList.size(); i++){
			if (pieceList.get(i).getRow() == target[0] && pieceList.get(i).getColumn() == target[1] && pieceList.get(i).color == Color.BLACK){
				board.remove(pieceList.get(i));
				pieceList.remove(pieceList.get(i));				
								
			}
		}				
		
		p.moveTo(target[0], target[1]);	
		soumya.clearMoveList();
	}
	
	
	/**
	 * Choose whether to play in auto or step mode.
	 * @return choice
	 */
	public int AutoOrStep(){
		String[] options = {"auto", "step"};
		String q = "Auto play or step by step?";
		String title = "Choose game mode";
		int choice = JOptionPane.showOptionDialog(null, q, title, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
		return choice;
	}
	
	
	/**
	 * Choose whether white goes first or black
	 * @return choice
	 */
	public int chooseFirst(){
		String[] options = {"black", "white"};
		String q = "choose the color which goes first";
		String title = "Start color";
		int choice = JOptionPane.showOptionDialog(null, q, title, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
		return choice;
	}
	
	
	/**
	 * If choice returned by chooseFirst() is 0 black plays first else white plays first.
	 * After every move check whether either of them has won.
	 * Game stops if either wins.
	 * @param i
	 */
	public void autoPlay(int i){
		if (i == 0){//black go first
			while (check.checkBlack(pieceList) == false && check.checkWhite(pieceList) == false){
				blackRound();
				if (check.checkWhite(pieceList) == false && check.checkBlack(pieceList) == false){
					whiteRound();
				}
				else break;
			}
			
			//Come here after break
			if (check.checkBlack(pieceList) == true){
				int choice = showWin(Color.BLACK);
				if (choice == 1) resetGame();
				else if (choice == 0) System.exit(0);
			}
			if (check.checkWhite(pieceList) == true){
				int choice = showWin(Color.WHITE);
				if (choice == 1) resetGame();				
				else if (choice == 0) System.exit(0);
			}
			
		}				
		if (i == 1){//white go first
			while (check.checkWhite(pieceList) == false && check.checkBlack(pieceList) == false){
				whiteRound();
				if (check.checkWhite(pieceList) == false && check.checkBlack(pieceList) == false){
					blackRound();					
				}
				else break;				
			}
			
			if (check.checkBlack(pieceList) == true){
				int choice = showWin(Color.BLACK);
				if (choice == 1) resetGame();
				else if (choice == 0) System.exit(0);
			}
			if (check.checkWhite(pieceList) == true){
				int choice = showWin(Color.WHITE);
				if (choice == 1) resetGame();
				else if (choice == 0) System.exit(0);
			}
		}
	}
	
	
	/**
	 * If i is 0 black makes a move.
	 * Checks if black has won. If not sets i to 1.
	 * 
	 * If i is 1 white makes a move.
	 * Checks if white has won. If not sets i to 0.
	 * @param i
	 * @return i
	 */
	public int stepPlay(int i){
		if (i == 0){
			blackRound();
			if (check.checkBlack(pieceList) == true){
				int choice = showWin(Color.BLACK);
				if (choice == 1) resetGame();
				else if (choice == 0) System.exit(0);
			}
			if (check.checkWhite(pieceList) == true){
				showWin(Color.WHITE);
				int choice = showWin(Color.WHITE);
				if (choice == 1) resetGame();
				else if (choice == 0) System.exit(0);
			}
			i = 1; return i;
		}
		else if (i== 1){
			whiteRound();
			if (check.checkBlack(pieceList) == true){
				int choice = showWin(Color.BLACK);
				if (choice == 1) resetGame();
				else if (choice == 0) System.exit(0);
			}
			if (check.checkWhite(pieceList) == true){
				showWin(Color.WHITE);
				int choice = showWin(Color.WHITE);
				if (choice == 1) resetGame();
				else if (choice == 0) System.exit(0);
			}
			i = 0; return i;
		}
		return i;
	}
	
	
	
	/**
	 * Displays options dialog to give choice to Quit or Restart the game.
	 * Displays message according to the color of the winning player.
	 * @param color
	 * @return choice
	 */
	private int showWin(Color color){
		String msg = "";
		String title = "Restart or quit?";
		String[] options = {"Quit", "Restart"};
		if (color == Color.BLACK) msg = "Black wins!\n Start again?";
		else if (color == Color.WHITE) msg = "White wins!\n Start again?";
		int choice = JOptionPane.showOptionDialog(null, msg, title, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
		return choice;
	}
	
	
	/**
	 * Resets the game by removing all pieces from the board and the pieceList.
	 * Sets up a new game.
	 */
	private void resetGame(){
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				board.remove(i, j);
			}
		}
		pieceList.clear();
		LinesOfAction newGame = new LinesOfAction();
		newGame.gameSetup();
	}
	
	
	/**
	 * Displays a speed slider.
	 */
	private void installSpeedSlider(){
		speedSlider = new JSlider(JSlider.HORIZONTAL, minSpeed, maxSpeed, initSpeed);
		buttonPanel.add(speedSlider);
		speedSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				JSlider speedSlider = (JSlider) e.getSource();
				board.setSpeed((int) speedSlider.getValue());				
			}
		});
	}
	
	
	/**
	 * If mode chosen is step mode, installs the step button.
	 * If pressed a player makes one move alternatively.
	 */
	private void installStepButton(){
      step = new JButton("Next");
      buttonPanel.add(step);
      step.addActionListener(new ActionListener(){
      	public void actionPerformed(ActionEvent e) {
      		first = stepPlay(first);
      	}
      });
      if(step.getComponentListeners().length > 0) {
          step.setEnabled(true);
      }
      else {
          step.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  Thread t = new Thread(self);
                  t.start();                   
              }});
      }
  }
	
	
	
	/**
	 * Installs a quit button .
	 * Quits the game.
	 */
	private void installQuitButton() {
      // Install Quit button
      quitButton = new JButton("Quit");
      buttonPanel.add(quitButton);
      quitButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              System.exit(0);
      }});
  }
	
	/**
	 * Close the box of the window.
	 */
	private void implementCloseBox() {
      // Implement window close box
      addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
              dispose();
              System.exit(0);
      }});
  }
	
	protected void dumpEverything() {
      board.dump();
      
  }

	@Override
	public void run() {
//		if (testBlock == null) {
//          testBlock = new Block();
//          board.place(testBlock, 5, 0);
//          testBlock.setSpeed(5);
//          testBlock.setDraggable(true);
//          testBlock.setSelectable(true);
//      } else {
//          int oldRow = testBlock.getRow();
//          int oldColumn = testBlock.getColumn() ;
//          if (oldRow < 0 || oldColumn < 0) {
//              testBlock.place(board, 0, 0);
//          }
//          int newRow = random.nextInt(ROWS);
//          int newColumn = random.nextInt(COLUMNS);
//          
//          testBlock.moveTo(newRow, newColumn);
//      }
		
	}

}