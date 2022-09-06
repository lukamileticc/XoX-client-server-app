package TCP_JAN1_2021;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class XoXClient {



    public static void main(String[] args) {
        XoXClient client = new XoXClient("localhost",XoXServer.PORT);
        System.err.println("Connecting to local port: " + XoXServer.PORT);
        client.execute();
    }

    private final int port;
    private final String hostname;


    public XoXClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    private void execute() {

        try(Socket socket = new Socket(this.hostname,this.port);){
            //otvaramo threadove
            Thread rt = new ReadThread(socket);
            rt.start();
            Thread wt = new WriteTHread(socket);
            wt.start();

            //cekamo da tredovi zavrse da bismo mogli da zatvorimo soket

            rt.join();
            wt.join();
        } catch (UnknownHostException e) {
            System.err.println("Error resolvnig hostname: " + this.hostname);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
