package hw10;

//Programmers: Soumya Saxena, Nan-Kun Wu

import java.awt.Color;
import java.util.ArrayList;

public class Nan extends Player{ 
	

	
	/**
	 * Pick a random piece to move
	 * @param pieceList
	 * @return piece
	 */
	public RoundPiece pickPiece(ArrayList<RoundPiece> pieceList){	
		int blackNumber = 0;
		int whiteNumber = 0;
		for (RoundPiece element: pieceList){
			if (element.color == Color.BLACK) blackNumber += 1;
			if (element.color == Color.WHITE) whiteNumber += 1; 
		}    	
		int randomPiece = (int) (Math.random()*(blackNumber));
		RoundPiece pickedPiece = pieceList.get(randomPiece + whiteNumber);
		return pickedPiece;
    }
    
	
	/**
	 * Make a list of all moves possible.
	 * 
	 */
    public void makeMoveList(ArrayList<RoundPiece> pieceList, RoundPiece p){
    	getMoveRow(pieceList, p);
    	getMoveColumn(pieceList, p);
    	getMoveDiagonal(pieceList, p);
    	getMoveRevDiagonal(pieceList, p);
    	if (this.moveList.size() == 0){
    		RoundPiece m = pickPiece(pieceList);
    		makeMoveList(pieceList, m);    		
    	}
    }
     
    
    /**
     * Pick a random move
     * @return move
     */
    public int[] pickMove(){
    	int n = this.moveList.size();
    	int randomMove = (int) (Math.random()*n);
    	return this.moveList.get(randomMove);
    }
    
    
    /**
     * Pick a move based on strategy. Moves the  picked piece to the middle.
     * @param p
     * @return move
     */
    public int[] pickMove(RoundPiece p){
    	int r = p.getRow();
    	int c = p.getColumn();
    	for (int[] move: this.moveList){
    		if (r < 4 && c >= 4 && move[0] >= r && move[1] <= c){
    			return move;
    		}
    		else if (r < 4 && c < 4 && move[0] >= r && move[1] >= c){
    			return move;
    		}
    		else if (r >= 4 && c < 4 && move[0] <= r && move[1] >= c){
    			return move;
    		}
    		else if (r >= 4 && c >= 4 && move[0] <= r && move[1] <= c){
    			return move;
    		}
    	}
    	return pickMove();
    }
    
    
    /** 
     * Clear moveList
     */
    public void clearMoveList(){
    	this.moveList.clear();
    }
    
}


