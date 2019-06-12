package queuedemo;

/**
 * Test our circular linked-list-based queue ADT implementation
 * @author William Duncan
 * @since 99/99/9999
 * @see Queue, QueueAPI
 */
public class QueueDemo
{
    public static void main(String []args) throws Exception
    {
        Queue<Character> word = new Queue();
        word.enqueue('S');
        System.out.printf("%c inserted in the queue.%n",'S');
        word.enqueue('R');
        System.out.printf("%c inserted in the queue.%n",'R');
        word.enqueue('E');
        System.out.printf("%c inserted in the queue.%n",'E');
        word.enqueue('G');
        System.out.printf("%c inserted in the queue.%n",'G');
        word.enqueue('I');
        System.out.printf("%c inserted in the queue.%n",'I');        
        word.enqueue('T');
        System.out.printf("%c inserted in the queue.%n%n",'T');        
        System.out.println(word);
        word.rightRotate();
        System.out.println(word);
        word.leftRotate();
        System.out.println(word);
        if (!word.isEmpty())
        {
            System.out.printf("%c is at the front of the queue.%n",word.front());
            System.out.printf("The queue is not empty%n%n");
        }
        else
            System.out.println("The queue is empty.%n%n");
        while (!word.isEmpty())
        {
            System.out.printf("%c is at the front of the queue.%n",word.front());
            System.out.printf("%c has been removed from the front.%n",word.dequeue());
        } 
    }
}
