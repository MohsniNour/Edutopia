/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author rayen
 */
public class Mail {

    public static void sendMail(String receiver, String obj, String core) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.googlemail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "rayen.benrhouma@esprit.tn";
        String password = "cr77neww";
        
        Session session = Session.getInstance(properties, new Authenticator() {
           @Override
           protected PasswordAuthentication getPasswordAuthentication(){
               return new javax.mail.PasswordAuthentication(myAccountEmail, password);
           }
            
        });
        
        Message message = prepareMessage(session, myAccountEmail, receiver, obj, core);
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String receiver, String obj, String core) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject(obj);
            message.setText(core);
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
