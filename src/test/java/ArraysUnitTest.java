/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Heave
 */
import com.mycompany.assignment1.Arrays;
public class ArraysUnitTest {
    Arrays arrays = new Arrays();
    public ArraysUnitTest() {
    }
    
    @Test
    public void SentMessages(){
        assertEquals("Message:Did you get the cake?;Recipient:+27834557896", arrays.updateSentMessages("+27834557896","Did you get the cake?"));
    }
    
    @Test
    public void LongestMessage(){
        assertEquals("Where are you? You are late! I have asked you to be on time.", arrays.LongestMessage());
    }
    
    @Test
    public void SearchMessageID(){
        assertEquals("Message ID: 2628191004\nRecipient: +27838884567\nMessage: Where are you? You are late! I have asked you to be on time.",
                arrays.searchMessage("2628191004"));
    }
    
    @Test
    public void SearchRecipient(){
        assertEquals("\nWhere are you? You are late! I have asked you to be on time.\nOk, I am leaving without you.",
                arrays.SearchRecipient("+27838884567"));
    }
    
    @Test
    public void DeleteMessage(){
        assertEquals("Message has succesfully been deleted!", arrays.DeleteMessage("26:1:WHERETIME"));
    }
    
    @Test
    public void FullReport(){
        assertEquals("---------------------------------\n" 
                + "FULL MESSAGE REPORT\n"
                + "---------------------------------"
                + "\nMessage Hash: 26:1:WHERETIME"
                + "\nMessage ID: 2628191004"
                + "\nMessage Number: 1"
                + "\nSender: kvdb_"
                + "\nRecipient: +27838884567"
                + "\nMessage: Where are you? You are late! I have asked you to be on time."
                + "\n"
                + "\nMessage Hash: 73:1:OK,YOU"
                + "\nMessage ID: 7340779410"
                + "\nMessage Number: 1"
                + "\nSender: kvdb_"
                + "\nRecipient: +27838884567"
                + "\nMessage: Ok, I am leaving without you.\n", arrays.compileReport());
    }
    
    
}
