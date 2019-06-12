package project2;
/**
* Generates a custom exception that is a subclass of NumberFormatException
* CSC 1351 Project # 2
* @author tdebar2
* @since 2/20/2016
* @version 2
*/
public class IndeterminateFractionException extends NumberFormatException
{
    /**
     * A default constructor that creates a NumberFormatException object
     */
    public IndeterminateFractionException()
    {
        super();
    }
    /**
     * creates an object of this class.
     * @param msg a string describing why the exception occurred.
     */
    public IndeterminateFractionException(String msg)
    {
        super(msg);
    }
}
