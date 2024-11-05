//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
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
//import com.PlayGroundAdv.Solar.Entity.DCOptimizerEntity;
//import com.PlayGroundAdv.Solar.Entity.DCOptimizerFavoritEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitArraysEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.model.AddConverterModelRequest;
//import com.PlayGroundAdv.Solar.model.BatteryCorrectionRequest;
//import com.PlayGroundAdv.Solar.model.ConverterCorrectionModel;
//import com.PlayGroundAdv.Solar.model.ConverterFavModelResult;
//import com.PlayGroundAdv.Solar.model.ConverterModel;
//import com.PlayGroundAdv.Solar.model.ProjectForConvertModelResult;
//import com.PlayGroundAdv.Solar.model.SearchBatteryRequest;
//import com.PlayGroundAdv.Solar.model.SearchBatteryResult;
//import com.PlayGroundAdv.Solar.model.SearchConvertersRequest;
//import com.PlayGroundAdv.Solar.model.UsersEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.ConvertersManagementService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.SsheetLibraryService;
//
//public class TestConvertersManagementService {
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
//	ConvertersManagementService convertersManagementService = new ConvertersManagementService();
//	
//	
//    @Before
//	public void setupMock() {
//    	convertersManagementService = new ConvertersManagementService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//
//	@Test
//	public void testgetNbrConvertes() {
//		Path battery = mock(Path.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(DCOptimizerFavoritEntity.class)).thenReturn(root);
//		when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//		when(root.get(Mockito.anyString())).thenReturn(battery);
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(" SELECT COUNT(u) FROM DCOptimizerEntity u WHERE u.isDeleted = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add(null);
//		// Boolean parameter
//		scenario.get(0).add(null);
//		// The result of Query2
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		SearchConvertersRequest sc = new SearchConvertersRequest();
//		sc.setFavorite(true);
//		scenario.get(1).add(sc);
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add(-5L);
//		// Boolean parameter
//		scenario.get(1).add(true);
//		// The result of Query2
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(sc);
//		scenario.get(2).add("");
//		// Result of query1
//		scenario.get(2).add(8L);
//		// Excpected Result
//		scenario.get(2).add(-5L);
//		// Boolean parameter
//		scenario.get(2).add(true);
//		// The result of Query2
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(sc);
//		scenario.get(3).add("1258");
//		// Result of query1
//		scenario.get(3).add(8L);
//		// Excpected Result
//		scenario.get(3).add(8L);
//		// Boolean parameter
//		scenario.get(3).add(true);
//		// The result of Query2
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of query1
//		scenario.get(4).add(null);
//		// Excpected Result
//		scenario.get(4).add(8L);
//		// Boolean parameter
//		scenario.get(4).add(null);
//		// The result of Query2
//		scenario.get(4).add(8L);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getNumberSearchBattery [ " + i + " ]" + scenario.get(i).get(3));
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			
//					convertersManagementService.getNbrConvertes((SearchConvertersRequest) scenario.get(i).get(0),
//							(String) scenario.get(i).get(1), (Boolean) scenario.get(i).get(4));
//		}
//
//	}
//	
//	@Test
//	public void testgetConverters() {
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		Path battery = mock(Path.class);
//
//		when(criteriaBuilder.createQuery(DCOptimizerEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(DCOptimizerFavoritEntity.class)).thenReturn(root);
//		when(criteriaQuery.from(DCOptimizerEntity.class)).thenReturn(root);
//		when(root.get(Mockito.anyString())).thenReturn(battery);
//		// when(r.get("authentificationEntity")).thenReturn(authentificationEntity);
//		when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//		when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//		when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//		when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(" SELECT u FROM DCOptimizerEntity u WHERE u.isDeleted = :p1 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		when(mockedQuery2.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery2);
//		when(mockedQuery2.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery2);
//		
//		
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u.optimizer.id FROM DCOptimizerFavoritEntity u WHERE u.user.id = :p1 "))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//		
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u from DCOptimizerEntity u WHERE u.user.id = :p1"))
//				.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//		
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT u from DCOptimizerFavoritEntity u WHERE u.optimizer.id = :p1"))
//				.thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
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
//		// Result of query3
//		scenario.get(0).add(null);
//		// Result of query4
//		scenario.get(0).add(null);
//		// Result of query5
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add(new ArrayList<>());
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Result of query3
//		scenario.get(1).add(null);
//		// Result of query4
//		scenario.get(1).add(null);
//		// Result of query5
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add(new ArrayList<>());
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		scenario.get(2).add("123");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(null);
//		// Result of query2
//		scenario.get(2).add(null);
//		// Result of query3
//		scenario.get(2).add(null);
//		// Result of query4
//		scenario.get(2).add(null);
//		// Result of query5
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add(new ArrayList<>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add("123");
//		scenario.get(3).add("123");
//		scenario.get(3).add("");
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(null);
//		// Result of query2
//		scenario.get(3).add(null);
//		// Result of query3
//		scenario.get(3).add(null);
//		// Result of query4
//		scenario.get(3).add(null);
//		// Result of query5
//		scenario.get(3).add(null);
//		// Excpected Result
//		scenario.get(3).add(new ArrayList<>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add("123");
//		scenario.get(4).add("123");
//		scenario.get(4).add("123");
//		scenario.get(4).add(null);
//		// Result of query1
//		scenario.get(4).add(null);
//		// Result of query2
//		scenario.get(4).add(null);
//		// Result of query3
//		scenario.get(4).add(null);
//		// Result of query4
//		scenario.get(4).add(null);
//		// Result of query5
//		scenario.get(4).add(null);
//		// Excpected Result
//		scenario.get(4).add(new ArrayList<>());
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(null);
//		scenario.get(5).add("123");
//		scenario.get(5).add("123");
//		scenario.get(5).add("123");
//		scenario.get(5).add(null);
//		// Result of query1
//		scenario.get(5).add(null);
//		// Result of query2
//		ArrayList<DCOptimizerEntity> listQ3 = new ArrayList<DCOptimizerEntity>();
//		listQ3.add(null);
//		DCOptimizerEntity dc2 = new DCOptimizerEntity();
//		listQ3.add(dc2);
//		DCOptimizerEntity dc = new DCOptimizerEntity();
//		dc.setId(55);
//		listQ3.add(dc);
//		scenario.get(5).add(listQ3);
//		// Result of query3
//		ArrayList<Integer> listQ2 = new ArrayList<Integer>();
//		listQ2.add(55);
//		listQ2.add(123);
//		scenario.get(5).add(listQ2);
//		// Result of query4
//		scenario.get(5).add(null);
//		// Result of query5
//		scenario.get(5).add(null);
//		// Excpected Result
//		ArrayList<ConverterModel> exp1 = new ArrayList<ConverterModel>();
//		ConverterModel c = new ConverterModel(dc2);
//		c.setIsFavorit(false);
//		exp1.add(c);
//		ConverterModel c2 = new ConverterModel(dc);
//		c2.setIsFavorit(true);
//		exp1.add(c2);
//		scenario.get(5).add(exp1);
//		
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		SearchConvertersRequest sc = new SearchConvertersRequest();
//		sc.setFavorite(true);
//		scenario.get(6).add(sc);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		// Result of query1
//		scenario.get(6).add(null);
//		// Result of query2
//		scenario.get(6).add(null);
//		// Result of query3
//		scenario.get(6).add(null);
//		// Result of query4
//		scenario.get(6).add(null);
//		// Result of query5
//		scenario.get(6).add(null);
//		// Excpected Result
//		scenario.get(6).add(new ArrayList<>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(7).add(sc);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add("");
//		scenario.get(7).add(null);
//		// Result of query1
//		scenario.get(7).add(null);
//		// Result of query2
//		scenario.get(7).add(null);
//		// Result of query3
//		scenario.get(7).add(null);
//		// Result of query4
//		scenario.get(7).add(null);
//		// Result of query5
//		scenario.get(7).add(null);
//		// Excpected Result
//		scenario.get(7).add(new ArrayList<>());
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(8).add(sc);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.get(8).add("123");
//		scenario.get(8).add(null);
//		// Result of query1
//		scenario.get(8).add(null);
//		// Result of query2
//		scenario.get(8).add(null);
//		// Result of query3
//		scenario.get(8).add(null);
//		// Result of query4
//		scenario.get(8).add(null);
//		// Result of query5
//		scenario.get(8).add(null);
//		// Excpected Result
//		scenario.get(8).add(new ArrayList<>());
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(9).add(sc);
//		scenario.get(9).add("");
//		scenario.get(9).add("");
//		scenario.get(9).add("123");
//		scenario.get(9).add(null);
//		// Result of query1
//		scenario.get(9).add(null);
//		// Result of query2
//		scenario.get(9).add(null);
//		// Result of query3
//		scenario.get(9).add(null);
//		// Result of query4
//		scenario.get(9).add(null);
//		// Result of query5
//		scenario.get(9).add(null);
//		// Excpected Result
//		scenario.get(9).add(new ArrayList<>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(10).add(sc);
//		scenario.get(10).add("125");
//		scenario.get(10).add("589");
//		scenario.get(10).add("123");
//		scenario.get(10).add(null);
//		// Result of query1
//		scenario.get(10).add(null);
//		// Result of query2
//		scenario.get(10).add(null);
//		// Result of query3
//		scenario.get(10).add(null);
//		// Result of query4
//		scenario.get(10).add(null);
//		// Result of query5
//		scenario.get(10).add(null);
//		// Excpected Result
//		scenario.get(10).add(new ArrayList<>());
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		SearchConvertersRequest sc2 = new SearchConvertersRequest();
//		sc2.setFavorite(true);
//		sc2.setModel("aaa_aaa hello xx");
//		scenario.get(11).add(sc2);
//		scenario.get(11).add("125");
//		scenario.get(11).add("589");
//		scenario.get(11).add("123");
//		scenario.get(11).add(null);
//		// Result of query1
//		ArrayList<DCOptimizerEntity> list = new ArrayList<DCOptimizerEntity>();
//		list.add(null);
//		DCOptimizerEntity dcQ1 = new DCOptimizerEntity();
//		list.add(dcQ1);
//		DCOptimizerEntity dc1Q1 = new DCOptimizerEntity();
//		dc1Q1.setId(45);
//		list.add(dc1Q1);
//		scenario.get(11).add(list);
//		// Result of query2
//		scenario.get(11).add(null);
//		// Result of query3
//		scenario.get(11).add(null);
//		// Result of query4
//		ArrayList<DCOptimizerEntity> listQ4 = new ArrayList<DCOptimizerEntity>();
//		listQ4.add(null);
//		DCOptimizerEntity dcQ4 = new DCOptimizerEntity();
//		dcQ4.setModel("aaa_aaa");
//		dcQ4.setId(987);
//		listQ4.add(dcQ4);
//		scenario.get(11).add(listQ4);
//		// Result of query5
//		ArrayList<DCOptimizerFavoritEntity> listQ5 = new ArrayList<DCOptimizerFavoritEntity>();
//		listQ5.add(null);
//		listQ5.add(new DCOptimizerFavoritEntity());
//		scenario.get(11).add(listQ5);
//		// Excpected Result
//		ArrayList<ConverterModel> exp3 = new ArrayList<ConverterModel>();
//		ConverterModel conExp1 = new ConverterModel(dcQ1);
//		conExp1.setIsFavorit(true);
//		exp3.add(conExp1);
//		ConverterModel con1Exp1 = new ConverterModel(dc1Q1);
//		con1Exp1.setIsFavorit(true);
//		exp3.add(con1Exp1);
//		ConverterModel con2Exp1 = new ConverterModel(dcQ4);
//		con2Exp1.setIsFavorit(true);
//		exp3.add(con2Exp1);
//		scenario.get(11).add(exp3);
//		
//        for(int i=0;i<scenario.size();i++) {
//			 System.out.println("searchBattery [ "+i+" ]");
//			 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(6));
//			 when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(7));
//			 when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(8));
//			 when(mockedQuery5.getResultList()).thenReturn((List) scenario.get(i).get(9));
//
//            ArrayList<ConverterModel> result = ((ArrayList<ConverterModel>) convertersManagementService.getConverters((SearchConvertersRequest) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3),(Boolean) scenario.get(i).get(4)));
//
//			 
//			
//		 }
//	}
//	
//	
//	
//	@Test
//	public void testsendCorrectionconverterRequest() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from DCOptimizerEntity u Where u.id = :p1 " )).thenReturn(mockedQuery1);
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
//		scenario.get(0).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		ConverterCorrectionModel acComSLC = new ConverterCorrectionModel();
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
//		ConverterCorrectionModel acComSLC1 = new ConverterCorrectionModel();
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
//		ConverterCorrectionModel acComSLC2 = new ConverterCorrectionModel();
//		acComSLC2.setId(123);
//		acComSLC2.setIdUser("123");
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
//		ConverterCorrectionModel acComSLC4 = new ConverterCorrectionModel();
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
//		DCOptimizerEntity lib = new DCOptimizerEntity();
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
//			convertersManagementService
//					.sendCorrectionconverterRequest((ConverterCorrectionModel) scenario.get(i).get(0));
//
//		}
//
//	}
//	
//	@Test
//	public void testFavoritConv() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u FROM DCOptimizerFavoritEntity u WHERE u.optimizer.id = :p1 AND u.user.id = :p2  "))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//              	+ "p.permitEntity.authentificationEntity.id = :p1 and p.systemOptimizerModel = :p2 "))
//						.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//		
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT  u  FROM DCOptimizerEntity u   WHERE u.id = :p1 "))
//						.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//		
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT u FROM AuthentificationEntity u WHERE u.id = :p1 "))
//						.thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(123);
//		scenario.get(0).add(123);
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
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result of the query4
//		scenario.get(0).add(null);
//		// Result of the query5
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(123);
//		scenario.get(1).add(123);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result of the query4
//		scenario.get(1).add(null);
//		// Result of the query5
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(223);
//		scenario.get(2).add(223);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result of the query4
//		scenario.get(2).add(null);
//		// Result of the query5
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("converter does not exist");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(323);
//		scenario.get(3).add(323);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result of the query4
//		DCOptimizerEntity q1 = new DCOptimizerEntity();
//		scenario.get(3).add(q1);
//		// Result of the query5
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("converter does not exist");
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(423);
//		scenario.get(4).add(423);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add("123");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result of the query4
//		scenario.get(4).add(q1);
//		// Result of the query5
//		scenario.get(4).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(4).add("success");
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(523);
//		scenario.get(5).add(523);
//		scenario.get(5).add(true);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add("123");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		// Result of the query1
//		scenario.get(5).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result of the query3
//		scenario.get(5).add(null);
//		// Result of the query4
//		scenario.get(5).add(q1);
//		// Result of the query5
//		scenario.get(5).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(5).add("converter does not exist");
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(6).add(623);
//		scenario.get(6).add(623);
//		scenario.get(6).add(true);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add("123");
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		// Result of the query1
//		scenario.get(6).add(new AuthentificationEntity());
//		// Result of the query2
//		DCOptimizerFavoritEntity q2 = new DCOptimizerFavoritEntity();
//		q2.setOptimizer(new DCOptimizerEntity());
//		scenario.get(6).add(q2);
//		// Result of the query3
//		scenario.get(6).add(null);
//		// Result of the query4
//		scenario.get(6).add(q1);
//		// Result of the query5
//		scenario.get(6).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(6).add("success");
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(7).add(723);
//		scenario.get(7).add(723);
//		scenario.get(7).add(true);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add("123");
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		// Result of the query1
//		scenario.get(7).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(7).add(q2);
//		// Result of the query3
//		ArrayList<PermitArraysEntity> list = new ArrayList<PermitArraysEntity>();
//		list.add(null);
//		list.add(new PermitArraysEntity());
//		scenario.get(7).add(list);
//		// Result of the query4
//		scenario.get(7).add(q1);
//		// Result of the query5
//		scenario.get(7).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(7).add("success");
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("FavoritConv [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn( scenario.get(i).get(9));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(10));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(11));
//			when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(12));
//			when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(13));
//			convertersManagementService
//					.FavoritConv((int) scenario.get(i).get(0), (int) scenario.get(i).get(1), (Boolean) scenario.get(i).get(2), (String) scenario.get(i).get(3), (String) scenario.get(i).get(4), (String) scenario.get(i).get(5), (String) scenario.get(i).get(6), (String) scenario.get(i).get(7), (String) scenario.get(i).get(8));
//
//		}
//	}
//	
//	@Test
//	public void testgetUsersForFavList() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from DCOptimizerFavoritEntity u WHERE u.optimizer.id = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult ( "
//				+ "   u.id,  "
//				+ "   u.firstName,  "
//				+ "   u.lastName ) "
//				+ " from AuthentificationEntity u WHERE u.id NOT IN :p1 and u.deleted = :p2 and u.active = :p3 and u.id != :p4 ORDER BY u.firstName"))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult ( "
//				+ "   u.id,  "
//				+ "   u.firstName,  "
//				+ "   u.lastName ) "
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
//		scenario.get(1).add(new ArrayList<DCOptimizerFavoritEntity>());
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
//		scenario.get(2).add(new ArrayList<DCOptimizerFavoritEntity>());
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
//		scenario.get(3).add(new ArrayList<DCOptimizerFavoritEntity>());
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
//		ArrayList<DCOptimizerFavoritEntity> list = new ArrayList<DCOptimizerFavoritEntity>();
//		list.add(null);
//		list.add(new DCOptimizerFavoritEntity());
//		DCOptimizerFavoritEntity ac = new DCOptimizerFavoritEntity();
//		ac.setUser(new AuthentificationEntity());
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
//			convertersManagementService
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
//		 when(em.createQuery("SELECT u from DCOptimizerEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
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
//		 Integer[] ListUsers = {null,2000};
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
//		 Integer[] ListUsers2 = {12,44,1333};
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
//		 scenario.get(3).add(new DCOptimizerEntity());
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
//		 scenario.get(4).add(new DCOptimizerEntity());
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
//           convertersManagementService.editUsersFavoriteList((Integer)scenario.get(i).get(0),(Integer[])scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6),(String)scenario.get(i).get(7));
//
//		 }
//	
//	}
//	
//	@Test
//	public void testeditConverters() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM DCOptimizerEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM DCOptimizerEntity u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM PermitArraysEntity u WHERE u.systemOptimizerModel = :p1")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//		 
//		 Query mockedQuery4 = mock(Query.class);
//		 when(em.createQuery("SELECT u.roleEntity.id FROM AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery4);
//		 when(mockedQuery4.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery4);
//		 
//		 
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //The result of query1
//		 scenario.get(0).add(null);
//		 //The result of query2
//		 scenario.get(0).add(null);
//		 //The result of query3
//		 scenario.get(0).add(null);
//		 //The result of query4
//		 scenario.get(0).add(null);
//		 //The result excpected
//		 scenario.get(0).add("error");
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 ConverterModel conv = new ConverterModel();
//		 conv.setModel("aaa");
//		 conv.setManufacturer("bbb");
//		 scenario.get(1).add(conv);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //The result of query1
//		 scenario.get(1).add(null);
//		 //The result of query2
//		 scenario.get(1).add(null);
//		 //The result of query3
//		 scenario.get(1).add(null);
//		 //The result of query4
//		 scenario.get(1).add(null);
//		 //The result excpected
//		 scenario.get(1).add("error");
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(2).add(conv);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //The result of query1
//		 DCOptimizerEntity dc = new DCOptimizerEntity();
//		 dc.setManufacturer("xxx");
//		 dc.setModel("yyy");
//		 scenario.get(2).add(dc);
//		 //The result of query2
//		 ArrayList<DCOptimizerEntity> list = new ArrayList<DCOptimizerEntity>();
//		 list.add(null);
//		 list.add(new DCOptimizerEntity());
//		 scenario.get(2).add(list);
//		 //The result of query3
//		 scenario.get(2).add(null);
//		 //The result of query4
//		 scenario.get(2).add(null);
//		 //The result excpected
//		 scenario.get(2).add("exist");
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(3).add(conv);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //The result of query1
//		 scenario.get(3).add(new DCOptimizerEntity());
//		 //The result of query2
//		 scenario.get(3).add(null);
//		 //The result of query3
//		 scenario.get(3).add(null);
//		 //The result of query4
//		 scenario.get(3).add(null);
//		 //The result excpected
//		 scenario.get(3).add("error");
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(4).add(conv);
//		 scenario.get(4).add("");
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 //The result of query1
//		 scenario.get(4).add(new DCOptimizerEntity());
//		 //The result of query2
//		 scenario.get(4).add(null);
//		 //The result of query3
//		 scenario.get(4).add(null);
//		 //The result of query4
//		 scenario.get(4).add(null);
//		 //The result excpected
//		 scenario.get(4).add("error");
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(5).add(conv);
//		 scenario.get(5).add("1258");
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 //The result of query1
//		 scenario.get(5).add(new DCOptimizerEntity());
//		 //The result of query2
//		 scenario.get(5).add(null);
//		 //The result of query3
//		 scenario.get(5).add(null);
//		 //The result of query4
//		 scenario.get(5).add(null);
//		 //The result excpected
//		 scenario.get(5).add("error");
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(6).add(conv);
//		 scenario.get(6).add("1258");
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 //The result of query1
//		 scenario.get(6).add(new DCOptimizerEntity());
//		 //The result of query2
//		 scenario.get(6).add(null);
//		 //The result of query3
//		 scenario.get(6).add(null);
//		 //The result of query4
//		 scenario.get(6).add(1);
//		 //The result excpected
//		 scenario.get(6).add("success");
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("editConverters [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(8));
//			 when(mockedQuery3.getSingleResult()).thenReturn( scenario.get(i).get(9));
//			 when(mockedQuery4.getSingleResult()).thenReturn( scenario.get(i).get(10));
//			  convertersManagementService.editConverters((ConverterModel)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6));
//
//		 }
//		 
//	}
//
//	@Test
//	public void testtestConverterFav() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u.roleEntity.id FROM AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.ConverterFavModelResult( u.optimizer.id, u.optimizer.manufacturer , u.optimizer.model ) FROM DCOptimizerFavoritEntity u , PermitEntity v WHERE v.id = :p2 AND u.user.id = v.authentificationEntity.id AND u.optimizer.isDeleted = :p1 ORDER BY u.optimizer.manufacturer,  u.optimizer.model ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.ConverterFavModelResult( u.optimizer.id, u.optimizer.manufacturer , u.optimizer.model ) FROM DCOptimizerFavoritEntity u WHERE u.user.id = :p1 AND  u.optimizer.isDeleted = :p2 ORDER BY u.optimizer.manufacturer,  u.optimizer.model ")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//		
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //The result of query1
//		 scenario.get(0).add(null);
//		 //The result of query2
//		 scenario.get(0).add(null);
//		 //The result of query3
//		 scenario.get(0).add(null);
//		 //The result excpected
//		 scenario.get(0).add(false);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(1).add("");
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //The result of query1
//		 scenario.get(1).add(null);
//		 //The result of query2
//		 scenario.get(1).add(null);
//		 //The result of query3
//		 scenario.get(1).add(null);
//		 //The result excpected
//		 scenario.get(1).add(false);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(2).add("1235");
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //The result of query1
//		 scenario.get(2).add(null);
//		 //The result of query2
//		 scenario.get(2).add(null);
//		 //The result of query3
//		 scenario.get(2).add(null);
//		 //The result excpected
//		 scenario.get(2).add(false);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(3).add("1235");
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //The result of query1
//		 scenario.get(3).add(1);
//		 //The result of query2
//		 scenario.get(3).add(null);
//		 //The result of query3
//		 scenario.get(3).add(null);
//		 //The result excpected
//		 scenario.get(3).add(false);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(4).add("1235");
//		 scenario.get(4).add("");
//		 scenario.get(4).add(null);
//		 //The result of query1
//		 scenario.get(4).add(1);
//		 //The result of query2
//		 scenario.get(4).add(null);
//		 //The result of query3
//		 scenario.get(4).add(null);
//		 //The result excpected
//		 scenario.get(4).add(false);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(5).add("1235");
//		 scenario.get(5).add("1258");
//		 scenario.get(5).add(null);
//		 //The result of query1
//		 scenario.get(5).add(1);
//		 //The result of query2
//		 scenario.get(5).add(null);
//		 //The result of query3
//		 scenario.get(5).add(null);
//		 //The result excpected
//		 scenario.get(5).add(false);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(6).add("1235");
//		 scenario.get(6).add("1258");
//		 scenario.get(6).add(null);
//		 //The result of query1
//		 scenario.get(6).add(1);
//		 //The result of query2
//		 ArrayList<ConverterFavModelResult> list = new ArrayList<ConverterFavModelResult>();
//		 list.add(null);
//		 list.add(new ConverterFavModelResult());
//		 scenario.get(6).add(list);
//		 //The result of query3
//		 scenario.get(6).add(list);
//		 //The result excpected
//		 scenario.get(6).add(true);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(7).add("1235");
//		 scenario.get(7).add("1258");
//		 scenario.get(7).add(null);
//		 //The result of query1
//		 scenario.get(7).add(4);
//		 //The result of query2
//		 scenario.get(7).add(list);
//		 //The result of query3
//		 scenario.get(7).add(list);
//		 //The result excpected
//		 scenario.get(7).add(true);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("testConverterFav [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			 when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			 convertersManagementService.testConverterFav((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	}
//	
//	@Test
//	public void testaddConverter() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM DCOptimizerEntity u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM DCOptimizerEntity u WHERE u.model = :p1 AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM DCOptimizerFavoritEntity u WHERE u.optimizer.id = :p1 AND u.user.id = :p2 ")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//		 
//		 Query mockedQuery4 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery4);
//		 when(mockedQuery4.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery4);
//		 
//		 Query mockedQuery5 = mock(Query.class);
//		 when(em.createQuery("SELECT u.authentificationEntity from PermitEntity u WHERE u.id = :p1")).thenReturn(mockedQuery5);
//		 when(mockedQuery5.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery5);
//		 
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //The result of query1
//		 scenario.get(0).add(null);
//		 //The result of query2
//		 scenario.get(0).add(null);
//		 //The result of query3
//		 scenario.get(0).add(null);
//		 //The result of query4
//		 scenario.get(0).add(null);
//		 //The result of query5
//		 scenario.get(0).add(null);
//		 //The result excpected
//		 LinkedHashMap<Integer, String> exp1 = new LinkedHashMap<Integer, String>();
//		 exp1.put(0, "error");
//		 scenario.get(0).add(exp1);
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(1).add(new AddConverterModelRequest());
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //The result of query1
//		 scenario.get(1).add(null);
//		 //The result of query2
//		 scenario.get(1).add(null);
//		 //The result of query3
//		 scenario.get(1).add(null);
//		 //The result of query4
//		 scenario.get(1).add(null);
//		 //The result of query5
//		 scenario.get(1).add(null);
//		 //The result excpected
//		 scenario.get(1).add(exp1);
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(2).add(new AddConverterModelRequest());
//		 scenario.get(2).add("");
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //The result of query1
//		 scenario.get(2).add(null);
//		 //The result of query2
//		 scenario.get(2).add(null);
//		 //The result of query3
//		 scenario.get(2).add(null);
//		 //The result of query4
//		 scenario.get(2).add(null);
//		 //The result of query5
//		 scenario.get(2).add(null);
//		 //The result excpected
//		 scenario.get(2).add(exp1);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(3).add(new AddConverterModelRequest());
//		 scenario.get(3).add("120");
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //The result of query1
//		 scenario.get(3).add(null);
//		 //The result of query2
//		 scenario.get(3).add(null);
//		 //The result of query3
//		 scenario.get(3).add(null);
//		 //The result of query4
//		 scenario.get(3).add(null);
//		 //The result of query5
//		 scenario.get(3).add(null);
//		 //The result excpected
//		 scenario.get(3).add(exp1);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(4).add(new AddConverterModelRequest());
//		 scenario.get(4).add("120");
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 //The result of query1
//		 scenario.get(4).add(null);
//		 //The result of query2
//		 scenario.get(4).add(null);
//		 //The result of query3
//		 scenario.get(4).add(null);
//		 //The result of query4
//		 AuthentificationEntity auth = new AuthentificationEntity();
//		 auth.setRoleEntity(new RoleEntity());
//		 scenario.get(4).add(auth);
//		 //The result of query5
//		 scenario.get(4).add(null);
//		 //The result excpected
//		 LinkedHashMap<Integer, String> exp2 = new LinkedHashMap<Integer, String>();
//		 exp2.put(0, "success");
//		 scenario.get(4).add(exp2);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(5).add(new AddConverterModelRequest());
//		 scenario.get(5).add("120");
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 //The result of query1
//		 ArrayList<DCOptimizerEntity> listQ1 = new ArrayList<DCOptimizerEntity>(); 
//		 listQ1.add(null);
//		 listQ1.add(new DCOptimizerEntity());
//		 scenario.get(5).add(listQ1);
//		 //The result of query2
//		 scenario.get(5).add(null);
//		 //The result of query3
//		 scenario.get(5).add(null);
//		 //The result of query4
//		 scenario.get(5).add(auth);
//		 //The result of query5
//		 scenario.get(5).add(null);
//		 //The result excpected
//		 LinkedHashMap<Integer, String> exp3 = new LinkedHashMap<Integer, String>();
//		 exp3.put(0, "exist");
//		 scenario.get(5).add(exp3);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(6).add(new AddConverterModelRequest());
//		 scenario.get(6).add("120");
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 //The result of query1
//		 scenario.get(6).add(null);
//		 //The result of query2
//		 ArrayList<DCOptimizerEntity> listQ2 = new ArrayList<DCOptimizerEntity>();
//		 listQ2.add(null);
//		 listQ2.add(new DCOptimizerEntity());
//		 scenario.get(6).add(listQ2);
//		 //The result of query3
//		 ArrayList<DCOptimizerFavoritEntity>  listQ3 = new ArrayList<DCOptimizerFavoritEntity>();
//		 listQ3.add(null);
//		 listQ3.add(new DCOptimizerFavoritEntity());
//		 scenario.get(6).add(listQ3);
//		 //The result of query4
//		 scenario.get(6).add(auth);
//		 //The result of query5
//		 scenario.get(6).add(null);
//		 //The result excpected
//		 LinkedHashMap<Integer, String> exp4 = new LinkedHashMap<Integer, String>();
//		 exp4.put(0, "manufacturerList");
//		 scenario.get(6).add(exp4);
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("deleteConverter [ "+i+" ]");
//			   when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(8));
//			   when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(9));
//			   when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(10));
//			   when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(11));
//			   when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(12));
//			  convertersManagementService.addConverter((AddConverterModelRequest)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(Integer)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6),(String)scenario.get(i).get(7));
//
//		 }
//	}
//	
//	@Test
//	public void teststillAddNewConverter() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //The result of query1
//		 scenario.get(0).add(null);
//		 //The result excpected
//		 scenario.get(0).add(new DCOptimizerFavoritEntity());
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(1).add(new AddConverterModelRequest());
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //The result of query1
//		 scenario.get(1).add(null);
//		 //The result excpected
//		 scenario.get(1).add(new DCOptimizerFavoritEntity());
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(2).add(new AddConverterModelRequest());
//		 scenario.get(2).add("");
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //The result of query1
//		 scenario.get(2).add(null);
//		 //The result excpected
//		 scenario.get(2).add(new DCOptimizerFavoritEntity());
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(3).add(new AddConverterModelRequest());
//		 scenario.get(3).add("123");
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //The result of query1
//		 scenario.get(3).add(null);
//		 //The result excpected
//		 scenario.get(3).add(new DCOptimizerFavoritEntity());
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(4).add(new AddConverterModelRequest());
//		 scenario.get(4).add("123");
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 //The result of query1
//		 AuthentificationEntity auth = new AuthentificationEntity();
//		 auth.setRoleEntity(new RoleEntity());
//		 scenario.get(4).add(auth);
//		 //The result excpected
//		 DCOptimizerFavoritEntity exp = new DCOptimizerFavoritEntity();
//		 exp.setUser(auth);
//		 scenario.get(4).add(exp);
//		 
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("deleteConverter [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			 DCOptimizerFavoritEntity rslt =  convertersManagementService.stillAddNewConverter((AddConverterModelRequest)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6));
//
//		 }
//	}
//	
//	
//	@Test
//	public void testdeleteConverter() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.ProjectForConvertModelResult  "
//					+ " ( w.permitEntity.homeOwnName, " + " w.permitEntity.status, "
//					+ " w.permitEntity.authentificationEntity.firstName, "
//					+ " w.permitEntity.authentificationEntity.lastName)" + " from " + " PermitArraysEntity w"
//					+ " where w.systemOptimizerModel = :p1 " + "and w.permitEntity.isDeleted  = :p2 "
//					+ "and w.permitEntity.status != 'Delivered'  " + "and w.permitEntity.status != 'Submitted' "
//					+ "and w.permitEntity.isTemplate  = :p2  ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //The result of query1
//		 scenario.get(0).add(null);
//		 //The result excpected
//		 scenario.get(0).add(new ArrayList<>());
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //The result of query1
//		 ArrayList<ProjectForConvertModelResult> list = new ArrayList<ProjectForConvertModelResult>();
//		 list.add(null);
//		 list.add(new ProjectForConvertModelResult());
//		 scenario.get(1).add(list);
//		 //The result excpected
//		 scenario.get(1).add(list);
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("deleteConverter [ "+i+" ]");
//			 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 List<ProjectForConvertModelResult> rslt = (List<ProjectForConvertModelResult>) convertersManagementService.deleteConverter((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	}
//	
//	@Test
//	public void teststilldeleteConverter() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM DCOptimizerEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM DCOptimizerFavoritEntity u WHERE u.optimizer.id = :p1")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u From PermitArraysEntity  u WHERE u.systemOptimizerModel = :p1 and"
//					+ " u.permitEntity.isDeleted  = :p2 and " + " u.permitEntity.status != 'Delivered'  and "
//					+ " u.permitEntity.status != 'Submitted'and" + " u.permitEntity.isTemplate  = :p2 ")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//		 
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(0).add(123);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //The result of query1
//		 scenario.get(0).add(null);
//		 //The result of the query2
//		 scenario.get(0).add(null);
//		 //The result of the query3
//		 scenario.get(0).add(null);
//		 //The result excpected
//		 scenario.get(0).add("error");
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(1).add(123);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //The result of query1
//		 scenario.get(1).add(new DCOptimizerEntity());
//		 //The result of the query2
//		 scenario.get(1).add(null);
//		 //The result of the query3
//		 scenario.get(1).add(null);
//		 //The result excpected
//		 scenario.get(1).add("success");
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(2).add(223);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //The result of query1
//		 scenario.get(2).add(new DCOptimizerEntity());
//		 //The result of the query2
//		 ArrayList<DCOptimizerFavoritEntity> list = new ArrayList<DCOptimizerFavoritEntity>();
//		 list.add(null);
//		 list.add(new DCOptimizerFavoritEntity());
//		 scenario.get(2).add(list);
//		 //The result of the query3
//		 ArrayList<PermitArraysEntity> list2 = new ArrayList<PermitArraysEntity>();
//		 list2.add(null);
//		 list2.add(new PermitArraysEntity());
//		 scenario.get(2).add(list2);
//		 //The result excpected
//		 scenario.get(2).add("success");
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("stilldeleteConverter [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(8));
//			 when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(9));
//			 convertersManagementService.stilldeleteConverter((int)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6));
//
//		 }
//	}
//	
//	@Test
//	public void testactivateConverter() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM DCOptimizerEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCOptimizerEntity u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 
//		 scenario.add(new ArrayList<Object>());
//		 //List of parameter possible
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //The result of query1
//		 scenario.get(0).add(null);
//		 //The result of the query2
//		 scenario.get(0).add(null);
//		 //The result excpected
//		 scenario.get(0).add("error");
//		 
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(1).add("");
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //The result of query1
//		 scenario.get(1).add(null);
//		 //The result of the query2
//		 scenario.get(1).add(null);
//		 //The result excpected
//		 scenario.get(1).add("error");
//		 
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("");
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //The result of query1
//		 scenario.get(2).add(null);
//		 //The result of the query2
//		 scenario.get(2).add(null);
//		 //The result excpected
//		 scenario.get(2).add("error");
//		 
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("22");
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //The result of query1
//		 DCOptimizerEntity ac1 = new DCOptimizerEntity();
//		 scenario.get(3).add(ac1);
//		 //The result of the query2
//		 scenario.get(3).add(null);
//		 //The result excpected
//		 scenario.get(3).add("success");
//		 
//		 
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 //The result of query1
//		 scenario.get(4).add(ac1);
//		 //The result of the query2
//		 ArrayList<DCOptimizerEntity> listAcCom= new ArrayList<DCOptimizerEntity>();
//		 listAcCom.add(new DCOptimizerEntity());
//		 scenario.get(4).add(listAcCom);
//		 //The result excpected
//		 scenario.get(4).add("exist");
//		
//		 
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("batteryLibraryActived [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(8));
//			 convertersManagementService.activateConverter((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6));
//
//		 }
//	}
//	
//	@Test
//	public void testeditOptimizerNotification() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//	    List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//	    
//		scenario.add(new ArrayList<Object>());
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
//			 System.out.println("editOptimizerNotification [ "+i+" ]");
//	        when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//	        convertersManagementService.editOptimizerNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	}
//	
//	@Test
//	public void testaddOptimizerNotification() {
//		 Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 
//       List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//			 System.out.println("addOptimizerNotification [ "+i+" ]");
//           when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//           convertersManagementService.addOptimizerNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	}
//
//}
