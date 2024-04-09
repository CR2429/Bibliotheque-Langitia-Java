import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenue a la bibliotheque de Langitia\n");

        Menu();

        System.out.println("Passer un bonne journée. :)");
    }

    public static void Menu() {
        System.out.println("Veuiller vous connecter a votre compre pour allez dans la bibliotheque.");
        System.out.println("1. Connexion");
        System.out.println("2. Creer un compte");
        System.out.println("3. Fermer l'application");

        //boucle de choix
        int choice = 0;
        while (true) {
            //input
            Scanner scanner = new Scanner(System.in);
            System.out.printf("\nCommande : ");
            String commande = scanner.nextLine();

            //drapeau
            boolean commande_execute = false;

            //connexion
            if(commande.equals("1")) {
                commande_execute = true;
                choice = 1;
            }

            //creer un compte
            if(commande.equals("2")) {
                commande_execute = true;
                choice = 2;
            }

            //fermer l'app
            if(commande.equals("3")) {
                commande_execute = true;
                choice = 3;
            }

            //error
            if(commande_execute) {break;}
            else {
                System.out.println("La commande n'existe pas.");
            }
        }

        //execute la commande
        switch (choice) {
            case 1:
                System.out.println("Fermeture de l'application");
                break;
            case 2:
                System.out.println("Fermeture de l'application");
                break;
            case 3:
                System.out.println("Fermeture de l'application");
                break;
        }
    }
}
