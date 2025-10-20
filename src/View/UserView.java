package View;

import Model.FibonacciSuite;
import Model.TimePerfMeasurer;

import java.util.*;

import static java.lang.System.exit;

public class UserView {
    private static List<String> historiquePerformance= new ArrayList<>();
    static long duration = 0;

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

    /**
     * Traduit une comparaison de durée d'exécution en message
     * @param durCompare = booléen comparant 2 vitesses d'exécution
     * @param d1Name = nom de la méthode associée à la durée 1
     * @param d2Name = nom de la méthode associée à la durée 2
     * @return un message montrant quelle méthode a été la plus rapide à s'exécuter
     */
    public static String comparisonView(boolean durCompare, String d1Name, String d2Name){
        String result;
        if(durCompare)
            result = String.format("%s a été plus rapide que %s", d2Name, d1Name);
        else
            result = String.format("%s a été plus rapide que %s", d1Name, d2Name);
        return result;
    }

    /**
     * Interface utilisateur avec un menu présentant les options
     */
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
                    System.out.println("______________________________________________");
                    System.out.println("| Veuillez choisir une option:                |\\");
                    System.out.println("| \t1. Calcul par boucle, étapes détaillées   ||");
                    System.out.println("| \t2. Calcul par boucle                      ||");
                    System.out.println("| \t3. Calcul par récursion                   ||");
                    System.out.println("| \t4. Comparer performance des méthodes      ||");
                    System.out.println("| \t5. Consulter historique des comparaisons  ||");
                    System.out.println("| \t0. Quitter                                ||");
                    System.out.println(" \\============================================\\|");
                    choix = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Erreur de saisie: il faut taper un entier. "+ e);
                    sc.nextLine();
                    iterations = 1;
                }
                switch(choix){
                    case 1:
                        // la methode runnee par methodDuration doit prendre un parametre final pour etre runnable
                        final int n = iterations;
                         duration = TimePerfMeasurer.methodDuration(()-> {
                            FibonacciSuite.loopCalculStepByStep(n);
                            });
                        //Affiche un message exprimant la durée de la méthode
                        System.out.println(perfView(duration, "loopCalculStepByStep"));

                        //Récupère la suite, puis l'affiche avec le dernier résultat
                        List<Long> suite = FibonacciSuite.getSuite();
                        long result = suite.get(suite.size() - 1);
                        System.out.println(String.format(Locale.US,"La suite après %d iterations donne %,d: %s",
                                iterations,
                                result,
                                suite));
                        break;
                    case 2:
                        // la methode runnee par methodDuration doit prendre un parametre final pour etre runnable
                        final int n2 = iterations;
                         duration = TimePerfMeasurer.methodDuration(()-> {
                            FibonacciSuite.loopCalcul(n2);
                        });
                        //Affiche un message exprimant la durée de la méthode
                        System.out.println(perfView(duration, "loopCalcul"));

                        //Récupère la suite, puis l'affiche avec le dernier résultat
                        List<Long> suite2 = FibonacciSuite.getSuite();
                        long result2 = suite2.get(suite2.size() - 1);
                        System.out.println(String.format(Locale.US,"La suite après %d iterations donne %,d: %s",
                                iterations,
                                result2,
                                FibonacciSuite.getSuite()));
                        break;
                    case 3:
                        // la methode runnee par methodDuration doit prendre un parametre final pour etre runnable
                        final int n3 = iterations;
                        FibonacciSuite.getSet().clear();
                        duration = TimePerfMeasurer.methodDuration(()-> {
                            FibonacciSuite.recurCalcul(n3);
                        });
                        //Affiche un message exprimant la durée de la méthode
                        System.out.println(perfView(duration, "recurCalcul"));

                        //Récupère la suite et l'affiche avec le résultat
                        long result3 = FibonacciSuite.recurCalcul(iterations);
                        System.out.println(String.format(Locale.US,"La suite après %d iterations donne %,d: %s",
                                iterations, result3, FibonacciSuite.getSet()));
                        break;
                    case 4:
                        // la methode runnee par methodDuration doit prendre un parametre final pour etre runnable
                        final int n4 = iterations;

                        // Les mesures à comparer
                        long d1, d2;

                        //Enregistre la mesure pour la comparer avec d2 plus tard
                        d1 = TimePerfMeasurer.methodDuration(()-> {
                            FibonacciSuite.loopCalcul(n4);
                        });

                        //Prepare le message a afficher pour la duree de loopCalcul
                        String resLoop = perfView(d1, "loopCalcul");

                        d2 = TimePerfMeasurer.methodDuration(()-> {
                            FibonacciSuite.recurCalcul(n4);
                        });

                        //Prepare le message a afficher pour la duree de recurCalcul
                        String resRecur = perfView(d2, "recurCalcul");

                        //donne true si d1 plus lent que d2
                        boolean durCompare = TimePerfMeasurer.perfComparison(d1,d2);

                        //Message de comparaison des durees
                        String perfComp = comparisonView(durCompare,"loopCalcul","recurCalcul");

                        //Message final
                        String comparaison = String.format("\nPour %d itérations, \n\t%s \n\t%s \n\t%s",n4,resLoop,resRecur,perfComp);

                        //Enregistre la comparaison pour l'historique de mesure
                        historiquePerformance.add(comparaison);
                        System.out.println(comparaison);
                        break;
                    case 5:
                        //Affiche l'historique si presence d'au moins 1 resultat
                        if (historiquePerformance.size()!=0)
                            System.out.println(historiquePerformance);
                        else
                            System.out.println("Liste vide. Commencez à comparer des vitesses d'exécution pour remplir la liste !");
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
