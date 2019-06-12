package triangledemo;
import java.awt.geom.*;
/**
 * Polygon is an abstract class 
 * @author Trevor
 */
abstract public class Polygon 
{
    
    protected Point2D.Double[] vertices;
    
    /**
     * Sets vertices to null
     */
    public Polygon()
    {
        vertices = null;
    }
    
    /**
     * a parameterized constructor that takes an array
     * of points as an explicit parameter and creates a polygon
     * @param points a Polygon object
     */
    public Polygon(Point2D.Double[] points)
    {
        vertices = points;
    }
    
    /**
     * an auxilliary method used to find the Euclidean distance between two points
     * @param p1 the first vertex
     * @param p2 the second vertex
     * @return the distance between two points
     */
    protected static double distance(Point2D.Double p1, Point2D.Double p2)
    {
        return Math.sqrt(Math.pow((p1.x - p2.x),2)+ Math.pow((p1.y - p2.y),2));
    }
    
    /**
     * 
     * @return 
     */
    public abstract double area();
    
    /**
     * Calculate the perimeter of a polygon
     * @return the perimeter of the polygon
     */
    public double perimeter()
    {
        double totalPerimeter = 0;
        for (int i = 0; i <= vertices.length -1; i++)
        {
            totalPerimeter += distance(vertices[i], vertices[(i+1)%vertices.length]);
        }
        return totalPerimeter;
    }
    
    /**
     * returns an array of points that are vertices of the
     * polygon
     * @return the vertices of a polygon
     */
    public Point2D.Double[] getVertices()
    {
        return vertices;
    }
    
    /**
     * modifies the vertices of a polygon using the points in the array, the explicit parameter of the method
     * @param points a polygon object
     */
    public void setVertices (Point2D.Double[] points)
    {
        vertices = points;
    }
    
}
