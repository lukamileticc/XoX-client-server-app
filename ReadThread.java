package TCP_JAN1_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread {

    private BufferedReader fromServer;
    public ReadThread(Socket socket) {
        try {
            this.fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error geting input stream: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {
            //treba da primamo poruke od servera
            while (true) {

                String response = this.fromServer.readLine();
                //ako je veza zatvorena
                if (response == null) {
                    System.err.println("\rConnection lost.");
                    break;
                }

                System.out.println(response);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
