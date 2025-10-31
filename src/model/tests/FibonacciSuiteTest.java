package model.tests;

import model.FibonacciSuite;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe pour tester les méthodes de FibonacciSuite
 */
class FibonacciSuiteTest {
    FibonacciSuite suite = new FibonacciSuite();

    /**
     * Teste si la suite retourne bien les résultats attendus
     */
    @Test
    void loopCalculTest() {
        assertEquals(0, suite.loopCalcul(0));
        assertEquals(2, suite.loopCalcul(1));
        assertEquals(13, suite.loopCalcul(5));
        assertEquals(2971215073L, suite.loopCalcul(45));
        assertEquals(7540113804746346429L, suite.loopCalcul(90));
        assertEquals(-6246583658587674878L, suite.loopCalcul(91));
    }

    /**
     * Teste si la suite retourne bien les résultats attendus
     */
    @Test
    void recurCalculTest() {
        assertEquals(1, suite.recurCalcul(0));
        assertEquals(2, suite.recurCalcul(1));
        assertEquals(13, suite.recurCalcul(5));
        assertEquals(2971215073L, suite.recurCalcul(45)); //18s405...
//        assertEquals(7540113804746346429L, suite.recurCalcul(90));
//        assertEquals(-6246583658587674878L, suite.recurCalcul(91)); plus long que 9min27...
    }
}