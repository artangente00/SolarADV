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
//import com.PlayGroundAdv.Solar.Entity.PermitAdditionalInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.Entity.TiltLegs;
//import com.PlayGroundAdv.Solar.Entity.TiltLegsFavLibraryEntity;
//import com.PlayGroundAdv.Solar.model.FlashingCorrectionRequest;
//import com.PlayGroundAdv.Solar.model.FlashingFavRequest;
//import com.PlayGroundAdv.Solar.model.SearchFlashingRequest;
//import com.PlayGroundAdv.Solar.model.SearchFlashingResult;
//import com.PlayGroundAdv.Solar.model.SearchTiltLegsRequest;
//import com.PlayGroundAdv.Solar.model.SearchTiltLegsResult;
//import com.PlayGroundAdv.Solar.model.TiltLegsCorrectionRequest;
//import com.PlayGroundAdv.Solar.model.TiltLegsFavRequest;
//import com.PlayGroundAdv.Solar.model.UsersEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetFlashingLibraryService;
//import com.PlayGroundAdv.Solar.Services.GetTiltLegsLibraryService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//
//public class TestGetTiltLegsLibraryService {
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
//	GetTiltLegsLibraryService getTiltLegsLibraryService = new GetTiltLegsLibraryService();
//	
//	
//    @Before
//	public void setupMock() {
//    	getTiltLegsLibraryService = new GetTiltLegsLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	
//	
//
//	@Test
//	public void testsearchTiltLegs() {
//		// Root<BatteryFavLibraryEntity> r = Mockito.mock(Root.class);
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		Path battery = mock(Path.class);
//
//		when(criteriaBuilder.createQuery(SearchTiltLegsResult.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(TiltLegsFavLibraryEntity.class)).thenReturn(root);
//		when(criteriaQuery.from(TiltLegs.class)).thenReturn(root);
//		when(root.get(Mockito.anyString())).thenReturn(battery);
//		// when(r.get("authentificationEntity")).thenReturn(authentificationEntity);
//		when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//		when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//		when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//		when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from TiltLegsFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
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
//		scenario.get(0).add(new ArrayList<SearchTiltLegsResult>());
//		
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 SearchTiltLegsRequest sc = new SearchTiltLegsRequest();
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
//		 scenario.get(1).add(new ArrayList<SearchTiltLegsResult>());
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
//		 scenario.get(2).add(new ArrayList<SearchTiltLegsResult>());
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
//		 scenario.get(3).add(new ArrayList<SearchTiltLegsResult>());
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
//		 scenario.get(4).add(new ArrayList<SearchTiltLegsResult>());
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
//		 scenario.get(5).add(new ArrayList<SearchTiltLegsResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(6) .add(sc);
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 //Result of the query1
//		 List<SearchTiltLegsResult> list = new ArrayList<SearchTiltLegsResult>();
//		 list.add(null);
//		 list.add(new SearchTiltLegsResult());
//		 SearchTiltLegsResult scBat = new SearchTiltLegsResult();
//		 scBat.setEroneousContent("");
//		 scBat.setId(458999);
//		 list.add(scBat);
//		 scenario.get(6).add(list);
//		 //Result of the query2
//		 scenario.get(6).add(null);
//	     //Result excpected
//		 ArrayList<SearchTiltLegsResult> listExp = new ArrayList<SearchTiltLegsResult>();
//		 SearchTiltLegsResult exp1 = new SearchTiltLegsResult();
//		 exp1.setIsFav(false);
//		 listExp.add(exp1);
//		 SearchTiltLegsResult exp2 = new SearchTiltLegsResult();
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
//		 List<TiltLegsFavLibraryEntity> list2 = new ArrayList<TiltLegsFavLibraryEntity>();
//		 list2.add(null);
//		 list2.add(new TiltLegsFavLibraryEntity());
//		 TiltLegsFavLibraryEntity bt = new TiltLegsFavLibraryEntity();
//		 bt.setId(12358);
//		 TiltLegs batt = new TiltLegs();
//		  batt.setManufacturer("bbb_ccc");
//		 bt.setTiltLegs(batt);
//		 list2.add(bt);
//		 scenario.get(7).add(list2);
//	     //Result excpected
//		 ArrayList<SearchTiltLegsResult> listExp2 = new ArrayList<SearchTiltLegsResult>();
//		 SearchTiltLegsResult scBt2 = new SearchTiltLegsResult();
//		 scBt2.setIsFav(true);
//		 listExp2.add(scBt2);
//		 scenario.get(7).add(listExp2);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 SearchTiltLegsRequest sc2 = new SearchTiltLegsRequest();
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
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(9) .add(sc2);
//		 scenario.get(9).add("123");
//		 scenario.get(9).add("123");
//		 scenario.get(9).add("123");
//		 //Result of the query1
//		 scenario.get(9).add(list);
//		 //Result of the query2
//		 List<TiltLegsFavLibraryEntity> list3 = new ArrayList<TiltLegsFavLibraryEntity>();
//		 list3.add(null);
//		 list3.add(new TiltLegsFavLibraryEntity());
//		 TiltLegsFavLibraryEntity bt2 = new TiltLegsFavLibraryEntity();
//		 bt2.setId(12358);
//		 TiltLegs batt2 = new TiltLegs();
//		  batt2.setManufacturer("bbb_ccc");
//		  batt2.setId(12358);
//		 bt2.setTiltLegs(batt2);
//		 list3.add(bt2);
//		 scenario.get(9).add(list3);
//	     //Result excpected
//		 ArrayList<SearchTiltLegsResult> listExp3 = new ArrayList<SearchTiltLegsResult>();
//		 SearchTiltLegsResult scBt3 = new SearchTiltLegsResult();
//		 scBt3.setIsFav(true);
//		 scBt3.setId(12358);
//		 scBt3.setManufacturer("bbb_ccc");
//		 scBt3.setIsDeleted(false);
//		 listExp3.add(scBt3);
//		 scenario.get(9).add(listExp3);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(10).add(new SearchTiltLegsRequest());
//		 scenario.get(10).add(null);
//		 scenario.get(10).add(null);
//		 scenario.get(10).add(null);
//		 //Result of the query1
//		 scenario.get(10).add(null);
//		 //Result of the query2
//		 scenario.get(10).add(null);
//	     //Result excpected
//		 scenario.get(10).add(new ArrayList<SearchTiltLegsResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(11) .add(new SearchTiltLegsRequest());
//		 scenario.get(11).add("");
//		 scenario.get(11).add("");
//		 scenario.get(11).add("");
//		 //Result of the query1
//		 scenario.get(11).add(null);
//		 //Result of the query2
//		 scenario.get(11).add(null);
//	     //Result excpected
//		 scenario.get(11).add(new ArrayList<SearchTiltLegsResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(12) .add(new SearchTiltLegsRequest());
//		 scenario.get(12).add("123");
//		 scenario.get(12).add("123");
//		 scenario.get(12).add("123");
//		 //Result of the query1
//		 scenario.get(12).add(null);
//		 //Result of the query2
//		 scenario.get(12).add(null);
//	     //Result excpected
//		 scenario.get(12).add(new ArrayList<SearchTiltLegsResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(13) .add(new SearchTiltLegsRequest());
//		 scenario.get(13).add("123");
//		 scenario.get(13).add("123");
//		 scenario.get(13).add("123");
//		 //Result of the query1
//		 scenario.get(13).add(list);
//		 //Result of the query2
//		 scenario.get(13).add(list2);
//	     //Result excpected
//		 ArrayList<SearchTiltLegsResult> listExp4 = new ArrayList<SearchTiltLegsResult>();
//		 SearchTiltLegsResult sc5 = new SearchTiltLegsResult();
//		 sc5.setIsFav(true);
//		 listExp4.add(sc5);
//		 SearchTiltLegsResult sc6 = new SearchTiltLegsResult();
//		 sc6.setIsFav(false);
//		 sc6.setEroneousContent("");
//		 sc6.setId(458999);
//		 listExp4.add(sc6);
//		 scenario.get(13).add(listExp4);
//		 
//		 
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("searchTiltLegs [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//
//			ArrayList<SearchTiltLegsResult> result = ((ArrayList<SearchTiltLegsResult>) getTiltLegsLibraryService
//					.searchTiltLegs((SearchTiltLegsRequest) scenario.get(i).get(0), (String) scenario.get(i).get(1),
//							(String) scenario.get(i).get(2), (String) scenario.get(i).get(3)));
//
//		}
//	}
//	
//	@Test
//	public void testsendCorrectionTiltLegsRequest() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from TiltLegs u Where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u  FROM AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Result of query2
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		TiltLegsCorrectionRequest acComSLC = new TiltLegsCorrectionRequest();
//		scenario.get(1).add(acComSLC);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		TiltLegsCorrectionRequest acComSLC1 = new TiltLegsCorrectionRequest();
//		acComSLC1.setId(123);
//		scenario.get(2).add(acComSLC1);
//		// Result of query1
//		scenario.get(2).add(null);
//		// Result of query2
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		TiltLegsCorrectionRequest acComSLC2 = new TiltLegsCorrectionRequest();
//		acComSLC2.setId(123);
//		acComSLC2.setIdUser("");
//		scenario.get(3).add(acComSLC2);
//		// Result of query1
//		scenario.get(3).add(null);
//		// Result of query2
//		scenario.get(3).add(null);
//		// Excpected Result
//		scenario.get(3).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		TiltLegsCorrectionRequest acComSLC4 = new TiltLegsCorrectionRequest();
//		acComSLC4.setId(123);
//		acComSLC4.setIdUser("123");
//		scenario.get(4).add(acComSLC4);
//		// Result of query1
//		scenario.get(4).add(null);
//		// Result of query2
//		scenario.get(4).add(null);
//		// Excpected Result
//		scenario.get(4).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(acComSLC4);
//		// Result of query1
//		TiltLegs lib = new TiltLegs();
//		scenario.get(5).add(lib);
//		// Result of query2
//		scenario.get(5).add(null);
//		// Excpected Result
//		scenario.get(5).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(6).add(acComSLC4);
//		// Result of query1
//		scenario.get(6).add(lib);
//		// Result of query2
//		AuthentificationEntity auth = new AuthentificationEntity();
//		scenario.get(6).add(auth);
//		// Excpected Result
//		scenario.get(6).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("sendCorrectionBatteryRequest [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			 getTiltLegsLibraryService
//					.sendCorrectionTiltLegsRequest((TiltLegsCorrectionRequest) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testgetNumberSearchTiltLegs() {
//
//
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(TiltLegsFavLibraryEntity.class)).thenReturn(root);
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
//		 SearchTiltLegsRequest sc = new SearchTiltLegsRequest();
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
//		 scenario.get(3).add(new SearchTiltLegsRequest());
//		 scenario.get(3).add(null);
//		 //Result of query1
//		 scenario.get(3).add(8L); 
//		//Excpected Result
//		 scenario.get(3).add(8L);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getNumberSearchTiltLegs [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//	        getTiltLegsLibraryService.getNumberSearchTiltLegs((SearchTiltLegsRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		 }
//   
//	
//	
//	}
//	
//	@Test
//	public void testgetTiltLegsLibrary() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from TiltLegs u order by u.manufacturer")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//		when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//		GetTiltLegsLibraryService getTiltLegsLibraryService2 = Mockito.spy(getTiltLegsLibraryService);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add(new ArrayList<TiltLegsFavRequest>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		// Result of query1
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add(new ArrayList<TiltLegsFavRequest>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add("123");
//		scenario.get(2).add("123");
//		// Result of query1
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add(new ArrayList<TiltLegsFavRequest>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add("123");
//		scenario.get(3).add("123");
//		// Result of query1
//		List<TiltLegs> list = new ArrayList<TiltLegs>();
//		list.add(null);
//		list.add(new TiltLegs());
//		scenario.get(3).add(list);
//		// Excpected Result
//		List<TiltLegsFavRequest> listExp = new ArrayList<TiltLegsFavRequest>();
//		TiltLegsFavRequest fl = new TiltLegsFavRequest();
//		fl.setIsFav("false");
//		listExp.add(new TiltLegsFavRequest());
//		listExp.add(fl);
//		scenario.get(3).add(listExp);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getFlashingLibrary [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			ArrayList<TiltLegsFavRequest> result = ((ArrayList<TiltLegsFavRequest>) getTiltLegsLibraryService2
//					.getTiltLegsLibrary((String) scenario.get(i).get(0), (String) scenario.get(i).get(1),
//							(String) scenario.get(i).get(2)));
//			Mockito.doReturn(new ArrayList<Integer>()).when(getTiltLegsLibraryService2)
//					.getTiltLegsFavorite(Mockito.any());
//
//
//		}
//
//	}
//	
//	@Test
//	public void testgetTiltLegsFavorite() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from TiltLegsFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<Integer>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<Integer>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<Integer>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(new ArrayList<TiltLegsFavLibraryEntity>());
//		// Result excpected
//		scenario.get(3).add(new ArrayList<Integer>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		// Result of the query1
//		ArrayList<TiltLegsFavLibraryEntity> list = new ArrayList<TiltLegsFavLibraryEntity>();
//		list.add(null);
//		list.add(new TiltLegsFavLibraryEntity());
//		scenario.get(4).add(list);
//		// Result excpected
//		scenario.get(4).add(new ArrayList<Integer>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("123");
//		// Result of the query1
//		ArrayList<TiltLegsFavLibraryEntity> list2 = new ArrayList<TiltLegsFavLibraryEntity>();
//		TiltLegsFavLibraryEntity dc = new TiltLegsFavLibraryEntity();
//		dc.setTiltLegs(new TiltLegs());
//		list2.add(dc);
//		TiltLegsFavLibraryEntity dc2 = new TiltLegsFavLibraryEntity();
//		TiltLegs dcEnt = new TiltLegs();
//		dcEnt.setId(123);
//		dc2.setTiltLegs(dcEnt);
//		list2.add(dc2);
//		scenario.get(5).add(list2);
//		// Result excpected
//		ArrayList<Integer> rlt = new ArrayList<Integer>();
//		rlt.add(0);
//		rlt.add(123);
//		scenario.get(5).add(rlt);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getTiltLegsFavorite [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			
//					getTiltLegsLibraryService.getTiltLegsFavorite((String) scenario.get(i).get(0));
//
//		}
//
//	}
//	
//	@Test
//	public void testaddTiltLegsFavorite() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from TiltLegs u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
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
//		 scenario.get(4).add(new TiltLegs());
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
//		 scenario.get(5).add(new TiltLegs());
//	     //Result excpected
//		 scenario.get(5).add("Done");
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("addTiltLegsFavorite [ "+i+" ]");
//          when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//          when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//          getTiltLegsLibraryService.addTiltLegsFavorite((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	
//	
//	}
//	
//	@Test
//	public void testremoveTiltLegsFavorite() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from TiltLegsFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 and u.tiltLegs.id = :p2")).thenReturn(mockedQuery2);
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
//		 scenario.get(4).add(new TiltLegsFavLibraryEntity());
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
//		 TiltLegsFavLibraryEntity bt = new TiltLegsFavLibraryEntity();
//		 bt.setTiltLegs(new TiltLegs());
//		 scenario.get(5).add(bt);
//	     //Result excpected
//		 scenario.get(5).add("Done");
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("removeTiltLegsFavorite [ "+i+" ]");
//         when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//         when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//         getTiltLegsLibraryService.removeTiltLegsFavorite((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	
//	
//	}
//	
//	@Test
//	public void testgetContractorTiltLegsFav() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from TiltLegs u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		GetTiltLegsLibraryService getTiltLegsLibraryService2 = Mockito.spy(getTiltLegsLibraryService);
//		Mockito.doReturn(new ArrayList<Integer>()).when(getTiltLegsLibraryService2).getTiltLegsFavorite(Mockito.any());
//
//		List<Integer> batteryFavID = new ArrayList<Integer>();
//		batteryFavID.add(1);
//		batteryFavID.add(2);
//		batteryFavID.add(3);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//		// Result of the function getBatteryFavorite
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		ArrayList<TiltLegs> listExp = new ArrayList<TiltLegs>();
//		listExp.add(null);
//		listExp.add(null);
//		listExp.add(null);
//		scenario.get(1).add(listExp);
//		// Result of the function getBatteryFavorite
//		scenario.get(1).add(batteryFavID);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getContractorFlashingFav [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			Mockito.doReturn(scenario.get(i).get(3)).when(getTiltLegsLibraryService2)
//					.getTiltLegsFavorite(Mockito.any());
//
//			ArrayList<TiltLegs> result = ((ArrayList<TiltLegs>) getTiltLegsLibraryService2
//					.getContractorTiltLegsFav((String) scenario.get(i).get(0)));
//
//		}
//
//	}
//	
//	@Test
//	public void testgetSuperUserTiltLegsFav() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from PermitEntity u " + " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from TiltLegs u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		GetTiltLegsLibraryService getTiltLegsLibraryService2 = Mockito.spy(getTiltLegsLibraryService);
//		List<Integer> batteryFavID = new ArrayList<Integer>();
//		batteryFavID.add(1);
//		batteryFavID.add(2);
//		batteryFavID.add(3);
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<TiltLegs>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<TiltLegs>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<TiltLegs>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		PermitEntity per = new PermitEntity();
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setId(125);
//		per.setAuthentificationEntity(auth);
//		scenario.get(3).add(per);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		ArrayList<TiltLegs> listExp = new ArrayList<TiltLegs>();
//		listExp.add(null);
//		listExp.add(null);
//		listExp.add(null);
//		scenario.get(3).add(listExp);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getSuperUserTiltLegsFav [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			Mockito.doReturn(batteryFavID).when(getTiltLegsLibraryService2).getTiltLegsFavorite(Mockito.any());
//
//			ArrayList<TiltLegs> result = ((ArrayList<TiltLegs>) getTiltLegsLibraryService2
//					.getSuperUserTiltLegsFav((String) scenario.get(i).get(0)));
//
//		}
//
//	}
//	
//	@Test
//	public void testgetTestTiltLegsFav() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u "
//					+ " from PermitEntity u "
//					+ " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from TiltLegs u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 GetTiltLegsLibraryService getTiltLegsLibraryService2 = Mockito.spy(getTiltLegsLibraryService);
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
//			 TiltLegs bt = new TiltLegs();
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
//		         Mockito.doReturn(scenario.get(i).get(5)).when(getTiltLegsLibraryService2).getTiltLegsFavorite(Mockito.any());
//               getTiltLegsLibraryService2.getTestTiltLegsFav((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//			 }
//	
//	}
//	
//	@Test
//	public void testaddTiltLegs() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u.model , u.manufacturer from TiltLegs u where u.manufacturer=:p1 and u.model =:p2")).thenReturn(mockedQuery1);
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
//			 scenario.get(1).add(new TiltLegsFavRequest());
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
//			 scenario.get(2).add(new TiltLegsFavRequest());
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 //Result of query1
//			 scenario.get(2).add(new ArrayList<TiltLegs>()); 
//			 //Result of query2
//			 scenario.get(2).add(null); 
//			//Excpected Result
//			 scenario.get(2).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(3).add("");
//			 scenario.get(3).add(new TiltLegsFavRequest());
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 //Result of query1
//			 scenario.get(3).add(new ArrayList<TiltLegs>()); 
//			 //Result of query2
//			 scenario.get(3).add(null); 
//			//Excpected Result
//			 scenario.get(3).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(4).add("123");
//			 scenario.get(4).add(new TiltLegsFavRequest());
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 //Result of query1
//			 scenario.get(4).add(new ArrayList<TiltLegs>()); 
//			 //Result of query2
//			 scenario.get(4).add(null); 
//			//Excpected Result
//			 scenario.get(4).add("error");
//			 
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(5).add("123");
//			 scenario.get(5).add(new TiltLegsFavRequest());
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 //Result of query1
//			 scenario.get(5).add(new ArrayList<TiltLegs>()); 
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
//			 scenario.get(6).add(new TiltLegsFavRequest());
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 //Result of query1
//			 ArrayList<TiltLegs> list = new ArrayList<TiltLegs>();
//			 list.add(null);
//			 list.add(new TiltLegs());
//			 scenario.get(6).add(list); 
//			 //Result of query2
//			 scenario.get(6).add(auth); 
//			//Excpected Result
//			 scenario.get(6).add("TiltLegs already exists in data sources");
//			 
//			 for(int i=0; i<scenario.size();i++) {
//				 System.out.println("addTiltLegs [ "+i+" ]");
//	            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(5));
//	            when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(6));
//	            getTiltLegsLibraryService.addTiltLegs((String)scenario.get(i).get(0),(TiltLegsFavRequest)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4));
//
//			 }
//	
//	
//	}
//	
//	@Test
//	public void testeditTiltLegs() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from TiltLegs u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		GetTiltLegsLibraryService getTiltLegsLibraryService2 = Mockito.spy(getTiltLegsLibraryService);
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
//		scenario.get(1).add(new TiltLegsFavRequest());
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
//		scenario.get(2).add(new TiltLegsFavRequest());
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(new ArrayList<TiltLegs>());
//		// Excpected Result
//		scenario.get(2).add("success");
//		// Result of query1 singleResult
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new TiltLegsFavRequest());
//		scenario.get(3).add(null);
//		// Result of query1
//		ArrayList<TiltLegs> list = new ArrayList<TiltLegs>();
//		list.add(null);
//		list.add(new TiltLegs());
//		scenario.get(3).add(list);
//		// Excpected Result
//		scenario.get(3).add("success");
//		// Result of query1 singleResult
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(new TiltLegsFavRequest());
//		scenario.get(4).add(null);
//		// Result of query1
//		scenario.get(4).add(list);
//		// Excpected Result
//		scenario.get(4).add("success");
//		// Result of query1 singleResult
//		scenario.get(4).add(new TiltLegs());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(new TiltLegsFavRequest());
//		scenario.get(5).add(null);
//		// Result of query1
//		scenario.get(5).add(list);
//		// Excpected Result
//		scenario.get(5).add("exist");
//		// Result of query1 singleResult
//		TiltLegs bc = new TiltLegs();
//		bc.setId(12589);
//		scenario.get(5).add(bc);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editTiltLegs [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			Mockito.doReturn("success").when(getTiltLegsLibraryService2).editTiltLegsFunction(Mockito.any(),
//					Mockito.any());
//
//			getTiltLegsLibraryService2
//					.editTiltLegs((TiltLegsFavRequest) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//	
//	}
//	
//	@Test
//	public void testeditTiltLegsFunction() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from TiltLegs u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u FROM PermitAdditionalInfoEntity u WHERE u.tiltLegsMod= :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Result of query2
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new TiltLegsFavRequest());
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new TiltLegsFavRequest());
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(new TiltLegs());
//		// Result of query2
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("success");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new TiltLegsFavRequest());
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(new TiltLegs());
//		// Result of query2
//		List<PermitAdditionalInfoEntity> list = new ArrayList<PermitAdditionalInfoEntity>();
//		list.add(null);
//		list.add(new PermitAdditionalInfoEntity());
//		scenario.get(3).add(list);
//		// Excpected Result
//		scenario.get(3).add("success");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editTiltLegsFunction [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			getTiltLegsLibraryService.editTiltLegsFunction(
//					(TiltLegsFavRequest) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//	
//	}
//	
//	@Test
//	public void testeditTiltLegsNotification() {
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
//			 System.out.println("editTiltLegsNotification [ "+i+" ]");
//	        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//	         getTiltLegsLibraryService.editTiltLegsNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }	
//	
//	
//	}
//	
//	
//	@Test
//	public void testaddTiltLegsNotification() {
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
//			 System.out.println("addTiltLegsNotification [ "+i+" ]");
//	        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//	        getTiltLegsLibraryService.addTiltLegsNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }	
//	
//	}
//	
//	@Test
//	public void testtiltLegsLibraryActived() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from TiltLegs i Where i.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from TiltLegs u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
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
//		 TiltLegs ac1 = new TiltLegs();
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
//		 TiltLegs acCombiner = new TiltLegs();
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
//		 scenario.get(6).add(new ArrayList<TiltLegs>());
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
//		 ArrayList<TiltLegs> listAcCom= new ArrayList<TiltLegs>();
//		 listAcCom.add(new TiltLegs());
//		 scenario.get(7).add(listAcCom);
//		 //The result excpected
//		 scenario.get(7).add("exist");
//		 
//		
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("tiltLegsLibraryActived [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			 getTiltLegsLibraryService.tiltLegsLibraryActived(123,(String)scenario.get(i).get(1));
//
//		 }
//	
//	
//	}
//	
//	@Test
//	public void testgetAllPermitOfTiltLegsDeleted() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from TiltLegs i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitAdditionalInfoEntity p Where " + "p.tiltLegsMod = :p1 ")).thenReturn(mockedQuery2);
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
//		 scenario.get(1).add(new TiltLegs());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<PermitEntity>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(123);
//		 //Result of the query1
//		 TiltLegs bc = new TiltLegs();
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
//			  System.out.println("getAllPermitOfTiltLegsDeleted [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 getTiltLegsLibraryService.getAllPermitOfTiltLegsDeleted((int)scenario.get(i).get(0));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testgetAllPermitOfTiltLegsDeleted1() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT i from TiltLegs i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT p from PermitAdditionalInfoEntity p Where " + "p.tiltLegsMod = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(123);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<PermitEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(123);
//		// Result of the query1
//		scenario.get(1).add(new TiltLegs());
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(123);
//		// Result of the query1
//		TiltLegs bc = new TiltLegs();
//		bc.setId(2235);
//		scenario.get(2).add(bc);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<PermitEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(123);
//		// Result of the query1
//		scenario.get(3).add(bc);
//		// Result of the query1
//		ArrayList<PermitAdditionalInfoEntity> list = new ArrayList<PermitAdditionalInfoEntity>();
//		list.add(null);
//		list.add(new PermitAdditionalInfoEntity());
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add(new ArrayList<PermitEntity>());
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllPermitOfTiltLegsDeleted1 [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			
//					getTiltLegsLibraryService.getAllPermitOfTiltLegsDeleted1((int) scenario.get(i).get(0));
//
//		}
//
//	}
//	
//	@Test
//	public void testdeleteTiltLegsLibrary() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT i from TiltLegs i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT p from PermitAdditionalInfoEntity p Where " + "p.permitEntity = :p1 and p.tiltLegsMod = :p2"))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from TiltLegsFavLibraryEntity u WHERE u.tiltLegs.id = :p2"))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		GetTiltLegsLibraryService getTiltLegsLibraryService2 = Mockito.spy(getTiltLegsLibraryService);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(false);
//		// Result of the function getAllPermitOfBatteryDeleted1
//		scenario.get(0).add(null);
//		// Result of the query2 list
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(123);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(false);
//		// Result of the function getAllPermitOfBatteryDeleted1
//		scenario.get(1).add(null);
//		// Result of the query2 list
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(223);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(false);
//		// Result of the function getAllPermitOfBatteryDeleted1
//		ArrayList<PermitEntity> listFun = new ArrayList<PermitEntity>();
//		listFun.add(null);
//		listFun.add(new PermitEntity());
//		PermitEntity pr = new PermitEntity();
//		pr.setId(123);
//		listFun.add(pr);
//		scenario.get(2).add(listFun);
//		// Result of the query2 list
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(323);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new TiltLegs());
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(true);
//		// Result of the function getAllPermitOfBatteryDeleted1
//		scenario.get(3).add(listFun);
//		// Result of the query2 list
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(423);
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add(new TiltLegs());
//		// Result of the query2
//		PermitAdditionalInfoEntity prAdd = new PermitAdditionalInfoEntity();
//		prAdd.setBatteryEnclosureManufacturer("");
//		scenario.get(4).add(prAdd);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(true);
//		// Result of the function getAllPermitOfBatteryDeleted1
//		scenario.get(4).add(listFun);
//		// Result of the query2 list
//		ArrayList<PermitAdditionalInfoEntity> listPrAdd = new ArrayList<PermitAdditionalInfoEntity>();
//		listPrAdd.add(prAdd);
//		scenario.get(4).add(listPrAdd);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deleteTiltLegsLibrary [ " + i + " ]");
//			Mockito.doReturn((List) scenario.get(i).get(6)).when(getTiltLegsLibraryService2)
//					.getAllPermitOfTiltLegsDeleted1(Mockito.anyInt());
//
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(7));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			getTiltLegsLibraryService2
//					.deleteTiltLegsLibrary((int) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//	
//	@Test
//	public void testgetUsersForFavList() {
//
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from TiltLegsFavLibraryEntity u WHERE u.tiltLegs.id = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult ( "
//				+ "   u.id,  " + "   u.firstName,  " + "   u.lastName ) "
//				+ " from AuthentificationEntity u WHERE u.id NOT IN :p1 and u.deleted = :p2 and u.active = :p3 and u.id != :p4 ORDER BY u.firstName"))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult ( "
//				+ "   u.id,  " + "   u.firstName,  " + "   u.lastName ) "
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
//		scenario.get(1).add(new ArrayList<TiltLegsFavLibraryEntity>());
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
//		scenario.get(2).add(new ArrayList<TiltLegsFavLibraryEntity>());
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
//		scenario.get(3).add(new ArrayList<TiltLegsFavLibraryEntity>());
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
//		ArrayList<TiltLegsFavLibraryEntity> list = new ArrayList<TiltLegsFavLibraryEntity>();
//		list.add(null);
//		list.add(new TiltLegsFavLibraryEntity());
//		TiltLegsFavLibraryEntity ac = new TiltLegsFavLibraryEntity();
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
//			getTiltLegsLibraryService
//					.getUsersForFavList((Integer) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	
//	}
//	
//	@Test
//	public void testeditUsersFavoriteList() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from TiltLegs u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
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
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		String[] ListUsers = { "", null, "1233" };
//		scenario.get(1).add(ListUsers);
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
//		scenario.get(1).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		String[] ListUsers2 = { "12", "44", "1233" };
//		scenario.get(2).add(ListUsers2);
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
//		scenario.get(2).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add(ListUsers2);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new TiltLegs());
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add(ListUsers2);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add(new TiltLegs());
//		// Result of the query2
//		scenario.get(4).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(4).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editUsersFavoriteList [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(8));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(9));
//			
//					getTiltLegsLibraryService.editUsersFavoriteList((Integer) scenario.get(i).get(0),
//							(String[]) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3), (String) scenario.get(i).get(4));
//
//		}
//
//	}
//	
//	@Test
//	public void testcheckTiltLegsExistent() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from TiltLegs u WHERE u.model = :p1 AND u.manufacturer = :p2 AND u.isDeleted=false"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from TiltLegs u WHERE u.model = :p1 AND u.manufacturer != :p2 AND u.isDeleted=false"))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		GetTiltLegsLibraryService getTiltLegsLibraryService2 = Mockito.spy(getTiltLegsLibraryService);
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
//		// Result excpected
//		scenario.get(0).add(new ArrayList<TiltLegsFavRequest>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new TiltLegsFavRequest());
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<TiltLegsFavRequest>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new TiltLegsFavRequest());
//		scenario.get(2).add(null);
//		// Result of the query1
//		List<TiltLegs> list = new ArrayList<TiltLegs>();
//		list.add(null);
//		list.add(new TiltLegs());
//		TiltLegs bt = new TiltLegs();
//		bt.setId(223);
//		bt.setManufacturer("abc");
//		list.add(bt);
//		scenario.get(2).add(list);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		List<TiltLegsFavRequest> listExpec = new ArrayList<TiltLegsFavRequest>();
//		listExpec.add(new TiltLegsFavRequest());
//		TiltLegsFavRequest btExp2 = new TiltLegsFavRequest();
//		btExp2.setIsFav("false");
//		listExpec.add(btExp2);
//		TiltLegsFavRequest btExp = new TiltLegsFavRequest();
//		btExp.setId(223);
//		btExp.setManufacturer("abc");
//		btExp.setIsFav("false");
//		listExpec.add(btExp);
//		scenario.get(2).add(listExpec);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new TiltLegsFavRequest());
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add(listExpec);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("checkTiltLegsExistent [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//
//			Mockito.doReturn(new ArrayList<Integer>()).when(getTiltLegsLibraryService2)
//					.getTiltLegsFavorite(Mockito.any());
//			ArrayList<TiltLegsFavRequest> result = ((ArrayList<TiltLegsFavRequest>) getTiltLegsLibraryService2
//					.checkTiltLegsExistent((TiltLegsFavRequest) scenario.get(i).get(0),
//							(String) scenario.get(i).get(1)));
//
//		}
//
//	}
//	
//	@Test
//	public void testaddNewTiltLegs() {
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
//		 scenario.get(0).add(new TiltLegs());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(new TiltLegsFavRequest());
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 TiltLegs bt = new TiltLegs();
//		 Date d = new Date();
//		 SimpleDateFormat FormattedDATE = new SimpleDateFormat("MM/dd/yyyy");
//		 String updatedDate = FormattedDATE.format(d); 
//		 bt.setUpdated(updatedDate);
//		 scenario.get(1).add(bt);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add("");
//		 scenario.get(2).add(new TiltLegsFavRequest());
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
//		 scenario.get(3).add(new TiltLegsFavRequest());
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
//		 scenario.get(4).add(new TiltLegsFavRequest());
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
//		 scenario.get(5).add(new TiltLegsFavRequest());
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
//		 scenario.get(6).add(new TiltLegsFavRequest());
//		 scenario.get(6).add("123");
//		 //Result of the query1
//		 scenario.get(6).add(null);
//		 //Result of the query2
//		 AuthentificationEntity auth = new AuthentificationEntity();
//		 auth.setRoleEntity(new RoleEntity());
//		 scenario.get(6).add(auth);
//	     //Result excpected
//		 TiltLegs bt2 = new TiltLegs();
//		 bt2.setUpdated(updatedDate);
//		 bt2.setHasSuperUserEdit(false);
//		 Date addDate = new Date();
//		 bt2.setAddDate(addDate);
//		 bt2.setAuthentificationEntity(auth);
//		 scenario.get(6).add(bt2);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("addNewTiltLegs [ "+i+" ]");
//           when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//           when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//          getTiltLegsLibraryService.addNewTiltLegs((String)scenario.get(i).get(0),(TiltLegsFavRequest)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	
//	
//	}
//}
