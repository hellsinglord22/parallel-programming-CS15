package com.example.hii;
import java.util.Random;
//import hj.lang.perf;
import static edu.rice.hj.Module1.*;
import edu.rice.hj.api.HjFuture;

/**
 * Created by user on 3/31/2015.
 */
public class arraysum {
    static final int default_n = 128;
    static final String err = "Incorrect argument for array size (should be > 0), assuming n = " + default_n;

    static int computeSum(int[] X, int lo, int hi) {
      //  final int sum1,sum2;
       // if ( lo > hi ) {
         //   return 0;
        //} else if ( lo == hi ) {
          //  return X[lo];
        //} else {
          //  int mid = (lo+hi)/2;
          //  final HjFuture <Integer> sum1 = future(()-> computeSum(X, lo, mid));
           // final HjFuture<Integer> sum2 = future(()->computeSum(X, mid+1, hi));
           // int local_sum = sum1.get() + sum2.get();
            final HjFuture <Integer> sum1 = future(()->{
            int sum=0;// Future Task T2!int sum = 0; !
                         for(int i=0 ; i < X.length/2 ; i++) sum += X[i];
                         return sum;
                         });
                     final HjFuture <Integer> sum2 = future(()->{
                         int sum=0;
                         for(int i=X.length/2 ; i < X.length ; i++) sum += X[i];
                         return sum;
                         });
                    //Task T1 waits for Tasks T2 and T3 to complete!
             int total = sum1.get() + sum2.get();
            // Variant of ArraySum1 -- count work as sum of the bits in input operands,
            // and assume that everything else is free
            //hj.lang.perf.doWork(countBits(sum1.get()) + countBits(sum2.get()));
            return total;

    } // computeSum

    public static void main(final String[] args) {
        // Initialization
        int n=70;
        int[] X;
        X = new int[n];
        Random myRand = new Random(n);

        for (int i = 0; i < n; i++)
            X[i] = myRand.nextInt(n);
        //initializeHabanero();

        final int[] result = new int[n];
        finish(() -> {


        // Recursive parallel computation
        int sum = computeSum(X, 0, n-1);

        // Output
        System.out.println("Sum of " + n + " elements = " + sum);
        });
        //finalizeHabanero();
    }

    /*
    * Return the number of bits in x for use in abstract performance metrics.
    * If x is negative, return the number of bits in -x.
    */
    static int countBits(int x) {
        // Convert x to a positive value
        if (x == Integer.MIN_VALUE) x = Integer.MAX_VALUE;
        else if (x < 0) x = -x;
        return 32 - Integer.numberOfLeadingZeros(x);
    }
}
