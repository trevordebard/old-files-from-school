package queuedemo;
/**
 * A circular linked-list-based queue ADT implementation
 * @author William Duncan
 * @author Trevor DeBardeleben 
 * @since 04-01-2016
 * @see QueueAPI
 */
 public class Queue<E> implements QueueAPI<E>
{
    /**
     * A reference to the back of the queue
     */
    Node rear;

    /**
     * the number of nodes in this queue
     */
    long length;


    @Override
    public boolean isEmpty() 
    {
        return length == 0;
    }

    
    @Override
    public void enqueue(E item) 
    {
        Node newNode = new Node();
        newNode.data = item;
        if(length == 0)
        {
            newNode.next = newNode;
        }
        else
        {
            newNode.next = rear.next;
            rear.next = newNode;
        }
        length++;
        rear = newNode;
    }
    

    @Override
    public E front() throws Exception 
    {
        if (length == 0)
            throw new Exception("Queue cannot be non empty");
        return rear.next.data;
    }

    @Override
    public E dequeue() throws Exception 
    {
        if (length == 0)
                throw new Exception("Queue cannot be non-empty");
        E item = rear.next.data;
        rear.next = rear.next.next;
        length--;
        return item;
    }


    @Override
    public long size() 
    {
        return length;
    }

    /**
     * this inner class is used to create nodes of the queue
     */
    private class Node
    {
       public E data;
       public Node next;
    }

    /**
     * Creates an empty doubly-ended queue
     */
    public Queue()
    {
       rear = null;
       length = 0;
    }

    /**
     * Moves the element at the front of the queue to the back.
     */
    public void rightRotate()
    {
       rear = rear.next;
    }

    /**
     * Moves the element at the back of the queue to the front.
     */
    public void leftRotate()
    {
        for (int i = 1; i < length; i++)
        {
            rear = rear.next;
        }
    }
	
    /**
     * Gives a string representation of the elements of this queue
     * in the format [en-1, en-2, ...e3, ,e1, e0], where each ei is an 
     * element of this queue and e0 is the element in the head node and en-1 
     * is the element in the rear node. It returns [] if this queue is empty.
     * @return a string representation of the queue in the format
     * [en-1, en-2, ...e3, ,e1, e0]
     */	 
    @Override
    public String toString()
    {
        String str = "]";
        if(length == 0)
            str = "[" + str;
        else
        {
            Node tmp = rear.next ;
            str = tmp.data + str;
            for (int i = 1; i < length; i++)
            {
                tmp = tmp.next;
                str = tmp.data + "," + str; 
            }
            str = "[" + str;          
        }
        return str;
    }	
}
