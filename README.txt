Name: Corey Crooks
Date: October 13, 2013 
Peoplesoft: 3395196
Email: ccc35@pitt.edu

Note: This README was written in TextWrangler, use for better formatting purposes.

Overview: 
 	This application is used to solve the 8-puzzle problem using the A* algorithm. The rules
 	are that you are permitted to slide blocks horizontally or vertically into the blank 
 	square. Two different forms of metrics are supposed to be applied when calculating the 
 	distance: The Manhattan Metric and the Hamming Metric; 
 	
 	The Manhattan Metric is the sum of the Manhattan distances (sum of the vertical and 
 	horizontal distance) from the blocks to their goal positions, plus the number of moves
 	made so far get to the search. 
 	
 	The Hamming Metric is the number of blocks in the wrong position, plus the number 
 	the number of moves made so far to get to the search node. Intuitively, a search node 
 	with a small number of blocks in the wrong position is close to the goal, and we 
 	prefer a search node that have been reached using a small number of moves.
 	
 Included Files:
 
 	Java Classes: 
 		Board.java  :: Used for the board class
 		Checker.java :: From Sedgewick for checking the files 
 		Solver.java :: Solves the puzzle 
 		SpecialDriver.java :: Test driver created for testing metrics
 	
 		(Used if needed and did not Import External from below)
 		StdIn.java
 		StdOut.java
 		StdRandom.java 
 		In.java
 		MinPQ.java
 		Queue.java
 	
 	
 	External (other files):
 		These are used for importing into project path instead of using extra classes. 
			1. algs-4-package.jar
	 		2. stdlib-package.jar 
			3. Puzzle00  - Puzzle45.txt (testing purposes)
	 				   
	 				   
	 				   
	 How-To-Execute: 
	 There are multiple ways to execute this application: 
	 1. Run the Checker.java class by the command line or terminal with the use of
	 	javac Checker.java 
	 	java Checker Puzzle*.txt 
	 	this will run through all of the files and test them.
	 	
	 2. Run the SpecialDriver.java class by the command line or terminal with the use of
	 	javac SpecialDriver.java
	 	java -m SpecialDriver puzzle00.txt   Where -m is for use of the Manhattan Metric
	 	or java -h SpecialDriver puzzle00.txt for hamming metric. 
	 	
	 
Problems: 
	There are no execution problems with this application, however there is a bottleneck
	when executing the Checker execution method around puzzle 31 but will execute further.
	I used Sedgewick's stopwatch to test optimization times on each of the puzzles and 
	notice that runtime around puzzle 31. I have learned a lot from this application, 
	such as how to implement the A* algorithm and also how to test my calculations 
	for optimization. 