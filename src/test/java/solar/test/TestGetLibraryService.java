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
//import com.PlayGroundAdv.Solar.Entity.Cmodulev2;
//import com.PlayGroundAdv.Solar.Entity.Flashing;
//import com.PlayGroundAdv.Solar.Entity.FlashingFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.ModuleLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitArraysEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.model.FlashingCorrectionRequest;
//import com.PlayGroundAdv.Solar.model.FlashingFavRequest;
//import com.PlayGroundAdv.Solar.model.ModuleCorrectionModel;
//import com.PlayGroundAdv.Solar.model.ModuleFavRequest;
//import com.PlayGroundAdv.Solar.model.PermitResponsePrime;
//import com.PlayGroundAdv.Solar.model.SearchFlashingRequest;
//import com.PlayGroundAdv.Solar.model.SearchFlashingResult;
//import com.PlayGroundAdv.Solar.model.SearchModulResult;
//import com.PlayGroundAdv.Solar.model.SearchModuleRequest;
//import com.PlayGroundAdv.Solar.model.UsersEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetFlashingLibraryService;
//import com.PlayGroundAdv.Solar.Services.GetLibraryService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//
//public class TestGetLibraryService {
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
//	GetLibraryService getLibraryService = new GetLibraryService();
//	
//	
//    @Before
//	public void setupMock() {
//    	getLibraryService = new GetLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testgetNumberSearch() {
//
//
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(ModuleLibraryEntity.class)).thenReturn(root);
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
//		 SearchModuleRequest sc = new SearchModuleRequest();
//		 sc.setFavorite(true);
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
//		 scenario.get(3).add(new SearchModuleRequest());
//		 scenario.get(3).add(null);
//		 //Result of query1
//		 scenario.get(3).add(8L); 
//		//Excpected Result
//		 scenario.get(3).add(8L);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getNumberSearch [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//	        getLibraryService.getNumberSearch((SearchModuleRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		 }
//   
//	
//	
//	}
//
//	@Test
//	public void testsendCorrectionModuleRequest() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from Cmodulev2 u Where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(" SELECT u  FROM AuthentificationEntity u WHERE u.id = :p1  ")).thenReturn(mockedQuery2);
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
//		scenario.get(0).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		ModuleCorrectionModel acComSLC = new ModuleCorrectionModel();
//		acComSLC.setId(1255);
//		scenario.get(1).add(acComSLC);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		ModuleCorrectionModel acComSLC1 = new ModuleCorrectionModel();
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
//		ModuleCorrectionModel acComSLC2 = new ModuleCorrectionModel();
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
//		ModuleCorrectionModel acComSLC4 = new ModuleCorrectionModel();
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
//		Cmodulev2 lib = new Cmodulev2();
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
//		auth.setId(12333);
//		scenario.get(6).add(auth);
//		// Excpected Result
//		scenario.get(6).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("sendCorrectionModuleRequest [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			 getLibraryService
//					.sendCorrectionModuleRequest((ModuleCorrectionModel) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testsearchModule() {
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		Path battery = mock(Path.class);
//
//		when(criteriaBuilder.createQuery(SearchModulResult.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(ModuleLibraryEntity.class)).thenReturn(root);
//		when(criteriaQuery.from(Cmodulev2.class)).thenReturn(root);
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
//		 when(em.createQuery("SELECT u from ModuleLibraryEntity u WHERE u.authentificationEntity.id = :p1")).thenReturn(mockedQuery2);
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
//		 scenario.get(0).add(new ArrayList<SearchModulResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 SearchModuleRequest sc = new SearchModuleRequest();
//		 sc.setFavorite(true);
//		 scenario.get(1) .add(sc);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<SearchModulResult>());
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
//		 scenario.get(2).add(new ArrayList<SearchModulResult>());
//		 
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
//		 scenario.get(3).add(new ArrayList<SearchModulResult>());
//		 
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
//		 scenario.get(4).add(new ArrayList<SearchModulResult>());
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
//		 scenario.get(5).add(new ArrayList<SearchModulResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(6) .add(sc);
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 //Result of the query1
//		 List<SearchModulResult> list = new ArrayList<SearchModulResult>();
//		 list.add(null);
//		  SearchModulResult scMod =new SearchModulResult();
//		  scMod.setId(0);
//		 list.add(scMod);
//		 SearchModulResult scBat = new SearchModulResult();
//		 scBat.setEroneousContent("");
//		 scBat.setId(458999);
//		 list.add(scBat);
//		 scenario.get(6).add(list);
//		 //Result of the query2
//		 scenario.get(6).add(null);
//	     //Result excpected
//		 ArrayList<SearchModulResult> listExp = new ArrayList<SearchModulResult>();
//		 SearchModulResult exp1 = new SearchModulResult();
//		 exp1.setIsFav(false);
//		 listExp.add(exp1);
//		 SearchModulResult exp2 = new SearchModulResult();
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
//		 List<ModuleLibraryEntity> list2 = new ArrayList<ModuleLibraryEntity>();
//		 list2.add(null);
//		 list2.add(new ModuleLibraryEntity());
//		 ModuleLibraryEntity bt = new ModuleLibraryEntity();
//		 bt.setId(12358);
//		 Cmodulev2 batt = new Cmodulev2();
//		  batt.setMake("bbb_ccc");
//		 bt.setCmodulev2(batt);
//		 list2.add(bt);
//		 scenario.get(7).add(list2);
//	     //Result excpected
//		 ArrayList<SearchModulResult> listExp2 = new ArrayList<SearchModulResult>();
//		 SearchModulResult scBt2 = new SearchModulResult();
//		 scBt2.setIsFav(true);
//		 listExp2.add(scBt2);
//		 scenario.get(7).add(listExp2);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 SearchModuleRequest sc2 = new SearchModuleRequest();
//		 sc2.setFavorite(true);
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
//		 List<ModuleLibraryEntity> list3 = new ArrayList<ModuleLibraryEntity>();
//		 list3.add(null);
//		 list3.add(new ModuleLibraryEntity());
//		 ModuleLibraryEntity bt2 = new ModuleLibraryEntity();
//		 bt2.setId(12358);
//		 Cmodulev2 batt2 = new Cmodulev2();
//		  batt2.setMake("bbb_ccc");
//		  batt2.setId(12358);
//		 bt2.setCmodulev2(batt2);
//		 list3.add(bt2);
//		 scenario.get(9).add(list3);
//	     //Result excpected
//		 ArrayList<SearchModulResult> listExp3 = new ArrayList<SearchModulResult>();
//		 SearchModulResult scBt3 = new SearchModulResult();
//		 scBt3.setIsFav(true);
//		 scBt3.setId(12358);
//		 scBt3.setMake("bbb_ccc");
//		 scBt3.setIsDeleted(false);
//		 listExp3.add(scBt3);
//		 scenario.get(9).add(listExp3);
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(10).add(new SearchModuleRequest());
//		 scenario.get(10).add(null);
//		 scenario.get(10).add(null);
//		 scenario.get(10).add(null);
//		 //Result of the query1
//		 scenario.get(10).add(null);
//		 //Result of the query2
//		 scenario.get(10).add(null);
//	     //Result excpected
//		 scenario.get(10).add(new ArrayList<SearchModulResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(11).add(new SearchModuleRequest());
//		 scenario.get(11).add("");
//		 scenario.get(11).add("");
//		 scenario.get(11).add("");
//		 //Result of the query1
//		 scenario.get(11).add(null);
//		 //Result of the query2
//		 scenario.get(11).add(null);
//	     //Result excpected
//		 scenario.get(11).add(new ArrayList<SearchModulResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(12) .add(new SearchModuleRequest());
//		 scenario.get(12).add("123");
//		 scenario.get(12).add("123");
//		 scenario.get(12).add("123");
//		 //Result of the query1
//		 scenario.get(12).add(null);
//		 //Result of the query2
//		 scenario.get(12).add(null);
//	     //Result excpected
//		 scenario.get(12).add(new ArrayList<SearchModulResult>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(13) .add(new SearchModuleRequest());
//		 scenario.get(13).add("123");
//		 scenario.get(13).add("123");
//		 scenario.get(13).add("123");
//		 //Result of the query1
//		 scenario.get(13).add(list);
//		 //Result of the query2
//		 scenario.get(13).add(list2);
//	     //Result excpected
//		 ArrayList<SearchModulResult> listExp4 = new ArrayList<SearchModulResult>();
//		 SearchModulResult sc5 = new SearchModulResult();
//		 sc5.setIsFav(true);
//		 listExp4.add(sc5);
//		 SearchModulResult sc6 = new SearchModulResult();
//		 sc6.setIsFav(false);
//		 sc6.setEroneousContent("");
//		 sc6.setId(458999);
//		 listExp4.add(sc6);
//		 scenario.get(13).add(listExp4);
//		 
//	        for(int i=0;i<scenario.size();i++) {
//				 System.out.println("searchModule [ "+i+" ]");
//				 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//				 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//
//	            ArrayList<SearchModulResult> result = ((ArrayList<SearchModulResult>) getLibraryService.searchModule((SearchModuleRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3)));
//				 
//				
//			 }
//		 
//		 
//	}
//
//	@Test
//	public void testgetModuleLibrary() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from Cmodulev2 u order by u.make")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//		when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//		GetLibraryService getLibraryService2 = Mockito.spy(getLibraryService);
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
//		scenario.get(0).add(new ArrayList<SearchModulResult>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		// Result of query1
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add(new ArrayList<SearchModulResult>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add("123");
//		scenario.get(2).add("123");
//		// Result of query1
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add(new ArrayList<SearchModulResult>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add("123");
//		scenario.get(3).add("123");
//		// Result of query1
//		List<Cmodulev2> list = new ArrayList<Cmodulev2>();
//		list.add(null);
//		list.add(new Cmodulev2());
//		scenario.get(3).add(list);
//		// Excpected Result
//		List<SearchModulResult> listExp = new ArrayList<SearchModulResult>();
//		SearchModulResult fl = new SearchModulResult();
//		fl.setIsFav(false);
//		listExp.add(new SearchModulResult());
//		listExp.add(fl);
//		scenario.get(3).add(listExp);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getModuleLibrary [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			ArrayList<SearchModulResult> result = ((ArrayList<SearchModulResult>) getLibraryService2
//					.getModuleLibrary((String) scenario.get(i).get(0), (String) scenario.get(i).get(1),
//							(String) scenario.get(i).get(2)));
//			Mockito.doReturn(new ArrayList<Integer>()).when(getLibraryService2)
//					.getModuleFavorite(Mockito.any());
//
//
//		}
//
//	}
//
//	@Test
//	public void testgetModuleFavorite() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from ModuleLibraryEntity u WHERE u.authentificationEntity.id = :p1 "))
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
//		scenario.get(3).add(new ArrayList<ModuleLibraryEntity>());
//		// Result excpected
//		scenario.get(3).add(new ArrayList<Integer>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		// Result of the query1
//		ArrayList<ModuleLibraryEntity> list = new ArrayList<ModuleLibraryEntity>();
//		list.add(null);
//		list.add(new ModuleLibraryEntity());
//		scenario.get(4).add(list);
//		// Result excpected
//		scenario.get(4).add(new ArrayList<Integer>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("123");
//		// Result of the query1
//		ArrayList<ModuleLibraryEntity> list2 = new ArrayList<ModuleLibraryEntity>();
//		ModuleLibraryEntity dc = new ModuleLibraryEntity();
//		dc.setCmodulev2(new Cmodulev2());
//		list2.add(dc);
//		ModuleLibraryEntity dc2 = new ModuleLibraryEntity();
//		Cmodulev2 dcEnt = new Cmodulev2();
//		dcEnt.setId(123);
//		dc2.setCmodulev2(dcEnt);
//		list2.add(dc2);
//		scenario.get(5).add(list2);
//		// Result excpected
//		ArrayList<Integer> rlt = new ArrayList<Integer>();
//		rlt.add(0);
//		rlt.add(123);
//		scenario.get(5).add(rlt);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getModuleFavorite [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			
//					getLibraryService.getModuleFavorite((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testaddModuleFavorite() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Cmodulev2 u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
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
//		 scenario.get(2).add("error");
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
//		 scenario.get(3).add("error");
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add(null);
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 //Result of the query1
//		 scenario.get(4).add(null);
//		 //Result of the query2
//		 scenario.get(4).add(new Cmodulev2());
//	     //Result excpected
//		 scenario.get(4).add("error");
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(5).add(null);
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 //Result of the query1
//		 scenario.get(5).add(new AuthentificationEntity());
//		 //Result of the query2
//		 scenario.get(5).add(new Cmodulev2());
//	     //Result excpected
//		 scenario.get(5).add("Done");
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("addModuleFavorite [ "+i+" ]");
//          when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//          when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//          getLibraryService.addModuleFavorite((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	
//	
//	}
//
//	@Test
//	public void testremoveModuleFavorite() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from ModuleLibraryEntity u WHERE u.authentificationEntity.id = :p1 and u.cmodulev2.id = :p2"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from Cmodulev2 u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
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
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add("1222");
//		scenario.get(2).add("1225");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add("1222");
//		scenario.get(3).add("1225");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(new Cmodulev2());
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add("1222");
//		scenario.get(4).add("1225");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		scenario.get(4).add(new Cmodulev2());
//		// Result of the query3
//		scenario.get(4).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(4).add("Done");
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("removeModuleFavorite [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			 getLibraryService.removeModuleFavorite(
//					(String) scenario.get(i).get(0), (String) scenario.get(i).get(1), (String) scenario.get(i).get(2));
//
//		}
//
//	}
//
//
//	@Test
//	public void testtestModuleFav() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u "
//					+ " from PermitEntity u "
//					+ " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Cmodulev2 u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 GetLibraryService getLibraryService2 = Mockito.spy(getLibraryService);
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
//			 Cmodulev2 bt = new Cmodulev2();
//			 bt.setMake("aa");
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
//				 System.out.println("testModuleFav [ "+i+" ]");
//				 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//				 when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//		         Mockito.doReturn(scenario.get(i).get(5)).when(getLibraryService2).getModuleFavorite(Mockito.any());
//                getLibraryService2.testModuleFav((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//			 }
//	
//	}
//
//	@Test
//	public void testaddModule() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.model , u.make from Cmodulev2 u where u.make=:p1 and u.model =:p2")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u.authentificationEntity from PermitEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//		
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Result of query2
//		scenario.get(0).add(null);
//		// Result of query3
//		scenario.get(0).add(null);
//		// Result expected
//		scenario.get(0).add(new Cmodulev2());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(new ModuleFavRequest());
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Result of query3
//		scenario.get(1).add(null);
//		// Result expected
//		scenario.get(1).add(new Cmodulev2());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("");
//		scenario.get(2).add(new ModuleFavRequest());
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(null);
//		// Result of query2
//		scenario.get(2).add(null);
//		// Result of query3
//		scenario.get(2).add(null);
//		// Result expected
//		scenario.get(2).add(new Cmodulev2()); 
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add(new ModuleFavRequest());
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(null);
//		// Result of query2
//		scenario.get(3).add(null);
//		// Result of query3
//		scenario.get(3).add(null);
//		// Result expected
//		scenario.get(3).add(new Cmodulev2()); 
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add(new ModuleFavRequest());
//		scenario.get(4).add("");
//		// Result of query1
//		scenario.get(4).add(null);
//		// Result of query2
//		scenario.get(4).add(null);
//		// Result of query3
//		scenario.get(4).add(null);
//		// Result expected
//		scenario.get(4).add(new Cmodulev2()); 
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add("123");
//		scenario.get(5).add(new ModuleFavRequest());
//		scenario.get(5).add("1234");
//		// Result of query1
//		scenario.get(5).add(null);
//		// Result of query2
//		scenario.get(5).add(null);
//		// Result of query3
//		scenario.get(5).add(null);
//		// Result expected
//		scenario.get(5).add(new Cmodulev2()); 
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(6).add("123");
//		scenario.get(6).add(new ModuleFavRequest());
//		scenario.get(6).add("1234");
//		// Result of query1
//		ArrayList<Cmodulev2> list = new ArrayList<Cmodulev2>();
//	
//		scenario.get(6).add(list);
//		// Result of query2
//		scenario.get(6).add(null);
//		// Result of query3
//		scenario.get(6).add(null);
//		// Result expected
//		Date d = new Date();
//		SimpleDateFormat FormattedDATE = new SimpleDateFormat("MM/dd/yyyy");
//		String updatedDate = FormattedDATE.format(d);
//		Cmodulev2 module = new Cmodulev2();
//		module.setDate(updatedDate);
//		scenario.get(6).add(module);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(7).add("123");
//		scenario.get(7).add(new ModuleFavRequest());
//		scenario.get(7).add("1234");
//		// Result of query1
//		scenario.get(7).add(list);
//		// Result of query2
//		AuthentificationEntity auth = new AuthentificationEntity();
//        auth.setRoleEntity(new RoleEntity());
//		scenario.get(7).add(auth);
//		// Result of query3
//		scenario.get(7).add(null);
//		// Result expected
//		Cmodulev2 module2 = new Cmodulev2();
//		module2.setHasSuperUserEdit(false);
//		module2.setDate(updatedDate);
//		Date addDate = new Date();
//		module2.setAddDate(addDate);
//		scenario.get(7).add(module2);
//		
//		
//		
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addModule [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			 getLibraryService
//					.addModule((String) scenario.get(i).get(0),
//					(ModuleFavRequest) scenario.get(i).get(1), (String) scenario.get(i).get(2));
//
//		}
//
//	}
//
//	@Test
//	public void testgetAllCellTechnologies() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.technology from Cmodulev2 u GROUP BY u.technology ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of query1
//		scenario.get(0).add(null);
//		// Result expected
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// Result of query1
//		ArrayList<String> list =new ArrayList<String>();
//		list.add(null);
//		list.add("");
//		scenario.get(1).add(list);
//		// Result expected
//		scenario.get(1).add(list);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getSuperUserFlashingFav [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//
//			ArrayList<String> result = ((ArrayList<String>) getLibraryService.getAllCellTechnologies());
//		}
//	}
//
//	@Test
//	public void testeditModule() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from Cmodulev2 u WHERE u.make = :p1 AND u.model = :p2 AND u.isDeleted=false"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		GetLibraryService getLibraryService2 = Mockito.spy(getLibraryService);
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
//		scenario.get(1).add(new ModuleFavRequest());
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
//		scenario.get(2).add(new ModuleFavRequest());
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(new ArrayList<Cmodulev2>());
//		// Excpected Result
//		scenario.get(2).add("success");
//		// Result of query1 singleResult
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new ModuleFavRequest());
//		scenario.get(3).add(null);
//		// Result of query1
//		ArrayList<Cmodulev2> list = new ArrayList<Cmodulev2>();
//		list.add(null);
//		list.add(new Cmodulev2());
//		scenario.get(3).add(list);
//		// Excpected Result
//		scenario.get(3).add("success");
//		// Result of query1 singleResult
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(new ModuleFavRequest());
//		scenario.get(4).add(null);
//		// Result of query1
//		scenario.get(4).add(list);
//		// Excpected Result
//		scenario.get(4).add("fail");
//		// Result of query1 singleResult
//		scenario.get(4).add(new Cmodulev2());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(new ModuleFavRequest());
//		scenario.get(5).add(null);
//		// Result of query1
//		scenario.get(5).add(list);
//		// Excpected Result
//		scenario.get(5).add("fail");
//		// Result of query1 singleResult
//		Cmodulev2 bc = new Cmodulev2();
//		bc.setId(12589);
//		scenario.get(5).add(bc);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editFlashing [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			Mockito.doReturn("success").when(getLibraryService2).editModuleFunction(Mockito.any(), Mockito.any());
//
//			getLibraryService2
//					.editModule((ModuleFavRequest) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testeditModuleFunction() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from Cmodulev2 u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u FROM PermitArraysEntity u WHERE u.pvModuleModEl = :p1")).thenReturn(mockedQuery2);
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
//		scenario.get(1).add(new ModuleFavRequest());
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
//		scenario.get(2).add(new ModuleFavRequest());
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(new Cmodulev2());
//		// Result of query2
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("success");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new ModuleFavRequest());
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(new Cmodulev2());
//		// Result of query2
//		List<PermitArraysEntity> list = new ArrayList<PermitArraysEntity>();
//		list.add(null);
//		list.add(new PermitArraysEntity());
//		scenario.get(3).add(list);
//		// Excpected Result
//		scenario.get(3).add("success");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editFlashingFunction [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			getLibraryService
//					.editModuleFunction((ModuleFavRequest) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testeditModuleNotification() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
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
//		scenario.get(0).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("123");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(new AuthentificationEntity());
//		// Excpected Result
//		scenario.get(1).add("Success");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(null);
//		// Excpected Result
//		scenario.get(3).add("fail");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editModuleNotification [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			getLibraryService.editModuleNotification(
//					(String) scenario.get(i).get(0), (String) scenario.get(i).get(1), (String) scenario.get(i).get(2));
//
//		}
//
//	}
//
//	@Test
//	public void testaddModuleNotification() {
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
//			 System.out.println("addModuleNotification [ "+i+" ]");
//	        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//	       getLibraryService.addModuleNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }	
//	
//	}
//
//	@Test
//	public void testmoduleLibraryActived() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from Cmodulev2 u Where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from Cmodulev2 u WHERE u.make = :p1 AND u.model = :p2 AND u.isDeleted=false"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		// List of scenario
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of parameter possible
//		scenario.get(0).add(123);
//		scenario.get(0).add(null);
//
//		// The result of query1
//		scenario.get(0).add(null);
//		// The result of the query2
//		scenario.get(0).add(null);
//		// The result excpected
//		scenario.get(0).add("false");
//
//		scenario.add(new ArrayList());
//		// List of parameter possible
//		scenario.get(1).add(123);
//		scenario.get(1).add("");
//		// The result of query1
//		scenario.get(1).add(null);
//		// The result of the query2
//		scenario.get(1).add(null);
//		// The result excpected
//		scenario.get(1).add("false");
//
//		scenario.add(new ArrayList());
//		// List of parameter possible
//		scenario.get(2).add(123);
//		scenario.get(2).add("");
//
//		// The result of query1
//		scenario.get(2).add(null);
//		// The result of the query2
//		scenario.get(2).add(null);
//		// The result excpected
//		scenario.get(2).add("false");
//
//		scenario.add(new ArrayList());
//		// List of parameter possible
//		scenario.get(3).add(123);
//		scenario.get(3).add("22");
//
//		// The result of query1
//		Cmodulev2 ac1 = new Cmodulev2();
//		scenario.get(3).add(ac1);
//		// The result of the query2
//		scenario.get(3).add(null);
//		// The result excpected
//		scenario.get(3).add("false");
//
//		scenario.add(new ArrayList());
//		// List of parameter possible
//		scenario.get(4).add(123);
//		scenario.get(4).add("123");
//
//		// The result of query1
//		scenario.get(4).add(null);
//		// The result of the query2
//		scenario.get(4).add(null);
//		// The result excpected
//		scenario.get(4).add("false");
//
//		scenario.add(new ArrayList());
//		// List of parameter possible
//		scenario.get(5).add(123);
//		scenario.get(5).add("123");
//
//		// The result of query1
//		Cmodulev2 acCombiner = new Cmodulev2();
//		acCombiner.setId(123);
//		scenario.get(5).add(acCombiner);
//		// The result of the query2
//		scenario.get(5).add(null);
//		// The result excpected
//		scenario.get(5).add("false");
//
//		scenario.add(new ArrayList());
//		// List of parameter possible
//		scenario.get(6).add(123);
//		scenario.get(6).add("123");
//
//		// The result of query1
//		scenario.get(6).add(acCombiner);
//		// The result of the query2
//		scenario.get(6).add(new ArrayList<Cmodulev2>());
//		// The result excpected
//		scenario.get(6).add("true");
//
//		scenario.add(new ArrayList());
//		// List of parameter possible
//		scenario.get(7).add(123);
//		scenario.get(7).add("");
//
//		// The result of query1
//		scenario.get(7).add(acCombiner);
//		// The result of the query2
//		ArrayList<Cmodulev2> listAcCom = new ArrayList<Cmodulev2>();
//		listAcCom.add(new Cmodulev2());
//		scenario.get(7).add(listAcCom);
//		// The result excpected
//		scenario.get(7).add("exist");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("flashingLibraryActived [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			
//					getLibraryService.moduleLibraryActived(123, (String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testgetAllPermitOfModuleDeleted() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT c from Cmodulev2 c Where c.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT p from PermitArraysEntity p Where " + "p.pvModuleModEl = :p1 "))
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
//		scenario.get(0).add(new ArrayList<PermitResponsePrime>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(123);
//		// Result of the query1
//		scenario.get(1).add(new Cmodulev2());
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<PermitResponsePrime>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(123);
//		// Result of the query1
//		Cmodulev2 bc = new Cmodulev2();
//		bc.setId(2235);
//		scenario.get(2).add(bc);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<PermitResponsePrime>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(123);
//		// Result of the query1
//		scenario.get(3).add(bc);
//		// Result of the query1
//		ArrayList<PermitArraysEntity> list = new ArrayList<PermitArraysEntity>();
//		list.add(null);
//		list.add(new PermitArraysEntity());
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add(new ArrayList<PermitResponsePrime>());
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllPermitOfModuleDeleted [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			
//					getLibraryService.getAllPermitOfModuleDeleted((int) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testdeleteModuleLibrary() {
//
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Cmodulev2 u Where u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitArraysEntity p Where " + "p.permitEntity.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ModuleLibraryEntity u WHERE u.cmodulev2.id = :p2")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//		 
//		 GetLibraryService getLibraryService2 = Mockito.spy(getLibraryService);
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
//		 ArrayList<PermitResponsePrime> listFun = new ArrayList<PermitResponsePrime>();
//		 listFun.add(null);
//		 listFun.add(new PermitResponsePrime());
//		 PermitResponsePrime pr = new PermitResponsePrime();
//		 pr.setId(123);
//		 listFun.add(pr);
//		 scenario.get(2).add(listFun);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add(323);
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 scenario.get(3).add(new Cmodulev2());
//		 //Result of the query2
//		 scenario.get(3).add(null);
//		 //Result of the query3
//		 scenario.get(3).add(null);
//	     //Result excpected
//		 scenario.get(3).add(true);
//		 //Result of the function getAllPermitOfBatteryDeleted1
//		 scenario.get(3).add(listFun);
//		 
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add(423);
//		 scenario.get(4).add(null);
//		 //Result of the query1
//		 scenario.get(4).add(new Cmodulev2());
//		 //Result of the query2
//		 PermitArraysEntity prAdd = new PermitArraysEntity();
//		 prAdd.setPvModuleModEl("");
//		 scenario.get(4).add(prAdd);
//		 //Result of the query3
//		 List<ModuleLibraryEntity>  list3  = new ArrayList<ModuleLibraryEntity>();
//		 list3.add(null);
//		 list3.add(new ModuleLibraryEntity());
//		 scenario.get(4).add(list3);
//	     //Result excpected
//		 scenario.get(4).add(true);
//		 //Result of the function getAllPermitOfBatteryDeleted1
//		 scenario.get(4).add(listFun);
//		 
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deleteFlashingLibrary [ " + i + " ]");
//			Mockito.doReturn((List) scenario.get(i).get(6)).when(getLibraryService2)
//					.getAllPermitOfModuleDeleted(Mockito.anyInt());
//
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			getLibraryService2
//					.deleteModuleLibrary((int) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//	
//		
//	
//	}
//
//	@Test
//	public void testgetUsersForFavList() {
//
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from ModuleLibraryEntity u WHERE u.cmodulev2.id = :p1"))
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
//		scenario.get(1).add(new ArrayList<ModuleLibraryEntity>());
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
//		scenario.get(2).add(new ArrayList<ModuleLibraryEntity>());
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
//		scenario.get(3).add(new ArrayList<ModuleLibraryEntity>());
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
//		ArrayList<ModuleLibraryEntity> list = new ArrayList<ModuleLibraryEntity>();
//		list.add(null);
//		list.add(new ModuleLibraryEntity());
//		ModuleLibraryEntity ac = new ModuleLibraryEntity();
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
//			getLibraryService
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
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from Cmodulev2 u WHERE u.id = :p1")).thenReturn(mockedQuery1);
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
//		 scenario.get(3).add(new Cmodulev2());
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
//		 scenario.get(4).add(new Cmodulev2());
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
//           getLibraryService.editUsersFavoriteList((Integer)scenario.get(i).get(0),(String[])scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6),(String)scenario.get(i).get(7));
//
//		 }
//	
//	
//	}
//
//}
