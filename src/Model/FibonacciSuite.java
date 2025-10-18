package Control;

public class FibonacciSuiteService {
    public static int loopCalcul(int iterations){
        int nombre1 = 1;
        int nombre2 = 1;
        int totalSuite = 0;
        for (int i = 0; i < iterations; i++) {
            totalSuite = nombre1 + nombre2;
            System.out.println(String.format("Iteration %d : \n\tnombre precedent (n - 2): %d \n\tnombre precedent (n - 1): %d \n\ttotal: %d  ", i +1, nombre1, nombre2, totalSuite));
            nombre1 = nombre2;
            nombre2 = totalSuite;
        }
        System.out.println(String.format("La suite après %d iterations donne %d", iterations, totalSuite));
        return totalSuite;
    }

    public static int recurCalcul(int iterations){
        int nombre1, nombre2, totalSuite = 0;
        if (iterations == 0) {
            return 1;
        }
        else if (iterations == 1) {
            return 1;
        }
        else {
            nombre1 = recurCalcul(iterations - 2);
            nombre2 = recurCalcul(iterations - 1);
            totalSuite = nombre1 + nombre2;
//            System.out.println(String.format("Iteration %d : \n\ttotal: %d  ", iterations, totalSuite));
//            System.out.println(String.format("La suite après %d iterations donne %d", iterations, totalSuite));
            return totalSuite;
        }

    }



}
