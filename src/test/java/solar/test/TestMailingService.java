//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.AddressException;
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.PlayGroundAdv.Solar.Entity.ProjectRequestEntity;
//import com.PlayGroundAdv.Solar.model.ProjectEmailModel;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.MailingService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.SsheetLibraryService;
//import com.PlayGroundAdv.Solar.Services.TLDSheetLibraryService;
//
//public class TestMailingService {
//
//	@Mock
//	EntityManager em;
//
//	@Mock
//	private Query query;
//
//	@Mock
//	HistoryActivityService historicActivity;
//
//	@Mock
//	HistoryActivityService historyActivityService;
//
//	@Mock
//	NotificationEntityService notificationEntityService;
//	 @Mock
//	 private CriteriaQuery criteriaQuery;
//
//	 @Mock
//	 private CriteriaQuery criteriaQueryAll;
//
//	 @Mock
//	 private CriteriaBuilder criteriaBuilder;
//
//	@Mock
//    private Root root;
//
//	@Spy
//	CheckValueTypesService checkValueTypesService;
//	@InjectMocks
//	MailingService mailingService = new MailingService();
//
//
//    @Before
//	public void setupMock() {
//    	mailingService = new MailingService();
//	       MockitoAnnotations.initMocks(this);
//	}
////	/*@Test
////	public void testsendingMail() throws UnsupportedEncodingException {
////		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		// Result excpected
////		scenario.get(0).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(1).add("malek@nuagetechnologies-tn.com");
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		// Result excpected
////		scenario.get(1).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(2).add("malek@nuagetechnologies-tn.com");
////		scenario.get(2).add(null);
////		scenario.get(2).add("sent");
////		// Result excpected
////		scenario.get(2).add("sent");
////
////		for (int i = 0; i < scenario.size(); i++) {
////			System.out.println("sendingMail [ " + i + " ]");
////			Assert.assertEquals(scenario.get(i).get(3), mailingService
////					.sendingMail((String) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2)));
////		}
////	}*/
////
////	/*@Test
////	public void testsendingRFIMail() throws UnsupportedEncodingException {
////		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		// Result excpected
////		scenario.get(0).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(1).add("malek@nuagetechnologies-tn.com");
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		// Result excpected
////		scenario.get(1).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(2).add("malek@nuagetechnologies-tn.com");
////		scenario.get(2).add(null);
////		scenario.get(2).add("");
////		scenario.get(2).add(null);
////		scenario.get(2).add(null);
////		// Result excpected
////		scenario.get(2).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(3).add("malek@nuagetechnologies-tn.com");
////		scenario.get(3).add(null);
////		scenario.get(3).add("");
////		scenario.get(3).add("13115050");
////		scenario.get(3).add(null);
////		// Result excpected
////		scenario.get(3).add("sent");
////
////
////		for (int i = 0; i < scenario.size(); i++) {
////			System.out.println("sendingRFIMail [ " + i + " ]");
////			Assert.assertEquals(scenario.get(i).get(5), mailingService
////					.sendingRFIMail((String) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3),(String) scenario.get(i).get(4)));
////		}
////	}*/
////
//////	@Test
//////	public void testsendingSubmitMailNUATN() {
//////		fail("Not yet implemented");
//////	}
//////
//////	@Test
//////	public void testsendingSubmitMail() {
//////		fail("Not yet implemented");
//////	}
//////
////	/*@Test
////	public void testsendingNUATNRegistrationMail() throws AddressException, UnsupportedEncodingException {
////		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		// Result excpected
////		scenario.get(0).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(1).add("malek@nuagetechnologies-tn.com");
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		// Result excpected
////		scenario.get(1).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(2).add("malek@nuagetechnologies-tn.com");
////		scenario.get(2).add("sent");
////		scenario.get(2).add("sent");
////		// Result excpected
////		scenario.get(2).add("sent");
////
////		for (int i = 0; i < scenario.size(); i++) {
////			System.out.println("sendingMail [ " + i + " ]");
////			Assert.assertEquals(scenario.get(i).get(3), mailingService
////					.sendingNUATNRegistrationMail((String) scenario.get(i).get(0),(String) scenario.get(i).get(1)));
////		}
////	}*/
////
////	/*@Test
////	public void testsendingRegistrationMail() throws AddressException, UnsupportedEncodingException {
////
////		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		// Result excpected
////		scenario.get(0).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(1).add("malek@nuagetechnologies-tn.com");
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		// Result excpected
////		scenario.get(1).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(2).add("malek@nuagetechnologies-tn.com");
////		scenario.get(2).add("sent");
////		scenario.get(2).add("sent");
////		// Result excpected
////		scenario.get(2).add("sent");
////
////		for (int i = 0; i < scenario.size(); i++) {
////			System.out.println("sendingMail [ " + i + " ]");
////			Assert.assertEquals(scenario.get(i).get(3), mailingService
////					.sendingRegistrationMail((String) scenario.get(i).get(0),(String) scenario.get(i).get(1)));
////		}
////
////	}*/
////
////	/*@Test
////	public void testsendingMailMultipleRecievers() throws AddressException, UnsupportedEncodingException {
////
////		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		// Result excpected
////		scenario.get(0).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		scenario.get(1).add("");
////		// Result excpected
////		scenario.get(1).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		ArrayList<String> list = new ArrayList<String>();
////		list.add(null);
////		list.add("malek@nuagetechnologies-tn.com");
////		scenario.get(2).add(list);
////		scenario.get(2).add(null);
////		scenario.get(2).add("");
////		// Result excpected
////		scenario.get(2).add("sent");
////
////		for (int i = 0; i < scenario.size(); i++) {
////			System.out.println("sendingMail [ " + i + " ]");
////			Assert.assertEquals(scenario.get(i).get(3), mailingService
////					.sendingMailMultipleRecievers((List<String>)scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2)));
////		}
////	}*/
////
////	/*@Test
////	public void testsendingMailMultipleRecieversLibraries() throws AddressException, UnsupportedEncodingException {
////
////
////		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		// Result excpected
////		scenario.get(0).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		scenario.get(1).add("");
////		// Result excpected
////		scenario.get(1).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		ArrayList<String> list = new ArrayList<String>();
////		list.add(null);
////		list.add("malek@nuagetechnologies-tn.com");
////		scenario.get(2).add(list);
////		scenario.get(2).add(null);
////		scenario.get(2).add("");
////		// Result excpected
////		scenario.get(2).add("sent");
////
////		for (int i = 0; i < scenario.size(); i++) {
////			System.out.println("sendingMail [ " + i + " ]");
////			Assert.assertEquals(scenario.get(i).get(3), mailingService
////					.sendingMailMultipleRecieversLibraries((List<String>)scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2)));
////		}
////
////	}*/
////
////	/*@Test
////	public void testsendingMailMultipleRecieversForRfi() throws AddressException, UnsupportedEncodingException {
////
////		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		// Result excpected
////		scenario.get(0).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(1).add("malek@nuagetechnologies-tn.com");
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		// Result excpected
////		scenario.get(1).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(2).add("malek@nuagetechnologies-tn.com");
////		scenario.get(2).add(null);
////		scenario.get(2).add(null);
////		scenario.get(2).add(null);
////		scenario.get(2).add(null);
////		scenario.get(2).add("malek@nuagetechnologies-tn.com");
////		// Result excpected
////		scenario.get(2).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(3).add("malek@nuagetechnologies-tn.com");
////		scenario.get(3).add(null);
////		scenario.get(3).add(null);
////		scenario.get(3).add(null);
////		scenario.get(3).add("hello word");
////		scenario.get(3).add("malek@nuagetechnologies-tn.com");
////		// Result excpected
////		scenario.get(3).add("sent");
////
////		for (int i = 0; i < scenario.size(); i++) {
////			System.out.println("sendingMailMultipleRecieversForRfi [ " + i + " ]");
////			Assert.assertEquals(scenario.get(i).get(6), mailingService
////					.sendingMailMultipleRecieversForRfi((String)scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String)scenario.get(i).get(3),(String) scenario.get(i).get(4),(String) scenario.get(i).get(5)));
////		}
////	}*/
////
////	/*@Test
////	public void testsendingMailPermitEvaluation() throws AddressException, UnsupportedEncodingException {
////		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		// Result excpected
////		scenario.get(0).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(1).add("malek@nuagetechnologies-tn.com");
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		// Result excpected
////		scenario.get(1).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(2).add("malek@nuagetechnologies-tn.com");
////		scenario.get(2).add(null);
////		scenario.get(2).add(null);
////		scenario.get(2).add("malek@nuagetechnologies-tn.com");
////		// Result excpected
////		scenario.get(2).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(3).add("malek@nuagetechnologies-tn.com");
////		scenario.get(3).add(null);
////		scenario.get(3).add("hello word one ");
////		scenario.get(3).add("malek@nuagetechnologies-tn.com");
////		// Result excpected
////		scenario.get(3).add("sent");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(4).add("malek@nuagetechnologies-tn.com");
////		scenario.get(4).add(null);
////		scenario.get(4).add("hello word one ");
////		scenario.get(4).add("imen@nuagetechnologies-tn.com");
////		// Result excpected
////		scenario.get(4).add("sent");
////
////		for (int i = 0; i < scenario.size(); i++) {
////			System.out.println("sendingMailPermitEvaluation [ " + i + " ]");
////			Assert.assertEquals(scenario.get(i).get(4), mailingService
////					.sendingMailPermitEvaluation((String)scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String)scenario.get(i).get(3)));
////		}
////	}*/
////
////	/*@Test
////	public void testsendingMailPMEvaluationComplete() throws AddressException {
////		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		// Result excpected
////		scenario.get(0).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		// Result excpected
////		scenario.get(1).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(2).add(null);
////		scenario.get(2).add(null);
////		scenario.get(2).add("malek@nuagetechnologies-tn.com");
////		// Result excpected
////		scenario.get(2).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(3).add(null);
////		scenario.get(3).add("hello word nuage tech ");
////		scenario.get(3).add("malek@nuagetechnologies-tn.com");
////		// Result excpected
////		scenario.get(3).add("sent");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(4).add(null);
////		scenario.get(4).add("hello word not nuage");
////		scenario.get(4).add("malek@nuagetech.com");
////		// Result excpected
////		scenario.get(4).add("sent");
////
////		for (int i = 0; i < scenario.size(); i++) {
////			System.out.println("sendingMailPMEvaluationComplete [ " + i + " ]");
////			Assert.assertEquals(scenario.get(i).get(3), mailingService
////					.sendingMailPMEvaluationComplete((String)scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2)));
////		}
////	}*/
////
////	/*@Test
////	public void testsendingMailPMSubmitEvaluation() throws AddressException, UnsupportedEncodingException {
////		MailingService mailingService2 = Mockito.spy(mailingService);
////
////		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		// Result excpected
////		scenario.get(0).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(1).add("malek@nuagetechnologies-tn.com");
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		scenario.get(1).add(null);
////		// Result excpected
////		scenario.get(1).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(2).add("malek@nuagetechnologies-tn.com");
////		scenario.get(2).add(null);
////		scenario.get(2).add("hello word now ");
////		scenario.get(2).add(null);
////		scenario.get(2).add(null);
////		scenario.get(2).add(null);
////		// Result excpected
////		scenario.get(2).add("sent");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(3).add("support@nuagetechnologies-tn.com");
////		scenario.get(3).add(null);
////		scenario.get(3).add("hello word 1258");
////		scenario.get(3).add(null);
////		scenario.get(3).add(null);
////		scenario.get(3).add(null);
////		// Result excpected
////		scenario.get(3).add("sent");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(4).add("support@nuagetechn.com");
////		scenario.get(4).add(null);
////		scenario.get(4).add("hello word 000000");
////		scenario.get(4).add(null);
////		scenario.get(4).add(null);
////		scenario.get(4).add(null);
////		// Result excpected
////		scenario.get(4).add("sent");
////
////		for (int i = 0; i < scenario.size(); i++) {
////			System.out.println("sendingMailPMSubmitEvaluation [ " + i + " ]");
////			mailingService2.sendADVMailPMSubmitEvaluation(
////					(String) scenario.get(i).get(0), (String) scenario.get(i).get(1),
////					(String) scenario.get(i).get(2), (String) scenario.get(i).get(3));
////
////			Assert.assertEquals(scenario.get(i).get(6),
////				 mailingService2
////					.sendingMailPMSubmitEvaluation((String)scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String)scenario.get(i).get(3),(String) scenario.get(i).get(4),(String) scenario.get(i).get(5)));
////		}
////	}*/
////
////	/*@Test
////	public void testsendADVMailPMSubmitEvaluation() {
////		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		for (int i = 0; i < scenario.size(); i++) {
////			System.out.println("sendingMailPMEvaluationComplete [ " + i + " ]");
////             mailingService.sendADVMailPMSubmitEvaluation((String)scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
////		}
////	}*/
////
////	/*@Test
////	public void testsendingProjectEmail() throws AddressException, UnsupportedEncodingException {
////		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(0).add(null);
////		scenario.get(0).add(null);
////		// Result excpected
////		scenario.get(0).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(1).add(new ProjectEmailModel());
////		scenario.get(1).add(null);
////		// Result excpected
////		scenario.get(1).add("problem sending email");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		ProjectEmailModel pr= new ProjectEmailModel();
////		pr.setOtherContractorEmail("malek@nuagetechnologies-tn.com");
////		pr.setEmailSubject("hello word!!!");
////		pr.setEmailContent("hhhhhhhhhhhhhh");
////		scenario.get(2).add(pr);
////		scenario.get(2).add(null);
////		// Result excpected
////		scenario.get(2).add("problem sending email");
////
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(3).add(pr);
////		scenario.get(3).add("malek@nuagetechnologies-tn.com");
////		// Result excpected
////		scenario.get(3).add("sent");
////
////		scenario.add(new ArrayList<Object>());
////		// List of the parameter
////		scenario.get(4).add(pr);
////		scenario.get(4).add("malek@nuagetec.com");
////		// Result excpected
////		scenario.get(4).add("sent");
////
////		for (int i = 0; i < scenario.size(); i++) {
////			System.out.println("sendingProjectEmail [ " + i + " ]");
////			Assert.assertEquals(scenario.get(i).get(2),
////				 mailingService
////					.sendingProjectEmail((ProjectEmailModel)scenario.get(i).get(0),(String) scenario.get(i).get(1)));
////		}
////	}*/
////
////	@Test
////	public void testsendingMailNotifForDeletedPermit() {
////		fail("Not yet implemented");
////	}
////
//////	@Test
//////	public void testattachmentSendMail() {
//////		fail("Not yet implemented");
//////	}
//////
//////	@Test
//////	public void testAddDateAccounting() {
//////		fail("Not yet implemented");
//////	}
//////
//////	@Test
//////	public void testsendWarningEmail() {
//////		fail("Not yet implemented");
//////	}
//}
