/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment1;

/**
 *
 * @author Heave
 */
import java.util.Random;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
public class Message {
    int NumOfMessages = 0;
    String RecipientPhone = "";
    String Sender = "";
    long MessageID;
    String MessageIDString = "";
    String MessageHash = "";
    String Text = "";
    String FirstWord = "";
    String LastWord = "";
    String Messages = "";
    String AllMessages = "";
    
    //PUBLIC BOOLEAN VARIABLES
    boolean PhoneValid = false;
    boolean MessageValid = false;
    
    public void getNumOfMessages(int numOfMessages){
        NumOfMessages = numOfMessages;
    }
    

    public String checkMessage(String Message){
        Text = Message;
        String lengthMessage = "";
        int exceedNumber = 0;
        if (Text.length() <= 250){
            MessageValid = true;
            lengthMessage = "Message ready to send!";
        } else {
            MessageValid = false;
            exceedNumber = Text.length() - 250;
            lengthMessage = "Message exceeds 250 characters by " + exceedNumber + "; Please reduce the size";
        }
        
        return(lengthMessage);
    }
    
    public boolean setMessageValid(){
        return(MessageValid);
    }
    
    public String checkRecipientCell(String Phone){
        RecipientPhone = Phone;
        String CellMessage = "";
        //Checks if phone number is correctly formatted
        if (RecipientPhone.matches("\\+27\\d{9}")){
            PhoneValid = true;
        } else {
            PhoneValid = false;
        }
        
        if (PhoneValid == true){
            CellMessage = "\nRecipient's phone number has successfully been captured!";
        } else if (PhoneValid == false){
            CellMessage = "\nError! "
                    + "\nPlease ensure that the number you are entering"
                    + "\nis no more than 10 characters and contains an international code";
        }
        
        return(CellMessage);
    }
    
    public String returnRecipientCell(){
        return(RecipientPhone);
    }
    
    public String returnMessage(){
        return(Text);
    }
    
    public String genrateMessageID(){
        Random random = new Random();
        MessageID = 1000000000L + (long)(random.nextDouble()*9000000000L); 
        MessageIDString = String.valueOf(MessageID);
        
        return(MessageIDString);
    }
    
    public boolean checkMessageID(){
        boolean MessageIDValid = false;
        
        if (MessageIDString.length() == 10){
            MessageIDValid = true;
        } else {
            MessageIDValid = false;
        }
        
        return(MessageIDValid);
    }
    
    public void getFirstAndLastWord(){
        String[] words = Text.split(" ");
        FirstWord = words[0];
        LastWord = words[words.length -1].replaceAll("[^a-zA-Z0-9]", "");
        
    }
    public String createMessageHash(){
       MessageHash = MessageIDString.substring(0, 2) + ":" + NumOfMessages + ":" + 
              FirstWord.toUpperCase() + LastWord.toUpperCase();
       
       return(MessageHash);
    }
    
    public void writeMessage(String Sender){
        try{
            Path path = Path.of("Messages.json");
            
            String content = Files.readString(path);
            JSONArray messages = new JSONArray(content);
            
            JSONObject FileMessage = new JSONObject();
            
            FileMessage.put("Message Number", NumOfMessages);
            FileMessage.put("Message ID", MessageID);
            FileMessage.put("Message Hash", MessageHash);
            FileMessage.put("Message", Text);
            FileMessage.put("Recipient", RecipientPhone);
            FileMessage.put("Sender", Sender);
            
            messages.put(FileMessage);
            Files.writeString(path, messages.toString(4));
        }catch (IOException e){
            e.printStackTrace();
        }
        
    }
    
    public void getMessages(String InformationMessage){
        Messages = InformationMessage;
    }
    
    public int returnTotalMessages(){
        return(NumOfMessages + 1);
    }
    
    public String CompileMessages(){        
        AllMessages = AllMessages + Messages;
        
        return(AllMessages);
    }
   
    
    public String SentMessage(int Choice){
        String Confirmation = "";
        switch (Choice){
            case 1:
                Confirmation = "Message successfully sent!";
                break;
            case 2:
                Confirmation = "Message has been successfully deleted!";
                break;
            case 3:
                Confirmation = "Message has successfully been stored!";
                break;
            default:
                Confirmation = "Error, please select one of the given option";
                break;
        }
        
        return(Confirmation);
    }
    
    public void resetMessage(){
        String RecipientPhone = "";
        long MessageID;
        String MessageIDString = "";
        String MessageHash = "";
        String Text = "";
        String FirstWord = "";
        String LastWord = "";
    }
}
