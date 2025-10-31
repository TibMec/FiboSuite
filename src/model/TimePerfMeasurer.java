package model;

/**
 * Classe servant à mesurer et comparer des durées de méthodes
 */
public class TimePerfMeasurer {

    public TimePerfMeasurer(){}

    /**
     * Mesure la performance d'une méthode en nanoSecondes
     * @param method = la méthode dont la performance de vitesse sera évaluée
     * @return la durée en nanoSecondes de la méthode
     */
    public long methodDuration(Runnable method){
        long start = System.nanoTime();
        method.run();
        long end = System.nanoTime();
        long durationNs = end - start;

        return durationNs;
    }

    /**
     * Compare 2 durées d'exécution de méthodes
     * @param duration1 = durée de la méthode1
     * @param duration2 = durée de la méthode 2
     * @return true si d1 moins rapide que d2, false le cas contraire
     */
    public boolean isSlower(long duration1, long duration2){
        return duration1 > duration2;
    }

}

