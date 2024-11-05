package com.PlayGroundAdv.Solar.service.mailing;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.MailingSetting;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.repositories.MailingSettingRepository;

@Service
@Transactional
public class EquipmentUpdate {
	
	final MailingSession session;
	
	public EquipmentUpdate(MailingSession session) {
		
		super();
		this.session = session;
		
	}	
	
	public String mailUpdate(String libraryName, String text, Boolean nuatn) {

		try {

			String textMail = "Dear Equipment Verifier, <br/><br/>"+text+".<br/><br/>Please review and verify."
					+ "<br/><br/>Thank you.";
			InternetAddress[] address = new InternetAddress[Boolean.TRUE.equals(nuatn) ? 1 : 8];
			if (Boolean.TRUE.equals(nuatn)) {
				address[0] = new InternetAddress("dev01@nuagetechnologies-tn.com");
			} else {
				address[0] = new InternetAddress("Ken-t@advpermits.com");
				address[1] = new InternetAddress("Mindy-z@advpermits.com");
				address[2] = new InternetAddress("Sterling-p@advpermits.com");
				address[3] = new InternetAddress("Neil-g@advpermits.com");
				address[4] = new InternetAddress("Ahnaf-c@advpermits.com");
				address[5] = new InternetAddress("Rahat-c@advpermits.com");
				address[6] = new InternetAddress("Turja-d@advpermits.com");
				address[7] = new InternetAddress("Khan-n@advpermits.com");
			}
			MimeMessage message = session.getMailingMessage();
			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.setRecipients(Message.RecipientType.TO, address);
			if (Boolean.FALSE.equals(nuatn)) message.addRecipient(Message.RecipientType.BCC, new InternetAddress("dev01@nuagetechnologies-tn.com"));
			message.setSubject("Equipment Review - "+libraryName);
			Multipart multipart = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setContent(textMail, "text/html");
			multipart.addBodyPart(textBodyPart);
			message.setContent(multipart);

			Transport.send(message);
			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

}
