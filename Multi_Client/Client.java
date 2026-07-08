import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("localhost", 6000);

            BufferedReader in =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter out =
                    new PrintWriter(socket.getOutputStream(), true);

            BufferedReader keyboard =
                    new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter Name: ");
            String name = keyboard.readLine();

            Thread receive = new Thread(() -> {

                try {

                    String msg;

                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                    }

                } catch (Exception e) {
                }

            });

            receive.start();

            while (true) {

                String message = keyboard.readLine();

                out.println(name + " : " + message);

                if (message.equalsIgnoreCase("exit"))
                    break;
            }

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}