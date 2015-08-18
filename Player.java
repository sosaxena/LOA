package hw10;

//Programmers: Soumya Saxena, Nan-Kun Wu

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * 
 * @author Soumya Saxena and Nan-Kun Wu
 *
 */
public abstract class Player {
	
	/**
	 * Instance Variables
	 */

    ArrayList<int[]> moveList = new ArrayList<int[]>();
	ArrayList<RoundPiece> pieceOnDiagonal = new ArrayList<RoundPiece>();
	ArrayList<RoundPiece> pieceOnRevDiagonal = new ArrayList<RoundPiece>();
	
	
	/**
	 * Add a piece to pieceList
	 * @param pieceList
	 * @param p
	 */
	public void addPiece(ArrayList<RoundPiece> pieceList, RoundPiece p){
		pieceList.add(p);
	}
	
	
	/**
	 * Delete a piece from a piece list.
	 * @param pieceList
	 * @param p
	 */
	public void deletePiece(ArrayList<RoundPiece> pieceList, RoundPiece p){		
		pieceList.remove(p);
	}
	 
	
	/**
	 * Returns true of a target is empty or occupied by opposite color.
	 * @param pieceList
	 * @param p
	 * @param r
	 * @param c
	 * @return true/false
	 */
	public boolean isOccupied(ArrayList<RoundPiece> pieceList, RoundPiece p, int r, int c){//return false if occupied by same color	
		//check if there is another piece on the target spot, if same color return false, if different color move and kill the piece
		for (int k=0; k<pieceList.size(); k++){
			if (pieceList.get(k).getRow() == r && pieceList.get(k).getColumn() == c && pieceList.get(k).color == p.color) return false;
			else if (pieceList.get(k).getRow() == r && pieceList.get(k).getColumn() == c && pieceList.get(k).color != p.color){
				return true;
			}
		}
		return true;
	}
		
	/**
	 * Returns number of pieces on the same row.
	 * @param pieceList
	 * @param p
	 * @return pieceOnSameRow
	 */
	public int getNumberOnSameRow(ArrayList<RoundPiece> pieceList, RoundPiece p){
		int pieceOnSameRow = 0;
		for (int i=0; i<pieceList.size(); i++){
			if (pieceList.get(i).getRow() == p.getRow()){
				pieceOnSameRow += 1;
			}	
		}
		return pieceOnSameRow;
	}
	
	
	/**
	 * Returns number of pieces on the same column.
	 * @param pieceList
	 * @param p
	 * @return pieceOnSameColumn
	 */
	public int getNumberOnSameColumn(ArrayList<RoundPiece> pieceList, RoundPiece p){
		int pieceOnSameCol = 0;
		for (int i=0; i<pieceList.size(); i++){
			if (pieceList.get(i).getColumn() == p.getColumn()){
				pieceOnSameCol += 1;
			}
		}
		return pieceOnSameCol;
	}
	
	
	
	/**
	 * Returns number of pieces on the same main diagonal.
	 * @param pieceList
	 * @param p
	 * @return pieceOnSameDiagonal
	 */
	public int getNumberOnDiagonal(ArrayList<RoundPiece> pieceList, RoundPiece p){
		int n = p.getRow() + p.getColumn();
		int pieceOnDiagonal = 0;
		for (int i=0; i<pieceList.size(); i++){
			if (pieceList.get(i).getRow() + pieceList.get(i).getColumn() == n){
				pieceOnDiagonal += 1;
			}
		}
		return pieceOnDiagonal;			
	}
	
	
	/**
	 * Returns number of pieces on the same reverse diagonal.
	 * @param pieceList
	 * @param p
	 * @return pieceOnSameRevDiagonal
	 */
	public int getNumberOnRevDiagonal(ArrayList<RoundPiece> pieceList, RoundPiece p){
		int n = p.getColumn() - p.getRow();
		int pieceOnRevDiagonal = 0;
		for (int i=0; i<pieceList.size(); i++){
			if (pieceList.get(i).getColumn() - pieceList.get(i).getRow() == n){
				pieceOnRevDiagonal += 1;
			}
		}
		return pieceOnRevDiagonal;
	}
	
	
	/**
	 * Returns false if blocked else true.
	 * @param pieceList
	 * @param p
	 * @param r
	 * @return boolean true/false
	 */
	public boolean checkBlockColumn(ArrayList<RoundPiece> pieceList, RoundPiece p, int r){//return false if blocked; r= target row	
		if (p.getRow() - r > 0){//moving upwards
			for (RoundPiece element: pieceList){  //  target<element<p
				if (element.getColumn() == p.getColumn() && element.getRow() - r < p.getRow() - r && element.color != p.color && element.getRow() - r > 0) return false;				
			}
		}
		else if (p.getRow() - r < 0){//moving downwards
			for (RoundPiece element: pieceList){ //   p<element<target
				if (element.getColumn() == p.getColumn() && element.getRow() - r > p.getRow() - r && element.color != p.color && element.getRow() - r < 0) return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Returns false if blocked else true.
	 * @param pieceList
	 * @param p
	 * @param r
	 * @return boolean true/false
	 */
	public boolean checkBlockRow(ArrayList<RoundPiece> pieceList, RoundPiece p, int c){//return false if blocked, c = target column
		
		if (p.getColumn() - c > 0){//moving to the left
			for (RoundPiece element: pieceList){ //IMP :Basically the element is between the column of p and target i.e.  targer<element<p
				if (element.getRow() == p.getRow() && element.getColumn() - c < p.getColumn() - c && element.color != p.color && element.getColumn() - c > 0) return false;
			}
		}
		else if (p.getColumn() - c < 0){//moving to the right
			for (RoundPiece element: pieceList){//IMP :Basically the element is between the column of p and target i.e.  p<element<target
				if (element.getRow() == p.getRow() && c - element.getColumn() < c - p.getColumn() && element.color != p.color && element.getColumn() - c < 0) return false;
			}
		}
		return true;	
	}
	
	
	/**
	 * Returns false if blocked else true.
	 * @param pieceList
	 * @param p
	 * @param r
	 * @return boolean true/false
	 */
	public boolean checkBlockDiagonal(ArrayList<RoundPiece> pieceList, RoundPiece p, int n){//return false if blocked, c = targeted column	
		if (p.getColumn() - n > 0 && p.getRow() + (p.getColumn() - n) <= 7){//moving to the lower left
			for (RoundPiece element: pieceList){
				if (element.getColumn() + element.getRow() == p.getColumn() + p.getRow() && element.getColumn() - n > 0){
					if (element.getColumn() - n < p.getColumn() && element.color != p.color) return false;
				}
			}
		}
		if (p.getColumn() - n < 0 && p.getRow() - (n - p.getColumn()) >= 0){//moving to the upper right
			for (RoundPiece element: pieceList){
				if (element.getColumn() + element.getRow() == p.getColumn() + p.getRow() && element.getColumn() - n < 0){
					if (element.getColumn() - n > p.getColumn() - n && element.color != p.color) return false;
				}
			}
		}
		return true;	
	}
	
	
	/**
	 * Returns false if blocked else true.
	 * @param pieceList
	 * @param p
	 * @param r
	 * @return boolean true/false
	 */
	public boolean checkBlockRevDiagonal(ArrayList<RoundPiece> pieceList, RoundPiece p , int n){//return false if blocked, c = targeted column	
		if (p.getColumn() - n > 0 && p.getRow() - (p.getColumn() - n) >= 0){//moving to the upper left
			for (RoundPiece element: pieceList){
				if (element.getColumn() - element.getRow() == p.getColumn() - p.getRow() && element.getColumn() - n >0){
					if (element.getColumn() - n < p.getColumn() - n && element.color != p.color) return false;
				}
			}
		}
		if (p.getColumn() - n < 0 && p.getRow() + (n - p.getColumn()) <= 7){//moving to the lower right
			for (RoundPiece element: pieceList){
				if (element.getColumn() - element.getRow() == p.getColumn() - p.getRow() && element.getColumn() - n < 0){
					if(element.getColumn() - n > p.getColumn() - n && element.color != p.color) return false;
				}
			}
		}
		return true;
	}
	
	
	/**
	 * Adds all possible moves along a row to moveList.
	 * @param pieceList
	 * @param p
	 */
	public void getMoveRow(ArrayList<RoundPiece> pieceList, RoundPiece p){
		int c = getNumberOnSameRow(pieceList, p);
		if (p.getColumn() + c <= 7 && p.getColumn() - c >= 0){//move right OK, move left OK
			if (checkBlockRow(pieceList, p, p.getColumn() + c)){//check block when move to right
				if (isOccupied(pieceList, p, p.getRow(), p.getColumn() + c)){ //check if spot is empty or if it is occupied by opposite color
					int[] target = {p.getRow(), p.getColumn() + c};
					moveList.add(target);	
				}
			}
			if (checkBlockRow(pieceList, p, p.getColumn() - c)){//check block when move to left
				if (isOccupied(pieceList, p, p.getRow(), p.getColumn() - c)){
					int[] target = {p.getRow(), p.getColumn() - c};
					moveList.add(target);
				}
			}
		}
		else if (p.getColumn() + c <= 7 && p.getColumn() - c < 0){//can only move to right
			if (checkBlockRow(pieceList, p, p.getColumn() + c)){
				if (isOccupied(pieceList, p, p.getRow(), p.getColumn() + c)){
					int[] target = {p.getRow(), p.getColumn() + c};
					moveList.add(target);
				}
			}
		}
		else if (p.getColumn() + c > 7 && p.getColumn() - c >= 0){//can only move to left
			if (checkBlockRow(pieceList, p, p.getColumn() - c)){
				if (isOccupied(pieceList, p, p.getRow(), p.getColumn() - c)){
					int[] target = {p.getRow(), p.getColumn() - c};
					moveList.add(target);
				}
			}
		}
	}
	
	
	
	/**
	 * Adds all possible moves along a column to moveList.
	 * @param pieceList
	 * @param p
	 */
	public void getMoveColumn(ArrayList<RoundPiece> pieceList, RoundPiece p){
		int r = getNumberOnSameColumn(pieceList, p);
		if (p.getRow() + r <= 7 && p.getRow() - r >= 0){// can move up and down
			if (checkBlockColumn(pieceList, p, p.getRow() + r)){//move down
				if (isOccupied(pieceList, p, p.getRow() + r, p.getColumn())){
					int[] target = {p.getRow() + r, p.getColumn()};
					moveList.add(target);			
				}
			}
			if (checkBlockColumn(pieceList, p, p.getRow() - r)){//move up
				if (isOccupied(pieceList, p, p.getRow() - r, p.getColumn())){
					int[] target = {p.getRow() - r, p.getColumn()};
					moveList.add(target);
				}	
			}
		}
		else if (p.getRow() + r <= 7 && p.getRow() - r < 0){//can only move down
			if (checkBlockColumn(pieceList, p, p.getRow() + r)){
				if (isOccupied(pieceList, p, p.getRow() + r, p.getColumn())){
					int[] target = {p.getRow() + r, p.getColumn()};
					moveList.add(target);					
				}
			}
		}
		else if (p.getRow() + r > 7 && p.getRow() - r >= 0){//can only move up
			if (checkBlockColumn(pieceList, p, p.getRow() - r)){
				if (isOccupied(pieceList, p, p.getRow() - r, p.getColumn())){
					int[] target = {p.getRow() - r, p.getColumn()};
					moveList.add(target);				
				}
			}
		}		
	}
	
	
	/**
	 * Adds all possible moves along a main diagonal to moveList.
	 * @param pieceList
	 * @param p
	 */
	public void getMoveDiagonal(ArrayList<RoundPiece> pieceList, RoundPiece p){
		int d = getNumberOnDiagonal(pieceList, p);
		if (p.getColumn() + d <= 7 && p.getRow() - d >= 0){//can move to upper right
			if (checkBlockDiagonal(pieceList, p, p.getColumn() + d)){
				if (isOccupied(pieceList, p, p.getRow() - d, p.getColumn() + d)){
					int[] target = {p.getRow() - d, p.getColumn() + d};
					moveList.add(target);
				}
			}
		}
		if (p.getColumn() - d >=0 && p.getRow() + d <=7){//can move to lower left
			if (checkBlockDiagonal(pieceList, p, p.getColumn() - d)){
				if (isOccupied(pieceList, p, p.getRow() + d, p.getColumn() - d)){
					int[] target = {p.getRow() + d, p.getColumn() - d};
					moveList.add(target);
				}
			}
		}
	}
	
	
	
	/**
	 * Adds all possible moves along a reverse diagonal to moveList.
	 * @param pieceList
	 * @param p
	 */
	public void getMoveRevDiagonal(ArrayList<RoundPiece> pieceList, RoundPiece p){
		int d = getNumberOnRevDiagonal(pieceList, p);
		if (p.getColumn() + d <= 7 && p.getRow() + d <= 7){//can move to the lower right
			if (checkBlockRevDiagonal(pieceList, p, p.getColumn() + d)){
				if (isOccupied(pieceList, p, p.getRow() + d, p.getColumn() + d)){
					int[] target = {p.getRow() + d, p.getColumn() + d};
					moveList.add(target);
				}
			}
		}
		if (p.getColumn() - d >= 0 && p.getRow() - d >= 0){//can move to the upper left
			if (checkBlockRevDiagonal(pieceList, p, p.getColumn() - d)){
				if (isOccupied(pieceList, p, p.getRow() - d, p.getColumn() - d)){
					int[] target = {p.getRow() - d, p.getColumn() - d};
					moveList.add(target);
				}
			}
		}
	}
	
	
	/**
	 * Returns coordinates of possible moves in terms of the rows and columns numbers.
	 */
	@Override
    public String toString(){
		String move = "";
		for (int i=0; i<moveList.size(); i++){
			move += "("+moveList.get(i)[0]+","+moveList.get(i)[1]+")"; 			
		}
		return move;
	}

}