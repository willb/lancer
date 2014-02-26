package com.freevariable.lancer;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

public class Probability {
    protected Probability() {}
    
    public static double studentT(double k, double t) {
        // XXX: sure would be smarter to memoize these
        return (new TDistribution(k)).cumulativeProbability(t);
    }

    public static double studentTInverse(double alpha, int size) {
        double t = 1 - (alpha / 2);
        // XXX: sure would be smarter to memoize these
        return (new TDistribution((double)size)).cumulativeProbability(t);
    }
    
    public static double normal(double a) {
        return normal(0,1,a);
    }
    
    public static double normal(double mean, double variance, double a) {
        // XXX: sure would be smarter to memoize these
        return (new NormalDistribution(mean,java.Math.sqrt(variance))).cumulativeProbability(a);
    }
    
    public static double normalInverse(double a) {
        return normalInverse(0,1,a);
    }

    public static double normalInverse(double mean, double variance, double a) {
        // XXX: sure would be smarter to memoize these
        return (new NormalDistribution(mean,java.Math.sqrt(variance))).inverseCumulativeProbability(a);
    }
}
