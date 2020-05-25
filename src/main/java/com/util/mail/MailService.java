package com.util.mail;

import com.sun.mail.smtp.SMTPTransport;
import com.util.propertiesloader.MyProperties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
//TODO Use propertiesReader
public class MailService {

    private static final String SMTP_SERVER = "smtp.gmail.com.";

    private static final String USERNAME = MyProperties.get("EMAILADDRESS");

    private static final String PASSWORD = MyProperties.get("PASSWORD");

    private static final String EMAIL_FROM = MyProperties.get("EMAILADDRESS");

    public void sendMail(String emailTo, String subject, String emailText) throws MessagingException {

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.ssl.enable", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(EMAIL_FROM));

        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(emailTo, false));

        msg.setSubject(subject);

        msg.setText(emailText);

        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

        t.connect(SMTP_SERVER, USERNAME, PASSWORD);

        t.sendMessage(msg, msg.getAllRecipients());

        t.close();
    }
}
