//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
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
//import org.mockito.stubbing.OngoingStubbing;
//
//import com.PlayGroundAdv.Solar.Entity.PathEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitConduitConductorSectionEntity;
//import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.CopyProjectService;
//import com.PlayGroundAdv.Solar.Services.CustomizeSheetsService;
//import com.PlayGroundAdv.Solar.Services.ExportSaveProjectSvcService;
//import com.PlayGroundAdv.Solar.Services.GetPDFReaderService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.RestoreProjectInputsService;
//import java.sql.Statement;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.TimeZone;
//
//import junit.framework.Assert;
//
//import java.io.File;
//import java.sql.DriverManager;
//
//
//public class TestExportSaveProjectSvcService {
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
//
//	@Mock
//    private Root root;
//
//	@Mock
//	RestoreProjectInputsService restoreProjectInputsService;
//
//	@Spy
//	CheckValueTypesService checkValueTypesService;
//	@InjectMocks
//	ExportSaveProjectSvcService exportSaveProjectSvcService = new ExportSaveProjectSvcService();
//
//
//    @Before
//	public void setupMock() {
//    	exportSaveProjectSvcService = new ExportSaveProjectSvcService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//
//	@Test
//	public void testgenerateSaveProjectScv() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from PathEntity u " + " where u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("");
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		PathEntity path = new PathEntity();
//		path.setDbPath("jdbc:postgresql://localhost:5432/NT-ADVSolar");
//		path.setDbName("NuageUser");
//		path.setDbPassword("Nuage123");
//		scenario.get(1).add(path);
//		// Result excpected
//		scenario.get(1).add("");
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("25320051");
//		scenario.get(2).add(null);
//		scenario.get(2).add(new Date());
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(path);
//		// Result excpected
//        TimeZone timeZone = TimeZone.getTimeZone("GMT"); // e.g. "Europe/Rome"
//		TimeZone.setDefault(timeZone);
//		String time = (new Date() + "").replace(":", "-");
//		File exportSaveFolder = null;
//		exportSaveFolder = new File("C:/files/exportSave/" + (String) scenario.get(2).get(0));
//		if (!exportSaveFolder.exists()) {
//			new File("C:/files/exportSave/" + (String) scenario.get(2).get(0)).mkdirs();
//		}
//		File[] listOfFiles = exportSaveFolder.listFiles();
//		String rslt = (String) scenario.get(2).get(0)+"-Version "+(listOfFiles.length+1)+" - "+time;
//		scenario.get(2).add(rslt.split("-")[1]+" - "+rslt.split("-")[2]+':'+rslt.split("-")[3]);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("generateSaveProjectScv [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			System.out.println(scenario.get(i).get(5));
//			exportSaveProjectSvcService.generateSaveProjectScv((String) scenario.get(i).get(0),(String) scenario.get(i).get(1),(Date) scenario.get(i).get(2),(Integer) scenario.get(i).get(3));
//		}
//	}
//
//	@Test
//	public void testrestoreProjectRevision() {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createNativeQuery(Mockito.anyString()))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		when(mockedQuery1.executeUpdate()).thenReturn(1);
//
//		ExportSaveProjectSvcService exportSaveProjectSvcService2 = Mockito.spy(exportSaveProjectSvcService);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("25320051");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("25320051");
//		scenario.get(2).add("2");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("25320051");
//		scenario.get(3).add("2");
//		scenario.get(3).add("");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("25320051");
//		scenario.get(4).add("2");
//		scenario.get(4).add("123");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(new GetPermitByIdResult());
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("restoreProjectRevision [ " + i + " ]");
//			//when(em.createQuery("UPDATE public.permit_entity SET  advancement = '100',  creation_date = '04/02/2019',  creation_permit_date = '2019-04-02 08:52:16.095',  file_1 = null,  file_10 = null,  file_11 = null,  file_2 = null,  file_3 = null,  file_4 = null,  file_5 = null,  file_6 = null,  file_7 = null,  file_8 = null,  file_9 = null,  home_own_name = 'Test System',  is_deleted = false,  status = 'Draft',  submitted = false,  updated_date = '04/02/2019',  id_contractor = 1,  type_file_1 = null,  type_file_10 = null,  type_file_11 = null,  type_file_2 = null,  type_file_3 = null,  type_file_4 = null,  type_file_5 = null,  type_file_6 = null,  type_file_7 = null,  type_file_8 = null,  type_file_9 = null,  is_payed = false,  name_file_1 = null,  name_file_10 = null,  name_file_11 = null,  name_file_2 = null,  name_file_3 = null,  name_file_4 = null,  name_file_5 = null,  name_file_6 = null,  name_file_7 = null,  name_file_8 = null,  name_file_9 = null,  files_dir = 'C:/files25320051/',  is_template = false,  r_r_version = 0,  date_of_submit_permit = null,  date_of_submit_permit_canceled = null,  date_of_submit_permit_on_hold = null,  is_canceled = false,  upload_file1 = null,  upload_file10 = null,  upload_file2 = null,  upload_file3 = null,  upload_file4 = null,  upload_file5 = null,  upload_file6 = null,  upload_file7 = null,  upload_file8 = null,  upload_file9 = null,  is_on_hold = false,  upload_comments_planset1 = null,  upload_comments_planset2 = null,  upload_comments_planset3 = null,  upload_comments_planset4 = null,  upload_comments_planset5 = null,  planset_inverter_technologies = null,  planset_roof_design = null,  planset_state = null,  planset_version = 0,  release_v2 = null,  release_v3 = null,  updated_date_v2 = null,  updated_date_v3 = null,  id_accounting = null,  project_name = null,  exist_accombiner = null,  exist_acdisconnect = null,  exist_acjunctionbox = null,  exist_inverter = null,  exist_module = null,  exist_optimizer = null,  exist_productionmeter = null,  exist_subpanel = null,  exist_dccombiner = null,  exist_dcdisconnect = null,  exist_dcjunctionbox = null,  insert_roof_note = null,  is_checking = '2019-04-02 09:04:26.95' "))
//			Mockito.doReturn("").when(exportSaveProjectSvcService2).generateSaveProjectScv(
//					Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any());
//			when(restoreProjectInputsService.getPermitById(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString()))
//			.thenReturn((GetPermitByIdResult)scenario.get(i).get(5));
//			exportSaveProjectSvcService2.restoreProjectRevision((String) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3),(String) scenario.get(i).get(4));
//		}
//	}
//
//
//
//}
