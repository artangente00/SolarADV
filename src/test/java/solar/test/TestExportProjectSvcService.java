//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//
//import com.PlayGroundAdv.Solar.Entity.PermitArraysEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
//import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
//import com.PlayGroundAdv.Solar.model.PermitArraysEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResultPrime;
//import com.PlayGroundAdv.Solar.model.PermitConduitConductorSectionEntitieResult;
//import com.PlayGroundAdv.Solar.model.PermitDrafterDataResult;
//import com.PlayGroundAdv.Solar.model.PermitEditStatusModel;
//import com.PlayGroundAdv.Solar.model.PermitEngineerEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitLayoutSketchResult;
//import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
//import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.ExportProjectSvcService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.PermitService;
//import com.PlayGroundAdv.Solar.Services.UserInformationService;
//
//public class TestExportProjectSvcService {
//	
//	@Mock
//	EntityManager em;
//	
//	@Mock
////	private Query query;
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
//	ExportProjectSvcService exportProjectSvcService = new ExportProjectSvcService();
//	
//	
//    @Before
//	public void setupMock() {
//    	exportProjectSvcService = new ExportProjectSvcService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testgenerateProjectScv() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.RRVersion  from PermitEntity u  where u.id = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u.downDir from PathEntity u where u.id=1"))
//		       .thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//	
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(0).add(null);
//		//The result of the query1
//		scenario.get(0).add(null);
//		//The result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//		//Result expected
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(1).add("");
//		//The result of the query1
//		scenario.get(1).add(null);
//		//The result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//		//Result expected
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(2).add("1235");
//		//The result of the query1
//		scenario.get(2).add(null);
//		//The result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//		//Result expected
//		scenario.get(2).add(new GetPermitByIdResult());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(3).add("1235");
//		//The result of the query1
//		scenario.get(3).add(null);
//		//The result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("C:/files/20807551/20807551.xls");
//		//Result expected
//		GetPermitByIdResult per2 = new GetPermitByIdResult();
//		PermitEntity ent2 = new PermitEntity();
//		ent2.setRRVersion(1);
//		per2.setPermitEntity(ent2);
//		per2.setPermitAdditionalInfoEntity(new PermitAdditionalInfoEntityResult());
//		per2.setPermitArraysEntityResult(new PermitArraysEntityResult());
//		per2.setPermitCompanyInfoEntityResult(new PermitCompanyInfoEntityResultPrime());
//		per2.setPermitHomeSiteEntityResult(new PermitHomeSiteEntityResult());
//		per2.setPermitProjectSiteInfoEntityTwo(new PermitProjectSiteInfoEntityTwo());
//		per2.setPermtiWeatherEntityResult(new PermtiWeatherEntityResult());
//		per2.setPermitArraysEntity(new PermitArrayEntityResultSecond());
//		per2.setPermitAdvEntityResult(new PermitAdvEntityResult());
//		per2.setPermitEngineerEntityResult(new PermitEngineerEntityResult());
//		per2.setPermitConduitConductorSection(new PermitConduitConductorSectionEntitieResult());
//		per2.setPermitDrafterData(new PermitDrafterDataResult());
//		per2.setLayoutSketch(new PermitLayoutSketchResult());
//		per2.setPermitEditStatusModel(new PermitEditStatusModel());
//		scenario.get(3).add(per2);
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("generateProjectScv [ "+i+" ]");
//			when(permitService.getPermitById(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn((GetPermitByIdResult)scenario.get(i).get(4));
//
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			exportProjectSvcService.generateProjectScv((String)scenario.get(i).get(0));
//
//		}
//	}
//
//	@Test
//	public void testconvert() {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(1).add("null");
//		// Result excpected
//		scenario.get(1).add("");
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("convert [ "+i+" ]");
//			 exportProjectSvcService.convert((String)scenario.get(i).get(0));
//		}
//		
//	}
//
//	@Test
//	public void testhomeOwnerMap() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u"
//				+ " from CityEntity u "
//				+ " where u.name = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(0).add(null);
//		//The result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(1).add(new GetPermitByIdResult());
//		//The result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per = new GetPermitByIdResult();
//		per.setPermitHomeSiteEntityResult(new PermitHomeSiteEntityResult());
//		per.setPermitEntity(new PermitEntity());
//		scenario.get(2).add(per);
//		//The result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new LinkedHashMap<String, String[]>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per1 = new GetPermitByIdResult();
//		per1.setPermitHomeSiteEntityResult(new PermitHomeSiteEntityResult());
//		per1.setPermitEntity(new PermitEntity());
//		per1.setPermitCompanyInfoEntityResult(new PermitCompanyInfoEntityResultPrime());
//		scenario.get(3).add(per1);
//		//The result of the query1
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new LinkedHashMap<String, String[]>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per2 = new GetPermitByIdResult();
//		per2.setPermitHomeSiteEntityResult(new PermitHomeSiteEntityResult());
//		per2.setPermitEntity(new PermitEntity());
//		per2.setPermitCompanyInfoEntityResult(new PermitCompanyInfoEntityResultPrime());
//		per2.setPermitArraysEntity(new PermitArrayEntityResultSecond());
//		scenario.get(4).add(per2);
//		//The result of the query1
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(new LinkedHashMap<String, String[]>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per3 = new GetPermitByIdResult();
//		per3.setPermitHomeSiteEntityResult(new PermitHomeSiteEntityResult());
//		per3.setPermitEntity(new PermitEntity());
//		per3.setPermitCompanyInfoEntityResult(new PermitCompanyInfoEntityResultPrime());
//		per3.setPermitArraysEntity(new PermitArrayEntityResultSecond());
//		per3.setPermitProjectSiteInfoEntityTwo(new PermitProjectSiteInfoEntityTwo());
//		scenario.get(5).add(per3);
//		//The result of the query1
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add(new LinkedHashMap<String, String[]>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per4 = new GetPermitByIdResult();
//		per4.setPermitHomeSiteEntityResult(new PermitHomeSiteEntityResult());
//		per4.setPermitEntity(new PermitEntity());
//		per4.setPermitCompanyInfoEntityResult(new PermitCompanyInfoEntityResultPrime());
//		per4.setPermitArraysEntity(new PermitArrayEntityResultSecond());
//		per4.setPermitProjectSiteInfoEntityTwo(new PermitProjectSiteInfoEntityTwo());
//		per4.setPermitEngineerEntityResult(new PermitEngineerEntityResult());
//		scenario.get(6).add(per4);
//		//The result of the query1
//		scenario.get(6).add(null);
//		// Result excpected
//		scenario.get(6).add(new LinkedHashMap<String, String[]>());
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("homeOwnerMap [ "+i+" ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			exportProjectSvcService.homeOwnerMap((GetPermitByIdResult)scenario.get(i).get(0));
//		}
//		
//	}
//	
//	@Test
//	public void testarraysMap() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u"
//				+ " from CityEntity u "
//				+ " where u.name = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(0).add(null);
//		//The result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(1).add(new GetPermitByIdResult());
//		//The result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new LinkedHashMap<String, String[]>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per = new GetPermitByIdResult();
//		per.setPermitArraysEntity(new PermitArrayEntityResultSecond());
//		scenario.get(2).add(per);
//		//The result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new LinkedHashMap<String, String[]>());
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("arraysMap [ "+i+" ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			exportProjectSvcService.arraysMap((GetPermitByIdResult)scenario.get(i).get(0));
//		}
//	}
//	
//	@Test
//	public void testbalanceOfSystem() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u"
//				+ " from CityEntity u "
//				+ " where u.name = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(0).add(null);
//		//The result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(1).add(new GetPermitByIdResult());
//		//The result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new LinkedHashMap<String, String[]>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per = new GetPermitByIdResult();
//		per.setPermitProjectSiteInfoEntityTwo(new PermitProjectSiteInfoEntityTwo());
//		scenario.get(2).add(per);
//		//The result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per1 = new GetPermitByIdResult();
//		per1.setPermitProjectSiteInfoEntityTwo(new PermitProjectSiteInfoEntityTwo());
//		per1.setPermitArraysEntity(new PermitArrayEntityResultSecond());
//		scenario.get(3).add(per1);
//		//The result of the query1
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per2 = new GetPermitByIdResult();
//		per2.setPermitProjectSiteInfoEntityTwo(new PermitProjectSiteInfoEntityTwo());
//		per2.setPermitArraysEntity(new PermitArrayEntityResultSecond());
//		per2.setPermitAdvEntityResult(new PermitAdvEntityResult());
//		scenario.get(4).add(per2);
//		//The result of the query1
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(new LinkedHashMap<String, String[]>());
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("balanceOfSystem [ "+i+" ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			exportProjectSvcService.balanceOfSystem((GetPermitByIdResult)scenario.get(i).get(0));
//		}
//	}
//	
//	@Test
//	public void testconduitContractorSection() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u"
//				+ " from CityEntity u "
//				+ " where u.name = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(0).add(null);
//		//The result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(1).add(new GetPermitByIdResult());
//		//The result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new LinkedHashMap<String, String[]>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per = new GetPermitByIdResult();
//		per.setPermitConduitConductorSection(new PermitConduitConductorSectionEntitieResult());
//		scenario.get(2).add(per);
//		//The result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new LinkedHashMap<String, String[]>());
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("conduitContractorSection [ "+i+" ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			exportProjectSvcService.conduitContractorSection((GetPermitByIdResult)scenario.get(i).get(0));
//		}
//	}
//	
//	@Test
//	public void testadditionalInfo() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u"
//				+ " from CityEntity u "
//				+ " where u.name = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(0).add(null);
//		//The result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(1).add(new GetPermitByIdResult());
//		//The result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new LinkedHashMap<String, String[]>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per = new GetPermitByIdResult();
//		per.setPermitAdditionalInfoEntity(new PermitAdditionalInfoEntityResult());
//		scenario.get(2).add(per);
//		//The result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per1 = new GetPermitByIdResult();
//		per1.setPermitAdditionalInfoEntity(new PermitAdditionalInfoEntityResult());
//		per1.setPermitProjectSiteInfoEntityTwo(new PermitProjectSiteInfoEntityTwo());
//		scenario.get(3).add(per1);
//		//The result of the query1
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per2 = new GetPermitByIdResult();
//		per2.setPermitAdditionalInfoEntity(new PermitAdditionalInfoEntityResult());
//		per2.setPermitProjectSiteInfoEntityTwo(new PermitProjectSiteInfoEntityTwo());
//		per2.setPermitEngineerEntityResult(new PermitEngineerEntityResult());
//		scenario.get(4).add(per2);
//		//The result of the query1
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(new LinkedHashMap<String, String[]>());
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("additionalInfo [ "+i+" ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			exportProjectSvcService.additionalInfo((GetPermitByIdResult)scenario.get(i).get(0));
//		}
//	}
//	
//	@Test
//	public void testlayoutSketch() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				   + "FROM PermitSketchEntity u "
//				   + "WHERE u.permitEntity.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(0).add(null);
//		//The result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(1).add(new GetPermitByIdResult());
//		//The result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new LinkedHashMap<String, String[]>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per = new GetPermitByIdResult();
//		per.setPermitEntity(new PermitEntity());
//		scenario.get(2).add(per);
//		//The result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per1 = new GetPermitByIdResult();
//		per1.setPermitEntity(new PermitEntity());
//		per1.setPermitArraysEntity(new PermitArrayEntityResultSecond());
//		scenario.get(3).add(per1);
//		//The result of the query1
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per2 = new GetPermitByIdResult();
//		per2.setPermitEntity(new PermitEntity());
//		per2.setPermitArraysEntity(new PermitArrayEntityResultSecond());
//		per2.setLayoutSketch(new PermitLayoutSketchResult());
//		scenario.get(4).add(per2);
//		//The result of the query1
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(new LinkedHashMap<String, String[]>());
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("layoutSketch [ "+i+" ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			exportProjectSvcService.layoutSketch((GetPermitByIdResult)scenario.get(i).get(0));
//		}
//	}
//	
//	@Test
//	public void testutilityCompany() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				   + "FROM PermitSketchEntity u "
//				   + "WHERE u.permitEntity.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(0).add(null);
//		//The result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(1).add(new GetPermitByIdResult());
//		//The result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new LinkedHashMap<String, String[]>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per = new GetPermitByIdResult();
//		per.setPermitCompanyInfoEntityResult(new PermitCompanyInfoEntityResultPrime());
//		scenario.get(2).add(per);
//		//The result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new LinkedHashMap<String, String[]>());
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("utilityCompany [ "+i+" ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			exportProjectSvcService.utilityCompany((GetPermitByIdResult)scenario.get(i).get(0));
//		}
//	}
//	
//	@Test
//	public void testadvPermitsInputs() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				   + "FROM PermitSketchEntity u "
//				   + "WHERE u.permitEntity.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(0).add(null);
//		//The result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(1).add(new GetPermitByIdResult());
//		//The result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new LinkedHashMap<String, String[]>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per = new GetPermitByIdResult();
//		per.setPermitAdvEntityResult(new PermitAdvEntityResult());
//		scenario.get(2).add(per);
//		//The result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per1 = new GetPermitByIdResult();
//		per1.setPermitAdvEntityResult(new PermitAdvEntityResult());
//		per1.setPermitHomeSiteEntityResult(new PermitHomeSiteEntityResult());
//		scenario.get(3).add(per1);
//		//The result of the query1
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per2 = new GetPermitByIdResult();
//		per2.setPermitAdvEntityResult(new PermitAdvEntityResult());
//		per2.setPermitHomeSiteEntityResult(new PermitHomeSiteEntityResult());
//		per2.setPermtiWeatherEntityResult(new PermtiWeatherEntityResult());
//		scenario.get(4).add(per2);
//		//The result of the query1
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per3 = new GetPermitByIdResult();
//		per3.setPermitAdvEntityResult(new PermitAdvEntityResult());
//		per3.setPermitHomeSiteEntityResult(new PermitHomeSiteEntityResult());
//		per3.setPermtiWeatherEntityResult(new PermtiWeatherEntityResult());
//		per3.setPermitEngineerEntityResult(new PermitEngineerEntityResult());
//		scenario.get(5).add(per3);
//		//The result of the query1
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add(new LinkedHashMap<String, String[]>());
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("advPermitsInputs [ "+i+" ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			exportProjectSvcService.advPermitsInputs((GetPermitByIdResult)scenario.get(i).get(0));
//		}
//	}
//	
//	@Test
//	public void testdrafterData() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				   + "FROM PermitTotalSectionEntity u "
//				   + "WHERE u.permitEntity.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(0).add(null);
//		//The result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		scenario.get(1).add(new GetPermitByIdResult());
//		//The result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per = new GetPermitByIdResult();
//		per.setPermitEntity(new PermitEntity());
//		scenario.get(2).add(per);
//		//The result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new LinkedHashMap<String, String[]>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the parameter
//		GetPermitByIdResult per1 = new GetPermitByIdResult();
//		per1.setPermitEntity(new PermitEntity());
//		per1.setPermitDrafterData(new PermitDrafterDataResult());
//		scenario.get(3).add(per1);
//		//The result of the query1
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new LinkedHashMap<String, String[]>());
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("drafterData [ "+i+" ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			exportProjectSvcService.drafterData((GetPermitByIdResult)scenario.get(i).get(0));
//		}
//	}

//}
