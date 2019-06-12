/**
 * Trevor DeBardeleben
 * CSC 3380 Project 1 Submission
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Successor {
    public static void main(String[] args) {
        Scanner s = null; //creates a new scanner s
        try {
            s = new Scanner(new File("input.txt")).useDelimiter("[^0-9|-]+"); //tells the scanner to read from the input file "test.txt"
        } catch (FileNotFoundException e) {
            e.printStackTrace(); //prints the stack trace if the file does not exist
        }
        try {
            ArrayList<Integer> list = new ArrayList<Integer>(); //creates an ArrayList called list
            while (s.hasNextInt()) {            //cycles through integers on a given line of code
                list.add(s.nextInt());          //adds integers to list
                if(list.get(list.size()-1) > 9 || list.get(list.size()-1) < 0) //checks to see if the number inserted is less than 10 and greater -1
                    throw new IllegalArgumentException("Must insert numbers less than 10 or greater than -1"); //throws exception if number is greater than 9 or less than 0
            }
            if (list.size() == 0) //checks to see if the list is empty
                throw new IllegalArgumentException("You must insert at least one number"); //throws an error
            editList(list);             //calls edit list, which finds the successor
            System.out.println(list);   //prints the now modified list
        } catch(Exception e) {
            System.out.println(e);      //catches the Exception e
        }
        s.close(); //closes the Scanner s
    }

    public static void editList(ArrayList<Integer> list) { //a wrapper method to find the successor of a list
        int index = list.size() - 1;    //sets the index to the size of the list minus 1
        int last = list.get(index);     //sets last to the last element in the list
        if (last < 9) {                 //if the last element is less than 9
            list.set(index, last + 1);  //modifies the list by setting the last element to 1 plus itself
        }
        else if(last > 9) //if last is greater than 9
            throw new IllegalArgumentException("element must be less than 10"); //throws error if the last element isn't less than 10
        else
            editList(list, index);  //calls the method edit list with the list and the index as inputs
    }

    private static void editList(ArrayList<Integer> list, int index) {
        if (list.get(index) == 9) { //if the value at the index is 9
            if (index == 0) {       //if you are at the front of the list
                list.set(0, 1);     //set the first element to 1
                list.add(0);        //add a zero to the end of the list
            }
            else {
                list.set(index, 0);         //set the value at the given index to zero
                editList(list, index - 1);  //recursively call edit list again with the list and the index at the position before current index
            }
        }
        else
            list.set(index, list.get(index) + 1); //sets the value at the given index to one plus it's current value and ends the recursive loop
    }
}