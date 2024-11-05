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
//import com.PlayGroundAdv.Solar.Entity.Flashing;
//import com.PlayGroundAdv.Solar.Entity.FlashingFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitAdditionalInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEngineerEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.model.BatteryCorrectionRequest;
//import com.PlayGroundAdv.Solar.model.BatteryFavRequest;
//import com.PlayGroundAdv.Solar.model.FlashingCorrectionRequest;
//import com.PlayGroundAdv.Solar.model.FlashingFavRequest;
//import com.PlayGroundAdv.Solar.model.SearchBatteryRequest;
//import com.PlayGroundAdv.Solar.model.SearchBatteryResult;
//import com.PlayGroundAdv.Solar.model.SearchEngineersResult;
//import com.PlayGroundAdv.Solar.model.SearchFlashingRequest;
//import com.PlayGroundAdv.Solar.model.SearchFlashingResult;
//import com.PlayGroundAdv.Solar.model.UsersEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetBatteryLibraryService;
//import com.PlayGroundAdv.Solar.Services.GetEngineersLibraryService;
//import com.PlayGroundAdv.Solar.Services.GetFlashingLibraryService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//
//public class TestGetFlashingLibraryService {
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
//	GetFlashingLibraryService getFlashingLibraryService = new GetFlashingLibraryService();
//	
//	
//    @Before
//	public void setupMock() {
//    	getFlashingLibraryService = new GetFlashingLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testsearchFlashing() {
//		 // Root<BatteryFavLibraryEntity> r = Mockito.mock(Root.class);
//		  TypedQuery mockedQuery1 = mock(TypedQuery.class);
//			when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//			Path battery = mock(Path.class);
//
//			when(criteriaBuilder.createQuery(SearchFlashingResult.class)).thenReturn(criteriaQuery);
//			when(criteriaQuery.from(FlashingFavLibraryEntity.class)).thenReturn(root);
//			when(criteriaQuery.from(Flashing.class)).thenReturn(root);
//			when(root.get(Mockito.anyString())).thenReturn(battery);
//			//when(r.get("authentificationEntity")).thenReturn(authentificationEntity);
//	        when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//	        when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//	        when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//	        when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//	        
//			 
//			 
//		   	 Query mockedQuery2 = mock(Query.class);
//			 when(em.createQuery("SELECT u from FlashingFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 ")).thenReturn(mockedQuery2);
//			 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
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
//			 scenario.get(0).add(new ArrayList<SearchFlashingResult>());
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 SearchFlashingRequest sc = new SearchFlashingRequest();
//			 sc.setIsFav(true);
//			 scenario.get(1) .add(sc);
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 //Result of the query1
//			 scenario.get(1).add(null);
//			 //Result of the query2
//			 scenario.get(1).add(null);
//		     //Result excpected
//			 scenario.get(1).add(new ArrayList<SearchFlashingResult>());
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(2) .add(sc);
//			 scenario.get(2).add("abc");
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 //Result of the query1
//			 scenario.get(2).add(null);
//			 //Result of the query2
//			 scenario.get(2).add(null);
//		     //Result excpected
//			 scenario.get(2).add(new ArrayList<SearchFlashingResult>());
//			 
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(3) .add(sc);
//			 scenario.get(3).add("123");
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 //Result of the query1
//			 scenario.get(3).add(null);
//			 //Result of the query2
//			 scenario.get(3).add(null);
//		     //Result excpected
//			 scenario.get(3).add(new ArrayList<SearchFlashingResult>());
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(4) .add(sc);
//			 scenario.get(4).add("123");
//			 scenario.get(4).add("");
//			 scenario.get(4).add("");
//			 //Result of the query1
//			 scenario.get(4).add(null);
//			 //Result of the query2
//			 scenario.get(4).add(null);
//		     //Result excpected
//			 scenario.get(4).add(new ArrayList<SearchFlashingResult>());
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(5) .add(sc);
//			 scenario.get(5).add("123");
//			 scenario.get(5).add("123");
//			 scenario.get(5).add("123");
//			 //Result of the query1
//			 scenario.get(5).add(null);
//			 //Result of the query2
//			 scenario.get(5).add(null);
//		     //Result excpected
//			 scenario.get(5).add(new ArrayList<SearchFlashingResult>());
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(6) .add(sc);
//			 scenario.get(6).add("123");
//			 scenario.get(6).add("123");
//			 scenario.get(6).add("123");
//			 //Result of the query1
//			 List<SearchFlashingResult> list = new ArrayList<SearchFlashingResult>();
//			 list.add(null);
//			 list.add(new SearchFlashingResult());
//			 SearchFlashingResult scBat = new SearchFlashingResult();
//			 scBat.setEroneousContent("");
//			 scBat.setId(458999);
//			 list.add(scBat);
//			 scenario.get(6).add(list);
//			 //Result of the query2
//			 scenario.get(6).add(null);
//		     //Result excpected
//			 ArrayList<SearchFlashingResult> listExp = new ArrayList<SearchFlashingResult>();
//			 SearchFlashingResult exp1 = new SearchFlashingResult();
//			 exp1.setIsFav(false);
//			 listExp.add(exp1);
//			 SearchFlashingResult exp2 = new SearchFlashingResult();
//			 exp2.setEroneousContent("");
//			 exp2.setId(458999);
//			 exp2.setIsFav(false);
//			 listExp.add(exp2);
//			 scenario.get(6).add(listExp);
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(7) .add(sc);
//			 scenario.get(7).add("123");
//			 scenario.get(7).add("123");
//			 scenario.get(7).add("123");
//			 //Result of the query1
//			 scenario.get(7).add(list);
//			 //Result of the query2
//			 List<FlashingFavLibraryEntity> list2 = new ArrayList<FlashingFavLibraryEntity>();
//			 list2.add(null);
//			 list2.add(new FlashingFavLibraryEntity());
//			 FlashingFavLibraryEntity bt = new FlashingFavLibraryEntity();
//			 bt.setId(12358);
//			 Flashing batt = new Flashing();
//			  batt.setManufacturer("bbb_ccc");
//			 bt.setFlashing(batt);
//			 list2.add(bt);
//			 scenario.get(7).add(list2);
//		     //Result excpected
//			 ArrayList<SearchFlashingResult> listExp2 = new ArrayList<SearchFlashingResult>();
//			 SearchFlashingResult scBt2 = new SearchFlashingResult();
//			 scBt2.setIsFav(true);
//			 listExp2.add(scBt2);
//			 scenario.get(7).add(listExp2);
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 SearchFlashingRequest sc2 = new SearchFlashingRequest();
//			 sc2.setIsFav(true);
//			 sc2.setManufacturer("aaa bbb_ccc eee_fff");
//			 scenario.get(8) .add(sc2);
//			 scenario.get(8).add("123");
//			 scenario.get(8).add("123");
//			 scenario.get(8).add("123");
//			 //Result of the query1
//			 scenario.get(8).add(list);
//			 //Result of the query2
//			 scenario.get(8).add(list2);
//		     //Result excpected
//			 scenario.get(8).add(listExp2);
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(9) .add(sc2);
//			 scenario.get(9).add("123");
//			 scenario.get(9).add("123");
//			 scenario.get(9).add("123");
//			 //Result of the query1
//			 scenario.get(9).add(list);
//			 //Result of the query2
//			 List<FlashingFavLibraryEntity> list3 = new ArrayList<FlashingFavLibraryEntity>();
//			 list3.add(null);
//			 list3.add(new FlashingFavLibraryEntity());
//			 FlashingFavLibraryEntity bt2 = new FlashingFavLibraryEntity();
//			 bt2.setId(12358);
//			 Flashing batt2 = new Flashing();
//			  batt2.setManufacturer("bbb_ccc");
//			  batt2.setId(12358);
//			 bt2.setFlashing(batt2);
//			 list3.add(bt2);
//			 scenario.get(9).add(list3);
//		     //Result excpected
//			 ArrayList<SearchFlashingResult> listExp3 = new ArrayList<SearchFlashingResult>();
//			 SearchFlashingResult scBt3 = new SearchFlashingResult();
//			 scBt3.setIsFav(true);
//			 scBt3.setId(12358);
//			 scBt3.setManufacturer("bbb_ccc");
//			 scBt3.setIsDeleted(false);
//			 listExp3.add(scBt3);
//			 scenario.get(9).add(listExp3);
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(10).add(new SearchFlashingRequest());
//			 scenario.get(10).add(null);
//			 scenario.get(10).add(null);
//			 scenario.get(10).add(null);
//			 //Result of the query1
//			 scenario.get(10).add(null);
//			 //Result of the query2
//			 scenario.get(10).add(null);
//		     //Result excpected
//			 scenario.get(10).add(new ArrayList<SearchFlashingResult>());
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(11) .add(new SearchFlashingRequest());
//			 scenario.get(11).add("");
//			 scenario.get(11).add("");
//			 scenario.get(11).add("");
//			 //Result of the query1
//			 scenario.get(11).add(null);
//			 //Result of the query2
//			 scenario.get(11).add(null);
//		     //Result excpected
//			 scenario.get(11).add(new ArrayList<SearchFlashingResult>());
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(12) .add(new SearchFlashingRequest());
//			 scenario.get(12).add("123");
//			 scenario.get(12).add("123");
//			 scenario.get(12).add("123");
//			 //Result of the query1
//			 scenario.get(12).add(null);
//			 //Result of the query2
//			 scenario.get(12).add(null);
//		     //Result excpected
//			 scenario.get(12).add(new ArrayList<SearchFlashingResult>());
//			 
//				
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(13) .add(new SearchFlashingRequest());
//			 scenario.get(13).add("123");
//			 scenario.get(13).add("123");
//			 scenario.get(13).add("123");
//			 //Result of the query1
//			 scenario.get(13).add(list);
//			 //Result of the query2
//			 scenario.get(13).add(list2);
//		     //Result excpected
//			 ArrayList<SearchFlashingResult> listExp4 = new ArrayList<SearchFlashingResult>();
//			 SearchFlashingResult sc5 = new SearchFlashingResult();
//			 sc5.setIsFav(true);
//			 listExp4.add(sc5);
//			 SearchFlashingResult sc6 = new SearchFlashingResult();
//			 sc6.setIsFav(false);
//			 sc6.setEroneousContent("");
//			 sc6.setId(458999);
//			 listExp4.add(sc6);
//			 scenario.get(13).add(listExp4);
//			 
//			 			 
//		        for(int i=0;i<scenario.size();i++) {
//					 System.out.println("searchFlashing [ "+i+" ]");
//					 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//					 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//
//		            ArrayList<SearchFlashingResult> result = ((ArrayList<SearchFlashingResult>) getFlashingLibraryService.searchFlashing((SearchFlashingRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3)));
//					
//				 }
//	}
//	
//	@Test
//	public void testsendCorrectionFlashingRequest() {
//
//
//		Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Flashing u Where u.id = :p1 " )).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u  FROM AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		  List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(0).add(null);
//			 //Result of query1
//			 scenario.get(0).add(null); 
//			 //Result of query2
//			 scenario.get(0).add(null); 
//			//Excpected Result
//			 scenario.get(0).add("Fail");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 FlashingCorrectionRequest acComSLC = new FlashingCorrectionRequest();
//			 scenario.get(1).add(acComSLC);
//			 //Result of query1
//			 scenario.get(1).add(null); 
//			 //Result of query2
//			 scenario.get(1).add(null); 
//			//Excpected Result
//			 scenario.get(1).add("Done");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 FlashingCorrectionRequest acComSLC1= new FlashingCorrectionRequest();
//			 acComSLC1.setId(123);
//			 scenario.get(2).add(acComSLC1);
//			 //Result of query1
//			 scenario.get(2).add(null); 
//			 //Result of query2
//			 scenario.get(2).add(null); 
//			//Excpected Result
//			 scenario.get(2).add("Fail");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 FlashingCorrectionRequest acComSLC2= new FlashingCorrectionRequest();
//			 acComSLC2.setId(123);
//			 acComSLC2.setIdUser("");
//			 scenario.get(3).add(acComSLC2);
//			 //Result of query1
//			 scenario.get(3).add(null); 
//			 //Result of query2
//			 scenario.get(3).add(null); 
//			//Excpected Result
//			 scenario.get(3).add("Fail");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 FlashingCorrectionRequest acComSLC4= new FlashingCorrectionRequest();
//			 acComSLC4.setId(123);
//			 acComSLC4.setIdUser("123");
//			 scenario.get(4).add(acComSLC4);
//			 //Result of query1
//			 scenario.get(4).add(null); 
//			 //Result of query2
//			 scenario.get(4).add(null); 
//			//Excpected Result
//			 scenario.get(4).add("Fail");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(5).add(acComSLC4);
//			 //Result of query1
//			 Flashing lib = new Flashing();
//			 scenario.get(5).add(lib); 
//			 //Result of query2
//			 scenario.get(5).add(null); 
//			//Excpected Result
//			 scenario.get(5).add("Fail");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(6).add(acComSLC4);
//			 //Result of query1
//			 scenario.get(6).add(lib); 
//			 //Result of query2
//			 AuthentificationEntity auth = new AuthentificationEntity();
//			 scenario.get(6).add(auth); 
//			//Excpected Result
//			 scenario.get(6).add("Done");
//			 
//			 
//			 for(int i=0;i<scenario.size();i++) {
//				 System.out.println("sendCorrectionBatteryRequest [ "+i+" ]");
//	             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//	             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//	             getFlashingLibraryService.sendCorrectionFlashingRequest( (FlashingCorrectionRequest) scenario.get(i).get(0));
//
//			 }
//			 
//	
//	
//	}
//	
//	@Test
//	public void testgetNumberSearchFlashing() {
//
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(FlashingFavLibraryEntity.class)).thenReturn(root);
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
//		 SearchFlashingRequest sc = new SearchFlashingRequest();
//		 sc.setIsFav(true);
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
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(new SearchFlashingRequest());
//		 scenario.get(3).add(null);
//		 //Result of query1
//		 scenario.get(3).add(8L); 
//		//Excpected Result
//		 scenario.get(3).add(8L);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getNumberSearchFlashing [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//	        getFlashingLibraryService.getNumberSearchFlashing((SearchFlashingRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		 }
//   
//	
//	}
//	
//	@Test
//	public void testgetFlashingLibrary() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Flashing u order by u.manufacturer")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//		 GetFlashingLibraryService getFlashingLibraryService2 = Mockito.spy(getFlashingLibraryService);
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
//				 scenario.get(0).add(new ArrayList<FlashingFavRequest>());
//				 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(1).add("");
//				 scenario.get(1).add("");
//				 scenario.get(1).add("");
//				 //Result of query1
//				 scenario.get(1).add(null); 
//				//Excpected Result
//				 scenario.get(1).add(new ArrayList<FlashingFavRequest>());
//				 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 //Result of query1
//				 scenario.get(2).add(null); 
//				//Excpected Result
//				 scenario.get(2).add(new ArrayList<FlashingFavRequest>());
//				 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(3).add("123");
//				 scenario.get(3).add("123");
//				 scenario.get(3).add("123");
//				 //Result of query1
//				 List<Flashing> list = new ArrayList<Flashing>();
//				 list.add(null);
//				 list.add(new Flashing());
//				 scenario.get(3).add(list); 
//				//Excpected Result
//				 List<FlashingFavRequest> listExp = new ArrayList<FlashingFavRequest>();
//				 FlashingFavRequest fl= new FlashingFavRequest();
//				 fl.setIsFav("false");
//				 listExp.add(new FlashingFavRequest());
//				 listExp.add(fl);
//				 scenario.get(3).add(listExp);
//				 
//				 for(int i=0;i<scenario.size();i++) {
//					 System.out.println("getFlashingLibrary [ "+i+" ]");
//		            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//		             ArrayList<FlashingFavRequest> result = ((ArrayList<FlashingFavRequest>) getFlashingLibraryService2.getFlashingLibrary((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2)));
//			         Mockito.doReturn(new ArrayList<Integer>()).when(getFlashingLibraryService2).getFlashingFavorite(Mockito.any());
//
//
//				 }
//	}
//	
//	@Test
//	public void testgetFlashingFavorite() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from FlashingFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new ArrayList<Integer>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add("");
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<Integer>());
//
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add("123");
//		 //Result of the query1
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add("123");
//		 //Result of the query1
//		 scenario.get(3).add(new ArrayList<FlashingFavLibraryEntity>());
//	     //Result excpected
//		 scenario.get(3).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add("123");
//		 //Result of the query1
//		 ArrayList<FlashingFavLibraryEntity> list = new ArrayList<FlashingFavLibraryEntity>();
//		 list.add(null);
//		 list.add(new FlashingFavLibraryEntity());
//		 scenario.get(4).add(list);
//	     //Result excpected
//		 scenario.get(4).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(5).add("123");
//		 //Result of the query1
//		 ArrayList<FlashingFavLibraryEntity> list2= new ArrayList<FlashingFavLibraryEntity>();
//		 FlashingFavLibraryEntity dc = new FlashingFavLibraryEntity();
//		 dc.setFlashing(new Flashing());
//		 list2.add(dc);
//		 FlashingFavLibraryEntity dc2 = new FlashingFavLibraryEntity();
//		 Flashing dcEnt = new Flashing();
//		 dcEnt.setId(123);
//		 dc2.setFlashing(dcEnt);
//		 list2.add(dc2);
//		 scenario.get(5).add(list2);
//	     //Result excpected
//		 ArrayList<Integer> rlt = new ArrayList<Integer>();
//		 rlt.add(0);
//		 rlt.add(123);
//		 scenario.get(5).add(rlt);
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getFlashingFavorite [ "+i+" ]");
//           when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//          getFlashingLibraryService.getFlashingFavorite((String)scenario.get(i).get(0));
//
//		 }
//	
//	
//	}
//	
//	@Test
//	public void testaddFlashingFavorite() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Flashing u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result of the query2
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add("Done");
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add("");
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add("Done");
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add("123");
//		 //Result of the query1
//		 scenario.get(2).add(null);
//		 //Result of the query2
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add("Fail");
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add(null);
//		 scenario.get(3).add("");
//		 scenario.get(3).add("123");
//		 //Result of the query1
//		 scenario.get(3).add(null);
//		 //Result of the query2
//		 scenario.get(3).add(null);
//	     //Result excpected
//		 scenario.get(3).add("Fail");
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add(null);
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 //Result of the query1
//		 scenario.get(4).add(null);
//		 //Result of the query2
//		 scenario.get(4).add(new Flashing());
//	     //Result excpected
//		 scenario.get(4).add("Fail");
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(5).add(null);
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 //Result of the query1
//		 scenario.get(5).add(new AuthentificationEntity());
//		 //Result of the query2
//		 scenario.get(5).add(new Flashing());
//	     //Result excpected
//		 scenario.get(5).add("Done");
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("addFlashingFavorite [ "+i+" ]");
//           when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//           when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//           getFlashingLibraryService.addFlashingFavorite((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testremoveFlashingFavorite() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from FlashingFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 and u.flashing.id = :p2")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result of the query2
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add("Fail");
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add("");
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add("Fail");
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add("123");
//		 //Result of the query1
//		 scenario.get(2).add(null);
//		 //Result of the query2
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add("Fail");
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add(null);
//		 scenario.get(3).add("");
//		 scenario.get(3).add("123");
//		 //Result of the query1
//		 scenario.get(3).add(null);
//		 //Result of the query2
//		 scenario.get(3).add(null);
//	     //Result excpected
//		 scenario.get(3).add("Fail");
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add(null);
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 //Result of the query1
//		 scenario.get(4).add(null);
//		 //Result of the query2
//		 scenario.get(4).add(new FlashingFavLibraryEntity());
//	     //Result excpected
//		 scenario.get(4).add("Fail");
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(5).add(null);
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 //Result of the query1
//		 scenario.get(5).add(new AuthentificationEntity());
//		 //Result of the query2
//		 FlashingFavLibraryEntity bt = new FlashingFavLibraryEntity();
//		 bt.setFlashing(new Flashing());
//		 scenario.get(5).add(bt);
//	     //Result excpected
//		 scenario.get(5).add("Done");
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("removeFlashingFavorite [ "+i+" ]");
//          when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//          when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//          getFlashingLibraryService.removeFlashingFavorite((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testgetContractorFlashingFav() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Flashing u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 GetFlashingLibraryService getFlashingLibraryService2 = Mockito.spy(getFlashingLibraryService);
//        Mockito.doReturn(new ArrayList<Integer>()).when(getFlashingLibraryService2).getFlashingFavorite(Mockito.any());
//        
//		 List<Integer> batteryFavID = new ArrayList<Integer>();
//		 batteryFavID.add(1);
//		 batteryFavID.add(2);
//		 batteryFavID.add(3);
//		 
//	     List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//	       
//	       
//	         scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(0).add(null);
//			 //Result of the query1
//			 scenario.get(0).add(null);
//		     //Result excpected
//			 scenario.get(0).add(null);
//			 //Result of the function getBatteryFavorite
//			 scenario.get(0).add(null);
//			 
//		       
//	         scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(1).add(null);
//			 //Result of the query1
//			 scenario.get(1).add(null);
//		     //Result excpected
//			 ArrayList<Battery> listExp = new ArrayList<Battery>();
//			 listExp.add(null);
//			 listExp.add(null);
//			 listExp.add(null);
//			 scenario.get(1).add(listExp);
//			 //Result of the function getBatteryFavorite
//			 scenario.get(1).add(batteryFavID);
//			 
//			 
//			  for(int i=0;i<scenario.size();i++) {
//					 System.out.println("getContractorFlashingFav [ "+i+" ]");
//					 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			         Mockito.doReturn(scenario.get(i).get(3)).when(getFlashingLibraryService2).getFlashingFavorite(Mockito.any());
//
//		             ArrayList<Flashing> result = ((ArrayList<Flashing>) getFlashingLibraryService2.getContractorFlashingFav((String) scenario.get(i).get(0)));
//		           
//					 
//					
//				 }
//
//	
//	}
//	
//	@Test
//	public void testgetSuperUserFlashingFav() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u " + " from PermitEntity u " + " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Flashing u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 GetFlashingLibraryService getFlashingLibraryService2 = Mockito.spy(getFlashingLibraryService);
//		 List<Integer> batteryFavID = new ArrayList<Integer>();
//		 batteryFavID.add(1);
//		 batteryFavID.add(2);
//		 batteryFavID.add(3);
//	       List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//	       
//	       
//	         scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(0).add(null);
//			 //Result of the query1
//			 scenario.get(0).add(null);
//			 //Result of the query2
//			 scenario.get(0).add(null);
//		     //Result excpected
//			 scenario.get(0).add(new ArrayList<Flashing>());
//			 
//			 
//	         scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(1).add("");
//			 //Result of the query1
//			 scenario.get(1).add(null);
//			 //Result of the query2
//			 scenario.get(1).add(null);
//		     //Result excpected
//			 scenario.get(1).add(new ArrayList<Flashing>());
//			 
//	         scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(2).add("123");
//			 //Result of the query1
//			 scenario.get(2).add(null);
//			 //Result of the query2
//			 scenario.get(2).add(null);
//		     //Result excpected
//			 scenario.get(2).add(new ArrayList<Flashing>());
//			 
//			 
//	         scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(3).add("123");
//			 //Result of the query1
//			 PermitEntity per = new PermitEntity();
//			 AuthentificationEntity auth = new AuthentificationEntity();
//			 auth.setId(125);
//			 per.setAuthentificationEntity(auth);
//			 scenario.get(3).add(per);
//			 //Result of the query2
//			 scenario.get(3).add(null);
//		     //Result excpected
//			 ArrayList<Flashing> listExp = new ArrayList<Flashing>();
//			 listExp.add(null);
//			 listExp.add(null);
//			 listExp.add(null);
//			 scenario.get(3).add(listExp);
//			 
//			 
//
//			 
//	       for(int i=0;i<scenario.size();i++) {
//				 System.out.println("getSuperUserFlashingFav [ "+i+" ]");
//				 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//				 when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//		         Mockito.doReturn(batteryFavID).when(getFlashingLibraryService2).getFlashingFavorite(Mockito.any());
//
//	             ArrayList<Flashing> result = ((ArrayList<Flashing>) getFlashingLibraryService2.getSuperUserFlashingFav((String) scenario.get(i).get(0)));
//				
//			 }
//	
//	}
//	
//	@Test
//	public void testgetTestFlashingFav() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u "
//					+ " from PermitEntity u "
//					+ " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Flashing u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 GetFlashingLibraryService getFlashingLibraryService2 = Mockito.spy(getFlashingLibraryService);
//		 List<Integer> batteryFavID = new ArrayList<Integer>();
//		 batteryFavID.add(1);
//		 batteryFavID.add(2);
//		 batteryFavID.add(3);
//		 
//	       List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//	       
//	         scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 //Result of the query1
//			 scenario.get(0).add(null);
//			 //Result of the query2
//			 scenario.get(0).add(null);
//		     //Result excpected
//			 scenario.get(0).add(false);
//			 //Reslt of the getBatteryFavorite
//			 scenario.get(0).add(null);
//			 
//	         scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(1).add("");
//			 scenario.get(1).add(null);
//			 //Result of the query1
//			 scenario.get(1).add(null);
//			 //Result of the query2
//			 scenario.get(1).add(null);
//		     //Result excpected
//			 scenario.get(1).add(false);
//			 //Reslt of the getBatteryFavorite
//			 scenario.get(1).add(null);
//			 
//	         scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(2).add("123");
//			 scenario.get(2).add(null);
//			 //Result of the query1
//			 scenario.get(2).add(null);
//			 //Result of the query2
//			 scenario.get(2).add(null);
//		     //Result excpected
//			 scenario.get(2).add(false);
//			 //Reslt of the getBatteryFavorite
//			 scenario.get(2).add(null);
//			 
//			 
//	         scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(3).add("123");
//			 scenario.get(3).add(null);
//			 //Result of the query1
//			 PermitEntity per = new PermitEntity();
//			 AuthentificationEntity auth = new AuthentificationEntity();
//			 auth.setId(125);
//			 per.setAuthentificationEntity(auth);
//			 scenario.get(3).add(per);
//			 //Result of the query2
//			 scenario.get(3).add(null);
//		     //Result excpected
//			 scenario.get(3).add(true);
//			 //Reslt of the getBatteryFavorite
//			 scenario.get(3).add(null);
//			 
//	         scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(4).add("123");
//			 scenario.get(4).add(null);
//			 //Result of the query1
//			 scenario.get(4).add(per);
//			 //Result of the query2
//			 scenario.get(4).add(null);
//		     //Result excpected
//			 scenario.get(4).add(true);
//			 //Reslt of the getBatteryFavorite
//			 scenario.get(4).add(batteryFavID);
//			 
//	         scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(5).add("123");
//			 scenario.get(5).add(null);
//			 //Result of the query1
//			 scenario.get(5).add(per);
//			 //Result of the query2
//			 Flashing bt = new Flashing();
//			 bt.setManufacturer("aa");
//			 bt.setModel("bb");
//			 scenario.get(5).add(bt);
//		     //Result excpected
//			 scenario.get(5).add(true);
//			 //Reslt of the getBatteryFavorite
//			 scenario.get(5).add(batteryFavID);
//			 
//			 
//	         scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(6).add("123");
//			 scenario.get(6).add("aa:bb");
//			 //Result of the query1
//			 scenario.get(6).add(per);
//			 //Result of the query2
//			 scenario.get(6).add(bt);
//		     //Result excpected
//			 scenario.get(6).add(false);
//			 //Reslt of the getBatteryFavorite
//			 scenario.get(6).add(batteryFavID);
//		 
//			 
//			 for(int i=0;i<scenario.size();i++) {
//				 System.out.println("getTestFlashingFav [ "+i+" ]");
//				 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//				 when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//		         Mockito.doReturn(scenario.get(i).get(5)).when(getFlashingLibraryService2).getFlashingFavorite(Mockito.any());
//                 getFlashingLibraryService2.getTestFlashingFav((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//			 }
//	}
//	
//	@Test
//	public void testaddFlashing() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u.model , u.manufacturer from Flashing u where u.manufacturer=:p1 and u.model =:p2 ")).thenReturn(mockedQuery1);
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
//			 scenario.get(1).add(new FlashingFavRequest());
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
//			 scenario.get(2).add(new FlashingFavRequest());
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 //Result of query1
//			 scenario.get(2).add(new ArrayList<Flashing>()); 
//			 //Result of query2
//			 scenario.get(2).add(null); 
//			//Excpected Result
//			 scenario.get(2).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(3).add("");
//			 scenario.get(3).add(new FlashingFavRequest());
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 //Result of query1
//			 scenario.get(3).add(new ArrayList<Flashing>()); 
//			 //Result of query2
//			 scenario.get(3).add(null); 
//			//Excpected Result
//			 scenario.get(3).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(4).add("123");
//			 scenario.get(4).add(new FlashingFavRequest());
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 //Result of query1
//			 scenario.get(4).add(new ArrayList<Flashing>()); 
//			 //Result of query2
//			 scenario.get(4).add(null); 
//			//Excpected Result
//			 scenario.get(4).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(5).add("123");
//			 scenario.get(5).add(new FlashingFavRequest());
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 //Result of query1
//			 scenario.get(5).add(new ArrayList<Flashing>()); 
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
//			 scenario.get(6).add(new FlashingFavRequest());
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 //Result of query1
//			 ArrayList<Flashing> list = new ArrayList<Flashing>();
//			 list.add(null);
//			 list.add(new Flashing());
//			 scenario.get(6).add(list); 
//			 //Result of query2
//			 scenario.get(6).add(auth); 
//			//Excpected Result
//			 scenario.get(6).add("Flashing already exists in data sources");
//			 
//			 for(int i=0; i<scenario.size();i++) {
//				 System.out.println("addFlashing [ "+i+" ]");
//	            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(5));
//	            when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(6));
//	            getFlashingLibraryService.addFlashing((String)scenario.get(i).get(0),(FlashingFavRequest)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4));
//
//			 }
//	
//	}
//	
//	@Test
//	public void testeditFlashing() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Flashing u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 GetFlashingLibraryService getFlashingLibraryService2 = Mockito.spy(getFlashingLibraryService);
//
//		 
//    List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		 scenario.get(1).add(new FlashingFavRequest());
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
//		 scenario.get(2).add(new FlashingFavRequest());
//		 scenario.get(2).add(null);
//		 //Result of query1
//		 scenario.get(2).add(new ArrayList<Flashing>()); 
//		//Excpected Result
//		 scenario.get(2).add("success");
//		//Result of query1 singleResult
//		 scenario.get(2).add(null);  
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(new FlashingFavRequest());
//		 scenario.get(3).add(null);
//		 //Result of query1
//		 ArrayList<Flashing> list = new ArrayList<Flashing>();
//		 list.add(null);
//		 list.add(new Flashing());
//		 scenario.get(3).add(list); 
//		//Excpected Result
//		 scenario.get(3).add("success");
//		//Result of query1 singleResult
//		 scenario.get(3).add(null); 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add(new FlashingFavRequest());
//		 scenario.get(4).add(null);
//		 //Result of query1
//		 scenario.get(4).add(list); 
//		//Excpected Result
//		 scenario.get(4).add("success");
//		//Result of query1 singleResult
//		 scenario.get(4).add(new Flashing()); 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(5).add(new FlashingFavRequest());
//		 scenario.get(5).add(null);
//		 //Result of query1
//		 scenario.get(5).add(list); 
//		//Excpected Result
//		 scenario.get(5).add("exist");
//		//Result of query1 singleResult
//		 Flashing bc = new Flashing();
//		 bc.setId(12589);
//		 scenario.get(5).add(bc); 
//		 
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("editFlashing [ "+i+" ]");
//         when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//         when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//         Mockito.doReturn("success").when(getFlashingLibraryService2).editFlashingFunction(Mockito.any(),Mockito.any());
//
//          getFlashingLibraryService2.editFlashing((FlashingFavRequest)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//	
//	}
//	
//	@Test
//	public void testeditFlashingFunction() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Flashing u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM PermitProjectSiteInfoEntity u WHERE u.flashing= :p1 ")).thenReturn(mockedQuery2);
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
//			 scenario.get(1).add(new FlashingFavRequest());
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
//			 scenario.get(2).add(new FlashingFavRequest());
//			 scenario.get(2).add(null);
//			 //Result of query1
//			 scenario.get(2).add(new Flashing()); 
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
//			 scenario.get(3).add(new FlashingFavRequest());
//			 scenario.get(3).add(null);
//			 //Result of query1
//			 scenario.get(3).add(new Flashing()); 
//			 //Result of query2
//			 List<PermitProjectSiteInfoEntity> list = new ArrayList<PermitProjectSiteInfoEntity>();
//			 list.add(null);
//			 list.add(new PermitProjectSiteInfoEntity());
//			 scenario.get(3).add(list); 
//			//Excpected Result
//			 scenario.get(3).add("success");
//			 
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("editFlashingFunction [ "+i+" ]");
//		     when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//           when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//            getFlashingLibraryService.editFlashingFunction((FlashingFavRequest)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//	
//	}
//	
//	@Test
//	public void testeditFlashingNotification() {
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
//			 System.out.println("editFlashingNotification [ "+i+" ]");
//	        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//	         getFlashingLibraryService.editFlashingNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }	
//	
//	}
//	
//	@Test
//	public void testaddFlashingNotification() {
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
//			 System.out.println("addFlashingNotification [ "+i+" ]");
//	        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//	         getFlashingLibraryService.addFlashingNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }	
//	}
//	
//	@Test
//	public void testflashingLibraryActived() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Flashing i Where i.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Flashing u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
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
//		 Flashing ac1 = new Flashing();
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
//		 Flashing acCombiner = new Flashing();
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
//		 scenario.get(6).add(new ArrayList<Flashing>());
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
//		 ArrayList<Flashing> listAcCom= new ArrayList<Flashing>();
//		 listAcCom.add(new Flashing());
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
//			  getFlashingLibraryService.flashingLibraryActived(123,(String)scenario.get(i).get(1));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testgetAllPermitOfFlashingDeleted() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Flashing i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//					+ "p.flashing = :p1 " )).thenReturn(mockedQuery2);
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
//		 scenario.get(1).add(new Flashing());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<PermitEntity>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(123);
//		 //Result of the query1
//		 Flashing bc = new Flashing();
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
//			  System.out.println("getAllPermitOfFlashingDeleted [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			   getFlashingLibraryService.getAllPermitOfFlashingDeleted((int)scenario.get(i).get(0));
//
//		 }
//	}
//	
//	@Test
//	public void testgetAllPermitOfFlashingDeleted1() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Flashing i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//					+ "p.flashing = :p1 " )).thenReturn(mockedQuery2);
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
//		 scenario.get(1).add(new Flashing());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(null);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(123);
//		 //Result of the query1
//		 Flashing bc = new Flashing();
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
//			  System.out.println("getAllPermitOfBatteryDeleted1 [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			  getFlashingLibraryService.getAllPermitOfFlashingDeleted1((int)scenario.get(i).get(0));
//
//		 }
//	}
//
//	
//	@Test
//	public void testdeleteFlashingLibrary() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Flashing i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//					+ "p.permitEntity = :p1 and p.flashing = :p2" )).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from FlashingFavLibraryEntity u WHERE u.flashing.id = :p2")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//		 
//		 GetFlashingLibraryService getFlashingLibraryService2 = Mockito.spy(getFlashingLibraryService);
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
//		 //Result of the query3
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(false);
//		 //Result of the function getAllPermitOfBatteryDeleted1
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
//		 //Result of the query3
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
//		 //Result of the query3
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add(false);
//		 //Result of the function getAllPermitOfBatteryDeleted1
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
//		 scenario.get(3).add(new Flashing());
//		 //Result of the query2
//		 scenario.get(3).add(null);
//		 //Result of the query3
//		 scenario.get(3).add(null);
//	     //Result excpected
//		 scenario.get(3).add(true);
//		 //Result of the function getAllPermitOfBatteryDeleted1
//		 scenario.get(3).add(listFun);
//		 //Result of the query2 list
//		 scenario.get(3).add(null);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add(423);
//		 scenario.get(4).add(null);
//		 //Result of the query1
//		 scenario.get(4).add(new Flashing());
//		 //Result of the query2
//		 PermitProjectSiteInfoEntity prAdd = new PermitProjectSiteInfoEntity();
//		 prAdd.setACCombinerInstalled("");
//		 scenario.get(4).add(prAdd);
//		 //Result of the query3
//		 scenario.get(4).add(null);
//	     //Result excpected
//		 scenario.get(4).add(true);
//		 //Result of the function getAllPermitOfBatteryDeleted1
//		 scenario.get(4).add(listFun);
//		 //Result of the query2 list
//		 ArrayList<PermitProjectSiteInfoEntity> listPrAdd = new ArrayList<PermitProjectSiteInfoEntity>();
//		 listPrAdd.add(prAdd);
//		 scenario.get(4).add(listPrAdd);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("deleteFlashingLibrary [ "+i+" ]");
//          Mockito.doReturn((List)scenario.get(i).get(6)).when(getFlashingLibraryService2).getAllPermitOfFlashingDeleted1(Mockito.anyInt());
//
//          when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//          when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//          when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(7));
//          when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(4));
//           getFlashingLibraryService2.deleteFlashingLibrary((int)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//		
//	}
//	
//	@Test
//	public void testgetUsersForFavList() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from FlashingFavLibraryEntity u WHERE u.flashing.id = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult ( " + "   u.id,  "
//				+ "   u.firstName,  " + "   u.lastName ) "
//				+ " from AuthentificationEntity u WHERE u.id NOT IN :p1 and u.deleted = :p2 and u.active = :p3 and u.id != :p4 ORDER BY u.firstName"))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult ( " + "   u.id,  "
//				+ "   u.firstName,  " + "   u.lastName ) "
//				+ " from AuthentificationEntity u WHERE u.deleted = :p2 and u.active = :p3 and u.id != :p4 ORDER BY u.firstName"))
//						.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<UsersEntityResult>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(new ArrayList<FlashingFavLibraryEntity>());
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<UsersEntityResult>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add("113");
//		// Result of the query1
//		scenario.get(2).add(new ArrayList<FlashingFavLibraryEntity>());
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add("113");
//		// Result of the query1
//		scenario.get(3).add(new ArrayList<FlashingFavLibraryEntity>());
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(new ArrayList<UsersEntityResult>());
//		// Result excpected
//		scenario.get(3).add(new ArrayList<UsersEntityResult>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add("113");
//		// Result of the query1
//		ArrayList<FlashingFavLibraryEntity> list = new ArrayList<FlashingFavLibraryEntity>();
//		list.add(null);
//		list.add(new FlashingFavLibraryEntity());
//		FlashingFavLibraryEntity ac = new FlashingFavLibraryEntity();
//		ac.setAuthentificationEntity(new AuthentificationEntity());
//		list.add(ac);
//		scenario.get(4).add(list);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result of the query3
//		scenario.get(4).add(new ArrayList<UsersEntityResult>());
//		// Result excpected
//		scenario.get(4).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(null);
//		scenario.get(5).add("113");
//		// Result of the query1
//		scenario.get(5).add(list);
//		// Result of the query2
//		scenario.get(5).add(new ArrayList<UsersEntityResult>());
//		// Result of the query3
//		scenario.get(5).add(new ArrayList<UsersEntityResult>());
//		// Result excpected
//		scenario.get(5).add(new ArrayList<UsersEntityResult>());
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getUsersForFavList [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			getFlashingLibraryService
//					.getUsersForFavList((Integer) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testeditUsersFavoriteList() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Flashing u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//        ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
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
//	     //Result excpected
//		 scenario.get(0).add("error");
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(null);
//		 String[] ListUsers = {"",null,"1233"};
//		 scenario.get(1).add(ListUsers);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add("error");
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add(null);
//		 String[] ListUsers2 = {"12","44","1233"};
//		 scenario.get(2).add(ListUsers2);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 scenario.get(2).add(null);
//		 //Result of the query2
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add("error");
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(ListUsers2);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 scenario.get(3).add(new Flashing());
//		 //Result of the query2
//		 scenario.get(3).add(null);
//	     //Result excpected
//		 scenario.get(3).add("error");
//	 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(ListUsers2);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 //Result of the query1
//		 scenario.get(4).add(new Flashing());
//		 //Result of the query2
//		 scenario.get(4).add(new AuthentificationEntity());
//	     //Result excpected
//		 scenario.get(4).add("Done");
//
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("editUsersFavoriteList [ "+i+" ]");
//            when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(8));
//            when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(9));
//            getFlashingLibraryService.editUsersFavoriteList((Integer)scenario.get(i).get(0),(String[])scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6),(String)scenario.get(i).get(7));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testcheckFlashingExistent() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Flashing u WHERE u.model = :p1 AND u.manufacturer = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Flashing u WHERE u.model = :p1 AND u.manufacturer != :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 GetFlashingLibraryService getFlashingLibraryService2 = Mockito.spy(getFlashingLibraryService);
//
//        ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//        
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result of the query2
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new ArrayList<FlashingFavRequest>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(new FlashingFavRequest());
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<FlashingFavRequest>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add(new FlashingFavRequest());
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 List<Flashing> list = new ArrayList<Flashing>();
//		 list.add(null);
//		 list.add(new Flashing());
//		 Flashing bt = new Flashing();
//		 bt.setId(223);
//		 bt.setManufacturer("abc");
//		 list.add(bt);
//		 scenario.get(2).add(list);
//		 //Result of the query2
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 List<FlashingFavRequest> listExpec = new ArrayList<FlashingFavRequest>();
//		 listExpec.add(new FlashingFavRequest());
//		 FlashingFavRequest btExp2 = new FlashingFavRequest();
//		 btExp2.setIsFav("false");
//		 listExpec.add(btExp2);
//		 FlashingFavRequest btExp = new FlashingFavRequest();
//		 btExp.setId(223);
//		 btExp.setManufacturer("abc");
//		 btExp.setIsFav("false");
//		 listExpec.add(btExp);
//		 scenario.get(2).add(listExpec);
//		 
//		 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(3).add(new FlashingFavRequest());
//			 scenario.get(3).add(null);
//			 //Result of the query1
//			 scenario.get(3).add(null);
//			 //Result of the query2
//			 scenario.get(3).add(list);
//		     //Result excpected
//			 scenario.get(3).add(listExpec);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("checkFlashingExistent [ "+i+" ]");
//			 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//
//			
//            Mockito.doReturn(new ArrayList<Integer>()).when(getFlashingLibraryService2).getFlashingFavorite(Mockito.any());
//            ArrayList<FlashingFavRequest> result = ((ArrayList<FlashingFavRequest>) getFlashingLibraryService2.checkFlashingExistent((FlashingFavRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1)));
//			 
//			
//		 }
//		 
//		 
//		 
//	
//	}
//	
//	@Test
//	public void testaddNewFlashing() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u.authentificationEntity from PermitEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
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
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result of the query2
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new Flashing());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(new FlashingFavRequest());
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 Flashing bt = new Flashing();
//		 Date d = new Date();
//		 SimpleDateFormat FormattedDATE = new SimpleDateFormat("MM/dd/yyyy");
//		 String updatedDate = FormattedDATE.format(d); 
//		 bt.setUpdated(updatedDate);
//		 scenario.get(1).add(bt);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add("");
//		 scenario.get(2).add(new FlashingFavRequest());
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 scenario.get(2).add(null);
//		 //Result of the query2
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add(bt);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add("123");
//		 scenario.get(3).add(new FlashingFavRequest());
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 scenario.get(3).add(null);
//		 //Result of the query2
//		 scenario.get(3).add(null);
//	     //Result excpected
//		 scenario.get(3).add(bt);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add("123");
//		 scenario.get(4).add(new FlashingFavRequest());
//		 scenario.get(4).add("");
//		 //Result of the query1
//		 scenario.get(4).add(null);
//		 //Result of the query2
//		 scenario.get(4).add(null);
//	     //Result excpected
//		 scenario.get(4).add(bt);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(5).add("123");
//		 scenario.get(5).add(new FlashingFavRequest());
//		 scenario.get(5).add("123");
//		 //Result of the query1
//		 scenario.get(5).add(null);
//		 //Result of the query2
//		 scenario.get(5).add(null);
//	     //Result excpected
//		 scenario.get(5).add(bt);
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(6).add("123");
//		 scenario.get(6).add(new FlashingFavRequest());
//		 scenario.get(6).add("123");
//		 //Result of the query1
//		 scenario.get(6).add(null);
//		 //Result of the query2
//		 AuthentificationEntity auth = new AuthentificationEntity();
//		 auth.setRoleEntity(new RoleEntity());
//		 scenario.get(6).add(auth);
//	     //Result excpected
//		 Flashing bt2 = new Flashing();
//		 bt2.setUpdated(updatedDate);
//		 bt2.setHasSuperUserEdit(false);
//		 Date addDate = new Date();
//		 bt2.setAddDate(addDate);
//		 bt2.setAuthentificationEntity(auth);
//		 scenario.get(6).add(bt2);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("addNewFlashing [ "+i+" ]");
//            when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//            when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//            getFlashingLibraryService.addNewFlashing((String)scenario.get(i).get(0),(FlashingFavRequest)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	
//	}
//	
//
//
//}
