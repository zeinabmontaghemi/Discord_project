package javaa;

import resource.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import resource.BroadcastMassage;

public class Database {
    ArrayList<Account> users = new ArrayList<>();
    // ArrayList<Friendshipreq> frinedReq = new ArrayList<>();
    ArrayList<Server> servers = new ArrayList<>();


    void loaduserFile(){
        try {
            FileInputStream fileinput = new FileInputStream(/*path*/);
            ObjectInputStream objectinput = new ObjectInputStream(fileinput);
            this.users = (ArrayList) objectinput.readObject();
            fileinput.close();
            objectinput.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        // catch (FileNotFoundException e){
        //     e.printStackTrace();
        // }

    }

    public void saveuserFile(Account user){
        try {
            FileOutputStream fileoutput = new FileOutputStream(/* path */);
            ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);
            objectoutput.writeObject(user);
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
        loaduserFile();
        for (Account user : users){
            if (user.getId().equals(username)){
                return user;
            }
        }
        return null;
    }

    public BroadcastMassage loadBroadcastMassage(String index){
        try {
            FileInputStream fileinput = new FileInputStream(/* foldername + index.txt */ );
            ObjectInputStream objInput = new ObjectInputStream(fileinput);
            return (BroadcastMassage) objInput.readObject();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public boolean checkUsername(String username){
        for (int i = 0 ; i < users.size() ; i++){
            if (users.get(i).getId().equals(username)){
                return true;
            }
        }
        return false;
    }

    public boolean checkPassword(String password){
        for (int i = 0 ; i < users.size() ; i++){
            if (users.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public boolean checkServerName(String name){
        FileInputStream filein = new FileInputStream(/* server list path */);
        ObjectInputStream objInputStream = new ObjectInputStream(filein);
        ArrayList<Serverclass> sList = (ArrayList<Serverclass>) objInputStream.readObject();
        for (Serverclass server : sList){
            if (server.getServerName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public void saveServer(Serverclass serverclass){
        FileOutputStream fileout = new FileOutputStream(/* */);
        ObjectOutputStream objOutputstream = new ObjectOutputStream(fileout);
        objOutputstream.writeObject(serverclass);
    }

    // void saveServer(){

    // }


}
