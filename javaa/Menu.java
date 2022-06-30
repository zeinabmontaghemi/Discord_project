// import com.google.gson.*;

package javaa;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {
    private Account account;
    private ValidInput validInput;
    private Scanner input;
    private ObjectInputStream objInputStream;
    private ObjectOutputStream objOutputStream;


    public Menu(ObjectInputStream objInputStream, ObjectOutputStream objOutputStream, Scanner input) {
        this.input = input;
        validInput = new ValidInput();
        account = new Account();

        this.objInputStream = objInputStream;
        this.objOutputStream = objOutputStream;

    }

    public Menu() {

    }

    public void menu() {
        try {
            String choice;
            System.out.println("1.Sign In\n2.Sign Up");
            choice = input.nextLine();
            objOutputStream.writeUTF(choice);
            objOutputStream.flush();
            switch (choice) {
                case "1" -> signIn();
                case "2" -> signUp();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    //    public void menu() throws IOException {
//        String choice;
//        System.out.println("""
//                1.Sign In
//                2.Sign Up
//                """);
//
//        choice = input.nextLine();
//        switch (choice){
//            case "1":
//                signIn();
//            case "2":
//                signUp();
//        }
//    }
    public void signIn() { // برای ورود
        String username, password;
        int i = 0;


        try {
            while (true) {
                System.out.println("Enter your username");
                username = input.nextLine();
                System.out.println("Enter your password");
                password = input.nextLine();
                objOutputStream.writeUTF(username);
                objOutputStream.flush();
                objOutputStream.writeUTF(password);
                objOutputStream.flush();
                if (!objInputStream.readBoolean()) {
                    if (i == 3) {
                        System.out.println("Forget password?\n1-Yes 2-No");
                        int dokme = input.nextInt();
                        if (dokme == 1) {
                            // do something like send password
                        } else if (dokme == 2) {
                            i = 0;
                            continue;
                        } else {
                            System.out.println("INVALID INPUT");
                            continue;
                        }
                    }
                    System.out.println("INCORRECT USERNAME OR PASSWORD");
                    i += 1;
                    continue;
                }
                System.out.println("Login in......");
                TimeUnit.SECONDS.sleep(5);
                break;

            }
        } catch (InputMismatchException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void signUp() throws IOException, InterruptedException { // برای ثبت نام

        String id, password, phoneNumber, countryCode, email;


      Jedis accountDB = new Jedis("localhost");

        while (true) {
            System.out.println("Enter your username");
            id = input.nextLine();
            // System.out.println(id);
            objOutputStream.writeUTF(id);
            objOutputStream.flush();
            if (objInputStream.readBoolean()) {
                System.out.println("THIS USERNAME HAS ALREADY EXIST");
            }
            else if (validInput.checkId(id)) {
                account.setId(id);

                break;
            }

        }

        while (true) {
            System.out.println("Enter your password : ");
            password = input.nextLine();
            if (validInput.checkPassword(password)) {
                account.setPassword(password);
                break;
            }


        }

        while (true) {
            System.out.println("Enter country code:");
            countryCode = input.nextLine();

            if (validInput.checkCountryCode(countryCode)) {
                account.setCountryCode(countryCode);
                break;
            }

        }
        while (true) {
            System.out.println("Enter Your Phone Number :");
            phoneNumber = input.nextLine();
            if (validInput.checkNumber(phoneNumber)) {
                account.setPhoneNumber(phoneNumber);
                break;
            }
        }

        while (true) {
            System.out.println("Enter your email :");
            email = input.nextLine();
            if (validInput.checkEmail(email)) {
                account.setEmail(email);
                break;
            }
        }
        objOutputStream.writeObject(account);
        objOutputStream.flush();
        System.out.println("Sign up.....");
        TimeUnit.SECONDS.sleep(5);


        // convert class account to json
        Gson gson = new Gson();

       

        //String json = gson.toJson(account);

        //save json as value in jedis
        accountDB.set(id,json);

    }

    void changeUsername() throws IOException {

        while (true) {
            System.out.println("Enter your new username");
            String newUsername = input.nextLine();
            objOutputStream.writeUTF(newUsername);
            objOutputStream.flush();
            if (objInputStream.readBoolean()) {
                System.out.println("THIS USERNAME HAS ALREADY EXIST");
            }
            else if (validInput.checkId(newUsername)) {
                account.setId(newUsername);

                break;
            }

        }


    }

    void changePassword() throws IOException {
        System.out.println("Enter your current password:");
        String currentPassword = input.nextLine();
        objOutputStream.writeUTF(currentPassword);
        objOutputStream.flush();

        /// check if the current password is correct or not which given in sign up
        ///if it was true
        System.out.println("enter your new password:");
        while (true) {
            System.out.println("Enter your password : ");
            String  newpassword = input.nextLine();
            if (validInput.checkPassword(newpassword)) {
                account.setPassword(newpassword);
                break;
            }


        }


    }
}
