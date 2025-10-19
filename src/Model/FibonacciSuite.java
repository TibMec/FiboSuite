package Model;

import java.util.*;

public class FibonacciSuite {
    /**
     * La suite stocke les termes calculés par boucle. Comme elle est statique,
     * elle est vidée en début d'appel de "loopCalcul"
     */
    private static List<Long> suite = new ArrayList();

    /**
     * Le TreeSet stocke et ordonne les termes calculés par récursion. Il empêche l'apparition
     * de doublons générés par la récursion. La récursion empêche de vider
     * la liste. Le UserView s'occupe de montrer seulement les termes voulus
     */
    private static Set<Long> set = new TreeSet<>();

    //Getter et setters nécessaires

    public static List<Long> getSuite() {
        return suite;
    }

    public static Set<Long> getSet() {
        return set;
    }

    /**
     * Calcule le N-ieme terme de la suite et met à jour la liste de termes
     * @param iterations = vise un terme précis de la suite
     * @return le terme visé, calculé par une boucle for
     * Type = long car au bout de 45 itérations on atteint la limite de int
     */
    public static long loopCalcul(int iterations){
        suite.clear();
        long nombre1 = 1, nombre2 = 1, totalSuite = 0;
        for (int i = 0; i < iterations; i++) {
            totalSuite = nombre1 + nombre2;
            suite.add(totalSuite);
            nombre1 = nombre2;
            nombre2 = totalSuite;
        }
        return totalSuite;
    }

    /**
     * Comme la méthode loopCalcul, avec des détails à chaque itération
     * @param iterations
     * @return le terme visé
     */
    public static long loopCalculStepByStep(int iterations){
        suite.clear();
        long nombre1 = 1, nombre2 = 1, totalSuite = 0;
        for (int i = 0; i < iterations; i++) {
            totalSuite = nombre1 + nombre2;
            suite.add(totalSuite);
            System.out.println(String.format(Locale.US,"Iteration %d : \n\tnombre precedent (n - 2): %,d \n\tnombre precedent (n - 1): %,d \n\ttotal: %,d  ", i +1, nombre1, nombre2, totalSuite));
            nombre1 = nombre2;
            nombre2 = totalSuite;
        }
        return totalSuite;
    }

    /**
     * Calcule le N-ieme terme de la suite et met à jour la liste de termes
     * @param iterations = vise un terme précis de la suite
     * @return Retourne le terme visé, calculé par récursion
     * Type = long car au bout de 45 itérations on atteint la limite de int
     */
    public static long recurCalcul(int iterations){
        long nombre1, nombre2, totalSuite = 0;
        if (iterations == 0)
            return 1;
        else if (iterations == 1)
            return 2;
        else {
            nombre1 = recurCalcul(iterations - 2);
            nombre2 = recurCalcul(iterations - 1);
            totalSuite = nombre1 + nombre2;
            set.add(totalSuite);
            return totalSuite;
        }
    }
}
