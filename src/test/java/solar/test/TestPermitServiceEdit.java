//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.io.FileInputStream;
//import java.util.ArrayList;
//import java.util.Calendar;
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
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitCompanyInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.RailRacking;
//import com.PlayGroundAdv.Solar.Entity.RsheetsLibraryEntity;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityDropDownResult;
//import com.PlayGroundAdv.Solar.model.PermitEditStatusModel;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetPermitsService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.MailingService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.PermitServiceEdit;
//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
//
//public class TestPermitServiceEdit {
//
//	@Mock
//	EntityManager em;
//
//	@Mock
//	MailingService mailingService;
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
//	private PermitServiceEdit permitServiceEdit = new PermitServiceEdit();
//
//
//    @Before
//	public void setupMock() {
//    	permitServiceEdit = new PermitServiceEdit();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testfailedPermitEntity() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from PermitEntity u " + " where u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(" SELECT u  FROM AuthentificationEntity u WHERE u.id = :p1  "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
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
//		scenario.get(0).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("12588");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("12588");
//		// Result of the query1
//		scenario.get(3).add(new PermitEntity());
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("done");
//
//
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("submitPermitEntity [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//					permitServiceEdit.failedPermitEntity((String) scenario.get(i).get(0));
//
//		}
//
//
//
//	}
//
//	@Test
//	public void testsubmitPermitEntity() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from PermitEntity u " + " where u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(" SELECT u  FROM AuthentificationEntity u WHERE u.id = :p1  "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from ProjectsTrackerEntity u WHERE u.permit.id = :p1 "))
//				.thenReturn(mockedQuery3);
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
//		scenario.get(0).add("Fail");
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
//		// Result of the query3
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
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("Fail");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("125");
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("125");
//		scenario.get(5).add("123");
//		// Result of the query1
//		scenario.get(5).add(new PermitEntity());
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result of the query3
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(6).add("125");
//		scenario.get(6).add("123");
//		// Result of the query1
//		scenario.get(6).add(new PermitEntity());
//		// Result of the query2
//		scenario.get(6).add(new AuthentificationEntity());
//		// Result of the query3
//		scenario.get(6).add(null);
//		// Result excpected
//		scenario.get(6).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("submitPermitEntity [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(4));
//
//					permitServiceEdit.submitPermitEntity((String) scenario.get(i).get(0),(String) scenario.get(i).get(1),false);
//
//		}
//
//
//	}
//
//	@Test
//	public void testgetDropDownValues() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitArrayEntityDropDownResult  "
//				+ " (u.deviceToIncorporate, " + " u.pvModuleModEl, " + " u.inverterModel, "
//				+ " u.secondInverterModel, " + " u.thirdInverterModel, " + " u.fourthInverterModel, "
//				+ " u.fifthInverterModel, " + " u.systemOptimizerModel, " + " u.microInverterManufacturer, "
//				+ " u.microInverterModel" + " ) " + " from PermitArraysEntity u  "
//				+ " where u.permitEntity.id = :p1 "))
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
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("1235");
//		// Result of the query1
//		PermitArrayEntityDropDownResult per = new PermitArrayEntityDropDownResult();
//		scenario.get(3).add(per);
//		// Result excpected
//		scenario.get(3).add(per);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("CloseProject [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//					permitServiceEdit.getDropDownValues((String) scenario.get(i).get(0));
//
//		}
//	}
//
//	@Test
//	public void testOpenProject () {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitCompanyInfoEntity u WHERE u.permitEntity.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(" SELECT u FROM AuthentificationEntity u WHERE u.id = :p1  "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
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
//		scenario.get(0).add(new PermitEditStatusModel());
//		//Result list of the Query2
//		scenario.get(0).add(null);
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
//		scenario.get(1).add(new PermitEditStatusModel());
//		//Result list of the Query2
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add("258");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new PermitEditStatusModel());
//		//Result list of the Query2
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("");
//		scenario.get(3).add("258");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new PermitEditStatusModel());
//		//Result list of the Query2
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("1235");
//		scenario.get(4).add("258");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(new PermitEditStatusModel());
//		//Result list of the Query2
//		scenario.get(4).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("1235");
//		scenario.get(5).add("258");
//		// Result of the query1
//		scenario.get(5).add(new PermitCompanyInfoEntity());
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add(new PermitEditStatusModel());
//		//Result list of the Query2
//		scenario.get(5).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(6).add("1235");
//		scenario.get(6).add("258");
//		// Result of the query1
//		scenario.get(6).add(new PermitCompanyInfoEntity());
//		// Result of the query2
//		AuthentificationEntity auth = new AuthentificationEntity();
//		scenario.get(6).add(auth);
//		// Result excpected
//		PermitEditStatusModel per = new PermitEditStatusModel();
//		per.setOpened(true);
//		per.setHasEditRequest(false);
//		per.setOpenedBy(auth.getId());
//		per.setHasEditAccess(true);
//		scenario.get(6).add(per);
//		//Result list of the Query2
//		ArrayList<AuthentificationEntity> list2 = new ArrayList<AuthentificationEntity>();
//		list2.add(null);
//		scenario.get(6).add(list2);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(7).add("1235");
//		scenario.get(7).add("258");
//		// Result of the query1
//		PermitCompanyInfoEntity perQ1 = new PermitCompanyInfoEntity();
//		perQ1.setOpened(true);
//		scenario.get(7).add(perQ1);
//		// Result of the query2
//		scenario.get(7).add(auth);
//		// Result excpected
//		PermitEditStatusModel per2 = new PermitEditStatusModel();
//		per2.setOpened(true);
//		per2.setHasEditRequest(false);
//		per2.setOpenedBy(0);
//		per2.setHasEditAccess(false);
//		scenario.get(7).add(per2);
//		//Result list of the Query2
//		scenario.get(7).add(list2);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("OpenProjectUtilityComapny [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			permitServiceEdit.OpenProjectUtilityComapny((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testCloseProject () {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u WHERE u.id = :p1 "))
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
//		scenario.get(0).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Done");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("1235");
//		// Result of the query1
//		PermitEntity per = new PermitEntity();
//		scenario.get(3).add(per);
//		// Result excpected
//		scenario.get(3).add("Done");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("CloseProject [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//					permitServiceEdit.CloseProject((String) scenario.get(i).get(0));
//
//		}
//	}
//
//
//	@Test
//	public void testrequestPermitAccess () {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u WHERE u.id = :p1 "))
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
//		scenario.get(0).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Done");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("1235");
//		// Result of the query1
//		PermitEntity per = new PermitEntity();
//		scenario.get(3).add(per);
//		// Result excpected
//		scenario.get(3).add("Done");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("requestPermitAccess [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//					permitServiceEdit.requestPermitAccess((String) scenario.get(i).get(0));
//
//		}
//	}
//
//	@Test
//	public void testOpenProjectUtilityComapny () {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitCompanyInfoEntity u WHERE u.permitEntity.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(" SELECT u FROM AuthentificationEntity u WHERE u.id = :p1  "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
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
//		scenario.get(0).add(new PermitEditStatusModel());
//		//Result list of the Query2
//		scenario.get(0).add(null);
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
//		scenario.get(1).add(new PermitEditStatusModel());
//		//Result list of the Query2
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add("258");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new PermitEditStatusModel());
//		//Result list of the Query2
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("");
//		scenario.get(3).add("258");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new PermitEditStatusModel());
//		//Result list of the Query2
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("1235");
//		scenario.get(4).add("258");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(new PermitEditStatusModel());
//		//Result list of the Query2
//		scenario.get(4).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("1235");
//		scenario.get(5).add("258");
//		// Result of the query1
//		scenario.get(5).add(new PermitCompanyInfoEntity());
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add(new PermitEditStatusModel());
//		//Result list of the Query2
//		scenario.get(5).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(6).add("1235");
//		scenario.get(6).add("258");
//		// Result of the query1
//		scenario.get(6).add(new PermitCompanyInfoEntity());
//		// Result of the query2
//		AuthentificationEntity auth = new AuthentificationEntity();
//		scenario.get(6).add(auth);
//		// Result excpected
//		PermitEditStatusModel per = new PermitEditStatusModel();
//		per.setOpened(true);
//		per.setHasEditRequest(false);
//		per.setOpenedBy(auth.getId());
//		per.setHasEditAccess(true);
//		scenario.get(6).add(per);
//		//Result list of the Query2
//		ArrayList<AuthentificationEntity> list2 = new ArrayList<AuthentificationEntity>();
//		list2.add(null);
//		scenario.get(6).add(list2);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(7).add("1235");
//		scenario.get(7).add("258");
//		// Result of the query1
//		PermitCompanyInfoEntity perQ1 = new PermitCompanyInfoEntity();
//		perQ1.setOpened(true);
//		scenario.get(7).add(perQ1);
//		// Result of the query2
//		scenario.get(7).add(auth);
//		// Result excpected
//		PermitEditStatusModel per2 = new PermitEditStatusModel();
//		per2.setOpened(true);
//		per2.setHasEditRequest(false);
//		per2.setOpenedBy(0);
//		per2.setHasEditAccess(false);
//		scenario.get(7).add(per2);
//		//Result list of the Query2
//		scenario.get(7).add(list2);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("OpenProjectUtilityComapny [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//					permitServiceEdit.OpenProjectUtilityComapny((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testCloseProjectUtilityComapny () {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitCompanyInfoEntity u WHERE u.permitEntity.id = :p1 "))
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
//		scenario.get(0).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Done");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("1235");
//		// Result of the query1
//		PermitCompanyInfoEntity per = new PermitCompanyInfoEntity();
//		scenario.get(3).add(per);
//		// Result excpected
//		scenario.get(3).add("Done");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("CloseProjectUtilityComapny [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//					permitServiceEdit.CloseProjectUtilityComapny((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testrequestUtilityComapnyAccess () {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitCompanyInfoEntity u WHERE u.permitEntity.id = :p1 "))
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
//		scenario.get(0).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Done");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("1235");
//		// Result of the query1
//		PermitCompanyInfoEntity per = new PermitCompanyInfoEntity();
//		scenario.get(3).add(per);
//		// Result excpected
//		scenario.get(3).add("Done");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("requestUtilityComapnyAccess [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//					permitServiceEdit.requestUtilityComapnyAccess((String) scenario.get(i).get(0));
//
//		}
//	}
//
//	@Test
//	public void testcheckProjectEditStatus () {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u WHERE u.id = :p1 "))
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
//		scenario.get(0).add(null);
//		// The result list of Query1
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(null);
//		// The result list of Query1
//		scenario.get(1).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(null);
//		// The result list of Query1
//		scenario.get(2).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("1235");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(null);
//		// The result list of Query1
//		ArrayList<PermitEntity> list = new ArrayList<PermitEntity>();
//		list.add(null);
//		scenario.get(3).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("1235");
//		// Result of the query1
//		PermitEntity per = new PermitEntity();
//		per.setHasEditRequest(true);
//		per.setOpened(true);
//		scenario.get(4).add(per);
//		// Result excpected
//		scenario.get(4).add(true);
//		// The result list of Query1
//		scenario.get(4).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("checkProjectEditStatus [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//					permitServiceEdit.checkProjectEditStatus((String) scenario.get(i).get(0));
//
//		}
//	}
//
//	@Test
//	public void testcheckProjectEditRequest () {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("update PermitEntity u set u.hasEditRequest = :p1, u.isChecking = :p2 where u.id= :p3"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//		// The result list of Query1
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(null);
//		// The result list of Query1
//		scenario.get(1).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(null);
//		// The result list of Query1
//		scenario.get(2).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("1235");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(null);
//		// The result list of Query1
//		ArrayList<PermitEntity> list = new ArrayList<PermitEntity>();
//		list.add(null);
//		scenario.get(3).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("1235");
//		// Result of the query1
//		PermitEntity per = new PermitEntity();
//		per.setHasEditRequest(true);
//		scenario.get(4).add(per);
//		// Result excpected
//		scenario.get(4).add(true);
//		// The result list of Query1
//		scenario.get(4).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("checkProjectEditRequest [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			when(mockedQuery2.executeUpdate()).thenReturn(1);
//					permitServiceEdit.checkProjectEditRequest((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testcheckUtilityComapnyEditStatus () {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitCompanyInfoEntity u WHERE u.permitEntity.id = :p1 "))
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
//		scenario.get(0).add(null);
//		// The result list of Query1
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(null);
//		// The result list of Query1
//		scenario.get(1).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(null);
//		// The result list of Query1
//		scenario.get(2).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("1235");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(null);
//		// The result list of Query1
//		ArrayList<PermitCompanyInfoEntity> list = new ArrayList<PermitCompanyInfoEntity>();
//		list.add(null);
//		scenario.get(3).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("1235");
//		// Result of the query1
//		PermitCompanyInfoEntity per = new PermitCompanyInfoEntity();
//		per.setHasEditRequest(true);
//		per.setOpened(true);
//		scenario.get(4).add(per);
//		// Result excpected
//		scenario.get(4).add(true);
//		// The result list of Query1
//		scenario.get(4).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("checkUtilityComapnyEditStatus [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//					permitServiceEdit.checkUtilityComapnyEditStatus((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testcheckUtilityComapnyEditRequest() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitCompanyInfoEntity u WHERE u.permitEntity.id = :p1 "))
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
//		scenario.get(0).add(null);
//		// The result list of Query1
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(null);
//		// The result list of Query1
//		scenario.get(1).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(null);
//		// The result list of Query1
//		scenario.get(2).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("1235");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(null);
//		// The result list of Query1
//		ArrayList<PermitCompanyInfoEntity> list = new ArrayList<PermitCompanyInfoEntity>();
//		list.add(null);
//		scenario.get(3).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("1235");
//		// Result of the query1
//		PermitCompanyInfoEntity per = new PermitCompanyInfoEntity();
//		per.setHasEditRequest(true);
//		scenario.get(4).add(per);
//		// Result excpected
//		scenario.get(4).add(true);
//		// The result list of Query1
//		scenario.get(4).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("checkUtilityComapnyEditRequest [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//					permitServiceEdit.checkUtilityComapnyEditRequest((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testgetUserFullName() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(" SELECT u FROM AuthentificationEntity u WHERE u.id = :p1  ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(1225);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Not Found");
//		// The result list of Query1
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(1225);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Not Found");
//		// The result list of Query1
//		ArrayList<AuthentificationEntity> list = new ArrayList<AuthentificationEntity>();
//		list.add(null);
//		scenario.get(1).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(1225);
//		// Result of the query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setFirstName("malek");
//		auth.setLastName("abd");
//		scenario.get(2).add(auth);
//		// Result excpected
//		scenario.get(2).add("malek abd");
//		// The result list of Query1
//		scenario.get(2).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getUserFullName [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//					permitServiceEdit.getUserFullName((Integer) scenario.get(i).get(0));
//
//		}
//
//	}
//	@Test
//	public void testgetgroundRackingMgt () {
//
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.manufacturer " + " from RailRacking u " + " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(1225);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(1225);
//		// Result of the query1
//		scenario.get(1).add("abc");
//		// Result excpected
//		scenario.get(1).add("abc");
//
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getgroundRackingMgt [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//					permitServiceEdit.getgroundRackingMgt((Integer) scenario.get(i).get(0));
//
//		}
//
//
//	}
//
//	@Test
//	public void testgetclosestRsheets() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from RsheetsLibraryEntity u where u.manufacturer = :p1 AND u.model =:p2 AND u.isDeleted =:p3 "))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
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
//		scenario.get(0).add(new ArrayList<RsheetsLibraryEntity>());
//		// The result list of Query1
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<RsheetsLibraryEntity>());
//		// The result list of Query1
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("1588");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<RsheetsLibraryEntity>());
//		// The result list of Query1
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("1588");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new ArrayList<RsheetsLibraryEntity>());
//		// The result list of Query1
//		ArrayList<RailRacking> list = new ArrayList<RailRacking>();
//		list.add(null);
//		scenario.get(3).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("1588");
//		// Result of the query1
//		scenario.get(4).add(new RailRacking());
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(new ArrayList<RsheetsLibraryEntity>());
//		// The result list of Query1
//		scenario.get(4).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("1588");
//		// Result of the query1
//		scenario.get(5).add(new RailRacking());
//		// Result of the query2
//		ArrayList<RsheetsLibraryEntity> list2 = new ArrayList<RsheetsLibraryEntity>();
//		list2.add(null);
//		list2.add(new RsheetsLibraryEntity());
//		scenario.get(5).add(list2);
//		// Result excpected
//		scenario.get(5).add(list2);
//		// The result list of Query1
//		scenario.get(5).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getclosestRsheets [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			List<RsheetsLibraryEntity> rslt = (List<RsheetsLibraryEntity>) permitServiceEdit
//					.getclosestRsheets((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//}
