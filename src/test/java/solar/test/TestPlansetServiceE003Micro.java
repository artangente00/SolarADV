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
//import java.util.Arrays;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
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
//import com.PlayGroundAdv.Solar.Entity.ACCombinerSLC;
//import com.PlayGroundAdv.Solar.Entity.ACDisconnect;
//import com.PlayGroundAdv.Solar.Entity.Cmodulev2;
//import com.PlayGroundAdv.Solar.Entity.ConduitConductorCircuitEntity;
//import com.PlayGroundAdv.Solar.Entity.DCCombinerDisconnectEntity;
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.Inverters;
//import com.PlayGroundAdv.Solar.Entity.LeasePPAMeter;
//import com.PlayGroundAdv.Solar.Entity.NEC_310_16_B_16;
//import com.PlayGroundAdv.Solar.Entity.PermitConduitConductorSectionEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.UserLicSectionsEntity;
//import com.PlayGroundAdv.Solar.model.DCCombinerDisconnectRequest;
//import com.PlayGroundAdv.Solar.model.EditUserInformations;
//import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
//import com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
//import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.PermitService;
//import com.PlayGroundAdv.Solar.Services.PermitServiceEdit;
//import com.PlayGroundAdv.Solar.Services.PlansetServiceE003Micro;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//public class TestPlansetServiceE003Micro {
//
//	@Mock
//    private EntityManager em;
//	
//	@Mock
//    private PermitService permitService;
//	
//	@Spy
//	private CheckValueTypesService checkValueTypesService;
//	
//	@Spy
//	private PermitServiceEdit permitServiceEdit;
//	
//    @InjectMocks
//    PlansetServiceE003Micro plansetServiceE003Micro = new PlansetServiceE003Micro();
//    
//    PdfReader reader = null;
//   	File fileRe = null;
//   	PdfStamper stamper = null;
//   	AcroFields form = null;
//   	PdfReader readerOrigin= null;
//   	
//     @Before
//     public void setupMock() {
//    	 plansetServiceE003Micro = new PlansetServiceE003Micro();
//        MockitoAnnotations.initMocks(this);
// 		
// 		try {
// 		reader = new PdfReader(Constants.rapportPlansetFolderUrl +"NEC-PDF/" +"PDF-E003-MICRO"+".pdf" );
// 		fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E003-MICRO"+".pdf");
// 		stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
// 		form = stamper.getAcroFields();
// 		readerOrigin = new PdfReader( Constants.rapportPlansetFolderUrl +"NEC-PDF/" + "PDF-E003-MICRO.pdf" );
// 		} catch (IOException e) {
// 			e.printStackTrace();
// 		}
// 		catch (DocumentException e){
// 		}
//     }
//     
//     @After
//     public void runAfterTestMethod() {
//     	try {
// 			stamper.close();
// 			reader.close();
// 		} catch (IOException e) {
// 				e.printStackTrace();
// 		}
// 		  catch (DocumentException e){
// 	    }
//     }
//
//	@Test
//	public void testmapTitleBlock() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);	
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite1 = new PermitHomeSiteEntityResult();
//		scenario.get(1).add(permitHomeSite1);
//		scenario.get(1).add(form);	
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		PermitArrayEntityResultSecond permitArraysEntityResult1= new PermitArrayEntityResultSecond();
//		scenario.get(1).add(permitArraysEntityResult1);
//		scenario.get(1).add(1);
//		scenario.get(1).add(1);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//		permitHomeSite2.setSiteAddress("siteAddress");
//		permitHomeSite2.setCity("city");
//		permitHomeSite2.setState("NY");
//		scenario.get(2).add(permitHomeSite2);
//		scenario.get(2).add(form);
//		PermitEntity permitEntity2 = new PermitEntity();
//		scenario.get(2).add(permitEntity2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		PermitArrayEntityResultSecond permitArraysEntityResult2= new PermitArrayEntityResultSecond();
//		permitArraysEntityResult2.setDeviceToIncorporate("Neither");
//		scenario.get(2).add(permitArraysEntityResult2);
//		scenario.get(2).add(2);
//		scenario.get(2).add(2);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//		permitHomeSite3.setSiteAddress("siteAddress");
//		permitHomeSite3.setCity("city");
//		permitHomeSite3.setState("NY");
//		scenario.get(3).add(permitHomeSite3);
//		scenario.get(3).add(form);
//		PermitEntity permitEntity3 = new PermitEntity();
//		scenario.get(3).add(permitEntity3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		PermitArrayEntityResultSecond permitArraysEntityResult3= new PermitArrayEntityResultSecond();
//		permitArraysEntityResult3.setDeviceToIncorporate("Neither");
//		permitArraysEntityResult3.setPvModuleModEl("pvModuleModEl");
//		scenario.get(3).add(permitArraysEntityResult3);
//		scenario.get(3).add(3);
//		scenario.get(3).add(3);
//		ElectricalUtilityEntity electricalCompany3 = new ElectricalUtilityEntity();
//		scenario.get(3).add(electricalCompany3);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//		permitHomeSite4.setSiteAddress("siteAddress");
//		permitHomeSite4.setCity("city");
//		permitHomeSite4.setState("NY");
//		scenario.get(4).add(permitHomeSite4);
//		scenario.get(4).add(form);
//		PermitEntity permitEntity4 = new PermitEntity();
//		scenario.get(4).add(permitEntity4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		PermitArrayEntityResultSecond permitArraysEntityResult4= new PermitArrayEntityResultSecond();
//		permitArraysEntityResult4.setDeviceToIncorporate("Neither");
//		permitArraysEntityResult4.setPvModuleModEl("pvModule:ModEl");
//		scenario.get(4).add(permitArraysEntityResult4);
//		scenario.get(4).add(4);
//		scenario.get(4).add(4);
//		ElectricalUtilityEntity electricalCompany4 = new ElectricalUtilityEntity();
//		electricalCompany4.setUtilityCompanyName("Oncor Electric Delivery");
//		scenario.get(4).add(electricalCompany4);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//		permitHomeSite5.setSiteAddress("siteAddress");
//		permitHomeSite5.setCity("city");
//		permitHomeSite5.setState("NY");
//		scenario.get(5).add(permitHomeSite5);
//		scenario.get(5).add(form);
//		PermitEntity permitEntity5 = new PermitEntity();
//		scenario.get(5).add(permitEntity5);
//		PermitCompanyInfoEntityResult permitCompanyInfo5 = new PermitCompanyInfoEntityResult();
//		scenario.get(5).add(permitCompanyInfo5);
//		scenario.get(5).add(null);
//		PermitArrayEntityResultSecond permitArraysEntityResult5= new PermitArrayEntityResultSecond();
//		permitArraysEntityResult5.setDeviceToIncorporate("Neither");
//		permitArraysEntityResult5.setPvModuleModEl("pvModule:ModEl");
//		permitArraysEntityResult5.setInverterModel("InverterModel");
//		scenario.get(5).add(permitArraysEntityResult5);
//		scenario.get(5).add(5);
//		scenario.get(5).add(5);
//		ElectricalUtilityEntity electricalCompany5 = new ElectricalUtilityEntity();
//		electricalCompany5.setUtilityCompanyName("Oncor Electric Delivery");
//		scenario.get(5).add(electricalCompany5);
//		for(int i = 0; i < scenario.size(); i++) {
//			 plansetServiceE003Micro.mapTitleBlock((PermitHomeSiteEntityResult)scenario.get(i).get(0),(PermitArrayEntityResultSecond)scenario.get(i).get(5),(int)scenario.get(i).get(6),(PermitEntity)scenario.get(i).get(2),(AcroFields)scenario.get(i).get(1),(String)scenario.get(i).get(4),(PermitCompanyInfoEntityResult)scenario.get(i).get(3),(ElectricalUtilityEntity)scenario.get(i).get(8),0);
//		}
//	}
//
//	
//	@Test
//	public void testmapContractorInfo() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		List<UserLicSectionsEntity> resultlist =null;
//		UserLicSectionsEntity userLicSection = new UserLicSectionsEntity();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(resultlist);
//		scenario.get(0).add(userLicSection);
//		
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations1 = new EditUserInformations() ;
//		scenario.get(1).add(editUserInformations1);
//		scenario.get(1).add(form);
//		scenario.get(1).add(null);
//		scenario.get(1).add(resultlist); 
//		scenario.get(1).add(userLicSection);
//		
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations2 = new EditUserInformations() ;
//		editUserInformations2.setContactFirstName("FirstName");
//		editUserInformations2.setContactOptions("firstContact");
//		editUserInformations2.setDesignBy("firstContact");
//		scenario.get(2).add(editUserInformations2);
//		scenario.get(2).add(form);
//		PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//		scenario.get(2).add(permitHomeSite2);
//		scenario.get(2).add(resultlist); 
//		scenario.get(2).add(userLicSection);
//		
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations3 = new EditUserInformations() ;
//		editUserInformations3.setContactFirstName("FirstName");
//		editUserInformations3.setContactLastName("LastName");
//		editUserInformations3.setContactOptions("secondContact");
//		editUserInformations3.setDesignBy("secondContact");
//		scenario.get(3).add(editUserInformations3);
//		scenario.get(3).add(form);
//		PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//		permitHomeSite3.setState("state");
//		scenario.get(3).add(permitHomeSite3);
//		scenario.get(3).add(resultlist); 
//		scenario.get(3).add(userLicSection);
//		
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations4 = new EditUserInformations() ;
//		editUserInformations4.setContactFirstName("FirstName");
//		editUserInformations4.setContactLastName("LastName");
//		editUserInformations4.setContactOptions("thirdContact");
//		editUserInformations4.setDesignBy("thirdContact");
//		editUserInformations4.setContractorLicenceState("state");
//		editUserInformations4.setContractorLic(false);
//		editUserInformations4.setContractorLicC10(false);
//		editUserInformations4.setContractorLicB(false);
//		scenario.get(4).add(editUserInformations4);
//		scenario.get(4).add(form);
//		PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//		permitHomeSite4.setState("state");
//		scenario.get(4).add(permitHomeSite4);
//		scenario.get(4).add(resultlist);
//		scenario.get(4).add(userLicSection);
//		
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations5 = new EditUserInformations() ;
//		editUserInformations5.setContactFirstName("FirstName");
//		editUserInformations5.setContactLastName("LastName");
//		editUserInformations5.setContactOptions("Other");
//		editUserInformations5.setDesignBy("OtherDesignBy");
//		editUserInformations5.setContractorLicenceState("state");
//		editUserInformations5.setContractorLic(false);
//		editUserInformations5.setContractorLicC10(false);
//		editUserInformations5.setContractorLicB(false);
//		scenario.get(5).add(editUserInformations5);
//		scenario.get(5).add(form);
//		PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//		permitHomeSite5.setState("state");
//		scenario.get(5).add(permitHomeSite5);
//		List<UserLicSectionsEntity> resultlist5 =new ArrayList<UserLicSectionsEntity>();
//		resultlist5.add(userLicSection);
//		scenario.get(5).add(resultlist5);
//		scenario.get(5).add(userLicSection);
//		
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations6 = new EditUserInformations() ;
//		editUserInformations6.setContactFirstName("FirstName");
//		editUserInformations6.setContactLastName("LastName");
//		editUserInformations6.setContactOptions("Other");
//		editUserInformations6.setDesignBy("");
//		editUserInformations6.setContractorLicenceState("state");
//		editUserInformations6.setContractorLic(false);
//		editUserInformations6.setContractorLicC10(false);
//		editUserInformations6.setContractorLicB(false);
//		scenario.get(6).add(editUserInformations6);
//		scenario.get(6).add(form);
//		PermitHomeSiteEntityResult permitHomeSite6 = new PermitHomeSiteEntityResult();
//		permitHomeSite6.setState("state");
//		scenario.get(6).add(permitHomeSite6);
//		List<UserLicSectionsEntity> resultlist6 =new ArrayList<UserLicSectionsEntity>();
//		UserLicSectionsEntity userLicSection6 = new UserLicSectionsEntity();
//		userLicSection6.setContractorLicenceState("CA");
//		resultlist6.add(userLicSection6);
//		scenario.get(6).add(resultlist6);
//		scenario.get(6).add(userLicSection6);
//		
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations7 = new EditUserInformations() ;
//		editUserInformations7.setContactFirstName("FirstName");
//		editUserInformations7.setContactLastName("LastName");
//		editUserInformations7.setContactOptions("Other");
//		editUserInformations7.setDesignBy("");
//		editUserInformations7.setContractorLicenceState("state");
//		editUserInformations7.setContractorLic(false);
//		editUserInformations7.setContractorLicC10(false);
//		editUserInformations7.setContractorLicB(false);
//		scenario.get(7).add(editUserInformations7);
//		scenario.get(7).add(form);
//		PermitHomeSiteEntityResult permitHomeSite7 = new PermitHomeSiteEntityResult();
//		permitHomeSite7.setState("state");
//		scenario.get(7).add(permitHomeSite7);
//		List<UserLicSectionsEntity> resultlist7 =new ArrayList<UserLicSectionsEntity>();
//		UserLicSectionsEntity userLicSection7 = new UserLicSectionsEntity();
//		String [] str = {};
//		userLicSection7.setLicTypeCode(str);
//		userLicSection7.setContractorLicenceState("CA");
//		resultlist7.add(userLicSection7);
//		scenario.get(7).add(resultlist7);
//		scenario.get(7).add(userLicSection7);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations8 = new EditUserInformations() ;
//		editUserInformations8.setContactFirstName("FirstName");
//		editUserInformations8.setContactLastName("LastName");
//		editUserInformations8.setContactOptions("Other");
//		editUserInformations8.setDesignBy("");
//		editUserInformations8.setContractorLicenceState("state");
//		editUserInformations8.setContractorLic(false);
//		editUserInformations8.setContractorLicC10(false);
//		editUserInformations8.setContractorLicB(false);
//		scenario.get(8).add(editUserInformations8);
//		scenario.get(8).add(form);
//		PermitHomeSiteEntityResult permitHomeSite8 = new PermitHomeSiteEntityResult();
//		permitHomeSite8.setState("state");
//		scenario.get(8).add(permitHomeSite8);
//		List<UserLicSectionsEntity> resultlist8 =new ArrayList<UserLicSectionsEntity>();
//		UserLicSectionsEntity userLicSection8 = new UserLicSectionsEntity();
//		String [] LicTypeCode = {null};
//		userLicSection8.setLicTypeCode(LicTypeCode);
//		userLicSection8.setContractorLicenceState("CA");
//		resultlist8.add(userLicSection8);
//		scenario.get(8).add(resultlist8);
//		scenario.get(8).add(userLicSection8);
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u from UserLicSectionsEntity u WHERE u.authentificationEntity.id = :p1 AND u.contractorLicenceState= :p2")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			 when(mockedQuery.getResultList()).thenReturn((List<UserLicSectionsEntity>)scenario.get(i).get(3));
//			 when(mockedQuery.getSingleResult()).thenReturn((UserLicSectionsEntity)scenario.get(i).get(4));
//			 plansetServiceE003Micro.mapContractorInfo((EditUserInformations)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),0);
//		}
//	}
//	
//	@Test
//	public void testcheckACdisconnect() {
//		ArrayList<String[]> acDisconnecTabList=new ArrayList<String[]>();
//		ArrayList<PermitProjectSiteInfoEntityTwo> scenario = new ArrayList<PermitProjectSiteInfoEntityTwo>();
//	    scenario.add(null);
//	    String[] acDisconnecTab0=new String[2];
//	    acDisconnecTab0[0]=null;
//	    acDisconnecTabList.add(acDisconnecTab0);
//	    PermitProjectSiteInfoEntityTwo scenario1 = new PermitProjectSiteInfoEntityTwo();
//	    String[] acDisconnecTab1=new String[2];
//	    acDisconnecTab1[0]=null;
//	    acDisconnecTabList.add(acDisconnecTab1);
//		scenario.add(scenario1);
//		PermitProjectSiteInfoEntityTwo scenario2 = new PermitProjectSiteInfoEntityTwo();
//		scenario2.setRooftopACCombinerModelTwo("test");
//		scenario2.setRooftopACCombinerModel("test");
//		String[] acDisconnecTab2=new String[2];
//		acDisconnecTab2[0]=null;
//		acDisconnecTabList.add(acDisconnecTab2);
//		scenario.add(scenario2);
//		PermitProjectSiteInfoEntityTwo scenario3= new PermitProjectSiteInfoEntityTwo();
//		scenario3.setRooftopACCombinerModelTwo("test");
//		scenario3.setRooftopACCombinerModel("test");
//		String[] acDisconnecTab3=new String[2];
//		acDisconnecTab3[0]="F";
//		acDisconnecTabList.add(acDisconnecTab3);
//		scenario.add(scenario3);
//		for(int i = 0; i < scenario.size(); i++) {
//			if(i>=2) {
//		Mockito.doReturn(acDisconnecTabList.get(i)).when(permitService).getACDisconnectType(scenario.get(i).getRooftopACCombinerModel());
//			}
//		 plansetServiceE003Micro.checkACdisconnect((PermitProjectSiteInfoEntityTwo)scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testthreeLineMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
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
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitAdvEntityResult editPermitAdvRequest = new PermitAdvEntityResult();
//		scenario.get(1).add(editPermitAdvRequest);
//		PermitConduitConductorSectionEntity circuit = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitAdvEntityResult editPermitAdvRequest1 = new PermitAdvEntityResult();
//		scenario.get(2).add(editPermitAdvRequest1);
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		circuit1.setComponentOrder("componentOrder");
//		scenario.get(2).add(circuit1);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitAdvEntityResult editPermitAdvRequest2 = new PermitAdvEntityResult();
//		scenario.get(3).add(editPermitAdvRequest2);
//		PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//		circuit2.setComponentOrder("ACC-");
//		scenario.get(3).add(circuit2);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitAdvEntityResult editPermitAdvRequest3 = new PermitAdvEntityResult();
//		scenario.get(4).add(editPermitAdvRequest3);
//		PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//		circuit3.setComponentOrder("MINV");
//		scenario.get(4).add(circuit3);
//		scenario.get(4).add(null);
//		 PermitHomeSiteEntityResult permitHomeSite = new PermitHomeSiteEntityResult();
//		scenario.get(4).add(permitHomeSite);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.threeLineMapping((PermitAdvEntityResult) scenario.get(i).get(0),
//					(PermitConduitConductorSectionEntity) scenario.get(i).get(1),
//					(PermitProjectSiteInfoEntityTwo) scenario.get(i).get(2),
//					(PermitHomeSiteEntityResult) scenario.get(i).get(3), (Cmodulev2) scenario.get(i).get(4),
//					(String) scenario.get(i).get(5), (PermitEntity) scenario.get(i).get(6),
//					(PermitArrayEntityResultSecond) scenario.get(i).get(7), (List<Inverters>) scenario.get(i).get(8),
//					(Inverters) scenario.get(i).get(9), stamper, (ACDisconnect) scenario.get(i).get(10),
//					(ACDisconnect) scenario.get(i).get(11), (ACCombinerSLC) scenario.get(i).get(12),
//					(String) scenario.get(i).get(13), 0);
//		}
//	}
//		
//	@Test
//	public void testequipmentTabMapping() {
//		List <Integer> componentListQTY=new ArrayList<Integer>();
//		componentListQTY.add(0); 
//		componentListQTY.add(1); 
//		componentListQTY.add(0); 
//		componentListQTY.add(1); 
//		componentListQTY.add(0); 
//		componentListQTY.add(1); 
//		componentListQTY.add(0); 
//		componentListQTY.add(1); 
//		componentListQTY.add(0); 
//		componentListQTY.add(1); 
//		componentListQTY.add(0); 
//		componentListQTY.add(1); 
//		componentListQTY.add(0); 
//		componentListQTY.add(1); 
//		componentListQTY.add(0); 
//		componentListQTY.add(1); 
//		componentListQTY.add(0); 
//		componentListQTY.add(1); 
//		componentListQTY.add(0); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//		Cmodulev2 moduleInfo = new Cmodulev2();
//		scenario.get(1).add(moduleInfo);
//		scenario.get(1).add(null);
//		PermitArrayEntityResultSecond permitArraysEntityResult =new PermitArrayEntityResultSecond();
//		scenario.get(1).add(permitArraysEntityResult);
//		scenario.get(1).add(0);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		Cmodulev2 moduleInfo2 = new Cmodulev2();
//		scenario.get(2).add(moduleInfo2);
//		scenario.get(2).add(null);
//		PermitArrayEntityResultSecond permitArraysEntityResult2 =new PermitArrayEntityResultSecond();
//		permitArraysEntityResult2.setDeviceToIncorporate("Micro Inverter");
//		scenario.get(2).add(permitArraysEntityResult2);
//		scenario.get(2).add(0);
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.equipmentTabMapping(form, componentListQTY, (ACDisconnect)scenario.get(i).get(0), moduleInfo2, (Inverters)scenario.get(i).get(2), permitArraysEntityResult2, 0, null,(Integer)scenario.get(i).get(4));
//		}
//	}
//		
//	@Test
//	public void testpermitProjectMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo =new PermitProjectSiteInfoEntityTwo();
//		scenario.get(1).add(permitProjectSiteInfo);
//		scenario.get(1).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 =new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo2.setPanelBusRating("Other");
//		permitProjectSiteInfo2.setPanelMainBreakerRating("Other");
//		permitProjectSiteInfo2.setMainPanelUpgrade(true);
//		permitProjectSiteInfo2.setMainBreakerLocatedEndBusBar(true);
//		scenario.get(2).add(permitProjectSiteInfo2);
//		scenario.get(2).add(0);
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.permitProjectMapping(form,(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(0),(int)scenario.get(i).get(1));
//		}
//	}
//	
//	@Test
//	public void testpermitHomeMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSiteEntityResult =new PermitHomeSiteEntityResult();
//		scenario.get(1).add(permitHomeSiteEntityResult);
//		scenario.get(1).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult PermitHomeSiteEntityResult2 =new PermitHomeSiteEntityResult();
//		PermitHomeSiteEntityResult2.setIfServiceVoltage(true);
//		scenario.get(2).add(PermitHomeSiteEntityResult2);
//		scenario.get(2).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult PermitHomeSiteEntityResult3 =new PermitHomeSiteEntityResult();
//		PermitHomeSiteEntityResult3.setIfServiceVoltage(false);
//		scenario.get(3).add(PermitHomeSiteEntityResult3);
//		scenario.get(3).add(0);
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.permitHomeMapping(form,(PermitHomeSiteEntityResult)scenario.get(i).get(0),(int)scenario.get(i).get(1));
//		}
//	}
//		
//	@Test
//	public void testcomponentListQtyMapping() {
//		List <Integer> componentListQTY=new ArrayList<Integer>();
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(0); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		DCCombinerDisconnectRequest dcCombinerDisconnect = new DCCombinerDisconnectRequest();
//		scenario.get(1).add(dcCombinerDisconnect);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		ACDisconnect acDisconnect = new ACDisconnect();
//		scenario.get(1).add(acDisconnect);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		DCCombinerDisconnectRequest dcCombinerDisconnect2 = new DCCombinerDisconnectRequest();
//		dcCombinerDisconnect2.setTypeDc("J Box");
//		scenario.get(2).add(dcCombinerDisconnect2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		ACDisconnect acDisconnect2 = new ACDisconnect();
//		acDisconnect2.setDisconnectDeviceType("Fusible");
//		scenario.get(2).add(acDisconnect2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		DCCombinerDisconnectRequest dcCombinerDisconnect3 = new DCCombinerDisconnectRequest();
//		dcCombinerDisconnect3.setTypeDc("J Box");
//		scenario.get(3).add(dcCombinerDisconnect3);
//		scenario.get(3).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(3).add(permitProjectSiteInfo3);
//		ACDisconnect acDisconnect3 = new ACDisconnect();
//		acDisconnect3.setDisconnectDeviceType("Fusible");
//		scenario.get(3).add(acDisconnect3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//		  plansetServiceE003Micro.componentListQtyMapping(form,componentListQTY,(DCCombinerDisconnectRequest)scenario.get(i).get(0),(DCCombinerDisconnectEntity)scenario.get(i).get(1),(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(2),(ACDisconnect)scenario.get(i).get(3),(LeasePPAMeter)scenario.get(i).get(4),(ACCombinerSLC)scenario.get(i).get(5),(ACDisconnect)scenario.get(i).get(6),(ACCombinerSLC)scenario.get(i).get(7),0);
//		}	
//	}
//	
//	@Test
//	public void testquatrePourCentAverageHigh() {
//		ArrayList<PermtiWeatherEntityResult> scenario = new ArrayList<PermtiWeatherEntityResult>();
//	    scenario.add(null);
//	    PermtiWeatherEntityResult scenario1 = new PermtiWeatherEntityResult();
//		scenario.add(scenario1);
//		 PermtiWeatherEntityResult scenario2 = new PermtiWeatherEntityResult();
//		 scenario2.setQuatrePourCentAverageHigh("4c");
//		 scenario.add(scenario2);
//		 PermtiWeatherEntityResult scenario3= new PermtiWeatherEntityResult();
//		 scenario3.setQuatrePourCentAverageHigh("Other");
//		 scenario3.setQuatrePourCentAvHighOther("4C");
//		 scenario.add(scenario3);
//		 PermtiWeatherEntityResult scenario4= new PermtiWeatherEntityResult();
//		 scenario4.setQuatrePourCentAverageHigh("Other");
//		 scenario.add(scenario4);
//		for(int i = 0; i < scenario.size(); i++) {
//		 plansetServiceE003Micro.quatrePourCentAverageHigh(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetiscRef() {
//		ArrayList<Cmodulev2> scenario = new ArrayList<Cmodulev2>();
//	    scenario.add(null);
//	    Cmodulev2 scenario1 = new Cmodulev2();
//		scenario.add(scenario1);
//		 Cmodulev2 scenario2 = new Cmodulev2();
//		 scenario2.setiScRef("1,2");
//		 scenario.add(scenario2);
//		 Cmodulev2 scenario3= new Cmodulev2();
//		 scenario3.setiScRef("11");
//		 scenario.add(scenario3);
//		
//		List<String> expectedresult = Arrays.asList("","","1.2","11");
//		for(int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE003Micro.getiscRef(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetCircuitEnvironment() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//	scenario.add(new ArrayList<Object>());
//	scenario.get(0).add(null);
//	scenario.add(new ArrayList<Object>());
//	PermitArrayEntityResultSecond permitArraysEntityResult1 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult1.setRoofMounted(true);
//	scenario.get(1).add(permitArraysEntityResult1);
//	scenario.add(new ArrayList<Object>());
//	PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult2.setRoofMounted(true);
//	scenario.get(2).add(permitArraysEntityResult1);
//	for(int i = 0; i < scenario.size(); i++) {
//		plansetServiceE003Micro.getCircuitEnvironment((PermitArrayEntityResultSecond)scenario.get(i).get(0));
//	}
//	}
//	
//	@Test
//	public void testmapAmpacityCorrection() {
//		PlansetServiceE003Micro plansetServiceE003Micro1 = Mockito.spy(plansetServiceE003Micro);
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("ROOFTOP");
//		scenario.get(1).add(0);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("ROOFTOP");
//		scenario.get(2).add(0);
//		scenario.get(2).add("35");
//		scenario.get(2).add("0");
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("");
//		scenario.get(3).add(80);
//		scenario.get(3).add("35");
//		scenario.get(3).add("0");
//		for(int i = 0; i < scenario.size(); i++) {
//		Mockito.doReturn((String)scenario.get(i).get(3)).when(plansetServiceE003Micro1).getDCAmpacityCorrectionB2aMicro((Integer)scenario.get(i).get(1));
//		Mockito.doReturn((String)scenario.get(i).get(3)).when(plansetServiceE003Micro1).getACAmpacityCorrectionB2aMicro((Integer)scenario.get(i).get(1));
//		  plansetServiceE003Micro.mapAmpacityCorrection((String)scenario.get(i).get(0),(Integer)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//		}
//	}
//	
//	@Test
//	public void testgetnec310() {
//		List<Double> scenario = Arrays.asList(0.0,12.5,3.15,4.6);
//		Integer [] expectedresult = {(int)0.0,(int)Math.round(12.5),(int)Math.round(3.15),(int)Math.round(4.6)};
//		for(int i = 0; i < scenario.size(); i++) {
//		  Integer result = plansetServiceE003Micro.getnec310(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetnec31016() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(12);
//		NEC_310_16_B_16 nEC_310_16_B_16 = new NEC_310_16_B_16 ();
//		scenario.get(1).add(nEC_310_16_B_16);
//		Query mockedQuery = mock(Query.class);
//		   when(em.createQuery("SELECT u"
//					+ " from NEC_310_16_B_16 u "
//					+ " where u.ninetyInsulation > :p1 ")).thenReturn(mockedQuery);
//		   when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//	    
//		for(int i = 0; i < scenario.size(); i++) {
//		  when(mockedQuery.getSingleResult()).thenReturn(scenario.get(i).get(1));
//		  plansetServiceE003Micro.getnec31016((Integer)scenario.get(i).get(0));
//		}
//	}
//	
//	@Test
//	public void testiacMaxMicroCalcul() {
//		ArrayList<Inverters> scenario = new ArrayList<Inverters>();
//	    scenario.add(null);
//	    Inverters scenario1 = new Inverters();
//		scenario.add(scenario1);
//		 Inverters scenario2 = new Inverters();
//		 scenario2.setIacmax("1,2");
//		 scenario.add(scenario2);
//		 Inverters scenario3= new Inverters();
//		 scenario3.setIacmax("11");
//		 scenario.add(scenario3);
//		for(int i = 0; i < scenario.size(); i++) {
//		 plansetServiceE003Micro.iacMaxMicroCalcul(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testdc1ExistingMappingbeforeRev() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit);
//		PermitEntity permitEntity = new PermitEntity();
//		scenario.get(1).add(permitEntity);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		for(int i = 0; i < scenario.size(); i++) {
//			 plansetServiceE003Micro.dc1ExistingMappingbeforeRev(form,(PermitConduitConductorSectionEntity)scenario.get(i).get(0),(PermitEntity)scenario.get(i).get(1),(PermitArrayEntityResultSecond)scenario.get(i).get(2),(String)scenario.get(i).get(3),0);
//		}
//	}
//	
//	@Test
//	public void testdc1ExistingMappingafterRevision() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		ConduitConductorCircuitEntity circuitConductor = new ConduitConductorCircuitEntity();
//		scenario.get(1).add(circuitConductor);
//		 EditUserInformations editUserInformations= new EditUserInformations();
//		scenario.get(1).add(editUserInformations);
//		 PermitEntity permitEntity=new PermitEntity();
//		scenario.get(1).add(permitEntity);
//		for(int i = 0; i < scenario.size(); i++) {
//			 plansetServiceE003Micro.dc1ExistingMappingafterRevision(form,(ConduitConductorCircuitEntity)scenario.get(i).get(0),(EditUserInformations)scenario.get(i).get(1),(PermitEntity)scenario.get(i).get(2),0);
//		}
//	}
//	
//	@Test
//	public void testgetRequiredAmpacity() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(0);
//		scenario.get(0).add(0);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		for(int i = 0; i < scenario.size(); i++) {
//			 plansetServiceE003Micro.getRequiredAmpacity((int)scenario.get(i).get(0),(int)scenario.get(i).get(1),(int)scenario.get(i).get(2),(Integer)scenario.get(i).get(3),(Boolean)scenario.get(i).get(4),(Double)scenario.get(i).get(5),0);
//		}
//		
//	}
//	
//	@Test
//	public void testconductorQtyCalcul() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("");
//		scenario.get(1).add(0);
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("AC COMBINER/LOAD CENTER-");
//		scenario.get(2).add(4);
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("AC COMBINER/LOAD CENTER-");
//		scenario.get(3).add(4);
//		scenario.get(3).add(null);
//		scenario.get(3).add(0);
//		ACCombinerSLC acCombiner3 = new ACCombinerSLC();
//		scenario.get(3).add(acCombiner3);
//		scenario.get(3).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add("AC COMBINER/LOAD CENTER-");
//		scenario.get(4).add(4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(0);
//		scenario.get(4).add(null);
//		ACCombinerSLC SLCacCombiner4 = new ACCombinerSLC();
//		scenario.get(4).add(SLCacCombiner4);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add("PV MODULE-INVERTER");
//		scenario.get(5).add(1);
//		scenario.get(5).add(null);
//		scenario.get(5).add(0);
//		scenario.get(5).add(null);
//		ACCombinerSLC SLCacCombiner5 = new ACCombinerSLC();
//		scenario.get(5).add(SLCacCombiner5);
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.conductorQtyCalcul(form, (String)scenario.get(i).get(0), 1, (PermitProjectSiteInfoEntityTwo)scenario.get(i).get(2), (int)scenario.get(i).get(3), (ACCombinerSLC)scenario.get(i).get(4), (ACCombinerSLC)scenario.get(i).get(5), 0, null);
//		}
//	}
//	
//		@Test
//		public void testgetnec31016Column90() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(0.0);
//			scenario.get(0).add(0);
//			scenario.add(new ArrayList<Object>());
//			scenario.get(1).add(0.0);
//			scenario.get(1).add(80);
//			double [] expectedresult = {(int)0.0,0};
//			for(int i = 0; i < scenario.size(); i++) {
//				double result = plansetServiceE003Micro.getnec31016Column90((double)scenario.get(i).get(0),(Integer)scenario.get(i).get(1));
//			}
//		}
//		
//		@Test
//		public void testgetnec310ACcircuit() {
//		List<Double> scenario = Arrays.asList(0.0,12.5,3.15,4.6);
//		Integer [] expectedresult = {(int)0.0,(int)Math.round(12.5),(int)Math.round(3.15),(int)Math.round(4.6)};
//		for(int i = 0; i < scenario.size(); i++) {
//		  Integer result = plansetServiceE003Micro.getnec310ACcircuit(scenario.get(i));
//		}
//		}
//	
//		@Test
//		public void testgetTradeSizeACcircuit() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.add(new ArrayList<Object>());
//			scenario.get(1).add(12);
//			NEC_310_16_B_16 nEC_310_16_B_16 = new NEC_310_16_B_16 ();
//			scenario.get(1).add(nEC_310_16_B_16);
//			Query mockedQuery = mock(Query.class);
//			   when(em.createQuery("SELECT u"
//						+ " from NEC_310_16_B_16 u "
//						+ " where u.seventyFiveInsulation > :p1 and u.tradeSze != :p2 ")).thenReturn(mockedQuery);
//			   when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    
//			for(int i = 0; i < scenario.size(); i++) {
//			  when(mockedQuery.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			  plansetServiceE003Micro.getTradeSizeACcircuit((Integer)scenario.get(i).get(0));
//			}	
//		}
//	
//	@Test
//	public void testmapInverterCircuit() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit);
//		scenario.get(1).add(null);
//		scenario.get(1).add(1);
//		scenario.get(1).add(null);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//		  plansetServiceE003Micro.mapInverterCircuit(form,(PermitConduitConductorSectionEntity)scenario.get(i).get(0),(PermitEntity)scenario.get(i).get(1),(int)scenario.get(i).get(2),(EditUserInformations)scenario.get(i).get(3),"",0);
//		}	
//	}
//	
//	@Test
//	public void testmapconduitMaterialandSize() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(1);
//		scenario.get(1).add("JUNCTION BOX-");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(1);
//		scenario.get(2).add("AC COMBINER/LOAD CENTER-");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(1);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(1);
//		scenario.get(3).add("AC DISCONNECT-");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(1);
//		scenario.get(4).add("PRODUCTION METER-");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(1);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(1);
//		scenario.get(5).add("SUB PANEL-");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(1);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.mapconduitMaterialandSize(form, (int) scenario.get(i).get(0),
//					(String) scenario.get(i).get(1), (PermitConduitConductorSectionEntity) scenario.get(i).get(2),
//					(EditUserInformations) scenario.get(i).get(3), (PermitEntity) scenario.get(i).get(4),
//					(PermitArrayEntityResultSecond) scenario.get(i).get(5),
//					(PermitProjectSiteInfoEntityTwo) scenario.get(i).get(6), (int) scenario.get(i).get(7),
//					(List<Integer>) scenario.get(i).get(8), (List<String>) scenario.get(i).get(9),
//					(List<String>) scenario.get(i).get(10), (String) scenario.get(i).get(11), 0);
//		}
//	}
//	
//	@Test
//	public void testmapCircuitsafterRevision() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("INVERTER-");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("JUNCTION BOX-");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("AC COMBINER/LOAD CENTER-");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add("AC DISCONNECT-");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add("PRODUCTION METER-");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add("SUB PANEL-");
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add("SUB PANEL-");
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo= new PermitProjectSiteInfoEntityTwo();
//		scenario.get(7).add(permitProjectSiteInfo);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.mapCircuitsafterRevision(new ArrayList<String>(), form, (String) scenario.get(i).get(0), 1, (ConduitConductorCircuitEntity) scenario.get(i).get(1), null, (PermitEntity) scenario.get(i).get(3), (EditUserInformations) scenario.get(i).get(4), 1, permitProjectSiteInfo);
//		}
//	}
//	
//	@Test
//	public void testmapBackFeedOcpd() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		List<Integer> stringQTYE003 = new ArrayList<Integer>();
//		scenario.get(1).add(stringQTYE003);
//		scenario.get(1).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(1).add(permitProjectSiteInfo);
//		scenario.add(new ArrayList<Object>());
//		List<Integer> stringQTYE0032 = new ArrayList<Integer>();
//		stringQTYE0032.add(2);
//		scenario.get(2).add(stringQTYE0032);
//		scenario.get(2).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo2.setSolarLocation("Back-fed Breaker");
//		scenario.get(2).add(permitProjectSiteInfo2);
//		
//		scenario.add(new ArrayList<Object>());
//		List<Integer> stringQTYE0033 = new ArrayList<Integer>();
//		stringQTYE0033.add(3);
//		scenario.get(3).add(stringQTYE0033);
//		List<String> stringStringE003 = new ArrayList<String>();
//		scenario.get(3).add(stringStringE003);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo3.setSolarLocation("Back-fed Breaker");
//		permitProjectSiteInfo3.setCombiningACCircuits("A Proposed AC Combiner Panel (Solar Load Center) MOUNTED AT THE MAIN SERVICE PANEL");
//		scenario.get(3).add(permitProjectSiteInfo3);
//		
//		scenario.add(new ArrayList<Object>());
//		List<Integer> stringQTYE0034 = new ArrayList<Integer>();
//		stringQTYE0034.add(3);
//		scenario.get(4).add(stringQTYE0034);
//		List<String> stringStringE0034 = new ArrayList<String>();
//		scenario.get(4).add(stringStringE0034);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo4.setSolarLocation("Back-fed Breaker");
//		permitProjectSiteInfo4.setCombiningACCircuits("A Proposed AC Combiner Panel (Solar Load Center) MOUNTED AT THE MAIN SERVICE PANEL");
//		permitProjectSiteInfo4.setSolarInterconnection("Other");
//		scenario.get(4).add(permitProjectSiteInfo4);
//		
//		scenario.add(new ArrayList<Object>());
//		List<Integer> stringQTYE0035 = new ArrayList<Integer>();
//		stringQTYE0035.add(3);
//		scenario.get(5).add(stringQTYE0035);
//		List<String> stringStringE0035 = new ArrayList<String>();
//		scenario.get(5).add(stringStringE0035);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo5.setSolarLocation("Back-fed Breaker");
//		permitProjectSiteInfo5.setCombiningACCircuits("A Proposed Main or Sub Panel with More Than One Back-Fed Breaker");
//		scenario.get(5).add(permitProjectSiteInfo5);
//		
//		scenario.add(new ArrayList<Object>());
//		List<Integer> stringQTYE0036 = new ArrayList<Integer>();
//		stringQTYE0036.add(3);
//		scenario.get(6).add(stringQTYE0036);
//		List<String> stringStringE0036 = new ArrayList<String>();
//		scenario.get(6).add(stringStringE0036);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo6 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo6.setSolarLocation("Back-fed Breaker");
//		permitProjectSiteInfo6.setCombiningACCircuits("A Proposed Main or Sub Panel with More Than One Back-Fed Breaker");
//		permitProjectSiteInfo6.setSolarInterconnection("Other");
//		scenario.get(6).add(permitProjectSiteInfo6);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//		  plansetServiceE003Micro.mapBackFeedOcpd(form,(List<Integer>)scenario.get(i).get(0),(List<String>)scenario.get(i).get(1),(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(2),0);
//		}	
//	}
//	
//	
//	@Test
//	public void testgetACAmpacityCorrectionB2aMultipleString() {
//		ArrayList<String> scenario = new ArrayList<String>();
//		scenario.add(null);
//		scenario.add("xx");
//		scenario.add("");
//		scenario.add("350,11");
//		scenario.add("350.11");
//		scenario.add("33");
//		scenario.add("10000000");
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.getACAmpacityCorrectionB2aMultipleString(scenario.get(i));
//		}	
//	}
//	
//	@Test
//	public void testgetACAmpacityCorrectionB2aMultipleMicroString() {
//		ArrayList<String> scenario = new ArrayList<String>();
//		scenario.add(null);
//		scenario.add("xx");
//		scenario.add("350.11");
//		scenario.add("33");
//		scenario.add("10000000");
//		scenario.add("350,11");
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.getACAmpacityCorrectionB2aMultipleMicro(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetACAmpacityCorrectionB2aMultipleMicroDouble() {
//		List<Double> scenario = Arrays.asList(24.0);
//		for(int i = 0; i < scenario.size(); i++) {
//			 String ampacityCorrection =plansetServiceE003Micro.getACAmpacityCorrectionB2aMultipleMicro(scenario.get(i));
//		
//		}	
//	}
//	
//	@Test
//	public void testgetDCAmpacityCorrectionB3aString() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit1= new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit2= new PermitConduitConductorSectionEntity();
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add("PV MODULE");
//		scenario.get(2).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit3= new PermitConduitConductorSectionEntity();
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add("DC/DC CONVERTER");
//		scenario.get(3).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit4= new PermitConduitConductorSectionEntity();
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add("JUNCTION BOX");
//		scenario.get(4).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit5= new PermitConduitConductorSectionEntity();
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add("DC COMBINER");
//		scenario.get(5).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit6= new PermitConduitConductorSectionEntity();
//		scenario.get(6).add(circuit6);
//		scenario.get(6).add("DC DISCONNECT");
//		scenario.get(6).add(null);
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.getDCAmpacityCorrectionB3aString((PermitConduitConductorSectionEntity)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//		}
//	}
//	
//	@Test
//	public void testgetDCAmpacityCorrectionB3aMicro() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit1= new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit2= new PermitConduitConductorSectionEntity();
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add("PV MODULE");
//		scenario.get(2).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit3= new PermitConduitConductorSectionEntity();
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add("DC/DC CONVERTER");
//		scenario.get(3).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit4= new PermitConduitConductorSectionEntity();
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add("JUNCTION BOX");
//		scenario.get(4).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit5= new PermitConduitConductorSectionEntity();
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add("DC COMBINER");
//		scenario.get(5).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit6= new PermitConduitConductorSectionEntity();
//		scenario.get(6).add(circuit6);
//		scenario.get(6).add("DC DISCONNECT");
//		scenario.get(6).add(null);
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.getDCAmpacityCorrectionB3aMicro((PermitConduitConductorSectionEntity)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//		}
//	}
//	
//	@Test
//	public void testgetCorrectionB3aString() {
//		ArrayList<String> scenario = new ArrayList<String>();
//		scenario.add(null);
//		scenario.add("xx");
//		scenario.add("350.11");
//		scenario.add("33");
//		scenario.add("10000000");
//		scenario.add("350,11");
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.getCorrectionB3aString(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetCorrectionB3aMicro() {
//		ArrayList<String> scenario = new ArrayList<String>();
//		scenario.add(null);
//		scenario.add("xx");
//		scenario.add("350.11");
//		scenario.add("33");
//		scenario.add("10000000");
//		scenario.add("350,11");
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.getCorrectionB3aMicro(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetDCAmpacityCorrectionB2aMultiple() {
//		ArrayList<String> scenario = new ArrayList<String>();
//		scenario.add(null);
//		scenario.add("xx");
//		scenario.add("");
//		scenario.add("350,11");
//		scenario.add("350.11");
//		scenario.add("33");
//		scenario.add("10000000");
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.getDCAmpacityCorrectionB2aMultiple(scenario.get(i));
//		}	
//	}
//	
//	@Test
//	public void testgetDCAmpacityCorrectionB2aString() {
//		ArrayList<String> scenario = new ArrayList<String>();
//		scenario.add(null);
//		scenario.add("xx");
//		scenario.add("");
//		scenario.add("350,11");
//		scenario.add("350.11");
//		scenario.add("33");
//		scenario.add("10000000");
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.getDCAmpacityCorrectionB2aString(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetDCAmpacityCorrectionB2aMicro() {
//		List<Double> scenario = Arrays.asList(24.0);
//		for(int i = 0; i < scenario.size(); i++) {
//			 String ampacityCorrection =plansetServiceE003Micro.getDCAmpacityCorrectionB2aMicro(scenario.get(i));
//		
//		}
//	}
//	
//	@Test
//	public void testgetACAmpacityCorrectionB2aMicroDouble() {
//		List<Double> scenario = Arrays.asList(24.0);
//		for(int i = 0; i < scenario.size(); i++) {
//			String ampacityCorrection =plansetServiceE003Micro.getACAmpacityCorrectionB2aMicro(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetACAmpacityCorrectionB2aMicroString() {
//		ArrayList<String> scenario = new ArrayList<String>();
//		scenario.add(null);
//		scenario.add("xx");
//		scenario.add("350.11");
//		scenario.add("33");
//		scenario.add("10000000");
//		scenario.add("350,11");
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003Micro.getACAmpacityCorrectionB2aMicro(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetConduitSizeEMT() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("/ee");
//		scenario.get(1).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("#3 AWG");
//		scenario.get(2).add(5);
//		for(int i = 0; i < scenario.size(); i++) {
//		plansetServiceE003Micro.getConduitSizeEMT((String)scenario.get(i).get(0),(int)scenario.get(i).get(1));
//		}
//	}
//	
//	@Test
//	public void testgetConduitSizePVC() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(1);
//		for(int i = 0; i < scenario.size(); i++) {
//		plansetServiceE003Micro.getConduitSizePVC((String)scenario.get(i).get(0),(Integer)scenario.get(i).get(1));
//	    }
//	}
//	
//	@Test
//	public void testconductorSizeIs2OrSmaller() {
//		List<String> conduitSize = Arrays.asList(null,"","EXIST","#14 AWG","#12 AWG","#10 AWG","#8 AWG","#6 AWG","#4 AWG","#3 AWG","#2 AWG","900 kc");
//		List<Boolean> expectedresult = Arrays.asList(false,false,false,true,true,true,true,true,true,true,true,false);
//		for(int i = 0; i < conduitSize.size(); i++) {
//			Boolean result = plansetServiceE003Micro.conductorSizeIs2OrSmaller(conduitSize.get(i));
//			}
//		
//	}
//	
//	@Test
//	public void testgetGroundSize() {
//		List<String> conduitSize = Arrays.asList(null,"","EXIST","#1 AWG","#1/0 AWG","#2/0 AWG","#3/0 AWG","#4/0 AWG","250 kc","300 kc","350 kc","400 kc","500 kc","600 kc","700 kc","750 kc","800 kc","900 kc","1000 kc","1100 kc","Other");
//		List<String> expectedresult = Arrays.asList("","","EXIST","#6 AWG","#6 AWG","#4 AWG","#4 AWG","#2 AWG","#2 AWG","#2 AWG","#2 AWG","#1/0 AWG","#1/0 AWG","#1/0 AWG","#2/0 AWG","#2/0 AWG","#2/0 AWG","#2/0 AWG","#2/0 AWG","#2/0 AWG","#3/0 AWG");
//		for(int i = 0; i < conduitSize.size(); i++) {
//		String result = plansetServiceE003Micro.getGroundSize(conduitSize.get(i));
//		}
//	}
//	
//	@Test
//	public void testGetGroundSizeValue() {
//
//		List<String> conduitSize = Arrays.asList("6 inch","EXIST",null);
//		List<String> tradeSize = Arrays.asList("#14 AWG","#12 AWG","#10 AWG","#8 AWG","#6 AWG","#4 AWG","#3 AWG","#2 AWG","#1 AWG","#1/0 AWG", "#2/0 AWG", "#3/0 AWG","#4/0 AWG","250 kc","300 kc","350 kc","400 kc","500 kc","600 kc","700 kc","750 kc","800 kc","900 kc","1000 kc","EXIST","");
//		List<String> MinimumACGroundCon = Arrays.asList("#12 AWG","#10 AWG","#8 AWG","#6 AWG","Other",null);
//		List<String> MinimumACGroundConOther = Arrays.asList("Other Value","", null);
//		String[][][][] arrayvoorbeeld = new String[3][26][6][4];
//		
//		for(int i = 0; i < conduitSize.size(); i++) {
//			for(int j = 0; j < tradeSize.size(); j++) {
//				for(int k = 0; k < MinimumACGroundCon.size(); k++) {
//					for(int m = 0; m < MinimumACGroundConOther.size(); m++) {
//						if (i == 1) {
//							
//							arrayvoorbeeld[i][j][k][m]="EXIST";
//							
//						}else if (i == 0 || i == 2) {
//							
//							if (j < 8) {
//								
//								if (k < 4) {
//									
//									arrayvoorbeeld[i][j][k][m]=MinimumACGroundCon.get(k);
//									
//								} else if (k == 4) {
//									
//									arrayvoorbeeld[i][j][k][m]=MinimumACGroundConOther.get(m);
//									
//								}  else if (k == 5) {
//									
//									arrayvoorbeeld[i][j][k][m]= "#8 AWG";
//								}
//								
//							} else if (j == 8 || j == 9) {
//								
//								arrayvoorbeeld[i][j][k][m]="#6 AWG";
//								
//							} else if (j == 10 || j == 11) {
//								
//								arrayvoorbeeld[i][j][k][m]="#4 AWG";
//								
//							} else if (j >= 12 && j < 16) {
//								
//								arrayvoorbeeld[i][j][k][m]="#2 AWG";
//								
//							} else if (j >= 16 && j < 19) {
//								
//								arrayvoorbeeld[i][j][k][m]="#1/0 AWG";
//								
//							} else if (j >= 19 && j < 24) {
//								
//								arrayvoorbeeld[i][j][k][m]="#2/0 AWG";
//								
//							} else if (j == 24) {
//								
//								arrayvoorbeeld[i][j][k][m]="EXIST";
//								
//							} else if (j == 25) {
//								
//								arrayvoorbeeld[i][j][k][m]="";
//								
//							}
//						}
//						
//						String result = plansetServiceE003Micro.getGroundSizeValue(conduitSize.get(i),tradeSize.get(j),MinimumACGroundCon.get(k),MinimumACGroundConOther.get(m));
//					}
//				}
//			}
//		}
//
//				
//	}
//	
//}
