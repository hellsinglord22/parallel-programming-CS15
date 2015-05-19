
    package com.example.hii;

/**
 * Created by user on 4/7/2015.
 */
import static edu.rice.hj.Module1.async;
import static edu.rice.hj.Module1.finish;

public class mergesort {
    public static void main(final String[] args) {

        finish(() -> {
          // int size = 10;

                java.util.Random rand = new java.util.Random(0);
                final int[] A = new int[]{34,5667,89,67,78};
                final int[] buff = new int[6]; // buffer used for copying during merge step
               // for (int i = 0; i < size; i++) {
                 //   A[i] = rand.nextInt();
                    //System.out.println(A[i]);
                //}
                    for (int i = 0; i < 5; i++) {
                       // A[i] = rand.nextInt();
                        System.out.println(A[i]);
                    }

                //executeProgram(4, A, buff);
                    ///////////////////////
                    final long parStartTime = System.nanoTime();

                    finish(() -> {
                        mergesort(A, 0, 5-1 );
                        //mergesortamal(A);
                    });

                    final long parExecTime = System.nanoTime() - parStartTime;


                    System.out.println("Parallel sort completed successfully in " + parExecTime / 1e6 + " milliseconds");
                    //////////////////////
                    for (int i = 0; i < 5; i++) {
                        // A[i] = rand.nextInt();
                        System.out.println(A[i]);
                    }
            }
        );
    }

    private static void executeProgram(final int size, final int[] A, final int[] buff) {
        // For sequential sort, set threshold = size
        final long seqStartTime = System.nanoTime();

        finish(() -> {
            mergesort(A, 0, size - 1);
        });

        final long seqExecTime = System.nanoTime() - seqStartTime;


            System.out.println("Sequential sort completed successfully in " + seqExecTime / 1e6 + " milliseconds");



        // For parallel sort, use default or user-specified threshold
        final long parStartTime = System.nanoTime();

        finish(() -> {
            mergesort(A, 0, size - 1);
        });

        final long parExecTime = System.nanoTime() - parStartTime;


            System.out.println("Parallel sort completed successfully in " + parExecTime / 1e6 + " milliseconds");


    }

    private static void mergesort(final int[] A, final int M, final int N) {
        int[] buff = new int[N+1];
        if (M < N) {
            final int mid = M + N  / 2;

            if ( mid < N) { // parallel version
                finish(() -> {
                    async(() -> mergesort(A, M, mid));
                    async(() -> mergesort(A, mid + 1, N));
                });
            }
            merge(A, M, mid, N, buff);
        }
    }
    private static void mergesortamal(final int[] A) {
        int[]buff= new int[A.length];
        int n = A.length-1;
        if(n<2) return;
        int mid = (0+ A.length)/2;
        int[]left= new int[mid];
        int[]right= new int[n-mid+1];
        for(int i =0;i<mid;i++)
            left[i]=A[i];
        for(int i =mid;i<n;i++)
            right[i]=A[i];
        finish(() -> {
            async(() -> mergesortamal(left));
            async(() -> mergesortamal(right));
        });
               // mergesortamal(left);
                //mergesortamal(right);
        merge(A,0, mid, A.length, buff);



    }

    private static void merge(final int[] A, final int M, final int mid, final int N, final int[] buff) {
        // make a copy in buff[M:N]
        for (int k = M; k <= N; k++) {
            buff[k] = A[k];
        }

        // merge from buff to A
        int i = M;
        int j = mid + 1;
        for (int k = M; k <= N; k++) {
            if (i > mid) {
                A[k] = buff[j++];
            } else if (j > N) {
                A[k] = buff[i++];
            } else if (buff[j] < buff[i]) {
                A[k] = buff[j++];
            } else {
                A[k] = buff[i++];
            }
        }
    }

}


