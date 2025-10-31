package model.tests;

import model.TimePerfMeasurer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Classe pour tester les méthodes de TimePerfMeasurer
 */
class TimePerfMeasurerTest {
    TimePerfMeasurer measurer = new TimePerfMeasurer();

    /**
     * Check la durée d'exécution d'une méthode simple, qui est normalement supérieure à 3000 nS
     */
    @Test
    void methodDurationTest() {
        long duration = measurer.methodDuration(() -> {
            int counter = 0;
            for (int i = 0; i < 10; i++) {
                counter++;
            }
        });
        System.out.println(duration);
        assertTrue(duration > 3000);
    }

    /**
     * Teste si la comparaison de durée retourne bien les bons booléens
     */
    @Test
    void perfComparisonTest() {
        assertTrue(measurer.isSlower(5, 2));
        assertFalse(measurer.isSlower(2, 5));
    }

}