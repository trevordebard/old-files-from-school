There are 3 files included in this submission.

1. ReadMe.txt is this file

2. Successor.java is a java class that receives a list of numbers and computes the successor 
of a given list. Each method and line of code has a description of what it does, so I will
not go into detail of each one.


3. 	TestScript.sh is a shell script designed to test a few possible inputs from a user and
	and display the results. 
	The expected output is as follows:
		[1, 2, 3, 5]
		[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
		[9, 9, 0, 0, 0, 0, 0]
		java.lang.IllegalArgumentException: Must insert numbers less than 10 or…			greater than -1
		[1]
		[1, 0]
	As you can see, the script tests for the case where the last number is less than 9,
	all the numbers are 9, all but the second number is 9, the input is less than 0 or
	greater than 10, the input is 0, and the input is just 9. 
	
	
	HOW TO RUN TestScript.sh
		1. Open a bash shell such as terminal on Mac OS.
		2. Move to the directory where Successor.java is saved.
		3. Type "sh " and then the location of the directory where TestScript.sh is saved.
			(note: you can drag and drop TestScript.sh into Terminal to make this step 
			easier)
		4. The script should run, and you should see the expected output given above. 
		
		
HOW TO TEST THE CODE WITH YOUR OWN INPUT
	1. Create a file called "input.txt" in the same directory as Successor.java
	2. Insert the sequence of numbers that you want to test into the text file.
	3. Open a bash shell such as terminal on Mac OS.
	4. Change to the directory where Successor.java and input.txt are saved.
	5. Type javac Successor.java
	6. Type java Successor input.txt
	7. The code will run and you should see your expected output.			