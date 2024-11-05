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
//import com.PlayGroundAdv.Solar.Entity.InterconnectionsEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.ProjectsTrackerEntity;
//import com.PlayGroundAdv.Solar.model.InterconnectionModel;
//import com.PlayGroundAdv.Solar.model.InterconnectionRequest;
//import com.PlayGroundAdv.Solar.model.ProjectTrackerModel;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.ProjectTrackerService;
//import com.PlayGroundAdv.Solar.Services.TemplateService;
//
//public class TestProjectTrackerService {
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
//	ProjectTrackerService projectTrackerService = new ProjectTrackerService();
//	
//	
//    @Before
//	public void setupMock() {
//    	projectTrackerService = new ProjectTrackerService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testgetprojectTrackerDashboard() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from ProjectsTrackerEntity u WHERE (u.permit.status =:p1 OR u.permit.status =:p2 OR u.permit.status =:p3 OR u.permit.status =:p4) AND u.permit.isDeleted =:p5 ORDER BY u.permit.creationPermitDate DESC "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<ProjectTrackerModel>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<ProjectTrackerModel>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<ProjectTrackerModel>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		ArrayList<ProjectsTrackerEntity> listQ1 = new ArrayList<ProjectsTrackerEntity>();
//		listQ1.add(null);
//		listQ1.add(new ProjectsTrackerEntity());
//		scenario.get(3).add(listQ1);
//		// Result excpected
//		ArrayList<ProjectTrackerModel> listExp = new ArrayList<ProjectTrackerModel>();
//		ProjectTrackerModel pr = new ProjectTrackerModel();
//		pr.setCurrentPM("");
//		pr.setProjectEditStarted("");
//		pr.setProjectEditCompleted("");
//		pr.setSubmit("");
//		pr.setSubmitBy("");
//		pr.setSubmitADVRFI("");
//		pr.setSubmitContRFI("");
//		pr.setRequestRevision("");
//		pr.setReopenProject("");
//		pr.setDelivered("");
//		pr.setDrafterDataEditStarted("");
//		pr.setDrafterDataEditCompleted("");
//		pr.setDownloadDrafter("");
//		pr.setDrafter("");
//		pr.setAdvInputsEditStarted("");
//		pr.setAdvInputsEditCompleted("");
//		pr.setAdvTeamMember("");
//		pr.setTimeLine("");
//		pr.setTimeLineLessRfi("");
//		listExp.add(pr);
//		scenario.get(3).add(listExp);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("123");
//		// Result of the query1
//		ArrayList<ProjectsTrackerEntity> listQ11 = new ArrayList<ProjectsTrackerEntity>();
//		listQ11.add(null);
//		ProjectsTrackerEntity prTra = new ProjectsTrackerEntity();
//		PermitEntity pE = new PermitEntity();
//		pE.setStatus("Delivered");
//		prTra.setPermit(pE);
//		prTra.setProjectManager("hello");
//		listQ11.add(prTra);
//		scenario.get(4).add(listQ11);
//		// Result excpected
//		ArrayList<ProjectTrackerModel> listExp2 = new ArrayList<ProjectTrackerModel>();
//		ProjectTrackerModel pr2 = new ProjectTrackerModel();
//		pr2.setCurrentPM("hello");
//		pr2.setStatus("<span class='label bg-color-darken'>Delivered</span>");
//		pr2.setProjectEditStarted("");
//		pr2.setProjectEditCompleted("");
//		pr2.setSubmit("");
//		pr2.setSubmitBy("");
//		pr2.setSubmitADVRFI("");
//		pr2.setSubmitContRFI("");
//		pr2.setRequestRevision("");
//		pr2.setReopenProject("");
//		pr2.setDelivered("");
//		pr2.setDrafterDataEditStarted("");
//		pr2.setDrafterDataEditCompleted("");
//		pr2.setDownloadDrafter("");
//		pr2.setDrafter("");
//		pr2.setAdvInputsEditStarted("");
//		pr2.setAdvInputsEditCompleted("");
//		pr2.setAdvTeamMember("");
//		pr2.setTimeLine("");
//		pr2.setTimeLineLessRfi("");
//		listExp2.add(pr2);
//		scenario.get(4).add(listExp2);
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getprojectTrackerDashboard [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			List<ProjectTrackerModel> rslt = (ArrayList<ProjectTrackerModel>) projectTrackerService.getprojectTrackerDashboard();
//
//		}
//	}
//
//	@Test
//	public void testgetTrackerDashboardContractor() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from ProjectsTrackerEntity u WHERE (u.permit.status =:p1 OR u.permit.status =:p2 OR u.permit.status =:p3 OR u.permit.status =:p4) AND u.permit.isDeleted =:p5 AND u.permit.authentificationEntity.id =:p6 ORDER BY u.permit.creationPermitDate DESC "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<ProjectTrackerModel>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<ProjectTrackerModel>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<ProjectTrackerModel>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		ArrayList<ProjectsTrackerEntity> listQ1 = new ArrayList<ProjectsTrackerEntity>();
//		listQ1.add(null);
//		listQ1.add(new ProjectsTrackerEntity());
//		scenario.get(3).add(listQ1);
//		// Result excpected
//		ArrayList<ProjectTrackerModel> listExp = new ArrayList<ProjectTrackerModel>();
//		ProjectTrackerModel pr = new ProjectTrackerModel();
//		pr.setCurrentPM("");
//		pr.setProjectEditStarted("");
//		pr.setProjectEditCompleted("");
//		pr.setSubmit("");
//		pr.setSubmitBy("");
//		pr.setSubmitADVRFI("");
//		pr.setSubmitContRFI("");
//		pr.setRequestRevision("");
//		pr.setReopenProject("");
//		pr.setDelivered("");
//		pr.setDrafterDataEditStarted("");
//		pr.setDrafterDataEditCompleted("");
//		pr.setDownloadDrafter("");
//		pr.setDrafter("");
//		pr.setAdvInputsEditStarted("");
//		pr.setAdvInputsEditCompleted("");
//		pr.setAdvTeamMember("");
//		pr.setTimeLine("");
//		pr.setTimeLineLessRfi("");
//		listExp.add(pr);
//		scenario.get(3).add(listExp);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("123");
//		// Result of the query1
//		ArrayList<ProjectsTrackerEntity> listQ11 = new ArrayList<ProjectsTrackerEntity>();
//		listQ11.add(null);
//		ProjectsTrackerEntity prTra = new ProjectsTrackerEntity();
//		PermitEntity pE = new PermitEntity();
//		pE.setStatus("Delivered");
//		prTra.setPermit(pE);
//		prTra.setProjectManager("hello");
//		listQ11.add(prTra);
//		scenario.get(4).add(listQ11);
//		// Result excpected
//		ArrayList<ProjectTrackerModel> listExp2 = new ArrayList<ProjectTrackerModel>();
//		ProjectTrackerModel pr2 = new ProjectTrackerModel();
//		pr2.setCurrentPM("hello");
//		pr2.setStatus("<span class='label bg-color-darken'>Delivered</span>");
//		pr2.setProjectEditStarted("");
//		pr2.setProjectEditCompleted("");
//		pr2.setSubmit("");
//		pr2.setSubmitBy("");
//		pr2.setSubmitADVRFI("");
//		pr2.setSubmitContRFI("");
//		pr2.setRequestRevision("");
//		pr2.setReopenProject("");
//		pr2.setDelivered("");
//		pr2.setDrafterDataEditStarted("");
//		pr2.setDrafterDataEditCompleted("");
//		pr2.setDownloadDrafter("");
//		pr2.setDrafter("");
//		pr2.setAdvInputsEditStarted("");
//		pr2.setAdvInputsEditCompleted("");
//		pr2.setAdvTeamMember("");
//		pr2.setTimeLine("");
//		pr2.setTimeLineLessRfi("");
//		listExp2.add(pr2);
//		scenario.get(4).add(listExp2);
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getTrackerDashboardContractor [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			List<ProjectTrackerModel> rslt = (ArrayList<ProjectTrackerModel>) projectTrackerService.getTrackerDashboardContractor((String)scenario.get(i).get(0));
//
//		}
//	}
//	
//	@Test
//	public void testgetprojectInterconection() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.InterconnectionRequest  (u.id, u.homeOwnName, u.authentificationEntity.firstName, u.authentificationEntity.company, w.utilityCompanyName, v.applicationType, w.phone, w.utilityNumber) "
//				+ " FROM  PermitEntity u, PermitCompanyInfoEntity v, ElectricalUtilityEntity w, PermitHomeSiteInfoEntity y WHERE y.permitEntity.id = u.id AND STR(w.id)=y.UtilityCompanyName AND v.permitEntity.id = u.id AND u.isDeleted =:p1 ORDER BY u.creationPermitDate DESC "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from InterconnectionsEntity u "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<InterconnectionModel>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		List<InterconnectionRequest> list = new ArrayList<InterconnectionRequest>();
//		list.add(null);
//		list.add(new InterconnectionRequest());
//		scenario.get(1).add(list);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		ArrayList<InterconnectionModel> listExp = new ArrayList<InterconnectionModel>();
//		listExp.add(new InterconnectionModel());
//		scenario.get(1).add(listExp);
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(2).add(list);
//		// Result of the query2
//		ArrayList<InterconnectionsEntity> list2 = new ArrayList<InterconnectionsEntity>();
//		list2.add(null);
//		list2.add(new InterconnectionsEntity());
//		scenario.get(2).add(list2);
//		// Result excpected
//		scenario.get(2).add(listExp);
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<InterconnectionRequest> listQ1 = new ArrayList<InterconnectionRequest>();
//		InterconnectionRequest inter1 = new InterconnectionRequest();
//		inter1.setId(123);
//		inter1.setName("hello");
//		listQ1.add(inter1);
//		InterconnectionRequest inter2 = new InterconnectionRequest();
//		inter2.setId(589);
//		inter2.setName("hello word");
//		listQ1.add(inter2);
//		scenario.get(3).add(listQ1);
//		// Result of the query2
//		ArrayList<InterconnectionsEntity> listQ2 = new ArrayList<InterconnectionsEntity>();
//		InterconnectionsEntity interEn = new InterconnectionsEntity();
//		PermitEntity per = new PermitEntity();
//		per.setId(123);
//		interEn.setPermit(per);
//		interEn.setContactClient("Test Junit");
//		listQ2.add(interEn);
//		scenario.get(3).add(listQ2);
//		// Result excpected
//		List<InterconnectionModel> listExp2 = new ArrayList<InterconnectionModel>();
//		InterconnectionModel exp1 = new InterconnectionModel();
//		exp1.setId(123);
//		exp1.setName("hello");
//		exp1.setContactClient("Test Junit");
//		listExp2.add(exp1);
//		InterconnectionModel exp2 = new InterconnectionModel();
//		exp2.setId(589);
//		exp2.setName("hello word");
//		listExp2.add(exp2);
//		scenario.get(3).add(listExp2);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getprojectInterconection [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			List<InterconnectionModel> rslt = (ArrayList<InterconnectionModel>) projectTrackerService.getprojectInterconection();
//
//		}
//	}
//	
//	@Test
//	public void testsaveInterconection() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from InterconnectionsEntity u WHERE u.permit.id =:p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u WHERE u.id =:p1"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//		//Result List of the Query 1
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(new InterconnectionModel());
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("success");
//		//Result List of the Query 1
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(new InterconnectionModel());
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//		//Result List of the Query 1
//		ArrayList<InterconnectionsEntity> list = new ArrayList<InterconnectionsEntity>();
//		list.add(null);
//		scenario.get(2).add(list);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(new InterconnectionModel());
//		// Result of the query1
//		scenario.get(3).add(new InterconnectionsEntity());
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("success");
//		//Result List of the Query 1
//		scenario.get(3).add(list);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getPermits [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			
//			 projectTrackerService.saveInterconection((InterconnectionModel) scenario.get(i).get(0));
//
//		}
//	}
//	
//}
