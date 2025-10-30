package control;

import model.FibonacciSuite;
import model.TimePerfMeasurer;

import java.util.*;

public class FibonacciService {
    private final FibonacciSuite fibo;
    private final TimePerfMeasurer perf;
    private final List<String> historique = new ArrayList<>();

    public FibonacciService() {
        this.fibo = new FibonacciSuite();
        this.perf = new TimePerfMeasurer();
    }

    public List<String> getHistorique() {
        return historique;
    }

    public long executeLoopCalcul(int n, boolean stepByStep) {
        return stepByStep
                ? fibo.loopCalculStepByStep(n)
                : fibo.loopCalcul(n);
    }

    public long executeRecurCalcul(int n) {
        fibo.clearSet();
        return fibo.recurCalcul(n);
    }

    public long loopPerf(int n) {
        return perf.methodDuration(() -> fibo.loopCalcul(n));
    }

    public long recurPerf(int n) {
        return perf.methodDuration(() -> fibo.recurCalcul(n));
    }

    public void comparePerformances(int n) {
        long duration1 = loopPerf(n);
        long duration2 = recurPerf(n);
        boolean slower = perf.isSlower(duration1, duration2);

        String comparaison = String.format(
                Locale.US,
                "\nPour %d itérations :\n\tloopCalcul = %.2f ms\n\trecurCalcul = %.2f ms\n\t%s",
                n, duration1 / 1_000_000.0, duration2 / 1_000_000.0,
                slower ? "recurCalcul plus rapide" : "loopCalcul plus rapide"
        );

        historique.add(comparaison);
        System.out.println(comparaison);
    }
}
