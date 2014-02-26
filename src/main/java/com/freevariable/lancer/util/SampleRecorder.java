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
    
    public void record(double sample) {
        sd_dirty = true;
        
        count++;
        updateEstimates(sample);
        
        if (sample>max) max = sample;
        if (sample<min) min = sample;
    }
    
    public long count() { return count; }
    public double meanEstimate() { return meanEstimate; }
    public double varianceEstimate() { return varianceEstimate; }
    public double stdDevEstimate() { 
        if (sd_dirty) {
            stdDevEstimate = Math.sqrt(varianceEstimate);
            sd_dirty = false;
        }
        return stdDevEstimate; 
    }
    public double max() { return max; }
    public double min() { return min; }
}