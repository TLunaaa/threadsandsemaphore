package viejo.luna.threadsandsem.model;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class RootHandler implements HttpHandler {

    public void handle(HttpExchange he) throws IOException {
        int port = 9000;
        String response = "Server start success if you see this message" + " Port: " + port;
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}