package com.example.hii;
import edu.rice.hj.api.HjMetrics;
import edu.rice.hj.api.HjRunnable;
import edu.rice.hj.runtime.config.HjSystemProperty;
import edu.rice.hj.runtime.metrics.AbstractMetricsManager;
import static edu.rice.hj.Module1.*;

/**
 * Created by user on 3/10/2015.
 */
public class bruteforcematching {
    private static final String default_pat = "shell";
    private static final String default_txt = "she sells sea shell";
    public static void main(final String[]args){
        final String pat =  default_pat;
        final String txt = default_txt;
        final char[] pattern = pat.toCharArray();
        final char[] text = txt.toCharArray();
        System.setProperty(HjSystemProperty.abstractMetrics.propertyKey(), "true");

        final int M = pattern.length;
        final int N = text.length;
        final boolean[] found = {false};

        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (text[i + j] != pattern[j]) {
                    break;
                }
            }
            if (j == M) {
                found[0] = true;
            }
        }
        if (found[0]==true)
        { System.out.println("text:    " + txt);
            System.out.println("pattern: " + pat);
            System.out.println("Pattern found by sequential algorithm: " + found[0]);
        }
        final int M1 = pattern.length;
        final int N1 = text.length;
        final boolean[] found1 = {false};
        finish(() -> {
            for (int i = 0; i <= N1 - M1; i++) {
                final int ii = i;
                async(() -> {
                    int j;
                    for (j = 0; j < M1; j++) {
                        doWork(1); // Count each char comparison as 1 unit of work
                        if (text[ii + j] != pattern[j]) {
                            break;
                        }
                    }
                    if (j == M1) {
                        found1[0] = true;
                    }
                });
            }
        });
        if(found1[0]==true) {
            System.out.println("Pattern found by parallel algorithm: " + found1[0]);
        }
       final HjMetrics actualMetrics = abstractMetrics();
        // HjMetrics actualMetrics;
        AbstractMetricsManager.dumpStatistics(actualMetrics);
    }
}
