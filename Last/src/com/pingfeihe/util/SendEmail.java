package com.pingfeihe.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class SendEmail {

	private static String account;
	private static String password;
	private static Properties properties;
	
	static {
		try {
			properties = new Properties();
			properties.load(SendEmail.class.getResourceAsStream("/mail.properties"));
			account = properties.getProperty("account");
		    password  = properties.getProperty("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void send(String email,String msg){
		Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
        try {
        	MimeMessage message = createMimeMessage(session, account, email,msg);
        	
        	Transport transport = session.getTransport();
        	
        	transport.connect(account, password);
        	
        	transport.sendMessage(message, message.getAllRecipients());
        	
        	transport.close();
			
		} catch (Exception e) {
		}
	}
	
	public MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String msg) throws Exception {
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(sendMail, "账户安全中心", "UTF-8"));

        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, receiveMail, "UTF-8"));

        message.setSubject("找回密码", "UTF-8");

        message.setContent("你的密码是：\n"+msg, "text/html;charset=UTF-8");

        message.setSentDate(new Date());

        message.saveChanges();

        return message;
    }
}
