package io.osvold.algdat.requirements.one;

/**
 * Created by hans on 17.03.16.
 */
public class Test {

    public static void main(String[] args)
    {
        int[] a = {1,2,3,4,5,6,7,8,9,10,1,20,3};
        int[] b = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(Requirement.max(a));
        System.out.println(Requirement.swaps(a));
        System.out.println(Requirement.swaps(b));
    }
}
