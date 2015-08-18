package hw10;
//Programmers: Soumya Saxena, Nan-Kun Wu

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CheckWin extends Player {
	
	/**
	 * Instance Variable 
	 */
	
	ArrayList<RoundPiece> temp = new ArrayList<RoundPiece>();
	
	/**
	 * Returns a list of only black pieces from the pieceList
	 * @param p
	 * @return blackList
	 */
	
	public ArrayList<RoundPiece> getBlackList(ArrayList<RoundPiece> p){
		ArrayList<RoundPiece> blackList = new ArrayList<RoundPiece>();
		for (RoundPiece element: p){
			if (element.color == Color.BLACK){
				blackList.add(element);
			}			
		}
		return blackList;
	}
	
	
	/**
	 * Returns a list of only white pieces from the pieceList
	 * @param p
	 * @return whiteList
	 */
	public ArrayList<RoundPiece> getWhiteList(ArrayList<RoundPiece> p){
		ArrayList<RoundPiece> whiteList = new ArrayList<RoundPiece>();
		for (RoundPiece element: p)
			if (element.color == Color.WHITE){
				whiteList.add(element);			
		}
		return whiteList;
	}

	/**
	 * Check if black has won.
	 * Black wins if finalSet is identical to blackList
	 * @param p
	 * @return true/false
	 */
    public boolean checkBlack(ArrayList<RoundPiece> p){
    	ArrayList<RoundPiece> blackList = getBlackList(p);
    	ArrayList<Set<RoundPiece>> collection = makeConnectList(blackList);
    	Set<RoundPiece> finalSet = compareSubCollection(collection);
    	return compareSetAndPieceList(blackList, finalSet);   	
    }
    
    

	/**
	 * Check if white has won.
	 * White wins if finalSet is identical to whiteList
	 * @param p
	 * @return true/false
	 */
    public boolean checkWhite(ArrayList<RoundPiece> p){
    	ArrayList<RoundPiece> whiteList = getWhiteList(p);
    	ArrayList<Set<RoundPiece>> collection = makeConnectList(whiteList);
    	Set<RoundPiece> finalSet = compareSubCollection(collection);
    	
    	return compareSetAndPieceList(whiteList, finalSet);   	
    }
    
    
    /**
     * Make sets of neighboring pieces of same color. Do this for each piece.
     * Add these sets to the main list called collection
     * @param p
     * @return collection
     */
    public ArrayList<Set<RoundPiece>> makeConnectList(ArrayList<RoundPiece> p){
    	ArrayList<Set<RoundPiece>> collection = new ArrayList<Set<RoundPiece>>();
    	int ri, ci, rj, cj;
    	for (int i=0; i<p.size(); i++){
    		Set<RoundPiece> subCollection = new HashSet<RoundPiece>();	
    		ri = p.get(i).getRow();
    		ci = p.get(i).getColumn();
    		for (int j=0; j<p.size(); j++){
    			rj = p.get(j).getRow();
    			cj = p.get(j).getColumn();
    			if (rj == ri-1 && cj==ci-1 || rj==ri-1 && cj==ci || rj==ri-1 && cj==ci+1 || rj==ri && cj==ci+1 || rj==ri+1 && cj==ci+1 || rj==ri+1 && cj==ci || rj==ri+1 && cj==ci-1 || rj==ri && cj==ci-1){
    				subCollection.add(p.get(j)); 
    			}	
    		}
    		subCollection.add(p.get(i));
    		collection.add(subCollection);	
    	}
    	return collection;
    }
    
    
    /**
     * Convert a set to an array list
     * @param s
     * @return a list
     */
    public ArrayList<RoundPiece> convertSetToArrayList(Set<RoundPiece> s){
    	ArrayList<RoundPiece> list = new ArrayList<RoundPiece>();
    	for (RoundPiece p: s){
    		list.add(p);
    	}
    	return list;
    }
    
    
    /**
     * Returns the set of all pieces that are connected to each other.
     * @param collection
     * @return
     */
    public Set<RoundPiece> compareSubCollection(ArrayList<Set<RoundPiece>> collection){
    	Set<RoundPiece> finalSet = new HashSet<RoundPiece>();
    	for (RoundPiece p: collection.get(0)){
    		finalSet.add(p); //First set as it is
    	}
    	for (int i=0; i<collection.size()-1; i++){
    		for (int j=1; j<collection.size(); j++){
    			for (RoundPiece p1: collection.get(i)){
    				for (RoundPiece p2: collection.get(j)){
    					int r1 = p1.getRow();
    					int c1 = p1.getColumn();
    					int r2 = p2.getRow();
    					int c2 = p2.getColumn();
    					if (r1 == r2 && c1 == c2){ //Common Piece in 2 sets
    						for (RoundPiece p11: collection.get(i)){ //Each piece in set 1
    							if (finalSet.contains(p11)){ //If final set has that piece
    								for (RoundPiece add1: collection.get(i)){
    									finalSet.add(add1); //Add entire set 1 to final set
    								}
    							
    							}
    						}
    						for (RoundPiece p21:collection.get(j)){//Each piece in set 2
    							if (finalSet.contains(p21)){ //If final set has that piece
    								for (RoundPiece add2:collection.get(j)){
    									finalSet.add(add2); //Add entire set 2 to final set
    								}
    							}
    						}
    						
    					}
    				}
    			}
    		}
    	}
    	return finalSet;
    }
    
    
    /**
     * Compares a set to the pieceList. Returns true only if set contains each piece in the pieceList
     * @param p
     * @param s
     * @return true or false
     */
    public boolean compareSetAndPieceList(ArrayList<RoundPiece> p, Set<RoundPiece> s){   	
    	for (RoundPiece i: p){
    		if (! s.contains(i)) return false;
    	}
    	return true;
    }
}