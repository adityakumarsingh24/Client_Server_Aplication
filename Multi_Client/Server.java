import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    static Vector<ClientHandler> clients = new Vector<>();

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(6000);

            System.out.println("Server Started...");
            System.out.println("Waiting for Clients...");

            while (true) {

                Socket socket = serverSocket.accept();

                ClientHandler client = new ClientHandler(socket);

                clients.add(client);

                client.start();

                System.out.println("Client Connected");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {

    Socket socket;
    BufferedReader in;
    PrintWriter out;

    ClientHandler(Socket socket) {

        this.socket = socket;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {

        try {

            String message;

            while ((message = in.readLine()) != null) {

                System.out.println(message);

                for (ClientHandler c : Server.clients) {
                    c.out.println(message);
                }

                if (message.equalsIgnoreCase("exit"))
                    break;
            }

            Server.clients.remove(this);

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}