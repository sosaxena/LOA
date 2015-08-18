//Programmers: Soumya Saxena, Nan-Kun Wu

package hw10;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class NanTest {
	
	Board board;
	RoundPiece wp1, wp2, wp3, wp4, wp5, bp1, bp2, bp3, bp4, bp5;
	ArrayList<RoundPiece> p = new ArrayList<RoundPiece>();
	ArrayList<int[]> testMoveList;
	Nan nan = new Nan();

	@Before
	public void setUp() throws Exception {
		board = new Board(8, 8);
		testMoveList = new ArrayList<int[]>();
		wp1 = new RoundPiece(Color.WHITE);
		wp2 = new RoundPiece(Color.WHITE);
		wp3 = new RoundPiece(Color.WHITE);
		wp4 = new RoundPiece(Color.WHITE);
		wp5 = new RoundPiece(Color.WHITE);
		
		bp1 = new RoundPiece(Color.BLACK);
		bp2 = new RoundPiece(Color.BLACK);
		bp3 = new RoundPiece(Color.BLACK);
		bp4 = new RoundPiece(Color.BLACK);
		bp5 = new RoundPiece(Color.BLACK);
		
		board.place(wp1, 4, 2);
		board.place(wp2, 6, 2);
		board.place(wp3, 3, 6);
		board.place(wp4, 6, 4);
		board.place(wp5, 4, 5);
		board.place(bp1, 3, 0);
		board.place(bp2, 3, 1);
		board.place(bp3, 2, 1);
		board.place(bp4, 2, 2);
		board.place(bp5, 1, 3);
		
		p.add(wp1);
		p.add(wp2);
		p.add(wp3);
		p.add(wp4);
		p.add(wp5);
		p.add(bp1);
		p.add(bp2);
		p.add(bp3);
		p.add(bp4);
		p.add(bp5);		
	}

	@Test
	public void testMakeMoveList() {//testing the makeMoveList method
		nan.makeMoveList(p, wp1);
		int[] m1 = {4, 4};
		int[] m2 = {4, 0};
		int[] m3 = {7, 2};
		int[] m4 = {3, 3};
		int[] m5 = {5, 1};
		int[] m6 = {7, 5};
		testMoveList.add(m1);
		testMoveList.add(m2);
		testMoveList.add(m3);
		testMoveList.add(m4);
		testMoveList.add(m5);
		testMoveList.add(m6);
		
		String test = "";
		
		for (int[] move: testMoveList){
			test += "("+move[0]+","+move[1]+")";
		}
		
		assertEquals(nan.toString(), test);
	}

	@Test
	public void testPickMoveRoundPiece() {//testing of picking the strategic move
		nan.makeMoveList(p, wp2);
		int[] m1 = nan.pickMove(wp2);
		String s1 = "("+m1[0]+","+m1[1]+")";
		int[] m2 = {3,2};		
		testMoveList.add(m2);
		String s2 = "("+m2[0]+","+m2[1]+")";
		assertEquals(s1, s2);		
	}

	@Test
	public void testClearMoveList() {//testing the method to clear the moveList
		nan.makeMoveList(p, wp3);
		assertFalse(nan.moveList.size() == 0);
		nan.clearMoveList();
		assertTrue(nan.moveList.size() == 0);		
	}
}
