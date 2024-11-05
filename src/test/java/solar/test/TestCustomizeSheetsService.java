//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
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
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.PlayGroundAdv.Solar.Entity.AccountBuildEntity;
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.Battery;
//import com.PlayGroundAdv.Solar.Entity.PermitAdditionalInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitArraysEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitCustomizedSheetsEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEngineerEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitHomeSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PlansetCustomizeSheets;
//import com.PlayGroundAdv.Solar.Entity.RailRacking;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.Entity.RoofMaterialType;
//import com.PlayGroundAdv.Solar.Entity.SsheetLibraryEntity;
//import com.PlayGroundAdv.Solar.model.AccountBuildModel;
//import com.PlayGroundAdv.Solar.model.CustomizeSheetsModel;
//import com.PlayGroundAdv.Solar.model.DuplicateCustomizeSheetsModel;
//import com.PlayGroundAdv.Solar.model.RackingDetailsMappingModel;
//import com.PlayGroundAdv.Solar.model.SRsheetsModel;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.CustomizeSheetsService;
//import com.PlayGroundAdv.Solar.Services.GetPDFReaderService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.SsheetLibraryService;
//
//public class TestCustomizeSheetsService {
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
//	@Mock
//	GetPDFReaderService getPDFReaderService;
//
//	@Spy
//	CheckValueTypesService checkValueTypesService;
//	@InjectMocks
//	CustomizeSheetsService customizeSheetsService = new CustomizeSheetsService();
//
//
//    @Before
//	public void setupMock() {
//    	customizeSheetsService = new CustomizeSheetsService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//
//	@Test
//	public void testgetPlCustomizeSheets() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PlansetCustomizeSheets u WHERE u.isDeleted = :p1 ORDER BY u.pdfName"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE CONCAT(u.firstName,' ',u.lastName) =:p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<CustomizeSheetsModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<PlansetCustomizeSheets> list = new ArrayList<>();
//		list.add(null);
//		list.add(new PlansetCustomizeSheets());
//		scenario.get(1).add(list);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		ArrayList<CustomizeSheetsModel> list2= new ArrayList<CustomizeSheetsModel>();
//		list2.add(new CustomizeSheetsModel());
//		scenario.get(1).add(list2);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getPlCustomizeSheets [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(1));
//
//			ArrayList<CustomizeSheetsModel> rslt = (ArrayList<CustomizeSheetsModel>) customizeSheetsService.getPlCustomizeSheets();
//
//		}
//
//
//	}
//
//	@Test
//	public void testgetDeletedPlCustomizeSheets() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PlansetCustomizeSheets u WHERE u.isDeleted = :p1 ORDER BY u.pdfName"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE CONCAT(u.firstName,' ',u.lastName) =:p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<PlansetCustomizeSheets>());
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<PlansetCustomizeSheets> list = new ArrayList<>();
//		list.add(null);
//		list.add(new PlansetCustomizeSheets());
//		scenario.get(1).add(list);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getDeletedPlCustomizeSheets [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(1));
//
//			ArrayList<PlansetCustomizeSheets> rslt = (ArrayList<PlansetCustomizeSheets>) customizeSheetsService.getDeletedPlCustomizeSheets();
//
//		}
//	}
//
//	@Test
//	public void testgetAccountBuildSheets() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AccountBuildEntity u WHERE u.isDeleted = :p1 ORDER BY u.pdfName"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<AccountBuildEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<AccountBuildEntity> list = new ArrayList<AccountBuildEntity>();
//		list.add(null);
//		list.add(new AccountBuildEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAccountBuildSheets [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			ArrayList<AccountBuildEntity> rslt = (ArrayList<AccountBuildEntity>) customizeSheetsService.getAccountBuildSheets();
//
//		}
//	}
//
//	@Test
//	public void testgetDeletedAccountBuildSheets() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AccountBuildEntity u WHERE u.isDeleted = :p1 ORDER BY u.pdfName"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<AccountBuildEntity>());
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<AccountBuildEntity> list = new ArrayList<AccountBuildEntity>();
//		list.add(null);
//		list.add(new AccountBuildEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAccountBuildSheets [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			ArrayList<AccountBuildEntity> rslt = (ArrayList<AccountBuildEntity>) customizeSheetsService.getDeletedAccountBuildSheets();
//
//		}
//	}
//
//	@Test
//	public void testsearchCustomizeSheets() {
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		Path battery = mock(Path.class);
//
//		when(criteriaBuilder.createQuery(PlansetCustomizeSheets.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(PlansetCustomizeSheets.class)).thenReturn(root);
//		when(root.get(Mockito.anyString())).thenReturn(battery);
//		// when(r.get("authentificationEntity")).thenReturn(authentificationEntity);
//		when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//		when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<CustomizeSheetsModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new PlansetCustomizeSheets());
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<CustomizeSheetsModel>());
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new PlansetCustomizeSheets());
//		// Result of the query1
//		ArrayList<PlansetCustomizeSheets> list = new ArrayList<>();
//		list.add(null);
//		list.add(new PlansetCustomizeSheets());
//		scenario.get(2).add(list);
//		// Result excpected
//		ArrayList<CustomizeSheetsModel> list1 = new ArrayList<>();
//		list1.add(new CustomizeSheetsModel());
//		scenario.get(2).add(list1);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("searchCustomizeSheets [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			ArrayList<CustomizeSheetsModel> rslt = (ArrayList<CustomizeSheetsModel>) customizeSheetsService
//					.searchCustomizeSheets((PlansetCustomizeSheets) scenario.get(i).get(0));
//
//		}
//	}
//
//	@Test
//	public void testsearchAccountBuildSheets() {
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//
//		when(criteriaBuilder.createQuery(AccountBuildEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(AccountBuildEntity.class)).thenReturn(root);
//		// when(r.get("authentificationEntity")).thenReturn(authentificationEntity);
//		when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//		when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
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
//		scenario.get(1).add(new AccountBuildEntity());
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new AccountBuildEntity());
//		// Result of the query1
//		ArrayList<AccountBuildEntity> list = new ArrayList<>();
//		list.add(null);
//		list.add(new AccountBuildEntity());
//		scenario.get(2).add(list);
//		// Result excpected
//		scenario.get(2).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("searchAccountBuildSheets [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			ArrayList<AccountBuildEntity> rslt = (ArrayList<AccountBuildEntity>) customizeSheetsService
//					.searchAccountBuildSheets((AccountBuildEntity) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testsearchDeletedAccountBuildSheets() {
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//
//		when(criteriaBuilder.createQuery(AccountBuildEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(AccountBuildEntity.class)).thenReturn(root);
//		// when(r.get("authentificationEntity")).thenReturn(authentificationEntity);
//		when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//		when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
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
//		scenario.get(1).add(new AccountBuildEntity());
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new AccountBuildEntity());
//		// Result of the query1
//		ArrayList<AccountBuildEntity> list = new ArrayList<>();
//		list.add(null);
//		list.add(new AccountBuildEntity());
//		scenario.get(2).add(list);
//		// Result excpected
//		scenario.get(2).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("searchDeletedAccountBuildSheets [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			ArrayList<AccountBuildEntity> rslt = (ArrayList<AccountBuildEntity>) customizeSheetsService
//					.searchDeletedAccountBuildSheets((AccountBuildEntity) scenario.get(i).get(0));
//
//		}
//	}
//
//	@Test
//	public void testsearchDeletedCustomizeSheets() {
//
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//
//		when(criteriaBuilder.createQuery(PlansetCustomizeSheets.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(PlansetCustomizeSheets.class)).thenReturn(root);
//		// when(r.get("authentificationEntity")).thenReturn(authentificationEntity);
//		when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//		when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
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
//		scenario.get(1).add(new PlansetCustomizeSheets());
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new PlansetCustomizeSheets());
//		// Result of the query1
//		ArrayList<PlansetCustomizeSheets> list = new ArrayList<>();
//		list.add(null);
//		list.add(new PlansetCustomizeSheets());
//		scenario.get(2).add(list);
//		// Result excpected
//		scenario.get(2).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("searchDeletedCustomizeSheets [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			ArrayList<PlansetCustomizeSheets> rslt = (ArrayList<PlansetCustomizeSheets>) customizeSheetsService
//					.searchDeletedCustomizeSheets((PlansetCustomizeSheets) scenario.get(i).get(0));
//
//		}
//
//	}
//
//
//	@Test
//	public void testuploadHomePicture() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.pdfName = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		//List of the parameter
//		scenario.get(1).add(new RackingDetailsMappingModel());
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(new RackingDetailsMappingModel());
//		scenario.get(2).add("");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(new RackingDetailsMappingModel());
//		scenario.get(3).add("1258");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("Done");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add(new RackingDetailsMappingModel());
//		scenario.get(4).add("1258");
//		// Result of the query1
//		ArrayList<SsheetLibraryEntity> list = new ArrayList<>();
//		list.add(null);
//		list.add(new SsheetLibraryEntity());
//		scenario.get(4).add(list);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("exist");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("uploadHomePicture [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			customizeSheetsService.uploadHomePicture((RackingDetailsMappingModel) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		}
//	}
//
//	@Test
//	public void testaddCustomizeSheet() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from Battery u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofMaterialType u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		// Result excpected
//		scenario.get(0).add("fail");
//		// Result List of the query2
//		scenario.get(0).add(null);
//		//Result List of the query3
//		scenario.get(0).add(null);
//		//Result List of the query4
//		scenario.get(0).add(null);
//		//Result List of the query5
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		// Result excpected
//		scenario.get(1).add("fail");
//		// Result List of the query2
//		scenario.get(1).add(null);
//		//Result List of the query3
//		scenario.get(1).add(null);
//		//Result List of the query4
//		scenario.get(1).add(null);
//		//Result List of the query5
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add("12358");
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
//		// Result excpected
//		scenario.get(2).add("fail");
//		// Result List of the query2
//		scenario.get(2).add(null);
//		//Result List of the query3
//		scenario.get(2).add(null);
//		//Result List of the query4
//		scenario.get(2).add(null);
//		//Result List of the query5
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		CustomizeSheetsModel cst = new CustomizeSheetsModel();
//		cst.setUsers(12358);
//		cst.setBatteryInSystem(12589);
//		cst.setRoofingMaterialType(1258);
//		cst.setRailRackingModel(12589);
//		scenario.get(3).add(cst);
//		scenario.get(3).add("12358");
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
//		// Result excpected
//		scenario.get(3).add("null");
//		// Result List of the query2
//		scenario.get(3).add(null);
//		//Result List of the query3
//		scenario.get(3).add(null);
//		//Result List of the query4
//		scenario.get(3).add(null);
//		//Result List of the query5
//		scenario.get(3).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add(cst);
//		scenario.get(4).add("12358");
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
//		// Result excpected
//		scenario.get(4).add("fail");
//		// Result List of the query2
//		ArrayList<AuthentificationEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(4).add(list);
//		//Result List of the query3
//		ArrayList<Battery> list2 = new ArrayList<>();
//		list2.add(null);
//		scenario.get(4).add(list2);
//		//Result List of the query4
//		ArrayList<RoofMaterialType> list3 = new ArrayList<>();
//		list3.add(null);
//		scenario.get(4).add(list3);
//		//Result List of the query5
//		ArrayList<RailRacking> list4 = new ArrayList<>();
//		list4.add(null);
//		scenario.get(4).add(list4);
//
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add(cst);
//		scenario.get(5).add("12358");
//		// Result of the query1
//		scenario.get(5).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(5).add(new AuthentificationEntity());
//		// Result of the query3
//		scenario.get(5).add(null);
//		// Result of the query4
//		scenario.get(5).add(null);
//		// Result of the query5
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("null");
//		// Result List of the query2
//		scenario.get(5).add(list);
//		//Result List of the query3
//		scenario.get(5).add(list2);
//		//Result List of the query4
//		scenario.get(5).add(list3);
//		//Result List of the query5
//		scenario.get(5).add(list4);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("uploadHomePicture [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(6));
//
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(8));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(9));
//			when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(10));
//			when(mockedQuery5.getResultList()).thenReturn((List) scenario.get(i).get(11));
//			customizeSheetsService.addCustomizeSheet((CustomizeSheetsModel) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		}
//
//	}
//
//	@Test
//	public void testaddAccountBuildSheet() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(new AccountBuildModel());
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("null");
//
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addAccountBuildSheet [ " + i + " ]");
//			customizeSheetsService.addAccountBuildSheet((AccountBuildModel) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		}
//	}
//
//	@Test
//	public void testaddLogic() throws IOException {
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		if(new File("C:/files/").exists()) {
//			new File("C:/files/xxx.txt").createNewFile();
//		}else {
//			new File("C:/files/").mkdir();
//			new File("C:/files/xxx.txt").createNewFile();
//		}
// 	FileInputStream inputFile = new FileInputStream("C:\\files\\xxx.txt");
//		MockMultipartFile file = new MockMultipartFile("file", "NameOfTheFile", "multipart/form-data", inputFile);
//		scenario.get(1).add(file);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addLogic [ " + i + " ]");
//		 customizeSheetsService.addLogic((MultipartFile) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
//		}
//
//	}
//
//	@Test
//	public void testaddAccountBuildLogic() throws IOException {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		if(new File("C:/files/").exists()) {
//			new File("C:/files/xxx.txt").mkdir();
//		}else {
//			new File("C:/files/").mkdir();
//			new File("C:/files/xxx.txt").mkdir();
//		}
//		FileInputStream inputFile = new FileInputStream("C:\\files\\xxx.txt");
//		MockMultipartFile file = new MockMultipartFile("file", "NameOfTheFile", "multipart/form-data", inputFile);
//		scenario.get(1).add(file);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addAccountBuildLogic [ " + i + " ]");
//			customizeSheetsService.addAccountBuildLogic((MultipartFile) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
//		}
//	}
//
//	@Test
//	public void testgetUsersNames() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
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
//		ArrayList<AuthentificationEntity> list = new ArrayList<AuthentificationEntity>();
//		list.add(null);
//		list.add(new AuthentificationEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getUsersNames [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			ArrayList<AuthentificationEntity> rslt = (ArrayList<AuthentificationEntity>) customizeSheetsService.getUsersNames();
//
//		}
//	}
//
//	@Test
//	public void testeditCustomizeSheet() throws IOException {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from PlansetCustomizeSheets u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u from Battery u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofMaterialType u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
//
//		Query mockedQuery6 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery6);
//		when(mockedQuery6.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery6);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		// Result excpected
//		scenario.get(0).add("error");
//		//The Result list of the Query4
//		scenario.get(0).add(null);
//		//The Result list of the Query5
//		scenario.get(0).add(null);
//		//The Result list of the Query6
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		// Result excpected
//		scenario.get(1).add("error");
//		//The Result list of the Query4
//		scenario.get(1).add(null);
//		//The Result list of the Query5
//		scenario.get(1).add(null);
//		//The Result list of the Query6
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		// Result excpected
//		scenario.get(2).add("error");
//		//The Result list of the Query4
//		scenario.get(2).add(null);
//		//The Result list of the Query5
//		scenario.get(2).add(null);
//		//The Result list of the Query6
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(new CustomizeSheetsModel());
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
//		// Result excpected
//		scenario.get(3).add("error");
//		//The Result list of the Query4
//		scenario.get(3).add(null);
//		//The Result list of the Query5
//		scenario.get(3).add(null);
//		//The Result list of the Query6
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add(new CustomizeSheetsModel());
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		scenario.get(4).add(new PlansetCustomizeSheets());
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result of the query4
//		scenario.get(4).add(null);
//		// Result of the query5
//		scenario.get(4).add(null);
//		// Result of the query6
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("error");
//		//The Result list of the Query4
//		scenario.get(4).add(null);
//		//The Result list of the Query5
//		scenario.get(4).add(null);
//		//The Result list of the Query6
//		scenario.get(4).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add(new CustomizeSheetsModel());
//		scenario.get(5).add("123");
//		// Result of the query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setRoleEntity(new RoleEntity());
//		scenario.get(5).add(auth);
//		// Result of the query2
//		scenario.get(5).add(new PlansetCustomizeSheets());
//		// Result of the query3
//		scenario.get(5).add(auth);
//		// Result of the query4
//		scenario.get(5).add(null);
//		// Result of the query5
//		scenario.get(5).add(null);
//		// Result of the query6
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("null");
//		//The Result list of the Query4
//		scenario.get(5).add(null);
//		//The Result list of the Query5
//		scenario.get(5).add(null);
//		//The Result list of the Query6
//		scenario.get(5).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		CustomizeSheetsModel cust = new CustomizeSheetsModel();
//		cust.setBatteryInSystem(122333);
//		cust.setRoofingMaterialType(12558);
//		cust.setRailRackingModel(125968);
//		scenario.get(6).add(cust);
//		scenario.get(6).add("123");
//		// Result of the query1
//		scenario.get(6).add(auth);
//		// Result of the query2
//		scenario.get(6).add(new PlansetCustomizeSheets());
//		// Result of the query3
//		scenario.get(6).add(auth);
//		// Result of the query4
//		scenario.get(6).add(null);
//		// Result of the query5
//		scenario.get(6).add(null);
//		// Result of the query6
//		scenario.get(6).add(null);
//		// Result excpected
//		scenario.get(6).add("null");
//		//The Result list of the Query4
//		scenario.get(6).add(null);
//		//The Result list of the Query5
//		scenario.get(6).add(null);
//		//The Result list of the Query6
//		scenario.get(6).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(7).add(cust);
//		scenario.get(7).add("123");
//		// Result of the query1
//		scenario.get(7).add(auth);
//		// Result of the query2
//		scenario.get(7).add(new PlansetCustomizeSheets());
//		// Result of the query3
//		scenario.get(7).add(auth);
//		// Result of the query4
//		scenario.get(7).add(null);
//		// Result of the query5
//		scenario.get(7).add(null);
//		// Result of the query6
//		scenario.get(7).add(null);
//		// Result excpected
//		scenario.get(7).add("null");
//		//The Result list of the Query4
//		ArrayList<Battery> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(7).add(list);
//		//The Result list of the Query5
//		ArrayList<RoofMaterialType> list2 = new ArrayList<>();
//		list2.add(null);
//		scenario.get(7).add(list2);
//		//The Result list of the Query6
//		ArrayList<RailRacking> list3 = new ArrayList<>();
//		list3.add(null);
//		scenario.get(7).add(list3);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editAccountBuildSheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(6));
//			when(mockedQuery6.getSingleResult()).thenReturn(scenario.get(i).get(7));
//
//			when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(9));
//			when(mockedQuery5.getResultList()).thenReturn((List) scenario.get(i).get(10));
//			when(mockedQuery6.getResultList()).thenReturn((List) scenario.get(i).get(11));
//
//			customizeSheetsService.editCustomizeSheet((CustomizeSheetsModel) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		}
//	}
//
//	@Test
//	public void testeditAccountBuildSheet() throws IOException {
//
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AccountBuildEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		//List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(new AccountBuildModel());
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(new AccountBuildModel());
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(new AccountBuildEntity());
//		// Result excpected
//		scenario.get(3).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add(new AccountBuildModel());
//		scenario.get(4).add("123");
//		// Result of the query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setRoleEntity(new RoleEntity());
//		scenario.get(4).add(auth);
//		// Result of the query2
//		scenario.get(4).add(new AccountBuildEntity());
//		// Result excpected
//		scenario.get(4).add(null+"");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editAccountBuildSheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			customizeSheetsService.editAccountBuildSheet((AccountBuildModel) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		}
//
//
//	}
//
//	@Test
//	public void testdeleteCustomizeSheet() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from PlansetCustomizeSheets u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		//List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(new PlansetCustomizeSheets());
//		// Result excpected
//		scenario.get(3).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add("123");
//		// Result of the query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setRoleEntity(new RoleEntity());
//		scenario.get(4).add(auth);
//		// Result of the query2
//		scenario.get(4).add(new PlansetCustomizeSheets());
//		// Result excpected
//		scenario.get(4).add("done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deleteCustomizeSheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			customizeSheetsService.deleteCustomizeSheet((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		}
//
//	}
//
//	@Test
//	public void testdeleteAccountBuildSheet() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AccountBuildEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		//List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(new AccountBuildEntity());
//		// Result excpected
//		scenario.get(3).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add("123");
//		// Result of the query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setRoleEntity(new RoleEntity());
//		scenario.get(4).add(auth);
//		// Result of the query2
//		scenario.get(4).add(new AccountBuildEntity());
//		// Result excpected
//		scenario.get(4).add("done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deleteAccountBuildSheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			customizeSheetsService.deleteAccountBuildSheet((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		}
//	}
//
//
//	@Test
//	public void testdeletePermanetCustSheet() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PlansetCustomizeSheets u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add("123");
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("123");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(new PlansetCustomizeSheets());
//		// Result excpected
//		scenario.get(3).add("done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deletePermanetSheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			customizeSheetsService.deletePermanetCustSheet((String) scenario.get(i).get(0));
//		}
//
//	}
//
//	@Test
//	public void testdeletePermanetSheet() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AccountBuildEntity u WHERE u.id = :p1 "))
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
//		scenario.get(0).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(new AccountBuildEntity());
//		// Result excpected
//		scenario.get(3).add("done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deletePermanetSheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			customizeSheetsService.deletePermanetSheet((String) scenario.get(i).get(0));
//		}
//
//	}
//
//	@Test
//	public void testrestoreCustomizesheet() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PlansetCustomizeSheets u WHERE u.id = :p1 "))
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
//		scenario.get(0).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(new PlansetCustomizeSheets());
//		// Result excpected
//		scenario.get(3).add("done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("restoreCustomizesheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			customizeSheetsService.restoreCustomizesheet((String) scenario.get(i).get(0));
//		}
//
//
//	}
//
//	@Test
//	public void testrestoreAccountBuildsheet() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AccountBuildEntity u WHERE u.id = :p1 "))
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
//		scenario.get(0).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(new AccountBuildEntity());
//		// Result excpected
//		scenario.get(3).add("done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("restoreCustomizesheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			customizeSheetsService.restoreAccountBuildsheet((String) scenario.get(i).get(0));
//		}
//	}
//
//	@Test
//	public void testgetCompatileCustomizeSheets() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u from PermitEntity u where u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitHomeSiteInfoEntity u where u.permitEntity.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitArraysEntity u where u.permitEntity.id = :p1 "))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitAdditionalInfoEntity u where u.permitEntity.id = :p1 "))
//				.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitProjectSiteInfoEntity u where u.permitEntity.id = :p1 "))
//				.thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
//
//		Query mockedQuery6 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEngineerEntity u where u.permitEntity.id = :p1 "))
//				.thenReturn(mockedQuery6);
//		when(mockedQuery6.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery6);
//
//		Query mockedQuery7 = mock(Query.class);
//		when(em.createQuery("DELETE from PermitCustomizedSheetsEntity u where u.project.id = :p1 "))
//				.thenReturn(mockedQuery7);
//		when(mockedQuery7.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery7);
//		when(mockedQuery7.executeUpdate()).thenReturn(1);
//
//		Query mockedQuery8 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from PlansetCustomizeSheets u where u.isDeleted=:p3 AND u.pdfName NOT IN :p1 "
//				+ " AND (upper(u.utilityCompany) =:p4 OR u.utilityCompany IS NULL OR u.utilityCompany = '' )"
//				+ " AND (upper(u.individualAHJ) =:p5 OR u.individualAHJ IS NULL OR u.individualAHJ = '')"
//				+ " AND (u.userSheet.id =:p6 OR u.userSheet IS NULL)"
//				+ " AND (u.state =:p7 OR u.state IS NULL OR u.state = '')"
//				+ " AND ((u.basicSystemType IS NULL OR u.basicSystemType = '') "
//				+ " OR	( u.basicSystemType = 'Roof Mounted' AND :p80 = true ) "
//				+ " OR	( u.basicSystemType = 'Ground Mounted' AND :p81 = true) "
//				+ " OR	( u.basicSystemType = 'Pole Mounted' AND :p82 = true) "
//				+ " OR	( u.basicSystemType = 'Carport / Patio Cover' AND :p83 = true) "
//				+ " OR (u.basicSystemType = 'Other' AND (upper(u.basicSystemTypeOther) = :p84 OR u.basicSystemTypeOther IS NULL OR u.basicSystemTypeOther = '') ) )"
//				+ " AND (u.inverterTechnology =:p9 OR u.inverterTechnology IS NULL OR u.inverterTechnology = '')"
//				+ " AND (u.batteryInSystem.id =:p10 OR u.batteryInSystem IS NULL)"
//				+ " AND ( (u.roofType =:p12 OR u.roofType =:p13 OR u.roofType IS NULL OR u.roofType = '')"
//				+ " OR (u.roofType = 'Other' AND (u.roofTypeOther =:p19 OR u.roofTypeOther IS NULL OR u.roofTypeOther = '') ) )"
//				+ " AND (u.roofingMaterialType.id =:p14 OR u.roofingMaterialType IS NULL)"
//				+ " AND (u.railRackingModel.id =:p15 OR u.railRackingModel.id =:p16 OR u.railRackingModel.id =:p17 OR u.railRackingModel IS NULL)"
//				+ " AND (u.electEngStructEng =:p18 OR u.electEngStructEng IS NULL OR u.electEngStructEng = '')"))
//				.thenReturn(mockedQuery8);
//		when(mockedQuery8.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery8);
//
//
//		Query mockedQuery9 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from PlansetCustomizeSheets u where u.isDeleted=:p3 AND u.pdfName IN :p1 "
//				+ " AND (upper(u.utilityCompany) =:p4 OR u.utilityCompany IS NULL OR u.utilityCompany = '' )"
//				+ " AND (upper(u.individualAHJ) =:p5 OR u.individualAHJ IS NULL OR u.individualAHJ = '')"
//				+ " AND (u.userSheet.id =:p6 OR u.userSheet IS NULL)"
//				+ " AND (u.state =:p7 OR u.state IS NULL OR u.state = '')"
//				+ " AND ((u.basicSystemType IS NULL OR u.basicSystemType = '') "
//				+ " OR	( u.basicSystemType = 'Roof Mounted' AND :p80 = true ) "
//				+ " OR	( u.basicSystemType = 'Ground Mounted' AND :p81 = true) "
//				+ " OR	( u.basicSystemType = 'Pole Mounted' AND :p82 = true) "
//				+ " OR	( u.basicSystemType = 'Carport / Patio Cover' AND :p83 = true) "
//				+ " OR (u.basicSystemType = 'Other' AND (upper(u.basicSystemTypeOther) = :p84 OR u.basicSystemTypeOther IS NULL OR u.basicSystemTypeOther = '') ) )"
//				+ " AND (u.inverterTechnology =:p9 OR u.inverterTechnology IS NULL OR u.inverterTechnology = '')"
//				+ " AND (u.batteryInSystem.id =:p10 OR u.batteryInSystem IS NULL)"
//				+ " AND ( (u.roofType =:p12 OR u.roofType =:p13 OR u.roofType IS NULL OR u.roofType = '')"
//				+ " OR (u.roofType = 'Other' AND (u.roofTypeOther =:p19 OR u.roofTypeOther IS NULL OR u.roofTypeOther = '') ) )"
//				+ " AND (u.roofingMaterialType.id =:p14 OR u.roofingMaterialType IS NULL)"
//				+ " AND (u.railRackingModel.id =:p15 OR u.railRackingModel.id =:p16 OR u.railRackingModel.id =:p17 OR u.railRackingModel IS NULL)"
//				+ " AND (u.electEngStructEng =:p18 OR u.electEngStructEng IS NULL OR u.electEngStructEng = '')"))
//				.thenReturn(mockedQuery9);
//		when(mockedQuery9.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery9);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		//The Result of the Query1
//		scenario.get(0).add(null);
//		//The Result List of the Query1
//		scenario.get(0).add(null);
//		//The Result of the Query2
//		scenario.get(0).add(null);
//		//The Result List of the Query2
//		scenario.get(0).add(null);
//		//The Result of the Query3
//		scenario.get(0).add(null);
//		//The Result List of the Query3
//		scenario.get(0).add(null);
//		//The Result of the Query4
//		scenario.get(0).add(null);
//		//The Result List of the Query4
//		scenario.get(0).add(null);
//		//The Result of the Query5
//		scenario.get(0).add(null);
//		//The Result List of the Query5
//		scenario.get(0).add(null);
//		//The Result of the Query6
//		scenario.get(0).add(null);
//		//The Result List of the Query6
//		scenario.get(0).add(null);
//		//The Result of the Query8
//		scenario.get(0).add(null);
//		//The Result of the Query9
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(123);
//		//The Result of the Query1
//		scenario.get(1).add(null);
//		//The Result List of the Query1
//		scenario.get(1).add(null);
//		//The Result of the Query2
//		scenario.get(1).add(null);
//		//The Result List of the Query2
//		scenario.get(1).add(null);
//		//The Result of the Query3
//		scenario.get(1).add(null);
//		//The Result List of the Query3
//		scenario.get(1).add(null);
//		//The Result of the Query4
//		scenario.get(1).add(null);
//		//The Result List of the Query4
//		scenario.get(1).add(null);
//		//The Result of the Query5
//		scenario.get(1).add(null);
//		//The Result List of the Query5
//		scenario.get(1).add(null);
//		//The Result of the Query6
//		scenario.get(1).add(null);
//		//The Result List of the Query6
//		scenario.get(1).add(null);
//		//The Result of the Query8
//		scenario.get(1).add(null);
//		//The Result of the Query9
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(223);
//		//The Result of the Query1
//		scenario.get(2).add(null);
//		//The Result List of the Query1
//		ArrayList<Object> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(2).add(list);
//		//The Result of the Query2
//		scenario.get(2).add(null);
//		//The Result List of the Query2
//		scenario.get(2).add(null);
//		//The Result of the Query3
//		scenario.get(2).add(null);
//		//The Result List of the Query3
//		scenario.get(2).add(null);
//		//The Result of the Query4
//		scenario.get(2).add(null);
//		//The Result List of the Query4
//		scenario.get(2).add(null);
//		//The Result of the Query5
//		scenario.get(2).add(null);
//		//The Result List of the Query5
//		scenario.get(2).add(null);
//		//The Result of the Query6
//		scenario.get(2).add(null);
//		//The Result List of the Query6
//		scenario.get(2).add(null);
//		//The Result of the Query8
//		scenario.get(2).add(null);
//		//The Result of the Query9
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(323);
//		//The Result of the Query1
//		PermitEntity per = new PermitEntity();
//		per.setAuthentificationEntity(new AuthentificationEntity());
//		scenario.get(3).add(per);
//		//The Result List of the Query1
//		scenario.get(3).add(list);
//		//The Result of the Query2
//		scenario.get(3).add(new PermitHomeSiteInfoEntity());
//		//The Result List of the Query2
//		scenario.get(3).add(list);
//		//The Result of the Query3
//		scenario.get(3).add(new PermitArraysEntity());
//		//The Result List of the Query3
//		scenario.get(3).add(list);
//		//The Result of the Query4
//		scenario.get(3).add(new PermitAdditionalInfoEntity());
//		//The Result List of the Query4
//		scenario.get(3).add(list);
//		//The Result of the Query5
//		scenario.get(3).add(new PermitProjectSiteInfoEntity());
//		//The Result List of the Query5
//		scenario.get(3).add(list);
//		//The Result of the Query6
//		scenario.get(3).add(new PermitEngineerEntity());
//		//The Result List of the Query6
//		scenario.get(3).add(list);
//		//The Result of the Query8
//		scenario.get(3).add(null);
//		//The Result of the Query9
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add(423);
//		//The Result of the Query1
//		scenario.get(4).add(per);
//		//The Result List of the Query1
//		scenario.get(4).add(list);
//		//The Result of the Query2
//		scenario.get(4).add(new PermitHomeSiteInfoEntity());
//		//The Result List of the Query2
//		scenario.get(4).add(list);
//		//The Result of the Query3
//		scenario.get(4).add(new PermitArraysEntity());
//		//The Result List of the Query3
//		scenario.get(4).add(list);
//		//The Result of the Query4
//		scenario.get(4).add(new PermitAdditionalInfoEntity());
//		//The Result List of the Query4
//		scenario.get(4).add(list);
//		//The Result of the Query5
//		scenario.get(4).add(new PermitProjectSiteInfoEntity());
//		//The Result List of the Query5
//		scenario.get(4).add(list);
//		//The Result of the Query6
//		scenario.get(4).add(new PermitEngineerEntity());
//		//The Result List of the Query6
//		scenario.get(4).add(list);
//		//The Result of the Query8
//		ArrayList<PlansetCustomizeSheets> list2 = new ArrayList<>();
//		list2.add(null);
//		list2.add(new PlansetCustomizeSheets());
//		scenario.get(4).add(list2);
//		//The Result of the Query9
//		scenario.get(4).add(null);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getCompatileCustomizeSheets [ " + i + " ]");
//
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//
//
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(4));
//
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(6));
//
//			when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(8));
//
//			when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(9));
//			when(mockedQuery5.getResultList()).thenReturn((List) scenario.get(i).get(10));
//
//			when(mockedQuery6.getSingleResult()).thenReturn(scenario.get(i).get(11));
//			when(mockedQuery6.getResultList()).thenReturn((List) scenario.get(i).get(12));
//
//			when(mockedQuery8.getResultList()).thenReturn((List) scenario.get(i).get(13));
//			when(mockedQuery9.getResultList()).thenReturn((List) scenario.get(i).get(14));
//
//			customizeSheetsService.getCompatileCustomizeSheets((Integer) scenario.get(i).get(0));
//		}
//	}
//
//	@Test
//	public void testgetDuplicateCompatileCustomizeSheets() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitCustomizedSheetsEntity u where u.project.id = :p1 AND (SELECT count(*) from PermitCustomizedSheetsEntity v" +
//				" where u.sheet.pdfName = v.sheet.pdfName AND  u.project.id = v.project.id ) > 1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		//The Result of the Query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new HashMap<String, List<DuplicateCustomizeSheetsModel>>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		//The Result of the Query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new HashMap<String, List<DuplicateCustomizeSheetsModel>>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		//The Result of the Query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new HashMap<String, List<DuplicateCustomizeSheetsModel>>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		//The Result of the Query1
//		ArrayList<PermitCustomizedSheetsEntity> list = new ArrayList<>();
//		list.add(null);
//		list.add(new PermitCustomizedSheetsEntity());
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add(new HashMap<String, List<DuplicateCustomizeSheetsModel>>());
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getDuplicateCompatileCustomizeSheets [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			customizeSheetsService.getDuplicateCompatileCustomizeSheets((String) scenario.get(i).get(0));
//		}
//	}
//
//	@Test
//	public void testupdateCusSheets() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("update PermitCustomizedSheetsEntity u set u.masterSheet = true where u.id IN :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		when(mockedQuery1.executeUpdate()).thenReturn(1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		Integer[] list = {1,2,3};
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("updateCusSheets [ " + i + " ]");
//			customizeSheetsService.updateCusSheets((Integer[]) scenario.get(i).get(0));
//		}
//	}
//}
