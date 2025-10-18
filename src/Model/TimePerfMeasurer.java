package Model;

import java.time.Duration;
import java.time.Instant;

public class TimePerfMeasurer {

    public TimePerfMeasurer(){}

    public static String methodPerf(Runnable method, String methodName){
        String result = "";

        long start = System.nanoTime();
        method.run();
        long end = System.nanoTime();
        long durationNs = end - start;

        if(durationNs < 1000){
            result = String.format("%s a duré %d ns",
                    methodName, durationNs);
        } else if (durationNs < 1000000){
            result = String.format("%s a duré %.2f μs",
                    methodName, durationNs/1000.0);
        } else if (durationNs < 1000000000){
            result = String.format("%s a duré %.2f ms",
                    methodName, durationNs/1000000.0);
        }
        return result;
    }

}

