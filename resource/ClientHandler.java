package resource;


import javaa.Account;
import javaa.Database;
import javaa.Serverclass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler implements Runnable {

    ArrayList<ClientHandler> clientHandlers;
    ObjectInputStream objInputstream;
    ObjectOutputStream objOutputstream;
    String clientUsername;
    Account user;
    Socket socket;
    Database database;
    InternalMenu iMenu;


    ClientHandler(Socket socket , Account user /*  , ObjectInputStream inputS, ObjectOutputStream outputS */  /*  , ArrayList<ClientHandler> clientHandlers */) throws IOException{
        try {
            this.user = user;
            this.socket = socket;
            database = new Database();
            objOutputstream = new ObjectOutputStream(socket.getOutputStream());    
            objInputstream = new ObjectInputStream(socket.getInputStream());
            String username = objInputstream.readUTF();
            clientUsername = username;
            System.out.println(username + " has succsessfully added");
        }
        catch (IOException e){
            if (socket != null){
                socket.close();
            }
            
        }

    }

    @Override
    public void run() {
        try {
            // System.out.println(objInputstream.readUTF());

            String massagefromclient;
            while (socket.isConnected()){
                String loginOrder = objInputstream.readUTF();
                    switch (loginOrder){
                        case "1":
                        String username , password;
                        while (true){
                            username = objInputstream.readUTF();
                            password = objInputstream.readUTF();
                            if (!database.checkUsername(username) && !database.checkPassword(password)){
                                objOutputstream.writeBoolean(false);
                                continue;
                            }
                            break;
                        }
                        this.user = database.getAccount(username);
                        break;
                        case "2":
                        signUp();

                        
                    }


                // objOutputstream.writeUTF();
                // objOutputstream.writeUTF(iMenu.firstMenu());
                String command = objInputstream.readUTF();
                int order;
                switch (command){
                    case "1":
                    objOutputstream.writeObject(user.getFriends());
                    case "2" :
                    objOutputstream.writeObject(user.getServerDis());
                    if (user.getServerDis().size() == 0){
                        if ((order = objInputstream.readInt()) == 1){
                            // list of servers
                        }
                    }
                    break;
                    case "3" :
                    while (true){
                        String serverName = objInputstream.readUTF();
                        if (!database.checkServerName(serverName)){
                            objOutputstream.writeBoolean(false);
                            continue;
                        }
                        else {
                            objOutputstream.writeBoolean(true);
                            Serverclass serverclass = user.addServer(serverName);
                            database.saveServer(serverclass);
                            break;
                        }
                    }
                    break;

                    // frineds list 
                    // String index = objInputstream.readUTF();
                    // broadcastmassage(database.loadBroadcastMassage(index).accounts);

                }
                // massagefromclient = objInputstream.readUTF();
                // System.out.println(massagefromclient);
                // broadcastmassage(massagefromclient);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    void signUp(){
        try {
            while (true){
                String username = objInputstream.readUTF();
                objOutputstream.writeBoolean(database.checkUsername(username));
                this.user = (Account) objInputstream.readObject();
                database.saveuserFile(user);
                
            }
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    void broadcastmassage(HashMap<Account , ClientHandler> accounts){
        try {
            String massagetosend = objInputstream.readUTF();
            while (!massagetosend.equals("BACK")){
                System.out.println(massagetosend);
                for (Map.Entry<Account , ClientHandler> entry : accounts.entrySet()){
                    if ( !entry.getKey().getId().equals(clientUsername)){
                        entry.getValue().objOutputstream.writeUTF(massagetosend);
                        entry.getValue().objOutputstream.flush();

                    }
                }
            massagetosend = objInputstream.readUTF();
            }
            for (Map.Entry<Account , ClientHandler> entry : accounts.entrySet()){
                if ( !entry.getKey().getId().equals(clientUsername)){
                    entry.getValue().objOutputstream.writeUTF(clientUsername + "is not here");
                    entry.getValue().objOutputstream.flush();

                }
            }

            
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    // void broadcastMessage(String massagefromclient){
    //     try {
    //         for (int i = 0 ; i < clientHandlers.size() ; i++){
    //             if (!clientHandlers.get(i).clientUsername.equals(clientUsername)){
    //                 clientHandlers.get(i).objOutputstream.writeUTF(massagefromclient);
    //                 clientHandlers.get(i).objOutputstream.flush();
    //             }
    //         }
    //     }
    //     catch (IOException e){
    //           closeAll(socket, objOutputstream, objInputstream);  
    //           e.printStackTrace();   
    //     }
    // }

    void removeClientHandler() {
        clientHandlers.remove(this);
        // broadcastMessage("SERVER: " + clientUsername + " has left the chat!");
    }

    void closeAll(Socket socket , ObjectOutputStream objoutputstream , ObjectInputStream objinputstream){
        try{
            removeClientHandler();
            if (socket != null){
                socket.close();
            }
            if (objinputstream != null){
                objinputstream.close();
            }
            if (objoutputstream != null){
                objoutputstream.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    void PvFromFile(String fileIndex){
        
    }



}
