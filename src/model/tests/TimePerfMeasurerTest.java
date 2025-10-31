package model.tests;

import model.TimePerfMeasurer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimePerfMeasurerTest {
    TimePerfMeasurer measurer = new TimePerfMeasurer();
    @Test
    void methodDurationTest() {
//        assertEquals();
    }

    @Test
    void perfComparisonTest() {
        assertTrue(measurer.isSlower(5,2));
        assertFalse(measurer.isSlower(2,5));
    }
}