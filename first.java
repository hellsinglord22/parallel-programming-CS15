package com.example.hii;
import static edu.rice.hj.Module1.async;
import static edu.rice.hj.Module1.finish;

/**
 * Created by user on 2/24/2015.
 */
public class first {


    /**
     * <p>HelloWorld class.</p>
     *
     * @author Shams Imam (shams@rice.edu)
     */

        /**
         * <p>main.</p>
         *
         * @param args an array of {@link String} objects.
         */
        public static void main(final String[] args) {
            finish(
                    () -> {
                        async(
                                () -> {//busyWaitAndThenPrint("Hello World - 1");
                                    int waitNum = (int) (Math.random() *100);
                                    for (int i = 0; i < waitNum; i++) {
                                        // dummy method calls
                                        //  Math.random();
                                    }

                                    System.out.printf("   message hi1 waittime %d ", waitNum); }
                        );
                        async(
                                () ->{// busyWaitAndThenPrint("Hello World - 2"){//busyWaitAndThenPrint("Hello World - 1");
                        int waitNum = (int) (Math.random() *100);
                        for (int i = 0; i < waitNum; i++) {
                            // dummy method calls
                            //  Math.random();
                        }

                        System.out.printf("   message hi2 waittime %d ", waitNum); }
                        );
                        async(
                                () ->{//busyWaitAndThenPrint("Hello World - 1");
                                    int waitNum = (int) (Math.random() *100);
                                    for (int i = 0; i < waitNum; i++) {
                                        // dummy method calls
                                        //  Math.random();
                                    }

                                    System.out.printf("   message hi3 waittime %d ", waitNum); } );

                    }
            );
        }

        private static void busyWaitAndThenPrint(final String message) {
            final int waitNum = (int) (Math.random() *100);
            for (int i = 0; i < waitNum; i++) {
                // dummy method calls
              //  Math.random();
            }

            System.out.printf("   message %s waittime %d ", message, waitNum);
            //System.out.println(message);
        }
    }


