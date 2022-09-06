package TCP_JAN1_2021;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class XoXServer {

    public static final int PORT = 12345;

    public static void main(String[] args) {
        XoXServer server = new XoXServer(PORT);
        server.execute();
    }

    private final Set<UserThread> users;
    private final int port;

    public XoXServer(int port){
        this.port = port;
        this.users = Collections.synchronizedSet(new HashSet<>());
    }

    private void execute() {

        Matrix matrix = new Matrix("---------");
        int sign = -1;

        System.err.println("Server starting...");

        try(ServerSocket server = new ServerSocket(PORT);) {
            System.err.println("Server bound to the port:" + PORT);

            while(true){
                System.err.println("Listening for clients...");
                Socket client = server.accept();
                sign *= -1;

                System.err.println("Client accepted! Dispatching thread...");
                UserThread t = new UserThread(client,matrix,sign,this);
                t.start();
                users.add(t);
                //za zatvaranje soketa su zaduzeni threadovi
            }

        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }

    }


    void send_do_another_client(UserThread userThread,String msg){
        synchronized (this.users){
            for(UserThread us : users) {
                if (us != userThread)
                    us.sendMessage(msg);
            }
        }

    }
}
