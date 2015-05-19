package com.example.hii;
import edu.rice.hj.api.HjCallable;
import edu.rice.hj.api.HjFuture;
import edu.rice.hj.api.HjMetrics;
import edu.rice.hj.runtime.config.HjSystemProperty;
import edu.rice.hj.runtime.metrics.AbstractMetricsManager;
import edu.rice.hj.runtime.util.Pair;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static edu.rice.hj.Module1.*;

/**
 * Created by user on 5/5/2015.
 */
public class memorized {
   /**
     * Pascal's Triangle --- Computes (n C k) using futures
     * <p>
     * The purpose of this example is to illustrate effects on memoization with abstract metrics while using futures.
     * C(n, k) = C(n - 1, k - 1) + C(n - 1, k)
     *
     * @author Shams Imam (shams@rice.edu)
     * @author Vivek Sarkar (vsarkar@rice.edu)
     */


        public static void main(final String[] args) {

            final int n =  4;
            final int k =  2;
            initializeHabanero();
            final int res =chooseMemoizedSeq(n, k);
            final int res1=chooseMemoizedPar(n, k);
            finalizeHabanero();
            System.out.println(" N = " + n);
            System.out.println(" K = " + k);
 //            System.out.println(" N = " + n);
            System.out.println(" resultseq = " +res );
            System.out.println(" resultpar = " +res1 );
           // kernel("Recursive Version (Sequential)", n, k, () -> chooseRecursiveSeq(n, k));
            //kernel("Recursive Version (Parallel)", n, k, () -> chooseRecursivePar(n, k));
            //kernel("Memoized Version (Sequential)", n, k, () -> chooseMemoizedSeq(n, k));
           // kernel("Memoized Version (Parallel)", n, k, () -> chooseMemoizedPar(n, k));

        }




        private static final Map<Pair<Integer, Integer>, Integer> chooseMemoizedSeqCache = new ConcurrentHashMap<>();

        private static int chooseMemoizedSeq(final int N, final int K) {

            final Pair<Integer, Integer> key = Pair.factory(N, K);
            if (chooseMemoizedSeqCache.containsKey(key)) {
                final Integer result = chooseMemoizedSeqCache.get(key);
                return result;
            }

            if (N == 0 || K == 0 || N == K) {
               // final Integer result = computeBaseCaseResult();
                //chooseMemoizedSeqCache.put(key, result);
                return 1;
            }

            final int left = chooseMemoizedSeq(N - 1, K - 1);
            final int right = chooseMemoizedSeq(N - 1, K);

            final int result = left+ right;
            chooseMemoizedSeqCache.put(key, result);
            return result;
        }

        private static final Map<Pair<Integer, Integer>, HjFuture<Integer>> chooseMemoizedParCache = new ConcurrentHashMap<>();

        private static int chooseMemoizedPar(final int N, final int K) {

            final Pair<Integer, Integer> key = Pair.factory(N, K);
            if (chooseMemoizedParCache.containsKey(key)) {
                final HjFuture<Integer> result = chooseMemoizedParCache.get(key);
                return result.get();
            }

            final HjFuture<Integer> resultFuture = future(() -> {
                if (N == 0 || K == 0 || N == K) {
                    return 1;
                }

                final HjFuture<Integer> left = future(() -> chooseMemoizedPar(N - 1, K - 1));
                final HjFuture<Integer> right = future(() -> chooseMemoizedPar(N - 1, K));

                final Integer leftValue = left.get();
                final Integer rightValue = right.get();
                final Integer  computeSum =leftValue+rightValue;
                return computeSum ;
            });
            chooseMemoizedParCache.put(key, resultFuture);
            return resultFuture.get();
        }

    }


