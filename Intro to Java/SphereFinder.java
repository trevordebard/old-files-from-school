package spherefinder;
/**
 * @author tdebar2
 */
import java.util.Scanner;
public class SphereFinder 
{
    public static void main(String[] args) 
    {
        double xCenter, yCenter, zCenter, r, distance;
        double xPoint, yPoint, zPoint;
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the x-, y-, z- coordinates of the center > ");
        xCenter = input.nextDouble();
        yCenter = input.nextDouble();
        zCenter = input.nextDouble();
        System.out.print("Enter the radius of the sphere > ");
        r = input.nextInt();
        
        System.out.print("Enter the x-, y-, z- coordinates of a point > ");
        xPoint = input.nextDouble();
        yPoint = input.nextDouble();
        zPoint = input.nextDouble();
        
        distance = Math.sqrt(Math.pow(xCenter - xPoint, 2) + Math.pow(yCenter - 
                yPoint, 2) + Math.pow(zCenter - zPoint, 2));
        
        if(distance < r)
        {
            System.out.printf("The point (P = %.4f, %.4f, %.4f) lies in the "
                    + "sphere%n", xPoint, yPoint, zPoint);
        }
        else if(distance > r)
        {
            System.out.printf("The point (P = %.4f, %.4f, %.4f) lies outside the"
                    + " sphere%n", xPoint, yPoint, zPoint);
        }
        else
        {
            System.out.printf("The point (P = %.4f, %.4f, %.4f) lies on the "
                    + "sphere%n", xPoint, yPoint, zPoint);
        }
        System.out.printf("whose center is C = (%.4f, %.4f, %.4f) and "
                + "radius is%n%.4f. P is %.4f units away from C.%n", xCenter, 
                + yCenter, zCenter, r, distance);
        
        

    }
    
}
