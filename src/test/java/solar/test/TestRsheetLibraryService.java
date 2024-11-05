//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
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
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.RailRacking;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.Entity.RsheetsLibraryEntity;
//import com.PlayGroundAdv.Solar.model.LeasePPAMeterCorrectionRequest;
//import com.PlayGroundAdv.Solar.model.SRsheetsModel;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.RailRackingLibraryService;
//import com.PlayGroundAdv.Solar.Services.RsheetLibraryService;
//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
//
//public class TestRsheetLibraryService {
//
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
//	RsheetLibraryService rsheetLibraryService = new RsheetLibraryService();
//
//
//    @Before
//	public void setupMock() {
//    	rsheetLibraryService = new RsheetLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//
//	@Test
//	public void testgetAllRsheet() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RsheetsLibraryEntity u WHERE u.isDeleted = :p1 ORDER BY u.pdfName"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<SRsheetsModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<RsheetsLibraryEntity>();
//		list.add(null);
//		list.add(new RsheetsLibraryEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		ArrayList<SRsheetsModel> listE = new ArrayList<SRsheetsModel>();
//		SRsheetsModel e = new SRsheetsModel();
//		e.setIsDeleted(false);
//		listE.add(e);
//		scenario.get(1).add(listE);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllRsheet [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			ArrayList<SRsheetsModel> rslt = (ArrayList<SRsheetsModel>) rsheetLibraryService.getAllRsheet();
//
//		}
//
//	}
//
//	@Test
//	public void testgetDeletedAllRsheet() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RsheetsLibraryEntity u WHERE u.isDeleted = :p1 ORDER BY u.pdfName"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<SRsheetsModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<RsheetsLibraryEntity>();
//		list.add(null);
//		list.add(new RsheetsLibraryEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		ArrayList<SRsheetsModel> listE = new ArrayList<SRsheetsModel>();
//		SRsheetsModel e = new SRsheetsModel();
//		e.setIsDeleted(false);
//		listE.add(e);
//		scenario.get(1).add(listE);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getDeletedAllRsheet [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			ArrayList<SRsheetsModel> rslt = (ArrayList<SRsheetsModel>) rsheetLibraryService.getDeletedAllRsheet();
//
//		}
//
//	}
//
//	@Test
//	public void testsearch() {
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		Path battery = mock(Path.class);
//
//		when(criteriaBuilder.createQuery(RsheetsLibraryEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(RsheetsLibraryEntity.class)).thenReturn(root);
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
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<SRsheetsModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<RsheetsLibraryEntity>();
//		list.add(null);
//		list.add(new RsheetsLibraryEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		ArrayList<SRsheetsModel> listE = new ArrayList<SRsheetsModel>();
//		SRsheetsModel e = new SRsheetsModel();
//		e.setIsDeleted(false);
//		listE.add(e);
//		scenario.get(1).add(listE);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("search [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			ArrayList<SRsheetsModel> rslt = (ArrayList<SRsheetsModel>) rsheetLibraryService.search((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//
//		}
//	}
//
//	@Test
//	public void testsearchDeleted() {
//
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		Path battery = mock(Path.class);
//
//		when(criteriaBuilder.createQuery(RsheetsLibraryEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(RsheetsLibraryEntity.class)).thenReturn(root);
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
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<SRsheetsModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<RsheetsLibraryEntity>();
//		list.add(null);
//		list.add(new RsheetsLibraryEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		ArrayList<SRsheetsModel> listE = new ArrayList<SRsheetsModel>();
//		SRsheetsModel e = new SRsheetsModel();
//		e.setIsDeleted(false);
//		listE.add(e);
//		scenario.get(1).add(listE);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("searchDeleted [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			ArrayList<SRsheetsModel> rslt = (ArrayList<SRsheetsModel>) rsheetLibraryService.searchDeleted((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testuploadHomePicture() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RsheetsLibraryEntity u WHERE u.pdfName = :p1 AND u.isDeleted = :p2")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
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
//		// Result excpected
//		scenario.get(0).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		FileInputStream inputFile = new FileInputStream("C:\\files\\xxx.txt");
//		MockMultipartFile file = new MockMultipartFile("file", "NameOfTheFile", "multipart/form-data", inputFile);
//		scenario.get(3).add(file);
//		scenario.get(3).add(null);
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("null");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(file);
//		scenario.get(4).add(null);
//		scenario.get(4).add("123");
//		// Result of the query1
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<RsheetsLibraryEntity>();
//		list.add(null);
//		scenario.get(4).add(list);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("exist");
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("uploadHomePicture [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//
//					rsheetLibraryService.uploadHomePicture((MultipartFile) scenario.get(i).get(0), (String) scenario.get(i).get(1), (String) scenario.get(i).get(2));
//
//		}
//	}
//
//	@Test
//	public void testdeleteSheet() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from RsheetsLibraryEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
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
//		scenario.get(0).add("fail");
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
//		scenario.get(1).add("fail");
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
//		scenario.get(2).add("fail");
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
//		scenario.get(3).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("123");
//		scenario.get(5).add("123");
//		// Result of the query1
//		scenario.get(5).add(null);
//		// Result of the query2
//		scenario.get(5).add(new RsheetsLibraryEntity());
//		// Result excpected
//		scenario.get(5).add("done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editRSheetFunction [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//					rsheetLibraryService.deleteSheet((String) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testloadSheets() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("");
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(1).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(1).add("");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("loadSheets [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(0));
//			rsheetLibraryService.loadSheets();
//
//		}
//	}
//
//	@Test
//	public void testeditRSheet() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RsheetsLibraryEntity u WHERE u.pdfName = :p1 AND u.isDeleted = :p2")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		RsheetLibraryService rsheetLibraryService2 = Mockito.spy(rsheetLibraryService);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Done");
//		// Result of the query1 List
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Done");
//		// Result of the query1 List
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<RsheetsLibraryEntity>();
//		list.add(null);
//		scenario.get(1).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("1233");
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(new RsheetsLibraryEntity());
//		// Result excpected
//		scenario.get(2).add("fail");
//		// Result of the query1 List
//		scenario.get(2).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("1233");
//		scenario.get(3).add(null);
//		// Result of the query1
//		RsheetsLibraryEntity r = new RsheetsLibraryEntity();
//		r.setId(123);
//		scenario.get(3).add(r);
//		// Result excpected
//		scenario.get(3).add("exist");
//		// Result of the query1 List
//		scenario.get(3).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editRSheetFunction [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(7));
//			Mockito.doReturn(scenario.get(i).get(6)).when(rsheetLibraryService2).editRSheetFunction(
//					(MultipartFile) scenario.get(i).get(0), (String) scenario.get(i).get(1),
//					(String) scenario.get(i).get(2), (String) scenario.get(i).get(3), (String) scenario.get(i).get(4));
//
//					rsheetLibraryService2.editRSheet((MultipartFile) scenario.get(i).get(0),
//							(String) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3), (String) scenario.get(i).get(4));
//
//		}
//	}
//
//	@Test
//	public void testeditRSheetFunction() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RsheetsLibraryEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("1233");
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("1233");
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new RsheetsLibraryEntity());
//		// Result excpected
//		scenario.get(3).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//
//		MockMultipartFile file = null;
//		try {
//			FileInputStream inputFile = new FileInputStream("C:\\files\\xxx.txt");
//			file = new MockMultipartFile("file", "NameOfTheFile", "multipart/form-data", inputFile);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		scenario.get(4).add(file);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add("1233");
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add(new RsheetsLibraryEntity());
//		// Result excpected
//		scenario.get(4).add("null");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editRSheetFunction [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(5));
//
//					rsheetLibraryService.editRSheetFunction((MultipartFile) scenario.get(i).get(0),
//							(String) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3), (String) scenario.get(i).get(4));
//
//		}
//	}
//
//	@Test
//	public void testrestoreRsheet() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RsheetsLibraryEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from RsheetsLibraryEntity u WHERE u.pdfName = :p1 AND u.isDeleted = :p2"))
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
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(new RsheetsLibraryEntity());
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(new RsheetsLibraryEntity());
//		// Result of the query2
//		scenario.get(4).add(new ArrayList<RsheetsLibraryEntity>());
//		// Result excpected
//		scenario.get(4).add("done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("123");
//		// Result of the query1
//		scenario.get(5).add(new RsheetsLibraryEntity());
//		// Result of the query2
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<RsheetsLibraryEntity>();
//		list.add(null);
//		list.add(new RsheetsLibraryEntity());
//		scenario.get(5).add(list);
//		// Result excpected
//		scenario.get(5).add("exist");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("restoreRsheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//
//					rsheetLibraryService.restoreRsheet((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testeditRSheetNotification() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from AuthentificationEntity u WHERE u.id = :p1 "))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
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
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(3).add("fail");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add(null);
//		// Result of the query1
//		AuthentificationEntity auth = new  AuthentificationEntity();
//		auth.setRoleEntity(new RoleEntity());
//		scenario.get(4).add(auth);
//		// Result excpected
//		scenario.get(4).add("Success");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editRSheetNotification [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			rsheetLibraryService
//					.editRSheetNotification((String) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//	}
//
//	@Test
//	public void testdeleteRSheetNotification() {
//
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from AuthentificationEntity u WHERE u.id = :p1 "))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
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
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(3).add("fail");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add(null);
//		// Result of the query1
//		AuthentificationEntity auth = new  AuthentificationEntity();
//		auth.setRoleEntity(new RoleEntity());
//		scenario.get(4).add(auth);
//		// Result excpected
//		scenario.get(4).add("Success");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deleteRSheetNotification [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			rsheetLibraryService
//					.deleteRSheetNotification((String) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//	}
//
//	@Test
//	public void testgetRsheet() {
//
//
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from RsheetsLibraryEntity u WHERE u.id =:p1 "))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(new RsheetsLibraryEntity());
//		// Result excpected
//		scenario.get(2).add(new RsheetsLibraryEntity());
//
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getAllDeletedUtilityCompany [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn( scenario.get(i).get(1));
//			RsheetsLibraryEntity rslt = (RsheetsLibraryEntity)rsheetLibraryService.getRsheet((String) scenario.get(i).get(0));
//
//			}
//		}
//
//
//
//	@Test
//	public void testaddRsheet() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RsheetsLibraryEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of query1
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		// Result of query1
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new RsheetsLibraryEntity());
//		scenario.get(3).add("123");
//		// Result of query1
//		scenario.get(3).add(null);
//		// Excpected Result
//		scenario.get(3).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(new RsheetsLibraryEntity());
//		scenario.get(4).add("123");
//		// Result of query1
//		scenario.get(4).add(new RsheetsLibraryEntity());
//		// Excpected Result
//		scenario.get(4).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("addRsheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			rsheetLibraryService
//					.addRsheet((RsheetsLibraryEntity) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testupdateRSheet() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RsheetsLibraryEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Excpected Result
//		scenario.get(0).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of query1
//		scenario.get(1).add(null);
//		// Excpected Result
//		scenario.get(1).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		// Result of query1
//		scenario.get(2).add(null);
//		// Excpected Result
//		scenario.get(2).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new RsheetsLibraryEntity());
//		scenario.get(3).add("123");
//		// Result of query1
//		scenario.get(3).add(null);
//		// Excpected Result
//		scenario.get(3).add("Done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(new RsheetsLibraryEntity());
//		scenario.get(4).add("123");
//		// Result of query1
//		scenario.get(4).add(new RsheetsLibraryEntity());
//		// Excpected Result
//		scenario.get(4).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("updateRSheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			 rsheetLibraryService
//					.updateRSheet((RsheetsLibraryEntity) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//	}
//}
