//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
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
//import com.PlayGroundAdv.Solar.Entity.Flashing;
//import com.PlayGroundAdv.Solar.Entity.FlashingFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.InverterLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.Inverters;
//import com.PlayGroundAdv.Solar.Entity.LeasePPAMeter;
//import com.PlayGroundAdv.Solar.Entity.LeasePPAMeterFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitArraysEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.model.FlashingCorrectionRequest;
//import com.PlayGroundAdv.Solar.model.FlashingFavRequest;
//import com.PlayGroundAdv.Solar.model.InverterCorrectionReq;
//import com.PlayGroundAdv.Solar.model.InverterFavRequest;
//import com.PlayGroundAdv.Solar.model.LeasePPAMeterFavRequest;
//import com.PlayGroundAdv.Solar.model.SearchFlashingRequest;
//import com.PlayGroundAdv.Solar.model.SearchFlashingResult;
//import com.PlayGroundAdv.Solar.model.SearchInverterRequest;
//import com.PlayGroundAdv.Solar.model.SearchInvertersResult;
//import com.PlayGroundAdv.Solar.model.UsersEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetFlashingLibraryService;
//import com.PlayGroundAdv.Solar.Services.GetInverterLibraryService;
//import com.PlayGroundAdv.Solar.Services.GetLeasePPAMeterLibraryService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//
//public class TestGetInverterLibraryService {
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
//	GetInverterLibraryService getInverterLibraryService = new GetInverterLibraryService();
//	
//	
//    @Before
//	public void setupMock() {
//    	getInverterLibraryService = new GetInverterLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//
//	@Test
//	public void testsearchInverters() {
//		// Root<BatteryFavLibraryEntity> r = Mockito.mock(Root.class);
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		Path battery = mock(Path.class);
//
//		when(criteriaBuilder.createQuery(SearchInvertersResult.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(InverterLibraryEntity.class)).thenReturn(root);
//		when(criteriaQuery.from(Inverters.class)).thenReturn(root);
//		when(root.get(Mockito.anyString())).thenReturn(battery);
//		// when(r.get("authentificationEntity")).thenReturn(authentificationEntity);
//		when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//		when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//		when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//		when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from InverterLibraryEntity u WHERE u.authentificationEntity.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
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
//		 scenario.get(0).add(new ArrayList<SearchInvertersResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 SearchInverterRequest sc = new SearchInverterRequest();
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
//		 scenario.get(1).add(new ArrayList<SearchInvertersResult>());
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
//		 scenario.get(2).add(new ArrayList<SearchInvertersResult>());
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
//		 scenario.get(3).add(new ArrayList<SearchInvertersResult>());
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
//		 scenario.get(4).add(new ArrayList<SearchInvertersResult>());
//		 
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
//		 scenario.get(5).add(new ArrayList<SearchInvertersResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(6) .add(sc);
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 //Result of the query1
//		 List<SearchInvertersResult> list = new ArrayList<SearchInvertersResult>();
//		 list.add(null);
//		 list.add(new SearchInvertersResult());
//		 SearchInvertersResult scBat = new SearchInvertersResult();
//		 scBat.setEroneousContent("");
//		 scBat.setId(458999);
//		 list.add(scBat);
//		 scenario.get(6).add(list);
//		 //Result of the query2
//		 scenario.get(6).add(null);
//	     //Result excpected
//		 ArrayList<SearchInvertersResult> listExp = new ArrayList<SearchInvertersResult>();
//		 SearchInvertersResult exp1 = new SearchInvertersResult();
//		 exp1.setIsFav(false);
//		 listExp.add(exp1);
//		 SearchInvertersResult exp2 = new SearchInvertersResult();
//		 exp2.setEroneousContent("");
//		 exp2.setId(458999);
//		 exp2.setIsFav(false);
//		 listExp.add(exp2);
//		 scenario.get(6).add(listExp);
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
//		 List<InverterLibraryEntity> list2 = new ArrayList<InverterLibraryEntity>();
//		 list2.add(null);
//		 list2.add(new InverterLibraryEntity());
//		 InverterLibraryEntity bt = new InverterLibraryEntity();
//		 bt.setId(12358);
//		 Inverters batt = new Inverters();
//		  batt.setMake("bbb_ccc");
//		 bt.setInverters(batt);
//		 list2.add(bt);
//		 scenario.get(7).add(list2);
//	     //Result excpected
//		 ArrayList<SearchInvertersResult> listExp2 = new ArrayList<SearchInvertersResult>();
//		 SearchInvertersResult scBt2 = new SearchInvertersResult();
//		 scBt2.setIsFav(true);
//		 listExp2.add(scBt2);
//		 scenario.get(7).add(listExp2);
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 SearchInverterRequest sc2 = new SearchInverterRequest();
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
//		 List<InverterLibraryEntity> list3 = new ArrayList<InverterLibraryEntity>();
//		 list3.add(null);
//		 list3.add(new InverterLibraryEntity());
//		 InverterLibraryEntity bt2 = new InverterLibraryEntity();
//		 bt2.setId(12358);
//		 Inverters batt2 = new Inverters();
//		  batt2.setMake("bbb_ccc");
//		  batt2.setId(12358);
//		 bt2.setInverters(batt2);
//		 list3.add(bt2);
//		 scenario.get(9).add(list3);
//	     //Result excpected
//		 ArrayList<SearchInvertersResult> listExp3 = new ArrayList<SearchInvertersResult>();
//		 SearchInvertersResult scBt3 = new SearchInvertersResult();
//		 scBt3.setIsFav(true);
//		 scBt3.setId(12358);
//		 scBt3.setMake("bbb_ccc");
//		 scBt3.setIsDeleted(false);
//		 listExp3.add(scBt3);
//		 scenario.get(9).add(listExp3);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(10).add(new SearchInverterRequest());
//		 scenario.get(10).add(null);
//		 scenario.get(10).add(null);
//		 scenario.get(10).add(null);
//		 //Result of the query1
//		 scenario.get(10).add(null);
//		 //Result of the query2
//		 scenario.get(10).add(null);
//	     //Result excpected
//		 scenario.get(10).add(new ArrayList<SearchInvertersResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(11) .add(new SearchInverterRequest());
//		 scenario.get(11).add("");
//		 scenario.get(11).add("");
//		 scenario.get(11).add("");
//		 //Result of the query1
//		 scenario.get(11).add(null);
//		 //Result of the query2
//		 scenario.get(11).add(null);
//	     //Result excpected
//		 scenario.get(11).add(new ArrayList<SearchInvertersResult>());
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(12) .add(new SearchInverterRequest());
//		 scenario.get(12).add("123");
//		 scenario.get(12).add("123");
//		 scenario.get(12).add("123");
//		 //Result of the query1
//		 scenario.get(12).add(null);
//		 //Result of the query2
//		 scenario.get(12).add(null);
//	     //Result excpected
//		 scenario.get(12).add(new ArrayList<SearchInvertersResult>());
//		 
//			
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(13) .add(new SearchInverterRequest());
//		 scenario.get(13).add("123");
//		 scenario.get(13).add("123");
//		 scenario.get(13).add("123");
//		 //Result of the query1
//		 scenario.get(13).add(list);
//		 //Result of the query2
//		 scenario.get(13).add(list2);
//	     //Result excpected
//		 ArrayList<SearchInvertersResult> listExp4 = new ArrayList<SearchInvertersResult>();
//		 SearchInvertersResult sc5 = new SearchInvertersResult();
//		 sc5.setIsFav(true);
//		 listExp4.add(sc5);
//		 SearchInvertersResult sc6 = new SearchInvertersResult();
//		 sc6.setIsFav(false);
//		 sc6.setEroneousContent("");
//		 sc6.setId(458999);
//		 listExp4.add(sc6);
//		 scenario.get(13).add(listExp4);
//		 
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("searchInverters [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//
//			ArrayList<SearchInvertersResult> result = ((ArrayList<SearchInvertersResult>) getInverterLibraryService
//					.searchInverters((SearchInverterRequest) scenario.get(i).get(0), (String) scenario.get(i).get(1),
//							(String) scenario.get(i).get(2), (String) scenario.get(i).get(3)));
//
//		}
//	}
//
//	@Test
//	public void testsendCorrectionInverterRequest() {
//
//
//
//		Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Inverters u Where u.id = :p1 " )).thenReturn(mockedQuery1);
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
//			 InverterCorrectionReq acComSLC = new InverterCorrectionReq();
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
//			 InverterCorrectionReq acComSLC1= new InverterCorrectionReq();
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
//			 InverterCorrectionReq acComSLC2= new InverterCorrectionReq();
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
//			 InverterCorrectionReq acComSLC4= new InverterCorrectionReq();
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
//			 Inverters lib = new Inverters();
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
//	              getInverterLibraryService.sendCorrectionInverterRequest( (InverterCorrectionReq) scenario.get(i).get(0));
//
//			 }
//			 
//	
//	
//	
//	}
//	
//	@Test
//	public void testgetNumberSearchInv() {
//
//
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(InverterLibraryEntity.class)).thenReturn(root);
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
//		 SearchInverterRequest sc = new SearchInverterRequest();
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
//		 scenario.get(3).add(new SearchInverterRequest());
//		 scenario.get(3).add(null);
//		 //Result of query1
//		 scenario.get(3).add(8L); 
//		//Excpected Result
//		 scenario.get(3).add(8L);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getNumberSearchFlashing [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//	        getInverterLibraryService.getNumberSearchInv((SearchInverterRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		 }
//   
//	
//		}
//	
//	@Test
//	public void testgetInverterLibrary() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Inverters u order by u.make")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//		 GetInverterLibraryService getInverterLibraryService2 = Mockito.spy(getInverterLibraryService);
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
//				 scenario.get(0).add(new ArrayList<InverterFavRequest>());
//				 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(1).add("");
//				 scenario.get(1).add("");
//				 scenario.get(1).add("");
//				 //Result of query1
//				 scenario.get(1).add(null); 
//				//Excpected Result
//				 scenario.get(1).add(new ArrayList<InverterFavRequest>());
//				 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 //Result of query1
//				 scenario.get(2).add(null); 
//				//Excpected Result
//				 scenario.get(2).add(new ArrayList<InverterFavRequest>());
//				 
//				 scenario.add(new ArrayList<Object>());
//				 //List of the parameter
//				 scenario.get(3).add("123");
//				 scenario.get(3).add("123");
//				 scenario.get(3).add("123");
//				 //Result of query1
//				 List<Inverters> list = new ArrayList<Inverters>();
//				 list.add(null);
//				 list.add(new Inverters());
//				 scenario.get(3).add(list); 
//				//Excpected Result
//				 List<InverterFavRequest> listExp = new ArrayList<InverterFavRequest>();
//				 InverterFavRequest fl= new InverterFavRequest();
//				 fl.setIsFav("false");
//				 listExp.add(new InverterFavRequest());
//				 listExp.add(fl);
//				 scenario.get(3).add(listExp);
//				 
//				 for(int i=0;i<scenario.size();i++) {
//					 System.out.println("getInverterLibrary [ "+i+" ]");
//		            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//		             ArrayList<InverterFavRequest> result = ((ArrayList<InverterFavRequest>) getInverterLibraryService2.getInverterLibrary((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2)));
//			         Mockito.doReturn(new ArrayList<Integer>()).when(getInverterLibraryService2).getInverterFavorite(Mockito.any());
//
//
//				 }
//	
//	}
//
//	@Test
//	public void testgetInverterFavorite() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from InverterLibraryEntity u WHERE u.authentificationEntity.id = :p1 "))
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
//		scenario.get(3).add(new ArrayList<InverterLibraryEntity>());
//		// Result excpected
//		scenario.get(3).add(new ArrayList<Integer>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		// Result of the query1
//		ArrayList<InverterLibraryEntity> list = new ArrayList<InverterLibraryEntity>();
//		list.add(null);
//		list.add(new InverterLibraryEntity());
//		scenario.get(4).add(list);
//		// Result excpected
//		scenario.get(4).add(new ArrayList<Integer>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("123");
//		// Result of the query1
//		ArrayList<InverterLibraryEntity> list2 = new ArrayList<InverterLibraryEntity>();
//		InverterLibraryEntity dc = new InverterLibraryEntity();
//		dc.setInverters(new Inverters());
//		list2.add(dc);
//		InverterLibraryEntity dc2 = new InverterLibraryEntity();
//		Inverters dcEnt = new Inverters();
//		dcEnt.setId(123);
//		dc2.setInverters(dcEnt);
//		list2.add(dc2);
//		scenario.get(5).add(list2);
//		// Result excpected
//		ArrayList<Integer> rlt = new ArrayList<Integer>();
//		rlt.add(0);
//		rlt.add(123);
//		scenario.get(5).add(rlt);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getInverterFavorite [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			
//					getInverterLibraryService.getInverterFavorite((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testaddInverterFavorite() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from Inverters u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add("");
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add("123");
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		scenario.get(4).add(new Inverters());
//		// Result excpected
//		scenario.get(4).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(null);
//		scenario.get(5).add("123");
//		scenario.get(5).add("123");
//		// Result of the query1
//		scenario.get(5).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(5).add(new Inverters());
//		// Result excpected
//		scenario.get(5).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addLeasePPAMeterFavorite [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			 getInverterLibraryService.addInverterFavorite(
//					(String) scenario.get(i).get(0), (String) scenario.get(i).get(1), (String) scenario.get(i).get(2));
//
//		}
//
//	}
//	
//	@Test
//	public void testremoveInverterFavorite() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from InverterLibraryEntity u WHERE u.authentificationEntity.id = :p1 and u.inverters.id = :p2"))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add("");
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add("123");
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		scenario.get(4).add(new InverterLibraryEntity());
//		// Result excpected
//		scenario.get(4).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(null);
//		scenario.get(5).add("123");
//		scenario.get(5).add("123");
//		// Result of the query1
//		scenario.get(5).add(new AuthentificationEntity());
//		// Result of the query2
//		InverterLibraryEntity bt = new InverterLibraryEntity();
//		bt.setInverters(new Inverters());
//		scenario.get(5).add(bt);
//		// Result excpected
//		scenario.get(5).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("removeInverterFavorite [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			getInverterLibraryService.removeInverterFavorite(
//					(String) scenario.get(i).get(0), (String) scenario.get(i).get(1), (String) scenario.get(i).get(2));
//
//		}
//
//	}
//	
//	@Test
//	public void testgetContractorInverterFav() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from Inverters u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		GetInverterLibraryService getInverterLibraryService2 = Mockito.spy(getInverterLibraryService);
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
//		ArrayList<Inverters> listExp = new ArrayList<Inverters>();
//		listExp.add(null);
//		listExp.add(null);
//		listExp.add(null);
//		scenario.get(1).add(listExp);
//		// Result of the function getBatteryFavorite
//		scenario.get(1).add(batteryFavID);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getContractorLeasePPAMeterFav [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			Mockito.doReturn(scenario.get(i).get(3)).when(getInverterLibraryService2)
//					.getInverterFavorite(Mockito.any());
//
//			ArrayList<Inverters> result = ((ArrayList<Inverters>) getInverterLibraryService2
//					.getContractorInverterFav((String) scenario.get(i).get(0)));
//
//		}
//
//	}
//	
//	@Test
//	public void testaddInverter() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.model , u.make from Inverters u where u.make=:p1 and u.model =:p2"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Result of query2
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(new InverterFavRequest());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(new InverterFavRequest());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(new ArrayList<Inverters>());
//		// Result of query2
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("");
//		scenario.get(3).add(new InverterFavRequest());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(new ArrayList<Inverters>());
//		// Result of query2
//		scenario.get(3).add(null);
//		// Excpected Result
//		scenario.get(3).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add(new InverterFavRequest());
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of query1
//		scenario.get(4).add(new ArrayList<Inverters>());
//		// Result of query2
//		scenario.get(4).add(null);
//		// Excpected Result
//		scenario.get(4).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("123");
//		scenario.get(5).add(new InverterFavRequest());
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		// Result of query1
//		scenario.get(5).add(new ArrayList<Inverters>());
//		// Result of query2
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setRoleEntity(new RoleEntity());
//		scenario.get(5).add(auth);
//		// Excpected Result
//		scenario.get(5).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(6).add("123");
//		scenario.get(6).add(new InverterFavRequest());
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		// Result of query1
//		ArrayList<Inverters> list = new ArrayList<Inverters>();
//		list.add(null);
//		list.add(new Inverters());
//		scenario.get(6).add(list);
//		// Result of query2
//		scenario.get(6).add(auth);
//		// Excpected Result
//		scenario.get(6).add("Inverter already exists in data sources");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addLeasePPAMeter [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(6));
//			
//					getInverterLibraryService.addInverter((String) scenario.get(i).get(0),
//							(InverterFavRequest) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3), (String) scenario.get(i).get(4));
//
//		}
//
//	}
//	
//	@Test
//	public void testeditInverter() {
//
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Inverters u WHERE u.make = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 GetInverterLibraryService getInverterLibraryService2 = Mockito.spy(getInverterLibraryService);
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
//		 scenario.get(1).add(new InverterFavRequest());
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
//		 scenario.get(2).add(new InverterFavRequest());
//		 scenario.get(2).add(null);
//		 //Result of query1
//		 scenario.get(2).add(new ArrayList<Inverters>()); 
//		//Excpected Result
//		 scenario.get(2).add("success");
//		//Result of query1 singleResult
//		 scenario.get(2).add(null);  
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(new InverterFavRequest());
//		 scenario.get(3).add(null);
//		 //Result of query1
//		 ArrayList<Inverters> list = new ArrayList<Inverters>();
//		 list.add(null);
//		 list.add(new Inverters());
//		 scenario.get(3).add(list); 
//		//Excpected Result
//		 scenario.get(3).add("success");
//		//Result of query1 singleResult
//		 scenario.get(3).add(null); 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add(new InverterFavRequest());
//		 scenario.get(4).add(null);
//		 //Result of query1
//		 scenario.get(4).add(list); 
//		//Excpected Result
//		 scenario.get(4).add("success");
//		//Result of query1 singleResult
//		 scenario.get(4).add(new Inverters()); 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(5).add(new InverterFavRequest());
//		 scenario.get(5).add(null);
//		 //Result of query1
//		 scenario.get(5).add(list); 
//		//Excpected Result
//		 scenario.get(5).add("exist");
//		//Result of query1 singleResult
//		 Inverters bc = new Inverters();
//		 bc.setId(12589);
//		 scenario.get(5).add(bc); 
//		 
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("editFlashing [ "+i+" ]");
//        when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//        Mockito.doReturn("success").when(getInverterLibraryService2).editInverterFunction(Mockito.any(),Mockito.any());
//
//        getInverterLibraryService2.editInverter((InverterFavRequest)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//	
//	
//	}
//	
//	@Test
//	public void testeditInverterFunction() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from Inverters u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u FROM PermitArraysEntity u WHERE u.inverterModel = :p1 OR u.secondInverterModel = :p1 OR u.thirdInverterModel = :p1 OR u.fourthInverterModel = :p1 OR u.fifthInverterModel = :p1"))
//						.thenReturn(mockedQuery2);
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
//		scenario.get(1).add(new InverterFavRequest());
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
//		scenario.get(2).add(new InverterFavRequest());
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(new Inverters());
//		// Result of query2
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new InverterFavRequest());
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(new Inverters());
//		// Result of query2
//		List<PermitArraysEntity> list = new ArrayList<PermitArraysEntity>();
//		list.add(null);
//		list.add(new PermitArraysEntity());
//		scenario.get(3).add(list);
//		// Excpected Result
//		scenario.get(3).add("success");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editInverterFunction [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			getInverterLibraryService.editInverterFunction(
//					(InverterFavRequest) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//	
//	@Test
//	public void testeditInverterNotification() {
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
//			 System.out.println("editInverterNotification [ "+i+" ]");
//	        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//	        getInverterLibraryService.editInverterNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }	
//	
//	
//	}
//	
//	@Test
//	public void testaddInverterNotification() {
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
//			 System.out.println("addInverterNotification [ "+i+" ]");
//	        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//	        getInverterLibraryService.addInverterNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }	
//	
//	}
//	
//	@Test
//	public void testinverterLibraryActived() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Inverters i Where i.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Inverters u WHERE u.make = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
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
//		 Inverters ac1 = new Inverters();
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
//		 Inverters acCombiner = new Inverters();
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
//		 scenario.get(6).add(new ArrayList<Inverters>());
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
//		 ArrayList<Inverters> listAcCom= new ArrayList<Inverters>();
//		 listAcCom.add(new Inverters());
//		 scenario.get(7).add(listAcCom);
//		 //The result excpected
//		 scenario.get(7).add("exist");
//		 
//		
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("inverterLibraryActived [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			 getInverterLibraryService.inverterLibraryActived(123,(String)scenario.get(i).get(1));
//
//		 }
//	
//	
//	}
//	
//	@Test
//	public void testgetAllPermitOfInverterDeleted() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Inverters i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//					+ "p.inverterModel = :p1 or p.secondInverterModel = :p1 or p.thirdInverterModel = :p1 or p.fourthInverterModel = :p1 or p.fifthInverterModel = :p1")).thenReturn(mockedQuery2);
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
//		 scenario.get(1).add(new Inverters());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<PermitEntity>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(123);
//		 //Result of the query1
//		 Inverters bc = new Inverters();
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
//		 ArrayList<PermitArraysEntity> list = new ArrayList<PermitArraysEntity>();
//		 list.add(null);
//		 list.add(new PermitArraysEntity());
//		 scenario.get(3).add(list);
//	     //Result excpected
//		 scenario.get(3).add(new ArrayList<PermitEntity>());
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("getAllPermitOfInverterDeleted [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			  getInverterLibraryService.getAllPermitOfInverterDeleted((int)scenario.get(i).get(0));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testgetAllPermitOfInverterDeleted1() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Inverters i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//					+ "p.inverterModel = :p1 or p.secondInverterModel = :p1 or p.thirdInverterModel = :p1 or p.fourthInverterModel = :p1 or p.fifthInverterModel = :p1")).thenReturn(mockedQuery2);
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
//		 scenario.get(1).add(new Inverters());
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(null);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(123);
//		 //Result of the query1
//		 Inverters bc = new Inverters();
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
//		 ArrayList<PermitArraysEntity> list = new ArrayList<PermitArraysEntity>();
//		 list.add(null);
//		 list.add(new PermitArraysEntity());
//		 scenario.get(3).add(list);
//	     //Result excpected
//		 scenario.get(3).add(new ArrayList<PermitEntity>());
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("getAllPermitOfInverterDeleted1 [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			  getInverterLibraryService.getAllPermitOfInverterDeleted1((int)scenario.get(i).get(0));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testdeleteInverterLibrary() {
//
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT i from Inverters i Where i.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//					+ "p.permitEntity = :p1 and p.inverterModel = :p2")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from InverterLibraryEntity u WHERE u.inverters.id = :p2")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//		 
//		 Query mockedQuery4 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//					+ "p.permitEntity = :p1 and p.secondInverterModel = :p2 ")).thenReturn(mockedQuery4);
//		 when(mockedQuery4.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery4);
//		 
//		 Query mockedQuery5 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//					+ "p.permitEntity = :p1 and p.thirdInverterModel = :p2 ")).thenReturn(mockedQuery5);
//		 when(mockedQuery5.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery5);
//		 
//		 Query mockedQuery6 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//					+ "p.permitEntity = :p1 and p.fourthInverterModel = :p2 ")).thenReturn(mockedQuery6);
//		 when(mockedQuery6.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery6);
//		 
//		 
//		 Query mockedQuery7 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//					+ "p.permitEntity = :p1 and p.fifthInverterModel = :p2 ")).thenReturn(mockedQuery7);
//		 when(mockedQuery7.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery7);
//		 
//		 GetInverterLibraryService getInverterLibraryService2 = Mockito.spy(getInverterLibraryService);
//
//      ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		 //Result of the query4
//		 scenario.get(0).add(null);
//		 //Result of the query4 List
//		 scenario.get(0).add(null);
//		 //Result of the query5
//		 scenario.get(0).add(null);
//		 //Result of the query5 List
//		 scenario.get(0).add(null);
//		 //Result of the query6
//		 scenario.get(0).add(null);
//		 //Result of the query6 List
//		 scenario.get(0).add(null);
//		 //Result of the query7
//		 scenario.get(0).add(null);
//		 //Result of the query7 List
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
//		 //Result of the query4
//		 scenario.get(1).add(null);
//		 //Result of the query4 List
//		 scenario.get(1).add(null);
//		 //Result of the query5
//		 scenario.get(1).add(null);
//		 //Result of the query5 List
//		 scenario.get(1).add(null);
//		 //Result of the query6
//		 scenario.get(1).add(null);
//		 //Result of the query6 List
//		 scenario.get(1).add(null);
//		 //Result of the query7
//		 scenario.get(1).add(null);
//		 //Result of the query7 List
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
//		 //Result of the query4
//		 scenario.get(2).add(null);
//		 //Result of the query4 List
//		 scenario.get(2).add(null);
//		 //Result of the query5
//		 scenario.get(2).add(null);
//		 //Result of the query5 List
//		 scenario.get(2).add(null);
//		 //Result of the query6
//		 scenario.get(2).add(null);
//		 //Result of the query6 List
//		 scenario.get(2).add(null);
//		 //Result of the query7
//		 scenario.get(2).add(null);
//		 //Result of the query7 List
//		 scenario.get(2).add(null);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add(323);
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 scenario.get(3).add(new Inverters());
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
//		 //Result of the query4
//		 scenario.get(3).add(null);
//		 //Result of the query4 List
//		 scenario.get(3).add(null);
//		 //Result of the query5
//		 scenario.get(3).add(null);
//		 //Result of the query5 List
//		 scenario.get(3).add(null);
//		 //Result of the query6
//		 scenario.get(3).add(null);
//		 //Result of the query6 List
//		 scenario.get(3).add(null);
//		 //Result of the query7
//		 scenario.get(3).add(null);
//		 //Result of the query7 List
//		 scenario.get(3).add(null);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add(423);
//		 scenario.get(4).add(null);
//		 //Result of the query1
//		 scenario.get(4).add(new Inverters());
//		 //Result of the query2
//		 PermitArraysEntity prAdd = new PermitArraysEntity();
//		 prAdd.setPvModuleModEl("");
//		 scenario.get(4).add(prAdd);
//		 //Result of the query3
//		 scenario.get(4).add(null);
//	     //Result excpected
//		 scenario.get(4).add(true);
//		 //Result of the function getAllPermitOfBatteryDeleted1
//		 scenario.get(4).add(listFun);
//		 //Result of the query2 list
//		 ArrayList<PermitArraysEntity> listPrAdd = new ArrayList<PermitArraysEntity>();
//		 listPrAdd.add(prAdd);
//		 scenario.get(4).add(listPrAdd);
//		 //Result of the query4
//		 scenario.get(4).add(null);
//		 //Result of the query4 List
//		 scenario.get(4).add(null);
//		 //Result of the query5
//		 scenario.get(4).add(null);
//		 //Result of the query5 List
//		 scenario.get(4).add(null);
//		 //Result of the query6
//		 scenario.get(4).add(null);
//		 //Result of the query6 List
//		 scenario.get(4).add(null);
//		 //Result of the query7
//		 scenario.get(4).add(null);
//		 //Result of the query7 List
//		 scenario.get(4).add(null);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("deleteInverterLibrary [ "+i+" ]");
//        Mockito.doReturn((List)scenario.get(i).get(6)).when(getInverterLibraryService2).getAllPermitOfInverterDeleted1(Mockito.anyInt());
//
//        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//        when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//        when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(7));
//        when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(4));
//        getInverterLibraryService2.deleteInverterLibrary((int)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	
//		
//	
//	
//	}
//	
//	@Test
//	public void testgetUsersForFavList() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from InverterLibraryEntity u WHERE u.inverters.id = :p1"))
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
//		scenario.get(1).add(new ArrayList<InverterLibraryEntity>());
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
//		scenario.get(2).add(new ArrayList<InverterLibraryEntity>());
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
//		scenario.get(3).add(new ArrayList<InverterLibraryEntity>());
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
//		ArrayList<InverterLibraryEntity> list = new ArrayList<InverterLibraryEntity>();
//		list.add(null);
//		list.add(new InverterLibraryEntity());
//		InverterLibraryEntity ac = new InverterLibraryEntity();
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
//			getInverterLibraryService
//					.getUsersForFavList((Integer) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//	
//	@Test
//	public void testeditUsersFavoriteList() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from Inverters u WHERE u.id = :p1")).thenReturn(mockedQuery1);
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
//		scenario.get(3).add(new Inverters());
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
//		scenario.get(4).add(new Inverters());
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
//					getInverterLibraryService.editUsersFavoriteList((Integer) scenario.get(i).get(0),
//							(String[]) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3), (String) scenario.get(i).get(4));
//
//		}
//
//	}
//	
//}
