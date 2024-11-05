//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
/////
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.UnaryOperator;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//
//import org.hibernate.criterion.Restrictions;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//import org.mockito.internal.configuration.injection.MockInjection;
//import org.mockito.internal.util.MockitoSpy;
//import org.mockito.junit.MockitoJUnit;
//import org.mockito.stubbing.Answer;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.PlayGroundAdv.Solar.Entity.ACCombinerFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.ACCombinerSLC;
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.Inverters;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.model.AcCombinerSLCCorrectionModel;
//import com.PlayGroundAdv.Solar.model.AcCombinerSLCModel;
//import com.PlayGroundAdv.Solar.model.AcCombinerSLCModelRequest;
//import com.PlayGroundAdv.Solar.model.NewACCombinerSLCModel;
//import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
//import com.PlayGroundAdv.Solar.model.UsersEntityResult;
//import com.PlayGroundAdv.Solar.Services.AcCombinerSlcLibraryService;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.PlansetServiceE003String;
//import com.itextpdf.text.pdf.ArrayBasedStringTokenizer;
//
//public class TestAcCombinerSlcLibraryService {
//	@Mock
//	EntityManager em;
//	@Mock
//	private Query query;
//
//	@Mock
//	HistoryActivityService historyActivityService;
//
//	@Mock
//	NotificationEntityService notificationEntityService;
//
//
//	@Spy
//	CheckValueTypesService checkValueTypesService;
//	@Mock
//    private Root root;
//
//	 @Mock
//	 private CriteriaQuery criteriaQuery;
//
//	 @Mock
//	 private CriteriaQuery criteriaQueryAll;
//
//	 @Mock
//	 private CriteriaBuilder criteriaBuilder;
//
//	@InjectMocks
//    AcCombinerSlcLibraryService acCombinerSlcLibraryService = new AcCombinerSlcLibraryService();
//
//
//    @Before
//	public void setupMock() {
//    	   acCombinerSlcLibraryService = new AcCombinerSlcLibraryService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//
//
//	@Test
//	public void testgetAcCombinerDiscoFavorite() {
//   	 Query mockedQuery = mock(Query.class);
//   	 String idUser = "5";
// 	ArrayList<ArrayList<ACCombinerFavLibraryEntity>> resultlist = new ArrayList<ArrayList<ACCombinerFavLibraryEntity>>();
// 	resultlist.add(null);
//
// 	ArrayList<ACCombinerFavLibraryEntity> array1 = new ArrayList<ACCombinerFavLibraryEntity>();
// 	resultlist.add(array1);
//
// 	ACCombinerFavLibraryEntity scenario1= new ACCombinerFavLibraryEntity();
// 	scenario1.setId(null);;
// 	scenario1.setAuthentificationEntity(null);
// 	scenario1.setaCCombinerSLC(null);
// 	ArrayList<ACCombinerFavLibraryEntity> array2 = new ArrayList<ACCombinerFavLibraryEntity>();
// 	array2.add(scenario1);
// 	resultlist.add(array2);
//
// 	ACCombinerFavLibraryEntity scenario2= new ACCombinerFavLibraryEntity();
// 	scenario2.setId(null);;
// 	scenario2.setAuthentificationEntity(null);
// 	ACCombinerSLC acCombiner = new ACCombinerSLC();
// 	acCombiner.setId(5);
// 	scenario2.setaCCombinerSLC(acCombiner);
// 	ArrayList<ACCombinerFavLibraryEntity> array3 = new ArrayList<ACCombinerFavLibraryEntity>();
// 	array3.add(scenario2);
// 	resultlist.add(array3);
//
// 	ACCombinerFavLibraryEntity scenario3= new ACCombinerFavLibraryEntity();
// 	scenario3.setId(null);;
// 	scenario3.setAuthentificationEntity(null);
// 	ACCombinerSLC acCombiner1 = new ACCombinerSLC();
// 	scenario3.setaCCombinerSLC(acCombiner1);
// 	ArrayList<ACCombinerFavLibraryEntity> array4 = new ArrayList<ACCombinerFavLibraryEntity>();
// 	array4.add(scenario3);
// 	resultlist.add(array4);
//
//	   	when(em.createQuery("SELECT u from ACCombinerFavLibraryEntity u WHERE u.authentificationEntity.id = :p1 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter("p1", Integer.parseInt(idUser))).thenReturn(mockedQuery);
//		for(int i =0;i<resultlist.size();i++) {
//			System.out.println("testgetAcCombinerDiscoFavorite ["+ i +"]");
//			when(mockedQuery.getResultList()).thenReturn(resultlist.get(i));
//			acCombinerSlcLibraryService.getAcCombinerDiscoFavorite(idUser);
//		}
//	}
//
//
//	@Test
//	public void testeditAcCombinerDiscoFavoriteList() {
//		String IdOwner = "12";
//		Integer AcCombinerDiscoID = 12;
//
//		//Array for the result of getSingleResult
//		ArrayList<AuthentificationEntity> resultListUser = new ArrayList<AuthentificationEntity>();
//		ArrayList<ACCombinerSLC> resultListACCombinerSLC = new ArrayList<ACCombinerSLC>();
//		ArrayList<ACCombinerFavLibraryEntity> resultListAcCombinerDiscoFav = new ArrayList<ACCombinerFavLibraryEntity>();
//
//
//		//Test For the query Authentification
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter("p1", Integer.parseInt(IdOwner))).thenReturn(mockedQuery);
//
//		resultListUser.add(null);
//		AuthentificationEntity userScenario1 = new AuthentificationEntity();
//		resultListUser.add(userScenario1);
//
//
//
//
//		//Test for the query Edit AC Combiner
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from ACCombinerSLC u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter("p1", AcCombinerDiscoID)).thenReturn(mockedQuery2);
//
//		resultListACCombinerSLC.add(null);
//		ACCombinerSLC ac1 = new ACCombinerSLC();
//		resultListACCombinerSLC.add(ac1);
//
//
//
//
//		//Test of the parameter possible
//
//		ArrayList<Integer> AcCombinerDiscoIDParams = new ArrayList<Integer>();
//		AcCombinerDiscoIDParams.add(null);
//		AcCombinerDiscoIDParams.add(123);
//		AcCombinerDiscoIDParams.add(123456895);
//
//		ArrayList<Boolean> isShownParams = new ArrayList<Boolean>();
//		isShownParams.add(null);
//		isShownParams.add(true);
//		isShownParams.add(false);
//
//		ArrayList<String> ipUserParams = new ArrayList<String>();
//		ipUserParams.add(null);
//		ipUserParams.add("");
//		ipUserParams.add("aaaa");
//
//		ArrayList<String> iptimeZoneUserParams = new ArrayList<String>();
//		iptimeZoneUserParams.add(null);
//		iptimeZoneUserParams.add("");
//		iptimeZoneUserParams.add("aaaa");
//
//		ArrayList<String> idUserCoParams = new ArrayList<String>();
//		idUserCoParams.add(null);
//		idUserCoParams.add("");
//		idUserCoParams.add("aaaa");
//
//		ArrayList<String> IdOwnerParams = new ArrayList<String>();
//		IdOwnerParams.add(null);
//		IdOwnerParams.add("");
//		IdOwnerParams.add("12");
//
//		ArrayList<String> numTabParams = new ArrayList<String>();
//		numTabParams.add(null);
//		numTabParams.add("");
//		numTabParams.add("12");
//
//		ArrayList<String> sessionIdParams = new ArrayList<String>();
//		sessionIdParams.add(null);
//		sessionIdParams.add("");
//		sessionIdParams.add("12");
//
//		ArrayList<String> openDateParams = new ArrayList<String>();
//		openDateParams.add(null);
//		openDateParams.add("");
//		openDateParams.add("12");
//
//		//Test For the query ACCombinerFavLibraryEntity
//				Query mockedQuery3 = mock(Query.class);
//				when(em.createQuery("SELECT u from ACCombinerFavLibraryEntity u WHERE u.aCCombinerSLC.id = :p1 and u.authentificationEntity.id = :p2")).thenReturn(mockedQuery3);
//				when(mockedQuery3.setParameter("p1", AcCombinerDiscoID)).thenReturn(mockedQuery3);
//				when(mockedQuery3.setParameter("p2", Integer.parseInt(IdOwner))).thenReturn(mockedQuery3);
//				resultListAcCombinerDiscoFav.add(null);
//				ACCombinerFavLibraryEntity acComFav1 = new ACCombinerFavLibraryEntity();
//				resultListAcCombinerDiscoFav.add(acComFav1);
//
//
//		for(int i=0;i<resultListUser.size();i++) {
//			when(mockedQuery.getSingleResult()).thenReturn(resultListUser.get(i));
//			when(mockedQuery2.getSingleResult()).thenReturn(resultListACCombinerSLC.get(i));
//			when(mockedQuery3.getSingleResult()).thenReturn(resultListAcCombinerDiscoFav.get(i));
//			for(int j=0;j<openDateParams.size();j++) {
//
//				System.out.println("testeditAcCombinerDiscoFavoriteList : [ "+ i +" ] [ "+ j + " ]"+resultListUser.get(i));
//			    acCombinerSlcLibraryService.editAcCombinerDiscoFavoriteList(AcCombinerDiscoID, isShownParams.get(j), ipUserParams.get(j), iptimeZoneUserParams.get(j), idUserCoParams.get(j), IdOwner, numTabParams.get(j), sessionIdParams.get(j), openDateParams.get(j));
//				acCombinerSlcLibraryService.editAcCombinerDiscoFavoriteList(AcCombinerDiscoID,true, "12", "12", "12", "12", "12","12", "12");
//
//			}
//			}
//
//
//
//	}
//	@Test
//	public void testgetUsersForFavList() {
//		Query mockedQuery = mock(Query.class);
//		Integer aCCombinerSLC = 123;
//		when(em.createQuery("SELECT u from ACCombinerFavLibraryEntity u WHERE u.aCCombinerSLC.id = :p1")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter("p1", aCCombinerSLC)).thenReturn(mockedQuery);
//		//Scenario of ACCombinerFavLibraryEntity
//		ArrayList<List<ACCombinerFavLibraryEntity>> resultlist = new ArrayList<List<ACCombinerFavLibraryEntity>>();
//
//		List<ACCombinerFavLibraryEntity>  scenario1=  null;
//		resultlist.add(scenario1);
//
//		List<ACCombinerFavLibraryEntity>  scenario2= new ArrayList<ACCombinerFavLibraryEntity>();
//		resultlist.add(scenario2);
//
//		List<ACCombinerFavLibraryEntity>  scenario3= new ArrayList<ACCombinerFavLibraryEntity>();
//		ACCombinerFavLibraryEntity acCombiner = new ACCombinerFavLibraryEntity();
//		scenario3.add(acCombiner);
//
//		ACCombinerFavLibraryEntity acCombiner1 = new ACCombinerFavLibraryEntity();
//		acCombiner1.setId(null);
//		AuthentificationEntity auth = new AuthentificationEntity();
//		acCombiner1.setAuthentificationEntity(auth);
//		ACCombinerSLC ac1 = new ACCombinerSLC();
//		acCombiner1.setaCCombinerSLC(ac1);
//		scenario3.add(acCombiner1);
//
//		scenario3.add(null);
//
//		resultlist.add(scenario3);
//
//		//Scenario of the userFavID
//		ArrayList<List<Integer>> usersFavIDList = new ArrayList<List<Integer>>();
//		usersFavIDList.add(null);
//		List<Integer> userScenario1 = new ArrayList<Integer>();
//		usersFavIDList.add(userScenario1);
//		List<Integer> userScenario2 = new ArrayList<Integer>();
//		userScenario2.add(null);
//		userScenario2.add(1);
//		usersFavIDList.add(userScenario2);
//
//		//Scenario of the user id
//		List<String> usersIDList = new ArrayList<String>();
//		usersIDList.add("1"); //must be null
//		usersIDList.add("1"); // must be String
//		usersIDList.add("1");
//
//
//		//Scenario of the user list
//		ArrayList<List<UsersEntityResult>> resultlistUser = new ArrayList<List<UsersEntityResult>>();
//		resultlistUser.add(null);
//		List<UsersEntityResult> scenarioUserList1 = new ArrayList<UsersEntityResult>();
//		resultlistUser.add(scenarioUserList1);
//		List<UsersEntityResult> scenarioUserList2 = new ArrayList<UsersEntityResult>();
//		scenarioUserList2.add(null);
//		UsersEntityResult usersEntityResult = new UsersEntityResult();
//		scenarioUserList2.add(usersEntityResult);
//		resultlistUser.add(scenarioUserList2);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult ( "
//				+ "   u.id,  "
//				+ "   u.firstName,  "
//				+ "   u.lastName ) "
//				+ " from AuthentificationEntity u WHERE u.id NOT IN :p1 and u.deleted = :p2 and u.active = :p3 and u.id != :p4 ORDER BY u.firstName")).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult ( "
//				+ "   u.id,  "
//				+ "   u.firstName,  "
//				+ "   u.lastName ) "
//				+ " from AuthentificationEntity u WHERE u.deleted = :p2 and u.active = :p3 and u.id != :p4 ORDER BY u.firstName")).thenReturn(mockedQuery3);
//
//		for(int i=0;i<resultlist.size();i++) {
//			when(mockedQuery.getResultList()).thenReturn(resultlist.get(i));
//
//			if(resultlist.get(i) != null && resultlist.get(i).size() > 0) {
//				for(int j=0;j<usersFavIDList.size();j++) {
//
//					List<Integer> usersFavID = new ArrayList<Integer>();
//					usersFavID.add(1);
//					String UserID = usersIDList.get(j);
//
//					when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//					when(mockedQuery2.setParameter("p2", false)).thenReturn(mockedQuery2);
//					when(mockedQuery2.setParameter("p3", true)).thenReturn(mockedQuery2);
//					when(mockedQuery2.setParameter("p4",  Integer.parseInt(UserID))).thenReturn(mockedQuery2);
//					for(int k=0;k<resultlistUser.size();k++) {
//						when(mockedQuery2.getResultList()).thenReturn(resultlistUser.get(k));
//						System.out.println("List of the user  [ "+k+j+i+" ]"+"-----"+resultlistUser.get(k));
//						acCombinerSlcLibraryService.getUsersForFavList(aCCombinerSLC,"5");
//					}
//				}
//			}else {
//				for(int j=0;j<usersIDList.size();j++) {
//
//					String UserID = usersIDList.get(j);
//					when(mockedQuery3.setParameter("p2", false)).thenReturn(mockedQuery3);
//					when(mockedQuery3.setParameter("p3", true)).thenReturn(mockedQuery3);
//					when(mockedQuery3.setParameter("p4",  Integer.parseInt(UserID))).thenReturn(mockedQuery3);
//					for(int k=0;k<resultlistUser.size();k++) {
//						when(mockedQuery3.getResultList()).thenReturn(resultlistUser.get(k));
//					System.out.println("List of the user  [ "+k+j+i+" ]"+"-----"+resultlistUser.get(k));
//						acCombinerSlcLibraryService.getUsersForFavList(aCCombinerSLC,"5");
//
//					}
//				}
//			}
//		}
//	}
//
//	@Test
//	public void testeditUsersFavoriteList() {
//
//		//List of the parameter possible
//		ArrayList <Integer> aCCombinerSLCList = new ArrayList <Integer>();
//		aCCombinerSLCList.add(null);
//		aCCombinerSLCList.add(1);
//		aCCombinerSLCList.add(4);
//
//		ArrayList <String[]> ListUsersList = new ArrayList <String[]>();
//		String[] scenarioUser =  {"a"};
//		ListUsersList.add(scenarioUser);
//		String[] scenario2User =  {"0"};
//		ListUsersList.add(scenario2User);
//		String[] scenario1User =  {"1","2","5"};
//		ListUsersList.add(scenario1User);
//
//
//		ArrayList <String> ipUserList = new ArrayList <String>();
//		ipUserList.add(null);
//		ipUserList.add("abc");
//		ipUserList.add("123");
//
//		ArrayList <String> timeZoneUserList = new ArrayList <String>();
//		timeZoneUserList.add(null);
//		timeZoneUserList.add("");
//		timeZoneUserList.add("abc");
//
//		ArrayList <String> idUserCoList = new ArrayList <String>();
//		idUserCoList.add(null);
//		idUserCoList.add("");
//		idUserCoList.add("123");
//
//		ArrayList <String> numTabList = new ArrayList <String>();
//		numTabList.add(null);
//		numTabList.add("");
//		numTabList.add("0");
//
//		ArrayList <String> sessionIdList = new ArrayList <String>();
//		sessionIdList.add(null);
//		sessionIdList.add("");
//		sessionIdList.add("aaa");
//
//		ArrayList <String> openDateList = new ArrayList <String>();
//		openDateList.add(null);
//		openDateList.add("");
//		openDateList.add("aaa");
//
//		//Result of get singleResult possible of first query
//		ArrayList<ACCombinerSLC> AcCombinerDiscoList = new ArrayList<ACCombinerSLC>();
//		AcCombinerDiscoList.add(null);
//		ACCombinerSLC scenario1ACCombinerSLC = new ACCombinerSLC();
//		AcCombinerDiscoList.add(scenario1ACCombinerSLC);
//		ACCombinerSLC scenario2ACCombinerSLC = new ACCombinerSLC();
//		scenario2ACCombinerSLC.setId(1);
//		scenario2ACCombinerSLC.setManufacturer(null);
//		scenario2ACCombinerSLC.setModel("");
//		scenario2ACCombinerSLC.setLastUpdate("");
//		AcCombinerDiscoList.add(scenario2ACCombinerSLC);
//
//
//		//Result of get singleResult possible of the second query
//
//		ArrayList<AuthentificationEntity> userResultList = new ArrayList<AuthentificationEntity>();
//		userResultList.add(null);
//		AuthentificationEntity scenario1Auth = new AuthentificationEntity();
//		userResultList.add(scenario1Auth);
//		AuthentificationEntity scenario2Auth = new AuthentificationEntity();
//		scenario2Auth.setId(1235);
//		scenario2Auth.setFirstName("");
//		userResultList.add(scenario2Auth);
//		Query mockedQuery = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACCombinerSLC u WHERE u.id = :p1")).thenReturn(mockedQuery);
//
//
//
//
//      //boucle for the parameter and the result of ACCombinerSLC getSingle result
//		for(int i=0; i< aCCombinerSLCList.size() ;i++) {
//
//			    when(mockedQuery.setParameter("p1", aCCombinerSLCList.get(i))).thenReturn(mockedQuery);
//				when(mockedQuery.getSingleResult()).thenReturn(AcCombinerDiscoList.get(i));
//				Query mockedQuery2 = mock(Query.class);
//			    when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//			    when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//			    //boucle for the parameter and the result of Authentification  getSingle result
//			    for(int j=0;j<userResultList.size();j++) {
//			    	when(mockedQuery2.getSingleResult()).thenReturn(userResultList.get(j));
//			    	System.out.println("testeditUsersFavoriteList ["+i+ " "+j+" ]");
//			    	acCombinerSlcLibraryService.editUsersFavoriteList(aCCombinerSLCList.get(i),ListUsersList.get(i),ipUserList.get(i),timeZoneUserList.get(i),idUserCoList.get(i),numTabList.get(i),sessionIdList.get(i),openDateList.get(i));
//			    }
//		}
//
//	}
//
//	@Test
//	public void testcheckACCombinerExistent() {
//		 AcCombinerSlcLibraryService acCombinerSlcLibraryService2 = Mockito.spy(acCombinerSlcLibraryService);
//
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACCombinerSLC u WHERE u.model = :p1 AND u.manufacturer = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACCombinerSLC u WHERE u.model = :p1 AND u.manufacturer != :p2 AND u.isDeleted=false")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACCombinerSLC u WHERE u.ratedCurrent = :p1 AND u.manufacturer = :p2 AND u.numberOfSpaces = :p3 AND u.combinerDeviceType = :p4 AND u.isDeleted=false")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//		 //Parameter possible
//		 ArrayList <NewACCombinerSLCModel> NewAcCombinerDiscoList = new ArrayList <NewACCombinerSLCModel>();
//		 NewAcCombinerDiscoList.add(null);
//		 NewACCombinerSLCModel scenario = new NewACCombinerSLCModel();
//		 NewAcCombinerDiscoList.add(scenario);
//		 NewACCombinerSLCModel scenario1 = new NewACCombinerSLCModel();
//		 scenario1.setManufacturer("");
//		 scenario1.setModel("");
//		 scenario1.setDeviceType("");
//
//		 ArrayList <String> idUserList = new ArrayList <String>();
//		 idUserList.add(null);
//		 idUserList.add("");
//		 idUserList.add("123");
//
//		 //The getResult possible in this query
//		 ArrayList<List<ACCombinerSLC>> scenarioQuery1 = new ArrayList<List<ACCombinerSLC>>();
//		 ArrayList<List<ACCombinerSLC>> scenarioQuery2 = new ArrayList<List<ACCombinerSLC>>();
//		 ArrayList<List<ACCombinerSLC>> scenarioQuery3 = new ArrayList<List<ACCombinerSLC>>();
//
//		 scenarioQuery1.add(null);
//		 scenarioQuery2.add(null);
//		 scenarioQuery3.add(null);
//
//
//	     List<ACCombinerSLC> acCombinerList = new ArrayList<ACCombinerSLC>();
//	     acCombinerList.add(null);
//	     scenarioQuery1.add(acCombinerList);
//	     scenarioQuery2.add(acCombinerList);
//	     scenarioQuery3.add(acCombinerList);
//
//
//	     List<ACCombinerSLC> acCombinerList2 = new ArrayList<ACCombinerSLC>();
//	     acCombinerList2.add(new ACCombinerSLC());
//	     scenarioQuery1.add(acCombinerList2);
//	     scenarioQuery2.add(acCombinerList2);
//	     scenarioQuery3.add(acCombinerList2);
//
//
//	     List<ACCombinerSLC> acCombinerList3 = new ArrayList<ACCombinerSLC>();
//	     ACCombinerSLC scenAC1 = new ACCombinerSLC();
//	     scenAC1.setId(12);
//	     AuthentificationEntity auth = new AuthentificationEntity();
//	     auth.setId(123);
//	     auth.setFirstName("");
//	     auth.setLastName("");
//	     scenAC1.setIdOwner(auth);
//	     acCombinerList3.add(scenAC1);
//	     scenarioQuery1.add(acCombinerList3);
//	     scenarioQuery2.add(acCombinerList3);
//	     scenarioQuery3.add(acCombinerList3);
//
//	     //The result of the function getAcCombinerDiscoFavorite
//	     ArrayList<ArrayList<Integer>> AcCombinerDiscoFavIDList = new ArrayList<ArrayList<Integer>>();
//		 AcCombinerDiscoFavIDList.add(null);
//		 ArrayList<Integer> listAcComb = new ArrayList<Integer>();
//		 AcCombinerDiscoFavIDList.add(listAcComb);
//		 ArrayList<Integer> listAcComb1 = new ArrayList<Integer>();
//		 listAcComb1.add(1);
//		 listAcComb1.add(null);
//		 AcCombinerDiscoFavIDList.add(listAcComb1);
//
//		 for(int i =0;i<NewAcCombinerDiscoList.size();i++) {
//				for(int j=0;j<scenarioQuery1.size();j++) {
//			    	for(int k =0;k<scenarioQuery2.size();k++) {
//			    		for(int h =0;h<scenarioQuery2.size();h++) {
//			    			for(int z=0; z<AcCombinerDiscoFavIDList.size();z++) {
//							   Mockito.doReturn(AcCombinerDiscoFavIDList.get(z)).when(acCombinerSlcLibraryService2).getAcCombinerDiscoFavorite(idUserList.get(i));
//
//				    			when(mockedQuery1.getResultList()).thenReturn(scenarioQuery1.get(j));
//				    			when(mockedQuery2.getResultList()).thenReturn(scenarioQuery2.get(k));
//				    			when(mockedQuery3.getResultList()).thenReturn(scenarioQuery3.get(h));
//				    			 System.out.println("testcheckACCombinerExistent [ "+i+" "+j+ " "+k+" "+h +" " +z +" ]");
//				    			acCombinerSlcLibraryService2.checkACCombinerExistent(NewAcCombinerDiscoList.get(i),idUserList.get(i));
//			    			}
//			    		}
//			    	}
//				}
//
//		 }
//
//
//	}
//	@Test
//	public void testaddAcCombinerDisco() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u.authentificationEntity from PermitEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 //list of the paramtere possible
//		 ArrayList<NewACCombinerSLCModel> AcCombinerDiscoList = new ArrayList<NewACCombinerSLCModel>();
//		 AcCombinerDiscoList.add(null);
//		 NewACCombinerSLCModel ac1 = new NewACCombinerSLCModel();
//		 AcCombinerDiscoList.add(ac1);
//		 NewACCombinerSLCModel ac2 = new NewACCombinerSLCModel();
//		 ac2.setDeviceType("");
//		 ac2.setManufacturer("");
//		 AcCombinerDiscoList.add(ac2);
//
//		 ArrayList<String> ipUserList = new ArrayList<String>();
//		 ipUserList.add(null);
//		 ipUserList.add("");
//		 ipUserList.add("1123");
//
//		 ArrayList<String> timeZoneUserList = new ArrayList<String>();
//		 timeZoneUserList.add(null);
//		 timeZoneUserList.add("");
//		 timeZoneUserList.add("1123");
//
//		 ArrayList<String> idUserCoList = new ArrayList<String>();
//		 idUserCoList.add(null);
//		 idUserCoList.add("");
//		 idUserCoList.add("1123");
//
//		 ArrayList<Integer> idPermitList = new ArrayList<Integer>();
//		 idPermitList.add(null);
//		 idPermitList.add(123);
//		 idPermitList.add(1222222);
//
//		 ArrayList<String> numTabList = new ArrayList<String>();
//		 numTabList.add(null);
//		 numTabList.add("");
//		 numTabList.add("1123");
//
//		 ArrayList<String> sessionIdList = new ArrayList<String>();
//		 sessionIdList.add(null);
//		 sessionIdList.add("");
//		 sessionIdList.add("1123");
//
//		 ArrayList<String> openDateList = new ArrayList<String>();
//		 openDateList.add(null);
//		 openDateList.add("");
//		 openDateList.add("1123");
//
//		 for(int i=0;i<AcCombinerDiscoList.size();i++) {
// 			when(mockedQuery1.getResultList()).thenReturn(null);
// 			when(mockedQuery2.getResultList()).thenReturn(null);
// 			 System.out.println("testaddAcCombinerDisco [ "+i+" ]");
//			acCombinerSlcLibraryService.addAcCombinerDisco(AcCombinerDiscoList.get(i),ipUserList.get(i),timeZoneUserList.get(i),idUserCoList.get(i),idPermitList.get(i),numTabList.get(i),sessionIdList.get(i),openDateList.get(i));
//
//
//		 }
//
//
//
//	}
//	@Test
//	public void testeditAcCombinerDisco() {
//
//	}
//
//	@Test
//	public void testgetRemoveAcCombinerDiscoConfirmation() {
//		//List of the parameter
//		ArrayList<AcCombinerSLCModel> AcCombinerDiscoList = new ArrayList<AcCombinerSLCModel>();
//		AcCombinerDiscoList.add(null);
//		AcCombinerSLCModel ac1 = new AcCombinerSLCModel();
//		AcCombinerDiscoList.add(ac1);
//		AcCombinerSLCModel ac2 = new AcCombinerSLCModel();
//		ac2.setManufacturer("");
//		ac2.setModel("");
//		AcCombinerDiscoList.add(ac2);
//		Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel  "
//					+ " ( u.homeOwnName, "
//					+ " u.status, "
//					+ " v.firstName, "
//					+ " v.lastName)"
//					+ " from PermitEntity u, "
//					+ " AuthentificationEntity v, "
//					+ " PermitProjectSiteInfoEntity w"
//					+ " where (w.groundLevelACCombinerBoxModel = :p1 or w.groundLevelACCombinerDisconnectModel = :p1 or "
//					+ "w.rooftopACCombinerModel = :p1 or w.rooftopACCombinerModelTwo = :p1) "
//					+ "and  u.isDeleted  = :p2 and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = :p2 and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 //The list of the result possible
//		 ArrayList<List<ProjectForLibrariesModel>> resultList = new ArrayList<List<ProjectForLibrariesModel>>();
//		 resultList.add(null);
//		 List<ProjectForLibrariesModel> scen1 = new ArrayList<ProjectForLibrariesModel>();
//		 scen1.add (new ProjectForLibrariesModel());
//		 ProjectForLibrariesModel p1 = new ProjectForLibrariesModel();
//		 p1.setFirstName("");
//		 p1.setHomeOwnerName("");
//		 scen1.add(p1);
//		 resultList.add(scen1);
//
//		 for(int i=0;i<AcCombinerDiscoList.size();i++) {
//			 for(int j=0;j<resultList.size();j++) {
//				  System.out.println("testgetRemoveAcCombinerDiscoConfirmation [ "+i+" "+j+" ]");
//	    			when(mockedQuery1.getResultList()).thenReturn(resultList.get(j));
//	    			if(AcCombinerDiscoList.get(i) == null || resultList.get(j) == null) {
//	    				List<ProjectForLibrariesModel> listProjectLibraries = new ArrayList<ProjectForLibrariesModel>();
//		    			Assert.assertEquals(listProjectLibraries, acCombinerSlcLibraryService.getRemoveAcCombinerDiscoConfirmation(AcCombinerDiscoList.get(i)));
//	    			}else
//	    			 Assert.assertEquals(resultList.get(j), acCombinerSlcLibraryService.getRemoveAcCombinerDiscoConfirmation(AcCombinerDiscoList.get(i)));
//
//			 }
//		 }
//
//
//
//	}
//	@Test
//	public void testdeleteAcCombinerDisco() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACCombinerSLC u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from PermitProjectSiteInfoEntity u WHERE (u.groundLevelACCombinerBoxModel = :p1 or u.groundLevelACCombinerDisconnectModel = :p1 or "
//					+ "u.rooftopACCombinerModel = :p1 or u.rooftopACCombinerModelTwo = :p1 or u.roofTopACCombiner = :p1) "
//					+ "and  u.permitEntity.isDeleted  = :p2")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//		 Query mockedQuery3 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACCombinerFavLibraryEntity u WHERE u.aCCombinerSLC.id = :p1")).thenReturn(mockedQuery3);
//		 when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//		 //List of scenario possible
//		 List<List<Object>> scenario = new ArrayList<List<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //Parameter possible
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		//Result possible of getList result of query1
//		 scenario.get(0).add(null);
//		//Result possible of getSingle result of query2
//		 scenario.get(0).add(null);
//		//Result possible of getSingle result of query3
//		 scenario.get(0).add(null);
//		 //Result that we expected
//		 scenario.get(0).add("error");
//
//		 scenario.add(new ArrayList<Object>());
//		 //Parameter possible
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		 scenario.get(1).add("");
//		//Result possible of getList result of query1
//		 scenario.get(1).add(null);
//		//Result possible of getSingle result of query2
//		 scenario.get(1).add(null);
//		//Result possible of getSingle result of query3
//		 scenario.get(1).add(null);
//		 //Result that we expected
//		 scenario.get(1).add("error");
//
//		 scenario.add(new ArrayList<Object>());
//		 //Parameter possible
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("123");
//		 scenario.get(2).add("1232");
//		 scenario.get(2).add("123");
//		//Result possible of getList result of query1
//		 scenario.get(2).add(null);
//		//Result possible of getSingle result of query2
//		 scenario.get(2).add(null);
//		//Result possible of getSingle result of query3
//		 scenario.get(2).add(null);
//		 //Result that we expected
//		 scenario.get(2).add("error");
//
//		 scenario.add(new ArrayList<Object>());
//		 //Parameter possible
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("123");
//		 scenario.get(3).add("1232");
//		 scenario.get(3).add("123");
//		//Result possible of getList result of query1
//		 ACCombinerSLC ac1 = new ACCombinerSLC();
//		 scenario.get(3).add(ac1);
//		//Result possible of getSingle result of query2
//		 scenario.get(3).add(null);
//		//Result possible of getSingle result of query3
//		 scenario.get(3).add(null);
//		 //Result that we expected
//		 scenario.get(3).add("error");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //Parameter possible
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("123");
//		 scenario.get(4).add("1232");
//		 scenario.get(4).add("123");
//		//Result possible of getList result of query1
//		 ACCombinerSLC ac2 = new ACCombinerSLC();
//		 ac2.setManufacturer("");
//		 ac2.setModel("");
//		 ac2.setId(123);
//		 scenario.get(4).add(ac2);
//		//Result possible of getSingle result of query2
//		 scenario.get(4).add(null);
//		//Result possible of getSingle result of query3
//		 scenario.get(4).add(null);
//		 //Result that we expected
//		 scenario.get(4).add("error");
//
//		 scenario.add(new ArrayList<Object>());
//		 //Parameter possible
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("123");
//		 scenario.get(5).add("1232");
//		 scenario.get(5).add("123");
//		//Result possible of getList result of query1
//		 ACCombinerSLC ac3 = new ACCombinerSLC();
//		 ac2.setManufacturer("");
//		 ac2.setModel("");
//		 ac2.setId(123);
//		 scenario.get(5).add(ac2);
//		//Result possible of getSingle result of query2
//		 List<PermitProjectSiteInfoEntity> listAcCombiner = new ArrayList<PermitProjectSiteInfoEntity>();
//		 PermitProjectSiteInfoEntity permit1 = new PermitProjectSiteInfoEntity();
//		 listAcCombiner.add(permit1);
//		 PermitProjectSiteInfoEntity permit2 = new PermitProjectSiteInfoEntity();
//		 //permit2.setGroundLevelACCombinerBoxModel("");
//		 permit2.setId(123);
//		 permit2.setRooftopACCombinerModel("");
//		 //permit2.setRoofTopACCombiner("");
//		 listAcCombiner.add(permit2);
//		 scenario.get(5).add(listAcCombiner);
//		//Result possible of getSingle result of query3
//		 scenario.get(5).add(null);
//		 //Result that we expected
//		 scenario.get(5).add("error");
//
//		 scenario.add(new ArrayList<Object>());
//		 //Parameter possible
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("123");
//		 scenario.get(6).add("1232");
//		 scenario.get(6).add("123");
//		//Result possible of getList result of query1
//		 scenario.get(6).add(ac2);
//		//Result possile of getSingle result of query2
//		 scenario.get(6).add(listAcCombiner);
//		//Result possible of getSingle result of query3
//		 List<ACCombinerFavLibraryEntity> acCombinerFavList = new ArrayList<ACCombinerFavLibraryEntity>();
//		 acCombinerFavList.add(null);
//		 ACCombinerFavLibraryEntity acFav = new ACCombinerFavLibraryEntity();
//		 acCombinerFavList.add(acFav);
//		 ACCombinerFavLibraryEntity acFav1 = new ACCombinerFavLibraryEntity();
//		 acFav1.setId(12);
//		 acCombinerFavList.add(acFav1);
//		 scenario.get(6).add(acCombinerFavList);
//		 //Result that we expected
//		 scenario.get(6).add("Done");
//
//		 for(int i=0;i<scenario.size();i++) {
//              System.out.println("deleteAcCombinerDisco [ "+i+" ]");
// 			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(8));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(9));
//			acCombinerSlcLibraryService.deleteAcCombinerDisco((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6));
//
//		 }
//
//
//
//	}
//
//	@Test
//	public void testactivateAcCombinerDisco() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACCombinerSLC u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACCombinerSLC u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.isDeleted=false")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
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
//		 scenario.get(0).add("error");
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
//		 scenario.get(1).add("error");
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
//		 scenario.get(2).add("error");
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
//		 ACCombinerSLC ac1 = new ACCombinerSLC();
//		 scenario.get(3).add(ac1);
//		 //The result of the query2
//		 scenario.get(3).add(null);
//		 //The result excpected
//		 scenario.get(3).add("error");
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
//		 scenario.get(4).add("error");
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
//		 ACCombinerSLC acCombiner = new ACCombinerSLC();
//		 acCombiner.setManufacturer("");
//		 acCombiner.setModel("");
//		 scenario.get(5).add(acCombiner);
//		 //The result of the query2
//		 scenario.get(5).add(null);
//		 //The result excpected
//		 scenario.get(5).add("error");
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
//		 scenario.get(6).add(new ArrayList<ACCombinerSLC>());
//		 //The result excpected
//		 scenario.get(6).add("Done");
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
//		 ArrayList<ACCombinerSLC> listAcCom= new ArrayList<ACCombinerSLC>();
//		 listAcCom.add(new ACCombinerSLC());
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
//			 //Assert.assertEquals(scenario.get(i).get(9), acCombinerSlcLibraryService.activateAcCombinerDisco((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6)));
//
//		 }
//
//	}
//
//	@Test
//	public void testgetSearchAcCombinerDisco() {
//		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//		when(criteriaBuilder.createQuery(ACCombinerSLC.class)).thenReturn(criteriaQuery);
//		when(criteriaQuery.from(ACCombinerSLC.class)).thenReturn(root);
//        when(criteriaQuery.select(root)).thenReturn(criteriaQueryAll);
//
//        TypedQuery mockedQuery1 = mock(TypedQuery.class);
//        when(em.createQuery(Mockito.isA(CriteriaQuery.class))).thenReturn(mockedQuery1);
//		AcCombinerSlcLibraryService acCombinerSlcLibraryService2 = Mockito.spy(acCombinerSlcLibraryService);
//
//		//The queryRes2
//	    Query mockedQuery2 = mock(Query.class);
//	    when(em.createQuery(Mockito.anyString())).thenReturn(mockedQuery2);
//	    when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//	  //The queryRes3
//	    Query mockedQuery3 = mock(Query.class);
//	    when(em.createQuery("SELECT u from ACCombinerFavLibraryEntity u WHERE u.aCCombinerSLC.id = :p1")).thenReturn(mockedQuery3);
//	    when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//
//         ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(0).add(null);
//         scenario.get(0).add(null);
//         scenario.get(0).add(null);
//         scenario.get(0).add(null);
//         scenario.get(0).add(null);
//         scenario.get(0).add(null);
//
//         //The result list of query 1
//         scenario.get(0).add(null);
//       //The result of QueryRes1
//         scenario.get(0).add(null);
//         //The result of QueryRes2
//         scenario.get(0).add(null);
//
//         //The result excpected
//         scenario.get(0).add(new ArrayList<AcCombinerSLCModel>());
//
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(1).add(null);
//         scenario.get(1).add(null);
//         scenario.get(1).add(null);
//         scenario.get(1).add(null);
//         scenario.get(1).add(null);
//         scenario.get(1).add(null);
//
//         //The result list of query 1
//         scenario.get(1).add(new ArrayList<ACCombinerSLC>());
//         //The result of QueryRes1
//         scenario.get(1).add(null);
//         //The result of QueryRes2
//         scenario.get(1).add(null);
//         //The result excpected
//         scenario.get(1).add(new ArrayList<AcCombinerSLCModel>());
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(2).add(null);
//         scenario.get(2).add(null);
//         scenario.get(2).add(null);
//         scenario.get(2).add(null);
//         scenario.get(2).add(null);
//         scenario.get(2).add(null);
//
//         //The result list of query 1
//         List<ACCombinerSLC> listAcCombinerDisco = new ArrayList<ACCombinerSLC>();
//         listAcCombinerDisco.add(null);
//         listAcCombinerDisco.add(new ACCombinerSLC());
//         ACCombinerSLC ac1 = new ACCombinerSLC();
//         ac1.setManufacturer("");
//         ac1.setModel("");
//         ac1.setIdOwner(new AuthentificationEntity());
//         listAcCombinerDisco.add(ac1);
//         ACCombinerSLC ac2 = new ACCombinerSLC();
//         ac2.setManufacturer("");
//         ac2.setModel("");
//         AuthentificationEntity auth = new AuthentificationEntity();
//         auth.setId(123);
//         auth.setFirstName("");
//         ac2.setIdOwner(auth);
//         listAcCombinerDisco.add(ac2);
//         scenario.get(2).add(listAcCombinerDisco);
//
//         //The result of QueryRes1
//         scenario.get(2).add(null);
//         //The result of QueryRes2
//         scenario.get(2).add(null);
//
//         //The result excpected
//         scenario.get(2).add(new ArrayList<AcCombinerSLCModel>());
//
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         AcCombinerSLCModelRequest acCombSLC = new AcCombinerSLCModelRequest();
//         scenario.get(3).add(acCombSLC);
//         scenario.get(3).add(false);
//         scenario.get(3).add(null);
//         scenario.get(3).add(null);
//         scenario.get(3).add(null);
//         scenario.get(3).add(null);
//
//         //The result list of query 1
//         scenario.get(3).add(listAcCombinerDisco);
//
//         //The result of QueryRes1
//         scenario.get(3).add(null);
//         //The result of QueryRes2
//         scenario.get(3).add(null);
//
//         //The result excpected
//         scenario.get(3).add(new ArrayList<AcCombinerSLCModel>());
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         AcCombinerSLCModelRequest acCombSLC1 = new AcCombinerSLCModelRequest();
//         acCombSLC1.setManufacturer("abc_def_hdhhd");
//         acCombSLC1.setModel("gdt_hyyd");
//         acCombSLC1.setCombinerDeviceType("ddd");
//         acCombSLC1.setRatedCurrent("hhh");
//         acCombSLC1.setIsShown(true);
//         scenario.get(4).add(acCombSLC1);
//         scenario.get(4).add(false);
//         scenario.get(4).add("123");
//         scenario.get(4).add("589");
//         scenario.get(4).add("58");
//         scenario.get(4).add("485");
//
//         //The result list of query 1
//         scenario.get(4).add(listAcCombinerDisco);
//
//         //The result of QueryRes1
//         scenario.get(4).add(null);
//         //The result of QueryRes2
//         scenario.get(4).add(null);
//
//         //The result excpected
//         scenario.get(4).add(new ArrayList<AcCombinerSLCModel>());
//
//         scenario.add(new ArrayList<Object>());
//         //List of the parameter
//         scenario.get(5).add(acCombSLC1);
//         scenario.get(5).add(false);
//         scenario.get(5).add("123");
//         scenario.get(5).add("589");
//         scenario.get(5).add("58");
//         scenario.get(5).add("485");
//
//         //The result list of query 1
//         scenario.get(5).add(listAcCombinerDisco);
//
//
//         //The result of QueryRes1
//         List<ACCombinerSLC> resltQuery2 = new ArrayList<ACCombinerSLC>();
//         resltQuery2.add(null);
//         resltQuery2.add(new ACCombinerSLC());
//         ACCombinerSLC ac1Query = new ACCombinerSLC();
//         ac1Query.setId(123);
//         ac1Query.setManufacturer("");
//         ac1Query.setIdOwner(new AuthentificationEntity());
//         resltQuery2.add(ac1Query);
//         scenario.get(5).add(resltQuery2);
//         //The result of QueryRes2
//         scenario.get(5).add(null);
//
//         //The result excpected
//         scenario.get(5).add(new ArrayList<AcCombinerSLCModel>());
//
//       for(int i=0;i<scenario.size();i++) {
//           Mockito.doReturn(new ArrayList<Integer>()).when(acCombinerSlcLibraryService2).getAcCombinerDiscoFavorite(Mockito.any());
//           when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(7));
//           when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(8));
//
//           when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(6));
//           System.out.println("getSearchAcCombinerDisco [ "+i+" ]");
//          // List<AcCombinerSLCModel> reslt = acCombinerSlcLibraryService2.getSearchAcCombinerDisco((AcCombinerSLCModelRequest)scenario.get(i).get(0),(Boolean)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5));
//          // System.out.println(reslt);
//       }
//	}
//
//	@Test
//	public void testgetContractorACComDisco() {
//		 Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACCombinerSLC u WHERE u.id IN :p1 and u.type = :p2 and u.isDeleted = :p3 ORDER BY u.dropdownOption")).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 AcCombinerSlcLibraryService acCombinerSlcLibraryService2 = Mockito.spy(acCombinerSlcLibraryService);
//         List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 //Result of query1
//		 scenario.get(1).add(new ArrayList<ACCombinerSLC>());
//		//Excpected Result
//		 scenario.get(1).add(new ArrayList<ACCombinerSLC>());
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getContractorACComDisco [ "+ i+ " ]");
//             Mockito.doReturn(new ArrayList<Integer>()).when(acCombinerSlcLibraryService2).getAcCombinerDiscoFavorite(Mockito.any());
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//            // Assert.assertEquals(scenario.get(i).get(3), acCombinerSlcLibraryService2.getContractorACComDisco((String)scenario.get(i).get(0),(String)scenario.get(i).get(1)));
//
//		 }
//	}
//	@Test
//	public void testeditACCombinerNotification() {
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
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("editACCombinerNotification [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//             Assert.assertEquals(scenario.get(i).get(4), acCombinerSlcLibraryService.editACCombinerNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2)));
//
//		 }
//
//	}
//	@Test
//	public void testaddACCombinerNotification() {
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
//		 for(int i=0; i<scenario.size();i++) {
//			 System.out.println("addACCombinerNotification [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//            // Assert.assertEquals(scenario.get(i).get(4), acCombinerSlcLibraryService.addACCombinerNotification((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2)));
//
//		 }
//	}
//
//	@Test
//	public void testsendCorrectionRequest() {
//		Query mockedQuery1 = mock(Query.class);
//		 when(em.createQuery("SELECT u from ACCombinerSLC u Where u.id = :p1 " )).thenReturn(mockedQuery1);
//		 when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//
//		 Query mockedQuery2 = mock(Query.class);
//		 when(em.createQuery(" SELECT u  FROM AuthentificationEntity u WHERE u.id = :p1  ")).thenReturn(mockedQuery2);
//		 when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//
//
//         List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		 //Result of query2
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 AcCombinerSLCCorrectionModel acComSLC = new AcCombinerSLCCorrectionModel();
//		 scenario.get(1).add(acComSLC);
//		 //Result of query1
//		 scenario.get(1).add(null);
//		 //Result of query2
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add("Done");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 AcCombinerSLCCorrectionModel acComSLC1= new AcCombinerSLCCorrectionModel();
//		 acComSLC1.setId(123);
//		 scenario.get(2).add(acComSLC1);
//		 //Result of query1
//		 scenario.get(2).add(null);
//		 //Result of query2
//		 scenario.get(2).add(null);
//		//Excpected Result
//		 scenario.get(2).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 AcCombinerSLCCorrectionModel acComSLC2= new AcCombinerSLCCorrectionModel();
//		 acComSLC2.setId(123);
//		 acComSLC2.setIdUser("");
//		 scenario.get(3).add(acComSLC2);
//		 //Result of query1
//		 scenario.get(3).add(null);
//		 //Result of query2
//		 scenario.get(3).add(null);
//		//Excpected Result
//		 scenario.get(3).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 AcCombinerSLCCorrectionModel acComSLC4= new AcCombinerSLCCorrectionModel();
//		 acComSLC4.setId(123);
//		 acComSLC4.setIdUser("123");
//		 scenario.get(4).add(acComSLC4);
//		 //Result of query1
//		 scenario.get(4).add(null);
//		 //Result of query2
//		 scenario.get(4).add(null);
//		//Excpected Result
//		 scenario.get(4).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(5).add(acComSLC4);
//		 //Result of query1
//		 ACCombinerSLC lib = new ACCombinerSLC();
//		 scenario.get(5).add(lib);
//		 //Result of query2
//		 scenario.get(5).add(null);
//		//Excpected Result
//		 scenario.get(5).add("Fail");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(6).add(acComSLC4);
//		 //Result of query1
//		 scenario.get(6).add(lib);
//		 //Result of query2
//		 AuthentificationEntity auth = new AuthentificationEntity();
//		 scenario.get(6).add(auth);
//		//Excpected Result
//		 scenario.get(6).add("Done");
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("sendCorrectionRequest [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//            acCombinerSlcLibraryService.sendCorrectionRequest((AcCombinerSLCCorrectionModel)scenario.get(i).get(0));
//
//		 }
//
//	}
//
//	@Test
//	public void testloadAcCombinerSLC() throws Exception{
//	}
//
//}
