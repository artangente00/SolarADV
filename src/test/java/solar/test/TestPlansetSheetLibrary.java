//package solar.test;
//
//import static org.junit.Assert.*;
//
//import javax.persistence.EntityManager;
//
//import org.junit.Test;
//import org.mockito.Mock;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//
//import org.junit.Before;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.PlansetSheetEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.model.SearchModulResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.LibariesManagementService;
//import com.PlayGroundAdv.Solar.Services.MailingService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.PlansetSheetLibrary;
//
//import junit.framework.Assert;
//
//
//public class TestPlansetSheetLibrary {
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
//
//	@Spy
//	CheckValueTypesService checkValueTypesService;
//	@InjectMocks
//	PlansetSheetLibrary plansetSheetLibrary = new PlansetSheetLibrary();
//
//	@Before
//	public void setupMock() {
//		plansetSheetLibrary = new PlansetSheetLibrary();
//		MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testgetPlansetSheets() {
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u from PlansetSheetEntity u WHERE u.type = :p1 ORDER BY u.pdfName"))
//				.thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<PlansetSheetEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(new ArrayList<PlansetSheetEntity>());
//		// Result excpected
//		scenario.get(1).add(new ArrayList<PlansetSheetEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("");
//		// Result of the query1
//		ArrayList<PlansetSheetEntity> list = new ArrayList<PlansetSheetEntity>();
//		list.add(null);
//		list.add(new PlansetSheetEntity());
//		scenario.get(2).add(list);
//		// Result excpected
//		scenario.get(2).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//		    when(mockedQuery.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			System.out.println("getPlansetSheets [ " + i + " ]");
//			ArrayList<PlansetSheetEntity> rslt = (ArrayList<PlansetSheetEntity>) plansetSheetLibrary
//					.getPlansetSheets((String) scenario.get(i).get(0));
//		}
//
//	}
//
//	@Test
//	public void testsearchPlansetSheets() {
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u from PlansetSheetEntity u WHERE u.type = :p1 AND u.pdfName = :p2"))
//				.thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<PlansetSheetEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(new ArrayList<PlansetSheetEntity>());
//		// Result excpected
//		scenario.get(1).add(new ArrayList<PlansetSheetEntity>());
//
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		ArrayList<PlansetSheetEntity> list = new ArrayList<PlansetSheetEntity>();
//		list.add(null);
//		list.add(new PlansetSheetEntity());
//		scenario.get(2).add(list);
//		// Result excpected
//		scenario.get(2).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("searchPlansetSheets [ " + i + " ]");
//		    when(mockedQuery.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			ArrayList<PlansetSheetEntity> rslt = (ArrayList<PlansetSheetEntity>) plansetSheetLibrary
//					.searchPlansetSheets((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		}
//
//	}
//
//	@Test
//	public void testeditPlansetSheets() throws IOException {
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u from PlansetSheetEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("fail");
//		// Result of the query2
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("fail");
//		// Result of the query2
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("fail");
//		// Result of the query2
//		scenario.get(2).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("");
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("fail");
//		// Result of the query2
//		scenario.get(3).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add("123");
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("Done");
//		// Result of the query2
//		scenario.get(4).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add("123");
//		scenario.get(5).add("123");
//		// Result of the query1
//		scenario.get(5).add(new PlansetSheetEntity());
//		// Result excpected
//		scenario.get(5).add("fail");
//		// Result of the query2
//		scenario.get(5).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		FileInputStream inputFile = new FileInputStream("C:\\files\\xxx.txt");
//		MockMultipartFile file = new MockMultipartFile("file", "NameOfTheFile", "multipart/form-data", inputFile);
//		scenario.get(6).add(file);
//		scenario.get(6).add(null);
//		scenario.get(6).add("123");
//		scenario.get(6).add("123");
//		// Result of the query1
//		scenario.get(6).add(new PlansetSheetEntity());
//		// Result excpected
//		scenario.get(6).add("fail");
//		// Result of the query2
//		scenario.get(6).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(7).add(file);
//		scenario.get(7).add(null);
//		scenario.get(7).add("123");
//		scenario.get(7).add("123");
//		// Result of the query1
//		scenario.get(7).add(new PlansetSheetEntity());
//		// Result excpected
//		scenario.get(7).add("Done");
//		// Result of the query2
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setRoleEntity(new RoleEntity());
//		scenario.get(7).add(auth);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(8).add(file);
//		scenario.get(8).add(null);
//		scenario.get(8).add("123");
//		scenario.get(8).add("123");
//		// Result of the query1
//		PlansetSheetEntity plan =  new PlansetSheetEntity();
//		plan.setType("CEC");
//		scenario.get(8).add(plan);
//		// Result excpected
//		scenario.get(8).add("Done");
//		// Result of the query2
//		scenario.get(8).add(auth);
//
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editPlansetSheets [ " + i + " ]");
//			when(mockedQuery.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(6));
//
//		}
//	}
//
//	@Test
//	public void testsetLastUpdate() {
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u from PlansetSheetEntity u"))
//				.thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<PlansetSheetEntity> list = new ArrayList<PlansetSheetEntity>();
//		list.add(null);
//		list.add(new PlansetSheetEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("setLastUpdate [ " + i + " ]");
//		    when(mockedQuery.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			plansetSheetLibrary.setLastUpdate();
//		}
//	}
//
//
//
//}
