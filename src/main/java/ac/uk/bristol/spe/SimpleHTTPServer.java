package ac.uk.bristol.spe;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;


/**
 * Created by csxds on 03/07/2017.
 */
public class SimpleHTTPServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0);
        server.createContext("/", new IndexHandler());
        server.createContext("/login", new LoginHandler());
        server.start();
    }

    static class IndexHandler implements HttpHandler {

        public void handle(HttpExchange t) throws IOException {
            String response = "Hello World";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class LoginHandler implements HttpHandler {

        public void handle(HttpExchange t) throws IOException {
            // do login
        }
    }

}


