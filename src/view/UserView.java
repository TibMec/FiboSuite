package view;

import control.FibonacciService;

import java.util.*;

public class UserView {
    private final Scanner sc = new Scanner(System.in);
    private final FibonacciService fibService;

    public UserView(FibonacciService fibService) {
        this.fibService = fibService;
    }

    public void start() {
        System.out.println("\nProgramme de calcul de la suite de Fibonacci");
        while (true) {
            try {
                System.out.print("Saisir le nombre d’itérations (0 pour quitter) : ");
                int iterations = sc.nextInt();
                if (iterations == 0) {
                    System.out.println("Bye !");
                    return;
                }

                System.out.println("""
                        ______________________________________________
                        | Choisissez une option :                    |
                        |   1. Calcul par boucle (détaillé)          |
                        |   2. Calcul par boucle (simple)            |
                        |   3. Calcul par récursion                  |
                        |   4. Comparer les performances             |
                        |   5. Voir l’historique                     |
                        |   0. Quitter                               |
                        \\============================================/
                        """);

                int choix = sc.nextInt();
                switch (choix) {
                    case 1 -> fibService.executeLoopCalcul(iterations, true);
                    case 2 -> fibService.executeLoopCalcul(iterations, false);
                    case 3 -> fibService.executeRecurCalcul(iterations);
                    case 4 -> fibService.comparePerformances(iterations);
                    case 5 -> viewHistorique();
                    case 0 -> {
                        System.out.println("Ciao !");
                        return;
                    }
                    default -> System.out.println("Option invalide");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : veuillez saisir un entier.");
                sc.nextLine();
            }
        }
    }

    private void viewHistorique() {
        List<String> hist = fibService.getHistorique();
        if (hist.isEmpty())
            System.out.println("Aucune comparaison enregistrée.");
        else
            hist.forEach(System.out::println);
    }
}
