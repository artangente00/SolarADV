//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//
//import org.apache.fontbox.ttf.TTFParser;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//
//import com.PlayGroundAdv.Solar.Constants.Constants;
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.PathEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEngineerEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.UserLicSectionsEntity;
//import com.PlayGroundAdv.Solar.model.AuthentificationEntityResult;
//import com.PlayGroundAdv.Solar.model.EditUserInformations;
//import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.PermitServiceEdit;
//import com.PlayGroundAdv.Solar.Services.PlanSetServiceE300;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//public class TestPlanSetServiceE300 {
//
//	@Mock
//    private EntityManager em;
//
//	@Spy
//	private CheckValueTypesService checkValueTypesService;
//
//	@Spy
//	private PermitServiceEdit permitServiceEdit;
//
//    @InjectMocks
//    PlanSetServiceE300 planSetServiceE300 = new PlanSetServiceE300();
//
//
//     @Before
//     public void setupMock() {
//    	 planSetServiceE300 = new PlanSetServiceE300();
//        MockitoAnnotations.initMocks(this);
//     }
//
//
//    @Test
//	public void testbuildingPDFE300() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//    	List<UserLicSectionsEntity> resultlist =null;
//		UserLicSectionsEntity userLicSection = new UserLicSectionsEntity();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(resultlist);
//		scenario.get(0).add(userLicSection);
//
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite1= new PermitHomeSiteEntityResult();
//		scenario.get(1).add(permitHomeSite1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("test");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		AuthentificationEntity userConnectedEntity1 = new AuthentificationEntity();
//		scenario.get(1).add(userConnectedEntity1);
//		scenario.get(1).add(resultlist);
//		scenario.get(1).add(userLicSection);
//
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite2= new PermitHomeSiteEntityResult();
//		scenario.get(2).add(permitHomeSite2);
//		scenario.get(2).add(null);
//		scenario.get(2).add("idPermit");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("test");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		AuthentificationEntity userConnectedEntity2 = new AuthentificationEntity();
//		userConnectedEntity2.setId(2);
//		scenario.get(2).add(userConnectedEntity2);
//		scenario.get(2).add(resultlist);
//		scenario.get(2).add(userLicSection);
//
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite3= new PermitHomeSiteEntityResult();
//		scenario.get(3).add(permitHomeSite3);
//		scenario.get(3).add(null);
//		scenario.get(3).add("12333");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("test");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(0);
//		AuthentificationEntity userConnectedEntity3 = new AuthentificationEntity();
//		userConnectedEntity3.setId(3);
//		scenario.get(3).add(userConnectedEntity3);
//		scenario.get(3).add(resultlist);
//		scenario.get(3).add(userLicSection);
//
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite4= new PermitHomeSiteEntityResult();
//		scenario.get(4).add(permitHomeSite4);
//		scenario.get(4).add(null);
//		scenario.get(4).add("12444");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		AuthentificationEntityResult userLogedInfo = new AuthentificationEntityResult();
//		userLogedInfo.setId(1);
//		scenario.get(4).add(userLogedInfo);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add("test");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(0);
//		AuthentificationEntity userConnectedEntity4 = new AuthentificationEntity();
//		userConnectedEntity4.setId(4);
//		scenario.get(4).add(userConnectedEntity4);
//		scenario.get(4).add(resultlist);
//		scenario.get(4).add(userLicSection);
//
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite5= new PermitHomeSiteEntityResult();
//		permitHomeSite5.setSiteAddress("siteAddress");
//		permitHomeSite5.setCity("city");
//		permitHomeSite5.setState("state");
//		permitHomeSite5.setPostalCode("postalCode");
//		scenario.get(5).add(permitHomeSite5);
//		scenario.get(5).add(null);
//		scenario.get(5).add("55555");
//		PermitEntity permitEntity5 = new PermitEntity();
//		scenario.get(5).add(permitEntity5);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		AuthentificationEntityResult userLogedInfo5 = new AuthentificationEntityResult();
//		userLogedInfo5.setId(1);
//		scenario.get(5).add(userLogedInfo5);
//		scenario.get(5).add(null);
//		EditUserInformations editUserInformations5 = new EditUserInformations();
//		editUserInformations5.setId(1);
//		scenario.get(5).add(editUserInformations5);
//		scenario.get(5).add(null);
//		scenario.get(5).add("test");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(0);
//		AuthentificationEntity userConnectedEntity5 = new AuthentificationEntity();
//		userConnectedEntity5.setId(4);
//		scenario.get(5).add(userConnectedEntity5);
//		scenario.get(5).add(resultlist);
//		scenario.get(5).add(userLicSection);
//
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite6= new PermitHomeSiteEntityResult();
//		permitHomeSite6.setSiteAddress("siteAddress");
//		permitHomeSite6.setCity("city");
//		permitHomeSite6.setState("state");
//		permitHomeSite6.setPostalCode("postalCode");
//		scenario.get(6).add(permitHomeSite6);
//		scenario.get(6).add(null);
//		scenario.get(6).add("666");
//		PermitEntity permitEntity6 = new PermitEntity();
//		permitEntity6.setReleasev2("2");
//		permitEntity6.setReleasev3("3");
//		scenario.get(6).add(permitEntity6);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		AuthentificationEntityResult userLogedInfo6 = new AuthentificationEntityResult();
//		userLogedInfo6.setId(1);
//		scenario.get(6).add(userLogedInfo6);
//		scenario.get(6).add(null);
//		EditUserInformations editUserInformations6 = new EditUserInformations();
//		editUserInformations6.setId(1);
//		editUserInformations6.setContractorLicenceState("state");
//		scenario.get(6).add(editUserInformations6);
//		scenario.get(6).add(null);
//		scenario.get(6).add("test");
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(0);
//		AuthentificationEntity userConnectedEntity6 = new AuthentificationEntity();
//		userConnectedEntity6.setId(4);
//		scenario.get(6).add(userConnectedEntity6);
//		List<UserLicSectionsEntity> resultlist6 =new ArrayList<UserLicSectionsEntity>();
//		UserLicSectionsEntity userLicSection6 = new UserLicSectionsEntity();
//		resultlist6.add(userLicSection6);
//		resultlist6.add(userLicSection6);
//		scenario.get(6).add(resultlist6);
//		scenario.get(6).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite7= new PermitHomeSiteEntityResult();
//		permitHomeSite7.setSiteAddress("siteAddress");
//		permitHomeSite7.setCity("city");
//		permitHomeSite7.setState("state");
//		permitHomeSite7.setPostalCode("postalCode");
//		scenario.get(7).add(permitHomeSite7);
//		scenario.get(7).add(null);
//		scenario.get(7).add("12447");
//		PermitEntity permitEntity7 = new PermitEntity();
//		permitEntity7.setReleasev2("2");
//		permitEntity7.setReleasev3("3");
//		scenario.get(7).add(permitEntity7);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		AuthentificationEntityResult userLogedInfo7 = new AuthentificationEntityResult();
//		userLogedInfo7.setId(1);
//		scenario.get(7).add(userLogedInfo7);
//		scenario.get(7).add(null);
//		EditUserInformations editUserInformations7 = new EditUserInformations();
//		editUserInformations7.setId(1);
//		scenario.get(7).add(editUserInformations7);
//		scenario.get(7).add(null);
//		scenario.get(7).add("test");
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(0);
//		AuthentificationEntity userConnectedEntity7 = new AuthentificationEntity();
//		userConnectedEntity7.setId(4);
//		scenario.get(7).add(userConnectedEntity7);
//		List<UserLicSectionsEntity> resultlist7 =new ArrayList<UserLicSectionsEntity>();
//		UserLicSectionsEntity userLicSection7 = new UserLicSectionsEntity();
//		resultlist7.add(userLicSection7);
//		scenario.get(7).add(resultlist7);
//		scenario.get(7).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite8= new PermitHomeSiteEntityResult();
//		permitHomeSite8.setSiteAddress("siteAddress");
//		permitHomeSite8.setCity("city");
//		permitHomeSite8.setState("state");
//		permitHomeSite8.setPostalCode("postalCode");
//		scenario.get(8).add(permitHomeSite8);
//		scenario.get(8).add(null);
//		scenario.get(8).add("12448");
//		PermitEntity permitEntity8 = new PermitEntity();
//		permitEntity8.setReleasev2("2");
//		permitEntity8.setReleasev3("3");
//		scenario.get(8).add(permitEntity8);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		AuthentificationEntityResult userLogedInfo8 = new AuthentificationEntityResult();
//		userLogedInfo8.setId(1);
//		scenario.get(8).add(userLogedInfo8);
//		scenario.get(8).add(null);
//		EditUserInformations editUserInformations8 = new EditUserInformations();
//		editUserInformations8.setId(1);
//		scenario.get(8).add(editUserInformations8);
//		scenario.get(8).add(null);
//		scenario.get(8).add("test");
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.get(8).add(0);
//		AuthentificationEntity userConnectedEntity8 = new AuthentificationEntity();
//		userConnectedEntity8.setId(4);
//		scenario.get(8).add(userConnectedEntity8);
//		List<UserLicSectionsEntity> resultlist8 =new ArrayList<UserLicSectionsEntity>();
//		UserLicSectionsEntity userLicSection8 = new UserLicSectionsEntity();
//		resultlist8.add(userLicSection8);
//		scenario.get(8).add(resultlist8);
//		scenario.get(8).add(userLicSection);
//
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite9= new PermitHomeSiteEntityResult();
//		permitHomeSite9.setSiteAddress("siteAddress");
//		permitHomeSite9.setCity("city");
//		permitHomeSite9.setState("state");
//		permitHomeSite9.setPostalCode("postalCode");
//		scenario.get(9).add(permitHomeSite9);
//		scenario.get(9).add(null);
//		scenario.get(9).add("12449");
//		PermitEntity permitEntity9 = new PermitEntity();
//		permitEntity9.setReleasev2("2");
//		permitEntity9.setReleasev3("3");
//		scenario.get(9).add(permitEntity9);
//		scenario.get(9).add(null);
//		scenario.get(9).add(null);
//		AuthentificationEntityResult userLogedInfo9 = new AuthentificationEntityResult();
//		userLogedInfo9.setId(1);
//		scenario.get(9).add(userLogedInfo9);
//		scenario.get(9).add(null);
//		EditUserInformations editUserInformations9 = new EditUserInformations();
//		editUserInformations9.setId(1);
//		scenario.get(9).add(editUserInformations9);
//		scenario.get(9).add(null);
//		scenario.get(9).add("test");
//		scenario.get(9).add(null);
//		scenario.get(9).add(null);
//		scenario.get(9).add(0);
//		AuthentificationEntity userConnectedEntity9 = new AuthentificationEntity();
//		userConnectedEntity9.setId(4);
//		scenario.get(9).add(userConnectedEntity9);
//		List<UserLicSectionsEntity> resultlist9 =new ArrayList<UserLicSectionsEntity>();
//		UserLicSectionsEntity userLicSection9 = new UserLicSectionsEntity();
//		userLicSection9.setContractorLicenceState("CA");
//		resultlist9.add(userLicSection9);
//		scenario.get(9).add(resultlist9);
//		scenario.get(9).add(userLicSection9);
//
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite10= new PermitHomeSiteEntityResult();
//		permitHomeSite10.setSiteAddress("siteAddress");
//		permitHomeSite10.setCity("city");
//		permitHomeSite10.setState("state");
//		permitHomeSite10.setPostalCode("postalCode");
//		scenario.get(10).add(permitHomeSite10);
//		scenario.get(10).add(null);
//		scenario.get(10).add("124410");
//		PermitEntity permitEntity10 = new PermitEntity();
//		permitEntity10.setReleasev2("2");
//		permitEntity10.setReleasev3("3");
//		scenario.get(10).add(permitEntity10);
//		scenario.get(10).add(null);
//		scenario.get(10).add(null);
//		AuthentificationEntityResult userLogedInfo10 = new AuthentificationEntityResult();
//		userLogedInfo10.setId(1);
//		scenario.get(10).add(userLogedInfo10);
//		scenario.get(10).add(null);
//		EditUserInformations editUserInformations10 = new EditUserInformations();
//		editUserInformations10.setId(1);
//		scenario.get(10).add(editUserInformations10);
//		scenario.get(10).add(null);
//		scenario.get(10).add("test");
//		scenario.get(10).add(null);
//		scenario.get(10).add(null);
//		scenario.get(10).add(0);
//		AuthentificationEntity userConnectedEntity10 = new AuthentificationEntity();
//		userConnectedEntity10.setId(4);
//		scenario.get(10).add(userConnectedEntity10);
//		List<UserLicSectionsEntity> resultlist10 =new ArrayList<UserLicSectionsEntity>();
//		UserLicSectionsEntity userLicSection10 = new UserLicSectionsEntity();
//		userLicSection10.setContractorLicenceState("CA");
//		String [] str = {};
//		userLicSection10.setLicTypeCode(str);
//		resultlist10.add(userLicSection10);
//		scenario.get(10).add(resultlist10);
//		scenario.get(10).add(userLicSection10);
//
//
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite11= new PermitHomeSiteEntityResult();
//		permitHomeSite11.setSiteAddress("siteAddress");
//		permitHomeSite11.setCity("city");
//		permitHomeSite11.setState("state");
//		permitHomeSite11.setPostalCode("postalCode");
//		scenario.get(11).add(permitHomeSite11);
//		scenario.get(11).add(null);
//		scenario.get(11).add("124411");
//		PermitEntity permitEntity11 = new PermitEntity();
//		permitEntity11.setReleasev2("2");
//		permitEntity11.setReleasev3("3");
//		scenario.get(11).add(permitEntity11);
//		scenario.get(11).add(null);
//		scenario.get(11).add(null);
//		AuthentificationEntityResult userLogedInfo11 = new AuthentificationEntityResult();
//		userLogedInfo11.setId(1);
//		scenario.get(11).add(userLogedInfo11);
//		scenario.get(11).add(null);
//		EditUserInformations editUserInformations11 = new EditUserInformations();
//		editUserInformations11.setId(1);
//		scenario.get(11).add(editUserInformations11);
//		scenario.get(11).add(null);
//		scenario.get(11).add("test");
//		scenario.get(11).add(null);
//		scenario.get(11).add(null);
//		scenario.get(11).add(0);
//		AuthentificationEntity userConnectedEntity11 = new AuthentificationEntity();
//		userConnectedEntity11.setId(4);
//		scenario.get(11).add(userConnectedEntity11);
//		List<UserLicSectionsEntity> resultlist11 =new ArrayList<UserLicSectionsEntity>();
//		UserLicSectionsEntity userLicSection11 = new UserLicSectionsEntity();
//		String [] LicTypeCode = {null};
//		userLicSection11.setLicTypeCode(LicTypeCode);
//		userLicSection11.setContractorLicenceState("CA");
//		resultlist11.add(userLicSection11);
//		scenario.get(11).add(resultlist11);
//		scenario.get(11).add(userLicSection11);
//
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from PermitEntity u " + " where u.id = :p1 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//	    when(mockedQuery.getSingleResult()).thenReturn(null);
//
//	    Query q = mock(Query.class);
//		when(em.createQuery("SELECT u.companyLogoName from UserSettingEntity u where u.userId.id =:p1 and u.logoConfirmed=:p2")).thenReturn(q);
//		when(q.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(q);
//	    when(q.getSingleResult()).thenReturn(null);
//
//	    Query q1 = mock(Query.class);
//		when(em.createQuery("SELECT u.signature from UserSettingEntity u where u.userId.id =:p1 and u.signatureConfirmed=:p2")).thenReturn(q1);
//		when(q1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(q1);
//	    when(q1.getSingleResult()).thenReturn(null);
//
//	    Query query = mock(Query.class);
//		when(em.createQuery("SELECT u from UserLicSectionsEntity u WHERE u.authentificationEntity.id = :p1 AND u.contractorLicenceState= :p2")).thenReturn(query);
//		when(query.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(query);
//
//		for(int i = 0; i < scenario.size(); i++) {
//			System.out.println("testbuildingPDFE300 ["+i+"]");
//			 when(query.getResultList()).thenReturn((List<UserLicSectionsEntity>)scenario.get(i).get(15));
//			 when(query.getSingleResult()).thenReturn((UserLicSectionsEntity)scenario.get(i).get(16));
//		}
//    }
//
//}
