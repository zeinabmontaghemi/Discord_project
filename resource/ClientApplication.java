package resource;

import javaa.Menu;
import javaa.Account;
import javaa.Serverclass;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class ClientApplication {
    // Account account;
    Scanner input;
    ArrayList<Account> friends;
    ArrayList<Serverclass> serverDis;
    ObjectOutputStream objOutputstream;
    ObjectInputStream objInputstream;
    InternalMenu iMenu;
    Menu menu;
    // Socket socket;


    ClientApplication(){
        // account = new Account();
        friends = new ArrayList<>(); // needed?
        iMenu = new InternalMenu();
    }
    
    void startClient(){
        try {
            input = new Scanner(System.in);
            Socket socket = new Socket("localhost" , 4000);
            objInputstream = new ObjectInputStream(socket.getInputStream());
            objOutputstream = new ObjectOutputStream(socket.getOutputStream());
            menu = new Menu(socket);

            while (socket.isConnected()){
                menu.menu();
                iMenu.firstMenu();
                String command = input.nextLine();
                objOutputstream.writeUTF(command);
                orders(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }

    void orders(String command){
        try {
            int order;
            switch (command){
                case "1":
                friends = (ArrayList<Account>) objInputstream.readObject();
                iMenu.printFriends(friends);
                // 2nd menu
                break;
                case "2":
                serverDis = (ArrayList<Serverclass>) objInputstream.readObject();
                if (serverDis.size() == 0){
                    System.out.println("Add new server? ");
                    System.out.println("1-Yes  2-Back");
                    order = input.nextInt();
                    if (order == 1){
                        
                    }
                    else if (order == 2) {
                        return;
                    }
                    else {
                        System.out.println("INVALID INPUT");
                    }
                }
            }
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    void AddServer(){
        try {
            while (true){
                System.out.println("Enter your Server Name");
                String serverName = input.nextLine(); 
                objOutputstream.writeUTF(serverName);
                if (!objInputstream.readBoolean()){
                    continue;
                }
                System.out.println("You have succsessfuly added " + serverName);
                // login to that discord server...
            }


        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
