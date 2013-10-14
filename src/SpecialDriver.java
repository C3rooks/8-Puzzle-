 /*
 *Author: Corey Crooks												           *
 *Date: 10/6/2013													           *
 *Email: ccc35@pitt.edu											               *
 *Purpose: To serve as a driver for Hamming and Manhattan Metrics using the A* * 
 * 	 	   Algorithm to solve the 8 puzzle. 	 				               *					
 *******************************************************************************/

import edu.princeton.cs.algs4.Stopwatch;
//import edu.princeton.cs.introcs.In;

public class SpecialDriver {

public static void main(String[] args){
    
	boolean hamming = false;  
	boolean manhattan = false; 
	
    if (args.length == 0){
    	System.out.println("USAGE: java SpecialDriver -m file.txt   //For Manhattan Metric");
        System.out.println("OR:    java SpecialDriver -h file.txt   //For Hamming Metric");
      return;
    } else if ((args.length == 1) && ((args[0] != "-m") || (args[0] != "-h"))){
      System.out.println("Error No Command Line Flag Example: -m or -h");
      System.out.println("USAGE: java SpecialDriver -m file.txt   //For Manhattan Metric");
      System.out.println("OR:    java SpecialDriver -h file.txt   //For Hamming Metric");
     
    } else if (args.length == 2){
      if (args[0].equals("-h")){
    	hamming = true; 
        System.out.println("Using Hamming Metric");
        readFile(args[1],hamming, manhattan); 
      } else if (args[0].equals("-m")){
        hamming = false; 
        manhattan = true;  
        System.out.println("Using Manhattan Metric");
        readFile(args[1],hamming, manhattan); 
      } }
        
    
    
  }

	public static void readFile(String file, boolean hamming, boolean manhattan)
	{
		
		Stopwatch s = new Stopwatch(); 
	
	    In in = new In(file);
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	                             
	    for (int i = 0; i < N; i++)
	       for (int j = 0; j < N; j++)
	          blocks[i][j] = in.readInt();
	                          
	    Board initial = new Board(blocks);      // solve the puzzle

	    Solver solver = new Solver(initial);    // print solution to standard output
	      
	    if (!solver.isSolvable())//This should call this.isSolvable to save the board the trouble
	       System.out.println("No solution possible");
	    else {
	       System.out.println("Minimum number of moves = " + solver.moves());
	     
	       for (Board board : solver.solution()){
	    	   
	            System.out.println(board);
	            if(hamming == true) System.out.println("Hamming = " + board.hamming()); 
	           
	            else if(manhattan == true)
	            System.out.println("Manhattan = " + board.manhattan()); 
	       }
	       
	    }
	    
	    
	    // used only for complexity testing. 
	    System.out.println("Elapsed time: " +  s.elapsedTime()); 
	    
	}




}
