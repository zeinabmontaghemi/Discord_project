//package javaa;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInput;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//
//public class addServerFriend {
//
//    void addServer(Serverclass serverclass){
//        try {
//            FileOutputStream fileout = new FileOutputStream(/* */);
//            ObjectOutputStream objoutputstream = new ObjectOutputStream(fileout);
//            objoutputstream.writeObject(serverclass);
//            fileout.close();
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    Serverclass loadServer(){
//        try{
//            FileInputStream filein = new FileInputStream(/* */);
//            ObjectInputStream objinputstream = new ObjectInputStream(filein);
//            Serverclass serverclass = (Serverclass) objinputstream.readObject();
//            return serverclass;
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//
//}
