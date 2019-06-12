package listdemo;

/**
* A doubly-linked list implementation of the List interface.
* This class implements many list operations, and permits all elements
* (including null). This class contains a subset of the methods of the
* standard <code>java.util.LinkedList&lt;E&gt;</code>. These operations allow linked lists to
* be used as a stack, queue, or double-ended queue (deque). Some methods
* are omitted and left as programming exercises for you. Cay Horstmann
* code in BJLO 16.1 is adapted and the class is modified to a generic
* class rather than one that uses an Object reference as the data type.
* Also, a generic ListIteratorAPI interface is defined since this List
* class does not implement all methods in the standard Java API and,
* more importantly, Object references are completely avoided.
* @author Cay Horstmann
* @author Duncan
* @author Trevor DeBardeleben
* @param <E> list type parameter
* @since 4/15/2016
*/
public class List<E extends Comparable<E>>
{
    /**
    * A reference to the first node
    */
    private Node first;
    /**
    * A reference to the last node
    */
    private Node last;
    /**
    * the size of this list
    */
    private long size;
    /**
    * An inner node class
    */
    private class Node
    {
        public E data;
        public Node next;
        public Node previous;
    }
    /**
    * A generic inner ListIterator class that implements the
    * ListIteratorAPI. It is used as a cursor to traverse the list.
    */
    private class ListIterator implements ListIteratorAPI<E>
    {   
        /**
        * current cursor position
        */
        private Node position;
        /**
        * previous cursor position
        */
        private Node previous;
        /**
        * Constructs an iterator that points to the front
        * of the linked list.
        */
        public ListIterator()
        {
            position = null;
            previous = null;
        }
        
        public E next()
        {
            if (!hasNext())
                throw new ListException("Called at the end of the list");
            previous = position;
            if (position == null)
                position = first;
            else
                position = position.next;
            return position.data;
        }

        public E previous()
        {
            if (!hasPrevious())
                throw new ListException("Called at the beginning of the list");
            previous = position;
            if (position == null)
            {
                position = last;
            }
            else
            {
                position = position.previous;
            }
            return position.data;
        }

        public boolean hasNext()
        {
            if (position == null)
                return first != null;
            else
                return position.next != null;
        }

        public boolean hasPrevious()
        {
            if (position == null)
                return last != null;
            else
                return position.previous != null;
        }

        public void add(E element)
        {
            if (position == null)
            {
                addFirst(element);
                position = first;
            }
            else
            {
                Node newNode = new Node();
                newNode.data = element;
                newNode.next = position.next;
                if (newNode.next != null)
                    newNode.next.previous = newNode;
                position.next = newNode;
                newNode.previous = position;
                position = newNode;
                if (newNode.previous == last) 
                    last = newNode;
                size++;
            }
            previous = position;
        }

        public void remove()
        {
            if (previous == position)
                throw new IllegalStateException();
            if (position == first)
            {
                removeFirst();
            }
            else
            {
                Node tmp = position;
                previous.next = position.next;
                if (position.next != null)
                    position.next.previous = previous;
                tmp.next = tmp.previous = null;  
                if(previous.next == null)
                    last = previous;
                size--;
            }
            position = previous;
        }
        
        public void set(E element)
        {
            if (position == null)
                throw new ListException("set calls on a null node");
            position.data = element;
        }
    }
    /**
    * Constructs an empty linked list.
    */
    public List()
    {
        first = null;
        last = null;
        size = 0;
    }

    /**
    * Returns the first element in the linked list.
    * @return the first element in the linked list
    */
    public E getFirst()
    {
        if (first == null)
            throw new ListException("Must be called on a non-empty list");
        return first.data;
    }

    /**
    * Removes the first element in the linked list.
    * @return the removed element
    */
    public E removeFirst()
    {
        if (first == null)
            throw new ListException("Must be called on a non-empty list");
        E element = first.data;
        Node tmp = first;
        if (last == first)
            last = null;
        first = first.next;
        if (first != null)
        {
            tmp.next = null;
            first.previous = null;
        }
        size--;
        return element;
    }
    /**
    * Adds an element to the front of the linked list.
    * @param element the element to add
    */
    public void addFirst(E element)
    {
        Node newNode = new Node();
        newNode.data = element;
        newNode.next = first;
        if (first != null)
            first.previous = newNode;
        else
            last = newNode;
        first = newNode;
        size++;
    }    
    //*******************Begin Project 4 Code Here ****************************
   /**
    * Replaces the element at the specified position in this list with the specified element.
    * @param index index of the element to replace
    * @param element element to be stored at the specified position
    * @return the element previously at the specified position
    */
    public E set(int index, E element)  
    {
        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Index out of bounds");
        ListIterator iter = new ListIterator();
        int length = (int) size;
        if (index > length/2)
        {
            iter.position = last;
            for(int i = length-1; i > index-1; i--)
            {
                iter.previous();
            }
        }
        else
        {
            for(int i = 0; i < index; i++)
            {
                iter.next();
            }
        }
        E value = iter.next();
        iter.set(element);
        return value;
    }
    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    public E get(int index)
    {
        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Index out of bounds");
        int length = (int) size;
        ListIterator iter = new ListIterator();
        if (index > length/2)
        {
            iter.position = last;
            for(int i = length-1; i > index-1; i--)
            {
                iter.previous();
            }
        }
        else
        {
            for(int i = 0; i < index; i++)
            {
                iter.next();
            }
        }
        return iter.next();
    }
        /**
    * Returns the last element in this list.
    * @return the last element in this list
    */
    public E getLast()
    {
        if (last == null)
            throw new ListException("Must be called on a non-empty list");
        return last.data;
    }

    /**
    * Removes and returns the last element from this list.
    * @return the last element from this list
    */
    public E removeLast()
    {
        if (last == null)
            throw new ListException("Must be called on a non-empty list");
        E element = last.data;
        Node tmp = last;
        if (last == first)
            first = null;
        last = last.previous;
        if (last != null)
        {
            tmp.previous = null;
            last.next = null;
        }
        size--;
        return element;
    }
   
    /**
    * Appends the specified element to the end of this list.
    * @param element the element to add
    */
    public void addLast(E element)
    {
        Node newNode = new Node();
        newNode.data = element;
        newNode.previous = last;
        if (last != null)
            last.next = newNode;
        else
            first = newNode;
        last = newNode;
        size++;
    }    
    
    /**
     * Appends the specified element to the end of this list.
     * @param o element to be appended to this list
     * @return true (as specified by Collection.add(E))
     */
    public boolean add(E o)
    {
        add((int) size, o);
        return true;
    }
    /**
     * Inserts the specified element at the specified position in this list. 
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).     
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    public void add(int index, E element)
    {
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException("Index is not in range");
        int length = (int) size;
        ListIterator iter = new ListIterator();
        if (index > length/2)
        {
            iter.position = last;
            for(int i = length-1; i > index-1; i--)
            {
                iter.previous();
            }
        }
        else
        {
            for(int i = 0; i < index; i++)
            {
                iter.next();
            }
        }
       
        iter.add(element);
    }
    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     * @return the head of this list, or null if this list is empty
     */
    public E peek()
    {
        return get(0);
    }
    /**
     * Retrieves and removes the head (first element) of this list.
     * @return the head of this list, or null if this list is empty
     */
    public E poll()
    {
        return remove(0);
    }
    /**
     * Retrieves and removes the head (first element) of this list.
     * @return the head of this list
     */
    public E remove()
    {
        if (size() == 0)
            throw new ListException("Non-empty list expected");
        return remove(0);
    }
    /**
     * Removes the element at the specified position in this list. Shifts any
     * subsequent elements to the left (subtracts one from their indices). 
     * Returns the element that was removed from the list.
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     */
    public E remove(int index)
    {
        if(index < 0 || index >= size()) 
        {
            throw new IndexOutOfBoundsException("Index is not in range");
        } 
        int length = (int) size;
        ListIterator iter = new ListIterator();
        if (index > length/2)
        {
            iter.position = last;
            for(int i = length-1; i > index-1; i--)
            {
                iter.previous();
            }
        }
        else
        {
            for(int i = 0; i < index; i++)
            {
                iter.next();
            }
        }
        E value = iter.next();
        iter.remove();
        return value;
    }
    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     * @return the head of this list
     */
    public E element()
    {
        if(size() == 0)
            throw new ListException("Non-empty list expected");
        return get(0);
    }
    
	
    //*******************End Project 4 Code************************************
    
    /**
    * Returns an iterator for iterating through this list.
    * @return an iterator for iterating through this list
    */
    public ListIterator listIterator()
    {
        return new ListIterator();
    }
    /**
    * The number of elements in this list.
    * @return the length of this list.
    */
    public long size()
    {
        return size;
    }
}
