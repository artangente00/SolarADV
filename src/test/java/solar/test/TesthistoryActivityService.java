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
//import com.PlayGroundAdv.Solar.model.HistoricActivityResult;
//import com.PlayGroundAdv.Solar.model.ModuleCorrectionModel;
//import com.PlayGroundAdv.Solar.model.RailRackingModel;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.RailRackingLibraryService;
//
//public class TesthistoryActivityService {
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
//	HistoryActivityService historyActivityService = new HistoryActivityService();
//
//
//    @Before
//	public void setupMock() {
//    	historyActivityService = new HistoryActivityService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//
//	@Test
//	public void testrecordActivity() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u.homeOwnName from PermitEntity u where u.id = :p1"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u.firstName from AuthentificationEntity u where u.id = :p1"))
//						.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u.lastName from AuthentificationEntity u where u.id = :p1"))
//						.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery(
//				" SELECT u  "
//						 + " FROM AuthentificationEntity u "
//						 + " WHERE u.id = :p1  "))
//						.thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
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
//		//Result expected on Query1
//		scenario.get(0).add(null);
//		//Result expected on Query2
//		scenario.get(0).add(null);
//		//Result expected on Query3
//		scenario.get(0).add(null);
//		//Result expected on Query4
//		scenario.get(0).add(null);
//		//Result expected
//		scenario.get(0).add("success");
//		//Result expected on Query1
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("abcdef");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		//Result expected on Query1
//		scenario.get(1).add(null);
//		//Result expected on Query2
//		scenario.get(1).add(null);
//		//Result expected on Query3
//		scenario.get(1).add(null);
//		//Result expected on Query4
//		scenario.get(1).add(null);
//		//Result expected
//		scenario.get(1).add("success");
//		//Result expected on Query1
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("abcdef;cdfr");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		//Result expected on Query1
//		scenario.get(2).add(null);
//		//Result expected on Query2
//		scenario.get(2).add(null);
//		//Result expected on Query3
//		scenario.get(2).add(null);
//		//Result expected on Query4
//		scenario.get(2).add(null);
//		//Result expected
//		scenario.get(2).add("success");
//		//Result expected on Query1
//		scenario.get(2).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("abcdef;cdfr");
//		scenario.get(3).add(true);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		//Result expected on Query1
//		scenario.get(3).add(null);
//		//Result expected on Query2
//		scenario.get(3).add(null);
//		//Result expected on Query3
//		scenario.get(3).add(null);
//		//Result expected on Query4
//		scenario.get(3).add(null);
//		//Result expected
//		scenario.get(3).add("success");
//		//Result expected on Query1
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add("Start Edit Project;");
//		scenario.get(4).add(true);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		//Result expected on Query1
//		scenario.get(4).add(null);
//		//Result expected on Query2
//		scenario.get(4).add(null);
//		//Result expected on Query3
//		scenario.get(4).add(null);
//		//Result expected on Query4
//		scenario.get(4).add(null);
//		//Result expected
//		scenario.get(4).add("success");
//		//Result expected on Query1
//		scenario.get(4).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add("Start Edit Project;abc");
//		scenario.get(5).add(true);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		//Result expected on Query1
//		scenario.get(5).add(null);
//		//Result expected on Query2
//		scenario.get(5).add(null);
//		//Result expected on Query3
//		scenario.get(5).add(null);
//		//Result expected on Query4
//		scenario.get(5).add(null);
//		//Result expected
//		scenario.get(5).add("success");
//		//Result expected on Query1
//		scenario.get(5).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add("Start Edit Project;123");
//		scenario.get(6).add(true);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		//Result expected on Query1
//		scenario.get(6).add(null);
//		//Result expected on Query2
//		scenario.get(6).add(null);
//		//Result expected on Query3
//		scenario.get(6).add(null);
//		//Result expected on Query4
//		scenario.get(6).add(null);
//		//Result expected
//		scenario.get(6).add("success");
//		//Result expected on Query1
//		scenario.get(6).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add("Start Edit Project;123;cvv");
//		scenario.get(7).add(true);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		//Result expected on Query1
//		scenario.get(7).add(null);
//		//Result expected on Query2
//		scenario.get(7).add(null);
//		//Result expected on Query3
//		scenario.get(7).add(null);
//		//Result expected on Query4
//		scenario.get(7).add(null);
//		//Result expected
//		scenario.get(7).add("success");
//		//Result expected on Query1
//		scenario.get(7).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.get(8).add("Start Edit Project;123;cvv");
//		scenario.get(8).add(true);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		//Result expected on Query1
//		scenario.get(8).add("abc");
//		//Result expected on Query2
//		scenario.get(8).add(null);
//		//Result expected on Query3
//		scenario.get(8).add(null);
//		//Result expected on Query4
//		scenario.get(8).add(null);
//		//Result expected
//		scenario.get(8).add("success");
//		//Result expected on Query1
//		ArrayList<String> list = new ArrayList<>();
//		list.add("");
//		scenario.get(8).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(9).add(null);
//		scenario.get(9).add(null);
//		scenario.get(9).add(null);
//		scenario.get(9).add("Start Edit Project Read Only;");
//		scenario.get(9).add(true);
//		scenario.get(9).add(null);
//		scenario.get(9).add(null);
//		scenario.get(9).add(null);
//		//Result expected on Query1
//		scenario.get(9).add(null);
//		//Result expected on Query2
//		scenario.get(9).add(null);
//		//Result expected on Query3
//		scenario.get(9).add(null);
//		//Result expected on Query4
//		scenario.get(9).add(null);
//		//Result expected
//		scenario.get(9).add("success");
//		//Result expected on Query1
//		scenario.get(9).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(10).add(null);
//		scenario.get(10).add(null);
//		scenario.get(10).add(null);
//		scenario.get(10).add("Start Edit Project Read Only;abc");
//		scenario.get(10).add(true);
//		scenario.get(10).add(null);
//		scenario.get(10).add(null);
//		scenario.get(10).add(null);
//		//Result expected on Query1
//		scenario.get(10).add(null);
//		//Result expected on Query2
//		scenario.get(10).add(null);
//		//Result expected on Query3
//		scenario.get(10).add(null);
//		//Result expected on Query4
//		scenario.get(10).add(null);
//		//Result expected
//		scenario.get(10).add("success");
//		//Result expected on Query1
//		scenario.get(10).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(11).add(null);
//		scenario.get(11).add(null);
//		scenario.get(11).add(null);
//		scenario.get(11).add("Start Edit Project Read Only;123");
//		scenario.get(11).add(true);
//		scenario.get(11).add(null);
//		scenario.get(11).add(null);
//		scenario.get(11).add(null);
//		//Result expected on Query1
//		scenario.get(11).add(null);
//		//Result expected on Query2
//		scenario.get(11).add(null);
//		//Result expected on Query3
//		scenario.get(11).add(null);
//		//Result expected on Query4
//		scenario.get(11).add(null);
//		//Result expected
//		scenario.get(11).add("success");
//		//Result expected on Query1
//		scenario.get(11).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(12).add(null);
//		scenario.get(12).add(null);
//		scenario.get(12).add(null);
//		scenario.get(12).add("Start Edit Project Read Only;123;cvv");
//		scenario.get(12).add(true);
//		scenario.get(12).add(null);
//		scenario.get(12).add(null);
//		scenario.get(12).add(null);
//		//Result expected on Query1
//		scenario.get(12).add(null);
//		//Result expected on Query2
//		scenario.get(12).add(null);
//		//Result expected on Query3
//		scenario.get(12).add(null);
//		//Result expected on Query4
//		scenario.get(12).add(null);
//		//Result expected
//		scenario.get(12).add("success");
//		//Result expected on Query1
//		scenario.get(12).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(13).add(null);
//		scenario.get(13).add(null);
//		scenario.get(13).add(null);
//		scenario.get(13).add("Start Edit Project Read Only;123;cvv");
//		scenario.get(13).add(true);
//		scenario.get(13).add(null);
//		scenario.get(13).add(null);
//		scenario.get(13).add(null);
//		//Result expected on Query1
//		scenario.get(13).add("abc");
//		//Result expected on Query2
//		scenario.get(13).add(null);
//		//Result expected on Query3
//		scenario.get(13).add(null);
//		//Result expected on Query4
//		scenario.get(13).add(null);
//		//Result expected
//		scenario.get(13).add("success");
//		//Result expected on Query1
//		scenario.get(13).add(list);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(14).add(null);
//		scenario.get(14).add(null);
//		scenario.get(14).add(null);
//		scenario.get(14).add("Save Permit as Draft;");
//		scenario.get(14).add(true);
//		scenario.get(14).add(null);
//		scenario.get(14).add(null);
//		scenario.get(14).add(null);
//		//Result expected on Query1
//		scenario.get(14).add(null);
//		//Result expected on Query2
//		scenario.get(14).add(null);
//		//Result expected on Query3
//		scenario.get(14).add(null);
//		//Result expected on Query4
//		scenario.get(14).add(null);
//		//Result expected
//		scenario.get(14).add("success");
//		//Result expected on Query1
//		scenario.get(14).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(15).add(null);
//		scenario.get(15).add(null);
//		scenario.get(15).add(null);
//		scenario.get(15).add("Save Permit as Draft;abc");
//		scenario.get(15).add(true);
//		scenario.get(15).add(null);
//		scenario.get(15).add(null);
//		scenario.get(15).add(null);
//		//Result expected on Query1
//		scenario.get(15).add(null);
//		//Result expected on Query2
//		scenario.get(15).add(null);
//		//Result expected on Query3
//		scenario.get(15).add(null);
//		//Result expected on Query4
//		scenario.get(15).add(null);
//		//Result expected
//		scenario.get(15).add("success");
//		//Result expected on Query1
//		scenario.get(15).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(16).add(null);
//		scenario.get(16).add(null);
//		scenario.get(16).add(null);
//		scenario.get(16).add("Save Permit as Draft;123");
//		scenario.get(16).add(true);
//		scenario.get(16).add(null);
//		scenario.get(16).add(null);
//		scenario.get(16).add(null);
//		//Result expected on Query1
//		scenario.get(16).add(null);
//		//Result expected on Query2
//		scenario.get(16).add(null);
//		//Result expected on Query3
//		scenario.get(16).add(null);
//		//Result expected on Query4
//		scenario.get(16).add(null);
//		//Result expected
//		scenario.get(16).add("success");
//		//Result expected on Query1
//		scenario.get(16).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(17).add(null);
//		scenario.get(17).add(null);
//		scenario.get(17).add(null);
//		scenario.get(17).add("Save Permit as Draft;123;cvv");
//		scenario.get(17).add(true);
//		scenario.get(17).add(null);
//		scenario.get(17).add(null);
//		scenario.get(17).add(null);
//		//Result expected on Query1
//		scenario.get(17).add(null);
//		//Result expected on Query2
//		scenario.get(17).add(null);
//		//Result expected on Query3
//		scenario.get(17).add(null);
//		//Result expected on Query4
//		scenario.get(17).add(null);
//		//Result expected
//		scenario.get(17).add("success");
//		//Result expected on Query1
//		scenario.get(17).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(18).add(null);
//		scenario.get(18).add(null);
//		scenario.get(18).add(null);
//		scenario.get(18).add("Save Permit as Draft;123;cvv");
//		scenario.get(18).add(true);
//		scenario.get(18).add(null);
//		scenario.get(18).add(null);
//		scenario.get(18).add(null);
//		//Result expected on Query1
//		scenario.get(18).add("abc");
//		//Result expected on Query2
//		scenario.get(18).add(null);
//		//Result expected on Query3
//		scenario.get(18).add(null);
//		//Result expected on Query4
//		scenario.get(18).add(null);
//		//Result expected
//		scenario.get(18).add("success");
//		//Result expected on Query1
//		scenario.get(18).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(19).add(null);
//		scenario.get(19).add(null);
//		scenario.get(19).add(null);
//		scenario.get(19).add("Download PDF Project;");
//		scenario.get(19).add(true);
//		scenario.get(19).add(null);
//		scenario.get(19).add(null);
//		scenario.get(19).add(null);
//		//Result expected on Query1
//		scenario.get(19).add(null);
//		//Result expected on Query2
//		scenario.get(19).add(null);
//		//Result expected on Query3
//		scenario.get(19).add(null);
//		//Result expected on Query4
//		scenario.get(19).add(null);
//		//Result expected
//		scenario.get(19).add("success");
//		//Result expected on Query1
//		scenario.get(19).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(20).add(null);
//		scenario.get(20).add(null);
//		scenario.get(20).add(null);
//		scenario.get(20).add("Download PDF Project;abc");
//		scenario.get(20).add(true);
//		scenario.get(20).add(null);
//		scenario.get(20).add(null);
//		scenario.get(20).add(null);
//		//Result expected on Query1
//		scenario.get(20).add(null);
//		//Result expected on Query2
//		scenario.get(20).add(null);
//		//Result expected on Query3
//		scenario.get(20).add(null);
//		//Result expected on Query4
//		scenario.get(20).add(null);
//		//Result expected
//		scenario.get(20).add("success");
//		//Result expected on Query1
//		scenario.get(20).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(21).add(null);
//		scenario.get(21).add(null);
//		scenario.get(21).add(null);
//		scenario.get(21).add("Download PDF Project;123");
//		scenario.get(21).add(true);
//		scenario.get(21).add(null);
//		scenario.get(21).add(null);
//		scenario.get(21).add(null);
//		//Result expected on Query1
//		scenario.get(21).add(null);
//		//Result expected on Query2
//		scenario.get(21).add(null);
//		//Result expected on Query3
//		scenario.get(21).add(null);
//		//Result expected on Query4
//		scenario.get(21).add(null);
//		//Result expected
//		scenario.get(21).add("success");
//		//Result expected on Query1
//		scenario.get(21).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(22).add(null);
//		scenario.get(22).add(null);
//		scenario.get(22).add(null);
//		scenario.get(22).add("Download PDF Project;123;cvv");
//		scenario.get(22).add(true);
//		scenario.get(22).add(null);
//		scenario.get(22).add(null);
//		scenario.get(22).add(null);
//		//Result expected on Query1
//		scenario.get(22).add(null);
//		//Result expected on Query2
//		scenario.get(22).add(null);
//		//Result expected on Query3
//		scenario.get(22).add(null);
//		//Result expected on Query4
//		scenario.get(22).add(null);
//		//Result expected
//		scenario.get(22).add("success");
//		//Result expected on Query1
//		scenario.get(22).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(23).add(null);
//		scenario.get(23).add(null);
//		scenario.get(23).add(null);
//		scenario.get(23).add("Download PDF Project;123;cvv");
//		scenario.get(23).add(true);
//		scenario.get(23).add(null);
//		scenario.get(23).add(null);
//		scenario.get(23).add(null);
//		//Result expected on Query1
//		scenario.get(23).add("abc");
//		//Result expected on Query2
//		scenario.get(23).add(null);
//		//Result expected on Query3
//		scenario.get(23).add(null);
//		//Result expected on Query4
//		scenario.get(23).add(null);
//		//Result expected
//		scenario.get(23).add("success");
//		//Result expected on Query1
//		scenario.get(23).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(24).add(null);
//		scenario.get(24).add(null);
//		scenario.get(24).add(null);
//		scenario.get(24).add("Submit Permit;");
//		scenario.get(24).add(true);
//		scenario.get(24).add(null);
//		scenario.get(24).add(null);
//		scenario.get(24).add(null);
//		//Result expected on Query1
//		scenario.get(24).add(null);
//		//Result expected on Query2
//		scenario.get(24).add(null);
//		//Result expected on Query3
//		scenario.get(24).add(null);
//		//Result expected on Query4
//		scenario.get(24).add(null);
//		//Result expected
//		scenario.get(24).add("success");
//		//Result expected on Query1
//		scenario.get(24).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(25).add(null);
//		scenario.get(25).add(null);
//		scenario.get(25).add(null);
//		scenario.get(25).add("Submit Permit;abc");
//		scenario.get(25).add(true);
//		scenario.get(25).add(null);
//		scenario.get(25).add(null);
//		scenario.get(25).add(null);
//		//Result expected on Query1
//		scenario.get(25).add(null);
//		//Result expected on Query2
//		scenario.get(25).add(null);
//		//Result expected on Query3
//		scenario.get(25).add(null);
//		//Result expected on Query4
//		scenario.get(25).add(null);
//		//Result expected
//		scenario.get(25).add("success");
//		//Result expected on Query1
//		scenario.get(25).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(26).add(null);
//		scenario.get(26).add(null);
//		scenario.get(26).add(null);
//		scenario.get(26).add("Submit Permit;123");
//		scenario.get(26).add(true);
//		scenario.get(26).add(null);
//		scenario.get(26).add(null);
//		scenario.get(26).add(null);
//		//Result expected on Query1
//		scenario.get(26).add(null);
//		//Result expected on Query2
//		scenario.get(26).add(null);
//		//Result expected on Query3
//		scenario.get(26).add(null);
//		//Result expected on Query4
//		scenario.get(26).add(null);
//		//Result expected
//		scenario.get(26).add("success");
//		//Result expected on Query1
//		scenario.get(26).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(27).add(null);
//		scenario.get(27).add(null);
//		scenario.get(27).add(null);
//		scenario.get(27).add("Submit Permit;123;cvv");
//		scenario.get(27).add(true);
//		scenario.get(27).add(null);
//		scenario.get(27).add(null);
//		scenario.get(27).add(null);
//		//Result expected on Query1
//		scenario.get(27).add(null);
//		//Result expected on Query2
//		scenario.get(27).add(null);
//		//Result expected on Query3
//		scenario.get(27).add(null);
//		//Result expected on Query4
//		scenario.get(27).add(null);
//		//Result expected
//		scenario.get(27).add("success");
//		//Result expected on Query1
//		scenario.get(27).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(28).add(null);
//		scenario.get(28).add(null);
//		scenario.get(28).add(null);
//		scenario.get(28).add("Submit Permit;123;cvv");
//		scenario.get(28).add(true);
//		scenario.get(28).add(null);
//		scenario.get(28).add(null);
//		scenario.get(28).add(null);
//		//Result expected on Query1
//		scenario.get(28).add("abc");
//		//Result expected on Query2
//		scenario.get(28).add(null);
//		//Result expected on Query3
//		scenario.get(28).add(null);
//		//Result expected on Query4
//		scenario.get(28).add(null);
//		//Result expected
//		scenario.get(28).add("success");
//		//Result expected on Query1
//		scenario.get(28).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(29).add(null);
//		scenario.get(29).add(null);
//		scenario.get(29).add(null);
//		scenario.get(29).add("Download EXCEL;");
//		scenario.get(29).add(true);
//		scenario.get(29).add(null);
//		scenario.get(29).add(null);
//		scenario.get(29).add(null);
//		//Result expected on Query1
//		scenario.get(29).add(null);
//		//Result expected on Query2
//		scenario.get(29).add(null);
//		//Result expected on Query3
//		scenario.get(29).add(null);
//		//Result expected on Query4
//		scenario.get(29).add(null);
//		//Result expected
//		scenario.get(29).add("success");
//		//Result expected on Query1
//		scenario.get(29).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(30).add(null);
//		scenario.get(30).add(null);
//		scenario.get(30).add(null);
//		scenario.get(30).add("Download EXCEL;abc");
//		scenario.get(30).add(true);
//		scenario.get(30).add(null);
//		scenario.get(30).add(null);
//		scenario.get(30).add(null);
//		//Result expected on Query1
//		scenario.get(30).add(null);
//		//Result expected on Query2
//		scenario.get(30).add(null);
//		//Result expected on Query3
//		scenario.get(30).add(null);
//		//Result expected on Query4
//		scenario.get(30).add(null);
//		//Result expected
//		scenario.get(30).add("success");
//		//Result expected on Query1
//		scenario.get(30).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(31).add(null);
//		scenario.get(31).add(null);
//		scenario.get(31).add(null);
//		scenario.get(31).add("Download EXCEL;123");
//		scenario.get(31).add(true);
//		scenario.get(31).add(null);
//		scenario.get(31).add(null);
//		scenario.get(31).add(null);
//		//Result expected on Query1
//		scenario.get(31).add(null);
//		//Result expected on Query2
//		scenario.get(31).add(null);
//		//Result expected on Query3
//		scenario.get(31).add(null);
//		//Result expected on Query4
//		scenario.get(31).add(null);
//		//Result expected
//		scenario.get(31).add("success");
//		//Result expected on Query1
//		scenario.get(31).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(32).add(null);
//		scenario.get(32).add(null);
//		scenario.get(32).add(null);
//		scenario.get(32).add("Download EXCEL;123;cvv");
//		scenario.get(32).add(true);
//		scenario.get(32).add(null);
//		scenario.get(32).add(null);
//		scenario.get(32).add(null);
//		//Result expected on Query1
//		scenario.get(32).add(null);
//		//Result expected on Query2
//		scenario.get(32).add(null);
//		//Result expected on Query3
//		scenario.get(32).add(null);
//		//Result expected on Query4
//		scenario.get(32).add(null);
//		//Result expected
//		scenario.get(32).add("success");
//		//Result expected on Query1
//		scenario.get(32).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(33).add(null);
//		scenario.get(33).add(null);
//		scenario.get(33).add(null);
//		scenario.get(33).add("Download EXCEL;123;cvv");
//		scenario.get(33).add(true);
//		scenario.get(33).add(null);
//		scenario.get(33).add(null);
//		scenario.get(33).add(null);
//		//Result expected on Query1
//		scenario.get(33).add("abc");
//		//Result expected on Query2
//		scenario.get(33).add(null);
//		//Result expected on Query3
//		scenario.get(33).add(null);
//		//Result expected on Query4
//		scenario.get(33).add(null);
//		//Result expected
//		scenario.get(33).add("success");
//		//Result expected on Query1
//		scenario.get(33).add(list);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(34).add(null);
//		scenario.get(34).add(null);
//		scenario.get(34).add(null);
//		scenario.get(34).add("Download Drafter Package;");
//		scenario.get(34).add(true);
//		scenario.get(34).add(null);
//		scenario.get(34).add(null);
//		scenario.get(34).add(null);
//		//Result expected on Query1
//		scenario.get(34).add(null);
//		//Result expected on Query2
//		scenario.get(34).add(null);
//		//Result expected on Query3
//		scenario.get(34).add(null);
//		//Result expected on Query4
//		scenario.get(34).add(null);
//		//Result expected
//		scenario.get(34).add("success");
//		//Result expected on Query1
//		scenario.get(34).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(35).add(null);
//		scenario.get(35).add(null);
//		scenario.get(35).add(null);
//		scenario.get(35).add("Download Drafter Package;abc");
//		scenario.get(35).add(true);
//		scenario.get(35).add(null);
//		scenario.get(35).add(null);
//		scenario.get(35).add(null);
//		//Result expected on Query1
//		scenario.get(35).add(null);
//		//Result expected on Query2
//		scenario.get(35).add(null);
//		//Result expected on Query3
//		scenario.get(35).add(null);
//		//Result expected on Query4
//		scenario.get(35).add(null);
//		//Result expected
//		scenario.get(35).add("success");
//		//Result expected on Query1
//		scenario.get(35).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(36).add(null);
//		scenario.get(36).add(null);
//		scenario.get(36).add(null);
//		scenario.get(36).add("Download Drafter Package;123");
//		scenario.get(36).add(true);
//		scenario.get(36).add(null);
//		scenario.get(36).add(null);
//		scenario.get(36).add(null);
//		//Result expected on Query1
//		scenario.get(36).add(null);
//		//Result expected on Query2
//		scenario.get(36).add(null);
//		//Result expected on Query3
//		scenario.get(36).add(null);
//		//Result expected on Query4
//		scenario.get(36).add(null);
//		//Result expected
//		scenario.get(36).add("success");
//		//Result expected on Query1
//		scenario.get(36).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(37).add(null);
//		scenario.get(37).add(null);
//		scenario.get(37).add(null);
//		scenario.get(37).add("Download Drafter Package;123;cvv");
//		scenario.get(37).add(true);
//		scenario.get(37).add(null);
//		scenario.get(37).add(null);
//		scenario.get(37).add(null);
//		//Result expected on Query1
//		scenario.get(37).add(null);
//		//Result expected on Query2
//		scenario.get(37).add(null);
//		//Result expected on Query3
//		scenario.get(37).add(null);
//		//Result expected on Query4
//		scenario.get(37).add(null);
//		//Result expected
//		scenario.get(37).add("success");
//		//Result expected on Query1
//		scenario.get(37).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(38).add(null);
//		scenario.get(38).add(null);
//		scenario.get(38).add(null);
//		scenario.get(38).add("Download Drafter Package;123;cvv");
//		scenario.get(38).add(true);
//		scenario.get(38).add(null);
//		scenario.get(38).add(null);
//		scenario.get(38).add(null);
//		//Result expected on Query1
//		scenario.get(38).add("abc");
//		//Result expected on Query2
//		scenario.get(38).add(null);
//		//Result expected on Query3
//		scenario.get(38).add(null);
//		//Result expected on Query4
//		scenario.get(38).add(null);
//		//Result expected
//		scenario.get(38).add("success");
//		//Result expected on Query1
//		scenario.get(38).add(list);
//
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(39).add(null);
//		scenario.get(39).add(null);
//		scenario.get(39).add(null);
//		scenario.get(39).add("Create Permit;");
//		scenario.get(39).add(true);
//		scenario.get(39).add(null);
//		scenario.get(39).add(null);
//		scenario.get(39).add(null);
//		//Result expected on Query1
//		scenario.get(39).add(null);
//		//Result expected on Query2
//		scenario.get(39).add(null);
//		//Result expected on Query3
//		scenario.get(39).add(null);
//		//Result expected on Query4
//		scenario.get(39).add(null);
//		//Result expected
//		scenario.get(39).add("success");
//		//Result expected on Query1
//		scenario.get(39).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(40).add(null);
//		scenario.get(40).add(null);
//		scenario.get(40).add(null);
//		scenario.get(40).add("Create Permit;abc");
//		scenario.get(40).add(true);
//		scenario.get(40).add(null);
//		scenario.get(40).add(null);
//		scenario.get(40).add(null);
//		//Result expected on Query1
//		scenario.get(40).add(null);
//		//Result expected on Query2
//		scenario.get(40).add(null);
//		//Result expected on Query3
//		scenario.get(40).add(null);
//		//Result expected on Query4
//		scenario.get(40).add(null);
//		//Result expected
//		scenario.get(40).add("success");
//		//Result expected on Query1
//		scenario.get(40).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(41).add(null);
//		scenario.get(41).add(null);
//		scenario.get(41).add(null);
//		scenario.get(41).add("Create Permit;123");
//		scenario.get(41).add(true);
//		scenario.get(41).add(null);
//		scenario.get(41).add(null);
//		scenario.get(41).add(null);
//		//Result expected on Query1
//		scenario.get(41).add(null);
//		//Result expected on Query2
//		scenario.get(41).add(null);
//		//Result expected on Query3
//		scenario.get(41).add(null);
//		//Result expected on Query4
//		scenario.get(41).add(null);
//		//Result expected
//		scenario.get(41).add("success");
//		//Result expected on Query1
//		scenario.get(41).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(42).add(null);
//		scenario.get(42).add(null);
//		scenario.get(42).add(null);
//		scenario.get(42).add("Create Permit;123;cvv");
//		scenario.get(42).add(true);
//		scenario.get(42).add(null);
//		scenario.get(42).add(null);
//		scenario.get(42).add(null);
//		//Result expected on Query1
//		scenario.get(42).add(null);
//		//Result expected on Query2
//		scenario.get(42).add(null);
//		//Result expected on Query3
//		scenario.get(42).add(null);
//		//Result expected on Query4
//		scenario.get(42).add(null);
//		//Result expected
//		scenario.get(42).add("success");
//		//Result expected on Query1
//		scenario.get(42).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(43).add(null);
//		scenario.get(43).add(null);
//		scenario.get(43).add(null);
//		scenario.get(43).add("Create Permit;123;cvv");
//		scenario.get(43).add(true);
//		scenario.get(43).add(null);
//		scenario.get(43).add(null);
//		scenario.get(43).add(null);
//		//Result expected on Query1
//		scenario.get(43).add("abc");
//		//Result expected on Query2
//		scenario.get(43).add(null);
//		//Result expected on Query3
//		scenario.get(43).add(null);
//		//Result expected on Query4
//		scenario.get(43).add(null);
//		//Result expected
//		scenario.get(43).add("success");
//		//Result expected on Query1
//		scenario.get(43).add(list);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(44).add(null);
//		scenario.get(44).add(null);
//		scenario.get(44).add(null);
//		scenario.get(44).add("Add Template;");
//		scenario.get(44).add(true);
//		scenario.get(44).add(null);
//		scenario.get(44).add(null);
//		scenario.get(44).add(null);
//		//Result expected on Query1
//		scenario.get(44).add(null);
//		//Result expected on Query2
//		scenario.get(44).add(null);
//		//Result expected on Query3
//		scenario.get(44).add(null);
//		//Result expected on Query4
//		scenario.get(44).add(null);
//		//Result expected
//		scenario.get(44).add("success");
//		//Result expected on Query1
//		scenario.get(44).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(45).add(null);
//		scenario.get(45).add(null);
//		scenario.get(45).add(null);
//		scenario.get(45).add("Add Template;abc");
//		scenario.get(45).add(true);
//		scenario.get(45).add(null);
//		scenario.get(45).add(null);
//		scenario.get(45).add(null);
//		//Result expected on Query1
//		scenario.get(45).add(null);
//		//Result expected on Query2
//		scenario.get(45).add(null);
//		//Result expected on Query3
//		scenario.get(45).add(null);
//		//Result expected on Query4
//		scenario.get(45).add(null);
//		//Result expected
//		scenario.get(45).add("success");
//		//Result expected on Query1
//		scenario.get(45).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(46).add(null);
//		scenario.get(46).add(null);
//		scenario.get(46).add(null);
//		scenario.get(46).add("Add Template;123");
//		scenario.get(46).add(true);
//		scenario.get(46).add(null);
//		scenario.get(46).add(null);
//		scenario.get(46).add(null);
//		//Result expected on Query1
//		scenario.get(46).add(null);
//		//Result expected on Query2
//		scenario.get(46).add(null);
//		//Result expected on Query3
//		scenario.get(46).add(null);
//		//Result expected on Query4
//		scenario.get(46).add(null);
//		//Result expected
//		scenario.get(46).add("success");
//		//Result expected on Query1
//		scenario.get(46).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(47).add(null);
//		scenario.get(47).add(null);
//		scenario.get(47).add(null);
//		scenario.get(47).add("Add Template;123;cvv");
//		scenario.get(47).add(true);
//		scenario.get(47).add(null);
//		scenario.get(47).add(null);
//		scenario.get(47).add(null);
//		//Result expected on Query1
//		scenario.get(47).add(null);
//		//Result expected on Query2
//		scenario.get(47).add(null);
//		//Result expected on Query3
//		scenario.get(47).add(null);
//		//Result expected on Query4
//		scenario.get(47).add(null);
//		//Result expected
//		scenario.get(47).add("success");
//		//Result expected on Query1
//		scenario.get(47).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(48).add(null);
//		scenario.get(48).add(null);
//		scenario.get(48).add(null);
//		scenario.get(48).add("Add Template;123;cvv");
//		scenario.get(48).add(true);
//		scenario.get(48).add(null);
//		scenario.get(48).add(null);
//		scenario.get(48).add(null);
//		//Result expected on Query1
//		scenario.get(48).add("abc");
//		//Result expected on Query2
//		scenario.get(48).add(null);
//		//Result expected on Query3
//		scenario.get(48).add(null);
//		//Result expected on Query4
//		scenario.get(48).add(null);
//		//Result expected
//		scenario.get(48).add("success");
//		//Result expected on Query1
//		scenario.get(48).add(list);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(49).add(null);
//		scenario.get(49).add(null);
//		scenario.get(49).add(null);
//		scenario.get(49).add("Delet Permit;");
//		scenario.get(49).add(true);
//		scenario.get(49).add(null);
//		scenario.get(49).add(null);
//		scenario.get(49).add(null);
//		//Result expected on Query1
//		scenario.get(49).add(null);
//		//Result expected on Query2
//		scenario.get(49).add(null);
//		//Result expected on Query3
//		scenario.get(49).add(null);
//		//Result expected on Query4
//		scenario.get(49).add(null);
//		//Result expected
//		scenario.get(49).add("success");
//		//Result expected on Query1
//		scenario.get(49).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(50).add(null);
//		scenario.get(50).add(null);
//		scenario.get(50).add(null);
//		scenario.get(50).add("Delet Permit;abc");
//		scenario.get(50).add(true);
//		scenario.get(50).add(null);
//		scenario.get(50).add(null);
//		scenario.get(50).add(null);
//		//Result expected on Query1
//		scenario.get(50).add(null);
//		//Result expected on Query2
//		scenario.get(50).add(null);
//		//Result expected on Query3
//		scenario.get(50).add(null);
//		//Result expected on Query4
//		scenario.get(50).add(null);
//		//Result expected
//		scenario.get(50).add("success");
//		//Result expected on Query1
//		scenario.get(50).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(51).add(null);
//		scenario.get(51).add(null);
//		scenario.get(51).add(null);
//		scenario.get(51).add("Delet Permit;123");
//		scenario.get(51).add(true);
//		scenario.get(51).add(null);
//		scenario.get(51).add(null);
//		scenario.get(51).add(null);
//		//Result expected on Query1
//		scenario.get(51).add(null);
//		//Result expected on Query2
//		scenario.get(51).add(null);
//		//Result expected on Query3
//		scenario.get(51).add(null);
//		//Result expected on Query4
//		scenario.get(51).add(null);
//		//Result expected
//		scenario.get(51).add("success");
//		//Result expected on Query1
//		scenario.get(51).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(52).add(null);
//		scenario.get(52).add(null);
//		scenario.get(52).add(null);
//		scenario.get(52).add("Delet Permit;123;cvv");
//		scenario.get(52).add(true);
//		scenario.get(52).add(null);
//		scenario.get(52).add(null);
//		scenario.get(52).add(null);
//		//Result expected on Query1
//		scenario.get(52).add(null);
//		//Result expected on Query2
//		scenario.get(52).add(null);
//		//Result expected on Query3
//		scenario.get(52).add(null);
//		//Result expected on Query4
//		scenario.get(52).add(null);
//		//Result expected
//		scenario.get(52).add("success");
//		//Result expected on Query1
//		scenario.get(52).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(53).add(null);
//		scenario.get(53).add(null);
//		scenario.get(53).add(null);
//		scenario.get(53).add("Delet Permit;123;cvv");
//		scenario.get(53).add(true);
//		scenario.get(53).add(null);
//		scenario.get(53).add(null);
//		scenario.get(53).add(null);
//		//Result expected on Query1
//		scenario.get(53).add("abc");
//		//Result expected on Query2
//		scenario.get(53).add(null);
//		//Result expected on Query3
//		scenario.get(53).add(null);
//		//Result expected on Query4
//		scenario.get(53).add(null);
//		//Result expected
//		scenario.get(53).add("success");
//		//Result expected on Query1
//		scenario.get(53).add(list);
//
//
//
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("recordActivity [ " + i + " ]"+scenario.get(i).get(13));
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(8));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(13));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(9));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(10));
//			when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(11));
//			historyActivityService
//					.recordActivity((String) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3),(Boolean) scenario.get(i).get(4),(String) scenario.get(i).get(5),(String) scenario.get(i).get(6),(String) scenario.get(i).get(7));
//
//		}
//
//	}
//
//	@Test
//	public void testgethistoricTable() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"Select new com.PlayGroundAdv.Solar.model.HistoricActivityResult("
//						   + "u.date,  "
//						   + "u.ipUserCo, "
//						   + "u.timeZoneUserCo, "
//						   + "u.typeAction, "
//						   + "u.idUserCo.firstName,"
//						   + "u.idUserCo.lastName, "
//						   + "u.numTab, "
//						   + "u.sessionId, "
//						   + "u.openDate, "
//						   + "u.isSuccess ) "
//						   + "FROM HistoryActivityEntity u  ORDER BY u.date DESC"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
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
//		ArrayList<HistoricActivityResult> list = new ArrayList<HistoricActivityResult>();
//		list.add(null);
//		list.add(new HistoricActivityResult());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("gethistoricTable [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			List<HistoricActivityResult> rslt = (List<HistoricActivityResult>)historyActivityService.gethistoricTable();
//		}
//	}
//
//
//	@Test
//	public void testgetlibrariesHistoricTable() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"Select new com.PlayGroundAdv.Solar.model.HistoricActivityResult("
//						   + "u.date,  "
//						   + "u.ipUserCo, "
//						   + "u.timeZoneUserCo, "
//						   + "u.typeAction, "
//						   + "u.idUserCo.firstName,"
//						   + "u.idUserCo.lastName, "
//						   + "u.numTab, "
//						   + "u.sessionId, "
//						   + "u.openDate, "
//						   + "u.isSuccess ) "
//						   + "FROM HistoryActivityEntity u WHERE SPLIT_PART(u.typeAction,';' , 1) = :p1  ORDER BY u.date"))
//						.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
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
//		ArrayList<HistoricActivityResult> list = new ArrayList<HistoricActivityResult>();
//		list.add(null);
//		list.add(new HistoricActivityResult());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getlibrariesHistoricTable [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			List<HistoricActivityResult> rslt = (List<HistoricActivityResult>)historyActivityService.getlibrariesHistoricTable();
//		}
//	}
//
//}
