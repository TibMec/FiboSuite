package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimePerfMeasurerTest {

    @Test
    void methodDurationTest() {
//        assertEquals();
    }

    @Test
    void perfComparisonTest() {
        assertTrue(TimePerfMeasurer.perfComparison(5,2));
        assertFalse(TimePerfMeasurer.perfComparison(2,5));
    }
}