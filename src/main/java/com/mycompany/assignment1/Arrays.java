/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment1;

/**
 *
 * @author Heave
 */
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import org.json.JSONObject;
import org.json.JSONArray;
public class Arrays {
    ArrayList<String> SenderAndRecipients = new ArrayList<>();
    ArrayList<String> SentMessages = new ArrayList<>();
    ArrayList<String> DisregardedMessages = new ArrayList<>();
    ArrayList<String> MessageHashes = new ArrayList<>();
    ArrayList<String> MessageIDs = new ArrayList<>();
    
    public String updateSentMessages(String recipient, String sentMessage){
        
        String SentEntry = "Message:" + sentMessage + ";Recipient:" + recipient;
        
        SentMessages.add(SentEntry);
        
        String message = "";
        
        for (int i = 0; i < SentMessages.size(); i++){
            message = message + SentMessages.get(i);
        }
        
        return(message);
    } 
    
    public void updateDisregardedMessages(String disregardMessageID, String disregardMessageHash, String disregardRecipient, String disregardMessage){
        String disregardEntry = "MessageID:" + disregardMessageID + ";MessageHash:" + disregardMessageHash + ";"
                + "Recipient:" + disregardRecipient + ";Message:" + disregardMessage;
        
        DisregardedMessages.add(disregardEntry);
    }
    public void populateSenderAndRecipients(){
        try{
            Path path = Path.of("Messages.json");
            String content = Files.readString(path);
            
            JSONArray messages = new JSONArray(content);
            
            for(int i = 0; i < messages.length(); i++){
                JSONObject obj = messages.getJSONObject(i);
                
                String sender = obj.getString("Sender");
                String recipient = obj.getString("Recipient");
                
                SenderAndRecipients.add("Sender: " + sender + ";Recipient: " + recipient);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public String LongestMessage(){
        String Longest = "";
        try{
            Path path = Path.of("Messages.json");
            String content = Files.readString(path);
            
            JSONArray messages = new JSONArray(content);
            
            for(int i = 0; i < messages.length(); i++){
                JSONObject obj = messages.getJSONObject(i);
                String current = obj.getString("Message");
                
                if (current.length() > Longest.length()){
                    Longest = current;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        
        return(Longest);
    }
    
    public String searchMessage(String MessageID){
        String returnMessage = "";
        String recipient = "";
        String message = "";
        try{
            Path path = Path.of("Messages.json");
            String content = Files.readString(path);
            
            JSONArray messages = new JSONArray(content);
            
            for (int i = 0; i < messages.length(); i++){
                JSONObject obj = messages.getJSONObject(i);
                
                long fileMessageID = obj.getLong("Message ID");
                String stringFileMessageID = String.valueOf(fileMessageID);
                
                
                if (stringFileMessageID.equals(MessageID)){
                    recipient = obj.getString("Recipient");
                    message = obj.getString("Message");
                     
                    returnMessage = "Message ID: " + MessageID + ""
                        + "\nRecipient: " + recipient + ""
                        + "\nMessage: " + message;
                    break;
                } 
            }
            
        } catch (IOException e){
            e.printStackTrace();
        }
        
        return(returnMessage);
    }
    
    
    public void populateMessageHashes(){
        try{
            Path path = Path.of("Messages.json");
            String content = Files.readString(path);
            
            JSONArray messages = new JSONArray(content);
            
            for (int i = 0; i < messages.length(); i++){
                JSONObject obj = messages.getJSONObject(i);
                
                String messageHash = obj.getString("Message Hash");
                
                MessageHashes.add(messageHash);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void populateMessageIDs(){
        try{
            Path path = Path.of("Messages.json");
            String content = Files.readString(path);
            
            JSONArray messages = new JSONArray(content);
            
            for (int i = 0; i < messages.length(); i++){
                JSONObject obj = messages.getJSONObject(i);
                
                String MessageID = obj.getString("Message ID");
                
                MessageIDs.add(MessageID);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public String SearchRecipient(String RecipientPhone){
        String returnString = "";
        
        if (RecipientPhone.matches("\\+27\\d{9}")){
            try{
                Path path = Path.of("Messages.json");
                String content = Files.readString(path);
            
                JSONArray messages = new JSONArray(content);
            
                for (int i = 0; i < messages.length(); i++){
                    JSONObject obj = messages.getJSONObject(i);
                    
                    String fileRecipientPhone = obj.getString("Recipient");
                    
                    if (fileRecipientPhone.equals(RecipientPhone)){
                        String message = obj.getString("Message");
                        
                        returnString = returnString + "\n" + message;
                    }
                }
               
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            returnString = "Error! Phone number incorrectly formatted or does not exist";
        }
        
        return(returnString);
    }
    
    public String DeleteMessage(String MessageHash){
        String message = "";
        boolean found = false;
        for(int i = 0; i < MessageHashes.size(); i++){
            if(MessageHashes.get(i).equals(MessageHash)){
                found = true;
            }
        }
        
        if (found = true){
            try{
                Path path = Path.of("Messages.json");
            
                String content = Files.readString(path);
                JSONArray messages = new JSONArray(content);
            
                for(int i = 0; i < messages.length(); i++){
                    JSONObject obj = messages.getJSONObject(i);
                
                    if(obj.getString("Message Hash").equals(MessageHash)){
                        messages.remove(i);
                        message = "Message has succesfully been deleted!";
                        break;
                    }
                }
            
                Files.writeString(path, messages.toString(4));
            } catch (IOException e){
                e.printStackTrace();
            }
        } 
        if(found = false){
            message = "Error! Message hash could not be found."
                    + "\nPlease try again.";
        }
        
        return(message);
    }
    
    
    public String compileReport(){
        String report = "---------------------------------"
                + "\nFULL MESSAGE REPORT"
                + "\n---------------------------------";
        try{
            Path path = Path.of("Messages.json");
            String content = Files.readString(path);
            
            JSONArray messages = new JSONArray(content);
            
            for(int i = 0; i < messages.length(); i++){
                JSONObject obj = messages.getJSONObject(i);
                
                report = report +
                        "\nMessage Hash: " + obj.getString("Message Hash") + ""
                        + "\nMessage ID: " + obj.getLong("Message ID") + ""
                        + "\nMessage Number: " + obj.getInt("Message Number") + ""
                        + "\nSender: " + obj.getString("Sender") + ""
                        + "\nRecipient: " + obj.getString("Recipient") + ""
                        + "\nMessage: " + obj.getString("Message") + "\n";
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return(report);
    }
    
}
