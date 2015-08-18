package hw10;
//Programmers: Soumya Saxena, Nan-Kun Wu
import java.awt.Color;
import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SoumyaTest {
	//Declaration
	Board board;
	RoundPiece w1;
	RoundPiece w2;
	RoundPiece w3;
	RoundPiece w4;
	RoundPiece w5;
	RoundPiece w6;
	RoundPiece w7;
	RoundPiece w8;
	RoundPiece w9;
	RoundPiece w10;
	RoundPiece w11;
	RoundPiece w12;
	Soumya s;
	ArrayList<RoundPiece> pieceList ;
	
	

	@Before
	public void setUp() throws Exception {
		//Definition
		pieceList = new ArrayList<RoundPiece>();
		
		s= new Soumya();
		
		board = new Board(8,8);
		
		w1= new RoundPiece(Color.WHITE);
		board.place(w1, 2, 2);
		pieceList.add(w1);
		
		w2= new RoundPiece(Color.WHITE);
		board.place(w2, 1, 4);
		pieceList.add(w2);
		
		w3= new RoundPiece(Color.WHITE);
		board.place(w3, 1, 5);
		pieceList.add(w3);
		
		w4= new RoundPiece(Color.WHITE);
		board.place(w4, 3, 4);
		pieceList.add(w4);
		
		w5= new RoundPiece(Color.WHITE);
		board.place(w5, 3, 5);
		pieceList.add(w5);
		
		w6= new RoundPiece(Color.WHITE);
		board.place(w6, 2, 7);
		pieceList.add(w6);
		
		w7= new RoundPiece(Color.WHITE);
		board.place(w7, 4, 2);
		pieceList.add(w7);
		
		w8= new RoundPiece(Color.WHITE);
		board.place(w8, 5, 2);
		pieceList.add(w8);
		
		w9= new RoundPiece(Color.WHITE);
		board.place(w9, 6, 5);
		pieceList.add(w9);
		
		w10= new RoundPiece(Color.WHITE);
		board.place(w10, 3, 1);
		pieceList.add(w10);
		
		w11= new RoundPiece(Color.WHITE);
		board.place(w11, 5, 0);
		pieceList.add(w11);
		
		w12= new RoundPiece(Color.WHITE);
		board.place(w12, 6, 0);
		pieceList.add(w12);
		
		
		
		
		
	}

	@Test
	public void testSoumya() {
		
		// Test the constructor here
		
		assertEquals(pieceList.get(0).color,Color.WHITE);
		assertEquals(pieceList.get(1).color,Color.WHITE);
		assertEquals(pieceList.get(2).color,Color.WHITE);
		assertEquals(pieceList.get(3).color,Color.WHITE);
		assertEquals(pieceList.get(4).color,Color.WHITE);
		assertEquals(pieceList.get(5).color,Color.WHITE);
		assertEquals(pieceList.get(6).color,Color.WHITE);
		assertEquals(pieceList.get(7).color,Color.WHITE);
		assertEquals(pieceList.get(8).color,Color.WHITE);
		assertEquals(pieceList.get(9).color,Color.WHITE);
		assertEquals(pieceList.get(10).color,Color.WHITE);
		assertEquals(pieceList.get(11).color,Color.WHITE);
	}


	@Test
	public void testMakeMoveList() {
		//Check whether the list of all possible moves returned is correct.
		
		s.makeMoveList(pieceList,w1);
		
        int[] target1=s.moveList.get(0);
		
		assertEquals(2,target1[0]);
		assertEquals(4,target1[1]);
		
		int[] target2=s.moveList.get(1);
		
		assertEquals(2,target2[0]);
		assertEquals(0,target2[1]);
	
		
		int[] target3=s.moveList.get(2);
		
		assertEquals(0,target3[0]);
		assertEquals(4,target3[1]);
		
        int[] target4=s.moveList.get(3);
		
		assertEquals(4,target4[0]);
		assertEquals(0,target4[1]);
		
        int[] target5=s.moveList.get(4);
		
		assertEquals(3,target5[0]);
		assertEquals(3,target5[1]);
		
		
       int[] target6=s.moveList.get(5);
		
		assertEquals(1,target6[0]);
		assertEquals(1,target6[1]);
		
	}
		
		
	@Test
	public void testSearchAroundMove() {
		//Checks whether it returns correctly, the pieces immediately around a particular piece.
		s.makeMoveList(pieceList,w1);
		int[] move1 = s.moveList.get(0);
		int n1= s.searchAroundMove(pieceList,move1);
		assertEquals(4,n1);
		
		int[] move2 = s.moveList.get(1);
		int n2= s.searchAroundMove(pieceList,move2);
		assertEquals(1,n2);
		
		int[] move3 = s.moveList.get(2);
		int n3= s.searchAroundMove(pieceList,move3);
		assertEquals(2,n3);
		
		int[] move4 = s.moveList.get(3);
		int n4= s.searchAroundMove(pieceList,move4);
		assertEquals(2,n4);
		
		int[] move5 = s.moveList.get(4);
		int n5= s.searchAroundMove(pieceList,move5);
		assertEquals(3,n5);
		
		int[] move6 = s.moveList.get(5);
		int n6= s.searchAroundMove(pieceList,move6);
		assertEquals(1,n6);
	}

	@Test
	public void testOppositeColor() {
		//Checks whether it detects correctly, the presence of opposite colored piece at a target,
		
		s.makeMoveList(pieceList,w1);
		int[] m1 = s.moveList.get(0);
		
		assertTrue(s.OppositeColor(pieceList,m1)==false);
		
        int[] m2 = s.moveList.get(1);
		
		assertTrue(s.OppositeColor(pieceList,m2)==false);
		
        int[] m3 = s.moveList.get(2);
		
		assertTrue(s.OppositeColor(pieceList,m3)==false);
		
        int[] m4 = s.moveList.get(3);
		
		assertTrue(s.OppositeColor(pieceList,m4)==false);
		
        int[] m5 = s.moveList.get(4);
		
		assertTrue(s.OppositeColor(pieceList,m5)==false);
		
        int[] m6 = s.moveList.get(5);
		
		assertTrue(s.OppositeColor(pieceList,m6)==false);
		
	}

	@Test
	public void testPickBestMove() {
		//Checks whether it returns correctly, the best move.
		s.makeMoveList(pieceList,w1);
		int[] bestMove = s.pickBestMove(pieceList);
		assertEquals(2,bestMove[0]);
		assertEquals(4,bestMove[1]);
	}

	@Test
	public void testClearMoveList() {
		//Checks whether it clears the moveList successfully.
		s.clearMoveList();
		assertEquals(s.moveList.size(),0);
	}

}
