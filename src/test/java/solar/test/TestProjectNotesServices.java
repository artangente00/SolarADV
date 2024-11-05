//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Date;
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
//
//import com.PlayGroundAdv.Solar.Entity.ProjectRequestEntity;
//import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
//import com.PlayGroundAdv.Solar.model.ProjectContactsModel;
//import com.PlayGroundAdv.Solar.model.SRsheetsModel;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.ProjectNotesServices;
//import com.PlayGroundAdv.Solar.Services.SsheetLibraryService;
//
//public class TestProjectNotesServices {
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
//	ProjectNotesServices projectNotesServices = new ProjectNotesServices();
//
//
//    @Before
//	public void setupMock() {
//    	projectNotesServices = new ProjectNotesServices();
//	       MockitoAnnotations.initMocks(this);
//	}
//	@Test
//	public void testgetProjectContactsOwnerNumber() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(" SELECT u.authentificationEntity FROM PermitEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT count(*) from ContactsNameEntity u WHERE u.idUser = :p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(0);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(0);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(0);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("1235");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add("");
//		// Result excpected
//		scenario.get(3).add(0);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("1235");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		scenario.get(4).add(5888);
//		// Result excpected
//		scenario.get(4).add(5888);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deleteTLDsheetNotification [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			projectNotesServices
//					.getProjectContactsOwnerNumber((String) scenario.get(i).get(0));
//		}
//
//	}
//
//	@Test
//	public void testgetProjectContactsOwnerAuth() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(" SELECT u.authentificationEntity.id FROM PermitEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(" SELECT new com.PlayGroundAdv.Solar.model.ProjectContactsModel("
//				+ "  u.contactFirstName, "
//				+ "  u.contactLastName,  "
//				+ "  u.secondContactFirstName, "
//				+ "  u.secondContactLastName, "
//				+ "  u.thirdContact, "
//				+ "  u.lastNameContact )  "
//				+ "  From AuthentificationEntity u"
//				+ "  Where u.id = :p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ProjectContactsModel ());
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ProjectContactsModel ());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("1255");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("1255");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(new ProjectContactsModel());
//		// Result excpected
//		scenario.get(3).add(new ProjectContactsModel());
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getProjectContactsOwnerAuth [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			ProjectContactsModel rslt =(ProjectContactsModel) projectNotesServices
//					.getProjectContactsOwnerAuth((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testgetProjectContactsOwnerAuthContN() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(" SELECT u.authentificationEntity.id FROM PermitEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(" SELECT new com.PlayGroundAdv.Solar.model.ProjectContactsModel("
//				+ "  u.contactFirstName, "
//				+ "  u.contactLastName,  "
//				+ "  u.secondContactFirstName, "
//				+ "  u.secondContactLastName, "
//				+ "  u.thirdContact, "
//				+ "  u.lastNameContact, "
//				+ "  v.firstname, "
//				+ "  v.lastName, "
//				+ "  v.projectContactPhone, "
//				+ "  v.projectContactEmail )  "
//				+ "  From AuthentificationEntity u, ContactsNameEntity v "
//				+ "  Where u.id = :p1 AND v.idUser.id = u.id ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<>());
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("1235");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		ArrayList<ProjectContactsModel> list = new ArrayList<ProjectContactsModel>();
//		list.add(null);
//		list.add(new ProjectContactsModel());
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getProjectContactsOwnerAuthContN [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			ArrayList<ProjectContactsModel> rslt = (ArrayList<ProjectContactsModel>) projectNotesServices
//					.getProjectContactsOwnerAuthContN((String) scenario.get(i).get(0));
//
//		}
//	}
//
//	@Test
//	public void testaddNewRequest() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u FROM PermitEntity u WHERE u.id =:p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(" SELECT u.authentificationEntity FROM PermitEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
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
//		scenario.get(0).add(new ProjectRequestEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new ProjectRequestEntity());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ProjectRequestEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new ProjectRequestEntity());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("");
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ProjectRequestEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new ProjectRequestEntity());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("1258");
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		ProjectRequestEntity exp = new ProjectRequestEntity();
//		Date currentDate= new Date();
//		exp.setDateAddNotif(currentDate);
//		exp.setLastUpdated(currentDate);
//		scenario.get(3).add(exp);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getProjectContactsOwnerAuth [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(6));
//			ProjectRequestEntity rslt =(ProjectRequestEntity) projectNotesServices
//					.addNewRequest((ProjectRequestEntity) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3),(String) scenario.get(i).get(4));
//
//		}
//
//	}
//
//	@Test
//	public void testgetAllRequests() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u FROM ProjectRequestEntity u WHERE u.permit.id = :p1 order by u.dateAddNotif DESC")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<>());
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("1235");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		ArrayList<ProjectRequestEntity> list = new ArrayList<ProjectRequestEntity>();
//		list.add(null);
//		list.add(new ProjectRequestEntity());
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllRequests [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			ArrayList<ProjectRequestEntity> rslt = (ArrayList<ProjectRequestEntity>) projectNotesServices
//					.getAllRequests((String) scenario.get(i).get(0));
//
//		}
//	}
//
//}
