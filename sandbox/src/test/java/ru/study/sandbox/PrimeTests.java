package ru.study.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.study.sandbox.Primes.isPrime;
import static ru.study.sandbox.Primes.isPrimeFast;

/**
 * Created by Dreamer on 08.12.2016.
 */
public class PrimeTests {
    @Test
    public void testPrime() {
        Assert.assertTrue(isPrime(Integer.MAX_VALUE));
    }

    @Test
    public void testPrimeFast() {
        Assert.assertTrue(isPrimeFast(Integer.MAX_VALUE));
    }

    @Test(enabled = false)
    public void testPrimeLong() {
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(isPrime(n));
    }

    @Test
    public void testNonPrime() {
        Assert.assertFalse(isPrime(Integer.MAX_VALUE-2));
    }

}
