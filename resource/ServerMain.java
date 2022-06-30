package resource;
import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain {
    public static void main(String[] args)  throws IOException {
        ServerSocket newServerSocket = new ServerSocket(4000);
        Server server = new Server(newServerSocket);
        server.startServer();
    }

}
