/* Copyright (c) 2014 Red Hat, Inc.
 * Copyright (c) 2003 William C. Benton
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

package com.freevariable.lancer.random;

public class Poisson {
    private double lambda;
    private final double z;
    private RandomEngine r;

    private Poisson() {
        this(1.0, System.currentTimeMillis());
    }

    public Poisson(double lambda, long seed) {
        this(lambda, new RandomEngine(seed));
    }

    public Poisson(double lambda, RandomEngine re) {
        r = re;
        this.lambda = lambda;
        z = Math.exp(-lambda);
    }

    /** 
     * Returns the next value the Poisson distribution; 
     * technique is due to Algorithm 369, CACM, January 1970
     */
    public int nextInt() {
        double t;
        int k;
        
        k = 0;
        t = 1.0;
        
        for (t*=r.nextDouble(); t > z; t*=r.nextDouble()) k++;
        
        return k;        
    }

    public double nextDouble() {
        return (double)nextInt();
    }
    
    public double expectedMean() {
        return lambda;
    }

    public double expectedVariance() {
        return lambda;
    }
}