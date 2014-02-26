/* Copyright (c) 2014 Red Hat, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.freevariable.lancer.stat;

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
