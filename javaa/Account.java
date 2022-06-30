package javaa;

import java.util.ArrayList;

public class Account {
    private String Id;
    private String Password;
    private String PhoneNumber;
    private String Email;
    private String countryCode;
    private ArrayList<Account> friends = new ArrayList<>();
    private ArrayList<Serverclass> serverDis = new ArrayList<>();


    enum UserStatus {
        Online,
        Idle,
        Do_Not_Disturb,
        Invisible,
    }

    // public int showServerDis(){
    //     if (serverDis.size() == 0){
    //         System.out.println("your server list is empty!");
    //         return -1;
    //     }
    //     else {
    //         for (int i = 0 ; i < serverDis.size() ; i++){
    //             System.out.println( i+1 + "-" + serverDis.get(i).getServerName());
    //         }
    //     }
    // }

    // public void showFriends(){
    //     if (friends.size() == 0){
    //         System.out.println("your friend list is empty!");
    //     }
    //     else {
    //         for (int i = 0 ; i < friends.size() ; i++){
    //             System.out.println(i+1 + "-" + friends.get(i).getId());
    //         }
    //     }
    // }
    public ArrayList<Serverclass> getServerDis() {
        return serverDis;
    }

    public ArrayList<Account> getFriends() {
        return friends;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Serverclass addServer(String serverName){
        Serverclass serverClass = new Serverclass(serverName);
        serverDis.add(serverClass);
        return serverClass;
    }
//    public String toString() {
//        return "(" + countryCode + ")" + number;
//}
}