
import java.io.*;
import java.util.*;

public class Functions {

    //PRE:  accepts a string as input
    //POST: returns true if string only has digits, false otherwise
    public static boolean isDigits(String str) {
        return str.matches("\\d+");
    }

    //PRE:  accepts the ArrayList of bank accoutns & an account number
    //POST: returns true if the account exists, false otherwise
    public static boolean accountExists(ArrayList<BankAccount> accounts, int acct){
        for (BankAccount accts : accounts ){
            if (accts.getAccountNumber() == acct)
                return true;
        }
        return false;
    }

    //PRE:  accepts a string and verifies that it is valid
    //POST: if string is C, S or CD return true, otherwise return false
    public static boolean validType(String t){
        if(t.equalsIgnoreCase("C") || t.equalsIgnoreCase("S") || t.equalsIgnoreCase("CD")){
            return true;
        }
        else return false;
    }

    //PRE:  accepts a string and verifies that it is valid
    //POST: if string is NEW, INTEREST, DEPOSIT, TRANSFER, WITHDRAWL return true, otherwise return false
    public static boolean validTransType(String t){

        if (t.equalsIgnoreCase("NEW") || t.equalsIgnoreCase("DEPOSIT") ||
            t.equalsIgnoreCase("INTEREST") || t.equalsIgnoreCase("WITHDRAWAL") ||
            t.equalsIgnoreCase("TRANSFER"))
            return true;

        return false;
    }

    //PRE:  accepts a string and verifies that it is a valid date
    //POST: if string contains valid information, return 1, otherwise return -1
    //      can be updated to true/false
    public static int validDate(String date) {
        try {
            String[] parts = date.split("/");
            if (parts.length != 3) return -1;
            int year = Integer.parseInt(parts[2]);
            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);

            if (year < 1900 || year > 2024) return -1;
            if (month < 1 || month > 12) return -1;
            if (day < 1 || day > 31) return -1;

            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public static void processFile(String filename, String errorLog, ArrayList<BankAccount> accounts) {
        try (
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            PrintWriter errWriter = new PrintWriter(new FileWriter(errorLog))
        ) {
            String line;
            reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                try {
                    Boolean accIsNum = isDigits(tokens[0]);
                    Boolean accTypeIsValid = validType(tokens[1]);
                    Boolean transTypeIsValid = validTransType(tokens[5]);
                    int dateIsValid = validDate(tokens[4]);
                    Boolean amountIsNum = isDigits(tokens[6]);

                    if((accIsNum == true) && (accTypeIsValid == true) && (transTypeIsValid == true) && (dateIsValid == 1) && (amountIsNum == true)){
                        System.out.println("test");
                        //processAccount(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], Integer.parseInt(tokens[6]), errorLog, accounts);
                    }
                    else{
                        System.out.println("fail");
                    }
                } catch (Exception e) {
                    errWriter.println("Parse error in record: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

    }

    public static void processAccount(int accNum, String accType, String fName, String lName, String date, String transType, int transAmount, String errorLog, ArrayList<BankAccount> accounts){
        try{
            if(transType.equalsIgnoreCase("NEW")){
                //BankAccount i = new BankAccount(accNum, accType, fName, lName, date, transAmount, 0, 0);
                //accounts.add(i);
            }
        }
        finally{}
    }

    //PRE:  none
    //POST: sorts information in accounts by account number, prints bank account information in accounts to report file
    public static void printReport(ArrayList<BankAccount> accounts, String reportFile) {
        accounts.sort(Comparator.comparing(BankAccount::getAccountNumber));
        try (PrintWriter out = new PrintWriter(new FileWriter(reportFile))) {
            out.printf("%-20s%-20s%-20s%-15s%10s%15s%n", "Account#", "Type", "First Name", "Last Name", "Balance", "Interest");
            for (BankAccount acct : accounts) {
                acct.printDetails(out);
            }
        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
    }
}
