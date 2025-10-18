import Model.FibonacciSuite;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciSuiteTest {

    @Test
    void loopCalculTest() {
        assertEquals(13, FibonacciSuite.loopCalcul(5));
        assertEquals(2, FibonacciSuite.loopCalcul(1));
    }

    @Test
    void recurCalculTest() {
        assertEquals(8, FibonacciSuite.recurCalcul(5));
        assertEquals(1, FibonacciSuite.recurCalcul(1));
    }
}