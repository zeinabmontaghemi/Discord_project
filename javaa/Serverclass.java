// package src.main.java;
// package discord.src.java;
package javaa;
import java.util.ArrayList;

public class Serverclass {
    // this is servers in discord
    private String serverName;
    private ArrayList<Admin> admins;
    private Admin mainAdmin;
    private ArrayList<Account> users;
    private ArrayList<Channel> channels;

    Serverclass(String serverName){
        this.serverName = serverName;
        admins = new ArrayList<>();
        users = new ArrayList<>();
        channels = new ArrayList<>();
    }

    public String getServerName() {
        return serverName;
    }

    // public void createServer(){

    // }

    // void createChannel(){
    //     Channel newChannel = new Channel();

    // }

    
}
