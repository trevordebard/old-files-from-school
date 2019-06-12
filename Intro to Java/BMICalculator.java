package bmi.calculator;

/**
 * This class will be used to calculate the BMI of 3 different people
 * CSCS 1350 Lab # 3
 * @author tdebar2
 * @since 9/14/2015
 */
import java.util.Scanner;
public class BMICalculator {
    public static void main(String[] args) {
       Scanner name1 = new Scanner(System.in);
       Scanner name2 = new Scanner(System.in);
       Scanner name3 = new Scanner(System.in);
       Scanner weight1 = new Scanner(System.in);
       Scanner weight2 = new Scanner(System.in);
       Scanner weight3 = new Scanner(System.in);
       Scanner height1 = new Scanner(System.in);
       Scanner height2 = new Scanner(System.in);
       Scanner height3 = new Scanner(System.in);
       
       String firstName1, lastName1, firstName2, lastName2, firstName3, lastName3;
       float firstWeight, secondWeight, thirdWeight, bmi1, bmi2, bmi3;
       int firstHeight, secondHeight, thirdHeight;
       
       System.out.print("Enter the first and last names of the 1st person -> ");
       firstName1 = name1.next();
       lastName1 = name1.next();
       System.out.print("Enter the mass(lbs) -> ");
       firstWeight = weight1.nextFloat();
       System.out.print("Enter an integer for the height (in) -> ");
       firstHeight = height1.nextInt();
       System.out.println();
       
       bmi1 = (float) (firstWeight/(Math.pow(firstHeight,2)))*703;
       
       
       System.out.print("Enter the first and last names of the 2nd person -> ");
       firstName2 = name2.next();
       lastName2 = name2.next();
       System.out.print("Enter the mass (lbs) -> ");
       secondWeight = weight2.nextFloat();
       System.out.print("Enter an integer for the height (in) -> ");
       secondHeight = height2.nextInt();
       System.out.println();
       
       bmi2 = (float) (secondWeight/(Math.pow(secondHeight,2)))*703;
       
       System.out.print("Enter the first and last names of the 3rd person -> ");
       firstName3 = name3.next();
       lastName3 = name3.next();
       System.out.print("Enter the mass (lbs) -> ");
       thirdWeight = weight3.nextFloat();
       System.out.print("Enter an integer for the height (in) -> ");
       thirdHeight = height3.nextInt();
       System.out.println();
       
       bmi3 = (float) (thirdWeight/(Math.pow(thirdHeight,2)))*703;
       
       
      
       
       
       System.out.println("-----------------------------------------------------------------------");
       System.out.printf("%-30s%-16s%-16s%-16s%n", "NAME", "MASS(lbs)", "HEIGHT(\")", "BMI");
       System.out.println("=======================================================================");
       //System.out.printf("%s, %-16s%-16.3f%-14d%-14.3f%n",lastName1, firstName1, firstWeight, firstHeight, bmi1);
       System.out.printf("%-30s%-16.3f%-15d %-16.3f%n",lastName1 + ',' + firstName1, firstWeight, firstHeight, bmi1);
       System.out.printf("%-30s%-16.3f%-15d %-16.3f%n",lastName2 + ',' + firstName2, secondWeight, secondHeight, bmi2);
       System.out.printf("%-30s%-16.3f%-15d %-16.3f%n",lastName3 + ',' + firstName3, thirdWeight, thirdHeight, bmi3);
       System.out.println("-----------------------------------------------------------------------");
       
       
       
       
       
       
       
    }
    
}
