import java.util.Scanner;

public class InternalMenu {
    private Scanner input;

    public InternalMenu() {
        input = new Scanner(System.in);

        // jsonClass = new JsonClass();
    }

    public void internalMenu() {
        String choice;
        System.out.println("""
                      1.Friends
                      2.Servers
                      3.Create new server
                      4.Setting
                      5.exit
                """);

        choice = input.nextLine();

        switch (choice) {
            case "1":
                ///
            case "2":
                ///
            case "3":
                ///create server
            case"5":
///exit

                serverMenu();

            case "4":
                setting();
        }
    }


    public void setting() {
        String choice;

        System.out.println("""
                               
                1.USER SETTING   ?????
                2.ACTIVITY SETTING
                3.log Out
                 """);
        choice = input.nextLine();
        switch (choice) {
            case "1":
                //
            case "2":
                System.out.println("  <<<Activity Status>>>");
                status();
            case "3":
                ///log out
        }
    }

    public void status() {
        String choice;
        System.out.println("""
                   <<< Choose your status >>>\s
                                
                       1.Online\s
                       2.Idle
                       3.Do No Disturb:
                          you will not receive any desktop
                          notification.
                       4.Invisible:
                          You will not appear online,but\040
                          will have full access to all of\040
                          Discord.
                        
                """);

        choice = input.nextLine();
        switch (choice) {
            case "1":
                // boolean status true

            case "2":
                // boolean status true
            case "3":
                // boolean status true
            case "4":
                // boolean status true
        }
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
        choice = input.nextLine();
        switch (choice) {
            case "1":
                //print online friends' Arraylist
            case "2":
                //print all friends' arraylist
            case "3":
                // request lists
            case "4":
                // blocked users
            case "5":
                // add friend
        }
    }


    public void serverMenu() {
        String choice;
        System.out.println("""
                                
                1.Invite People
                2.Server Setting
                3.Create Channel
                4.Edit Server Profile ???
                                
                """);
        choice = input.nextLine();
        switch (choice) {
            case "1":
                //show friends' list
            case "2":
                serverSetting();
            case "3":
                //
            case "4":
                //

        }
    }

    public void serverSetting() {
        String choice;
        System.out.println("""
                1.User Management
                2.Delete Server
                3.Server Overview
                """);
        choice = input.nextLine();
        switch (choice) {
            case "1":
                userManagement();
            case "2":
                //
            case "3":
                //able to edit server name ?????
        }
    }

    public void userManagement() {
        String choice;

        System.out.println("""
                                
                1.Member
                2.Invites
                3.Ban
                                
                """);
        choice = input.nextLine();

        switch (choice) {
            case "1":

            case "2":

        }

    }
}
