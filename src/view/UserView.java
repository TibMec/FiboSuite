package view;

import control.FibonacciService;

import java.util.*;

/**
 * Classe qui présente l'application, oriente l'utilisateur et applique les choix de celui-ci
 */
public class UserView {
    private MeasureView measureView = new MeasureView();
    private FibonacciService fibService;
    private Scanner sc = new Scanner(System.in);

    public UserView() {
        this.fibService = new FibonacciService(this);
    }

    /**
     * Affiche et applique les options saisies par l'utilisateur
     */
    public void start() {
        System.out.println("\nCe programme vous permet d'afficher la suite de Fibonacci à l'étape de votre choix. On part du principe que les deux premiers nombres sont 1 et 1.");
        while (true) {
            try {
                System.out.println("""
                        ______________________________________________
                        | Choisissez une option :                    |
                        |   1. Calcul par boucle (détaillé)          |
                        |   2. Calcul par boucle (simple)            |
                        |   3. Calcul par récursion                  |
                        |   4. Comparer les performances             |
                        |   5. Voir l’historique                     |
                        |   0. Quitter                               |
                        \\===========================================/
                        """);

                int choix = sc.nextInt();

                // Choix indépendants des itérations
                if (choix == 5) {
                    System.out.println("""
                             Historique des mesures
                            *********************************************""");
                    measureView.showHistory();
                    System.out.println("*********************************************");
                    continue;
                }

                if (choix == 0) {
                    System.out.println("Ciao !");
                    return;
                }

                // Choix dépendants des itérations
                System.out.print("Saisir le nombre d’itérations : ");
                int iterations = sc.nextInt();

                switch (choix) {
                    case 1 -> System.out.println(fibService.showLoopCalcul(iterations, true));
                    case 2 -> System.out.println(fibService.showLoopCalcul(iterations, false));
                    case 3 -> System.out.println(fibService.showRecurCalcul(iterations));
                    case 4 -> System.out.println(fibService.comparePerformances(iterations));
                    default -> System.out.println("Tapez seulement un chiffre présenté dans les options !");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : veuillez saisir un entier.");
                sc.nextLine();
            }
        }
    }

    /**
     * Présente les résultats des calculs de suite
     * @param collection = la List obtenue par loopCalcul ou le Set obtenu par recurCalcul
     */
    public void showCollection(Collection<Long> collection) {
        System.out.println("Suite obtenue : " + collection);
    }
}
