package resource;

import javaa.Account;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    

    Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    void startServer() throws IOException{
        try {
            while (!serverSocket.isClosed()){
            Socket socket = serverSocket.accept();
            // System.out.println("test1");
            Account account = new Account();
            ClientHandler ch = new ClientHandler(socket  , account/*  , clientHandlers */);// این قسمت برای هرکدوم از سرور های دیسکورد باید لحاظ بشه
            clientHandlers.add(ch);
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
