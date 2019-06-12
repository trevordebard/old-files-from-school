package project2;
/**
* An interface of methods to be implemented in other classes
* CSC 1351 Project # 2
* @author tdebar2   
* @since 2/20/2016
* @version 2
*/
public interface Arithmetic 
{
    /**
     * Computes and returns the  sum of two objects of a class.
     * @param obj the object being added
     * @return the the modular sum of two objects
     */
    public Object add(Object obj);

    /**
     * Computes and returns the  difference of two objects of a class.
     * @param obj the object being subtracted
     * @return the the modular difference of two objects
     */
    public Object subtract(Object obj);

    /**
     * Computes and returns the  product of two objects of a class.
     * @param obj the object being multiplied
     * @return the the modular sum of two objects
     */    
    public Object multiply(Object obj);

    /**
     * Computes and returns the quotient of two objects of a class.
     * @param obj the object being divided
     * @return the the modular quotient of two objects
     */
    public Object divide(Object obj);
}
