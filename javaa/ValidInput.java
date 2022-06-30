package javaa;// package src.main.java;

public class ValidInput {

    public boolean checkId(String id)  {
        if (id.matches("^[\\w]{6,}+$")) {
            System.out.println("Id is accepted");
            return true;
        } else {
            System.out.println("invalid id");
            return false;
        }

    }



    public boolean checkEmail(String email) {
        if (email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
            System.out.println("Email address accepted");
            return true;
        } else {
            System.out.println("email address is not valid");///???
            return false;
        }

    }




    public boolean checkNumber(String number) {

        if (number.matches("\\d{10}")) {
            System.out.println("phone Number accepted");
            return true;
        } else {
            System.out.println("invalid phone number");
            return false;
        }

    }


    public boolean checkCountryCode
            (String countryCode) {
        if (countryCode.matches("^\\+\\d+")) {

            return true;
        } else {
            System.out.println("country code is not valid");
            return false;
        }
    }


    public boolean checkPassword(String password) {
        if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            System.out.println("Password accepted");
            return true;
        } else {
            System.out.println("invalid password");
            return false;
        }

    }




    }

