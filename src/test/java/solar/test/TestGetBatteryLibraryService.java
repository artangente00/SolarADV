//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.EnumMap;
//import java.util.List;
//import java.util.Set;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.AbstractQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.JoinType;
//import javax.persistence.criteria.Path;
//import javax.persistence.criteria.Root;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Matchers;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//import org.mockito.stubbing.Answer;
//
//import com.PlayGroundAdv.Solar.Entity.ACDisconnectFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.BackFeedSolarOCPD;
//import com.PlayGroundAdv.Solar.Entity.Battery;
//import com.PlayGroundAdv.Solar.Entity.BatteryFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.DCCombinerDisconnectEntity;
//import com.PlayGroundAdv.Solar.Entity.DcCombinerorDiscFavoriteEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitAdditionalInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.model.DcCombinerDisconnectCorrectionModel;
//import com.PlayGroundAdv.Solar.model.DcCombinerorDisconnectModel;
//import com.PlayGroundAdv.Solar.model.SearchBackFeedSolarOCPDRequest;
//import com.PlayGroundAdv.Solar.model.SearchBackFeedSolarOCPDResult;
//import com.PlayGroundAdv.Solar.model.SearchBatteryRequest;
//import com.PlayGroundAdv.Solar.model.SearchBatteryResult;
//import com.PlayGroundAdv.Solar.model.UsersEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetAlldcCombinerOrDiService;
//import com.PlayGroundAdv.Solar.Services.GetBackFeedSolarOCPDLibraryService;
//import com.PlayGroundAdv.Solar.Services.GetBatteryLibraryService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.itextpdf.text.xml.XMLUtil;
//import com.PlayGroundAdv.Solar.model.BatteryCorrectionRequest;
//import com.PlayGroundAdv.Solar.model.BatteryFavRequest;
//
//public class TestGetBatteryLibraryService {
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
//	GetBatteryLibraryService getBatteryLibraryService = new GetBatteryLibraryService();
//	
//	
//    @Before
//	public void setupMock() {
//    	getBatteryLibraryService = new GetBatteryLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//	
//
//	@Test
//	public void testsearchBattery() {
//	 // Root<BatteryFavLibraryEntity> r = Mockito.mock(Root.class);
//	  TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		Path battery = mock(Path.class);
//
//		when(criteriaBuilder.createQuery(SearchBatteryResult.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(BatteryFavLibraryEntity.class)).thenReturn(root);
//		when(criteriaQuery.from(Battery.class)).thenReturn(root);
//		when(root.get(Mockito.anyString())).thenReturn(battery);
//		//when(r.get("authentificationEntity")).thenReturn(authentificationEntity);
//        when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//        when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//        when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//        when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//        
//		 
//		 
//	   	 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from BatteryFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//	 
//        
//	   	 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0) .add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result of the query2
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new ArrayList<SearchBatteryResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 SearchBatteryRequest sc = new SearchBatteryRequest();
//		 sc.setIsFav(true);
//		 scenario.get(1) .add(sc);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<SearchBatteryResult>());
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2) .add(sc);
//		 scenario.get(2).add("abc");
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 scenario.get(2).add(null);
//		 //Result of the query2
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add(new ArrayList<SearchBatteryResult>());
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3) .add(sc);
//		 scenario.get(3).add("123");
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 scenario.get(3).add(null);
//		 //Result of the query2
//		 scenario.get(3).add(null);
//	     //Result excpected
//		 scenario.get(3).add(new ArrayList<SearchBatteryResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4) .add(sc);
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("");
//		 scenario.get(4).add("");
//		 //Result of the query1
//		 scenario.get(4).add(null);
//		 //Result of the query2
//		 scenario.get(4).add(null);
//	     //Result excpected
//		 scenario.get(4).add(new ArrayList<SearchBatteryResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(5) .add(sc);
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 //Result of the query1
//		 scenario.get(5).add(null);
//		 //Result of the query2
//		 scenario.get(5).add(null);
//	     //Result excpected
//		 scenario.get(5).add(new ArrayList<SearchBatteryResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(6) .add(sc);
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 //Result of the query1
//		 List<SearchBatteryResult> list = new ArrayList<SearchBatteryResult>();
//		 list.add(null);
//		 list.add(new SearchBatteryResult());
//		 SearchBatteryResult scBat = new SearchBatteryResult();
//		 scBat.setEroneousContent("");
//		 scBat.setId(458999);
//		 list.add(scBat);
//		 scenario.get(6).add(list);
//		 //Result of the query2
//		 scenario.get(6).add(null);
//	     //Result excpected
//		 ArrayList<SearchBatteryResult> listExp = new ArrayList<SearchBatteryResult>();
//		 SearchBatteryResult exp1 = new SearchBatteryResult();
//		 exp1.setIsFav(false);
//		 listExp.add(exp1);
//		 SearchBatteryResult exp2 = new SearchBatteryResult();
//		 exp2.setEroneousContent("");
//		 exp2.setId(458999);
//		 exp2.setIsFav(false);
//		 listExp.add(exp2);
//		 scenario.get(6).add(listExp);
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(7) .add(sc);
//		 scenario.get(7).add("123");
//		 scenario.get(7).add("123");
//		 scenario.get(7).add("123");
//		 //Result of the query1
//		 scenario.get(7).add(list);
//		 //Result of the query2
//		 List<BatteryFavLibraryEntity> list2 = new ArrayList<BatteryFavLibraryEntity>();
//		 list2.add(null);
//		 list2.add(new BatteryFavLibraryEntity());
//		 BatteryFavLibraryEntity bt = new BatteryFavLibraryEntity();
//		 bt.setId(12358);
//		  Battery batt = new Battery();
//		  batt.setManufacturer("bbb_ccc");
//		 bt.setBattery(batt);
//		 list2.add(bt);
//		 scenario.get(7).add(list2);
//	     //Result excpected
//		 ArrayList<SearchBatteryResult> listExp2 = new ArrayList<SearchBatteryResult>();
//		 SearchBatteryResult scBt2 = new SearchBatteryResult();
//		 scBt2.setIsFav(true);
//		 listExp2.add(scBt2);
//		 scenario.get(7).add(listExp2);
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 SearchBatteryRequest sc2 = new SearchBatteryRequest();
//		 sc2.setIsFav(true);
//		 sc2.setManufacturer("aaa bbb_ccc eee_fff");
//		 scenario.get(8) .add(sc2);
//		 scenario.get(8).add("123");
//		 scenario.get(8).add("123");
//		 scenario.get(8).add("123");
//		 //Result of the query1
//		 scenario.get(8).add(list);
//		 //Result of the query2
//		 scenario.get(8).add(list2);
//	     //Result excpected
//		 scenario.get(8).add(listExp2);
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(9) .add(sc2);
//		 scenario.get(9).add("123");
//		 scenario.get(9).add("123");
//		 scenario.get(9).add("123");
//		 //Result of the query1
//		 scenario.get(9).add(list);
//		 //Result of the query2
//		 List<BatteryFavLibraryEntity> list3 = new ArrayList<BatteryFavLibraryEntity>();
//		 list3.add(null);
//		 list3.add(new BatteryFavLibraryEntity());
//		 BatteryFavLibraryEntity bt2 = new BatteryFavLibraryEntity();
//		 bt2.setId(12358);
//		  Battery batt2 = new Battery();
//		  batt2.setManufacturer("bbb_ccc");
//		  batt2.setId(12358);
//		 bt2.setBattery(batt2);
//		 list3.add(bt2);
//		 scenario.get(9).add(list3);
//	     //Result excpected
//		 ArrayList<SearchBatteryResult> listExp3 = new ArrayList<SearchBatteryResult>();
//		 SearchBatteryResult scBt3 = new SearchBatteryResult();
//		 scBt3.setIsFav(true);
//		 scBt3.setId(12358);
//		 scBt3.setManufacturer("bbb_ccc");
//		 scBt3.setIsDeleted(false);
//		 listExp3.add(scBt3);
//		 scenario.get(9).add(listExp3);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(10).add(new SearchBatteryRequest());
//		 scenario.get(10).add(null);
//		 scenario.get(10).add(null);
//		 scenario.get(10).add(null);
//		 //Result of the query1
//		 scenario.get(10).add(null);
//		 //Result of the query2
//		 scenario.get(10).add(null);
//	     //Result excpected
//		 scenario.get(10).add(new ArrayList<SearchBatteryResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(11) .add(new SearchBatteryRequest());
//		 scenario.get(11).add("");
//		 scenario.get(11).add("");
//		 scenario.get(11).add("");
//		 //Result of the query1
//		 scenario.get(11).add(null);
//		 //Result of the query2
//		 scenario.get(11).add(null);
//	     //Result excpected
//		 scenario.get(11).add(new ArrayList<SearchBatteryResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(12) .add(new SearchBatteryRequest());
//		 scenario.get(12).add("123");
//		 scenario.get(12).add("123");
//		 scenario.get(12).add("123");
//		 //Result of the query1
//		 scenario.get(12).add(null);
//		 //Result of the query2
//		 scenario.get(12).add(null);
//	     //Result excpected
//		 scenario.get(12).add(new ArrayList<SearchBatteryResult>());
//		 
//			
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(13) .add(new SearchBatteryRequest());
//		 scenario.get(13).add("123");
//		 scenario.get(13).add("123");
//		 scenario.get(13).add("123");
//		 //Result of the query1
//		 scenario.get(13).add(list);
//		 //Result of the query2
//		 scenario.get(13).add(list2);
//	     //Result excpected
//		 ArrayList<SearchBatteryResult> listExp4 = new ArrayList<SearchBatteryResult>();
//		 SearchBatteryResult sc5 = new SearchBatteryResult();
//		 sc5.setIsFav(true);
//		 listExp4.add(sc5);
//		 SearchBatteryResult sc6 = new SearchBatteryResult();
//		 sc6.setIsFav(false);
//		 sc6.setEroneousContent("");
//		 sc6.setId(458999);
//		 listExp4.add(sc6);
//		 scenario.get(13).add(listExp4);
//		 
//		 
//        for(int i=0;i<scenario.size();i++) {
//			 System.out.println("searchBattery [ "+i+" ]");
//			 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//
//            ArrayList<SearchBatteryResult> result = ((ArrayList<SearchBatteryResult>) getBatteryLibraryService.searchBattery((SearchBatteryRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3)));
//			
//		 }
//	}
//	
//	@Test
//	public void testsendCorrectionBatteryRequest() {
//
//		Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Battery u Where u.id = :p1 ")).thenReturn(mockedQuery1);
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
//			 BatteryCorrectionRequest acComSLC = new BatteryCorrectionRequest();
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
//			 BatteryCorrectionRequest acComSLC1= new BatteryCorrectionRequest();
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
//			 BatteryCorrectionRequest acComSLC2= new BatteryCorrectionRequest();
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
//			 BatteryCorrectionRequest acComSLC4= new BatteryCorrectionRequest();
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
//			 Battery lib = new Battery();
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
//	             getBatteryLibraryService.sendCorrectionBatteryRequest( (BatteryCorrectionRequest) scenario.get(i).get(0));
//
//			 }
//			 
//	
//	}
//	@Test
//	public void testgetNumberSearchBattery() {
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(BatteryFavLibraryEntity.class)).thenReturn(root);
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
//		 SearchBatteryRequest sc = new SearchBatteryRequest();
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
//		 scenario.get(3).add(new SearchBatteryRequest());
//		 scenario.get(3).add(null);
//		 //Result of query1
//		 scenario.get(3).add(8L); 
//		//Excpected Result
//		 scenario.get(3).add(8L);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getNumberSearchBattery [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//	        getBatteryLibraryService.getNumberSearchBattery((SearchBatteryRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		 }
//   
//	}
//	@Test
//	public void testgetBatteryLibrary() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from BatteryFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 ")).thenReturn(mockedQuery1);
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
//		 scenario.get(3).add(new ArrayList<BatteryFavLibraryEntity>());
//	     //Result excpected
//		 scenario.get(3).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add("123");
//		 //Result of the query1
//		 ArrayList<BatteryFavLibraryEntity> list = new ArrayList<BatteryFavLibraryEntity>();
//		 list.add(null);
//		 list.add(new BatteryFavLibraryEntity());
//		 scenario.get(4).add(list);
//	     //Result excpected
//		 scenario.get(4).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(5).add("123");
//		 //Result of the query1
//		 ArrayList<BatteryFavLibraryEntity> list2= new ArrayList<BatteryFavLibraryEntity>();
//		 BatteryFavLibraryEntity dc = new BatteryFavLibraryEntity();
//		 dc.setBattery(new Battery());
//		 list2.add(dc);
//		 BatteryFavLibraryEntity dc2 = new BatteryFavLibraryEntity();
//		 Battery dcEnt = new Battery();
//		 dcEnt.setId(123);
//		 dc2.setBattery(dcEnt);
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
//			 System.out.println("getBatteryFavorite [ "+i+" ]");
//            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//            getBatteryLibraryService.getBatteryFavorite((String)scenario.get(i).get(0));
//
//		 }
//	
//	}
//	@Test
//	public void testgetBatteryFavorite() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from BatteryFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 ")).thenReturn(mockedQuery1);
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
//		 scenario.get(3).add(new ArrayList<BatteryFavLibraryEntity>());
//	     //Result excpected
//		 scenario.get(3).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add("123");
//		 //Result of the query1
//		 ArrayList<BatteryFavLibraryEntity> list = new ArrayList<BatteryFavLibraryEntity>();
//		 list.add(null);
//		 list.add(new BatteryFavLibraryEntity());
//		 scenario.get(4).add(list);
//	     //Result excpected
//		 scenario.get(4).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(5).add("123");
//		 //Result of the query1
//		 ArrayList<BatteryFavLibraryEntity> list2= new ArrayList<BatteryFavLibraryEntity>();
//		 BatteryFavLibraryEntity dc = new BatteryFavLibraryEntity();
//		 dc.setBattery(new Battery());
//		 list2.add(dc);
//		 BatteryFavLibraryEntity dc2 = new BatteryFavLibraryEntity();
//		 Battery dcEnt = new Battery();
//		 dcEnt.setId(123);
//		 dc2.setBattery(dcEnt);
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
//			 System.out.println("getBatteryFavorite [ "+i+" ]");
//            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//            getBatteryLibraryService.getBatteryFavorite((String)scenario.get(i).get(0));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testaddBatteryFavorite() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Battery u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
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
//		 scenario.get(4).add(new Battery());
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
//		 scenario.get(5).add(new Battery());
//	     //Result excpected
//		 scenario.get(5).add("Done");
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("addBatteryFavorite [ "+i+" ]");
//            when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//            when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//            getBatteryLibraryService.addBatteryFavorite((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	}
//	
//	@Test
//	public void testremoveBatteryFavorite() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from BatteryFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 and u.battery.id = :p2")).thenReturn(mockedQuery2);
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
//		 scenario.get(4).add(new BatteryFavLibraryEntity());
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
//		 BatteryFavLibraryEntity bt = new BatteryFavLibraryEntity();
//		 bt.setBattery(new Battery());
//		 scenario.get(5).add(bt);
//	     //Result excpected
//		 scenario.get(5).add("Done");
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("removeBatteryFavorite [ "+i+" ]");
//           when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//           when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//           getBatteryLibraryService.removeBatteryFavorite((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	}
//	
//	@Test
//	public void testgetContractorBatteryFav() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Battery u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 GetBatteryLibraryService getBatteryLibraryService2 = Mockito.spy(getBatteryLibraryService);
//         Mockito.doReturn(new ArrayList<Integer>()).when(getBatteryLibraryService2).getBatteryFavorite(Mockito.any());
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
//					 System.out.println("getContractorBatteryFav [ "+i+" ]");
//					 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			         Mockito.doReturn(scenario.get(i).get(3)).when(getBatteryLibraryService2).getBatteryFavorite(Mockito.any());
//
//		             ArrayList<Battery> result = ((ArrayList<Battery>) getBatteryLibraryService2.getContractorBatteryFav((String) scenario.get(i).get(0)));
//					 
//					
//				 }
//
//	}
//	
//	@Test
//	public void testgetSuperUserBatteryFav() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u " + " from PermitEntity u " + " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Battery u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 GetBatteryLibraryService getBatteryLibraryService2 = Mockito.spy(getBatteryLibraryService);
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
//			 scenario.get(0).add(new ArrayList<Battery>());
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
//			 scenario.get(1).add(new ArrayList<Battery>());
//			 
//	         scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(2).add("123");
//			 //Result of the query1
//			 scenario.get(2).add(null);
//			 //Result of the query2
//			 scenario.get(2).add(null);
//		     //Result excpected
//			 scenario.get(2).add(new ArrayList<Battery>());
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
//			 ArrayList<Battery> listExp = new ArrayList<Battery>();
//			 listExp.add(null);
//			 listExp.add(null);
//			 listExp.add(null);
//			 scenario.get(3).add(listExp);
//			 
//			 
//
//			 
//	       for(int i=0;i<scenario.size();i++) {
//				 System.out.println("getSuperUserBatteryFav [ "+i+" ]");
//				 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//				 when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//		         Mockito.doReturn(batteryFavID).when(getBatteryLibraryService2).getBatteryFavorite(Mockito.any());
//
//	             ArrayList<Battery> result = ((ArrayList<Battery>) getBatteryLibraryService2.getSuperUserBatteryFav((String) scenario.get(i).get(0)));
//				 
//				
//			 }
//	}
//	
//	@Test
//	public void testgetTestBatteryFav() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u "
//					+ " from PermitEntity u "
//					+ " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Battery u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 GetBatteryLibraryService getBatteryLibraryService2 = Mockito.spy(getBatteryLibraryService);
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
//			 Battery bt = new Battery();
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
//				 System.out.println("getTestBatteryFav [ "+i+" ]");
//				 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//				 when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//		         Mockito.doReturn(scenario.get(i).get(5)).when(getBatteryLibraryService2).getBatteryFavorite(Mockito.any());
//                 getBatteryLibraryService2.getTestBatteryFav((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//			 }
//			 
//			 
//			
//	}
//	
//	@Test
//	public void testaddBattery() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u.model , u.manufacturer from Battery u where u.manufacturer=:p1 and u.model =:p2 ")).thenReturn(mockedQuery1);
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
//			 scenario.get(1).add(new BatteryFavRequest());
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
//			 scenario.get(2).add(new BatteryFavRequest());
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 //Result of query1
//			 scenario.get(2).add(new ArrayList<Battery>()); 
//			 //Result of query2
//			 scenario.get(2).add(null); 
//			//Excpected Result
//			 scenario.get(2).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(3).add("");
//			 scenario.get(3).add(new BatteryFavRequest());
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 //Result of query1
//			 scenario.get(3).add(new ArrayList<Battery>()); 
//			 //Result of query2
//			 scenario.get(3).add(null); 
//			//Excpected Result
//			 scenario.get(3).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(4).add("123");
//			 scenario.get(4).add(new BatteryFavRequest());
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 //Result of query1
//			 scenario.get(4).add(new ArrayList<Battery>()); 
//			 //Result of query2
//			 scenario.get(4).add(null); 
//			//Excpected Result
//			 scenario.get(4).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(5).add("123");
//			 scenario.get(5).add(new BatteryFavRequest());
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 //Result of query1
//			 scenario.get(5).add(new ArrayList<Battery>()); 
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
//			 scenario.get(6).add(new BatteryFavRequest());
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 //Result of query1
//			 ArrayList<Battery> list = new ArrayList<Battery>();
//			 list.add(null);
//			 list.add(new Battery());
//			 scenario.get(6).add(list); 
//			 //Result of query2
//			 scenario.get(6).add(auth); 
//			//Excpected Result
//			 scenario.get(6).add("Battery already exists in data sources");
//			 
//			 for(int i=0; i<scenario.size();i++) {
//				 System.out.println("addBackFeedSolarOCPD [ "+i+" ]");
//	            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(5));
//	            when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(6));
//	             getBatteryLibraryService.addBattery((String)scenario.get(i).get(0),(BatteryFavRequest)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4));
//
//			 }
//	}
//	
//	@Test
//	public void testeditBattery() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Battery u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 GetBatteryLibraryService getBatteryLibraryService2 = Mockito.spy(getBatteryLibraryService);
//
//		 
//      List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		 scenario.get(1).add(new BatteryFavRequest());
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
//		 scenario.get(2).add(new BatteryFavRequest());
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
//		 scenario.get(3).add(new BatteryFavRequest());
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
//		 scenario.get(4).add(new BatteryFavRequest());
//		 scenario.get(4).add(null);
//		 //Result of query1
//		 scenario.get(4).add(list); 
//		//Excpected Result
//		 scenario.get(4).add("success");
//		//Result of query1 singleResult
//		 scenario.get(4).add(new Battery()); 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(5).add(new BatteryFavRequest());
//		 scenario.get(5).add(null);
//		 //Result of query1
//		 scenario.get(5).add(list); 
//		//Excpected Result
//		 scenario.get(5).add("exist");
//		//Result of query1 singleResult
//		 Battery bc = new Battery();
//		 bc.setId(12589);
//		 scenario.get(5).add(bc); 
//		 
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("editBattery [ "+i+" ]");
//           when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//           when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//           Mockito.doReturn("success").when(getBatteryLibraryService2).editBatteryFunction(Mockito.any(),Mockito.any());
//
//           getBatteryLibraryService2.editBattery((BatteryFavRequest)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	}
//	
//	@Test
//	public void testeditBatteryFunction() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Battery u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM PermitAdditionalInfoEntity u WHERE u.battery= :p1 ")).thenReturn(mockedQuery2);
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
//			 scenario.get(1).add(new BatteryFavRequest());
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
//			 scenario.get(2).add(new BatteryFavRequest());
//			 scenario.get(2).add(null);
//			 //Result of query1
//			 scenario.get(2).add(new Battery()); 
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
//			 scenario.get(3).add(new BatteryFavRequest());
//			 scenario.get(3).add(null);
//			 //Result of query1
//			 scenario.get(3).add(new Battery()); 
//			 //Result of query2
//			 List<PermitAdditionalInfoEntity> list = new ArrayList<PermitAdditionalInfoEntity>();
//			 list.add(null);
//			 list.add(new PermitAdditionalInfoEntity());
//			 PermitAdditionalInfoEntity el = new PermitAdditionalInfoEntity();
//			 scenario.get(3).add(list); 
//			//Excpected Result
//			 scenario.get(3).add("success");
//			 
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("editBatteryFunction [ "+i+" ]");
//		     when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//             when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//             getBatteryLibraryService.editBatteryFunction((BatteryFavRequest)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	}
//	
//	@Test
//	public void testeditBatteryNotification() {
//	 Query mockedQuery1 = mock(Query.class);
//	 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//	 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//	 
//    List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//	 
//	 scenario.add(new ArrayList<Object>());
//	 //List of the parameter
//	 scenario.get(0).add(null);
//	 scenario.get(0).add(null);
//	 scenario.get(0).add(null);
//	 //Result of query1
//	 scenario.get(0).add(null); 
//	//Excpected Result
//	 scenario.get(0).add("fail");
//	 
//	 scenario.add(new ArrayList<Object>());
//	 //List of the parameter
//	 scenario.get(1).add("123");
//	 scenario.get(1).add(null);
//	 scenario.get(1).add(null);
//	 //Result of query1
//	 scenario.get(1).add(new AuthentificationEntity()); 
//	//Excpected Result
//	 scenario.get(1).add("Success");
//	 
//	 for(int i=0; i<scenario.size();i++) {
//		 System.out.println("editBatteryNotification [ "+i+" ]");
//        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//        getBatteryLibraryService.editBatteryNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//	 }
//	}
//	
//	@Test
//	public void testaddBatteryNotification() {
//		 Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
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
//			 System.out.println("addBatteryNotification [ "+i+" ]");
//            when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//            getBatteryLibraryService.addBatteryNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	}
//	
//	@Test
//	public void testbatteryLibraryActived() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Battery i Where i.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Battery u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
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
//		 Battery ac1 = new Battery();
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
//		 Battery acCombiner = new Battery();
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
//		 scenario.get(6).add(new ArrayList<Battery>());
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
//		 ArrayList<Battery> listAcCom= new ArrayList<Battery>();
//		 listAcCom.add(new Battery());
//		 scenario.get(7).add(listAcCom);
//		 //The result excpected
//		 scenario.get(7).add("exist");
//		 
//		
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("batteryLibraryActived [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			 getBatteryLibraryService.batteryLibraryActived(123,(String)scenario.get(i).get(1));
//
//		 }
//	}
//	@Test
//	public void testgetAllPermitOfBatteryDeleted() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Battery i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitAdditionalInfoEntity p Where "
//					+ "p.battery = :p1 " )).thenReturn(mockedQuery2);
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
//		 scenario.get(1).add(new Battery());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<PermitEntity>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 Battery bc = new Battery();
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
//		 ArrayList<PermitAdditionalInfoEntity> list = new ArrayList<PermitAdditionalInfoEntity>();
//		 list.add(null);
//		 list.add(new PermitAdditionalInfoEntity());
//		 scenario.get(3).add(list);
//	     //Result excpected
//		 scenario.get(3).add(new ArrayList<PermitEntity>());
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("getAllPermitOfBatteryDeleted [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 getBatteryLibraryService.getAllPermitOfBatteryDeleted((Integer)scenario.get(i).get(0));
//
//		 }
//		 
//	}
//	@Test
//	public void testgetAllPermitOfBatteryDeleted1() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Battery i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitAdditionalInfoEntity p Where "
//					+ "p.battery = :p1 " )).thenReturn(mockedQuery2);
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
//		 scenario.get(1).add(new Battery());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(null);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(123);
//		 //Result of the query1
//		 Battery bc = new Battery();
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
//		 ArrayList<PermitAdditionalInfoEntity> list = new ArrayList<PermitAdditionalInfoEntity>();
//		 list.add(null);
//		 list.add(new PermitAdditionalInfoEntity());
//		 scenario.get(3).add(list);
//	     //Result excpected
//		 scenario.get(3).add(new ArrayList<PermitEntity>());
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("getAllPermitOfBatteryDeleted1 [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 getBatteryLibraryService.getAllPermitOfBatteryDeleted1((int)scenario.get(i).get(0));
//
//		 }
//	}
//	
//	@Test
//	public void testdeleteBatteryLibrary() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Battery i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitAdditionalInfoEntity p Where "
//					+ "p.permitEntity = :p1 and p.battery = :p2")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from BatteryFavLibraryEntity u WHERE u.battery.id = :p2")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//		 
//		 GetBatteryLibraryService getBatteryLibraryService2 = Mockito.spy(getBatteryLibraryService);
//
//         ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		 scenario.get(3).add(new Battery());
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
//		 scenario.get(4).add(new Battery());
//		 //Result of the query2
//		 PermitAdditionalInfoEntity prAdd = new PermitAdditionalInfoEntity();
//		 prAdd.setBattery("");
//		 scenario.get(4).add(prAdd);
//		 //Result of the query3
//		 scenario.get(4).add(null);
//	     //Result excpected
//		 scenario.get(4).add(true);
//		 //Result of the function getAllPermitOfBatteryDeleted1
//		 scenario.get(4).add(listFun);
//		 //Result of the query2 list
//		 ArrayList<PermitAdditionalInfoEntity> listPrAdd = new ArrayList<PermitAdditionalInfoEntity>();
//		 listPrAdd.add(prAdd);
//		 scenario.get(4).add(listPrAdd);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("deleteBatteryLibrary [ "+i+" ]");
//           Mockito.doReturn((List)scenario.get(i).get(6)).when(getBatteryLibraryService2).getAllPermitOfBatteryDeleted1(Mockito.anyInt());
//
//           when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//           when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//           when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(7));
//           when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(4));
//           getBatteryLibraryService2.deleteBatteryLibrary((int)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	}
//	
//	@Test
//	public void testgetUsersForFavList() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from BatteryFavLibraryEntity u WHERE u.battery.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult ( "
//					+ "   u.id,  " + "   u.firstName,  " + "   u.lastName ) "
//					+ " from AuthentificationEntity u WHERE u.id NOT IN :p1 and u.deleted = :p2 and u.active = :p3 and u.id != :p4 ORDER BY u.firstName")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult ( "
//					+ "   u.id,  " + "   u.firstName,  " + "   u.lastName ) "
//					+ " from AuthentificationEntity u WHERE u.deleted = :p2 and u.active = :p3 and u.id != :p4 ORDER BY u.firstName")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
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
//		 //Result of the query3
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new ArrayList<UsersEntityResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(1).add(null);
//			 scenario.get(1).add("");
//			 //Result of the query1
//			 scenario.get(1).add(new ArrayList<BatteryFavLibraryEntity>());
//			 //Result of the query2
//			 scenario.get(1).add(null);
//			 //Result of the query3
//			 scenario.get(1).add(null);
//		     //Result excpected
//			 scenario.get(1).add(new ArrayList<UsersEntityResult>());
//			 
//			 scenario.add(new ArrayList<Object>());
//				//List of the parameter
//				 scenario.get(2).add(null);
//				 scenario.get(2).add("113");
//				 //Result of the query1
//				 scenario.get(2).add(new ArrayList<BatteryFavLibraryEntity>());
//				 //Result of the query2
//				 scenario.get(2).add(null);
//				 //Result of the query3
//				 scenario.get(2).add(null);
//			     //Result excpected
//				 scenario.get(2).add(null);
//				 
//				 scenario.add(new ArrayList<Object>());
//					//List of the parameter
//					 scenario.get(3).add(null);
//					 scenario.get(3).add("113");
//					 //Result of the query1
//					 scenario.get(3).add(new ArrayList<BatteryFavLibraryEntity>());
//					 //Result of the query2
//					 scenario.get(3).add(null);
//					 //Result of the query3
//					 scenario.get(3).add(new ArrayList<UsersEntityResult>());
//				     //Result excpected
//					 scenario.get(3).add(new ArrayList<UsersEntityResult>());
//					 
//					 scenario.add(new ArrayList<Object>());
//						//List of the parameter
//						 scenario.get(4).add(null);
//						 scenario.get(4).add("113");
//						 //Result of the query1
//						ArrayList<BatteryFavLibraryEntity> list = new ArrayList<BatteryFavLibraryEntity>();
//						list.add(null);
//						list.add(new BatteryFavLibraryEntity());
//						BatteryFavLibraryEntity ac = new BatteryFavLibraryEntity();
//						ac.setAuthentificationEntity(new AuthentificationEntity());
//						list.add(ac);
//						 scenario.get(4).add(list);
//						 //Result of the query2
//						 scenario.get(4).add(null);
//						 //Result of the query3
//						 scenario.get(4).add(new ArrayList<UsersEntityResult>());
//					     //Result excpected
//						 scenario.get(4).add(null);
//						 
//						 scenario.add(new ArrayList<Object>());
//							//List of the parameter
//							 scenario.get(5).add(null);
//							 scenario.get(5).add("113");
//							 //Result of the query1
//							 scenario.get(5).add(list);
//							 //Result of the query2
//							 scenario.get(5).add(new ArrayList<UsersEntityResult>());
//							 //Result of the query3
//							 scenario.get(5).add(new ArrayList<UsersEntityResult>());
//						     //Result excpected
//							 scenario.get(5).add(new ArrayList<UsersEntityResult>());
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getUsersForFavList [ "+i+" ]");
//           when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//           when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//           when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(4));
//           getBatteryLibraryService.getUsersForFavList((Integer)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//		 
//	}
//	@Test
//	public void testeditUsersFavoriteList() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Battery u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//         ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		 scenario.get(3).add(new Battery());
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
//		 scenario.get(4).add(new Battery());
//		 //Result of the query2
//		 scenario.get(4).add(new AuthentificationEntity());
//	     //Result excpected
//		 scenario.get(4).add("Done");
//
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("editUsersFavoriteList [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(8));
//             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(9));
//             getBatteryLibraryService.editUsersFavoriteList((Integer)scenario.get(i).get(0),(String[])scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6),(String)scenario.get(i).get(7));
//
//		 }
//	}
//	@Test
//	public void testcheckBatteryExistent() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Battery u WHERE u.model = :p1 AND u.manufacturer = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Battery u WHERE u.model = :p1 AND u.manufacturer != :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 GetBatteryLibraryService getBatteryLibraryService2 = Mockito.spy(getBatteryLibraryService);
//
//         ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		 scenario.get(0).add(new ArrayList<BatteryFavRequest>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(new BatteryFavRequest());
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<BatteryFavRequest>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add(new BatteryFavRequest());
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 List<Battery> list = new ArrayList<Battery>();
//		 list.add(null);
//		 list.add(new Battery());
//		 Battery bt = new Battery();
//		 bt.setId(223);
//		 bt.setManufacturer("abc");
//		 list.add(bt);
//		 scenario.get(2).add(list);
//		 //Result of the query2
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 List<BatteryFavRequest> listExpec = new ArrayList<BatteryFavRequest>();
//		 listExpec.add(new BatteryFavRequest());
//		 BatteryFavRequest btExp2 = new BatteryFavRequest();
//		 btExp2.setIsFav("false");
//		 listExpec.add(btExp2);
//		 BatteryFavRequest btExp = new BatteryFavRequest();
//		 btExp.setId(223);
//		 btExp.setManufacturer("abc");
//		 btExp.setIsFav("false");
//		 listExpec.add(btExp);
//		 scenario.get(2).add(listExpec);
//		 
//		 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(3).add(new BatteryFavRequest());
//			 scenario.get(3).add(null);
//			 //Result of the query1
//			 scenario.get(3).add(null);
//			 //Result of the query2
//			 scenario.get(3).add(list);
//		     //Result excpected
//			 scenario.get(3).add(listExpec);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("checkBatteryExistent [ "+i+" ]");
//			 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//
//			
//             Mockito.doReturn(new ArrayList<Integer>()).when(getBatteryLibraryService2).getBatteryFavorite(Mockito.any());
//             ArrayList<BatteryFavRequest> result = ((ArrayList<BatteryFavRequest>) getBatteryLibraryService2.checkBatteryExistent((BatteryFavRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1)));
//			 
//			
//		 }
//		 
//		 
//		 
//	}
//	@Test
//	public void testaddNewBattery() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u.authentificationEntity from PermitEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//         ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		 scenario.get(0).add(new Battery());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(new BatteryFavRequest());
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 Battery bt = new Battery();
//		 Date d = new Date();
//		 SimpleDateFormat FormattedDATE = new SimpleDateFormat("MM/dd/yyyy");
//		 String updatedDate = FormattedDATE.format(d); 
//		 bt.setUpdated(updatedDate);
//		 scenario.get(1).add(bt);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add("");
//		 scenario.get(2).add(new BatteryFavRequest());
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
//		 scenario.get(3).add(new BatteryFavRequest());
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
//		 scenario.get(4).add(new BatteryFavRequest());
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
//		 scenario.get(5).add(new BatteryFavRequest());
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
//		 scenario.get(6).add(new BatteryFavRequest());
//		 scenario.get(6).add("123");
//		 //Result of the query1
//		 scenario.get(6).add(null);
//		 //Result of the query2
//		 AuthentificationEntity auth = new AuthentificationEntity();
//		 auth.setRoleEntity(new RoleEntity());
//		 scenario.get(6).add(auth);
//	     //Result excpected
//		 Battery bt2 = new Battery();
//		 bt2.setUpdated(updatedDate);
//		 bt2.setHasSuperUserEdit(false);
//		 Date addDate = new Date();
//		 bt2.setAddDate(addDate);
//		 bt2.setAuthentificationEntity(auth);
//		 scenario.get(6).add(bt2);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("addNewBattery [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//             getBatteryLibraryService.addNewBattery((String)scenario.get(i).get(0),(BatteryFavRequest)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	}
//	
//}
