package com.example.hii;

/**
 * Created by user on 3/24/2015.
 */
import edu.rice.hj.api.HjFuture;
import static edu.rice.hj.Module1.future;
import edu.rice.hj.api.HjMetrics;
import edu.rice.hj.runtime.config.HjSystemProperty;
import edu.rice.hj.runtime.metrics.AbstractMetricsManager;
import java.util.Random;
import static edu.rice.hj.Module1.*;

public class addrecursive {
    static final int default_n = 128;
    static final String err = "Incorrect argument for array size (should be > 0), assuming n = " + default_n;
    /**
     * Computes the sum of an array of integers between two indexes
     * @param X the array to sum up
     * @param lo The leftmost index to use in the sum
     * @param hi the rightmost index to use in the sum
     * @return - the sum of all elements in between the two indexes
     */
    public static int computeSum(int[] X, int lo, int hi) {
        if ( lo > hi ) {
            return 0;
        } else if ( lo == hi ) {
            return X[lo];
        }
        else {
            int mid = (lo + hi) / 2;
            final HjFuture<Integer> sum1 = future(() -> computeSum(X, lo, mid));
            final HjFuture<Integer> sum2 = future(() -> computeSum(X, mid + 1, hi));
           // final int local_sum = sum1.get() + sum2.get();
           // final int sum1 = computeSum(X, lo, mid);
            //final int sum2 = computeSum(X, mid+1, hi);
             // Parent now waits for the container values!
             return sum1.get() + sum2.get();

          //  doWork(1); // Assume that add takes 1 unit of time, and that everything else is free
           // return local_sum;
        }

    } // computeSum
    /**
     * Main function. Input can take one parameter, which is size
     * of the array. If there are no input parameters, use a default size
     * @param argv - the input parameter
     */
    public static void main(final String[] args) {
        // Setup metrics
        final int[] sum = {0};
       // System.setProperty(HjSystemProperty.abstractMetrics.propertyKey(), "true");
        initializeHabanero();
        int n = 128;
        // Initialization

        int[] X;

        X = new int[n];
        Random myRand = new Random(n);
        for (int i = 0; i < n; i++)
            X[i] = i;
                    //myRand.nextInt(n);
        // Recursive parallel computation
        finish(()->{
            for (int i = 0; i < numWorkerThreads(); i++) {
               // result[i] = fib(i);
        //        System.out.printf("fib(%2d) = %2d \n", i, result[i]);
        sum[i] = computeSum(X, 0, n-1);
           }});
        // Output
        System.out.println("Sum of " + n + " elements = " + sum[0]);
        // Print out the metrics data
        finalizeHabanero();
        //final HjMetrics actualMetrics = abstractMetrics();
        //AbstractMetricsManager.dumpStatistics(actualMetrics);
        //System.out.println(actualMetrics);
    }

    }


