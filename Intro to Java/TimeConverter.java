package timeconverter;
import java.util.Scanner;
/**
 * This program will convert 24 hour time to 12 hour time
 * CSC 1350 Lab #4* 
 * @author tdebar2
 * @sicne 9/21/2015
 */
public class TimeConverter 
{

    public static void main(String[] args) 
    {
        int time, hour, minute;
        Scanner inputTime = new Scanner(System.in);
        
        
        System.out.print("Enter an integer [0-2400] for the 24-hour clock time -> ");
        time = inputTime.nextInt();
        hour = time / 100;
        minute = time % 100;
        
        if (minute >= 60)
        {
            System.out.println(time + " is not a valid 24-hour clock time.");
        }
        else if (hour > 24)
        {
            System.out.println(time + " is not a valid 24-hour clock time.");
        }
        else if (time == 0)
        {
            System.out.println("0 hours is equivalent to 12:00 AM.");
        }
        else if (time >= 1 && time <= 1159)
        {
            if (hour == 0)
            {
                System.out.printf("%d is equivalent to 12:%02d AM%n", time, minute);
            }
            else
                System.out.printf("%d is equivalent to %2d:%02d AM.%n", time, hour, minute);
        }
        else if (time >= 1200 && time <= 1259)
        {
            System.out.printf("%d is equivalent to %2d:%02d PM.%n", time, hour, minute);
        }
        else if (time>=1300 && time <= 2359)
        {
            hour = hour - 12;
            System.out.printf("%d is equivalent to %2d:%02d PM.%n", time, hour, minute);
        }
        else if (time == 2400)
        {
            System.out.println(time + " is equivalent to 12:00 AM");
        }
        else
            System.out.println(time + " is not a valid 24-hour clock time");
        
        
        
        
    }
    
}
