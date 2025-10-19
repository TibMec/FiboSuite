package View;

import Model.FibonacciSuite;
import Model.TimePerfMeasurer;

import java.util.*;

import static java.lang.System.exit;

public class UserView {
    private static Set<String> historiquePerformance= new TreeSet<>();
    static long duration = 0;
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

    public static String comparisonView(boolean durCompare, String d1Name, String d2Name){
        String result;
        if(durCompare)
            result = String.format("%s a été plus rapide que %s", d2Name, d1Name);
        else
            result = String.format("%s a été plus rapide que %s", d1Name, d2Name);
        return result;
    }

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
                         duration = TimePerfMeasurer.methodDuration(()-> {
                            FibonacciSuite.loopCalculStepByStep(n);
                            });
                        System.out.println(perfView(duration, "loopCalculStepByStep"));
                        List<Long> suite = FibonacciSuite.getSuite();
                        long result = suite.get(suite.size() - 1);
                        System.out.println(String.format(Locale.US,"La suite après %d iterations donne %,d: %s",
                                iterations,
                                result,
                                FibonacciSuite.getSuite()));
                        break;
                    case 2:
                        final int n2 = iterations;
                         duration = TimePerfMeasurer.methodDuration(()-> {
                            FibonacciSuite.loopCalcul(n2);
                        });
                        System.out.println(perfView(duration, "loopCalcul"));
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
                        duration = TimePerfMeasurer.methodDuration(()-> {
                            FibonacciSuite.recurCalcul(n3);
                        });
                        System.out.println(perfView(duration, "recurCalcul"));
                        long result3 = FibonacciSuite.recurCalcul(iterations);
                        System.out.println(String.format(Locale.US,"La suite après %d iterations donne %,d: %s",
                                iterations, result3, FibonacciSuite.getSet()));
                        break;
                    case 4:
                        final int n4 = iterations;
                        long d1, d2;
                        duration = TimePerfMeasurer.methodDuration(()-> {
                            FibonacciSuite.loopCalcul(n4);
                        });
                        d1 = duration;
                        String resLoop = perfView(duration, "loopCalcul");

                        duration = TimePerfMeasurer.methodDuration(()-> {
                            FibonacciSuite.recurCalcul(n4);
                        });
                        d2 =duration;
                        String resRecur = perfView(duration, "recurCalcul");

                        boolean durCompare = TimePerfMeasurer.perfComparison(d1,d2);
                        String perfComp = comparisonView(durCompare,"loopCalcul","recurCalcul");
                        String comparaison = String.format("\nPour %d itérations, \n\t%s \n\t%s \n\t%s",n4,resLoop,resRecur,perfComp);
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
