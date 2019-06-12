package project2;
import java.util.Scanner;

/**
 * This is the main method of a program that will preform various operations on fractions
 * CSC 1351 Project # 2
 * @author tdebar2
 * @since 2/20/2016
 * @version 2
 * @see Fraction
 */
public class FractionDEMO 
{
    public static void main(String[] args) 
    {
         Scanner input = new Scanner(System.in);

        System.out.print("Enter the first fraction -> ");
        String num1 = input.next();
        String[] split = num1.split("/");
        Fraction fract1 = new Fraction(Integer.parseInt(split[0]), Integer.parseInt(split[1]));

        System.out.print("Enter the second fraction -> ");
        String num2 = input.next();
        split = num2.split("/");
        Fraction fract2 = new Fraction(Integer.parseInt(split[0]),Integer.parseInt(split[1]));

        System.out.print("Enter the third fraction -> ");
        String num3 = input.next();
        split = num3.split("/");
        Fraction fract3 = new Fraction(Integer.parseInt(split[0]), Integer.parseInt(split[1]));

        System.out.print("Enter the fourth fraction -> ");
        String num4 = input.next();
        split = num4.split("/");
        Fraction fract4 = new Fraction(Integer.parseInt(split[0]),Integer.parseInt(split[1]));

        Fraction f3Pow = new Fraction(1);
        for (int i = 0; i < 6; i++)
        {
            f3Pow = (Fraction) f3Pow.multiply(fract3);
        }

        System.out.printf("%nf1 = %s, f2 = %s, f3 = %s and f4 = %s%n%n", fract1, fract2,
                fract3, fract4);
        System.out.printf("f1 + f3 = %s%n", fract1.add(fract3));
        System.out.printf("f3 x ( f4 - f2 ) / ( f3 - f4 ) = %s%n", ((Fraction) fract3.multiply(fract4.subtract(fract2))).divide(fract3.subtract(fract4)));
        System.out.printf("f3^6 = %s%n", f3Pow);
        System.out.printf("( f1 / f2 ) / ( f2 + f4 ) = %s%n", ((Fraction)fract1.divide(fract2)).divide(fract2.add(fract4)));
        System.out.printf("5 / (f3 x f4) = %s%n", new Fraction(5).divide(fract3.multiply(fract4)));
        System.out.print("Is f1(f2-f3) equal to f1f2 - f1f3? ");
        System.out.println(fract1.multiply(fract2.subtract(fract3)).equals(((Fraction)(fract1.multiply(fract2))).subtract(fract1.multiply(fract3))));
        int compare = ((Fraction)(fract1.divide(fract2.add(fract3)))).compareTo(((Fraction)(fract1.divide(fract2))).subtract(fract1.divide(fract3)));
        if(compare == -1)
            System.out.println("f1 /( f2 + f3 ) is less than f1 / f2 - f1 / f3");
        else if (compare == 0)
            System.out.println("f1 /( f2 + f3 ) equals f1 / f2 - f1 / f3");

        else 
            System.out.println("f1 /( f2 + f3 ) is greater than f1 / f2 - f1 / f3");
    }
    
}
