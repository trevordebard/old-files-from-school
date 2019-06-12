package project3;

import java.util.Random;

/**
 * This class contains utility methods for array manipulation.
 * @author Cay Horstman
 * @author Duncan
 * @since 3/11/2016
 * @version 1
*/  
public class ArrayUtil
{ 
   /**
    * A random number generated seeded using the current time of day.
	*/
   private static Random generator = new Random(System.currentTimeMillis());

   /**
    * Creates an array filled with random values.
    * @param length the length of the array
    * @param n the number of possible random values
    * @return an array filled with length numbers between
    * 0 and n - 1
    */
   public static int[] randomIntArray(int length, int n)
   {  
      int[] a = new int[length];      
      for (int i = 0; i < a.length; i++)
      {
         a[i] = generator.nextInt(n);
      }
      return a;
   }

   /**
    * Creates an array filled with values in ascending order:
    * @param length the length of the array
    * @return an array filled with length numbers between
    * 0 to length-1
    */
   public static int[] ascIntArray(int length)
   {  
      int i;
      int[] ascArray = new int[length];
      for (i=0; i<length; i++)
	      ascArray[i] = i;
      return ascArray;
   }   
   
   /**
    * Creates an array filled with values in descending order:
    * @param length the length of the array
    * @return an array filled with length numbers between
    * length-1 to 0
    */
   public static int[] descIntArray(int length)
   {  
      int i;
      int[] descArray = new int[length];
      for (i=0; i<length; i++)
	      descArray[i] = length-1-i;
      return descArray;
   }   

   /**
    * Swaps two entries of an array.
    * @param a the array
    * @param i the first position to swap
    * @param j the second position to swap
    */
   public static void swap(int[] a, int i, int j)
   {
      int temp = a[i];
      a[i] = a[j];
      a[j] = temp;
   }
}
      
