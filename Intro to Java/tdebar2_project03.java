package eulerseriesapproximator;
/**
 * This program will estimate the sum of the series e^x
 * given the number of terms in the series and x
 * CSC 1350 Project # 3 
 * @author Trevor
 * @since 10/15/2015
 */
import java.util.Scanner;
public class EulerSeriesApproximator 
{

    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        int terms;
        double exp, approx;
        int i,j;
        
        
        System.out.print("Enter the number of terms to be used in approximating e^x -> ");
        terms = input.nextInt();
        System.out.print("Enter the exponent x to approximate e^ x -> ");
        exp = input.nextDouble();
        
        double num = exp , denom = 1, trm = 0, total =0;
        
        if (terms <= 0)
        {
            System.out.printf("%n%d is an invalid number of terms.%n", terms);
            
        }
        else if (terms == 1 || exp == 0)
        {
            System.out.printf("%ne^%.5f ~ 1.00000 = 1.0000000000%n", exp);
        }
        
        else if (terms == 2)
        {
            approx = 1+exp;
            System.out.printf("%ne^%.5f ~ 1.00000%+.5f = %f%n", exp, exp, approx);
        }
        else if (terms > 2)
        {
            System.out.printf("%ne^%.5f ~ 1.00000%+.5f", exp, exp);
            for (i = 1; i <= terms - 2; i++)
            {
                num = num * exp;
                denom = denom * (i+1); 
                trm = trm + num/denom;
                System.out.printf("%+.5f", trm);
                total += trm;
                trm = 0;
              
            }
            approx = 1 + exp + total;
            System.out.printf(" = %.10f%n", approx) ;
        }
        else
        {
            System.out.println("Invalid input. Try again");
        }
        
        
        
    }
    
}
