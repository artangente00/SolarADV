//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.io.UnsupportedEncodingException;
//import java.security.NoSuchAlgorithmException;
//import java.security.spec.InvalidKeySpecException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Date;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//
//import com.PlayGroundAdv.Solar.Entity.ACDisconnectFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.RailRackingFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.RoofAttachmentFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.RoofTypeandAttachmentEntity;
//import com.PlayGroundAdv.Solar.Entity.UserLicSectionsEntity;
//import com.PlayGroundAdv.Solar.model.AuthoritiesResponse;
//import com.PlayGroundAdv.Solar.model.ContractorLicSectionModel;
//import com.PlayGroundAdv.Solar.model.EditUserInformations;
//import com.PlayGroundAdv.Solar.model.IntegerModelResult;
//import com.PlayGroundAdv.Solar.model.LoginFour;
//import com.PlayGroundAdv.Solar.model.LoginModel;
//import com.PlayGroundAdv.Solar.model.LoginResult;
//import com.PlayGroundAdv.Solar.model.RoofTypeAttachementModel;
//import com.PlayGroundAdv.Solar.model.SetUserModelRequest;
//import com.PlayGroundAdv.Solar.model.SigninResult;
//import com.PlayGroundAdv.Solar.model.SigninResultTwo;
//import com.PlayGroundAdv.Solar.model.SignupResult;
//import com.PlayGroundAdv.Solar.model.StringModelResult;
//import com.PlayGroundAdv.Solar.model.UserLicSectionModel;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.CopyProjectService;
//import com.PlayGroundAdv.Solar.Services.GetTiltLegsLibraryService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.PasswordEncryptionService;
//import com.PlayGroundAdv.Solar.Services.PermitService;
//import com.PlayGroundAdv.Solar.Services.UserInformationService;
//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
//
//import junit.framework.Assert;
//
//public class TestUserInformationService {
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
//	
//	@Mock
//	PermitService permitService;
//	 
//	@Spy
//	CheckValueTypesService checkValueTypesService;
//	@InjectMocks
//	UserInformationService userInformationService = new UserInformationService();
//	
//	
//    @Before
//	public void setupMock() {
//    	userInformationService = new UserInformationService();
//	       MockitoAnnotations.initMocks(this);
//	}
//	
//
//	@Test
//	public void testgetAuthorities() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.AuthoritiesResponse(u.roleEntity.designationName, u.roleEntity.isSuperUser )  "
//				+ " from AuthentificationEntity u "
//				+ " where u.id = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(125);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new HashMap<String , String>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(125);
//		// Result of the query1
//		scenario.get(1).add(new AuthoritiesResponse());
//		// Result excpected
//		HashMap<String , String> exp = new HashMap<String , String>();
//		exp.put("IsSuperUser", "false");
//		exp.put("Role", "null");
//		scenario.get(1).add(exp);
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAuthorities [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			userInformationService.getAuthorities((int)scenario.get(i).get(0));
//		}
//	}
//	
//	@Test
//	public void testchangingPassword() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				+ " from AuthentificationEntity u " + " where u.id = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(125);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Password Incorrect");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(125);
//		scenario.get(1).add(null);
//		scenario.get(1).add("abc");
//		// Result of the query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setPassword("abc");
//		scenario.get(1).add(auth);
//		// Result excpected
//		scenario.get(1).add("fail");
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("changingPassword [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			userInformationService.changingPassword((int)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//		}
//	}
//
//	@Test
//	public void testgetLoged() throws NoSuchAlgorithmException, InvalidKeySpecException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u where lower(u.email)=lower(:p1)"))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.LoginModel (u.id, u.firstName, u.lastName,"
//				+ " u.password, u.email, u.contractorCode, u.active, v.description, u.deleted, u.solarPermit, u.siteSurvey, u.hasSettingAccess) "
//				+ " from AuthentificationEntity u, RoleEntity v " + " where lower(u.email) = lower(:p1) AND u.roleEntity = v.id"))
//		       .thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new LoginModel());
//		//The result list of the Query2
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(new LoginFour());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(null);
//		//The result list of the Query2
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(new LoginFour());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setActive(true);
//		scenario.get(2).add(auth);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new LoginModel());
//		//The result list of the Query2
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(new LoginFour());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		AuthentificationEntity auth1 = new AuthentificationEntity();
//		auth1.setActive(true);
//		auth1.setEmail("nbhy @k.bo");
//		scenario.get(3).add(auth1);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(null);
//		//The result list of the Query2
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add(new LoginFour());
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add(auth1);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(null);
//		//The result list of the Query2
//		ArrayList<LoginModel> list = new ArrayList<>();
//		list.add(null);
//		list.add(new LoginModel());
//		scenario.get(4).add(list);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add(new LoginFour());
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		// Result of the query1
//		scenario.get(5).add(auth1);
//		// Result of the query2
//		scenario.get(5).add(new LoginModel());
//		// Result excpected
//		scenario.get(5).add(new LoginModel());
//		//The result list of the Query2
//		scenario.get(5).add(list);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getLoged [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(6));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(9));
//			LoginModel rslt = userInformationService.getLoged((LoginFour)scenario.get(i).get(0),(HttpSession)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(HttpServletRequest)scenario.get(i).get(4),(HttpServletResponse)scenario.get(i).get(5));
//			
//		}
//	}
//	
//	
//	@Test
//	public void testsetUser() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT new com.PlayGroundAdv.Solar.model.SignupResult (u.id, u.firstName, u.lastName, u.password) "
//						+ " from AuthentificationEntity u " + " where lower(u.email) = lower(:p1) "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT (u) "
//				+ " from RoleEntity u " + " where u.isSuperUser = :p1  AND u.designationName = :p2"))
//		       .thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//		
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u where (u.roleEntity.id= 1 or u.roleEntity.id= 5)  and u.deleted=:p1 and u.active=:p2 and u.email LIKE '%nuagetechnologies-tn%' OR u.email LIKE '%nabil-g%'"))
//		       .thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery3);
//		
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u where (u.roleEntity.id=1 or u.roleEntity.id= 5) and u.deleted=:p1 and u.active=:p2"))
//		       .thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery4);
//		
//		UserInformationService userInformationService2 = Mockito.spy(userInformationService);
//
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result of the query4
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new StringModelResult ("It seems that there was a technical problem, please try later."));
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(new SetUserModelRequest());
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result of the query4
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new StringModelResult ("It seems that there was a technical problem, please try later."));
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		SetUserModelRequest param1 = new SetUserModelRequest();
//		param1.setEmail("malek@gmail.com");
//		scenario.get(2).add(param1);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result of the query4
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new StringModelResult ("Mail Does not exist in DNS Server "));
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(param1);
//		// Result of the query1
//		ArrayList<SignupResult> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(3).add(list);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result of the query4
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new StringModelResult (" Mail already exist "));
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("setUser [ " + i + " ]");
//			 
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			
//			//Mockito.doReturn(true).when(userInformationService2).isAddressValid("malek@gmail.com");
//			userInformationService.setUser((SetUserModelRequest) scenario.get(i).get(0)).getStr();
//			
//		}
//	}
//	
//	@Test
//	public void testgetAllUserActivated() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.LoginResult " + " (u.id, u.firstName, u.lastName,  "
//				+ " u.email,u.country, u.company, u.contractorCode, u.active,u.lastLogin, u.roleEntity.description, u.solarPermit, u.siteSurvey) "
//				+ " from AuthentificationEntity u " + " where u.deleted = :p1"))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<SigninResult>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<LoginResult> list = new ArrayList<>();
//		list.add(null);
//		list.add(new LoginResult());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<LoginResult> list2 = new ArrayList<>();
//		list2.add(null);
//		LoginResult log = new LoginResult();
//		log.setLastLogin("08/09/2018 at 03:57");
//		list2.add(log);
//		scenario.get(2).add(list2);
//		// Result excpected
//		scenario.get(2).add(list2);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllUserActivated [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			userInformationService.getAllUserActivated();
//			
//		}
//	}
//	
//	@Test
//	public void testgetAllUserActivatedNotDel() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.LoginResult " + " (u.id, u.firstName, u.lastName,"
//				+ " u.email,u.country, u.company, u.contractorCode, u.active,u.lastLogin, u.roleEntity.description, u.solarPermit, u.siteSurvey) "
//				+ " from AuthentificationEntity u " + " where u.deleted = :p1 AND u.active = :p2"))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<SigninResult>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<LoginResult> list = new ArrayList<>();
//		list.add(null);
//		list.add(new LoginResult());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<LoginResult> list2 = new ArrayList<>();
//		list2.add(null);
//		LoginResult log = new LoginResult();
//		log.setLastLogin("08/09/2018 at 03:57");
//		list2.add(log);
//		scenario.get(2).add(list2);
//		// Result excpected
//		scenario.get(2).add(list2);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllUserActivatedNotDel [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			userInformationService.getAllUserActivatedNotDel();
//			
//		}
//	
//	}
//	
//	@Test
//	public void testgetAllUserNonActivated() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.LoginResult " + " (u.id, u.firstName, u.lastName,  "
//				+ " u.email,u.country, u.company, u.contractorCode, u.active,u.lastLogin, u.roleEntity.description, u.solarPermit, u.siteSurvey) "
//				+ " from AuthentificationEntity u " + " where u.deleted = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<SigninResult>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<LoginResult> list = new ArrayList<>();
//		list.add(null);
//		list.add(new LoginResult());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<LoginResult> list2 = new ArrayList<>();
//		list2.add(null);
//		LoginResult log = new LoginResult();
//		log.setLastLogin("08/09/2018 at 03:57");
//		list2.add(log);
//		scenario.get(2).add(list2);
//		// Result excpected
//		scenario.get(2).add(list2);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllUserNonActivated [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			userInformationService.getAllUserNonActivated();
//			
//		}
//	
//	}
//	
//	@Test
//	public void testgetUser() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.EditUserInformations "
//				+ " (u.id,  "
//				+ "  u.password,  "
//				+ "  u.contractorCode, "
//				+ "  u.firstName, "
//				+ "  u.lastName,  "
//				+ "  u.country, "
//				+ "  u.company, "
//				+ "  u.email,  "
//				+ "  u.active,   "
//				+ "  u.address,  "
//				+ "  u.secondAddressLine,  "
//				+ "  u.city,  "
//				+ "  u.state, "
//				+ "  u.postalCode, "
//				+ "  u.roleEntity.id, "
//				+ "  u.projectReferenceId,  "
//				+ "  u.date,  "
//				+ "  u.sameMalingAddress, "
//				+ "  u.mailingAddress,   "
//				+ "  u.secondMailingAddress,  "
//				+ "  u.mailingCity,  "
//				+ "  u.mailingState,   "
//				+ "  u.mailingZipCode,  "
//				+ "  u.contactFirstName,  "
//				+ "  u.contactLastName,   "
//				+ "  u.contactEmail,   "
//				+ "  u.contactPhone, "
//				+ "  u.contactAddPhone,  "
//				+ "  u.secondContactFirstName, "
//				+ "  u.secondContactLastName,  "
//				+ "  u.secondContactEmail, "
//				+ "  u.secondContactPhone, "
//				+ "  u.secondContactAddPhone, "
//				+ "  u.includeSecondContact, "
//				+ "  u.thirdContact, "
//				+ "  u.thirdContactEmail, "
//				+ "  u.thirdContactPhone, "
//				+ "  u.thirdContactAddPhone, "
//				+ "  u.includeThirdContact, "
//				+ "  u.businessPhone, "
//				+ "  u.otherPhone, "
//				+ "  u.designBy, "
//				+ "  u.licenseNumber, "
//				+ "  u.licenseExpiration, "
//				+ "  u.contractorLic, "
//				+ "  u.qualifyingIndividual, "
//				+ "  u.additionalQualifying, "
//				+ "  u.includeSecondContactOnly, "
//				+ "  u.includeSecondContactWhen, "
//				+ "  u.includeThirdContactOnly, "
//				+ "  u.includeThirdContactWhen, "
//				+ "  u.designByOther, "
//				+ "  u.contractorLicC10, "
//				+ "  u.contractorLicB,  "
//				+ "  u.qualifyingIndividualOther,  "
//				+ "  u.additionalQualifyingOther, "
//				+ "  u.contractorLicenceState, "
//				+ "  u.isProjectAddInclud, "
//				+ "  u.lastNameContact, u.reminder, "
//				+ "  u.compPhoneNum, "
//				+ "  u.contactOptions, "
//				+ "  u.contactOptionsOther, "
//				+ "  u.minimumDCGroundCon, "
//				+ "  u.minimumDCGroConOther, "
//				+ "  u.minimumACGroundCon, "
//				+ "  u.minimumACGroConOther,"
//				+ "  u.userGroundRailRaking, " 
//				+ "  u.userSizeOfPipe, "
//				+ "  u.userSizeOfPipeOther, "
//				+ "  u.userThicknessOfPipe, " 
//				+ "  u.userThicknessOfPipeOther, "
//				+ "  u.userBracedUnbraced, " 
//				+ "  u.userFootingDiameter, " 
//				+ "  u.userFootingDiameterOther, "
//				+ "  u.siteSurvey, "
//				+ "  u.solarPermit, " 
//				+ "  u.hasSettingAccess, "
//				+ "  u.pvmModelDefault, "
//				+ "  u.userAcDisconnect, "
//				+ "  u.ampRating, "
//				+ "  u.fusibleNonFusible, "
//				+ "  u.nemaRating, "
//				+ "  u.useRomexInAttic, "
//				+ "  u.atticTemperatureAdder )"
//				+ " from AuthentificationEntity u "
//				+ " where u.id = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(2255);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new EditUserInformations());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(2255);
//		// Result of the query1
//		scenario.get(1).add(new EditUserInformations());
//		// Result excpected
//		scenario.get(1).add(new EditUserInformations());
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getUser [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			EditUserInformations rslt = userInformationService.getUser((int) scenario.get(i).get(0));
//			
//		}
//	}
//	
//	@Test
//	public void testdeleteUser() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				+ " from AuthentificationEntity u "
//				+ " where u.id = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				+ " from PermitEntity u "
//				+ " where u.authentificationEntity.id = :p1 "))
//		       .thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("258");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("258");
//		// Result of the query1
//		scenario.get(3).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("secces");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("258");
//		// Result of the query1
//		scenario.get(4).add(new AuthentificationEntity());
//		// Result of the query2
//		ArrayList<PermitEntity> list = new ArrayList<>();
//		list.add(null);
//		list.add(new PermitEntity());
//		scenario.get(4).add(list);
//		// Result excpected
//		scenario.get(4).add("secces");
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deleteUser [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			userInformationService.deleteUser((String) scenario.get(i).get(0));
//			
//		}
//	}
//	
//	@Test
//	public void testEditUserContact() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				+ " from AuthentificationEntity u "
//				+ " where u.id = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
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
//		scenario.get(0).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(new EditUserInformations());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(new EditUserInformations());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(2).add("success");
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("EditUserContact [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			userInformationService.EditUserContact((EditUserInformations) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
//			
//		}
//	}
//	
//	@Test
//	public void testeditUserSetting() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				+ " from AuthentificationEntity u "
//				+ " where u.id = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRackingFavLibraryEntity u WHERE u.railRacking.id = :p1 AND u.authentificationEntity.id = :p2"))
//		       .thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//		
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id = :p1"))
//		       .thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery3);
//		
//		
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u from ACDisconnectFavLibraryEntity u WHERE u.aCDisconnect.id = :p1 AND u.authentificationEntity.id = :p2"))
//		       .thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery4);
//		
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT u from ACDisconnect u WHERE u.id = :p1"))
//		       .thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery5);
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
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result of the query4
//		scenario.get(0).add(null);
//		// Result of the query5
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(new EditUserInformations());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
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
//		scenario.get(1).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(new EditUserInformations());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result of the query4
//		scenario.get(2).add(null);
//		// Result of the query5
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("success");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		EditUserInformations user = new EditUserInformations();
//		user.setUserGroundRailRaking("1255");
//		user.setUserAcDisconnect("1255:abc");
//		scenario.get(3).add(user);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result of the query4
//		scenario.get(3).add(null);
//		// Result of the query5
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add(user);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add("");
//		// Result of the query1
//		scenario.get(4).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result of the query4
//		scenario.get(4).add(null);
//		// Result of the query5
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add(user);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add("125");
//		// Result of the query1
//		scenario.get(5).add(new AuthentificationEntity());
//		// Result of the query2
//		ArrayList<RailRackingFavLibraryEntity> list = new ArrayList<>();
//		scenario.get(5).add(list);
//		// Result of the query3
//		scenario.get(5).add(null);
//		// Result of the query4
//		ArrayList<ACDisconnectFavLibraryEntity> list1 = new ArrayList<>();
//		scenario.get(5).add(list1);
//		// Result of the query5
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("success");
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("EditUserContact [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(6));
//			when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(7));
//			when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(8));
//
//		userInformationService.editUserSetting((EditUserInformations) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
//			
//		}
//	}
//	
//	@Test
//	public void testeditUser() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				+ " from AuthentificationEntity u "
//				+ " where u.id = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRackingFavLibraryEntity u WHERE u.railRacking.id = :p1 AND u.authentificationEntity.id = :p2"))
//		       .thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//		
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from RailRacking u WHERE u.id = :p1"))
//		       .thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery3);
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
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(new EditUserInformations());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(new EditUserInformations());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("success");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		EditUserInformations user = new EditUserInformations();
//		user.setUserGroundRailRaking("1255");
//		user.setUserAcDisconnect("1255:abc");
//		scenario.get(3).add(user);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add(user);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add(new AuthentificationEntity());
//		// Result of the query2
//		ArrayList<RailRackingFavLibraryEntity> list = new ArrayList<>();
//		scenario.get(4).add(list);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add(user);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add("");
//		// Result of the query1
//		scenario.get(5).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(5).add(list);
//		// Result of the query3
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(6).add(user);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add("125");
//		// Result of the query1
//		scenario.get(6).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(6).add(list);
//		// Result of the query3
//		scenario.get(6).add(null);
//		// Result excpected
//		scenario.get(6).add("success");
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editUser [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(6));
//
//			userInformationService.editUser((EditUserInformations) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
//			
//		}
//	}
//	
//	@Test
//	public void testactivateUser() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				+ " from AuthentificationEntity u "
//				+ " where u.id = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(true);
//		scenario.get(0).add(true);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add(true);
//		scenario.get(1).add(true);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("1258");
//		scenario.get(2).add(true);
//		scenario.get(2).add(true);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("1258");
//		scenario.get(3).add(true);
//		scenario.get(3).add(true);
//		// Result of the query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		scenario.get(3).add(auth);
//		// Result excpected
//		scenario.get(3).add("null null");
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("activateUser [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			userInformationService.activateUser((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(boolean)scenario.get(i).get(3),(boolean)scenario.get(i).get(4));
//		}
//	}
//	
//	
//	@Test
//	public void testdeletedUser() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				+ " from AuthentificationEntity u "
//				+ " where u.id = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("1235");
//		// Result of the query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setDeleted(false);
//		scenario.get(3).add(auth);
//		// Result excpected
//		scenario.get(3).add("User alredy not deleted");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("1235");
//		// Result of the query1
//		AuthentificationEntity auth2 = new AuthentificationEntity();
//		auth2.setDeleted(true);
//		scenario.get(4).add(auth2);
//		// Result excpected
//		scenario.get(4).add("succes");
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deletedUser [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			 userInformationService.deletedUser((String)scenario.get(i).get(0));
//		}
//	}
//	
//	@Test
//	public void testgetAllPermitForChart() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT count(*) as nb from PermitEntity u  "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u.creationPermitDate "
//				+ " from PermitEntity u ORDER BY u.creationPermitDate" ))
//		       .thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<List<String>>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(1).add(2L);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		ArrayList<List<String>> exp1 = new ArrayList<List<String>>();
//		exp1.add(new ArrayList<>());
//		exp1.add(new ArrayList<>());
//		exp1.add(new ArrayList<>());
//		ArrayList<String> t = new ArrayList<>();
//		t.add("0");
//		exp1.add(t);
//		scenario.get(1).add(exp1); 
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(2).add(2L);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(exp1); 
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(3).add(2L);
//		// Result of the query2
//		ArrayList<Date> list = new ArrayList<>();
//		list.add(null);
//		Date d = new Date();
//		list.add(d);
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add(new ArrayList<List<String>>()); 
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(4).add(2L);
//		// Result of the query2
//		ArrayList<Date> list2 = new ArrayList<>();
//		list2.add(d);
//		scenario.get(4).add(list2);
//		// Result excpected
//		ArrayList<List<String>> exp2 = new ArrayList<List<String>>();
//		ArrayList<String> t2 = new ArrayList<String>();
//		t2.add((d.getYear()+1900)+"");
//		ArrayList<String> t3 = new ArrayList<String>();
//		if(d.getMonth()+1<10) {
//			t3.add("0"+(d.getMonth()+1));
//		}else {
//			t3.add((d.getMonth()+1)+"");
//		}
//		
//		ArrayList<String> t4 = new ArrayList<String>();
//		if(d.getDate()+1<10) {
//			t4.add("0"+(d.getDate()));
//		}else {
//			t4.add((d.getDate())+"");
//		}
//		
//		ArrayList<String> t5 = new ArrayList<String>();
//		t5.add("1");
//		t5.add("1");
//		exp2.add(t2);
//		exp2.add(t3);
//		exp2.add(t4);
//		exp2.add(t5);
//		scenario.get(4).add(exp2); 
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("resetPassword [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(0));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			userInformationService.getAllPermitForChart();
//		}
//	}
//	
//	@Test
//	public void testresetPassword() throws UnsupportedEncodingException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(Mockito.anyString()))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.executeUpdate()).thenReturn(1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new StringModelResult("Mail does not exist!"));
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("malek@gmail.com");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new StringModelResult("Mail does not exist!"));
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("malek@gmail.com");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(2).add(new StringModelResult("temporary password created"));
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("resetPassword [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			userInformationService.resetPassword((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//		}
//	}
//	
//	@Test
//	public void testverifyPassword() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u  from AuthentificationEntity u where lower(u.email)='"+Mockito.anyString()+"'"))
//		       .thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("update AuthentificationEntity u set password=null where u.id ="+Mockito.anyString()))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.executeUpdate()).thenReturn(1);
//		
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
//		scenario.get(0).add(new StringModelResult("User does not exist"));
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("ab@Gmaol");
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new StringModelResult("Temporary password incorrect"));
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("ab@Gmaol");
//		scenario.get(2).add("");
//		// Result of the query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setTempPwd("");
//		scenario.get(2).add(auth);
//		// Result excpected
//		scenario.get(2).add(new StringModelResult("Temporary password created, proceeding to next step."));
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getIdUser [ " + i + " ]"+scenario.get(i).get(2));
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			userInformationService.verifyPassword((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//		}
//	}
//	
//	@Test
//	public void testresetFinalStep()  throws NoSuchAlgorithmException, InvalidKeySpecException{
//		Query mockedQuery1 = mock(Query.class);
//		Query mockedQuery2 = mock(Query.class);
//		
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
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new StringModelResult("User does not exist"));
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new StringModelResult("User does not exist"));
//	
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("");
//		scenario.get(2).add("");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new StringModelResult("User does not exist"));
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("");
//		scenario.get(3).add("");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(3).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(3).add(new StringModelResult("New Password created"));
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getIdUser [ " + i + " ]");
//			when(em.createQuery("SELECT u  from AuthentificationEntity u where lower(u.email)='"+scenario.get(i).get(1)+"'"))
//					.thenReturn(mockedQuery1);
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			
//			when(em.createQuery(Mockito.anyString()))
//				   .thenReturn(mockedQuery2);
//	        when(mockedQuery2.executeUpdate()).thenReturn(1);
//	        when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			userInformationService.resetFinalStep((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3));
//		}
//	}
//
//	@Test
//	public void testcheckIamges() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u where u.id=:p1"))
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
//		scenario.get(0).add(new HashMap<String,List<String>>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new HashMap<String,List<String>>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("258");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new HashMap<String,List<String>>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("258");
//		// Result of the query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		auth.setSignature("test junit");
//		auth.setSignatureConfirmed(true);
//		auth.setCompanyLogoName("test");
//		auth.setLogoConfirmed(false);
//		scenario.get(3).add(auth);
//		// Result excpected
//		HashMap<String,List<String>> exp1 = new HashMap<String,List<String>>();
//		ArrayList<String> s1 = new ArrayList<>();
//		s1.add("test junit");
//		s1.add("true");
//		exp1.put("Signature", s1);
//		ArrayList<String> s2 = new ArrayList<>();
//		s2.add("test");
//		s2.add("false");
//		exp1.put("Logo", s2);
//		scenario.get(3).add(exp1);
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("checkIamges [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			userInformationService.checkIamges((String)scenario.get(i).get(0));
//		}
//	}
//	
//	@Test
//	public void testpasswordUpdate() throws NoSuchAlgorithmException, InvalidKeySpecException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.password from AuthentificationEntity u"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("update AuthentificationEntity u set password='"+PasswordEncryptionService.generateStorngPasswordHash(Mockito.anyString())+"' where u.password=:p1"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		when(mockedQuery2.executeUpdate()).thenReturn(1);
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//The Result of the Query1
//		scenario.get(0).add(null);
//		//The Result expected
//		scenario.get(0).add("passwords updated");
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("updateUserLicSection [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			userInformationService.passwordUpdate();
//		}
//	}
//	
//	@Test
//	public void testgetUserLicSections() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(" SELECT new com.PlayGroundAdv.Solar.model.ContractorLicSectionModel( "
//				+ "u.isInUSorTerritories, " + "u.contractorLicenceState," + "u.licenseNumber, "+ "u.licTypeCode, " + "u.firstLicTypeCodeOther, " 
//				+ " u.secondLicTypeCodeOther, " + "thirdLicTypeCodeOther, " + "u.licType, " + "u.licTypeOther," + "u.licenseExpiration, " 
//				+ "u.qualifyingIndividual, " + "u.qualifyingIndividualOther"+ "  ) "
//				+ " from UserLicSectionsEntity u " + " where u.authentificationEntity.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u where u.id=:p1"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the Query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<ContractorLicSectionModel>());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the Query2
//		scenario.get(1).add(new AuthentificationEntity());
//		// Result excpected
//		ArrayList<ContractorLicSectionModel> exp = new ArrayList<ContractorLicSectionModel>();
//		exp.add(new ContractorLicSectionModel());
//		scenario.get(1).add(exp);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(null);
//		// Result of the query1
//		ArrayList<ContractorLicSectionModel> list = new ArrayList<>();
//		list.add(null);
//		list.add(new ContractorLicSectionModel());
//		scenario.get(2).add(list);
//		// Result of the Query2
//		scenario.get(2).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(2).add(list);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getUserLicSections [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			List<ContractorLicSectionModel> rslt =userInformationService.getUserLicSections((Integer) scenario.get(i).get(0));
//			
//		}
//	}
//	
//	@Test
//	public void testupdateUserLicSection() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u where u.id=:p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from UserLicSectionsEntity u where u.authentificationEntity.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the Query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(new UserLicSectionModel());
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the Query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Success");
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("updateUserLicSection [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			userInformationService.updateUserLicSection((UserLicSectionModel) scenario.get(i).get(0));
//		}
//	}
//	
//	@Test
//	public void testgetRoofModels() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentFavLibraryEntity u where u.authentificationEntity.id = :p1 "))
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
//		scenario.get(0).add(new ArrayList<RoofAttachmentFavLibraryEntity>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<RoofAttachmentFavLibraryEntity>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		ArrayList<RoofAttachmentFavLibraryEntity> list = new ArrayList<>();
//		list.add(null);
//		list.add(new RoofAttachmentFavLibraryEntity());
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add(list);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getRoofModels [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			userInformationService.getRoofModels((String) scenario.get(i).get(0));
//		}
//	}
//	
//	@Test
//	public void testsaveRoofAttachment() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofTypeandAttachmentEntity u where u.authentificationEntity.id=:p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u where u.id=:p1"))
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
//		// Result of the Query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//		//The result list of the Query1
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the Query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//		//The result list of the Query1
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the Query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//		//The result list of the Query1
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(new RoofTypeAttachementModel());
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the Query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("success");
//		//The result list of the Query1
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add(new RoofTypeAttachementModel());
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the Query2
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("error");
//		//The result list of the Query1
//		ArrayList<RoofTypeandAttachmentEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(4).add(list);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add(new RoofTypeAttachementModel());
//		scenario.get(5).add("123");
//		// Result of the query1
//		scenario.get(5).add(new RoofTypeandAttachmentEntity());
//		// Result of the Query2
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("success");
//		//The result list of the Query1
//		scenario.get(5).add(list);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("saveRoofAttachment [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(5));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			userInformationService.saveRoofAttachment((RoofTypeAttachementModel) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		}
//	}
//	
//	@Test
//	public void testgetRoofAttachment() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofTypeandAttachmentEntity u where u.authentificationEntity.id=:p1"))
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
//		// Result List of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result List of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result List of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new RoofTypeandAttachmentEntity());
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result List of the query1
//		ArrayList<RoofTypeandAttachmentEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(new RoofTypeandAttachmentEntity());
//		// Result List of the query1
//		scenario.get(4).add(list);
//		// Result excpected
//		scenario.get(4).add(new RoofTypeandAttachmentEntity());
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getRoofAttachment [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			RoofTypeandAttachmentEntity rslt = userInformationService.getRoofAttachment((String) scenario.get(i).get(0));
//		}
//	}
//}
