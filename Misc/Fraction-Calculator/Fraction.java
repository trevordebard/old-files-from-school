package fractiondemo;

public class Fraction
{
    /**
     * The numerator
     */
    private int top;
    
    /**
     * The denominator.
     */
    private int bottom;
	
    /**
     * Computes the greatest common denominator of the specified
     * integers.
     * @param m an integer
     * @param n an integer
     * @return the greatest common denominator of the specified integer
     * or 1/0 when both integers are 0.	 
     */
    private int gCD(int m, int n)
    {
        if (m == 0)
        {
            if (n == 0)
            {
                return 1/0;
            }
            return Math.abs(m+n);
        }
        else if (n == 0)
            return Math.abs(m+n);
        else 
        {
            m=Math.abs(m);
            n=Math.abs(n);
            while (n != 0)
            {
                int r = m%n;
                m = n;
                n = r;
            }
            return m;
        }
    }
    
    /**
     * Creates 0/1
     */
    public Fraction()
    {
       top = 0;
       bottom = 1;
    }	
	
    /**
     * Creates a fraction with the specified numerator whose denominator is 1.
     * @param n numerator
     */
    public Fraction(int n)
    {
        top = n;
        bottom = 1;
    }    

    /**
     * Creates a fraction with the specified numerator and denominator
     * @param n numerator
     * @param d denominator
     */  
    public Fraction(int n, int d)
    {
        top = n;
        bottom = d;
    }    
    
    /**
     * Adds two fractions and then normalizes the result
     * @param f the fraction to be added to this fraction
     * @return the sum of this fraction and f
     */
    public Fraction add(Fraction f) 
    {
        Fraction sumFract = new Fraction();
        sumFract.bottom = bottom * f.bottom;
        sumFract.top = top * f.bottom + f.top * bottom;
        
        if (sumFract.bottom < 0)
        {
            sumFract.top *= -1;
            sumFract.bottom *= -1;
        }
        
         sumFract.top /= gCD(sumFract.top, sumFract.bottom);
         sumFract.bottom /= gCD(sumFract.top, sumFract.bottom);
      
        return sumFract;
    }
    
    /**
     * Subtracts two fractions and then normalizes the result
     * @param f the fraction to be subtracted from this fraction
     * @return the difference between this fraction and f
     */
    public Fraction subtract(Fraction f) 
    {
        Fraction subFract = new Fraction();
        subFract.bottom = bottom * f.bottom;
        subFract.top = top * f.bottom - f.top * bottom;
        
        if (subFract.bottom < 0)
        {
            subFract.top *= -1;
            subFract.bottom *= -1;
        }
        
        return new Fraction ((subFract.top/(gCD(subFract.top,subFract.bottom))),
            (subFract.bottom/(gCD(subFract.top,subFract.bottom))));
      
    }
    
    /**
     * Multiplies two fractions and then normalizes them
     * @param f the fraction to be multiplied by this fraction
     * @return the result of this fraction times f
     */
    public Fraction multiply(Fraction f) 
    {
        Fraction multFract = new Fraction();
        multFract.top = top * f.top;
        multFract.bottom = bottom * f.bottom;
        
        if (multFract.bottom < 0)
        {
            multFract.top *= -1;
            multFract.bottom *= -1;
        }
        
        return new Fraction ((multFract.top/(gCD(multFract.top,multFract.bottom))),
            (multFract.bottom/(gCD(multFract.top,multFract.bottom))));
    }
    
    /**
     * Divides two fractions and then normalizes them
     * @param f the divisor
     * @return the result of this fraction divided by f
     */
    public Fraction divide(Fraction f) 
    {
        int topQuot = top * f.bottom;
        int bottomQuot = bottom * f.top;
        if (bottomQuot < 0)
        {
            topQuot *= -1;
            bottomQuot *= -1;
        }
        return new Fraction(topQuot/gCD(topQuot, bottomQuot), bottomQuot/gCD(topQuot, bottomQuot));
    }
    
    /**
     * Tests whether two fractions are equal
     * @param f the fraction being compared to this fraction
     * @return true if both fractions are equal
     */
    public boolean equals(Fraction f)
    {
        return top == f.top && bottom == f.bottom;
    }
    
    /**
     * Gives a string representation of this fraction
     * in inline notation
     * @return a string representation of this Fraction
     */
    public String toString()
    {
        return String.format("%d/%d", top, bottom);
    }   
}
