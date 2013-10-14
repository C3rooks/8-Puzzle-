/*
 * Author: Corey Crooks														      *
 * Purpose: Class to find a solution to the initial board using the A* algorithm  *
 * 			- builds a min priority queue and sets the board initial 	          *
 * 			from the argument to the constructor					              *
 * Date: 10/10/2013 														      *
 **********************************************************************************/



public class Solver {
    private Board boardObject;
    private int steps; // movement posx to other pos steps
    private Queue<Board> queue;
    private MinPQ<boardObject> priority;
    private boolean boardCopy;
    int count = 0;


    public Solver(Board initial)            
    {
        priority = new MinPQ<boardObject>();
        boardObject = initial;
        buildBoardQueue(); // method to build the queue
     
    }


    public void solveBoard(int distance, boardObject prevBoard, boardObject newBoard)
    {
    	// when 0 then board is complete 
    	while (distance != 0) { 
            prevBoard = priority.delMin(); 
			
			
            count++;
            
            // use this for iteration over the board. 
    	for (Board board : prevBoard.getBoard().neighbors()) {
    		
            distance = board.manhattan();
            newBoard = new boardObject(prevBoard, board, distance + prevBoard.size());
            if (newBoard.replacement())  continue;

            priority.insert(newBoard);
            if (distance == 0) {
              
                    prevBoard = priority.delMin();
                    queue = newBoard.getBoardQueue();
                    steps = newBoard.size() - 1;
                    boardCopy = true;
                    break;
               
            }
        }
    }
    }
    
    // goes and builds the entire queue from ^ above
    private void buildBoardQueue()
    {
    	// check to see if the object for manhattan is 0 
    	 if (boardObject.manhattan() == 0) {
             queue = new Queue<Board>(); 
             queue.enqueue(boardObject); 
             steps = 0;
             boardCopy = true; 
         } 
 		else {
             boardCopy = false;
             steps = -1;
             solve(); 
         }

    }
    
    
    // step 1 to initialize the default board with the previous node 
    // then put the next board into the priority queue. 
    private void solve() {
    	   
    	// need to create two boards, one for previous and new. 
        boardObject prevBoard = null;
        boardObject newBoard; 
        int distance = -1;

		newBoard = new boardObject(null, boardObject, boardObject.manhattan()); 
        priority.insert(newBoard); 
		
      
        queue = newBoard.getBoardQueue(); 
        solveBoard(distance, prevBoard, newBoard);  // main heart to solve the application 

    }
    
    
    
    // methods needed for solving (posted on courseweb)
    public boolean isSolvable()             
    {
        return boardCopy;
    }

    
    // returns the number of moves for a solution
    // if the number = -1 then it is impossible for any moves. 
    public int moves()                      
    {
        return steps;
    }

    
    // return the queue with the final solution. 
    public Iterable<Board> solution()       
    {
        if (steps==-1) 
        return null;
        return queue;
    }

    
    
    
    
    
    
    
    
    
    private class boardObject implements Comparable<boardObject> {  //
        private int location;
        private Board newBoard;
        private Queue<Board> queue;
        private boolean check;

        // constructor to create a queue of boards 
        // first is the previous board and second is manhattan * NOTE 
        public boardObject(boardObject obj, Board board, int location) {
            check = false;
            queue = new Queue<Board>(); 

            this.newBoard = board; 
            if (obj != null) { 
                for (Object q : obj.getBoardQueue()) { 
                    queue.enqueue((Board) q);
                    if (q.equals(board)) 
					check = true; 
                }
            }
            if (check == false)
			queue.enqueue(board);

            this.location = location;

        }
     
        
        private Board getBoard() {
            return newBoard;
        }

      
        
        // return the copy // replacement 
        private boolean replacement() {
            return check;
        }

        
        // return board
        private Queue<Board> getBoardQueue() {
            return queue;
        }
        
        // entire size of the queue
        public int size() {
            return queue.size();
        }

       
        
  

        @Override
        public int compareTo(boardObject o) {
            return this.location - o.location;
        }
        
    }
}