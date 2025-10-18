package View;

import Model.FibonacciSuite;
import Model.TimePerfMeasurer;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static java.lang.System.exit;

public class UserView {
    private static Set<String> historiquePerformance= new TreeSet<>();
    public static void userPrompt(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nCe programme vous permet d'afficher la suite de Fibonacci à l'étape de votre choix. On part du principe que les deux premiers nombres sont 1 et 1.");
            while(true){
                int iterations = 0;
                int choix = 0;
                try {
                    System.out.println("Saisir le nombre d'itérations à afficher (0 pour quitter): ");
                    iterations = sc.nextInt();
                    if(iterations == 0){
                        System.out.println("Bye !");
                        exit(0);
                    }
                    System.out.println("--------------------------------------");
                    System.out.println("Veuillez choisir une option:");
                    System.out.println("\t1. Calcul par boucle, étapes détaillées");
                    System.out.println("\t2. Calcul par boucle");
                    System.out.println("\t3. Calcul par récursion");
                    System.out.println("\t4. Comparer performance des méthodes");
                    System.out.println("\t5. Consulter historique des comparaisons");
                    System.out.println("\t0. Quitter");
                    choix = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Erreur de saisie: il faut taper un entier. "+ e);
                    sc.nextLine();
                    iterations = 1;
                }
                switch(choix){
                    case 1:
                        final int n = iterations;
                        System.out.println(TimePerfMeasurer.methodPerf(()-> {
                            FibonacciSuite.loopCalculStepByStep(n);
                            }, "loopCalculStepByStep"));
                        List<Long> suite = FibonacciSuite.getSuite();
                        long result = suite.get(suite.size() - 1);
                        System.out.println(String.format(Locale.US,"La suite après %d iterations donne %,d: %s",
                                iterations,
                                result,
                                FibonacciSuite.getSuite()));
                        break;
                    case 2:
                        final int n2 = iterations;
                        System.out.println(TimePerfMeasurer.methodPerf(()-> {
                            FibonacciSuite.loopCalcul(n2);
                            }, "loopCalcul"));
                        List<Long> suite2 = FibonacciSuite.getSuite();
                        long result2 = suite2.get(suite2.size() - 1);
                        System.out.println(String.format(Locale.US,"La suite après %d iterations donne %,d: %s",
                                iterations,
                                result2,
                                FibonacciSuite.getSuite()));
                        break;
                    case 3:
                        final int n3 = iterations;
                        FibonacciSuite.getSet().clear();
                        System.out.println(TimePerfMeasurer.methodPerf(()-> {
                            FibonacciSuite.recurCalcul(n3);
                        }, "recurCalcul"));
                        long result3 = FibonacciSuite.recurCalcul(iterations);
                        System.out.println(String.format(Locale.US,"La suite après %d iterations donne %,d: %s",
                                iterations, result3, FibonacciSuite.getSet()));
                        break;
                    case 4:
                        final int n4 = iterations;
                        String resLoop = TimePerfMeasurer.methodPerf(()-> {
                            FibonacciSuite.loopCalcul(n4);
                        }, "loopCalcul");
                        String resRecur = TimePerfMeasurer.methodPerf(()-> {
                            FibonacciSuite.recurCalcul(n4);
                        }, "recurCalcul");
                        String comparaison = String.format("\nPour %d itérations, \n\t%s \n\t%s",n4,resLoop,resRecur);
                        historiquePerformance.add(comparaison);
                        System.out.println(comparaison);
                        break;
                    case 5:
                        System.out.println(historiquePerformance);
                        break;
                    case 0:
                        System.out.println("Ciao !");
                        return;
                    default:
                        System.out.println("Tapez seulement 1, 2 ou 0");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur de saisie, il faut taper un entier: " + e);
        }

    }
}
