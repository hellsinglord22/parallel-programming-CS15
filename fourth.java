package com.example.hii;

/**
 * Created by user on 3/4/2015.
 */
import static edu.rice.hj.Module1.async;
import static edu.rice.hj.Module1.finish;

import java.util.Random;
public class fourth {
    private static double sum1;
    private static double sum2;
    private static double sum;
    public static void main(final String[] argv) {
        int n = 100000000;
        int[]X = new int[n];
       final  Random r = new Random(n);
        for(int i=0; i<n;i++) {
            X[i]=r.nextInt();

        }
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
        System.out.println("seqArraySum"+ timeInNanos/ 1e6);
        final long startTime1 = System.nanoTime();
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
        final double sum3 = sum1 + sum2;
        final long timeInNanos1 = System.nanoTime() - startTime1;
        System.out.println("parArraySum" + timeInNanos1/ 1e6);

    }
}
