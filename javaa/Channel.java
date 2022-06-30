// package src.main.java;
package  javaa;
import java.util.ArrayList;

public class Channel {
    private String channelName;
    private Chat pinned;

    Channel(){

    }

    public void setPinned(Chat pinned) {
        this.pinned = pinned; /* چجوری بگیم کدوم پیام؟  */
    }

    void showpinned(){
        System.out.println("Pinned Massage is: " + pinned);
    }
}
