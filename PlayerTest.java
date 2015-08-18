package hw10;
//Programmers: Soumya Saxena, Nan-Kun Wu
import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	
	/**
	 * Declaring variables
	 */
	
	RoundPiece t1,t2,t3,t4,t5,t6,t7,t8,t9, t10, t11, t12, t13;
	Board board = new Board(8, 8);
	Nan nan = new Nan();
	ArrayList<RoundPiece> test = new ArrayList<RoundPiece>();
	ArrayList<RoundPiece> main = new ArrayList<RoundPiece>();
	String list = "";
	
	/**
	 * Set up the board with pieces to test
	 * @throws Exception
	 */

	@Before
	public void setUp() throws Exception {
		
		t1 = new RoundPiece(Color.WHITE); board.place(t1, 3, 2);		
		t2 = new RoundPiece(Color.WHITE); board.place(t2, 2, 3);
		t3 = new RoundPiece(Color.WHITE); board.place(t3, 3, 5);
		t8 = new RoundPiece(Color.WHITE); board.place(t8, 2, 5);
		t13 = new RoundPiece(Color.WHITE); board.place(t13, 6, 3);
		
		t4 = new RoundPiece(Color.BLACK); board.place(t4, 4, 2);
		t5 = new RoundPiece(Color.BLACK); board.place(t5, 1, 4);
		t6 = new RoundPiece(Color.BLACK); board.place(t6, 5, 4);
		t7 = new RoundPiece(Color.BLACK); board.place(t7, 6, 5); 
		t9 = new RoundPiece(Color.BLACK); board.place(t9, 3, 6);
		t10 = new RoundPiece(Color.BLACK); board.place(t10, 0, 4);
		t11 = new RoundPiece(Color.BLACK); board.place(t11, 6, 2);
		t12 = new RoundPiece(Color.BLACK); board.place(t12, 4, 4);
		
		main.add(t1);
		main.add(t2);
		main.add(t3);
		main.add(t4);
		main.add(t5);
		main.add(t6);
		main.add(t7);
		main.add(t8);
		main.add(t9);
		
		test.add(t1);
		test.add(t2);
		test.add(t3);
		test.add(t4);
		test.add(t5);
		test.add(t6);
		test.add(t7);
		test.add(t8);
		test.add(t9);	
	}

	@Test
	public void testAddPiece() {
		//add piece 
		main.add(t10);
		test.add(t10);
		assertEquals(test, main);
	}

//	@Test
//	public void testDeletePiece() {
//		nan.deletePiece(t9);	
//		test.remove(t9);
//		assertEquals(test, nan.pieceList);
//	}

	@Test
	public void testIsOccupied() {
		
		//Whether a block at row r and column c is occupied or not
		assertFalse(nan.isOccupied(main, t1, 2, 3));//occupied by same color
		assertFalse(nan.isOccupied(main, t1, 3, 2));//occupied by itself
		assertTrue(nan.isOccupied(main, t1, 2, 2));//empty spot
		assertTrue(nan.isOccupied(main, t1, 4, 2));//occupied by different color
//		test.remove(t4);
//		assertEquals(test, nan.pieceList);
	}

	@Test
	public void testGetNumberOnSameRow() {
		// Number of moves on the same row returned by function are equal or not.
		assertEquals(3, nan.getNumberOnSameRow(main, t1));
	}

	@Test
	public void testGetNumberOnSameColumn() {
		// Number of moves on the same column returned by function are equal or not.
		main.add(t11);
		assertEquals(2, nan.getNumberOnSameColumn(main, t5));
		assertEquals(3, nan.getNumberOnSameColumn(main, t3));
		assertEquals(3, nan.getNumberOnSameColumn(main, t1));
	}

	@Test
	public void testGetNumberOnDiagonal() {
		// Number of moves on the same diagonal returned by function are equal or not.
		assertEquals(3, nan.getNumberOnDiagonal(main, t1));
	}

	@Test
	public void testGetNumberOnRevDiagonal() {
		// Number of moves on the same reverse diagonal returned by function are equal or not.
		assertEquals(3, nan.getNumberOnRevDiagonal(main, t6));
		assertEquals(3, nan.getNumberOnRevDiagonal(main, t9));		
	}

	@Test
	public void testCheckBlockRow() {
		main.add(t11);
		assertTrue(nan.checkBlockRow(main, t2, 6));
		assertFalse(nan.checkBlockRow(main, t9, 4));	
		assertTrue(nan.checkBlockRow(main, t11, 4));
		assertTrue(nan.checkBlockRow(main, t11, 0));
		assertFalse(nan.checkBlockRow(main, t1, 7));
		assertFalse(nan.checkBlockRow(main, t9, 1));
		
	}

	@Test
	public void testCheckBlockColumn() {
		main.add(t10);
		nan.moveList.remove(t7);
		assertTrue(nan.checkBlockColumn(main, t10, 2));
		assertFalse(nan.checkBlockColumn(main, t1, 7));
		assertTrue(nan.checkBlockColumn(main, t8, 4));
		assertFalse(nan.checkBlockColumn(main, t4, 1));
		assertFalse(nan.checkBlockColumn(main, t8, 7));
	}

	@Test
	public void testCheckBlockDiagonal() {
		
		assertTrue(nan.checkBlockDiagonal(main, t2, 0));
		assertFalse(nan.checkBlockDiagonal(main, t2, 5));
		assertFalse(nan.checkBlockDiagonal(main, t1, 5));
		assertFalse(nan.checkBlockDiagonal(main, t5, 1));
		main.add(t11);
		assertFalse(nan.checkBlockDiagonal(main, t11, 7));
		main.add(t12);
		assertFalse(nan.checkBlockDiagonal(main, t11, 6));// bug to fix
		main.add(t13);
		assertFalse(nan.checkBlockDiagonal(main, t9, 2));
		
	}

	@Test
	public void testCheckBlockRevDiagonal() {
		assertFalse(nan.checkBlockRevDiagonal(main, t7, 1));
		assertFalse(nan.checkBlockRevDiagonal(main, t5, 7));
		assertFalse(nan.checkBlockRevDiagonal(main, t6, 1));
	}

	@Test
	public void testGetMoveRow() {
		// test possible moves along a row.
//		ArrayList<int[]> movet11 = new ArrayList<int[]>();
		ArrayList<int[]> movet5 = new ArrayList<int[]>();
		main.add(t11);
		main.add(t12);

//		int[] m1 = {6,4};
//		int[] m2 = {6,0};
		int[] m1 = {1,5};
		int[] m2 = {1,3};
		movet5.add(m1);
		movet5.add(m2);
//		nan.getMoveRow(t11);
		nan.getMoveRow(main, t5);
		String test = "";
		for (int[] element: movet5){
			test += "("+element[0]+","+element[1]+")";
		}
		
		System.out.println(test);
		System.out.println(nan.toString());
		System.out.println("there are "+nan.moveList.size()+" legal moves");
				
		assertEquals(nan.toString(), test);		
	}

	@Test
	public void testGetMoveColumn() {
		// test possible moves along a column
		ArrayList<int[]> movet3 = new ArrayList<int[]>();
		ArrayList<int[]> movet9 = new ArrayList<int[]>();
		ArrayList<int[]> movet1 = new ArrayList<int[]>();
		main.add(t11);
		
		nan.getMoveColumn(main, t3);
		String test = "";
		int[] m1 = {6,5};
		int[] m2 = {0,5};
		movet3.add(m1);
		movet3.add(m2);
		for (int[] element: movet3){
			test += "("+element[0]+","+element[1]+")";
		}
		assertEquals(nan.toString(), test);
		nan.moveList.clear();
		
		nan.getMoveColumn(main, t9);
		String test1 = "";
		int[] m3 = {4,6};
		int[] m4 = {2,6};
		movet9.add(m3);
		movet9.add(m4);
		for (int[] element: movet9){
			test1 += "("+element[0]+","+element[1]+")";
		}
		assertEquals(nan.toString(), test1);
		nan.moveList.clear();
				
		nan.getMoveColumn(main, t1);
		String test2 = "";
		int[] m5 = {0,2};
		movet1.add(m5);
		for (int[] element: movet1){
			test2 += "("+element[0]+","+element[1]+")";
		}
		assertEquals(nan.toString(), test2);		
	}

	@Test
	public void testGetMoveDiagonal() {
		// test possible moves along a main diagonal
		ArrayList<int[]> movet2 = new ArrayList<int[]>();
		ArrayList<int[]> movet6 = new ArrayList<int[]>();
		ArrayList<int[]> movet11 = new ArrayList<int[]>();
		main.add(t11);
		
		nan.getMoveDiagonal(main, t11);
		String test = "";
		int[] m1 = {4,4};
		movet11.add(m1);
		for (int[] element: movet11){
			test += "("+element[0]+","+element[1]+")";
		}
		assertEquals(nan.toString(), test);
		nan.moveList.clear();
		
		nan.getMoveDiagonal(main, t6);
		String test1 = "";
		int[] m3 = {7,2};
		movet6.add(m3);
		for (int[] element: movet6){
			test1 += "("+element[0]+","+element[1]+")";
		}
		assertEquals(nan.toString(), test1);
		nan.moveList.clear();
		
		nan.getMoveDiagonal(main, t2);
		String test2 = "";
		int[] m4 = {5,0};
		movet2.add(m4);
		for (int[] element: movet2){
			test2 += "("+element[0]+","+element[1]+")";
		}
		assertEquals(nan.toString(), test2);
	}

	@Test
	public void testGetMoveRevDiagonal() {
		//// test possible moves along a reverse diagonal
		ArrayList<int[]> movet2 = new ArrayList<int[]>();
		ArrayList<int[]> movet6 = new ArrayList<int[]>();
		ArrayList<int[]> movet11 = new ArrayList<int[]>();
		ArrayList<int[]> movet7 = new ArrayList<int[]>();
		main.add(t11);
		
		nan.getMoveRevDiagonal(main, t11);
		String test = "";
		int[] m1 = {5,1};
		int[] m0 = {7,3};
		movet11.add(m0);
		movet11.add(m1);
		for (int[] element: movet11){
			test += "("+element[0]+","+element[1]+")";
		}
		assertEquals(nan.toString(), test);
		nan.moveList.clear();
		
		nan.getMoveRevDiagonal(main, t6);
		String test1 = "";
		for (int[] element: movet6){
			test1 += "("+element[0]+","+element[1]+")";
		}
		assertEquals(nan.toString(), test1);
		nan.moveList.clear();
		
		nan.getMoveRevDiagonal(main, t7);
		String test2 = "";
		int[] m4 = {3,2};
		movet2.add(m4);
		for (int[] element: movet2){
			test2 += "("+element[0]+","+element[1]+")";
		}
		assertEquals(nan.toString(), test2);
		nan.moveList.clear();
		
		nan.getMoveRevDiagonal(main, t8);
		String test5 = "";
		assertEquals(nan.toString(), test5);
		
	}
}