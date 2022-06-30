package resource;


import javaa.*;

import java.io.*;
import java.net.Socket;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler implements Runnable {

    HashMap<String , ClientHandler> clientHandlers;
    ObjectInputStream objInputstream;
    ObjectOutputStream objOutputstream;
    String clientUsername;
    Account user;
    Socket socket;
    Database database;



    ClientHandler(Socket socket , HashMap<String , ClientHandler> clientHandlers /*  , ObjectInputStream inputS, ObjectOutputStream outputS */  /*  , ArrayList<ClientHandler> clientHandlers */) throws IOException{
        try {
            this.clientHandlers = clientHandlers;

            this.socket = socket;
            database = new Database();
            objOutputstream = new ObjectOutputStream(socket.getOutputStream());
            objInputstream = new ObjectInputStream(socket.getInputStream());
//            user = (Account) objInputstream.readObject();

//            String username = objInputstream.readUTF();
//            clientUsername = username;
//            System.out.println(username + " has successfully added");
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
                switch (loginOrder) {
                    case "1" -> {
                        String username, password;
                        while (true) {
                            username = objInputstream.readUTF();
                            password = objInputstream.readUTF();
                            if (!(database.checkUsername(username)) || !(database.checkPassword(password))) {
                                objOutputstream.writeBoolean(false);
                                objOutputstream.flush();
                                continue;
                            }
                            objOutputstream.writeBoolean(true);
                            objOutputstream.flush();
                            break;
                        }
                        clientUsername = username;
                        this.user = database.getAccount(username);
                    }
                    case "2" -> signUp();
                }
                // update friend list


                // objOutputstream.writeUTF();
                // objOutputstream.writeUTF(iMenu.firstMenu());
                this.user = database.getAccount(clientUsername);
                while (true){
                    String command = objInputstream.readUTF();
                    int order;
                    switch (command) {
                        case "1":
                            int friendOrder = objInputstream.readInt();
                            if (friendOrder == 1){
                                // online friends
                            }
                            else if (friendOrder == 2){
                                objOutputstream.writeObject(user.getFriends());
                                objOutputstream.flush();
                                if (user.getFriends().size() == 0) {
                                    String commandCode = objInputstream.readUTF();
                                    if (commandCode.equals("10")) {
                                        addNewFriend();
                                    } else if (commandCode.equals("20")) {
                                        continue;
                                    }
                                }
                            }
                            else if (friendOrder == 3){
                                int req = objInputstream.readInt();
                                if (req != 0){
                                    int ord = objInputstream.readInt();
                                    Account maybefriend = ((Account)objInputstream.readObject());
                                    user.removeFriendship(maybefriend);
                                    maybefriend.removeFriendship(user);
                                    if (ord == 1){
                                        maybefriend.addFriend(user);
                                        user.addFriend(maybefriend);
                                        objOutputstream.writeUTF(maybefriend.getId() + "Added to your friend");
                                        objOutputstream.flush();
                                    }
                                    else if (ord == 2){
                                        objOutputstream.writeUTF(maybefriend.getId() + "removed from request");
                                        objOutputstream.flush();
                                    }

                                }
                            }
                            break;

                        case "2":
                            objOutputstream.writeObject(user.getServerDis());
                            if (user.getServerDis().size() == 0) {
                                int orderAdd = objInputstream.readInt();
                                if (orderAdd == 1) {
                                    objOutputstream.writeObject(database.getServers());
                                    objOutputstream.flush();
                                }
                            }
                            break;
                        case "3":
//                        while (true) {
//                            String serverName = objInputstream.readUTF();
//                            if (!database.checkServerName(serverName)) {
//                                objOutputstream.writeBoolean(false);
//                                continue;
//                            } else {
//                                objOutputstream.writeBoolean(true);
//                                Serverclass serverclass = user.addServer(serverName);
//                                database.saveServer(serverclass);
//                                break;
//                            }
//                        }
                            break;
                        case "4":
                            objOutputstream.writeObject(database.getServers());
                            objOutputstream.flush();
                            int serverInd = objInputstream.readInt();
                            database.getServers().get(serverInd).addUser(user);
                            user.addServerDis(database.getServers().get(serverInd));
                        case "5":
                            addNewFriend();

                        case "6":
                            String settingChoice = objInputstream.readUTF();//////////////////////
                            if(settingChoice.equals("1")){

                            }
                            else if(settingChoice.equals("2")){

                            }
                            else if(settingChoice.equals("3")){

                            }

                    }

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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    void signUp(){
        try {
            while (true){
                String username = objInputstream.readUTF();

                boolean check = database.checkUsername(username);
                objOutputstream.writeBoolean(check);
                objOutputstream.flush();
                if (check){
                    continue;
                }
                user = (Account) objInputstream.readObject();
                database.saveuserFile(user);
                clientUsername = user.getId();
                break;
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

    void addNewFriend() throws IOException {
        String accountName = objInputstream.readUTF();
        Account searchAccount = database.getAccount(accountName);
        if (searchAccount == null){
            objOutputstream.writeUTF("This Account doesn't exist");
            objOutputstream.flush();
        }
        else {
//            clientHandlers.get(accountName).objOutputstream.writeObject(user); // uncomment this
            objOutputstream.flush();
            objOutputstream.writeUTF("You have friend request has sent");
            objOutputstream.flush();
        }
    }


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

    void changeUsername(){
        try {
            while (true){
                String newUsername = objInputstream.readUTF();

                boolean check = database.checkUsername(newUsername);
                objOutputstream.writeBoolean(check);
                objOutputstream.flush();
                if (check){
                    continue;
                }
                user = (Account) objInputstream.readObject();
                database.saveuserFile(user);
                String  clientNewUsername = user.getId();
                break;
            }
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    void changePassword() throws IOException {
        String newPassword = objInputstream.readUTF();

    }


}
