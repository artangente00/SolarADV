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
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.Entity.RsheetsLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.SsheetLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.TLDSheetLibraryEntity;
//import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
//import com.PlayGroundAdv.Solar.model.SRsheetsModel;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.SsheetLibraryService;
//import com.PlayGroundAdv.Solar.Services.TLDSheetLibraryService;
//
//public class TestTLDSheetLibraryService {
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
//	TLDSheetLibraryService tLDSheetLibraryService = new TLDSheetLibraryService();
//
//
//    @Before
//	public void setupMock() {
//    	tLDSheetLibraryService = new TLDSheetLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//
//	@Test
//	public void testgetAllTLDsheet() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from TLDSheetLibraryEntity u WHERE u.isDeleted = :p1 ORDER BY u.pdfName"))
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
//		ArrayList<TLDSheetLibraryEntity> list = new ArrayList<TLDSheetLibraryEntity>();
//		list.add(null);
//		list.add(new TLDSheetLibraryEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		ArrayList<SRsheetsModel> listE = new ArrayList<SRsheetsModel>();
//		SRsheetsModel e = new SRsheetsModel();
//		e.setIsDeleted(false);
//		listE.add(e);
//		scenario.get(1).add(listE);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllSsheet [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			ArrayList<SRsheetsModel> rslt = (ArrayList<SRsheetsModel>) tLDSheetLibraryService.getAllTLDsheet();
//
//		}
//
//	}
//
//	@Test
//	public void testgetDeletedAllTLDsheet() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from TLDSheetLibraryEntity u WHERE u.isDeleted = :p1 ORDER BY u.pdfName"))
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
//		ArrayList<TLDSheetLibraryEntity> list = new ArrayList<TLDSheetLibraryEntity>();
//		list.add(null);
//		list.add(new TLDSheetLibraryEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		ArrayList<SRsheetsModel> listE = new ArrayList<SRsheetsModel>();
//		SRsheetsModel e = new SRsheetsModel();
//		e.setIsDeleted(false);
//		listE.add(e);
//		scenario.get(1).add(listE);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getDeletedAllTLDsheet [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			ArrayList<SRsheetsModel> rslt = (ArrayList<SRsheetsModel>) tLDSheetLibraryService.getDeletedAllTLDsheet();
//
//		}
//	}
//
//	@Test
//	public void testsearchTLDsheet() {
//
//
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		Path battery = mock(Path.class);
//
//		when(criteriaBuilder.createQuery(TLDSheetLibraryEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(TLDSheetLibraryEntity.class)).thenReturn(root);
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
//		ArrayList<TLDSheetLibraryEntity> list = new ArrayList<TLDSheetLibraryEntity>();
//		list.add(null);
//		list.add(new TLDSheetLibraryEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		ArrayList<SRsheetsModel> listE = new ArrayList<SRsheetsModel>();
//		SRsheetsModel e = new SRsheetsModel();
//		e.setIsDeleted(false);
//		listE.add(e);
//		scenario.get(1).add(listE);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("searchTLDsheet [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			ArrayList<SRsheetsModel> rslt = (ArrayList<SRsheetsModel>) tLDSheetLibraryService
//					.searchTLDsheet((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//
//		}
//
//
//	}
//
//	@Test
//	public void testsearchDeletedTLDsheet() {
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		Path battery = mock(Path.class);
//
//		when(criteriaBuilder.createQuery(TLDSheetLibraryEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(TLDSheetLibraryEntity.class)).thenReturn(root);
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
//		ArrayList<TLDSheetLibraryEntity> list = new ArrayList<TLDSheetLibraryEntity>();
//		list.add(null);
//		list.add(new TLDSheetLibraryEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		ArrayList<SRsheetsModel> listE = new ArrayList<SRsheetsModel>();
//		SRsheetsModel e = new SRsheetsModel();
//		e.setIsDeleted(false);
//		listE.add(e);
//		scenario.get(1).add(listE);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("searchDeletedTLDsheet [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			ArrayList<SRsheetsModel> rslt = (ArrayList<SRsheetsModel>) tLDSheetLibraryService
//					.searchDeletedTLDsheet((String) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testuploadHomePicture() throws IOException {
//
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from TLDSheetLibraryEntity u WHERE u.pdfName = :p1 AND u.isDeleted = :p2")).thenReturn(mockedQuery1);
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
//		scenario.get(3).add("Done");
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
//					tLDSheetLibraryService.uploadHomePicture((MultipartFile) scenario.get(i).get(0), (String) scenario.get(i).get(1), (String) scenario.get(i).get(2));
//
//		}
//
//
//	}
//
//	@Test
//	public void testdeleteTLDsheet() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from TLDSheetLibraryEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
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
//		scenario.get(5).add(new TLDSheetLibraryEntity());
//		// Result excpected
//		scenario.get(5).add("done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deleteTLDsheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			 tLDSheetLibraryService
//					.deleteTLDsheet((String) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testeditTLDsheet() throws IOException {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from TLDSheetLibraryEntity u WHERE u.pdfName = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		TLDSheetLibraryService tLDSheetLibraryService2 = Mockito.spy(tLDSheetLibraryService);
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
//		ArrayList<TLDSheetLibraryEntity> list = new ArrayList<TLDSheetLibraryEntity>();
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
//		scenario.get(2).add(new TLDSheetLibraryEntity());
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
//		TLDSheetLibraryEntity r = new TLDSheetLibraryEntity();
//		r.setId(123);
//		scenario.get(3).add(r);
//		// Result excpected
//		scenario.get(3).add("exist");
//		// Result of the query1 List
//		scenario.get(3).add(list);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editSSheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(7));
//			Mockito.doReturn(scenario.get(i).get(6)).when(tLDSheetLibraryService2).editTLDsheetFunction(
//					(MultipartFile) scenario.get(i).get(0), (String) scenario.get(i).get(1),
//					(String) scenario.get(i).get(2), (String) scenario.get(i).get(3));
//
//					tLDSheetLibraryService2.editTLDsheet((MultipartFile) scenario.get(i).get(0),
//							(String) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3));
//
//		}
//
//	}
//
//	@Test
//	public void testeditTLDsheetFunction() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from TLDSheetLibraryEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
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
//		scenario.get(3).add(new TLDSheetLibraryEntity());
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
//		scenario.get(4).add(new TLDSheetLibraryEntity());
//		// Result excpected
//		scenario.get(4).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editSSheetFunction [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(5));
//
//					tLDSheetLibraryService.editTLDsheetFunction((MultipartFile) scenario.get(i).get(0),
//							(String) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3));
//
//		}
//
//	}
//
//	@Test
//	public void testrestoreTLDsheet() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from TLDSheetLibraryEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from TLDSheetLibraryEntity u WHERE u.pdfName = :p1 AND u.isDeleted = :p2"))
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
//		scenario.get(3).add(new TLDSheetLibraryEntity());
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(new TLDSheetLibraryEntity());
//		// Result of the query2
//		scenario.get(4).add(new ArrayList<TLDSheetLibraryEntity>());
//		// Result excpected
//		scenario.get(4).add("done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("123");
//		// Result of the query1
//		scenario.get(5).add(new TLDSheetLibraryEntity());
//		// Result of the query2
//		ArrayList<TLDSheetLibraryEntity> list = new ArrayList<TLDSheetLibraryEntity>();
//		list.add(null);
//		list.add(new TLDSheetLibraryEntity());
//		scenario.get(5).add(list);
//		// Result excpected
//		scenario.get(5).add("exist");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("restoreTLDsheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//
//					tLDSheetLibraryService.restoreTLDsheet((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testeditTLDsheetNotification() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
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
//		scenario.get(0).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(3).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add(null);
//		// Result of the query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setRoleEntity(new RoleEntity());
//		scenario.get(4).add(auth);
//		// Result excpected
//		scenario.get(4).add("Success");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editTLDsheetNotification [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			 tLDSheetLibraryService
//					.editTLDsheetNotification((String) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testdeleteTLDsheetNotification() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
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
//		scenario.get(0).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(3).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add(null);
//		// Result of the query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setRoleEntity(new RoleEntity());
//		scenario.get(4).add(auth);
//		// Result excpected
//		scenario.get(4).add("Success");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deleteTLDsheetNotification [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			tLDSheetLibraryService
//					.deleteTLDsheetNotification((String) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testloadSheets() {
//
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
//		scenario.get(0).add("Success");
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(1).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(1).add("Success");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("loadSheets [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(0));
//			tLDSheetLibraryService.loadSheets();
//
//		}
//
//	}
//
//	@Test
//	public void testsearchShotList() {
//
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		Path battery = mock(Path.class);
//
//		when(criteriaBuilder.createQuery(TLDSheetLibraryEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(TLDSheetLibraryEntity.class)).thenReturn(root);
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
//		scenario.get(1).add(new GetPermitByIdResult());
//		scenario.get(1).add(null);
//		// Result of the query1
//		ArrayList<TLDSheetLibraryEntity> list = new ArrayList<TLDSheetLibraryEntity>();
//		list.add(null);
//		list.add(new TLDSheetLibraryEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		ArrayList<SRsheetsModel> listE = new ArrayList<SRsheetsModel>();
//		SRsheetsModel e = new SRsheetsModel();
//		e.setIsDeleted(false);
//		listE.add(e);
//		scenario.get(1).add(listE);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("searchTLDsheet [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			ArrayList<SRsheetsModel> rslt = (ArrayList<SRsheetsModel>) tLDSheetLibraryService
//					.searchShotList((GetPermitByIdResult) scenario.get(i).get(0));
//
//		}
//
//	}
//
//
//}
