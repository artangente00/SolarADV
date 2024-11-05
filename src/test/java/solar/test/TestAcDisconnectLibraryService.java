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
//import com.PlayGroundAdv.Solar.Entity.ACCombinerFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.ACCombinerSLC;
//import com.PlayGroundAdv.Solar.Entity.ACDisconnect;
//import com.PlayGroundAdv.Solar.Entity.ACDisconnectFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.model.AcCombinerDiscoCorrectionModel;
//import com.PlayGroundAdv.Solar.model.AcCombinerDiscoModelRequest;
//import com.PlayGroundAdv.Solar.model.AcCombinerSLCCorrectionModel;
//import com.PlayGroundAdv.Solar.model.AcDisconnectModel;
//import com.PlayGroundAdv.Solar.model.NewDisconnectModel;
//import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
//import com.PlayGroundAdv.Solar.model.UsersEntityResult;
//import com.PlayGroundAdv.Solar.Services.AcCombinerSlcLibraryService;
//import com.PlayGroundAdv.Solar.Services.AcDisconnectLibraryService;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//
//public class TestAcDisconnectLibraryService {
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
//
//	@InjectMocks
//	AcDisconnectLibraryService acDisconnectLibraryService = new AcDisconnectLibraryService();
//
//
//    @Before
//	public void setupMock() {
//    	acDisconnectLibraryService = new AcDisconnectLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testgetAllAcCombinerDisco() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.isDeleted = :p1 ORDER BY u.manufacturer")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 //Spy the service
//		 AcDisconnectLibraryService acDisconnectLibraryService2 = Mockito.spy(acDisconnectLibraryService);
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result excpected
//		 scenario.get(0).add(new ArrayList<AcDisconnectModel>());
//		 //The result of the method getAcCombinerDiscoFavorite
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(true);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result excpected
//		 scenario.get(1).add(new ArrayList<AcDisconnectModel>());
//		 //The result of the method getAcCombinerDiscoFavorite
//		 scenario.get(1).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(true);
//		 //Result of the query1
//		 ArrayList<ACDisconnect> listAc = new ArrayList<ACDisconnect>();
//		 listAc.add(null);
//		 listAc.add(new ACDisconnect());
//		 ACDisconnect ac = new ACDisconnect();
//		 ac.setIdOwner(new AuthentificationEntity());
//		 listAc.add(ac);
//		 scenario.get(2).add(listAc);
//		 //Result excpected
//		 scenario.get(2).add(new ArrayList<AcDisconnectModel>());
//		 //The result of the method getAcCombinerDiscoFavorite
//		 scenario.get(2).add(null);
//
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getAllAcCombinerDisco [ "+i+" ]");
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 Mockito.doReturn(scenario.get(i).get(4)).when(acDisconnectLibraryService2).getAcCombinerDiscoFavorite(Mockito.anyString());
//			if(i > 1) {
//			  acDisconnectLibraryService2.getAllAcCombinerDisco((String)scenario.get(i).get(0),(Boolean)scenario.get(i).get(1));
//			 }else
//              acDisconnectLibraryService2.getAllAcCombinerDisco((String)scenario.get(i).get(0),(Boolean)scenario.get(i).get(1));
//
//
//		 }
//
//
//	}
//
//	@Test
//	public void testgetAcCombinerDiscoFavorite() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnectFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result excpected
//		 scenario.get(0).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add("");
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result excpected
//		 scenario.get(1).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add("123");
//		 //Result of the query1
//		 scenario.get(2).add(null);
//		 //Result excpected
//		 scenario.get(2).add(new ArrayList<Integer>());
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add("123");
//		 //Result of the query1
//		 scenario.get(3).add(new ArrayList<ACDisconnectFavLibraryEntity>());
//		 //Result excpected
//		 scenario.get(3).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add("123");
//		 //Result of the query1
//		 ArrayList<ACDisconnectFavLibraryEntity> list = new ArrayList<ACDisconnectFavLibraryEntity>();
//		 list.add(null);
//		 list.add(new ACDisconnectFavLibraryEntity());
//		 ACDisconnectFavLibraryEntity ac = new ACDisconnectFavLibraryEntity();
//		 ac.setaCDisconnect(new ACDisconnect());
//		 list.add(ac);
//		 scenario.get(4).add(list);
//		 //Result excpected
//		 scenario.get(4).add(new ArrayList<Integer>());
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getAcCombinerDiscoFavorite [ "+i+" ]");
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//             if(i >= 4) {
//                 acDisconnectLibraryService.getAcCombinerDiscoFavorite((String)scenario.get(i).get(0));
//
//             }else
//            acDisconnectLibraryService.getAcCombinerDiscoFavorite((String)scenario.get(i).get(0));
//
//		 }
//	}
//
//	@Test
//	public void testgetAllRatedCurrent() {
//		Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u.ratedCurrent from ACDisconnect u WHERE u.isDeleted = :p1 GROUP BY u.ratedCurrent ORDER BY u.ratedCurrent")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result excpected
//		 scenario.get(0).add(new ArrayList<String>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//	     scenario.get(1).add(null);
//		 scenario.get(1).add(true);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result excpected
//		 scenario.get(1).add(new ArrayList<String>());
//
//		 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//	     scenario.get(2).add(null);
//		 scenario.get(2).add(true);
//			 //Result of the query1
//		 scenario.get(2).add(new ArrayList<ACDisconnectFavLibraryEntity>());
//			 //Result excpected
//		 scenario.get(2).add(new ArrayList<String>());
//
//		 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//	     scenario.get(3).add(null);
//		 scenario.get(3).add(true);
//			 //Result of the query1
//		  ArrayList<ACDisconnectFavLibraryEntity> list = new ArrayList<ACDisconnectFavLibraryEntity>();
//		  list.add(null);
//		  list.add(new ACDisconnectFavLibraryEntity());
//		  ACDisconnectFavLibraryEntity ac = new ACDisconnectFavLibraryEntity();
//		  ac.setaCDisconnect(new ACDisconnect());
//		  list.add(ac);
//		 scenario.get(3).add(list);
//			 //Result excpected
//		 scenario.get(3).add(new ArrayList<String>());
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getAllRatedCurrent [ "+i+" ]");
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//             if(i >= 3) {
//                  acDisconnectLibraryService.getAllRatedCurrent((String)scenario.get(i).get(0),(Boolean)scenario.get(i).get(1));
//
//             }else
//                  acDisconnectLibraryService.getAllRatedCurrent((String)scenario.get(i).get(0),(Boolean)scenario.get(i).get(1));
//
//		 }
//	}
//
//	@Test
//	public void testgetAllDeletedAcCombinerDisco() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.isDeleted = :p1 ORDER BY u.manufacturer")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//			 //Result of the query1
//		 scenario.get(0).add(null);
//			 //Result excpected
//		 scenario.get(0).add(new ArrayList<AcDisconnectModel>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of the query1
//	     scenario.get(1).add(new ArrayList<ACDisconnect>());
//		 //Result excpected
//	     scenario.get(1).add(new ArrayList<AcDisconnectModel>());
//
//	     scenario.add(new ArrayList<Object>());
//		 //Result of the query1
//	     ArrayList<ACDisconnect> list = new ArrayList<ACDisconnect>();
//	     list.add(null);
//	     list.add(new ACDisconnect());
//	     ACDisconnect ac = new ACDisconnect();
//	     ac.setIdOwner(new AuthentificationEntity());
//	     list.add(ac);
//	     scenario.get(2).add(list);
//		 //Result excpected
//	     scenario.get(2).add(new ArrayList<AcDisconnectModel>());
//
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getAllDeletedAcCombinerDisco [ "+i+" ]");
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//             if(i >= 2) {
//                  acDisconnectLibraryService.getAllDeletedAcCombinerDisco();
//             }else
//                  acDisconnectLibraryService.getAllDeletedAcCombinerDisco();
//
//		 }
//	}
//
//	@Test
//	public void testeditAcCombinerDiscoFavoriteList() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnectFavLibraryEntity u WHERE u.aCDisconnect.id = :p1 and u.authentificationEntity.id = :p2")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
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
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result of the query2
//		 scenario.get(0).add(null);
//		 //Result of the query2
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add("error");
//
//		 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(1).add(123);
//			 scenario.get(1).add(false);
//			 scenario.get(1).add("");
//			 scenario.get(1).add("");
//			 scenario.get(1).add("");
//			 scenario.get(1).add("");
//			 scenario.get(1).add("");
//			 scenario.get(1).add("");
//			 scenario.get(1).add("");
//			 //Result of the query1
//			 scenario.get(1).add(null);
//			 //Result of the query2
//			 scenario.get(1).add(null);
//			 //Result of the query3
//			 scenario.get(1).add(null);
//		     //Result excpected
//			 scenario.get(1).add("error");
//
//			 scenario.add(new ArrayList<Object>());
//				//List of the parameter
//				 scenario.get(2).add(123);
//				 scenario.get(2).add(false);
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 scenario.get(2).add("123");
//				 //Result of the query1
//				 scenario.get(2).add(null);
//				 //Result of the query2
//				 scenario.get(2).add(null);
//				 //Result of the query2
//				 scenario.get(2).add(null);
//			     //Result excpected
//				 scenario.get(2).add("error");
//
//				 scenario.add(new ArrayList<Object>());
//					//List of the parameter
//					 scenario.get(3).add(123);
//					 scenario.get(3).add(false);
//					 scenario.get(3).add("123");
//					 scenario.get(3).add("123");
//					 scenario.get(3).add("123");
//					 scenario.get(3).add("123");
//					 scenario.get(3).add("123");
//					 scenario.get(3).add("123");
//					 scenario.get(3).add("123");
//					 //Result of the query1
//					 scenario.get(3).add(null);
//					 //Result of the query2
//					 scenario.get(3).add(null);
//					 //Result of the query2
//					 scenario.get(3).add(new ACDisconnectFavLibraryEntity());
//				     //Result excpected
//					 scenario.get(3).add("error");
//
//					 scenario.add(new ArrayList<Object>());
//						//List of the parameter
//						 scenario.get(4).add(123);
//						 scenario.get(4).add(false);
//						 scenario.get(4).add("123");
//						 scenario.get(4).add("123");
//						 scenario.get(4).add("123");
//						 scenario.get(4).add("123");
//						 scenario.get(4).add("123");
//						 scenario.get(4).add("123");
//						 scenario.get(4).add("123");
//						 //Result of the query1
//						 scenario.get(4).add(new ACDisconnect());
//						 //Result of the query2
//						 scenario.get(4).add(new AuthentificationEntity());
//						 //Result of the query2
//						 scenario.get(4).add(new ACDisconnectFavLibraryEntity());
//					     //Result excpected
//						 scenario.get(4).add("Done");
//
//
//						 scenario.add(new ArrayList<Object>());
//							//List of the parameter
//							 scenario.get(5).add(123);
//							 scenario.get(5).add(true);
//							 scenario.get(5).add("123");
//							 scenario.get(5).add("123");
//							 scenario.get(5).add("123");
//							 scenario.get(5).add("123");
//							 scenario.get(5).add("123");
//							 scenario.get(5).add("123");
//							 scenario.get(5).add("123");
//							 //Result of the query1
//							 scenario.get(5).add(null);
//							 //Result of the query2
//							 scenario.get(5).add(new AuthentificationEntity());
//							 //Result of the query2
//							 scenario.get(5).add(new ACDisconnectFavLibraryEntity());
//						     //Result excpected
//							 scenario.get(5).add("error");
//
//							 scenario.add(new ArrayList<Object>());
//								//List of the parameter
//								 scenario.get(6).add(123);
//								 scenario.get(6).add(true);
//								 scenario.get(6).add("123");
//								 scenario.get(6).add("123");
//								 scenario.get(6).add("123");
//								 scenario.get(6).add("123");
//								 scenario.get(6).add("123");
//								 scenario.get(6).add("123");
//								 scenario.get(6).add("123");
//								 //Result of the query1
//								 scenario.get(6).add(new ACDisconnect());
//								 //Result of the query2
//								 scenario.get(6).add(null);
//								 //Result of the query2
//								 scenario.get(6).add(new ACDisconnectFavLibraryEntity());
//							     //Result excpected
//								 scenario.get(6).add("error");
//
//
//								 scenario.add(new ArrayList<Object>());
//									//List of the parameter
//									 scenario.get(7).add(123);
//									 scenario.get(7).add(true);
//									 scenario.get(7).add("123");
//									 scenario.get(7).add("123");
//									 scenario.get(7).add("123");
//									 scenario.get(7).add("123");
//									 scenario.get(7).add("123");
//									 scenario.get(7).add("123");
//									 scenario.get(7).add("123");
//									 //Result of the query1
//									 scenario.get(7).add(new ACDisconnect());
//									 //Result of the query2
//									 scenario.get(7).add(new AuthentificationEntity());
//									 //Result of the query2
//									 scenario.get(7).add(new ACDisconnectFavLibraryEntity());
//								     //Result excpected
//									 scenario.get(7).add("Done");
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getAllDeletedAcCombinerDisco [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(9));
//             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(10));
//             when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(11));
//              acDisconnectLibraryService.editAcCombinerDiscoFavoriteList((Integer)scenario.get(i).get(0),(Boolean)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6),(String)scenario.get(i).get(7),(String)scenario.get(i).get(8));
//
//		 }
//
//	}
//
//	@Test
//	public void testgetUsersForFavList() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnectFavLibraryEntity u WHERE u.aCDisconnect.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult ( "
//					+ "   u.id,  "
//					+ "   u.firstName,  "
//					+ "   u.lastName ) "
//					+ " from AuthentificationEntity u WHERE u.id NOT IN :p1 and u.deleted = :p2 and u.active = :p3 and u.id != :p4 ORDER BY u.firstName")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult ( "
//					+ "   u.id,  "
//					+ "   u.firstName,  "
//					+ "   u.lastName ) "
//					+ " from AuthentificationEntity u WHERE u.deleted = :p2 and u.active = :p3 and u.id != :p4 ORDER BY u.firstName")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
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
//			 scenario.get(1).add(new ArrayList<ACDisconnectFavLibraryEntity>());
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
//				 scenario.get(2).add(new ArrayList<ACDisconnectFavLibraryEntity>());
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
//					 scenario.get(3).add(new ArrayList<ACDisconnectFavLibraryEntity>());
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
//						ArrayList<ACDisconnectFavLibraryEntity> list = new ArrayList<ACDisconnectFavLibraryEntity>();
//						list.add(null);
//						list.add(new ACDisconnectFavLibraryEntity());
//						ACDisconnectFavLibraryEntity ac = new ACDisconnectFavLibraryEntity();
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
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//             when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//             when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(4));
//           acDisconnectLibraryService.getUsersForFavList((Integer)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	}
//
//	@Test
//	public void testeditUsersFavoriteList() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.id = :p1")).thenReturn(mockedQuery1);
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
//		 scenario.get(3).add(new ACDisconnect());
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
//		 scenario.get(4).add(new ACDisconnect());
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
//              acDisconnectLibraryService.editUsersFavoriteList((Integer)scenario.get(i).get(0),(String[])scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6),(String)scenario.get(i).get(7));
//
//		 }
//
//	}
//
//	@Test
//	public void testcheckACCombinerExistent() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.model = :p1 AND u.manufacturer = :p2 AND u.isDeleted = false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.model = :p1 AND u.manufacturer != :p2 AND u.isDeleted = false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnectFavLibraryEntity u WHERE u.aCDisconnect.id = :p1")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//		 Query mockedQuery4 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnectFavLibraryEntity u WHERE u.aCDisconnect.id = :p1")).thenReturn(mockedQuery4);
//		 when(mockedQuery4.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery4);
//         ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 AcDisconnectLibraryService acDisconnectLibraryService2 = Mockito.spy(acDisconnectLibraryService);
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
//		 //Result of the query4
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new ArrayList<AcDisconnectModel>());
//
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(new NewDisconnectModel());
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//		 //Result of the query3
//		 scenario.get(1).add(null);
//		 //Result of the query4
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<AcDisconnectModel>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add(new NewDisconnectModel());
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 scenario.get(2).add(new ArrayList<ACDisconnect>());
//		 //Result of the query2
//		 scenario.get(2).add(null);
//		 //Result of the query3
//		 scenario.get(2).add(null);
//		 //Result of the query4
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add(new ArrayList<AcDisconnectModel>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add(new NewDisconnectModel());
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 ArrayList<ACDisconnect> listQ1 =new ArrayList<ACDisconnect>();
//		 listQ1.add(null);
//		 listQ1.add(new ACDisconnect());
//		 ACDisconnect ac = new ACDisconnect();
//		 ac.setIdOwner(new AuthentificationEntity());
//		 listQ1.add(ac);
//		 scenario.get(3).add(listQ1);
//		 //Result of the query2
//		 scenario.get(3).add(null);
//		 //Result of the query3
//		 scenario.get(3).add(null);
//		 //Result of the query4
//		 scenario.get(3).add(null);
//	     //Result excpected
//		 scenario.get(3).add(new ArrayList<AcDisconnectModel>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add(new NewDisconnectModel());
//		 scenario.get(4).add(null);
//		 //Result of the query1
//		 scenario.get(4).add(null);
//		 //Result of the query2
//		 scenario.get(4).add(new ArrayList<ACDisconnect>());
//		 //Result of the query3
//		 scenario.get(4).add(null);
//		 //Result of the query4
//		 scenario.get(4).add(null);
//	     //Result excpected
//		 scenario.get(4).add(new ArrayList<AcDisconnectModel>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(5).add(new NewDisconnectModel());
//		 scenario.get(5).add(null);
//		 //Result of the query1
//		 scenario.get(5).add(null);
//		 //Result of the query2
//		 scenario.get(5).add(listQ1);
//		 //Result of the query3
//		 scenario.get(5).add(null);
//		 //Result of the query4
//		 scenario.get(5).add(null);
//	     //Result excpected
//		 scenario.get(5).add(new ArrayList<AcDisconnectModel>());
//
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("checkACCombinerExistent [ "+i+" ]");
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//             when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//             when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(4));
//             when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			 Mockito.doReturn(new ArrayList<Integer>()).when(acDisconnectLibraryService2).getAcCombinerDiscoFavorite(Mockito.anyString());
//			 if(i == 3 || i == 5) {
//	           acDisconnectLibraryService2.checkACCombinerExistent((NewDisconnectModel)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//			 }else
//                acDisconnectLibraryService2.checkACCombinerExistent((NewDisconnectModel)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//
//	}
//
//	@Test
//	public void testaddAcCombinerDisco() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u.authentificationEntity from PermitEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
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
//		 scenario.get(0).add(new ACDisconnect());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add("");
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ACDisconnect());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add("12123");
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 scenario.get(2).add(null);
//		 //Result of the query2
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add(new ACDisconnect());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add(new NewDisconnectModel());
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add("12123");
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 scenario.get(3).add(null);
//		 //Result of the query2
//		 scenario.get(3).add(null);
//	     //Result excpected
//		 scenario.get(3).add(new ACDisconnect());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add(new NewDisconnectModel());
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add("12123");
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 //Result of the query1
//		 scenario.get(4).add(new AuthentificationEntity());
//		 //Result of the query2
//		 scenario.get(4).add(null);
//	     //Result excpected
//		 scenario.get(4).add(new ACDisconnect());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(5).add(new NewDisconnectModel());
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add("12123");
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 //Result of the query1
//		 AuthentificationEntity auth = new AuthentificationEntity();
//		 auth.setRoleEntity(new RoleEntity());
//		 scenario.get(5).add(auth);
//		 //Result of the query2
//		 scenario.get(5).add(null);
//	     //Result excpected
//		 scenario.get(5).add(new ACDisconnect());
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("addAcCombinerDisco [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(8));
//             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(9));
//             Assert.assertNotNull(acDisconnectLibraryService.addAcCombinerDisco((NewDisconnectModel)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(Integer)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6),(String)scenario.get(i).get(6)));
//
//		 }
//
//
//	}
//
//	@Test
//	public void testeditAcCombinerDisco() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM PermitProjectSiteInfoEntity u WHERE u.roofTopACCombinerDisconnect = :p1 OR u.rooftopACCombinerModel = :p1 OR u.rooftopACCombinerModelTwo = :p1 OR u.groundLevelACCombinerDisconnectModel = :p1 OR u.groundLevelACCombinerBoxModel = :p1")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//		 Query mockedQuery4 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery4);
//		 when(mockedQuery4.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery4);
//
//
//		 Query mockedQuery6 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery6);
//		 when(mockedQuery6.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery6);
//
//         ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(0).add(null);
//         scenario.get(0).add(null);
//         scenario.get(0).add(null);
//         scenario.get(0).add(null);
//         scenario.get(0).add(null);
//         scenario.get(0).add(null);
//         scenario.get(0).add(null);
//         //The result of Query1
//         scenario.get(0).add(null);
//        //The result of Query2
//         scenario.get(0).add(null);
//        //The result of Query3
//         scenario.get(0).add(null);
//        //The result of Query4
//         scenario.get(0).add(null);
//        //The result of Query5
//         scenario.get(0).add(null);
//        //The result of Query6
//         scenario.get(0).add(null);
//         //The result expected
//         scenario.get(0).add("error");
//       //The result of Query2.2
//         scenario.get(0).add(null);
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(1).add(new AcDisconnectModel());
//         scenario.get(1).add(null);
//         scenario.get(1).add(null);
//         scenario.get(1).add(null);
//         scenario.get(1).add(null);
//         scenario.get(1).add(null);
//         scenario.get(1).add(null);
//         //The result of Query1
//         scenario.get(1).add(null);
//        //The result of Query2
//         scenario.get(1).add(null);
//        //The result of Query3
//         scenario.get(1).add(null);
//        //The result of Query4
//         scenario.get(1).add(null);
//        //The result of Query5
//         scenario.get(1).add(null);
//        //The result of Query6
//         scenario.get(1).add(null);
//         //The result expected
//         scenario.get(1).add("error");
//       //The result of Query2.2
//         scenario.get(1).add(null);
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(2).add(new AcDisconnectModel());
//         scenario.get(2).add(null);
//         scenario.get(2).add(null);
//         scenario.get(2).add(null);
//         scenario.get(2).add(null);
//         scenario.get(2).add(null);
//         scenario.get(2).add(null);
//         //The result of Query1
//         scenario.get(2).add(null);
//        //The result of Query2
//         scenario.get(2).add(new ArrayList<ACDisconnect>());
//        //The result of Query3
//         scenario.get(2).add(null);
//        //The result of Query4
//         scenario.get(2).add(null);
//        //The result of Query5
//         scenario.get(2).add(null);
//        //The result of Query6
//         scenario.get(2).add(null);
//         //The result expected
//         scenario.get(2).add("error");
//       //The result of Query2.2
//         scenario.get(2).add(null);
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(3).add(new AcDisconnectModel());
//         scenario.get(3).add(null);
//         scenario.get(3).add(null);
//         scenario.get(3).add(null);
//         scenario.get(3).add(null);
//         scenario.get(3).add(null);
//         scenario.get(3).add(null);
//         //The result of Query1
//         scenario.get(3).add(new ACDisconnect());
//        //The result of Query2
//         scenario.get(3).add(new ArrayList<ACDisconnect>());
//        //The result of Query3
//         scenario.get(3).add(null);
//        //The result of Query4
//         scenario.get(3).add(null);
//        //The result of Query5
//         scenario.get(3).add(null);
//        //The result of Query6
//         scenario.get(3).add(null);
//         //The result expected
//         scenario.get(3).add("error");
//       //The result of Query2.2
//         scenario.get(3).add(null);
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(4).add(new AcDisconnectModel());
//         scenario.get(4).add("");
//         scenario.get(4).add("");
//         scenario.get(4).add("");
//         scenario.get(4).add("");
//         scenario.get(4).add("");
//         scenario.get(4).add("");
//         //The result of Query1
//         scenario.get(4).add(new ACDisconnect());
//        //The result of Query2
//         scenario.get(4).add(new ArrayList<ACDisconnect>());
//        //The result of Query3
//         scenario.get(4).add(null);
//        //The result of Query4
//         scenario.get(4).add(null);
//        //The result of Query5
//         scenario.get(4).add(null);
//        //The result of Query6
//         scenario.get(4).add(null);
//         //The result expected
//         scenario.get(4).add("error");
//       //The result of Query2.2
//         scenario.get(4).add(null);
//
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(5).add(new AcDisconnectModel());
//         scenario.get(5).add("123");
//         scenario.get(5).add("123");
//         scenario.get(5).add("123");
//         scenario.get(5).add("123");
//         scenario.get(5).add("123");
//         scenario.get(5).add("123");
//         //The result of Query1
//         scenario.get(5).add(new ACDisconnect());
//        //The result of Query2
//         scenario.get(5).add(new ArrayList<ACDisconnect>());
//        //The result of Query3
//         scenario.get(5).add(null);
//        //The result of Query4
//         scenario.get(5).add(null);
//        //The result of Query5
//         scenario.get(5).add(null);
//        //The result of Query6
//         scenario.get(5).add(null);
//         //The result expected
//         scenario.get(5).add("error");
//         //The result of Query2.2
//         scenario.get(5).add(null);
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(6).add(new AcDisconnectModel());
//         scenario.get(6).add("123");
//         scenario.get(6).add("123");
//         scenario.get(6).add("123");
//         scenario.get(6).add("123");
//         scenario.get(6).add("123");
//         scenario.get(6).add("123");
//         //The result of Query1
//         scenario.get(6).add(new ACDisconnect());
//        //The result of Query2
//         scenario.get(6).add(new ArrayList<ACDisconnect>());
//        //The result of Query3
//         scenario.get(6).add(null);
//        //The result of Query4
//         scenario.get(6).add(null);
//        //The result of Query5
//         scenario.get(6).add(null);
//        //The result of Query6
//         scenario.get(6).add(new AuthentificationEntity());
//         //The result expected
//         scenario.get(6).add("success");
//         //The result of Query2.2
//         scenario.get(6).add(null);
//
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(7).add(new AcDisconnectModel());
//         scenario.get(7).add(null);
//         scenario.get(7).add(null);
//         scenario.get(7).add(null);
//         scenario.get(7).add(null);
//         scenario.get(7).add(null);
//         scenario.get(7).add(null);
//         //The result of Query1
//         scenario.get(7).add(new ACDisconnect());
//        //The result of Query2
//         ArrayList<ACDisconnect> list = new ArrayList<ACDisconnect>();
//         ACDisconnect ac1 = new ACDisconnect();
//         ac1.setModel("");
//         list.add(ac1);
//         scenario.get(7).add(list);
//        //The result of Query3
//         scenario.get(7).add(null);
//        //The result of Query4
//         scenario.get(7).add(null);
//        //The result of Query5
//         scenario.get(7).add(null);
//        //The result of Query6
//         scenario.get(7).add(new AuthentificationEntity());
//         //The result expected
//         scenario.get(7).add("error");
//         //The result of Query2.2
//         scenario.get(7).add(ac1);
//
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(8).add(new AcDisconnectModel());
//         scenario.get(8).add("123");
//         scenario.get(8).add("123");
//         scenario.get(8).add("123");
//         scenario.get(8).add("123");
//         scenario.get(8).add("123");
//         scenario.get(8).add("123");
//         //The result of Query1
//         scenario.get(8).add(new ACDisconnect());
//        //The result of Query2
//         scenario.get(8).add(list);
//        //The result of Query3
//         scenario.get(8).add(null);
//        //The result of Query4
//         scenario.get(8).add(null);
//        //The result of Query5
//         scenario.get(8).add(null);
//        //The result of Query6
//         scenario.get(8).add(new AuthentificationEntity());
//         //The result expected
//         scenario.get(8).add("success");
//         //The result of Query2.2
//         scenario.get(8).add(ac1);
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         AcDisconnectModel acModel = new AcDisconnectModel();
//         acModel.setId(125555);
//         scenario.get(9).add(acModel);
//         scenario.get(9).add("123");
//         scenario.get(9).add("123");
//         scenario.get(9).add("123");
//         scenario.get(9).add("123");
//         scenario.get(9).add("123");
//         scenario.get(9).add("123");
//         //The result of Query1
//         scenario.get(9).add(new ACDisconnect());
//        //The result of Query2
//         scenario.get(9).add(list);
//        //The result of Query3
//         scenario.get(9).add(null);
//        //The result of Query4
//         scenario.get(9).add(null);
//        //The result of Query5
//         scenario.get(9).add(null);
//        //The result of Query6
//         scenario.get(9).add(new AuthentificationEntity());
//         //The result expected
//         scenario.get(9).add("exist");
//         //The result of Query2.2
//         scenario.get(9).add(ac1);
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(10).add(new AcDisconnectModel());
//         scenario.get(10).add("123");
//         scenario.get(10).add("123");
//         scenario.get(10).add("123");
//         scenario.get(10).add("123");
//         scenario.get(10).add("123");
//         scenario.get(10).add("123");
//         //The result of Query1
//         scenario.get(10).add(null);
//        //The result of Query2
//         scenario.get(10).add(list);
//        //The result of Query3
//         scenario.get(10).add(null);
//        //The result of Query4
//         scenario.get(10).add(null);
//        //The result of Query5
//         scenario.get(10).add(null);
//        //The result of Query6
//         scenario.get(10).add(new AuthentificationEntity());
//         //The result expected
//         scenario.get(10).add("error");
//         //The result of Query2.2
//         scenario.get(10).add(ac1);
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(11).add(new AcDisconnectModel());
//         scenario.get(11).add("123");
//         scenario.get(11).add("123");
//         scenario.get(11).add("123");
//         scenario.get(11).add("123");
//         scenario.get(11).add("123");
//         scenario.get(11).add("123");
//         //The result of Query1
//         scenario.get(11).add(new ACDisconnect());
//        //The result of Query2
//         scenario.get(11).add(list);
//        //The result of Query3
//         List<PermitProjectSiteInfoEntity> listProject = new  ArrayList<PermitProjectSiteInfoEntity>();
//         listProject.add(null);
//         listProject.add(new PermitProjectSiteInfoEntity());
//         scenario.get(11).add(listProject);
//        //The result of Query4
//         scenario.get(11).add(null);
//        //The result of Query5
//         scenario.get(11).add(null);
//        //The result of Query6
//         scenario.get(11).add(new AuthentificationEntity());
//         //The result expected
//         scenario.get(11).add("error");
//         //The result of Single Result Query2
//         scenario.get(11).add(ac1);
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(12).add(new AcDisconnectModel());
//         scenario.get(12).add("123");
//         scenario.get(12).add("123");
//         scenario.get(12).add("123");
//         scenario.get(12).add("123");
//         scenario.get(12).add("123");
//         scenario.get(12).add("123");
//         //The result of Query1
//         scenario.get(12).add(new ACDisconnect());
//        //The result of Query2
//         scenario.get(12).add(new ArrayList<ACDisconnect>());
//        //The result of Query3
//         scenario.get(12).add(listProject);
//        //The result of Query4
//         scenario.get(12).add(null);
//        //The result of Query5
//         scenario.get(12).add(null);
//        //The result of Query6
//         scenario.get(12).add(new AuthentificationEntity());
//         //The result expected
//         scenario.get(12).add("error");
//         //The result of Single Result Query2
//         scenario.get(12).add(ac1);
//
//
//
//         for(int i=0;i<scenario.size();i++) {
//        	 System.out.println("editAcCombinerDisco [ "+i+" ]"+scenario.get(i).get(8));
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//             when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(8));
//              when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(14));
//             when(mockedQuery6.getSingleResult()).thenReturn(scenario.get(i).get(12));
//             when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(9));
//             acDisconnectLibraryService.editAcCombinerDisco((AcDisconnectModel)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6));
//
//         }
//
//
//	}
//
//	@Test
//	public void testgetRemoveAcCombinerDiscoConfirmation() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel  "
//					+ " ( u.homeOwnName, "
//					+ " u.status, "
//					+ " v.firstName, "
//					+ " v.lastName)"
//					+ " from PermitEntity u, "
//					+ " AuthentificationEntity v, "
//					+ " PermitProjectSiteInfoEntity w"
//					+ " where (w.groundLevelACCombinerBoxModel = :p1 or w.groundLevelACCombinerDisconnectModel = :p1 or "
//					+ "w.rooftopACCombinerModel = :p1 or w.rooftopACCombinerModelTwo = :p1) "
//					+ "and  u.isDeleted  = :p2 and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = :p2 and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		     ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(0).add(null);
//			 //Result of the query1
//			 scenario.get(0).add(null);
//		     //Result excpected
//			 scenario.get(0).add(new ArrayList<ProjectForLibrariesModel>());
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(1).add(new AcDisconnectModel());
//			 //Result of the query1
//			 scenario.get(1).add(null);
//		     //Result excpected
//			 scenario.get(1).add(new ArrayList<ProjectForLibrariesModel>());
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(2).add(new AcDisconnectModel());
//			 //Result of the query1
//			 ArrayList<ProjectForLibrariesModel> list = new ArrayList<ProjectForLibrariesModel>();
//			 list.add(null);
//			 list.add(new ProjectForLibrariesModel());
//			 scenario.get(2).add(list);
//		     //Result excpected
//			 scenario.get(2).add(list);
//
//
//
//			 for(int i=0;i<scenario.size();i++) {
//				 System.out.println("getRemoveAcCombinerDiscoConfirmation [ "+i+" ]");
//	             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//	             acDisconnectLibraryService.getRemoveAcCombinerDiscoConfirmation((AcDisconnectModel)scenario.get(i).get(0));
//
//			 }
//	}
//
//	@Test
//	public void testdeleteAcCombinerDisco() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from PermitProjectSiteInfoEntity u WHERE (u.groundLevelACCombinerBoxModel = :p1 or u.groundLevelACCombinerDisconnectModel = :p1 or "
//					+ "u.rooftopACCombinerModel = :p1 or u.rooftopACCombinerModelTwo = :p1 or u.roofTopACDisco = :p1) "
//					+ "and  u.permitEntity.isDeleted  = :p2")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnectFavLibraryEntity u WHERE u.aCDisconnect.id = :p1")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//		 //List of scenario possible
//		 List<List<Object>> scenario = new ArrayList<List<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //Parameter possible
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		//Result possible of getList result of query1
//		 scenario.get(0).add(null);
//		//Result possible of getSingle result of query2
//		 scenario.get(0).add(null);
//		//Result possible of getSingle result of query3
//		 scenario.get(0).add(null);
//		 //Result that we expected
//		 scenario.get(0).add("error");
//
//		 scenario.add(new ArrayList<Object>());
//		 //Parameter possible
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		//Result possible of getList result of query1
//		 scenario.get(1).add(null);
//		//Result possible of getSingle result of query2
//		 scenario.get(1).add(null);
//		//Result possible of getSingle result of query3
//		 scenario.get(1).add(null);
//		 //Result that we expected
//		 scenario.get(1).add("error");
//
//		 scenario.add(new ArrayList<Object>());
//		 //Parameter possible
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("1232");
//		 scenario.get(2).add("123");
//		//Result possible of getList result of query1
//		 scenario.get(2).add(null);
//		//Result possible of getSingle result of query2
//		 scenario.get(2).add(null);
//		//Result possible of getSingle result of query3
//		 scenario.get(2).add(null);
//		 //Result that we expected
//		 scenario.get(2).add("error");
//
//		 scenario.add(new ArrayList<Object>());
//		 //Parameter possible
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("1232");
//		 scenario.get(3).add("123");
//		//Result possible of getList result of query1
//		 ACDisconnect ac1 = new ACDisconnect();
//		 scenario.get(3).add(ac1);
//		//Result possible of getSingle result of query2
//		 scenario.get(3).add(null);
//		//Result possible of getSingle result of query3
//		 scenario.get(3).add(null);
//		 //Result that we expected
//		 scenario.get(3).add("error");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //Parameter possible
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("1232");
//		 scenario.get(4).add("123");
//		//Result possible of getList result of query1
//		 ACDisconnect ac2 = new ACDisconnect();
//		 ac2.setManufacturer("");
//		 ac2.setModel("");
//		 ac2.setId(123);
//		 scenario.get(4).add(ac2);
//		//Result possible of getSingle result of query2
//		 scenario.get(4).add(null);
//		//Result possible of getSingle result of query3
//		 scenario.get(4).add(null);
//		 //Result that we expected
//		 scenario.get(4).add("error");
//
//		 scenario.add(new ArrayList<Object>());
//		 //Parameter possible
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("1232");
//		 scenario.get(5).add("123");
//		//Result possible of getList result of query1
//		 ACDisconnect ac3 = new ACDisconnect();
//		 ac2.setManufacturer("");
//		 ac2.setModel("");
//		 ac2.setId(123);
//		 scenario.get(5).add(ac3);
//		//Result possible of getSingle result of query2
//		 List<PermitProjectSiteInfoEntity> listAcCombiner = new ArrayList<PermitProjectSiteInfoEntity>();
//		 PermitProjectSiteInfoEntity permit1 = new PermitProjectSiteInfoEntity();
//		 listAcCombiner.add(permit1);
//		 PermitProjectSiteInfoEntity permit2 = new PermitProjectSiteInfoEntity();
//		 permit2.setGroundLevelACCombinerBoxModel("");
//		 permit2.setId(123);
//		 permit2.setRooftopACCombinerModel("");
//		 permit2.setRoofTopACCombiner("");
//		 listAcCombiner.add(permit2);
//		 scenario.get(5).add(listAcCombiner);
//		//Result possible of getSingle result of query3
//		 scenario.get(5).add(null);
//		 //Result that we expected
//		 scenario.get(5).add("error");
//
//		 scenario.add(new ArrayList<Object>());
//		 //Parameter possible
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("1232");
//		 scenario.get(6).add("123");
//		//Result possible of getList result of query1
//		 scenario.get(6).add(ac3);
//		//Result possile of getSingle result of query2
//		 scenario.get(6).add(listAcCombiner);
//		//Result possible of getSingle result of query3
//		 List<ACDisconnectFavLibraryEntity> acCombinerFavList = new ArrayList<ACDisconnectFavLibraryEntity>();
//		 acCombinerFavList.add(null);
//		 ACDisconnectFavLibraryEntity acFav = new ACDisconnectFavLibraryEntity();
//		 acCombinerFavList.add(acFav);
//		 ACDisconnectFavLibraryEntity acFav1 = new ACDisconnectFavLibraryEntity();
//		 acFav1.setId(12);
//		 acCombinerFavList.add(acFav1);
//		 scenario.get(6).add(acCombinerFavList);
//		 //Result that we expected
//		 scenario.get(6).add("Done");
//
//		 for(int i=0;i<scenario.size();i++) {
//             System.out.println("deleteAcCombinerDisco [ "+i+" ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(8));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(9));
//			 acDisconnectLibraryService.deleteAcCombinerDisco((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6));
//
//		 }
//
//	}
//
//	@Test
//	public void testactivateAcCombinerDisco() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//
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
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//
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
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//
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
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//
//		 //The result of query1
//		 ACDisconnect ac1 = new ACDisconnect();
//		 scenario.get(3).add(ac1);
//		 //The result of the query2
//		 scenario.get(3).add(null);
//		 //The result excpected
//		 scenario.get(3).add("error");
//
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//
//		 //The result of query1
//		 scenario.get(4).add(null);
//		 //The result of the query2
//		 scenario.get(4).add(null);
//		 //The result excpected
//		 scenario.get(4).add("error");
//
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//
//		 //The result of query1
//		 ACDisconnect acCombiner = new ACDisconnect();
//		 acCombiner.setManufacturer("");
//		 acCombiner.setModel("");
//		 scenario.get(5).add(acCombiner);
//		 //The result of the query2
//		 scenario.get(5).add(null);
//		 //The result excpected
//		 scenario.get(5).add("error");
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//
//		 //The result of query1
//		 scenario.get(6).add(acCombiner);
//		 //The result of the query2
//		 scenario.get(6).add(new ArrayList<ACCombinerSLC>());
//		 //The result excpected
//		 scenario.get(6).add("Done");
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(7).add("123");
//		 scenario.get(7).add("123");
//		 scenario.get(7).add("123");
//		 scenario.get(7).add("123");
//		 scenario.get(7).add("123");
//		 scenario.get(7).add("123");
//		 scenario.get(7).add("123");
//
//		 //The result of query1
//		 scenario.get(7).add(acCombiner);
//		 //The result of the query2
//		 ArrayList<ACDisconnect> listAcCom= new ArrayList<ACDisconnect>();
//		 listAcCom.add(new ACDisconnect());
//		 scenario.get(7).add(listAcCom);
//		 //The result excpected
//		 scenario.get(7).add("exist");
//
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("activateAcCombinerDisco [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(8));
//			 acDisconnectLibraryService.activateAcCombinerDisco((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6));
//
//		 }
//	}
//
//	@Test
//	public void testgetSearchAcCombinerDisco() {
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(ACDisconnect.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(ACDisconnect.class)).thenReturn(root);
//        when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//
//        TypedQuery mockedQuery1 = mock(TypedQuery.class);
//        when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//		AcDisconnectLibraryService acDisconnectLibraryService2 = Mockito.spy(acDisconnectLibraryService);
//
//		//Any of the Query Res possible
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.idOwner.id = :p1 and u.isDeleted = :p2 and u.disconnectDeviceType = :p3 and u.ratedCurrent = :p4")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.idOwner.id = :p1 and u.isDeleted = :p2 and u.disconnectDeviceType = :p3")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//		 Query mockedQuery4 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.idOwner.id = :p1 and u.isDeleted = :p2 and u.ratedCurrent = :p3")).thenReturn(mockedQuery4);
//		 when(mockedQuery4.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery4);
//
//		 Query mockedQuery5 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.idOwner.id = :p1 and u.isDeleted = :p2")).thenReturn(mockedQuery5);
//		 when(mockedQuery5.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery5);
//
//		//Any of the Query Res2 possible
//		 Query mockedQuery6 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnectFavLibraryEntity u WHERE u.aCDisconnect.id = :p1")).thenReturn(mockedQuery6);
//		 when(mockedQuery6.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery6);
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(0).add(null);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(0).add(null);
//		//The result of the Query RES2
//		 scenario.get(0).add(null);
//		 //The result expected
//		 scenario.get(0).add(new ArrayList<AcDisconnectModel>());
//
//		 //List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(1).add(new ArrayList<ACDisconnect>());
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(1).add(null);
//		//The result of the Query RES2
//		 scenario.get(1).add(null);
//		 //The result expected
//		 scenario.get(1).add(new ArrayList<AcDisconnectModel>());
//
//		 //List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 ArrayList<ACDisconnect> listACDisco =  new ArrayList<ACDisconnect>();
//		 listACDisco.add(null);
//		 listACDisco.add(new ACDisconnect());
//		 ACDisconnect ac1 = new ACDisconnect();
//		 ac1.setIdOwner(new AuthentificationEntity());
//		 ac1.setMaxInput("");
//		 listACDisco.add(ac1);
//		 scenario.get(2).add(listACDisco);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(2).add(null);
//		//The result of the Query RES2
//		 scenario.get(2).add(null);
//		 //The result expected
//		 scenario.get(2).add(new ArrayList<AcDisconnectModel>());
//
//		 //List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(3).add(new AcCombinerDiscoModelRequest());
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(3).add(listACDisco);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(3).add(null);
//		//The result of the Query RES2
//		 scenario.get(3).add(null);
//		 //The result expected
//		 scenario.get(3).add(new ArrayList<AcDisconnectModel>());
//
//		 //List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(4).add(new AcCombinerDiscoModelRequest());
//		 scenario.get(4).add(null);
//		 scenario.get(4).add("");
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(4).add(listACDisco);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(4).add(null);
//		//The result of the Query RES2
//		 scenario.get(4).add(null);
//		 //The result expected
//		 scenario.get(4).add(new ArrayList<AcDisconnectModel>());
//
//		//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(5).add(new AcCombinerDiscoModelRequest());
//		 scenario.get(5).add(null);
//		 scenario.get(5).add("123");
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(5).add(listACDisco);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(5).add(null);
//		//The result of the Query RES2
//		 scenario.get(5).add(null);
//		 //The result expected
//		 scenario.get(5).add(new ArrayList<AcDisconnectModel>());
//
//			//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(6).add(new AcCombinerDiscoModelRequest());
//		 scenario.get(6).add(null);
//		 scenario.get(6).add("123");
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(6).add(listACDisco);
//		//The result of the Query2 (Daynamic Query)
//		 List<ACDisconnect> listACDisco2 = new ArrayList<ACDisconnect>();
//		 listACDisco2.add(null);
//		 listACDisco2.add(new ACDisconnect());
//		 ACDisconnect ac2 = new ACDisconnect();
//		 ac2.setIdOwner(new AuthentificationEntity());
//		 listACDisco2.add(ac2);
//		 scenario.get(6).add(listACDisco2);
//		//The result of the Query RES2
//		 scenario.get(6).add(null);
//		 //The result expected
//		 scenario.get(6).add(new ArrayList<AcDisconnectModel>());
//
//		//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 AcCombinerDiscoModelRequest acCombModel = new AcCombinerDiscoModelRequest();
//		 acCombModel.setModel("test aaa hhh_cccc ddd_eeee");
//		 scenario.get(7).add(acCombModel);
//		 scenario.get(7).add(null);
//		 scenario.get(7).add("123");
//		 scenario.get(7).add(null);
//		 scenario.get(7).add(null);
//		 scenario.get(7).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(7).add(listACDisco);
//		//The result of the Query2 (Daynamic Query)
//		 List<ACDisconnect> listACDisco3 = new ArrayList<ACDisconnect>();
//		 listACDisco3.add(ac2);
//		 scenario.get(7).add(listACDisco3);
//		//The result of the Query RES2
//		 scenario.get(7).add(null);
//		 //The result expected
//		 scenario.get(7).add(new ArrayList<AcDisconnectModel>());
//
//			//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 acCombModel.setModel("test aaa hhh_cccc ddd_eeee");
//		 scenario.get(8).add(acCombModel);
//		 scenario.get(8).add(null);
//		 scenario.get(8).add("123");
//		 scenario.get(8).add(null);
//		 scenario.get(8).add(null);
//		 scenario.get(8).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(8).add(listACDisco);
//		//The result of the Query RES
//		 List<ACDisconnect> listACDisco4 = new ArrayList<ACDisconnect>();
//		 ACDisconnect ac3 = new ACDisconnect();
//		 ac3.setModel("hhh_cccc");
//		 ac3.setManufacturer("aaa_hhh");
//		 ac3.setId(12589);
//		 ac3.setIdOwner(new AuthentificationEntity());
//		 listACDisco4.add(ac3);
//		 scenario.get(8).add(listACDisco4);
//		//The result of the Query RES2
//		 scenario.get(8).add(null);
//		 //The result expected
//		 scenario.get(8).add(new ArrayList<AcDisconnectModel>());
//
//		//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 acCombModel.setModel("test aaa hhh_cccc ddd_eeee");
//		 scenario.get(9).add(acCombModel);
//		 scenario.get(9).add(null);
//		 scenario.get(9).add("123");
//		 scenario.get(9).add(null);
//		 scenario.get(9).add(null);
//		 scenario.get(9).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(9).add(listACDisco);
//		//The result of the Query RES
//		 scenario.get(9).add(listACDisco4);
//		//The result of the Query RES2
//		 ArrayList<ACDisconnectFavLibraryEntity> listACFav = new ArrayList<ACDisconnectFavLibraryEntity>();
//		 listACFav.add(null);
//		 listACFav.add(new ACDisconnectFavLibraryEntity());
//		 scenario.get(9).add(listACFav);
//		 //The result expected
//		 scenario.get(9).add(new ArrayList<AcDisconnectModel>());
//
//		//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 AcCombinerDiscoModelRequest acCombModel2 = new AcCombinerDiscoModelRequest();
//		 acCombModel2.setManufacturer("test aaa aaa_hhh");
//		 acCombModel2.setModel("test aaa hhh_cccc ddd_eeee");
//		 scenario.get(10).add(acCombModel2);
//		 scenario.get(10).add(null);
//		 scenario.get(10).add("123");
//		 scenario.get(10).add(null);
//		 scenario.get(10).add(null);
//		 scenario.get(10).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(10).add(listACDisco);
//		//The result of the Query RES
//		 scenario.get(10).add(listACDisco4);
//		//The result of the Query RES2
//		 scenario.get(10).add(null);
//		 //The result expected
//		 scenario.get(10).add(new ArrayList<AcDisconnectModel>());
//
//
//		//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(11).add(acCombModel2);
//		 scenario.get(11).add(null);
//		 scenario.get(11).add("");
//		 scenario.get(11).add(null);
//		 scenario.get(11).add(null);
//		 scenario.get(11).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(11).add(listACDisco);
//		//The result of the Query RES
//		 List<ACDisconnect> listACDisco5 = new ArrayList<ACDisconnect>();
//		 ACDisconnect ac4 = new ACDisconnect();
//		 ac4.setModel("xxxx");
//		 ac4.setManufacturer("aaa_hhh");
//		 ac4.setId(12589);
//		 ac4.setIdOwner(new AuthentificationEntity());
//		 listACDisco5.add(ac4);
//		 scenario.get(11).add(listACDisco5);
//		//The result of the Query RES2
//		 scenario.get(11).add(null);
//		 //The result expected
//		 scenario.get(11).add(new ArrayList<AcDisconnectModel>());
//
//
//		//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 AcCombinerDiscoModelRequest acCombModel4 = new AcCombinerDiscoModelRequest();
//		 acCombModel4.setIsShown(true);
//		 scenario.get(12).add(acCombModel4);
//		 scenario.get(12).add(null);
//		 scenario.get(12).add("123");
//		 scenario.get(12).add(null);
//		 scenario.get(12).add(null);
//		 scenario.get(12).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(12).add(listACDisco);
//		//The result of the Query RES
//		 scenario.get(12).add(null);
//		//The result of the Query RES2
//		 scenario.get(12).add(null);
//		 //The result expected
//		 scenario.get(12).add(new ArrayList<AcDisconnectModel>());
//
//			//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(13).add(acCombModel4);
//		 scenario.get(13).add(null);
//		 scenario.get(13).add("123");
//		 scenario.get(13).add(null);
//		 scenario.get(13).add(null);
//		 scenario.get(13).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(13).add(listACDisco);
//		//The result of the Query RES
//		 List<ACDisconnect> listACDisco6 = new ArrayList<ACDisconnect>();
//		 listACDisco6.add(null);
//		 listACDisco6.add(new ACDisconnect());
//		 ACDisconnect ac5 = new ACDisconnect();
//		 ac5.setIdOwner(new AuthentificationEntity());
//		 listACDisco6.add(ac5);
//		 scenario.get(13).add(listACDisco6);
//		//The result of the Query RES2
//		 scenario.get(13).add(null);
//		 //The result expected
//		 scenario.get(13).add(new ArrayList<AcDisconnectModel>());
//
//		//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 AcCombinerDiscoModelRequest acCombModel6 = new AcCombinerDiscoModelRequest();
//		 acCombModel6.setIsShown(true);
//		 acCombModel6.setModel("ab_aa bb_bb");
//		 acCombModel6.setManufacturer("cc_cc dd_dd");
//		 scenario.get(14).add(acCombModel6);
//		 scenario.get(14).add(null);
//		 scenario.get(14).add("123");
//		 scenario.get(14).add(null);
//		 scenario.get(14).add(null);
//		 scenario.get(14).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(14).add(listACDisco);
//		//The result of the Query RES
//		 List<ACDisconnect> listACDisco7 = new ArrayList<ACDisconnect>();
//		 ACDisconnect ac6 = new ACDisconnect();
//		 ac6.setModel("ab_aa");
//		 ac6.setManufacturer("cc_cc");
//		 ac6.setId(5896665);
//		 listACDisco7.add(ac6);
//		 scenario.get(14).add(listACDisco7);
//		//The result of the Query RES2
//		 scenario.get(14).add(null);
//		 //The result expected
//		 scenario.get(14).add(new ArrayList<AcDisconnectModel>());
//
//
//			//List of the parameter
//			 scenario.add(new ArrayList<Object>());
//			 AcCombinerDiscoModelRequest acCombModel5 = new AcCombinerDiscoModelRequest();
//			 acCombModel5.setModel("ab_aa bb_bb");
//			 acCombModel5.setManufacturer("cc_cc dd_dd");
//			 acCombModel5.setIsShown(true);
//			 scenario.get(15).add(acCombModel5);
//			 scenario.get(15).add(null);
//			 scenario.get(15).add("123");
//			 scenario.get(15).add(null);
//			 scenario.get(15).add(null);
//			 scenario.get(15).add(null);
//			 //The result of the Query1 (Daynamic Query)
//			 scenario.get(15).add(listACDisco);
//			//The result of the Query RES
//			 scenario.get(15).add(listACDisco6);
//			//The result of the Query RES2
//			 scenario.get(15).add(null);
//			 //The result expected
//			 scenario.get(15).add(new ArrayList<AcDisconnectModel>());
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("Search AC Combiner [ "+i+" ]");
//			 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(6));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(7));
//			 when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(7));
//			 when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(7));
//			 when(mockedQuery5.getResultList()).thenReturn((List) scenario.get(i).get(7));
//
//			 when(mockedQuery6.getResultList()).thenReturn((List) scenario.get(i).get(8));
//             Mockito.doReturn(new ArrayList<Integer>()).when(acDisconnectLibraryService2).getAcCombinerDiscoFavorite(Mockito.any());
//          //  Assert.assertEquals(actual, delta);
//             acDisconnectLibraryService2.getSearchAcCombinerDisco((AcCombinerDiscoModelRequest) scenario.get(i).get(0), (Boolean)scenario.get(i).get(1), (String)scenario.get(i).get(2),(String) scenario.get(i).get(3), (String) scenario.get(i).get(4),(String) scenario.get(i).get(5));
//
//
//		 }
//
//
//
//	}
//
//	@Test
//	public void testgetContractorACComDisco() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.id IN :p1 and u.type = :p2 and u.isDeleted = :p3 ORDER BY u.dropdownOption")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 AcDisconnectLibraryService acDisconnectLibraryService2 = Mockito.spy(acDisconnectLibraryService);
//         List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		 scenario.get(1).add(new ArrayList<ACCombinerSLC>());
//		//Excpected Result
//		 scenario.get(1).add(new ArrayList<ACCombinerSLC>());
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getContractorACComDisco [ "+ i+ " ]");
//             Mockito.doReturn(new ArrayList<Integer>()).when(acDisconnectLibraryService2).getAcCombinerDiscoFavorite(Mockito.any());
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//            acDisconnectLibraryService2.getContractorACComDisco((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	}
//
//	@Test
//	public void testeditACCombinerNotification() {
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
//			 System.out.println("editACCombinerNotification [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//             acDisconnectLibraryService.editACCombinerNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//
//	}
//
//	@Test
//	public void testaddACCombinerNotification() {
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
//			 System.out.println("addACCombinerNotification [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//             acDisconnectLibraryService.addACCombinerNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	}
//
//
//	@Test
//	public void testsendCorrectionRequest() {
//		Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u Where u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery(" SELECT u  FROM AuthentificationEntity u WHERE u.id = :p1  ")).thenReturn(mockedQuery2);
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
//			 AcCombinerDiscoCorrectionModel acComSLC = new AcCombinerDiscoCorrectionModel();
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
//			 AcCombinerDiscoCorrectionModel acComSLC1= new AcCombinerDiscoCorrectionModel();
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
//			 AcCombinerDiscoCorrectionModel acComSLC2= new AcCombinerDiscoCorrectionModel();
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
//			 AcCombinerDiscoCorrectionModel acComSLC4= new AcCombinerDiscoCorrectionModel();
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
//			 ACDisconnect lib = new ACDisconnect();
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
//				 System.out.println("sendCorrectionRequest [ "+i+" ]");
//	             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//	             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//	             acDisconnectLibraryService.sendCorrectionRequest((AcCombinerDiscoCorrectionModel)scenario.get(i).get(0));
//
//			 }
//
//
//	}
//
//	@Test
//	public void testgetListOfAcDisconnect() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE  u.isDeleted= :p1 ORDER BY u.manufacturer, u.model")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 ArrayList<ArrayList<Object>> scenario = new  ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result Query1
//		 scenario.get(0).add(null);
//		 //ResultExcpected
//		 scenario.get(0).add(new ArrayList<>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result Query1
//		 scenario.get(1).add(new ArrayList<ACDisconnect>());
//		 //ResultExcpected
//		 scenario.get(1).add(new ArrayList<>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result Query1
//		 ArrayList<ACDisconnect> list = new ArrayList<ACDisconnect>();
//		 list.add(null);
//		 list.add(new ACDisconnect());
//		 scenario.get(2).add(list);
//		 //ResultExcpected
//		 scenario.get(2).add(list);
//
//		 for(int i=0;i<scenario.size();i++) {
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			 System.out.println("getListOfAcDisconnect [ "+i+" ]");
//
//             acDisconnectLibraryService.getListOfAcDisconnect();
//
//		 }
//
//
//	}
//
//	@Test
//	public void testremoveAcDiscoFavorite() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnectFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 and u.aCDisconnect.id = :p2")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //Set les parameter of the method
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//
//		 //Set the result of Query1
//		 scenario.get(0).add(null);
//		 //Set the result of Query2
//		 scenario.get(0).add(null);
//		 //Result excpected
//		 scenario.get(0).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //Set les parameter of the method
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//
//		 //Set the result of Query1
//		 scenario.get(1).add(null);
//		 //Set the result of Query2
//		 scenario.get(1).add(null);
//		 //Result excpected
//		 scenario.get(1).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //Set les parameter of the method
//		 scenario.get(2).add("12");
//		 scenario.get(2).add("12");
//		 scenario.get(2).add("12");
//
//		 //Set the result of Query1
//		 scenario.get(2).add(null);
//		 //Set the result of Query2
//		 scenario.get(2).add(null);
//		 //Result excpected
//		 scenario.get(2).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //Set les parameter of the method
//		 scenario.get(3).add("12");
//		 scenario.get(3).add("12");
//		 scenario.get(3).add("12");
//
//		 //Set the result of Query1
//		 scenario.get(3).add(new AuthentificationEntity());
//		 //Set the result of Query2
//		 scenario.get(3).add(new ACDisconnectFavLibraryEntity());
//		 //Result excpected
//		 scenario.get(3).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //Set les parameter of the method
//		 scenario.get(4).add("12");
//		 scenario.get(4).add("12");
//		 scenario.get(4).add("12");
//
//		 //Set the result of Query1
//		 scenario.get(4).add(new AuthentificationEntity());
//		 //Set the result of Query2
//		 ACDisconnectFavLibraryEntity ac = new ACDisconnectFavLibraryEntity();
//		 ac.setaCDisconnect(new ACDisconnect());
//		 scenario.get(4).add(ac);
//		 //Result excpected
//		 scenario.get(4).add("Done");
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("removeAcDiscoFavorite [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//
//             acDisconnectLibraryService.removeAcDiscoFavorite((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//
//
//
//	}
//
//	@Test
//	public void testaddAcDiscoFavorite() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACDisconnect u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//
//		 //The result of the query1
//		 scenario.get(0).add(null);
//		 //The Result of the query2
//		 scenario.get(0).add(null);
//		 //The result excpected
//		 scenario.get(0).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//
//		 //The result of the query1
//		 scenario.get(1).add(null);
//		 //The Result of the query2
//		 scenario.get(1).add(null);
//		 //The result excpected
//		 scenario.get(1).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//
//		 //The result of the query1
//		 scenario.get(2).add(null);
//		 //The Result of the query2
//		 scenario.get(2).add(null);
//		 //The result excpected
//		 scenario.get(2).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//
//		 //The result of the query1
//		 scenario.get(3).add(new AuthentificationEntity());
//		 //The Result of the query2
//		 scenario.get(3).add(null);
//		 //The result excpected
//		 scenario.get(3).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//
//		 //The result of the query1
//		 scenario.get(4).add(new AuthentificationEntity());
//		 //The Result of the query2
//		 scenario.get(4).add(new ACDisconnect());
//		 //The result excpected
//		 scenario.get(4).add("Done");
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("addAcDiscoFavorite [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//
//             acDisconnectLibraryService.addAcDiscoFavorite((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	}
//
//
//}
