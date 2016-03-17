package course.requirements.one;

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

    public static int max(int[] a, int i, int j)
    {
        throw new IllegalStateException("Not done yet.");
    }

    public static void swap(int[] a, int i, int j)
    {
        int temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

}
