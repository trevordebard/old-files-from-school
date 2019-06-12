#!/bin/bash
javac Successor.java
for i in {1..6} 
do 
	if [[ $i -eq 1 ]] 
	then
		echo "[1, 2, 3, 4]" > input.txt
		java Successor input.txt
	fi
	if [[ $i -eq 2 ]] 
	then
		echo "9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9" > input.txt
		java Successor input.txt
	fi
	if [[ $i -eq 3 ]] 
	then
		echo "9 8 9 9 9 9 9" > input.txt
		java Successor input.txt
	fi
	if [[ $i -eq 4 ]] 
	then
		echo "5 6 -5" > input.txt
		java Successor input.txt
	fi
	if [[ $i -eq 5 ]] 
	then
		echo "0" > input.txt
		java Successor input.txt
	fi
		if [[ $i -eq 6 ]] 
	then
		echo "9" > input.txt
		java Successor input.txt
	fi
	
done
rm -f input.txt