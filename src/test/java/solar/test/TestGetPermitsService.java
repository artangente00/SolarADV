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
//import org.springframework.web.multipart.MultipartFile;
//
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.model.PermitResponse;
//import com.PlayGroundAdv.Solar.model.PermitResponsePrime;
//import com.PlayGroundAdv.Solar.model.PermitResult;
//import com.PlayGroundAdv.Solar.model.SRsheetsModel;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetPermitsService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.TLDSheetLibraryService;
//
//public class TestGetPermitsService {
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
//	GetPermitsService getPermitsService = new GetPermitsService();
//
//
//    @Before
//	public void setupMock() {
//    	getPermitsService = new GetPermitsService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testgetAllPermit() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT count(*) as nb from PermitEntity u  "
//				+ " where  (u.isTemplate = false or u.isTemplate = :p1)"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitResponsePrime (u.id, u.homeOwnName, u.advancement, u.status, u.updateDate, u.submitted, u.creationPermitDate, u.authentificationEntity.firstName,u.isPayed, u.authentificationEntity.lastName,u.dateOfSubmitPermitOnHold,u.dateOfSubmitPermit,u.isCanceled,u.dateOfSubmitPermitCanceled,u.isOnHold)"
//				+ " from PermitEntity u  "
//				+ " where u.isTemplate = :p1 or u.isTemplate = :p2 ORDER BY u.creationPermitDate DESC"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(123);
//		scenario.get(0).add(123);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(123);
//		scenario.get(1).add(123);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		List<PermitResponsePrime> list = new ArrayList<PermitResponsePrime>();
//		list.add(null);
//		list.add(new PermitResponsePrime());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(123);
//		scenario.get(2).add(123);
//		// Result of the query1
//		scenario.get(2).add(5L);
//		// Result of the query2
//		List<PermitResponsePrime> list2 = new ArrayList<PermitResponsePrime>();
//		list2.add(new PermitResponsePrime());
//		scenario.get(2).add(list2);
//		// Result excpected
//		scenario.get(2).add(list2);
//
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getAllPermit [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//
//			List<PermitResponsePrime> rslt = (List<PermitResponsePrime>)getPermitsService.getAllPermit((int)scenario.get(i).get(0),(int)scenario.get(i).get(1));
//
//		}
//	}
////
//	@Test
//	public void testgetAllPermits() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT count(*) as nb from PermitEntity u  "
//				+ " where  u.isTemplate = false or u.isTemplate = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitResponsePrime (u.id, u.homeOwnName, u.advancement, u.status, u.updateDate, u.submitted, u.creationPermitDate, u.authentificationEntity.firstName,u.isPayed, u.authentificationEntity.lastName,u.dateOfSubmitPermitOnHold,u.dateOfSubmitPermit,u.isCanceled,u.dateOfSubmitPermitCanceled,u.isOnHold,u.projectName)"
//				+ " from PermitEntity u  "
//				+ " where u.isTemplate = :p1 or u.isTemplate = :p2 ORDER BY u.creationPermitDate DESC"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT count(*) as nb from PermitEntity u  "
//				+ " where  u.authentificationEntity.email  NOT IN ('arij@nuagetechnologies-tn.com', 'soumaya@nuagetechnologies-tn.com', 'nader@nuagetechnologies-tn.com', 'nabil-g@advpermits.com', 'imen@nuagetechnologies-tn.com', 'malek@nuagetechnologies-tn.com', 'abdessalem@nuagetechnologies-tn.com') and (u.isTemplate = false or u.isTemplate = :p1)"))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitResponsePrime (u.id, u.homeOwnName, u.advancement, u.status, u.updateDate, u.submitted, u.creationPermitDate, u.authentificationEntity.firstName,u.isPayed, u.authentificationEntity.lastName,u.dateOfSubmitPermitOnHold,u.dateOfSubmitPermit,u.isCanceled,u.dateOfSubmitPermitCanceled,u.isOnHold,u.projectName)"
//				+ " from PermitEntity u  "
//				+ " where u.authentificationEntity.email  NOT IN ('arij@nuagetechnologies-tn.com', 'soumaya@nuagetechnologies-tn.com', 'nader@nuagetechnologies-tn.com', 'nabil-g@advpermits.com', 'imen@nuagetechnologies-tn.com', 'malek@nuagetechnologies-tn.com', 'abdessalem@nuagetechnologies-tn.com') and (u.isTemplate = :p1 or u.isTemplate = :p2) ORDER BY u.creationPermitDate DESC"))
//				.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		scenario.get(0).add(new ArrayList<>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("malek@nuagetechnologies-tn.com");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result of the query4
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("malek@nuagetechnologies-tn.com");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		List<PermitResponsePrime> list = new ArrayList<PermitResponsePrime>();
//		list.add(null);
//		list.add(new PermitResponsePrime());
//		scenario.get(2).add(list);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result of the query4
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("malek@nuagetechnom");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(list);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result of the query4
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add(list);
//
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getAllPermits [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(4));
//
//			List<PermitResponsePrime> rslt = (List<PermitResponsePrime>)getPermitsService.getAllPermits((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testgetDrafterPermit() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT count(*) as nb from PermitEntity u  "
//				+ " where  (u.isTemplate = false or u.isTemplate = :p1) and u.isDeleted = false"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitResponsePrime (u.id, u.homeOwnName, u.advancement, u.status, u.updateDate, u.submitted, u.creationPermitDate, u.authentificationEntity.firstName,u.isPayed, u.authentificationEntity.lastName,u.dateOfSubmitPermitOnHold,u.dateOfSubmitPermit,u.isCanceled,u.dateOfSubmitPermitCanceled,u.isOnHold,u.projectName)"
//				+ " from PermitEntity u  "
//				+ " where (u.isTemplate = :p1 or u.isTemplate = :p2) and u.isDeleted = false ORDER BY u.creationPermitDate DESC"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<>());
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		List<PermitResponsePrime> list = new ArrayList<PermitResponsePrime>();
//		list.add(null);
//		list.add(new PermitResponsePrime());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(2).add(5L);
//		// Result of the query2
//		List<PermitResponsePrime> list2 = new ArrayList<PermitResponsePrime>();
//		list2.add(new PermitResponsePrime());
//		scenario.get(2).add(list2);
//		// Result excpected
//		scenario.get(2).add(list2);
//
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getDrafterPermit [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(0));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(1));
//
//			List<PermitResponsePrime> rslt = (List<PermitResponsePrime>)getPermitsService.getDrafterPermit();
//		}
//	}
//
//	@Test
//	public void testgetAllPermitsByUser() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT count(*) as nb from PermitEntity u  "
//				+ " where  u.authentificationEntity.id = :p1  and (u.isTemplate = false or u.isTemplate = :p2)"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitResponsePrime (u.id, u.homeOwnName, u.advancement, u.status,  u.updatedDate, u.submitted, u.creationPermitDate, u.authentificationEntity.firstName,u.isPayed, u.authentificationEntity.lastName,u.dateOfSubmitPermitOnHold,u.dateOfSubmitPermit,u.isCanceled,u.dateOfSubmitPermitCanceled,u.isOnHold,u.projectName) "
//				+ " from PermitEntity u  "
//				+ " where u.authentificationEntity.id = :p3  and (u.isTemplate = :p1 or u.isTemplate = :p2) ORDER BY u.creationPermitDate DESC"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT count(*) as nb from PermitEntity u  "
//				+ " where u.isDeleted = false and u.authentificationEntity.id = :p1 and (u.isTemplate = false or u.isTemplate = :p2)"))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitResponsePrime (u.id, u.homeOwnName, u.advancement, u.status, u.updateDate, u.submitted, u.creationPermitDate, u.authentificationEntity.firstName,u.isPayed, u.authentificationEntity.lastName,u.dateOfSubmitPermitOnHold,u.dateOfSubmitPermit,u.isCanceled,u.dateOfSubmitPermitCanceled,u.isOnHold,u.projectName) "
//				+ " from PermitEntity u  "
//				+ " where u.authentificationEntity.id = :p3 and  u.isDeleted = :p1  and (u.isTemplate = :p1 or u.isTemplate = :p2) ORDER BY u.creationPermitDate DESC"))
//				.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		scenario.get(0).add(new ArrayList<>());
//		//Result of find
//		scenario.get(0).add(new AuthentificationEntity());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		scenario.get(1).add(new ArrayList<>());
//		//Result of find
//		scenario.get(1).add(new AuthentificationEntity());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("1255");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result of the query4
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<>());
//		//Result of find
//		scenario.get(2).add(new AuthentificationEntity());
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("1255");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result of the query4
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new ArrayList<>());
//		//Result of find
//		AuthentificationEntity auth = new AuthentificationEntity();
//		RoleEntity role = new RoleEntity();
//		role.setDescription("Adv_Team");
//		auth.setRoleEntity(role);
//		scenario.get(3).add(auth);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("1255");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		List<PermitResult> list = new ArrayList<PermitResult>();
//		list.add(null);
//		list.add(new PermitResult());
//		scenario.get(4).add(list);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result of the query4
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(list);
//		//Result of find
//		scenario.get(4).add(auth);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add("1255");
//		// Result of the query1
//		scenario.get(5).add(null);
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result of the query3
//		scenario.get(5).add(null);
//		// Result of the query4
//		scenario.get(5).add(list);
//		// Result excpected
//		scenario.get(5).add(new ArrayList<>());
//		//Result of find
//		AuthentificationEntity auth1 = new AuthentificationEntity();
//		RoleEntity role1 = new RoleEntity();
//		role1.setDescription("Nuatn");
//		auth1.setRoleEntity(role1);
//		scenario.get(5).add(auth1);
//
//
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getAllPermits [ " + i + " ]");
//			//Mockito.when(em.find(AuthentificationEntity.class, 1)).thenReturn(auth);
//		      Mockito.when(em.find(AuthentificationEntity.class, 1255)).thenReturn(auth);
//
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			List<PermitResponsePrime> rslt = (List<PermitResponsePrime>)getPermitsService.getAllPermitsByUser((String) scenario.get(i).get(0));
//		}
//
//	}
//
//	@Test
//	public void testgetPermits() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT count(*) as nb  "
//				+ " from PermitEntity u  "
//				+ " where u.homeOwnName = :p1  and (u.isTemplate = false or u.isTemplate = :p2)"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(false);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(0L);
//		// Result excpected
//		scenario.get(1).add(false);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(1L);
//		// Result excpected
//		scenario.get(2).add(true);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getPermits [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//
//					getPermitsService.getPermits((String) scenario.get(i).get(0));
//
//		}
//	}
//
//	@Test
//	public void testgetPermitByIdUser() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT count(*) as nb from PermitEntity u "
//				+ " and u.authentificationEntity.id = :p1 and (u.isTemplate = false or u.isTemplate = :p2)"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitResult "
//				+ "(u.id, u.homeOwnName, u.advancement, u.status,"
//				+ " u.creationDate, u.updatedDate, u.creationPermitDate,u.isPayed, u.submitted,u.projectName)"
//				+ " from PermitEntity u  "
//				+ " and u.authentificationEntity.id = :p1  and (u.isTemplate = false or u.isTemplate = :p2) ORDER BY u.creationPermitDate DESC"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(123);
//		scenario.get(0).add(123);
//		scenario.get(0).add(123);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(123);
//		scenario.get(1).add(123);
//		scenario.get(1).add(123);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		List<PermitResult> list = new ArrayList<PermitResult>();
//		list.add(null);
//		list.add(new PermitResult());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(223);
//		scenario.get(2).add(223);
//		scenario.get(2).add(223);
//		// Result of the query1
//		scenario.get(2).add(5L);
//		// Result of the query2
//		List<PermitResult> list2 = new ArrayList<PermitResult>();
//		list2.add(new PermitResult());
//		scenario.get(2).add(list2);
//		// Result excpected
//		scenario.get(2).add(list2);
//
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getPermitResponseByIdUser [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(4));
//
//			List<PermitResult> rslt = (List<PermitResult>)getPermitsService.getPermitByIdUser((int) scenario.get(i).get(0),(int) scenario.get(i).get(1),(int) scenario.get(i).get(2));
//
//		}
//	}
//
//	@Test
//	public void testgetPermitResponseByIdUser() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitResponse "
//				+ "(u.id, u.homeOwnName, u.advancement, u.status,"
//				+ " u.creationPermitDate, u.updateDate, u.submitted,u.projectName) "
//				+ " from PermitEntity u  "
//				+ " and u.authentificationEntity.id = :p1  and (u.isTemplate = false or u.isTemplate = :p2) ORDER BY u.creationPermitDate "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(125);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(125);
//		// Result of the query1
//		List<PermitResponse> list = new ArrayList<PermitResponse>();
//		list.add(null);
//		list.add(new PermitResponse());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getPermitResponseByIdUser [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			List<PermitResponse> rslt = (List<PermitResponse>)getPermitsService.getPermitResponseByIdUser((int) scenario.get(i).get(0));
//		}
//	}
//}
