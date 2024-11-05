//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
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
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.PlayGroundAdv.Solar.Entity.FlashingFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.SsheetLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.SsheetRackingMappingEntity;
//import com.PlayGroundAdv.Solar.Entity.SsheetSpacingMappingEntity;
//import com.PlayGroundAdv.Solar.model.RackingDetailsMappingModel;
//import com.PlayGroundAdv.Solar.model.SearchFlashingRequest;
//import com.PlayGroundAdv.Solar.model.SearchFlashingResult;
//import com.PlayGroundAdv.Solar.model.SiteSurveyFieldSetting;
//import com.PlayGroundAdv.Solar.model.SpacingDetailsMappingModel;
//import com.PlayGroundAdv.Solar.model.SsheetRackingModel;
//import com.PlayGroundAdv.Solar.model.SsheetSpacingMappingModel;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.SsheetLibraryService;
//import com.PlayGroundAdv.Solar.Services.SsheetRackingmapping;
//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
//
//public class TestSsheetRackingmapping {
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
//	SsheetRackingmapping ssheetRackingmapping = new SsheetRackingmapping();
//
//
//    @Before
//	public void setupMock() {
//    	ssheetRackingmapping = new SsheetRackingmapping();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testgetSsheetRackingMapping() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetRackingMappingEntity u  WHERE u.isDeleted = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE (u.pdfName = :p1 OR u.pdfName = :p3) AND u.isDeleted = :p2")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.SsheetRackingModel (u.id, u.roofType, u.roofTypeOther, u.rackingManufacturer, u.rackingModel, u.roofManufacturer, u.roofModel, u.flashingManufacturer, u.sheetNumber, u.quadNumber, u.detailsHeading, u.ahj, u.utilityCompany, u.sSheetFile.id, u.sSheetFile.pdfName "
//				+" ) from SsheetRackingMappingEntity u WHERE u.isDeleted = :p1 ORDER BY u.pdfname ")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of query1
//		 scenario.get(0).add(null);
//		 //Result of query2
//		 scenario.get(0).add(null);
//		 //Result of query3
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add(new ArrayList<SsheetRackingModel>());
//		 //The Single result of the Query 2
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of query1
//		 ArrayList<SsheetRackingMappingEntity> list = new ArrayList<>();
//		 list.add(null);
//		 list.add(new SsheetRackingMappingEntity());
//		 SsheetRackingMappingEntity sc = new SsheetRackingMappingEntity();
//		 sc.setPdfname("test");
//		 list.add(sc);
//		 scenario.get(1).add(list);
//		 //Result of query2
//		 scenario.get(1).add(null);
//		 //Result of query3
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add(new ArrayList<SsheetRackingModel>());
//		 //The Single result of the Query 2
//		 scenario.get(1).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of query1
//		 scenario.get(2).add(list);
//		 //Result of query2
//		 scenario.get(2).add(null);
//		 //Result of query3
//		 scenario.get(2).add(null);
//		//Excpected Result
//		 scenario.get(2).add(new ArrayList<SsheetRackingModel>());
//		 //The Single result of the Query 2
//		 ArrayList<SsheetLibraryEntity> list2 = new ArrayList<>();
//		 list2.add(null);
//		 scenario.get(2).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of query1
//		 scenario.get(3).add(list);
//		 //Result of query2
//		 scenario.get(3).add(null);
//		 //Result of query3
//		 ArrayList<SsheetRackingModel> list3 = new ArrayList<>();
//		 list3.add(null);
//		 list3.add(new SsheetRackingModel());
//		 scenario.get(3).add(list3);
//		//Excpected Result
//		 scenario.get(3).add(list3);
//		 //The Single result of the Query 2
//		 scenario.get(3).add(null);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getSsheetRackingMapping [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(2));
//
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//
//			ArrayList<SsheetRackingModel> result = ((ArrayList<SsheetRackingModel>) ssheetRackingmapping
//					.getSsheetRackingMapping());
//
//		}
//	}
//
//    @Test
//	public void testgetSsheetSpacingMapping() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetSpacingMappingEntity u WHERE u.isDeleted = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.pdfName = :p1 AND u.isDeleted = :p2")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.SsheetSpacingMappingModel (u.detailsHeading, u.id, u.lastUpdate, u.sSheetFile.id, u.sSheetFile.pdfName," +
//				" u.quadNumber, u.rafterTrussSpacing, u.roofType, u.roofTypeOther, u.sheetNumber," +
//				" u.stanchionMaxSpacing) from SsheetSpacingMappingEntity u WHERE u.isDeleted = :p1 ORDER BY u.pdfname ")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of query1
//		 scenario.get(0).add(null);
//		 //Result of query2
//		 scenario.get(0).add(null);
//		 //Result of query3
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add(new ArrayList<SsheetRackingModel>());
//		 //The Single result of the Query 2
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of query1
//		 ArrayList<SsheetSpacingMappingEntity> list = new ArrayList<>();
//		 list.add(null);
//		 list.add(new SsheetSpacingMappingEntity());
//		 SsheetSpacingMappingEntity sc = new SsheetSpacingMappingEntity();
//		 sc.setPdfname("test");
//		 list.add(sc);
//		 scenario.get(1).add(list);
//		 //Result of query2
//		 scenario.get(1).add(null);
//		 //Result of query3
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add(new ArrayList<SsheetRackingModel>());
//		 //The Single result of the Query 2
//		 scenario.get(1).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of query1
//		 scenario.get(2).add(list);
//		 //Result of query2
//		 scenario.get(2).add(null);
//		 //Result of query3
//		 scenario.get(2).add(null);
//		//Excpected Result
//		 scenario.get(2).add(new ArrayList<SsheetRackingModel>());
//		 //The Single result of the Query 2
//		 ArrayList<SsheetLibraryEntity> list2 = new ArrayList<>();
//		 list2.add(null);
//		 scenario.get(2).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of query1
//		 scenario.get(3).add(list);
//		 //Result of query2
//		 scenario.get(3).add(null);
//		 //Result of query3
//		 ArrayList<SsheetSpacingMappingModel> list3 = new ArrayList<>();
//		 list3.add(null);
//		 list3.add(new SsheetSpacingMappingModel());
//		 scenario.get(3).add(list3);
//		//Excpected Result
//		 scenario.get(3).add(list3);
//		 //The Single result of the Query 2
//		 scenario.get(3).add(null);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getSsheetSpacingMapping [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(2));
//
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//
//			ArrayList<SsheetSpacingMappingModel> result = ((ArrayList<SsheetSpacingMappingModel>) ssheetRackingmapping
//					.getSsheetSpacingMapping());
//
//		}
//	}
//
//	@Test
//	public void testgetDeletedSsheetRackingMapping() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetRackingMappingEntity u WHERE u.isDeleted = :p1 ORDER BY u.pdfname ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 scenario.add(new ArrayList<Object>());
//		 //Result of query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add(new ArrayList<SsheetRackingMappingEntity>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of query1
//		 ArrayList<SsheetRackingMappingEntity> list = new ArrayList<>();
//		 list.add(null);
//		 list.add(new SsheetRackingMappingEntity());
//		 scenario.get(1).add(list);
//		//Excpected Result
//		 scenario.get(1).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getSsheetspdfs [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//
//			ArrayList<SsheetRackingMappingEntity> result = ((ArrayList<SsheetRackingMappingEntity>) ssheetRackingmapping
//					.getDeletedSsheetRackingMapping());
//
//		}
//	}
//
//
//    @Test
//	public void testsearchSsheetRacking() {
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(SsheetRackingMappingEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(SsheetRackingMappingEntity.class)).thenReturn(root);
//        when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//
//        TypedQuery mockedQuery1 = mock(TypedQuery.class);
//        when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//        List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 //The result of the Query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add(new ArrayList<SsheetRackingModel>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(new SsheetRackingMappingEntity());
//		 //The result of the Query1
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add(new ArrayList<SsheetRackingModel>());
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(new SsheetRackingMappingEntity());
//		 //The result of the Query1
//		 ArrayList<SsheetRackingMappingEntity> list = new ArrayList<>();
//		 list.add(null);
//		 list.add(new SsheetRackingMappingEntity());
//		 scenario.get(2).add(list);
//		//Excpected Result
//		 ArrayList<SsheetRackingModel> list2 = new ArrayList<SsheetRackingModel>();
//		 list2.add( new SsheetRackingModel());
//		 scenario.get(2).add(list2);
//
//			for (int i = 0; i < scenario.size(); i++) {
//				System.out.println("searchSsheetSpacing [ " + i + " ]");
//				when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//
//				ArrayList<SsheetRackingModel> result = ((ArrayList<SsheetRackingModel>) ssheetRackingmapping
//						.searchSsheetRacking((SsheetRackingMappingEntity)scenario.get(i).get(0)));
//
//			}
//	}
//
//
//    @Test
//	public void testsearchSsheetSpacing() {
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(SsheetSpacingMappingEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(SsheetSpacingMappingEntity.class)).thenReturn(root);
//        when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//
//        TypedQuery mockedQuery1 = mock(TypedQuery.class);
//        when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//        List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 //The result of the Query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add(new ArrayList<SsheetSpacingMappingModel>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(new SsheetSpacingMappingEntity());
//		 //The result of the Query1
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add(new ArrayList<SsheetSpacingMappingModel>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(new SsheetSpacingMappingEntity());
//		 //The result of the Query1
//		 ArrayList<SsheetSpacingMappingEntity> list = new ArrayList<>();
//		 list.add(null);
//		 list.add(new SsheetSpacingMappingEntity());
//		 scenario.get(2).add(list);
//		//Excpected Result
//		 ArrayList<SsheetSpacingMappingModel> list2 = new ArrayList<SsheetSpacingMappingModel>();
//		 list2.add( new SsheetSpacingMappingModel());
//		 scenario.get(2).add(list2);
//
//			for (int i = 0; i < scenario.size(); i++) {
//				System.out.println("searchSsheetSpacing [ " + i + " ]");
//				when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//
//				ArrayList<SsheetSpacingMappingModel> result = ((ArrayList<SsheetSpacingMappingModel>) ssheetRackingmapping
//						.searchSsheetSpacing((SsheetSpacingMappingEntity)scenario.get(i).get(0)));
//
//			}
//	}
//
//
//    @Test
//	public void testsearchDeletedSsheetRacking() {
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(SsheetRackingMappingEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(SsheetRackingMappingEntity.class)).thenReturn(root);
//        when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//
//        TypedQuery mockedQuery1 = mock(TypedQuery.class);
//        when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//        List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 //The result of the Query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add(new ArrayList<SsheetRackingMappingEntity>());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(new SsheetRackingMappingEntity());
//		 //The result of the Query1
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add(null);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(new SsheetRackingMappingEntity());
//		 //The result of the Query1
//		 ArrayList<SsheetRackingMappingEntity> list = new ArrayList<>();
//		 list.add(null);
//		 list.add(new SsheetRackingMappingEntity());
//		 scenario.get(2).add(list);
//		//Excpected Result
//		 scenario.get(2).add(list);
//
//			for (int i = 0; i < scenario.size(); i++) {
//				System.out.println("searchDeletedSsheetRacking [ " + i + " ]");
//				when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//
//				ArrayList<SsheetRackingMappingEntity> result = ((ArrayList<SsheetRackingMappingEntity>) ssheetRackingmapping
//						.searchDeletedSsheetRacking((SsheetRackingMappingEntity)scenario.get(i).get(0)));
//
//			}
//	}
//
//
//	@Test
//	public void testuploadHomePicture() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.pdfName = :p1 AND u.isDeleted = :p2 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetRackingMappingEntity u WHERE u.sSheetFile.id = :p1 AND "
//				+ "u.roofType = :p2 AND u.roofTypeOther = :p13 AND "
//				+ "u.rackingManufacturer = :p3 AND "
//				+ "u.rackingModel = :p4 AND " +
//				" u.roofManufacturer = :p5 AND u.roofModel = :p6 "
//				+ "AND u.flashingManufacturer = :p7 AND u.sheetNumber = :p8 "
//				+ "AND u.quadNumber = :p9 AND u.detailsHeading = :p10 "
//				+ "AND u.ahj = :p11 AND u.utilityCompany = :p12 AND u.isDeleted =:p14 ")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.id = :p1")).thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //The result of the Query1
//		 scenario.get(0).add(null);
//		 //The result of the Query2
//		 scenario.get(0).add(null);
//		 //The result of the Query3
//		 scenario.get(0).add(null);
//		 //The result of the Query4
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add("");
//		 //The result of the Query1
//		 scenario.get(1).add(null);
//		 //The result of the Query2
//		 scenario.get(1).add(null);
//		 //The result of the Query3
//		 scenario.get(1).add(null);
//		 //The result of the Query4
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(null);
//		 scenario.get(2).add("1235");
//		 //The result of the Query1
//		 scenario.get(2).add(null);
//		 //The result of the Query2
//		 scenario.get(2).add(null);
//		 //The result of the Query3
//		 scenario.get(2).add(null);
//		 //The result of the Query4
//		 scenario.get(2).add(null);
//		//Excpected Result
//		 scenario.get(2).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 RackingDetailsMappingModel rail = new RackingDetailsMappingModel();
//		 rail.setSsheet("122");
//		 scenario.get(3).add(rail);
//		 scenario.get(3).add("1235");
//		 //The result of the Query1
//		 scenario.get(3).add(null);
//		 //The result of the Query2
//		 scenario.get(3).add(null);
//		 //The result of the Query3
//		 scenario.get(3).add(null);
//		 //The result of the Query4
//		 scenario.get(3).add(null);
//		//Excpected Result
//		 scenario.get(3).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add(rail);
//		 scenario.get(4).add("1235");
//		 //The result of the Query1
//		 scenario.get(4).add(null);
//		 //The result of the Query2
//		 scenario.get(4).add(null);
//		 //The result of the Query3
//		 scenario.get(4).add(null);
//		 //The result of the Query4
//		 scenario.get(4).add(new SsheetLibraryEntity());
//		//Excpected Result
//		 scenario.get(4).add("Done");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(5).add(rail);
//		 scenario.get(5).add("1235");
//		 //The result of the Query1
//		 scenario.get(5).add(null);
//		 //The result of the Query2
//		 scenario.get(5).add(null);
//		 //The result of the Query3
//		 ArrayList<SsheetRackingMappingEntity> list2 = new ArrayList<>();
//		 list2.add(null);
//		 list2.add(new SsheetRackingMappingEntity());
//		 scenario.get(5).add(list2);
//		 //The result of the Query4
//		 scenario.get(5).add(new SsheetLibraryEntity());
//		//Excpected Result
//		 scenario.get(5).add("exist");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 RackingDetailsMappingModel rail2 = new RackingDetailsMappingModel();
//		 rail2.setUploadSheet(true);
//		 scenario.get(6).add(rail2);
//		 scenario.get(6).add("1235");
//		 //The result of the Query1
//		 scenario.get(6).add(null);
//		 //The result of the Query2
//		 scenario.get(6).add(null);
//		 //The result of the Query3
//		 scenario.get(6).add(list2);
//		 //The result of the Query4
//		 scenario.get(6).add(new SsheetLibraryEntity());
//		//Excpected Result
//		 scenario.get(6).add("Done");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(7).add(rail2);
//		 scenario.get(7).add("1235");
//		 //The result of the Query1
//		 scenario.get(7).add(null);
//		 //The result of the Query2
//		 ArrayList<SsheetLibraryEntity> list3 = new ArrayList<>();
//		 list3.add(null);
//		 scenario.get(7).add(list3);
//		 //The result of the Query3
//		 scenario.get(7).add(list2);
//		 //The result of the Query4
//		 scenario.get(7).add(new SsheetLibraryEntity());
//		//Excpected Result
//		 scenario.get(7).add("exist");
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("uploadHomePicture [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//             when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//             when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(4));
//             when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(5));
//
//             ssheetRackingmapping.uploadHomePicture( (RackingDetailsMappingModel) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//
//		 }
//	}
//
//	@Test
//	public void testaddSpacingLogic() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.id = :p1 AND u.isDeleted = :p2")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetSpacingMappingEntity u WHERE u.sSheetFile.id = :p1 AND "
//				+ "u.roofType = :p2 AND "
//				+ "u.rafterTrussSpacing = :p3 AND "
//				+ "u.stanchionMaxSpacing = :p4 AND " +
//				" u.sheetNumber = :p5 AND u.quadNumber = :p6 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //The result of the Query1
//		 scenario.get(0).add(null);
//		 //The result of the Query2
//		 scenario.get(0).add(null);
//		 //The result of the Query3
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("Fail");
//		//The result list of the Query1
//		 scenario.get(0).add(null);
//		 //The Result list of the Query2
//		 scenario.get(0).add(null);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(new SpacingDetailsMappingModel());
//		 scenario.get(1).add(null);
//		 //The result of the Query1
//		 scenario.get(1).add(null);
//		 //The result of the Query2
//		 scenario.get(1).add(null);
//		 //The result of the Query3
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add("Fail");
//		//The result list of the Query1
//		 scenario.get(1).add(null);
//		 //The Result list of the Query2
//		 scenario.get(1).add(null);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 SpacingDetailsMappingModel rail = new SpacingDetailsMappingModel();
//		 rail.setSsheet("125");
//		 scenario.get(2).add(rail);
//		 scenario.get(2).add(null);
//		 //The result of the Query1
//		 scenario.get(2).add(null);
//		 //The result of the Query2
//		 scenario.get(2).add(null);
//		 //The result of the Query3
//		 scenario.get(2).add(null);
//		//Excpected Result
//		 scenario.get(2).add("Fail");
//		//The result list of the Query1
//		 scenario.get(2).add(null);
//		 //The Result list of the Query2
//		 scenario.get(2).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(rail);
//		 scenario.get(3).add(null);
//		 //The result of the Query1
//		 scenario.get(3).add(null);
//		 //The result of the Query2
//		 scenario.get(3).add(null);
//		 //The result of the Query3
//		 scenario.get(3).add(null);
//		//Excpected Result
//		 scenario.get(3).add("Fail");
//		 //The result list of the Query1
//		 ArrayList<SsheetLibraryEntity> list = new ArrayList<>();
//		 list.add(null);
//		 scenario.get(3).add(list);
//		 //The Result list of the Query2
//		 scenario.get(3).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add(rail);
//		 scenario.get(4).add("");
//		 //The result of the Query1
//		 scenario.get(4).add(null);
//		 //The result of the Query2
//		 scenario.get(4).add(null);
//		 //The result of the Query3
//		 scenario.get(4).add(null);
//		//Excpected Result
//		 scenario.get(4).add("Fail");
//		 //The result list of the Query1
//		 scenario.get(4).add(list);
//		 //The Result list of the Query2
//		 scenario.get(4).add(null);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(5).add(rail);
//		 scenario.get(5).add("123");
//		 //The result of the Query1
//		 scenario.get(5).add(null);
//		 //The result of the Query2
//		 scenario.get(5).add(null);
//		 //The result of the Query3
//		 scenario.get(5).add(null);
//		//Excpected Result
//		 scenario.get(5).add("Fail");
//		 //The result list of the Query1
//		 scenario.get(5).add(list);
//		 //The Result list of the Query2
//		 scenario.get(5).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(6).add(rail);
//		 scenario.get(6).add("123");
//		 //The result of the Query1
//		 scenario.get(6).add(new SsheetLibraryEntity());
//		 //The result of the Query2
//		 scenario.get(6).add(null);
//		 //The result of the Query3
//		 scenario.get(6).add(null);
//		//Excpected Result
//		 scenario.get(6).add("Done");
//		 //The result list of the Query1
//		 scenario.get(6).add(list);
//		 //The Result list of the Query2
//		 scenario.get(6).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(7).add(rail);
//		 scenario.get(7).add("123");
//		 //The result of the Query1
//		 scenario.get(7).add(new SsheetLibraryEntity());
//		 //The result of the Query2
//		 scenario.get(7).add(null);
//		 //The result of the Query3
//		 scenario.get(7).add(null);
//		//Excpected Result
//		 scenario.get(7).add("exist");
//		 //The result list of the Query1
//		 scenario.get(7).add(list);
//		 //The Result list of the Query2
//		 ArrayList<SsheetSpacingMappingEntity> list2 = new ArrayList<>();
//		 list2.add(null);
//		 scenario.get(7).add(list2);
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("addSpacingLogic [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//             when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(4));
//
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(6));
//             when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(7));
//
//             ssheetRackingmapping.addSpacingLogic( (SpacingDetailsMappingModel) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//
//		 }
//	}
//
//	@Test
//	public void testaddLogic() throws IOException {
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("error");
//
//		 scenario.add(new ArrayList<Object>());
//
//			if(!new File("C:\\files\\orig.png").exists()) {
//				new File("C:\\files\\orig.png").createNewFile();
//			}
//			FileInputStream inputFile = new FileInputStream("C:\\files\\orig.png");
//
//		MockMultipartFile file = new MockMultipartFile("file", "NameOfTheFile", "multipart/form-data", inputFile);
//		 scenario.get(1).add(file);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add("Done");
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("editSpacingMapping [ "+i+" ]");
//             ssheetRackingmapping.addLogic( (MultipartFile) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
//
//		 }
//	}
//
//	@Test
//	public void testaddNewSSheet() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.pdfName = :p1 AND u.isDeleted = :p2")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		 //Result of query2
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("error::");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add("");
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of query1
//		 scenario.get(1).add(null);
//		 //Result of query2
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add("error::");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(null);
//		 scenario.get(2).add("12589");
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //Result of query1
//		 scenario.get(2).add(null);
//		 //Result of query2
//		 scenario.get(2).add(null);
//		//Excpected Result
//		 scenario.get(2).add("error::");
//
//		 scenario.add(new ArrayList<Object>());
//			if(!new File("C:\\files\\orig.png").exists()) {
//				new File("C:\\files\\orig.png").createNewFile();
//			}
//			FileInputStream inputFile = new FileInputStream("C:\\files\\orig.png");
//
//		MockMultipartFile file = new MockMultipartFile("file", "NameOfTheFile", "multipart/form-data", inputFile);
//		 scenario.get(3).add(file);
//		 scenario.get(3).add("12589");
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //Result of query1
//		 scenario.get(3).add(null);
//		 //Result of query2
//		 scenario.get(3).add(null);
//		//Excpected Result
//		 scenario.get(3).add("Done::null");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add(file);
//		 scenario.get(4).add("12589");
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 //Result of query1
//		 ArrayList<SsheetLibraryEntity> list = new ArrayList<>();
//		 list.add(null);
//		 list.add(new SsheetLibraryEntity());
//		 scenario.get(4).add(list);
//		 //Result of query2
//		 scenario.get(4).add(null);
//		//Excpected Result
//		 scenario.get(4).add("exist::");
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("editSpacingMapping [ "+i+" ]");
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(5));
//             ssheetRackingmapping.addNewSSheet( (MultipartFile) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
//
//		 }
//
//
//	}
//
//	@Test
//	public void testgetSsheetspdfs() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.isDeleted = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 scenario.add(new ArrayList<Object>());
//		 //Result of query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //Result of query1
//		 ArrayList<SsheetLibraryEntity> list = new ArrayList<>();
//		 list.add(null);
//		 list.add(new SsheetLibraryEntity());
//		 scenario.get(1).add(list);
//		//Excpected Result
//		 scenario.get(1).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getSsheetspdfs [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//
//			ArrayList<SsheetLibraryEntity> result = ((ArrayList<SsheetLibraryEntity>) ssheetRackingmapping
//					.getSsheetspdfs());
//
//		}
//	}
//
//	@Test
//	public void testeditRackingMapping() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from SsheetLibraryEntity u WHERE u.pdfName = :p1 AND u.id != :p2 AND (u.isDeleted = :p3 OR u.isDeleted = :p4) "))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from SsheetRackingMappingEntity u WHERE u.id = :p1 AND (u.isDeleted = :p2 OR u.isDeleted = :p3) "))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from SsheetRackingMappingEntity u WHERE u.id = :p1  AND (u.isDeleted = :p2 OR u.isDeleted = :p3) "))
//						.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.id = :p1")).thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		// Result of query3
//		scenario.get(0).add(null);
//		// Result of query4
//		scenario.get(0).add(null);
//		// Result of query5
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(new RackingDetailsMappingModel());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Result of query3
//		scenario.get(1).add(null);
//		// Result of query4
//		scenario.get(1).add(null);
//		// Result of query5
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(new RackingDetailsMappingModel());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of query1
//		scenario.get(2).add(null);
//		// Result of query2
//		scenario.get(2).add(null);
//		// Result of query3
//		scenario.get(2).add(null);
//		// Result of query4
//		scenario.get(2).add(new SsheetRackingMappingEntity());
//		// Result of query5
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		RackingDetailsMappingModel r = new RackingDetailsMappingModel();
//		r.setSsheet("12333");
//		scenario.get(3).add(r);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of query1
//		scenario.get(3).add(null);
//		// Result of query2
//		scenario.get(3).add(null);
//		// Result of query3
//		scenario.get(3).add(null);
//		// Result of query4
//		scenario.get(3).add(new SsheetRackingMappingEntity());
//		// Result of query5
//		scenario.get(3).add(null);
//		// Excpected Result
//		scenario.get(3).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add(r);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of query1
//		scenario.get(4).add(null);
//		// Result of query2
//		scenario.get(4).add(null);
//		// Result of query3
//		scenario.get(4).add(null);
//		// Result of query4
//		scenario.get(4).add(new SsheetRackingMappingEntity());
//		// Result of query5
//		scenario.get(4).add(new SsheetLibraryEntity());
//		// Excpected Result
//		scenario.get(4).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(null);
//		RackingDetailsMappingModel r1 = new RackingDetailsMappingModel();
//		r1.setUploadSheet(true);
//		scenario.get(5).add(r1);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		// Result of query1
//		scenario.get(5).add(null);
//		// Result of query2
//		scenario.get(5).add(null);
//		// Result of query3
//		scenario.get(5).add(null);
//		// Result of query4
//		scenario.get(5).add(new SsheetRackingMappingEntity());
//		// Result of query5
//		scenario.get(5).add(new SsheetLibraryEntity());
//		// Excpected Result
//		scenario.get(5).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(6).add(null);
//		scenario.get(6).add(r1);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		// Result of query1
//		ArrayList<SsheetLibraryEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(6).add(list);
//		// Result of query2
//		scenario.get(6).add(null);
//		// Result of query3
//		scenario.get(6).add(null);
//		// Result of query4
//		scenario.get(6).add(new SsheetRackingMappingEntity());
//		// Result of query5
//		scenario.get(6).add(new SsheetLibraryEntity());
//		// Excpected Result
//		scenario.get(6).add("exist");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(7).add("");
//		scenario.get(7).add(r1);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		// Result of query1
//		scenario.get(7).add(null);
//		// Result of query2
//		scenario.get(7).add(null);
//		// Result of query3
//		scenario.get(7).add(null);
//		// Result of query4
//		scenario.get(7).add(new SsheetRackingMappingEntity());
//		// Result of query5
//		scenario.get(7).add(new SsheetLibraryEntity());
//		// Excpected Result
//		scenario.get(7).add("Fail");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(8).add("1255");
//		scenario.get(8).add(r1);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		// Result of query1
//		scenario.get(8).add(null);
//		// Result of query2
//		scenario.get(8).add(null);
//		// Result of query3
//		scenario.get(8).add(null);
//		// Result of query4
//		scenario.get(8).add(new SsheetRackingMappingEntity());
//		// Result of query5
//		scenario.get(8).add(new SsheetLibraryEntity());
//		// Excpected Result
//		scenario.get(8).add("Fail");
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(9).add("1255");
//		scenario.get(9).add(r1);
//		scenario.get(9).add(null);
//		scenario.get(9).add(null);
//		// Result of query1
//		scenario.get(9).add(null);
//		// Result of query2
//		scenario.get(9).add(new SsheetRackingMappingEntity());
//		// Result of query3
//		scenario.get(9).add(null);
//		// Result of query4
//		scenario.get(9).add(new SsheetRackingMappingEntity());
//		// Result of query5
//		scenario.get(9).add(new SsheetLibraryEntity());
//		// Excpected Result
//		scenario.get(9).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editRackingMapping [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(6));
//			when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(8));
//					ssheetRackingmapping.editRackingMapping((String) scenario.get(i).get(0),
//							(RackingDetailsMappingModel) scenario.get(i).get(1), (Integer) scenario.get(i).get(2),
//							(Integer) scenario.get(i).get(3));
//
//		}
//	}
//
//	@Test
//	public void testeditSpacingMapping() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetSpacingMappingEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		 //Result of query2
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("Fail");
//		 //The result of the Query1
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(new SpacingDetailsMappingModel());
//		 scenario.get(1).add(null);
//		 //Result of query1
//		 scenario.get(1).add(null);
//		 //Result of query2
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add("Fail");
//		 //The result of the Query1
//		 scenario.get(1).add(null);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 SpacingDetailsMappingModel sp = new SpacingDetailsMappingModel();
//		 sp.setSsheet("123");
//		 scenario.get(2).add(sp);
//		 scenario.get(2).add(null);
//		 //Result of query1
//		 scenario.get(2).add(null);
//		 //Result of query2
//		 scenario.get(2).add(null);
//		//Excpected Result
//		 scenario.get(2).add("Fail");
//		 //The result of the Query1
//		 scenario.get(2).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(sp);
//		 scenario.get(3).add(null);
//		 //Result of query1
//		 scenario.get(3).add(null);
//		 //Result of query2
//		 scenario.get(3).add(null);
//		//Excpected Result
//		 scenario.get(3).add("Fail");
//		 //The result of the Query1
//		 ArrayList<SsheetLibraryEntity> list = new ArrayList<>();
//		 list.add(null);
//		 scenario.get(3).add(list);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add(sp);
//		 scenario.get(4).add("");
//		 //Result of query1
//		 scenario.get(4).add(null);
//		 //Result of query2
//		 scenario.get(4).add(null);
//		//Excpected Result
//		 scenario.get(4).add("Fail");
//		 //The result of the Query1
//		 scenario.get(4).add(list);
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(5).add(sp);
//		 scenario.get(5).add("1258");
//		 //Result of query1
//		 scenario.get(5).add(null);
//		 //Result of query2
//		 scenario.get(5).add(null);
//		//Excpected Result
//		 scenario.get(5).add("Fail");
//		 //The result of the Query1
//		 scenario.get(5).add(list);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(6).add(sp);
//		 scenario.get(6).add("1258");
//		 //Result of query1
//		 scenario.get(6).add(new SsheetLibraryEntity());
//		 //Result of query2
//		 scenario.get(6).add(new SsheetSpacingMappingEntity());
//		//Excpected Result
//		 scenario.get(6).add("Done");
//		 //The result of the Query1
//		 scenario.get(6).add(list);
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("editSpacingMapping [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(5));
//             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//             ssheetRackingmapping.editSpacingMapping( (SpacingDetailsMappingModel) scenario.get(i).get(0),(String) scenario.get(i).get(1), "");
//
//		 }
//	}
//
//	@Test
//	public void testdeleteRackingMapping() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetRackingMappingEntity u WHERE u.id = :p1  AND (u.isDeleted = :p2 OR u.isDeleted = :p3) ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		 //Result of query2
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 //Result of query1
//		 scenario.get(1).add(null);
//		 //Result of query2
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add("fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 //Result of query1
//		 scenario.get(2).add(null);
//		 //Result of query2
//		 scenario.get(2).add(null);
//		//Excpected Result
//		 scenario.get(2).add("fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 //Result of query1
//		 scenario.get(3).add(null);
//		 //Result of query2
//		 scenario.get(3).add(new SsheetRackingMappingEntity());
//		//Excpected Result
//		 scenario.get(3).add("done");
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("deleteSpacingMapping [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//             ssheetRackingmapping.deleteRackingMapping( (String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//
//		 }
//	}
//
//	@Test
//	public void testdeleteSpacingMapping() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetSpacingMappingEntity u WHERE u.id = :p1  AND (u.isDeleted = :p2 OR u.isDeleted = :p3) ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		 //Result of query2
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 //Result of query1
//		 scenario.get(1).add(null);
//		 //Result of query2
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add("fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 //Result of query1
//		 scenario.get(2).add(null);
//		 //Result of query2
//		 scenario.get(2).add(null);
//		//Excpected Result
//		 scenario.get(2).add("fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 //Result of query1
//		 scenario.get(3).add(null);
//		 //Result of query2
//		 scenario.get(3).add(new SsheetSpacingMappingEntity());
//		//Excpected Result
//		 scenario.get(3).add("done");
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("deleteSpacingMapping [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//            ssheetRackingmapping.deleteSpacingMapping( (String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//
//		 }
//	}
//
//	@Test
//	public void testgetIdSheetFromLibrary() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.pdfName = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		 //The result list of the Query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add(0);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(null);
//		 //Result of query1
//		 scenario.get(1).add(null);
//		 //The result list of the Query1
//		 ArrayList<SsheetLibraryEntity> list = new ArrayList<SsheetLibraryEntity>();
//		 list.add(null);
//		 scenario.get(1).add(list);
//		//Excpected Result
//		 scenario.get(1).add(0);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(null);
//		 //Result of query1
//		 SsheetLibraryEntity s = new SsheetLibraryEntity();
//		 s.setId(123);
//		 scenario.get(2).add(s);
//		 //The result list of the Query1
//		 scenario.get(2).add(list);
//		//Excpected Result
//		 scenario.get(2).add(123);
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getSiteSurveyReqLogField [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//             ssheetRackingmapping.getIdSheetFromLibrary( (String) scenario.get(i).get(0));
//
//		 }
//	}
//}
