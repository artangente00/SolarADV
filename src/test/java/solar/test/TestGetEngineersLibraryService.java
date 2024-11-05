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
//import javax.persistence.criteria.Path;
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
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.Battery;
//import com.PlayGroundAdv.Solar.Entity.BatteryFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.Engineers;
//import com.PlayGroundAdv.Solar.Entity.PermitAdditionalInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEngineerEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.model.BatteryFavRequest;
//import com.PlayGroundAdv.Solar.model.SearchBatteryRequest;
//import com.PlayGroundAdv.Solar.model.SearchBatteryResult;
//import com.PlayGroundAdv.Solar.model.SearchEngineersRequest;
//import com.PlayGroundAdv.Solar.model.SearchEngineersResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetBatteryLibraryService;
//import com.PlayGroundAdv.Solar.Services.GetEngineersLibraryService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//
//public class TestGetEngineersLibraryService {
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
//	
//	@InjectMocks
//	GetEngineersLibraryService getEngineersLibraryService = new GetEngineersLibraryService();
//	
//	
//    @Before
//	public void setupMock() {
//    	getEngineersLibraryService = new GetEngineersLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testsearchEngineers() {
//		  TypedQuery mockedQuery1 = mock(TypedQuery.class);
//			when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//			Path battery = mock(Path.class);
//
//			when(criteriaBuilder.createQuery(SearchEngineersResult.class)).thenReturn(criteriaQuery);
//			when(criteriaQuery.from(Engineers.class)).thenReturn(root);
//			when(root.get(Mockito.anyString())).thenReturn(battery);
//			//when(r.get("authentificationEntity")).thenReturn(authentificationEntity);
//	        when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//	        when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//	        when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//	        when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//	        
//		   	 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(0) .add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 //Result of the query1
//			 scenario.get(0).add(null);
//			 //Result of the query2
//			 scenario.get(0).add(null);
//		     //Result excpected
//			 scenario.get(0).add(new ArrayList<SearchEngineersResult>());
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(1) .add(new SearchEngineersRequest());
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 //Result of the query1
//			 scenario.get(1).add(null);
//			 //Result of the query2
//			 scenario.get(1).add(null);
//		     //Result excpected
//			 scenario.get(1).add(new ArrayList<SearchEngineersResult>());
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(2) .add(new SearchEngineersRequest());
//			 scenario.get(2).add("");
//			 scenario.get(2).add("");
//			 scenario.get(2).add("");
//			 //Result of the query1
//			 scenario.get(2).add(null);
//			 //Result of the query2
//			 scenario.get(2).add(null);
//		     //Result excpected
//			 scenario.get(2).add(new ArrayList<SearchEngineersResult>());
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(3) .add(new SearchEngineersRequest());
//			 scenario.get(3).add("123");
//			 scenario.get(3).add("123");
//			 scenario.get(3).add("123");
//			 //Result of the query1
//			 scenario.get(3).add(null);
//			 //Result of the query2
//			 scenario.get(3).add(null);
//		     //Result excpected
//			 scenario.get(3).add(new ArrayList<SearchEngineersResult>());
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(4) .add(new SearchEngineersRequest());
//			 scenario.get(4).add("123");
//			 scenario.get(4).add("123");
//			 scenario.get(4).add("123");
//			 //Result of the query1
//			 List<SearchEngineersResult> list = new ArrayList<SearchEngineersResult>();
//			 list.add(null);
//			 list.add(new SearchEngineersResult());
//			 scenario.get(4).add(list);
//			 //Result of the query2
//			 scenario.get(4).add(null);
//		     //Result excpected
//			 scenario.get(4).add(list);
//	
//			 
//			 
//			    for(int i=0;i<scenario.size();i++) {
//					 System.out.println("searchEngineers [ "+i+" ]");
//					 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//
//		            ArrayList<SearchEngineersResult> result = ((ArrayList<SearchEngineersResult>) getEngineersLibraryService.searchEngineers((SearchEngineersRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3)));
//					 
//					
//				 }
//	}
//	
//	@Test
//	public void testgetNumberSearchEngineers() {
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(Engineers.class)).thenReturn(root);
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
//		 scenario.get(0).add(-5L);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 SearchEngineersRequest sc = new SearchEngineersRequest();
//		 scenario.get(1).add(sc);
//		 scenario.get(1).add(null);
//		 //Result of query1
//		 scenario.get(1).add(null); 
//		//Excpected Result
//		 scenario.get(1).add(null);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(sc);
//		 scenario.get(2).add(null);
//		 //Result of query1
//		 scenario.get(2).add(8L); 
//		//Excpected Result
//		 scenario.get(2).add(8L);
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getNumberSearchEngineers [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//	         getEngineersLibraryService.getNumberSearchEngineers((SearchEngineersRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		 }
//   
//	}
//	
//	@Test
//	public void testgetEngineersLibrary() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Engineers u order by u.company")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//		 
//	       List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//			 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(0).add(null);
//				 scenario.get(0).add(null);
//				 scenario.get(0).add(null);
//				 //Result of query1
//				 scenario.get(0).add(null); 
//				//Excpected Result
//				 scenario.get(0).add(new ArrayList<SearchEngineersResult>());
//				 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(1).add("");
//				 scenario.get(1).add("");
//				 scenario.get(1).add("");
//				 //Result of query1
//				 scenario.get(1).add(null); 
//				//Excpected Result
//				 scenario.get(1).add(new ArrayList<SearchEngineersResult>());
//				 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 //Result of query1
//				 scenario.get(2).add(null); 
//				//Excpected Result
//				 scenario.get(2).add(new ArrayList<SearchEngineersResult>());
//				 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(3).add("123");
//				 scenario.get(3).add("123");
//				 scenario.get(3).add("123");
//				 //Result of query1
//				 List<Engineers> list = new ArrayList<Engineers>();
//				 list.add(null);
//				 list.add(new Engineers());
//				 scenario.get(3).add(list); 
//				//Excpected Result
//				 List<SearchEngineersResult> listExp = new ArrayList<SearchEngineersResult>();
//				 listExp.add(new SearchEngineersResult());
//				 listExp.add(new SearchEngineersResult());
//				 scenario.get(3).add(listExp);
//				 
//				 for(int i=0;i<scenario.size();i++) {
//					 System.out.println("getEngineersLibrary [ "+i+" ]");
//		            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//		             ArrayList<SearchEngineersResult> result = ((ArrayList<SearchEngineersResult>) getEngineersLibraryService.getEngineersLibrary((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2)));
//
//
//				 }
//	}
//	
//	@Test
//	public void testaddEngineers() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT  u.company from Engineers u where u.company=:p1 AND u.contact=:p2 AND u.licenseState=:p3 ")).thenReturn(mockedQuery1);
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
//			 scenario.get(1).add(new SearchEngineersResult());
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
//			 scenario.get(2).add(new SearchEngineersResult());
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 //Result of query1
//			 scenario.get(2).add(new ArrayList<Engineers>()); 
//			 //Result of query2
//			 scenario.get(2).add(null); 
//			//Excpected Result
//			 scenario.get(2).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(3).add("");
//			 scenario.get(3).add(new SearchEngineersResult());
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 //Result of query1
//			 scenario.get(3).add(new ArrayList<Engineers>()); 
//			 //Result of query2
//			 scenario.get(3).add(null); 
//			//Excpected Result
//			 scenario.get(3).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(4).add("123");
//			 scenario.get(4).add(new SearchEngineersResult());
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 //Result of query1
//			 scenario.get(4).add(new ArrayList<Engineers>()); 
//			 //Result of query2
//			 scenario.get(4).add(null); 
//			//Excpected Result
//			 scenario.get(4).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(5).add("123");
//			 scenario.get(5).add(new SearchEngineersResult());
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 //Result of query1
//			 scenario.get(5).add(new ArrayList<Engineers>()); 
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
//			 scenario.get(6).add(new SearchEngineersResult());
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 //Result of query1
//			 ArrayList<Engineers> list = new ArrayList<Engineers>();
//			 list.add(null);
//			 list.add(new Engineers());
//			 scenario.get(6).add(list); 
//			 //Result of query2
//			 scenario.get(6).add(auth); 
//			//Excpected Result
//			 scenario.get(6).add("Engineers already exists in data sources");
//			 
//			 for(int i=0; i<scenario.size();i++) {
//				 System.out.println("addEngineers [ "+i+" ]");
//	            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(5));
//	            when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(6));
//	            getEngineersLibraryService.addEngineers((String)scenario.get(i).get(0),(SearchEngineersResult)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4));
//
//			 }
//	
//	}
//	
//	@Test
//	public void testeditEngineers() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Engineers u WHERE u.company=:p1 AND u.contact=:p2 AND u.licenseState=:p3 AND u.isDeleted=false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 GetEngineersLibraryService getEngineersLibraryService2 = Mockito.spy(getEngineersLibraryService);
//
//		 
//     List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		 scenario.get(1).add(new SearchEngineersResult());
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
//		 scenario.get(2).add(new SearchEngineersResult());
//		 scenario.get(2).add(null);
//		 //Result of query1
//		 scenario.get(2).add(new ArrayList<Battery>()); 
//		//Excpected Result
//		 scenario.get(2).add("success");
//		//Result of query1 singleResult
//		 scenario.get(2).add(null);  
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(new SearchEngineersResult());
//		 scenario.get(3).add(null);
//		 //Result of query1
//		 ArrayList<Battery> list = new ArrayList<Battery>();
//		 list.add(null);
//		 list.add(new Battery());
//		 scenario.get(3).add(list); 
//		//Excpected Result
//		 scenario.get(3).add("success");
//		//Result of query1 singleResult
//		 scenario.get(3).add(null); 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add(new SearchEngineersResult());
//		 scenario.get(4).add(null);
//		 //Result of query1
//		 scenario.get(4).add(list); 
//		//Excpected Result
//		 scenario.get(4).add("success");
//		//Result of query1 singleResult
//		 scenario.get(4).add(new Engineers()); 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(5).add(new SearchEngineersResult());
//		 scenario.get(5).add(null);
//		 //Result of query1
//		 scenario.get(5).add(list); 
//		//Excpected Result
//		 scenario.get(5).add("exist");
//		//Result of query1 singleResult
//		 Engineers bc = new Engineers();
//		 bc.setId(12589);
//		 scenario.get(5).add(bc); 
//		 
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("editEngineers [ "+i+" ]");
//          when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//          when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//          Mockito.doReturn("success").when(getEngineersLibraryService2).editEngineersFunction(Mockito.any(),Mockito.any());
//
//          getEngineersLibraryService2.editEngineers((SearchEngineersResult)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testeditEngineersFunction() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Engineers u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM PermitEngineerEntity u WHERE u.engineeredBy= :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//	      List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//			 scenario.get(1).add(new SearchEngineersResult());
//			 scenario.get(1).add(null);
//			 //Result of query1
//			 scenario.get(1).add(null); 
//			 //Result of query2
//			 scenario.get(1).add(null); 
//			//Excpected Result
//			 scenario.get(1).add("fail");
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(2).add(new SearchEngineersResult());
//			 scenario.get(2).add(null);
//			 //Result of query1
//			 scenario.get(2).add(new Engineers()); 
//			 //Result of query2
//			 scenario.get(2).add(null); 
//			//Excpected Result
//			 scenario.get(2).add("fail");
//			 
//			 
//			
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(3).add(new SearchEngineersResult());
//			 scenario.get(3).add(null);
//			 //Result of query1
//			 scenario.get(3).add(new Engineers()); 
//			 //Result of query2
//			 List<PermitEngineerEntity> list = new ArrayList<PermitEngineerEntity>();
//			 list.add(null);
//			 list.add(new PermitEngineerEntity());
//			 PermitEngineerEntity el = new PermitEngineerEntity();
//			 el.setEngineeredBy("");
//			 scenario.get(3).add(list); 
//			//Excpected Result
//			 scenario.get(3).add("success");
//			 
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("editEngineersFunction [ "+i+" ]");
//		     when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//            when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//            getEngineersLibraryService.editEngineersFunction((SearchEngineersResult)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testeditEngineersNotification() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//	    List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add("");
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //Result of query1
//		 scenario.get(2).add(null); 
//		//Excpected Result
//		 scenario.get(2).add("fail");
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add("123");
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //Result of query1
//		 scenario.get(3).add(null); 
//		//Excpected Result
//		 scenario.get(3).add("fail");
//		 
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("editEngineersNotification [ "+i+" ]");
//	        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//	        getEngineersLibraryService.editEngineersNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	}
//	
//	@Test
//	public void testaddEngineersNotification() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//	    List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add("");
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //Result of query1
//		 scenario.get(2).add(null); 
//		//Excpected Result
//		 scenario.get(2).add("fail");
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add("123");
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //Result of query1
//		 scenario.get(3).add(null); 
//		//Excpected Result
//		 scenario.get(3).add("fail");
//		 
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("addEngineersNotification [ "+i+" ]");
//	        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//	        getEngineersLibraryService.addEngineersNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	}
//	
//	@Test
//	public void testengineersLibraryActived() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Engineers i Where i.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Engineers u WHERE u.company=:p1 AND u.contact=:p2 AND u.licenseState=:p3 AND u.isDeleted=false")).thenReturn(mockedQuery2);
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
//		 Engineers ac1 = new Engineers();
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
//		 Engineers acCombiner = new Engineers();
//		 acCombiner.setId(123);
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
//		 scenario.get(6).add(new ArrayList<Engineers>());
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
//		 ArrayList<Engineers> listAcCom= new ArrayList<Engineers>();
//		 listAcCom.add(new Engineers());
//		 scenario.get(7).add(listAcCom);
//		 //The result excpected
//		 scenario.get(7).add("exist");
//		 
//		
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("engineersLibraryActived [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			  getEngineersLibraryService.engineersLibraryActived(123,(String)scenario.get(i).get(1));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testgetAllPermitOfEngineersDeleted() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Engineers i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitEngineerEntity p Where "
//					+ "p.engineeredBy = :p1 " )).thenReturn(mockedQuery2);
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
//		 scenario.get(1).add(new Engineers());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<PermitEntity>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 Engineers bc = new Engineers();
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
//		 ArrayList<PermitEngineerEntity> list = new ArrayList<PermitEngineerEntity>();
//		 list.add(null);
//		 list.add(new PermitEngineerEntity());
//		 scenario.get(3).add(list);
//	     //Result excpected
//		 scenario.get(3).add(new ArrayList<PermitEntity>());
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("getAllPermitOfBatteryDeleted [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 getEngineersLibraryService.getAllPermitOfEngineersDeleted((Integer)scenario.get(i).get(0));
//
//		 }
//		 
//	
//	}
//	
//	@Test
//	public void testgetAllPermitOfEngineersDeleted1() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Engineers i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitEngineerEntity p Where "
//					+ "p.engineeredBy = :p1 " )).thenReturn(mockedQuery2);
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
//		 scenario.get(1).add(new Engineers());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(null);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(123);
//		 //Result of the query1
//		 Engineers bc = new Engineers();
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
//		 ArrayList<PermitEngineerEntity> list = new ArrayList<PermitEngineerEntity>();
//		 list.add(null);
//		 list.add(new PermitEngineerEntity());
//		 scenario.get(3).add(list);
//	     //Result excpected
//		 scenario.get(3).add(new ArrayList<PermitEntity>());
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("getAllPermitOfEngineersDeleted1 [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 getEngineersLibraryService.getAllPermitOfEngineersDeleted1((int)scenario.get(i).get(0));
//
//		 }
//	}
//	
//	@Test
//	public void testdeleteEngineersLibrary() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Engineers i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitEngineerEntity p Where "
//					+ "p.permitEntity = :p1 and p.engineeredBy = :p2")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 
//		 GetEngineersLibraryService getEngineersLibraryService2 = Mockito.spy(getEngineersLibraryService);
//
//        ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//        
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0).add(0);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result of the query2
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(false);
//		 //Result of the function getAllPermitOfEngineersDeleted1
//		 scenario.get(0).add(null);
//		 //Result of the query2 list
//		 scenario.get(0).add(null);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(123);
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(false);
//		 //Result of the function getAllPermitOfBatteryDeleted1
//		 scenario.get(1).add(null);
//		 //Result of the query2 list
//		 scenario.get(1).add(null);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add(223);
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 scenario.get(2).add(null);
//		 //Result of the query2
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add(false);
//		 //Result of the function getAllPermitOfEngineersDeleted1
//		 ArrayList<PermitEntity> listFun = new ArrayList<PermitEntity>();
//		 listFun.add(null);
//		 listFun.add(new PermitEntity());
//		 PermitEntity pr = new PermitEntity();
//		 pr.setId(123);
//		 listFun.add(pr);
//		 scenario.get(2).add(listFun);
//		 //Result of the query2 list
//		 scenario.get(2).add(null);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add(323);
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 scenario.get(3).add(new Engineers());
//		 //Result of the query2
//		 scenario.get(3).add(null);
//	     //Result excpected
//		 scenario.get(3).add(true);
//		 //Result of the function getAllPermitOfEngineersDeleted1
//		 scenario.get(3).add(listFun);
//		 //Result of the query2 list
//		 scenario.get(3).add(null);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add(423);
//		 scenario.get(4).add(null);
//		 //Result of the query1
//		 scenario.get(4).add(new Engineers());
//		 //Result of the query2
//		 PermitEngineerEntity prAdd = new PermitEngineerEntity();
//		 prAdd.setAdressIng("");
//		 scenario.get(4).add(prAdd);
//	     //Result excpected
//		 scenario.get(4).add(true);
//		 //Result of the function getAllPermitOfEngineersDeleted1
//		 scenario.get(4).add(listFun);
//		 //Result of the query2 list
//		 ArrayList<PermitEngineerEntity> listPrAdd = new ArrayList<PermitEngineerEntity>();
//		 listPrAdd.add(prAdd);
//		 scenario.get(4).add(listPrAdd);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("deleteEngineersLibrary [ "+i+" ]");
//          Mockito.doReturn((List)scenario.get(i).get(5)).when(getEngineersLibraryService2).getAllPermitOfEngineersDeleted1(Mockito.anyInt());
//
//          when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//          when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//          when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(6));
//           getEngineersLibraryService2.deleteEngineersLibrary((int)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	}
//	
//	@Test
//	public void testcheckEngineersExistent() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Engineers u WHERE u.company=:p1 AND u.contact=:p2 AND u.licenseState=:p3 AND u.isDeleted=false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//	      ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(0).add(null);
//			 //Result of the query1
//			 scenario.get(0).add(null);
//		     //Result excpected
//			 scenario.get(0).add("fail");
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(1).add(new SearchEngineersResult());
//			 //Result of the query1
//			 scenario.get(1).add(null);
//		     //Result excpected
//			 scenario.get(1).add("fail");
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(2).add(new SearchEngineersResult());
//			 //Result of the query1
//			 scenario.get(2).add(new ArrayList<Engineers>());
//		     //Result excpected
//			 scenario.get(2).add("success");
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(3).add(new SearchEngineersResult());
//			 //Result of the query1
//			 ArrayList<Engineers> list = new ArrayList<Engineers>();
//			 list.add(null);
//			 scenario.get(3).add(list);
//		     //Result excpected
//			 scenario.get(3).add("exist");
//			 
//			 
//			 for(int i=0;i<scenario.size();i++) {
//				 System.out.println("checkEngineersExistent [ "+i+" ]");
//	             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//	             getEngineersLibraryService.checkEngineersExistent((SearchEngineersResult)scenario.get(i).get(0));
//
//			 }
//	}
//	
//	@Test
//	public void testaddNewEngineers() {
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//        ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new Engineers());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(new SearchEngineersResult());
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 Engineers bt = new Engineers();
//		 Date d = new Date();
//		 SimpleDateFormat FormattedDATE = new SimpleDateFormat("MM/dd/yyyy");
//		 String updatedDate = FormattedDATE.format(d); 
//		 bt.setUpdated(updatedDate);
//		 scenario.get(1).add(bt);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add(new SearchEngineersResult());
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add(bt);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add(new SearchEngineersResult());
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 scenario.get(3).add(null);
//	     //Result excpected
//		 scenario.get(3).add(bt);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add(new SearchEngineersResult());
//		 scenario.get(4).add("");
//		 //Result of the query1
//		 scenario.get(4).add(null);
//	     //Result excpected
//		 scenario.get(4).add(bt);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(5).add(new SearchEngineersResult());
//		 scenario.get(5).add("123");
//		 //Result of the query1
//		 scenario.get(5).add(null);
//	     //Result excpected
//		 scenario.get(5).add(bt);
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(6).add(new SearchEngineersResult());
//		 scenario.get(6).add("123");
//		 //Result of the query1
//		 AuthentificationEntity auth = new AuthentificationEntity();
//		 auth.setRoleEntity(new RoleEntity());
//		 scenario.get(6).add(auth);
//	     //Result excpected
//		 Engineers bt2 = new Engineers();
//		 bt2.setUpdated(updatedDate);
//		 bt2.setHasSuperUserEdit(false);
//		 Date addDate = new Date();
//		 bt2.setAddDate(addDate);
//		 bt2.setAuthentificationEntity(auth);
//		 scenario.get(6).add(bt2);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("addNewEngineers [ "+i+" ]");
//            when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//            getEngineersLibraryService.addNewEngineers((SearchEngineersResult)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	}
//	
//	@Test
//	public void testgetListOfEnginners() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Engineers u WHERE  u.isDeleted= :p1 ORDER BY u.company")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//	      ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//			 scenario.add(new ArrayList<Object>());
//			 //Result of the query1
//			 scenario.get(0).add(null);
//		     //Result excpected
//			 scenario.get(0).add(null);
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //Result of the query1
//			 scenario.get(1).add(new ArrayList<Engineers>());
//		     //Result excpected
//			 scenario.get(1).add(new ArrayList<Engineers>());
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //Result of the query1
//			ArrayList<Engineers> list = new ArrayList<Engineers>();
//			list.add(null);
//			list.add(new Engineers());
//			 scenario.get(2).add(list);
//		     //Result excpected
//			 scenario.get(2).add(list);
//			 
//			 
//			 for(int i=0;i<scenario.size();i++) {
//				 System.out.println("getListOfEnginners [ "+i+" ]");
//	            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//	             ArrayList<Engineers> result = ((ArrayList<Engineers>) getEngineersLibraryService.getListOfEnginners());
//
//	           
//
//			 }
//		 
//	}
//
//}
