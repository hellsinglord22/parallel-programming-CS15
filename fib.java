package com.example.hii;

/**
 * Created by user on 3/24/2015.
 */
import edu.rice.hj.api.HjFuture;

import static edu.rice.hj.Module1.async;
import static edu.rice.hj.Module1.finish;
import static edu.rice.hj.Module1.future;

public class fib {
        public static int fib(final int n) {

            if (n <= 0) {
                return 0;
            } else if (n == 1) {
                return 1;
            }

            final HjFuture<Integer> f1 = future(() -> fib(n - 1));
            final HjFuture<Integer> f2 = future(() -> fib(n - 2));

            final int result = f1.get() + f2.get();
            return result;
        }

        public static void main(final String[] args) {
            final int N = 10;
            final int[] result = new int[N];
            finish(() -> {
                for (int i = 0; i < N; i++) {
                    result[i] = fib(i);
                    System.out.printf("fib(%2d) = %2d \n", i, result[i]);
                }
            });
        }

    }
