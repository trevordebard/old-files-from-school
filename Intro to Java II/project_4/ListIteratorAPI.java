package listdemo;
/**
* A generic list iterator allows access of a position
* in a linked list. This interface contains a subset
* of the methods of the standard java.util.ListIterator<E>
* interface. Some methods signatures are omitted and are
* left as programming exercises for you.
* @author Duncan
* @param <E> list type parameter
* @since 9999-99-99
*/
public interface ListIteratorAPI<E>
{
    /**
    * Returns the next element in the list and advances
    * the cursor position.
    * @return the traversed element
    */
    E next();
    /**
    * Returns the previous element in the list and moves
    * the cursor position backwards.
    * @return the traversed element
    */
    E previous();
    /**
    * Returns true if this list iterator has more elements
    * when traversing the list in the forward direction.
    * @return true if there is an element after the iterator
    * position
    */
    boolean hasNext();
    /**
    * Returns true if this list iterator has more elements
    * when traversing the list in the reverse direction.
    * @return true if there is an element before the iterator
    * position
    */
    boolean hasPrevious();
    /**
    * Adds an element before the iterator position and moves
    * the iterator past the inserted element.
    * @param element the element to add
    */
    void add(E element);
    /**
    * Removes the last traversed element. This method may
    * only be called after a call to the next() method.
    */
    void remove();
    /**
    * Replaces the last element returned by next() or
    * previous() with the specified element.
    * @param element the element to set
    */
    void set(E element);
}
