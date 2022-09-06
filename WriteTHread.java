package TCP_JAN1_2021;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteTHread extends Thread {

    private BufferedWriter toServer;


    public WriteTHread(Socket socket) {
        try {
            this.toServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.err.println("Error geting output stream: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Unesite vase ime: ");
        try(Scanner sc = new Scanner(System.in)){
            String name = sc.nextLine();
            this.toServer.write(name);
            this.toServer.newLine();
            this.toServer.flush();

            while(true) {
                System.out.println("\rUnesite potez: ");
                String potez = sc.nextLine();
                this.toServer.write(potez);
                this.toServer.newLine();
                this.toServer.flush();
                System.out.println("Potez poslat serveru...");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
