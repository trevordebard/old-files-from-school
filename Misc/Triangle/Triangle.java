package triangledemo;
import java.awt.geom.Point2D;

/**
 * A subclass of Polygon that preforms calculations on a triangle
 * @see Polygon
 */
public class Triangle extends Polygon 
{
    /**
     * a parameterized constructor that takes, as an explicit parameter, an array of three points
     * @param points an object of the triangle class
     */
    public Triangle(Point2D.Double[] points)
    {
        vertices = points;
    }
    
    /**
     * A method that finds the area of a triangle
     * @return the area of a triangle
     */
    public double area()
    {
        double s = super.perimeter()/2;
        return Math.sqrt(s*(s-super.distance(vertices[0], vertices[1]))*
                (s-super.distance(vertices[1],vertices[2]))*(s-super.distance(vertices[0], vertices[2])));
    }
    
    /**
     * method so that it returns a string representation of the triangle in the
     * form {(x1, y1) , (x2, y2) , (x3, y3)}, where (x1, y1), (x2, y2), and (x3, y3) are the vertices of the triangle.
     * @return the string format
     */
    public String toString()
    {
        return String.format("((%.1f, %.1f),(%.1f, %.1f))", vertices[0].x, vertices[0].y,
                vertices[1].x, vertices[1].y);
    }
    
}
