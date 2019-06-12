package bstreedemo;

import java.util.NoSuchElementException;
import java.util.function.Function;
/**
 * Describes a binary search tree<br>
 * Requires JDK 1.8 for Function
 * @author Duncan
 * @author YOUR NAME
 * @since DATE MODIFIED BY YOU
 * @param <E> the data type
 */
public interface BSTreeAPI<E extends Comparable<E>>
{
   /**
    * Determines whether the binary search tree is empty.
    * @return this function returns true if the tree is empty;
    * otherwise, it returns false if the tree contains at least one node.
    */
   boolean isEmpty();
   /**
    * Inserts an item into the tree.
    * @param item the value to be inserted.
    */
   void insert(E item);
   /**
    * Determines whether an item is in the tree.
    * @param item item with a specified search key.
    * @return true on success; false on failure.
    */
   boolean inTree(E item);
   /**
    * Deletes an item from the tree.
    * @param item item with a specified search key.
    */
   void remove(E item);
   /**
    * Gives a reference to the item in the tree with the specified
    * key. If the item does not exists, an exception occurs.
    * @param key the key to the item to be retrieved.
    * @return it with the specified key.
    * @throws NoSuchElementException
    */
   E retrieve(E key) throws NoSuchElementException;
   /**
    * Traverses a binary tree in inorder
    * and the function is applied to the data in each node of the tree.
    * @param func a function applied to each node during the traversal
    */
   void inorderTraverse(Function func);   
   /**
    * Gives the size of the binary search tree
    * @return the number of nodes in this tree
    */
   int size();
   /*
    * Determines whether this binary tree is perfect. 
    */
   @Override
   public String toString();
   
   /**
    * This method is a wrapper for a method that determines the * height of a binary tree.
     * @return the height of the tree
    */
    public int height();
    /**
     * Determines whether this binary tree is perfect. 
     * @return true if the tree is perfect, false otherwise
     */
    public boolean isPerfect();
    /**
    * Determines whether this binary tree is complete. 
    * @return true if the tree is complete, false otherwise
    */
    public boolean isComplete();
    /**
    * Gives the diameter of this tree. 
    * @return the diameter of this tree. 
    */
    public int diameter();
}