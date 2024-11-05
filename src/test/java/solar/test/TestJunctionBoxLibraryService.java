//package solar.test;
//
////import static org.junit.Assert;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Date;
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
//import com.PlayGroundAdv.Solar.Entity.ACDisconnect;
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.DCCombinerDisconnectEntity;
//import com.PlayGroundAdv.Solar.Entity.Flashing;
//import com.PlayGroundAdv.Solar.Entity.FlashingFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.JunctionBoxFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.Entity.TiltLegs;
//import com.PlayGroundAdv.Solar.Entity.TiltLegsFavLibraryEntity;
//import com.PlayGroundAdv.Solar.model.AcDisconnectModel;
//import com.PlayGroundAdv.Solar.model.FlashingCorrectionRequest;
//import com.PlayGroundAdv.Solar.model.JunctionBoxCorrectionModel;
//import com.PlayGroundAdv.Solar.model.JunctionBoxModelRequest;
//import com.PlayGroundAdv.Solar.model.JunctionsBoxModel;
//import com.PlayGroundAdv.Solar.model.NewJunctionBoxModel;
//import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
//import com.PlayGroundAdv.Solar.model.SearchModulResult;
//import com.PlayGroundAdv.Solar.model.UsersEntityResult;
//import com.PlayGroundAdv.Solar.Services.AcDisconnectLibraryService;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetFlashingLibraryService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.JunctionBoxLibraryService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
//
//public class TestJunctionBoxLibraryService {
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
//	@Mock
//	private CriteriaQuery criteriaQuery;
//
//	@Mock
//	private CriteriaQuery criteriaQueryAll;
//
//	@Mock
//	private CriteriaBuilder criteriaBuilder;
//
//	@Mock
//	private Root root;
//
//	@Spy
//	CheckValueTypesService checkValueTypesService;
//	@InjectMocks
//	JunctionBoxLibraryService junctionBoxLibraryService = new JunctionBoxLibraryService();
//
//	@Before
//	public void setupMock() {
//		junctionBoxLibraryService = new JunctionBoxLibraryService();
//		MockitoAnnotations.initMocks(this);
//	}
//
//
//	@Test
//	public void testgetAllJunctionBox() {
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.isDeleted = :p1 and u.typeDc = 'J Box' ORDER BY u.manufacturer")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 //Spy the service
//		 JunctionBoxLibraryService junctionBoxLibraryService2 = Mockito.spy(junctionBoxLibraryService);
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
//		 scenario.get(0).add(new ArrayList<JunctionsBoxModel>());
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
//		 scenario.get(1).add(new ArrayList<JunctionsBoxModel>());
//		 //The result of the method getAcCombinerDiscoFavorite
//		 scenario.get(1).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(true);
//		 //Result of the query1
//		 ArrayList<DCCombinerDisconnectEntity> listAc = new ArrayList<DCCombinerDisconnectEntity>();
//		 listAc.add(null);
//		 listAc.add(new DCCombinerDisconnectEntity());
//		 DCCombinerDisconnectEntity ac = new DCCombinerDisconnectEntity();
//		 ac.setIdOwner(new AuthentificationEntity());
//		 listAc.add(ac);
//		 scenario.get(2).add(listAc);
//		 //Result excpected
//		 scenario.get(2).add(new ArrayList<JunctionsBoxModel>());
//		 //The result of the method getAcCombinerDiscoFavorite
//		 scenario.get(2).add(null);
//
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getAllJunctionBox [ "+i+" ]");
//            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 Mockito.doReturn(scenario.get(i).get(4)).when(junctionBoxLibraryService2).getJunctionBoxFavorite(Mockito.anyString());
//			 junctionBoxLibraryService2.getAllJunctionBox((String)scenario.get(i).get(0),(Boolean)scenario.get(i).get(1));
//
//
//		 }
//
//
//
//	}
//
//
//	@Test
//	public void testgetJunctionBoxFavorite() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from JunctionBoxFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 "))
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
//		scenario.get(3).add(new ArrayList<JunctionBoxFavLibraryEntity>());
//		// Result excpected
//		scenario.get(3).add(new ArrayList<Integer>());
//
//		scenario.add(new ArrayList<Object>()); // List of the parameter
//		scenario.get(4).add("123"); // Result of the query1
//		ArrayList<JunctionBoxFavLibraryEntity> list = new ArrayList<JunctionBoxFavLibraryEntity>();
//		list.add(null);
//		list.add(new JunctionBoxFavLibraryEntity());
//		scenario.get(4).add(list);
//		// Result excpected
//		scenario.get(4).add(new ArrayList<Integer>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("123");
//		// Result of the query1
//		ArrayList<JunctionBoxFavLibraryEntity> list2 = new ArrayList<JunctionBoxFavLibraryEntity>();
//		JunctionBoxFavLibraryEntity dc = new JunctionBoxFavLibraryEntity();
//		dc.setjBox(new DCCombinerDisconnectEntity());
//		list2.add(dc);
//		JunctionBoxFavLibraryEntity dc2 = new JunctionBoxFavLibraryEntity();
//		DCCombinerDisconnectEntity dcEnt = new DCCombinerDisconnectEntity();
//		dcEnt.setId(123);
//		dc2.setjBox(dcEnt);
//		list2.add(dc2);
//		scenario.get(5).add(list2); // Result excpected
//		ArrayList<Integer> rlt = new ArrayList<Integer>();
//		rlt.add(null);
//		rlt.add(123);
//		scenario.get(5).add(rlt);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getJunctionBoxFavorite [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//					junctionBoxLibraryService.getJunctionBoxFavorite((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//
//	@Test
//	public void testgetAllDeletedJunctionBox() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from DCCombinerDisconnectEntity u WHERE u.isDeleted = :p1 and u.typeDc = 'J Box' ORDER BY u.manufacturer"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of Query1
//		scenario.get(0).add(null);
//		// Result Expected
//		scenario.get(0).add(new ArrayList<JunctionsBoxModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// Result of Query1
//		ArrayList<DCCombinerDisconnectEntity> list = new ArrayList<DCCombinerDisconnectEntity>();
//		list.add(null);
//		list.add(new DCCombinerDisconnectEntity());
//		scenario.get(1).add(list);
//		// Result Expected
//		ArrayList<JunctionsBoxModel> listExp = new ArrayList<JunctionsBoxModel>();
//		listExp.add(new JunctionsBoxModel());
//		JunctionsBoxModel dc = new JunctionsBoxModel();
//		dc.setIsDeleted(false);
//		listExp.add(dc);
//		scenario.get(1).add(listExp);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllDeletedJunctionBox [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			ArrayList<JunctionsBoxModel> rslt = (ArrayList<JunctionsBoxModel>) junctionBoxLibraryService
//					.getAllDeletedJunctionBox();
//
//		}
//
//	}
//
//	@Test
//	public void testeditJunctionBoxFavoriteList() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from JunctionBoxFavLibraryEntity u WHERE u.jBox.id = :p1 and u.authentificationEntity.id = :p2")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//		 Query mockedQuery4 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//               	+ "p.permitEntity.authentificationEntity.id = :p1 and p.roofTopDcJboxType = :p2 ")).thenReturn(mockedQuery4);
//		 when(mockedQuery4.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery4);
//
//		 Query mockedQuery5 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//               	+ "p.permitEntity.authentificationEntity.id = :p1 and p.dcJboxType = :p2 ")).thenReturn(mockedQuery5);
//		 when(mockedQuery5.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery5);
//
//		 Query mockedQuery6 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//               	+ "p.permitEntity.authentificationEntity.id = :p1 and p.roofTopJbox = :p2 ")).thenReturn(mockedQuery6);
//		 when(mockedQuery6.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery6);
//
//		 Query mockedQuery7 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//               	+ "p.permitEntity.authentificationEntity.id = :p1 and p.roofTopJbox = :p2 ")).thenReturn(mockedQuery7);
//		 when(mockedQuery7.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery7);
//
//		 Query mockedQuery8 = mock(Query.class);
//		 when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//               	+ "p.permitEntity.authentificationEntity.id = :p1 and p.roofTopJboxDC = :p2 ")).thenReturn(mockedQuery8);
//		 when(mockedQuery8.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery8);
//
//
//
//			ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//			scenario.add(new ArrayList<Object>());
//			// List of the parameter
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			// Result of the query1
//			scenario.get(0).add(null);
//			// Result of the query2
//			scenario.get(0).add(null);
//			// Result of the query3
//			scenario.get(0).add(null);
//			// Result of the query4
//			scenario.get(0).add(null);
//			// Result of the query5
//			scenario.get(0).add(null);
//			// Result of the query6
//			scenario.get(0).add(null);
//			// Result of the query7
//			scenario.get(0).add(null);
//			// Result of the query8
//			scenario.get(0).add(null);
//			// Result excpected
//			scenario.get(0).add("error");
//
//
//			scenario.add(new ArrayList<Object>());
//			// List of the parameter
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.get(1).add("");
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			// Result of the query1
//			scenario.get(1).add(null);
//			// Result of the query2
//			scenario.get(1).add(null);
//			// Result of the query3
//			scenario.get(1).add(null);
//			// Result of the query4
//			scenario.get(1).add(null);
//			// Result of the query5
//			scenario.get(1).add(null);
//			// Result of the query6
//			scenario.get(1).add(null);
//			// Result of the query7
//			scenario.get(1).add(null);
//			// Result of the query8
//			scenario.get(1).add(null);
//			// Result excpected
//			scenario.get(1).add("error");
//
//
//			scenario.add(new ArrayList<Object>());
//			// List of the parameter
//			scenario.get(2).add(null);
//			scenario.get(2).add(null);
//			scenario.get(2).add(null);
//			scenario.get(2).add(null);
//			scenario.get(2).add(null);
//			scenario.get(2).add("123");
//			scenario.get(2).add(null);
//			scenario.get(2).add(null);
//			scenario.get(2).add(null);
//			// Result of the query1
//			scenario.get(2).add(null);
//			// Result of the query2
//			scenario.get(2).add(null);
//			// Result of the query3
//			scenario.get(2).add(null);
//			// Result of the query4
//			scenario.get(2).add(null);
//			// Result of the query5
//			scenario.get(2).add(null);
//			// Result of the query6
//			scenario.get(2).add(null);
//			// Result of the query7
//			scenario.get(2).add(null);
//			// Result of the query8
//			scenario.get(2).add(null);
//			// Result excpected
//			scenario.get(2).add("error");
//
//
//			scenario.add(new ArrayList<Object>());
//			// List of the parameter
//			scenario.get(3).add(null);
//			scenario.get(3).add(true);
//			scenario.get(3).add(null);
//			scenario.get(3).add(null);
//			scenario.get(3).add(null);
//			scenario.get(3).add("123");
//			scenario.get(3).add(null);
//			scenario.get(3).add(null);
//			scenario.get(3).add(null);
//			// Result of the query1
//			scenario.get(3).add(null);
//			// Result of the query2
//			scenario.get(3).add(null);
//			// Result of the query3
//			scenario.get(3).add(null);
//			// Result of the query4
//			scenario.get(3).add(null);
//			// Result of the query5
//			scenario.get(3).add(null);
//			// Result of the query6
//			scenario.get(3).add(null);
//			// Result of the query7
//			scenario.get(3).add(null);
//			// Result of the query8
//			scenario.get(3).add(null);
//			// Result excpected
//			scenario.get(3).add("error");
//
//			scenario.add(new ArrayList<Object>());
//			// List of the parameter
//			scenario.get(4).add(null);
//			scenario.get(4).add(true);
//			scenario.get(4).add(null);
//			scenario.get(4).add(null);
//			scenario.get(4).add(null);
//			scenario.get(4).add("123");
//			scenario.get(4).add(null);
//			scenario.get(4).add(null);
//			scenario.get(4).add(null);
//			// Result of the query1
//			scenario.get(4).add(new AuthentificationEntity());
//			// Result of the query2
//			scenario.get(4).add(new DCCombinerDisconnectEntity());
//			// Result of the query3
//			scenario.get(4).add(null);
//			// Result of the query4
//			scenario.get(4).add(null);
//			// Result of the query5
//			scenario.get(4).add(null);
//			// Result of the query6
//			scenario.get(4).add(null);
//			// Result of the query7
//			scenario.get(4).add(null);
//			// Result of the query8
//			scenario.get(4).add(null);
//			// Result excpected
//			scenario.get(4).add("Done");
//
//
//
//			scenario.add(new ArrayList<Object>());
//			// List of the parameter
//			scenario.get(5).add(null);
//			scenario.get(5).add(false);
//			scenario.get(5).add(null);
//			scenario.get(5).add(null);
//			scenario.get(5).add(null);
//			scenario.get(5).add("123");
//			scenario.get(5).add(null);
//			scenario.get(5).add(null);
//			scenario.get(5).add(null);
//			// Result of the query1
//			scenario.get(5).add(new AuthentificationEntity());
//			// Result of the query2
//			scenario.get(5).add(new DCCombinerDisconnectEntity());
//			// Result of the query3
//			JunctionBoxFavLibraryEntity juncFav = new JunctionBoxFavLibraryEntity();
//			juncFav.setjBox(new DCCombinerDisconnectEntity());
//			scenario.get(5).add(juncFav);
//			// Result of the query4
//			scenario.get(5).add(null);
//			// Result of the query5
//			scenario.get(5).add(null);
//			// Result of the query6
//			scenario.get(5).add(null);
//			// Result of the query7
//			scenario.get(5).add(null);
//			// Result of the query8
//			scenario.get(5).add(null);
//			// Result excpected
//			scenario.get(5).add("Done");
//
//
//			scenario.add(new ArrayList<Object>());
//			// List of the parameter
//			scenario.get(6).add(null);
//			scenario.get(6).add(false);
//			scenario.get(6).add(null);
//			scenario.get(6).add(null);
//			scenario.get(6).add(null);
//			scenario.get(6).add("123");
//			scenario.get(6).add(null);
//			scenario.get(6).add(null);
//			scenario.get(6).add(null);
//			// Result of the query1
//			scenario.get(6).add(new AuthentificationEntity());
//			// Result of the query2
//			scenario.get(6).add(new DCCombinerDisconnectEntity());
//			// Result of the query3
//			scenario.get(6).add(juncFav);
//			// Result of the query4
//			ArrayList<PermitProjectSiteInfoEntity> list = new ArrayList<PermitProjectSiteInfoEntity>();
//			list.add(null);
//			list.add(new PermitProjectSiteInfoEntity());
//			scenario.get(6).add(list);
//			// Result of the query5
//			scenario.get(6).add(null);
//			// Result of the query6
//			scenario.get(6).add(null);
//			// Result of the query7
//			scenario.get(6).add(null);
//			// Result of the query8
//			scenario.get(6).add(null);
//			// Result excpected
//			scenario.get(6).add("Done");
//
//
//			scenario.add(new ArrayList<Object>());
//			// List of the parameter
//			scenario.get(7).add(null);
//			scenario.get(7).add(false);
//			scenario.get(7).add(null);
//			scenario.get(7).add(null);
//			scenario.get(7).add(null);
//			scenario.get(7).add("123");
//			scenario.get(7).add(null);
//			scenario.get(7).add(null);
//			scenario.get(7).add(null);
//			// Result of the query1
//			scenario.get(7).add(new AuthentificationEntity());
//			// Result of the query2
//			scenario.get(7).add(new DCCombinerDisconnectEntity());
//			// Result of the query3
//			scenario.get(7).add(juncFav);
//			// Result of the query4
//			scenario.get(7).add(list);
//			// Result of the query5
//			ArrayList<PermitProjectSiteInfoEntity> list2 = new ArrayList<PermitProjectSiteInfoEntity>();
//			list2.add(null);
//			list2.add(new PermitProjectSiteInfoEntity());
//			scenario.get(7).add(list2);
//			// Result of the query6
//			scenario.get(7).add(null);
//			// Result of the query7
//			scenario.get(7).add(null);
//			// Result of the query8
//			scenario.get(7).add(null);
//			// Result excpected
//			scenario.get(7).add("Done");
//
//			scenario.add(new ArrayList<Object>());
//			// List of the parameter
//			scenario.get(8).add(null);
//			scenario.get(8).add(false);
//			scenario.get(8).add(null);
//			scenario.get(8).add(null);
//			scenario.get(8).add(null);
//			scenario.get(8).add("123");
//			scenario.get(8).add(null);
//			scenario.get(8).add(null);
//			scenario.get(8).add(null);
//			// Result of the query1
//			scenario.get(8).add(new AuthentificationEntity());
//			// Result of the query2
//			scenario.get(8).add(new DCCombinerDisconnectEntity());
//			// Result of the query3
//			scenario.get(8).add(juncFav);
//			// Result of the query4
//			scenario.get(8).add(list);
//			// Result of the query5
//			scenario.get(8).add(list2);
//			// Result of the query6
//			ArrayList<PermitProjectSiteInfoEntity> list3 = new ArrayList<PermitProjectSiteInfoEntity>();
//			list3.add(null);
//			list3.add(new PermitProjectSiteInfoEntity());
//			scenario.get(8).add(list3);
//			// Result of the query7
//			scenario.get(8).add(null);
//			// Result of the query8
//			scenario.get(8).add(null);
//			// Result excpected
//			scenario.get(8).add("Done");
//
//
//			scenario.add(new ArrayList<Object>());
//			// List of the parameter
//			scenario.get(9).add(null);
//			scenario.get(9).add(false);
//			scenario.get(9).add(null);
//			scenario.get(9).add(null);
//			scenario.get(9).add(null);
//			scenario.get(9).add("123");
//			scenario.get(9).add(null);
//			scenario.get(9).add(null);
//			scenario.get(9).add(null);
//			// Result of the query1
//			scenario.get(9).add(new AuthentificationEntity());
//			// Result of the query2
//			scenario.get(9).add(new DCCombinerDisconnectEntity());
//			// Result of the query3
//			scenario.get(9).add(juncFav);
//			// Result of the query4
//			scenario.get(9).add(list);
//			// Result of the query5
//			scenario.get(9).add(list2);
//			// Result of the query6
//			scenario.get(9).add(list3);
//			// Result of the query7
//			ArrayList<PermitProjectSiteInfoEntity> list4 = new ArrayList<PermitProjectSiteInfoEntity>();
//			list4.add(null);
//			list4.add(new PermitProjectSiteInfoEntity());
//			scenario.get(9).add(list4);
//			// Result of the query8
//			scenario.get(9).add(null);
//			// Result excpected
//			scenario.get(9).add("Done");
//
//			scenario.add(new ArrayList<Object>());
//			// List of the parameter
//			scenario.get(10).add(null);
//			scenario.get(10).add(false);
//			scenario.get(10).add(null);
//			scenario.get(10).add(null);
//			scenario.get(10).add(null);
//			scenario.get(10).add("123");
//			scenario.get(10).add(null);
//			scenario.get(10).add(null);
//			scenario.get(10).add(null);
//			// Result of the query1
//			scenario.get(10).add(new AuthentificationEntity());
//			// Result of the query2
//			scenario.get(10).add(new DCCombinerDisconnectEntity());
//			// Result of the query3
//			scenario.get(10).add(juncFav);
//			// Result of the query4
//			scenario.get(10).add(list);
//			// Result of the query5
//			scenario.get(10).add(list2);
//			// Result of the query6
//			scenario.get(10).add(list3);
//			// Result of the query7
//			scenario.get(10).add(list4);
//			// Result of the query8
//			ArrayList<PermitProjectSiteInfoEntity> list5 = new ArrayList<PermitProjectSiteInfoEntity>();
//			list5.add(null);
//			list5.add(new PermitProjectSiteInfoEntity());
//			scenario.get(10).add(list5);
//			// Result excpected
//			scenario.get(10).add("Done");
//
//
//			for (int i = 0; i < scenario.size(); i++) {
//				System.out.println("editJunctionBoxFavoriteList [ " + i + " ]");
//				when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(9));
//				when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(10));
//				when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(11));
//				when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(12));
//				when(mockedQuery5.getResultList()).thenReturn((List) scenario.get(i).get(13));
//				when(mockedQuery6.getResultList()).thenReturn((List) scenario.get(i).get(14));
//				when(mockedQuery7.getResultList()).thenReturn((List) scenario.get(i).get(15));
//				when(mockedQuery8.getResultList()).thenReturn((List) scenario.get(i).get(16));
//				junctionBoxLibraryService
//						.editJunctionBoxFavoriteList((Integer) scenario.get(i).get(0), (Boolean) scenario.get(i).get(1), (String) scenario.get(i).get(2), (String) scenario.get(i).get(3), (String) scenario.get(i).get(4), (String) scenario.get(i).get(5), (String) scenario.get(i).get(6), (String) scenario.get(i).get(7), (String) scenario.get(i).get(8));
//
//			}
//	}
//
//	@Test
//	public void testgetUsersForFavList() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from JunctionBoxFavLibraryEntity u WHERE u.jBox.id = :p1"))
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
//		scenario.get(1).add(new ArrayList<JunctionBoxFavLibraryEntity>());
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
//		scenario.get(2).add(new ArrayList<JunctionBoxFavLibraryEntity>());
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
//		scenario.get(3).add(new ArrayList<JunctionBoxFavLibraryEntity>());
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
//		ArrayList<JunctionBoxFavLibraryEntity> list = new ArrayList<JunctionBoxFavLibraryEntity>();
//		list.add(null);
//		list.add(new JunctionBoxFavLibraryEntity());
//		JunctionBoxFavLibraryEntity ac = new JunctionBoxFavLibraryEntity();
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
//			junctionBoxLibraryService
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
//		when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
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
//		scenario.get(3).add(new DCCombinerDisconnectEntity());
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
//		scenario.get(4).add(new DCCombinerDisconnectEntity());
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
//					junctionBoxLibraryService.editUsersFavoriteList((Integer) scenario.get(i).get(0),
//							(String[]) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3), (String) scenario.get(i).get(4), (String) scenario.get(i).get(5), (String) scenario.get(i).get(6), (String) scenario.get(i).get(7));
//
//		}
//
//	}
//
//	@Test
//	public void testcheckJBoxExistent() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.model = :p1 AND u.manufacturer = :p2 and u.typeDc = 'J Box' and u.isDeleted = false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.model = :p1 AND u.manufacturer != :p2 and u.typeDc = 'J Box'and u.isDeleted = false ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//			JunctionBoxLibraryService junctionBoxLibraryService2 = Mockito.spy(junctionBoxLibraryService);
//
//
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		//Result of the Query1
//		scenario.get(0).add(null);
//		//Result of the Query2
//		scenario.get(0).add(null);
//		//Expected Result
//		scenario.get(0).add(new ArrayList<JunctionsBoxModel>());
//		//Result of the function getJunctionBoxFavorite
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new NewJunctionBoxModel());
//		scenario.get(1).add(null);
//		//Result of the Query1
//		ArrayList<DCCombinerDisconnectEntity> list = new ArrayList<DCCombinerDisconnectEntity>();
//		list.add(null);
//		list.add(new DCCombinerDisconnectEntity());
//		scenario.get(1).add(list);
//		//Result of the Query2
//		scenario.get(1).add(null);
//		//Expected Result
//		ArrayList<JunctionsBoxModel> listExp = new ArrayList<JunctionsBoxModel>();
//		listExp.add(new JunctionsBoxModel());
//		JunctionsBoxModel jncB = new JunctionsBoxModel();
//		jncB.setIsShown(false);
//		jncB.setIsDeleted(false);
//		listExp.add(jncB);
//		scenario.get(1).add(listExp);
//		//Result of the function getJunctionBoxFavorite
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new NewJunctionBoxModel());
//		scenario.get(2).add(null);
//		//Result of the Query1
//		scenario.get(2).add(null);
//		//Result of the Query2
//		scenario.get(2).add(list);
//		//Expected Result
//		scenario.get(2).add(listExp);
//		//Result of the function getJunctionBoxFavorite
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new NewJunctionBoxModel());
//		scenario.get(3).add(null);
//		//Result of the Query1
//		scenario.get(3).add(null);
//		//Result of the Query2
//		scenario.get(3).add(null);
//		//Expected Result
//		scenario.get(3).add(new ArrayList<JunctionsBoxModel>());
//		//Result of the function getJunctionBoxFavorite
//		scenario.get(3).add(null);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("checkJBoxExistent [ "+i+" ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			Mockito.doReturn((List) scenario.get(i).get(5)).when(junctionBoxLibraryService2)
//			.getJunctionBoxFavorite(Mockito.any());
//			ArrayList<JunctionsBoxModel> rslt = (ArrayList<JunctionsBoxModel>) junctionBoxLibraryService2
//					.checkJBoxExistent((NewJunctionBoxModel) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//		}
//	}
//
//	@Test
//	public void testaddJunctionBox() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u.authentificationEntity from PermitEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		//Result of the Query1
//		scenario.get(0).add(null);
//		//Result of the Query2
//		scenario.get(0).add(null);
//		//Expected Result
//		scenario.get(0).add(new DCCombinerDisconnectEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		//Result of the Query1
//		scenario.get(1).add(null);
//		//Result of the Query2
//		scenario.get(1).add(null);
//		//Expected Result
//		scenario.get(1).add(new DCCombinerDisconnectEntity());
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		//Result of the Query1
//		scenario.get(2).add(null);
//		//Result of the Query2
//		scenario.get(2).add(null);
//		//Expected Result
//		scenario.get(2).add(new DCCombinerDisconnectEntity());
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new NewJunctionBoxModel());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		//Result of the Query1
//		scenario.get(3).add(null);
//		//Result of the Query2
//		scenario.get(3).add(null);
//		//Expected Result
//		DCCombinerDisconnectEntity dcExp = new DCCombinerDisconnectEntity();
//		Date today = new Date();
//		String Day;
//		String thisMonth;
//		String Year = today.getYear() + 1900 + "";
//
//		if ((today.getMonth() + 1) < 10) {
//			thisMonth = "0" + (today.getMonth() + 1);
//		} else {
//			thisMonth = today.getMonth() + 1 + "";
//		}
//		if (today.getDate() < 10) {
//			Day = "0" + (today.getDate());
//		} else {
//			Day = today.getDate() + "";
//		}
//		dcExp.setLastUpdate(thisMonth + "-" + Day + "-" + Year);
//		dcExp.setIsDeleted(false);
//		dcExp.setTypeDc("J Box");
//		scenario.get(3).add(dcExp);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(new NewJunctionBoxModel());
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add("123");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		//Result of the Query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setRoleEntity(new RoleEntity());
//		scenario.get(4).add(auth);
//		//Result of the Query2
//		scenario.get(4).add(null);
//		//Expected Result
//		DCCombinerDisconnectEntity dcExp2 = new DCCombinerDisconnectEntity();
//		dcExp2.setLastUpdate(thisMonth + "-" + Day + "-" + Year);
//		dcExp2.setIsDeleted(false);
//		dcExp2.setTypeDc("J Box");
//		dcExp2.setHasSuperUserEdit(false);
//		Date addDate = new Date();
//		dcExp2.setAddDate(addDate);
//		dcExp2.setIdOwner(auth);
//		scenario.get(4).add(dcExp2);
//
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("addJunctionBox [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(8));
//			 when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(9));
//		     junctionBoxLibraryService.addJunctionBox((NewJunctionBoxModel)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(Integer)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6),(String)scenario.get(i).get(7));
//
//
//		}
//	}
//
//	@Test
//	public void testeditJunctionBox() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.id !=:p3 AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM PermitProjectSiteInfoEntity u WHERE u.roofTopDcJboxType = :p1 OR u.dcJboxType = :p1 ")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		//Result of the Query1
//		scenario.get(0).add(null);
//		//Result of the Query2
//		scenario.get(0).add(null);
//		//Result of the Query3
//		scenario.get(0).add(null);
//		//Expected Result
//		scenario.get(0).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new JunctionsBoxModel());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		//Result of the Query1
//		scenario.get(1).add(null);
//		//Result of the Query2
//		scenario.get(1).add(null);
//		//Result of the Query3
//		scenario.get(1).add(null);
//		//Expected Result
//		scenario.get(1).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new JunctionsBoxModel());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		//Result of the Query1
//		scenario.get(2).add(new DCCombinerDisconnectEntity());
//		//Result of the Query2
//		scenario.get(2).add(null);
//		//Result of the Query3
//		scenario.get(2).add(null);
//		//Expected Result
//		scenario.get(2).add("success");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new JunctionsBoxModel());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		//Result of the Query1
//		scenario.get(3).add(new DCCombinerDisconnectEntity());
//		//Result of the Query2
//		ArrayList<DCCombinerDisconnectEntity> list = new ArrayList<DCCombinerDisconnectEntity>();
//		list.add(null);
//		list.add(new DCCombinerDisconnectEntity());
//		scenario.get(3).add(list);
//		//Result of the Query3
//		scenario.get(3).add(null);
//		//Expected Result
//		scenario.get(3).add("exist");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(new JunctionsBoxModel());
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		//Result of the Query1
//		scenario.get(4).add(new DCCombinerDisconnectEntity());
//		//Result of the Query2
//		ArrayList<DCCombinerDisconnectEntity> list2 = new ArrayList<DCCombinerDisconnectEntity>();
//		scenario.get(4).add(list2);
//		//Result of the Query3
//		ArrayList<PermitProjectSiteInfoEntity> list3 = new ArrayList<PermitProjectSiteInfoEntity>();
//		list3.add(null);
//		list3.add(new PermitProjectSiteInfoEntity());
//		scenario.get(4).add(list3);
//		//Expected Result
//		scenario.get(4).add("success");
//
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("editJunctionBox [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(8));
//			 when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(9));
//			 junctionBoxLibraryService.editJunctionBox((JunctionsBoxModel)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6));
//
//		 }
//	}
//
//	@Test
//	public void testgetRemoveJunctionBoxConfirmation() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel  " + " ( u.homeOwnName, "
//					+ " u.status, " + " v.firstName, " + " v.lastName)" + " from PermitEntity u, "
//					+ " AuthentificationEntity v, " + " PermitProjectSiteInfoEntity w" + " where w.dcJboxType = :p1 "
//					+ "and  u.isDeleted  = :p2 and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = :p2 and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		//Result of the Query1
//		scenario.get(0).add(null);
//		//Expected Result
//		scenario.get(0).add(new ArrayList<>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new JunctionsBoxModel());
//		//Result of the Query1
//		scenario.get(1).add(null);
//		//Expected Result
//		scenario.get(1).add(new ArrayList<>());
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new JunctionsBoxModel());
//		//Result of the Query1
//		ArrayList<ProjectForLibrariesModel> list = new ArrayList<ProjectForLibrariesModel>();
//		list.add(null);
//		list.add(new ProjectForLibrariesModel());
//		scenario.get(2).add(list);
//		//Expected Result
//		scenario.get(2).add(list);
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("deleteJunctionBox [ "+i+" ]");
//			 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			 ArrayList<ProjectForLibrariesModel> rslt = ((ArrayList<ProjectForLibrariesModel>)junctionBoxLibraryService.getRemoveJunctionBoxConfirmation((JunctionsBoxModel)scenario.get(i).get(0)));
//
//		 }
//	}
//
//	@Test
//	public void testdeleteJunctionBox() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from PermitProjectSiteInfoEntity u WHERE u.dcJboxType = :p1 "
//					+ "and  u.permitEntity.isDeleted  = :p2")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from PermitProjectSiteInfoEntity u WHERE u.roofTopJbox = :p1 "
//					+ "and  u.permitEntity.isDeleted  = :p2")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//		 Query mockedQuery4 = mock(Query.class);
//		 when(em.createQuery("SELECT u from PermitProjectSiteInfoEntity u WHERE u.roofTopJboxDC = :p1 "
//					+ "and  u.permitEntity.isDeleted  = :p2")).thenReturn(mockedQuery4);
//		 when(mockedQuery4.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery4);
//
//		 Query mockedQuery5 = mock(Query.class);
//		 when(em.createQuery("SELECT u from JunctionBoxFavLibraryEntity u WHERE u.jBox.id = :p1")).thenReturn(mockedQuery5);
//		 when(mockedQuery5.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery5);
//
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		//Result of the Query1
//		scenario.get(0).add(null);
//		//Result of the Query2
//		scenario.get(0).add(null);
//		//Result of the Query3
//		scenario.get(0).add(null);
//		//Result of the Query4
//		scenario.get(0).add(null);
//		//Result of the Query5
//		scenario.get(0).add(null);
//		//Expected Result
//		scenario.get(0).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		//Result of the Query1
//		scenario.get(1).add(new DCCombinerDisconnectEntity());
//		//Result of the Query2
//		scenario.get(1).add(null);
//		//Result of the Query3
//		scenario.get(1).add(null);
//		//Result of the Query4
//		scenario.get(1).add(null);
//		//Result of the Query5
//		scenario.get(1).add(null);
//		//Expected Result
//		scenario.get(1).add("Done");
//
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		//Result of the Query1
//		scenario.get(2).add(new DCCombinerDisconnectEntity());
//		//Result of the Query2
//		ArrayList<PermitProjectSiteInfoEntity> list  = new ArrayList<PermitProjectSiteInfoEntity>();
//		list.add(null);
//		list.add(new PermitProjectSiteInfoEntity());
//		scenario.get(2).add(list);
//		//Result of the Query3
//		scenario.get(2).add(null);
//		//Result of the Query4
//		scenario.get(2).add(null);
//		//Result of the Query5
//		scenario.get(2).add(null);
//		//Expected Result
//		scenario.get(2).add("Done");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		//Result of the Query1
//		scenario.get(3).add(new DCCombinerDisconnectEntity());
//		//Result of the Query2
//		scenario.get(3).add(list);
//		//Result of the Query3
//		scenario.get(3).add(list);
//		//Result of the Query4
//		scenario.get(3).add(null);
//		//Result of the Query5
//		scenario.get(3).add(null);
//		//Expected Result
//		scenario.get(3).add("Done");
//
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		//Result of the Query1
//		scenario.get(4).add(new DCCombinerDisconnectEntity());
//		//Result of the Query2
//		scenario.get(4).add(list);
//		//Result of the Query3
//		scenario.get(4).add(list);
//		//Result of the Query4
//		scenario.get(4).add(list);
//		//Result of the Query5
//		scenario.get(4).add(null);
//		//Expected Result
//		scenario.get(4).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		//Result of the Query1
//		scenario.get(5).add(new DCCombinerDisconnectEntity());
//		//Result of the Query2
//		scenario.get(5).add(list);
//		//Result of the Query3
//		scenario.get(5).add(list);
//		//Result of the Query4
//		scenario.get(5).add(list);
//		//Result of the Query5
//		ArrayList<JunctionBoxFavLibraryEntity> list2 = new ArrayList<JunctionBoxFavLibraryEntity>();
//		list2.add(null);
//		list2.add(new JunctionBoxFavLibraryEntity());
//		scenario.get(5).add(list2);
//		//Expected Result
//		scenario.get(5).add("Done");
//
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("deleteJunctionBox [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(8));
//			 when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(9));
//			 when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(10));
//			 when(mockedQuery5.getResultList()).thenReturn((List) scenario.get(i).get(11));
//			 junctionBoxLibraryService.deleteJunctionBox((Integer)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6));
//
//		 }
//	}
//
//	@Test
//	public void testactivateJunctionBox() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		//Result of the Query1
//		scenario.get(0).add(null);
//		//Result of the Query2
//		scenario.get(0).add(null);
//		//Expected Result
//		scenario.get(0).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		//Result of the Query1
//		scenario.get(1).add(null);
//		//Result of the Query2
//		scenario.get(1).add(null);
//		//Expected Result
//		scenario.get(1).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		//Result of the Query1
//		scenario.get(2).add(null);
//		//Result of the Query2
//		scenario.get(2).add(null);
//		//Expected Result
//		scenario.get(2).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		//Result of the Query1
//		scenario.get(3).add(new DCCombinerDisconnectEntity());
//		//Result of the Query2
//		scenario.get(3).add(null);
//		//Expected Result
//		scenario.get(3).add("Done");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		//Result of the Query1
//		scenario.get(4).add(new DCCombinerDisconnectEntity());
//		//Result of the Query2
//		ArrayList<DCCombinerDisconnectEntity> list = new ArrayList<DCCombinerDisconnectEntity>();
//		list.add(null);
//		list.add(new DCCombinerDisconnectEntity());
//		scenario.get(4).add(list);
//		//Expected Result
//		scenario.get(4).add("exist");
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("activateJunctionBox [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(8));
//			  junctionBoxLibraryService.activateJunctionBox((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6));
//
//		 }
//
//
//
//	}
//
//	@Test
//	public void testgetSearchJunctionBox() {
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(DCCombinerDisconnectEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(DCCombinerDisconnectEntity.class)).thenReturn(root);
//        when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//
//        TypedQuery mockedQuery1 = mock(TypedQuery.class);
//        when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//        JunctionBoxLibraryService junctionBoxLibraryService2 = Mockito.spy(junctionBoxLibraryService);
//
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.idOwner.id = :p1 and u.isDeleted = :p2")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from JunctionBoxFavLibraryEntity u WHERE u.jBox.id = :p1")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
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
//			//The result of the Query3 (Daynamic Query)
//		 scenario.get(0).add(null);
//		 //The result expected
//		 scenario.get(0).add(new ArrayList<JunctionsBoxModel>());
//		 //The reslut of the function getJunctionBoxFavorite
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 JunctionBoxModelRequest jncMod = new JunctionBoxModelRequest();
//		 jncMod.setIsShown(true);
//		 scenario.get(1).add(jncMod);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(1).add(null);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(1).add(null);
//			//The result of the Query3 (Daynamic Query)
//		 scenario.get(1).add(null);
//		 //The result expected
//		 scenario.get(1).add(new ArrayList<JunctionsBoxModel>());
//		 //The reslut of the function getJunctionBoxFavorite
//		 scenario.get(1).add(null);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(jncMod);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 List<DCCombinerDisconnectEntity> list  = new ArrayList<DCCombinerDisconnectEntity>();
//		 list.add(null);
//		 DCCombinerDisconnectEntity dc = new DCCombinerDisconnectEntity();
//		 dc.setId(2);
//		 list.add(dc);
//		 scenario.get(2).add(list);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(2).add(null);
//			//The result of the Query3 (Daynamic Query)
//		 scenario.get(2).add(null);
//		 //The result expected
//		 scenario.get(2).add(new ArrayList<JunctionsBoxModel>());
//		 //The reslut of the function getJunctionBoxFavorite
//		 scenario.get(2).add(null);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(jncMod);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add("");
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(3).add(list);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(3).add(null);
//			//The result of the Query3 (Daynamic Query)
//		 scenario.get(3).add(null);
//		 //The result expected
//		 scenario.get(3).add(new ArrayList<JunctionsBoxModel>());
//		 //The reslut of the function getJunctionBoxFavorite
//		 scenario.get(3).add(null);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add(jncMod);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add("123");
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(4).add(list);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(4).add(null);
//			//The result of the Query3 (Daynamic Query)
//		 scenario.get(4).add(null);
//		 //The result expected
//		 ArrayList<JunctionsBoxModel> listExp = new ArrayList<JunctionsBoxModel>();
//		 JunctionsBoxModel exp = new JunctionsBoxModel();
//		 exp.setId(2);
//		 exp.setIsShown(true);
//		 exp.setIsDeleted(false);
//		 listExp.add(exp);
//		 scenario.get(4).add(listExp);
//		 //The reslut of the function getJunctionBoxFavorite
//		 ArrayList<Integer> arrayInt = new ArrayList<Integer>();
//		 arrayInt.add(1);
//		 arrayInt.add(2);
//		 arrayInt.add(3);
//		 scenario.get(4).add(arrayInt);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(5).add(jncMod);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add("123");
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(5).add(list);
//		//The result of the Query2 (Daynamic Query)
//		 ArrayList<DCCombinerDisconnectEntity> listQ2 = new ArrayList<DCCombinerDisconnectEntity>();
//		 listQ2.add(null);
//		 listQ2.add(new DCCombinerDisconnectEntity());
//		 scenario.get(5).add(listQ2);
//			//The result of the Query3 (Daynamic Query)
//		 scenario.get(5).add(null);
//		 //The result expected
//		 scenario.get(5).add(listExp);
//		 //The reslut of the function getJunctionBoxFavorite
//		 scenario.get(5).add(arrayInt);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 JunctionBoxModelRequest jncMod2 = new JunctionBoxModelRequest();
//		 jncMod2.setIsShown(true);
//		 jncMod2.setModel("abc_def bbb");
//		 jncMod2.setManufacturer("aaa_bbb hello");
//		 scenario.get(6).add(jncMod2);
//		 scenario.get(6).add(null);
//		 scenario.get(6).add("123");
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(6).add(list);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(6).add(listQ2);
//			//The result of the Query3 (Daynamic Query)
//		 scenario.get(6).add(null);
//		 //The result expected
//		 scenario.get(6).add(listExp);
//		 //The reslut of the function getJunctionBoxFavorite
//		 scenario.get(6).add(arrayInt);
//
//		 for (int i = 0; i < scenario.size(); i++) {
//				System.out.println("getSearchJunctionBox [ " + i + " ]");
//				Mockito.doReturn((List) scenario.get(i).get(10)).when(junctionBoxLibraryService2)
//						.getJunctionBoxFavorite(Mockito.any());
//				when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(6));
//				when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(7));
//				when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(8));
//				List<JunctionsBoxModel> rslt = ((ArrayList<JunctionsBoxModel>) junctionBoxLibraryService2
//						.getSearchJunctionBox((JunctionBoxModelRequest) scenario.get(i).get(0),(Boolean) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3),(String) scenario.get(i).get(4),(String) scenario.get(i).get(5)));
//
//
//			}
//
//		}
//
//
//
//
//
//
//	@Test
//	public void testgetJBox() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.id IN :p1 and u.typeDc = 'J Box' ORDER BY u.dropdownOption")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		JunctionBoxLibraryService junctionBoxLibraryService2 = Mockito.spy(junctionBoxLibraryService);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<DCCombinerDisconnectEntity>());
//		// Result of the function getJunctionBoxFavorite
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<DCCombinerDisconnectEntity>());
//		// Result of the function getJunctionBoxFavorite
//		scenario.get(1).add(null);
//
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<DCCombinerDisconnectEntity>());
//		// Result of the function getJunctionBoxFavorite
//		scenario.get(2).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		PermitEntity per = new PermitEntity();
//		per.setAuthentificationEntity(new AuthentificationEntity());
//		scenario.get(3).add(per);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new ArrayList<DCCombinerDisconnectEntity>());
//		// Result of the function getJunctionBoxFavorite
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(per);
//		// Result of the query2
//	    ArrayList<DCCombinerDisconnectEntity> lisrQ2 = new ArrayList<DCCombinerDisconnectEntity>();
//	    lisrQ2.add(null);
//	    lisrQ2.add(new DCCombinerDisconnectEntity());
//		scenario.get(4).add(lisrQ2);
//		// Result excpected
//		scenario.get(4).add(lisrQ2);
//		// Result of the function getJunctionBoxFavorite
//		ArrayList<Integer> listInt = new ArrayList<Integer>();
//		listInt.add(1);
//		listInt.add(12);
//		scenario.get(4).add(listInt);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getJBox [ " + i + " ]");
//			Mockito.doReturn((List) scenario.get(i).get(4)).when(junctionBoxLibraryService2)
//					.getJunctionBoxFavorite(Mockito.any());
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			List<DCCombinerDisconnectEntity> rslt = ((ArrayList<DCCombinerDisconnectEntity>) junctionBoxLibraryService2
//					.getJBox((String) scenario.get(i).get(0)));
//
//		}
//
//	}
//
//
//
//	@Test
//	public void testrefreshJBox() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		JunctionBoxLibraryService junctionBoxLibraryService2 = Mockito.spy(junctionBoxLibraryService);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(false);
//		// Result of the function getJunctionBoxFavorite
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(false);
//		// Result of the function getJunctionBoxFavorite
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(false);
//		// Result of the function getJunctionBoxFavorite
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add("123");
//		// Result of the query1
//		PermitEntity per = new PermitEntity();
//		scenario.get(3).add(per);
//		// Result excpected
//		scenario.get(3).add(false);
//		// Result of the function getJunctionBoxFavorite
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add("123");
//		// Result of the query1
//		PermitEntity per2 = new PermitEntity();
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setId(123);
//		per2.setAuthentificationEntity(auth);
//		scenario.get(4).add(per2);
//		// Result excpected
//		scenario.get(4).add(true);
//		// Result of the function getJunctionBoxFavorite
//		scenario.get(4).add(null);
//
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("refreshJBox [ "+i+" ]");
//			Mockito.doReturn((List) scenario.get(i).get(4)).when(junctionBoxLibraryService2).getJunctionBoxFavorite(Mockito.any());
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			junctionBoxLibraryService2.refreshJBox((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		}
//	}
//
//	@Test
//	public void testgetContractorJBox() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from DCCombinerDisconnectEntity u WHERE u.id IN :p1 and u.typeDc = 'J Box' ORDER BY u.dropdownOption"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		JunctionBoxLibraryService junctionBoxLibraryService2 = Mockito.spy(junctionBoxLibraryService);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<DCCombinerDisconnectEntity>());
//		// Result of the function getJunctionBoxFavorite
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<DCCombinerDisconnectEntity>());
//		// Result of the function getJunctionBoxFavorite
//		List<Integer> listExp = new ArrayList<Integer>();
//		scenario.get(1).add(listExp);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("");
//		// Result of the query1
//		List<DCCombinerDisconnectEntity> list = new ArrayList<DCCombinerDisconnectEntity>();
//		list.add(null);
//		list.add(new DCCombinerDisconnectEntity());
//		scenario.get(2).add(list);
//		// Result excpected
//		scenario.get(2).add(list);
//		// Result of the function getJunctionBoxFavorite
//		List<Integer> listExp2 = new ArrayList<Integer>();
//		listExp2.add(2);
//		listExp2.add(null);
//		listExp2.add(258);
//		scenario.get(2).add(listExp2);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getContractorJBox [ " + i + " ]");
//			Mockito.doReturn((List) scenario.get(i).get(3)).when(junctionBoxLibraryService2)
//					.getJunctionBoxFavorite(Mockito.any());
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			List<DCCombinerDisconnectEntity> rslt = ((ArrayList<DCCombinerDisconnectEntity>) junctionBoxLibraryService2
//					.getContractorJBox((String) scenario.get(i).get(0)));
//
//		}
//
//	}
//
//
//	@Test public void testeditDCJboxNotification() {
//
//	  Query mockedQuery1 = mock(Query.class); when(em.
//	  createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).
//	  thenReturn(mockedQuery1); when(mockedQuery1.setParameter(Mockito.anyString(),
//	  Mockito.any())).thenReturn(mockedQuery1);
//
//	  List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//	  scenario.add(new ArrayList<Object>());
//	  // List of the parameter
//	  scenario.get(0).add(null);
//	  scenario.get(0).add(null);
//	  scenario.get(0).add(null);
//	  // Result of query1
//	  scenario.get(0).add(null);
//	  //Excpected Result
//	  scenario.get(0).add("fail");
//
//	  scenario.add(new ArrayList<Object>());
//	  // List of the parameter
//	  scenario.get(1).add("123");
//	  scenario.get(1).add(null);
//	  scenario.get(1).add(null);
//	  // Result of query1
//	  scenario.get(1).add(new  AuthentificationEntity());
//	  // Excpected Result
//	  scenario.get(1).add("Success");
//
//	  scenario.add(new ArrayList<Object>());
//	  // List of the parameter
//	  scenario.get(2).add("");
//	  scenario.get(2).add(null);
//	  scenario.get(2).add(null);
//	  // Result of query1
//	  scenario.get(2).add(null);
//	  //  Excpected Result
//	  scenario.get(2).add("fail");
//
//	  scenario.add(new ArrayList<Object>());
//	  // List of the parameter
//	  scenario.get(3).add("123");
//	  scenario.get(3).add(null);
//	  scenario.get(3).add(null);
//	  // Result of query1
//	  scenario.get(3).add(null);
//	  //Excpected Result
//	  scenario.get(3).add("fail");
//
//	  for (int i = 0; i < scenario.size(); i++) {
//	  System.out.println("editDCJboxNotification [ " + i + " ]");
//	  when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//
//	  junctionBoxLibraryService.editDCJboxNotification( (String)
//	  scenario.get(i).get(0), (String) scenario.get(i).get(1), (String)
//	  scenario.get(i).get(2));
//
//	  }
//
//	  }
//
//
//
//	  @Test public void testaddDCJboxNotification() {
//
//	  Query mockedQuery1 = mock(Query.class); when(em.
//	  createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).
//	  thenReturn(mockedQuery1); when(mockedQuery1.setParameter(Mockito.anyString(),
//	  Mockito.any())).thenReturn(mockedQuery1);
//
//	  List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//	  scenario.add(new ArrayList<Object>());
//	  //List of the parameter
//	  scenario.get(0).add(null);
//	  scenario.get(0).add(null);
//	  scenario.get(0).add(null);
//	  //Result of query1
//	  scenario.get(0).add(null);
//	  //Excpected Result
//	  scenario.get(0).add("fail");
//
//	  scenario.add(new ArrayList<Object>());
//	  //List of the parameter
//	  scenario.get(1).add("123");
//	  scenario.get(1).add(null);
//	  scenario.get(1).add(null);
//	  //Result of query1
//	  scenario.get(1).add(new AuthentificationEntity());
//	   //Excpected Result
//	  scenario.get(1).add("Success");
//
//	  scenario.add(new ArrayList<Object>());
//	  //List of the parameter
//	  scenario.get(2).add("");
//	  scenario.get(2).add(null);
//	  scenario.get(2).add(null);
//	  //Result of query1
//	  scenario.get(2).add(null);
//	  //Excpected Result
//	  scenario.get(2).add("fail");
//
//	  scenario.add(new ArrayList<Object>());
//	  //List of the parameter
//	  scenario.get(3).add("123");
//	  scenario.get(3).add(null);
//	  scenario.get(3).add(null);
//	  //Result of query1
//	  scenario.get(3).add(null);
//	  //Excpected Result
//	  scenario.get(3).add("fail");
//
//	  for(int i=0; i<scenario.size();i++) {
//	  System.out.println("addDCJboxNotification [ "+i+" ]");
//	  when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//
//	  junctionBoxLibraryService.addDCJboxNotification((String)scenario.get(i).get(0
//	  ),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//	  }
//
//	  }
//
//
//
//	  @Test public void testsendCorrectionRequest() {
//
//	  Query mockedQuery1 = mock(Query.class); when(em.
//	  createQuery("SELECT u from DCCombinerDisconnectEntity u Where u.id = :p1 ")).
//	  thenReturn(mockedQuery1); when(mockedQuery1.setParameter(Mockito.anyString(),
//	  Mockito.any())).thenReturn(mockedQuery1);
//
//	  Query mockedQuery2 = mock(Query.class); when(em.
//	  createQuery(" SELECT u  FROM AuthentificationEntity u WHERE u.id = :p1  ")).
//	  thenReturn(mockedQuery2); when(mockedQuery2.setParameter(Mockito.anyString(),
//	  Mockito.any())).thenReturn(mockedQuery2);
//
//	  List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//	  scenario.add(new ArrayList<Object>());
//	  // List of the parameter
//	  scenario.get(0).add(null);
//	  // Result of query1
//	  scenario.get(0).add(null);
//	  //Result of query2
//	  scenario.get(0).add(null);
//	  // Excpected Result
//	  scenario.get(0).add("Fail");
//
//	  scenario.add(new ArrayList<Object>());
//	  // List of the parameter
//	  JunctionBoxCorrectionModel acComSLC = new JunctionBoxCorrectionModel();
//	  acComSLC.setId(0);
//	  scenario.get(1).add(acComSLC);
//	  // Result of query1
//	  scenario.get(1).add(null);
//	  // Result of query2
//	  scenario.get(1).add(null);
//	  //Excpected Result
//	  scenario.get(1).add("Done");
//
//	  scenario.add(new ArrayList<Object>());
//	  // List of the parameter
//	  JunctionBoxCorrectionModel acComSLC1 = new JunctionBoxCorrectionModel();
//	  acComSLC1.setId(123);
//	  scenario.get(2).add(acComSLC1);
//	  // Result of query1
//	  scenario.get(2).add(null);
//	  // Result of query2
//	  scenario.get(2).add(null);
//	  // Excpected Result
//	  scenario.get(2).add("Fail");
//
//	  scenario.add(new ArrayList<Object>());
//	  // List of the parameter
//	  JunctionBoxCorrectionModel acComSLC2 = new JunctionBoxCorrectionModel();
//	  acComSLC2.setId(123);
//	  acComSLC2.setIdUser("");
//	  scenario.get(3).add(acComSLC2);
//	  // Result of query1
//	  scenario.get(3).add(null);
//	  // Result of query2
//	  scenario.get(3).add(null);
//	  //Excpected Result
//	  scenario.get(3).add("Fail");
//
//	  scenario.add(new ArrayList<Object>());
//	  // List of the parameter
//	  JunctionBoxCorrectionModel acComSLC4 = new JunctionBoxCorrectionModel();
//	  acComSLC4.setId(123); acComSLC4.setIdUser("123");
//	  scenario.get(4).add(acComSLC4);
//	  // Result of query1
//	  scenario.get(4).add(null);
//	  // Result of query2
//	  scenario.get(4).add(null);
//	  //Excpected Result
//	  scenario.get(4).add("Fail");
//
//	  scenario.add(new ArrayList<Object>());
//	  // List of the parameter
//	  scenario.get(5).add(acComSLC4);
//	  // Result of query1
//	  DCCombinerDisconnectEntity lib = new DCCombinerDisconnectEntity();
//	  scenario.get(5).add(lib);
//	  // Result of query2
//	  scenario.get(5).add(null);
//	  // Excpected Result
//	  scenario.get(5).add("Fail");
//
//	  scenario.add(new ArrayList<Object>());
//	  // List of the parameter
//	  scenario.get(6).add(acComSLC4);
//	  // Result of query1
//	  scenario.get(6).add(lib);
//	  // Result of query2
//	  AuthentificationEntity auth = new AuthentificationEntity();
//	  scenario.get(6).add(auth);
//	  // Excpected Result
//	  scenario.get(6).add("Done");
//
//	  for (int i = 0; i < scenario.size(); i++) {
//	  System.out.println("sendCorrectionRequest [ " + i + " ]");
//	  when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//	  when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//	  junctionBoxLibraryService
//	  .sendCorrectionRequest((JunctionBoxCorrectionModel) scenario.get(i).get(0));
//
//	  }
//
//	  }
//
//}
