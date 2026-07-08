import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server Started...");
            System.out.println("Waiting for Client...");

            Socket socket = serverSocket.accept();

            System.out.println("Client Connected!");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            BufferedReader keyboard = new BufferedReader(
                    new InputStreamReader(System.in));

            while (true) {

                String clientMsg = in.readLine();

                if (clientMsg == null || clientMsg.equalsIgnoreCase("exit"))
                    break;

                System.out.println("Client : " + clientMsg);

                System.out.print("Server : ");
                String reply = keyboard.readLine();

                out.println(reply);

                if (reply.equalsIgnoreCase("exit"))
                    break;
            }

            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}