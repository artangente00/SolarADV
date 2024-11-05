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
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.LeasePPAMeterFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitHomeSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.RailRacking;
//import com.PlayGroundAdv.Solar.Entity.RailRackingFavLibraryEntity;
//import com.PlayGroundAdv.Solar.model.ElectricalUtilityModel;
//import com.PlayGroundAdv.Solar.model.HistoricActivityResult;
//import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
//import com.PlayGroundAdv.Solar.model.RailRackingModel;
//import com.PlayGroundAdv.Solar.model.SearchLeasePPAMeterRequest;
//import com.PlayGroundAdv.Solar.model.UtilityCompanyModelRequest;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.RailRackingLibraryService;
//import com.PlayGroundAdv.Solar.Services.UtilityCompanyLibraryService;
//
//public class TestUtilityCompanyLibraryService {
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
//	UtilityCompanyLibraryService utilityCompanyLibraryService = new UtilityCompanyLibraryService();
//
//
//    @Before
//	public void setupMock() {
//    	utilityCompanyLibraryService = new UtilityCompanyLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testgetAllUtilityCompanyNum() {
//
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(ElectricalUtilityEntity.class)).thenReturn(root);
//		when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		UtilityCompanyModelRequest sc = new UtilityCompanyModelRequest();
//		scenario.get(1).add(sc);
//		scenario.get(1).add(false);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(sc);
//		scenario.get(2).add(false);
//		// Result of query1
//		scenario.get(2).add(8L);
//		// Excpected Result
//		scenario.get(2).add(8L);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new UtilityCompanyModelRequest());
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(8L);
//		// Excpected Result
//		scenario.get(3).add(8L);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllUtilityCompanyNum [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			utilityCompanyLibraryService.getAllUtilityCompanyNum(
//					(UtilityCompanyModelRequest) scenario.get(i).get(0), (Boolean) scenario.get(i).get(1));
//		}
//
//	}
//
//	@Test
//	public void testgetAllUtilityCompany() {
//
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(ElectricalUtilityEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(ElectricalUtilityEntity.class)).thenReturn(root);
//		when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//		when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//		when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
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
//		scenario.get(0).add(new ArrayList<ElectricalUtilityEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new UtilityCompanyModelRequest());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add(new ArrayList<ElectricalUtilityEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new UtilityCompanyModelRequest());
//		scenario.get(2).add("");
//		scenario.get(2).add("");
//		// Result of query1
//		ArrayList<ElectricalUtilityEntity> list = new ArrayList<ElectricalUtilityEntity>();
//		list.add(null);
//		list.add(new ElectricalUtilityEntity());
//		scenario.get(2).add(list);
//		// Excpected Result
//		scenario.get(2).add(new ArrayList<ElectricalUtilityEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new UtilityCompanyModelRequest());
//		scenario.get(3).add("123");
//		scenario.get(3).add("123");
//		// Result of query1
//		scenario.get(3).add(list);
//		// Excpected Result
//		scenario.get(3).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(new UtilityCompanyModelRequest());
//		scenario.get(4).add("123");
//		scenario.get(4).add("123");
//		// Result of query1
//		scenario.get(4).add(null);
//		// Excpected Result
//		scenario.get(4).add(null);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllUtilityCompany [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			List<ElectricalUtilityEntity> rslt = (List<ElectricalUtilityEntity>) utilityCompanyLibraryService
//					.getAllUtilityCompany((UtilityCompanyModelRequest) scenario.get(i).get(0),
//							(String) scenario.get(i).get(1), (String) scenario.get(i).get(2));
//		}
//	}
//
//	@Test
//	public void testgetAllDeletedUtilityCompany() {
//
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from ElectricalUtilityEntity u WHERE u.isDeleted = :p1 ORDER BY u.zip, u.utilityCompanyName"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<ElectricalUtilityEntity> list = new ArrayList<ElectricalUtilityEntity>();
//		list.add(null);
//		list.add(new ElectricalUtilityEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getAllDeletedUtilityCompany [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			List<ElectricalUtilityEntity> rslt = (List<ElectricalUtilityEntity>)utilityCompanyLibraryService.getAllDeletedUtilityCompany();
//		}
//
//	}
//
//	@Test
//	public void testeditUtilityCompany() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from ElectricalUtilityEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from ElectricalUtilityEntity u WHERE u.id != :p1 and u.zip = :p2 and u.utilityCompanyName = :p3 AND u.isDeleted=false"))
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
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new ElectricalUtilityEntity());
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
//		scenario.get(2).add(new ElectricalUtilityEntity());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(new ElectricalUtilityEntity());
//		// Result of query2
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("success");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new ElectricalUtilityEntity());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(null);
//		// Result of query2
//		ArrayList<ElectricalUtilityEntity> list = new ArrayList<ElectricalUtilityEntity>();
//		list.add(null);
//		list.add(new ElectricalUtilityEntity());
//		scenario.get(3).add(list);
//		// Excpected Result
//		scenario.get(3).add("Exist");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editRailRacking [ " + i + " ]");
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			String rslt = utilityCompanyLibraryService.editUtilityCompany((ElectricalUtilityEntity) scenario.get(i).get(0),
//					(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
//
//		}
//
//	}
//
//	@Test
//	public void testaddUtilityCompany() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from ElectricalUtilityEntity u WHERE u.zip = :p2 and u.utilityCompanyName = :p3 AND u.isDeleted=false"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
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
//		// Excpected Result
//		scenario.get(0).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new ElectricalUtilityModel());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("0");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new ElectricalUtilityModel());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of query1
//		ArrayList<ElectricalUtilityEntity> l = new ArrayList<>();
//		scenario.get(2).add(l);
//		// Excpected Result
//		scenario.get(2).add("0");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new ElectricalUtilityModel());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of query1
//		ArrayList<ElectricalUtilityEntity> list = new ArrayList<ElectricalUtilityEntity>();
//		list.add(null);
//		list.add(new ElectricalUtilityEntity());
//		scenario.get(3).add(list);
//		// Excpected Result
//		scenario.get(3).add("Exist");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addUtilityCompany [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			String rslt = utilityCompanyLibraryService.addUtilityCompany(
//					(ElectricalUtilityModel) scenario.get(i).get(0), (String) scenario.get(i).get(1),
//					(String) scenario.get(i).get(2), (String) scenario.get(i).get(3));
//
//		}
//
//	}
//
//	@Test
//	public void testgetRemoveUtilityCompanyConfirmation() {
//
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel  "
//						+ " ( u.homeOwnName, "
//						+ " u.status, "
//						+ " v.firstName, "
//						+ " v.lastName)"
//						+ " from PermitEntity u, "
//						+ " AuthentificationEntity v, "
//						+ " PermitHomeSiteInfoEntity w"
//						+ " where w.UtilityCompanyName = :p1 "
//						+ "and  u.isDeleted  = :p2 and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = :p2 and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add(new ArrayList<ProjectForLibrariesModel>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//			System.out.println("getRemoveUtilityCompanyConfirmation [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			List<ProjectForLibrariesModel> rslt = (List<ProjectForLibrariesModel>) utilityCompanyLibraryService.getRemoveUtilityCompanyConfirmation((Integer)scenario.get(i).get(0));
//		}
//
//
//	}
//
//	@Test
//	public void testdeleteUtilityCompany() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from ElectricalUtilityEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitHomeSiteInfoEntity u WHERE u.UtilityCompanyName = :p1 "
//				+ "and  u.permitEntity.isDeleted  = :p2")).thenReturn(mockedQuery2);
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
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(new ElectricalUtilityEntity());
//		// Result of query2
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
//		scenario.get(2).add(new ElectricalUtilityEntity());
//		// Result of query2
//		List<PermitHomeSiteInfoEntity> list = new ArrayList<PermitHomeSiteInfoEntity>();
//		list.add(null);
//		list.add(new PermitHomeSiteInfoEntity());
//		scenario.get(2).add(list);
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
//		scenario.get(3).add(new ElectricalUtilityEntity());
//		// Result of query2
//		scenario.get(3).add(list);
//		// Excpected Result
//		scenario.get(3).add("Done");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deleteUtilityCompany [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			String rslt = ((String)utilityCompanyLibraryService.deleteUtilityCompany((Integer) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3)));
//
//		}
//
//
//
//	}
//
//	@Test
//	public void testactivateUtilityCompany() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from ElectricalUtilityEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from ElectricalUtilityEntity u WHERE u.zip = :p2 and u.utilityCompanyName = :p3 AND u.isDeleted=false")).thenReturn(mockedQuery2);
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
//		scenario.get(1).add(113);
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
//		scenario.get(2).add(123);
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
//		scenario.get(3).add(123);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(new ElectricalUtilityEntity());
//		// Result of query2
//		scenario.get(3).add(null);
//		// Excpected Result
//		scenario.get(3).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(123);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of query1
//		scenario.get(4).add(new ElectricalUtilityEntity());
//		// Result of query2
//		scenario.get(4).add(new ArrayList<ElectricalUtilityEntity>());
//		// Excpected Result
//		scenario.get(4).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(123);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		// Result of query1
//		scenario.get(5).add(new ElectricalUtilityEntity());
//		// Result of query2
//		ArrayList<ElectricalUtilityEntity> list = new ArrayList<ElectricalUtilityEntity>();
//		list.add(null);
//		list.add(new ElectricalUtilityEntity());
//		scenario.get(5).add(list);
//		// Excpected Result
//		scenario.get(5).add("exist");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("activateRailRacking [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			utilityCompanyLibraryService.activateUtilityCompany((Integer) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
//
//		}
//
//	}
//
//	@Test
//	public void testgetSearchUtilityCompany() {
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(ElectricalUtilityEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(ElectricalUtilityEntity.class)).thenReturn(root);
//		when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		UtilityCompanyModelRequest sc = new UtilityCompanyModelRequest();
//		scenario.get(1).add(sc);
//		scenario.get(1).add(false);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(sc);
//		scenario.get(2).add(false);
//		// Result of query1
//		ArrayList<ElectricalUtilityEntity> list = new ArrayList<ElectricalUtilityEntity>();
//		list.add(null);
//		list.add(new ElectricalUtilityEntity());
//		scenario.get(2).add(list);
//		// Excpected Result
//		scenario.get(2).add(list);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getSearchUtilityCompany [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			List<ElectricalUtilityEntity> rslt = (List<ElectricalUtilityEntity>)utilityCompanyLibraryService.getSearchUtilityCompany(
//					(UtilityCompanyModelRequest) scenario.get(i).get(0), (Boolean) scenario.get(i).get(1));
//		}
//
//	}
//
//}
