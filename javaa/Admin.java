package javaa;

import java.util.ArrayList;

public class Admin extends Account{
    // private String serverName;
    // private String createdRoleName;

    ArrayList<Account> serverUsers = new ArrayList<Account>();///??we have more than one server ,so we should specify
                                                              //// server user of which server should be added or delete


    // public String getServerName() {
    //     return serverName;
    // }

    // public void setServerName(String serverName) {
    //     this.serverName = serverName;
    // }

    // public String getCreatedRoleName() {
    //     return createdRoleName;
    // }

    // public void setCreatedRoleName(String createdRoleName) {
    //     this.createdRoleName = createdRoleName;
    // }

    public boolean addContact(Account account){
        boolean f = true;
        //if that contact has already exist return false
        for(Account serverUser :serverUsers){
            if(serverUser.equals(account) ){
                f = false;

                break;
            }

        }
        serverUsers.add(account);
        return f;

    }

    public boolean deleteServerUser(String id){
        boolean f = false;
        for(Account serverUser : serverUsers){
            //check if serverUser existed in list or not
            if(){
                f=true;
                serverUsers.remove(serverUser);
                break;
            }
        }
        return  f;
    }





}
