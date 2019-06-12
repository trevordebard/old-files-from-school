package magicsquarecreator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program will generate a Magic Square or test whether a given set of numbers
 * is a magic square.
 * CSC 1350 Project # 4
 * @author Trevor
 * @since 11/6/2015
 */

public class MagicSquareDemo
{
   public static void main(String[] args) 
   {
      Scanner keyIn = new Scanner(System.in); 
      String option; 
      do
      {
         System.out.println();		  
         System.out.println("             MAGIC SQUARE APPLICATION             ");
         System.out.println("==================================================");
         System.out.println("Generate a Magic Square........................[1]");
         System.out.println("Test for a Magic Square........................[2]");
         System.out.println("Quit the Program...............................[0]");
         System.out.println();
         System.out.print("Select an option -> ");
         option = keyIn.next(); 
          
            
         switch(option)
         {
            case "1": 
                    
                    System.out.print("Enter the dimension of the magic square -> ");
                    int order = keyIn.nextInt();
                    if (order < 1)
                    {
                        System.out.println("The order must be positive and odd");
                    }
                    else if (order % 2 == 0)
                    {
                        System.out.println("The program can only generate a magic square"
                                + "\nwhose dimension is a positive odd number.");
                        return;
                    }
                    else if (order % 2 != 0)
                    {
                        System.out.print("Enter the name of the output file -> ");
                        try
                        {
                            String outFileName = keyIn.next();
                            PrintWriter fileOut = new PrintWriter(new File(outFileName));
                        
                            int i = 1;
                            int row = order - 1;
                            int col = order / 2;
                            int index;
                            int[] array = new int[order * order];


                            while (i != Math.pow(order,2) +1)
                            {

                              index = (row * order) + col;
                              array[index] = i; 
                              row = (row+1)%order;
                              col = (col+1)%order;
                              index = (row * order) + col;
                              if (array[index] != 0)
                              {
                                  row = (row + order - 2) % order;
                                  col = (col + order - 1) % order;
                              }
                              i++;
                            }
                            System.out.println();
                            for (i=0; i<order; i++)
                            {
                                for (int j=0; j<order; j++)
                                {
                                    int place = (i * order) + j;
                                    System.out.printf("%-5d",array[place]);
                                    fileOut.printf("%-5d",array[place]);
                                }
                                System.out.println();
                                fileOut.println();
                            }
                            System.out.printf("%nA %d x %d Magic Square%n", order, order);
                            fileOut.printf("%nA %d x %d Magic Square%n", order, order);
                            fileOut.close();
                            break;
                        }
                        catch(IOException e)
                        {
                            System.out.println(e);
                        }                    }
               break; 

            case "2": 
                    ArrayList<Integer> magic = new ArrayList<>();
                    System.out.print("Enter the name of the input file -> ");
                    try
                    {
                        String inFileName = keyIn.next();
                        Scanner fileIn = new Scanner(new File(inFileName));

                        while(fileIn.hasNextInt())
                        {
                            magic.add(fileIn.nextInt());
                        }
                        fileIn.close();
                    }
                    catch (IOException e)
                    {
                        System.out.println(e);
                        return;
                    }        
                     int i;
                     int size = magic.size();
                     double sqrt = Math.sqrt(size);
                     int sqrtInt = (int) sqrt;
                     System.out.println();
                     
                     for (i=0; i<magic.size(); i++)
                     {
                         if ((i % sqrtInt == 0) && (i > 0)) 
                         {
                             System.out.println();
                         }
                         System.out.printf("%-4d",magic.get(i));
                     }        
                     System.out.println();

                    
                     if (sqrtInt != sqrt)
                     {
                         System.out.println("\nNot a magic square");
                         break;
                     }
                     boolean proceed;
                     
                     for (i = 1; i <= magic.size(); i++)
                     {
                         if (!magic.contains(i))
                         {
                             System.out.println("\nNot a magic square");
                             break;
                         }
                     }
                     
                    int n = sqrtInt;
                    int[] rowSums = new int[n];
                    int[] colSums = new int[n];
                    int sumDiagMajor = 0;
                    int sumDiagMinor = 0;
                    int index = 0;
                    int row;
                    int col;

                    while (index != Math.pow(n,2))
                    {
                        row = index / n;
                        col = index % n;
                        rowSums[row] += magic.get(index);
                        colSums[col] += magic.get(index);

                        if (row == col)
                        {
                            sumDiagMajor += magic.get(index);
                        }
                        if (row + col == n - 1)
                        {
                            sumDiagMinor += magic.get(index);
                        }
                        index++;
                    }

                    if (sumDiagMajor != sumDiagMinor)
                    {
                        System.out.println("\nNot a Magic Square");
                        break;
                    }
                    for (i=0; i < n; i++)
                    {
                        if (rowSums[i] != sumDiagMajor || colSums[i] != sumDiagMajor)
                        {
                            System.out.println("Not a Magic Square");
                            break;
                        }
                    }

                    System.out.printf("%nA %d x %d Magic Square%n", n, n);
                break;  
                        
               
            case "0": 
                break; 
                
            default: System.out.println("Invalid choice...please select a valid menu option.");
            return;
         }
      }           
     while (option.compareTo("0") != 0); 
   }
}
