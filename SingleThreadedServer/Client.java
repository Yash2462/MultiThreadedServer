import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public void run() throws UnknownHostException, IOException {
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        try {
             Socket socket = new Socket(address, port);
             PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             toServer.println("Hello from the client");
            // Read response from server
            String line = fromServer.readLine();
            System.out.println("Response from the socket is :"+line);
            if (line != null) {
                // Send response back to the server
                toServer.println("Response from the server is: " + line);
            } else {
                System.out.println("No response received from server.");
            }

            socket.close();
            toServer.close();
            fromServer.close();

        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.run();
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}
