package stackdemo;


/**
 * This program tests the array-list-based stack ADT implementation
 * @author William Duncan
 * @author Trevor DeBardeleben
 * @since 4/8/2016
 */
public class StackDemo
{
    public static void main(String []args) throws Exception
    {
        Stack<String> names = new Stack();
        names.push("Jamie");
        System.out.printf("%s inserted in the stack.%n",names.top());
        names.push("Adam");
        System.out.printf("%s inserted in the stack.%n",names.top());
        names.push("Emily");
        System.out.printf("%s inserted in the stack.%n",names.top());
        names.push("Jeremy");
        System.out.printf("%s inserted in the stack.%n",names.top());
        names.push("Stephen");
        System.out.printf("%s inserted in the stack.%n",names.top());
        names.push("Chloe");
        System.out.printf("%s inserted in the stack.%n",names.top());
        System.out.printf("%nCurrent contents of the stack:%n");
        System.out.println(names);
        names.leftRotate();
        System.out.printf("%nAfter the stack is left-rotated:%n");
        System.out.println(names);        
        names.rightRotate();
        System.out.printf("%nAfter the stack is right-rotated:%n");
        System.out.println(names);
        System.out.println();
        if (!names.isEmpty())
        {
            System.out.printf("%s is at the top of the stack.%n",names.top());
            System.out.printf("The stack is not empty%n");
        }
        else
            System.out.println("The stack is empty.%n%n");
        while (!names.isEmpty())
        {
            System.out.printf("%n%s is at the top of the stack.%n",names.top());
            System.out.printf("%s has been removed from the top.%n",names.pop());
            System.out.printf("The stack of names is now %s.%n",names);
        }
    }
}