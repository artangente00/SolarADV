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
//
//import com.PlayGroundAdv.Solar.model.PermitResponse;
//import com.PlayGroundAdv.Solar.model.PermitResponsePrime;
//import com.PlayGroundAdv.Solar.model.PermitResult;
//import com.PlayGroundAdv.Solar.model.TemplateModelResponse;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.SsheetLibraryService;
//import com.PlayGroundAdv.Solar.Services.TemplateService;
//
//public class TestTemplateService {
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
//	TemplateService templateService = new TemplateService();
//
//
//    @Before
//	public void setupMock() {
//    	templateService = new TemplateService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testgetAllPermit() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT count(*) as nb from PermitEntity u  "
//				+ " where u.isDeleted = false and u.isTemplate = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitResponsePrime (u.id, u.homeOwnName, u.advancement, u.status, u.creationDate, u.updatedDate, u.submitted, u.creationPermitDate, u.authentificationEntity.firstName,u.isPayed, u.authentificationEntity.lastName) "
//				+ " from PermitEntity u  "
//				+ " where u.isDeleted = :p1 and u.isTemplate = :p2 ORDER BY u.creationPermitDate DESC"))
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
//			List<PermitResponsePrime> rslt = (List<PermitResponsePrime>)templateService.getAllPermit((int)scenario.get(i).get(0),(int)scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testgetPermits() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT count(*) as nb  " + " from PermitEntity u  "
//				+ " where u.homeOwnName = :p1 and u.isDeleted = false and u.isTemplate = :p2"))
//						.thenReturn(mockedQuery1);
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
//		scenario.get(0).add(false);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(0L);
//		// Result excpected
//		scenario.get(1).add(false);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(1L);
//		// Result excpected
//		scenario.get(2).add(true);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getPermits [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			templateService.getPermits((String) scenario.get(i).get(0));
//		}
//
//	}
//
//	@Test
//	public void testgetPermitByIdUser() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT count(*) as nb from PermitEntity u"
//				+ " where u.isDeleted = false "
//				+ " and u.authentificationEntity.id = :p1 and u.isTemplate = :p2"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitResult "
//				+ "(u.id, u.homeOwnName, u.advancement, u.status,"
//				+ " u.creationDate, u.updatedDate, u.creationPermitDate,u.isPayed, u.submitted)"
//				+ " from PermitEntity u  "
//				+ " where u.isDeleted = false "
//				+ " and u.authentificationEntity.id = :p1  and u.isTemplate = :p2 ORDER BY u.creationPermitDate DESC"))
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
//			List<PermitResult> rslt = (List<PermitResult>)templateService.getPermitByIdUser((int) scenario.get(i).get(0),(int) scenario.get(i).get(1),(int) scenario.get(i).get(2));
//
//		}
//
//	}
//
//	@Test
//	public void testgetPermitResponseByIdUser() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitResponse "
//				+ "(u.id, u.homeOwnName, u.advancement, u.status,"
//				+ " u.creationDate, u.updatedDate, u.submitted) "
//				+ " from PermitEntity u  "
//				+ " where u.isDeleted = false "
//				+ " and u.authentificationEntity.id = :p1  and u.isTemplate = :p2 ORDER BY u.creationDate "))
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
//			List<PermitResponse> rslt = (List<PermitResponse>)templateService.getPermitResponseByIdUser((int) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testgetAllTemplate() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.TemplateModelResponse("
//				+ "u.id, "
//				+ "u.homeOwnName, "
//				+ "u.creationDate, "
//				+ "u.updatedDate, "
//				+ "u.authentificationEntity.firstName, "
//				+ "u.authentificationEntity.lastName) "
//				+ "FROM  PermitEntity u "
//				+ "where u.isDeleted = false "
//				+ "and u.isTemplate = true ORDER BY u.creationPermitDate DESC"))
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
//		List<TemplateModelResponse> list = new ArrayList<TemplateModelResponse>();
//		list.add(null);
//		list.add(new TemplateModelResponse());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getAllTemplate [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			List<TemplateModelResponse> rslt = (List<TemplateModelResponse>)templateService.getAllTemplate();
//		}
//	}
//
//	@Test
//	public void testgetAllTemplateByUser() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.TemplateModelResponse("
//				+ "u.id, "
//				+ "u.homeOwnName, "
//				+ "u.creationDate, "
//				+ "u.updatedDate, "
//				+ "u.authentificationEntity.firstName, "
//				+ "u.authentificationEntity.lastName) "
//				+ "FROM  PermitEntity u "
//				+ "WHERE u.isDeleted = false "
//				+ "AND u.authentificationEntity.id = :p1 "
//				+ "AND u.isTemplate = true ORDER BY u.creationPermitDate DESC"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add("125");
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("125");
//		// Result of the query1
//		List<TemplateModelResponse> list = new ArrayList<TemplateModelResponse>();
//		list.add(null);
//		list.add(new TemplateModelResponse());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<>());
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new ArrayList<>());
//
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getAllTemplateByUser [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			List<TemplateModelResponse> rslt = (List<TemplateModelResponse>)templateService.getAllTemplateByUser((String) scenario.get(i).get(0));
//
//		}
//	}
//
//}
