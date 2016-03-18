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

    public static int[] sort(int[] a)
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

    public static String merge(String s, String t)
    {
        int i = 0;
        int j = 0;
        int k = 0;

        char[] array1 = s.toCharArray();
        char[] array2 = t.toCharArray();
        char[] finalf = new char[s.length() + t.length()];

        while(i < s.length() && j < t.length())
        {
            finalf[k++] = array1[i++];
            finalf[k++] = array1[j++];
        }

        while(i < s.length())
        {
            finalf[k++] = array1[i++];
        }

        while(j < t.length())
        {
            finalf[k++] = array2[j++];
        }

        return String.valueOf(finalf);
    }

    public static int[] index(int[] a)
    {
        if(a.length < 3)
            throw new NoSuchElementException("Illegal interval!");

        int[] index = {0, 1, 2};

        if(a[index[0]] > a[index[1]]) swap(index, 0, 1);
        if(a[index[1]] > a[index[2]]) swap(index, 1, 2);
        if(a[index[0]] > a[index[1]]) swap(index, 0, 1);

        return index;
    }

    public static int[] thirdMin(int[] a)
    {
        int n = a.length;
        if(n < 3)
            throw new IllegalArgumentException("Illegal interval!");

        int[] index = {0, 1, 2};

        int min = min(a);
        int max = max(a);

        for(int i = 0; i < 3; i++)
        {
            if(a[i] <= min)
            {
                min = a[i];
                index[0] = i;
            }
            if(a[i] >= max)
            {
                max = a[i];
                index[2] = i;
            }
        }

        index[1] = Math.abs( (index[0] + index[2] - 3));

        return index;
    }

    public static int min(int[] a)
    {
        return min(a, 0, a.length);
    }

    public static int min(int[] a, int from, int to)
    {
        if(from < 0 || to > a.length || from >= to)
            throw new IllegalArgumentException("Illegal argument!");

        int min = from;
        int minValue = a[min];

        for(int i = (from + 1); i < to; i++)
            if(a[i] < minValue)
            {
                min = i;
                minValue = a[min];
            }

        return minValue;
    }

    public static int characterNumber(char character)
    {
        if(character <= 'Z') return character - 'A'; // A->0, B->1, C->2...
        if(character == 'Ø') return 27; // Ø->27
        if(character == 'Å') return 28; // Å->28
        else return 26; // Æ->26
    }

    public static boolean contained(String a, String b)
    {
        if(a.length() > b.length())
            return false;

        int[] number = new int[29];

        for(int i = 0; i < a.length(); i++) number[characterNumber(a.charAt(i))]++;
        for(int i = 0; i < b.length(); i++) number[characterNumber(b.charAt(i))]--;

        for(int i = 0; i < number.length; i++) if(number[i] > 0) return false;

        return true;
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
