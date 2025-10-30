package view;

public class MeasureView {
    /**
     * Traduit une durée à l'échelle de mesure appropriée
     * @param durationNs = temps d'exécution d'une méthode
     * @param methodName = nom de la méthode mesurée
     * @return un message précisant  la durée et l'échelle de mesure de la méthode
     */
    public static String perfView(long durationNs,String methodName){
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
}
