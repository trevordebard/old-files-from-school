package fractiondemo;
import java.util.Scanner;

/**
 * This program will preform various operations on fractions
 * CSC 1351 Project # 1
 * @author tdebar2
 * @since 11/6/2015
 * @version 1
 * @see Fraction
 */

public class FractionDemo 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the first fraction -> ");
        String num1 = input.next();
        String[] first = num1.split("/");
        int top1 = Integer.parseInt(first[0]);
        int bottom1 = Integer.parseInt(first[1]);
        Fraction fract1 = new Fraction(top1,bottom1);
        
        System.out.print("Enter the second fraction -> ");
        String num2 = input.next();
        String[] second = num2.split("/");
        int top2 = Integer.parseInt(second[0]);
        int bottom2 = Integer.parseInt(second[1]);
        Fraction fract2 = new Fraction(top2,bottom2);
        
        System.out.print("Enter the third fraction -> ");
        String num3 = input.next();
        String[] third = num3.split("/");
        int top3 = Integer.parseInt(third[0]);
        int bottom3 = Integer.parseInt(third[1]);
        Fraction fract3 = new Fraction(top3,bottom3);
        
        System.out.print("Enter the fourth fraction -> ");
        String num4 = input.next();
        String[] fourth = num4.split("/");
        int top4 = Integer.parseInt(fourth[0]);
        int bottom4 = Integer.parseInt(fourth[1]);
        Fraction fract4 = new Fraction(top4,bottom4);
        
        Fraction f3Pow = new Fraction(1);
        for (int i = 0; i < 6; i++)
        {
           f3Pow = f3Pow.multiply(fract3);
        }
       
        System.out.printf("%nf1 = %s, f2 = %s, f3 = %s and f4 = %s%n%n", fract1, fract2,
                fract3, fract4);
        
        System.out.printf("f1 + f3 = %s%n", fract1.add(fract3));
        System.out.printf("f3 x (f4 - f2) / (f3 - f4) = %s%n", fract3.multiply
        (fract4.subtract(fract2)).divide(fract3.subtract(fract4)));
        System.out.printf("f3^6 = %s%n", f3Pow);
        System.out.printf("(f1 / f2) / (f2 + f4) = %s%n", (fract1.divide(fract2)).divide((fract2.add(fract4))));
        System.out.printf("5 / (f3 x f4) = %s%n", new Fraction(5).divide(fract3.multiply(fract4)));
    }   
}
