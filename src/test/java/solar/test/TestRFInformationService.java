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
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.RFIConfirmationEntity;
//import com.PlayGroundAdv.Solar.Entity.RFIQuestionEntity;
//import com.PlayGroundAdv.Solar.Entity.RFInformationEntity;
//import com.PlayGroundAdv.Solar.model.ConfContracRfiResponseModel;
//import com.PlayGroundAdv.Solar.model.ProjectTrackerModel;
//import com.PlayGroundAdv.Solar.model.RFIModelRequest;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.MailingService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.RFInformationService;
//import com.PlayGroundAdv.Solar.Services.SsheetLibraryService;
//
//public class TestRFInformationService {
//
//
//	@Mock
//	EntityManager em;
//
//	@Mock
//	private Query query;
//
//	@Mock
//	private MailingService mailingService;
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
//	RFInformationService rFInformationService = new RFInformationService();
//
//
//    @Before
//	public void setupMock() {
//    	rFInformationService = new RFInformationService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testsaveAdvRfInformation() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				   + "FROM RFInformationEntity u "
//				   + "WHERE u.idPermit.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				 + "FROM PermitEntity u "
//				 + "WHERE u.id = :p1"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				 + "FROM AuthentificationEntity u "
//				 + "WHERE u.id = :p1"))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery(" SELECT u "
//				 + "FROM RFIConfirmationEntity u "
//				 + "WHERE u.idPermit.id = :p1"))
//				.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		//List of the parameter
//		scenario.get(1).add("");
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
//		// Result excpected
//		scenario.get(1).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("112");
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
//		// Result excpected
//		scenario.get(2).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("112");
//		scenario.get(3).add("");
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result of the query4
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("112");
//		scenario.get(4).add("111");
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result of the query4
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("success");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add("112");
//		scenario.get(5).add("111");
//		scenario.get(5).add(null);
//		// Result of the query1
//		List<RFInformationEntity> list = new ArrayList<RFInformationEntity>();
//		list.add(null);
//		list.add(new RFInformationEntity());
//		scenario.get(5).add(list);
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result of the query3
//		scenario.get(5).add(null);
//		// Result of the query4
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("success");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(6).add("112");
//		scenario.get(6).add("111");
//		List<RFIModelRequest> listP = new ArrayList<RFIModelRequest>();
//		listP.add(null);
//		listP.add(new RFIModelRequest());
//		scenario.get(6).add(listP);
//		// Result of the query1
//		scenario.get(6).add(list);
//		// Result of the query2
//		scenario.get(6).add(null);
//		// Result of the query3
//		scenario.get(6).add(null);
//		// Result of the query4
//		scenario.get(6).add(null);
//		// Result excpected
//		scenario.get(6).add("success");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("saveAdvRfInformation [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(6));
//		   rFInformationService.saveAdvRfInformation((String) scenario.get(i).get(0),(String) scenario.get(i).get(1),(List<RFIModelRequest>) scenario.get(i).get(2));
//		}
//	}
//
//	@Test
//	public void testgetRfibyPermit() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + "FROM RFInformationEntity u " + "WHERE u.idPermit.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<RFInformationEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<RFInformationEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<RFInformationEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		ArrayList<RFInformationEntity> list = new ArrayList<RFInformationEntity>();
//		list.add(null);
//		list.add(new RFInformationEntity());
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getRfibyPermit [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			List<RFInformationEntity> rslt = (ArrayList<RFInformationEntity>) rFInformationService.getRfibyPermit((String) scenario.get(i).get(0));
//
//		}
//	}
//
//	@Test
//	public void testsaveContractorResponse() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + "FROM RFInformationEntity u " + "WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				   + "FROM AuthentificationEntity u "
//				   + "WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				   + "FROM PermitEntity u "
//				   + "WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		//List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
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
//		//List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add("");
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add("123");
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("success");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add(null);
//		scenario.get(5).add("123");
//		scenario.get(5).add("123");
//		// Result of the query1
//		ArrayList<RFInformationEntity> list = new ArrayList<RFInformationEntity>();
//		list.add(null);
//		list.add(new RFInformationEntity());
//		scenario.get(5).add(list);
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result of the query3
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("success");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("saveContractorResponse [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(5));
//		     rFInformationService.saveContractorResponse((List<ConfContracRfiResponseModel>) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2));
//		}
//	}
//
//	@Test
//	public void testupdateContractorSheetRFI() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				+ "FROM RFInformationEntity u "
//				+ "WHERE u.idPermit.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("echec");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("13685050");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Ok");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("13685050");
//		// Result of the query1
//		List<RFInformationEntity> list = new ArrayList<RFInformationEntity>();
//		list.add(null);
//		list.add(new RFInformationEntity());
//		scenario.get(2).add(list);
//		// Result excpected
//		scenario.get(2).add("echec");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("13685050");
//		// Result of the query1
//		List<RFInformationEntity> list2 = new ArrayList<RFInformationEntity>();
//		RFInformationEntity r = new RFInformationEntity();
//		r.setAdvQuestion("hello::junit test");
//		r.setAttributeName("test junit::rfi::service::test");
//		r.setContractorResponse("aa::bb::cc::vv");
//		r.setContentField("xx::yy::zz::aa");
//		r.setIsConfirmed(true);
//		list2.add(r);
//		scenario.get(3).add(list2);
//		// Result excpected
//		scenario.get(3).add("Ok");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("generateTheSheetRFI [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//		    rFInformationService.updateContractorSheetRFI((String) scenario.get(i).get(0));
//		}
//	}
//
//
//	@Test
//	public void testgenerateTheSheetRFI() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				+ "FROM RFInformationEntity u "
//				+ "WHERE u.idPermit.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("echec");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("echec");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("echec");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		ArrayList<RFInformationEntity> list = new ArrayList<RFInformationEntity>();
//		list.add(null);
//		list.add(new RFInformationEntity());
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add("echec");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		// Result of the query1
//		ArrayList<RFInformationEntity> list2 = new ArrayList<RFInformationEntity>();
//		RFInformationEntity r1 = new RFInformationEntity();
//		r1.setAttributeName("aa::bb::cc::dd");
//		r1.setAdvQuestion("dd::dd");
//		r1.setIsConfirmed(true);
//		r1.setContentField("kk::kk::cc::bb");
//		list2.add(r1);
//		scenario.get(4).add(list2);
//		// Result excpected
//		scenario.get(4).add("Ok");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("generateTheSheetRFI [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//		  rFInformationService.generateTheSheetRFI((String) scenario.get(i).get(0));
//		}
//	}
////
//	@Test
//	public void testsubmitADVRFI() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.authentificationEntity.reminder "
//				   + "FROM  PermitEntity u "
//				   + "WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				   + "FROM  PermitEntity u "
//				   + "WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				   + "FROM  AuthentificationEntity u "
//				   + "WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				   + "FROM  RFIConfirmationEntity u "
//				   + "WHERE u.idPermit.id = :p1 "))
//				.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT u.email "
//				   + "FROM  AuthentificationEntity u "
//				   + "WHERE u.roleEntity.id = :p1 "))
//				.thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
//
//		Query mockedQuery6 = mock(Query.class);
//		when(em.createQuery("SELECT u.authentificationEntity.email "
//				   + "FROM  PermitEntity u "
//				   + "WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery6);
//		when(mockedQuery6.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery6);
//
//		Query mockedQuery7 = mock(Query.class);
//		when(em.createQuery("SELECT u.authentificationEntity.contactEmail "
//				   + "FROM  PermitEntity u "
//				   + "WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery7);
//		when(mockedQuery7.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery7);
//
//
//		Query mockedQuery8 = mock(Query.class);
//		when(em.createQuery("SELECT u.authentificationEntity.secondContactEmail "
//				   + "FROM  PermitEntity u "
//				   + "WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery8);
//		when(mockedQuery8.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery8);
//
//
//		Query mockedQuery9 = mock(Query.class);
//		when(em.createQuery("SELECT u.authentificationEntity.thirdContactEmail "
//				   + "FROM  PermitEntity u "
//				   + "WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery9);
//		when(mockedQuery9.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery9);
//
//
//
//		Query mockedQuery10 = mock(Query.class);
//		when(em.createQuery(" SELECT new com.PlayGroundAdv.Solar.model.AddContactRfiModel("
//				+ " u.contactEmail, "
//				+ " u.isProjectAddInclud, "
//				+ " u.secondContactEmail, "
//				+ " u.thirdContactEmail )"
//				+ " FROM AuthentificationEntity u , PermitEntity v  "
//				+ " WHERE v.id = :p1 "
//				+ " AND v.authentificationEntity.id = u.id "))
//				.thenReturn(mockedQuery10);
//		when(mockedQuery10.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery10);
//
//
//		Query mockedQuery11 = mock(Query.class);
//		when(em.createQuery("SELECT u.authentificationEntity.active "
//				   + "FROM  PermitEntity u "
//				   + "WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery11);
//		when(mockedQuery11.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery11);
//
//		Query mockedQuery12 = mock(Query.class);
//		when(em.createQuery("SELECT u.authentificationEntity.deleted "
//				   + "FROM  PermitEntity u "
//				   + "WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery12);
//		when(mockedQuery12.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery12);
//
//		Query mockedQuery13 = mock(Query.class);
//		when(em.createQuery("SELECT u.homeOwnName from PermitEntity u where u.id =:p1"))
//				.thenReturn(mockedQuery13);
//		when(mockedQuery13.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery13);
//
//		Query mockedQuery14 = mock(Query.class);
//		when(em.createQuery("SELECT u.iscONTRACTORConfirm "
//				+ "FROM RFIConfirmationEntity u "
//				+ "WHERE u.idPermit.id = :p1 "))
//				.thenReturn(mockedQuery14);
//		when(mockedQuery14.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery14);
//
////		Query mockedQuery15 = mock(Query.class);
////		when(em.createQuery("SELECT u "
////				   + "FROM  PermitEntity u "
////				   + "WHERE u.id = :p1 "))
////				.thenReturn(mockedQuery15);
////		when(mockedQuery15.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery15);
//
//		Query mockedQuery16 = mock(Query.class);
//		when(em.createQuery("SELECT u from ProjectsTrackerEntity u WHERE u.permit.id = :p1 "))
//				.thenReturn(mockedQuery16);
//		when(mockedQuery16.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery16);
//
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
//		// Result of the query4
//		scenario.get(0).add(null);
//		// Result of the query5
//		scenario.get(0).add(null);
//		// Result of the query6
//		scenario.get(0).add(null);
//		// Result of the query7
//		scenario.get(0).add(null);
//		// Result of the query8
//		scenario.get(0).add(null);
//		// Result of the query9
//		scenario.get(0).add(null);
//		// Result of the query10
//		scenario.get(0).add(null);
//		// Result of the query11
//		scenario.get(0).add(null);
//		// Result of the query12
//		scenario.get(0).add(null);
//		// Result of the query13
//		scenario.get(0).add(null);
//		// Result of the query14
//		scenario.get(0).add(null);
//		// Result of the query15
//		scenario.get(0).add(null);
//		// Result of the query16
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//		//the list of result of the Query4
//    	scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
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
//		// Result of the query6
//		scenario.get(1).add(null);
//		// Result of the query7
//		scenario.get(1).add(null);
//		// Result of the query8
//		scenario.get(1).add(null);
//		// Result of the query9
//		scenario.get(1).add(null);
//		// Result of the query10
//		scenario.get(1).add(null);
//		// Result of the query11
//		scenario.get(1).add(null);
//		// Result of the query12
//		scenario.get(1).add(null);
//		// Result of the query13
//		scenario.get(1).add(null);
//		// Result of the query14
//		scenario.get(1).add(null);
//		// Result of the query15
//		scenario.get(1).add(null);
//		// Result of the query16
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//		//the list of result of the Query4
//    	scenario.get(1).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("12588");
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
//		// Result of the query6
//		scenario.get(2).add(null);
//		// Result of the query7
//		scenario.get(2).add(null);
//		// Result of the query8
//		scenario.get(2).add(null);
//		// Result of the query9
//		scenario.get(2).add(null);
//		// Result of the query10
//		scenario.get(2).add(null);
//		// Result of the query11
//		scenario.get(2).add(null);
//		// Result of the query12
//		scenario.get(2).add(null);
//		// Result of the query13
//		scenario.get(2).add(null);
//		// Result of the query14
//		scenario.get(2).add(null);
//		// Result of the query15
//		scenario.get(2).add(null);
//		// Result of the query16
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//		//the list of result of the Query4
//    	scenario.get(2).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("12588");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		PermitEntity pr = new PermitEntity();
//		pr.setAuthentificationEntity(new AuthentificationEntity());
//		scenario.get(3).add(pr);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result of the query4
//		scenario.get(3).add(null);
//		// Result of the query5
//		scenario.get(3).add(null);
//		// Result of the query6
//		scenario.get(3).add(null);
//		// Result of the query7
//		scenario.get(3).add(null);
//		// Result of the query8
//		scenario.get(3).add(null);
//		// Result of the query9
//		scenario.get(3).add(null);
//		// Result of the query10
//		scenario.get(3).add(null);
//		// Result of the query11
//		scenario.get(3).add(null);
//		// Result of the query12
//		scenario.get(3).add(null);
//		// Result of the query13
//		scenario.get(3).add(null);
//		// Result of the query14
//		scenario.get(3).add(null);
//		// Result of the query15
//		scenario.get(3).add(null);
//		// Result of the query16
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("error");
//		//the list of result of the Query4
//    	scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("12588");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add("");
//		// Result of the query2
//		scenario.get(4).add(pr);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result of the query4
//		scenario.get(4).add(null);
//		// Result of the query5
//		scenario.get(4).add(null);
//		// Result of the query6
//		scenario.get(4).add(null);
//		// Result of the query7
//		scenario.get(4).add(null);
//		// Result of the query8
//		scenario.get(4).add(null);
//		// Result of the query9
//		scenario.get(4).add(null);
//		// Result of the query10
//		scenario.get(4).add(null);
//		// Result of the query11
//		scenario.get(4).add(null);
//		// Result of the query12
//		scenario.get(4).add(null);
//		// Result of the query13
//		scenario.get(4).add(null);
//		// Result of the query14
//		scenario.get(4).add(null);
//		// Result of the query15
//		scenario.get(4).add(null);
//		// Result of the query16
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("error");
//		//the list of result of the Query4
//    	scenario.get(4).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("12588");
//		scenario.get(5).add("");
//		scenario.get(5).add(null);
//		// Result of the query1
//		scenario.get(5).add("123");
//		// Result of the query2
//		scenario.get(5).add(pr);
//		// Result of the query3
//		scenario.get(5).add(null);
//		// Result of the query4
//		scenario.get(5).add(null);
//		// Result of the query5
//		scenario.get(5).add(null);
//		// Result of the query6
//		scenario.get(5).add(null);
//		// Result of the query7
//		scenario.get(5).add(null);
//		// Result of the query8
//		scenario.get(5).add(null);
//		// Result of the query9
//		scenario.get(5).add(null);
//		// Result of the query10
//		scenario.get(5).add(null);
//		// Result of the query11
//		scenario.get(5).add(null);
//		// Result of the query12
//		scenario.get(5).add(null);
//		// Result of the query13
//		scenario.get(5).add(null);
//		// Result of the query14
//		scenario.get(5).add(null);
//		// Result of the query15
//		scenario.get(5).add(null);
//		// Result of the query16
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("error");
//		//the list of result of the Query4
//    	scenario.get(5).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(6).add("12588");
//		scenario.get(6).add("2558");
//		scenario.get(6).add(null);
//		// Result of the query1
//		scenario.get(6).add("123");
//		// Result of the query2
//		scenario.get(6).add(pr);
//		// Result of the query3
//		scenario.get(6).add(null);
//		// Result of the query4
//		scenario.get(6).add(null);
//		// Result of the query5
//		scenario.get(6).add(null);
//		// Result of the query6
//		scenario.get(6).add(null);
//		// Result of the query7
//		scenario.get(6).add(null);
//		// Result of the query8
//		scenario.get(6).add(null);
//		// Result of the query9
//		scenario.get(6).add(null);
//		// Result of the query10
//		scenario.get(6).add(null);
//		// Result of the query11
//		scenario.get(6).add(null);
//		// Result of the query12
//		scenario.get(6).add(null);
//		// Result of the query13
//		scenario.get(6).add(null);
//		// Result of the query14
//		scenario.get(6).add(null);
//		// Result of the query15
//		scenario.get(6).add(null);
//		// Result of the query16
//		scenario.get(6).add(null);
//		// Result excpected
//		scenario.get(6).add("notPermit");
//		//the list of result of the Query4
//    	scenario.get(6).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(7).add("12588");
//		scenario.get(7).add("2558");
//		scenario.get(7).add(null);
//		// Result of the query1
//		scenario.get(7).add("123");
//		// Result of the query2
//		scenario.get(7).add(pr);
//		// Result of the query3
//		scenario.get(7).add(null);
//		// Result of the query4
//		scenario.get(7).add(new RFIConfirmationEntity());
//		// Result of the query5
//		scenario.get(7).add(null);
//		// Result of the query6
//		scenario.get(7).add(null);
//		// Result of the query7
//		scenario.get(7).add(null);
//		// Result of the query8
//		scenario.get(7).add(null);
//		// Result of the query9
//		scenario.get(7).add(null);
//		// Result of the query10
//		scenario.get(7).add(null);
//		// Result of the query11
//		scenario.get(7).add(null);
//		// Result of the query12
//		scenario.get(7).add(null);
//		// Result of the query13
//		scenario.get(7).add(null);
//		// Result of the query14
//		scenario.get(7).add(null);
//		// Result of the query15
//		scenario.get(7).add(null);
//		// Result of the query16
//		scenario.get(7).add(null);
//		// Result excpected
//		scenario.get(7).add("success");
//		//the list of result of the Query4
//		ArrayList<RFIConfirmationEntity> listQ4 = new ArrayList<RFIConfirmationEntity>();
//		listQ4.add(null);
//    	scenario.get(7).add(listQ4);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getConfirmationRfi [ " + i + " ]"+scenario.get(i).get(4));
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(6));
//			when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			when(mockedQuery6.getSingleResult()).thenReturn(scenario.get(i).get(8));
//			when(mockedQuery7.getSingleResult()).thenReturn(scenario.get(i).get(9));
//			when(mockedQuery8.getSingleResult()).thenReturn(scenario.get(i).get(10));
//			when(mockedQuery9.getSingleResult()).thenReturn(scenario.get(i).get(11));
//			when(mockedQuery10.getResultList()).thenReturn((List) scenario.get(i).get(12));
//			when(mockedQuery11.getSingleResult()).thenReturn(scenario.get(i).get(13));
//			when(mockedQuery12.getSingleResult()).thenReturn(scenario.get(i).get(14));
//			when(mockedQuery13.getSingleResult()).thenReturn(scenario.get(i).get(15));
//			when(mockedQuery14.getSingleResult()).thenReturn(scenario.get(i).get(16));
//		//	when(mockedQuery15.getSingleResult()).thenReturn(scenario.get(i).get(17));
//			when(mockedQuery16.getSingleResult()).thenReturn(scenario.get(i).get(18));
//			when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(20));
//		    rFInformationService.submitADVRFI((String) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2));
//		}
//	}
//
//	@Test
//     public void testsubmitContractorRFI() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				   + "FROM  AuthentificationEntity u "
//				   + "WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				   + "FROM  RFIConfirmationEntity u "
//				   + "WHERE u.idPermit.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u.email "
//				   + "FROM  AuthentificationEntity u "
//				   + "WHERE u.roleEntity.id = :p1 "))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u.idAdvUserCo from RFInformationEntity u where u.idPermit.id=:p1"))
//				.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//		when(mockedQuery4.setMaxResults(1)).thenReturn(mockedQuery4);
//
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT u.homeOwnName from PermitEntity u where u.id =:p1"))
//				.thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
//
//		Query mockedQuery6 = mock(Query.class);
//		when(em.createQuery("SELECT u.deleted "
//				   + "FROM  AuthentificationEntity u "
//				   + "WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery6);
//		when(mockedQuery6.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery6);
//
//		Query mockedQuery7 = mock(Query.class);
//		when(em.createQuery("SELECT u from ProjectsTrackerEntity u WHERE u.permit.id = :p1 "))
//				.thenReturn(mockedQuery7);
//		when(mockedQuery7.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery7);
//
//		Query mockedQuery8 = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from PermitEntity u " + " where u.id = :p1 "))
//				.thenReturn(mockedQuery8);
//		when(mockedQuery8.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery8);
//
//		Query mockedQuery9 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				   + "FROM  AuthentificationEntity u "
//				   + "WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery9);
//		when(mockedQuery9.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery9);
//
//
//
//		Query mockedQuery11 = mock(Query.class);
//		when(em.createQuery("SELECT u.homeOwnName from PermitEntity u where u.id =:p1"))
//				.thenReturn(mockedQuery11);
//		when(mockedQuery11.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery11);
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
//		// Result of the query4
//		scenario.get(0).add(null);
//		// Result of the query5
//		scenario.get(0).add(null);
//		// Result of the query6
//		scenario.get(0).add(null);
//		// Result of the query7
//		scenario.get(0).add(null);
//		// Result of the query8
//		scenario.get(0).add(null);
//		// Result of the query9
//		scenario.get(0).add(null);
//		// Result of the query10
//		scenario.get(0).add(null);
//		// Result of the query11
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//		//Result list of the Query2
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
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
//		// Result of the query5
//		scenario.get(1).add(null);
//		// Result of the query6
//		scenario.get(1).add(null);
//		// Result of the query7
//		scenario.get(1).add(null);
//		// Result of the query8
//		scenario.get(1).add(null);
//		// Result of the query9
//		scenario.get(1).add(null);
//		// Result of the query10
//		scenario.get(1).add(null);
//		// Result of the query11
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//		//Result list of the Query2
//		scenario.get(1).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
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
//		// Result of the query5
//		scenario.get(2).add(null);
//		// Result of the query6
//		scenario.get(2).add(null);
//		// Result of the query7
//		scenario.get(2).add(null);
//		// Result of the query8
//		scenario.get(2).add(null);
//		// Result of the query9
//		scenario.get(2).add(null);
//		// Result of the query10
//		scenario.get(2).add(null);
//		// Result of the query11
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//		//Result list of the Query2
//		scenario.get(2).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("");
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result of the query4
//		scenario.get(3).add(null);
//		// Result of the query5
//		scenario.get(3).add(null);
//		// Result of the query6
//		scenario.get(3).add(null);
//		// Result of the query7
//		scenario.get(3).add(null);
//		// Result of the query8
//		scenario.get(3).add(null);
//		// Result of the query9
//		scenario.get(3).add(null);
//		// Result of the query10
//		scenario.get(3).add(null);
//		// Result of the query11
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("error");
//		//Result list of the Query2
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("258");
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result of the query4
//		scenario.get(4).add(null);
//		// Result of the query5
//		scenario.get(4).add(null);
//		// Result of the query6
//		scenario.get(4).add(null);
//		// Result of the query7
//		scenario.get(4).add(null);
//		// Result of the query8
//		scenario.get(4).add(null);
//		// Result of the query9
//		scenario.get(4).add(null);
//		// Result of the query10
//		scenario.get(4).add(null);
//		// Result of the query11
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("notPermit");
//		//Result list of the Query2
//		scenario.get(4).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("258");
//		scenario.get(5).add("123");
//		// Result of the query1
//		scenario.get(5).add(null);
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result of the query3
//		scenario.get(5).add(null);
//		// Result of the query4
//		scenario.get(5).add(null);
//		// Result of the query5
//		scenario.get(5).add(null);
//		// Result of the query6
//		scenario.get(5).add(null);
//		// Result of the query7
//		scenario.get(5).add(null);
//		// Result of the query8
//		scenario.get(5).add(null);
//		// Result of the query9
//		scenario.get(5).add(null);
//		// Result of the query10
//		scenario.get(5).add(null);
//		// Result of the query11
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("error");
//		//Result list of the Query2
//		ArrayList<RFIConfirmationEntity> listQ2 = new ArrayList<RFIConfirmationEntity>();
//		listQ2.add(null);
//		scenario.get(5).add(listQ2);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(6).add("258");
//		scenario.get(6).add("123");
//		// Result of the query1
//		scenario.get(6).add(null);
//		// Result of the query2
//		scenario.get(6).add(null);
//		// Result of the query3
//		scenario.get(6).add(null);
//		// Result of the query4
//		ArrayList<AuthentificationEntity> listQ4 = new ArrayList<AuthentificationEntity>();
//		listQ4.add(null);
//		scenario.get(6).add(listQ4);
//		// Result of the query5
//		scenario.get(6).add(null);
//		// Result of the query6
//		scenario.get(6).add(null);
//		// Result of the query7
//		scenario.get(6).add(null);
//		// Result of the query8
//		scenario.get(6).add(null);
//		// Result of the query9
//		scenario.get(6).add(null);
//		// Result of the query10
//		scenario.get(6).add(null);
//		// Result of the query11
//		scenario.get(6).add(null);
//		// Result excpected
//		scenario.get(6).add("error");
//		//Result list of the Query2
//		scenario.get(6).add(listQ2);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(7).add("258");
//		scenario.get(7).add("123");
//		// Result of the query1
//		scenario.get(7).add(null);
//		// Result of the query2
//		scenario.get(7).add(null);
//		// Result of the query3
//		scenario.get(7).add(null);
//		// Result of the query4
//		ArrayList<AuthentificationEntity> listQ4v1 = new ArrayList<AuthentificationEntity>();
//		listQ4v1.add(new AuthentificationEntity());
//		scenario.get(7).add(listQ4v1);
//		// Result of the query5
//		scenario.get(7).add(null);
//		// Result of the query6
//		scenario.get(7).add(null);
//		// Result of the query7
//		scenario.get(7).add(null);
//		// Result of the query8
//		scenario.get(7).add(null);
//		// Result of the query9
//		scenario.get(7).add(null);
//		// Result of the query10
//		scenario.get(7).add(null);
//		// Result of the query11
//		scenario.get(7).add(null);
//		// Result excpected
//		scenario.get(7).add("success");
//		//Result list of the Query2
//		scenario.get(7).add(listQ2);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(8).add("258");
//		scenario.get(8).add("123");
//		// Result of the query1
//		scenario.get(8).add(null);
//		// Result of the query2
//		scenario.get(8).add(null);
//		// Result of the query3
//		scenario.get(8).add(null);
//		// Result of the query4
//		ArrayList<AuthentificationEntity> listQ4v2 = new ArrayList<AuthentificationEntity>();
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setActive(true);
//		listQ4v2.add(auth);
//		scenario.get(8).add(listQ4v2);
//		// Result of the query5
//		scenario.get(8).add(null);
//		// Result of the query6
//		scenario.get(8).add(false);
//		// Result of the query7
//		scenario.get(8).add(null);
//		// Result of the query8
//		scenario.get(8).add(null);
//		// Result of the query9
//		scenario.get(8).add(null);
//		// Result of the query10
//		scenario.get(8).add(null);
//		// Result of the query11
//		scenario.get(8).add(null);
//		// Result excpected
//		scenario.get(8).add("error");
//		//Result list of the Query2
//		scenario.get(8).add(listQ2);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(9).add("258");
//		scenario.get(9).add("123");
//		// Result of the query1
//		scenario.get(9).add(null);
//		// Result of the query2
//		scenario.get(9).add(null);
//		// Result of the query3
//		ArrayList<String> listQ3 = new ArrayList<String>();
//		listQ3.add(null);
//		scenario.get(9).add(listQ3);
//		// Result of the query4
//		scenario.get(9).add(listQ4v2);
//		// Result of the query5
//		scenario.get(9).add(null);
//		// Result of the query6
//		scenario.get(9).add(false);
//		// Result of the query7
//		scenario.get(9).add(null);
//		// Result of the query8
//		scenario.get(9).add(null);
//		// Result of the query9
//		scenario.get(9).add(null);
//		// Result of the query10
//		scenario.get(9).add(null);
//		// Result of the query11
//		scenario.get(9).add(null);
//		// Result excpected
//		scenario.get(9).add("success");
//		//Result list of the Query2
//		scenario.get(9).add(listQ2);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getConfirmationRfi [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(14));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(6));
//			when(mockedQuery6.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			when(mockedQuery7.getSingleResult()).thenReturn(scenario.get(i).get(8));
//			when(mockedQuery8.getSingleResult()).thenReturn(scenario.get(i).get(9));
//			when(mockedQuery9.getSingleResult()).thenReturn(scenario.get(i).get(10));
//		//	when(mockedQuery10.getResultList()).thenReturn((List) scenario.get(i).get(11));
//			when(mockedQuery11.getSingleResult()).thenReturn(scenario.get(i).get(12));
//			when(em.find(PermitEntity.class, 258)).thenReturn(null);
//		    rFInformationService.submitContractorRFI((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		}
//	 }
//
//	@Test
//	public void testgetConfirmationRfi() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + "FROM  RFIConfirmationEntity u " + "WHERE u.idPermit.id = :p1 "))
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
//		scenario.get(0).add(new RFIConfirmationEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new RFIConfirmationEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(new RFIConfirmationEntity());
//		// Result excpected
//		scenario.get(3).add(new RFIConfirmationEntity());
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getConfirmationRfi [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			RFIConfirmationEntity rslt = rFInformationService.getConfirmationRfi((String) scenario.get(i).get(0));
//		}
//	}
//
//	@Test
//	public void testgetRFIQuestion() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				   + "FROM RFIQuestionEntity u "
//				   + "WHERE u.fieldName = :p1 and u.questionActived = :p2"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		List<String> list = new ArrayList<String>();
//		list.add("Wire Qty[");
//		scenario.get(1).add(list);
//		// Result of the query1
//		ArrayList<RFIQuestionEntity> listQ1 = new ArrayList<RFIQuestionEntity>();
//		listQ1.add(null);
//		listQ1.add(new RFIQuestionEntity());
//		scenario.get(1).add(listQ1);
//		// Result excpected
//		scenario.get(1).add(listQ1);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getRfibyPermit [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//
//			List<RFIQuestionEntity> rslt = (List<RFIQuestionEntity>) rFInformationService.getRFIQuestion((List<String>) scenario.get(i).get(0));
//
//		}
//	}
//
//	@Test
//	public void testaddNotifSubmitRfiADV() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from PermitEntity u " + " where u.id = :p1 "))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(" SELECT u  FROM AuthentificationEntity u WHERE u.id = :p1  "))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
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
//		scenario.get(0).add("Fail");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("");
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("589");
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("589");
//		scenario.get(5).add("123");
//		// Result of the query1
//		PermitEntity per = new PermitEntity();
//		per.setAuthentificationEntity(new AuthentificationEntity());
//		scenario.get(5).add(per);
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addNotifSubmitRfiADV [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			rFInformationService.addNotifSubmitRfiADV((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//
//		}
//	}
//
//	@Test
//	public void testaddNotifSubmitRfiContractor() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from PermitEntity u " + " where u.id = :p1 "))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
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
//		scenario.get(0).add("Fail");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("");
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("589");
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("589");
//		scenario.get(5).add("123");
//		// Result of the query1
//		PermitEntity per = new PermitEntity();
//		per.setAuthentificationEntity(new AuthentificationEntity());
//		scenario.get(5).add(per);
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addNotifSubmitRfiContractor [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			rFInformationService.addNotifSubmitRfiContractor((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//	       }
//	}
//}
