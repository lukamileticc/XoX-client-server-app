package TCP_JAN1_2021;

import java.io.*;
import java.net.Socket;

public class UserThread extends Thread {

    private XoXServer server;
    private Socket client;
    private final int sign;
    private Matrix matrix;

    private BufferedReader in;
    private BufferedWriter out;


    UserThread(Socket client, Matrix matrix, int sign, XoXServer xoXServer){
        this.client = client;
        this.matrix = matrix;
        this.sign = sign;
        this.server = xoXServer;
        try{

            this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void run() {

        try(
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))
        ) {
            System.err.println("");
            String name = in.readLine();
            System.out.println("Igrac prikljucen:" + name);


           //saljemo tabelu prvom igracu
            if(sign == 1) {
                out.write(this.matrix.getTable());
                out.newLine();
                out.flush();
            }

            System.out.println("Cekam na whilu");
           while(true) {
               System.out.println("Uso sam u while");
               //citamo potez od igraca
               String hand = in.readLine();
               System.err.println(hand);

               //validiramo potez
               if (is_validate(hand)) {
                   char c = sign == 1 ? 'X' : 'O';
                   this.matrix.setHand(hand,c);

                   //saljes drugom igracu
                   this.server.send_do_another_client(this,this.matrix.getTable());
                   System.out.println(this.matrix.getTable());
               } else {
                   out.write("Nevalidan potez, probajte ponovo!");
                   out.newLine();
                   out.flush();
               }
           }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                this.client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean is_validate(String hand) {
        int positon = Integer.parseInt(hand);
        return this.matrix.getMatrix().charAt(positon) == '-';
    }

    public void sendMessage(String msg){
        try {
            this.out.write(msg);
            this.out.newLine();
            this.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
