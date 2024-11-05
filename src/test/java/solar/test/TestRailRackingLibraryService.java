//package solar.test;
//
//import static org.junit.Assert.*;
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
//import com.PlayGroundAdv.Solar.Entity.InverterLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.Inverters;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.RailRacking;
//import com.PlayGroundAdv.Solar.Entity.RailRackingFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.Entity.TiltLegs;
//import com.PlayGroundAdv.Solar.model.InverterCorrectionReq;
//import com.PlayGroundAdv.Solar.model.InverterFavRequest;
//import com.PlayGroundAdv.Solar.model.NewRailRackingModel;
//import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
//import com.PlayGroundAdv.Solar.model.RailRackingModel;
//import com.PlayGroundAdv.Solar.model.RailRackingModelRequest;
//import com.PlayGroundAdv.Solar.model.RailRakingCorrectionModel;
//import com.PlayGroundAdv.Solar.model.SearchInverterRequest;
//import com.PlayGroundAdv.Solar.model.SearchInvertersResult;
//import com.PlayGroundAdv.Solar.model.SearchRailRackingModelRequest;
//import com.PlayGroundAdv.Solar.model.TiltLegsFavRequest;
//import com.PlayGroundAdv.Solar.model.UsersEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetInverterLibraryService;
//import com.PlayGroundAdv.Solar.Services.GetTiltLegsLibraryService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.RailRackingLibraryService;
//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
//
//public class TestRailRackingLibraryService {
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
//	RailRackingLibraryService railRackingLibraryService = new RailRackingLibraryService();
//
//
//    @Before
//	public void setupMock() {
//    	railRackingLibraryService = new RailRackingLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//
//	@Test
//	public void testgetAllRailRacking() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.isDeleted = :p1 ORDER BY u.manufacturer,u.model"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		RailRackingLibraryService railRackingLibraryService2 = Mockito.spy(railRackingLibraryService);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(new ArrayList<RailRacking>());
//		// Result excpected
//		scenario.get(0).add(new ArrayList<RailRackingModel>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(false);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<RailRackingModel>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(false);
//		// Result of the query1
//		ArrayList<RailRacking> list = new ArrayList<RailRacking>();
//		list.add(null);
//		RailRacking rail = new RailRacking();
//		rail.setId(123);
//		rail.setModel("aaa");
//		list.add(rail);
//		scenario.get(2).add(list);
//		// Result excpected
//		ArrayList<RailRackingModel> listExp = new ArrayList<RailRackingModel>();
//		listExp.add(new RailRackingModel());
//		RailRackingModel exp = new RailRackingModel();
//		exp.setId(123);
//		exp.setIsShown(false);
//		exp.setModelNumber("aaa");
//		listExp.add(exp);
//		scenario.get(2).add(listExp);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllDeletedRailRacking [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			Mockito.doReturn(null).when(railRackingLibraryService2)
//			.getRailRackingFavorite(Mockito.anyString());
//			ArrayList<RailRackingModel> result = ((ArrayList<RailRackingModel>) railRackingLibraryService2
//					.getAllRailRacking((String) scenario.get(i).get(0),(Boolean) scenario.get(i).get(1)));
//
//		}
//
//
//	}
//
//	@Test
//	public void testgetRailRackingFavorite() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRackingFavLibraryEntity u WHERE u.authentificationEntity.id = :p1"))
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
//		scenario.get(3).add(new ArrayList<RailRackingFavLibraryEntity>());
//		// Result excpected
//		scenario.get(3).add(new ArrayList<Integer>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		// Result of the query1
//		ArrayList<RailRackingFavLibraryEntity> list = new ArrayList<RailRackingFavLibraryEntity>();
//		list.add(null);
//		list.add(new RailRackingFavLibraryEntity());
//		scenario.get(4).add(list);
//		// Result excpected
//		scenario.get(4).add(new ArrayList<Integer>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("123");
//		// Result of the query1
//		ArrayList<RailRackingFavLibraryEntity> list2 = new ArrayList<RailRackingFavLibraryEntity>();
//		RailRackingFavLibraryEntity dc = new RailRackingFavLibraryEntity();
//		dc.setRailRacking(new RailRacking());
//		list2.add(dc);
//		RailRackingFavLibraryEntity dc2 = new RailRackingFavLibraryEntity();
//		RailRacking dcEnt = new RailRacking();
//		dcEnt.setId(123);
//		dc2.setRailRacking(dcEnt);
//		list2.add(dc2);
//		scenario.get(5).add(list2);
//		// Result excpected
//		ArrayList<Integer> rlt = new ArrayList<Integer>();
//		rlt.add(0);
//		rlt.add(123);
//		scenario.get(5).add(rlt);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getRailRackingFavorite [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//					railRackingLibraryService.getRailRackingFavorite((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testgetAllDeletedRailRacking() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.isDeleted = :p1 ORDER BY u.manufacturer"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(new ArrayList<RailRacking>());
//		// Result excpected
//		scenario.get(0).add(new ArrayList<RailRackingModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<RailRackingModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<RailRacking> list = new ArrayList<RailRacking>();
//		list.add(null);
//		RailRacking rail = new RailRacking();
//		rail.setId(123);
//		rail.setModel("aaa");
//		list.add(rail);
//		scenario.get(2).add(list);
//		// Result excpected
//		ArrayList<RailRackingModel> listExp = new ArrayList<RailRackingModel>();
//		listExp.add(new RailRackingModel());
//		RailRackingModel exp = new RailRackingModel();
//		exp.setId(123);
//		exp.setModelNumber("aaa");
//		listExp.add(exp);
//		scenario.get(2).add(listExp);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllDeletedRailRacking [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			ArrayList<RailRackingModel> result = ((ArrayList<RailRackingModel>) railRackingLibraryService
//					.getAllDeletedRailRacking());
//
//		}
//	}
//
//	@Test
//	public void testeditRailRackingFavoriteList() {
//
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id = :p1")).thenReturn(mockedQuery1);
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
//		scenario.get(0).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		Integer[] ListUsers = { 123, null, 1233 };
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
//		Integer[] ListUsers2 = { 12, 44, 1233 };
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
//		scenario.get(3).add(new RailRacking());
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
//		scenario.get(4).add(new RailRacking());
//		// Result of the query2
//		scenario.get(4).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(4).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editManyUsersFavoriteList [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(8));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(9));
//					railRackingLibraryService.editManyUsersFavoriteList((Integer) scenario.get(i).get(0),
//							(Integer[]) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3), (String) scenario.get(i).get(4));
//
//		}
//
//
//	}
//
//	@Test
//	public void testgetUsersForFavList() {
//
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRackingFavLibraryEntity u WHERE u.railRacking.id = :p1"))
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
//		scenario.get(1).add(new ArrayList<RailRackingFavLibraryEntity>());
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
//		scenario.get(2).add(new ArrayList<RailRackingFavLibraryEntity>());
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
//		scenario.get(3).add(new ArrayList<RailRackingFavLibraryEntity>());
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
//		ArrayList<RailRackingFavLibraryEntity> list = new ArrayList<RailRackingFavLibraryEntity>();
//		list.add(null);
//		list.add(new RailRackingFavLibraryEntity());
//		RailRackingFavLibraryEntity ac = new RailRackingFavLibraryEntity();
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
//			railRackingLibraryService
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
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id = :p1")).thenReturn(mockedQuery1);
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
//		scenario.get(3).add(new RailRacking());
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
//		scenario.get(4).add(new RailRacking());
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
//					railRackingLibraryService.editUsersFavoriteList((Integer) scenario.get(i).get(0),
//							(String[]) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3), (String) scenario.get(i).get(4));
//
//		}
//
//
//	}
//
//	@Test
//	public void testeditManyUsersFavoriteList() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id = :p1")).thenReturn(mockedQuery1);
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
//		Integer[] ListUsers2 = { 12, 44 , null};
//		scenario.get(1).add(ListUsers2);
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
//		scenario.get(2).add(ListUsers2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(new RailRacking());
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add(ListUsers2);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new RailRacking());
//		// Result of the query2
//		scenario.get(3).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(3).add("Done");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editManyUsersFavoriteList [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(6));
//					railRackingLibraryService.editManyUsersFavoriteList((Integer) scenario.get(i).get(0),
//							(Integer[]) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3), (String) scenario.get(i).get(4));
//
//		}
//	}
//
//	@Test
//	public void testcheckRailRackingExistent() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from RailRacking u WHERE u.model = :p1 AND u.manufacturer = :p2 AND u.isDeleted = false"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from RailRacking u WHERE u.model = :p1 AND u.manufacturer != :p2 AND u.isDeleted = false"))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		RailRackingLibraryService railRackingLibraryService2 = Mockito.spy(railRackingLibraryService);
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
//		scenario.get(0).add(new ArrayList<RailRackingModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new NewRailRackingModel());
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<RailRackingModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new NewRailRackingModel());
//		scenario.get(2).add(null);
//		// Result of the query1
//		List<RailRacking> list = new ArrayList<RailRacking>();
//		list.add(null);
//		list.add(new RailRacking());
//		RailRacking bt = new RailRacking();
//		bt.setId(223);
//		bt.setManufacturer("abc");
//		list.add(bt);
//		scenario.get(2).add(list);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		List<RailRackingModel> listExpec = new ArrayList<RailRackingModel>();
//		listExpec.add(new RailRackingModel());
//		RailRackingModel btExp2 = new RailRackingModel();
//		btExp2.setId(0);
//		btExp2.setIsDeleted(false);
//		btExp2.setIsShown(false);
//		listExpec.add(btExp2);
//		RailRackingModel btExp = new RailRackingModel();
//		btExp.setId(223);
//		btExp.setManufacturer("abc");
//		btExp.setIsShown(false);
//		btExp.setIsDeleted(false);
//		listExpec.add(btExp);
//		scenario.get(2).add(listExpec);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new NewRailRackingModel());
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
//			Mockito.doReturn(new ArrayList<Integer>()).when(railRackingLibraryService2)
//					.getRailRackingFavorite(Mockito.any());
//			ArrayList<RailRackingModel> result = ((ArrayList<RailRackingModel>) railRackingLibraryService2
//					.checkRailRackingExistent((NewRailRackingModel) scenario.get(i).get(0),
//							(String) scenario.get(i).get(1)));
//
//		}
//
//	}
//
//	@Test
//	public void testaddRailRacking() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u.authentificationEntity from PermitEntity u WHERE u.id = :p1"))
//				.thenReturn(mockedQuery2);
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
//		scenario.get(0).add(new RailRacking());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add(new RailRacking());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(null);
//		// Result of query2
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add(new RailRacking());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new NewRailRackingModel());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(null);
//		// Result of query2
//		scenario.get(3).add(null);
//		// Excpected Result
//		RailRacking exp = new RailRacking();
//
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
//		exp.setLastUpdate(thisMonth + "-" + Day + "-" + Year);
//		exp.setIsDeleted(false);
//		scenario.get(3).add(exp);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(new NewRailRackingModel());
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add("123");
//		scenario.get(4).add(null);
//		// Result of query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		RoleEntity role = new RoleEntity();
//		role.setId(1);
//		auth.setRoleEntity(role);
//		scenario.get(4).add(auth);
//		// Result of query2
//		scenario.get(4).add(null);
//		// Excpected Result
//		RailRacking exp1 = new RailRacking();
//		exp1.setLastUpdate(thisMonth + "-" + Day + "-" + Year);
//		exp1.setIsDeleted(false);
//		exp1.setHasSuperUserEdit(true);
//		Date addDate = new Date();
//		exp1.setAddDate(addDate);
//		exp1.setIdOwner(auth);
//		scenario.get(4).add(exp1);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addRailRacking [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(6));
//			RailRacking rslt = railRackingLibraryService.addRailRacking((NewRailRackingModel) scenario.get(i).get(0),
//					(String) scenario.get(i).get(1), (String) scenario.get(i).get(2), (String) scenario.get(i).get(3),
//					(Integer) scenario.get(i).get(4));
//
//		}
//
//	}
//
//	@Test
//	public void testeditRailRacking() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
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
//		// Result of query2 List
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new RailRackingModel());
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("fail");
//		// Result of query2 List
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new RailRackingModel());
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(new RailRacking());
//		// Result of query2
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("success");
//		// Result of query2 List
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new RailRackingModel());
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(new RailRacking());
//		// Result of query2
//		scenario.get(3).add(null);
//		// Excpected Result
//		scenario.get(3).add("success");
//		// Result of query2 List
//		ArrayList<RailRacking> list = new ArrayList<RailRacking>();
//		list.add(null);
//		list.add(new RailRacking());
//		scenario.get(3).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		RailRackingModel railParm = new RailRackingModel();
//		railParm.setId(25);
//		scenario.get(4).add(railParm);
//		scenario.get(4).add(null);
//		// Result of query1
//		scenario.get(4).add(new RailRacking());
//		// Result of query2
//		RailRacking rail  = new RailRacking();
//		rail.setId(123);
//		scenario.get(4).add(rail);
//		// Excpected Result
//		scenario.get(4).add("exist");
//		// Result of query2 List
//		scenario.get(4).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editRailRacking [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			String rslt = railRackingLibraryService.editRailRacking((RailRackingModel) scenario.get(i).get(0),
//					(String) scenario.get(i).get(1));
//
//		}
//	}
//
//    @Test
//	public void testeditRailRackingInt() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u FROM PermitProjectSiteInfoEntity u WHERE u.railRakingModel = :p1 OR u.railRakingModelforGroundMounted = :p1 OR u.railRakingModelforPoleMounted = :p1 "))
//				.thenReturn(mockedQuery2);
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
//		// Result of query1
//		scenario.get(0).add(null);
//		// Result of query2
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add("error");
//		// Result of query2 List
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new RailRackingModel());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("error");
//		// Result of query2 List
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new RailRackingModel());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of query1
//		RailRacking rail = new RailRacking();
//		rail.setId(1258);
//		scenario.get(2).add(rail);
//		// Result of query2
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("success");
//		// Result of query2 List
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new RailRackingModel());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(rail);
//		// Result of query2
//		ArrayList<PermitProjectSiteInfoEntity> list = new ArrayList<PermitProjectSiteInfoEntity>();
//		list.add(null);
//		list.add(new PermitProjectSiteInfoEntity());
//		scenario.get(3).add(list);
//		// Excpected Result
//		scenario.get(3).add("success");
//		// Result of query2 List
//		scenario.get(3).add(null);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editRailRacking [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			String rslt = railRackingLibraryService.editRailRacking((RailRackingModel) scenario.get(i).get(0),
//					(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
//
//		}
//    }
//	@Test
//	public void testgetRemoveRailRackingConfirmation() {
//
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel  "
//				+ " ( u.homeOwnName, "
//				+ " u.status, "
//				+ " v.firstName, "
//				+ " v.lastName)"
//				+ " from PermitEntity u, "
//				+ " AuthentificationEntity v, "
//				+ " PermitProjectSiteInfoEntity w"
//				+ " where (w.railRakingModel = :p1 or  w.railRakingModelforGroundMounted = :p1 or w.railRakingModelforPoleMounted = :p1) and  u.isDeleted  = :p2 and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = :p2 and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add(new ArrayList<ProjectForLibrariesModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new RailRackingModel());
//		// Result of query1
//		ArrayList<ProjectForLibrariesModel> list = new ArrayList<ProjectForLibrariesModel>();
//		list.add(null);
//		list.add(new ProjectForLibrariesModel());
//		scenario.get(1).add(list);
//		// Excpected Result
//		scenario.get(1).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getRemoveRailRackingConfirmation [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//
//			List<ProjectForLibrariesModel> rslt = (List<ProjectForLibrariesModel>) railRackingLibraryService
//					.getRemoveRailRackingConfirmation((RailRackingModel) scenario.get(i).get(0));
//		}
//
//
//	}
//
//	@Test
//	public void testgetRemoveRailRackingConfirmationAPI() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel  "
//				+ " ( u.homeOwnName, "
//				+ " u.status, "
//				+ " v.firstName, "
//				+ " v.lastName)"
//				+ " from PermitEntity u, "
//				+ " AuthentificationEntity v, "
//				+ " PermitProjectSiteInfoEntity w"
//				+ " where (w.railRakingModel = :p1 or  w.railRakingModelforGroundMounted = :p1 or w.railRakingModelforPoleMounted = :p1) and  u.isDeleted  = :p2 and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = :p2 and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add(new ArrayList<ProjectForLibrariesModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		// Result of query1
//		ArrayList<ProjectForLibrariesModel> list = new ArrayList<ProjectForLibrariesModel>();
//		list.add(null);
//		list.add(new ProjectForLibrariesModel());
//		scenario.get(1).add(list);
//		// Excpected Result
//		scenario.get(1).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getRemoveRailRackingConfirmationAPI [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//
//			List<ProjectForLibrariesModel> rslt = (List<ProjectForLibrariesModel>) railRackingLibraryService
//					.getRemoveRailRackingConfirmationAPI((Integer) scenario.get(i).get(0));
//		}
//
//	}
//
//	@Test
//	public void testgetRemoveRailRackingConfirmationInt() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel  "
//				+ " ( u.homeOwnName, "
//				+ " u.status, "
//				+ " v.firstName, "
//				+ " v.lastName)"
//				+ " from PermitEntity u, "
//				+ " AuthentificationEntity v, "
//				+ " PermitProjectSiteInfoEntity w"
//				+ " where (w.railRakingModel = :p1 or  w.railRakingModelforGroundMounted = :p1 or w.railRakingModelforPoleMounted = :p1) and  u.isDeleted  = :p2 and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = :p2 and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add(new ArrayList<ProjectForLibrariesModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		// Result of query1
//		ArrayList<ProjectForLibrariesModel> list = new ArrayList<ProjectForLibrariesModel>();
//		list.add(null);
//		list.add(new ProjectForLibrariesModel());
//		scenario.get(1).add(list);
//		// Excpected Result
//		scenario.get(1).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getRemoveRailRackingConfirmation [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//
//			List<ProjectForLibrariesModel> rslt = (List<ProjectForLibrariesModel>) railRackingLibraryService
//					.getRemoveRailRackingConfirmation((Integer) scenario.get(i).get(0));
//		}
//	}
//
//	@Test
//	public void testdeleteRailRackingString() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitProjectSiteInfoEntity u WHERE (u.railRakingModel = :p1 or  u.railRakingModelforGroundMounted = :p1 or u.railRakingModelforPoleMounted = :p1) and  u.permitEntity.isDeleted  = :p2")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRackingFavLibraryEntity u WHERE u.railRacking.id = :p1")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
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
//		// Excpected Result
//		scenario.get(0).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(new RailRacking());
//		// Result of query2
//		scenario.get(1).add(null);
//		// Result of query3
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("Done");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(new RailRacking());
//		// Result of query2
//		List<PermitProjectSiteInfoEntity> list = new ArrayList<PermitProjectSiteInfoEntity>();
//		list.add(null);
//		list.add(new PermitProjectSiteInfoEntity());
//		scenario.get(2).add(list);
//		// Result of query3
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(new RailRacking());
//		// Result of query2
//		scenario.get(3).add(list);
//		// Result of query3
//		ArrayList<RailRackingFavLibraryEntity> list2 = new ArrayList<RailRackingFavLibraryEntity>();
//		list2.add(null);
//		list2.add(new RailRackingFavLibraryEntity());
//		scenario.get(3).add(list2);
//		// Excpected Result
//		scenario.get(3).add("Done");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deleteRailRacking [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(6));
//			String rslt = ((String)railRackingLibraryService.deleteRailRacking((Integer) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3)));
//
//		}
//
//
//	}
//	@Test
//	public void testdeleteRailRacking() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitProjectSiteInfoEntity u WHERE (u.railRakingModel = :p1 or  u.railRakingModelforGroundMounted = :p1 or u.railRakingModelforPoleMounted = :p1) and  u.permitEntity.isDeleted  = :p2")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRackingFavLibraryEntity u WHERE u.railRacking.id = :p1")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
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
//		// Result of query3
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(new RailRacking());
//		// Result of query2
//		scenario.get(1).add(null);
//		// Result of query3
//		scenario.get(1).add(null);
//		// Excpected Result
//		RailRacking exp = new RailRacking();
//		exp.setIsDeleted(true);
//		scenario.get(1).add(exp);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(new RailRacking());
//		// Result of query2
//		List<PermitProjectSiteInfoEntity> list = new ArrayList<PermitProjectSiteInfoEntity>();
//		list.add(null);
//		list.add(new PermitProjectSiteInfoEntity());
//		scenario.get(2).add(list);
//		// Result of query3
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add(exp);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(new RailRacking());
//		// Result of query2
//		scenario.get(3).add(list);
//		// Result of query3
//		ArrayList<RailRackingFavLibraryEntity> list2 = new ArrayList<RailRackingFavLibraryEntity>();
//		list2.add(null);
//		list2.add(new RailRackingFavLibraryEntity());
//		scenario.get(3).add(list2);
//		// Excpected Result
//		scenario.get(3).add(exp);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deleteRailRacking [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			RailRacking rslt = ((RailRacking)railRackingLibraryService.deleteRailRacking((Integer) scenario.get(i).get(0)));
//		}
//
//
//	}
//
//	@Test
//	public void testactivateRailRacking() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
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
//		// Result of query1
//		scenario.get(0).add(null);
//		// Result of query2
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
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
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(null);
//		// Result of query2
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(new RailRacking());
//		// Result of query2
//		scenario.get(3).add(null);
//		// Excpected Result
//		scenario.get(3).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of query1
//		scenario.get(4).add(new RailRacking());
//		// Result of query2
//		scenario.get(4).add(new ArrayList<RailRacking>());
//		// Excpected Result
//		scenario.get(4).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("123");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		// Result of query1
//		scenario.get(5).add(new RailRacking());
//		// Result of query2
//		ArrayList<RailRacking> list = new ArrayList<RailRacking>();
//		list.add(null);
//		list.add(new RailRacking());
//		scenario.get(5).add(list);
//		// Excpected Result
//		scenario.get(5).add("exist");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("activateRailRacking [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			railRackingLibraryService.activateRailRacking((String) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
//		}
//	}
//
//	@Test
//	public void testactivateRailRackingInt() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(new RailRacking());
//		// Excpected Result
//		scenario.get(1).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("activateRailRacking [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			railRackingLibraryService.activateRailRacking((Integer) scenario.get(i).get(0));
//		}
//
//	}
//
//	@Test
//	public void testgetSearchRailRacking() {
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		Path battery = mock(Path.class);
//
//		when(criteriaBuilder.createQuery(RailRacking.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(RailRacking.class)).thenReturn(root);
//		when(root.get(Mockito.anyString())).thenReturn(battery);
//		// when(r.get("authentificationEntity")).thenReturn(authentificationEntity);
//		when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//		when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//		when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//		when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//
//		RailRackingLibraryService railRackingLibraryService2 = Mockito.spy(railRackingLibraryService);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0) .add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new ArrayList<RailRackingModel>());
//		 //Result expected getRailRackingFavorite
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 RailRackingModelRequest rail = new RailRackingModelRequest();
//		 rail.setManufacturer("abc hdh");
//		 rail.setModelNumber("abc hdh");
//		 rail.setMountingType("Roof Mounted");
//		 rail.setFavorite(true);
//		 scenario.get(1) .add(rail);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<RailRackingModel>());
//		 //Result expected getRailRackingFavorite
//		 scenario.get(1).add(null);
//
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2) .add(rail);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 List<RailRacking> list = new ArrayList<RailRacking>();
//		 list.add(null);
//		 list.add(new RailRacking());
//		 scenario.get(2).add(list);
//	     //Result excpected
//		 scenario.get(2).add(new ArrayList<RailRackingModel>());
//		 //Result expected getRailRackingFavorite
//		 scenario.get(2).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3) .add(rail);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 List<RailRacking> list2 = new ArrayList<RailRacking>();
//		 list2.add(null);
//		 list2.add(new RailRacking());
//		 RailRacking r1 = new RailRacking();
//		 r1.setId(123);
//		 list2.add(r1);
//		 scenario.get(3).add(list2);
//	     //Result excpected
//		 List<RailRackingModel> listExp = new ArrayList<RailRackingModel>();
//		 RailRackingModel rExp = new RailRackingModel();
//		 rExp.setId(123);
//		 rExp.setIsShown(true);
//		 listExp.add(rExp);
//		 scenario.get(3).add(listExp);
//		 //Result expected getRailRackingFavorite
//		 ArrayList<Integer> listInt = new ArrayList<Integer>();
//		 listInt.add(null);
//		 listInt.add(1258);
//		 listInt.add(123);
//		 scenario.get(3).add(listInt);
//
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4) .add(new RailRackingModelRequest());
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 //Result of the query1
//		 scenario.get(4).add(list2);
//	     //Result excpected
//		 List<RailRackingModel> listExp2 = new ArrayList<RailRackingModel>();
//		 RailRackingModel rExp2 = new RailRackingModel();
//		 rExp2.setId(0);
//		 rExp2.setIsShown(false);
//		 listExp2.add(new RailRackingModel());
//		 listExp2.add(rExp2);
//		 listExp2.add(rExp);
//		 scenario.get(4).add(listExp2);
//		 //Result expected getRailRackingFavorite
//		 scenario.get(4).add(listInt);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getSearchRailRacking [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			Mockito.doReturn((List) scenario.get(i).get(5)).when(railRackingLibraryService2)
//			.getRailRackingFavorite(Mockito.anyString());
//			ArrayList<RailRackingModel> result = ((ArrayList<RailRackingModel>) railRackingLibraryService2
//					.getSearchRailRacking((RailRackingModelRequest) scenario.get(i).get(0),
//							(Boolean) scenario.get(i).get(1), (String) scenario.get(i).get(2)));
//
//		}
//
//	}
//
//	@Test
//	public void testgetSearchRailRackingSear() {
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//
//		when(criteriaBuilder.createQuery(RailRacking.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(RailRacking.class)).thenReturn(root);
//		when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//		when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from RailRacking u WHERE u.idOwner.id = :p1 and u.isDeleted = :p2 and u.typeOfSystem = :p3"))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.idOwner.id = :p1 and u.isDeleted = :p2"))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRackingFavLibraryEntity u WHERE u.railRacking.id = :p1"))
//				.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		RailRackingLibraryService railRackingLibraryService2 = Mockito.spy(railRackingLibraryService);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Result of query2
//		scenario.get(0).add(null);
//		// Result of query3
//		scenario.get(0).add(null);
//		// Result of query4
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add(new ArrayList<RailRackingModel>());
//		// Result Expected on getRailRackingFavorite
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new SearchRailRackingModelRequest());
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Result of query3
//		scenario.get(1).add(null);
//		// Result of query4
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add(new ArrayList<RailRackingModel>());
//		// Result Expected on getRailRackingFavorite
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		SearchRailRackingModelRequest rail = new SearchRailRackingModelRequest();
//		RailRackingModelRequest railReq = new RailRackingModelRequest();
//		railReq.setFavorite(true);
//		rail.setRailRequest(railReq);
//		rail.setIdUser("255");
//		railReq.setManufacturer("abc fd_gg");
//		railReq.setModelNumber("abc fd_gg");
//		scenario.get(2).add(rail);
//		// Result of query1
//		scenario.get(2).add(null);
//		// Result of query2
//		scenario.get(2).add(null);
//		// Result of query3
//		scenario.get(2).add(null);
//		// Result of query4
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add(new ArrayList<RailRackingModel>());
//		// Result Expected on getRailRackingFavorite
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(rail);
//		// Result of query1
//		ArrayList<RailRacking> list = new ArrayList<RailRacking>();
//		list.add(null);
//		list.add(new RailRacking());
//		RailRacking rReq1 = new RailRacking();
//		rReq1.setId(123);
//		list.add(rReq1);
//		scenario.get(3).add(list);
//		// Result of query2
//		scenario.get(3).add(null);
//		// Result of query3
//		scenario.get(3).add(null);
//		// Result of query4
//		scenario.get(3).add(null);
//		// Excpected Result
//		List<RailRackingModel> listExp = new ArrayList<RailRackingModel>();
//		RailRackingModel rExp = new RailRackingModel();
//		rExp.setId(123);
//		rExp.setIsShown(true);
//		listExp.add(rExp);
//		scenario.get(3).add(listExp);
//		// Result Expected on getRailRackingFavorite
//		ArrayList<Integer> listInt = new ArrayList<Integer>();
//		listInt.add(null);
//		listInt.add(1258);
//		listInt.add(123);
//		scenario.get(3).add(listInt);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(rail);
//		// Result of query1
//		scenario.get(4).add(list);
//		// Result of query2
//		scenario.get(4).add(null);
//		// Result of query3
//		ArrayList<RailRacking> listQ3 = new ArrayList<RailRacking>();
//		listQ3.add(null);
//		RailRacking railQ3 = new RailRacking();
//		railQ3.setManufacturer("fd_gg");
//		railQ3.setModel("fd_gg");
//		listQ3.add(railQ3);
//		scenario.get(4).add(listQ3);
//		// Result of query4
//		scenario.get(4).add(null);
//		// Excpected Result
//		scenario.get(4).add(listExp);
//		// Result Expected on getRailRackingFavorite
//		scenario.get(4).add(listInt);
//
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(rail);
//		// Result of query1
//		scenario.get(5).add(list);
//		// Result of query2
//		scenario.get(5).add(null);
//		// Result of query3
//		scenario.get(5).add(listQ3);
//		// Result of query4
//		ArrayList<RailRackingFavLibraryEntity> listQ5 = new ArrayList<RailRackingFavLibraryEntity>();
//		listQ5.add(null);
//		scenario.get(5).add(listQ5);
//		// Excpected Result
//		List<RailRackingModel> listExp2 = new ArrayList<RailRackingModel>();
//		listExp2.addAll(listExp);//fd_gg
//		RailRackingModel rExp2 = new RailRackingModel();
//		rExp2.setModelNumber("fd_gg");
//		rExp2.setManufacturer("fd_gg");
//		rExp2.setIsShown(true);
//		rExp2.setId(0);
//		listExp2.add(rExp2);
//		scenario.get(5).add(listExp2);
//		// Result Expected on getRailRackingFavorite
//		scenario.get(5).add(listInt);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		SearchRailRackingModelRequest rail2 = new SearchRailRackingModelRequest();
//		RailRackingModelRequest railReq2 = new RailRackingModelRequest();
//		railReq2.setFavorite(false);
//		rail2.setRailRequest(railReq2);
//		rail2.setIdUser("255");
//		railReq2.setManufacturer("abc fd_gg");
//		railReq2.setModelNumber("abc fd_gg");
//		scenario.get(6).add(rail2);
//		// Result of query1
//		scenario.get(6).add(null);
//		// Result of query2
//		scenario.get(6).add(null);
//		// Result of query3
//		scenario.get(6).add(null);
//		// Result of query4
//		scenario.get(6).add(null);
//		// Excpected Result
//		scenario.get(6).add(new ArrayList<RailRackingModel>());
//		// Result Expected on getRailRackingFavorite
//		scenario.get(6).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(7).add(rail2);
//		// Result of query1
//		scenario.get(7).add(list);
//		// Result of query2
//		scenario.get(7).add(null);
//		// Result of query3
//		scenario.get(7).add(null);
//		// Result of query4
//		scenario.get(7).add(null);
//		// Excpected Result
//		List<RailRackingModel> listExp3 = new ArrayList<RailRackingModel>();
//		listExp3.add(new RailRackingModel());
//		RailRackingModel rExp4 = new RailRackingModel();
//		rExp4.setId(0);
//		rExp4.setIsShown(false);
//		listExp3.add(rExp4);
//		RailRackingModel rExp5 = new RailRackingModel();
//		rExp5.setIsShown(false);
//		rExp5.setId(123);
//		listExp3.add(rExp5);
//		scenario.get(7).add(listExp3);
//		// Result Expected on getRailRackingFavorite
//		scenario.get(7).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(8).add(rail2);
//		// Result of query1
//		scenario.get(8).add(list);
//		// Result of query2
//		scenario.get(8).add(null);
//		// Result of query3
//		scenario.get(8).add(listQ3);
//		// Result of query4
//		scenario.get(8).add(null);
//		// Excpected Result
//		scenario.get(8).add(listExp3);
//		// Result Expected on getRailRackingFavorite
//		scenario.get(8).add(null);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getSearchRailRacking [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			Mockito.doReturn((List) scenario.get(i).get(6)).when(railRackingLibraryService2)
//					.getRailRackingFavorite(Mockito.anyString());
//			ArrayList<RailRackingModel> result = ((ArrayList<RailRackingModel>) railRackingLibraryService2
//					.getSearchRailRacking((SearchRailRackingModelRequest) scenario.get(i).get(0)));
//
//		}
//
//	}
//
//	@Test
//	public void testgetRoofRailRacking() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from RailRacking u WHERE u.id IN :p1 and (u.typeOfSystem = 'Roof Mounted' or u.typeOfSystem = 'Roof/Ground Mounted') ORDER BY u.manufacturer, u.model"))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		RailRackingLibraryService railRackingLibraryService2 = Mockito.spy(railRackingLibraryService);
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
//		scenario.get(0).add(new ArrayList<RailRacking>());
//		// List expected from getRailRackingFavorite
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add(new ArrayList<RailRacking>());
//		// List expected from getRailRackingFavorite
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		// Result of query1
//		scenario.get(2).add(null);
//		// Result of query2
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add(new ArrayList<RailRacking>());
//		// List expected from getRailRackingFavorite
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		// Result of query1
//		PermitEntity per = new PermitEntity();
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setId(1133);
//		per.setAuthentificationEntity(auth);
//		scenario.get(3).add(per);
//		// Result of query2
//		scenario.get(3).add(null);
//		// Excpected Result
//		scenario.get(3).add(new ArrayList<RailRacking>());
//		// List expected from getRailRackingFavorite
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		// Result of query1
//		scenario.get(4).add(per);
//		// Result of query2
//		ArrayList<RailRacking> list = new ArrayList<RailRacking>();
//		list.add(null);
//		list.add(new RailRacking());
//		scenario.get(4).add(list);
//		// Excpected Result
//		scenario.get(4).add(list);
//		// List expected from getRailRackingFavorite
//		scenario.get(4).add(null);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getRoofRailRacking [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			Mockito.doReturn((List) scenario.get(i).get(4)).when(railRackingLibraryService2)
//					.getRailRackingFavorite(Mockito.anyString());
//
//			List<RailRacking> rslt = (List<RailRacking>) railRackingLibraryService2
//					.getRoofRailRacking((String) scenario.get(i).get(0));
//		}
//	}
//
//	@Test
//	public void testrefreshRoofRailRacking() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
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
//		scenario.get(0).add(false);
//		// List expected from getRailRackingFavorite
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add(false);
//		// List expected from getRailRackingFavorite
//		scenario.get(1).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add(false);
//		// List expected from getRailRackingFavorite
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		// Result of query1
//		PermitEntity per = new PermitEntity();
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setId(1133);
//		per.setAuthentificationEntity(auth);
//		scenario.get(3).add(per);
//		// Excpected Result
//		scenario.get(3).add(true);
//		// List expected from getRailRackingFavorite
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add("1133");
//		// Result of query1
//		scenario.get(4).add(per);
//		// Excpected Result
//		scenario.get(4).add(false);
//		// List expected from getRailRackingFavorite
//		ArrayList<Integer> list = new ArrayList<>();
//		list.add(null);
//		list.add(1);
//		list.add(1133);
//		scenario.get(4).add(list);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("refreshRoofRailRacking [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			RailRackingLibraryService railRackingLibraryService2 = Mockito.spy(railRackingLibraryService);
//			Mockito.doReturn((List) scenario.get(i).get(4)).when(railRackingLibraryService2)
//					.getRailRackingFavorite(Mockito.anyString());
//
//			railRackingLibraryService2.refreshRoofRailRacking((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		}
//
//	}
//
//	@Test
//	public void testgetContractorRoofRailRacking() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from RailRacking u WHERE u.id IN :p1 and (u.typeOfSystem = 'Roof Mounted' or u.typeOfSystem = 'Roof/Ground Mounted') ORDER BY u.manufacturer, u.model"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add(new ArrayList<RailRacking>());
//		// List expected from getRailRackingFavorite
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		// Result of query1
//		List<RailRacking> list = new ArrayList<>();
//		list.add(null);
//		list.add(new RailRacking());
//		scenario.get(1).add(list);
//		// Excpected Result
//		scenario.get(1).add(list);
//		// List expected from getRailRackingFavorite
//		scenario.get(1).add(null);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getContractorRoofRailRacking [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			RailRackingLibraryService railRackingLibraryService2 = Mockito.spy(railRackingLibraryService);
//			Mockito.doReturn((List) scenario.get(i).get(3)).when(railRackingLibraryService2)
//					.getRailRackingFavorite(Mockito.anyString());
//
//			List<RailRacking> rslt = (List<RailRacking>) railRackingLibraryService2
//					.getContractorRoofRailRacking((String) scenario.get(i).get(0));
//		}
//	}
//
//	@Test
//	public void testgetGroundRailRacking() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from RailRacking u WHERE u.id IN :p1 and u.typeOfSystem = 'Pole Mounted' and u.isDeleted = :p2 ORDER BY u.manufacturer, u.model"))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		RailRackingLibraryService railRackingLibraryService2 = Mockito.spy(railRackingLibraryService);
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
//		scenario.get(0).add(new ArrayList<RailRacking>());
//		// List expected from getRailRackingFavorite
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add(new ArrayList<RailRacking>());
//		// List expected from getRailRackingFavorite
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		// Result of query1
//		scenario.get(2).add(null);
//		// Result of query2
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add(new ArrayList<RailRacking>());
//		// List expected from getRailRackingFavorite
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		// Result of query1
//		PermitEntity per = new PermitEntity();
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setId(1133);
//		per.setAuthentificationEntity(auth);
//		scenario.get(3).add(per);
//		// Result of query2
//		scenario.get(3).add(null);
//		// Excpected Result
//		scenario.get(3).add(new ArrayList<RailRacking>());
//		// List expected from getRailRackingFavorite
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		// Result of query1
//		scenario.get(4).add(per);
//		// Result of query2
//		ArrayList<RailRacking> list = new ArrayList<RailRacking>();
//		list.add(null);
//		list.add(new RailRacking());
//		scenario.get(4).add(list);
//		// Excpected Result
//		scenario.get(4).add(list);
//		// List expected from getRailRackingFavorite
//		scenario.get(4).add(null);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getPoleRailRacking [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			Mockito.doReturn((List) scenario.get(i).get(4)).when(railRackingLibraryService2)
//					.getRailRackingFavorite(Mockito.anyString());
//
//			List<RailRacking> rslt = (List<RailRacking>) railRackingLibraryService2
//					.getPoleRailRacking((String) scenario.get(i).get(0));
//		}
//	}
//
//	@Test
//	public void testgetContractorGroundRailRacking() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from RailRacking u WHERE u.id IN :p1 and (u.typeOfSystem = 'Ground Mounted' or u.typeOfSystem = 'Roof/Ground Mounted') ORDER BY u.manufacturer, u.model"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add(new ArrayList<RailRacking>());
//		// List expected from getRailRackingFavorite
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		// Result of query1
//		List<RailRacking> list = new ArrayList<>();
//		list.add(null);
//		list.add(new RailRacking());
//		scenario.get(1).add(list);
//		// Excpected Result
//		scenario.get(1).add(list);
//		// List expected from getRailRackingFavorite
//		scenario.get(1).add(null);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getContractorGroundRailRacking [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			RailRackingLibraryService railRackingLibraryService2 = Mockito.spy(railRackingLibraryService);
//			Mockito.doReturn((List) scenario.get(i).get(3)).when(railRackingLibraryService2)
//					.getRailRackingFavorite(Mockito.anyString());
//
//			List<RailRacking> rslt = (List<RailRacking>) railRackingLibraryService2
//					.getContractorGroundRailRacking((String) scenario.get(i).get(0));
//		}
//	}
//
//	@Test
//	public void testgetPoleRailRacking() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id IN :p1 and u.typeOfSystem = 'Pole Mounted' and u.isDeleted = :p2 ORDER BY u.manufacturer, u.model")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		RailRackingLibraryService railRackingLibraryService2 = Mockito.spy(railRackingLibraryService);
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
//		scenario.get(0).add(new ArrayList<RailRacking>());
//		//List expected from getRailRackingFavorite
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add(new ArrayList<RailRacking>());
//		//List expected from getRailRackingFavorite
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		// Result of query1
//		scenario.get(2).add(null);
//		// Result of query2
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add(new ArrayList<RailRacking>());
//		//List expected from getRailRackingFavorite
//		scenario.get(2).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		// Result of query1
//		PermitEntity per = new PermitEntity();
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setId(1133);
//		per.setAuthentificationEntity(auth);
//		scenario.get(3).add(per);
//		// Result of query2
//		scenario.get(3).add(null);
//		// Excpected Result
//		scenario.get(3).add(new ArrayList<RailRacking>());
//		//List expected from getRailRackingFavorite
//		scenario.get(3).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		// Result of query1
//		scenario.get(4).add(per);
//		// Result of query2
//		ArrayList<RailRacking> list = new ArrayList<RailRacking>();
//		list.add(null);
//		list.add(new RailRacking());
//		scenario.get(4).add(list);
//		// Excpected Result
//		scenario.get(4).add(list);
//		//List expected from getRailRackingFavorite
//		scenario.get(4).add(null);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getPoleRailRacking [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			Mockito.doReturn((List) scenario.get(i).get(4)).when(railRackingLibraryService2)
//					.getRailRackingFavorite(Mockito.anyString());
//
//			List<RailRacking> rslt = (List<RailRacking>) railRackingLibraryService2
//					.getPoleRailRacking((String) scenario.get(i).get(0));
//		}
//	}
//
//	@Test
//	public void testgetContractorPoleRailRacking() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id IN :p1 and u.typeOfSystem = 'Pole Mounted' and u.isDeleted = :p2 ORDER BY u.manufacturer, u.model")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add(new ArrayList<RailRacking>());
//		//List expected from getRailRackingFavorite
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		// Result of query1
//		List<RailRacking>  list =new ArrayList<>();
//		list.add(null);
//		list.add(new RailRacking());
//		scenario.get(1).add(list);
//		// Excpected Result
//		scenario.get(1).add(list);
//		//List expected from getRailRackingFavorite
//		scenario.get(1).add(null);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getListOfGroundRailRacking [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			RailRackingLibraryService railRackingLibraryService2 = Mockito.spy(railRackingLibraryService);
//			Mockito.doReturn((List) scenario.get(i).get(3)).when(railRackingLibraryService2)
//					.getRailRackingFavorite(Mockito.anyString());
//
//			List<RailRacking> rslt = (List<RailRacking>) railRackingLibraryService2
//					.getContractorPoleRailRacking((String) scenario.get(i).get(0));
//		}
//	}
//
//	@Test
//	public void testeditRailRackingNotification() {
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
//			System.out.println("editRailRackingNotification [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			railRackingLibraryService.editRailRackingNotification(
//					(String) scenario.get(i).get(0), (String) scenario.get(i).get(1), (String) scenario.get(i).get(2));
//
//		}
//
//	}
//
//	@Test
//	public void testaddRailRackingNotification() {
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
//			System.out.println("addRailRackingNotification [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			 railRackingLibraryService.addRailRackingNotification(
//					(String) scenario.get(i).get(0), (String) scenario.get(i).get(1), (String) scenario.get(i).get(2));
//
//		}
//
//	}
//
//	@Test
//	public void testsendCorrectionRequest() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u Where u.id = :p1 ")).thenReturn(mockedQuery1);
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
//		RailRakingCorrectionModel acComSLC = new RailRakingCorrectionModel();
//		acComSLC.setId(0);
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
//		RailRakingCorrectionModel acComSLC1 = new RailRakingCorrectionModel();
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
//		RailRakingCorrectionModel acComSLC2 = new RailRakingCorrectionModel();
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
//		RailRakingCorrectionModel acComSLC4 = new RailRakingCorrectionModel();
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
//		RailRacking lib = new RailRacking();
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
//			System.out.println("RailRakingCorrectionModel [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			railRackingLibraryService
//					.sendCorrectionRequest((RailRakingCorrectionModel) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testgetListOfGroundRailRacking() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from RailRacking u WHERE (u.typeOfSystem = 'Ground Mounted' or u.typeOfSystem = 'Roof/Ground Mounted') AND u.isDeleted= :p1 ORDER BY u.manufacturer, u.model"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add(new ArrayList<RailRacking>());
//
//		scenario.add(new ArrayList<Object>());
//		// Result of query1
//		ArrayList<RailRacking> list = new ArrayList<RailRacking>();
//		list.add(null);
//		list.add(new RailRacking());
//		scenario.get(1).add(list);
//		// Excpected Result
//		scenario.get(1).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getListOfGroundRailRacking [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			List<RailRacking> rslt = (List<RailRacking>) railRackingLibraryService.getListOfGroundRailRacking();
//		}
//
//	}
//
//}
