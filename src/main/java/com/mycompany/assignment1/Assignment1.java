/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.assignment1;

/**
 *
 * @author Heave
 */
import java.util.Scanner;
public class Assignment1 {
    public static void main(String[] args) {
        startApplication();
    }
    
    public static void startApplication() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("============================================="
                + "\nMAIN MENU"
                + "\n=============================================\n"
                + "\nWhat would you like to do:"
                + "\n[1] Create an account"
                + "\n[2] Login");

        int choice = scanner.nextInt();
        scanner.nextLine();

        handleMainMenu(choice, scanner);
    }
    
    

    public static void handleMainMenu(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                handleRegister(scanner);
                break;
            case 2:
                handleLogin(scanner);
                break;
            default:
                System.out.println("Please choose either of the given options.");
        }
    }
    
    public static void handleRegister(Scanner scanner) {
        // Global variables for value validity
        boolean userValid;
        boolean passwordValid;
        boolean phoneValid;

        // Initializing Name and surname
        String Name = "";
        String Surname = "";

        // creates a new login object
        Login account1 = new Login();

        // Asks for name
        System.out.println("Enter your first name: ");
        Name = scanner.nextLine();

        // Asks for surname
        System.out.println("Enter your surname:");
        Surname = scanner.nextLine();

        // Sets Name and Surname
        account1.setNameAndSurname(Name, Surname);

        // Asks for username
        System.out.println("Enter your username: ");
        String Username = scanner.nextLine();
        userValid = account1.checkUserName(Username);

        // Looping for when username is incorrectly formatted
        if (userValid == false){
            while (account1.checkUserName(Username) == false){
                System.out.println(account1.registerUser(false, true, true));
                System.out.println("Enter your username: ");
                Username = scanner.nextLine();
                userValid = account1.checkUserName(Username);
            }
        }

        // Asks for password
        System.out.println("Enter your password: ");
        String Password = scanner.nextLine();
        passwordValid = account1.checkPasswordComplexity(Password);

        // Looping for when password is incorrectly formatted
        if (passwordValid == false){
            while (account1.checkPasswordComplexity(Password) == false){
                System.out.println(account1.registerUser(true, false, true));
                System.out.println("Enter your password: ");
                Password = scanner.nextLine();
                passwordValid = account1.checkPasswordComplexity(Password);
            }
        }

        // Asks for phone number
        System.out.println("Enter your phone number: ");
        String Phone = scanner.nextLine();
        phoneValid = account1.checkCellPhoneNumber(Phone);

        // Looping for when phone is incorrectly formatted
        if (phoneValid == false){
            while (account1.checkCellPhoneNumber(Phone) == false){
                System.out.println(account1.registerUser(true, true, false));
                System.out.println("Enter your phone number: ");
                Phone = scanner.nextLine();
                phoneValid = account1.checkCellPhoneNumber(Phone);
            }
        }

        // Registers user
        if (account1.writeUser() == true){
            System.out.println(account1.registerUser(userValid, passwordValid, phoneValid));
        } else {
            System.out.println("An error occured, please try again.");
        }
    }
    
    public static void handleLogin(Scanner scanner) {
        
        System.out.println("Enter your username: ");
        String loginUsername = scanner.nextLine();

        System.out.println("Enter your password: ");
        String loginPassword = scanner.nextLine();

        Login account1 = new Login();

        boolean match = account1.loginUser(loginUsername, loginPassword);

        String NameAndSurname = "";

        if(match == true){
            NameAndSurname = account1.getNameAndSurname(loginUsername, loginPassword);
            //Message message = new Message();
            
        }

        System.out.println(account1.returnLoginStatus(match, NameAndSurname));

        if (match == true){
            handleMessagingMenu(scanner, loginUsername);
        }
    }
    
    public static void handleMessagingMenu(Scanner scanner, String loginUsername) {
        String InformationMessage = "";
        String AllMessages = "";
        String MessageID = "";
        int messageChoice = 0;
        
        while (messageChoice != 4){
            System.out.println("\nWould you like to:"
                + "\n[1] Send messages"
                + "\n[2] Show recently sent messages"
                + "\n[3] Stored Messages"
                + "\n[4] Quit");
            
            messageChoice = scanner.nextInt();
            scanner.nextLine();
            
            switch (messageChoice){
                case 1:
                    //Asks for number of messages
                    int NumMessages = 0;
                    System.out.println("How many messages would you like to send:");
                    NumMessages = scanner.nextInt();
                    scanner.nextLine();
            
                    Message message = new Message();
                        
                    for (int i = 0; i < NumMessages; i++){
                        message.resetMessage();
                                
                        //Asks for recipients phone number
                        System.out.println("What is the phone number of the person you would like"
                            + " to send the message to:");
                        String RecipientPhone = scanner.nextLine();
                                
                        message.checkRecipientCell(RecipientPhone);
                        boolean RecipientPhoneValid = false;
                        RecipientPhoneValid = message.PhoneValid;
                        
                        if (RecipientPhoneValid == false){
                            while (RecipientPhoneValid == false){
                                System.out.println(message.checkRecipientCell(RecipientPhone));
                                System.out.println("What is the phone number of the person you would like"
                                    + " to send the message to:");
                                RecipientPhone = scanner.nextLine();
                                message.checkRecipientCell(RecipientPhone);
                                RecipientPhoneValid = message.PhoneValid;
                            }
                        } 
                        boolean messageValid = false;
                        
                        if (RecipientPhoneValid == true){
                            System.out.println(message.checkRecipientCell(RecipientPhone));
                            System.out.println("What would you like to say:");
                            String text = scanner.nextLine();
                            System.out.println(message.checkMessage(text));
                            messageValid = message.setMessageValid();
                        
                            while (message.setMessageValid() == false){
                                System.out.println("What would you like to say:");
                                text = scanner.nextLine();
                                System.out.println(message.checkMessage(text));
                                messageValid = message.setMessageValid();
                            }
                         
                                    
                            if (messageValid == true){
                                message.getNumOfMessages(i);
                                System.out.println("What would you like to do with the message:"
                                    + "\n[1] Send Message"
                                    + "\n[2] Disregard Message"
                                    + "\n[3] Store Message to Send Later");
                            
                                int sendChoice = scanner.nextInt();
                                scanner.nextLine();
                                String MessageHash = "";
                                Arrays arrays = new Arrays();
                                switch (sendChoice){
                                    case 1:
                                        MessageID = message.genrateMessageID();
                                        String Cell = message.returnRecipientCell();
                            
                                                
                                        if (message.checkMessageID() == true){
                                            message.getFirstAndLastWord();
                                            MessageHash = message.createMessageHash();
                                            InformationMessage = InformationMessage + "\n" + "Message ID: " +
                                                MessageID + "\nMessage Hash: " + MessageHash +
                                                "\nRecipient: " + Cell + "\nMessage: " 
                                                + message.returnMessage() + "\n";
                                                    
                                            
                                            
                                            arrays.updateSentMessages(Cell, text);
                                            System.out.println(InformationMessage);
                                        }
                                        System.out.println(message.SentMessage(1));
                                        break;
                                    case 2:
                                        System.out.println("Press 0 to delete the messages");
                                        int delete = scanner.nextInt();
                                        scanner.nextLine();
                                        
                                        if (delete == 0){
                                            arrays.updateDisregardedMessages(MessageID, MessageHash, RecipientPhone, text);
                                            System.out.println("Message has successfully been deleted");
                                        } else {
                                            System.out.println("Message was not deleted");
                                            break;
                                        }
                                        
                                        
                                        break;
                                    case 3:
                                        String messageHash;
                                        String messageID = message.genrateMessageID();
                                        message.getFirstAndLastWord();
                                        messageHash = message.createMessageHash();
                                        message.writeMessage(loginUsername);
                                        System.out.println(message.SentMessage(3));
                                        arrays.updateSentMessages(RecipientPhone, text);
                                        break;
                                    default:
                                        System.out.println(message.SentMessage(4));
                                        NumMessages--;
                                        break;
                                }
                            }
                        }   
                           
                    }
                    System.out.println("==========================================================");
                    System.out.println("Total number of messages sent: " + message.returnTotalMessages());
                    System.out.println("==========================================================");

                    break;
                            
                case 2:
                    System.out.println("Coming Soon");
                    break;
                case 3:
                    System.out.println("Would you like to:"
                            + "\n[1] View all stored messages"
                            + "\n[2] View longest message"
                            + "\n[3] Search for a message using ID"
                            + "\n[4] View all messages sent to a particular recipient"
                            + "\n[5] Delete a message"
                            + "\n[6] View report");
                    
                    int StoredChoice = scanner.nextInt();
                    scanner.nextLine();
                    
                    Arrays arrays = new Arrays();
                    
                    switch(StoredChoice){
                        case 1:
                            arrays.populateSenderAndRecipients();
                            System.out.println(arrays.SenderAndRecipients);
                            break;
                        case 2:
                            System.out.println("The longest message is:");
                            System.out.println(arrays.LongestMessage());
                            break;
                        case 3:
                            System.out.println("What is the message ID of the message you wish to search:");
                            long searchID = scanner.nextLong();
                            
                            System.out.println(arrays.searchMessage(String.valueOf(searchID)));
                            break;
                        case 4:
                            System.out.println("What is the phone number of the recipient you wish to search:");
                            String recipientPhone = scanner.nextLine();
                            
                            System.out.println(arrays.SearchRecipient(recipientPhone));
                            break;
                        case 5:
                            System.out.println("Please provide the message hash of the message you would like to delete:");
                            String messageHash = scanner.nextLine();
                            
                            System.out.println(arrays.DeleteMessage(messageHash));
                            break;
                        case 6:
                            System.out.println(arrays.compileReport());
                            break;
                        default:
                            System.out.println("Error! Please select one of the provided options");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    System.exit(0);            
                    break;
                default:
                    System.out.println("Error! Please select one of the given options!");            
                    break;
            }
        }
        
    }
    
    
}
