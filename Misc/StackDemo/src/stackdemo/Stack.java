
package stackdemo;
import java.util.ArrayList;

/**
 * An array-list-based implementation of a stack.
 * @author Duncan
 * @author Trevor DeBardeleben
 * @param <E> the stack data type
 * @since 4/8/2016
 */
public class Stack<E> implements StackAPI<E>
{
    /**
     * stores the elements of the stack such that
     * the first element of the array list is at the
     * top of the stack and its last element is at
     * the bottom of the stack.
     */
    private final ArrayList<E> list;
    
    /**
     * Creates an empty stack.
     */
    public Stack()
    {
       list = new ArrayList<>();
    }
    
    @Override
    public void push(E item)
    {
       list.add(item);
    }
    
    @Override
    public E pop() throws Exception
    {
       if(list.size() == 0)
           throw new Exception("Non-empty Stack expected");
       else
           return list.remove(list.size()-1);
           
    }
    
    @Override
    public E top() throws Exception
    {
       if(list.size() == 0)
           throw new Exception("Non-empty Stack expected");
       else
           return list.get(list.size()-1);
    }   
    
    @Override
    public boolean isEmpty()
    {
       return list.size() == 0;
    }
    
    @Override
    public long size()
    {
       return list.size();
    }
    
    /**
     * Moves the element from the top of this stack,
     * its left-most elements, and puts it at the bottom of the bottom
     * so that it becomes the right-most element; this method does
     * nothing if the queue is empty or has only one element.
     */
    public void rightRotate()
    {
       list.add(0, list.remove(list.size()-1));
    }

    /**
     * Moves the element from the bottom of this stack,
     * its right-most elements, and puts it at the top of the stack
     * so that it becomes the left-most element.; it does nothing
     * if the queue is empty or has only one element.
     */
    public void leftRotate()
    {
       list.add(list.size()-1, list.remove(0));
    }    
    
    /**
     * Displays the elements of this stack in the format
     * [e0, e2, ..., en-1] where the eis are the elements of the 
     * stack and e0 is the element at the bottom of the stack and
     * en-1 is the element at the top of the stack.
     * @return the elements of the stack in the format:
     * [e0, e2, ..., en-1], where em-1 is at the top and e0 is at the 
     * bottom of the stack.
     */
    @Override
    public String toString()
    {
      return list.toString();
    }
}
