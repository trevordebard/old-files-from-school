package bstreedemo;

import java.io.PrintStream;
import java.util.function.Function;

/**
 * A program to test our BSTree implementation<br>
 * Requires JDK 1.8 for Function
 * @author Trevor DeBardeleben
 * @since 4/25/16
 * @see BSTree, BSTreeAPI
 */

public class BSTreeDemo 
{
    public static void main(String[] args) 
    {
	BSTree<Integer> tree1 = new BSTree();
	BSTree<Integer> tree2 = new BSTree();
	BSTree<Integer> tree3 = new BSTree();
	BSTree<Integer> tree4 = new BSTree();
        
        tree1.insert(5);
        
        tree2.insert(12);
        tree2.insert(10);
        tree2.insert(13);
        tree2.insert(8);
        tree2.insert(11);
        tree2.insert(6);
        tree2.insert(9);
        tree2.insert(4);
        tree2.insert(7);
        tree2.insert(2);
        tree2.insert(1);
        tree2.insert(3);
        tree2.insert(5);
        
        tree3.insert(4);
        tree3.insert(2);
        tree3.insert(1);
        tree3.insert(3);
        tree3.insert(6);
        tree3.insert(5);
        tree3.insert(7);
       
        tree4.insert(40);
        tree4.insert(25);
        tree4.insert(15);
        tree4.insert(10);
        tree4.insert(5);
        tree4.insert(20);
        tree4.insert(30);
        tree4.insert(35);
        tree4.insert(50);
        tree4.insert(45);
        tree4.insert(55);
        tree4.insert(60);
        tree4.insert(52);
        tree4.insert(42);
        tree4.insert(48);
        tree4.insert(27);
        
        System.out.println("After inserting data into the trees:");
        System.out.printf("Tree 1: %s%n", tree1);
        System.out.printf("Tree 2: %s%n", tree2);
        System.out.printf("Tree 3: %s%n", tree3);
        System.out.printf("Tree 4: %s%n", tree4);
        System.out.println();
        System.out.printf("%-6s%-6s%-8s%-10s%-9s%-9s%n", "Tree", "Size", "Height", "Diameter","Perfect","Complete");
        System.out.println("------------------------------------------------");
        System.out.printf("%-6s%-6d%-8d%-10d%-9s%-9s%n","1", tree1.size(), tree1.height(), tree1.diameter(),tree1.isPerfect(),tree1.isComplete());
        System.out.printf("%-6s%-6d%-8d%-10d%-9s%-9s%n","2", tree2.size(), tree2.height(), tree2.diameter(),tree2.isPerfect(),tree2.isComplete());
        System.out.printf("%-6s%-6d%-8d%-10d%-9s%-9s%n","3", tree3.size(), tree3.height(), tree3.diameter(),tree3.isPerfect(),tree3.isComplete());
        System.out.printf("%-6s%-6d%-8d%-10d%-9s%-9s%n","4", tree4.size(), tree4.height(), tree4.diameter(),tree4.isPerfect(),tree4.isComplete());
        System.out.println("------------------------------------------------");
        System.out.println();
        tree1.remove(5);
        tree2.remove(5);
        tree3.remove(5);
        tree4.remove(5);
        System.out.println("After deleting 5 from each tree:");
        System.out.printf("Tree 1: %s%n", tree1);
        System.out.printf("Tree 2: %s%n", tree2);
        System.out.printf("Tree 3: %s%n", tree3);
        System.out.printf("Tree 4: %s%n", tree4);
        System.out.println();
        System.out.printf("%-6s%-6s%-8s%-10s%-9s%-9s%n", "Tree", "Size", "Height", "Diameter","Perfect","Complete");
        System.out.println("------------------------------------------------");
        System.out.printf("%-6s%-6d%-8d%-10d%-9s%-9s%n","1", tree1.size(), tree1.height(), tree1.diameter(),tree1.isPerfect(),tree1.isComplete());
        System.out.printf("%-6s%-6d%-8d%-10d%-9s%-9s%n","2", tree2.size(), tree2.height(), tree2.diameter(),tree2.isPerfect(),tree2.isComplete());
        System.out.printf("%-6s%-6d%-8d%-10d%-9s%-9s%n","3", tree3.size(), tree3.height(), tree3.diameter(),tree3.isPerfect(),tree3.isComplete());
        System.out.printf("%-6s%-6d%-8d%-10d%-9s%-9s%n","4", tree4.size(), tree4.height(), tree4.diameter(),tree4.isPerfect(),tree4.isComplete());
        System.out.println("------------------------------------------------");
        System.out.println();
        
        
        
        
    }    
}