package loan.ammortization;
/**
 * purpose: to calculate preform loan amortization
 * by calculating interest paid, total amount paid, and monthly payments, 
 * given the annual interest rate, number of payments, and loan amount. 
 * 
 * CSC 1350 Project # 1
 * @author tdebar2 (Trevor DeBardeleben)
 * @since 9/13/15
 */
import java.util.Scanner;
public class LoanAmmortization 
{
    public static void main(String[] args) 
    {
        
        double mRate, annualRate, payments, loanAmt, mPayment, amtPaid, intrstPaid;
        
        Scanner rateInput = new Scanner(System.in);
        Scanner paymentsInput = new Scanner(System.in);
        Scanner loanAmtInput = new Scanner(System.in);
       
        System.out.print("Enter the annual interest rate> ");
        annualRate = rateInput.nextInt();
        System.out.print("Enter the number of payments to be made> ");
        payments = paymentsInput.nextInt();
        System.out.print("Enter the loan amount> ");
        loanAmt = loanAmtInput.nextInt();
        System.out.println();
        
        mRate = annualRate/12;
        mPayment = (mRate/100*Math.pow(1+mRate/100,payments)*loanAmt)/(Math.pow(1+mRate/100, payments)-1);
        intrstPaid = mPayment * payments - loanAmt;
        amtPaid = mPayment * payments;
        
       
        System.out.printf("Loan ammount:                $ %15.2f%n", loanAmt);
        System.out.printf("Monthly interest rate:        %15.3f%%%n", mRate);
        System.out.printf("Number of payments:            %15.0f%n", payments);
        System.out.printf("Monthly payment:             $ %15.2f%n", mPayment);
        System.out.printf("Amount Paid Back:            $ %15.2f%n", amtPaid);
        System.out.printf("Interest Paid:               $ %15.2f%n", intrstPaid);
        
        
        
    }
    
}
