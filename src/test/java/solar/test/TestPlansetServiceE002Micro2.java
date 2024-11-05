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
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.Cmodulev2;
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.Inverters;
//import com.PlayGroundAdv.Solar.Entity.NEC_310_16_B_16;
//import com.PlayGroundAdv.Solar.Entity.PermitConduitConductorSectionEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitLayoutEntity;
//import com.PlayGroundAdv.Solar.Entity.UserLicSectionsEntity;
//import com.PlayGroundAdv.Solar.model.EditUserInformations;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
//import com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
//import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.PermitService;
//import com.PlayGroundAdv.Solar.Services.PermitServiceEdit;
//import com.PlayGroundAdv.Solar.Services.PlansetServiceE002Micro2;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//public class TestPlansetServiceE002Micro2 {
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
//    PlansetServiceE002Micro2 plansetServiceE002Micro2 = new PlansetServiceE002Micro2();
//    
//    PdfReader reader = null;
//   	File fileRe = null;
//   	PdfStamper stamper = null;
//   	AcroFields form = null;
//   	PdfReader readerOrigin= null;
//   	
//     @Before
//     public void setupMock() {
//    	plansetServiceE002Micro2 = new PlansetServiceE002Micro2();
//        MockitoAnnotations.initMocks(this);
// 		
// 		try {
// 		reader = new PdfReader(Constants.rapportPlansetFolderUrl +"NEC-PDF/" +"PDF-E002-MICRO.pdf" );
// 		fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E002-MICRO.pdf"+".pdf");
// 		stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
// 		form = stamper.getAcroFields();
// 		readerOrigin = new PdfReader( Constants.rapportPlansetFolderUrl +"NEC-PDF/" + "PDF-E002-MICRO.pdf" );
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
//     
//	@Test
//	public void testgetpourcentageAverageHigh() {
//		ArrayList<PermtiWeatherEntityResult> scenario = new ArrayList<PermtiWeatherEntityResult>();
//		scenario.add(null);
//		PermtiWeatherEntityResult scenario1 = new PermtiWeatherEntityResult();
//		scenario.add(scenario1);
//		PermtiWeatherEntityResult scenario2 = new PermtiWeatherEntityResult();
//		scenario2.setQuatrePourCentAverageHigh("4c");
//		scenario.add(scenario2);
//		PermtiWeatherEntityResult scenario3 = new PermtiWeatherEntityResult();
//		scenario3.setQuatrePourCentAverageHigh("Other");
//		scenario3.setQuatrePourCentAvHighOther("4C");
//		scenario.add(scenario3);
//		PermtiWeatherEntityResult scenario4 = new PermtiWeatherEntityResult();
//		scenario4.setQuatrePourCentAverageHigh("Other");
//		scenario.add(scenario4);
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002Micro2.getpourcentageAverageHigh(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetDeuxpourcentageAverageHigh() {
//		ArrayList<PermtiWeatherEntityResult> scenario = new ArrayList<PermtiWeatherEntityResult>();
//		scenario.add(null);
//		PermtiWeatherEntityResult scenario1 = new PermtiWeatherEntityResult();
//		scenario.add(scenario1);
//		PermtiWeatherEntityResult scenario2 = new PermtiWeatherEntityResult();
//		scenario2.setDeuxPourCentAverageHigh("4c");
//		scenario.add(scenario2);
//		PermtiWeatherEntityResult scenario3 = new PermtiWeatherEntityResult();
//		scenario3.setDeuxPourCentAverageHigh("Other");
//		scenario3.setDeuxPourCentAverageHighOther("4C");
//		scenario.add(scenario3);
//		PermtiWeatherEntityResult scenario4 = new PermtiWeatherEntityResult();
//		scenario4.setDeuxPourCentAverageHigh("Other");
//		scenario.add(scenario4);
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002Micro2.getDeuxpourcentageAverageHigh(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetiScRef() {
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
//			String result = plansetServiceE002Micro2.getiScRef(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetiacMaxMicro() {
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
//		 plansetServiceE002Micro2.getiacMaxMicro(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetiacMaxMicroCalcul() {
//		ArrayList<String> scenario = new ArrayList<String>();
//	    scenario.add(null);
//	    scenario.add("1,5");
//	    scenario.add("12");
//	    double [] expectedresult = {(int)1.00,1.00,12.0};
//		for(int i = 0; i < scenario.size(); i++) {
//			double result = plansetServiceE002Micro2.getiacMaxMicroCalcul(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testcircuitEnvironment() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult1 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult1.setRoofMounted(true);
//		scenario.get(1).add(permitArraysEntityResult1);
//		PermitLayoutEntity permitLayoutEntity1 =  new PermitLayoutEntity();
//		scenario.get(1).add(permitLayoutEntity1);
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult2.setRoofMounted(true);
//		scenario.get(2).add(permitArraysEntityResult1);
//		PermitLayoutEntity permitLayoutEntity2=  new PermitLayoutEntity();
//		permitLayoutEntity2.setConduitMounted(true);
//		scenario.get(2).add(permitLayoutEntity2);
//		scenario.get(2).add(null);
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002Micro2.circuitEnvironment((PermitArrayEntityResultSecond)scenario.get(i).get(0),(PermitLayoutEntity)scenario.get(i).get(1),(Integer)scenario.get(i).get(2));
//		}
//	}
//	
//	@Test
//	public void testtitleBlockMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite1 = new PermitHomeSiteEntityResult();
//		scenario.get(1).add(permitHomeSite1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//		permitHomeSite2.setSiteAddress("siteAddress");
//		permitHomeSite2.setCity("city");
//		permitHomeSite2.setState("NY");
//		scenario.get(2).add(permitHomeSite2);
//		PermitEntity permitEntity2 = new PermitEntity();
//		scenario.get(2).add(permitEntity2);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//		permitHomeSite3.setSiteAddress("siteAddress");
//		permitHomeSite3.setCity("city");
//		permitHomeSite3.setState("NY");
//		scenario.get(3).add(permitHomeSite3);
//		PermitEntity permitEntity3 = new PermitEntity();
//		scenario.get(3).add(permitEntity3);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//		permitHomeSite4.setSiteAddress("siteAddress");
//		permitHomeSite4.setCity("city");
//		permitHomeSite4.setState("NY");
//		scenario.get(4).add(permitHomeSite4);
//		PermitEntity permitEntity4 = new PermitEntity();
//		scenario.get(4).add(permitEntity4);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//		permitHomeSite5.setSiteAddress("siteAddress");
//		permitHomeSite5.setCity("city");
//		permitHomeSite5.setState("NY");
//		scenario.get(5).add(permitHomeSite5);
//		PermitEntity permitEntity5 = new PermitEntity();
//		scenario.get(5).add(permitEntity5);
//		scenario.get(5).add(null);
//		for(int i = 0; i < scenario.size(); i++) {
//			 plansetServiceE002Micro2.titleBlockMapping((PermitHomeSiteEntityResult)scenario.get(i).get(0),(PermitEntity)scenario.get(i).get(1),form,(String)scenario.get(i).get(2),0);
//		}
//	}
//	
//	@Test
//	public void testcontractorInfoMapping() {
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
//			 plansetServiceE002Micro2.contractorInfoMapping((EditUserInformations)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),0);
//		}
//	}
//	
//	@Test
//	public void testgetdc1beforeRevision() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0.00);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult1 = new PermitArrayEntityResultSecond();
//		scenario.get(1).add(permitArraysEntityResult1);
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		PermitEntity permitEntity1 = new PermitEntity();
//		scenario.get(1).add(permitEntity1);
//		scenario.get(1).add(0.00);
//		scenario.get(1).add("2.1");
//		
//		
//		 String [] expectedresult = {"","63,0"};
//		for(int i = 0; i < scenario.size(); i++) {
//			String result =  plansetServiceE002Micro2.getdc1beforeRevision(form,(PermitArrayEntityResultSecond)scenario.get(i).get(0),(PermitConduitConductorSectionEntity)scenario.get(i).get(1),(PermitEntity)scenario.get(i).get(2),(double)scenario.get(i).get(3),(String)scenario.get(i).get(4),0);
//		}
//	}
//	
//	@Test
//	public void testgetdc1afterRevision() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//		circuit2.setConductorSize("Other");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//		circuit3.setConductorSize("Per Manufacturer");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit4 = new PermitConduitConductorSectionEntity();
//		circuit4.setConductorSize("Per Manufacturer");
//		circuit4.setConductorQty("Other");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		scenario.get(4).add("test");
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit5 = new PermitConduitConductorSectionEntity();
//		circuit5.setConductorSize("Per Manufacturer");
//		circuit5.setConductorQty("3");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(null);
//		scenario.get(5).add("1");
//		scenario.get(5).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit6 = new PermitConduitConductorSectionEntity();
//		circuit6.setConductorSize("test");
//		circuit6.setConductorQty("3");
//		scenario.get(6).add(circuit6);
//		scenario.get(6).add(null);
//		scenario.get(6).add("1");
//		NEC_310_16_B_16 nEC_310_16_B_16 = new NEC_310_16_B_16();
//		scenario.get(6).add(nEC_310_16_B_16);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit7 = new PermitConduitConductorSectionEntity();
//		circuit7.setConductorSize("test");
//		circuit7.setConductorQty("3");
//		scenario.get(7).add(circuit7);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		NEC_310_16_B_16 nEC_310_16_B_167 = new NEC_310_16_B_16();
//		nEC_310_16_B_167.setNinetyInsulation(15);
//		scenario.get(7).add(nEC_310_16_B_167);
//		
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u" + " from NEC_310_16_B_16 u " + " where u.tradeSze = :p1 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		when(mockedQuery.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery);
//			 
//		 String [] expectedresult = {"","","","","","30,0","",""};
//		for(int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getSingleResult()).thenReturn((NEC_310_16_B_16)scenario.get(i).get(3));
//			String result =  plansetServiceE002Micro2.getdc1afterRevision(form,(PermitConduitConductorSectionEntity)scenario.get(i).get(0),(PermitEntity)scenario.get(i).get(1),(String)scenario.get(i).get(2),0);
//		}
//	}
//	
//	@Test
//	public void testgetDC1Mapping() {
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
//		scenario.get(1).add(null);
//		PermitConduitConductorSectionEntity circuit = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		AuthentificationEntity userConnectedEntity = new AuthentificationEntity();
//		scenario.get(1).add(userConnectedEntity);
//		PermitArrayEntityResultSecond permitArraysEntityResult1 = new PermitArrayEntityResultSecond();
//		scenario.get(1).add(permitArraysEntityResult1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002Micro2.getDC1Mapping(form,(String)scenario.get(i).get(0),(PermitConduitConductorSectionEntity)scenario.get(i).get(1),(PermitEntity)scenario.get(i).get(2),(PermtiWeatherEntityResult)scenario.get(i).get(3),(AuthentificationEntity)scenario.get(i).get(4),(PermitArrayEntityResultSecond)scenario.get(i).get(5),(Integer)scenario.get(i).get(6),(String)scenario.get(i).get(7),0);
//		}
//	}
//	
//	@Test
//	public void testgetAcCircuitOriginandDestination() {
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
//		
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("test");
//		scenario.get(1).add(0);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("test-split");
//		scenario.get(2).add(2);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			 plansetServiceE002Micro2.getAcCircuitOriginandDestination(form,(String)scenario.get(i).get(0),(int)scenario.get(i).get(1),componentListQTY,0);
//		}
//	}
//	
//	@Test
//	public void testgetACRequiredAmpacity() {
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo = new PermitProjectSiteInfoEntityTwo ();
//		permitProjectSiteInfo.setIfApplicableSubPanelMainBreakerRating(null);
//		
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo ();
//		permitProjectSiteInfo2.setIfApplicableSubPanelMainBreakerRating("");
//		
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo ();
//		permitProjectSiteInfo2.setIfApplicableSubPanelMainBreakerRating("100");
//		
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(0);
//		scenario.get(0).add(0);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0.00);
//		scenario.get(0).add(null);
//		scenario.get(0).add(permitProjectSiteInfo);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(1);
//		scenario.get(1).add(1);
//		scenario.get(1).add(0);
//		scenario.get(1).add(0);
//		scenario.get(1).add(true);
//		scenario.get(1).add(1.00);
//		scenario.get(1).add("");
//		scenario.get(1).add(permitProjectSiteInfo2);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(1);
//		scenario.get(2).add(1);
//		scenario.get(2).add(0);
//		scenario.get(2).add(0);
//		scenario.get(2).add(true);
//		scenario.get(2).add(1.00);
//		scenario.get(2).add("X-X");
//		scenario.get(2).add(permitProjectSiteInfo3);
//		 String [] expectedresult = {"","1,2","1,2"};
//		for(int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002Micro2.getACRequiredAmpacity(form, (int) scenario.get(i).get(0),
//					(int) scenario.get(i).get(1), (int) scenario.get(i).get(2), (Integer) scenario.get(i).get(3),
//					(Boolean) scenario.get(i).get(4), (double) scenario.get(i).get(5), 0, (String) scenario.get(i).get(6), (PermitProjectSiteInfoEntityTwo) scenario.get(i).get(7));
//		}
//		
//	}
//	
//	@Test
//	public void testaverageHighMapping() {
//		ArrayList<PermtiWeatherEntityResult> scenario = new ArrayList<PermtiWeatherEntityResult>();
//		scenario.add(null);
//		PermtiWeatherEntityResult scenario1 = new PermtiWeatherEntityResult();
//		scenario.add(scenario1);
//		PermtiWeatherEntityResult scenario2 = new PermtiWeatherEntityResult();
//		scenario2.setQuatrePourCentAverageHigh("test");
//		scenario.add(scenario2);
//		PermtiWeatherEntityResult scenario3 = new PermtiWeatherEntityResult();
//		scenario3.setQuatrePourCentAverageHigh("Other");
//		scenario.add(scenario3);
//		PermtiWeatherEntityResult scenario4 = new PermtiWeatherEntityResult();
//		scenario4.setQuatrePourCentAverageHigh("Other");
//		scenario4.setDeuxPourCentAverageHighOther("°");
//		scenario.add(scenario4);
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002Micro2.averageHighMapping(form,scenario.get(i),1,1);
//		}
//	}
//	
//	@Test
//	public void testconductorTemperatureDeratingMapping() {
//		List<String> acCircuitEnvironment = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(acCircuitEnvironment);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(new PermitConduitConductorSectionEntity());
//		scenario.get(0).add(new AuthentificationEntity());
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002Micro2.conductorTemperatureDeratingMapping(form,(String)scenario.get(i).get(0),(int)scenario.get(i).get(1),(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(2),(String)scenario.get(i).get(3),(Integer)scenario.get(i).get(4),(String)scenario.get(i).get(5),(int)scenario.get(i).get(6),(List<String>)scenario.get(i).get(7),(String)scenario.get(i).get(8),(Boolean)scenario.get(i).get(9),(PermitConduitConductorSectionEntity)scenario.get(i).get(10),(AuthentificationEntity)scenario.get(i).get(11),0);
//		}
//	}
//	
//	@Test
//	public void testgetHightAboveRoof() {
//		List<List<String>> scenario = new ArrayList<List<String>>();
//		
//		scenario.add(new ArrayList<String>());
//		scenario.get(0).add(null);
//	    scenario.get(0).add(null);
//	    scenario.get(0).add(null);
//	    
//	    scenario.add(new ArrayList<String>());
//		scenario.get(1).add("OPEN AIR");
//	    scenario.get(1).add(null);
//	    scenario.get(1).add(null);
//	    
//	    scenario.add(new ArrayList<String>());
//		scenario.get(2).add("ATTIC");
//	    scenario.get(2).add(null);
//	    scenario.get(2).add(null);
//	    
//	    scenario.add(new ArrayList<String>());
//		scenario.get(3).add("ROOFTOP");
//	    scenario.get(3).add(null);
//	    scenario.get(3).add(null);
//	    
//	    scenario.add(new ArrayList<String>());
//		scenario.get(4).add("ROOFTOP");
//	    scenario.get(4).add("Trunk Cable");
//	    scenario.get(4).add(null);
//	    
//	    
//	    scenario.add(new ArrayList<String>());
//		scenario.get(5).add("ROOFTOP");
//	    scenario.get(5).add("THWN-2");
//	    scenario.get(5).add("xx");
//	    
//	    String [] expectedresult = {"N/A","N/A","IN ATTIC","N/A","0.5\" – 3.5\"","xx"};
//		for(int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002Micro2.getHightAboveRoof(scenario.get(i).get(0),scenario.get(i).get(1),scenario.get(i).get(2));
//		}
//	}
//	
//	@Test
//	public void testgetTempAdder() {
//		ArrayList<String> scenario = new ArrayList<String>();
//	    scenario.add(null);
//	    scenario.add("");
//	    scenario.add("0.5\" – 3.5\"");
//	    scenario.add("3.5\" – 12\"");
//	    scenario.add("12\" – 36\"");
//	    scenario.add("IN ATTIC");
//	    scenario.add("0.5");
//	    String [] expectedresult = {"0","0","22","17","14","22","0"};
//		for(int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002Micro2.getTempAdder(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetOperatingTemperature() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//	    scenario.get(0).add(null);
//	    scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//	    scenario.get(1).add("-22");
//	    scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(0);
//	    scenario.get(2).add("3.5");
//	    scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(0);
//	    scenario.get(3).add("2,5");
//	    scenario.add(new ArrayList<Object>());
//	  	scenario.get(4).add(0);
//	  	scenario.get(4).add("22");
//	    String [] expectedresult = {"0","0","0","0","22"};
//		for(int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002Micro2.getOperatingTemperature((Integer)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//		}
//	}
//	
//	@Test
//	public void testacCircuitbeforeRevision() {
//		List<String> acTradeSize = new ArrayList<String>();
//		acTradeSize.add("#10 AWG");
//		acTradeSize.add("#12 AWG");
//		List<Integer> acNumberOfConductors = new ArrayList<Integer> ();
//		List<String> acCircuitEnvironment= new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("AC COMBINER/LOAD CENTER-INVERTER-");
//		scenario.get(1).add(1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("AC COMBINER/LOAD CENTER-INVERTER-");
//		scenario.get(2).add(2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		for(int i = 0; i < scenario.size(); i++) {
////			plansetServiceE002Micro2.acCircuitbeforeRevision(form,(String)scenario.get(i).get(0),(int)scenario.get(i).get(1),
////					(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(2),(int)scenario.get(i).get(3),
////					(String)scenario.get(i).get(4) , (PermitConduitConductorSectionEntity)scenario.get(i).get(5) , (PermitEntity)scenario.get(i).get(6)  ,
////					acTradeSize , acNumberOfConductors, acCircuitEnvironment,(String )scenario.get(i).get(7) , (ACCombinerSLC)scenario.get(i).get(8) , (ACCombinerSLC)scenario.get(i).get(9) ,0);
//		}
//	}
//	
//	@Test
//	public void testmapInverterRevision() {
//		
//		List<String> acTradeSize = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//		circuit2.setConductorSizeSix("Other");
//		circuit2.setConductorQtySix("Other");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(null);
//		scenario.get(3).add(0);
//		PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//		circuit3.setConductorSizeSix("Per Manufacturer");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(null);
//		scenario.get(4).add(0);
//		PermitConduitConductorSectionEntity circuit4 = new PermitConduitConductorSectionEntity();
//		circuit4.setConductorSizeSix("Per Manufacturer");
//		circuit4.setConductorQtySix("Other");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(null);
//		scenario.get(5).add(0);
//		PermitConduitConductorSectionEntity circuit5 = new PermitConduitConductorSectionEntity();
//		circuit5.setConductorSizeSix("Per Manufacturer");
//		circuit5.setConductorQtySix("3");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add(null);
//		scenario.get(6).add(0);
//		PermitConduitConductorSectionEntity circuit6 = new PermitConduitConductorSectionEntity();
//		circuit6.setConductorSizeSix("test");
//		circuit6.setConductorQtySix("3");
//		scenario.get(6).add(circuit6);
//		NEC_310_16_B_16 nEC_310_16_B_16 = new NEC_310_16_B_16();
//		scenario.get(6).add(nEC_310_16_B_16);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add(null);
//		scenario.get(7).add(0);
//		PermitConduitConductorSectionEntity circuit7 = new PermitConduitConductorSectionEntity();
//		circuit7.setConductorSizeSix("test");
//		circuit7.setConductorQtySix("3");
//		scenario.get(7).add(circuit7);
//		NEC_310_16_B_16 nEC_310_16_B_167 = new NEC_310_16_B_16();
//		nEC_310_16_B_167.setNinetyInsulation(15);
//		scenario.get(7).add(nEC_310_16_B_167);
//		
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u"
//				+ " from NEC_310_16_B_16 u "
//				+ " where u.tradeSze = :p1 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		when(mockedQuery.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery);
//		for(int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getSingleResult()).thenReturn((NEC_310_16_B_16)scenario.get(i).get(3));
//			plansetServiceE002Micro2.mapInverterRevision(form,(String)scenario.get(i).get(0),(int)scenario.get(i).get(1),(PermitConduitConductorSectionEntity)scenario.get(i).get(2),acTradeSize,0);
//		}
//	}
//	
//	@Test
//	public void testmapAcCombinerRevision() {
//		List<String> acTradeSize = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//		circuit2.setConductorSizeEight("Other");
//		circuit2.setConductorQtyEight("Other");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(null);
//		scenario.get(3).add(0);
//		PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//		circuit3.setConductorSizeEight("Per Manufacturer");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(null);
//		scenario.get(4).add(0);
//		PermitConduitConductorSectionEntity circuit4 = new PermitConduitConductorSectionEntity();
//		circuit4.setConductorSizeEight("Per Manufacturer");
//		circuit4.setConductorQtyEight("Other");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(null);
//		scenario.get(5).add(0);
//		PermitConduitConductorSectionEntity circuit5 = new PermitConduitConductorSectionEntity();
//		circuit5.setConductorSizeEight("Per Manufacturer");
//		circuit5.setConductorQtyEight("3");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add(null);
//		scenario.get(6).add(0);
//		PermitConduitConductorSectionEntity circuit6 = new PermitConduitConductorSectionEntity();
//		circuit6.setConductorSizeEight("test");
//		circuit6.setConductorQtyEight("3");
//		scenario.get(6).add(circuit6);
//		NEC_310_16_B_16 nEC_310_16_B_16 = new NEC_310_16_B_16();
//		scenario.get(6).add(nEC_310_16_B_16);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add(null);
//		scenario.get(7).add(0);
//		PermitConduitConductorSectionEntity circuit7 = new PermitConduitConductorSectionEntity();
//		circuit7.setConductorSizeEight("test");
//		circuit7.setConductorQtyEight("3");
//		scenario.get(7).add(circuit7);
//		NEC_310_16_B_16 nEC_310_16_B_167 = new NEC_310_16_B_16();
//		nEC_310_16_B_167.setNinetyInsulation(15);
//		scenario.get(7).add(nEC_310_16_B_167);
//		
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u"
//				+ " from NEC_310_16_B_16 u "
//				+ " where u.tradeSze = :p1 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		when(mockedQuery.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery);
//		for(int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getSingleResult()).thenReturn((NEC_310_16_B_16)scenario.get(i).get(3));
//			plansetServiceE002Micro2.mapAcCombinerRevision(form,(String)scenario.get(i).get(0),(int)scenario.get(i).get(1),(PermitConduitConductorSectionEntity)scenario.get(i).get(2),acTradeSize,0);
//		}
//	}
//	
//	@Test
//	public void testmapJunctionRevision() {
//		List<String> acTradeSize = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//		circuit2.setConductorSizeSeven("Other");
//		circuit2.setConductorQtySeven("Other");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(null);
//		scenario.get(3).add(0);
//		PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//		circuit3.setConductorSizeSeven("Per Manufacturer");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(null);
//		scenario.get(4).add(0);
//		PermitConduitConductorSectionEntity circuit4 = new PermitConduitConductorSectionEntity();
//		circuit4.setConductorSizeSeven("Per Manufacturer");
//		circuit4.setConductorQtySeven("Other");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(null);
//		scenario.get(5).add(0);
//		PermitConduitConductorSectionEntity circuit5 = new PermitConduitConductorSectionEntity();
//		circuit5.setConductorSizeSeven("Per Manufacturer");
//		circuit5.setConductorQtySeven("3");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add(null);
//		scenario.get(6).add(0);
//		PermitConduitConductorSectionEntity circuit6 = new PermitConduitConductorSectionEntity();
//		circuit6.setConductorSizeSeven("test");
//		circuit6.setConductorQtySeven("3");
//		scenario.get(6).add(circuit6);
//		NEC_310_16_B_16 nEC_310_16_B_16 = new NEC_310_16_B_16();
//		scenario.get(6).add(nEC_310_16_B_16);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add(null);
//		scenario.get(7).add(0);
//		PermitConduitConductorSectionEntity circuit7 = new PermitConduitConductorSectionEntity();
//		circuit7.setConductorSizeSeven("test");
//		circuit7.setConductorQtySeven("3");
//		scenario.get(7).add(circuit7);
//		NEC_310_16_B_16 nEC_310_16_B_167 = new NEC_310_16_B_16();
//		nEC_310_16_B_167.setNinetyInsulation(15);
//		scenario.get(7).add(nEC_310_16_B_167);
//		
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u"
//				+ " from NEC_310_16_B_16 u "
//				+ " where u.tradeSze = :p1 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		when(mockedQuery.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery);
//		for(int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getSingleResult()).thenReturn((NEC_310_16_B_16)scenario.get(i).get(3));
//			plansetServiceE002Micro2.mapJunctionRevision(form,(String)scenario.get(i).get(0),(int)scenario.get(i).get(1),(PermitConduitConductorSectionEntity)scenario.get(i).get(2),acTradeSize,0);
//		}
//	}
//	
//	@Test
//	public void testmapAcDiscoRevision() {
//		List<String> acTradeSize = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//		circuit2.setConductorSizeNine("Other");
//		circuit2.setConductorQtyNine("Other");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(null);
//		scenario.get(3).add(0);
//		PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//		circuit3.setConductorSizeNine("Per Manufacturer");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(null);
//		scenario.get(4).add(0);
//		PermitConduitConductorSectionEntity circuit4 = new PermitConduitConductorSectionEntity();
//		circuit4.setConductorSizeNine("Per Manufacturer");
//		circuit4.setConductorQtyNine("Other");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(null);
//		scenario.get(5).add(0);
//		PermitConduitConductorSectionEntity circuit5 = new PermitConduitConductorSectionEntity();
//		circuit5.setConductorSizeNine("Per Manufacturer");
//		circuit5.setConductorQtyNine("3");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add(null);
//		scenario.get(6).add(0);
//		PermitConduitConductorSectionEntity circuit6 = new PermitConduitConductorSectionEntity();
//		circuit6.setConductorSizeNine("test");
//		circuit6.setConductorQtyNine("3");
//		scenario.get(6).add(circuit6);
//		NEC_310_16_B_16 nEC_310_16_B_16 = new NEC_310_16_B_16();
//		scenario.get(6).add(nEC_310_16_B_16);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add(null);
//		scenario.get(7).add(0);
//		PermitConduitConductorSectionEntity circuit7 = new PermitConduitConductorSectionEntity();
//		circuit7.setConductorSizeNine("test");
//		circuit7.setConductorQtyNine("3");
//		scenario.get(7).add(circuit7);
//		NEC_310_16_B_16 nEC_310_16_B_167 = new NEC_310_16_B_16();
//		nEC_310_16_B_167.setNinetyInsulation(15);
//		scenario.get(7).add(nEC_310_16_B_167);
//		
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u"
//				+ " from NEC_310_16_B_16 u "
//				+ " where u.tradeSze = :p1 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		when(mockedQuery.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery);
//		for(int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getSingleResult()).thenReturn((NEC_310_16_B_16)scenario.get(i).get(3));
//			plansetServiceE002Micro2.mapAcDiscoRevision(form,(String)scenario.get(i).get(0),(int)scenario.get(i).get(1),(PermitConduitConductorSectionEntity)scenario.get(i).get(2),acTradeSize,0);
//		}
//	}
//	
//	@Test
//	public void testmapAcDiscoTwoRevision() {
//		List<String> acTradeSize = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//		circuit2.setConductorSizeNineTwo("Other");
//		circuit2.setConductorQtyNineTwo("Other");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(null);
//		scenario.get(3).add(0);
//		PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//		circuit3.setConductorSizeNineTwo("Per Manufacturer");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(null);
//		scenario.get(4).add(0);
//		PermitConduitConductorSectionEntity circuit4 = new PermitConduitConductorSectionEntity();
//		circuit4.setConductorSizeNineTwo("Per Manufacturer");
//		circuit4.setConductorQtyNineTwo("Other");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(null);
//		scenario.get(5).add(0);
//		PermitConduitConductorSectionEntity circuit5 = new PermitConduitConductorSectionEntity();
//		circuit5.setConductorSizeNineTwo("Per Manufacturer");
//		circuit5.setConductorQtyNineTwo("3");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add(null);
//		scenario.get(6).add(0);
//		PermitConduitConductorSectionEntity circuit6 = new PermitConduitConductorSectionEntity();
//		circuit6.setConductorSizeNineTwo("test");
//		circuit6.setConductorQtyNineTwo("3");
//		scenario.get(6).add(circuit6);
//		NEC_310_16_B_16 nEC_310_16_B_16 = new NEC_310_16_B_16();
//		scenario.get(6).add(nEC_310_16_B_16);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add(null);
//		scenario.get(7).add(0);
//		PermitConduitConductorSectionEntity circuit7 = new PermitConduitConductorSectionEntity();
//		circuit7.setConductorSizeNineTwo("test");
//		circuit7.setConductorQtyNineTwo("3");
//		scenario.get(7).add(circuit7);
//		NEC_310_16_B_16 nEC_310_16_B_167 = new NEC_310_16_B_16();
//		nEC_310_16_B_167.setNinetyInsulation(15);
//		scenario.get(7).add(nEC_310_16_B_167);
//		
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u"
//				+ " from NEC_310_16_B_16 u "
//				+ " where u.tradeSze = :p1 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		when(mockedQuery.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery);
//		for(int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getSingleResult()).thenReturn((NEC_310_16_B_16)scenario.get(i).get(3));
//			plansetServiceE002Micro2.mapAcDiscoTwoRevision(form,(String)scenario.get(i).get(0),(int)scenario.get(i).get(1),(PermitConduitConductorSectionEntity)scenario.get(i).get(2),acTradeSize,0);
//		}
//	}
//	
//	@Test
//	public void testmapProductionMeterRevision() {
//		List<String> acTradeSize = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//		circuit2.setConductorSizeTen("Other");
//		circuit2.setConductorQtyTen("Other");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(null);
//		scenario.get(3).add(0);
//		PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//		circuit3.setConductorSizeTen("Per Manufacturer");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(null);
//		scenario.get(4).add(0);
//		PermitConduitConductorSectionEntity circuit4 = new PermitConduitConductorSectionEntity();
//		circuit4.setConductorSizeTen("Per Manufacturer");
//		circuit4.setConductorQtyTen("Other");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(null);
//		scenario.get(5).add(0);
//		PermitConduitConductorSectionEntity circuit5 = new PermitConduitConductorSectionEntity();
//		circuit5.setConductorSizeTen("Per Manufacturer");
//		circuit5.setConductorQtyTen("3");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add(null);
//		scenario.get(6).add(0);
//		PermitConduitConductorSectionEntity circuit6 = new PermitConduitConductorSectionEntity();
//		circuit6.setConductorSizeTen("test");
//		circuit6.setConductorQtyTen("3");
//		scenario.get(6).add(circuit6);
//		NEC_310_16_B_16 nEC_310_16_B_16 = new NEC_310_16_B_16();
//		scenario.get(6).add(nEC_310_16_B_16);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add(null);
//		scenario.get(7).add(0);
//		PermitConduitConductorSectionEntity circuit7 = new PermitConduitConductorSectionEntity();
//		circuit7.setConductorSizeTen("test");
//		circuit7.setConductorQtyTen("3");
//		scenario.get(7).add(circuit7);
//		NEC_310_16_B_16 nEC_310_16_B_167 = new NEC_310_16_B_16();
//		nEC_310_16_B_167.setNinetyInsulation(15);
//		scenario.get(7).add(nEC_310_16_B_167);
//		
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u"
//				+ " from NEC_310_16_B_16 u "
//				+ " where u.tradeSze = :p1 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		when(mockedQuery.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery);
//		for(int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getSingleResult()).thenReturn((NEC_310_16_B_16)scenario.get(i).get(3));
//			plansetServiceE002Micro2.mapProductionMeterRevision(form,(String)scenario.get(i).get(0),(int)scenario.get(i).get(1),(PermitConduitConductorSectionEntity)scenario.get(i).get(2),acTradeSize,0);
//		}
//	}
//	
//	@Test
//	public void testmapSubPanelRevision() {
//		List<String> acTradeSize = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//		circuit2.setConductorSizeEleven("Other");
//		circuit2.setConductorQtyEleven("Other");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo = new PermitProjectSiteInfoEntityTwo ();
//		scenario.get(2).add(permitProjectSiteInfo);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(null);
//		scenario.get(3).add(0);
//		PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//		circuit3.setConductorSizeEleven("Per Manufacturer");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(null);
//		scenario.get(4).add(0);
//		PermitConduitConductorSectionEntity circuit4 = new PermitConduitConductorSectionEntity();
//		circuit4.setConductorSizeEleven("Per Manufacturer");
//		circuit4.setConductorQtyEleven("Other");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(null);
//		scenario.get(5).add(0);
//		PermitConduitConductorSectionEntity circuit5 = new PermitConduitConductorSectionEntity();
//		circuit5.setConductorSizeEleven("Per Manufacturer");
//		circuit5.setConductorQtyEleven("3");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add(null);
//		scenario.get(6).add(0);
//		PermitConduitConductorSectionEntity circuit6 = new PermitConduitConductorSectionEntity();
//		circuit6.setConductorSizeEleven("test");
//		circuit6.setConductorQtyEleven("3");
//		scenario.get(6).add(circuit6);
//		NEC_310_16_B_16 nEC_310_16_B_16 = new NEC_310_16_B_16();
//		scenario.get(6).add(nEC_310_16_B_16);
//		scenario.get(6).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add(null);
//		scenario.get(7).add(0);
//		PermitConduitConductorSectionEntity circuit7 = new PermitConduitConductorSectionEntity();
//		circuit7.setConductorSizeEleven("test");
//		circuit7.setConductorQtyEleven("3");
//		scenario.get(7).add(circuit7);
//		NEC_310_16_B_16 nEC_310_16_B_167 = new NEC_310_16_B_16();
//		nEC_310_16_B_167.setNinetyInsulation(15);
//		scenario.get(7).add(nEC_310_16_B_167);
//		scenario.get(7).add(null);
//		
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u"
//				+ " from NEC_310_16_B_16 u "
//				+ " where u.tradeSze = :p1 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		when(mockedQuery.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery);
//		for(int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getSingleResult()).thenReturn((NEC_310_16_B_16)scenario.get(i).get(3));
//			plansetServiceE002Micro2.mapSubPanelRevision(form,(String)scenario.get(i).get(0),(int)scenario.get(i).get(1),(PermitConduitConductorSectionEntity)scenario.get(i).get(2),acTradeSize,0,(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(4));
//		}
//	}
//
//	@Test
//	public void testacCircuitafterRevision() {
//		List<String> acTradeSize = new ArrayList<String>();
//		acTradeSize.add("#10 AWG");
//		acTradeSize.add("#12 AWG");
//		List<Integer> acNumberOfConductors = new ArrayList<Integer> ();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(1);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//		scenario.get(1).add(1);
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		scenario.get(2).add(1);
//		PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(null);
//		scenario.get(3).add(2);
//		PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(null);
//		scenario.get(4).add(1);
//		PermitConduitConductorSectionEntity circuit4= new PermitConduitConductorSectionEntity();
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		NEC_310_16_B_16 nEC_310_16_B_16 = new NEC_310_16_B_16();
//		scenario.get(4).add(nEC_310_16_B_16);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(null);
//		scenario.get(5).add(1);
//		PermitConduitConductorSectionEntity circuit5= new PermitConduitConductorSectionEntity();
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(null);
//		NEC_310_16_B_16 nEC_310_16_B_165 = new NEC_310_16_B_16();
//		nEC_310_16_B_165.setSeventyFiveInsulation(11);
//		scenario.get(5).add(nEC_310_16_B_165);
//		PermitProjectSiteInfoEntityTwo PermitProjectSiteInfoEntity= new PermitProjectSiteInfoEntityTwo();
//		scenario.get(5).add(PermitProjectSiteInfoEntity);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002Micro2.acCircuitafterRevision(form,(String)scenario.get(i).get(0),(int)scenario.get(i).get(1),
//					(PermitConduitConductorSectionEntity)scenario.get(i).get(2),(PermitEntity)scenario.get(i).get(3),(NEC_310_16_B_16)scenario.get(i).get(4),
//					acTradeSize,acNumberOfConductors,0,(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(5));
//		}
//	}
//	
//	@Test
//	public void testmapACCircuit() {
//		List<Integer> acNumberOfConductors = new ArrayList<Integer>();
//		List<String> acCircuitEnvironment = new ArrayList<String>();
//		List<String> acTradeSize = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(0);
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
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("INVERTER-AC DISCONNECT");
//		scenario.get(1).add(1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		scenario.get(1).add(1);
//		scenario.get(1).add(1);
//		scenario.get(1).add(null);
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
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002Micro2.mapACCircuit(form,(String)scenario.get(i).get(0),(int)scenario.get(i).get(1),(List<Integer>)scenario.get(i).get(2),
//					(PermitConduitConductorSectionEntity)scenario.get(i).get(3),(int)scenario.get(i).get(4),(int)scenario.get(i).get(5),
//					(int)scenario.get(i).get(6),(Boolean)scenario.get(i).get(7),(Double)scenario.get(i).get(8),
//					(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(9),(String)scenario.get(i).get(10),
//					(Integer)scenario.get(i).get(11),(String)scenario.get(i).get(12),(int)scenario.get(i).get(13),
//					acCircuitEnvironment,(String)scenario.get(i).get(14),
//					(PermitEntity)scenario.get(i).get(17),acTradeSize,acNumberOfConductors,(String)scenario.get(i).get(18),
//					(ACCombinerSLC)scenario.get(i).get(19),(ACCombinerSLC)scenario.get(i).get(20),(AuthentificationEntity)scenario.get(i).get(21),
//					(PermtiWeatherEntityResult)scenario.get(i).get(22),(PermitLayoutEntity)scenario.get(i).get(23),0);
//		}
//	}
//	
//	@Test
//	public void testgetDCAmpacityCorrectionB2a() {
//		List<Double> scenario = Arrays.asList(24.0);
//		for(int i = 0; i < scenario.size(); i++) {
//			 String ampacityCorrection =plansetServiceE002Micro2.getDCAmpacityCorrectionB2a(scenario.get(i));
//		
//		}
//	}
//	
//	@Test
//	public void testgetACAmpacityCorrectionB2a() {
//		List<Double> scenario = Arrays.asList(24.0);
//		for(int i = 0; i < scenario.size(); i++) {
//			String ampacityCorrection =plansetServiceE002Micro2.getACAmpacityCorrectionB2a(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetACAmpacityCorrectionB2aMultiple() {
//		List<Double> scenario = Arrays.asList(24.0);
//		for(int i = 0; i < scenario.size(); i++) {
//			 String ampacityCorrection =plansetServiceE002Micro2.getACAmpacityCorrectionB2aMultiple(scenario.get(i));
//		}	
//	}
//	
//	@Test
//	public void testgetTraseZiseDC() {
//		List<Double> scenario = Arrays.asList(0.0,27.0,31.1,44.5,60.0,80.4,120.0,150.6,170.1,199.22);
//		List<String> expectedresult = Arrays.asList("14", "12", "10", "8", "6", "4","2","1/0","2/0","4/0");
//		for (int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002Micro2.getTraseZiseDC(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetTraseZiseAC() {
//		List<Double> scenario = Arrays.asList(0.0,27.0,35.0,44.5,60.0,80.4,110.0,150.0,170.1,199.22);
//		List<String> expectedresult = Arrays.asList("10", "10", "10", "8", "6", "4","2","1/0","2/0","4/0");
//		for (int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002Micro2.getTraseZiseAC(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetDCAmpacityCorrectionB3a() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit1= new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit2= new PermitConduitConductorSectionEntity();
//		circuit2.setConduitSize("2");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add("PV MODULE");
//		scenario.get(2).add("3");
//		scenario.get(2).add(1);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit3= new PermitConduitConductorSectionEntity();
//		circuit3.setConduitSize("N/A");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add("PV MODULE");
//		scenario.get(3).add(null);
//		scenario.get(3).add(0);
//		scenario.get(3).add(false);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit4= new PermitConduitConductorSectionEntity();
//		circuit4.setConduitSizeTwo("2");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add("DC/DC CONVERTER");
//		scenario.get(4).add("3");
//		scenario.get(4).add(1);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit5= new PermitConduitConductorSectionEntity();
//		circuit5.setConduitSizeTwo("N/A");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add("DC/DC CONVERTER");
//		scenario.get(5).add(null);
//		scenario.get(5).add(0);
//		scenario.get(5).add(false);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit6= new PermitConduitConductorSectionEntity();
//		circuit6.setConduitSizeThree("2");
//		scenario.get(6).add(circuit6);
//		scenario.get(6).add("JUNCTION BOX");
//		scenario.get(6).add("3");
//		scenario.get(6).add(1);
//		scenario.get(6).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit7= new PermitConduitConductorSectionEntity();
//		circuit7.setConduitSizeThree("N/A");
//		scenario.get(7).add(circuit7);
//		scenario.get(7).add("JUNCTION BOX");
//		scenario.get(7).add(null);
//		scenario.get(7).add(0);
//		scenario.get(7).add(false);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit8= new PermitConduitConductorSectionEntity();
//		circuit8.setConduitSizeFour("2");
//		scenario.get(8).add(circuit8);
//		scenario.get(8).add("DC COMBINER");
//		scenario.get(8).add("3");
//		scenario.get(8).add(1);
//		scenario.get(8).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit9= new PermitConduitConductorSectionEntity();
//		circuit9.setConduitSizeFour("N/A");
//		scenario.get(9).add(circuit9);
//		scenario.get(9).add("DC COMBINER");
//		scenario.get(9).add(null);
//		scenario.get(9).add(0);
//		scenario.get(9).add(false);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit10= new PermitConduitConductorSectionEntity();
//		circuit10.setConduitSizeFive("2");
//		scenario.get(10).add(circuit10);
//		scenario.get(10).add("DC DISCONNECT");
//		scenario.get(10).add("3");
//		scenario.get(10).add(1);
//		scenario.get(10).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit11= new PermitConduitConductorSectionEntity();
//		circuit11.setConduitSizeFive("N/A");
//		scenario.get(11).add(circuit11);
//		scenario.get(11).add("DC DISCONNECT");
//		scenario.get(11).add(null);
//		scenario.get(11).add(0);
//		scenario.get(11).add(false);
//		
//		
//		List<String> expectedresult = Arrays.asList("","","1.0","N/A","1.0","N/A","1.0","N/A","1.0","N/A","1.0","N/A");
//		for(int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002Micro2.getDCAmpacityCorrectionB3a((PermitConduitConductorSectionEntity)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(Integer)scenario.get(i).get(3),(Boolean)scenario.get(i).get(4));
//		}
//	}
//	
//	@Test
//	public void testgetCorrectionB3a() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("3");
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("3");
//		scenario.get(2).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("5");
//		scenario.get(3).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add("9");
//		scenario.get(4).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add("10");
//		scenario.get(5).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add("27");
//		scenario.get(6).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add("40");
//		scenario.get(7).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(8).add("782");
//		scenario.get(8).add(1);
//		List<String> expectedresult = Arrays.asList("", "", "1.0", ".80", ".70", ".50", ".45", ".40", ".35");
//		for (int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002Micro2.getCorrectionB3a((String) scenario.get(i).get(0),
//					(Integer) scenario.get(i).get(1));
//		}
//	}
//	
//	@Test
//	public void testgetACAmpacityCorrectionB2aString() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("3");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("21");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("26");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add("34");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add("37");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add("44");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add("47");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(8).add("52");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(9).add("58");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(10).add("62");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(11).add("67");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(12).add("80");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(13).add("100");
//		
//		List<String> expectedresult = Arrays.asList("","0.00","1.05","1.00","0.94","0.88","0.82","0.75","0.67","0.58","0.47","0.33","0.00","0.00");
//		for (int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002Micro2.getACAmpacityCorrectionB2a((String) scenario.get(i).get(0));
//		}
//	}
//	
//	@Test
//	public void testgetACAmpacityCorrectionB3aBeforeRevision() {
//		PlansetServiceE002Micro2 plansetServiceE002Micro = Mockito.spy(plansetServiceE002Micro2);
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(1);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(1);
//		scenario.get(1).add(null);
//		scenario.get(1).add("3c");
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(1);
//		scenario.get(2).add(null);
//		scenario.get(2).add("3");
//		scenario.get(2).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(1);
//		List<String> acCircuitEnvironment= new ArrayList<String>();
//		scenario.get(3).add(acCircuitEnvironment);
//		scenario.get(3).add("3");
//		scenario.get(3).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(1);
//		List<String> acCircuitEnvironment1= new ArrayList<String>();
//		acCircuitEnvironment1.add("ATTIC");
//		scenario.get(4).add(acCircuitEnvironment1);
//		scenario.get(4).add("3");
//		scenario.get(4).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(1);
//		List<String> acCircuitEnvironment2= new ArrayList<String>();
//		acCircuitEnvironment2.add("UNDERGROUND");
//		scenario.get(5).add(acCircuitEnvironment2);
//		scenario.get(5).add("3");
//		scenario.get(5).add(1);
//		Mockito.doReturn("not N/A").when(plansetServiceE002Micro).getConduitSizePVC("",3);
//		List<String> expectedresult = Arrays.asList("","","","","N/A","1.0");
//		for (int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002Micro2.getACAmpacityCorrectionB3aBeforeRevision((int) scenario.get(i).get(0),(List<String>) scenario.get(i).get(1),(String) scenario.get(i).get(2),(Integer) scenario.get(i).get(3));
//		}
//	}
//	
//	@Test
//	public void testgetACAmpacityCorrectionB3a() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit1= new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit2= new PermitConduitConductorSectionEntity();
//		circuit2.setConduitSizeSix("2");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add("INVERTER");
//		scenario.get(2).add("3");
//		scenario.get(2).add(1);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit3= new PermitConduitConductorSectionEntity();
//		circuit3.setConduitSizeSix("N/A");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add("INVERTER");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(false);
//		scenario.get(3).add(true);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit4= new PermitConduitConductorSectionEntity();
//		circuit4.setConduitSizeSeven("2");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add("JUNCTION BOX");
//		scenario.get(4).add("3");
//		scenario.get(4).add(1);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit5= new PermitConduitConductorSectionEntity();
//		circuit5.setConduitSizeSeven("N/A");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add("JUNCTION BOX");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(false);
//		scenario.get(5).add(true);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit6= new PermitConduitConductorSectionEntity();
//		circuit6.setConduitSizeEight("2");
//		scenario.get(6).add(circuit6);
//		scenario.get(6).add("AC COMBINER/LOAD CENTER");
//		scenario.get(6).add("3");
//		scenario.get(6).add(1);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit7= new PermitConduitConductorSectionEntity();
//		circuit7.setConduitSizeEight("N/A");
//		scenario.get(7).add(circuit7);
//		scenario.get(7).add("AC COMBINER/LOAD CENTER");
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(false);
//		scenario.get(7).add(true);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit8= new PermitConduitConductorSectionEntity();
//		circuit8.setConduitSizeNine("2");
//		scenario.get(8).add(circuit8);
//		scenario.get(8).add("AC DISCONNECT");
//		scenario.get(8).add("3");
//		scenario.get(8).add(1);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit9= new PermitConduitConductorSectionEntity();
//		circuit9.setConduitSizeNine("N/A");
//		scenario.get(9).add(circuit9);
//		scenario.get(9).add("AC DISCONNECT");
//		scenario.get(9).add(null);
//		scenario.get(9).add(null);
//		scenario.get(9).add(false);
//		scenario.get(9).add(true);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit10= new PermitConduitConductorSectionEntity();
//		circuit10.setConduitSizeTen("2");
//		scenario.get(10).add(circuit10);
//		scenario.get(10).add("PRODUCTION METER");
//		scenario.get(10).add("3");
//		scenario.get(10).add(1);
//		scenario.get(10).add(null);
//		scenario.get(10).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit11= new PermitConduitConductorSectionEntity();
//		circuit11.setConduitSizeTen("N/A");
//		scenario.get(11).add(circuit11);
//		scenario.get(11).add("PRODUCTION METER");
//		scenario.get(11).add(null);
//		scenario.get(11).add(null);
//		scenario.get(11).add(false);
//		scenario.get(11).add(true);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit12= new PermitConduitConductorSectionEntity();
//		circuit12.setConduitSizeEleven("2");
//		scenario.get(12).add(circuit12);
//		scenario.get(12).add("SUB PANEL");
//		scenario.get(12).add("3");
//		scenario.get(12).add(1);
//		scenario.get(12).add(null);
//		scenario.get(12).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit13= new PermitConduitConductorSectionEntity();
//		circuit13.setConduitSizeEleven("N/A");
//		scenario.get(13).add(circuit13);
//		scenario.get(13).add("SUB PANEL");
//		scenario.get(13).add(null);
//		scenario.get(13).add(null);
//		scenario.get(13).add(false);
//		scenario.get(13).add(true);
//		
//		List<String> expectedresult = Arrays.asList("","","1.0","N/A","1.0","N/A","1.0","N/A","1.0","N/A","1.0","N/A","1.0","N/A");
//		for(int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002Micro2.getACAmpacityCorrectionB3a((PermitConduitConductorSectionEntity)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(Integer)scenario.get(i).get(3),(Boolean)scenario.get(i).get(4),(Boolean)scenario.get(i).get(5));
//		}
//	}
//	
//	@Test
//	public void testgetACCorrectionB3a() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("3");
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("3");
//		scenario.get(2).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("5");
//		scenario.get(3).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add("9");
//		scenario.get(4).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add("10");
//		scenario.get(5).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add("27");
//		scenario.get(6).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add("40");
//		scenario.get(7).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(8).add("782");
//		scenario.get(8).add(1);
//		List<String> expectedresult = Arrays.asList("","","1.0",".80",".70",".50",".45",".40",".35");
//		for (int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002Micro2.getACCorrectionB3a((String) scenario.get(i).get(0), (Integer) scenario.get(i).get(1));
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
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002Micro2.getConduitSizeEMT((String) scenario.get(i).get(0), (int) scenario.get(i).get(1));
//		}
//	}
//
//	@Test
//	public void testgetConduitSizePVC() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(1);
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002Micro2.getConduitSizePVC((String) scenario.get(i).get(0),
//					(Integer) scenario.get(i).get(1));
//		}
//	}
//	
//}
