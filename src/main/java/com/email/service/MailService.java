package com.email.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@SpringBootApplication
public class MailService implements CommandLineRunner {
@Autowired 
private JavaMailSender javaMailSender;

public static void main(String[] args) {
    SpringApplication.run(MailService.class, args);
}


@Override 
public void run(String... args) throws MessagingException,IOException {
  
    System.out.println("Sending Email...");
  
    sendEmailWithoutAttachment(); 
    //sendEmailWithAttachment();
  
    System.out.println("Email Sent...!");
 }
  
  void sendEmailWithoutAttachment() {
  
  SimpleMailMessage message = new SimpleMailMessage();
  message.setTo("receiver@mail.com");
  message.setSubject("Testing from Spring Boot");
  message.setText("Hello World \n Spring Boot Email");
  
  javaMailSender.send(message);
  
  }
  
  void sendEmailWithAttachment() throws MessagingException, IOException {
  
  MimeMessage message = javaMailSender.createMimeMessage(); MimeMessageHelper
  helper = new MimeMessageHelper(message, true);
  helper.setTo("receiver@mail.com");
  helper.setSubject("Testing from Spring Boot");
  
  helper.setText("<B>Image attached in the email..!</B>", true);
  helper.addAttachment("Spring.png", new ClassPathResource("ImageToUpload.png"));
  
  javaMailSender.send(message);
  
  }

}
