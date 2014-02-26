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

package com.freevariable.lancer.util;

/**
    A class to manage aggregate statistics for a stream of double values in constant space.
    Uses the technique from <a href="http://dl.acm.org/citation.cfm?id=359153">"Updating mean and variance estimates: an improved method"</a>, by D. H. D. West (1979).

@author William Benton <willb@acm.org>
@since 0.0.1

*/
public final class SampleRecorder {
    private long count;
    private double meanEstimate;
    private double varianceEstimate;
    private double stdDevEstimate;
    private boolean sd_dirty;
    private double max;
    private double min;
    private double sumX;
    private double sumXSquared;
    
    public SampleRecorder() {
        reset();
    }
    
    private void updateEstimates(double sample) {
        double deviation;

        sumX += sample;
        deviation = sample - meanEstimate;
        meanEstimate = meanEstimate + (deviation / count);
        sumXSquared = sumXSquared + (deviation * (sample - meanEstimate));
        
        varianceEstimate = sumXSquared / count;
    }
    
    public void reset() {
        count = 0;
        meanEstimate = 0;
        varianceEstimate = 0;
        max = Double.MIN_VALUE;
        min = Double.MAX_VALUE;
        sumX = 0;
        sumXSquared = 0;
        sd_dirty = false;
    }
    
    /** Records the given sample. */
    public void record(double sample) {
        sd_dirty = true;
        
        count++;
        updateEstimates(sample);
        
        if (sample>max) max = sample;
        if (sample<min) min = sample;
    }
    
    /** Returns the count of samples recorded so far. */
    public long count() { return count; }
    
    /** Returns the estimated mean of samples recorded so far. */
    public double meanEstimate() { return meanEstimate; }
    
    /** Returns the estimated variance of samples recorded so far. */
    public double varianceEstimate() { return varianceEstimate; }
    
    /** Returns the estimated standard deviation of samples recorded so far; this is computed by need. */
    public double stdDevEstimate() { 
        if (sd_dirty) {
            stdDevEstimate = Math.sqrt(varianceEstimate);
            sd_dirty = false;
        }
        return stdDevEstimate; 
    }
    
    /** Returns the largest sample recorded so far. */
    public double max() { return max; }

    /** Returns the smallest sample recorded so far. */
    public double min() { return min; }
}