
package stackdemo;

/**
 * A parameterized interface that describes basic
 * operations of a stack.
 * @author Duncan
 * @param <E> the type of data that the stack contains
 * @since 4/8/2016
 */
public interface StackAPI<E> 
{
   /**
    * Determines whether the stack is empty.
    * @return true if the stack is empty;
    * otherwise, false
   */
   boolean isEmpty();

   /**
    * Adds an item at the top of the stack.
    * @param item the value to be inserted.
    */
   void push(E item);

   /**
    *  Accesses the item at the top of a non-empty stack
    *  @return item at the top of the stack.
     * @throws Exception when this stack is empty
    */
   E top() throws Exception;

   /**
    * Removes an item from the top of the stack.
    * @return item at the top of the stack.
     * @throws Exception when this stack is empty
    */
   E pop() throws Exception;

   /**
    * Gives the size of the stack.
    * @return the size of the stack
    */
   long size();    
}
