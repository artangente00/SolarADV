//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Arrays;
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
//
//import com.PlayGroundAdv.Solar.Entity.ACCombinerSLC;
//import com.PlayGroundAdv.Solar.Entity.ACDisconnect;
//import com.PlayGroundAdv.Solar.Entity.ACDisconnectFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.DCCombinerDisconnectEntity;
//import com.PlayGroundAdv.Solar.Entity.DcCombinerorDiscFavoriteEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.model.AcCombinerDiscoCorrectionModel;
//import com.PlayGroundAdv.Solar.model.AcCombinerDiscoModelRequest;
//import com.PlayGroundAdv.Solar.model.AcDisconnectModel;
//import com.PlayGroundAdv.Solar.model.DcCombinerDisconnectCorrectionModel;
//import com.PlayGroundAdv.Solar.model.DcCombinerorDisconnectModel;
//import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
//import com.PlayGroundAdv.Solar.model.UsersEntityResult;
//import com.PlayGroundAdv.Solar.Services.AcDisconnectLibraryService;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetAlldcCombinerOrDiService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.model.DcCombinerOrDisconnectRequest;
//
//
//public class TestGetAlldcCombinerOrDiService {
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
//	@Mock
//	private CriteriaBuilder criteriaBuilder;
//
//	@Mock
//    private Root root;
//
//	@Spy
//	CheckValueTypesService checkValueTypesService;
//
//	@InjectMocks
//	GetAlldcCombinerOrDiService getAlldcCombinerOrDiService = new GetAlldcCombinerOrDiService();
//
//
//    @Before
//	public void setupMock() {
//    	getAlldcCombinerOrDiService = new GetAlldcCombinerOrDiService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testgetDcCombDiscFavorite() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DcCombinerorDiscFavoriteEntity u WHERE u.authentificationEntity.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add("");
//		 //Result of the query1
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<Integer>());
//
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add("123");
//		 //Result of the query1
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add("123");
//		 //Result of the query1
//		 scenario.get(3).add(new ArrayList<DcCombinerorDiscFavoriteEntity>());
//	     //Result excpected
//		 scenario.get(3).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add("123");
//		 //Result of the query1
//		 ArrayList<DcCombinerorDiscFavoriteEntity> list = new ArrayList<DcCombinerorDiscFavoriteEntity>();
//		 list.add(null);
//		 list.add(new DcCombinerorDiscFavoriteEntity());
//		 scenario.get(4).add(list);
//	     //Result excpected
//		 scenario.get(4).add(new ArrayList<Integer>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(5).add("123");
//		 //Result of the query1
//		 ArrayList<DcCombinerorDiscFavoriteEntity> list2= new ArrayList<DcCombinerorDiscFavoriteEntity>();
//		 DcCombinerorDiscFavoriteEntity dc = new DcCombinerorDiscFavoriteEntity();
//		 dc.setdCCombinerDisconnectEntity(new DCCombinerDisconnectEntity());
//		 list2.add(dc);
//		 DcCombinerorDiscFavoriteEntity dc2 = new DcCombinerorDiscFavoriteEntity();
//		 DCCombinerDisconnectEntity dcEnt = new DCCombinerDisconnectEntity();
//		 dcEnt.setId(123);
//		 dc2.setdCCombinerDisconnectEntity(dcEnt);
//		 list2.add(dc2);
//		 scenario.get(5).add(list2);
//	     //Result excpected
//		 ArrayList<Integer> rlt = new ArrayList<Integer>();
//		 rlt.add(null);
//		 rlt.add(123);
//		 scenario.get(5).add(rlt);
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getDcCombDiscFavorite [ "+i+" ]");
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//             getAlldcCombinerOrDiService.getDcCombDiscFavorite((String)scenario.get(i).get(0));
//
//		 }
//	}
//
//	@Test
//	public void testcheckdcComDiscExistent() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.model = :p1 AND u.manufacturer = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.model = :p1 AND u.manufacturer != :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 GetAlldcCombinerOrDiService getAlldcCombinerOrDiService2 = Mockito.spy(getAlldcCombinerOrDiService);
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result of the query2
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new ArrayList<DcCombinerorDisconnectModel>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(new DcCombinerorDisconnectModel());
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<DcCombinerorDisconnectModel>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add(new DcCombinerorDisconnectModel());
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 ArrayList<DCCombinerDisconnectEntity> list = new ArrayList<DCCombinerDisconnectEntity>();
//		 list.add(null);
//		 list.add(new DCCombinerDisconnectEntity());
//		 DCCombinerDisconnectEntity dc = new DCCombinerDisconnectEntity();
//		 dc.setManufacturer("aaaa");
//		 dc.setModel("aaaa");
//		 AuthentificationEntity auth = new AuthentificationEntity();
//		 auth.setFirstName("abc");
//		 auth.setLastName("abc");
//		 auth.setId(223445);
//		 dc.setIdOwner(auth);
//		 list.add(dc);
//		 scenario.get(2).add(list);
//		 //Result of the query2
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 ArrayList<DcCombinerorDisconnectModel> listExpec = new ArrayList<DcCombinerorDisconnectModel>();
//		 listExpec.add(new DcCombinerorDisconnectModel());
//	     DcCombinerorDisconnectModel dcRes = new DcCombinerorDisconnectModel();
//	     dcRes.setOwner("Super User");
//	     dcRes.setIsShown(false);
//	     listExpec.add(dcRes);
//	     DcCombinerorDisconnectModel dcRes2 = new DcCombinerorDisconnectModel();
//	     dcRes2.setModel(dc.getModel());
//	     dcRes2.setManufacturer(dc.getManufacturer());
//	     dcRes2.setIdOwner(dc.getIdOwner().getId());
//	     dcRes2.setOwner(dc.getIdOwner().getFirstName()+" "+dc.getIdOwner().getLastName());
//	     dcRes2.setIsShown(false);
//	     listExpec.add(dcRes2);
//		 scenario.get(2).add(listExpec);
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add(new DcCombinerorDisconnectModel());
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 scenario.get(3).add(null);
//		 //Result of the query2
//		 scenario.get(3).add(list);
//	     //Result excpected
//		 scenario.get(3).add(listExpec);
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("checkdcComDiscExistent [ "+i+" ]");
//			 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(6));
//
//
//             Mockito.doReturn(new ArrayList<Integer>()).when(getAlldcCombinerOrDiService2).getDcCombDiscFavorite(Mockito.any());
//             ArrayList<DcCombinerorDisconnectModel> result = ((ArrayList<DcCombinerorDisconnectModel>) getAlldcCombinerOrDiService2.checkdcComDiscExistent((DcCombinerorDisconnectModel) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3),(String) scenario.get(i).get(4)));
//
//
//		 }
//
//	}
//
//	@Test
//	public void testgetDcCombinerorDiscList() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.isDeleted = :p1 AND (u.typeDc= :p2 OR u.typeDc= :p3 or u.typeDc= :p4 or u.typeDc= :p5 or u.typeDc= :p6) ORDER BY u.manufacturer")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 GetAlldcCombinerOrDiService getAlldcCombinerOrDiService2 = Mockito.spy(getAlldcCombinerOrDiService);
//
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new ArrayList<DcCombinerorDisconnectModel>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(new ArrayList<DCCombinerDisconnectEntity>());
//	     //Result excpected
//		 scenario.get(1).add(new ArrayList<DcCombinerorDisconnectModel>());
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 ArrayList<DCCombinerDisconnectEntity> list = new ArrayList<DCCombinerDisconnectEntity>();
//		 list.add(null);
//		 list.add(new DCCombinerDisconnectEntity());
//		 AuthentificationEntity auth =  new AuthentificationEntity();
//		 DCCombinerDisconnectEntity dc = new DCCombinerDisconnectEntity();
//		 dc.setIdOwner(auth);
//		 dc.setManufacturer("aaa");
//		 dc.setModel("aaa");
//		 list.add(dc);
//		 scenario.get(2).add(list);
//	     //Result excpected
//		 ArrayList<DcCombinerorDisconnectModel> listExp = new ArrayList<DcCombinerorDisconnectModel>();
//		 listExp.add(new DcCombinerorDisconnectModel());
//
//		 DcCombinerorDisconnectModel dcCombD2 = new DcCombinerorDisconnectModel();
//		 dcCombD2.setOwner("Super User");
//		 dcCombD2.setIsShown(false);
//		 listExp.add(dcCombD2);
//
//		 DcCombinerorDisconnectModel dcCombD3 = new DcCombinerorDisconnectModel();
//		 dcCombD3.setOwner(auth.getFirstName()+" "+auth.getLastName());
//		 dcCombD3.setManufacturer("aaa");
//		 dcCombD3.setModel("aaa");
//		 dcCombD3.setIsShown(false);
//		 listExp.add(dcCombD3);
//		 dcCombD3.setIdOwner(auth.getId());
//
//		 scenario.get(2).add(listExp);
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getDcCombinerorDiscList [ "+i+" ]");
//			 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//
//             Mockito.doReturn(new ArrayList<Integer>()).when(getAlldcCombinerOrDiService2).getDcCombDiscFavorite(Mockito.any());
//             ArrayList<DcCombinerorDisconnectModel> result = ((ArrayList<DcCombinerorDisconnectModel>) getAlldcCombinerOrDiService2.getDcCombinerorDiscList((String) scenario.get(i).get(0),(Boolean) scenario.get(i).get(1)));
//
//
//		 }
//	}
//
//	@Test
//	public void testupdateDcCombiOrDiscStatus() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.id= :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id= :p2 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("DELETE from DcCombinerorDiscFavoriteEntity u where u.dCCombinerDisconnectEntity.id = :p1 and u.authentificationEntity.id= :p2")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//		  ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 //Result of the query1
//			 scenario.get(0).add(null);
//			 //Result of the query2
//			 scenario.get(0).add(null);
//			 //Result of the query3
//			 scenario.get(0).add(null);
//		     //Result excpected
//			 scenario.get(0).add("fail");
//
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(123);
//			 scenario.get(1).add(123);
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 scenario.get(1).add("");
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 //Result of the query1
//			 scenario.get(1).add(null);
//			 //Result of the query2
//			 scenario.get(1).add(null);
//			 //Result of the query3
//			 scenario.get(1).add(null);
//		     //Result excpected
//			 scenario.get(1).add("fail");
//
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(223);
//			 scenario.get(2).add(223);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add("123");
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 //Result of the query1
//			 scenario.get(2).add(null);
//			 //Result of the query2
//			 scenario.get(2).add(null);
//			 //Result of the query3
//			 scenario.get(2).add(null);
//		     //Result excpected
//			 scenario.get(2).add("Done");
//
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(3).add(false);
//			 scenario.get(3).add(323);
//			 scenario.get(3).add(323);
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 scenario.get(3).add("123");
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 //Result of the query1
//			 scenario.get(3).add(null);
//			 //Result of the query2
//			 scenario.get(3).add(null);
//			 //Result of the query3
//			 scenario.get(3).add(null);
//		     //Result excpected
//			 scenario.get(3).add("fail");
//
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(4).add(false);
//			 scenario.get(4).add(423);
//			 scenario.get(4).add(423);
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 scenario.get(4).add("123");
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 //Result of the query1
//			 scenario.get(4).add(new DCCombinerDisconnectEntity());
//			 //Result of the query2
//			 scenario.get(4).add(null);
//			 //Result of the query3
//			 scenario.get(4).add(null);
//		     //Result excpected
//			 scenario.get(4).add("fail");
//
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(5).add(false);
//			 scenario.get(5).add(523);
//			 scenario.get(5).add(523);
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 scenario.get(5).add("123");
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 //Result of the query1
//			 scenario.get(5).add(new DCCombinerDisconnectEntity());
//			 //Result of the query2
//			 scenario.get(5).add(new AuthentificationEntity());
//			 //Result of the query3
//			 scenario.get(5).add(null);
//		     //Result excpected
//			 scenario.get(5).add("Done");
//
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(6).add(true);
//			 scenario.get(6).add(623);
//			 scenario.get(6).add(623);
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 scenario.get(6).add("123");
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 //Result of the query1
//			 scenario.get(6).add(null);
//			 //Result of the query2
//			 scenario.get(6).add(null);
//			 //Result of the query3
//			 scenario.get(6).add(null);
//		     //Result excpected
//			 scenario.get(6).add("fail");
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(7).add(true);
//			 scenario.get(7).add(723);
//			 scenario.get(7).add(723);
//			 scenario.get(7).add(null);
//			 scenario.get(7).add(null);
//			 scenario.get(7).add("123");
//			 scenario.get(7).add(null);
//			 scenario.get(7).add(null);
//			 scenario.get(7).add(null);
//			 //Result of the query1
//			 scenario.get(7).add(new DCCombinerDisconnectEntity());
//			 //Result of the query2
//			 scenario.get(7).add(null);
//			 //Result of the query3
//			 scenario.get(7).add(null);
//		     //Result excpected
//			 scenario.get(7).add("fail");
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(8).add(true);
//			 scenario.get(8).add(823);
//			 scenario.get(8).add(823);
//			 scenario.get(8).add(null);
//			 scenario.get(8).add(null);
//			 scenario.get(8).add("123");
//			 scenario.get(8).add(null);
//			 scenario.get(8).add(null);
//			 scenario.get(8).add(null);
//			 //Result of the query1
//			 scenario.get(8).add(new DCCombinerDisconnectEntity());
//			 //Result of the query2
//			 scenario.get(8).add(new AuthentificationEntity());
//			 //Result of the query3
//			 scenario.get(8).add(null);
//		     //Result excpected
//			 scenario.get(8).add("Done");
//
//			 for(int i=0;i<scenario.size();i++) {
//				 System.out.println("updateDcCombiOrDiscStatus [ "+i+" ]");
//	             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(9));
//	             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(10));
//	            when(mockedQuery3.executeUpdate()).thenReturn(Mockito.anyInt());
//	            getAlldcCombinerOrDiService.updateDcCombiOrDiscStatus((Boolean)scenario.get(i).get(0),(Integer)scenario.get(i).get(1),(Integer)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6),(String)scenario.get(i).get(7),(String)scenario.get(i).get(8));
//
//			 }
//	}
//
//	@Test
//	public void testeditDCcombiOrDisc() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.manufacturer = :p1 AND u.model= :p2 AND u.id != :p3 AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u FROM PermitProjectSiteInfoEntity u WHERE u.disconnectModel = :p1 OR u.disconnectModelTwo = :p1 OR u.disconnectModelThree = :p1 ")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//
//        ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//        scenario.add(new ArrayList<Object>());
//        //List of the parameter
//        scenario.get(0).add(null);
//        scenario.get(0).add(null);
//        scenario.get(0).add(null);
//        scenario.get(0).add(null);
//        scenario.get(0).add(null);
//        scenario.get(0).add(null);
//        scenario.get(0).add(null);
//        //The result of Query1
//        scenario.get(0).add(null);
//       //The result of Query2
//        scenario.get(0).add(null);
//       //The result of Query3
//        scenario.get(0).add(null);
//        //The result expected
//        scenario.get(0).add("error");
//      //The result of Query2.2
//        scenario.get(0).add(null);
//
//        scenario.add(new ArrayList<Object>());
//        //List of the parameter
//        scenario.get(1).add(new DCCombinerDisconnectEntity());
//        scenario.get(1).add(null);
//        scenario.get(1).add(null);
//        scenario.get(1).add(null);
//        scenario.get(1).add(null);
//        scenario.get(1).add(null);
//        scenario.get(1).add(null);
//        //The result of Query1
//        scenario.get(1).add(null);
//       //The result of Query2
//        scenario.get(1).add(null);
//       //The result of Query3
//        scenario.get(1).add(null);
//        //The result expected
//        scenario.get(1).add("error");
//      //The result of Query2.2
//        scenario.get(1).add(null);
//
//        scenario.add(new ArrayList<Object>());
//        //List of the parameter
//        scenario.get(2).add(new DCCombinerDisconnectEntity());
//        scenario.get(2).add(null);
//        scenario.get(2).add(null);
//        scenario.get(2).add(null);
//        scenario.get(2).add(null);
//        scenario.get(2).add(null);
//        scenario.get(2).add(null);
//        //The result of Query1
//        scenario.get(2).add(null);
//       //The result of Query2
//        scenario.get(2).add(new ArrayList<DCCombinerDisconnectEntity>());
//       //The result of Query3
//        scenario.get(2).add(null);
//        //The result expected
//        scenario.get(2).add("error");
//      //The result of Query2.2
//        scenario.get(2).add(null);
//
//        scenario.add(new ArrayList<Object>());
//        //List of the parameter
//        scenario.get(3).add(new DCCombinerDisconnectEntity());
//        scenario.get(3).add(null);
//        scenario.get(3).add(null);
//        scenario.get(3).add(null);
//        scenario.get(3).add(null);
//        scenario.get(3).add(null);
//        scenario.get(3).add(null);
//        //The result of Query1
//        scenario.get(3).add(new DCCombinerDisconnectEntity());
//       //The result of Query2
//        scenario.get(3).add(new ArrayList<DCCombinerDisconnectEntity>());
//       //The result of Query3
//        scenario.get(3).add(null);
//        //The result expected
//        scenario.get(3).add("success");
//      //The result of Query2.2
//        scenario.get(3).add(null);
//
//        scenario.add(new ArrayList<Object>());
//        //List of the parameter
//        scenario.get(4).add(new DCCombinerDisconnectEntity());
//        scenario.get(4).add("");
//        scenario.get(4).add("");
//        scenario.get(4).add("");
//        scenario.get(4).add("");
//        scenario.get(4).add("");
//        scenario.get(4).add("");
//        //The result of Query1
//        scenario.get(4).add(new DCCombinerDisconnectEntity());
//       //The result of Query2
//        scenario.get(4).add(new ArrayList<DCCombinerDisconnectEntity>());
//       //The result of Query3
//        scenario.get(4).add(null);
//        //The result expected
//        scenario.get(4).add("success");
//      //The result of Query2.2
//        scenario.get(4).add(null);
//
//
//        scenario.add(new ArrayList<Object>());
//        //List of the parameter
//        scenario.get(5).add(new DCCombinerDisconnectEntity());
//        scenario.get(5).add("123");
//        scenario.get(5).add("123");
//        scenario.get(5).add("123");
//        scenario.get(5).add("123");
//        scenario.get(5).add("123");
//        scenario.get(5).add("123");
//        //The result of Query1
//        scenario.get(5).add(new DCCombinerDisconnectEntity());
//       //The result of Query2
//        scenario.get(5).add(new ArrayList<DCCombinerDisconnectEntity>());
//       //The result of Query3
//        scenario.get(5).add(null);
//        //The result expected
//        scenario.get(5).add("success");
//        //The result of Query2.2
//        scenario.get(5).add(null);
//
//        scenario.add(new ArrayList<Object>());
//        //List of the parameter
//        scenario.get(6).add(new DCCombinerDisconnectEntity());
//        scenario.get(6).add("123");
//        scenario.get(6).add("123");
//        scenario.get(6).add("123");
//        scenario.get(6).add("123");
//        scenario.get(6).add("123");
//        scenario.get(6).add("123");
//        //The result of Query1
//        scenario.get(6).add(new DCCombinerDisconnectEntity());
//       //The result of Query2
//        scenario.get(6).add(new ArrayList<DCCombinerDisconnectEntity>());
//       //The result of Query3
//        scenario.get(6).add(null);
//        //The result expected
//        scenario.get(6).add("success");
//        //The result of Query2.2
//        scenario.get(6).add(null);
//
//
//        scenario.add(new ArrayList<Object>());
//        //List of the parameter
//        scenario.get(7).add(new DCCombinerDisconnectEntity());
//        scenario.get(7).add(null);
//        scenario.get(7).add(null);
//        scenario.get(7).add(null);
//        scenario.get(7).add(null);
//        scenario.get(7).add(null);
//        scenario.get(7).add(null);
//        //The result of Query1
//        scenario.get(7).add(new DCCombinerDisconnectEntity());
//       //The result of Query2
//        ArrayList<DCCombinerDisconnectEntity> list = new ArrayList<DCCombinerDisconnectEntity>();
//        DCCombinerDisconnectEntity ac1 = new DCCombinerDisconnectEntity();
//        ac1.setModel("");
//        list.add(ac1);
//        scenario.get(7).add(list);
//       //The result of Query3
//        scenario.get(7).add(null);
//        //The result expected
//        scenario.get(7).add("exist");
//        //The result of Query2.2
//        scenario.get(7).add(ac1);
//
//
//        for(int i=0;i<scenario.size();i++) {
//       	 System.out.println("editDCcombiOrDisc [ "+i+" ]"+scenario.get(i).get(8));
//            when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//            when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(8));
//             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(11));
//            when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(9));
//            getAlldcCombinerOrDiService.editDCcombiOrDisc((DCCombinerDisconnectEntity)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6));
//
//        }
//
//	}
//
//	@Test
//	public void testgetRemovedcComDisconnectConfirmation() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel  "
//					+ " ( u.homeOwnName, " + " u.status, " + " v.firstName, " + " v.lastName)"
//					+ " from PermitEntity u, " + " AuthentificationEntity v, " + " PermitProjectSiteInfoEntity w"
//					+ " where w.disconnectModel = :p1 and  u.isDeleted  = :p2 and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = :p2 and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		     ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 //Result of the query1
//			 scenario.get(0).add(null);
//		     //Result excpected
//			 scenario.get(0).add(new ArrayList<ProjectForLibrariesModel>());
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(1).add("");
//			 scenario.get(1).add("");
//			 //Result of the query1
//			 scenario.get(1).add(null);
//		     //Result excpected
//			 scenario.get(1).add(new ArrayList<ProjectForLibrariesModel>());
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(2).add("");
//			 scenario.get(2).add("");
//			 //Result of the query1
//			 ArrayList<ProjectForLibrariesModel> list = new ArrayList<ProjectForLibrariesModel>();
//			 list.add(null);
//			 list.add(new ProjectForLibrariesModel());
//			 scenario.get(2).add(list);
//		     //Result excpected
//			 scenario.get(2).add(list);
//
//
//
//			 for(int i=0;i<scenario.size();i++) {
//				 System.out.println("getRemoveAcCombinerDiscoConfirmation [ "+i+" ]");
//	             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//	            getAlldcCombinerOrDiService.getRemovedcComDisconnectConfirmation((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//			 }
//	}
//
//	@Test
//	public void testdeletedcCombOrDisco() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from PermitProjectSiteInfoEntity u WHERE u.disconnectModel = :p1 and u.permitEntity.isDeleted  = :p2")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from PermitProjectSiteInfoEntity u WHERE u.disconnectModelTwo = :p1 and u.permitEntity.isDeleted  = :p2")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//		 Query mockedQuery4 = mock(Query.class);
//		 when(em.createQuery("SELECT u from PermitProjectSiteInfoEntity u WHERE u.disconnectModelThree = :p1 and u.permitEntity.isDeleted  = :p2")).thenReturn(mockedQuery4);
//		 when(mockedQuery4.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery4);
//
//		 Query mockedQuery5 = mock(Query.class);
//		 when(em.createQuery("SELECT u from PermitProjectSiteInfoEntity u WHERE u.roofTopDCDisco = :p1 and u.permitEntity.isDeleted  = :p2")).thenReturn(mockedQuery5);
//		 when(mockedQuery5.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery5);
//
//
//		 Query mockedQuery6 = mock(Query.class);
//		 when(em.createQuery("SELECT u from PermitProjectSiteInfoEntity u WHERE u.roofTopDCCombiner = :p1 and u.permitEntity.isDeleted  = :p2")).thenReturn(mockedQuery6);
//		 when(mockedQuery6.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery6);
//
//		 Query mockedQuery7 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DcCombinerorDiscFavoriteEntity u WHERE u.dCCombinerDisconnectEntity.id = :p1")).thenReturn(mockedQuery7);
//		 when(mockedQuery7.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery7);
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 scenario.get(0).add(null);
//			 //Result of the query1
//			 scenario.get(0).add(null);
//			 //Result of the query2
//			 scenario.get(0).add(null);
//			 //Result of the query3
//			 scenario.get(0).add(null);
//			 //Result of the query4
//			 scenario.get(0).add(null);
//			 //Result of the query5
//			 scenario.get(0).add(null);
//			 //Result of the query6
//			 scenario.get(0).add(null);
//			 //Result of the query7
//			 scenario.get(0).add(null);
//		     //Result excpected
//			 scenario.get(0).add("error");
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 scenario.get(1).add(null);
//			 //Result of the query1
//			 scenario.get(1).add(new DCCombinerDisconnectEntity());
//			 //Result of the query2
//			 scenario.get(1).add(null);
//			 //Result of the query3
//			 scenario.get(1).add(null);
//			 //Result of the query4
//			 scenario.get(1).add(null);
//			 //Result of the query5
//			 scenario.get(1).add(null);
//			 //Result of the query6
//			 scenario.get(1).add(null);
//			 //Result of the query7
//			 scenario.get(1).add(null);
//		     //Result excpected
//			 scenario.get(1).add("Done");
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 scenario.get(2).add(null);
//			 //Result of the query1
//			 scenario.get(2).add(new DCCombinerDisconnectEntity());
//			 //Result of the query2
//			 scenario.get(2).add(null);
//			 //Result of the query3
//			 scenario.get(2).add(null);
//			 //Result of the query4
//			 scenario.get(2).add(null);
//			 //Result of the query5
//			 scenario.get(2).add(null);
//			 //Result of the query6
//			 scenario.get(2).add(null);
//			 //Result of the query7
//			 scenario.get(2).add(null);
//		     //Result excpected
//			 scenario.get(2).add("Done");
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 scenario.get(3).add(null);
//			 //Result of the query1
//			 scenario.get(3).add(new DCCombinerDisconnectEntity());
//			 //Result of the query2
//			 ArrayList<DCCombinerDisconnectEntity>  list =new ArrayList<DCCombinerDisconnectEntity>();
//			 list.add(null);
//			 list.add(new DCCombinerDisconnectEntity());
//			 scenario.get(3).add(list);
//			 //Result of the query3
//			 scenario.get(3).add(null);
//			 //Result of the query4
//			 scenario.get(3).add(null);
//			 //Result of the query5
//			 scenario.get(3).add(null);
//			 //Result of the query6
//			 scenario.get(3).add(null);
//			 //Result of the query7
//			 scenario.get(3).add(null);
//		     //Result excpected
//			 scenario.get(3).add("error");
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 scenario.get(4).add(null);
//			 //Result of the query1
//			 scenario.get(4).add(new DCCombinerDisconnectEntity());
//			 //Result of the query2
//			 scenario.get(4).add(null);
//			 //Result of the query3
//			 scenario.get(4).add(list);
//			 //Result of the query4
//			 scenario.get(4).add(null);
//			 //Result of the query5
//			 scenario.get(4).add(null);
//			 //Result of the query6
//			 scenario.get(4).add(null);
//			 //Result of the query7
//			 scenario.get(4).add(null);
//		     //Result excpected
//			 scenario.get(4).add("error");
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 scenario.get(5).add(null);
//			 //Result of the query1
//			 scenario.get(5).add(new DCCombinerDisconnectEntity());
//			 //Result of the query2
//			 scenario.get(5).add(null);
//			 //Result of the query3
//			 scenario.get(5).add(null);
//			 //Result of the query4
//			 scenario.get(5).add(list);
//			 //Result of the query5
//			 scenario.get(5).add(null);
//			 //Result of the query6
//			 scenario.get(5).add(null);
//			 //Result of the query7
//			 scenario.get(5).add(null);
//		     //Result excpected
//			 scenario.get(5).add("error");
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 scenario.get(6).add(null);
//			 //Result of the query1
//			 scenario.get(6).add(new DCCombinerDisconnectEntity());
//			 //Result of the query2
//			 scenario.get(6).add(null);
//			 //Result of the query3
//			 scenario.get(6).add(null);
//			 //Result of the query4
//			 scenario.get(6).add(null);
//			 //Result of the query5
//			 scenario.get(6).add(list);
//			 //Result of the query6
//			 scenario.get(6).add(null);
//			 //Result of the query7
//			 scenario.get(6).add(null);
//		     //Result excpected
//			 scenario.get(6).add("error");
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(7).add(null);
//			 scenario.get(7).add(null);
//			 scenario.get(7).add(null);
//			 scenario.get(7).add(null);
//			 scenario.get(7).add(null);
//			 scenario.get(7).add(null);
//			 scenario.get(7).add(null);
//			 //Result of the query1
//			 scenario.get(7).add(new DCCombinerDisconnectEntity());
//			 //Result of the query2
//			 scenario.get(7).add(null);
//			 //Result of the query3
//			 scenario.get(7).add(null);
//			 //Result of the query4
//			 scenario.get(7).add(null);
//			 //Result of the query5
//			 scenario.get(7).add(null);
//			 //Result of the query6
//			 scenario.get(7).add(list);
//			 //Result of the query7
//			 scenario.get(7).add(null);
//		     //Result excpected
//			 scenario.get(7).add("error");
//
//			 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(8).add(null);
//			 scenario.get(8).add(null);
//			 scenario.get(8).add(null);
//			 scenario.get(8).add(null);
//			 scenario.get(8).add(null);
//			 scenario.get(8).add(null);
//			 scenario.get(8).add(null);
//			 //Result of the query1
//			 scenario.get(8).add(new DCCombinerDisconnectEntity());
//			 //Result of the query2
//			 scenario.get(8).add(null);
//			 //Result of the query3
//			 scenario.get(8).add(null);
//			 //Result of the query4
//			 scenario.get(8).add(null);
//			 //Result of the query5
//			 scenario.get(8).add(null);
//			 //Result of the query6
//			 scenario.get(8).add(null);
//			 //Result of the query7
//			 ArrayList<DcCombinerorDiscFavoriteEntity> list2 = new ArrayList<DcCombinerorDiscFavoriteEntity>();
//			 list2.add(null);
//			 list2.add(new DcCombinerorDiscFavoriteEntity());
//			 scenario.get(8).add(list2);
//		     //Result excpected
//			 scenario.get(8).add("Done");
//
//			 for(int i=0;i<scenario.size();i++) {
//				 System.out.println("deletedcCombOrDisco [ "+i+" ]");
//	            when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//	            when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(8));
//	            when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(9));
//	            when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(10));
//	            when(mockedQuery5.getResultList()).thenReturn((List) scenario.get(i).get(11));
//	            when(mockedQuery6.getResultList()).thenReturn((List) scenario.get(i).get(12));
//	            when(mockedQuery7.getResultList()).thenReturn((List) scenario.get(i).get(13));
//	            getAlldcCombinerOrDiService.deletedcCombOrDisco((Integer)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6));
//
//			 }
//	}
//
//	@Test
//	public void testgetUsersForFavList() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DcCombinerorDiscFavoriteEntity u WHERE u.dCCombinerDisconnectEntity.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult ( "
//					+ "   u.id,  " + "   u.firstName,  " + "   u.lastName ) "
//					+ " from AuthentificationEntity u WHERE u.id NOT IN :p1 and u.deleted = :p2 and u.active = :p3 and u.id != :p4 ORDER BY u.firstName")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult ( "
//					+ "   u.id,  " + "   u.firstName,  " + "   u.lastName ) "
//					+ " from AuthentificationEntity u WHERE u.deleted = :p2 and u.active = :p3 and u.id != :p4 ORDER BY u.firstName")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//        ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result of the query2
//		 scenario.get(0).add(null);
//		 //Result of the query3
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add(new ArrayList<UsersEntityResult>());
//
//		 scenario.add(new ArrayList<Object>());
//			//List of the parameter
//			 scenario.get(1).add(null);
//			 scenario.get(1).add("");
//			 //Result of the query1
//			 scenario.get(1).add(new ArrayList<ACDisconnectFavLibraryEntity>());
//			 //Result of the query2
//			 scenario.get(1).add(null);
//			 //Result of the query3
//			 scenario.get(1).add(null);
//		     //Result excpected
//			 scenario.get(1).add(new ArrayList<UsersEntityResult>());
//
//			 scenario.add(new ArrayList<Object>());
//				//List of the parameter
//				 scenario.get(2).add(null);
//				 scenario.get(2).add("113");
//				 //Result of the query1
//				 scenario.get(2).add(new ArrayList<ACDisconnectFavLibraryEntity>());
//				 //Result of the query2
//				 scenario.get(2).add(null);
//				 //Result of the query3
//				 scenario.get(2).add(null);
//			     //Result excpected
//				 scenario.get(2).add(null);
//
//				 scenario.add(new ArrayList<Object>());
//					//List of the parameter
//					 scenario.get(3).add(null);
//					 scenario.get(3).add("113");
//					 //Result of the query1
//					 scenario.get(3).add(new ArrayList<ACDisconnectFavLibraryEntity>());
//					 //Result of the query2
//					 scenario.get(3).add(null);
//					 //Result of the query3
//					 scenario.get(3).add(new ArrayList<UsersEntityResult>());
//				     //Result excpected
//					 scenario.get(3).add(new ArrayList<UsersEntityResult>());
//
//					 scenario.add(new ArrayList<Object>());
//						//List of the parameter
//						 scenario.get(4).add(null);
//						 scenario.get(4).add("113");
//						 //Result of the query1
//						ArrayList<ACDisconnectFavLibraryEntity> list = new ArrayList<ACDisconnectFavLibraryEntity>();
//						list.add(null);
//						list.add(new ACDisconnectFavLibraryEntity());
//						ACDisconnectFavLibraryEntity ac = new ACDisconnectFavLibraryEntity();
//						ac.setAuthentificationEntity(new AuthentificationEntity());
//						list.add(ac);
//						 scenario.get(4).add(list);
//						 //Result of the query2
//						 scenario.get(4).add(null);
//						 //Result of the query3
//						 scenario.get(4).add(new ArrayList<UsersEntityResult>());
//					     //Result excpected
//						 scenario.get(4).add(new ArrayList<UsersEntityResult>());
//
//						 scenario.add(new ArrayList<Object>());
//							//List of the parameter
//							 scenario.get(5).add(null);
//							 scenario.get(5).add("113");
//							 //Result of the query1
//							 scenario.get(5).add(list);
//							 //Result of the query2
//							 scenario.get(5).add(new ArrayList<UsersEntityResult>());
//							 //Result of the query3
//							 scenario.get(5).add(new ArrayList<UsersEntityResult>());
//						     //Result excpected
//							 scenario.get(5).add(new ArrayList<UsersEntityResult>());
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getUsersForFavList [ "+i+" ]");
//            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//            when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(3));
//            when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(4));
//            getAlldcCombinerOrDiService.getUsersForFavList((Integer)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//		 }
//	}
//
//	@Test
//	public void testeditUsersFavoriteList() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//         ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of the query1
//		 scenario.get(0).add(null);
//		 //Result of the query2
//		 scenario.get(0).add(null);
//	     //Result excpected
//		 scenario.get(0).add("error");
//
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(1).add(null);
//		 String[] ListUsers = {"",null,"1233"};
//		 scenario.get(1).add(ListUsers);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of the query1
//		 scenario.get(1).add(null);
//		 //Result of the query2
//		 scenario.get(1).add(null);
//	     //Result excpected
//		 scenario.get(1).add("error");
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(2).add(null);
//		 String[] ListUsers2 = {"12","44","1233"};
//		 scenario.get(2).add(ListUsers2);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //Result of the query1
//		 scenario.get(2).add(null);
//		 //Result of the query2
//		 scenario.get(2).add(null);
//	     //Result excpected
//		 scenario.get(2).add("error");
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(ListUsers2);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //Result of the query1
//		 scenario.get(3).add(new DCCombinerDisconnectEntity());
//		 //Result of the query2
//		 scenario.get(3).add(null);
//	     //Result excpected
//		 scenario.get(3).add("error");
//
//		 scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(ListUsers2);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 //Result of the query1
//		 scenario.get(4).add(new DCCombinerDisconnectEntity());
//		 //Result of the query2
//		 scenario.get(4).add(new AuthentificationEntity());
//	     //Result excpected
//		 scenario.get(4).add("Done");
//
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("editUsersFavoriteList [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(8));
//             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(9));
//             getAlldcCombinerOrDiService.editUsersFavoriteList((Integer)scenario.get(i).get(0),(String[])scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6),(String)scenario.get(i).get(7));
//
//		 }
//
//	}
//
//	@Test
//	public void testgetdeleteddcCombinerOrDis() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.isDeleted = :p1 AND (u.typeDc= :p2 OR u.typeDc= :p3 or u.typeDc= :p4 or u.typeDc= :p5 or u.typeDc= :p6) ORDER BY u.manufacturer")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//	      ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//			 scenario.add(new ArrayList<Object>());
//			 //Result of the query1
//			 scenario.get(0).add(null);
//		     //Result excpected
//			 scenario.get(0).add(new ArrayList<DcCombinerorDisconnectModel>());
//
//			 scenario.add(new ArrayList<Object>());
//			 //Result of the query1
//			 List<DCCombinerDisconnectEntity> list = new ArrayList<DCCombinerDisconnectEntity>();
//			 list.add(null);
//			 list.add(new DCCombinerDisconnectEntity());
//			 DCCombinerDisconnectEntity dc = new DCCombinerDisconnectEntity();
//			 AuthentificationEntity auth = new AuthentificationEntity();
//			 auth.setFirstName("aaa");
//			 auth.setLastName("aaa");
//			 auth.setId(12358);
//			 dc.setIdOwner(auth);
//			 list.add(dc);
//			 scenario.get(1).add(list);
//		     //Result excpected
//			 List<DcCombinerorDisconnectModel> listExpec = new ArrayList<DcCombinerorDisconnectModel>();
//			 listExpec.add(new DcCombinerorDisconnectModel());
//			 DcCombinerorDisconnectModel dcCombD = new DcCombinerorDisconnectModel();
//			 dcCombD.setOwner("Super User");
//			 listExpec.add(dcCombD);
//			 DcCombinerorDisconnectModel dcCombD2 = new DcCombinerorDisconnectModel();
//			 dcCombD2.setIdOwner(auth.getId());
//			 dcCombD2.setOwner(auth.getFirstName()+" "+auth.getLastName());
//			 listExpec.add(dcCombD2);
//			 scenario.get(1).add(listExpec);
//
//			 for(int i=0;i<scenario.size();i++) {
//				 System.out.println("getdeleteddcCombinerOrDis [ "+i+" ]");
//	             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//	             ArrayList<DcCombinerorDisconnectModel> result = (ArrayList<DcCombinerorDisconnectModel>)  getAlldcCombinerOrDiService.getdeleteddcCombinerOrDis();
//
//			 }
//	}
//
//	@Test
//	public void testactivatedcComOrDisc() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//
//		 //List of scenario
//		 List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//
//		 //The result of query1
//		 scenario.get(0).add(null);
//		 //The result of the query2
//		 scenario.get(0).add(null);
//		 //The result excpected
//		 scenario.get(0).add("Fail");
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//
//		 //The result of query1
//		 scenario.get(1).add(null);
//		 //The result of the query2
//		 scenario.get(1).add(null);
//		 //The result excpected
//		 scenario.get(1).add("Fail");
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//
//		 //The result of query1
//		 scenario.get(2).add(null);
//		 //The result of the query2
//		 scenario.get(2).add(null);
//		 //The result excpected
//		 scenario.get(2).add("Fail");
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//
//		 //The result of query1
//		 DCCombinerDisconnectEntity ac1 = new DCCombinerDisconnectEntity();
//		 scenario.get(3).add(ac1);
//		 //The result of the query2
//		 scenario.get(3).add(null);
//		 //The result excpected
//		 scenario.get(3).add("Fail");
//
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//
//		 //The result of query1
//		 scenario.get(4).add(null);
//		 //The result of the query2
//		 scenario.get(4).add(null);
//		 //The result excpected
//		 scenario.get(4).add("Fail");
//
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//
//		 //The result of query1
//		 DCCombinerDisconnectEntity acCombiner = new DCCombinerDisconnectEntity();
//		 acCombiner.setManufacturer("");
//		 acCombiner.setModel("");
//		 scenario.get(5).add(acCombiner);
//		 //The result of the query2
//		 scenario.get(5).add(null);
//		 //The result excpected
//		 scenario.get(5).add("Fail");
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//
//		 //The result of query1
//		 scenario.get(6).add(acCombiner);
//		 //The result of the query2
//		 scenario.get(6).add(new ArrayList<DCCombinerDisconnectEntity>());
//		 //The result excpected
//		 scenario.get(6).add("Success");
//
//		 scenario.add(new ArrayList());
//		 //List of parameter possible
//		 scenario.get(7).add("123");
//		 scenario.get(7).add("123");
//		 scenario.get(7).add("123");
//		 scenario.get(7).add("123");
//		 scenario.get(7).add("123");
//		 scenario.get(7).add("123");
//		 scenario.get(7).add("123");
//
//		 //The result of query1
//		 scenario.get(7).add(acCombiner);
//		 //The result of the query2
//		 ArrayList<DCCombinerDisconnectEntity> listAcCom= new ArrayList<DCCombinerDisconnectEntity>();
//		 listAcCom.add(new DCCombinerDisconnectEntity());
//		 scenario.get(7).add(listAcCom);
//		 //The result excpected
//		 scenario.get(7).add("exist");
//
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			  System.out.println("activateAcCombinerDisco [ "+i+" ]");
//			 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(8));
//			getAlldcCombinerOrDiService.activatedcComOrDisc((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6));
//
//		 }
//	}
//
//	@Test
//	public void testgetSearchDcCombOrDisconnect() {
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(DCCombinerDisconnectEntity.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(DCCombinerDisconnectEntity.class)).thenReturn(root);
//        when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//
//        TypedQuery mockedQuery1 = mock(TypedQuery.class);
//        when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//        GetAlldcCombinerOrDiService getAlldcCombinerOrDiService2 = Mockito.spy(getAlldcCombinerOrDiService);
//
//		//Any of the Query Res possible
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.idOwner.id = :p1 and u.isDeleted = :p2 and u.maxInput = :p3 and u.ocpd = :p4")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.idOwner.id = :p1 and u.isDeleted = :p2 and u.maxInput = :p3")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//		 Query mockedQuery4 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.idOwner.id = :p1 and u.isDeleted = :p2 and u.ocpd = :p4")).thenReturn(mockedQuery4);
//		 when(mockedQuery4.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery4);
//
//		 Query mockedQuery5 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.idOwner.id = :p1 and u.isDeleted = :p2")).thenReturn(mockedQuery5);
//		 when(mockedQuery5.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery5);
//
//		//Any of the Query Res2 possible
//		 Query mockedQuery6 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DcCombinerorDiscFavoriteEntity u WHERE u.dCCombinerDisconnectEntity.id = :p1")).thenReturn(mockedQuery6);
//		 when(mockedQuery6.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery6);
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(0).add(null);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(0).add(null);
//		//The result of the Query RES2
//		 scenario.get(0).add(null);
//		 //The result expected
//		 scenario.get(0).add(new ArrayList<DcCombinerorDisconnectModel>());
//		 //The list of the getSearchDcCombOrDisconnect
//		 scenario.get(0).add(new ArrayList<Integer>());
//
//		 //List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(1).add(new ArrayList<DcCombinerorDisconnectModel>());
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(1).add(null);
//		//The result of the Query RES2
//		 scenario.get(1).add(null);
//		 //The result expected
//		 scenario.get(1).add(new ArrayList<DcCombinerorDisconnectModel>());
//		 //The list of the getSearchDcCombOrDisconnect
//		 scenario.get(1).add(new ArrayList<Integer>());
//
//		 //List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 ArrayList<DCCombinerDisconnectEntity> listACDisco =  new ArrayList<DCCombinerDisconnectEntity>();
//		 listACDisco.add(null);
//		 listACDisco.add(new DCCombinerDisconnectEntity());
//		 DCCombinerDisconnectEntity ac1 = new DCCombinerDisconnectEntity();
//		 ac1.setIdOwner(new AuthentificationEntity());
//		 ac1.setMaxInput("");
//		 listACDisco.add(ac1);
//		 scenario.get(2).add(listACDisco);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(2).add(null);
//		//The result of the Query RES2
//		 scenario.get(2).add(null);
//		 //The result expected
//		 scenario.get(2).add(new ArrayList<DcCombinerorDisconnectModel>());
//		 //The list of the getSearchDcCombOrDisconnect
//		 scenario.get(2).add(new ArrayList<Integer>());
//
//		 //List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(3).add(new DcCombinerOrDisconnectRequest());
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 scenario.get(3).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(3).add(listACDisco);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(3).add(null);
//		//The result of the Query RES2
//		 scenario.get(3).add(null);
//		 //The result expected
//		 ArrayList<DcCombinerorDisconnectModel> listExp = new ArrayList<DcCombinerorDisconnectModel>();
//		 listExp.add(new DcCombinerorDisconnectModel());
//		 DcCombinerorDisconnectModel dc2 = new DcCombinerorDisconnectModel();
//		 dc2.setIsShown(false);
//		 listExp.add(dc2);
//		 DcCombinerorDisconnectModel dc = new DcCombinerorDisconnectModel();
//		 dc.setOwner("null null");
//		 dc.setIdOwner(0);
//		 dc.setMaxInput("");
//		 dc.setIsShown(false);
//		 listExp.add(dc);
//		 scenario.get(3).add(listExp);
//		 //The list of the getSearchDcCombOrDisconnect
//		 scenario.get(3).add(new ArrayList<Integer>());
//
//		 //List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(4).add(new DcCombinerOrDisconnectRequest());
//		 scenario.get(4).add(null);
//		 scenario.get(4).add("");
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(4).add(listACDisco);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(4).add(null);
//		//The result of the Query RES2
//		 scenario.get(4).add(null);
//		 //The result expected
//		 scenario.get(4).add(listExp);
//		 //The list of the getSearchDcCombOrDisconnect
//		 scenario.get(4).add(new ArrayList<Integer>());
//
//
//		//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(5).add(new DcCombinerOrDisconnectRequest());
//		 scenario.get(5).add(null);
//		 scenario.get(5).add("123");
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(5).add(listACDisco);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(5).add(null);
//		//The result of the Query RES2
//		 scenario.get(5).add(null);
//		 //The result expected
//		 scenario.get(5).add(listExp);
//		 //The list of the getSearchDcCombOrDisconnect
//		 scenario.get(5).add(new ArrayList<Integer>());
//
//
//			//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(6).add(new DcCombinerOrDisconnectRequest());
//		 scenario.get(6).add(null);
//		 scenario.get(6).add("123");
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(6).add(listACDisco);
//		//The result of the Query2 (Daynamic Query)
//		 List<DCCombinerDisconnectEntity> listACDisco2 = new ArrayList<DCCombinerDisconnectEntity>();
//		 listACDisco2.add(null);
//		 listACDisco2.add(new DCCombinerDisconnectEntity());
//		 DCCombinerDisconnectEntity ac2 = new DCCombinerDisconnectEntity();
//		 ac2.setIdOwner(new AuthentificationEntity());
//		 listACDisco2.add(ac2);
//		 scenario.get(6).add(listACDisco2);
//		//The result of the Query RES2
//		 scenario.get(6).add(null);
//		 //The result expected
//		 scenario.get(6).add(listExp);
//		 //The list of the getSearchDcCombOrDisconnect
//		 scenario.get(6).add(new ArrayList<Integer>());
//
//			//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 DcCombinerOrDisconnectRequest acCombModel = new DcCombinerOrDisconnectRequest();
//		 acCombModel.setModel("test aaa hhh_cccc ddd_eeee");
//		 scenario.get(7).add(acCombModel);
//		 scenario.get(7).add(null);
//		 scenario.get(7).add("123");
//		 scenario.get(7).add(null);
//		 scenario.get(7).add(null);
//		 scenario.get(7).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(7).add(listACDisco);
//		//The result of the Query2 (Daynamic Query)
//		 scenario.get(7).add(listACDisco2);
//		//The result of the Query RES2
//		 scenario.get(7).add(null);
//		 //The result expected
//		 scenario.get(7).add(listExp);
//		 //The list of the getSearchDcCombOrDisconnect
//		 scenario.get(7).add(new ArrayList<Integer>());
//
//			//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(8).add(acCombModel);
//		 scenario.get(8).add(null);
//		 scenario.get(8).add("123");
//		 scenario.get(8).add(null);
//		 scenario.get(8).add(null);
//		 scenario.get(8).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(8).add(listACDisco);
//		//The result of the Query RES
//		 List<DCCombinerDisconnectEntity> listACDisco4 = new ArrayList<DCCombinerDisconnectEntity>();
//		 DCCombinerDisconnectEntity ac3 = new DCCombinerDisconnectEntity();
//		 ac3.setModel("hhh_cccc");
//		 ac3.setManufacturer("aaa_hhh");
//		 ac3.setId(12589);
//		 ac3.setIdOwner(new AuthentificationEntity());
//		 listACDisco4.add(ac3);
//		 scenario.get(8).add(listACDisco4);
//		//The result of the Query RES2
//		 scenario.get(8).add(null);
//		 //The result expected
//		 ArrayList<DcCombinerorDisconnectModel> listExp2 = new ArrayList<DcCombinerorDisconnectModel>();
//		 listExp2.add(new DcCombinerorDisconnectModel());
//		 listExp2.add(dc2);
//		 listExp2.add(dc);
//		 DcCombinerorDisconnectModel dc3 = new DcCombinerorDisconnectModel();
//		 dc3.setOwner("null null");
//		 dc3.setIdOwner(0);
//		 dc3.setModel("hhh_cccc");
//		 dc3.setManufacturer("aaa_hhh");
//		 dc3.setId(12589);
//		 dc3.setIsShown(false);
//		 listExp2.add(dc3);
//		 scenario.get(8).add(listExp2);
//		 //The list of the getSearchDcCombOrDisconnect
//		 scenario.get(8).add(new ArrayList<Integer>());
//
//			//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 DcCombinerOrDisconnectRequest acCombModel2 = new DcCombinerOrDisconnectRequest();
//		 acCombModel2.setManufacturer("test aaa aaa_hhh");
//		 acCombModel2.setModel("test aaa hhh_cccc ddd_eeee");
//		 scenario.get(9).add(acCombModel2);
//		 scenario.get(9).add(null);
//		 scenario.get(9).add("123");
//		 scenario.get(9).add(null);
//		 scenario.get(9).add(null);
//		 scenario.get(9).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(9).add(listACDisco);
//		//The result of the Query RES
//		 scenario.get(9).add(listACDisco4);
//		//The result of the Query RES2
//		 scenario.get(9).add(null);
//		 //The result expected
//		 scenario.get(9).add(listExp2);
//		 //The list of the getSearchDcCombOrDisconnect
//		 scenario.get(9).add(new ArrayList<Integer>());
//
//			//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(10).add(acCombModel2);
//		 scenario.get(10).add(null);
//		 scenario.get(10).add("123");
//		 scenario.get(10).add(null);
//		 scenario.get(10).add(null);
//		 scenario.get(10).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(10).add(listACDisco);
//		//The result of the Query RES
//		 List<DCCombinerDisconnectEntity> listACDisco5 = new ArrayList<DCCombinerDisconnectEntity>();
//		 DCCombinerDisconnectEntity ac4 = new DCCombinerDisconnectEntity();
//		 ac4.setModel("xxxx");
//		 ac4.setManufacturer("aaa_hhh");
//		 ac4.setId(12589);
//		 ac4.setIdOwner(new AuthentificationEntity());
//		 listACDisco5.add(ac4);
//		 scenario.get(10).add(listACDisco5);
//		//The result of the Query RES2
//		 scenario.get(10).add(null);
//		 //The result expected
//		 ArrayList<DcCombinerorDisconnectModel> listExp3 = new ArrayList<DcCombinerorDisconnectModel>();
//		 listExp3.add(new DcCombinerorDisconnectModel());
//		 listExp3.add(dc2);
//		 listExp3.add(dc);
//		 DcCombinerorDisconnectModel dc4 = new DcCombinerorDisconnectModel();
//		 dc4.setOwner("null null");
//		 dc4.setIdOwner(0);
//		 dc4.setModel("xxxx");
//		 dc4.setManufacturer("aaa_hhh");
//		 dc4.setId(12589);
//		 dc4.setIsShown(false);
//		 listExp3.add(dc4);
//		 scenario.get(10).add(listExp3);
//		 //The list of the getSearchDcCombOrDisconnect
//		 scenario.get(10).add(new ArrayList<Integer>());
//
//			//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 DcCombinerOrDisconnectRequest acCombModel4 = new DcCombinerOrDisconnectRequest();
//		 acCombModel4.setFavorite(true);
//		 scenario.get(11).add(acCombModel4);
//		 scenario.get(11).add(null);
//		 scenario.get(11).add("123");
//		 scenario.get(11).add(null);
//		 scenario.get(11).add(null);
//		 scenario.get(11).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(11).add(listACDisco);
//		//The result of the Query RES
//		 scenario.get(11).add(null);
//		//The result of the Query RES2
//		 scenario.get(11).add(null);
//		 //The result expected
//		 ArrayList<DcCombinerorDisconnectModel> listExp5 = new ArrayList<DcCombinerorDisconnectModel>();
//		 DcCombinerorDisconnectModel dc5 = new DcCombinerorDisconnectModel();
//		 dc5.setIsShown(true);
//		 listExp5.add(dc5);
//		 DcCombinerorDisconnectModel dc6 = new DcCombinerorDisconnectModel();
//		 dc6.setOwner("null null");
//		 dc6.setIdOwner(0);
//		 dc6.setMaxInput("");
//		 dc6.setIsShown(true);
//		 listExp5.add(dc6);
//		 scenario.get(11).add(listExp5);
//		 //The list of the getSearchDcCombOrDisconnect
//		 List<Integer> listDC = new ArrayList<Integer>();
//		 listDC.add(null);
//		 scenario.get(11).add(listDC);
//
//			//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(12).add(acCombModel4);
//		 scenario.get(12).add(null);
//		 scenario.get(12).add("123");
//		 scenario.get(12).add(null);
//		 scenario.get(12).add(null);
//		 scenario.get(12).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(12).add(listACDisco);
//		//The result of the Query RES
//		 scenario.get(12).add(listACDisco2);
//		//The result of the Query RES2
//		 scenario.get(12).add(null);
//		 //The result expected
//		 scenario.get(12).add(listExp5);
//		 //The list of the getSearchDcCombOrDisconnect
//		 scenario.get(12).add(listDC);
//
//			//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 DcCombinerOrDisconnectRequest acCombModel6 = new DcCombinerOrDisconnectRequest();
//		 acCombModel6.setFavorite(true);
//		 acCombModel6.setModel("ab_aa bb_bb");
//		 acCombModel6.setManufacturer("cc_cc dd_dd");
//		 scenario.get(13).add(acCombModel6);
//		 scenario.get(13).add(null);
//		 scenario.get(13).add("123");
//		 scenario.get(13).add(null);
//		 scenario.get(13).add(null);
//		 scenario.get(13).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(13).add(listACDisco);
//		//The result of the Query RES
//		 List<DCCombinerDisconnectEntity> listACDisco7 = new ArrayList<DCCombinerDisconnectEntity>();
//		 DCCombinerDisconnectEntity ac6 = new DCCombinerDisconnectEntity();
//		 ac6.setModel("ab_aa");
//		 ac6.setManufacturer("cc_cc");
//		 ac6.setId(5896665);
//		 listACDisco7.add(ac6);
//		 scenario.get(13).add(listACDisco7);
//		//The result of the Query RES2
//		 scenario.get(13).add(null);
//		 //The result expected
//		 scenario.get(13).add(listExp5);
//		 //The list of the getSearchDcCombOrDisconnect
//		 scenario.get(13).add(listDC);
//
//
//			//List of the parameter
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(14).add(acCombModel6);
//		 scenario.get(14).add(null);
//		 scenario.get(14).add("123");
//		 scenario.get(14).add(null);
//		 scenario.get(14).add(null);
//		 scenario.get(14).add(null);
//		 //The result of the Query1 (Daynamic Query)
//		 scenario.get(14).add(listACDisco);
//		//The result of the Query RES
//		 scenario.get(14).add(listACDisco7);
//		//The result of the Query RES2
//		 ArrayList<DcCombinerorDiscFavoriteEntity> listDCRES2 = new ArrayList<DcCombinerorDiscFavoriteEntity>();
//		 listDCRES2.add(null);
//		 listDCRES2.add(new DcCombinerorDiscFavoriteEntity());
//		 scenario.get(14).add(listDCRES2);
//		 //The result expected
//		 ArrayList<DcCombinerorDisconnectModel> listExp6 = new ArrayList<DcCombinerorDisconnectModel>();
//		 listExp6.add(dc5);
//		 listExp6.add(dc6);
//		 DcCombinerorDisconnectModel dc7 = new DcCombinerorDisconnectModel();
//		 dc7.setIsShown(true);
//		 dc7.setManufacturer("cc_cc");
//		 dc7.setModel("ab_aa");
//		 dc7.setId(5896665);
//		 listExp6.add(dc7);
//		 scenario.get(14).add(listExp6);
//		 //The list of the getSearchDcCombOrDisconnect
//		 scenario.get(14).add(listDC);
//
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("Search AC Combiner [ "+i+" ]");
//			 when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(6));
//			 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(7));
//			 when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(7));
//			 when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(7));
//			 when(mockedQuery5.getResultList()).thenReturn((List) scenario.get(i).get(7));
//
//			 when(mockedQuery6.getResultList()).thenReturn((List) scenario.get(i).get(8));
//
//             Mockito.doReturn(scenario.get(i).get(10)).when(getAlldcCombinerOrDiService2).getDcCombDiscFavorite(Mockito.any());
//          //  Assert.assertEquals(actual, delta);
//             ArrayList<DcCombinerorDisconnectModel> result = (ArrayList<DcCombinerorDisconnectModel>) getAlldcCombinerOrDiService2.getSearchDcCombOrDisconnect((DcCombinerOrDisconnectRequest) scenario.get(i).get(0), (Boolean)scenario.get(i).get(1), (String)scenario.get(i).get(2),(String) scenario.get(i).get(3), (String) scenario.get(i).get(4),(String) scenario.get(i).get(5));
//
//		 }
//
//	}
//
//	@Test
//	public void testeditDCCombinerNotification() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//         List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add("123");
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of query1
//		 scenario.get(1).add(new AuthentificationEntity());
//		//Excpected Result
//		 scenario.get(1).add("Success");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add("");
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //Result of query1
//		 scenario.get(2).add(new AuthentificationEntity());
//		//Excpected Result
//		 scenario.get(2).add("fail");
//
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("editDCCombinerNotification [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//             getAlldcCombinerOrDiService.editDCCombinerNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//
//	}
//
//	@Test
//	public void testaddDCCombinerNotification() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//         List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add("123");
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of query1
//		 scenario.get(1).add(new AuthentificationEntity());
//		//Excpected Result
//		 scenario.get(1).add("Success");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add("");
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 //Result of query1
//		 scenario.get(2).add(new AuthentificationEntity());
//		//Excpected Result
//		 scenario.get(2).add("fail");
//
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("addDCCombinerNotification [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//              getAlldcCombinerOrDiService.addDCCombinerNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//
//		 }
//	}
//
//	@Test
//	public void testsendCorrectionRequest() {
//		Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u Where u.id = :p1 ")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery(" SELECT u  FROM AuthentificationEntity u WHERE u.id = :p1  ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		  List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(0).add(null);
//			 //Result of query1
//			 scenario.get(0).add(null);
//			 //Result of query2
//			 scenario.get(0).add(null);
//			//Excpected Result
//			 scenario.get(0).add("Fail");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 DcCombinerDisconnectCorrectionModel acComSLC = new DcCombinerDisconnectCorrectionModel();
//			 scenario.get(1).add(acComSLC);
//			 //Result of query1
//			 scenario.get(1).add(null);
//			 //Result of query2
//			 scenario.get(1).add(null);
//			//Excpected Result
//			 scenario.get(1).add("Fail");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 DcCombinerDisconnectCorrectionModel acComSLC1= new DcCombinerDisconnectCorrectionModel();
//			 acComSLC1.setId(123);
//			 scenario.get(2).add(acComSLC1);
//			 //Result of query1
//			 scenario.get(2).add(null);
//			 //Result of query2
//			 scenario.get(2).add(null);
//			//Excpected Result
//			 scenario.get(2).add("Fail");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 DcCombinerDisconnectCorrectionModel acComSLC2= new DcCombinerDisconnectCorrectionModel();
//			 acComSLC2.setId(123);
//			 acComSLC2.setIdUser("");
//			 scenario.get(3).add(acComSLC2);
//			 //Result of query1
//			 scenario.get(3).add(null);
//			 //Result of query2
//			 scenario.get(3).add(null);
//			//Excpected Result
//			 scenario.get(3).add("Fail");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 DcCombinerDisconnectCorrectionModel acComSLC4= new DcCombinerDisconnectCorrectionModel();
//			 acComSLC4.setId(123);
//			 acComSLC4.setIdUser("123");
//			 scenario.get(4).add(acComSLC4);
//			 //Result of query1
//			 scenario.get(4).add(null);
//			 //Result of query2
//			 scenario.get(4).add(null);
//			//Excpected Result
//			 scenario.get(4).add("Fail");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(5).add(acComSLC4);
//			 //Result of query1
//			 DCCombinerDisconnectEntity lib = new DCCombinerDisconnectEntity();
//			 scenario.get(5).add(lib);
//			 //Result of query2
//			 scenario.get(5).add(null);
//			//Excpected Result
//			 scenario.get(5).add("Fail");
//
//			 scenario.add(new ArrayList<Object>());
//			 //List of the parameter
//			 scenario.get(6).add(acComSLC4);
//			 //Result of query1
//			 scenario.get(6).add(lib);
//			 //Result of query2
//			 AuthentificationEntity auth = new AuthentificationEntity();
//			 scenario.get(6).add(auth);
//			//Excpected Result
//			 scenario.get(6).add("Done");
//
//
//			 for(int i=0;i<scenario.size();i++) {
//				 System.out.println("sendCorrectionRequest [ "+i+" ]");
//	             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//	             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//	             getAlldcCombinerOrDiService.sendCorrectionRequest((DcCombinerDisconnectCorrectionModel)scenario.get(i).get(0));
//
//			 }
//
//	}
//
//}
