package resource;

import javaa.Account;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    private ServerSocket serverSocket;
    private HashMap<String  , ClientHandler > clientHandlers = new HashMap<>();


    Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    void startServer() throws IOException{
        try {
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("client has connected");
                ClientHandler ch = new ClientHandler(socket , clientHandlers /*  , clientHandlers */);
//            clientHandlers.put(Id , ClientHandler);
                Thread myThread = new Thread(ch);
                myThread.start();
            }
        }
        catch (IOException e){
            if (serverSocket != null){
                serverSocket.close();
            }
            else {
                e.printStackTrace();
            }
        }
    }






}