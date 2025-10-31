import view.UserView;

/**
 * Point d'entrée de l'Application
 */
public class AppView {
    public static void main(String[] args) {
        UserView userView =  new UserView();
        userView.start();
    }
}