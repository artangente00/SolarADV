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
//import com.PlayGroundAdv.Solar.Entity.Flashing;
//import com.PlayGroundAdv.Solar.Entity.FlashingFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.LeasePPAMeter;
//import com.PlayGroundAdv.Solar.Entity.LeasePPAMeterFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.model.FlashingCorrectionRequest;
//import com.PlayGroundAdv.Solar.model.FlashingFavRequest;
//import com.PlayGroundAdv.Solar.model.LeasePPAMeterCorrectionRequest;
//import com.PlayGroundAdv.Solar.model.LeasePPAMeterFavRequest;
//import com.PlayGroundAdv.Solar.model.SearchFlashingRequest;
//import com.PlayGroundAdv.Solar.model.SearchFlashingResult;
//import com.PlayGroundAdv.Solar.model.SearchLeasePPAMeterRequest;
//import com.PlayGroundAdv.Solar.model.SearchLeasePPAMeterResult;
//import com.PlayGroundAdv.Solar.model.UsersEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetBatteryLibraryService;
//import com.PlayGroundAdv.Solar.Services.GetFlashingLibraryService;
//import com.PlayGroundAdv.Solar.Services.GetLeasePPAMeterLibraryService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//
//public class TestGetLeasePPAMeterLibraryService {
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
//	GetLeasePPAMeterLibraryService getLeasePPAMeterLibraryService = new GetLeasePPAMeterLibraryService();
//	
//	
//    @Before
//	public void setupMock() {
//    	getLeasePPAMeterLibraryService = new GetLeasePPAMeterLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testsearchLeasePPAMeter() {
//		  TypedQuery mockedQuery1 = mock(TypedQuery.class);
//			when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//			Path battery = mock(Path.class);
//
//			when(criteriaBuilder.createQuery(SearchLeasePPAMeterResult.class)).thenReturn(criteriaQuery);
//			when(criteriaQuery.from(LeasePPAMeterFavLibraryEntity.class)).thenReturn(root);
//			when(criteriaQuery.from(LeasePPAMeter.class)).thenReturn(root);
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
//			 when(em.createQuery("SELECT u from LeasePPAMeterFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 ")).thenReturn(mockedQuery2);
//			 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
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
//			 scenario.get(0).add(new ArrayList<SearchLeasePPAMeterResult>());
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 SearchLeasePPAMeterRequest sc = new SearchLeasePPAMeterRequest();
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
//			 scenario.get(1).add(new ArrayList<SearchLeasePPAMeterResult>());
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
//			 scenario.get(2).add(new ArrayList<SearchLeasePPAMeterResult>());
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
//			 scenario.get(3).add(new ArrayList<SearchLeasePPAMeterResult>());
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
//			 scenario.get(4).add(new ArrayList<SearchLeasePPAMeterResult>());
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
//			 scenario.get(5).add(new ArrayList<SearchLeasePPAMeterResult>());
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(6) .add(sc);
//			 scenario.get(6).add("123");
//			 scenario.get(6).add("123");
//			 scenario.get(6).add("123");
//			 //Result of the query1
//			 List<SearchLeasePPAMeterResult> list = new ArrayList<SearchLeasePPAMeterResult>();
//			 list.add(null);
//			 list.add(new SearchLeasePPAMeterResult());
//			 SearchLeasePPAMeterResult scBat = new SearchLeasePPAMeterResult();
//			 scBat.setEroneousContent("");
//			 scBat.setId(458999);
//			 list.add(scBat);
//			 scenario.get(6).add(list);
//			 //Result of the query2
//			 scenario.get(6).add(null);
//		     //Result excpected
//			 ArrayList<SearchLeasePPAMeterResult> listExp = new ArrayList<SearchLeasePPAMeterResult>();
//			 SearchLeasePPAMeterResult exp1 = new SearchLeasePPAMeterResult();
//			 exp1.setIsFav(false);
//			 listExp.add(exp1);
//			 SearchLeasePPAMeterResult exp2 = new SearchLeasePPAMeterResult();
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
//			 List<LeasePPAMeterFavLibraryEntity> list2 = new ArrayList<LeasePPAMeterFavLibraryEntity>();
//			 list2.add(null);
//			 list2.add(new LeasePPAMeterFavLibraryEntity());
//			 LeasePPAMeterFavLibraryEntity bt = new LeasePPAMeterFavLibraryEntity();
//			 bt.setId(12358);
//			 LeasePPAMeter batt = new LeasePPAMeter();
//			  batt.setManufacturer("bbb_ccc");
//			 bt.setLeasePPAMeter(batt);
//			 list2.add(bt);
//			 scenario.get(7).add(list2);
//		     //Result excpected
//			 ArrayList<SearchLeasePPAMeterResult> listExp2 = new ArrayList<SearchLeasePPAMeterResult>();
//			 SearchLeasePPAMeterResult scBt2 = new SearchLeasePPAMeterResult();
//			 scBt2.setIsFav(true);
//			 listExp2.add(scBt2);
//			 scenario.get(7).add(listExp2);
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 SearchLeasePPAMeterRequest sc2 = new SearchLeasePPAMeterRequest();
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
//			 List<LeasePPAMeterFavLibraryEntity> list3 = new ArrayList<LeasePPAMeterFavLibraryEntity>();
//			 list3.add(null);
//			 list3.add(new LeasePPAMeterFavLibraryEntity());
//			 LeasePPAMeterFavLibraryEntity bt2 = new LeasePPAMeterFavLibraryEntity();
//			 bt2.setId(12358);
//			 LeasePPAMeter batt2 = new LeasePPAMeter();
//			  batt2.setManufacturer("bbb_ccc");
//			  batt2.setId(12358);
//			 bt2.setLeasePPAMeter(batt2);
//			 list3.add(bt2);
//			 scenario.get(9).add(list3);
//		     //Result excpected
//			 ArrayList<SearchLeasePPAMeterResult> listExp3 = new ArrayList<SearchLeasePPAMeterResult>();
//			 SearchLeasePPAMeterResult scBt3 = new SearchLeasePPAMeterResult();
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
//			 scenario.get(10).add(new SearchLeasePPAMeterRequest());
//			 scenario.get(10).add(null);
//			 scenario.get(10).add(null);
//			 scenario.get(10).add(null);
//			 //Result of the query1
//			 scenario.get(10).add(null);
//			 //Result of the query2
//			 scenario.get(10).add(null);
//		     //Result excpected
//			 scenario.get(10).add(new ArrayList<SearchLeasePPAMeterResult>());
//			 
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(11) .add(new SearchLeasePPAMeterRequest());
//			 scenario.get(11).add("");
//			 scenario.get(11).add("");
//			 scenario.get(11).add("");
//			 //Result of the query1
//			 scenario.get(11).add(null);
//			 //Result of the query2
//			 scenario.get(11).add(null);
//		     //Result excpected
//			 scenario.get(11).add(new ArrayList<SearchLeasePPAMeterResult>());
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(12) .add(new SearchLeasePPAMeterRequest());
//			 scenario.get(12).add("123");
//			 scenario.get(12).add("123");
//			 scenario.get(12).add("123");
//			 //Result of the query1
//			 scenario.get(12).add(null);
//			 //Result of the query2
//			 scenario.get(12).add(null);
//		     //Result excpected
//			 scenario.get(12).add(new ArrayList<SearchLeasePPAMeterResult>());
//			 
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(13) .add(new SearchLeasePPAMeterRequest());
//			 scenario.get(13).add("123");
//			 scenario.get(13).add("123");
//			 scenario.get(13).add("123");
//			 //Result of the query1
//			 scenario.get(13).add(list);
//			 //Result of the query2
//			 scenario.get(13).add(list2);
//		     //Result excpected
//			 ArrayList<SearchLeasePPAMeterResult> listExp4 = new ArrayList<SearchLeasePPAMeterResult>();
//			 SearchLeasePPAMeterResult sc5 = new SearchLeasePPAMeterResult();
//			 sc5.setIsFav(true);
//			 listExp4.add(sc5);
//			 SearchLeasePPAMeterResult sc6 = new SearchLeasePPAMeterResult();
//			 sc6.setIsFav(false);
//			 sc6.setEroneousContent("");
//			 sc6.setId(458999);
//			 listExp4.add(sc6);
//			 scenario.get(13).add(listExp4);
//			 
//		        for(int i=0;i<scenario.size();i++) {
//					 System.out.println("searchLeasePPAMeter [ "+i+" ]");
//					 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//					 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//
//		            ArrayList<SearchLeasePPAMeterResult> result = ((ArrayList<SearchLeasePPAMeterResult>) getLeasePPAMeterLibraryService.searchLeasePPAMeter((SearchLeasePPAMeterRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3)));
//					 
//					
//				 }
//	}
//	
//	@Test
//	public void testsendCorrectionLeasePPAMeterRequest() {
//
//
//
//		Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from LeasePPAMeter u Where u.id = :p1 " )).thenReturn(mockedQuery1);
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
//			 LeasePPAMeterCorrectionRequest acComSLC = new LeasePPAMeterCorrectionRequest();
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
//			 LeasePPAMeterCorrectionRequest acComSLC1= new LeasePPAMeterCorrectionRequest();
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
//			 LeasePPAMeterCorrectionRequest acComSLC2= new LeasePPAMeterCorrectionRequest();
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
//			 LeasePPAMeterCorrectionRequest acComSLC4= new LeasePPAMeterCorrectionRequest();
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
//			 LeasePPAMeter lib = new LeasePPAMeter();
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
//	             getLeasePPAMeterLibraryService.sendCorrectionLeasePPAMeterRequest( (LeasePPAMeterCorrectionRequest) scenario.get(i).get(0));
//
//			 }
//	}
//	
//	@Test
//	public void testgetNumberSearchLeasePPAMeter() {
//
//
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(LeasePPAMeterFavLibraryEntity.class)).thenReturn(root);
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
//		 SearchLeasePPAMeterRequest sc = new SearchLeasePPAMeterRequest();
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
//		 scenario.get(3).add(new SearchLeasePPAMeterRequest());
//		 scenario.get(3).add(null);
//		 //Result of query1
//		 scenario.get(3).add(8L); 
//		//Excpected Result
//		 scenario.get(3).add(8L);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getNumberSearchLeasePPAMeter [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//	        getLeasePPAMeterLibraryService.getNumberSearchLeasePPAMeter((SearchLeasePPAMeterRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		 }
//   
//	
//	
//	}
//
//	@Test
//	public void testgetLeasePPAMeterLibrary() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from LeasePPAMeter u order by u.manufacturer")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//		 GetLeasePPAMeterLibraryService getLeasePPAMeterLibraryService2 = Mockito.spy(getLeasePPAMeterLibraryService);
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
//				 scenario.get(0).add(new ArrayList<LeasePPAMeterFavRequest>());
//				 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(1).add("");
//				 scenario.get(1).add("");
//				 scenario.get(1).add("");
//				 //Result of query1
//				 scenario.get(1).add(null); 
//				//Excpected Result
//				 scenario.get(1).add(new ArrayList<LeasePPAMeterFavRequest>());
//				 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 //Result of query1
//				 scenario.get(2).add(null); 
//				//Excpected Result
//				 scenario.get(2).add(new ArrayList<LeasePPAMeterFavRequest>());
//				 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(3).add("123");
//				 scenario.get(3).add("123");
//				 scenario.get(3).add("123");
//				 //Result of query1
//				 List<LeasePPAMeter> list = new ArrayList<LeasePPAMeter>();
//				 list.add(null);
//				 list.add(new LeasePPAMeter());
//				 scenario.get(3).add(list); 
//				//Excpected Result
//				 List<LeasePPAMeterFavRequest> listExp = new ArrayList<LeasePPAMeterFavRequest>();
//				 LeasePPAMeterFavRequest fl= new LeasePPAMeterFavRequest();
//				 fl.setIsFav("false");
//				 listExp.add(new LeasePPAMeterFavRequest());
//				 listExp.add(fl);
//				 scenario.get(3).add(listExp);
//				 
//				 for(int i=0;i<scenario.size();i++) {
//					 System.out.println("getLeasePPAMeterLibrary [ "+i+" ]");
//		            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//		             ArrayList<LeasePPAMeterFavRequest> result = ((ArrayList<LeasePPAMeterFavRequest>) getLeasePPAMeterLibraryService2.getLeasePPAMeterLibrary((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2)));
//			         Mockito.doReturn(new ArrayList<Integer>()).when(getLeasePPAMeterLibraryService2).getLeasePPAMeterFavorite(Mockito.any());
//
//
//				 }
//	
//	}
//	
//	@Test
//	public void testgetLeasePPAMeterFavorite() {
//
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from LeasePPAMeterFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 ")).thenReturn(mockedQuery1);
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
//		 scenario.get(3).add(new ArrayList<LeasePPAMeterFavLibraryEntity>());
//	     //Result excpected
//		 scenario.get(3).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add("123");
//		 //Result of the query1
//		 ArrayList<LeasePPAMeterFavLibraryEntity> list = new ArrayList<LeasePPAMeterFavLibraryEntity>();
//		 list.add(null);
//		 list.add(new LeasePPAMeterFavLibraryEntity());
//		 scenario.get(4).add(list);
//	     //Result excpected
//		 scenario.get(4).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(5).add("123");
//		 //Result of the query1
//		 ArrayList<LeasePPAMeterFavLibraryEntity> list2= new ArrayList<LeasePPAMeterFavLibraryEntity>();
//		 LeasePPAMeterFavLibraryEntity dc = new LeasePPAMeterFavLibraryEntity();
//		 dc.setLeasePPAMeter(new LeasePPAMeter());
//		 list2.add(dc);
//		 LeasePPAMeterFavLibraryEntity dc2 = new LeasePPAMeterFavLibraryEntity();
//		 LeasePPAMeter dcEnt = new LeasePPAMeter();
//		 dcEnt.setId(123);
//		 dc2.setLeasePPAMeter(dcEnt);
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
//			 System.out.println("getLeasePPAMeterFavorite [ "+i+" ]");
//          when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//          getLeasePPAMeterLibraryService.getLeasePPAMeterFavorite((String)scenario.get(i).get(0));
//
//		 }
//	
//	
//	
//	}
//	
//	@Test
//	public void testaddLeasePPAMeterFavorite() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from LeasePPAMeter u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
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
//		 scenario.get(4).add(new LeasePPAMeter());
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
//		 scenario.get(5).add(new LeasePPAMeter());
//	     //Result excpected
//		 scenario.get(5).add("Done");
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("addLeasePPAMeterFavorite [ "+i+" ]");
//          when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//          when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//          getLeasePPAMeterLibraryService.addLeasePPAMeterFavorite((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testremoveLeasePPAMeterFavorite() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from LeasePPAMeterFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 and u.leasePPAMeter.id = :p2")).thenReturn(mockedQuery2);
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
//		 scenario.get(4).add(new LeasePPAMeterFavLibraryEntity());
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
//		 LeasePPAMeterFavLibraryEntity bt = new LeasePPAMeterFavLibraryEntity();
//		 bt.setLeasePPAMeter(new LeasePPAMeter());		 
//		 scenario.get(5).add(bt);
//	     //Result excpected
//		 scenario.get(5).add("Done");
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("removeLeasePPAMeterFavorite [ "+i+" ]");
//         when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//         when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//         getLeasePPAMeterLibraryService.removeLeasePPAMeterFavorite((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	
//	}
//	
//	
//	@Test
//	public void testgetContractorLeasePPAMeterFav() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from LeasePPAMeter u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 GetLeasePPAMeterLibraryService getLeasePPAMeterLibraryService2 = Mockito.spy(getLeasePPAMeterLibraryService);
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
//			 ArrayList<LeasePPAMeter> listExp = new ArrayList<LeasePPAMeter>();
//			 listExp.add(null);
//			 listExp.add(null);
//			 listExp.add(null);
//			 scenario.get(1).add(listExp);
//			 //Result of the function getBatteryFavorite
//			 scenario.get(1).add(batteryFavID);
//			 
//			  for(int i=0;i<scenario.size();i++) {
//					 System.out.println("getContractorLeasePPAMeterFav [ "+i+" ]");
//					 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			         Mockito.doReturn(scenario.get(i).get(3)).when(getLeasePPAMeterLibraryService2).getLeasePPAMeterFavorite(Mockito.any());
//
//		             ArrayList<LeasePPAMeter> result = ((ArrayList<LeasePPAMeter>) getLeasePPAMeterLibraryService2.getContractorLeasePPAMeterFav((String) scenario.get(i).get(0)));
//					 
//					
//				 }
//	}
//	
//	@Test
//	public void testgetSuperUserLeasePPAMeterFav() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u " + " from PermitEntity u " + " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from LeasePPAMeter u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 GetLeasePPAMeterLibraryService getLeasePPAMeterLibraryService2 = Mockito.spy(getLeasePPAMeterLibraryService);
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
//			 scenario.get(0).add(new ArrayList<LeasePPAMeter>());
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
//			 scenario.get(1).add(new ArrayList<LeasePPAMeter>());
//			 
//	         scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(2).add("123");
//			 //Result of the query1
//			 scenario.get(2).add(null);
//			 //Result of the query2
//			 scenario.get(2).add(null);
//		     //Result excpected
//			 scenario.get(2).add(new ArrayList<LeasePPAMeter>());
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
//			 ArrayList<LeasePPAMeter> listExp = new ArrayList<LeasePPAMeter>();
//			 listExp.add(null);
//			 listExp.add(null);
//			 listExp.add(null);
//			 scenario.get(3).add(listExp);
//			 
//			 
//
//			 
//	       for(int i=0;i<scenario.size();i++) {
//				 System.out.println("getSuperUserLeasePPAMeterFav [ "+i+" ]");
//				 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//				 when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//		         Mockito.doReturn(batteryFavID).when(getLeasePPAMeterLibraryService2).getLeasePPAMeterFavorite(Mockito.any());
//
//	             ArrayList<LeasePPAMeter> result = ((ArrayList<LeasePPAMeter>) getLeasePPAMeterLibraryService2.getSuperUserLeasePPAMeterFav((String) scenario.get(i).get(0)));
//				 
//				
//			 }
//	}
//	
//	@Test
//	public void testgetTestLeasePPAMeterFav() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u "
//					+ " from PermitEntity u "
//					+ " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from LeasePPAMeter u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 GetLeasePPAMeterLibraryService getLeasePPAMeterLibraryService2 = Mockito.spy(getLeasePPAMeterLibraryService);
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
//			 LeasePPAMeter bt = new LeasePPAMeter();
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
//				 System.out.println("getTestLeasePPAMeterFav [ "+i+" ]");
//				 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//				 when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//		         Mockito.doReturn(scenario.get(i).get(5)).when(getLeasePPAMeterLibraryService2).getLeasePPAMeterFavorite(Mockito.any());
//                getLeasePPAMeterLibraryService2.getTestLeasePPAMeterFav((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//			 }
//	
//	}
//	
//	@Test
//	public void testaddLeasePPAMeter() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u.model , u.manufacturer from LeasePPAMeter u where u.manufacturer=:p1 and u.model =:p2 ")).thenReturn(mockedQuery1);
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
//			 scenario.get(1).add(new LeasePPAMeterFavRequest());
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
//			 scenario.get(2).add(new LeasePPAMeterFavRequest());
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 //Result of query1
//			 scenario.get(2).add(new ArrayList<LeasePPAMeter>()); 
//			 //Result of query2
//			 scenario.get(2).add(null); 
//			//Excpected Result
//			 scenario.get(2).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(3).add("");
//			 scenario.get(3).add(new LeasePPAMeterFavRequest());
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 //Result of query1
//			 scenario.get(3).add(new ArrayList<LeasePPAMeter>()); 
//			 //Result of query2
//			 scenario.get(3).add(null); 
//			//Excpected Result
//			 scenario.get(3).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(4).add("123");
//			 scenario.get(4).add(new LeasePPAMeterFavRequest());
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 //Result of query1
//			 scenario.get(4).add(new ArrayList<LeasePPAMeter>()); 
//			 //Result of query2
//			 scenario.get(4).add(null); 
//			//Excpected Result
//			 scenario.get(4).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(5).add("123");
//			 scenario.get(5).add(new LeasePPAMeterFavRequest());
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 //Result of query1
//			 scenario.get(5).add(new ArrayList<LeasePPAMeter>()); 
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
//			 scenario.get(6).add(new LeasePPAMeterFavRequest());
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 //Result of query1
//			 ArrayList<LeasePPAMeter> list = new ArrayList<LeasePPAMeter>();
//			 list.add(null);
//			 list.add(new LeasePPAMeter());
//			 scenario.get(6).add(list); 
//			 //Result of query2
//			 scenario.get(6).add(auth); 
//			//Excpected Result
//			 scenario.get(6).add("LeasePPAMeter already exists in data sources");
//			 
//			 for(int i=0; i<scenario.size();i++) {
//				 System.out.println("addLeasePPAMeter [ "+i+" ]");
//	            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(5));
//	            when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(6));
//	             getLeasePPAMeterLibraryService.addLeasePPAMeter((String)scenario.get(i).get(0),(LeasePPAMeterFavRequest)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4));
//
//			 }
//	
//	
//	}
//	
//	@Test
//	public void testeditLeasePPAMeter() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from LeasePPAMeter u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 GetLeasePPAMeterLibraryService getLeasePPAMeterLibraryService2 = Mockito.spy(getLeasePPAMeterLibraryService);
//
//		 
//   List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		 scenario.get(1).add(new LeasePPAMeterFavRequest());
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
//		 scenario.get(2).add(new LeasePPAMeterFavRequest());
//		 scenario.get(2).add(null);
//		 //Result of query1
//		 scenario.get(2).add(new ArrayList<LeasePPAMeter>()); 
//		//Excpected Result
//		 scenario.get(2).add("success");
//		//Result of query1 singleResult
//		 scenario.get(2).add(null);  
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(new LeasePPAMeterFavRequest());
//		 scenario.get(3).add(null);
//		 //Result of query1
//		 ArrayList<LeasePPAMeter> list = new ArrayList<LeasePPAMeter>();
//		 list.add(null);
//		 list.add(new LeasePPAMeter());
//		 scenario.get(3).add(list); 
//		//Excpected Result
//		 scenario.get(3).add("success");
//		//Result of query1 singleResult
//		 scenario.get(3).add(null); 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add(new LeasePPAMeterFavRequest());
//		 scenario.get(4).add(null);
//		 //Result of query1
//		 scenario.get(4).add(list); 
//		//Excpected Result
//		 scenario.get(4).add("success");
//		//Result of query1 singleResult
//		 scenario.get(4).add(new LeasePPAMeter()); 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(5).add(new LeasePPAMeterFavRequest());
//		 scenario.get(5).add(null);
//		 //Result of query1
//		 scenario.get(5).add(list); 
//		//Excpected Result
//		 scenario.get(5).add("exist");
//		//Result of query1 singleResult
//		 LeasePPAMeter bc = new LeasePPAMeter();
//		 bc.setId(12589);
//		 scenario.get(5).add(bc); 
//		 
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("editLeasePPAMeter [ "+i+" ]");
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//             Mockito.doReturn("success").when(getLeasePPAMeterLibraryService2).editLeasePPAMeterFunction(Mockito.any(),Mockito.any());
//              getLeasePPAMeterLibraryService2.editLeasePPAMeter((LeasePPAMeterFavRequest)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//	
//	
//	}
//	
//	@Test
//	public void testeditLeasePPAMeterFunction() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from LeasePPAMeter u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM PermitProjectSiteInfoEntity u WHERE u.leasePPAMeter= :p1 ")).thenReturn(mockedQuery2);
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
//			 scenario.get(1).add(new LeasePPAMeterFavRequest());
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
//			 scenario.get(2).add(new LeasePPAMeterFavRequest());
//			 scenario.get(2).add(null);
//			 //Result of query1
//			 scenario.get(2).add(new LeasePPAMeter()); 
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
//			 scenario.get(3).add(new LeasePPAMeterFavRequest());
//			 scenario.get(3).add(null);
//			 //Result of query1
//			 scenario.get(3).add(new LeasePPAMeter()); 
//			 //Result of query2
//			 List<PermitProjectSiteInfoEntity> list = new ArrayList<PermitProjectSiteInfoEntity>();
//			 list.add(null);
//			 list.add(new PermitProjectSiteInfoEntity());
//			 scenario.get(3).add(list); 
//			//Excpected Result
//			 scenario.get(3).add("success");
//			 
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("editLeasePPAMeterFunction [ "+i+" ]");
//		     when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//          when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//           getLeasePPAMeterLibraryService.editLeasePPAMeterFunction((LeasePPAMeterFavRequest)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//	
//	
//	}
//	
//	
//	@Test
//	public void testeditLeasePPAMeterNotification() {
//
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
//			 System.out.println("editLeasePPAMeterNotification [ "+i+" ]");
//	        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//	        getLeasePPAMeterLibraryService.editLeasePPAMeterNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }	
//	
//	
//	}
//	
//	
//	@Test
//	public void testaddLeasePPAMeterNotification() {
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
//			 System.out.println("addFlashingNotification [ "+i+" ]");
//	        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//	        getLeasePPAMeterLibraryService.addLeasePPAMeterNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }	
//	
//	}
//	
//	
//	@Test
//	public void testleasePPAMeterLibraryActived() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from LeasePPAMeter i Where i.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from LeasePPAMeter u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
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
//		 LeasePPAMeter ac1 = new LeasePPAMeter();
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
//		 LeasePPAMeter acCombiner = new LeasePPAMeter();
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
//		 scenario.get(6).add(new ArrayList<LeasePPAMeter>());
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
//		 ArrayList<LeasePPAMeter> listAcCom= new ArrayList<LeasePPAMeter>();
//		 listAcCom.add(new LeasePPAMeter());
//		 scenario.get(7).add(listAcCom);
//		 //The result excpected
//		 scenario.get(7).add("exist");
//		 
//		
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("leasePPAMeterLibraryActived [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			 getLeasePPAMeterLibraryService.leasePPAMeterLibraryActived(123,(String)scenario.get(i).get(1));
//
//		 }
//	
//	
//	}
//	
//	@Test
//	public void testgetAllPermitOfLeasePPAMeterDeleted() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from LeasePPAMeter i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//					+ "p.leasePPAMeter = :p1 " )).thenReturn(mockedQuery2);
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
//		 scenario.get(1).add(new LeasePPAMeter());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<PermitEntity>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(123);
//		 //Result of the query1
//		 LeasePPAMeter bc = new LeasePPAMeter();
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
//			  System.out.println("getAllPermitOfLeasePPAMeterDeleted [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 getLeasePPAMeterLibraryService.getAllPermitOfLeasePPAMeterDeleted((int)scenario.get(i).get(0));
//
//		 }
//	
//	
//	}
//	
//	@Test
//	public void testgetAllPermitOfLeasePPAMeterDeleted1() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from LeasePPAMeter i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//					+ "p.leasePPAMeter = :p1 " )).thenReturn(mockedQuery2);
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
//		 scenario.get(1).add(new LeasePPAMeter());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(null);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(123);
//		 //Result of the query1
//		 LeasePPAMeter bc = new LeasePPAMeter();
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
//			  System.out.println("getAllPermitOfLeasePPAMeterDeleted1 [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 getLeasePPAMeterLibraryService.getAllPermitOfLeasePPAMeterDeleted1((int)scenario.get(i).get(0));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testdeleteLeasePPAMeterLibrary() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from LeasePPAMeter i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//					+ "p.permitEntity = :p1 and p.leasePPAMeter = :p2" )).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from LeasePPAMeterFavLibraryEntity u WHERE u.leasePPAMeter.id = :p2")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//		 
//		 GetLeasePPAMeterLibraryService getLeasePPAMeterLibraryService2 = Mockito.spy(getLeasePPAMeterLibraryService);
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
//		 scenario.get(3).add(new LeasePPAMeter());
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
//		 scenario.get(4).add(new LeasePPAMeter());
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
//			 System.out.println("deleteLeasePPAMeterLibrary [ "+i+" ]");
//         Mockito.doReturn((List)scenario.get(i).get(6)).when(getLeasePPAMeterLibraryService2).getAllPermitOfLeasePPAMeterDeleted1(Mockito.anyInt());
//
//         when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//         when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//         when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(7));
//         when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(4));
//          getLeasePPAMeterLibraryService2.deleteLeasePPAMeterLibrary((int)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//		
//	
//	}
//	
//	@Test
//	public void testgetUsersForFavList() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from LeasePPAMeterFavLibraryEntity u WHERE u.leasePPAMeter.id = :p1"))
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
//		scenario.get(1).add(new ArrayList<LeasePPAMeterFavLibraryEntity>());
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
//		scenario.get(2).add(new ArrayList<LeasePPAMeterFavLibraryEntity>());
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
//		scenario.get(3).add(new ArrayList<LeasePPAMeterFavLibraryEntity>());
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
//		ArrayList<LeasePPAMeterFavLibraryEntity> list = new ArrayList<LeasePPAMeterFavLibraryEntity>();
//		list.add(null);
//		list.add(new LeasePPAMeterFavLibraryEntity());
//		LeasePPAMeterFavLibraryEntity ac = new LeasePPAMeterFavLibraryEntity();
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
//			 getLeasePPAMeterLibraryService
//					.getUsersForFavList((Integer) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//	
//	@Test
//	public void testeditUsersFavoriteList() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from LeasePPAMeter u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//       ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		 scenario.get(3).add(new LeasePPAMeter());
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
//		 scenario.get(4).add(new LeasePPAMeter());
//		 //Result of the query2
//		 scenario.get(4).add(new AuthentificationEntity());
//	     //Result excpected
//		 scenario.get(4).add("Done");
//
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("editUsersFavoriteList [ "+i+" ]");
//           when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(8));
//           when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(9));
//           getLeasePPAMeterLibraryService.editUsersFavoriteList((Integer)scenario.get(i).get(0),(String[])scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6),(String)scenario.get(i).get(7));
//
//		 }
//	
//	
//	}
//	
//	@Test
//	public void testcheckLeasePPAMeterExistent() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from LeasePPAMeter u WHERE u.model = :p1 AND u.manufacturer = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from LeasePPAMeter u WHERE u.model = :p1 AND u.manufacturer != :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 GetLeasePPAMeterLibraryService getLeasePPAMeterLibraryService2 = Mockito.spy(getLeasePPAMeterLibraryService);
//
//       ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		 scenario.get(0).add(new ArrayList<LeasePPAMeterFavRequest>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(new LeasePPAMeterFavRequest());
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<LeasePPAMeterFavRequest>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add(new LeasePPAMeterFavRequest());
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 List<LeasePPAMeter> list = new ArrayList<LeasePPAMeter>();
//		 list.add(null);
//		 list.add(new LeasePPAMeter());
//		 LeasePPAMeter bt = new LeasePPAMeter();
//		 bt.setId(223);
//		 bt.setManufacturer("abc");
//		 list.add(bt);
//		 scenario.get(2).add(list);
//		 //Result of the query2
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 List<LeasePPAMeterFavRequest> listExpec = new ArrayList<LeasePPAMeterFavRequest>();
//		 listExpec.add(new LeasePPAMeterFavRequest());
//		 LeasePPAMeterFavRequest btExp2 = new LeasePPAMeterFavRequest();
//		 btExp2.setIsFav("false");
//		 listExpec.add(btExp2);
//		 LeasePPAMeterFavRequest btExp = new LeasePPAMeterFavRequest();
//		 btExp.setId(223);
//		 btExp.setManufacturer("abc");
//		 btExp.setIsFav("false");
//		 listExpec.add(btExp);
//		 scenario.get(2).add(listExpec);
//		 
//		 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(3).add(new LeasePPAMeterFavRequest());
//			 scenario.get(3).add(null);
//			 //Result of the query1
//			 scenario.get(3).add(null);
//			 //Result of the query2
//			 scenario.get(3).add(list);
//		     //Result excpected
//			 scenario.get(3).add(listExpec);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("checkLeasePPAMeterExistent [ "+i+" ]");
//			 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//
//			
//           Mockito.doReturn(new ArrayList<Integer>()).when(getLeasePPAMeterLibraryService2).getLeasePPAMeterFavorite(Mockito.any());
//           ArrayList<LeasePPAMeterFavRequest> result = ((ArrayList<LeasePPAMeterFavRequest>) getLeasePPAMeterLibraryService2.checkLeasePPAMeterExistent((LeasePPAMeterFavRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1)));
//			
//		 }
//	
//	}
//	
//	@Test
//	public void testaddNewLeasePPAMeter() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u.authentificationEntity from PermitEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
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
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result of the query2
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new LeasePPAMeter());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(new LeasePPAMeterFavRequest());
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 LeasePPAMeter bt = new LeasePPAMeter();
//		 Date d = new Date();
//		 SimpleDateFormat FormattedDATE = new SimpleDateFormat("MM/dd/yyyy");
//		 String updatedDate = FormattedDATE.format(d); 
//		 bt.setUpdated(updatedDate);
//		 scenario.get(1).add(bt);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add("");
//		 scenario.get(2).add(new LeasePPAMeterFavRequest());
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
//		 scenario.get(3).add(new LeasePPAMeterFavRequest());
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
//		 scenario.get(4).add(new LeasePPAMeterFavRequest());
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
//		 scenario.get(5).add(new LeasePPAMeterFavRequest());
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
//		 scenario.get(6).add(new LeasePPAMeterFavRequest());
//		 scenario.get(6).add("123");
//		 //Result of the query1
//		 scenario.get(6).add(null);
//		 //Result of the query2
//		 AuthentificationEntity auth = new AuthentificationEntity();
//		 auth.setRoleEntity(new RoleEntity());
//		 scenario.get(6).add(auth);
//	     //Result excpected
//		 LeasePPAMeter bt2 = new LeasePPAMeter();
//		 bt2.setUpdated(updatedDate);
//		 bt2.setHasSuperUserEdit(false);
//		 Date addDate = new Date();
//		 bt2.setAddDate(addDate);
//		 bt2.setAuthentificationEntity(auth);
//		 scenario.get(6).add(bt2);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("addNewLeasePPAMeter [ "+i+" ]");
//           when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//           when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//           getLeasePPAMeterLibraryService.addNewLeasePPAMeter((String)scenario.get(i).get(0),(LeasePPAMeterFavRequest)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	
//	
//	}
//	
//	
//}
