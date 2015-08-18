package hw10;

//Programmers: Soumya Saxena, Nan-Kun Wu
import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * Check for a Board containing both white and black Pieces
 * 
 *
 */

public class CheckWinTest {
	//Declaration
	Board board;
	RoundPiece wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp9, bp1, bp2, bp3, bp4, bp5, bp6, bp7;
	ArrayList<RoundPiece> p;
	CheckWin check;
	

	@Before
	public void setUp() throws Exception {
		
		//Definition
		
		check = new CheckWin();
		p = new ArrayList<RoundPiece>();
		board = new Board(8,8);
		wp1 = new RoundPiece(Color.WHITE);
		wp2 = new RoundPiece(Color.WHITE);
		wp3 = new RoundPiece(Color.WHITE);
		wp4 = new RoundPiece(Color.WHITE);
		wp5 = new RoundPiece(Color.WHITE);
		wp6 = new RoundPiece(Color.WHITE);
		wp7 = new RoundPiece(Color.WHITE);
		wp9 = new RoundPiece(Color.WHITE);
		
		
		bp1 = new RoundPiece(Color.BLACK);
		bp2 = new RoundPiece(Color.BLACK);
		bp3 = new RoundPiece(Color.BLACK);
		bp4 = new RoundPiece(Color.BLACK);
		bp5 = new RoundPiece(Color.BLACK);
		bp6 = new RoundPiece(Color.BLACK);
		
		p.add(wp1);
		p.add(wp2);
		p.add(wp3);
		p.add(wp4);
		p.add(wp5);
		p.add(wp6);
		p.add(wp7);
		
		p.add(wp9);
		p.add(bp1);
		p.add(bp2);
		p.add(bp3);
		p.add(bp4);
		p.add(bp5);
		
		
		
		
		board.place(wp1, 4, 2);
		board.place(wp2, 6, 2);
		board.place(wp3, 3, 6);
		board.place(wp4, 6, 4);
		board.place(wp5, 4, 5);
		board.place(wp6, 5, 3);
		board.place(wp7, 5, 4);
		board.place(wp9, 4, 1);
		
		board.place(bp1, 3, 0);
		board.place(bp2, 3, 1);
		board.place(bp3, 2, 0);
		board.place(bp4, 2, 2);
		board.place(bp5, 1, 3);
		board.place(bp6, 0, 0);
		
		

		
		
		
		
	}

	@Test
	public void testGetBlackList() {
		
		//Test whether the list returned has all black pieces
		ArrayList<RoundPiece> bl = new ArrayList<RoundPiece>();
		bl= check.getBlackList(p);
		ArrayList<RoundPiece> test = new ArrayList<RoundPiece>();
		
		test.add(bp1);
		test.add(bp2);
		test.add(bp3);
		test.add(bp4);
		test.add(bp5);
		
		assertEquals(bl,test);
		
	}

	@Test
	public void testGetWhiteList() {
		
		//Test whether the list returned has all white pieces
		ArrayList<RoundPiece> wL = new ArrayList<RoundPiece>();
		wL = check.getWhiteList(p);
		ArrayList<RoundPiece> test = new ArrayList<RoundPiece>();
		test.add(wp1);
		test.add(wp2);
		test.add(wp3);
		test.add(wp4);
		test.add(wp5);
		test.add(wp6);
		test.add(wp7);
		test.add(wp9);
		assertEquals(test, wL);
	}

	@Test
	public void testCheckBlack() {
		//Black wins if all black pieces are connected
		assertTrue(check.checkBlack(p)==true);
	}
	
	@Test
	public void testMakeConnectList(){
		
		//Check the collection contains all sets of neighbors for each piece
		
	   Set<RoundPiece> set1,set2,set3,set4,set5,set6,set7,set8,set9;
		set1 = new HashSet<RoundPiece>();
		set2 = new HashSet<RoundPiece>();
		set3 = new HashSet<RoundPiece>();
		set4 = new HashSet<RoundPiece>();
		set5 = new HashSet<RoundPiece>();
		set6 = new HashSet<RoundPiece>();
		set7 = new HashSet<RoundPiece>();
		set8 = new HashSet<RoundPiece>();
		set9 = new HashSet<RoundPiece>();
		
		
		set1.add(wp6);
		set1.add(wp9);
		set1.add(wp1);
		
		set2.add(wp6);
		set2.add(wp2);
		
		set3.add(wp5);
		set3.add(wp3);
		
		set4.add(wp6);
		set4.add(wp7);
		set4.add(wp4);
		
		set5.add(wp3);
		set5.add(wp7);
		set5.add(wp5);
		
		set6.add(wp1);
		set6.add(wp2);
		set6.add(wp4);
		set6.add(wp7);
		set6.add(wp6);
		
		set7.add(wp4);
		set7.add(wp5);
		set7.add(wp6);
		set7.add(wp7);
		
		
		set9.add(wp1);
		set9.add(wp9);
		ArrayList<RoundPiece> wL = new ArrayList<RoundPiece>();
		wL=check.getWhiteList(p);
		ArrayList<Set<RoundPiece>> collection = check.makeConnectList(wL);
		
		assertEquals(collection.get(0),set1);
		assertEquals(collection.get(1),set2);
		assertEquals(collection.get(2),set3);
		assertEquals(collection.get(3),set4);
		assertEquals(collection.get(4),set5);
		assertEquals(collection.get(5),set6);
		assertEquals(collection.get(6),set7);
		
		assertEquals(collection.get(7),set9);
		
}

	@Test 
	public void testCompareSubCollection(){
		// Make sure collection formed is correct
		
		Set<RoundPiece> test = new HashSet<RoundPiece>();
		test.add(wp1);
		test.add(wp2);
		test.add(wp3);
		test.add(wp4);
		test.add(wp5);
		test.add(wp6);
		test.add(wp7);
        
		test.add(wp9);
		
		ArrayList<RoundPiece> wL = new ArrayList<RoundPiece>();
		wL=check.getWhiteList(p);
		ArrayList<Set<RoundPiece>> collection = check.makeConnectList(wL);
		assertEquals(test, check.compareSubCollection(collection));
	}
	
	
	
	@Test
	public void testCheckWhite() {
		
		//White Wins if true
		
		assertTrue(check.checkWhite(p));
	}

	

}