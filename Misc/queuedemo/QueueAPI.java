package queuedemo;

/**
 * A parameterized interface that defines operations
 * that a queue must implement.
 * @author Duncan
 * @author Trevor DeBardeleben
 * @param <E> the type of data that the queue contains
 * @since 04-01-2016
 */
public interface QueueAPI<E>
{
	/**
	 * Determines whether the queue is empty.
     * @return true if the queue is empty;
     * otherwise, false
     */
    boolean isEmpty();

    /**
	 * Inserts an item at the back of the queue.
     * @param item the value to be inserted.
     */
    void enqueue(E item);

    /**
     * Accesses the item at the front of a non-empty queue
     * @return item at the front of the queue.
     * @throws Exception when this queue is empty
     */
    E front() throws Exception;

    /**
     * Deletes an item from the front of the queue.
     * @return item at the front of the queue.
     * @throws Exception when this queue is empty
     */
    E dequeue() throws Exception;

    /**
     * Gives the size of the queue.
     * @return the size of the queue
     */
    long size();
}