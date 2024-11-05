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
//import com.PlayGroundAdv.Solar.Entity.Engineers;
//import com.PlayGroundAdv.Solar.Entity.Flashing;
//import com.PlayGroundAdv.Solar.Entity.PermitEngineerEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.Entity.RoofMaterialType;
//import com.PlayGroundAdv.Solar.model.FlashingFavRequest;
//import com.PlayGroundAdv.Solar.model.SearchEngineersRequest;
//import com.PlayGroundAdv.Solar.model.SearchEngineersResult;
//import com.PlayGroundAdv.Solar.model.SearchRoofMaterialTypeRequest;
//import com.PlayGroundAdv.Solar.model.SearchRoofMaterialTypeResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetEngineersLibraryService;
//import com.PlayGroundAdv.Solar.Services.GetFlashingLibraryService;
//import com.PlayGroundAdv.Solar.Services.GetRoofMaterialTypeLibraryService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//
//public class TestGetRoofMaterialTypeLibraryService {
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
//	GetRoofMaterialTypeLibraryService getRoofMaterialTypeLibraryService = new GetRoofMaterialTypeLibraryService();
//	
//	
//    @Before
//	public void setupMock() {
//    	getRoofMaterialTypeLibraryService = new GetRoofMaterialTypeLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testsearchRoofMaterialType() {
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		Path battery = mock(Path.class);
//
//		when(criteriaBuilder.createQuery(SearchRoofMaterialTypeResult.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(RoofMaterialType.class)).thenReturn(root);
//		when(root.get(Mockito.anyString())).thenReturn(battery);
//		// when(r.get("authentificationEntity")).thenReturn(authentificationEntity);
//		when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//		when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//		when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//		when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<SearchRoofMaterialTypeResult>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new SearchRoofMaterialTypeRequest());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<SearchRoofMaterialTypeResult>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new SearchRoofMaterialTypeRequest());
//		scenario.get(2).add("");
//		scenario.get(2).add("");
//		scenario.get(2).add("");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<SearchRoofMaterialTypeResult>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new SearchRoofMaterialTypeRequest());
//		scenario.get(3).add("123");
//		scenario.get(3).add("123");
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new ArrayList<SearchRoofMaterialTypeResult>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(new SearchRoofMaterialTypeRequest());
//		scenario.get(4).add("123");
//		scenario.get(4).add("123");
//		scenario.get(4).add("123");
//		// Result of the query1
//		List<SearchRoofMaterialTypeResult> list = new ArrayList<SearchRoofMaterialTypeResult>();
//		list.add(null);
//		list.add(new SearchRoofMaterialTypeResult());
//		scenario.get(4).add(list);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("searchRoofMaterialType [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//
//			ArrayList<SearchRoofMaterialTypeResult> result = ((ArrayList<SearchRoofMaterialTypeResult>) getRoofMaterialTypeLibraryService
//					.searchRoofMaterialType((SearchRoofMaterialTypeRequest) scenario.get(i).get(0),
//							(String) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3)));
//
//		}
//
//	}
//	
//	@Test
//	public void testgetNumberSearchRoofMaterialType() {
//
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(RoofMaterialType.class)).thenReturn(root);
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
//		 SearchRoofMaterialTypeRequest sc = new SearchRoofMaterialTypeRequest();
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
//			 System.out.println("getNumberSearchRoofMaterialType [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//	        getRoofMaterialTypeLibraryService.getNumberSearchRoofMaterialType((SearchRoofMaterialTypeRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		 }
//   
//	
//	}
//	
//	@Test
//	public void testgetRoofMaterialTypeLibrary() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from RoofMaterialType u order by u.typeRoof")).thenReturn(mockedQuery1);
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
//				 scenario.get(0).add(new ArrayList<SearchRoofMaterialTypeResult>());
//				 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(1).add("");
//				 scenario.get(1).add("");
//				 scenario.get(1).add("");
//				 //Result of query1
//				 scenario.get(1).add(null); 
//				//Excpected Result
//				 scenario.get(1).add(new ArrayList<SearchRoofMaterialTypeResult>());
//				 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 //Result of query1
//				 scenario.get(2).add(null); 
//				//Excpected Result
//				 scenario.get(2).add(new ArrayList<SearchRoofMaterialTypeResult>());
//				 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(3).add("123");
//				 scenario.get(3).add("123");
//				 scenario.get(3).add("123");
//				 //Result of query1
//				 List<RoofMaterialType> list = new ArrayList<RoofMaterialType>();
//				 list.add(null);
//				 RoofMaterialType r =new RoofMaterialType();
//				 r.setId(123333);
//				 r.setTypeRoof("abc");
//				 list.add(r);
//				 scenario.get(3).add(list); 
//				//Excpected Result
//				 List<SearchRoofMaterialTypeResult> listExp = new ArrayList<SearchRoofMaterialTypeResult>();
//				 SearchRoofMaterialTypeResult fl= new SearchRoofMaterialTypeResult();
//				 fl.setTypeRoof("abc");
//				 fl.setId(123333);
//				 listExp.add(new SearchRoofMaterialTypeResult());
//				 listExp.add(fl);
//				 scenario.get(3).add(listExp);
//				 
//				 for(int i=0;i<scenario.size();i++) {
//					 System.out.println("getRoofMaterialTypeLibrary [ "+i+" ]");
//		            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//		             ArrayList<SearchRoofMaterialTypeResult> result = ((ArrayList<SearchRoofMaterialTypeResult>) getRoofMaterialTypeLibraryService.getRoofMaterialTypeLibrary((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2)));
//
//
//				 }
//	
//	}
//	
//	@Test
//	public void testaddRoofMaterialType() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT  u.typeRoof from RoofMaterialType u where u.typeRoof=:p1 ")).thenReturn(mockedQuery1);
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
//			 scenario.get(1).add(new SearchRoofMaterialTypeResult());
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
//			 scenario.get(2).add(new SearchRoofMaterialTypeResult());
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 //Result of query1
//			 scenario.get(2).add(new ArrayList<RoofMaterialType>()); 
//			 //Result of query2
//			 scenario.get(2).add(null); 
//			//Excpected Result
//			 scenario.get(2).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(3).add("");
//			 scenario.get(3).add(new SearchRoofMaterialTypeResult());
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 //Result of query1
//			 scenario.get(3).add(new ArrayList<RoofMaterialType>()); 
//			 //Result of query2
//			 scenario.get(3).add(null); 
//			//Excpected Result
//			 scenario.get(3).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(4).add("123");
//			 scenario.get(4).add(new SearchRoofMaterialTypeResult());
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 //Result of query1
//			 scenario.get(4).add(new ArrayList<RoofMaterialType>()); 
//			 //Result of query2
//			 scenario.get(4).add(null); 
//			//Excpected Result
//			 scenario.get(4).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(5).add("123");
//			 scenario.get(5).add(new SearchRoofMaterialTypeResult());
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 //Result of query1
//			 scenario.get(5).add(new ArrayList<RoofMaterialType>()); 
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
//			 scenario.get(6).add(new SearchRoofMaterialTypeResult());
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 //Result of query1
//			 ArrayList<RoofMaterialType> list = new ArrayList<RoofMaterialType>();
//			 list.add(null);
//			 list.add(new RoofMaterialType());
//			 scenario.get(6).add(list); 
//			 //Result of query2
//			 scenario.get(6).add(auth); 
//			//Excpected Result
//			 scenario.get(6).add("RoofMaterialType already exists in data sources");
//			 
//			 for(int i=0; i<scenario.size();i++) {
//				 System.out.println("addRoofMaterialType [ "+i+" ]");
//	            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(5));
//	            when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(6));
//	            getRoofMaterialTypeLibraryService.addRoofMaterialType((String)scenario.get(i).get(0),(SearchRoofMaterialTypeResult)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4));
//
//			 }
//	
//	
//	}
//	
//	@Test
//	public void testeditRoofMaterialType() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofMaterialType u WHERE u.typeRoof = :p1 AND u.isDeleted=false"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		GetRoofMaterialTypeLibraryService getRoofMaterialTypeLibraryService2 = Mockito
//				.spy(getRoofMaterialTypeLibraryService);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add("fail");
//		// Result of query1 singleResult
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new SearchRoofMaterialTypeResult());
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("fail");
//		// Result of query1 singleResult
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new SearchRoofMaterialTypeResult());
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(new ArrayList<RoofMaterialType>());
//		// Excpected Result
//		scenario.get(2).add("success");
//		// Result of query1 singleResult
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new SearchRoofMaterialTypeResult());
//		scenario.get(3).add(null);
//		// Result of query1
//		ArrayList<RoofMaterialType> list = new ArrayList<RoofMaterialType>();
//		list.add(null);
//		list.add(new RoofMaterialType());
//		scenario.get(3).add(list);
//		// Excpected Result
//		scenario.get(3).add("success");
//		// Result of query1 singleResult
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(new SearchRoofMaterialTypeResult());
//		scenario.get(4).add(null);
//		// Result of query1
//		scenario.get(4).add(list);
//		// Excpected Result
//		scenario.get(4).add("success");
//		// Result of query1 singleResult
//		scenario.get(4).add(new RoofMaterialType());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(new SearchRoofMaterialTypeResult());
//		scenario.get(5).add(null);
//		// Result of query1
//		scenario.get(5).add(list);
//		// Excpected Result
//		scenario.get(5).add("exist");
//		// Result of query1 singleResult
//		RoofMaterialType bc = new RoofMaterialType();
//		bc.setId(12589);
//		scenario.get(5).add(bc);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editRoofMaterialType [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			Mockito.doReturn("success").when(getRoofMaterialTypeLibraryService2)
//					.editRoofMaterialTypeFunction(Mockito.any(), Mockito.any());
//
//			getRoofMaterialTypeLibraryService2.editRoofMaterialType(
//					(SearchRoofMaterialTypeResult) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//	
//	@Test
//	public void testeditRoofMaterialTypeFunction() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from RoofMaterialType u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM PermitProjectSiteInfoEntity u WHERE u.roofMaterialType= :p1 ")).thenReturn(mockedQuery2);
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
//			 scenario.get(1).add(new SearchRoofMaterialTypeResult());
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
//			 scenario.get(2).add(new SearchRoofMaterialTypeResult());
//			 scenario.get(2).add(null);
//			 //Result of query1
//			 scenario.get(2).add(new RoofMaterialType()); 
//			 //Result of query2
//			 scenario.get(2).add(null); 
//			//Excpected Result
//			 scenario.get(2).add("success");
//			 
//			 
//			
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(3).add(new SearchRoofMaterialTypeResult());
//			 scenario.get(3).add(null);
//			 //Result of query1
//			 scenario.get(3).add(new RoofMaterialType()); 
//			 //Result of query2
//			 List<PermitProjectSiteInfoEntity> list = new ArrayList<PermitProjectSiteInfoEntity>();
//			 list.add(null);
//			 list.add(new PermitProjectSiteInfoEntity());
//			 PermitProjectSiteInfoEntity el = new PermitProjectSiteInfoEntity();
//			 el.setRoofMaterialType("");
//			 scenario.get(3).add(list); 
//			//Excpected Result
//			 scenario.get(3).add("success");
//			 
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("editRoofMaterialTypeFunction [ "+i+" ]");
//		     when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//           when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//           getRoofMaterialTypeLibraryService.editRoofMaterialTypeFunction((SearchRoofMaterialTypeResult)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//	
//	}
//	
//	@Test
//	public void testeditRoofMaterialTypeNotification() {
//
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
//			 System.out.println("editRoofMaterialTypeNotification [ "+i+" ]");
//	        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//	        getRoofMaterialTypeLibraryService.editRoofMaterialTypeNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testaddRoofMaterialTypeNotification() {
//
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
//			 System.out.println("addRoofMaterialTypeNotification [ "+i+" ]");
//	        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//	        getRoofMaterialTypeLibraryService.addRoofMaterialTypeNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testroofMaterialTypeLibraryActived() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from RoofMaterialType i Where i.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from RoofMaterialType u WHERE u.typeRoof = :p1  AND u.isDeleted=false")).thenReturn(mockedQuery2);
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
//		 RoofMaterialType ac1 = new RoofMaterialType();
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
//		 RoofMaterialType acCombiner = new RoofMaterialType();
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
//		 scenario.get(6).add(new ArrayList<RoofMaterialType>());
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
//		 ArrayList<RoofMaterialType> listAcCom= new ArrayList<RoofMaterialType>();
//		 listAcCom.add(new RoofMaterialType());
//		 scenario.get(7).add(listAcCom);
//		 //The result excpected
//		 scenario.get(7).add("exist");
//		 
//		
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("flashingLibraryActived [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			 getRoofMaterialTypeLibraryService.roofMaterialTypeLibraryActived(123,(String)scenario.get(i).get(1));
//
//		 }
//	
//	
//	}
//	
//	@Test
//	public void testgetAllPermitOfRoofMaterialTypeDeleted() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from RoofMaterialType i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//					+ "p.roofMaterialType = :p1 " )).thenReturn(mockedQuery2);
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
//		 scenario.get(1).add(new RoofMaterialType());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<PermitEntity>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(123);
//		 //Result of the query1
//		 RoofMaterialType bc = new RoofMaterialType();
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
//			  System.out.println("getAllPermitOfRoofMaterialTypeDeleted [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 getRoofMaterialTypeLibraryService.getAllPermitOfRoofMaterialTypeDeleted((int)scenario.get(i).get(0));
//
//		 }
//	
//	}
//
//	@Test
//	public void testgetAllPermitOfRoofMaterialTypeDeleted1() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from RoofMaterialType i Where i.id = :p1 " )).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//					+ "p.roofMaterialType = :p1 " )).thenReturn(mockedQuery2);
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
//		 scenario.get(1).add(new RoofMaterialType());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(null);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(123);
//		 //Result of the query1
//		 RoofMaterialType bc = new RoofMaterialType();
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
//			  System.out.println("getAllPermitOfEngineersDeleted1 [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			  getRoofMaterialTypeLibraryService.getAllPermitOfRoofMaterialTypeDeleted1((int)scenario.get(i).get(0));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testdeleteRoofMaterialTypeLibrary() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from RoofMaterialType i Where i.id = :p1 " )).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//					+ "p.permitEntity = :p1 and p.roofMaterialType = :p2")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 
//		 GetRoofMaterialTypeLibraryService getRoofMaterialTypeLibraryService2 = Mockito.spy(getRoofMaterialTypeLibraryService);
//
//       ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		 scenario.get(3).add(new RoofMaterialType());
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
//		 scenario.get(4).add(new RoofMaterialType());
//		 //Result of the query2
//		 PermitProjectSiteInfoEntity prAdd = new PermitProjectSiteInfoEntity();
//		 prAdd.setUploadComments("");
//		 scenario.get(4).add(prAdd);
//	     //Result excpected
//		 scenario.get(4).add(true);
//		 //Result of the function getAllPermitOfEngineersDeleted1
//		 scenario.get(4).add(listFun);
//		 //Result of the query2 list
//		 ArrayList<PermitProjectSiteInfoEntity> listPrAdd = new ArrayList<PermitProjectSiteInfoEntity>();
//		 listPrAdd.add(prAdd);
//		 scenario.get(4).add(listPrAdd);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("deleteEngineersLibrary [ "+i+" ]");
//         Mockito.doReturn((List)scenario.get(i).get(5)).when(getRoofMaterialTypeLibraryService2).getAllPermitOfRoofMaterialTypeDeleted1(Mockito.anyInt());
//
//         when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//         when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//         when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(6));
//         getRoofMaterialTypeLibraryService2.deleteRoofMaterialTypeLibrary((int)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testcheckRoofMaterialTypeExistent() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from RoofMaterialType u WHERE u.typeRoof = :p1 AND u.isDeleted = false ")).thenReturn(mockedQuery1);
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
//			 scenario.get(1).add(new SearchRoofMaterialTypeResult());
//			 //Result of the query1
//			 scenario.get(1).add(null);
//		     //Result excpected
//			 scenario.get(1).add("fail");
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(2).add(new SearchRoofMaterialTypeResult());
//			 //Result of the query1
//			 scenario.get(2).add(new ArrayList<RoofMaterialType>());
//		     //Result excpected
//			 scenario.get(2).add("success");
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(3).add(new SearchRoofMaterialTypeResult());
//			 //Result of the query1
//			 ArrayList<RoofMaterialType> list = new ArrayList<RoofMaterialType>();
//			 list.add(null);
//			 scenario.get(3).add(list);
//		     //Result excpected
//			 scenario.get(3).add("exist");
//			 
//			 
//			 for(int i=0;i<scenario.size();i++) {
//				 System.out.println("checkEngineersExistent [ "+i+" ]");
//	             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//	             getRoofMaterialTypeLibraryService.checkRoofMaterialTypeExistent((SearchRoofMaterialTypeResult)scenario.get(i).get(0));
//
//			 }
//	
//	}
//	
//    @Test
//	public void testaddNewRoofMaterialType() {
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//       ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new RoofMaterialType());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(new SearchRoofMaterialTypeResult());
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 RoofMaterialType bt = new RoofMaterialType();
//		 Date d = new Date();
//		 SimpleDateFormat FormattedDATE = new SimpleDateFormat("MM/dd/yyyy");
//		 String updatedDate = FormattedDATE.format(d); 
//		 bt.setUpdated(updatedDate);
//		 scenario.get(1).add(bt);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add(new SearchRoofMaterialTypeResult());
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add(bt);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add(new SearchRoofMaterialTypeResult());
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 scenario.get(3).add(null);
//	     //Result excpected
//		 scenario.get(3).add(bt);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add(new SearchRoofMaterialTypeResult());
//		 scenario.get(4).add("");
//		 //Result of the query1
//		 scenario.get(4).add(null);
//	     //Result excpected
//		 scenario.get(4).add(bt);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(5).add(new SearchRoofMaterialTypeResult());
//		 scenario.get(5).add("123");
//		 //Result of the query1
//		 scenario.get(5).add(null);
//	     //Result excpected
//		 scenario.get(5).add(bt);
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(6).add(new SearchRoofMaterialTypeResult());
//		 scenario.get(6).add("123");
//		 //Result of the query1
//		 AuthentificationEntity auth = new AuthentificationEntity();
//		 auth.setRoleEntity(new RoleEntity());
//		 scenario.get(6).add(auth);
//	     //Result excpected
//		 RoofMaterialType bt2 = new RoofMaterialType();
//		 bt2.setUpdated(updatedDate);
//		 bt2.setHasSuperUserEdit(false);
//		 Date addDate = new Date();
//		 bt2.setAddDate(addDate);
//		 bt2.setAuthentificationEntity(auth);
//		 scenario.get(6).add(bt2);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("addNewRoofMaterialType [ "+i+" ]");
//           when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//            getRoofMaterialTypeLibraryService.addNewRoofMaterialType((SearchRoofMaterialTypeResult)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//    }
//	@Test
//	public void testgetListOfRoofMaterialType() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from RoofMaterialType u WHERE  u.isDeleted= :p1 ORDER BY u.typeRoof")).thenReturn(mockedQuery1);
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
//			 scenario.get(1).add(new ArrayList<RoofMaterialType>());
//		     //Result excpected
//			 scenario.get(1).add(new ArrayList<RoofMaterialType>());
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //Result of the query1
//			ArrayList<RoofMaterialType> list = new ArrayList<RoofMaterialType>();
//			list.add(null);
//			list.add(new RoofMaterialType());
//			 scenario.get(2).add(list);
//		     //Result excpected
//			 scenario.get(2).add(list);
//			 
//			 
//			 for(int i=0;i<scenario.size();i++) {
//				 System.out.println("getListOfEnginners [ "+i+" ]");
//	            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//	             ArrayList<RoofMaterialType> result = ((ArrayList<RoofMaterialType>) getRoofMaterialTypeLibraryService.getListOfRoofMaterialType());
//
//
//			 }
//		 
//	
//	}
//
//	@Test
//	public void testgetRoofMaterialType() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.typeRoof from RoofMaterialType u WHERE u.id= :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result Expected
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// Parameter
//		scenario.get(1).add(123);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result Expected
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// Parameter
//		scenario.get(2).add(123);
//		// Result of the query1
//		scenario.get(2).add("");
//		// Result Expected
//		scenario.get(2).add("");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getRoofMaterialType [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			
//					getRoofMaterialTypeLibraryService.getRoofMaterialType((Integer) scenario.get(i).get(0));
//		}
//
//	}
//
//}
