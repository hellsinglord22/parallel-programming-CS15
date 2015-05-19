package com.example.hii;
import java.util.Random;
import static edu.rice.hj.Module1.*;
import static edu.rice.hj.Module1.async;
import static edu.rice.hj.Module1.finish;

/**
 * Created by user on 3/3/2015.
 */
public class third {
    //public static final String ERROR_MSG = "Incorrect argument for array size (should be > 0), assuming n = 25,000,000";
//    public static final int DEFAULT_N = 100_000_000;
    private static double sum1;
   // private static double sum;
    private static double sum2;
   // public static double[] X={56,78,89,90,67,890};

    public static double seqArraySum(final double[] X) {
        final long startTime = System.nanoTime();
        sum1 = 0;
        sum2 = 0;
        // Compute sum of lower half of array
        for (int i = 0; i < X.length / 2; i++) {
            sum1 +=  X[i];
        }
        // Compute sum of upper half of array
        for (int i = X.length / 2; i < X.length; i++) {
            sum2 +=  X[i];
        }
        // Combine sum1 and sum2
        final double sum = sum1 + sum2;
        final long timeInNanos = System.nanoTime() - startTime;
        printResults("seqArraySum", timeInNanos, sum);
        // Task T0 waits for Task T1 (join)
        return sum;
    }

    public static double parArraySum(final double[] X) {
        // Start of Task T0 (main program)
        final long startTime = System.nanoTime();
        sum1 = 0;
        sum2 = 0;
        finish(() -> {
            async(() -> {
                // Compute sum of lower half of array
                for (int i = 0; i < X.length / 2; i++) {
                    sum1 +=  X[i];
                }});
                async(() -> {
                    for (int i = X.length / 2; i < X.length; i++) {
                        sum2 +=  X[i];
                    }
                });




                // Compute sum of upper half of array

                // Task T0 waits for Task T1 (join)
                //

        });
        double sum = sum1 + sum2;



        long timeInNanos = System.nanoTime() - startTime;
        printResults("parArraySum", timeInNanos, sum);


                return sum;
        // Combine sum1 and sum2

    }

    private static void printResults(final String name, final long timeInNanos, final double sum) {
        System.out.printf("  %s completed in %8.3f milliseconds, with sum = %8.5f \n", name, timeInNanos / 1e6, sum);
    }

    public static void main(final String[] argv) {
        // Initialization
       // int n=10000000;
       double[] X={56,78,89,90,67,890,7889,899,890,566,789,890,567,678,345,7890,2345,5678,5678,4567,6789,4567,5678,789,789,89,8789,4556,6788,678,4567,678,678,345,456,689,789,789,9890,876,765,876,567,567,667,789,456,345,567,567,678,345,5678};

       //X = new double[n];
        //final Random myRand = new Random(n);

        //for (int i = 0; i < n; i++) {
          //  X[i] = myRand.nextInt(n);
           // if (X[i] == 0.0) {
             //  i--;
          //}
       //}

       // initializeHabanero();

        //for (int numRun = 0; numRun < 5; numRun++) {
           // System.out.printf("Run %d\n", numRun);
            finish(() -> {
               seqArraySum(X);
                parArraySum(X);
           });
        //}

        //finalizeHabanero();

    }
}


