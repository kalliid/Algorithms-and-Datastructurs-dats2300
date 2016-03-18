package course.requirements.one;

import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by hans on 17.03.16.
 */
public class Requirement {

    private Requirement(){}

    /**
     * @param a unsorted integer array.
     * @return the larges value in the array.
     */
    public static int max(int[] a)
    {
        int n = a.length;

        if(n < 1)
            throw new NoSuchElementException("Illegal interval");

        if(n == 1)
            return a[0];

        if(a[0] > a[1])
            swap(a, 0, 1);

        for(int i = 1; i < n - 1; i++)
            if(a[i] > a[i + 1])
                swap(a, i, (i+1));

        return a[n-1];
    }

    /**
     *
     * @param a unsorted integer array.
     * @return the number of swops to find the larges value in the array.
     */
    public static int swaps(int[] a)
    {
        int n = a.length;

        if(n < 1)
            throw new NoSuchElementException("Illegal interval");

        int p = 0;

        if(a[0] > a[1])
        {
            swap(a, 0, 1);
            p++;
        }

        for(int i = 1; i < n-1; i++)
        {
            if(a[i] > a[i + 1])
            {
                swap(a, i, (i+1));
                p++;
            }
        }

        return p;
    }

    public static int[] sorting(int[] a)
    {
        int m;

        for(int i = a.length; i > 1; i--)
        {
            m = max(a, 0, i);
            swap(a, (i-1), m);
        }

        return a;
    }

    /**
     * @return a number with only 0, 1, 2, 3, 4 and 5,
     * equals a number with the Integer-representation of base6.
     * Number 100000 == 6^4 = 1296 and 55555 == 6^5 -1 == 7775.
     */
    public static int generateNumber()
    {
        int number = 0;
        int ceiling = 7775;
        int floor = 1296;

        for(int i = floor; i < ceiling; i++)
        {
            char[] chars = Integer.toString(i, 6).toCharArray();
            int[] a = new int[6];
            for(char c : chars)
                a[c - '0']++;
            if(max(a) < 3) number++;
        }
        return number;
    }

    public static int numDiffUnsorted(int[] a)
    {
        int max = 0;

        int[] temp = new int[a.length];

        if(a.length == 0)
            return 0;

        for(int i = 0; i < a.length; i++)
            for(int j = (i+1); j < a.length; j++)
                if(a[i] == a[j])
                    temp[i] = -1;

        for (int aTemp : temp)
            if (aTemp != -1)
                max++;

        return max;
    }

    public static char[] rotation(char[] a)
    {
        int n = a.length;

        if(n < 2)
            return a;

        if(n == 2)
        {
            char temp = a[0];
            a[0] = a[1];
            a[1] = temp;
            return a;
        }

        char[] b = new char[n];

        b[0] = a[n - 1];

        System.arraycopy(a, 0, b, 1, n - 1);
        System.arraycopy(b, 0, a, 0, n);

        return a;
    }

    // Euklids algorithm
    public static int gcd(int a, int b)
    {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void rotation(char[] c, int k)
    {
        int n = c.length;

        if(n < 2)
            return;

        if((k %= n) < 0)
            k += n;

        int s = gcd(n, k);

        for(int i = 0; i < s; i++)
        {
            char value = c[i];
            for(int j = (i - k), l = i; j != i; j -= k)
            {
                if(j < 0)
                    j += n;

                c[l] = c[j];
                l = j;
            }
            c[i + k] = value;
        }
    }

    public static int max(int[] a, int i, int j)
    {
        throw new IllegalStateException("Not done yet.");
    }

    public static void swap(int[] a, int i, int j)
    {
        int temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

}
