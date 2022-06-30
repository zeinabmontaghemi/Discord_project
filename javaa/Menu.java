// import com.google.gson.*;

package javaa;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Account account;
    private ValidInput validInput;
    private Scanner input;
    private ObjectInputStream objInputStream;
    private ObjectOutputStream objOutputStream;



    public Menu (Socket socket){
        try {
            input = new Scanner(System.in);
            validInput = new ValidInput();
            account = new Account();
            objInputStream = new ObjectInputStream(socket.getInputStream());
            objOutputStream = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void  menu() {
        try{
            String choice;
            System.out.println("1.Sign In\n2.Sign Up");
            choice = input.nextLine();
            objOutputStream.writeUTF(choice);
            switch (choice){
                case "1":
                    signIn();
                case "2":
                    signUp();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    public void menu() throws IOException {
        String choice;
        System.out.println("""
                1.Sign In
                2.Sign Up
                """);

        choice = input.nextLine();
        switch (choice){
            case "1":
                signIn();
            case "2":
                signUp();
        }
    }
    public void signIn() { // برای ورود
        String username,password;
            int i = 0;
            try {
                while (true){
                    System.out.println("Enter your username");
                    username = input.nextLine();
                    System.out.println("Enter your password");
                    password = input.nextLine();
                    objOutputStream.writeUTF(username);
                    objOutputStream.writeUTF(password);
                    if (!objInputStream.readBoolean()){
                        if (i == 3){
                            System.out.println("Forget password?\n1-Yes 2-No");
                            int dokme = input.nextInt();
                            if (dokme == 1){
                                // do something like send password
                            }
                            else {
                                i = 0;
                                continue;
                            }
                        }
                        System.out.println("INCORRECT USERNAME OR PASSWORD");
                        i += 1;
                    }
                    System.out.println("Login in......"); 
                    break;
                }
            }
            catch (InputMismatchException | IOException e){
                e.printStackTrace(); // doroste?
            }
    }

    public void signUp() throws IOException { // برای ثبت نام

        String id, password,phoneNumber,countryCode,email;
        while (true){
            System.out.println("Enter your username");
            id = input.nextLine();
            objOutputStream.writeUTF(id);
            
            if (!validInput.checkId(id) ){
                System.out.println("INVALID USERNAME");
                continue;
            }
            else if (!objInputStream.readBoolean()){
                System.out.println("THIS USERNAME HAS ALREADY EXIST");
                continue;
            }
            break;
        }

        while (true){
            System.out.println("Enter your password : ");
            password = input.nextLine();
            objOutputStream.writeUTF(password);
            if (!validInput.checkPassword(password)){
                System.out.println("INVALID PASSWORD");
                continue;
            }
            else {
                break;
            }
        }

        while (true){
            System.out.println("Enter country code:");
            countryCode = input.nextLine();
            System.out.println("Enter your phone number:");
            phoneNumber = input.nextLine();
            if (!(validInput.checkCountryCode(countryCode)) && validInput.checkNumber(phoneNumber)){
                System.out.println("INVALID PHONENUMBER");
                continue;
            }
            else {
                break;
            }
        }

        while (true){
            System.out.println("Enter your email :");
            email=input.nextLine();
            if (!validInput.checkEmail(email)){
                System.out.println("INVALID EMAIL ADDRESS");
                continue;
            } 
            else {
                break;
            }
        }        
        account.setId(id);
        account.setPassword(password);
        account.setEmail(email);
        account.setCountryCode(countryCode);
        account.setPhoneNumber(phoneNumber);
        objOutputStream.writeObject(account);
    }

}
