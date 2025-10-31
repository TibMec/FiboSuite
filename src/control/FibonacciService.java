package control;

import model.FibonacciSuite;
import model.TimePerfMeasurer;
import view.MeasureView;
import view.UserView;

import java.util.*;

/**
 * Classe qui fait le lien entre la vue et le modèle
 */
public class FibonacciService {
    private FibonacciSuite fibo;
    private TimePerfMeasurer perf;
    private static final List<String> HISTORY = new ArrayList<>();
    private UserView view;
    private MeasureView measureView;

    public FibonacciService() {
    }

    public FibonacciService(UserView view) {
        this.fibo = new FibonacciSuite();
        this.perf = new TimePerfMeasurer();
        this.measureView = new MeasureView();
        this.view = view;
    }

    public List<String> getHistory() {
        return HISTORY;
    }

    /**
     * Choisit la fonction loopCalcul appropriée et donne la liste obtenue à la vue pour affichage
     * @param iterations = vise un terme précis de la suite
     * @param stepByStep = true si methode loopCalculStepByStep, false si loopCalcul
     * @return le résultat de la methode loopCalculStepByStep ou loopCalcul
     */
    public long showLoopCalcul(int iterations, boolean stepByStep) {
        long result =  stepByStep
                ? fibo.loopCalculStepByStep(iterations)
                : fibo.loopCalcul(iterations);
        view.showCollection(fibo.getSuite());
        return result;
    }

    /**
     * Vide le set pour y placer les resultats de recurCalcul et donne le set obtenu à la vue pour affichage
     * @param iterations = vise un terme précis de la suite
     * @return le résultat de la methode recurCalcul
     */
    public long showRecurCalcul(int iterations) {
        fibo.clearSet();
        long result = fibo.recurCalcul(iterations);
        view.showCollection(fibo.getSet());
        return result;
    }

    /**
     * Retourne un message comparant les mesures de durées de 2 méthodes et l'ajoute à l'historique
     * @param iterations = vise un terme précis de la suite
     * @return message comparant les mesures de durées de 2 méthodes
     */
    public String comparePerformances(int iterations) {
        long duration1 = perf.methodDuration(() -> fibo.loopCalcul(iterations));
        long duration2 = perf.methodDuration(() -> fibo.recurCalcul(iterations));

        String slower = perf.isSlower(duration1, duration2) ?
                "recurCalcul a été plus rapide"
                : "loopCalcul a été plus rapide";

        String resLoop = measureView.perfView(duration1,"loopCalcul");
        String resRecur = measureView.perfView(duration2,"recurCalcul");
        String comparaison = String.format("\nPour %d itérations, \n\t%s \n\t%s \n\t%s",iterations,resLoop,resRecur,slower);

        HISTORY.add(comparaison);
        return comparaison;
    }
}
