//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
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
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.NotificationEntity;
//import com.PlayGroundAdv.Solar.Entity.RsheetsLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.UserNoticationsEntity;
//import com.PlayGroundAdv.Solar.model.NotificationRequest;
//import com.PlayGroundAdv.Solar.model.SRsheetsModel;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.MailingService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.RsheetLibraryService;
//import com.PlayGroundAdv.Solar.Services.SsheetLibraryService;
//
//public class TestNotificationEntityService {
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
//	@Mock
//    private MailingService mailingService;
//
//	@Spy
//	CheckValueTypesService checkValueTypesService;
//	@InjectMocks
//	NotificationEntityService notificationEntityService = new NotificationEntityService();
//
//
//    @Before
//	public void setupMock() {
//    	notificationEntityService = new NotificationEntityService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//
//
//	@Test
//	public void testgetAllUnreadNotification() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.idNotif.id FROM UserNoticationsEntity u WHERE u.idUser.id = :p1 AND u.isRead = :p2 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u FROM NotificationEntity u WHERE u.id IN :p1 ORDER BY u.dateNotif desc"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<NotificationRequest>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<NotificationRequest>());
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<NotificationRequest>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		ArrayList<UserNoticationsEntity> list = new ArrayList<UserNoticationsEntity>();
//		list.add(null);
//		list.add(new UserNoticationsEntity());
//		scenario.get(3).add(list);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new ArrayList<NotificationRequest>());
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(list);
//		// Result of the query2
//		ArrayList<NotificationEntity> listQ2 = new ArrayList<NotificationEntity>();
//		listQ2.add(null);
//		listQ2.add(new NotificationEntity());
//		scenario.get(4).add(listQ2);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result excpected
//		ArrayList<NotificationRequest> listExp = new ArrayList<NotificationRequest>();
//		NotificationRequest e = new NotificationRequest();
//		e.setStatus("unread");
//		e.setId(0);
//		listExp.add(e);
//		scenario.get(4).add(listExp);
//
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllNotification [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			ArrayList<NotificationRequest> rslt = (ArrayList<NotificationRequest>) notificationEntityService.getAllUnreadNotification((String) scenario.get(i).get(0));
//
//		}
//	}
//
//	@Test
//	public void testgetAllNotification() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.idNotif.id FROM UserNoticationsEntity u WHERE u.idUser.id = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u FROM NotificationEntity u WHERE u.id IN :p1  ORDER BY u.dateNotif desc"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u.isRead FROM UserNoticationsEntity u WHERE u.idUser.id = :p1 AND u.idNotif.id = :p2 "))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<NotificationRequest>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<NotificationRequest>());
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<NotificationRequest>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		ArrayList<UserNoticationsEntity> list = new ArrayList<UserNoticationsEntity>();
//		list.add(null);
//		list.add(new UserNoticationsEntity());
//		scenario.get(3).add(list);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new ArrayList<NotificationRequest>());
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(list);
//		// Result of the query2
//		ArrayList<NotificationEntity> listQ2 = new ArrayList<NotificationEntity>();
//		listQ2.add(null);
//		listQ2.add(new NotificationEntity());
//		scenario.get(4).add(listQ2);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(new ArrayList<NotificationRequest>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add("123");
//		// Result of the query1
//		scenario.get(5).add(list);
//		// Result of the query2
//		scenario.get(5).add(listQ2);
//		// Result of the query3
//		scenario.get(5).add(true);
//		// Result excpected
//		ArrayList<NotificationRequest> listExp = new ArrayList<NotificationRequest>();
//		NotificationRequest e = new NotificationRequest();
//		e.setStatus("read");
//		e.setId(0);
//		listExp.add(e);
//		scenario.get(5).add(listExp);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllNotification [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			ArrayList<NotificationRequest> rslt = (ArrayList<NotificationRequest>) notificationEntityService.getAllNotification((String) scenario.get(i).get(0));
//
//		}
//	}
//
//	@Test
//	public void testsetNotifRead() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u FROM UserNoticationsEntity u WHERE u.idUser.id = :p1 AND u.idNotif.id = :p2 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u FROM UserNoticationsEntity u WHERE u.isRead = :p1 AND u.idNotif.id = :p2 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u FROM NotificationEntity u WHERE u.id = :p1 ORDER BY u.dateNotif desc"))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Fail");
//		//Reuslt Query 1 list
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//		//Reuslt Query 1 list
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Fail");
//		//Reuslt Query 1 list
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(new UserNoticationsEntity());
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("Fail");
//		//Reuslt Query 1 list
//		scenario.get(3).add(new ArrayList<UserNoticationsEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(new UserNoticationsEntity());
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result of the query3
//		scenario.get(4).add(new NotificationEntity());
//		// Result excpected
//		scenario.get(4).add("Success");
//		//Reuslt Query 1 list
//		scenario.get(4).add(new ArrayList<UserNoticationsEntity>());
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("setNotifRead [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(6));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(4));
//
//
//					notificationEntityService.setNotifRead((String) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//	}
//
//	@Test
//	public void testgetAllSuperUsers() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(" SELECT u FROM AuthentificationEntity u WHERE u.roleEntity.id = :p1  "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<AuthentificationEntity> list = new ArrayList<AuthentificationEntity>();
//		list.add(null);
//		list.add(new AuthentificationEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllSuperUsers [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			ArrayList<AuthentificationEntity> rslt = (ArrayList<AuthentificationEntity>) notificationEntityService
//					.getAllSuperUsers();
//
//		}
//
//	}
//
//	@Test
//	public void testaddNewNotif() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u FROM AuthentificationEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(" SELECT u FROM AuthentificationEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		NotificationEntityService notificationEntityService2 = Mockito.spy(notificationEntityService);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Fail");
//		//Result expected from function getAllSuperUsers
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//		//Result expected from function getAllSuperUsers
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Fail");
//		//Result expected from function getAllSuperUsers
//		scenario.get(2).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("Success");
//		//Result expected from function getAllSuperUsers
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(true);
//		// Result of the query1
//		scenario.get(4).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("Success");
//		//Result expected from function getAllSuperUsers
//		scenario.get(4).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add("123");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(true);
//		// Result of the query1
//		scenario.get(5).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("Success");
//		//Result expected from function getAllSuperUsers
//		scenario.get(5).add(new ArrayList<AuthentificationEntity>());
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addNewNotif [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(8));
//			Mockito.doReturn(scenario.get(i).get(10)).when(notificationEntityService2).getAllSuperUsers();
//
//					notificationEntityService2.addNewNotif((String) scenario.get(i).get(0), (Integer) scenario.get(i).get(1), (String) scenario.get(i).get(2), (String) scenario.get(i).get(3), (String) scenario.get(i).get(4), (String) scenario.get(i).get(5), (Boolean) scenario.get(i).get(6));
//
//		}
//
//	}
//
//	@Test
//	public void testrequestComponentCorrectionNotif() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(" SELECT u  "
//				 + " FROM AuthentificationEntity u "
//				 + " WHERE u.id = :p1  "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("success");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("success");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("1235");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("success");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("1235");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(3).add("success");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addNewNotif [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//
//					notificationEntityService.requestComponentCorrectionNotif((String) scenario.get(i).get(0), (String) scenario.get(i).get(1), (String) scenario.get(i).get(2));
//
//		}
//	}
//
//}
