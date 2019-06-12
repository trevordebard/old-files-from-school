package project3;
import java.util.Arrays;

/**
 * This class will sort arrays in random, ascending, and descending order and calculate their run times
 * CSC 1351 Project 3
 * @author Trevor DeBardeleben
 * @since 3/11/2016
 * @see StopWatch, Sorter, ArrayUtil
 * @version 1
 */
public class Project3 
{
    
    public enum sorters
    {
            SELECTION, INSERTION, QUICK, MERGE;
    }
    public enum orders
    {
            RANDOM, ASCENDING, DESCENDING
    }
    public static void main(String[] args) 
    {
        
        StopWatch clock = new StopWatch();
        for (orders order : orders.values())
        {
            switch(order)
            {
                case RANDOM:
                    System.out.println("==============================================================");
                    System.out.println("                 Arrays With Random Integers");
                    System.out.println("==============================================================");
                    System.out.printf("%-12s%-12s%-12s%-12s%-12s%n", "N", "SELECTION", "INSERTION", "QUICK", "MERGE");
                    break;
                case ASCENDING:
                    System.out.println("==============================================================");
                    System.out.println("                 Arrays With Ascending Integers");
                    System.out.println("==============================================================");
                    System.out.printf("%-12s%-12s%-12s%-12s%-12s%n", "N", "SELECTION", "INSERTION", "QUICK", "MERGE");
                    break;
                case DESCENDING:
                    System.out.println("==============================================================");
                    System.out.println("                 Arrays With Descending Integers");
                    System.out.println("==============================================================");
                    System.out.printf("%-12s%-12s%-12s%-12s%-12s%n", "N", "SELECTION", "INSERTION", "QUICK", "MERGE");
                    break;    
            }
           
            for (int size = 10000; size <= 60000; size += 10000)
            {
                System.out.printf("%-12d", size);
                int[] data = new int[size];   

                switch(order)
                {
                    case RANDOM:
                        data = ArrayUtil.randomIntArray(data.length, size);
                        break;
                    case ASCENDING:
                        data = ArrayUtil.ascIntArray(size);
                        break;
                    case DESCENDING:
                        data = ArrayUtil.descIntArray(size); 
                        break;
                }
                
                

                for(sorters sorter : sorters.values())
                {
                    int[] dataCopy = Arrays.copyOf(data, size);
                    clock.reset();
                    switch(sorter)
                    {
                        case SELECTION:
                            clock.start();
                            Sorter.selectionSort(dataCopy);
                            clock.stop();
                            break;
                        case INSERTION:
                            clock.start();
                            Sorter.insertionSort(dataCopy);
                            clock.stop();
                            break;
                        case QUICK:
                            clock.start();
                            Sorter.quickSort(dataCopy);
                            clock.stop();
                            break;
                        case MERGE:
                            clock.start();
                            Sorter.mergeSort(dataCopy);
                            clock.stop();
                            break;
                    }
                    System.out.printf("%-12d", clock.getElapsedTime());
                }
                System.out.println();
            }
            System.out.println("==============================================================");
            System.out.println();
        }
    }
    
}
