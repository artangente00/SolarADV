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
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.FlashingFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.RFIQuestionEntity;
//import com.PlayGroundAdv.Solar.Entity.RFInformationEntity;
//import com.PlayGroundAdv.Solar.model.FlashingCorrectionRequest;
//import com.PlayGroundAdv.Solar.model.RFIQuestionFavRequest;
//import com.PlayGroundAdv.Solar.model.SearchFlashingRequest;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetInverterLibraryService;
//import com.PlayGroundAdv.Solar.Services.GetQuestionLibraryService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//
//public class TestGetQuestionLibraryService {
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
//	GetQuestionLibraryService getQuestionLibraryService = new GetQuestionLibraryService();
//
//
//    @Before
//	public void setupMock() {
//    	getQuestionLibraryService = new GetQuestionLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testeditRFIQuestion() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT r from RFIQuestionEntity r WHERE r.id_RFIQuestion = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u FROM RFInformationEntity u WHERE u.attributeName = :p1 AND split_part(u.advQuestion,'::', 2) = :p2"))
//						.thenReturn(mockedQuery2);
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
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new RFIQuestionFavRequest());
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
//		scenario.get(2).add(new RFIQuestionFavRequest());
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(new RFIQuestionEntity());
//		// Result of query2
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("success");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new RFIQuestionFavRequest());
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(new RFIQuestionEntity());
//		// Result of query2
//		List<RFInformationEntity> list = new ArrayList<RFInformationEntity>();
//		list.add(null);
//		list.add(new RFInformationEntity());
//		RFInformationEntity rf = new RFInformationEntity();
//		rf.setAdvQuestion("hello::aa");
//		list.add(rf);
//		scenario.get(3).add(list);
//		// Excpected Result
//		scenario.get(3).add("success");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editRFIQuestion [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			getQuestionLibraryService
//					.editRFIQuestion((RFIQuestionFavRequest) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//	}
//
//	@Test
//	public void testupdateExistingOfDocument() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT r from RFIQuestionEntity r where r.id_RFIQuestion = :p1")).thenReturn(mockedQuery1);
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
//		scenario.get(0).add("ok");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of query1
//		scenario.get(1).add(new RFIQuestionEntity());
//		// Excpected Result
//		scenario.get(1).add("ok");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("222");
//		// Result of query1
//		scenario.get(2).add(new RFIQuestionEntity());
//		// Excpected Result
//		scenario.get(2).add("ok");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("updateExistingOfDocument [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			getQuestionLibraryService
//					.updateExistingOfDocument((String) scenario.get(i).get(0));
//
//		}
//	}
//
//	@Test
//	public void testupdateRFIQuestionFavorite() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT r from RFIQuestionEntity r WHERE r.id_RFIQuestion = :p1 "))
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
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new RFIQuestionFavRequest());
//		scenario.get(1).add("");
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("fail");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new RFIQuestionFavRequest());
//		scenario.get(2).add("123");
//		// Result of query1
//		scenario.get(2).add(null);
//		// Result of query2
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("fail");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		RFIQuestionFavRequest qt = new RFIQuestionFavRequest();
//		qt.setQuestionActived(true);
//		scenario.get(3).add(qt);
//		scenario.get(3).add("123");
//		// Result of query1
//		scenario.get(3).add(null);
//		// Result of query2
//		scenario.get(3).add(null);
//		// Excpected Result
//		scenario.get(3).add("fail");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(qt);
//		scenario.get(4).add("123");
//		// Result of query1
//		scenario.get(4).add(null);
//		// Result of query2
//		scenario.get(4).add(new RFIQuestionEntity());
//		// Excpected Result
//		scenario.get(4).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(qt);
//		scenario.get(5).add("123");
//		// Result of query1
//		scenario.get(5).add(new AuthentificationEntity());
//		// Result of query2
//		scenario.get(5).add(new RFIQuestionEntity());
//		// Excpected Result
//		scenario.get(5).add("success");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		RFIQuestionFavRequest qt2 = new RFIQuestionFavRequest();
//		qt2.setQuestionActived(false);
//		scenario.get(6).add(qt2);
//		scenario.get(6).add("123");
//		// Result of query1
//		scenario.get(6).add(null);
//		// Result of query2
//		scenario.get(6).add(new RFIQuestionEntity());
//		// Excpected Result
//		scenario.get(6).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(7).add(qt2);
//		scenario.get(7).add("123");
//		// Result of query1
//		scenario.get(7).add(new AuthentificationEntity());
//		// Result of query2
//		scenario.get(7).add(new RFIQuestionEntity());
//		// Excpected Result
//		scenario.get(7).add("success");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("updateRFIQuestionFavorite [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			getQuestionLibraryService.updateRFIQuestionFavorite(
//					(RFIQuestionFavRequest) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//	}
//
//	@Test
//	public void testgetAllFieldName() {
//		Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT DISTINCT r.fieldName from RFIQuestionEntity r ORDER BY r.fieldName" )).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//
//		  List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(0).add(null);
//			 //Result of query1
//			 scenario.get(0).add(null);
//			//Excpected Result
//			 scenario.get(0).add(new ArrayList<>());
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(1).add(null);
//			 //Result of query1
//			 scenario.get(1).add(new ArrayList<>());
//			//Excpected Result
//			 scenario.get(1).add(new ArrayList<>());
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(2).add(null);
//			 //Result of query1
//			 List<String> list = new ArrayList<>();
//			 list.add("");
//			 list.add("aaa");
//			 list.add(null);
//			 scenario.get(2).add(list);
//			//Excpected Result
//			 scenario.get(2).add(list);
//
//			 for(int i=0;i<scenario.size();i++) {
//				 System.out.println("getAllFieldName [ "+i+" ]");
//	             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//	             List<String> rslt = getQuestionLibraryService.getAllFieldName();
//
//			 }
//
//	}
//
//	@Test
//	public void testaddRFIQuestion() {
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new RFIQuestionFavRequest());
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("success");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addRFIQuestion [ " + i + " ]");
//			getQuestionLibraryService.addRFIQuestion((RFIQuestionFavRequest) scenario.get(i).get(0),
//					(String) scenario.get(i).get(1));
//		}
//	}
//
//
//	@Test
//	public void testgetQuestions() {
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(RFIQuestionFavRequest.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(RFIQuestionEntity.class)).thenReturn(root);
//        when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//
//        TypedQuery mockedQuery1 = mock(TypedQuery.class);
//        when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setFirstResult(Mockito.anyInt())).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery1);
//        List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add(new ArrayList<>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(true);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of query1
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add(new ArrayList<>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(true);
//		 scenario.get(2).add("");
//		 scenario.get(2).add("");
//		 //Result of query1
//		 scenario.get(2).add(null);
//		//Excpected Result
//		 scenario.get(2).add(new ArrayList<>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(true);
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 //Result of query1
//		 scenario.get(3).add(null);
//		//Excpected Result
//		 scenario.get(3).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(true);
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 //Result of query1
//		 List<RFIQuestionFavRequest> list = new ArrayList<RFIQuestionFavRequest>();
//		 list.add(null);
//		 list.add(new RFIQuestionFavRequest());
//		 scenario.get(4).add(list);
//		//Excpected Result
//		 scenario.get(4).add(list);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(false);
//		 scenario.get(5).add("");
//		 scenario.get(5).add("");
//		 //Result of query1
//		 scenario.get(5).add(null);
//		//Excpected Result
//		 scenario.get(5).add(new ArrayList<>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(false);
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 //Result of query1
//		 scenario.get(6).add(null);
//		//Excpected Result
//		 scenario.get(6).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(7).add(null);
//		 scenario.get(7).add(false);
//		 scenario.get(7).add("123");
//		 scenario.get(7).add("123");
//		 //Result of query1
//		 scenario.get(7).add(list);
//		//Excpected Result
//		 scenario.get(7).add(list);
//
//
//			for (int i = 0; i < scenario.size(); i++) {
//				System.out.println("getQuestions [ " + i + " ]");
//				when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//				List<RFIQuestionFavRequest> rslt = getQuestionLibraryService
//						.getQuestions((String) scenario.get(i).get(0),(Boolean) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
//			}
//	}
//
//
//	@Test
//	public void testgetNbrQuestions() {
//
//
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(RFIQuestionEntity.class)).thenReturn(root);
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
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add("");
//		 scenario.get(1).add(true);
//		 //Result of query1
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add("");
//		 scenario.get(2).add(true);
//		 //Result of query1
//		 scenario.get(2).add(8L);
//		//Excpected Result
//		 scenario.get(2).add(8L);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add("");
//		 scenario.get(3).add(false);
//		 //Result of query1
//		 scenario.get(3).add(8L);
//		//Excpected Result
//		 scenario.get(3).add(8L);
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getNbrQuestions [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//	        getQuestionLibraryService.getNbrQuestions((String) scenario.get(i).get(0),(Boolean) scenario.get(i).get(1));
//		 }
//
//
//
//	}
//
//
//	@Test
//	public void testgetAllQuestionSearchedLibraryTrue() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT new com.PlayGroundAdv.Solar.model.RFIQuestionFavRequest (r.id_RFIQuestion, r.questionstatic, r.fieldName, r.rfiQuestion,r.questionActived, r.addedBy, r.confirmation) from RFIQuestionEntity r where r.fieldName = :p and r.questionActived = :p1 "))
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
//		scenario.get(0).add(new ArrayList<RFIQuestionFavRequest>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		// Result of query1
//		ArrayList<RFIQuestionFavRequest> list = new ArrayList<RFIQuestionFavRequest>();
//		list.add(null);
//		list.add(new RFIQuestionFavRequest());
//		scenario.get(1).add(list);
//		// Excpected Result
//		scenario.get(1).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllQuestionSearchedLibraryTrue [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			List<RFIQuestionFavRequest> rslt = getQuestionLibraryService
//					.getAllQuestionSearchedLibraryTrue((String) scenario.get(i).get(0));
//
//		}
//	}
//
//}
