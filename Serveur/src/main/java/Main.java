import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        Socket clientSocket;
        serverSocket = new ServerSocket(8080);

        while (true){
            clientSocket = serverSocket.accept();

            Command command = new Command(clientSocket);
            Thread thread = new Thread(command);

            thread.start();
        }
    }
}
