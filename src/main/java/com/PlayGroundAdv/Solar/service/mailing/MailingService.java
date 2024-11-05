package com.PlayGroundAdv.Solar.service.mailing;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.transaction.Transactional;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.entity.AccountingsEntity;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.ContractorInformationEntity;
import com.PlayGroundAdv.Solar.entity.MissingSheetsLogEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.users.UserLicSectionsEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ProjectEmailModel;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.project.AccountingsRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.ContractorInformationRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserLicSectionRepository;
import com.PlayGroundAdv.Solar.service.export_project.ExportProjectSvcService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class MailingService {

	final ExportProjectSvcService exportProjectSvcService;
	final CheckValueTypesService checkValueTypesService;
	final PermitRepository permitRepo;
	final AuthenticationRepository userRepo;
	final ContractorInformationRepository contractorRepo;
	final MailingSession session;
	final PathRepository pathRepo;
	final AccountingsRepository accountingsRepo;
	final UserLicSectionRepository userLicSRepo;

	public MailingService(ExportProjectSvcService exportProjectSvcService,
			CheckValueTypesService checkValueTypesService, PermitRepository permitRepo,
			AuthenticationRepository userRepo, ContractorInformationRepository contractorRepo,
			MailingSession session, PathRepository pathRepo, AccountingsRepository accountingsRepo,
			UserLicSectionRepository userLicSRepo) {
		super();
		this.exportProjectSvcService = exportProjectSvcService;
		this.checkValueTypesService = checkValueTypesService;
		this.permitRepo = permitRepo;
		this.userRepo = userRepo;
		this.contractorRepo = contractorRepo;
		this.session = session;
		this.pathRepo = pathRepo;
		this.accountingsRepo = accountingsRepo;
		this.userLicSRepo = userLicSRepo;
	}

	

	public String getfilesPath() {
		try {
			return pathRepo.findFilePath();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "";
		}
	}

	public String sendingMail(String dest, String objectMail, String textMail) {

		try {

			MimeMessage message = session.getMailingMessage();
			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));
			message.setSubject(objectMail);
			message.setText(textMail);

			// Send message
			Transport.send(message);
			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	// F.B CR-3907 2021-09-22

	public String sendingMailVerification(String dest) {

		try {
			String randomCode = RandomStringUtils.randomAlphanumeric(10);
			MimeMessage message = session.getMailingMessage();
//			message.setFrom(new InternetAddress("info@advpermits.com"));
                        message.setFrom(new InternetAddress("artangente00@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));
//			message.setRecipients(Message.RecipientType.BCC, "dev01@nuagetechnologies-tn.com");
                        message.setRecipients(Message.RecipientType.BCC, "artangente00@gmail.com");
			if (!checkValueTypesService.contains(dest, "nuagetechnologies")) {
//				message.addRecipient(Message.RecipientType.CC, new InternetAddress("Portal@advpermits.com"));
                                message.addRecipient(Message.RecipientType.CC, new InternetAddress("artangente00@gmail.com"));
			}
			message.setSubject("Verification code");
			String contentMail = "Hello,<br><br>" + "Here is your unique code: " + randomCode + ".<br>"
					+ "Enter this code to confirm your identity.<br><br>" + "ADV Team,";

			Multipart multipart = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setContent(contentMail, "text/html");
			multipart.addBodyPart(textBodyPart);
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			StringBuilder strb = new StringBuilder(randomCode);
			randomCode = strb.reverse().toString();
			return "nh136" + randomCode;

		} catch (Exception mex) {
			mex.printStackTrace();
			return "It seems that there was a technical problem, please try later.";
		}

	}

	public String sendingRFIMail(String dest, String objectMail, String textMail, Long idPermit) {
		try {

			MimeMessage message = session.getMailingMessage();
			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));
			message.setSubject(objectMail);
			message.setText(textMail);

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(textMail, "text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = getfilesPath() + "RFISheet/RFISheet-" + idPermit + ".xlsx";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("RFISheet-" + idPermit + ".xlsx");
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);
			Transport.send(message);
			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	public String sendingSubmitMailNUATN(String objectMail, String textMail, Long idPermit, Long idUser,
			String projectname, String state) throws AddressException, ResourceNotFoundException {

		try {
			MimeMessage message = session.getMailingMessage();
			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.setRecipients(Message.RecipientType.TO, "dev01@nuagetechnologies-tn.com");
			message.setSubject(objectMail);

			// CR-2332
			String pathresult = exportProjectSvcService.generateProjectScv(idPermit, idUser);
			String result = getfilesPath() + pathresult;
			Multipart multipart = new MimeMultipart();
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(textMail, "text/html");
			multipart.addBodyPart(messageBodyPart);

			if (checkValueTypesService.NotEquals(pathresult, "error")) {
				MimeBodyPart attachmentBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(result); // ex : "D:\\test.pdf"
				attachmentBodyPart.setDataHandler(new DataHandler(source));
				PermitEntity permit = permitRepo.findById(idPermit)
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found:" + idPermit));
				AuthentificationEntity userEntity = userRepo.findById(idUser)
						.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idUser));
				if (checkValueTypesService.NotEquals(permit.getProjectName(), "")) {
					attachmentBodyPart.setFileName(permit.getProjectName() + "_" + permit.getDateOfSubmitPermit() + "_"
							+ userEntity.getFirstName() + " " + userEntity.getLastName() + ".xls");
				} else {
					attachmentBodyPart.setFileName(permit.getHomeOwnLastName() + ", " + permit.getHomeOwnName() + "_"
							+ permit.getDateOfSubmitPermit() + "_" + userEntity.getFirstName() + " "
							+ userEntity.getLastName() + ".xls");

				}
				multipart.addBodyPart(attachmentBodyPart); // add the attachement part
				message.setContent(multipart);
			}

			// Send message
			Transport.send(message);

			AuthentificationEntity user = userRepo.findById(idUser).orElse(new AuthentificationEntity());
			PermitEntity permit = permitRepo.findById(idPermit).orElse(new PermitEntity());
			ContractorInformationEntity contractor = contractorRepo.findByAuthentificationEntityId(idUser);

			if (!Boolean.TRUE.equals(permit.getSubmittedByContractor())
					&& user.getRoleEntity().getDescription().equals("Contractor")) {
				sendSubmittionToContractor(objectMail, user, contractor, permit, projectname, pathresult, state);
			}
			return "sent";

		} catch (MessagingException mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	public String sendingSubmitMail(String objectMail, String textMail, Long idPermit, Long idUser, String projectname, String state)
			throws AddressException, ResourceNotFoundException {

		try {
			MimeMessage message = session.getMailingMessage();
			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.setSubject(objectMail);

			AuthentificationEntity user = userRepo.findById(idUser).orElse(new AuthentificationEntity());
			PermitEntity permit = permitRepo.findById(idPermit).orElse(new PermitEntity());

			InternetAddress[] address = new InternetAddress[2];
			address[0] = new InternetAddress("Portal@advpermits.com");
			address[1] = new InternetAddress("dev01@nuagetechnologies-tn.com");
			message.setRecipients(Message.RecipientType.TO, address);

			// CR-2332
			String pathresult = exportProjectSvcService.generateProjectScv(idPermit, idUser);
			String result = getfilesPath() + pathresult;
			Multipart multipart = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setContent(textMail, "text/html");
			multipart.addBodyPart(textBodyPart);
			try {
				if (!checkValueTypesService.contains(pathresult, "error")) {
					MimeBodyPart attachmentBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(result); // ex : "D:\\test.pdf"
					attachmentBodyPart.setDataHandler(new DataHandler(source));
					if (checkValueTypesService.NotEquals(permit.getProjectName(), "")) {
						attachmentBodyPart.setFileName(permit.getProjectName() + "_" + permit.getDateOfSubmitPermit()
								+ "_" + user.getFirstName() + " " + user.getLastName() + ".xls");
					} else {
						attachmentBodyPart.setFileName(permit.getHomeOwnLastName() + ", " + permit.getHomeOwnName()
								+ "_" + permit.getDateOfSubmitPermit() + "_" + user.getFirstName() + " "
								+ user.getLastName() + ".xls");
					}
					multipart.addBodyPart(attachmentBodyPart); // add the attachement part
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			message.setContent(multipart);
			// Send message
			Transport.send(message);
			// Send Contractor message
			if (!Boolean.TRUE.equals(permit.getSubmittedByContractor())
					&& user.getRoleEntity().getDescription().equals("Contractor")
					&& user.getId().equals(permit.getAuthentificationEntity().getId())) {
				ContractorInformationEntity contractor = contractorRepo.findByAuthentificationEntityId(idUser);
				sendSubmittionToContractor(objectMail, user, contractor, permit, projectname, pathresult, state);
			}

			return "sent";

		} catch (MessagingException mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	private void sendSubmittionToContractor(String objectMail, AuthentificationEntity user,
			ContractorInformationEntity contractor, PermitEntity permit, String projectname, String pathresult, String state)
			throws ResourceNotFoundException {
		try {
			UserLicSectionsEntity userLicSectionList = userLicSRepo
					.findFirstByAuthentificationEntityIdAndContractorLicenceState(user.getId(), state);
			
			MimeMessage message = session.getMailingMessage();
			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.setSubject(objectMail);
			String textMail = "Good Day,<br><br>" + "This is a copy of your portal submission in excel for "
					+ projectname + ".<br><br>"
					+ "All the values you selected or entered within the portal are attached.<br><br>"
					+ "Nothing for you to do with this, but it is a record of what you have provided, so we recommend saving it to your files.<br><br>"
					+ "ADV Team,";

			message.setRecipients(Message.RecipientType.TO, permit.getAuthentificationEntity().getEmail());

			if (!checkValueTypesService.contains(user.getEmail(), "nuagetechnologies-tn"))
				message.addRecipient(Message.RecipientType.CC, new InternetAddress("projects@advpermits.com"));
			if (checkValueTypesService.isStringNotEmpty(contractor.getContactEmail()) && checkValueTypesService
					.NotEquals(permit.getAuthentificationEntity().getEmail(), contractor.getContactEmail()))
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(contractor.getContactEmail()));
			if (checkValueTypesService.isStringNotEmpty(contractor.getSecondContactEmail()) && checkValueTypesService
					.NotEquals(permit.getAuthentificationEntity().getEmail(), contractor.getSecondContactEmail()))
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(contractor.getSecondContactEmail()));
			if (checkValueTypesService.isStringNotEmpty(contractor.getThirdContactEmail()) && checkValueTypesService
					.NotEquals(permit.getAuthentificationEntity().getEmail(), contractor.getThirdContactEmail()))
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(contractor.getThirdContactEmail()));
			if(userLicSectionList != null) {
				if (checkValueTypesService.isStringNotEmpty(userLicSectionList.getAdditionalEmail1()))
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(userLicSectionList.getAdditionalEmail1()));
				if (checkValueTypesService.isStringNotEmpty(userLicSectionList.getAdditionalEmail2()))
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(userLicSectionList.getAdditionalEmail2()));
				if (checkValueTypesService.isStringNotEmpty(userLicSectionList.getAdditionalEmail3()))
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(userLicSectionList.getAdditionalEmail3()));
				if (checkValueTypesService.isStringNotEmpty(userLicSectionList.getAdditionalEmail4()))
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(userLicSectionList.getAdditionalEmail4()));
			}

			Multipart multipart = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setContent(textMail, "text/html");
			multipart.addBodyPart(textBodyPart);

			String result = getfilesPath() + pathresult;
			try {
				if (!checkValueTypesService.contains(pathresult, "error")) {
					MimeBodyPart attachmentBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(result); // ex : "D:\\test.pdf"
					attachmentBodyPart.setDataHandler(new DataHandler(source));
					if (checkValueTypesService.NotEquals(permit.getProjectName(), "")) {
						attachmentBodyPart.setFileName(permit.getProjectName() + "_" + permit.getDateOfSubmitPermit()
								+ "_" + user.getFirstName() + " " + user.getLastName() + ".xls");
					} else {
						attachmentBodyPart.setFileName(permit.getHomeOwnLastName() + ", " + permit.getHomeOwnName()
								+ "_" + permit.getDateOfSubmitPermit() + "_" + user.getFirstName() + " "
								+ user.getLastName() + ".xls");
					}
					multipart.addBodyPart(attachmentBodyPart); // add the attachement part
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			permit.setSubmittedByContractor(true);
			permitRepo.save(permit);
			message.setContent(multipart);
			// Send message
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	public String sendingNUATNRegistrationMail(String objectMail, String textMail) throws AddressException {
		InternetAddress[] address = new InternetAddress[1];
		address[0] = new InternetAddress("dev01@nuagetechnologies-tn.com");

		try {
			MimeMessage message = session.getMailingMessage();
			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.setRecipients(Message.RecipientType.TO, address);
			message.setSubject(objectMail);
			message.setText(textMail);

			// Send message
			Transport.send(message);
			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	public String sendingRegistrationMail(String objectMail, String textMail) throws AddressException {
		InternetAddress[] address = new InternetAddress[2];
		address[0] = new InternetAddress("Portal@advpermits.com");
		address[1] = new InternetAddress("dev01@nuagetechnologies-tn.com");

		try {
			MimeMessage message = session.getMailingMessage();
			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.setRecipients(Message.RecipientType.TO, address);
			message.setSubject(objectMail);
			message.setText(textMail);

			// Send message
			Transport.send(message);
			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	public String sendingMailMultipleRecievers(List<String> susers, String objectMail, String textMail)
			throws AddressException {
		InternetAddress[] address = null;
		if (susers != null) {
			address = new InternetAddress[susers.size()];
			for (int i = 0; i < susers.size(); i++) {
				if (susers.get(i) != null && !(susers.get(i).contains("nuagetechnologies-tn.com"))
						&& checkValueTypesService.NotEquals(susers.get(i), "nabil-g@advpermits.com")) {
					address[i] = new InternetAddress("Portal@advpermits.com");
				} else {
					address[i] = new InternetAddress("dev01@nuagetechnologies-tn.com");
				}

			}
		}
		try {
			MimeMessage message = session.getMailingMessage();
			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.setRecipients(Message.RecipientType.TO, address);
			message.setSubject(objectMail);
			message.setText(textMail);

			// Send message
			Transport.send(message);
			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	public String sendingMailMultipleRecieversLibraries(List<String> susers, String objectMail, String textMail)
			throws AddressException {
		InternetAddress[] address = null;
		if (susers != null) {
			address = new InternetAddress[susers.size()];
			for (int i = 0; i < susers.size(); i++) {
				if (susers.get(i) != null && !(susers.get(i).contains("nuagetechnologies-tn.com"))
						&& checkValueTypesService.NotEquals(susers.get(i), "nabil-g@advpermits.com")) {
					address[i] = new InternetAddress("Updates@advpermits.com");
				} else {
					address[i] = new InternetAddress("dev01@nuagetechnologies-tn.com");
				}

			}
		}
		try {
			MimeMessage message = session.getMailingMessage();
			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.setRecipients(Message.RecipientType.TO, address);
			message.setSubject(objectMail);
			message.setText(textMail);

			// Send message
			Transport.send(message);
			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	public String sendingMailMultipleRecieversForRfi(String susers, String objectMail, String body, String userConEmail)
			throws AddressException, UnsupportedEncodingException {

		try {
			MimeMessage message = session.getMailingMessage();
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(susers));
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress(userConEmail));
			message.setFrom(new InternetAddress("info@advpermits.com", "PLAN SET ON HOLD"));
			message.setSubject(objectMail);
			message.setText(body, "UTF-8", "html");
			// Send message
			Transport.send(message);
			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	public String sendingMailPermitEvaluation(String susers, String objectMail, String body, String userConEmail)
			throws AddressException {

		try {
			MimeMessage message = session.getMailingMessage();
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(susers));
			if (checkValueTypesService.NotEquals(susers, userConEmail)) {
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress(userConEmail));
			}
			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.setSubject(objectMail);
			message.setText(body, "UTF-8", "html");
			// Send message
			Transport.send(message);
			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	public String sendingMailPMEvaluationComplete(String objectMail, String body, String userConEmail)
			throws AddressException {

		try {
			MimeMessage message = session.getMailingMessage();
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("dev01@nuagetechnologies-tn.com"));
			if (userConEmail != null && !(userConEmail.contains("nuagetechnologies-tn.com"))
					&& checkValueTypesService.NotEquals(userConEmail, "nabil-g@advpermits.com")) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress("Portal@advpermits.com"));
			}

			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.setSubject(objectMail);
			message.setText(body, "UTF-8", "html");
			// Send message
			Transport.send(message);
			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	// CR-2424
	public String sendingMailPMSubmitEvaluation(String susers, String objectMail, String body, String projectName,
			String userName) throws AddressException {

		try {
			MimeMessage message = session.getMailingMessage();
			if (checkValueTypesService.Equals(susers, "support@nuagetechnologies-tn.com")) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress("support@nuagetechnologies-tn.com"));
			} else if (susers != null && susers.contains("nuagetechnologies-tn.com")) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress("dev01@nuagetechnologies-tn.com"));

			} else if (susers != null && !susers.equals("")) {
				// Message.RecipientType.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress("support@nuagetechnologies-tn.com"));
				sendADVMailPMSubmitEvaluation(objectMail, projectName, userName);
			}

			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.setSubject(objectMail);
			message.setText(body, "UTF-8", "html");
			// Send message
			Transport.send(message);
			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	public void sendADVMailPMSubmitEvaluation(String objectMail, String projectName, String userName) {
		try {
			String body = "Hello,<br><br>" + userName + " has a technical problem when submitting the project "
					+ projectName
					+ ".<br>NUATN technical team will be working on the problem as soon as possible.<br><br>Thank you.";

			MimeMessage message = session.getMailingMessage();
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("arij@nuagetechnologies-tn.com"));

			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.setSubject(objectMail);
			message.setText(body, "UTF-8", "html");
			// Send message
			Transport.send(message);

		} catch (Exception mex) {
			mex.printStackTrace();

		}
	}

	public String sendingProjectEmail(ProjectEmailModel projectEmailAttributes, String userConEmail)
			throws AddressException {

		try {

			MimeMessage message = session.getMailingMessage();
			if (checkValueTypesService.NotEquals(projectEmailAttributes.getContractorEmail(), "Other")) {
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(projectEmailAttributes.getContractorEmail()));
			} else {
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(projectEmailAttributes.getOtherContractorEmail()));
			}
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress(userConEmail));
			if ((userConEmail != null && userConEmail.contains("nuagetechnologies-tn.com"))
					|| checkValueTypesService.Equals(userConEmail, "nabil-g@advpermits.com")) {
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress("dev01@nuagetechnologies-tn.com"));
			} else {
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress("ken-t@advpermits.com"));
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress("dev01@nuagetechnologies-tn.com"));
			}
			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.setSubject(projectEmailAttributes.getEmailSubject());
			message.setText(projectEmailAttributes.getEmailContent(), "UTF-8", "html");
			BodyPart messageBodyPart = new MimeBodyPart();

			InternetHeaders headers = new InternetHeaders();
			headers.addHeader("Content-type", "text/html; charset=UTF-8");
			String html = projectEmailAttributes.getEmailContent() + "<br/><br/><a href='"
					+ projectEmailAttributes.getProjectAttachement() + "'>attached plan-set</a>";
			messageBodyPart.setContent(html, "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setHeader("Content-Type", "multipart");
			message.setContent(multipart);
			// Send message
			Transport.send(message);
			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	public String sendingMailNotifForDeletedPermit(String objectMail, String textMail, String emailContact) {

		String[] mailAddressTo = { "Portal@advpermits.com", "dev01@nuagetechnologies-tn.com" };
		try {

			InternetAddress[] mailAddressTO = new InternetAddress[mailAddressTo.length];
			for (int i = 0; i < mailAddressTo.length; i++) {
				mailAddressTO[i] = new InternetAddress(mailAddressTo[i]);
			}

			MimeMessage message = session.getMailingMessage();

			if (!(emailContact.contains("nuagetechnologies-tn.com"))
					&& checkValueTypesService.NotEquals(emailContact, "nabil-g@advpermits.com")) {

				message.setFrom(new InternetAddress("info@advpermits.com"));
				message.addRecipients(Message.RecipientType.TO, mailAddressTO);
				message.setSubject(objectMail);
				message.setText(textMail);
				// Send message
				Transport.send(message);
				return "sent";

			} else {

				message.setFrom(new InternetAddress("info@advpermits.com"));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress("dev01@nuagetechnologies-tn.com"));
				message.setSubject(objectMail);
				message.setText(textMail);
				// Send message
				Transport.send(message);
				return "sent";
			}

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	public String attachmentSendMail(String typeAttachment, Long idPermit, String emailOfReceiver,
			String attachmentEmailSubject, String attachmentEmailContent, MultipartFile[] uploadedFiles)
			throws AddressException {
		try {

			MimeMessage message = session.getMailingMessage();

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailOfReceiver));

			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.setSubject(attachmentEmailSubject);
			message.setText(attachmentEmailContent, "UTF-8", "html");

			BodyPart messageBodyPart = new MimeBodyPart();

			InternetHeaders headers = new InternetHeaders();
			headers.addHeader("Content-type", "text/html; charset=UTF-8");
			String html = attachmentEmailContent + "<br/><br/>";
			messageBodyPart.setContent(html, "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			if (uploadedFiles != null && uploadedFiles.length > 0) {
				for (MultipartFile filePath : uploadedFiles) {
					MimeBodyPart attachPart = new MimeBodyPart();
					try {
						filePath.transferTo(new File(filePath.getOriginalFilename()).getAbsoluteFile());
						attachPart.attachFile(filePath.getOriginalFilename());
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					multipart.addBodyPart(attachPart);
				}
			}
			message.setHeader("Content-Type", "multipart");

			message.setContent(multipart);
			// Send message
			Transport.send(message);

			addDateAccounting(typeAttachment, idPermit);
			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	private void addDateAccounting(String typeAttachment, Long idPermit) {
		try {

			SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yyyy");
			AccountingsEntity accountingEntity = accountingsRepo.findByPermitId(idPermit);

			if (accountingEntity != null) {
				switch (typeAttachment) {
				case "Invoice":
					accountingEntity.setDateSendInvoice(formattedDate.format(new Date()));
					break;
				case "Project":
					accountingEntity.setDateSendProject(formattedDate.format(new Date()));
					break;
				case "Revisions":
					accountingEntity.setDateSendRevisions(formattedDate.format(new Date()));
					break;
				case "Engineering Request":
					accountingEntity.setDateSendEngineeringRequest(formattedDate.format(new Date()));
					break;
				case "Corrections":
					accountingEntity.setDateSendCorrections(formattedDate.format(new Date()));
					break;
				default:
					break;
				}
				accountingsRepo.save(accountingEntity);
			} else {
				PermitEntity permitEntity = permitRepo.findById(idPermit)
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found:" + idPermit));
				AccountingsEntity newAccounting = new AccountingsEntity();
				newAccounting.setPermit(permitEntity);
				switch (typeAttachment) {
				case "Invoice":
					newAccounting.setDateSendInvoice(formattedDate.format(new Date()));
					break;
				case "Project":
					newAccounting.setDateSendProject(formattedDate.format(new Date()));
					break;
				case "Revisions":
					newAccounting.setDateSendRevisions(formattedDate.format(new Date()));
					break;
				case "Engineering Request":
					newAccounting.setDateSendEngineeringRequest(formattedDate.format(new Date()));
					break;
				case "Corrections":
					newAccounting.setDateSendCorrections(formattedDate.format(new Date()));
					break;
				default:
					break;
				}
				accountingsRepo.save(newAccounting);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// CR-2219
	public String sendWarningEmail(String textMail, String from, String projectName) throws AddressException {

		try {

			MimeMessage message = session.getMailingMessage();
			message.setFrom(new InternetAddress("info@advpermits.com"));

			InternetHeaders headers = new InternetHeaders();
			headers.addHeader("Content-type", "text/html; charset=UTF-8");

			InternetAddress[] address;

			if (from.contains("nuagetechnologies-tn.com")) {
				address = new InternetAddress[1];
				address[0] = new InternetAddress("dev01@nuagetechnologies-tn.com");
			} else {
				address = new InternetAddress[2];
				address[0] = new InternetAddress("Portal@advpermits.com");
				address[1] = new InternetAddress("dev01@nuagetechnologies-tn.com");
			}
			message.setRecipients(Message.RecipientType.TO, address);
			String objectMail;
			objectMail = projectName + " Cover Sheet - large Values";
			message.setSubject(objectMail);
			textMail = "The project " + projectName + " has large values in the following fields:<br/>" + textMail
					+ "<br/>Thank you.<br/>" + "<br/>" + "Your ADV Solar Team.";

			// Accept the email format

			message.setText(textMail, "UTF-8", "html");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(textMail, "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setHeader("Content-Type", "multipart");
			message.setContent(multipart);
			// Send message
			Transport.send(message);

			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

	public void sendMissingEmail(List<MissingSheetsLogEntity> missingSheets) {

		try {

			List<String> missingRsheets = new ArrayList<>();
			List<String> missingSsheets = new ArrayList<>();

			String projectName = missingSheets.get(0).getProject().getProjectName();

			if (!checkValueTypesService.isStringNotEmpty(missingSheets.get(0).getProject().getProjectName())) {
				projectName = missingSheets.get(0).getProject().getHomeOwnLastName() + ", "
						+ missingSheets.get(0).getProject().getHomeOwnName();
			}

			for (MissingSheetsLogEntity sheet : missingSheets) {
				if (checkValueTypesService.contains(sheet.getSheetType(), "R-sheet")) {
					missingRsheets.add(sheet.getSheetName());
				} else
					missingSsheets.add(sheet.getSheetName());
			}
			MimeMessage message = session.getMailingMessage();
			message.setFrom(new InternetAddress("info@advpermits.com"));

			InternetHeaders headers = new InternetHeaders();
			headers.addHeader("Content-type", "text/html; charset=UTF-8");

			InternetAddress[] address;
			address = new InternetAddress[2];
			address[0] = new InternetAddress("arij@nuagetechnologies-tn.com");
			address[1] = new InternetAddress("ftouma@nuagetechnologies-tn.com");

			message.setRecipients(Message.RecipientType.TO, address);
			String objectMail;
			objectMail = "Missing Sheets - " + projectName;
			message.setSubject(objectMail);

			StringBuilder textMail = new StringBuilder(
					"Hello,<br/><br/>The project " + projectName + " was generated with missing sheet(s):<br/><br/>");
			if (!missingRsheets.isEmpty()) {
				textMail.append("Missing R-sheets:<br/></ul>");
				for (String sheet : missingRsheets) {
					textMail.append("<li>" + sheet + "</li>");
				}
				textMail.append("</ul><br/>");
			}
			if (!missingSsheets.isEmpty()) {
				textMail.append("Missing S-sheets:<br/></ul>");
				for (String sheet : missingSsheets) {
					textMail.append("<li>" + sheet + "</li>");
				}
				textMail.append("</ul><br/>");
			}

			textMail.append("Thank you.");
			// Accept the email format

			message.setText(textMail.toString(), "UTF-8", "html");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(textMail.toString(), "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setHeader("Content-Type", "multipart");
			message.setContent(multipart);
			// Send message
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// S.B CR-3182-MOD-001 23/06/2021 project reminder
	public void projectReminder(AuthentificationEntity user, String projectName) {

		try {
			MimeMessage message = session.getMailingMessage();
			message.setFrom(new InternetAddress("info@advpermits.com"));

			InternetHeaders headers = new InternetHeaders();
			headers.addHeader("Content-type", "text/html; charset=UTF-8");

			InternetAddress[] address;
			address = new InternetAddress[2];
			address[0] = new InternetAddress(user.getEmail());
			if (user.getEmail().contains("nuagetechnologies-tn.com"))
				address[1] = new InternetAddress(user.getEmail());
			else
				address[1] = new InternetAddress("Projects@advpermits.com");

			message.setRecipients(Message.RecipientType.TO, address);

			ContractorInformationEntity contractor = contractorRepo.findByAuthentificationEntityId(user.getId());
			if (contractor != null && checkValueTypesService.NotEquals(contractor.getContactEmail(), "")
					&& checkValueTypesService.NotEquals(contractor.getContactEmail(), user.getEmail()))
				message.setRecipients(Message.RecipientType.CC, contractor.getContactEmail());
			if (contractor != null && checkValueTypesService.NotEquals(contractor.getSecondContactEmail(), "")
					&& checkValueTypesService.NotEquals(contractor.getSecondContactEmail(), user.getEmail())
					&& checkValueTypesService.NotEquals(contractor.getSecondContactEmail(),
							contractor.getContactEmail()))
				message.setRecipients(Message.RecipientType.CC, contractor.getSecondContactEmail());
			if (contractor != null && checkValueTypesService.NotEquals(contractor.getThirdContactEmail(), "")
					&& checkValueTypesService.NotEquals(contractor.getThirdContactEmail(), user.getEmail())
					&& checkValueTypesService.NotEquals(contractor.getThirdContactEmail(), contractor.getContactEmail())
					&& checkValueTypesService.NotEquals(contractor.getThirdContactEmail(),
							contractor.getSecondContactEmail()))
				message.setRecipients(Message.RecipientType.CC, contractor.getThirdContactEmail());
			message.setSubject("Project Delayed – " + projectName);
			String textMail = "WARNING!!!<br/>The project shown in the Subject line is completed or nearly completed and has not been submitted. Please consider moving this project to a SUBMITTED state.<br/><br/>Thank you.<br/>Advance Solar Project Management Team";

			message.setText(textMail, "UTF-8", "html");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(textMail, "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setHeader("Content-Type", "multipart");
			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException | ResourceNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String mondayExportError(String projectName, String error, String type) throws AddressException {

		try {
			String textMail = "Good Day,<br/><br/>MAPI syncing of " + type + " " + projectName
					+ " failed. The API error states.<br/>" + error + "<br/><br/>Thank you.";

			MimeMessage message = session.getMailingMessage();
			InternetAddress[] address = new InternetAddress[1];// [2];
			address[0] = new InternetAddress("dev01@nuagetechnologies-tn.com");
//			address[1] = new InternetAddress("Dev_CR@advpermits.com");
			message.setRecipients(Message.RecipientType.TO, address);
			message.setSubject("Monday API Syncing of the Project " + projectName + " Failed");
			Multipart multipart = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setContent(textMail, "text/html");
			multipart.addBodyPart(textBodyPart);
			message.setContent(multipart);

			// Send message
			Transport.send(message);
			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}
	
	public String sendingMailNewUser(String dest, String password) {

		try {			
			String objectMail = "Welcome to the portal";			
			String textMail = "Your account has been created successfully.<br/><br/>"+
					"Your current password: "+password+"<br/>"+
					"You can always change your password from your profile password setting.<br/><br/>"+
					"If you have any questions, email us at accounts@advpermit.com.<br/><br/>"+
					"ADV Team,";

			MimeMessage message = session.getMailingMessage();
			message.setFrom(new InternetAddress("info@advpermits.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));
			message.setSubject(objectMail);
			//message.setText(textMail);
			Multipart multipart = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setContent(textMail, "text/html");
			multipart.addBodyPart(textBodyPart);
			message.setContent(multipart);

			// Send message
			Transport.send(message);
			return "sent";

		} catch (Exception mex) {
			mex.printStackTrace();
			return "problem sending email";
		}

	}

}
