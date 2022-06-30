package resource;

import javaa.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    Socket socket;
    private Scanner input;
    String username;
    ObjectOutputStream objOutputstream;
    ObjectInputStream objInputstream;
    ArrayList<Account> friends;
    ArrayList<Serverclass> clientServerDis;
    InternalMenu iMenu;
    Account account;
    Menu Menu;
//    BroadcastMassage bMassage; //uncomment this

    public Client(Socket socket , Scanner input , Account account , ObjectOutputStream objOutputstream , ObjectInputStream objInputstream /*  ,  ObjectInputStream input , ObjectOutputStream output, String username */){
        try {
            this.socket = socket;
            this.account = account;
            this.input = input;
//            bMassage = new BroadcastMassage();
            this.objOutputstream = objOutputstream;
            this.objInputstream = objInputstream;
            friends = new ArrayList<>();
            iMenu = new InternalMenu();
            // this.Menu = new Menu();

        }
        catch (Exception e){
            closeAll(socket, objOutputstream, objInputstream);
            e.printStackTrace();
        }
    }


    void orders() throws IOException {
        try {
            while (true){
                iMenu.firstMenu();
                String command = input.nextLine();
                objOutputstream.writeUTF(command);
                objOutputstream.flush();
                int order;
                switch (command) {
                    case "1":
                        iMenu.friendPartMenu();
                        String friendDokme = input.nextLine();
                        if (friendDokme.equals("1")){
                            objOutputstream.writeInt(1);
                            objOutputstream.flush();
                            //online friends
                        }
                        else if (friendDokme.equals("2")){
                            objOutputstream.writeInt(2);
                            objOutputstream.flush();
                            friends = (ArrayList<Account>) objInputstream.readObject();
                            iMenu.printFriends(friends);
                            if (friends.size() == 0){
                                String dokme = input.nextLine();
                                if (dokme.equals("1")){
                                    objOutputstream.writeUTF("10");
                                    objOutputstream.flush();
                                    addNewFriend();
                                    // send friend req to that person
                                }
                                else if (dokme.equals("2")){
                                    objOutputstream.writeUTF("20");
                                    objOutputstream.flush();
                                    continue;
                                    //back
                                }
                            }
                            // send pv massage
                            // 2nd menu
                        }
                        else if (friendDokme.equals("3")){
//                            System.out.println("------------------Update-----------------------");
//                            updateMenu.showPendingMenu();
//                            System.out.println("-----------------------Friends--------------------");
                            iMenu.printFriendReq(account.getFriendReq());
                            String reqAct = input.nextLine();
                            String[] reqOrd = reqAct.split(",");
                            int req = Integer.parseInt(reqOrd[0]);
                            objOutputstream.writeInt(req);
                            objOutputstream.flush();
                            if (req != 0){
                                int ord = Integer.parseInt(reqOrd[1]);
                                objOutputstream.writeObject(account.getFriendReq().get(req));
                                objOutputstream.flush();
                                if (ord == 1){
                                    System.out.println(objInputstream.readUTF());
                                }
                                else if (ord == 2){
                                    System.out.println(objInputstream.readUTF());
                                }
                            }
//                            else if (req == 0){
//                              //back
//                            }
                        }
                        break;
                    case "2":
                        clientServerDis = (ArrayList<Serverclass>) objInputstream.readObject();
                        if (clientServerDis.size() == 0) {
                            System.out.println("Add new server? ");
                            System.out.println("1-Yes  2-Back");
                            order = Integer.parseInt(input.nextLine());
                            objOutputstream.writeInt(order);
                            objOutputstream.flush();
                            if (order == 1) {
                                joinuserServer();
                                // join that server
                            } else if (order == 2) {
                                continue;
                            } else {
                                System.out.println("INVALID INPUT");
                            }
                        }
                    case "3":
                        //create server
                    case "4":
                        joinuserServer();
                    case "5":
                        addNewFriend();
                    case"6"://////////////////////////////////////////////////////////////////
                        iMenu.setting();
                        String settingChoice = input.nextLine();

                        //user setting
                        if(settingChoice.equals("1")){
                            objOutputstream.writeUTF("1");
                            objOutputstream.flush();
                            iMenu.userSetting();
                            String userSettingChoice = input.nextLine();
                            //change username
                            if(userSettingChoice.equals("1")){
                                objOutputstream.writeUTF("1");
                                objOutputstream.flush();
                                //changeUser

                            }
                            //change password
                            else if(userSettingChoice.equals("2")){
                                objOutputstream.writeUTF("2");
                                objOutputstream.flush();
                                ///changePassword();

                            }
                        }
                        //activity setting
                        else if(settingChoice.equals("2")){

                            objOutputstream.writeUTF("2");
                            objOutputstream.flush();
                            iMenu.status();
                            String statusChoice = input.nextLine();
                            //online
                            if(statusChoice.equals("1")){
                                objOutputstream.writeUTF("1");
                                objOutputstream.flush();
                                account.setUserStatus(Account.UserStatus.Online);

                            }
                            //idle
                            else if(statusChoice.equals("2")){
                                objOutputstream.writeUTF("2");
                                objOutputstream.flush();
                                account.setUserStatus(Account.UserStatus.Idle);


                            }
                            //do not disturb
                            else if(statusChoice.equals("3")){
                                objOutputstream.writeUTF("3");
                                objOutputstream.flush();
                                account.setUserStatus(Account.UserStatus.Do_Not_Disturb);

                            }
                            //invisible
                            else if (statusChoice.equals("4")){
                                objOutputstream.writeUTF("4");
                                objOutputstream.flush();
                                account.setUserStatus(Account.UserStatus.Invisible);

                            }
                        }
                        //log out
                        else if (settingChoice.equals("3")){
                            objOutputstream.writeUTF("3");
                            objOutputstream.flush();
                            ///log out
                        }
                    case"7":


                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void clientMassageSender(){
        try {

            String massageFromClient;
            objOutputstream.writeUTF(username);
            objOutputstream.flush();
            while (socket.isConnected()){
                massageFromClient = input.nextLine();
                objOutputstream.writeUTF(username + " : " + massageFromClient);
                objOutputstream.flush();
            }
        }
        catch (IOException | NullPointerException e){
            closeAll(socket, objOutputstream, objInputstream);
            e.printStackTrace();
        }

    }

//    void recieveFriendReq() throws IOException {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Account sender = (Account) objInputstream.readObject();
//                    System.out.println(sender + "send you a friend request do you want to accept it?\n1-Yes 2-N0 3-Back");
//                    int dokme = input.nextInt();
//                    if (dokme == 1){
//                        account.addFriend(sender);
//                        sender.addFriend(account);
//                    }
//                    else if (dokme == 2) {
//                        System.out.println("You denied friend request");
//                    }
//                    else if (dokme == 3){
//                        // back
//                    }
//                    else {
//                        System.out.println("INVALID INPUT");
//                    }
//                } catch (IOException | ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//
//    }

    //    void clientmassagereiciver(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String massagefromgroup;
//                while (socket.isConnected()){
//                    try {
//                        massagefromgroup = objInputstream.readUTF();
//                        if (/*user is online */){
//                            System.out.println(massagefromgroup);
//                        }
//                        else {
//                            System.out.println("--------------New Massage-----------------");
////                            bMassage.loadUnreadMassage(username);
//                        }
//                    }
//                    catch (IOException | NullPointerException e){
//                        e.printStackTrace();
//                        closeAll(socket, objOutputstream, objInputstream);
//
//                    }
//                }
//            }
//        }).start();
//    }
    void recieveFriendReq(){

    }

    void joinuserServer() throws IOException, ClassNotFoundException {
        iMenu.printServers((ArrayList<Serverclass>) objInputstream.readObject());
        int indexServer = Integer.parseInt(input.nextLine());
        objOutputstream.writeInt(indexServer);
        objOutputstream.flush();
        System.out.println("You have succseccfuly joined the server");
    }

    void addNewFriend() throws IOException, ClassNotFoundException {
        System.out.print("Search for new user: ");
        String searchfriend = input.nextLine();
        objOutputstream.writeUTF(searchfriend);
        objOutputstream.flush();
        System.out.println(objInputstream.readUTF());

    }

    void closeAll(Socket socket , ObjectOutputStream objoutputstream , ObjectInputStream objinputstream){
        try{
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

//when user logged out -> back to sign up menu
    // when exit in the first menu is chosen its status turn to offline
    // the difference is when he back , at first case he should sign up and in second one sign in


}
