import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Command implements Runnable {
    private Socket clientSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;
    final Object o1 = new Object(); //verrou fichier compte


    public Command(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;

        System.out.printf("DEBUG: Connexion du client: %s\n", this.clientSocket.getInetAddress().getHostAddress());

        this.socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
        this.socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }


    public void Main() throws IOException {
        //recuperer la commande
        String command = socketIn.readLine();
        System.out.printf("DEBUG: Commande %s\n", command);

        //objet reutiliser
        Gson gson = new Gson();

        switch (command) {
            case "VALIDE_MAIL": //Commande pouir savoir si un email est sur le serveur ou pas. Envoie true si l'email est absent et valide
                synchronized (o1) {
                    String email_to_valid = socketIn.readLine();
                    boolean mail_valide = true;

                    //recupere la liste
                    Account[] accounts_list;
                    BufferedReader br = new BufferedReader(new FileReader("compte.json"));
                    accounts_list = gson.fromJson(br, Account[].class);

                    //test la liste
                    for (Account account : accounts_list) {
                        if (Objects.equals(account.getEmail(), email_to_valid)) {
                            mail_valide = false; //email existant
                            break;
                        }
                    }

                    //test si le mail est un vrai mail
                    if (!email_to_valid.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { mail_valide = false; }

                    //envoie de la reponse
                    if (mail_valide) { socketOut.println("True"); }
                    else { socketOut.println("False"); }
                }
                break;
            default:
                System.out.println("INFO: La commande entre n'est pas valide");
        }
    }


    @Override
    public void run() {
        try {
            this.Main();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
