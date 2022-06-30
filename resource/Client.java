package resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket socket;
    String username;
    ObjectOutputStream objOutputstream;
    ObjectInputStream objInputstream;
    BroadcastMassage bMassage;

    public Client(Socket socket /*  ,  ObjectInputStream input , ObjectOutputStream output, String username */){
        try {
            this.socket = socket;      
            bMassage = new BroadcastMassage(); 
            objOutputstream = new ObjectOutputStream(socket.getOutputStream());
            objInputstream = new ObjectInputStream(socket.getInputStream());

        }
        catch (Exception e){
            closeAll(socket, objOutputstream, objInputstream);
            e.printStackTrace();
        }
    }

    void clientmassageSender(Scanner input){
        try {    
      
            String massagefromclient;
            objOutputstream.writeUTF(username);
            objOutputstream.flush();
            // System.out.println("test3");
            while (socket.isConnected()){             
                massagefromclient = input.nextLine();
                objOutputstream.writeUTF(username + " : " + massagefromclient);
                objOutputstream.flush();
            }
        }
        catch (IOException | NullPointerException e){
            closeAll(socket, objOutputstream, objInputstream);
            e.printStackTrace();
        }
        
    }

    void clientmassagereiciver(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String massagefromgroup;
                while (socket.isConnected()){
                    try {
                        massagefromgroup = objInputstream.readUTF();
                        if (/*user is online */){
                            System.out.println(massagefromgroup);
                        }
                        else {
                            System.out.println("--------------New Massage-----------------");
                            bMassage.loadUnreadMassage(username);
                        }
                        
                    }
                    catch (IOException | NullPointerException e){
                        e.printStackTrace();
                        closeAll(socket, objOutputstream, objInputstream);
                        
                    }
                }
            }
        }).start();
    }

    void 

    void closeAll(Socket socket , ObjectOutputStream objoutputstream , ObjectInputStream objinputstream){
        try{
            if (socket != null){
                socket.close();
            }
            if (objinputstream != null){
                objinputstream.close();
            }
            if (objoutputstream != null){
                objoutputstream.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args)  {
        try {
            Scanner input = new Scanner(System.in);
            Socket socket = new Socket("localhost" , 4000 );
            Client client = new Client(socket/*  ,objInputstream,objOutputstream, username */ ) ;
            client.getClient(input);
            client.clientmassagereiciver();            
            client.clientmassageSender(input);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    void getClient(Scanner input){
        System.out.println("Enter your username: ");
        username = input.nextLine();
    }
}
