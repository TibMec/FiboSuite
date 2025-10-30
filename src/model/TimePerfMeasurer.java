package model;

public class TimePerfMeasurer {

    public TimePerfMeasurer(){}

    /**
     * @param method = la méthode dont la performance de vitesse sera évaluée
     * @return la durée en nanoSecondes de la méthode
     */
    public static long methodDuration(Runnable method){
        long start = System.nanoTime();
        method.run();
        long end = System.nanoTime();
        long durationNs = end - start;

        return durationNs;
    }

    /**
     *
     * @param d1 = durée de la méthode1
     * @param d2 = durée de la méthode 2
     * @return true si d1 moins rapide que d2, false le cas contraire
     */
    public static boolean isSlower(long d1, long d2){
        return d1 > d2;
    }

}

