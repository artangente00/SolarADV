//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//
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
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.DCOptimizerEntity;
//import com.PlayGroundAdv.Solar.Entity.RailRacking;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.model.ComponentTypeRequest;
//import com.PlayGroundAdv.Solar.model.ConfirmComponentRequest;
//import com.PlayGroundAdv.Solar.model.JunctionsBoxModel;
//import com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.JunctionBoxLibraryService;
//import com.PlayGroundAdv.Solar.Services.LibariesManagementService;
//import com.PlayGroundAdv.Solar.Services.MailingService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//
//public class TestLibariesManagementService {
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
//	@Mock
//	private CriteriaQuery criteriaQuery;
//
//	@Mock
//	private CriteriaQuery criteriaQueryAll;
//
//	@Mock
//	private CriteriaBuilder criteriaBuilder;
//
//	@Mock
//	private Root root;
//
//	@Spy
//	CheckValueTypesService checkValueTypesService;
//	@Mock
//	MailingService mailingService;
//	@InjectMocks
//	LibariesManagementService libariesManagementService = new LibariesManagementService();
//
//	@Before
//	public void setupMock() {
//		libariesManagementService = new LibariesManagementService();
//		MockitoAnnotations.initMocks(this);
//	}
//
//
//
//
//	@Test
//	public void testgetAllNewComponent() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery(" SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult( "
//					+ " u.id, " + " u.make, " + " u.model, " + " u.addDate, "
//					+ " u.authentificationEntity.firstName, " + " u.authentificationEntity.lastName ) "
//					+ " FROM  Inverters u " + " WHERE u.isDeleted = :p1 " + " AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery(" SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult( "
//					+ " u.id, " + " u.make, " + " u.model, " + " u.addDate, "
//					+ " u.authentificationEntity.firstName, " + " u.authentificationEntity.lastName ) "
//					+ " FROM  Cmodulev2 u " + " WHERE u.isDeleted = :p1 " + " AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery(" SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult( "
//					+ " u.id, " + " u.manufacturer, " + " u.model, " + " u.addDate, " + " u.user.firstName, "
//					+ " u.user.lastName ) " + " FROM  DCOptimizerEntity u " + " WHERE u.isDeleted = :p1 "
//					+ " AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//		 Query mockedQuery4 = mock(Query.class);
//		 when(em.createQuery(" SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult( "
//					+ " u.id, " + " u.manufacturer, " + " u.model, " + " u.addDate," + " u.idOwner.firstName, "
//					+ " u.idOwner.lastName ) " + " FROM  RailRacking u " + " WHERE u.isDeleted = :p1 "
//					+ " AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")).thenReturn(mockedQuery4);
//		 when(mockedQuery4.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery4);
//
//		 Query mockedQuery5 = mock(Query.class);
//		 when(em.createQuery(" SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult( "
//					+ " u.id, " + " u.manufacturer, " + " u.model, " + " u.addDate," + " u.idOwner.firstName, "
//					+ " u.idOwner.lastName ) " + " FROM  RoofAttachmentsEntity u" + " WHERE u.isDeleted = :p1 "
//					+ " AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")).thenReturn(mockedQuery5);
//		 when(mockedQuery5.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery5);
//
//		 Query mockedQuery6 = mock(Query.class);
//		 when(em.createQuery(" SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult( "
//					+ " u.id, " + " u.manufacturer, " + " u.model, " + " u.addDate," + " u.idOwner.firstName, "
//					+ " u.idOwner.lastName," + " u.typeDc ) " + " FROM  DCCombinerDisconnectEntity u"
//					+ " WHERE u.isDeleted = :p1 " + " AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")).thenReturn(mockedQuery6);
//		 when(mockedQuery6.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery6);
//
//
//		 Query mockedQuery7 = mock(Query.class);
//		 when(em.createQuery(" SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult( "
//					+ " u.id, " + " u.manufacturer, " + " u.model, " + " u.addDate," + " u.idOwner.firstName, "
//					+ " u.idOwner.lastName," + " u.type ) " + " FROM  ACDisconnect u " + " WHERE u.isDeleted = :p1 "
//					+ " AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")).thenReturn(mockedQuery7);
//		 when(mockedQuery7.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery7);
//
//		 Query mockedQuery8 = mock(Query.class);
//		 when(em.createQuery(" SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult( "
//					+ " u.id, " + " u.manufacturer, " + " u.model, " + " u.addDate," + " u.idOwner.firstName, "
//					+ " u.idOwner.lastName," + " u.type ) " + " FROM  ACCombinerSLC u " + " WHERE u.isDeleted = :p1 "
//					+ " AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")).thenReturn(mockedQuery8);
//		 when(mockedQuery8.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery8);
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result of the query2
//		 scenario.get(0).add(null);
//		 //Result of the query3
//		 scenario.get(0).add(null);
//		 //Result of the query4
//		 scenario.get(0).add(null);
//		 //Result of the query5
//		 scenario.get(0).add(null);
//		 //Result of the query6
//		 scenario.get(0).add(null);
//		 //Result of the query7
//		 scenario.get(0).add(null);
//		 //Result of the query8
//		 scenario.get(0).add(null);
//		 //Result excpected
//		 scenario.get(0).add(new ArrayList<LibrariesManagementModelResult>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of the query1
//		 ArrayList<LibrariesManagementModelResult> listQ1 = new ArrayList<LibrariesManagementModelResult>();
//		 listQ1.add(null);
//		 listQ1.add(new LibrariesManagementModelResult());
//		 scenario.get(1).add(listQ1);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//		 //Result of the query3
//		 scenario.get(1).add(null);
//		 //Result of the query4
//		 scenario.get(1).add(null);
//		 //Result of the query5
//		 scenario.get(1).add(null);
//		 //Result of the query6
//		 scenario.get(1).add(null);
//		 //Result of the query7
//		 scenario.get(1).add(null);
//		 //Result of the query8
//		 scenario.get(1).add(null);
//		 //Result excpected
//		 ArrayList<LibrariesManagementModelResult> listExp1 = new ArrayList<LibrariesManagementModelResult>();
//		 listExp1.addAll(listQ1);
//		 scenario.get(1).add(listExp1);
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of the query1
//		 scenario.get(2).add(listQ1);
//		 //Result of the query2
//		 ArrayList<LibrariesManagementModelResult> listQ2 = new ArrayList<LibrariesManagementModelResult>();
//		 listQ2.add(null);
//		 listQ2.add(new LibrariesManagementModelResult());
//		 scenario.get(2).add(listQ2);
//		 //Result of the query3
//		 scenario.get(2).add(null);
//		 //Result of the query4
//		 scenario.get(2).add(null);
//		 //Result of the query5
//		 scenario.get(2).add(null);
//		 //Result of the query6
//		 scenario.get(2).add(null);
//		 //Result of the query7
//		 scenario.get(2).add(null);
//		 //Result of the query8
//		 scenario.get(2).add(null);
//		 //Result excpected
//		 ArrayList<LibrariesManagementModelResult> listExp2 = new ArrayList<LibrariesManagementModelResult>();
//		 listExp2.addAll(listQ1);
//		 listExp2.addAll(listQ2);
//		 scenario.get(2).add(listExp2);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of the query1
//		 scenario.get(3).add(listQ1);
//		 //Result of the query2
//		 scenario.get(3).add(listQ2);
//		 //Result of the query3
//		 ArrayList<LibrariesManagementModelResult> listQ3 = new ArrayList<LibrariesManagementModelResult>();
//		 listQ3.add(null);
//		 listQ3.add(new LibrariesManagementModelResult());
//		 scenario.get(3).add(listQ3);
//		 //Result of the query4
//		 scenario.get(3).add(null);
//		 //Result of the query5
//		 scenario.get(3).add(null);
//		 //Result of the query6
//		 scenario.get(3).add(null);
//		 //Result of the query7
//		 scenario.get(3).add(null);
//		 //Result of the query8
//		 scenario.get(3).add(null);
//		 //Result excpected
//		 ArrayList<LibrariesManagementModelResult> listExp3 = new ArrayList<LibrariesManagementModelResult>();
//		 listExp3.addAll(listQ1);
//		 listExp3.addAll(listQ2);
//		 listExp3.addAll(listQ3);
//		 scenario.get(3).add(listExp3);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of the query1
//		 scenario.get(4).add(listQ1);
//		 //Result of the query2
//		 scenario.get(4).add(listQ2);
//		 //Result of the query3
//		 scenario.get(4).add(listQ3);
//		 //Result of the query4
//		 ArrayList<LibrariesManagementModelResult> listQ4 = new ArrayList<LibrariesManagementModelResult>();
//		 listQ4.add(null);
//		 listQ4.add(new LibrariesManagementModelResult());
//		 scenario.get(4).add(listQ4);
//		 //Result of the query5
//		 scenario.get(4).add(null);
//		 //Result of the query6
//		 scenario.get(4).add(null);
//		 //Result of the query7
//		 scenario.get(4).add(null);
//		 //Result of the query8
//		 scenario.get(4).add(null);
//		 //Result excpected
//		 ArrayList<LibrariesManagementModelResult> listExp4 = new ArrayList<LibrariesManagementModelResult>();
//		 listExp4.addAll(listQ1);
//		 listExp4.addAll(listQ2);
//		 listExp4.addAll(listQ3);
//		 listExp4.addAll(listQ4);
//		 scenario.get(4).add(listExp4);
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of the query1
//		 scenario.get(5).add(listQ1);
//		 //Result of the query2
//		 scenario.get(5).add(listQ2);
//		 //Result of the query3
//		 scenario.get(5).add(listQ3);
//		 //Result of the query4
//		 scenario.get(5).add(listQ4);
//		 //Result of the query5
//		 ArrayList<LibrariesManagementModelResult> listQ5 = new ArrayList<LibrariesManagementModelResult>();
//		 listQ5.add(null);
//		 listQ5.add(new LibrariesManagementModelResult());
//		 scenario.get(5).add(listQ5);
//		 //Result of the query6
//		 scenario.get(5).add(null);
//		 //Result of the query7
//		 scenario.get(5).add(null);
//		 //Result of the query8
//		 scenario.get(5).add(null);
//		 //Result excpected
//		 ArrayList<LibrariesManagementModelResult> listExp5 = new ArrayList<LibrariesManagementModelResult>();
//		 listExp5.addAll(listQ1);
//		 listExp5.addAll(listQ2);
//		 listExp5.addAll(listQ3);
//		 listExp5.addAll(listQ4);
//		 listExp5.addAll(listQ5);
//		 scenario.get(5).add(listExp5);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of the query1
//		 scenario.get(6).add(listQ1);
//		 //Result of the query2
//		 scenario.get(6).add(listQ2);
//		 //Result of the query3
//		 scenario.get(6).add(listQ3);
//		 //Result of the query4
//		 scenario.get(6).add(listQ4);
//		 //Result of the query5
//		 scenario.get(6).add(listQ5);
//		 //Result of the query6
//		 ArrayList<LibrariesManagementModelResult> listQ6 = new ArrayList<LibrariesManagementModelResult>();
//		 listQ6.add(null);
//		 listQ6.add(new LibrariesManagementModelResult());
//		 scenario.get(6).add(listQ6);
//		 //Result of the query7
//		 scenario.get(6).add(null);
//		 //Result of the query8
//		 scenario.get(6).add(null);
//		 //Result excpected
//		 ArrayList<LibrariesManagementModelResult> listExp6 = new ArrayList<LibrariesManagementModelResult>();
//		 listExp6.addAll(listQ1);
//		 listExp6.addAll(listQ2);
//		 listExp6.addAll(listQ3);
//		 listExp6.addAll(listQ4);
//		 listExp6.addAll(listQ5);
//		 listExp6.addAll(listQ6);
//		 scenario.get(6).add(listExp6);
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of the query1
//		 scenario.get(7).add(listQ1);
//		 //Result of the query2
//		 scenario.get(7).add(listQ2);
//		 //Result of the query3
//		 scenario.get(7).add(listQ3);
//		 //Result of the query4
//		 scenario.get(7).add(listQ4);
//		 //Result of the query5
//		 scenario.get(7).add(listQ5);
//		 //Result of the query6
//		 scenario.get(7).add(listQ6);
//		 //Result of the query7
//		 ArrayList<LibrariesManagementModelResult> listQ7 = new ArrayList<LibrariesManagementModelResult>();
//		 listQ7.add(null);
//		 listQ7.add(new LibrariesManagementModelResult());
//		 scenario.get(7).add(listQ7);
//		 //Result of the query8
//		 scenario.get(7).add(null);
//		 //Result excpected
//		 ArrayList<LibrariesManagementModelResult> listExp7 = new ArrayList<LibrariesManagementModelResult>();
//		 listExp7.addAll(listQ1);
//		 listExp7.addAll(listQ2);
//		 listExp7.addAll(listQ3);
//		 listExp7.addAll(listQ4);
//		 listExp7.addAll(listQ5);
//		 listExp7.addAll(listQ6);
//		 listExp7.addAll(listQ7);
//		 scenario.get(7).add(listExp7);
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of the query1
//		 scenario.get(8).add(listQ1);
//		 //Result of the query2
//		 scenario.get(8).add(listQ2);
//		 //Result of the query3
//		 scenario.get(8).add(listQ3);
//		 //Result of the query4
//		 scenario.get(8).add(listQ4);
//		 //Result of the query5
//		 scenario.get(8).add(listQ5);
//		 //Result of the query6
//		 scenario.get(8).add(listQ6);
//		 //Result of the query7
//		 scenario.get(8).add(listQ7);
//		 //Result of the query8
//		 ArrayList<LibrariesManagementModelResult> listQ8 = new ArrayList<LibrariesManagementModelResult>();
//		 listQ8.add(null);
//		 listQ8.add(new LibrariesManagementModelResult());
//		 scenario.get(8).add(listQ8);
//		 //Result excpected
//		 ArrayList<LibrariesManagementModelResult> listExp8 = new ArrayList<LibrariesManagementModelResult>();
//		 listExp8.addAll(listQ1);
//		 listExp8.addAll(listQ2);
//		 listExp8.addAll(listQ3);
//		 listExp8.addAll(listQ4);
//		 listExp8.addAll(listQ5);
//		 listExp8.addAll(listQ6);
//		 listExp8.addAll(listQ7);
//		 listExp8.addAll(listQ8);
//		 scenario.get(8).add(listExp8);
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getAllNewComponent [ "+i+" ]");
//	         when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//	         when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(1));
//	         when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(2));
//	         when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(3));
//	         when(mockedQuery5.getResultList()).thenReturn((List) scenario.get(i).get(4));
//	         when(mockedQuery6.getResultList()).thenReturn((List) scenario.get(i).get(5));
//	         when(mockedQuery7.getResultList()).thenReturn((List) scenario.get(i).get(6));
//	         when(mockedQuery8.getResultList()).thenReturn((List) scenario.get(i).get(7));
//
//	         List<LibrariesManagementModelResult> rslt = (List<LibrariesManagementModelResult>)libariesManagementService.getAllNewComponent();
//		 }
//
//	}
//
//
//	@Test
//	public void testconfirmComponent() {
//		String entityName = "";
//
//		 Query mockedQuery4 = mock(Query.class);
//		 when(em.createQuery("SELECT v from AuthentificationEntity v where v.id = :p1")).thenReturn(mockedQuery4);
//		 when(mockedQuery4.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery4);
//
//		 Query mockedQuery5 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u where (u.roleEntity.id= 1 or u.roleEntity.id= 5)  and u.deleted=:p1 and u.active=:p2 and u.email LIKE '%nuagetechnologies-tn%' OR u.email LIKE '%nabil-g%'")).thenReturn(mockedQuery5);
//		 when(mockedQuery5.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery5);
//
//		 Query mockedQuery6 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u where (u.roleEntity.id=1 or u.roleEntity.id= 5) and u.deleted=:p1 and u.active=:p2")).thenReturn(mockedQuery6);
//		 when(mockedQuery6.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery6);
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result of the query2
//		 scenario.get(0).add(null);
//		 //Result of the query3
//		 scenario.get(0).add(null);
//		 //Result of the query4
//		 scenario.get(0).add(null);
//		 //Result of the query5
//		 scenario.get(0).add(null);
//		 //Result of the query6
//		 scenario.get(0).add(null);
//		 //Result excpected
//		 scenario.get(0).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(0).add(null);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add("Rail / Racking");
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//		 //Result of the query3
//		 scenario.get(1).add(null);
//		 //Result of the query4
//		 scenario.get(1).add(null);
//		 //Result of the query5
//		 scenario.get(1).add(null);
//		 //Result of the query6
//		 scenario.get(1).add(null);
//		 //Result excpected
//		 scenario.get(1).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(1).add("RailRacking");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 ConfirmComponentRequest parm1 = new ConfirmComponentRequest();
//		 parm1.setMake("abc");
//		 parm1.setModel("cdg");
//		 scenario.get(2).add(parm1);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add("Rail / Racking");
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 scenario.get(2).add(null);
//		 //Result of the query2
//		 scenario.get(2).add(null);
//		 //Result of the query3
//		 scenario.get(2).add(null);
//		 //Result of the query4
//		 scenario.get(2).add(null);
//		 //Result of the query5
//		 scenario.get(2).add(null);
//		 //Result of the query6
//		 scenario.get(2).add(null);
//		 //Result excpected
//		 scenario.get(2).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(2).add("RailRacking");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(parm1);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add("Rail / Racking");
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 scenario.get(3).add(null);
//		 //Result of the query2
//		 scenario.get(3).add(null);
//		 //Result of the query3
//		 scenario.get(3).add(null);
//		 //Result of the query4
//		 AuthentificationEntity auth = new AuthentificationEntity();
//		 auth.setRoleEntity(new RoleEntity());
//		 scenario.get(3).add(auth);
//		 //Result of the query5
//		 scenario.get(3).add(null);
//		 //Result of the query6
//		 scenario.get(3).add(null);
//		 //Result excpected
//		 scenario.get(3).add("Success");
//		 //The value of the Entity name
//		 scenario.get(3).add("RailRacking");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add(parm1);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add("Rail / Racking");
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 //Result of the query1
//		 scenario.get(4).add(null);
//		 //Result of the query2
//		 scenario.get(4).add(null);
//		 //Result of the query3
//		 scenario.get(4).add(null);
//		 //Result of the query4
//		 AuthentificationEntity auth1 = new AuthentificationEntity();
//		 RoleEntity role = new RoleEntity();
//		 role.setId(3);
//		 auth1.setRoleEntity(role);
//		 scenario.get(4).add(auth1);
//		 //Result of the query5
//		 scenario.get(4).add(null);
//		 //Result of the query6
//		 scenario.get(4).add(null);
//		 //Result excpected
//		 scenario.get(4).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(4).add("RailRacking");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(5).add(parm1);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add("Rail / Racking");
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 //Result of the query1
//		 scenario.get(5).add(null);
//		 //Result of the query2
//		 scenario.get(5).add(null);
//		 //Result of the query3
//		 scenario.get(5).add(null);
//		 //Result of the query4
//		 AuthentificationEntity auth2 = new AuthentificationEntity();
//		 auth2.setRoleEntity(role);
//		 auth2.setEmail("malek@nuagetechnologies-tn");
//		 scenario.get(5).add(auth2);
//		 //Result of the query5
//		 List<AuthentificationEntity> listAuth = new ArrayList<AuthentificationEntity>();
//		 listAuth.add(null);
//		 listAuth.add(new AuthentificationEntity());
//		 listAuth.add(auth2);
//		 scenario.get(5).add(listAuth);
//		 //Result of the query6
//		 scenario.get(5).add(listAuth);
//		 //Result excpected
//		 scenario.get(5).add("Success");
//		 //The value of the Entity name
//		 scenario.get(5).add("RailRacking");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(6).add(parm1);
//		 scenario.get(6).add(null);
//		 scenario.get(6).add("Rail / Racking");
//		 scenario.get(6).add(null);
//		 scenario.get(6).add("abc");
//		 scenario.get(6).add("abc");
//		 //Result of the query1
//		 ArrayList<RailRacking> listRail = new ArrayList<RailRacking>();
//		 listRail.add(null);
//		 listRail.add(new RailRacking());
//		 scenario.get(6).add(listRail);
//		 //Result of the query2
//		 scenario.get(6).add(listRail);
//		 //Result of the query3
//		 scenario.get(6).add(null);
//		 //Result of the query4
//		 scenario.get(6).add(auth2);
//		 //Result of the query5
//		 scenario.get(6).add(listAuth);
//		 //Result of the query6
//		 scenario.get(6).add(listAuth);
//		 //Result excpected
//		 scenario.get(6).add("Exist");
//		 //The value of the Entity name
//		 scenario.get(6).add("RailRacking");
//
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(7).add(null);
//		 scenario.get(7).add(null);
//		 scenario.get(7).add("DC - DC System Converter");
//		 scenario.get(7).add(null);
//		 scenario.get(7).add(null);
//		 scenario.get(7).add(null);
//		 //Result of the query1
//		 scenario.get(7).add(null);
//		 //Result of the query2
//		 scenario.get(7).add(null);
//		 //Result of the query3
//		 scenario.get(7).add(null);
//		 //Result of the query4
//		 scenario.get(7).add(null);
//		 //Result of the query5
//		 scenario.get(7).add(null);
//		 //Result of the query6
//		 scenario.get(7).add(null);
//		 //Result excpected
//		 scenario.get(7).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(7).add("DCOptimizerEntity");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(8).add(parm1);
//		 scenario.get(8).add(null);
//		 scenario.get(8).add("DC - DC System Converter");
//		 scenario.get(8).add(null);
//		 scenario.get(8).add(null);
//		 scenario.get(8).add(null);
//		 //Result of the query1
//		 scenario.get(8).add(null);
//		 //Result of the query2
//		 scenario.get(8).add(null);
//		 //Result of the query3
//		 scenario.get(8).add(null);
//		 //Result of the query4
//		 scenario.get(8).add(null);
//		 //Result of the query5
//		 scenario.get(8).add(null);
//		 //Result of the query6
//		 scenario.get(8).add(null);
//		 //Result excpected
//		 scenario.get(8).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(8).add("DCOptimizerEntity");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(9).add(parm1);
//		 scenario.get(9).add(null);
//		 scenario.get(9).add("DC - DC System Converter");
//		 scenario.get(9).add(null);
//		 scenario.get(9).add(null);
//		 scenario.get(9).add(null);
//		 //Result of the query1
//		 scenario.get(9).add(null);
//		 //Result of the query2
//		 scenario.get(9).add(null);
//		 //Result of the query3
//		 scenario.get(9).add(null);
//		 //Result of the query4
//		 scenario.get(9).add(auth);
//		 //Result of the query5
//		 scenario.get(9).add(null);
//		 //Result of the query6
//		 scenario.get(9).add(null);
//		 //Result excpected
//		 scenario.get(9).add("Success");
//		 //The value of the Entity name
//		 scenario.get(9).add("DCOptimizerEntity");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(10).add(parm1);
//		 scenario.get(10).add(null);
//		 scenario.get(10).add("DC - DC System Converter");
//		 scenario.get(10).add(null);
//		 scenario.get(10).add(null);
//		 scenario.get(10).add(null);
//		 //Result of the query1
//		 scenario.get(10).add(null);
//		 //Result of the query2
//		 scenario.get(10).add(null);
//		 //Result of the query3
//		 scenario.get(10).add(null);
//		 //Result of the query4
//		 scenario.get(10).add(auth1);
//		 //Result of the query5
//		 scenario.get(10).add(null);
//		 //Result of the query6
//		 scenario.get(10).add(null);
//		 //Result excpected
//		 scenario.get(10).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(10).add("DCOptimizerEntity");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(11).add(parm1);
//		 scenario.get(11).add(null);
//		 scenario.get(11).add("DC - DC System Converter");
//		 scenario.get(11).add(null);
//		 scenario.get(11).add(null);
//		 scenario.get(11).add(null);
//		 //Result of the query1
//		 scenario.get(11).add(null);
//		 //Result of the query2
//		 scenario.get(11).add(null);
//		 //Result of the query3
//		 scenario.get(11).add(null);
//		 //Result of the query4
//		 scenario.get(11).add(auth2);
//		 //Result of the query5
//		 scenario.get(11).add(listAuth);
//		 //Result of the query6
//		 scenario.get(11).add(listAuth);
//		 //Result excpected
//		 scenario.get(11).add("Success");
//		 //The value of the Entity name
//		 scenario.get(11).add("DCOptimizerEntity");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(12).add(parm1);
//		 scenario.get(12).add(null);
//		 scenario.get(12).add("DC - DC System Converter");
//		 scenario.get(12).add(null);
//		 scenario.get(12).add("abc");
//		 scenario.get(12).add("abc");
//		 //Result of the query1
//		 scenario.get(12).add(listRail);
//		 //Result of the query2
//		 scenario.get(12).add(listRail);
//		 //Result of the query3
//		 scenario.get(12).add(null);
//		 //Result of the query4
//		 scenario.get(12).add(auth2);
//		 //Result of the query5
//		 scenario.get(12).add(listAuth);
//		 //Result of the query6
//		 scenario.get(12).add(listAuth);
//		 //Result excpected
//		 scenario.get(12).add("Exist");
//		 //The value of the Entity name
//		 scenario.get(12).add("DCOptimizerEntity");
//
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(13).add(null);
//		 scenario.get(13).add(null);
//		 scenario.get(13).add("Rail to Roof Connection");
//		 scenario.get(13).add(null);
//		 scenario.get(13).add(null);
//		 scenario.get(13).add(null);
//		 //Result of the query1
//		 scenario.get(13).add(null);
//		 //Result of the query2
//		 scenario.get(13).add(null);
//		 //Result of the query3
//		 scenario.get(13).add(null);
//		 //Result of the query4
//		 scenario.get(13).add(null);
//		 //Result of the query5
//		 scenario.get(13).add(null);
//		 //Result of the query6
//		 scenario.get(13).add(null);
//		 //Result excpected
//		 scenario.get(13).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(13).add("RoofAttachmentsEntity");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(14).add(parm1);
//		 scenario.get(14).add(null);
//		 scenario.get(14).add("Rail to Roof Connection");
//		 scenario.get(14).add(null);
//		 scenario.get(14).add(null);
//		 scenario.get(14).add(null);
//		 //Result of the query1
//		 scenario.get(14).add(null);
//		 //Result of the query2
//		 scenario.get(14).add(null);
//		 //Result of the query3
//		 scenario.get(14).add(null);
//		 //Result of the query4
//		 scenario.get(14).add(null);
//		 //Result of the query5
//		 scenario.get(14).add(null);
//		 //Result of the query6
//		 scenario.get(14).add(null);
//		 //Result excpected
//		 scenario.get(14).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(14).add("RoofAttachmentsEntity");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(15).add(parm1);
//		 scenario.get(15).add(null);
//		 scenario.get(15).add("Rail to Roof Connection");
//		 scenario.get(15).add(null);
//		 scenario.get(15).add(null);
//		 scenario.get(15).add(null);
//		 //Result of the query1
//		 scenario.get(15).add(null);
//		 //Result of the query2
//		 scenario.get(15).add(null);
//		 //Result of the query3
//		 scenario.get(15).add(null);
//		 //Result of the query4
//		 scenario.get(15).add(auth);
//		 //Result of the query5
//		 scenario.get(15).add(null);
//		 //Result of the query6
//		 scenario.get(15).add(null);
//		 //Result excpected
//		 scenario.get(15).add("Success");
//		 //The value of the Entity name
//		 scenario.get(15).add("RoofAttachmentsEntity");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(16).add(parm1);
//		 scenario.get(16).add(null);
//		 scenario.get(16).add("Rail to Roof Connection");
//		 scenario.get(16).add(null);
//		 scenario.get(16).add(null);
//		 scenario.get(16).add(null);
//		 //Result of the query1
//		 scenario.get(16).add(null);
//		 //Result of the query2
//		 scenario.get(16).add(null);
//		 //Result of the query3
//		 scenario.get(16).add(null);
//		 //Result of the query4
//		 scenario.get(16).add(auth1);
//		 //Result of the query5
//		 scenario.get(16).add(null);
//		 //Result of the query6
//		 scenario.get(16).add(null);
//		 //Result excpected
//		 scenario.get(16).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(16).add("RoofAttachmentsEntity");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(17).add(parm1);
//		 scenario.get(17).add(null);
//		 scenario.get(17).add("Rail to Roof Connection");
//		 scenario.get(17).add(null);
//		 scenario.get(17).add(null);
//		 scenario.get(17).add(null);
//		 //Result of the query1
//		 scenario.get(17).add(null);
//		 //Result of the query2
//		 scenario.get(17).add(null);
//		 //Result of the query3
//		 scenario.get(17).add(null);
//		 //Result of the query4
//		 scenario.get(17).add(auth2);
//		 //Result of the query5
//		 scenario.get(17).add(listAuth);
//		 //Result of the query6
//		 scenario.get(17).add(listAuth);
//		 //Result excpected
//		 scenario.get(17).add("Success");
//		 //The value of the Entity name
//		 scenario.get(17).add("RoofAttachmentsEntity");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(18).add(parm1);
//		 scenario.get(18).add(null);
//		 scenario.get(18).add("Rail to Roof Connection");
//		 scenario.get(18).add(null);
//		 scenario.get(18).add("abc");
//		 scenario.get(18).add("abc");
//		 //Result of the query1
//		 scenario.get(18).add(listRail);
//		 //Result of the query2
//		 scenario.get(18).add(listRail);
//		 //Result of the query3
//		 scenario.get(18).add(null);
//		 //Result of the query4
//		 scenario.get(18).add(auth2);
//		 //Result of the query5
//		 scenario.get(18).add(listAuth);
//		 //Result of the query6
//		 scenario.get(18).add(listAuth);
//		 //Result excpected
//		 scenario.get(18).add("Exist");
//		 //The value of the Entity name
//		 scenario.get(18).add("RoofAttachmentsEntity");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(19).add(null);
//		 scenario.get(19).add(null);
//		 scenario.get(19).add("DC Combiner / Disconnect");
//		 scenario.get(19).add(null);
//		 scenario.get(19).add(null);
//		 scenario.get(19).add(null);
//		 //Result of the query1
//		 scenario.get(19).add(null);
//		 //Result of the query2
//		 scenario.get(19).add(null);
//		 //Result of the query3
//		 scenario.get(19).add(null);
//		 //Result of the query4
//		 scenario.get(19).add(null);
//		 //Result of the query5
//		 scenario.get(19).add(null);
//		 //Result of the query6
//		 scenario.get(19).add(null);
//		 //Result excpected
//		 scenario.get(19).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(19).add("DCCombinerDisconnectEntity");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(20).add(parm1);
//		 scenario.get(20).add(null);
//		 scenario.get(20).add("DC Combiner / Disconnect");
//		 scenario.get(20).add(null);
//		 scenario.get(20).add(null);
//		 scenario.get(20).add(null);
//		 //Result of the query1
//		 scenario.get(20).add(null);
//		 //Result of the query2
//		 scenario.get(20).add(null);
//		 //Result of the query3
//		 scenario.get(20).add(null);
//		 //Result of the query4
//		 scenario.get(20).add(null);
//		 //Result of the query5
//		 scenario.get(20).add(null);
//		 //Result of the query6
//		 scenario.get(20).add(null);
//		 //Result excpected
//		 scenario.get(20).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(20).add("DCCombinerDisconnectEntity");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(21).add(parm1);
//		 scenario.get(21).add(null);
//		 scenario.get(21).add("DC Combiner / Disconnect");
//		 scenario.get(21).add(null);
//		 scenario.get(21).add(null);
//		 scenario.get(21).add(null);
//		 //Result of the query1
//		 scenario.get(21).add(null);
//		 //Result of the query2
//		 scenario.get(21).add(null);
//		 //Result of the query3
//		 scenario.get(21).add(null);
//		 //Result of the query4
//		 scenario.get(21).add(auth);
//		 //Result of the query5
//		 scenario.get(21).add(null);
//		 //Result of the query6
//		 scenario.get(21).add(null);
//		 //Result excpected
//		 scenario.get(21).add("Success");
//		 //The value of the Entity name
//		 scenario.get(21).add("DCCombinerDisconnectEntity");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(22).add(parm1);
//		 scenario.get(22).add(null);
//		 scenario.get(22).add("DC Combiner / Disconnect");
//		 scenario.get(22).add(null);
//		 scenario.get(22).add(null);
//		 scenario.get(22).add(null);
//		 //Result of the query1
//		 scenario.get(22).add(null);
//		 //Result of the query2
//		 scenario.get(22).add(null);
//		 //Result of the query3
//		 scenario.get(22).add(null);
//		 //Result of the query4
//		 scenario.get(22).add(auth1);
//		 //Result of the query5
//		 scenario.get(22).add(null);
//		 //Result of the query6
//		 scenario.get(22).add(null);
//		 //Result excpected
//		 scenario.get(22).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(22).add("DCCombinerDisconnectEntity");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(23).add(parm1);
//		 scenario.get(23).add(null);
//		 scenario.get(23).add("DC Combiner / Disconnect");
//		 scenario.get(23).add(null);
//		 scenario.get(23).add(null);
//		 scenario.get(23).add(null);
//		 //Result of the query1
//		 scenario.get(23).add(null);
//		 //Result of the query2
//		 scenario.get(23).add(null);
//		 //Result of the query3
//		 scenario.get(23).add(null);
//		 //Result of the query4
//		 scenario.get(23).add(auth2);
//		 //Result of the query5
//		 scenario.get(23).add(listAuth);
//		 //Result of the query6
//		 scenario.get(23).add(listAuth);
//		 //Result excpected
//		 scenario.get(23).add("Success");
//		 //The value of the Entity name
//		 scenario.get(23).add("DCCombinerDisconnectEntity");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(24).add(parm1);
//		 scenario.get(24).add(null);
//		 scenario.get(24).add("DC Combiner / Disconnect");
//		 scenario.get(24).add(null);
//		 scenario.get(24).add("abc");
//		 scenario.get(24).add("abc");
//		 //Result of the query1
//		 scenario.get(24).add(listRail);
//		 //Result of the query2
//		 scenario.get(24).add(listRail);
//		 //Result of the query3
//		 scenario.get(24).add(null);
//		 //Result of the query4
//		 scenario.get(24).add(auth2);
//		 //Result of the query5
//		 scenario.get(24).add(listAuth);
//		 //Result of the query6
//		 scenario.get(24).add(listAuth);
//		 //Result excpected
//		 scenario.get(24).add("Exist");
//		 //The value of the Entity name
//		 scenario.get(24).add("DCCombinerDisconnectEntity");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(25).add(null);
//		 scenario.get(25).add(null);
//		 scenario.get(25).add("Junction Box");
//		 scenario.get(25).add(null);
//		 scenario.get(25).add(null);
//		 scenario.get(25).add(null);
//		 //Result of the query1
//		 scenario.get(25).add(null);
//		 //Result of the query2
//		 scenario.get(25).add(null);
//		 //Result of the query3
//		 scenario.get(25).add(null);
//		 //Result of the query4
//		 scenario.get(25).add(null);
//		 //Result of the query5
//		 scenario.get(25).add(null);
//		 //Result of the query6
//		 scenario.get(25).add(null);
//		 //Result excpected
//		 scenario.get(25).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(25).add("DCCombinerDisconnectEntity");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(26).add(parm1);
//		 scenario.get(26).add(null);
//		 scenario.get(26).add("Junction Box");
//		 scenario.get(26).add(null);
//		 scenario.get(26).add(null);
//		 scenario.get(26).add(null);
//		 //Result of the query1
//		 scenario.get(26).add(null);
//		 //Result of the query2
//		 scenario.get(26).add(null);
//		 //Result of the query3
//		 scenario.get(26).add(null);
//		 //Result of the query4
//		 scenario.get(26).add(null);
//		 //Result of the query5
//		 scenario.get(26).add(null);
//		 //Result of the query6
//		 scenario.get(26).add(null);
//		 //Result excpected
//		 scenario.get(26).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(26).add("DCCombinerDisconnectEntity");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(27).add(parm1);
//		 scenario.get(27).add(null);
//		 scenario.get(27).add("Junction Box");
//		 scenario.get(27).add(null);
//		 scenario.get(27).add(null);
//		 scenario.get(27).add(null);
//		 //Result of the query1
//		 scenario.get(27).add(null);
//		 //Result of the query2
//		 scenario.get(27).add(null);
//		 //Result of the query3
//		 scenario.get(27).add(null);
//		 //Result of the query4
//		 scenario.get(27).add(auth);
//		 //Result of the query5
//		 scenario.get(27).add(null);
//		 //Result of the query6
//		 scenario.get(27).add(null);
//		 //Result excpected
//		 scenario.get(27).add("Success");
//		 //The value of the Entity name
//		 scenario.get(27).add("DCCombinerDisconnectEntity");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(28).add(parm1);
//		 scenario.get(28).add(null);
//		 scenario.get(28).add("Junction Box");
//		 scenario.get(28).add(null);
//		 scenario.get(28).add(null);
//		 scenario.get(28).add(null);
//		 //Result of the query1
//		 scenario.get(28).add(null);
//		 //Result of the query2
//		 scenario.get(28).add(null);
//		 //Result of the query3
//		 scenario.get(28).add(null);
//		 //Result of the query4
//		 scenario.get(28).add(auth1);
//		 //Result of the query5
//		 scenario.get(28).add(null);
//		 //Result of the query6
//		 scenario.get(28).add(null);
//		 //Result excpected
//		 scenario.get(28).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(28).add("DCCombinerDisconnectEntity");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(29).add(parm1);
//		 scenario.get(29).add(null);
//		 scenario.get(29).add("Junction Box");
//		 scenario.get(29).add(null);
//		 scenario.get(29).add(null);
//		 scenario.get(29).add(null);
//		 //Result of the query1
//		 scenario.get(29).add(null);
//		 //Result of the query2
//		 scenario.get(29).add(null);
//		 //Result of the query3
//		 scenario.get(29).add(null);
//		 //Result of the query4
//		 scenario.get(29).add(auth2);
//		 //Result of the query5
//		 scenario.get(29).add(listAuth);
//		 //Result of the query6
//		 scenario.get(29).add(listAuth);
//		 //Result excpected
//		 scenario.get(29).add("Success");
//		 //The value of the Entity name
//		 scenario.get(29).add("DCCombinerDisconnectEntity");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(30).add(parm1);
//		 scenario.get(30).add(null);
//		 scenario.get(30).add("Junction Box");
//		 scenario.get(30).add(null);
//		 scenario.get(30).add("abc");
//		 scenario.get(30).add("abc");
//		 //Result of the query1
//		 scenario.get(30).add(listRail);
//		 //Result of the query2
//		 scenario.get(30).add(listRail);
//		 //Result of the query3
//		 scenario.get(30).add(null);
//		 //Result of the query4
//		 scenario.get(30).add(auth2);
//		 //Result of the query5
//		 scenario.get(30).add(listAuth);
//		 //Result of the query6
//		 scenario.get(30).add(listAuth);
//		 //Result excpected
//		 scenario.get(30).add("Exist");
//		 //The value of the Entity name
//		 scenario.get(30).add("DCCombinerDisconnectEntity");
//
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(31).add(null);
//		 scenario.get(31).add(null);
//		 scenario.get(31).add("AC Combiner");
//		 scenario.get(31).add(null);
//		 scenario.get(31).add(null);
//		 scenario.get(31).add(null);
//		 //Result of the query1
//		 scenario.get(31).add(null);
//		 //Result of the query2
//		 scenario.get(31).add(null);
//		 //Result of the query3
//		 scenario.get(31).add(null);
//		 //Result of the query4
//		 scenario.get(31).add(null);
//		 //Result of the query5
//		 scenario.get(31).add(null);
//		 //Result of the query6
//		 scenario.get(31).add(null);
//		 //Result excpected
//		 scenario.get(31).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(31).add("ACDisconnect");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(32).add(parm1);
//		 scenario.get(32).add(null);
//		 scenario.get(32).add("AC Combiner");
//		 scenario.get(32).add(null);
//		 scenario.get(32).add(null);
//		 scenario.get(32).add(null);
//		 //Result of the query1
//		 scenario.get(32).add(null);
//		 //Result of the query2
//		 scenario.get(32).add(null);
//		 //Result of the query3
//		 scenario.get(32).add(null);
//		 //Result of the query4
//		 scenario.get(32).add(null);
//		 //Result of the query5
//		 scenario.get(32).add(null);
//		 //Result of the query6
//		 scenario.get(32).add(null);
//		 //Result excpected
//		 scenario.get(32).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(32).add("ACDisconnect");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(33).add(parm1);
//		 scenario.get(33).add(null);
//		 scenario.get(33).add("AC Combiner");
//		 scenario.get(33).add(null);
//		 scenario.get(33).add(null);
//		 scenario.get(33).add(null);
//		 //Result of the query1
//		 scenario.get(33).add(null);
//		 //Result of the query2
//		 scenario.get(33).add(null);
//		 //Result of the query3
//		 scenario.get(33).add(null);
//		 //Result of the query4
//		 scenario.get(33).add(auth);
//		 //Result of the query5
//		 scenario.get(33).add(null);
//		 //Result of the query6
//		 scenario.get(33).add(null);
//		 //Result excpected
//		 scenario.get(33).add("Success");
//		 //The value of the Entity name
//		 scenario.get(33).add("ACDisconnect");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(34).add(parm1);
//		 scenario.get(34).add(null);
//		 scenario.get(34).add("AC Combiner");
//		 scenario.get(34).add(null);
//		 scenario.get(34).add(null);
//		 scenario.get(34).add(null);
//		 //Result of the query1
//		 scenario.get(34).add(null);
//		 //Result of the query2
//		 scenario.get(34).add(null);
//		 //Result of the query3
//		 scenario.get(34).add(null);
//		 //Result of the query4
//		 scenario.get(34).add(auth1);
//		 //Result of the query5
//		 scenario.get(34).add(null);
//		 //Result of the query6
//		 scenario.get(34).add(null);
//		 //Result excpected
//		 scenario.get(34).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(34).add("ACDisconnect");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(35).add(parm1);
//		 scenario.get(35).add(null);
//		 scenario.get(35).add("AC Combiner");
//		 scenario.get(35).add(null);
//		 scenario.get(35).add(null);
//		 scenario.get(35).add(null);
//		 //Result of the query1
//		 scenario.get(35).add(null);
//		 //Result of the query2
//		 scenario.get(35).add(null);
//		 //Result of the query3
//		 scenario.get(35).add(null);
//		 //Result of the query4
//		 scenario.get(35).add(auth2);
//		 //Result of the query5
//		 scenario.get(35).add(listAuth);
//		 //Result of the query6
//		 scenario.get(35).add(listAuth);
//		 //Result excpected
//		 scenario.get(35).add("Success");
//		 //The value of the Entity name
//		 scenario.get(35).add("ACDisconnect");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(36).add(parm1);
//		 scenario.get(36).add(null);
//		 scenario.get(36).add("AC Combiner");
//		 scenario.get(36).add(null);
//		 scenario.get(36).add("abc");
//		 scenario.get(36).add("abc");
//		 //Result of the query1
//		 scenario.get(36).add(listRail);
//		 //Result of the query2
//		 scenario.get(36).add(listRail);
//		 //Result of the query3
//		 scenario.get(36).add(null);
//		 //Result of the query4
//		 scenario.get(36).add(auth2);
//		 //Result of the query5
//		 scenario.get(36).add(listAuth);
//		 //Result of the query6
//		 scenario.get(36).add(listAuth);
//		 //Result excpected
//		 scenario.get(36).add("Exist");
//		 //The value of the Entity name
//		 scenario.get(36).add("ACDisconnect");
//
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(37).add(null);
//		 scenario.get(37).add(null);
//		 scenario.get(37).add("Inverter");
//		 scenario.get(37).add(null);
//		 scenario.get(37).add(null);
//		 scenario.get(37).add(null);
//		 //Result of the query1
//		 scenario.get(37).add(null);
//		 //Result of the query2
//		 scenario.get(37).add(null);
//		 //Result of the query3
//		 scenario.get(37).add(null);
//		 //Result of the query4
//		 scenario.get(37).add(null);
//		 //Result of the query5
//		 scenario.get(37).add(null);
//		 //Result of the query6
//		 scenario.get(37).add(null);
//		 //Result excpected
//		 scenario.get(37).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(37).add("Inverters");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(38).add(parm1);
//		 scenario.get(38).add(null);
//		 scenario.get(38).add("Inverter");
//		 scenario.get(38).add(null);
//		 scenario.get(38).add(null);
//		 scenario.get(38).add(null);
//		 //Result of the query1
//		 scenario.get(38).add(null);
//		 //Result of the query2
//		 scenario.get(38).add(null);
//		 //Result of the query3
//		 scenario.get(38).add(null);
//		 //Result of the query4
//		 scenario.get(38).add(null);
//		 //Result of the query5
//		 scenario.get(38).add(null);
//		 //Result of the query6
//		 scenario.get(38).add(null);
//		 //Result excpected
//		 scenario.get(38).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(38).add("Inverters");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(39).add(parm1);
//		 scenario.get(39).add(null);
//		 scenario.get(39).add("Inverter");
//		 scenario.get(39).add(null);
//		 scenario.get(39).add(null);
//		 scenario.get(39).add(null);
//		 //Result of the query1
//		 scenario.get(39).add(null);
//		 //Result of the query2
//		 scenario.get(39).add(null);
//		 //Result of the query3
//		 scenario.get(39).add(null);
//		 //Result of the query4
//		 scenario.get(39).add(auth);
//		 //Result of the query5
//		 scenario.get(39).add(null);
//		 //Result of the query6
//		 scenario.get(39).add(null);
//		 //Result excpected
//		 scenario.get(39).add("Success");
//		 //The value of the Entity name
//		 scenario.get(39).add("Inverters");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(40).add(parm1);
//		 scenario.get(40).add(null);
//		 scenario.get(40).add("Inverter");
//		 scenario.get(40).add(null);
//		 scenario.get(40).add(null);
//		 scenario.get(40).add(null);
//		 //Result of the query1
//		 scenario.get(40).add(null);
//		 //Result of the query2
//		 scenario.get(40).add(null);
//		 //Result of the query3
//		 scenario.get(40).add(null);
//		 //Result of the query4
//		 scenario.get(40).add(auth1);
//		 //Result of the query5
//		 scenario.get(40).add(null);
//		 //Result of the query6
//		 scenario.get(40).add(null);
//		 //Result excpected
//		 scenario.get(40).add("Fail");
//		 //The value of the Entity name
//		 scenario.get(40).add("Inverters");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(41).add(parm1);
//		 scenario.get(41).add(null);
//		 scenario.get(41).add("Inverter");
//		 scenario.get(41).add(null);
//		 scenario.get(41).add(null);
//		 scenario.get(41).add(null);
//		 //Result of the query1
//		 scenario.get(41).add(null);
//		 //Result of the query2
//		 scenario.get(41).add(null);
//		 //Result of the query3
//		 scenario.get(41).add(null);
//		 //Result of the query4
//		 scenario.get(41).add(auth2);
//		 //Result of the query5
//		 scenario.get(41).add(listAuth);
//		 //Result of the query6
//		 scenario.get(41).add(listAuth);
//		 //Result excpected
//		 scenario.get(41).add("Success");
//		 //The value of the Entity name
//		 scenario.get(41).add("Inverters");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(42).add(parm1);
//		 scenario.get(42).add(null);
//		 scenario.get(42).add("Inverter");
//		 scenario.get(42).add(null);
//		 scenario.get(42).add("abc");
//		 scenario.get(42).add("abc");
//		 //Result of the query1
//		 scenario.get(42).add(listRail);
//		 //Result of the query2
//		 scenario.get(42).add(listRail);
//		 //Result of the query3
//		 scenario.get(42).add(null);
//		 //Result of the query4
//		 scenario.get(42).add(auth2);
//		 //Result of the query5
//		 scenario.get(42).add(listAuth);
//		 //Result of the query6
//		 scenario.get(42).add(listAuth);
//		 //Result excpected
//		 scenario.get(42).add("Exist");
//		 //The value of the Entity name
//		 scenario.get(42).add("Inverters");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("confirmComponent [ " + i + " ]");
//			entityName = (String) scenario.get(i).get(13);
//			Query mockedQuery1 = mock(Query.class);
//			when(em.createQuery("SELECT v from " + entityName + " v where v.manufacturer = :p1 and v.model = :p2"))
//					.thenReturn(mockedQuery1);
//			when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//			Query mockedQuery2 = mock(Query.class);
//			when(em.createQuery("SELECT v from " + entityName + " v where v.make = :p1 and v.model = :p2"))
//					.thenReturn(mockedQuery2);
//			when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//			Query mockedQuery3 = mock(Query.class);
//			when(em.createQuery("SELECT v from " + entityName + " v where v.id = :p1")).thenReturn(mockedQuery3);
//			when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(6));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(7));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(8));
//			when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(9));
//			when(mockedQuery5.getResultList()).thenReturn((List) scenario.get(i).get(10));
//			when(mockedQuery6.getResultList()).thenReturn((List) scenario.get(i).get(11));
//
//
//					libariesManagementService.confirmComponent((ConfirmComponentRequest) scenario.get(i).get(0),
//							(Integer) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(Integer) scenario.get(i).get(3), (String) scenario.get(i).get(4),
//							(String) scenario.get(i).get(5));
//
//		}
//
//
//	}
//
//	@Test
//	public void testgetComponentInfo() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery(Mockito.anyString())).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result excpected
//		 scenario.get(0).add(new ComponentTypeRequest());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add("DC - DC System Converter");
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result excpected
//		 scenario.get(1).add(new ComponentTypeRequest());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(null);
//		 scenario.get(2).add("DC - DC System Converter");
//		 //Result of the query1
//		 DCOptimizerEntity dc =new DCOptimizerEntity();
//		 scenario.get(2).add(dc);
//		 //Result excpected
//		 ComponentTypeRequest exp = new ComponentTypeRequest();
//		 exp.setdCOptimizerEntity(dc);
//		 scenario.get(2).add(exp);
//
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getComponentInfo [ "+i+" ]");
//			  when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			libariesManagementService.getComponentInfo((Integer)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//		 }
//
//
//
//	}
//
//
//}
