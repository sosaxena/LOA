package hw10;
//Programmers: Soumya Saxena, Nan-Kun Wu
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Soumya extends Player {
	
	/**
	 * CONSTRUCTOR
	 * 
	 */
	public Soumya(){
	

	}
	
	
	
	/**
	 * Pick a random piece to move
	 * @return RoundPiece
	 */
	public RoundPiece pickPiece(ArrayList<RoundPiece> pieceList){	
		int whiteNumber = 0;
		for (RoundPiece element:pieceList){
			if (element.color == Color.WHITE) {
				whiteNumber += 1; 
		} 
		}
		int randomPiece = (int) (Math.random()*(whiteNumber));
		RoundPiece pickedPiece = pieceList.get(randomPiece);
		return pickedPiece;
    }
    
	
	
	
	/**
	 * Make a list of all possible moves for the picked piece
	 * @param p
	 */
    public void makeMoveList(ArrayList<RoundPiece> pieceList,RoundPiece p){
    	
    	// These 4 statements create the moveList
    	this.getMoveRow(pieceList, p);
    	this.getMoveColumn(pieceList, p);
    	this.getMoveDiagonal(pieceList, p);
    	this.getMoveRevDiagonal(pieceList, p);
    	
    	//If no possible moves pick a new piece :
    	if (this.moveList.size() == 0){
    		RoundPiece m = pickPiece(pieceList);
    		makeMoveList(pieceList,m);
    	}		
    }
 
    
    /**
     * For a given move/target find the number of same color pieces(white in this case) immediately around it
     * @param target
     * @return number w
     */
    
   int searchAroundMove(ArrayList<RoundPiece> pieceList,int[] target){
    	
    	int w=0;
    	int r= target[0];
    	int c= target[1];
    	for(RoundPiece e: pieceList){
    		
    		if(e.color==Color.WHITE){
    			if(e.getRow()==r+1 && e.getColumn()==c+1 || e.getRow()==r && e.getColumn()==c+1 || e.getRow()==r-1 && e.getColumn()==c+1 ||e.getRow()==r+1 && e.getColumn()==c-1 ||e.getRow()==r && e.getColumn()==c-1 ||e.getRow()==r-1 && e.getColumn()==c-1 ||e.getRow()==r-1 && e.getColumn()==c || e.getRow()==r+1 && e.getColumn()==c){
        			w+=1;
    			
    		}
    			
    	}
    	
    	}
    		
    	return w;
    }
   
   
   /**
    * If an opposite colored piece is at the target, returns true else returns false
    ** @param pieceList
    * @param target
    * @return boolean:true/false
    */
   boolean OppositeColor(ArrayList<RoundPiece> pieceList,int[] target){
	   int n=0;
	   int r= target[0];
	   int c = target[1];
	   for(RoundPiece e: pieceList){
		   if(e.color==Color.BLACK){
			   if(e.getRow()==r && e.getColumn()==c){
				   n+=1;
			   }
		   }
	   }
	   
	   if(n==0){
	   return false;
	   }
	   else{
		   return true;
	   }
   }
    
    
    /**
     * Compute number of similar colored pieces around each move in the moves' list.
     * Also find whether that target position has any opposite colored(black) pieces
     * 
     * Following conditions to pick best move:
     *  Whichever has the maximum number of similar colored pieces around it.
     *  OR
     *  Whichever has an opposite(black) color piece is the best move 
     * @return a move
     */
    
        
    public int[] pickBestMove(ArrayList<RoundPiece> pieceList){
    	
    	int[] pickedMove=moveList.get(0);
    	int max=0;
    	
    	for(int i=0; i<moveList.size();i++){
    		
    		int[] target= moveList.get(i);
    		
    		int number= searchAroundMove(pieceList,target);
    		if(number>max || OppositeColor(pieceList,target)==true){
    			max=number;
    			
    			pickedMove=target;
    		}
    		
    	}
    	return pickedMove;
    }
    
     
    /**
     * Clears the list of all moves
     */
    public void clearMoveList(){
    	this.moveList.clear();
    }
    
    
    
}
    
    
	
	
			
			
	
	
	
	
	
	
	
	
	
		
	


