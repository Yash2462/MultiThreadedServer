import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() throws IOException {
        int port = 8010;
        try {
            ServerSocket serverSocket = new ServerSocket(port); 
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is listening on port: " + port);

            while (true) {
                    Socket acceptedConnection = serverSocket.accept();
                    System.out.println("Connection accepted from client " + acceptedConnection.getInetAddress());

                        PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                        BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                        toClient.println("Hello from the server");
                        // Optionally, read input from the client
                        //  String clientMessage = fromClient.readLine();
                    
                        //  System.out.println("Message from client: " + clientMessage);

                        serverSocket.close();
                        toClient.close();
                        fromClient.close();
                } 
                
            }catch (IOException e) {
                    System.err.println("Error accepting connection: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.run();
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
