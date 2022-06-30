package javaa;

import resource.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//import resource.BroadcastMassage;

public class Database {
    private ArrayList<Account> users = new ArrayList<>();
    // ArrayList<Friendshipreq> frinedReq = new ArrayList<>();
    private ArrayList<Serverclass> servers = new ArrayList<>();
    final String accountListpath = "C:\\Users\\LENOVO\\Desktop\\Midterm-Discord-master\\src\\datafiles\\usersList.bin";

    public ArrayList<Serverclass> getServers() {
        return servers;
    }

    void loadusersFile(){
        try {
            FileInputStream fileinput = new FileInputStream(accountListpath);
            ObjectInputStream objectinput = new ObjectInputStream(fileinput);
            this.users = (ArrayList<Account>) objectinput.readObject();
            fileinput.close();
            objectinput.close();
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        // catch (FileNotFoundException e){
        //     e.printStackTrace();
        // }

    }

    void saveusersFile(Account user){
        try {
            FileOutputStream fileinput = new FileOutputStream(accountListpath);
            ObjectOutputStream objOutputstream = new ObjectOutputStream(fileinput);
            users.add(user);
            objOutputstream.writeObject(users);
            objOutputstream.flush();
            fileinput.close();
            objOutputstream.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveuserFile(Account user){
        try {
            FileOutputStream fileoutput = new FileOutputStream("C:\\Users\\LENOVO\\Desktop\\Midterm-Discord-master\\src\\datafiles\\" + user.getId() + ".bin");
            ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);
            objectoutput.writeObject(user);
            objectoutput.flush();
            saveusersFile(user);
            objectoutput.close();
            fileoutput.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        // catch (FileNotFoundException e){
        //     e.printStackTrace();
        // }
    }

    public Account getAccount(String username){
        loadusersFile();
        for (Account user : users){
            if (user.getId().equals(username)){
                return user;
            }
        }
        return null;
    }

//    public BroadcastMassage loadBroadcastMassage(String index){
//        try {
//            FileInputStream fileinput = new FileInputStream(/* foldername + index.txt */ );
//            ObjectInputStream objInput = new ObjectInputStream(fileinput);
//            return (BroadcastMassage) objInput.readObject();
//        }
//        catch (IOException | ClassNotFoundException e){
//            e.printStackTrace();
//        }
//
//    }


//    public  ArrayList<Serverclass> loadServers(){
//        return  servers;
//    }

    public boolean checkUsername(String username){
        loadusersFile();
        for (Account user : users) {
            if (user.getId().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPassword(String password){
        for (Account user : users) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Account> getUsers() {
        return users;
    }

//    //
//    public boolean checkServerName(String name) throws IOException {
//        FileInputStream filein = new FileInputStream(/* server list path */);
//        ObjectInputStream objInputStream = new ObjectInputStream(filein);
//        ArrayList<Serverclass> sList = (ArrayList<Serverclass>) objInputStream.readObject();
//        for (Serverclass server : sList){
//            if (server.getServerName().equals(name)){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void saveServer(Serverclass serverclass) throws IOException {
//        FileOutputStream fileout = new FileOutputStream(/* */);
//        ObjectOutputStream objOutputstream = new ObjectOutputStream(fileout);
//        objOutputstream.writeObject(serverclass);
//    }




}
