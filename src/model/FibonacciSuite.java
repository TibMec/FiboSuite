package model;

import java.util.*;

/**
 * Classe qui calcule la SUITE de Fibonacci par boucle ou par recursion et stocke les valeurs obtenues.
 */
public class FibonacciSuite {
    /**
     * La suite stocke les termes calculés par boucle.
     * Elle est vidée en début d'appel de "loopCalcul".
     */
    private final List<Long> SUITE = new ArrayList();

    /**
     * Le TreeSet stocke et ordonne les termes calculés par récursion. Il empêche l'apparition
     * de doublons générés par la récursion. La récursion empêche de vider
     * la liste.
     */
    private final Set<Long> SET = new TreeSet<>();

    //Getter et SETters nécessaires
    public List<Long> getSuite() {
        return SUITE;
    }

    public Set<Long> getSet() {
        return SET;
    }

    /**
     * Calcule le N-ieme terme de la suite et met à jour la liste de termes
     * @param iterations = vise un terme précis de la suite
     * @return le terme visé, calculé par une boucle for
     * Type = long car au bout de 45 itérations on atteint la limite de int
     */
    public long loopCalcul(int iterations){
        SUITE.clear();
        long nombre1 = 1, nombre2 = 1, totalSuite = 0;
        for (int i = 0; i < iterations; i++) {
            totalSuite = nombre1 + nombre2;
            SUITE.add(totalSuite);
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
    public long loopCalculStepByStep(int iterations){
        SUITE.clear();
        long nombre1 = 1, nombre2 = 1, totalSuite = 0;
        for (int i = 0; i < iterations; i++) {
            totalSuite = nombre1 + nombre2;
            SUITE.add(totalSuite);
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
    public long recurCalcul(int iterations){
        long nombre1, nombre2, totalSuite = 0;
        if (iterations == 0)
            return 1;
        else if (iterations == 1){
            SET.add(2L);
            return 2;
        }
        else {
            nombre1 = recurCalcul(iterations - 2);
            nombre2 = recurCalcul(iterations - 1);
            totalSuite = nombre1 + nombre2;
            SET.add(totalSuite);
            return totalSuite;
        }
    }

    /**
     * Vide le set à la place de recurCalcul car la récursion empêche le vidage unique du set
     */
    public void clearSet() {
        SET.clear();
    }
}
