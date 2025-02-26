package myKcc.com.Service;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void send(SimpleMailMessage email){
        mailSender.send(email);
    }

    public void sendRegistration(String email, String parishName){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Registration confirmed");
            message.setText("You have been add as member of " + parishName + " " + "parish");
            mailSender.send(message);
        }catch (MailException e){
            System.err.println("Error sending an Email: " + e.getMessage());
        }
    }

    public void sendPaymentNotification(String email, String messages){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Missing Payment notification");
            message.setText("Peace!, you forget to pay your monthly church membership. " + " " + messages );
            mailSender.send(message);
        }catch (MailException e){
            System.err.println("Error sending an Email: " + e.getMessage());
        }
    }



    public void sendConfirmationCode(String email, BigDecimal amount){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Thank you for your church membership payment");
            message.setText("Your Payment amount: " + "$"+ amount);
            mailSender.send(message);
        }catch (MailException e){
            System.err.println("Error sending email: " + e.getMessage());
        }
    }

}
