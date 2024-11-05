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
//import com.PlayGroundAdv.Solar.model.SRsheetsModel;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.RsheetLibraryService;
//import com.PlayGroundAdv.Solar.Services.SsheetLibraryService;
//
//public class TestSsheetLibraryService {
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
//	SsheetLibraryService ssheetLibraryService = new SsheetLibraryService();
//
//
//    @Before
//	public void setupMock() {
//    	ssheetLibraryService = new SsheetLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testgetAllSsheet() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.isDeleted = :p1 ORDER BY u.pdfName"))
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
//		ArrayList<SsheetLibraryEntity> list = new ArrayList<SsheetLibraryEntity>();
//		list.add(null);
//		list.add(new SsheetLibraryEntity());
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
//			ArrayList<SRsheetsModel> rslt = (ArrayList<SRsheetsModel>) ssheetLibraryService.getAllSsheet();
//			}
//
//
//	}
//
//	@Test
//	public void testgetDeletedAllSsheet() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.isDeleted = :p1 ORDER BY u.pdfName"))
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
//		ArrayList<SsheetLibraryEntity> list = new ArrayList<SsheetLibraryEntity>();
//		list.add(null);
//		list.add(new SsheetLibraryEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		ArrayList<SRsheetsModel> listE = new ArrayList<SRsheetsModel>();
//		SRsheetsModel e = new SRsheetsModel();
//		e.setIsDeleted(false);
//		listE.add(e);
//		scenario.get(1).add(listE);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getDeletedAllSsheet [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			ArrayList<SRsheetsModel> rslt = (ArrayList<SRsheetsModel>) ssheetLibraryService.getDeletedAllSsheet();
//
//		}
//
//	}
//	@Test
//	public void testsearchSSheet() {
//
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		Path battery = mock(Path.class);
//
//		when(criteriaBuilder.createQuery(SsheetLibraryEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(SsheetLibraryEntity.class)).thenReturn(root);
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
//		ArrayList<SsheetLibraryEntity> list = new ArrayList<SsheetLibraryEntity>();
//		list.add(null);
//		list.add(new SsheetLibraryEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		ArrayList<SRsheetsModel> listE = new ArrayList<SRsheetsModel>();
//		SRsheetsModel e = new SRsheetsModel();
//		e.setIsDeleted(false);
//		listE.add(e);
//		scenario.get(1).add(listE);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("searchSSheet [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			ArrayList<SRsheetsModel> rslt = (ArrayList<SRsheetsModel>) ssheetLibraryService
//					.searchSSheet((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testsearchDeletedSSheet() {
//
//		TypedQuery mockedQuery1 = mock(TypedQuery.class);
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		Path battery = mock(Path.class);
//
//		when(criteriaBuilder.createQuery(SsheetLibraryEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(SsheetLibraryEntity.class)).thenReturn(root);
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
//		ArrayList<SsheetLibraryEntity> list = new ArrayList<SsheetLibraryEntity>();
//		list.add(null);
//		list.add(new SsheetLibraryEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		ArrayList<SRsheetsModel> listE = new ArrayList<SRsheetsModel>();
//		SRsheetsModel e = new SRsheetsModel();
//		e.setIsDeleted(false);
//		listE.add(e);
//		scenario.get(1).add(listE);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("searchDeleted [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			ArrayList<SRsheetsModel> rslt = (ArrayList<SRsheetsModel>) ssheetLibraryService
//					.searchDeletedSSheet((String) scenario.get(i).get(0));
//
//		}
//
//	}
//
//	@Test
//	public void testuploadHomePicture() throws IOException {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.pdfName = :p1 AND u.isDeleted = :p2")).thenReturn(mockedQuery1);
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
//		 ssheetLibraryService.uploadHomePicture((MultipartFile) scenario.get(i).get(0), (String) scenario.get(i).get(1), (String) scenario.get(i).get(2));
//
//		}
//
//	}
//
//	@Test
//	public void testdeleteSSheet() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
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
//		scenario.get(5).add(new SsheetLibraryEntity());
//		// Result excpected
//		scenario.get(5).add("done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deleteSSheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			 ssheetLibraryService
//					.deleteSSheet((String) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testeditSSheet() throws IOException {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.pdfName = :p1 AND u.isDeleted = :p2 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		SsheetLibraryService ssheetLibraryService2 = Mockito.spy(ssheetLibraryService);
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
//		ArrayList<SsheetLibraryEntity> list = new ArrayList<SsheetLibraryEntity>();
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
//		scenario.get(2).add(new SsheetLibraryEntity());
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
//		SsheetLibraryEntity r = new SsheetLibraryEntity();
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
//			Mockito.doReturn(scenario.get(i).get(6)).when(ssheetLibraryService2).editSSheetFunction(
//					(MultipartFile) scenario.get(i).get(0), (String) scenario.get(i).get(1),
//					(String) scenario.get(i).get(2), (String) scenario.get(i).get(3));
//
//					ssheetLibraryService2.editSSheet((MultipartFile) scenario.get(i).get(0),
//							(String) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3));
//
//		}
//
//	}
//
//	@Test
//	public void testeditSSheetFunction() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
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
//		scenario.get(3).add(new SsheetLibraryEntity());
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
//		scenario.get(4).add(new SsheetLibraryEntity());
//		// Result excpected
//		scenario.get(4).add("Done");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editSSheetFunction [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(5));
//
//					ssheetLibraryService.editSSheetFunction((MultipartFile) scenario.get(i).get(0),
//							(String) scenario.get(i).get(1), (String) scenario.get(i).get(2),
//							(String) scenario.get(i).get(3));
//
//		}
//	}
//
//	@Test
//	public void testrestoreSsheet() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from SsheetLibraryEntity u WHERE u.pdfName = :p1 AND u.isDeleted = :p2"))
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
//		scenario.get(3).add(new SsheetLibraryEntity());
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(new SsheetLibraryEntity());
//		// Result of the query2
//		scenario.get(4).add(new ArrayList<SsheetLibraryEntity>());
//		// Result excpected
//		scenario.get(4).add("done");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("123");
//		// Result of the query1
//		scenario.get(5).add(new SsheetLibraryEntity());
//		// Result of the query2
//		ArrayList<SsheetLibraryEntity> list = new ArrayList<SsheetLibraryEntity>();
//		list.add(null);
//		list.add(new SsheetLibraryEntity());
//		scenario.get(5).add(list);
//		// Result excpected
//		scenario.get(5).add("exist");
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("restoreRsheet [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//
//					ssheetLibraryService.restoreSsheet((String) scenario.get(i).get(0));
//
//		}
//
//
//	}
//
//	@Test
//	public void testeditSSheetNotification() {
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
//			System.out.println("editSSheetNotification [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			 ssheetLibraryService
//					.editSSheetNotification((String) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//	@Test
//	public void testdeleteSSheetNotification() {
//
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
//			ssheetLibraryService
//					.deleteSSheetNotification((String) scenario.get(i).get(0), (String) scenario.get(i).get(1));
//
//		}
//
//	}
//}
