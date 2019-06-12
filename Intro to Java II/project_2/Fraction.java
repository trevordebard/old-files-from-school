package project2;
/**
* Defines methods that will preform various operations on fractions
* CSC 1351 Project # 2
* @author tdebar2
* @since 2/20/16
* @version 2
*/
public class Fraction implements Comparable, Arithmetic
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
                throw new IllegalArgumentException("Cannot compute GCD of two zero values");
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
        if (d == 0)
            throw new IndeterminateFractionException("Denominator cannot be zero.");
        top = n;
        bottom = d;
    }



    /**
     * Gives a string representation of this fraction
     * in in line notation
     * @return a string representation of this Fraction in the form "top/bottom"
     */
    @Override
    public String toString()
    {
        return String.format("%d/%d", top, bottom);
    }

    /**
     * Tests whether two fractions are equal
     * @param obj the object being compared
     * @return true if the fractions are equal and false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Fraction))
            return false;
        Fraction v = (Fraction) obj;
        return (top == v.bottom && bottom == v.top);
    }
    
    /**
     * Compares the cross products of two fractions
     * @param obj the second fraction
     * @return 1 if topBot is greater than botTop, -1 if topBot is less than botTop or 0 if the cross products are equal
     */
    @Override
    public int compareTo(Object obj)
    {
        if(!(obj instanceof Fraction))
            throw new IllegalArgumentException("Object must be of the type Fraction");
        Fraction v = (Fraction) obj;
        int top1 = top;
        int bot1 = bottom;
        int top2 = v.top;
        int bot2 = v.bottom;
        if (bot2 < 0)
        {
            top2 *= -1;
            bot2 *= -1;
        }
        if (bot1 < 0)
        {
            top1 *= -1;
            bot1 *= -1;
        }
        int common1 = gCD(top1, bot1);
        int common2 = gCD(top2, bot2);
        top1 /= common1;
        bot1 /= common1;
        top2 /= common2;
        bot2 /= common2;
        int topBot = top1 * bot2;
        int botTop = bot1 * top2;
        if(topBot > botTop)
            return 1;
        if (topBot < botTop)
            return -1;
        return 0;



    }

    /**
     * Computes and returns the  sum of two fractions
     * @param obj the fraction to be added to this fraction
     * @return the the sum of two fractions
     */
    @Override
    public Object add(Object obj)
    {
        if(!(obj instanceof Fraction))
            throw new IllegalArgumentException("Object must be of type Fraction");
        Fraction f = (Fraction) obj;

        int sumBot = bottom * f.bottom;
        int sumTop = top * f.bottom + f.top * bottom;

        if (sumBot < 0)
        {
            sumTop *= -1;
            sumBot *= -1;
        }
        int greatest = gCD(sumTop, sumBot);

        return new Fraction(sumTop/greatest, sumBot/greatest);
    }

    /**
     * Computes and returns the  difference of two objects of a class.
     *
     * @param obj the object being subtracted
     * @return the the modular difference of two objects
     */
    @Override
    public Object subtract(Object obj)
    {
        if(!(obj instanceof Fraction))
            throw new IllegalArgumentException("Object must be of type Fraction");
        Fraction f = (Fraction) obj;
        int subBot = bottom * f.bottom;
        int subTop = top * f.bottom - f.top * bottom;

        if (subBot < 0)
        {
            subTop *= -1;
            subBot *= -1;
        }
        int common = gCD(subBot, subTop);
        return new Fraction(subTop/common, subBot/common);
    }

    /**
     * Computes and returns the  product of two objects of a class.
     *
     * @param obj the object being multiplied
     * @return the the modular sum of two objects
     */
    @Override
    public Object multiply(Object obj)
    {
        if(!(obj instanceof Fraction))
            throw new IllegalArgumentException("Object must be of type Fraction");
        Fraction f = (Fraction) obj;
        int multTop = top * f.top;
        int multBot = bottom * f.bottom;

        if (multBot < 0)
        {
            multTop *= -1;
            multBot *= -1;
        }
        int common = gCD(multTop, multBot);
        return new Fraction (multTop/common, multBot/common);
    }

    /**
     * Computes and returns the quotient of two objects of a class.
     *
     * @param obj the object being divided
     * @return the the modular quotient of two objects
     */
    @Override
    public Object divide(Object obj)
    {
        if(!(obj instanceof Fraction))
            throw new IllegalArgumentException("Object must be of type Fraction");
        Fraction f = (Fraction) obj;
        if (f.top == 0)
            throw new ArithmeticException("Cannot divide by zero");
        int topQuot = top * f.bottom;
        int bottomQuot = bottom * f.top;
        if (bottomQuot < 0)
        {
            topQuot *= -1;
            bottomQuot *= -1;
        }
        int common = gCD(topQuot, bottomQuot);
        return new Fraction(topQuot/common, bottomQuot/common);
    }

}
