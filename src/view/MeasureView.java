package view;

import control.FibonacciService;

import java.util.List;

/**
 * Cette classe présente les résultats obtenus par la classe TimePerfMeasurer
 */
public class MeasureView {
    private FibonacciService fibService;

    public MeasureView() {
        this.fibService = new FibonacciService();
    }

    /**
     * Traduit une durée à l'échelle de mesure appropriée
     * @param durationNs = temps d'exécution d'une méthode
     * @param methodName = nom de la méthode mesurée
     * @return un message précisant  la durée et l'échelle de mesure de la méthode
     */
    public String perfView(long durationNs,String methodName){
        String perfMessage = "";

        if(durationNs < 1000){
            perfMessage = String.format("%s a duré %d ns",
                    methodName, durationNs);
        } else if (durationNs < 1000000){
            perfMessage = String.format("%s a duré %.2f μs",
                    methodName, durationNs/1000.0);
        } else if (durationNs < 1000000000){
            perfMessage = String.format("%s a duré %.2f ms",
                    methodName, durationNs/1000000.0);
        }
        return perfMessage;
    }

    /**
     * Affiche l'historique des comparaisons de mesures effectuées s'il y en a
     */
    public void showHistory() {
        List<String> hist = fibService.getHistory();
        if (hist.isEmpty())
            System.out.println("Liste vide. Commencez à comparer des vitesses d'exécution pour remplir la liste ! (4)");
        else
            hist.forEach(ligne -> System.out.println(ligne));
    }
}
