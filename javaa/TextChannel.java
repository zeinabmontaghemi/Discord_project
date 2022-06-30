package javaa;// package src.main.java;

import java.util.ArrayList;

public class TextChannel extends Channel{
    String textchannelName;
    ArrayList<Chat> chats;

    TextChannel(){
        chats = new ArrayList<>();
    }

}
