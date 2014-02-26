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

import java.util.Random;

/** Reimplementation of the colt RandomEngine interface atop java.util.Random */

public class RandomEngine {
    private Random r;
    
    public RandomEngine(long seed) {
        r = new Random(seed);
    }

    public RandomEngine() {
        r = new Random(1);
    }
    
    public double apply(double _ignored) {
        return nextDouble();
    }
    
    public int apply(int _ignored) {
        return nextInt();
    }
    
    public static RandomEngine makeDefault() {
        return new RandomEngine(System.currentTimeMillis());
    }
    
    public double nextDouble() {
        return r.nextDouble();
    }
    
    public float nextFloat() {
        return r.nextFloat();
    }
    
    public int nextInt() {
        return r.nextInt();
    }
    
    public long nextLong() {
        return r.nextLong();
    }
    
    public double raw() {
        return nextFloat();
    }
}