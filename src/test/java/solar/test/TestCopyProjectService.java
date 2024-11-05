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
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.Battery;
//import com.PlayGroundAdv.Solar.Entity.BatteryFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.ConduitConductorCircuitEntity;
//import com.PlayGroundAdv.Solar.Entity.FlashingFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.LeasePPAMeterFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitAdditionalInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitAdvEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitArraysEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitCompanyInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitConduitConductorSectionEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitDrafterDataEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEngineerEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitHomeSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitLayoutEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitSketchEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitTotalSectionEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitWeatherEntity;
//import com.PlayGroundAdv.Solar.model.SRsheetsModel;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.CopyProjectService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.PermitService;
//import com.PlayGroundAdv.Solar.Services.SsheetLibraryService;
//
//public class TestCopyProjectService {
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
//
//	@Mock
//	PermitService permitService;
//
//	@Spy
//	CheckValueTypesService checkValueTypesService;
//	@InjectMocks
//	CopyProjectService copyProjectService = new CopyProjectService();
//
//
//    @Before
//	public void setupMock() {
//    	copyProjectService = new CopyProjectService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testcopyPermit() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from AuthentificationEntity u " + " where u.id = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("Select u  "
//				+ "From PermitEntity u "
//				+ "Where u.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitConduitConductorSectionEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from ConduitConductorCircuitEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitProjectSiteInfoEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
//
//		Query mockedQuery6 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from RailRackingFavLibraryEntity u " + " where u.railRacking.id = :p1 AND u.authentificationEntity.id = :p3"))
//				.thenReturn(mockedQuery6);
//		when(mockedQuery6.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery6);
//
//		Query mockedQuery7 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery7);
//		when(mockedQuery7.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery7);
//
//		Query mockedQuery8 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id = :p1"))
//				.thenReturn(mockedQuery8);
//		when(mockedQuery8.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery8);
//
//		Query mockedQuery9 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitArraysEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery9);
//		when(mockedQuery9.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery9);
//
//		Query mockedQuery10 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitAdditionalInfoEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery10);
//		when(mockedQuery10.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery10);
//
//		Query mockedQuery11 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitTotalSectionEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery11);
//		when(mockedQuery11.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery11);
//
//		Query mockedQuery12 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitCompanyInfoEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery12);
//		when(mockedQuery12.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery12);
//
//		Query mockedQuery13 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitHomeSiteInfoEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery13);
//		when(mockedQuery13.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery13);
//
//		Query mockedQuery14 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitWeatherEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery14);
//		when(mockedQuery14.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery14);
//
//		Query mockedQuery15 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitAdvEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery15);
//		when(mockedQuery15.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery15);
//
//		Query mockedQuery16 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitEngineerEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery16);
//		when(mockedQuery16.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery16);
//
//		Query mockedQuery17 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitDrafterDataEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery17);
//		when(mockedQuery17.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery17);
//
//		Query mockedQuery18 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitLayoutEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery18);
//		when(mockedQuery18.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery18);
//
//		Query mockedQuery19 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitSketchEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery19);
//		when(mockedQuery19.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery19);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		CopyProjectService copyProjectService2 = Mockito.spy(copyProjectService);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
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
//		// Result of the query17
//		scenario.get(0).add(null);
//		// Result of the query18
//		scenario.get(0).add(null);
//		// Result of the query19
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("enter a name Permit Please ");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add("bonjour");
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
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
//		// Result of the query17
//		scenario.get(1).add(null);
//		// Result of the query18
//		scenario.get(1).add(null);
//		// Result of the query19
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("It seems that there is a technical problem, please try again.");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add("");
//		scenario.get(2).add("12345");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(new AuthentificationEntity());
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
//		// Result of the query17
//		scenario.get(2).add(null);
//		// Result of the query18
//		scenario.get(2).add(null);
//		// Result of the query19
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("It seems that there is a technical problem, please try again.");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("");
//		scenario.get(3).add("bnj");
//		scenario.get(3).add("12345");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new AuthentificationEntity());
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
//		// Result of the query17
//		scenario.get(3).add(null);
//		// Result of the query18
//		scenario.get(3).add(null);
//		// Result of the query19
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("It seems that there is a technical problem, please try again.");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("1235");
//		scenario.get(4).add("hello");
//		scenario.get(4).add("12345");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add(new AuthentificationEntity());
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
//		// Result of the query17
//		scenario.get(4).add(null);
//		// Result of the query18
//		scenario.get(4).add(null);
//		// Result of the query19
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("It seems that there is a technical problem, please try again.");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add("1235");
//		scenario.get(5).add("fff");
//		scenario.get(5).add("12345");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add("");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		// Result of the query1
//		scenario.get(5).add(null);
//		// Result of the query2
//		PermitEntity pr = new PermitEntity();
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
//		// Result of the query17
//		scenario.get(5).add(null);
//		// Result of the query18
//		scenario.get(5).add(null);
//		// Result of the query19
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("It seems that there is a technical problem, please try again.");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(6).add("1235");
//		scenario.get(6).add("fff");
//		scenario.get(6).add("12345");
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add("");
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		// Result of the query1
//		scenario.get(6).add(null);
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
//		PermitArraysEntity prArray = new PermitArraysEntity();
//		scenario.get(6).add(prArray);
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
//		// Result of the query17
//		scenario.get(6).add(null);
//		// Result of the query18
//		scenario.get(6).add(null);
//		// Result of the query19
//		scenario.get(6).add(null);
//		// Result excpected
//		scenario.get(6).add("123");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(7).add("1235");
//		scenario.get(7).add("");
//		scenario.get(7).add("12345");
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add("");
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		// Result of the query1
//		scenario.get(7).add(null);
//		// Result of the query2
//		scenario.get(7).add(pr);
//		// Result of the query3
//		scenario.get(7).add(null);
//		// Result of the query4
//		scenario.get(7).add(null);
//		// Result of the query5
//		scenario.get(7).add(new PermitProjectSiteInfoEntity());
//		// Result of the query6
//		scenario.get(7).add(null);
//		// Result of the query7
//		scenario.get(7).add(null);
//		// Result of the query8
//		scenario.get(7).add(null);
//		// Result of the query9
//		scenario.get(7).add(prArray);
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
//		// Result of the query17
//		scenario.get(7).add(null);
//		// Result of the query18
//		scenario.get(7).add(null);
//		// Result of the query19
//		scenario.get(7).add(null);
//		// Result excpected
//		scenario.get(7).add("123");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addTemplatePermit [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(10));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(11));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(12));
//			when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(13));
//			when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(14));
//			when(mockedQuery6.getResultList()).thenReturn((List) scenario.get(i).get(15));
//			when(mockedQuery7.getResultList()).thenReturn((List) scenario.get(i).get(16));
//			when(mockedQuery8.getResultList()).thenReturn((List) scenario.get(i).get(17));
//			when(mockedQuery9.getSingleResult()).thenReturn(scenario.get(i).get(18));
//			when(mockedQuery10.getResultList()).thenReturn((List) scenario.get(i).get(19));
//			when(mockedQuery11.getResultList()).thenReturn((List) scenario.get(i).get(20));
//			when(mockedQuery12.getResultList()).thenReturn((List) scenario.get(i).get(21));
//			when(mockedQuery13.getResultList()).thenReturn((List) scenario.get(i).get(22));
//			when(mockedQuery14.getResultList()).thenReturn((List) scenario.get(i).get(23));
//			when(mockedQuery15.getResultList()).thenReturn((List) scenario.get(i).get(24));
//			when(mockedQuery16.getResultList()).thenReturn((List) scenario.get(i).get(25));
//			when(mockedQuery17.getResultList()).thenReturn((List) scenario.get(i).get(26));
//			when(mockedQuery18.getResultList()).thenReturn((List) scenario.get(i).get(27));
//			when(mockedQuery19.getResultList()).thenReturn((List) scenario.get(i).get(28));
//			when(permitService.addPermit(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),Mockito.anyBoolean(),Mockito.anyString(),Mockito.anyString())).thenReturn("123");
//			Mockito.doReturn(new PermitConduitConductorSectionEntity()).when(copyProjectService2).clonePermitConduitConductorSectionEntity(
//					Mockito.any(),Mockito.any());
//			Mockito.doReturn(new ConduitConductorCircuitEntity()).when(copyProjectService2).cloneConduitConductorCircuitEntity(
//					Mockito.any(),Mockito.any());
//			Mockito.doReturn(new PermitProjectSiteInfoEntity()).when(copyProjectService2).clonePermitProjectSiteInfoEntity(
//					Mockito.any(),Mockito.any(),Mockito.anyBoolean());
//			Mockito.doReturn(new PermitArraysEntity()).when(copyProjectService2).clonePermitArraysEntity(
//					Mockito.any(),Mockito.any(),Mockito.anyBoolean());
//
//				copyProjectService2.copyPermit((String) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3),(String) scenario.get(i).get(4),(String) scenario.get(i).get(5),(String) scenario.get(i).get(6),(String) scenario.get(i).get(7),null,null);
//		}
//	}
//
//	@Test
//	public void testaddTemplatePermit() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from AuthentificationEntity u " + " where u.id = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("Select u  "
//				+ "From PermitEntity u "
//				+ "Where u.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitConduitConductorSectionEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from ConduitConductorCircuitEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitProjectSiteInfoEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
//
//		Query mockedQuery6 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from RailRackingFavLibraryEntity u " + " where u.railRacking.id = :p1 AND u.authentificationEntity.id = :p3"))
//				.thenReturn(mockedQuery6);
//		when(mockedQuery6.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery6);
//
//		Query mockedQuery7 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery7);
//		when(mockedQuery7.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery7);
//
//		Query mockedQuery8 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id = :p1"))
//				.thenReturn(mockedQuery8);
//		when(mockedQuery8.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery8);
//
//		Query mockedQuery9 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitArraysEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery9);
//		when(mockedQuery9.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery9);
//
//		Query mockedQuery10 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitAdditionalInfoEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery10);
//		when(mockedQuery10.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery10);
//
//		Query mockedQuery11 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitTotalSectionEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery11);
//		when(mockedQuery11.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery11);
//
//		Query mockedQuery12 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitCompanyInfoEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery12);
//		when(mockedQuery12.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery12);
//
//		Query mockedQuery13 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitHomeSiteInfoEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery13);
//		when(mockedQuery13.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery13);
//
//		Query mockedQuery14 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitWeatherEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery14);
//		when(mockedQuery14.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery14);
//
//		Query mockedQuery15 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitAdvEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery15);
//		when(mockedQuery15.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery15);
//
//		Query mockedQuery16 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitEngineerEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery16);
//		when(mockedQuery16.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery16);
//
//		Query mockedQuery17 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitDrafterDataEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery17);
//		when(mockedQuery17.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery17);
//
//		Query mockedQuery18 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitLayoutEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery18);
//		when(mockedQuery18.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery18);
//
//		Query mockedQuery19 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from PermitSketchEntity u " + " where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery19);
//		when(mockedQuery19.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery19);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		CopyProjectService copyProjectService2 = Mockito.spy(copyProjectService);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
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
//		// Result of the query17
//		scenario.get(0).add(null);
//		// Result of the query18
//		scenario.get(0).add(null);
//		// Result of the query19
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("It seems that there is a technical problem, please try again.");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
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
//		// Result of the query17
//		scenario.get(1).add(null);
//		// Result of the query18
//		scenario.get(1).add(null);
//		// Result of the query19
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("It seems that there is a technical problem, please try again.");
//
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("12345");
//		scenario.get(2).add(null);
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
//		// Result of the query17
//		scenario.get(2).add(null);
//		// Result of the query18
//		scenario.get(2).add(null);
//		// Result of the query19
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("It seems that there is a technical problem, please try again.");
//
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("");
//		scenario.get(3).add(null);
//		scenario.get(3).add("12345");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
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
//		// Result of the query17
//		scenario.get(3).add(null);
//		// Result of the query18
//		scenario.get(3).add(null);
//		// Result of the query19
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("It seems that there is a technical problem, please try again.");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("1235");
//		scenario.get(4).add(null);
//		scenario.get(4).add("12345");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
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
//		// Result of the query17
//		scenario.get(4).add(null);
//		// Result of the query18
//		scenario.get(4).add(null);
//		// Result of the query19
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("It seems that there is a technical problem, please try again.");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add("1235");
//		scenario.get(5).add(null);
//		scenario.get(5).add("12345");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(true);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		// Result of the query1
//		scenario.get(5).add(null);
//		// Result of the query2
//		PermitEntity pr = new PermitEntity();
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
//		// Result of the query17
//		scenario.get(5).add(null);
//		// Result of the query18
//		scenario.get(5).add(null);
//		// Result of the query19
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("It seems that there is a technical problem, please try again.");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(6).add("1235");
//		scenario.get(6).add(null);
//		scenario.get(6).add("12345");
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(true);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		// Result of the query1
//		scenario.get(6).add(null);
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
//		PermitArraysEntity prArray = new PermitArraysEntity();
//		scenario.get(6).add(prArray);
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
//		// Result of the query17
//		scenario.get(6).add(null);
//		// Result of the query18
//		scenario.get(6).add(null);
//		// Result of the query19
//		scenario.get(6).add(null);
//		// Result excpected
//		scenario.get(6).add("success");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(7).add("1235");
//		scenario.get(7).add(null);
//		scenario.get(7).add("12345");
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(true);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		// Result of the query1
//		scenario.get(7).add(null);
//		// Result of the query2
//		scenario.get(7).add(pr);
//		// Result of the query3
//		scenario.get(7).add(null);
//		// Result of the query4
//		scenario.get(7).add(null);
//		// Result of the query5
//		scenario.get(7).add(new PermitProjectSiteInfoEntity());
//		// Result of the query6
//		scenario.get(7).add(null);
//		// Result of the query7
//		scenario.get(7).add(null);
//		// Result of the query8
//		scenario.get(7).add(null);
//		// Result of the query9
//		scenario.get(7).add(prArray);
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
//		// Result of the query17
//		scenario.get(7).add(null);
//		// Result of the query18
//		scenario.get(7).add(null);
//		// Result of the query19
//		scenario.get(7).add(null);
//		// Result excpected
//		scenario.get(7).add("success");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addTemplatePermit [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(10));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(11));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(12));
//			when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(13));
//			when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(14));
//			when(mockedQuery6.getResultList()).thenReturn((List) scenario.get(i).get(15));
//			when(mockedQuery7.getResultList()).thenReturn((List) scenario.get(i).get(16));
//			when(mockedQuery8.getResultList()).thenReturn((List) scenario.get(i).get(17));
//			when(mockedQuery9.getSingleResult()).thenReturn(scenario.get(i).get(18));
//			when(mockedQuery10.getResultList()).thenReturn((List) scenario.get(i).get(19));
//			when(mockedQuery11.getResultList()).thenReturn((List) scenario.get(i).get(20));
//			when(mockedQuery12.getResultList()).thenReturn((List) scenario.get(i).get(21));
//			when(mockedQuery13.getResultList()).thenReturn((List) scenario.get(i).get(22));
//			when(mockedQuery14.getResultList()).thenReturn((List) scenario.get(i).get(23));
//			when(mockedQuery15.getResultList()).thenReturn((List) scenario.get(i).get(24));
//			when(mockedQuery16.getResultList()).thenReturn((List) scenario.get(i).get(25));
//			when(mockedQuery17.getResultList()).thenReturn((List) scenario.get(i).get(26));
//			when(mockedQuery18.getResultList()).thenReturn((List) scenario.get(i).get(27));
//			when(mockedQuery19.getResultList()).thenReturn((List) scenario.get(i).get(28));
//			when(permitService.addPermit(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),Mockito.anyBoolean(),Mockito.anyString(), Mockito.anyString())).thenReturn("123");
//			Mockito.doReturn(new PermitConduitConductorSectionEntity()).when(copyProjectService2).clonePermitConduitConductorSectionEntity(
//					Mockito.any(),Mockito.any());
//			Mockito.doReturn(new ConduitConductorCircuitEntity()).when(copyProjectService2).cloneConduitConductorCircuitEntity(
//					Mockito.any(),Mockito.any());
//			Mockito.doReturn(new PermitProjectSiteInfoEntity()).when(copyProjectService2).clonePermitProjectSiteInfoEntity(
//					Mockito.any(),Mockito.any(),Mockito.anyBoolean());
//			Mockito.doReturn(new PermitArraysEntity()).when(copyProjectService2).clonePermitArraysEntity(
//					Mockito.any(),Mockito.any(),Mockito.anyBoolean());
//
//
//					copyProjectService2.addTemplatePermit((String) scenario.get(i).get(0), (String) scenario.get(i).get(1), (String) scenario.get(i).get(2), (String) scenario.get(i).get(3), (String) scenario.get(i).get(4), null, null, "1", (String) scenario.get(i).get(6), (String) scenario.get(i).get(7), (String) scenario.get(i).get(9), (Boolean) scenario.get(i).get(5));
//		}
//
//	}
//
//
//	@Test
//	public void testclonePermitEntity() {
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new PermitEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		PermitEntity pr = new PermitEntity();
//		pr.setAdvancement("20");
//		pr.setStatus("unedr");
//		scenario.get(1).add(pr);
//		// Result excpected
//		PermitEntity prExp = new PermitEntity();
//		prExp.setAdvancement("20");
//		prExp.setStatus("unedr");
//		scenario.get(1).add(prExp);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("clonePermitTotalSectionEntity [ " + i + " ]");
//
//					copyProjectService.clonePermitEntity((PermitEntity) scenario.get(i).get(0));
//		}
//
//	}
//
//	@Test
//	public void testclonePermitAdditionalInfoEntity() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from BatteryFavLibraryEntity u WHERE u.battery.id = :p1 and u.authentificationEntity.id = :p2"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 "))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from Battery u WHERE u.id = :p1"))
//						.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		//The result of the Query1
//		scenario.get(0).add(null);
//		//The result of the Query2
//		scenario.get(0).add(null);
//		//The result of the Query3
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//		//The result list of the Query3
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		PermitAdditionalInfoEntity pr = new PermitAdditionalInfoEntity();
//		scenario.get(1).add(pr);
//		PermitAdditionalInfoEntity prCop = new PermitAdditionalInfoEntity();
//		prCop.setCostModules("helli junit");
//		prCop.setBatteryStorage(true);
//		prCop.setBattery("hhh");
//		scenario.get(1).add(prCop);
//		//The result of the Query1
//		scenario.get(1).add(null);
//		//The result of the Query2
//		scenario.get(1).add(null);
//		//The result of the Query3
//		scenario.get(1).add(null);
//		// Result excpected
//		PermitAdditionalInfoEntity exp1 = new PermitAdditionalInfoEntity();
//		exp1.setCostModules("helli junit");
//		exp1.setBatteryStorage(true);
//		exp1.setBattery("hhh");
//		scenario.get(1).add(exp1);
//		//The result list of the Query3
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(pr);
//		PermitAdditionalInfoEntity prCop2 = new PermitAdditionalInfoEntity();
//		prCop2.setCostModules("helli junit");
//		prCop2.setBatteryStorage(true);
//		prCop2.setBattery("123");
//		scenario.get(2).add(prCop2);
//		//The result of the Query1
//		scenario.get(2).add(null);
//		//The result of the Query2
//		scenario.get(2).add(null);
//		//The result of the Query3
//		scenario.get(2).add(null);
//		// Result excpected
//		PermitAdditionalInfoEntity exp2 = new PermitAdditionalInfoEntity();
//		exp2.setCostModules("helli junit");
//		exp2.setBatteryStorage(true);
//		exp2.setBattery("123");
//		scenario.get(2).add(exp2);
//		//The result list of the Query3
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		PermitAdditionalInfoEntity pr2 = new PermitAdditionalInfoEntity();
//		PermitEntity prEnt = new PermitEntity();
//		AuthentificationEntity  auth = new AuthentificationEntity();
//		prEnt.setAuthentificationEntity(auth);
//		pr2.setPermitEntity(prEnt);
//		scenario.get(3).add(pr2);
//		scenario.get(3).add(prCop2);
//		//The result of the Query1
//		scenario.get(3).add(new ArrayList<BatteryFavLibraryEntity>());
//		//The result of the Query2
//		scenario.get(3).add(null);
//		//The result of the Query3
//		scenario.get(3).add(null);
//		// Result excpected
//		PermitAdditionalInfoEntity prExp3 = new PermitAdditionalInfoEntity();
//		prExp3.setCostModules("helli junit");
//		prExp3.setBatteryStorage(true);
//		prExp3.setBattery("123");
//		prExp3.setPermitEntity(prEnt);
//		scenario.get(3).add(prExp3);
//		//The result list of the Query3
//		scenario.get(3).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(pr2);
//		scenario.get(4).add(prCop2);
//		//The result of the Query1
//		scenario.get(4).add(new ArrayList<BatteryFavLibraryEntity>());
//		//The result of the Query2
//		scenario.get(4).add(null);
//		//The result of the Query3
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(prExp3);
//		//The result list of the Query3
//		ArrayList<Battery> btList = new ArrayList<Battery>();
//		btList.add(null);
//		scenario.get(4).add(btList);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("clonePermitAdditionalInfoEntity [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(6));
//			PermitAdditionalInfoEntity rslt = copyProjectService.clonePermitAdditionalInfoEntity((PermitAdditionalInfoEntity) scenario.get(i).get(0),(PermitAdditionalInfoEntity) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testclonePermitCompanyInfoEntity() {
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new PermitCompanyInfoEntity());
//		scenario.get(1).add(new PermitCompanyInfoEntity());
//		// Result excpected
//		scenario.get(1).add(new PermitCompanyInfoEntity());
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("clonePermitAdditionalInfoEntity [ " + i + " ]");
//			PermitCompanyInfoEntity rslt = copyProjectService.clonePermitCompanyInfoEntity(
//					(PermitCompanyInfoEntity) scenario.get(i).get(0), (PermitCompanyInfoEntity) scenario.get(i).get(1));
//
//
//		}
//	}
//
//	@Test
//	public void testclonePermitHomeSiteInfoEntity() {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new PermitHomeSiteInfoEntity());
//		PermitHomeSiteInfoEntity pr = new PermitHomeSiteInfoEntity();
//		pr.setAddressLine2("the adress");
//		pr.setCity("CA");
//		scenario.get(1).add(pr);
//		// Result excpected
//		PermitHomeSiteInfoEntity prExp = new PermitHomeSiteInfoEntity();
//		prExp.setAddressLine2("the adress");
//		prExp.setCity("CA");
//		scenario.get(1).add(prExp);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("clonePermitHomeSiteInfoEntity [ " + i + " ]");
//			PermitHomeSiteInfoEntity rslt = copyProjectService.clonePermitHomeSiteInfoEntity(
//					(PermitHomeSiteInfoEntity) scenario.get(i).get(0), (PermitHomeSiteInfoEntity) scenario.get(i).get(1));
//
//
//		}
//	}
//
//
//	@Test
//	public void testclonePermitHomeSiteInfoEntityNotAll() {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new PermitHomeSiteInfoEntity());
//		PermitHomeSiteInfoEntity pr = new PermitHomeSiteInfoEntity();
//		pr.setHomePhone("the adress");
//		pr.setPropertyAPN("CA");
//		scenario.get(1).add(pr);
//		// Result excpected
//		PermitHomeSiteInfoEntity prExp = new PermitHomeSiteInfoEntity();
//		prExp.setHomePhone("the adress");
//		prExp.setPropertyAPN("CA");
//		scenario.get(1).add(prExp);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("clonePermitHomeSiteInfoEntity [ " + i + " ]");
//			PermitHomeSiteInfoEntity rslt = copyProjectService.clonePermitHomeSiteInfoEntityNotAll(
//					(PermitHomeSiteInfoEntity) scenario.get(i).get(0), (PermitHomeSiteInfoEntity) scenario.get(i).get(1));
//
//
//		}
//	}
//
//
//	@Test
//	public void testclonePermitProjectSiteInfoEntity() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from FlashingFavLibraryEntity u WHERE u.flashing.id = :p1 and u.authentificationEntity.id = :p2")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from Flashing u WHERE u.id = :p1")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u from LeasePPAMeterFavLibraryEntity u WHERE u.leasePPAMeter.id = :p1 and u.authentificationEntity.id = :p2")).thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT u from LeasePPAMeter u WHERE u.id = :p1")).thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		//The result of the Query1
//		scenario.get(0).add(null);
//		//The result of the Query2
//		scenario.get(0).add(null);
//		//The result of the Query3
//		scenario.get(0).add(null);
//		//The result of the Query4
//		scenario.get(0).add(null);
//		//The result of the Query5
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//		//The list of result Query3
//		scenario.get(0).add(null);
//		//the list of result of Query5
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new PermitProjectSiteInfoEntity());
//		scenario.get(1).add(new PermitProjectSiteInfoEntity());
//		scenario.get(1).add(null);
//		//The result of the Query1
//		scenario.get(1).add(null);
//		//The result of the Query2
//		scenario.get(1).add(null);
//		//The result of the Query3
//		scenario.get(1).add(null);
//		//The result of the Query4
//		scenario.get(1).add(null);
//		//The result of the Query5
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new PermitProjectSiteInfoEntity());
//		//The list of result Query3
//		scenario.get(1).add(null);
//		//the list of result of Query5
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		PermitProjectSiteInfoEntity prOld = new PermitProjectSiteInfoEntity();
//		PermitEntity pr = new PermitEntity();
//		pr.setAuthentificationEntity(new AuthentificationEntity());
//		prOld.setPermitEntity(pr);
//		scenario.get(2).add(prOld);
//		PermitProjectSiteInfoEntity prNew = new PermitProjectSiteInfoEntity();
//		prNew.setFlashing("123");
//		prNew.setLeasePPAMeter("123");
//		scenario.get(2).add(prNew);
//		scenario.get(2).add(null);
//		//The result of the Query1
//		ArrayList<FlashingFavLibraryEntity> list = new ArrayList<FlashingFavLibraryEntity>();
//		list.add(null);
//		scenario.get(2).add(list);
//		//The result of the Query2
//		scenario.get(2).add(null);
//		//The result of the Query3
//		scenario.get(2).add(null);
//		//The result of the Query4
//		ArrayList<LeasePPAMeterFavLibraryEntity> list2 = new ArrayList<LeasePPAMeterFavLibraryEntity>();
//		list.add(null);
//		scenario.get(2).add(list2);
//		//The result of the Query5
//		scenario.get(2).add(null);
//		// Result excpected
//		PermitProjectSiteInfoEntity exp = new PermitProjectSiteInfoEntity();
//		exp.setFlashing("123");
//		exp.setLeasePPAMeter("123");
//		exp.setPermitEntity(pr);
//		scenario.get(2).add(exp);
//		//The list of result Query3
//		scenario.get(2).add(null);
//		//the list of result of Query5
//		scenario.get(2).add(null);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("clonePermitProjectSiteInfoEntity [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(6));
//			when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(9));
//			when(mockedQuery5.getResultList()).thenReturn((List) scenario.get(i).get(10));
//
//			PermitProjectSiteInfoEntity rslt = copyProjectService.clonePermitProjectSiteInfoEntity(
//					(PermitProjectSiteInfoEntity) scenario.get(i).get(0), (PermitProjectSiteInfoEntity) scenario.get(i).get(1), (Boolean) scenario.get(i).get(2));
//
//
//		}
//	}
//
//	@Test
//	public void testclonePermitWeatherEntity() {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new PermitWeatherEntity());
//		PermitWeatherEntity pr = new PermitWeatherEntity();
//		pr.setExtremeMinimumOther("the adress");
//		pr.setQuatrePourCentAverageHigh("CA");
//		scenario.get(1).add(pr);
//		// Result excpected
//		PermitWeatherEntity prExp = new PermitWeatherEntity();
//		prExp.setExtremeMinimumOther("the adress");
//		prExp.setQuatrePourCentAverageHigh("CA");
//		scenario.get(1).add(prExp);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("clonePermitWeatherEntity [ " + i + " ]");
//			PermitWeatherEntity rslt = copyProjectService.clonePermitWeatherEntity(
//					(PermitWeatherEntity) scenario.get(i).get(0), (PermitWeatherEntity) scenario.get(i).get(1));
//
//
//		}
//	}
//
//	@Test
//	public void testclonePermitArraysEntity() {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new PermitArraysEntity());
//		PermitArraysEntity pr = new PermitArraysEntity();
//		pr.setSystemType("the adress");
//		pr.setInverterLocation("CA");
//		pr.setSystemType("hello");
//		scenario.get(1).add(pr);
//		scenario.get(1).add(false);
//		// Result excpected
//		PermitArraysEntity prExp = new PermitArraysEntity();
//		prExp.setSystemType("the adress");
//		prExp.setInverterLocation("CA");
//		prExp.setSystemType("hello");
//		scenario.get(1).add(prExp);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("clonePermitArraysEntity [ " + i + " ]");
//			PermitArraysEntity rslt = copyProjectService.clonePermitArraysEntity(
//					(PermitArraysEntity) scenario.get(i).get(0), (PermitArraysEntity) scenario.get(i).get(1),(Boolean) scenario.get(i).get(2));
//
//
//		}
//	}
//
//
//	@Test
//	public void testclonePermitAdvEntity() {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new PermitAdvEntity());
//		PermitAdvEntity pr = new PermitAdvEntity();
//		pr.setS200Qte("the adress");
//		pr.setPv1("CA");
//		scenario.get(1).add(pr);
//		// Result excpected
//		PermitAdvEntity prExp = new PermitAdvEntity();
//		prExp.setS200Qte("the adress");
//		prExp.setPv1("CA");
//		scenario.get(1).add(prExp);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("clonePermitAdvEntity [ " + i + " ]");
//			PermitAdvEntity rslt = copyProjectService.clonePermitAdvEntity(
//					(PermitAdvEntity) scenario.get(i).get(0), (PermitAdvEntity) scenario.get(i).get(1));
//
//		}
//	}
//
//
//	@Test
//	public void testclonePermitEngineerEntity() {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new PermitEngineerEntity());
//		PermitEngineerEntity pr = new PermitEngineerEntity();
//		pr.setCodePostale("the adress");
//		pr.setCity("CA");
//		scenario.get(1).add(pr);
//		// Result excpected
//		PermitEngineerEntity prExp = new PermitEngineerEntity();
//		prExp.setCodePostale("the adress");
//		prExp.setCity("CA");
//		scenario.get(1).add(prExp);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("clonePermitEngineerEntity [ " + i + " ]");
//			PermitEngineerEntity rslt = copyProjectService.clonePermitEngineerEntity(
//					(PermitEngineerEntity) scenario.get(i).get(0), (PermitEngineerEntity) scenario.get(i).get(1));
//
//
//		}
//	}
//
//	@Test
//	public void testclonePermitConduitConductorSectionEntity() {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new PermitConduitConductorSectionEntity());
//		PermitConduitConductorSectionEntity pr = new PermitConduitConductorSectionEntity();
//		pr.setConductorSizeTwoOther("the adress");
//		pr.setConduitTypeTwelveOther("CA");
//		scenario.get(1).add(pr);
//		// Result excpected
//		PermitConduitConductorSectionEntity prExp = new PermitConduitConductorSectionEntity();
//		prExp.setConductorSizeTwoOther("the adress");
//		prExp.setConduitTypeTwelveOther("CA");
//		scenario.get(1).add(prExp);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("clonePermitConduitConductorSectionEntity [ " + i + " ]");
//			PermitConduitConductorSectionEntity rslt = copyProjectService.clonePermitConduitConductorSectionEntity(
//					(PermitConduitConductorSectionEntity) scenario.get(i).get(0), (PermitConduitConductorSectionEntity) scenario.get(i).get(1));
//
//		}
//	}
//
//	@Test
//	public void testclonePermitDrafterDataEntity() {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new PermitDrafterDataEntity());
//		PermitDrafterDataEntity pr = new PermitDrafterDataEntity();
//		pr.setScaleelectricalLayout("the adress");
//		pr.setScale("CA");
//		scenario.get(1).add(pr);
//		// Result excpected
//		PermitDrafterDataEntity prExp = new PermitDrafterDataEntity();
//		prExp.setScaleelectricalLayout("the adress");
//		prExp.setScale("CA");
//		scenario.get(1).add(prExp);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("clonePermitDrafterDataEntity [ " + i + " ]");
//			PermitDrafterDataEntity rslt = copyProjectService.clonePermitDrafterDataEntity(
//					(PermitDrafterDataEntity) scenario.get(i).get(0), (PermitDrafterDataEntity) scenario.get(i).get(1));
//
//
//		}
//	}
//
//	@Test
//	public void testclonePermitLayoutEntity() {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new PermitLayoutEntity());
//		PermitLayoutEntity pr = new PermitLayoutEntity();
//		pr.setConduitRun("the adress");
//		scenario.get(1).add(pr);
//		// Result excpected
//		PermitLayoutEntity prExp = new PermitLayoutEntity();
//		prExp.setConduitRun("the adress");
//		scenario.get(1).add(prExp);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("clonePermitDrafterDataEntity [ " + i + " ]");
//			PermitLayoutEntity rslt = copyProjectService.clonePermitLayoutEntity(
//					(PermitLayoutEntity) scenario.get(i).get(0), (PermitLayoutEntity) scenario.get(i).get(1));
//
//
//		}
//	}
//
//	@Test
//	public void testcloneConduitConductorCircuitEntity() {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new ConduitConductorCircuitEntity());
//		ConduitConductorCircuitEntity pr = new ConduitConductorCircuitEntity();
//		pr.setConduitTypeOther("the adress");
//		scenario.get(1).add(pr);
//		// Result excpected
//		ConduitConductorCircuitEntity prExp = new ConduitConductorCircuitEntity();
//		prExp.setConduitTypeOther("the adress");
//		scenario.get(1).add(prExp);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("cloneConduitConductorCircuitEntity [ " + i + " ]");
//			ConduitConductorCircuitEntity rslt = copyProjectService.cloneConduitConductorCircuitEntity(
//					(ConduitConductorCircuitEntity) scenario.get(i).get(0), (ConduitConductorCircuitEntity) scenario.get(i).get(1));
//
//
//		}
//	}
//
//
//	@Test
//	public void testclonePermitSketchEntity() {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new PermitSketchEntity());
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		PermitSketchEntity pr = new PermitSketchEntity();
//		pr.setEaveOverHang("the adress");
//		scenario.get(1).add(pr);
//		// Result excpected
//		PermitSketchEntity prExp = new PermitSketchEntity();
//		prExp.setEaveOverHang("the adress");
//		scenario.get(1).add(prExp);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("clonePermitSketchEntity [ " + i + " ]");
//			PermitSketchEntity rslt = copyProjectService.clonePermitSketchEntity(
//					(PermitSketchEntity) scenario.get(i).get(0));
//
//
//		}
//	}
//
//	@Test
//	public void testclonePermitTotalSectionEntity2() {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new PermitTotalSectionEntity());
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		PermitTotalSectionEntity pr = new PermitTotalSectionEntity();
//		pr.setSection(123);
//		scenario.get(1).add(pr);
//		// Result excpected
//		PermitTotalSectionEntity prExp = new PermitTotalSectionEntity();
//		prExp.setSection(123);
//		scenario.get(1).add(prExp);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("clonePermitTotalSectionEntity [ " + i + " ]");
//			PermitTotalSectionEntity rslt = copyProjectService.clonePermitTotalSectionEntity(
//					(PermitTotalSectionEntity) scenario.get(i).get(0));
//
//		}
//	}
//}
