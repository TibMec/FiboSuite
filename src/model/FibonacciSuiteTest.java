package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciSuiteTest {

    @Test
    void loopCalculTest() {
        assertEquals(0, FibonacciSuite.loopCalcul(0));
        assertEquals(2, FibonacciSuite.loopCalcul(1));
        assertEquals(13, FibonacciSuite.loopCalcul(5));
        assertEquals(2971215073L, FibonacciSuite.loopCalcul(45));
        assertEquals(7540113804746346429L, FibonacciSuite.loopCalcul(90));
        assertEquals(-6246583658587674878L, FibonacciSuite.loopCalcul(91));
    }

    @Test
    void recurCalculTest() {
        assertEquals(1, FibonacciSuite.recurCalcul(0));
        assertEquals(2, FibonacciSuite.recurCalcul(1));
        assertEquals(13, FibonacciSuite.recurCalcul(5));
        assertEquals(2971215073L, FibonacciSuite.recurCalcul(45)); //18s405...
//        assertEquals(7540113804746346429L, FibonacciSuite.recurCalcul(90));
//        assertEquals(-6246583658587674878L, FibonacciSuite.recurCalcul(91)); plus long que 9min27...
    }
}