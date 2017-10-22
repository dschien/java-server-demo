package ac.uk.bristol.spe;

/**
 * Created by csxds on 03/07/2017.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Simple HTTP Server
 */
public class BasicSocketServer {

    public static void main(String args[]) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080 ....");
        while (true) {
            try (Socket socket = server.accept()) {

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream());

                // Write out our header to the client
                out.write("HTTP/1.0 200 OK\r\n\r\n");
                out.flush();

                // Echo lines back to the client until the client closes the connection or we receive an empty line
                String http_header = in.readLine();
                String path = http_header.split(" ")[1];

                String result = null;

                switch (path) {
                    case "/":
                        result = index();
                }

                out.println(result);
                out.flush();

                // Close our connection
                in.close();
                out.close();
                socket.close();
            }
        }
    }

    public static String index() {
        return "Hello World!";
    }
}

