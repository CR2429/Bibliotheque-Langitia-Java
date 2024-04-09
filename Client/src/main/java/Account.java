import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Account {
    //propriete
    private String email;
    private String password;

    //constructeur
    public Account() {}
    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //get-set
    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }

    //method
    /**
     * Cherche à savoir si l'email est unique ou pas.
     * C'est le serveur qui doit envoyer la reponse.
     * */
    public boolean ValidationEmail(String email) {
        try {
            //Connexion
            Socket socket = new Socket("localhost", 8080);

            //recuperer les flux
            PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Execute de la commande
            socketOut.println("VALIDE_MAIL");

            //Envoie du mail
            socketOut.println(email);

            //reception de la reponse
            String reponse = socketIn.readLine();
            boolean real_reponse;
            if (reponse.equals("True")) { real_reponse = true; }
            else { real_reponse = false; }

            //fermeture de la connexion
            socket.close();

            //return
            return real_reponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Methode pour envoyer sur le serveur le compte a enregistrer
     * */
    public void Register() {

    }
}
