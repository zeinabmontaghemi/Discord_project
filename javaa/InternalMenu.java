package javaa;
import java.util.ArrayList;
import java.util.Scanner;


public class InternalMenu {

    Account account = new Account();
    public InternalMenu() {
    }

    public void firstMenu() {
        System.out.println("""
                1.Friends
                2.Servers
                3.Create new server
                4.Join new server
                5.Add new friend ////???????
                6.Setting
                7.exit
                """);
    }

    public void printFriends(ArrayList<Account> friends){
        if (friends.size() == 0){
            System.out.println("your friends list is empty");
            System.out.println("Add new friend?\n1-Yes 2-No");
        }
        else {
            for (int i = 0 ; i < friends.size() ; i++){
                System.out.println(i + 1 + friends.get(i).getId());
            }
        }
    }

    public  void printServers(ArrayList<Serverclass> servers){
        for (int i = 0 ; i < servers.size() ; i++){
            System.out.println(i + 1 + servers.get(i).getServerName());
        }
    }


    public void setting() {

        System.out.println("""
                  1.USER SETTING   ????? \s
                  2.ACTIVITY SETTING \s
                  3.log Out
                  """);

    }

    public void userSetting(){
        System.out.println("<<<<My Account>>>>\n"+ "username:"+account.getId()  +"\n1.Change username\n" +"2.change password\n");
    }

    public void status() {
        String choice;
        System.out.println("""
                    <<< Activity Status>>>\s
                      Choose your status:\s
                      1.Online\s
                      2.Idle\040
                      3.Do Not Disturb:
                        you will not receive any desktop
                        notification.
                      4.Invisible:
                        You will not appear online,but\040
                        will have full access to all of\040
                        Discord.
                        
                 """);

    }

    public void friendPartMenu() {
        String choice;
        System.out.println("""
                 
                   << Friends >>
                                
                      1.Online
                      2.All
                      3.Pending
                      4.Blocked
                      5.Add Friend
                   
                 """);
    }


    public void serverMenu() {
        String choice;
        System.out.println("""
                                
                     1.Invite People ////??????
                     2.Server Setting
                     3.Create Channel
                     4.Edit Server Profile ???
                                
                 """);
    }

    public void serverSetting() {
        String choice;
        System.out.println("""
                   1.User Management
                   2.Delete Server
                   3.Server Overview
                 """);
    }

    public void userManagement() {
        String choice;

        System.out.println("""
                                
                   1.Member
                   2.Invites ////???????
                   3.Ban
                                
                 """);
    }


    public void printFriendReq(ArrayList<Account> friendShip){
        System.out.println("Enter friendship number and the action (split them with comma)");
        for (int i = 0 ; i < friendShip.size() ; i++){
            System.out.println(i + 1 + friendShip.get(i).getId() + "1-Accept  2-Deny");
        }
        System.out.println("0-Back");
    }
    //if user type #menu
//this menu should be shown
    public void chatMenu(){
        System.out.println("""
            <<chat menu>>
            1.send message
            2.reaction
            3.send file
            4.pin /// if user type  !pin ,the message will be pin and saved in a file which called pined message
                  //// it will be store with ?pin sign and when we want to read it we should print  ?pin message
            5.exit // exit from chat and channel
            """);
    }
}
