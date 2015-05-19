package com.example.hii;

import java.util.Random;

import static edu.rice.hj.Module1.async;
import static edu.rice.hj.Module1.finish;

/**
 * Created by user on 2/24/2015.
 */
public class second {
    //public static final String ERROR_MSG = "Incorrect argument for array size (should be > 0), assuming n = 25,000,000";
    /**
     * Constant <code>DEFAULT_N=100_000_000</code>
     */
    public static final int DEFAULT_N = 100_000_000;

    private static double sum1;
    private static double sum2;
    private static double sum;

    /**
     * <p>seqArraySum.</p>
     *
     * @param X an array of double.
     * @return a double.
     */
    public static double seqArraySum(final int[] X) {
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

    /**
     * <p>parArraySum.</p>
     *
     * @param X an array of double.
     * @return a double.
     */
    public static double parArraySum(final int[] X) {
        // Start of Task T0 (main program)
        final long startTime = System.nanoTime();
        sum1 = 0;
        sum2 = 0;
        finish(() -> {
            async(() -> {
                // Compute sum of lower half of array
                for (int i = 0; i < X.length / 2; i++) {
                    sum1 +=  X[i];
                }
            });
            // Compute sum of upper half of array
            async(() -> { for (int i = X.length / 2; i < X.length; i++) {
                sum2 += X[i];
            } });
           // sum = sum1 + sum2;
        });
        // Combine sum1 and sum2
        final double sum = sum1 + sum2;
        final long timeInNanos = System.nanoTime() - startTime;
        printResults("parArraySum", timeInNanos, sum);
        // Task T0 waits for Task T1 (join)
        return sum;
    }

    private static void printResults(final String name, final long timeInNanos, final double sum) {
        System.out.printf("  %s completed in %8.3f milliseconds, with sum = %8.5f \n", name, timeInNanos / 1e6, sum);
    }

    /**
     * <p>main.</p>
     *
     * @param argv an array of {@link String} objects.
     */
    public static void main(final String[] argv) {
        // Initialization
        int n = 100000;
        //  if (argv.length != 0) {
        //  try {
        // n = Integer.parseInt(argv[0]);
        // if (n <= 0) {
        // Bad value of n
        // System.out.println(ERROR_MSG);
        // n = DEFAULT_N;
        // }
        // } catch (Throwable e) {
        //  System.out.println(ERROR_MSG);
        // n = DEFAULT_N;
        // }
        //} else { // argv.length == 0
        // n = DEFAULT_N;

        // }
        final int[] X = new int[n];
        final Random myRand = new Random(n);

        for (int i = 0; i < n; i++) {
            X[i] = myRand.nextInt(n);
            if (X[i] == 0.0) {
                i--;
            }
            //}
           // finish(() -> {
                seqArraySum(X);
                parArraySum(X);
            //});
            //initializeHabanero();

        for (int numRun = 0; numRun < 5; numRun++) {
            System.out.printf("Run %d\n", numRun);
            finish(() -> {
                seqArraySum(X);
                parArraySum(X);
            });
        }

            // finalizeHabanero();

        }
    }}
