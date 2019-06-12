package numberconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author tdebar2
 */
public class NumberConverter 
{
    public static void main(String[] args) throws FileNotFoundException 
    {
        
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a non-negative base 10 integer -> ");
        int dividend = input.nextInt();
        if (dividend < 1)
        {
            System.out.println("\nThe base 10 number must be non-negative");
        }
        else if (dividend >= 1)
        {
            System.out.print("Enter a conversion base greater than 1 -> ");
            int base = input.nextInt();
            if (base < 2)
            {
                System.out.println("\nThe base must be greater than or equals to 2");
            }
            else if(base >= 2)
            {
                
                    System.out.print("Enter the output file name -> ");
                    String outFileName = input.next();
                    System.out.println();
                    try (PrintWriter fileOut = new PrintWriter(new File(outFileName))) 
                    {
                        System.out.printf("%-14s%-12s%-12s%-12s%n", "base", "dividend", "quotient", "remainder");
                        fileOut.printf("%-14s%-12s%-12s%-12s%n", "base", "dividend", "quotient", "remainder");
                        System.out.println("------------------------------------------------");
                        fileOut.println("------------------------------------------------");
                        int i=2;
                        int count = 1;
                        int quotient = 1;
                        char ch;
                        String tempS = "";
                        String tempC = "";
                        while (quotient !=0)
                        {
                            quotient = dividend / base;
                            int remainder = dividend % base;
                            if (remainder >= 10) 
                            {
                                ch = Character.toUpperCase(Character.forDigit(remainder, 36));
                                tempC += ch;
                            } 
                            else if (remainder >= 0)
                                tempS += remainder;
                        
                            System.out.printf("%-14d%-12d%-12d%-12d%n", base, dividend, quotient, remainder);
                            fileOut.printf("%-14d%-12d%-12d%-12d%n", base, dividend, quotient, remainder);
                            dividend = quotient;
                        }
                        System.out.println("------------------------------------------------");
                        fileOut.println("------------------------------------------------");
                        System.out.printf("%s%s[%d]%n", tempS, tempC, base);
                        fileOut.printf("%s%s[%d]%n", tempS, tempC, base);
                        fileOut.close();
                    }
                    catch(IOException e)
                    {
                        System.out.println(e);
                        System.exit(0);
                    }
            }
        }
    }
}
