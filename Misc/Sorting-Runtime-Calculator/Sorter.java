package project3;

/**
 * This class implements insertion, merge, quick, and selection sort algorithms
 * CSC 1351 Project 3
 * @author Trevor DeBardeleben
 * @since 3/11/2016
 * @see ArrayUtil
 * @version 1
 */
public class Sorter 
{
    public static void selectionSort(int[] data)
    {
        for (int i = 0; i < data.length -1; i++)
        {
            int minPos = minimumPosition(data, i);
            ArrayUtil.swap(data, minPos, i);
        }
    }
    private static int minimumPosition(int[] data, int from)
    {
        int minPos = from;
        for(int i = from + 1; i < data.length; i++)
        {
            if (data[i] < data[minPos])
                minPos = i;
        }
        return minPos;
    }
    
    public static void insertionSort(int[] data)
    {
        for(int i = 1; i < data.length; i++)
        {
            int next = data[i];
            int j = i;
            while (j > 0 && data[j - 1] > next)
            {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = next;
        }
    }
    
    public static void quickSort(int[] data)
    {
        quickSort(data, 0, data.length - 1);
    }
    private static void quickSort(int[] data, int from, int to)
    {
        if (from >= to)
            return;
        int p = partition(data, from, to);
        quickSort(data, from, p);
        quickSort(data, p+1, to);
    }
    private static int partition(int[] data, int from, int to)
    {
        int pivot = data[from];
        int i = from - 1;
        int j = to + 1;
        while(i < j)
        {
            i++;
            while (data[i] < pivot)
                i++;
            j--;
            while(data[j] > pivot)
                j--;
            if (i < j)
                ArrayUtil.swap(data, i, j);
        }
        return j;
    }
    
    public static void mergeSort(int[] a)
    {
        if (a.length <= 1)
            return;
        int[] first = new int[a.length / 2];
        int[] second = new int[a.length - first.length];
        
        for(int i =0; i < first.length; i++)
            first[i] = a[i];
        for (int i = 0; i < second.length; i++)
            second[i] = a[first.length + i];
        mergeSort(first);
        mergeSort(second);
        merge(first, second, a);
    }
    private static void merge(int[] first, int[] second, int[] data)
    {
        int iFirst = 0;
        int iSecond = 0;
        int j = 0;
        
        while (iFirst < first.length && iSecond < second.length)
        {
            if (first[iFirst] < second[iSecond])
            {
                data[j] = first[iFirst];
                iFirst++;
            }
            else
            {
                data[j] = second[iSecond];
                iSecond++;
            }
            j++;
            
            while (iFirst < first.length)
            {
                data[j] = first[iFirst];
                iFirst++;
                j++;
            }
            
            while (iSecond < second.length)
            {
                data[j] = second[iSecond];
                iSecond++;
                j++;
            }
        }
    }
}
