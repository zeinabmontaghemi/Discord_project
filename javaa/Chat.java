package javaa;// package src.main.java;

import java.util.HashMap;

public class Chat {
    private String chat;
    private HashMap<Account , Action > reaction;

    Chat(){
        reaction = new HashMap<>();
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getChat() {
        return chat;
    }

    void setReaction(Account myAccount){
        Action newAction = new Action();
//        newAction.setAction();
//        reaction.add(myAccount , newAction); //uncomment this
    }

}
