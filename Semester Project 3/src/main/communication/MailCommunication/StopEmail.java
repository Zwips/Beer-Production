package communication.MailCommunication;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import javax.mail.*;
import java.util.Properties;

public class StopEmail {

	private String username = "beerproductiontest@gmail.com";
	private String password = "beer:123";
	private String smtpport = "25";
	private String port = "465";
	private String recipent = "fartillucas@gmail.com";
	private String benefactor = "beerproductiontest@gmail.com";
	private Date date = new Date();


	public void SendStopEMail(String machineName) {

		Properties props = new Properties();
		props.put("mail.smtp.user", "beerproductiontest");
		props.put("mail.smtp.host" ,"smtp.gmail.com");
		props.put("mail.smtp.port" , smtpport);
		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");

		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", port);
		props.setProperty("mail.smtp.socketFactory.port" , port);

		Session session = Session.getInstance (props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(benefactor));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recipent));
			message.setSubject("test test");
			message.setText(date+" "+ machineName+" "+"has been manually stopped");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);

		}


	}
}