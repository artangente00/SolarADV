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
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.RailRacking;
//import com.PlayGroundAdv.Solar.Entity.RailRackingFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.Entity.RoofAttachmentFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.RoofAttachmentsEntity;
//import com.PlayGroundAdv.Solar.model.NewRailRackingModel;
//import com.PlayGroundAdv.Solar.model.NewRoofAttachmentModel;
//import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
//import com.PlayGroundAdv.Solar.model.RailRackingModel;
//import com.PlayGroundAdv.Solar.model.RailRakingCorrectionModel;
//import com.PlayGroundAdv.Solar.model.RoofAttachementCorrectionModel;
//import com.PlayGroundAdv.Solar.model.RoofAttachmentModel;
//import com.PlayGroundAdv.Solar.model.UsersEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.RailRackingLibraryService;
//import com.PlayGroundAdv.Solar.Services.RoofAttachmentLibraryService;
//
//public class TestRoofAttachmentLibraryService {
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
//	RoofAttachmentLibraryService roofAttachmentLibraryService = new RoofAttachmentLibraryService();
//
//
//    @Before
//	public void setupMock() {
//    	roofAttachmentLibraryService = new RoofAttachmentLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//
//	@Test
//	public void testgetAllRoofAttachment() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentsEntity u WHERE u.isDeleted = :p1 ORDER BY u.manufacturer,u.model"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		RoofAttachmentLibraryService roofAttachmentLibraryService2 = Mockito.spy(roofAttachmentLibraryService);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(new ArrayList<RoofAttachmentsEntity>());
//		// Result excpected
//		scenario.get(0).add(new ArrayList<RoofAttachmentModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(false);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<RoofAttachmentModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(false);
//		// Result of the query1
//		ArrayList<RoofAttachmentsEntity> list = new ArrayList<RoofAttachmentsEntity>();
//		list.add(null);
//		RoofAttachmentsEntity rail = new RoofAttachmentsEntity();
//		rail.setId(123);
//		rail.setModel("aaa");
//		list.add(rail);
//		scenario.get(2).add(list);
//		// Result excpected
//		ArrayList<RoofAttachmentModel> listExp = new ArrayList<RoofAttachmentModel>();
//		listExp.add(new RoofAttachmentModel());
//		RoofAttachmentModel exp = new RoofAttachmentModel();
//		exp.setId(123);
//		exp.setIsShown(false);
//		exp.setModel("aaa");
//		exp.setIsDeleted(false);
//		listExp.add(exp);
//		scenario.get(2).add(listExp);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllRoofAttachment [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			Mockito.doReturn(null).when(roofAttachmentLibraryService2).getRoofAttachmentFavorite(Mockito.anyString());
//			ArrayList<RoofAttachmentModel> result = ((ArrayList<RoofAttachmentModel>) roofAttachmentLibraryService2
//					.getAllRoofAttachment((String) scenario.get(i).get(0), (Boolean) scenario.get(i).get(1)));
//
//		}
//
//	}
//
//	@Test
//	public void testgetRoofAttachmentFavorite() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 "))
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
//		scenario.get(3).add(new ArrayList<RoofAttachmentFavLibraryEntity>());
//		// Result excpected
//		scenario.get(3).add(new ArrayList<Integer>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		// Result of the query1
//		ArrayList<RoofAttachmentFavLibraryEntity> list = new ArrayList<RoofAttachmentFavLibraryEntity>();
//		list.add(null);
//		list.add(new RoofAttachmentFavLibraryEntity());
//		scenario.get(4).add(list);
//		// Result excpected
//		scenario.get(4).add(new ArrayList<Integer>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("123");
//		// Result of the query1
//		ArrayList<RoofAttachmentFavLibraryEntity> list2 = new ArrayList<RoofAttachmentFavLibraryEntity>();
//		RoofAttachmentFavLibraryEntity dc = new RoofAttachmentFavLibraryEntity();
//		dc.setRoofAttachment(new RoofAttachmentsEntity());
//		list2.add(dc);
//		RoofAttachmentFavLibraryEntity dc2 = new RoofAttachmentFavLibraryEntity();
//		RoofAttachmentsEntity dcEnt = new RoofAttachmentsEntity();
//		dcEnt.setId(123);
//		dc2.setRoofAttachment(dcEnt);
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
//
//					roofAttachmentLibraryService.getRoofAttachmentFavorite((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testgetAllDeletedRoofAttachment() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentsEntity u WHERE u.isDeleted = :p1 ORDER BY u.manufacturer"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(new ArrayList<RoofAttachmentsEntity>());
//		// Result excpected
//		scenario.get(0).add(new ArrayList<RoofAttachmentsEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<RoofAttachmentsEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<RoofAttachmentsEntity> list = new ArrayList<RoofAttachmentsEntity>();
//		list.add(null);
//		RoofAttachmentsEntity rail = new RoofAttachmentsEntity();
//		rail.setId(123);
//		rail.setModel("aaa");
//		list.add(rail);
//		scenario.get(2).add(list);
//		// Result excpected
//		ArrayList<RoofAttachmentModel> listExp = new ArrayList<RoofAttachmentModel>();
//		listExp.add(new RoofAttachmentModel());
//		RoofAttachmentModel exp = new RoofAttachmentModel();
//		exp.setId(123);
//		exp.setIsDeleted(false);
//		exp.setModel("aaa");
//		listExp.add(exp);
//		scenario.get(2).add(listExp);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllDeletedRoofAttachment [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			ArrayList<RoofAttachmentModel> result = ((ArrayList<RoofAttachmentModel>) roofAttachmentLibraryService
//					.getAllDeletedRoofAttachment());
//
//		}
//
//	}
//
//	@Test
//	public void testeditRoofAttachmentFavoriteList() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentsEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentFavLibraryEntity u WHERE u.roofAttachment.id = :p1 and u.authentificationEntity.id = :p2")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//              	+ "p.permitEntity.authentificationEntity.id = :p1 and p.railConnectionModel = :p2 ")).thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
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
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result of the query4
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(true);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result of the query4
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(true);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result of the query4
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add(true);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(new RoofAttachmentsEntity());
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result of the query4
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add(true);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(4).add(new RoofAttachmentsEntity());
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result of the query4
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("Done");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(null);
//		scenario.get(5).add(false);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add("123");
//		// Result of the query1
//		scenario.get(5).add(null);
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result of the query3
//		scenario.get(5).add(null);
//		// Result of the query4
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(6).add(null);
//		scenario.get(6).add(false);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add("123");
//		// Result of the query1
//		scenario.get(6).add(null);
//		// Result of the query2
//		scenario.get(6).add(null);
//		// Result of the query3
//		RoofAttachmentFavLibraryEntity q1 = new RoofAttachmentFavLibraryEntity();
//		q1.setRoofAttachment(new RoofAttachmentsEntity());
//		scenario.get(6).add(q1);
//		// Result of the query4
//		scenario.get(6).add(null);
//		// Result excpected
//		scenario.get(6).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(7).add(null);
//		scenario.get(7).add(false);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add("123");
//		// Result of the query1
//		scenario.get(7).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(7).add(null);
//		// Result of the query3
//		scenario.get(7).add(q1);
//		// Result of the query4
//		scenario.get(7).add(null);
//		// Result excpected
//		scenario.get(7).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(8).add(null);
//		scenario.get(8).add(false);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.get(8).add("123");
//		// Result of the query1
//		scenario.get(8).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(8).add(null);
//		// Result of the query3
//		scenario.get(8).add(q1);
//		// Result of the query4
//		List<PermitProjectSiteInfoEntity> list = new ArrayList<PermitProjectSiteInfoEntity>();
//		list.add(null);
//		list.add(new PermitProjectSiteInfoEntity());
//		scenario.get(8).add(list);
//		// Result excpected
//		scenario.get(8).add("Done");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editRoofAttachmentFavoriteList [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(6));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(8));
//
//					roofAttachmentLibraryService.editRoofAttachmentFavoriteList((Integer) scenario.get(i).get(0),
//							(Boolean) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3), (String) scenario.get(i).get(4));
//
//		}
//
//	}
//
//	@Test
//	public void testeditOtherUserFavorite() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentsEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentFavLibraryEntity u WHERE u.roofAttachment.id = :p1 and u.authentificationEntity.id = :p2")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//
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
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		Integer[] ListUsers = { null };
//		scenario.get(1).add(ListUsers);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		Integer[] ListUsers2 = { 12,25 };
//		scenario.get(2).add(ListUsers2);
//		scenario.get(2).add(true);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(ListUsers2);
//		scenario.get(3).add(true);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(new RoofAttachmentsEntity());
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(ListUsers2);
//		scenario.get(4).add(true);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(4).add(new RoofAttachmentsEntity());
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("Done");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(ListUsers2);
//		scenario.get(5).add(false);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		// Result of the query1
//		scenario.get(5).add(null);
//		// Result of the query2
//		scenario.get(5).add(new RoofAttachmentsEntity());
//		// Result of the query3
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(6).add(ListUsers2);
//		scenario.get(6).add(false);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		// Result of the query1
//		scenario.get(6).add(null);
//		// Result of the query2
//		scenario.get(6).add(new RoofAttachmentsEntity());
//		// Result of the query3
//		RoofAttachmentFavLibraryEntity q1 = new RoofAttachmentFavLibraryEntity();
//		q1.setRoofAttachment(new RoofAttachmentsEntity());
//		scenario.get(6).add(q1);
//		// Result excpected
//		scenario.get(6).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(7).add(ListUsers2);
//		scenario.get(7).add(false);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		// Result of the query1
//		scenario.get(7).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(7).add(new RoofAttachmentsEntity());
//		// Result of the query3
//		scenario.get(7).add(q1);
//		// Result excpected
//		scenario.get(7).add("Done");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editManyUsersFavoriteList [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(6));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(7));
//
//					roofAttachmentLibraryService.editOtherUserFavorite((Integer[]) scenario.get(i).get(0),
//							(Boolean) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3), (String) scenario.get(i).get(4));
//
//		}
//	}
//
//	@Test
//	public void testcheckExistent() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from RoofAttachmentsEntity u WHERE u.model = :p1 AND u.manufacturer = :p2 AND u.isDeleted=false"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from RoofAttachmentsEntity u WHERE u.model = :p1 AND u.manufacturer != :p2 AND u.isDeleted=false"))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		RoofAttachmentLibraryService roofAttachmentLibraryService2 = Mockito.spy(roofAttachmentLibraryService);
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
//		scenario.get(0).add(new ArrayList<RoofAttachmentModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new NewRoofAttachmentModel());
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<RoofAttachmentModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new NewRoofAttachmentModel());
//		scenario.get(2).add(null);
//		// Result of the query1
//		List<RoofAttachmentsEntity> list = new ArrayList<RoofAttachmentsEntity>();
//		list.add(null);
//		list.add(new RoofAttachmentsEntity());
//		RoofAttachmentsEntity bt = new RoofAttachmentsEntity();
//		bt.setId(223);
//		bt.setManufacturer("abc");
//		list.add(bt);
//		scenario.get(2).add(list);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		List<RoofAttachmentModel> listExpec = new ArrayList<RoofAttachmentModel>();
//		listExpec.add(new RoofAttachmentModel());
//		RoofAttachmentModel btExp2 = new RoofAttachmentModel();
//		btExp2.setId(0);
//		btExp2.setIsDeleted(false);
//		btExp2.setIsShown(false);
//		listExpec.add(btExp2);
//		RoofAttachmentModel btExp = new RoofAttachmentModel();
//		btExp.setId(223);
//		btExp.setManufacturer("abc");
//		btExp.setIsShown(false);
//		btExp.setIsDeleted(false);
//		listExpec.add(btExp);
//		scenario.get(2).add(listExpec);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new NewRoofAttachmentModel());
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add(listExpec);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("checkExistent [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//
//			Mockito.doReturn(new ArrayList<Integer>()).when(roofAttachmentLibraryService2)
//					.getRoofAttachmentFavorite(Mockito.any());
//			ArrayList<RoofAttachmentModel> result = ((ArrayList<RoofAttachmentModel>) roofAttachmentLibraryService2
//					.checkExistent((NewRoofAttachmentModel) scenario.get(i).get(0),
//							(String) scenario.get(i).get(1)));
//
//		}
//
//	}
//
//	@Test
//	public void testaddRoofAttachment() {
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
//		scenario.get(0).add(new RoofAttachmentsEntity());
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
//		scenario.get(1).add(new RoofAttachmentsEntity());
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
//		scenario.get(2).add(new RoofAttachmentsEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new NewRoofAttachmentModel());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(null);
//		// Result of query2
//		scenario.get(3).add(null);
//		// Excpected Result
//		RoofAttachmentsEntity exp = new RoofAttachmentsEntity();
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
//		scenario.get(4).add(new NewRoofAttachmentModel());
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
//		RoofAttachmentsEntity exp1 = new RoofAttachmentsEntity();
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
//			RoofAttachmentsEntity rslt = (RoofAttachmentsEntity) roofAttachmentLibraryService.addRoofAttachment(
//					(NewRoofAttachmentModel) scenario.get(i).get(0), (String) scenario.get(i).get(1),
//					(String) scenario.get(i).get(2), (String) scenario.get(i).get(3), (Integer) scenario.get(i).get(4));
//
//		}
//
//	}
//
//	@Test
//	public void testeditRoofAttachment() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from RoofAttachmentsEntity u WHERE u.manufacturer = :p1 AND u.model =:p2 AND u.isDeleted=false"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentsEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u FROM PermitProjectSiteInfoEntity u WHERE u.railConnectionModel = :p1"))
//				.thenReturn(mockedQuery3);
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
//		// Result of query2 List
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new RoofAttachmentModel());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Result of query3
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("error");
//		// Result of query2 List
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new RoofAttachmentModel());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(null);
//		// Result of query2
//		scenario.get(2).add(new RoofAttachmentsEntity());
//		// Result of query3
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("success");
//		// Result of query2 List
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		RoofAttachmentModel param1 = new RoofAttachmentModel();
//		param1.setId(123);
//		scenario.get(3).add(param1);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of query1
//		RoofAttachmentsEntity Q1 = new RoofAttachmentsEntity();
//		Q1.setId(123);
//		scenario.get(3).add(Q1);
//		// Result of query2
//		scenario.get(3).add(null);
//		// Result of query3
//		scenario.get(3).add(null);
//		// Excpected Result
//		scenario.get(3).add("error");
//		// Result of query1 List
//		ArrayList<RoofAttachmentsEntity> list = new ArrayList<RoofAttachmentsEntity>();
//		list.add(null);
//		list.add(new RoofAttachmentsEntity());
//		scenario.get(3).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(param1);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of query1
//		RoofAttachmentsEntity Q2 = new RoofAttachmentsEntity();
//		Q2.setId(122);
//		scenario.get(4).add(Q2);
//		// Result of query2
//		scenario.get(4).add(null);
//		// Result of query3
//		scenario.get(4).add(null);
//		// Excpected Result
//		scenario.get(4).add("exist");
//		// Result of query3 List
//		scenario.get(4).add(list);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(param1);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		// Result of query1
//		scenario.get(5).add(Q1);
//		// Result of query2
//		scenario.get(5).add(new RoofAttachmentsEntity());
//		// Result of query3
//		ArrayList<PermitProjectSiteInfoEntity> listQ3 = new ArrayList<PermitProjectSiteInfoEntity>();
//		listQ3.add(null);
//		listQ3.add(new PermitProjectSiteInfoEntity());
//		scenario.get(5).add(listQ3);
//		// Excpected Result
//		scenario.get(5).add("success");
//		// Result of query1 List
//		scenario.get(5).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editRailRacking [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(6));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(8));
//			String rslt = roofAttachmentLibraryService.editRoofAttachment((RoofAttachmentModel) scenario.get(i).get(0),
//					(String) scenario.get(i).get(1), (String) scenario.get(i).get(2), (String) scenario.get(i).get(3));
//
//		}
//
//	}
//
//	@Test
//	public void testgetRemoveRoofAttachmentConfirmation() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel  "
//				+ " ( u.homeOwnName, " + " u.status, " + " v.firstName, " + " v.lastName)"
//				+ " from PermitEntity u, " + " AuthentificationEntity v, " + " PermitProjectSiteInfoEntity w"
//				+ " where w.railConnectionModel = :p1 and  u.isDeleted  = :p2 and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = :p2 and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id"))
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
//		scenario.get(0).add(new ArrayList<ProjectForLibrariesModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new RoofAttachmentModel());
//		// Result of query1
//		ArrayList<ProjectForLibrariesModel> list = new ArrayList<ProjectForLibrariesModel>();
//		list.add(null);
//		list.add(new ProjectForLibrariesModel());
//		scenario.get(1).add(list);
//		// Excpected Result
//		scenario.get(1).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getRemoveRoofAttachmentConfirmation [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//
//			List<ProjectForLibrariesModel> rslt = (List<ProjectForLibrariesModel>) roofAttachmentLibraryService
//					.getRemoveRoofAttachmentConfirmation((RoofAttachmentModel) scenario.get(i).get(0));
//		}
//
//	}
//
//	@Test
//	public void testdeleteRoofAttachment() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentsEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from PermitProjectSiteInfoEntity u WHERE u.railConnectionModel = :p1 and  u.permitEntity.isDeleted  = :p2"))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentFavLibraryEntity u WHERE u.roofAttachment.id = :p1"))
//				.thenReturn(mockedQuery3);
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
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(new RoofAttachmentsEntity());
//		// Result of query2
//		scenario.get(1).add(null);
//		// Result of query3
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(new RoofAttachmentsEntity());
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
//		scenario.get(3).add(new RoofAttachmentsEntity());
//		// Result of query2
//		scenario.get(3).add(list);
//		// Result of query3
//		ArrayList<RoofAttachmentFavLibraryEntity> list2 = new ArrayList<RoofAttachmentFavLibraryEntity>();
//		list2.add(null);
//		list2.add(new RoofAttachmentFavLibraryEntity());
//		scenario.get(3).add(list2);
//		// Excpected Result
//		scenario.get(3).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deleteRoofAttachment [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(6));
//			String rslt = ((String) roofAttachmentLibraryService.deleteRoofAttachment((Integer) scenario.get(i).get(0),
//					(String) scenario.get(i).get(1), (String) scenario.get(i).get(2), (String) scenario.get(i).get(3)));
//
//		}
//
//	}
//
//	@Test
//	public void testgetUsersForFavList() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentFavLibraryEntity u WHERE u.roofAttachment.id = :p1"))
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
//		scenario.get(1).add(new ArrayList<RoofAttachmentFavLibraryEntity>());
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
//		scenario.get(2).add(new ArrayList<RoofAttachmentFavLibraryEntity>());
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
//		scenario.get(3).add(new ArrayList<RoofAttachmentFavLibraryEntity>());
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
//		ArrayList<RoofAttachmentFavLibraryEntity> list = new ArrayList<RoofAttachmentFavLibraryEntity>();
//		list.add(null);
//		list.add(new RoofAttachmentFavLibraryEntity());
//		RoofAttachmentFavLibraryEntity ac = new RoofAttachmentFavLibraryEntity();
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
//			roofAttachmentLibraryService
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
//		when(em.createQuery("SELECT u from RoofAttachmentsEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
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
//		scenario.get(3).add(new RoofAttachmentsEntity());
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
//		scenario.get(4).add(new RoofAttachmentsEntity());
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
//					roofAttachmentLibraryService.editUsersFavoriteList((Integer) scenario.get(i).get(0),
//							(String[]) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3), (String) scenario.get(i).get(4));
//
//		}
//
//	}
//
//	@Test
//	public void testactivateRoofAttachment() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentsEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentsEntity u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
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
//		scenario.get(3).add(new RoofAttachmentsEntity());
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
//		scenario.get(4).add(new RoofAttachmentsEntity());
//		// Result of query2
//		scenario.get(4).add(new ArrayList<RoofAttachmentsEntity>());
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
//		scenario.get(5).add(new RoofAttachmentsEntity());
//		// Result of query2
//		ArrayList<RoofAttachmentsEntity> list = new ArrayList<RoofAttachmentsEntity>();
//		list.add(null);
//		list.add(new RoofAttachmentsEntity());
//		scenario.get(5).add(list);
//		// Excpected Result
//		scenario.get(5).add("exist");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("activateRailRacking [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			roofAttachmentLibraryService.activateRoofAttachment((String) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
//		}
//
//	}
//
//	@Test
//	public void testgetSearchRoofAttachment() {
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//
//		when(criteriaBuilder.createQuery(RoofAttachmentsEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(RoofAttachmentsEntity.class)).thenReturn(root);
//		// when(r.get("authentificationEntity")).thenReturn(authentificationEntity);
//		when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//		when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from RoofAttachmentsEntity u WHERE u.idOwner.id = :p1 and u.isDeleted = :p2 and u.roofType = :p3 and u.integrated = :p4"))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentsEntity u WHERE u.idOwner.id = :p1 and u.isDeleted = :p2 and u.roofType = :p3"))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentsEntity u WHERE u.idOwner.id = :p1 and u.isDeleted = :p2 and u.integrated = :p3"))
//				.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentsEntity u WHERE u.idOwner.id = :p1 and u.isDeleted = :p2"))
//				.thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
//
//		Query mockedQuery6 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentFavLibraryEntity u WHERE u.roofAttachment.id = :p1"))
//				.thenReturn(mockedQuery6);
//		when(mockedQuery6.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery6);
//	}
//
//	@Test
//	public void testeditRoofAttachmentNotification() {
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
//			System.out.println("editRoofAttachmentNotification [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//		 roofAttachmentLibraryService.editRoofAttachmentNotification(
//					(String) scenario.get(i).get(0), (String) scenario.get(i).get(1), (String) scenario.get(i).get(2));
//
//		}
//
//	}
//
//	@Test
//	public void testaddRoofAttachmentNotification() {
//
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
//			System.out.println("addRoofAttachmentNotification [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			roofAttachmentLibraryService.addRoofAttachmentNotification(
//					(String) scenario.get(i).get(0), (String) scenario.get(i).get(1), (String) scenario.get(i).get(2));
//
//		}
//
//
//	}
//
//	@Test
//	public void testrefreshRoofAttachements() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 and u.roofAttachment.id = :p2 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
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
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		// Result of query1
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add(false);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add("123");
//		// Result of query1
//		ArrayList<RoofAttachmentFavLibraryEntity> list = new ArrayList<RoofAttachmentFavLibraryEntity>();
//		list.add(null);
//		list.add(new RoofAttachmentFavLibraryEntity());
//		scenario.get(2).add(list);
//		// Excpected Result
//		scenario.get(2).add(true);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("refreshRoofAttachements [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			 roofAttachmentLibraryService
//					.refreshRoofAttachements((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testsendCorrectionRequest() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentsEntity u Where u.id = :p1 ")).thenReturn(mockedQuery1);
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
//		RoofAttachementCorrectionModel acComSLC = new RoofAttachementCorrectionModel();
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
//		RoofAttachementCorrectionModel acComSLC1 = new RoofAttachementCorrectionModel();
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
//		RoofAttachementCorrectionModel acComSLC2 = new RoofAttachementCorrectionModel();
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
//		RoofAttachementCorrectionModel acComSLC4 = new RoofAttachementCorrectionModel();
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
//		RoofAttachmentsEntity lib = new RoofAttachmentsEntity();
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
//			roofAttachmentLibraryService
//					.sendCorrectionRequest((RoofAttachementCorrectionModel) scenario.get(i).get(0));
//
//		}
//
//	}
//}
