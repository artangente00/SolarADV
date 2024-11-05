//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import javax.persistence.TypedQuery;
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
//import com.PlayGroundAdv.Solar.Entity.ACCombinerSLC;
//import com.PlayGroundAdv.Solar.Entity.ACDisconnect;
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.BackFeedSolarOCPD;
//import com.PlayGroundAdv.Solar.Entity.DCCombinerDisconnectEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.model.DcCombinerorDisconnectModel;
//import com.PlayGroundAdv.Solar.model.SearchBackFeedSolarOCPDRequest;
//import com.PlayGroundAdv.Solar.model.SearchBackFeedSolarOCPDResult;
//import com.PlayGroundAdv.Solar.Services.AcDisconnectLibraryService;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetAlldcCombinerOrDiService;
//import com.PlayGroundAdv.Solar.Services.GetBackFeedSolarOCPDLibraryService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//
//public class TestGetBackFeedSolarOCPDLibraryService {
//
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
//	@Mock
//	private CriteriaBuilder criteriaBuilder;
//
//	@Mock
//    private Root root;
//
//	@Spy
//	CheckValueTypesService checkValueTypesService;
//	@InjectMocks
//	GetBackFeedSolarOCPDLibraryService getBackFeedSolarOCPDLibraryService = new GetBackFeedSolarOCPDLibraryService();
//
//
//    @Before
//	public void setupMock() {
//    	getBackFeedSolarOCPDLibraryService = new GetBackFeedSolarOCPDLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testsearchBackFeedSolarOCPD() {
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(SearchBackFeedSolarOCPDResult.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(BackFeedSolarOCPD.class)).thenReturn(root);
//        when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//
//        TypedQuery mockedQuery1 = mock(TypedQuery.class);
//        when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//        when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//        when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//
//
//	     List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 //Result of query1
//			 scenario.get(0).add(null);
//			//Excpected Result
//			 scenario.get(0).add(new ArrayList<SearchBackFeedSolarOCPDResult>());
//
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 scenario.get(1).add("");
//			 scenario.get(1).add("");
//			 //Result of query1
//			 scenario.get(1).add(null);
//			//Excpected Result
//			 scenario.get(1).add(new ArrayList<SearchBackFeedSolarOCPDResult>());
//
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 SearchBackFeedSolarOCPDRequest sc = new SearchBackFeedSolarOCPDRequest();
//			 sc.setBackFeed(125);
//			 sc.setIsDel(true);
//			 scenario.get(2).add(sc);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add("123");
//			 scenario.get(2).add("123");
//			 //Result of query1
//			 scenario.get(2).add(null);
//			//Excpected Result
//			 scenario.get(2).add(new ArrayList<SearchBackFeedSolarOCPDResult>());
//
//
//			 for(int i=0;i<scenario.size();i++) {
//				 System.out.println("searchBackFeedSolarOCPD [ "+i+" ]"+scenario.get(i).get(4));
//			     ArrayList<SearchBackFeedSolarOCPDResult> result = (ArrayList<SearchBackFeedSolarOCPDResult>) getBackFeedSolarOCPDLibraryService.searchBackFeedSolarOCPD((SearchBackFeedSolarOCPDRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1), (String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
//	             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//
//			 }
//
//
//	}
//
//	@Test
//	public void testgetNumberSearchBackFeedSolarOCPD() {
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(BackFeedSolarOCPD.class)).thenReturn(root);
//        when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//
//        TypedQuery mockedQuery1 = mock(TypedQuery.class);
//        when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//        List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of query1
//		 scenario.get(1).add(8L);
//		//Excpected Result
//		 scenario.get(1).add(8L);
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("searchBackFeedSolarOCPD [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//	       getBackFeedSolarOCPDLibraryService.getNumberSearchBackFeedSolarOCPD((SearchBackFeedSolarOCPDRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		 }
//
//	}
//
//	@Test
//	public void testgetBackFeedSolarOCPDLibrary() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from BackFeedSolarOCPD u order by u.backFeed")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//	     List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 //Result of query1
//			 scenario.get(0).add(null);
//			//Excpected Result
//			 scenario.get(0).add(new ArrayList<SearchBackFeedSolarOCPDResult>());
//
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(1).add("");
//			 scenario.get(1).add("");
//			 scenario.get(1).add("");
//			 //Result of query1
//			 scenario.get(1).add(null);
//			//Excpected Result
//			 scenario.get(1).add(new ArrayList<SearchBackFeedSolarOCPDResult>());
//
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(2).add("123");
//			 scenario.get(2).add("123");
//			 scenario.get(2).add("123");
//			 //Result of query1
//			 scenario.get(2).add(null);
//			//Excpected Result
//			 scenario.get(2).add(new ArrayList<SearchBackFeedSolarOCPDResult>());
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(3).add("123");
//			 scenario.get(3).add("123");
//			 scenario.get(3).add("123");
//			 //Result of query1
//			 scenario.get(3).add(new ArrayList<BackFeedSolarOCPD>());
//			//Excpected Result
//			 scenario.get(3).add(new ArrayList<SearchBackFeedSolarOCPDResult>());
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(4).add("123");
//			 scenario.get(4).add("123");
//			 scenario.get(4).add("123");
//			 //Result of query1
//			 ArrayList<BackFeedSolarOCPD> list =  new ArrayList<BackFeedSolarOCPD>();
//			 list.add(null);
//			 list.add(new BackFeedSolarOCPD());
//			 scenario.get(4).add(list);
//			//Excpected Result
//			 ArrayList<SearchBackFeedSolarOCPDResult> listExp = new ArrayList<SearchBackFeedSolarOCPDResult>();
//			 listExp.add(new SearchBackFeedSolarOCPDResult());
//			 listExp.add(new SearchBackFeedSolarOCPDResult());
//			 scenario.get(4).add(listExp);
//
//			 for(int i=0; i<scenario.size();i++) {
//				 System.out.println("getBackFeedSolarOCPDLibrary [ "+i+" ]");
//	             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//	             ArrayList<SearchBackFeedSolarOCPDResult> result = ((ArrayList<SearchBackFeedSolarOCPDResult>) getBackFeedSolarOCPDLibraryService.getBackFeedSolarOCPDLibrary((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2)));
//
//
//
//			 }
//
//	}
//
//	@Test
//	public void testeditBackFeedSolarOCPDNotification() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//         List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add("123");
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of query1
//		 scenario.get(1).add(new AuthentificationEntity());
//		//Excpected Result
//		 scenario.get(1).add("Success");
//
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("editBackFeedSolarOCPDNotification [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//             getBackFeedSolarOCPDLibraryService.editBackFeedSolarOCPDNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	}
//
//	@Test
//	public void testaddBackFeedSolarOCPD() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT  u.backFeed from BackFeedSolarOCPD u where u.backFeed=:p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//	       List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 //Result of query1
//			 scenario.get(0).add(null);
//			 //Result of query2
//			 scenario.get(0).add(null);
//			//Excpected Result
//			 scenario.get(0).add("error");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(new SearchBackFeedSolarOCPDResult());
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 //Result of query1
//			 scenario.get(1).add(null);
//			 //Result of query2
//			 scenario.get(1).add(null);
//			//Excpected Result
//			 scenario.get(1).add("error");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(new SearchBackFeedSolarOCPDResult());
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 //Result of query1
//			 scenario.get(2).add(new ArrayList<BackFeedSolarOCPD>());
//			 //Result of query2
//			 scenario.get(2).add(null);
//			//Excpected Result
//			 scenario.get(2).add("error");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(3).add("");
//			 scenario.get(3).add(new SearchBackFeedSolarOCPDResult());
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 //Result of query1
//			 scenario.get(3).add(new ArrayList<BackFeedSolarOCPD>());
//			 //Result of query2
//			 scenario.get(3).add(null);
//			//Excpected Result
//			 scenario.get(3).add("error");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(4).add("123");
//			 scenario.get(4).add(new SearchBackFeedSolarOCPDResult());
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 //Result of query1
//			 scenario.get(4).add(new ArrayList<BackFeedSolarOCPD>());
//			 //Result of query2
//			 scenario.get(4).add(null);
//			//Excpected Result
//			 scenario.get(4).add("error");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(5).add("123");
//			 scenario.get(5).add(new SearchBackFeedSolarOCPDResult());
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 //Result of query1
//			 scenario.get(5).add(new ArrayList<BackFeedSolarOCPD>());
//			 //Result of query2
//			 AuthentificationEntity auth = new AuthentificationEntity();
//			 auth.setRoleEntity(new RoleEntity());
//			 scenario.get(5).add(auth);
//			//Excpected Result
//			 scenario.get(5).add("Done");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(6).add("123");
//			 scenario.get(6).add(new SearchBackFeedSolarOCPDResult());
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 //Result of query1
//			 ArrayList<BackFeedSolarOCPD> list = new ArrayList<BackFeedSolarOCPD>();
//			 list.add(null);
//			 list.add(new BackFeedSolarOCPD());
//			 scenario.get(6).add(list);
//			 //Result of query2
//			 scenario.get(6).add(auth);
//			//Excpected Result
//			 scenario.get(6).add("BackFeedSolarOCPD already exists in data sources");
//
//			 for(int i=0; i<scenario.size();i++) {
//				 System.out.println("addBackFeedSolarOCPD [ "+i+" ]");
//	            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(5));
//	            when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(6));
//	           getBackFeedSolarOCPDLibraryService.addBackFeedSolarOCPD((String)scenario.get(i).get(0),(SearchBackFeedSolarOCPDResult)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4));
//
//			 }
//	}
//
//	@Test
//	public void testeditBackFeedSolarOCPD() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from BackFeedSolarOCPD u WHERE u.backFeed = :p1 AND u.isDeleted=false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 GetBackFeedSolarOCPDLibraryService getBackFeedSolarOCPDLibraryService2 = Mockito.spy(getBackFeedSolarOCPDLibraryService);
//
//
//       List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("fail");
//		//Result of query1 singleResult
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(new SearchBackFeedSolarOCPDResult());
//		 scenario.get(1).add(null);
//		 //Result of query1
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add("fail");
//		//Result of query1 singleResult
//		 scenario.get(1).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(new SearchBackFeedSolarOCPDResult());
//		 scenario.get(2).add(null);
//		 //Result of query1
//		 scenario.get(2).add(new ArrayList<BackFeedSolarOCPD>());
//		//Excpected Result
//		 scenario.get(2).add("success");
//		//Result of query1 singleResult
//		 scenario.get(2).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(new SearchBackFeedSolarOCPDResult());
//		 scenario.get(3).add(null);
//		 //Result of query1
//		 ArrayList<BackFeedSolarOCPD> list = new ArrayList<BackFeedSolarOCPD>();
//		 list.add(null);
//		 list.add(new BackFeedSolarOCPD());
//		 scenario.get(3).add(list);
//		//Excpected Result
//		 scenario.get(3).add("success");
//		//Result of query1 singleResult
//		 scenario.get(3).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add(new SearchBackFeedSolarOCPDResult());
//		 scenario.get(4).add(null);
//		 //Result of query1
//		 scenario.get(4).add(list);
//		//Excpected Result
//		 scenario.get(4).add("success");
//		//Result of query1 singleResult
//		 scenario.get(4).add(new BackFeedSolarOCPD());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(5).add(new SearchBackFeedSolarOCPDResult());
//		 scenario.get(5).add(null);
//		 //Result of query1
//		 scenario.get(5).add(list);
//		//Excpected Result
//		 scenario.get(5).add("exist");
//		//Result of query1 singleResult
//		 BackFeedSolarOCPD bc = new BackFeedSolarOCPD();
//		 bc.setId(12589);
//		 scenario.get(5).add(bc);
//
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("editBackFeedSolarOCPD [ "+i+" ]");
//            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//            when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//            Mockito.doReturn("success").when(getBackFeedSolarOCPDLibraryService2).editBackFeedSolarOCPDFunction(Mockito.any(),Mockito.any());
//
//            getBackFeedSolarOCPDLibraryService2.editBackFeedSolarOCPD((SearchBackFeedSolarOCPDResult)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	}
//	@Test
//	public void testeditBackFeedSolarOCPDFunction() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from BackFeedSolarOCPD u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM PermitProjectSiteInfoEntity u WHERE u.solarInterconnection = :p1 or u.secondSolarInterconnection = :p1 or u.thirdSolarInterconnection = :p1 or u.fourthSolarInterconnection = :p1 or u.fifthSolarInterconnection = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//	       List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 //Result of query1
//			 scenario.get(0).add(null);
//			 //Result of query2
//			 scenario.get(0).add(null);
//			//Excpected Result
//			 scenario.get(0).add("fail");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(1).add(new SearchBackFeedSolarOCPDResult());
//			 scenario.get(1).add(null);
//			 //Result of query1
//			 scenario.get(1).add(null);
//			 //Result of query2
//			 scenario.get(1).add(null);
//			//Excpected Result
//			 scenario.get(1).add("fail");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(2).add(new SearchBackFeedSolarOCPDResult());
//			 scenario.get(2).add(null);
//			 //Result of query1
//			 scenario.get(2).add(new BackFeedSolarOCPD());
//			 //Result of query2
//			 scenario.get(2).add(null);
//			//Excpected Result
//			 scenario.get(2).add("fail");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(3).add(new SearchBackFeedSolarOCPDResult());
//			 scenario.get(3).add(null);
//			 //Result of query1
//			 BackFeedSolarOCPD bc = new BackFeedSolarOCPD();
//			 bc.setBackFeed(1258);
//			 scenario.get(3).add(bc);
//			 //Result of query2
//			 scenario.get(3).add(null);
//			//Excpected Result
//			 scenario.get(3).add("fail");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(4).add(new SearchBackFeedSolarOCPDResult());
//			 scenario.get(4).add(null);
//			 //Result of query1
//			 scenario.get(4).add(bc);
//			 //Result of query2
//			 scenario.get(4).add(new ArrayList<PermitProjectSiteInfoEntity>());
//			//Excpected Result
//			 scenario.get(4).add("success");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(5).add(new SearchBackFeedSolarOCPDResult());
//			 scenario.get(5).add(null);
//			 //Result of query1
//			 scenario.get(5).add(bc);
//			 //Result of query2
//			 ArrayList<PermitProjectSiteInfoEntity> list = new ArrayList<PermitProjectSiteInfoEntity>();
//			 list.add(null);
//			 list.add(new PermitProjectSiteInfoEntity());
//			 scenario.get(5).add(list);
//			//Excpected Result
//			 scenario.get(5).add("fail");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(6).add(new SearchBackFeedSolarOCPDResult());
//			 scenario.get(6).add(null);
//			 //Result of query1
//			 BackFeedSolarOCPD bc2 = new BackFeedSolarOCPD();
//			 bc2.setBackFeed(1258);
//			 scenario.get(6).add(bc2);
//			 //Result of query2
//			 ArrayList<PermitProjectSiteInfoEntity> list2 = new ArrayList<PermitProjectSiteInfoEntity>();
//			 list2.add(new PermitProjectSiteInfoEntity());
//			 scenario.get(6).add(list2);
//			//Excpected Result
//			 scenario.get(6).add("success");
//
//
//			 for(int i=0; i<scenario.size();i++) {
//				 System.out.println("editBackFeedSolarOCPDFunction [ "+i+" ]");
//	            when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//	            when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//	            getBackFeedSolarOCPDLibraryService.editBackFeedSolarOCPDFunction((SearchBackFeedSolarOCPDResult)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//			 }
//	}
//
//	@Test
//	public void testaaddBackFeedSolarOCPDNotification() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//        List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add("123");
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of query1
//		 scenario.get(1).add(new AuthentificationEntity());
//		//Excpected Result
//		 scenario.get(1).add("Success");
//
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("addBackFeedSolarOCPD [ "+i+" ]");
//            when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//            getBackFeedSolarOCPDLibraryService.addBackFeedSolarOCPDNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//		 }
//	}
//
//
//	@Test
//	public void testbackFeedSolarOCPDLibraryActived() {
//				 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from BackFeedSolarOCPD i Where i.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from BackFeedSolarOCPD u WHERE u.backFeed = :p1  AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(0).add(123);
//		 scenario.get(0).add(null);
//
//		 //The result of query1
//		 scenario.get(0).add(null);
//		 //The result of the query2
//		 scenario.get(0).add(null);
//		 //The result excpected
//		 scenario.get(0).add("false");
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(1).add(123);
//		 scenario.get(1).add("");
//		 //The result of query1
//		 scenario.get(1).add(null);
//		 //The result of the query2
//		 scenario.get(1).add(null);
//		 //The result excpected
//		 scenario.get(1).add("false");
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(2).add(123);
//		 scenario.get(2).add("");
//
//		 //The result of query1
//		 scenario.get(2).add(null);
//		 //The result of the query2
//		 scenario.get(2).add(null);
//		 //The result excpected
//		 scenario.get(2).add("false");
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(3).add(123);
//		 scenario.get(3).add("22");
//
//		 //The result of query1
//		 BackFeedSolarOCPD ac1 = new BackFeedSolarOCPD();
//		 scenario.get(3).add(ac1);
//		 //The result of the query2
//		 scenario.get(3).add(null);
//		 //The result excpected
//		 scenario.get(3).add("false");
//
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(4).add(123);
//		 scenario.get(4).add("123");
//
//		 //The result of query1
//		 scenario.get(4).add(null);
//		 //The result of the query2
//		 scenario.get(4).add(null);
//		 //The result excpected
//		 scenario.get(4).add("false");
//
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(5).add(123);
//		 scenario.get(5).add("123");
//
//		 //The result of query1
//		 BackFeedSolarOCPD acCombiner = new BackFeedSolarOCPD();
//		 acCombiner.setBackFeed(125);
//		 scenario.get(5).add(acCombiner);
//		 //The result of the query2
//		 scenario.get(5).add(null);
//		 //The result excpected
//		 scenario.get(5).add("false");
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(6).add(123);
//		 scenario.get(6).add("123");
//
//		 //The result of query1
//		 scenario.get(6).add(acCombiner);
//		 //The result of the query2
//		 scenario.get(6).add(new ArrayList<BackFeedSolarOCPD>());
//		 //The result excpected
//		 scenario.get(6).add("true");
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(7).add(123);
//		 scenario.get(7).add("");
//
//		 //The result of query1
//		 scenario.get(7).add(acCombiner);
//		 //The result of the query2
//		 ArrayList<BackFeedSolarOCPD> listAcCom= new ArrayList<BackFeedSolarOCPD>();
//		 listAcCom.add(new BackFeedSolarOCPD());
//		 scenario.get(7).add(listAcCom);
//		 //The result excpected
//		 scenario.get(7).add("exist");
//
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("backFeedSolarOCPDLibraryActived [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			 getBackFeedSolarOCPDLibraryService.backFeedSolarOCPDLibraryActived((Integer)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	}
//	@Test
//	public void testgetAllPermitOfBackFeedSolarOCPDDeleted() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from BackFeedSolarOCPD i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//					+ "p.solarInterconnection = :p1 or p.secondSolarInterconnection = :p1 or p.thirdSolarInterconnection = :p1 or p.fourthSolarInterconnection = :p1 or p.fifthSolarInterconnection = :p1 " )).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//	 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new ArrayList<PermitEntity>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(new BackFeedSolarOCPD());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<PermitEntity>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 BackFeedSolarOCPD bc = new BackFeedSolarOCPD();
//		 bc.setId(2235);
//		 scenario.get(2).add(bc);
//		 //Result of the query1
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add(new ArrayList<PermitEntity>());
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 scenario.get(3).add(bc);
//		 //Result of the query1
//		 ArrayList<PermitProjectSiteInfoEntity> list = new ArrayList<PermitProjectSiteInfoEntity>();
//		 list.add(null);
//		 list.add(new PermitProjectSiteInfoEntity());
//		 scenario.get(3).add(list);
//	     //Result excpected
//		 scenario.get(3).add(new ArrayList<PermitEntity>());
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("getAllPermitOfBackFeedSolarOCPDDeleted [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 getBackFeedSolarOCPDLibraryService.getAllPermitOfBackFeedSolarOCPDDeleted((Integer)scenario.get(i).get(0));
//
//		 }
//
//	}
//
//	@Test
//	public void testgetAllPermitOfBackFeedSolarOCPDDeleted1() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from BackFeedSolarOCPD i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//					+ "p.solarInterconnection = :p1 or p.secondSolarInterconnection = :p1 or p.thirdSolarInterconnection = :p1 or p.fourthSolarInterconnection = :p1 or p.fifthSolarInterconnection = :p1 " )).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//	 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(123);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new ArrayList<PermitEntity>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(123);
//		 //Result of the query1
//		 scenario.get(1).add(new BackFeedSolarOCPD());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(123);
//		 //Result of the query1
//		 BackFeedSolarOCPD bc = new BackFeedSolarOCPD();
//		 bc.setId(2235);
//		 scenario.get(2).add(bc);
//		 //Result of the query1
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add(new ArrayList<PermitEntity>());
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(123);
//		 //Result of the query1
//		 scenario.get(3).add(bc);
//		 //Result of the query1
//		 ArrayList<PermitProjectSiteInfoEntity> list = new ArrayList<PermitProjectSiteInfoEntity>();
//		 list.add(null);
//		 list.add(new PermitProjectSiteInfoEntity());
//		 scenario.get(3).add(list);
//	     //Result excpected
//		 scenario.get(3).add(new ArrayList<PermitEntity>());
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("getAllPermitOfBackFeedSolarOCPDDeleted1 [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 getBackFeedSolarOCPDLibraryService.getAllPermitOfBackFeedSolarOCPDDeleted1((int)scenario.get(i).get(0));
//
//		 }
//
//	}
//	@Test
//	public void testdeleteBackFeedSolarOCPDLibrary() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from BackFeedSolarOCPD i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "+ "p.permitEntity = :p1 and p.solarInterconnection = :p2")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "+ "p.permitEntity = :p1 and p.secondSolarInterconnection = :p2")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//		 Query mockedQuery4 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "+ "p.permitEntity = :p1 and p.thirdSolarInterconnection = :p2")).thenReturn(mockedQuery4);
//		 when(mockedQuery4.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery4);
//
//		 Query mockedQuery5 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "+ "p.permitEntity = :p1 and p.fourthSolarInterconnection = :p2")).thenReturn(mockedQuery5);
//		 when(mockedQuery5.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery5);
//
//		 Query mockedQuery6 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "+ "p.permitEntity = :p1 and p.fifthSolarInterconnection = :p2")).thenReturn(mockedQuery6);
//		 when(mockedQuery6.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery6);
//
//		 Query mockedQuery7 = mock(Query.class);
//		 when(em.createQuery("SELECT i from BackFeedSolarOCPD i Where i.id = :p1")).thenReturn(mockedQuery7);
//		 when(mockedQuery7.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery7);
//
//		 GetBackFeedSolarOCPDLibraryService getBackFeedSolarOCPDLibraryService2 = Mockito.spy(getBackFeedSolarOCPDLibraryService);
//
//
//	 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(123);
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
//		 //Result of the query7
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(false);
//		 //Result of getAllPermitOfBackFeedSolarOCPDDeleted1
//		 scenario.get(0).add(null);
//			//Result of the query2 List
//		 scenario.get(0).add(null);
//			//Result of the query3 List
//		 scenario.get(0).add(null);
//			//Result of the query4 List
//		 scenario.get(0).add(null);
//		 //Result of the query5 List
//		 scenario.get(0).add(null);
//		 //Result of the query5 List
//		 scenario.get(0).add(null);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(0);
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
//		 //Result of the query7
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(false);
//		 //Result of getAllPermitOfBackFeedSolarOCPDDeleted1
//		 scenario.get(1).add(null);
//			//Result of the query2 List
//		 scenario.get(1).add(null);
//			//Result of the query3 List
//		 scenario.get(1).add(null);
//			//Result of the query4 List
//		 scenario.get(1).add(null);
//		 //Result of the query5 List
//		 scenario.get(1).add(null);
//		 //Result of the query5 List
//		 scenario.get(1).add(null);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(125);
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
//		 //Result of the query7
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add(false);
//		 //Result of getAllPermitOfBackFeedSolarOCPDDeleted1
//		 List<PermitEntity> list = new ArrayList<PermitEntity>();
//		 list.add(null);
//		 list.add(new PermitEntity());
//		 PermitEntity p = new PermitEntity();
//		 p.setDeleted(false);
//		 p.setAdvancement("100");
//		 list.add(p);
//		 scenario.get(2).add(list);
//		//Result of the query2 List
//		 scenario.get(2).add(null);
//			//Result of the query3 List
//		 scenario.get(2).add(null);
//			//Result of the query4 List
//		 scenario.get(2).add(null);
//		 //Result of the query5 List
//		 scenario.get(2).add(null);
//		 //Result of the query5 List
//		 scenario.get(2).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(125);
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 BackFeedSolarOCPD bc =new BackFeedSolarOCPD();
//		 bc.setBackFeed(125);
//		 scenario.get(3).add(bc);
//		 //Result of the query2
//		 scenario.get(3).add(null);
//		 //Result of the query3
//		 scenario.get(3).add(null);
//		 //Result of the query4
//		 scenario.get(3).add(null);
//		 //Result of the query5
//		 scenario.get(3).add(null);
//		 //Result of the query6
//		 scenario.get(3).add(null);
//		 //Result of the query7
//		 scenario.get(3).add(null);
//	     //Result excpected
//		 scenario.get(3).add(false);
//		 //Result of getAllPermitOfBackFeedSolarOCPDDeleted1
//		 scenario.get(3).add(list);
//		//Result of the query2 List
//		 scenario.get(3).add(null);
//		//Result of the query3 List
//		 scenario.get(3).add(null);
//		 //Result of the query4 List
//		 scenario.get(3).add(null);
//		 //Result of the query5 List
//		 scenario.get(3).add(null);
//		 //Result of the query5 List
//		 scenario.get(3).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add(125);
//		 scenario.get(4).add(null);
//		 //Result of the query1
//		 scenario.get(4).add(bc);
//		 //Result of the query2
//		 scenario.get(4).add(null);
//		 //Result of the query3
//		 scenario.get(4).add(null);
//		 //Result of the query4
//		 scenario.get(4).add(null);
//		 //Result of the query5
//		 scenario.get(4).add(null);
//		 //Result of the query6
//		 scenario.get(4).add(null);
//		 //Result of the query7
//		 scenario.get(4).add(bc);
//	     //Result excpected
//		 scenario.get(4).add(true);
//		 //Result of getAllPermitOfBackFeedSolarOCPDDeleted1
//		 scenario.get(4).add(list);
//		 //Result of the query2 List
//		 scenario.get(4).add(null);
//		 //Result of the query3 List
//		 scenario.get(4).add(null);
//		 //Result of the query4 List
//		 scenario.get(4).add(null);
//		 //Result of the query5 List
//		 scenario.get(4).add(null);
//		 //Result of the query5 List
//		 scenario.get(4).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(5).add(125);
//		 scenario.get(5).add(null);
//		 //Result of the query1
//		 scenario.get(5).add(bc);
//		 //Result of the query2
//		 scenario.get(5).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query3
//		 scenario.get(5).add(null);
//		 //Result of the query4
//		 scenario.get(5).add(null);
//		 //Result of the query5
//		 scenario.get(5).add(null);
//		 //Result of the query6
//		 scenario.get(5).add(null);
//		 //Result of the query7
//		 scenario.get(5).add(bc);
//	     //Result excpected
//		 scenario.get(5).add(true);
//		 //Result of getAllPermitOfBackFeedSolarOCPDDeleted1
//		 scenario.get(5).add(list);
//		 //Result of the query2 List
//		 ArrayList<PermitProjectSiteInfoEntity> listPermit = new ArrayList<PermitProjectSiteInfoEntity>();
//		 listPermit.add(new PermitProjectSiteInfoEntity());
//		 scenario.get(5).add(listPermit);
//		 //Result of the query3 List
//		 scenario.get(5).add(null);
//		 //Result of the query4 List
//		 scenario.get(5).add(null);
//		 //Result of the query5 List
//		 scenario.get(5).add(null);
//		 //Result of the query5 List
//		 scenario.get(5).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(6).add(125);
//		 scenario.get(6).add(null);
//		 //Result of the query1
//		 scenario.get(6).add(bc);
//		 //Result of the query2
//		 scenario.get(6).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query3
//		 scenario.get(6).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query4
//		 scenario.get(6).add(null);
//		 //Result of the query5
//		 scenario.get(6).add(null);
//		 //Result of the query6
//		 scenario.get(6).add(null);
//		 //Result of the query7
//		 scenario.get(6).add(bc);
//	     //Result excpected
//		 scenario.get(6).add(true);
//		 //Result of getAllPermitOfBackFeedSolarOCPDDeleted1
//		 scenario.get(6).add(list);
//		 //Result of the query2 List
//		 scenario.get(6).add(listPermit);
//		 //Result of the query3 List
//		 scenario.get(6).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(6).add(null);
//		 //Result of the query5 List
//		 scenario.get(6).add(null);
//		 //Result of the query5 List
//		 scenario.get(6).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(7).add(125);
//		 scenario.get(7).add(null);
//		 //Result of the query1
//		 scenario.get(7).add(bc);
//		 //Result of the query2
//		 scenario.get(7).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query3
//		 scenario.get(7).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query4
//		 scenario.get(7).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query5
//		 scenario.get(7).add(null);
//		 //Result of the query6
//		 scenario.get(7).add(null);
//		 //Result of the query7
//		 scenario.get(7).add(bc);
//	     //Result excpected
//		 scenario.get(7).add(true);
//		 //Result of getAllPermitOfBackFeedSolarOCPDDeleted1
//		 scenario.get(7).add(list);
//		 //Result of the query2 List
//		 scenario.get(7).add(listPermit);
//		 //Result of the query3 List
//		 scenario.get(7).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(7).add(listPermit);
//		 //Result of the query5 List
//		 scenario.get(7).add(null);
//		 //Result of the query5 List
//		 scenario.get(7).add(null);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(8).add(125);
//		 scenario.get(8).add(null);
//		 //Result of the query1
//		 scenario.get(8).add(bc);
//		 //Result of the query2
//		 scenario.get(8).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query3
//		 scenario.get(8).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query4
//		 scenario.get(8).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query5
//		 scenario.get(8).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query6
//		 scenario.get(8).add(null);
//		 //Result of the query7
//		 scenario.get(8).add(bc);
//	     //Result excpected
//		 scenario.get(8).add(true);
//		 //Result of getAllPermitOfBackFeedSolarOCPDDeleted1
//		 scenario.get(8).add(list);
//		 //Result of the query2 List
//		 scenario.get(8).add(listPermit);
//		 //Result of the query3 List
//		 scenario.get(8).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(8).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(8).add(listPermit);
//		 //Result of the query5 List
//		 scenario.get(8).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(9).add(125);
//		 scenario.get(9).add(null);
//		 //Result of the query1
//		 scenario.get(9).add(bc);
//		 //Result of the query2
//		 scenario.get(9).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query3
//		 scenario.get(9).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query4
//		 scenario.get(9).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query5
//		 scenario.get(9).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query6
//		 scenario.get(9).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query7
//		 scenario.get(9).add(bc);
//	     //Result excpected
//		 scenario.get(9).add(true);
//		 //Result of getAllPermitOfBackFeedSolarOCPDDeleted1
//		 scenario.get(9).add(list);
//		 //Result of the query2 List
//		 scenario.get(9).add(listPermit);
//		 //Result of the query3 List
//		 scenario.get(9).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(9).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(9).add(listPermit);
//		 //Result of the query5 List
//		 scenario.get(9).add(listPermit);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(10).add(125);
//		 scenario.get(10).add(null);
//		 //Result of the query1
//		 scenario.get(10).add(bc);
//		 //Result of the query2
//		 scenario.get(10).add(null);
//		 //Result of the query3
//		 scenario.get(10).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query4
//		 scenario.get(10).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query5
//		 scenario.get(10).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query6
//		 scenario.get(10).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query7
//		 scenario.get(10).add(bc);
//	     //Result excpected
//		 scenario.get(10).add(false);
//		 //Result of getAllPermitOfBackFeedSolarOCPDDeleted1
//		 scenario.get(10).add(list);
//		 //Result of the query2 List
//		 scenario.get(10).add(listPermit);
//		 //Result of the query3 List
//		 scenario.get(10).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(10).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(10).add(listPermit);
//		 //Result of the query5 List
//		 scenario.get(10).add(listPermit);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(11).add(125);
//		 scenario.get(11).add(null);
//		 //Result of the query1
//		 scenario.get(11).add(bc);
//		 //Result of the query2
//		 scenario.get(11).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query3
//		 scenario.get(11).add(null);
//		 //Result of the query4
//		 scenario.get(11).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query5
//		 scenario.get(11).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query6
//		 scenario.get(11).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query7
//		 scenario.get(11).add(bc);
//	     //Result excpected
//		 scenario.get(11).add(false);
//		 //Result of getAllPermitOfBackFeedSolarOCPDDeleted1
//		 scenario.get(11).add(list);
//		 //Result of the query2 List
//		 scenario.get(11).add(listPermit);
//		 //Result of the query3 List
//		 scenario.get(11).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(11).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(11).add(listPermit);
//		 //Result of the query5 List
//		 scenario.get(11).add(listPermit);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(12).add(125);
//		 scenario.get(12).add(null);
//		 //Result of the query1
//		 scenario.get(12).add(bc);
//		 //Result of the query2
//		 scenario.get(12).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query3
//		 scenario.get(12).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query4
//		 scenario.get(12).add(null);
//		 //Result of the query5
//		 scenario.get(12).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query6
//		 scenario.get(12).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query7
//		 scenario.get(12).add(bc);
//	     //Result excpected
//		 scenario.get(12).add(false);
//		 //Result of getAllPermitOfBackFeedSolarOCPDDeleted1
//		 scenario.get(12).add(list);
//		 //Result of the query2 List
//		 scenario.get(12).add(listPermit);
//		 //Result of the query3 List
//		 scenario.get(12).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(12).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(12).add(listPermit);
//		 //Result of the query5 List
//		 scenario.get(12).add(listPermit);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(13).add(135);
//		 scenario.get(13).add(null);
//		 //Result of the query1
//		 scenario.get(13).add(bc);
//		 //Result of the query2
//		 scenario.get(13).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query3
//		 scenario.get(13).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query4
//		 scenario.get(13).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query5
//		 scenario.get(13).add(null);
//		 //Result of the query6
//		 scenario.get(13).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query7
//		 scenario.get(13).add(bc);
//	     //Result excpected
//		 scenario.get(13).add(false);
//		 //Result of getAllPermitOfBackFeedSolarOCPDDeleted1
//		 scenario.get(13).add(list);
//		 //Result of the query2 List
//		 scenario.get(13).add(listPermit);
//		 //Result of the query3 List
//		 scenario.get(13).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(13).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(13).add(listPermit);
//		 //Result of the query5 List
//		 scenario.get(13).add(listPermit);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(14).add(145);
//		 scenario.get(14).add(null);
//		 //Result of the query1
//		 scenario.get(14).add(bc);
//		 //Result of the query2
//		 scenario.get(14).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query3
//		 scenario.get(14).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query4
//		 scenario.get(14).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query5
//		 scenario.get(14).add(new PermitProjectSiteInfoEntity());
//		 //Result of the query6
//		 scenario.get(14).add(null);
//		 //Result of the query7
//		 scenario.get(14).add(bc);
//	     //Result excpected
//		 scenario.get(14).add(false);
//		 //Result of getAllPermitOfBackFeedSolarOCPDDeleted1
//		 scenario.get(14).add(list);
//		 //Result of the query2 List
//		 scenario.get(14).add(listPermit);
//		 //Result of the query3 List
//		 scenario.get(14).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(14).add(listPermit);
//		 //Result of the query4 List
//		 scenario.get(14).add(listPermit);
//		 //Result of the query5 List
//		 scenario.get(14).add(listPermit);
//
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("deleteBackFeedSolarOCPDLibrary [ "+i+" ]"+scenario.get(i).get(2));
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//
//			 when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(11));
//
//			 when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			 when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(12));
//
//			 when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			 when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(13));
//
//			 when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(6));
//			 when(mockedQuery5.getResultList()).thenReturn((List) scenario.get(i).get(14));
//
//			 when(mockedQuery6.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			 when(mockedQuery6.getResultList()).thenReturn((List) scenario.get(i).get(15));
//
//			 when(mockedQuery7.getSingleResult()).thenReturn(scenario.get(i).get(8));
//             Mockito.doReturn(scenario.get(i).get(10)).when(getBackFeedSolarOCPDLibraryService2).getAllPermitOfBackFeedSolarOCPDDeleted1((int)scenario.get(i).get(0));
//
//			 getBackFeedSolarOCPDLibraryService2.deleteBackFeedSolarOCPDLibrary((int)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	}
//
//	@Test
//	public void testcheckBackFeedSolarOCPDExistent() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from BackFeedSolarOCPD u WHERE u.backFeed = :p1 AND u.isDeleted =false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//	 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add("fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(new SearchBackFeedSolarOCPDResult());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add("fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(new SearchBackFeedSolarOCPDResult());
//		 //Result of the query1
//		 scenario.get(2).add(new ArrayList<BackFeedSolarOCPD>());
//	     //Result excpected
//		 scenario.get(2).add("success");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(new SearchBackFeedSolarOCPDResult());
//		 //Result of the query1
//		 ArrayList<BackFeedSolarOCPD> list = new ArrayList<BackFeedSolarOCPD>();
//		 list.add(null);
//		 scenario.get(3).add(list);
//	     //Result excpected
//		 scenario.get(3).add("exist");
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("checkBackFeedSolarOCPDExistent [ "+i+" ]");
//			 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			 getBackFeedSolarOCPDLibraryService.checkBackFeedSolarOCPDExistent( (SearchBackFeedSolarOCPDResult) scenario.get(i).get(0));
//		 }
//	}
//
//	@Test
//	public void testaddNewBackFeedSolarOCPD() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
// ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new BackFeedSolarOCPD());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(new SearchBackFeedSolarOCPDResult());
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 BackFeedSolarOCPD bc =new BackFeedSolarOCPD();
//		 Date d = new Date();
//		 SimpleDateFormat FormattedDATE = new SimpleDateFormat("MM/dd/yyyy");
//		 String updatedDate = FormattedDATE.format(d);
//		 bc.setUpdated(updatedDate);
//		 scenario.get(1).add(bc);
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add(new SearchBackFeedSolarOCPDResult());
//		 scenario.get(2).add("");
//		 //Result of the query1
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add(bc);
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add(new SearchBackFeedSolarOCPDResult());
//		 scenario.get(3).add("123");
//		 //Result of the query1
//		 scenario.get(3).add(null);
//	     //Result excpected
//		 scenario.get(3).add(bc);
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add(new SearchBackFeedSolarOCPDResult());
//		 scenario.get(4).add("123");
//		 //Result of the query1
//		 AuthentificationEntity auth = new AuthentificationEntity();
//		 auth.setRoleEntity(new RoleEntity());
//		 scenario.get(4).add(auth);
//	     //Result excpected
//		 BackFeedSolarOCPD bc1 =new BackFeedSolarOCPD();
//		 bc1.setUpdated(updatedDate);
//		 bc1.setHasSuperUserEdit(false);
//		 Date addDate = new Date();
//		 bc1.setAddDate(addDate);
//		 bc1.setAuthentificationEntity(auth);
//		 scenario.get(4).add(bc1);
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("addNewBackFeedSolarOCPD [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			 getBackFeedSolarOCPDLibraryService.addNewBackFeedSolarOCPD( (SearchBackFeedSolarOCPDResult) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		 }
//	}
//
//	@Test
//	public void testgetListOfBackFeedSolarOCPD() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from BackFeedSolarOCPD u WHERE  u.isDeleted= :p1 ORDER BY u.backFeed")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//	 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of the query1
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of the query1
//		 scenario.get(1).add(new ArrayList<BackFeedSolarOCPD>());
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<BackFeedSolarOCPD>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of the query1
//		 ArrayList<BackFeedSolarOCPD> list = new ArrayList<BackFeedSolarOCPD>();
//		 list.add(new BackFeedSolarOCPD());
//		 list.add(null);
//		 scenario.get(2).add(list);
//	     //Result excpected
//		 scenario.get(2).add(list);
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getListOfBackFeedSolarOCPD [ "+i+" ]");
//			 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			  getBackFeedSolarOCPDLibraryService.getListOfBackFeedSolarOCPD();
//		 }
//	}
//
//
//
//}
