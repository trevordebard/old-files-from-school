package triangledemo;
import java.awt.geom.*;

/**
 * CSC 1351 Lab #3
 * This program will use inheritance and abstract methods to preform various operations on polygons 
 * @author Trevor DeBardeleben
 * @since 2/5/2016
 * @see triangle
 */
public class TriangleDemo 
{

    public static void main(String[] args) 
    {
        Point2D.Double[] points1 = new Point2D.Double[] {new Point2D.Double(9,9), new Point2D.Double(11,14), new Point2D.Double(13,17)};
        Triangle one = new Triangle(points1);
        System.out.printf("The first triangle: %s%n", one);
        System.out.printf("perimeter: %.4f%n", one.perimeter());
        System.out.printf("area: %.4f%n", one.area());
        Point2D.Double[] points2 = new Point2D.Double[] {new Point2D.Double(5,6), new Point2D.Double(5,46), new Point2D.Double(14,6)};
        Triangle two = new Triangle(points2);
        System.out.printf("The second triangle: %s%n", two);
        System.out.printf("perimeter: %.4f%n", two.perimeter());
        System.out.printf("area: %.4f%n", two.area());

        
    }
    
}
