package com.freevariable.lancer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.freevariable.lancer.util.SampleRecorder;
import com.freevariable.lancer.random.Poisson;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    public final static int ITERATIONS = 100000;
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testPoissonMean() {
        SampleRecorder sr = new SampleRecorder();
        Poisson p = new Poisson(7.0, System.currentTimeMillis());
        
        for (int i = 0; i < ITERATIONS; i++) {
            sr.record(p.nextDouble());
        }
        
        assertEquals(p.expectedMean(), sr.meanEstimate(), 0.01);
    }

    public void testPoissonVariance() {
        SampleRecorder sr = new SampleRecorder();
        Poisson p = new Poisson(7.0, System.currentTimeMillis());
        
        for (int i = 0; i < ITERATIONS; i++) {
            sr.record(p.nextDouble());
        }
        
        assertEquals(p.expectedVariance(), sr.varianceEstimate(), 0.1);
    }

    public void testPoissonRepeatable() {
        Poisson p1 = new Poisson(7.0, 12345l);
        Poisson p2 = new Poisson(7.0, 12345l);
        
        for (int i = 0; i < ITERATIONS; i++) {
            assertEquals(p1.nextInt(), p2.nextInt());
        }
    }
}
