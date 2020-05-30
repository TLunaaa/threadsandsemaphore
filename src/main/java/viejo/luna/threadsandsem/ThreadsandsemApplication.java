package viejo.luna.threadsandsem;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import viejo.luna.threadsandsem.model.*;


import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

@SpringBootApplication
public class ThreadsandsemApplication {

    public static void main(String[] args) {
        //SpringApplication.run(ThreadsandsemApplication.class, args);
        int port = 9000;
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            System.out.println("server started at " + port);
            server.createContext("/", (HttpHandler) new RootHandler());
            server.createContext("/comprar",(HttpHandler) new CompraHandler());
            server.createContext("/reservar",(HttpHandler) new ReservaHandler());
            server.createContext("/echoHeader", (HttpHandler) new EchoHeaderHandler());
            server.createContext("/echoGet", new EchoGetHandler());
            server.createContext("/echoPost", (HttpHandler) new EchoPostHandler());
            server.setExecutor(null);
            server.start();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

}
