package com.PlayGroundAdv.Solar.service.mailing;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.MailingSetting;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.repositories.MailingSettingRepository;
import com.PlayGroundAdv.Solar.service.utils.TrippleDes;

@Service
public class MailingSession {

	final MailingSettingRepository mailingRepo;
	final TrippleDes td;

	public MailingSession(MailingSettingRepository mailingRepo, TrippleDes td) {
		super();
		this.mailingRepo = mailingRepo;
		this.td = td;

	}

	private Properties setProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		return props;
	}

	public MimeMessage getMailingMessage() throws ResourceNotFoundException {
		MailingSetting m = mailingRepo.findById(1)
				.orElseThrow(() -> new ResourceNotFoundException("Mailing Setting not found"));
		return new MimeMessage(getSession(m.getEmail(), m.getPassword()));
	}

	public Session getSession(String mail, String pwd) {
		Session session = Session.getInstance(setProperties(), new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mail, td.decrypt(pwd));
			}
		});
		return session;
	}
}
