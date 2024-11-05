package com.PlayGroundAdv.Solar.service.mailing;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.service.project.GetProjectDetailsUtils;
import com.PlayGroundAdv.Solar.service.utils.TrippleDes;

@Service
public class ArchiveMailing {
	final MailingSession session;
	final GetProjectDetailsUtils projectUtils;
	final PathRepository pathRepo;
	final TrippleDes td;

	public ArchiveMailing(MailingSession session, GetProjectDetailsUtils projectUtils, PathRepository pathRepo,
			TrippleDes td) {
		super();
		this.session = session;
		this.projectUtils = projectUtils;
		this.pathRepo = pathRepo;
		this.td = td;
	}

	public void archiveNotice(PermitEntity project) {
		try {
			String server = pathRepo.findURLPath();
			if (!server.equals("localhost")) {
				String url = server.equals("localhost") ? "http://localhost:4200"
						: server.equals("sandbox") ? "http://144.217.161.234:8181"
								: "https://portal.advancedsolarpermits.com";
				String cryptId = (td.encrypt(project.getId() + ""));
				String contentMail = "Hello,<br><br>" + "The project " + projectUtils.getProjectName(project)
						+ " will be archived after 72h.<br>"
						+ "All the project files will be moved to ADV \"Clients & Jobs\" drive folder.<br>"
						+ "Once the project is archived no actions can be done on the project except making a copy of it.<br>"
						+ "If you would like to delay the project archiving for 7 days, " + "please click <a href=\"" + url
						+ "/#/auth/delayArchiving/" + cryptId + "\">here</a>.<br><br>" + "Thank you,";
				MimeMessage message = session.getMailingMessage();
				message.setFrom(new InternetAddress("info@advpermits.com"));
				InternetAddress[] address = new InternetAddress[server.equals("production") ? 2 : 1];
				address[0] = new InternetAddress("dev01@nuagetechnologies-tn.com");
				if (server.equals("production")) {
					address[1] = new InternetAddress("portal-archive@advpermits.com");
				}
				message.setRecipients(Message.RecipientType.TO, address);
				message.setSubject("The project " + projectUtils.getProjectName(project) + " will be archived");
				Multipart multipart = new MimeMultipart();
				MimeBodyPart textBodyPart = new MimeBodyPart();
				textBodyPart.setContent(contentMail, "text/html; charset=utf-8");
				multipart.addBodyPart(textBodyPart);
				message.setContent(multipart);
				// Send message
				Transport.send(message);
			}
		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}
}
