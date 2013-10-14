/*
 * Author: Corey Crooks														   *
 * Purpose: Constructs a board from a count by count array of blocks; 		   *
 * 			hamming finds the number of blocks out of place. 			       *
 * 			manhattan is sum between blocks and goal + uses and finds neighbor.* 
 * Date: 10/8/2013															   *
 *******************************************************************************/

import java.util.Iterator;
import java.util.Stack;


public class Board {
    private int[][] board;
    private int N;
    private int posX; 
    private int posY;
    private int hammingVal;
    private Stack<Board> neighbors = new Stack<Board>(); // may use this to store iterator. 
    private int manhattanVal;
    
    public Board()
    {
    	
    }
    public Board(int[][] blocks)         
    {
    	setUpBoard(blocks); 

        hammingVal = getHamming(board);
        manhattanVal = getManhattan(board);
	

    }
    
    // call from constructor to set up the board for every board. 
    public void setUpBoard(int[][] blocks)
    {
    N = blocks[1].length;
	
		// creates an initial board. 
        board = new int[N][N];		
        for (int i = 0; i < N; i++) {	
            for (int j = 0; j < N; j++) {
            	
            	// dimensions of the board 
                board[i][j] = blocks[i][j]; 
                if (blocks[i][j] == 0) {
                    posX = i;
                    posY = j; 
                }
            }
        }
    }    
  //--------------------------------Calculate Manhattan Distance first ---------------------------------------
     //The sum of the Manhattan distances 
    //(sum of the vertical and horizontal distance) from the blocks to their goal positions, 
   //plus the number of moves made so far to get to the search node.
   private int getManhattan(int[][] blocks) { 
       int distance = 0;
       int boardPosX = 0; 
       int boardPosY = 0; 
		
       for (int i = 0; i < N; i++) {
         distance = optimizedDistance(boardPosX, boardPosY, distance, blocks, i);
       }
       return distance;

   }
   
   
   
   //-------------------------------Calculate Hamming Distance Second ------------------------------------
   
   
   //The number of blocks in the wrong position, plus the number of moves made so far to get to the search node. Intuitively, 
   //a search node with a small number of blocks in the wrong position is close to the goal, 
   //and we prefer a search node that have been reached using a small number of moves.
   private int getHamming(int[][] blocks) { 
       int distance = 0;
       int test = 1;
       for (int i = 0; i < N; i++) {
          distance = optimizatedHamming(distance, test, i, blocks);
       }
		
       return distance - 1;

   }

 
   
   
   
   // use to make the copy of an array
    private int[][] makeCopy(int[][] x, int i1, int j1, int i2, int j2) {
        int m = x[1].length;
        int[][] y = new int[m][m];
        int t;
        for (int i = 0; i < m; i++) {
            System.arraycopy(x[i], 0, y[i], 0, m);
        }
        t = y[i1][j1];
        y[i1][j1] = y[i2][j2];
        y[i2][j2] = t;
        return y;
    }

    public int dimension()                 // board dimension N
    {
        return N;
    }

    public int hamming()                   // number of blocks out of place
    {
        return hammingVal;

    }
    // returns the sum 
    public int manhattan()              
    {
        return manhattanVal;
    }

    
    // way to optimize compare method but still n^2 
    // check out other equals for more optimal calculation != n^2 
    public boolean otimizedEqual(Object y) {
        if (y == null) {
          throw new IllegalArgumentException();
        }
        Board b = (Board)y;  
        if ((this.board == null) && (b.board == null)) return true;
        if (this.board.length != b.board.length) return false;
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++)
          {
            if (this.board[i][j] != b.board[i][j]) return false;
          }
        }
        return true;
      }
    
    
    
    // check to see if at the goal board.
    public boolean isGoal()               
    {
        return manhattanVal == 0;
    }

    

    // method to try to reduce the time of the manhattan. 
    private int optimizedDistance(int boardPosX, int boardPosY, int distance, int [][]blocks, int i)
    {
 	   for (int j = 0; j < N; j++) {
            if (blocks[i][j] == 0) 
            continue;
 			
            else{
            boardPosX = 1 + (blocks[i][j] - 1) / N;
            boardPosY = blocks[i][j] - N * (boardPosX - 1);
            distance += Math.abs(boardPosX - i - 1) + Math.abs(boardPosY - j - 1);
 			}
        }
 	   return distance;
    }

    // check to see if the board is equal. 
    public boolean equals(Object obj)       
    {
        if ((obj == null) || (obj.getClass() != this.getClass())) 
        return false;
        
        Board board = (Board) obj; 
        if (board.dimension() != this.dimension()) return false;
        if (obj == this) return true;
        return board.toString().equals(this.toString());
    }
 // string representation of the board (in the output format specified below)
    public String toString() {
      StringBuilder s = new StringBuilder();
      s.append(N + "\n");
      for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
              s.append(String.format("%2d ", board[i][j]));
          }
          s.append("\n");
      }
      return s.toString();    
    }
    // method for reducing the time for hamming execution; 
    private int optimizatedHamming(int distance, int test, int i, int[][]blocks)
    {
 	   for (int j = 0; j < N; j++) {
            if (blocks[i][j] != test) distance++;
            test++;
        }
 	   return distance; 
    }
    
  //iterator class for moving through the position array on the board. 
    private class iterator implements Iterator<Board> {
        private int count, total, pointer;
        private int[] position;

        public iterator() {
            count = 0;
            position = new int[4];
            total = 0;
            pointer = 0;
            if (posX == N - 1) position[0] = 0;
            else {
                position[0] = 1;
                total++;
            }
            if (posX == 0) position[1] = 0;
            else {
                position[1] = 1;
                total++;
            }
            if (posY == N - 1) position[2] = 0;
            else {
                position[2] = 1;
                total++;
            }
            if (posY == 0) position[3] = 0;
            else {
                position[3] = 1;
                total++;
            }

        }

        @Override
        public boolean hasNext() {
            return count < total;
        }

        @Override
        public Board next() {
            while (position[pointer] == 0 && pointer < 4) pointer++;
            if (pointer == 0) {
                Board x = new Board(makeCopy(board, posX, posY, posX + 1, posY));
                count++;
                pointer++;
                return x;
            }

            if (pointer == 1) {
                Board x = new Board(makeCopy(board, posX, posY, posX - 1, posY));
                count++;
                pointer++;
                return x;
            }
            if (pointer == 2) {
                Board x = new Board(makeCopy(board, posX, posY, posX, posY + 1));
                count++;
                pointer++;
                return x;
            }
            if (pointer == 3) {
                Board x = new Board(makeCopy(board, posX, posY, posX, posY - 1));
                count++;
                pointer++;
                return x;
            }


            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Neighbor implements Iterable<Board> {

        @Override
        public Iterator<Board> iterator() {
            return new iterator();
        }
    }

    public Iterable<Board> neighbors()     // all neighboring boards
    {
        return new Neighbor();
    }
    
    

    
}