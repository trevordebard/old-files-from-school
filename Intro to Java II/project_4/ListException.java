package listdemo;

import java.util.NoSuchElementException;

/**
 * To report some exceptions of the List<E> class
 * @author Duncan
 * @since 99-99-9999
 */
public class ListException extends NoSuchElementException 
{

    /**
     * Creates a new instance of <code>NewException</code> without detail
     * message.
     */
    public ListException() 
	{
    }

    /**
     * Constructs an instance of <code>NewException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ListException(String msg) {
        super(msg);
    }
}
