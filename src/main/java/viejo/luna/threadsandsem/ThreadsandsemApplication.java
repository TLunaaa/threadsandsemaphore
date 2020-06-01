package viejo.luna.threadsandsem;

import viejo.luna.threadsandsem.model.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ThreadsandsemApplication {

    public static void main(String[] args) throws IOException {
        final int PORT = 8080;
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("MiniServer active " + PORT);
        while (true)
        {
            Socket s = null;
            try
            {
                // socket object to receive incoming client requests
                s = server.accept();
                System.out.println("A new client is connected : " + s);
                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                System.out.println("Assigning new thread for this client");
                // create a new thread object
                Thread t = new TicketHandler(Conector.getInstance(),s);
                // Invoking the start() method
                t.start();

            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }

    }

}
