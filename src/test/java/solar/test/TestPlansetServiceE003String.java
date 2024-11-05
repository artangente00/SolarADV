//package solar.test;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import static org.mockito.Mockito.*;
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
//import org.mockito.Spy;
//
//import com.PlayGroundAdv.Solar.Entity.Inverters;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
//import com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.PermitServiceEdit;
//import com.PlayGroundAdv.Solar.Services.PlansetServiceE003Micro;
//import com.PlayGroundAdv.Solar.Services.PlansetServiceE003String;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//import com.PlayGroundAdv.Solar.Constants.Constants;
//import com.PlayGroundAdv.Solar.Entity.ACCombinerSLC;
//import com.PlayGroundAdv.Solar.Entity.ACDisconnect;
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.Cmodulev2;
//import com.PlayGroundAdv.Solar.Entity.ConduitConductorCircuitEntity;
//import com.PlayGroundAdv.Solar.Entity.DCCombinerDisconnectEntity;
//import com.PlayGroundAdv.Solar.Entity.DCOptimizerEntity;
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitConduitConductorSectionEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitLayoutEntity;
//import com.PlayGroundAdv.Solar.Entity.PlansetCustomizeSheets;
//import com.PlayGroundAdv.Solar.Entity.UserLicSectionsEntity;
//import com.PlayGroundAdv.Solar.model.DCCombinerDisconnectRequest;
//import com.PlayGroundAdv.Solar.model.EditUserInformations;
//import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
//import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
//
//public class TestPlansetServiceE003String {
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
//    PlansetServiceE003String plansetServiceE003String = new PlansetServiceE003String();
//    
//    PdfReader reader = null;
//   	File fileRe = null;
//   	PdfStamper stamper = null;
//   	AcroFields form = null;
//   	PdfReader readerOrigin= null;
//   	
//     @Before
//     public void setupMock() {
//    	 plansetServiceE003String = new PlansetServiceE003String();
//        MockitoAnnotations.initMocks(this);
// 		
// 		try {
// 		reader = new PdfReader(Constants.rapportPlansetFolderUrl +"NEC-PDF/" +"PDF-E003-"+"STRING"+".pdf" );
// 		fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E003-STRING"+".pdf");
// 		stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
// 		form = stamper.getAcroFields();
// 		readerOrigin = new PdfReader( Constants.rapportPlansetFolderUrl +"NEC-PDF/" + "PDF-E003-STRING.pdf" );
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
//	public void testModuleQty1() {
//		 PermitArrayEntityResultSecond scenario1=new PermitArrayEntityResultSecond() ;
//		 scenario1.setInverterModel("");
//		 PermitArrayEntityResultSecond scenario2=new PermitArrayEntityResultSecond() ;
//		 scenario2.setInverterModel(null);
//		 PermitArrayEntityResultSecond scenario3=new PermitArrayEntityResultSecond() ;
//		 scenario3.setInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario3.setStringOne(null);
//		 PermitArrayEntityResultSecond scenario4=new PermitArrayEntityResultSecond() ;
//		 scenario4.setInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario4.setStringOne("");
//		 PermitArrayEntityResultSecond scenario5=new PermitArrayEntityResultSecond() ;
//		 scenario5.setInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario5.setStringOne("1");
//		 scenario5.setStringTwo("");
//		 PermitArrayEntityResultSecond scenario6=new PermitArrayEntityResultSecond() ;
//		 scenario6.setInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario6.setStringOne("1");
//		 scenario6.setStringTwo(null);
//		 PermitArrayEntityResultSecond scenario7=new PermitArrayEntityResultSecond() ;
//		 scenario7.setInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario7.setStringOne("1");
//		 scenario7.setStringTwo("1");
//		 scenario7.setStringThree("");
//		 PermitArrayEntityResultSecond scenario8=new PermitArrayEntityResultSecond() ;
//		 scenario8.setInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario8.setStringOne("1");
//		 scenario8.setStringTwo("1");
//		 scenario8.setStringThree(null);
//		 PermitArrayEntityResultSecond scenario9=new PermitArrayEntityResultSecond() ;
//		 scenario9.setInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario9.setStringOne("1");
//		 scenario9.setStringTwo("1");
//		 scenario9.setStringThree("1");
//		 scenario9.setStringFour("");
//		 PermitArrayEntityResultSecond scenario10=new PermitArrayEntityResultSecond() ;
//		 scenario10.setInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario10.setStringOne("1");
//		 scenario10.setStringTwo("1");
//		 scenario10.setStringThree("1");
//		 scenario10.setStringFour(null);
//		 PermitArrayEntityResultSecond scenario11=new PermitArrayEntityResultSecond() ;
//		 scenario11.setInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario11.setStringOne("1");
//		 scenario11.setStringTwo("1");
//		 scenario11.setStringThree("1");
//		 scenario11.setStringFour("1");
//		 scenario11.setStringFive("");
//		 PermitArrayEntityResultSecond scenario12=new PermitArrayEntityResultSecond() ;
//		 scenario12.setInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario12.setStringOne("1");
//		 scenario12.setStringTwo("1");
//		 scenario12.setStringThree("1");
//		 scenario12.setStringFour("1");
//		 scenario11.setStringFive(null);
//		 ArrayList<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//	     scenario.add(null);
//		 scenario.add(scenario1);
//		 scenario.add(scenario2);
//		 scenario.add(scenario3);
//		 scenario.add(scenario4);
//		 scenario.add(scenario5);
//		 scenario.add(scenario6);
//		 scenario.add(scenario7);
//		 scenario.add(scenario8);
//		 scenario.add(scenario9);
//		 scenario.add(scenario10);
//		 scenario.add(scenario11);
//		 scenario.add(scenario12);
//		 for(int i = 0; i < scenario.size(); i++) {
//		     plansetServiceE003String.ModuleQty1(scenario.get(i));
//		 }
//	}
//	
//	@Test
//	public void testModuleQty2() {
//		 PermitArrayEntityResultSecond scenario1=new PermitArrayEntityResultSecond() ;
//		 scenario1.setSecondInverterModel("");
//		 PermitArrayEntityResultSecond scenario2=new PermitArrayEntityResultSecond() ;
//		 scenario2.setSecondInverterModel(null);
//		 PermitArrayEntityResultSecond scenario3=new PermitArrayEntityResultSecond() ;
//		 scenario3.setSecondInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario3.setSecondStringOne(null);
//		 PermitArrayEntityResultSecond scenario4=new PermitArrayEntityResultSecond() ;
//		 scenario4.setSecondInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario4.setSecondStringOne("");
//		 PermitArrayEntityResultSecond scenario5=new PermitArrayEntityResultSecond() ;
//		 scenario5.setSecondInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario5.setSecondStringOne("1");
//		 scenario5.setSecondStringTwo("");
//		 PermitArrayEntityResultSecond scenario6=new PermitArrayEntityResultSecond() ;
//		 scenario6.setSecondInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario6.setSecondStringOne("1");
//		 scenario6.setSecondStringTwo(null);
//		 PermitArrayEntityResultSecond scenario7=new PermitArrayEntityResultSecond() ;
//		 scenario7.setSecondInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario7.setSecondStringOne("1");
//		 scenario7.setSecondStringTwo("1");
//		 scenario7.setSecondStringThree("");
//		 PermitArrayEntityResultSecond scenario8=new PermitArrayEntityResultSecond() ;
//		 scenario8.setSecondInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario8.setSecondStringOne("1");
//		 scenario8.setSecondStringTwo("1");
//		 scenario8.setSecondStringThree(null);
//		 PermitArrayEntityResultSecond scenario9=new PermitArrayEntityResultSecond() ;
//		 scenario9.setSecondInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario9.setSecondStringOne("1");
//		 scenario9.setSecondStringTwo("1");
//		 scenario9.setSecondStringThree("1");
//		 scenario9.setSecondStringFour("");
//		 PermitArrayEntityResultSecond scenario10=new PermitArrayEntityResultSecond() ;
//		 scenario10.setSecondInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario10.setSecondStringOne("1");
//		 scenario10.setSecondStringTwo("1");
//		 scenario10.setSecondStringThree("1");
//		 scenario10.setSecondStringFour(null);
//		 PermitArrayEntityResultSecond scenario11=new PermitArrayEntityResultSecond() ;
//		 scenario11.setSecondInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario11.setSecondStringOne("1");
//		 scenario11.setSecondStringTwo("1");
//		 scenario11.setSecondStringThree("1");
//		 scenario11.setSecondStringFour("1");
//		 scenario11.setSecondStringFive("");
//		 PermitArrayEntityResultSecond scenario12=new PermitArrayEntityResultSecond() ;
//		 scenario12.setSecondInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario12.setSecondStringOne("1");
//		 scenario12.setSecondStringTwo("1");
//		 scenario12.setSecondStringThree("1");
//		 scenario12.setSecondStringFour("1");
//		 scenario11.setSecondStringFive(null);
//		 ArrayList<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//	     scenario.add(null);
//		 scenario.add(scenario1);
//		 scenario.add(scenario2);
//		 scenario.add(scenario3);
//		 scenario.add(scenario4);
//		 scenario.add(scenario5);
//		 scenario.add(scenario6);
//		 scenario.add(scenario7);
//		 scenario.add(scenario8);
//		 scenario.add(scenario9);
//		 scenario.add(scenario10);
//		 scenario.add(scenario11);
//		 scenario.add(scenario12);
//		 for(int i = 0; i < scenario.size(); i++) {
//			 plansetServiceE003String.ModuleQty2(scenario.get(i));
//		 }
//	}
//	
//	@Test
//	public void testModuleQty3() {
//		PermitArrayEntityResultSecond scenario1=new PermitArrayEntityResultSecond() ;
//		 scenario1.setThirdInverterModel("");
//		 PermitArrayEntityResultSecond scenario2=new PermitArrayEntityResultSecond() ;
//		 scenario2.setThirdInverterModel(null);
//		 PermitArrayEntityResultSecond scenario3=new PermitArrayEntityResultSecond() ;
//		 scenario3.setThirdInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario3.setThirdStringOne(null);
//		 PermitArrayEntityResultSecond scenario4=new PermitArrayEntityResultSecond() ;
//		 scenario4.setThirdInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario4.setThirdStringOne(1);
//		 scenario4.setThirdStringTwo(null);
//		 PermitArrayEntityResultSecond scenario5=new PermitArrayEntityResultSecond() ;
//		 scenario5.setThirdInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario5.setThirdStringOne(1);
//		 scenario5.setThirdStringTwo(1);
//		 scenario5.setThirdStringThree(null);
//		 PermitArrayEntityResultSecond scenario6=new PermitArrayEntityResultSecond() ;
//		 scenario6.setThirdInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario6.setThirdStringOne(1);
//		 scenario6.setThirdStringTwo(1);
//		 scenario6.setThirdStringThree(1);
//		 scenario6.setThirdStringFour(null);
//		 PermitArrayEntityResultSecond scenario7=new PermitArrayEntityResultSecond() ;
//		 scenario7.setThirdInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario7.setThirdStringOne(1);
//		 scenario7.setThirdStringTwo(1);
//		 scenario7.setThirdStringThree(1);
//		 scenario7.setThirdStringFour(1);
//		 scenario7.setThirdStringFive(null);
//		 ArrayList<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//	     scenario.add(null);
//		 scenario.add(scenario1);
//		 scenario.add(scenario2);
//		 scenario.add(scenario3);
//		 scenario.add(scenario4);
//		 scenario.add(scenario5);
//		 scenario.add(scenario6);
//		 scenario.add(scenario7);
//		 for(int i = 0; i < scenario.size(); i++) {
//		    plansetServiceE003String.ModuleQty3(scenario.get(i));
//		 }
//	}
//	
//	@Test
//	public void testModuleQty4() {
//		 PermitArrayEntityResultSecond scenario1=new PermitArrayEntityResultSecond() ;
//		 scenario1.setFourthInverterModel("");
//		 PermitArrayEntityResultSecond scenario2=new PermitArrayEntityResultSecond() ;
//		 scenario2.setFourthInverterModel(null);
//		 PermitArrayEntityResultSecond scenario3=new PermitArrayEntityResultSecond() ;
//		 scenario3.setFourthInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario3.setFourthStringOne(null);
//		 PermitArrayEntityResultSecond scenario4=new PermitArrayEntityResultSecond() ;
//		 scenario4.setFourthInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario4.setFourthStringOne(1);
//		 scenario4.setFourthStringTwo(null);
//		 PermitArrayEntityResultSecond scenario5=new PermitArrayEntityResultSecond() ;
//		 scenario5.setFourthInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario5.setFourthStringOne(1);
//		 scenario5.setFourthStringTwo(1);
//		 scenario5.setFourthStringThree(null);
//		 PermitArrayEntityResultSecond scenario6=new PermitArrayEntityResultSecond() ;
//		 scenario6.setFourthInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario6.setFourthStringOne(1);
//		 scenario6.setFourthStringTwo(1);
//		 scenario6.setFourthStringThree(1);
//		 scenario6.setFourthStringFour(null);
//		 PermitArrayEntityResultSecond scenario7=new PermitArrayEntityResultSecond() ;
//		 scenario7.setFourthInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario7.setFourthStringOne(1);
//		 scenario7.setFourthStringTwo(1);
//		 scenario7.setFourthStringThree(1);
//		 scenario7.setFourthStringFour(1);
//		 scenario7.setFourthStringFive(null);
//		 ArrayList<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//	     scenario.add(null);
//		 scenario.add(scenario1);
//		 scenario.add(scenario2);
//		 scenario.add(scenario3);
//		 scenario.add(scenario4);
//		 scenario.add(scenario5);
//		 scenario.add(scenario6);
//		 scenario.add(scenario7);
//		 for(int i = 0; i < scenario.size(); i++) {
//		    plansetServiceE003String.ModuleQty4(scenario.get(i));
//		 }
//	}
//	
//	@Test
//	public void testModuleQty5() {
//		PermitArrayEntityResultSecond scenario1=new PermitArrayEntityResultSecond() ;
//		 scenario1.setFifthInverterModel("");
//		 PermitArrayEntityResultSecond scenario2=new PermitArrayEntityResultSecond() ;
//		 scenario2.setFifthInverterModel(null);
//		 PermitArrayEntityResultSecond scenario3=new PermitArrayEntityResultSecond() ;
//		 scenario3.setFifthInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario3.setFifthStringOne(null);
//		 PermitArrayEntityResultSecond scenario4=new PermitArrayEntityResultSecond() ;
//		 scenario4.setFifthInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario4.setFifthStringOne(1);
//		 scenario4.setFifthStringTwo(null);
//		 PermitArrayEntityResultSecond scenario5=new PermitArrayEntityResultSecond() ;
//		 scenario5.setFifthInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario5.setFifthStringOne(1);
//		 scenario5.setFifthStringTwo(1);
//		 scenario5.setFifthStringThree(null);
//		 PermitArrayEntityResultSecond scenario6=new PermitArrayEntityResultSecond() ;
//		 scenario6.setFifthInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario6.setFifthStringOne(1);
//		 scenario6.setFifthStringTwo(1);
//		 scenario6.setFifthStringThree(1);
//		 scenario6.setFifthStringFour(null);
//		 PermitArrayEntityResultSecond scenario7=new PermitArrayEntityResultSecond() ;
//		 scenario7.setFifthInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//		 scenario7.setFifthStringOne(1);
//		 scenario7.setFifthStringTwo(1);
//		 scenario7.setFifthStringThree(1);
//		 scenario7.setFifthStringFour(1);
//		 scenario7.setFifthStringFive(null);
//		 ArrayList<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//	     scenario.add(null);
//		 scenario.add(scenario1);
//		 scenario.add(scenario2);
//		 scenario.add(scenario3);
//		 scenario.add(scenario4);
//		 scenario.add(scenario5);
//		 scenario.add(scenario6);
//		 scenario.add(scenario7);
//		 for(int i = 0; i < scenario.size(); i++) {
//		    plansetServiceE003String.ModuleQty5(scenario.get(i));
//		 }
//	}
//	
//	@Test
//	public void testiacMax() {
//		ArrayList<Inverters> scenario = new ArrayList<Inverters>();
//	    scenario.add(null);
//		Inverters scenario1 = new Inverters();
//		scenario1.setIacmax(null);
//		scenario.add(scenario1);
//		Inverters scenario2 = new Inverters();
//		scenario2.setIacmax("1,1");
//		scenario.add(scenario2);
//		for(int i = 0; i < scenario.size(); i++) {
//		 plansetServiceE003String.iacMax(scenario.get(i));
//		}
//	}
//	@Test
//	public void testiacMax2() {
//		ArrayList<Inverters> scenario = new ArrayList<Inverters>();
//	    scenario.add(null);
//		Inverters scenario1 = new Inverters();
//		scenario1.setIacmax(null);
//		scenario.add(scenario1);
//		Inverters scenario2 = new Inverters();
//		scenario2.setIacmax("1,1");
//		scenario.add(scenario2);
//		for(int i = 0; i < scenario.size(); i++) {
//		plansetServiceE003String.iacMax2(scenario.get(i));
//		}
//	}
//	@Test
//	public void testThirdInverterInfo() {
//		ArrayList<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//		PermitArrayEntityResultSecond scenario1=new PermitArrayEntityResultSecond() ;
//		scenario.add(scenario1);
//		PermitArrayEntityResultSecond scenario2=new PermitArrayEntityResultSecond() ;
//    	Inverters thirdInverterInfo = new Inverters();
//    	thirdInverterInfo.setIacmax("1.1");
//    	ArrayList<Inverters> resultlist = new ArrayList<Inverters>();
//    	resultlist.add(thirdInverterInfo);
//    	scenario2.setThirdInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//    	 Query mockedQuery = mock(Query.class);
//    	when(em.createQuery("SELECT u" + " from Inverters u " + " where u.model = :p1 " + " and u.make = :p2 ")).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p1", scenario2.getThirdInverterModel().split(":")[1])).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p2", scenario2.getThirdInverterModel().split(":")[0])).thenReturn(mockedQuery);
//    	when(mockedQuery.getResultList()).thenReturn(resultlist);
//    	when(mockedQuery.getSingleResult()).thenReturn(thirdInverterInfo);
//    	scenario.add(scenario2);
//    	for(int i = 0; i < scenario.size(); i++) {
//   		Inverters r=plansetServiceE003String.thirdInverterInfo(scenario.get(i));
//   		}
//	}
//	
//	@Test
//	public void testiacMax3() {
//		ArrayList<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//		PermitArrayEntityResultSecond scenario1=new PermitArrayEntityResultSecond() ;
//		scenario.add(scenario1);
//		PermitArrayEntityResultSecond scenario2=new PermitArrayEntityResultSecond() ;
//		Inverters thirdInverterInfo1 = new Inverters();
//		thirdInverterInfo1.setIacmax("1.1");
//		scenario2.setThirdInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//    	ArrayList<Inverters> resultlist = new ArrayList<Inverters>();
//    	resultlist.add(thirdInverterInfo1);
//    	 Query mockedQuery = mock(Query.class);
//    	when(em.createQuery("SELECT u" + " from Inverters u " + " where u.model = :p1 " + " and u.make = :p2 ")).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p1", scenario2.getThirdInverterModel().split(":")[1])).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p2", scenario2.getThirdInverterModel().split(":")[0])).thenReturn(mockedQuery);
//    	when(mockedQuery.getResultList()).thenReturn(resultlist);
//    	when(mockedQuery.getSingleResult()).thenReturn(thirdInverterInfo1);
//		scenario.add(scenario2);
//		for(int i = 0; i < scenario.size(); i++) {
//		String r=plansetServiceE003String.iacMax3(scenario.get(i));
//		}
//	}
//	
//	
//	@Test
//	public void testFourthInverterInfo() {
//		ArrayList<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//		PermitArrayEntityResultSecond scenario1=new PermitArrayEntityResultSecond() ;
//		scenario.add(scenario1);
//		PermitArrayEntityResultSecond scenario2=new PermitArrayEntityResultSecond() ;
//    	Inverters fourthInverterInfo = new Inverters();
//    	fourthInverterInfo.setIacmax("1.1");
//    	ArrayList<Inverters> resultlist = new ArrayList<Inverters>();
//    	resultlist.add(fourthInverterInfo);
//    	scenario2.setFourthInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//    	 Query mockedQuery = mock(Query.class);
//    	when(em.createQuery("SELECT u" + " from Inverters u " + " where u.model = :p1 " + " and u.make = :p2 ")).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p1", scenario2.getFourthInverterModel().split(":")[1])).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p2", scenario2.getFourthInverterModel().split(":")[0])).thenReturn(mockedQuery);
//    	when(mockedQuery.getResultList()).thenReturn(resultlist);
//    	when(mockedQuery.getSingleResult()).thenReturn(fourthInverterInfo);
//    	scenario.add(scenario2);
//    	for(int i = 0; i < scenario.size(); i++) {
//   		Inverters r=plansetServiceE003String.fourthInverterInfo(scenario.get(i));
//   		}
//	}
//	@Test
//	public void testiacMax4() {
//		ArrayList<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//		PermitArrayEntityResultSecond scenario1=new PermitArrayEntityResultSecond() ;
//		scenario.add(scenario1);
//		PermitArrayEntityResultSecond scenario2=new PermitArrayEntityResultSecond() ;
//		Inverters fourthInverterModel = new Inverters();
//		fourthInverterModel.setIacmax("1.1");
//		scenario2.setFourthInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//    	ArrayList<Inverters> resultlist = new ArrayList<Inverters>();
//    	resultlist.add(fourthInverterModel);
//    	 Query mockedQuery = mock(Query.class);
//    	when(em.createQuery("SELECT u" + " from Inverters u " + " where u.model = :p1 " + " and u.make = :p2 ")).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p1", scenario2.getFourthInverterModel().split(":")[1])).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p2", scenario2.getFourthInverterModel().split(":")[0])).thenReturn(mockedQuery);
//    	when(mockedQuery.getResultList()).thenReturn(resultlist);
//    	when(mockedQuery.getSingleResult()).thenReturn(fourthInverterModel);
//		scenario.add(scenario2);
//		for(int i = 0; i < scenario.size(); i++) {
//		String r=plansetServiceE003String.iacMax4(scenario.get(i));
//		}
//	}
//	@Test
//	public void testFifthInverterInfo() {
//		ArrayList<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//		PermitArrayEntityResultSecond scenario1=new PermitArrayEntityResultSecond() ;
//		scenario.add(scenario1);
//		PermitArrayEntityResultSecond scenario2=new PermitArrayEntityResultSecond() ;
//    	Inverters fifthInverterInfo = new Inverters();
//    	fifthInverterInfo.setIacmax("1.1");
//    	ArrayList<Inverters> resultlist = new ArrayList<Inverters>();
//    	resultlist.add(fifthInverterInfo);
//    	scenario2.setFifthInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//    	 Query mockedQuery = mock(Query.class);
//    	when(em.createQuery("SELECT u" + " from Inverters u " + " where u.model = :p1 " + " and u.make = :p2 ")).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p1", scenario2.getFifthInverterModel().split(":")[1])).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p2", scenario2.getFifthInverterModel().split(":")[0])).thenReturn(mockedQuery);
//    	when(mockedQuery.getResultList()).thenReturn(resultlist);
//    	when(mockedQuery.getSingleResult()).thenReturn(fifthInverterInfo);
//    	scenario.add(scenario2);
//    	for(int i = 0; i < scenario.size(); i++) {
//   		Inverters r=plansetServiceE003String.fifthInverterInfo(scenario.get(i));
//   		}
//	}
//	@Test
//	public void testiacMax5() {
//		ArrayList<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//		PermitArrayEntityResultSecond scenario1=new PermitArrayEntityResultSecond() ;
//		scenario.add(scenario1);
//		PermitArrayEntityResultSecond scenario2=new PermitArrayEntityResultSecond() ;
//		Inverters fifthInverterModel = new Inverters();
//		fifthInverterModel.setIacmax("1.1");
//		scenario2.setFifthInverterModel("ABB:MICRO-0.3HV-I-OUTD-US-208");
//    	ArrayList<Inverters> resultlist = new ArrayList<Inverters>();
//    	resultlist.add(fifthInverterModel);
//    	 Query mockedQuery = mock(Query.class);
//    	when(em.createQuery("SELECT u" + " from Inverters u " + " where u.model = :p1 " + " and u.make = :p2 ")).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p1", scenario2.getFifthInverterModel().split(":")[1])).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p2", scenario2.getFifthInverterModel().split(":")[0])).thenReturn(mockedQuery);
//    	when(mockedQuery.getResultList()).thenReturn(resultlist);
//    	when(mockedQuery.getSingleResult()).thenReturn(fifthInverterModel);
//		scenario.add(scenario2);
//		for(int i = 0; i < scenario.size(); i++) {
//		String r=plansetServiceE003String.iacMax5(scenario.get(i));
//		}
//	}
//	@Test
//	public void testgetRequiredAmpacity() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(0);
//		scenario.get(0).add(true);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(0);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(0);
//		scenario.get(1).add(true);
//		DCOptimizerEntity dcOptimizer = new DCOptimizerEntity();
//		dcOptimizer.setManufacturer("SolarEdge");
//		scenario.get(1).add(dcOptimizer);
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		scenario.get(1).add(0);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(0);
//		scenario.get(2).add(true);
//		DCOptimizerEntity dcOptimizer2 = new DCOptimizerEntity();
//		dcOptimizer2.setManufacturer("SolarEdge");
//		scenario.get(2).add(dcOptimizer2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		scenario.get(2).add(0);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(2);
//		scenario.get(3).add(true);
//		DCOptimizerEntity dcOptimizer3 = new DCOptimizerEntity();
//		scenario.get(3).add(dcOptimizer3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(1);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(0);
//		scenario.get(3).add(2);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(2);
//		scenario.get(4).add(false);
//		DCOptimizerEntity dcOptimizer4 = new DCOptimizerEntity();
//		scenario.get(4).add(dcOptimizer4);
//		scenario.get(4).add("1.1");
//		scenario.get(4).add(1);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(1);
//		scenario.get(4).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(0);
//		scenario.get(5).add(false);
//		DCOptimizerEntity dcOptimizer5= new DCOptimizerEntity();
//		scenario.get(5).add(dcOptimizer5);
//		scenario.get(5).add("1");
//		scenario.get(5).add(0);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(0);
//		scenario.get(5).add(0);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add(1);
//		scenario.get(6).add(true);
//		DCOptimizerEntity dcOptimizer6 = new DCOptimizerEntity();
//		dcOptimizer6.setManufacturer("SolarEdge");
//		scenario.get(6).add(dcOptimizer6);
//		scenario.get(6).add("1");
//		scenario.get(6).add(0);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(0);
//		scenario.get(6).add(0);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add(1);
//		scenario.get(7).add(true);
//		DCOptimizerEntity dcOptimizer7 = new DCOptimizerEntity();
//		dcOptimizer7.setManufacturer("SolarEdge");
//		scenario.get(7).add(dcOptimizer7);
//		scenario.get(7).add("1");
//		scenario.get(7).add(1);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(0);
//		scenario.get(7).add(0);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(8).add(1);
//		scenario.get(8).add(true);
//		DCOptimizerEntity dcOptimizer8 = new DCOptimizerEntity();
//		dcOptimizer8.setManufacturer("SolarEdge");
//		scenario.get(8).add(dcOptimizer8);
//		scenario.get(8).add("1");
//		scenario.get(8).add(1);
//		Cmodulev2 moduleInfo = new Cmodulev2();
//		moduleInfo.setStcRounded("1");
//		scenario.get(8).add(moduleInfo);
//		scenario.get(8).add(null);
//		scenario.get(8).add(0);
//		scenario.get(8).add(0);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(9).add(1);
//		scenario.get(9).add(true);
//		DCOptimizerEntity dcOptimizer9 = new DCOptimizerEntity();
//		dcOptimizer9.setManufacturer("SolarEdge");
//		scenario.get(9).add(dcOptimizer9);
//		scenario.get(9).add("1");
//		scenario.get(9).add(1);
//		Cmodulev2 moduleInfo9 = new Cmodulev2();
//		moduleInfo9.setStcRounded("1");
//		scenario.get(9).add(moduleInfo9);
//		Inverters inverterInfo = new Inverters();
//		scenario.get(9).add(inverterInfo);
//		scenario.get(9).add(0);
//		scenario.get(9).add(0);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(10).add(1);
//		scenario.get(10).add(true);
//		DCOptimizerEntity dcOptimizer10 = new DCOptimizerEntity();
//		dcOptimizer10.setManufacturer("SolarEdge");
//		scenario.get(10).add(dcOptimizer10);
//		scenario.get(10).add("1");
//		scenario.get(10).add(1);
//		Cmodulev2 moduleInfo10 = new Cmodulev2();
//		moduleInfo10.setStcRounded("1");
//		scenario.get(10).add(moduleInfo10);
//		Inverters inverterInfo10 = new Inverters();
//		inverterInfo10.setMpptLow("0");
//		scenario.get(10).add(inverterInfo10);
//		scenario.get(10).add(10);
//		scenario.get(10).add(1);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(11).add(2);
//		scenario.get(11).add(true);
//		DCOptimizerEntity dcOptimizer11 = new DCOptimizerEntity();
//		dcOptimizer11.setManufacturer("SolarEdge");
//		scenario.get(11).add(dcOptimizer11);
//		scenario.get(11).add("1");
//		scenario.get(11).add(0);
//		Cmodulev2 moduleInfo11= new Cmodulev2();
//		moduleInfo11.setStcRounded("1");
//		scenario.get(11).add(moduleInfo11);
//		Inverters inverterInfo11 = new Inverters();
//		inverterInfo11.setMpptLow("0");
//		scenario.get(11).add(inverterInfo11);
//		scenario.get(11).add(10);
//		scenario.get(11).add(0);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(12).add(2);
//		scenario.get(12).add(true);
//		DCOptimizerEntity dcOptimizer12 = new DCOptimizerEntity();
//		dcOptimizer12.setManufacturer("SolarEdge");
//		scenario.get(12).add(dcOptimizer12);
//		scenario.get(12).add("1");
//		scenario.get(12).add(0);
//		Cmodulev2 moduleInfo12= new Cmodulev2();
//		moduleInfo12.setStcRounded("1");
//		scenario.get(12).add(moduleInfo12);
//		Inverters inverterInfo12 = new Inverters();
//		scenario.get(12).add(inverterInfo12);
//		scenario.get(12).add(10);
//		scenario.get(12).add(0);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(13).add(2);
//		scenario.get(13).add(true);
//		DCOptimizerEntity dcOptimizer13 = new DCOptimizerEntity();
//		dcOptimizer13.setManufacturer("SolarEdge");
//		scenario.get(13).add(dcOptimizer13);
//		scenario.get(13).add("1");
//		scenario.get(13).add(0);
//		Cmodulev2 moduleInfo13= new Cmodulev2();
//		moduleInfo13.setStcRounded("1");
//		scenario.get(13).add(moduleInfo13);
//		scenario.get(13).add(null);
//		scenario.get(13).add(10);
//		scenario.get(13).add(0);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(14).add(2);
//		scenario.get(14).add(true);
//		DCOptimizerEntity dcOptimizer14 = new DCOptimizerEntity();
//		dcOptimizer14.setManufacturer("SolarEdge");
//		scenario.get(14).add(dcOptimizer14);
//		scenario.get(14).add("1");
//		scenario.get(14).add(0);
//		Cmodulev2 moduleInfo14= new Cmodulev2();
//		scenario.get(14).add(moduleInfo14);
//		Inverters inverterInfo14 = new Inverters();
//		inverterInfo14.setMpptLow("1");
//		scenario.get(14).add(inverterInfo14);
//		scenario.get(14).add(10);
//		scenario.get(14).add(0);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(15).add(2);
//		scenario.get(15).add(true);
//		DCOptimizerEntity dcOptimizer15 = new DCOptimizerEntity();
//		dcOptimizer15.setManufacturer("SolarEdge");
//		scenario.get(15).add(dcOptimizer15);
//		scenario.get(15).add("1");
//		scenario.get(15).add(0);
//		scenario.get(15).add(null);
//		Inverters inverterInfo15 = new Inverters();
//		inverterInfo15.setMpptLow("1");
//		scenario.get(15).add(inverterInfo15);
//		scenario.get(15).add(10);
//		scenario.get(15).add(0);
//		for(int i = 0; i < scenario.size(); i++) {
//			double r= plansetServiceE003String.getRequiredAmpacity((int)scenario.get(i).get(0),(Boolean)scenario.get(i).get(1),(DCOptimizerEntity)scenario.get(i).get(2),(String)scenario.get(i).get(3),(int)scenario.get(i).get(4),(Cmodulev2)scenario.get(i).get(5),(Inverters)scenario.get(i).get(6),(int)scenario.get(i).get(7),(int)scenario.get(i).get(8));
//		}
//		}
//	
//	@Test
//	public void testgetContractorLN() {
//		ArrayList<UserLicSectionsEntity> scenario = new ArrayList<UserLicSectionsEntity>();
//	    scenario.add(null);
//	    UserLicSectionsEntity scenario1 =new UserLicSectionsEntity();
//		scenario1.setLicenseNumber(null);
//		scenario.add(scenario1);
//		UserLicSectionsEntity scenario2 = new UserLicSectionsEntity();
//		scenario2.setLicenseNumber("");
//		scenario.add(scenario2);
//		UserLicSectionsEntity scenario3 = new UserLicSectionsEntity();
//		scenario3.setContractorLicenceState(null);
//		scenario.add(scenario3);
//		UserLicSectionsEntity scenario4 = new UserLicSectionsEntity();
//		scenario4.setContractorLicenceState("");
//		scenario.add(scenario4);
//		UserLicSectionsEntity scenario5 = new UserLicSectionsEntity();
//		scenario5.setContractorLicenceState("CA");
//		scenario5.setLicTypeCode(null);
//		scenario.add(scenario5);
//		UserLicSectionsEntity scenario6 = new UserLicSectionsEntity();
//		scenario6.setContractorLicenceState("CA");
//		String list[] = {null};
//		scenario6.setLicTypeCode(list);
//		scenario.add(scenario6);
//		UserLicSectionsEntity scenario7 = new UserLicSectionsEntity();
//		scenario7.setContractorLicenceState("CA");
//		String listTypeCode[] = {""};
//		scenario7.setLicTypeCode(listTypeCode);
//		scenario.add(scenario7);
//		UserLicSectionsEntity scenario8 = new UserLicSectionsEntity();
//		scenario8.setContractorLicenceState(null);
//		scenario8.setFirstLicTypeCodeOther(null);
//		scenario8.setSecondLicTypeCodeOther(null);
//		scenario8.setThirdLicTypeCodeOther(null);
//		scenario.add(scenario8);
//		UserLicSectionsEntity scenario9 = new UserLicSectionsEntity();
//		scenario9.setContractorLicenceState(null);
//		scenario9.setFirstLicTypeCodeOther("");
//		scenario9.setSecondLicTypeCodeOther("");
//		scenario9.setThirdLicTypeCodeOther("");
//		scenario.add(scenario9);
//		for(int i = 0; i < scenario.size(); i++) {
//		plansetServiceE003String.getContractorLN(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetC46() {
//		
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations1=new EditUserInformations() ;
//		editUserInformations1.setContractorLicenceState(null);
//		PermitHomeSiteEntityResult permitHomeSite1= new PermitHomeSiteEntityResult();
//		scenario.get(1).add(editUserInformations1);
//		scenario.get(1).add(permitHomeSite1);
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations2=new EditUserInformations() ;
//		editUserInformations2.setContractorLicenceState("");
//		scenario.get(2).add(editUserInformations2);
//		scenario.get(2).add(null);
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations3=new EditUserInformations() ;
//		PermitHomeSiteEntityResult permitHomeSite3= new PermitHomeSiteEntityResult();
//		permitHomeSite3.setState("");
//		editUserInformations3.setContractorLicenceState("");
//		editUserInformations3.setContractorLic(null);
//		scenario.get(3).add(editUserInformations3);
//		scenario.get(3).add(permitHomeSite3);
//		for(int i = 0; i < scenario.size(); i++) {
//		plansetServiceE003String.getC46((EditUserInformations)scenario.get(i).get(0),(PermitHomeSiteEntityResult)scenario.get(i).get(1));
//		}
//	}
//		
//	
//	@Test
//	public void testgetC10() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations1=new EditUserInformations() ;
//		editUserInformations1.setContractorLicenceState(null);
//		PermitHomeSiteEntityResult permitHomeSite1= new PermitHomeSiteEntityResult();
//		scenario.get(1).add(editUserInformations1);
//		scenario.get(1).add(permitHomeSite1);
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations2=new EditUserInformations() ;
//		editUserInformations2.setContractorLicenceState("");
//		scenario.get(2).add(editUserInformations2);
//		scenario.get(2).add(null);
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations3=new EditUserInformations() ;
//		PermitHomeSiteEntityResult permitHomeSite3= new PermitHomeSiteEntityResult();
//		permitHomeSite3.setState("");
//		editUserInformations3.setContractorLicenceState("");
//		editUserInformations3.setContractorLicC10(null);
//		scenario.get(3).add(editUserInformations3);
//		scenario.get(3).add(permitHomeSite3);
//		for(int i = 0; i < scenario.size(); i++) {
//		plansetServiceE003String.getC10((EditUserInformations)scenario.get(i).get(0),(PermitHomeSiteEntityResult)scenario.get(i).get(1));
//		}
//	}
//	@Test
//	public void testgetB() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations1=new EditUserInformations() ;
//		editUserInformations1.setContractorLicenceState(null);
//		PermitHomeSiteEntityResult permitHomeSite1= new PermitHomeSiteEntityResult();
//		scenario.get(1).add(editUserInformations1);
//		scenario.get(1).add(permitHomeSite1);
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations2=new EditUserInformations() ;
//		editUserInformations2.setContractorLicenceState("");
//		scenario.get(2).add(editUserInformations2);
//		scenario.get(2).add(null);
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations editUserInformations3=new EditUserInformations() ;
//		PermitHomeSiteEntityResult permitHomeSite3= new PermitHomeSiteEntityResult();
//		permitHomeSite3.setState("");
//		editUserInformations3.setContractorLicenceState("");
//		editUserInformations3.setContractorLicB(null);
//		scenario.get(3).add(editUserInformations3);
//		scenario.get(3).add(permitHomeSite3);
//		for(int i = 0; i < scenario.size(); i++) {
//		plansetServiceE003String.getB((EditUserInformations)scenario.get(i).get(0),(PermitHomeSiteEntityResult)scenario.get(i).get(1));
//		}
//	}
//	@Test
//	public void testgetsumIacMax() {
//		List<List<String>> scenario = new ArrayList<List<String>>();
//		scenario.add(new ArrayList<String>());
//		scenario.get(0).add(null);
//		scenario.get(0).add("");
//		scenario.get(0).add("");
//		scenario.get(0).add("");
//		scenario.get(0).add("");
//		scenario.add(new ArrayList<String>());
//		scenario.get(1).add("1");
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.add(new ArrayList<String>());
//		scenario.get(2).add("1");
//		scenario.get(2).add("1");
//		scenario.get(2).add(null);
//		scenario.get(2).add("");
//		scenario.get(2).add("");
//		scenario.add(new ArrayList<String>());
//		scenario.get(3).add("1");
//		scenario.get(3).add("1");
//		scenario.get(3).add("1");
//		scenario.get(3).add(null);
//		scenario.get(3).add("");
//		scenario.add(new ArrayList<String>());
//		scenario.get(4).add("1");
//		scenario.get(4).add("1");
//		scenario.get(4).add("1");
//		scenario.get(4).add("1");
//		scenario.get(4).add(null);
//		scenario.add(new ArrayList<String>());
//		scenario.get(5).add("");
//		scenario.get(5).add("1");
//		scenario.get(5).add("");
//		scenario.get(5).add("");
//		scenario.get(5).add("");
//		scenario.add(new ArrayList<String>());
//		scenario.get(6).add("1");
//		scenario.get(6).add("");
//		scenario.get(6).add("");
//		scenario.get(6).add("1");
//		scenario.get(6).add("1"); 
//		scenario.add(new ArrayList<String>());
//		scenario.get(7).add("1");
//		scenario.get(7).add("1");
//		scenario.get(7).add("");
//		scenario.get(7).add("");
//		scenario.get(7).add("1"); 
//		for(int i = 0; i < scenario.size(); i++) {
//		plansetServiceE003String.getsumIacMax(scenario.get(i).get(0),scenario.get(i).get(1),scenario.get(i).get(2),scenario.get(i).get(3),scenario.get(i).get(4));
//		}
//	}
//	@Test
//	public void testgetlargestIacMax() {
//		List<List<String>> scenario = new ArrayList<List<String>>();
//		scenario.add(new ArrayList<String>());
//		scenario.get(0).add(null);
//		scenario.get(0).add("");
//		scenario.get(0).add("");
//		scenario.get(0).add("");
//		scenario.get(0).add("");
//		scenario.add(new ArrayList<String>());
//		scenario.get(1).add("1");
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.add(new ArrayList<String>());
//		scenario.get(2).add("1");
//		scenario.get(2).add("1");
//		scenario.get(2).add(null);
//		scenario.get(2).add("");
//		scenario.get(2).add("");
//		scenario.add(new ArrayList<String>());
//		scenario.get(3).add("1");
//		scenario.get(3).add("1");
//		scenario.get(3).add("1");
//		scenario.get(3).add(null);
//		scenario.get(3).add("");
//		scenario.add(new ArrayList<String>());
//		scenario.get(4).add("1");
//		scenario.get(4).add("1");
//		scenario.get(4).add("1");
//		scenario.get(4).add("1");
//		scenario.get(4).add(null);
//		scenario.add(new ArrayList<String>());
//		scenario.get(5).add("");
//		scenario.get(5).add("1");
//		scenario.get(5).add("");
//		scenario.get(5).add("");
//		scenario.get(5).add("");
//		scenario.add(new ArrayList<String>());
//		scenario.get(6).add("1");
//		scenario.get(6).add("");
//		scenario.get(6).add("");
//		scenario.get(6).add("1");
//		scenario.get(6).add("1"); 
//		scenario.add(new ArrayList<String>());
//		scenario.get(7).add("1");
//		scenario.get(7).add("1");
//		scenario.get(7).add("");
//		scenario.get(7).add("");
//		scenario.get(7).add("1"); 
//		for(int i = 0; i < scenario.size(); i++) {
//		plansetServiceE003String.getlargestIacMax(scenario.get(i).get(0),scenario.get(i).get(1),scenario.get(i).get(2),scenario.get(i).get(3),scenario.get(i).get(4));
//		}
//	}
//	@Test
//	public void testgetypeAC1() {
//		ArrayList<PermitProjectSiteInfoEntityTwo> scenario = new ArrayList<PermitProjectSiteInfoEntityTwo>();
//	    scenario.add(null);
//	    PermitProjectSiteInfoEntityTwo scenario1 = new PermitProjectSiteInfoEntityTwo();
//		scenario1.setRooftopACCombinerModel(null);
//		scenario.add(scenario1);
//		PermitProjectSiteInfoEntityTwo scenario2 = new PermitProjectSiteInfoEntityTwo();
//		scenario2.setRooftopACCombinerModel("");
//		scenario.add(scenario2);
//		for(int i = 0; i < scenario.size(); i++) {
//		 plansetServiceE003String.getypeAC1(scenario.get(i));
//		}
//	}
//	@Test
//	public void testgetypeAC2() {
//		ArrayList<PermitProjectSiteInfoEntityTwo> scenario = new ArrayList<PermitProjectSiteInfoEntityTwo>();
//	    scenario.add(null);
//	    PermitProjectSiteInfoEntityTwo scenario1 = new PermitProjectSiteInfoEntityTwo();
//		scenario1.setRooftopACCombinerModel(null);
//		scenario.add(scenario1);
//		PermitProjectSiteInfoEntityTwo scenario2 = new PermitProjectSiteInfoEntityTwo();
//		scenario2.setRooftopACCombinerModel("");
//		scenario.add(scenario2);
//		PermitProjectSiteInfoEntityTwo scenario3 = new PermitProjectSiteInfoEntityTwo();
//		scenario3.setRooftopACCombinerModel("tt");
//		scenario.add(scenario3);
//		List<String> expectedresult = Arrays.asList("","","","NF");
//		for(int i = 0; i < scenario.size(); i++) {
//			String result =plansetServiceE003String.getypeAC2(scenario.get(i),"Non-Fusible");
//		}
//	}
//	
//	@Test
//	public void testgetACDAbbr() {
//		ArrayList<PermitProjectSiteInfoEntityTwo> scenario = new ArrayList<PermitProjectSiteInfoEntityTwo>();
//	    scenario.add(null);
//	    PermitProjectSiteInfoEntityTwo scenario1 = new PermitProjectSiteInfoEntityTwo();
//		scenario1.setRooftopACCombinerModel(null);
//		scenario.add(scenario1);
//		PermitProjectSiteInfoEntityTwo scenario2 = new PermitProjectSiteInfoEntityTwo();
//		scenario2.setRooftopACCombinerModel("");
//		scenario.add(scenario2);
//		for(int i = 0; i < scenario.size(); i++) {
//		plansetServiceE003String.getACDAbbr(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetpdfName() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitAdvEntityResult permitAdvEntityResult1=new PermitAdvEntityResult() ;
//		PermitConduitConductorSectionEntity circuit1= new PermitConduitConductorSectionEntity();
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfoEntityTwo1 =new PermitProjectSiteInfoEntityTwo();
//		PermitHomeSiteEntityResult permitHomeSiteEntityResult1 = new PermitHomeSiteEntityResult();
//		scenario.get(1).add(permitAdvEntityResult1);
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(permitProjectSiteInfoEntityTwo1);
//		scenario.get(1).add(permitHomeSiteEntityResult1);
//		scenario.add(new ArrayList<Object>());
//		PermitAdvEntityResult permitAdvEntityResult2=new PermitAdvEntityResult() ;
//		PermitConduitConductorSectionEntity circuit2= new PermitConduitConductorSectionEntity();
//		circuit2.setComponentOrder("DCJBOX-ACC");
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfoEntityTwo2 =new PermitProjectSiteInfoEntityTwo();
//		PermitHomeSiteEntityResult permitHomeSiteEntityResult2 = new PermitHomeSiteEntityResult();
//		scenario.get(2).add(permitAdvEntityResult2);
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add(permitProjectSiteInfoEntityTwo2);
//		scenario.get(2).add(permitHomeSiteEntityResult2);
//		scenario.add(new ArrayList<Object>());
//		PermitAdvEntityResult permitAdvEntityResult3=new PermitAdvEntityResult() ;
//		PermitConduitConductorSectionEntity circuit3= new PermitConduitConductorSectionEntity();
//		circuit3.setComponentOrder("DCJBOX-ACC");
//		PermitHomeSiteEntityResult permitHomeSiteEntityResult3 = new PermitHomeSiteEntityResult();
//		scenario.get(3).add(permitAdvEntityResult3);
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(permitHomeSiteEntityResult3);
//		scenario.add(new ArrayList<Object>());
//		PermitAdvEntityResult permitAdvEntityResult4=new PermitAdvEntityResult() ;
//		PermitConduitConductorSectionEntity circuit4= new PermitConduitConductorSectionEntity();
//		circuit4.setComponentOrder("DCJBOX-ACSUBPANEL");
//		PermitHomeSiteEntityResult permitHomeSiteEntityResult4 = new PermitHomeSiteEntityResult();
//		scenario.get(4).add(permitAdvEntityResult4);
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(permitHomeSiteEntityResult4);
//		scenario.add(new ArrayList<Object>());
//		PermitAdvEntityResult permitAdvEntityResult5=new PermitAdvEntityResult() ;
//		PermitConduitConductorSectionEntity circuit5= new PermitConduitConductorSectionEntity();
//		circuit5.setComponentOrder("DCJBOX-ACSUBPANEL");
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfoEntityTwo5 =new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfoEntityTwo5.setConnectionPoint("Existing");
//		PermitHomeSiteEntityResult permitHomeSiteEntityResult5 = new PermitHomeSiteEntityResult();
//		scenario.get(5).add(permitAdvEntityResult5);
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(permitProjectSiteInfoEntityTwo5);
//		scenario.get(5).add(permitHomeSiteEntityResult5);
//		scenario.add(new ArrayList<Object>());
//		PermitAdvEntityResult permitAdvEntityResult6=new PermitAdvEntityResult() ;
//		PermitConduitConductorSectionEntity circuit6= new PermitConduitConductorSectionEntity();
//		circuit6.setComponentOrder("DCJBOX-ACSUBPANEL");
//		PermitHomeSiteEntityResult permitHomeSiteEntityResult6 = new PermitHomeSiteEntityResult();
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfoEntityTwo6 =new PermitProjectSiteInfoEntityTwo();
//		scenario.get(6).add(permitAdvEntityResult6);
//		scenario.get(6).add(circuit6);
//		scenario.get(6).add(permitProjectSiteInfoEntityTwo6);
//		scenario.get(6).add(permitHomeSiteEntityResult6);
//		scenario.add(new ArrayList<Object>());
//		PermitAdvEntityResult permitAdvEntityResult7=new PermitAdvEntityResult() ;
//		PermitConduitConductorSectionEntity circuit7= new PermitConduitConductorSectionEntity();
//		circuit7.setComponentOrder("DCJBOX-DCC");
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfoEntityTwo7 =new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfoEntityTwo7.setSolarLocation("Line-side tap");
//		scenario.get(7).add(permitAdvEntityResult7);
//		scenario.get(7).add(circuit7);
//		scenario.get(7).add(permitProjectSiteInfoEntityTwo7);
//		scenario.get(7).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitAdvEntityResult permitAdvEntityResult8=new PermitAdvEntityResult() ;
//		PermitConduitConductorSectionEntity circuit8= new PermitConduitConductorSectionEntity();
//		circuit8.setComponentOrder("DCJBOX-DCC");
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfoEntityTwo8 =new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfoEntityTwo8.setSolarLocation("Line-side tap");
//		PermitHomeSiteEntityResult permitHomeSiteEntityResult8 = new PermitHomeSiteEntityResult();
//		permitHomeSiteEntityResult8.setState("CA");
//		scenario.get(8).add(permitAdvEntityResult8);
//		scenario.get(8).add(circuit8);
//		scenario.get(8).add(permitProjectSiteInfoEntityTwo8);
//		scenario.get(8).add(permitHomeSiteEntityResult8);
//		scenario.add(new ArrayList<Object>());
//		PermitAdvEntityResult permitAdvEntityResult9=new PermitAdvEntityResult() ;
//		PermitConduitConductorSectionEntity circuit9= new PermitConduitConductorSectionEntity();
//		circuit9.setComponentOrder("DCJBOX-DCC");
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfoEntityTwo9=new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfoEntityTwo9.setSolarLocation("Line-side tap");
//		PermitHomeSiteEntityResult permitHomeSiteEntityResult9 = new PermitHomeSiteEntityResult();
//		scenario.get(9).add(permitAdvEntityResult9);
//		scenario.get(9).add(circuit9);
//		scenario.get(9).add(permitProjectSiteInfoEntityTwo9);
//		scenario.get(9).add(permitHomeSiteEntityResult9);
//		scenario.add(new ArrayList<Object>());
//		PermitAdvEntityResult permitAdvEntityResult10=new PermitAdvEntityResult() ;
//		PermitConduitConductorSectionEntity circuit10= new PermitConduitConductorSectionEntity();
//		circuit10.setComponentOrder("DCJBOX-DCC");
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfoEntityTwo10 =new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfoEntityTwo10.setSolarLocation("");
//		PermitHomeSiteEntityResult permitHomeSiteEntityResult10 = new PermitHomeSiteEntityResult();
//		scenario.get(10).add(permitAdvEntityResult10);
//		scenario.get(10).add(circuit10);
//		scenario.get(10).add(permitProjectSiteInfoEntityTwo10);
//		scenario.get(10).add(permitHomeSiteEntityResult10);
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003String.getpdfName((PermitAdvEntityResult) scenario.get(i).get(0),
//					(PermitConduitConductorSectionEntity) scenario.get(i).get(1),
//					(PermitProjectSiteInfoEntityTwo) scenario.get(i).get(2),
//					(PermitHomeSiteEntityResult) scenario.get(i).get(3),3,"ACDAbbr");
//		}
//	}
//	
//	@Test
//	public void testgetServiceVoltage() {
//		ArrayList<PermitHomeSiteEntityResult> scenario = new ArrayList<PermitHomeSiteEntityResult>();
//	    scenario.add(null);
//	    PermitHomeSiteEntityResult scenario1 = new PermitHomeSiteEntityResult();
//		scenario.add(scenario1);
//		for(int i = 0; i < scenario.size(); i++) {
//		 plansetServiceE003String.getServiceVoltage(scenario.get(i));
//		}
//	}
//	
//	
//	
//	@Test
//	public void testGetThreeLineMapping() throws IOException, DocumentException {
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
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003String.getThreeLineMapping((String) scenario.get(i).get(0),
//					(Cmodulev2) scenario.get(i).get(1), (PermitEntity) scenario.get(i).get(2),
//					(List<Inverters>) scenario.get(i).get(3), (PermitArrayEntityResultSecond) scenario.get(i).get(4),
//					(PermitHomeSiteEntityResult) scenario.get(i).get(5), (Inverters) scenario.get(i).get(6),
//					(Inverters) scenario.get(i).get(7), (Inverters) scenario.get(i).get(8),
//					(ACDisconnect) scenario.get(i).get(9), (PermitProjectSiteInfoEntityTwo) scenario.get(i).get(10),
//					(ACCombinerSLC) scenario.get(i).get(11), (ACDisconnect) scenario.get(i).get(12), 0) ;
//		}
//	}
//	
//	
//	@Test
//	public void testTitleBlockMapping() {
//		
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);	
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(0);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite = new PermitHomeSiteEntityResult();
//		scenario.get(1).add(permitHomeSite);
//		scenario.get(1).add(form);	
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		scenario.get(1).add(0);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//		permitHomeSite2.setSiteAddress("SiteAddress");
//		permitHomeSite2.setCity("City") ;
//		permitHomeSite2.setResidenceBindingCategory("") ;
//		permitHomeSite2.setState("NY");
//		scenario.get(2).add(permitHomeSite2);
//		scenario.get(2).add(form);	
//		PermitEntity permitEntity2 = new PermitEntity();
//		scenario.get(2).add(permitEntity2);
//		PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//		scenario.get(2).add(permitArraysEntityResult2);
//		PermitAdvEntityResult PermitAdvEntityInfo2 = new PermitAdvEntityResult();
//		scenario.get(2).add(PermitAdvEntityInfo2);
//		PermitCompanyInfoEntityResult permitCompanyInfo2 =new PermitCompanyInfoEntityResult();
//		scenario.get(2).add(permitCompanyInfo2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		scenario.get(2).add(0);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//		permitHomeSite3.setSiteAddress("SiteAddress");
//		permitHomeSite3.setCity("City") ;
//		permitHomeSite3.setHomeOwnName("HomeOwnName");
//		permitHomeSite3.setResidenceBindingCategory("Other") ;
//		permitHomeSite3.setState("NY");
//		scenario.get(3).add(permitHomeSite3);
//		scenario.get(3).add(form);	
//		PermitEntity permitEntity3 = new PermitEntity();
//		scenario.get(3).add(permitEntity3);
//		PermitArrayEntityResultSecond permitArraysEntityResult3 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult3.setDeviceToIncorporate("System Optimizer");
//		permitArraysEntityResult3.setRoofMounted(true);
//		scenario.get(3).add(permitArraysEntityResult3);
//		PermitAdvEntityResult PermitAdvEntityInfo3 = new PermitAdvEntityResult();
//		PermitAdvEntityInfo3.setSnowLoad("");
//		scenario.get(3).add(PermitAdvEntityInfo3);
//		scenario.get(3).add(null);
//		ElectricalUtilityEntity electricalCompany3 = new ElectricalUtilityEntity();
//		scenario.get(3).add(electricalCompany3);
//		scenario.get(3).add(0);
//		scenario.get(3).add(0);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//		permitHomeSite4.setSiteAddress("SiteAddress");
//		permitHomeSite4.setCity("City") ;
//		permitHomeSite4.setResidenceBindingCategory("") ;
//		permitHomeSite4.setState("NY");
//		permitHomeSite4.setUtilityCompanyName("Other");
//		scenario.get(4).add(permitHomeSite4);
//		scenario.get(4).add(form);	
//		PermitEntity permitEntity4 = new PermitEntity();
//		scenario.get(4).add(permitEntity4);
//		PermitArrayEntityResultSecond permitArraysEntityResult4 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult4.setDeviceToIncorporate("System Optimizer");
//		permitArraysEntityResult4.setPvModuleModEl("pvModuleModEl");
//		scenario.get(4).add(permitArraysEntityResult4);
//		scenario.get(4).add(null);
//		PermitCompanyInfoEntityResult permitCompanyInfo4 =new PermitCompanyInfoEntityResult();
//		scenario.get(4).add(permitCompanyInfo4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(0);
//		scenario.get(4).add(0);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//		permitHomeSite5.setSiteAddress("SiteAddress");
//		permitHomeSite5.setCity("City") ;
//		permitHomeSite5.setResidenceBindingCategory("") ;
//		permitHomeSite5.setState("NY");
//		permitHomeSite5.setUtilityCompanyName("Other");
//		scenario.get(5).add(permitHomeSite5);
//		scenario.get(5).add(form);	
//		PermitEntity permitEntity5 = new PermitEntity();
//		scenario.get(5).add(permitEntity5);
//		PermitArrayEntityResultSecond permitArraysEntityResult5 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult5.setDeviceToIncorporate("System Optimizer");
//		permitArraysEntityResult5.setPvModuleModEl("pvModule:ModEl");
//		scenario.get(5).add(permitArraysEntityResult5);
//		scenario.get(5).add(null);
//		PermitCompanyInfoEntityResult permitCompanyInfo5 =new PermitCompanyInfoEntityResult();
//		scenario.get(5).add(permitCompanyInfo5);
//		scenario.get(5).add(null);
//		scenario.get(5).add(0);
//		scenario.get(5).add(0);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite6 = new PermitHomeSiteEntityResult();
//		permitHomeSite6.setSiteAddress("SiteAddress");
//		permitHomeSite6.setCity("City") ;
//		permitHomeSite6.setResidenceBindingCategory("") ;
//		permitHomeSite6.setState("NY");
//		permitHomeSite6.setUtilityCompanyName("Other");
//		scenario.get(6).add(permitHomeSite6);
//		scenario.get(6).add(form);	
//		PermitEntity permitEntity6 = new PermitEntity();
//		scenario.get(6).add(permitEntity6);
//		PermitArrayEntityResultSecond permitArraysEntityResult6 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult6.setDeviceToIncorporate("System Optimizer");
//		permitArraysEntityResult6.setPvModuleModEl("pvModule:ModEl");
//		permitArraysEntityResult6.setInverterModel("inverterModel");
//		scenario.get(6).add(permitArraysEntityResult6);
//		scenario.get(6).add(null);
//		PermitCompanyInfoEntityResult permitCompanyInfo6 =new PermitCompanyInfoEntityResult();
//		scenario.get(6).add(permitCompanyInfo6);
//		scenario.get(6).add(null);
//		scenario.get(6).add(0);
//		scenario.get(6).add(0);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite7 = new PermitHomeSiteEntityResult();
//		permitHomeSite7.setSiteAddress("SiteAddress");
//		permitHomeSite7.setCity("City") ;
//		permitHomeSite7.setHomeOwnName("HomeOwnName");
//		permitHomeSite7.setResidenceBindingCategory("Other") ;
//		permitHomeSite7.setState("NY");
//		scenario.get(7).add(permitHomeSite7);
//		scenario.get(7).add(form);	
//		PermitEntity permitEntity7 = new PermitEntity();
//		scenario.get(7).add(permitEntity7);
//		PermitArrayEntityResultSecond permitArraysEntityResult7 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult7.setDeviceToIncorporate("AC Modules");
//		permitArraysEntityResult7.setRoofMounted(true);
//		scenario.get(7).add(permitArraysEntityResult7);
//		PermitAdvEntityResult PermitAdvEntityInfo7 = new PermitAdvEntityResult();
//		PermitAdvEntityInfo7.setSnowLoad("");
//		scenario.get(7).add(PermitAdvEntityInfo7);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(0);
//		scenario.get(7).add(0);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite8 = new PermitHomeSiteEntityResult();
//		permitHomeSite8.setSiteAddress("SiteAddress");
//		permitHomeSite8.setCity("City") ;
//		permitHomeSite8.setResidenceBindingCategory("") ;
//		permitHomeSite8.setState("NY");
//		permitHomeSite8.setUtilityCompanyName("Other");
//		scenario.get(8).add(permitHomeSite8);
//		scenario.get(8).add(form);	
//		PermitEntity permitEntity8 = new PermitEntity();
//		scenario.get(8).add(permitEntity8);
//		PermitArrayEntityResultSecond permitArraysEntityResult8 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult8.setDeviceToIncorporate("AC Modules");
//		permitArraysEntityResult8.setPvModuleModEl("pvModuleModEl");
//		scenario.get(8).add(permitArraysEntityResult8);
//		scenario.get(8).add(null);
//		PermitCompanyInfoEntityResult permitCompanyInfo8 =new PermitCompanyInfoEntityResult();
//		scenario.get(8).add(permitCompanyInfo8);
//		scenario.get(8).add(null);
//		scenario.get(8).add(0);
//		scenario.get(8).add(0);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite9 = new PermitHomeSiteEntityResult();
//		permitHomeSite9.setSiteAddress("SiteAddress");
//		permitHomeSite9.setCity("City") ;
//		permitHomeSite9.setResidenceBindingCategory("") ;
//		permitHomeSite9.setState("NY");
//		permitHomeSite9.setUtilityCompanyName("Other");
//		scenario.get(9).add(permitHomeSite9);
//		scenario.get(9).add(form);	
//		PermitEntity permitEntity9 = new PermitEntity();
//		scenario.get(9).add(permitEntity9);
//		PermitArrayEntityResultSecond permitArraysEntityResult9 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult9.setDeviceToIncorporate("AC Modules");
//		permitArraysEntityResult9.setPvModuleModEl("pvModule:ModEl");
//		scenario.get(9).add(permitArraysEntityResult9);
//		scenario.get(9).add(null);
//		PermitCompanyInfoEntityResult permitCompanyInfo9 =new PermitCompanyInfoEntityResult();
//		scenario.get(9).add(permitCompanyInfo9);
//		scenario.get(9).add(null);
//		scenario.get(9).add(0);
//		scenario.get(9).add(0);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003String.titleBlockMapping((int)scenario.get(i).get(7),(PermitHomeSiteEntityResult)scenario.get(i).get(0),(PermitArrayEntityResultSecond)scenario.get(i).get(3),(int)scenario.get(i).get(8),(PermitEntity)scenario.get(i).get(2),(AcroFields)scenario.get(i).get(1),"",(PermitCompanyInfoEntityResult)scenario.get(i).get(5),(ElectricalUtilityEntity)scenario.get(i).get(6),0);
//		}
//	}
//	
//	@Test
//	public void testMapContractorInfo() {
//		
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
//			 plansetServiceE003String.mapContractorInfo((EditUserInformations)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),0);
//		}
//	}
//	
//	@Test
//	public void testMapEquipmentTable() {
//		List <Integer> componentListQTY=new ArrayList<Integer>();
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
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
//		componentListQTY.add(1); 
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
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		ACDisconnect acDisconnect1 = new ACDisconnect();
//		scenario.get(1).add(acDisconnect1);
//		scenario.get(1).add(null);
//		PermitArrayEntityResultSecond permitArraysEntityResult1 = new PermitArrayEntityResultSecond();
//		scenario.get(1).add(permitArraysEntityResult1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		ACDisconnect acDisconnect2 = new ACDisconnect();
//		scenario.get(2).add(acDisconnect2);
//		scenario.get(2).add(null);
//		PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult2.setInverterModel("inverter:model");
//		scenario.get(2).add(permitArraysEntityResult2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(3).add(permitProjectSiteInfo3);
//		scenario.get(3).add(null);
//		ACDisconnect acDisconnect3 = new ACDisconnect();
//		acDisconnect3.setManufacturerMappingValue("manufacturerMappingValue");
//		scenario.get(3).add(acDisconnect3);
//		scenario.get(3).add(null);
//		PermitArrayEntityResultSecond permitArraysEntityResult3 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult3.setInverterModel("inverter:model");
//		scenario.get(3).add(permitArraysEntityResult3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo4.setDisconnectModel("disconnect:Model");
//		scenario.get(4).add(permitProjectSiteInfo4);
//		scenario.get(4).add(null);
//		ACDisconnect acDisconnect4 = new ACDisconnect();
//		acDisconnect4.setManufacturerMappingValue("manufacturerMappingValue");
//		acDisconnect4.setDisconnectDeviceType("FUSIBLE DISCONNECT");
//		scenario.get(4).add(acDisconnect4);
//		scenario.get(4).add(null);
//		PermitArrayEntityResultSecond permitArraysEntityResult4 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult4.setInverterModel("inverter:model");
//		scenario.get(4).add(permitArraysEntityResult4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo5.setRoofTopDCDisco("roofTop:DCDisco");
//		scenario.get(5).add(permitProjectSiteInfo5);
//		scenario.get(5).add(null);
//		ACDisconnect acDisconnect5 = new ACDisconnect();
//		acDisconnect5.setManufacturerMappingValue("manufacturerMappingValue");
//		acDisconnect5.setDisconnectDeviceType("FUSIBLE DISCONNECT");
//		scenario.get(5).add(acDisconnect5);
//		scenario.get(5).add(null);
//		PermitArrayEntityResultSecond permitArraysEntityResult5 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult5.setInverterModel("inverter:model");
//		scenario.get(5).add(permitArraysEntityResult5);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		for(int i = 0; i < scenario.size(); i++) {
//		  plansetServiceE003String.mapEquipmentTable(form,componentListQTY,(DCCombinerDisconnectEntity)scenario.get(i).get(0),
//				  (ACCombinerSLC)scenario.get(i).get(1),(Inverters)scenario.get(i).get(2),(DCCombinerDisconnectRequest)scenario.get(i).get(3),
//				  (PermitProjectSiteInfoEntityTwo)scenario.get(i).get(4),(DCCombinerDisconnectRequest)scenario.get(i).get(5),
//				  (ACDisconnect)scenario.get(i).get(6),(Cmodulev2)scenario.get(i).get(7),(PermitArrayEntityResultSecond)scenario.get(i).get(8),
//				  (ACDisconnect)scenario.get(i).get(9),(ACCombinerSLC)scenario.get(i).get(10),0);
//		}
//	}
//	
//	@Test
//	public void testProjectInfoMapping() {
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
//			plansetServiceE003String.projectInfoMapping(form,(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(0),(int)scenario.get(i).get(1));
//		}
//	}
//	
//	@Test
//	public void testPermitHomeMapping() {
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
//			plansetServiceE003String.permitHomeMapping(form,(PermitHomeSiteEntityResult)scenario.get(i).get(0),(int)scenario.get(i).get(1));
//		}
//	}
//	
//	@Test
//	public void testpermitArraysMapping() {
//	
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(form);
//		PermitArrayEntityResultSecond permitArraysEntityResult = new PermitArrayEntityResultSecond();
//		scenario.get(1).add(permitArraysEntityResult);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(form);
//		PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult2.setDeviceToIncorporate("System Optimizer");
//		scenario.get(2).add(permitArraysEntityResult);
//		scenario.get(2).add(null);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			 plansetServiceE003String.permitArraysMapping((AcroFields)scenario.get(i).get(0),(PermitArrayEntityResultSecond)scenario.get(i).get(1),(DCOptimizerEntity)scenario.get(i).get(2),0);
//		}
//	}
//	
//	@Test
//	public void testQuatrepourCentAverageHigh() {
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
//		 plansetServiceE003String.quatrepourCentAverageHigh(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetCircuitEnvironment() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
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
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult2.setRoofMounted(true);
//		scenario.get(2).add(permitArraysEntityResult1);
//		PermitLayoutEntity permitLayoutEntity2=  new PermitLayoutEntity();
//		permitLayoutEntity2.setConduitMounted(true);
//		scenario.get(2).add(permitLayoutEntity2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		for(int i = 0; i < scenario.size(); i++) {
//		 plansetServiceE003String.getCircuitEnvironment((PermitArrayEntityResultSecond)scenario.get(i).get(0),(PermitLayoutEntity)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(Integer)scenario.get(i).get(5),0);
//		}
//	}
//	
//	@Test
//	public void testMapModuleAmpacity() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("UNDERGROUND");
//		scenario.get(1).add(null);
//		for(int i = 0; i < scenario.size(); i++) {
//		plansetServiceE003String.mapModuleAmpacity((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//		}
//	}
//	@Test
//	public void testMapAmpacityJunction() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("ROOFTOP");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		scenario.get(2).add("UNDERGROUND");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("ROOFTOP");
//		scenario.get(3).add(null);
//		scenario.get(3).add("UNDERGROUND");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		for(int i = 0; i < scenario.size(); i++) {
//		plansetServiceE003String.mapAmpacityJunction((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4));
//		}
//	}
//
//	@Test
//	public void testMapBeforeRevision() {
//		List<String> dcTradeSize = new ArrayList<String>();
//		List<Integer> dcNumberOfConductors = new ArrayList<Integer>();
//		List<String> dcCircuitEnvironment = new ArrayList<String>();
//		
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(true);
//		scenario.get(1).add("PV MODULE-");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(true);
//		scenario.get(2).add("JUNCTION BOX-");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(null);
//		scenario.get(3).add("DC COMBINER-");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(null);
//		scenario.get(4).add("DC DISCONNECT-");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(null);
//		scenario.get(5).add("DC/DC CONVERTER");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003String.mapBeforeRevision(form, 1, (Boolean) scenario.get(i).get(0),
//					(String) scenario.get(i).get(1), (PermitConduitConductorSectionEntity) scenario.get(i).get(2), 0, 0,
//					0, (PermitEntity) scenario.get(i).get(3), (EditUserInformations) scenario.get(i).get(4),
//					dcCircuitEnvironment, dcTradeSize, dcNumberOfConductors, 0);
//		}
//	}	
//	
//	//A.B 03-13: CR-2515
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
//						String result = plansetServiceE003String.getGroundSizeValue(conduitSize.get(i),tradeSize.get(j),MinimumACGroundCon.get(k),MinimumACGroundConOther.get(m));
//						System.out.println(conduitSize.get(i)+" " + tradeSize.get(j)+" " + MinimumACGroundCon.get(k)+" " + MinimumACGroundConOther.get(m));
//						System.out.println(i +" "+ j +" " + k +" " + m);
//					}
//				}
//			}
//		}
//
//				
//	}
//	
//	@Test
//	public void testMapAfterRevision() {
//	List<List<Object>> scenario = new ArrayList<List<Object>>();
//	scenario.add(new ArrayList<Object>());
//	scenario.get(0).add(null);
//	scenario.get(0).add(null);
//	scenario.get(0).add(0);
//	scenario.get(0).add(null);
//	scenario.get(0).add(null);
//	scenario.get(0).add(null);
//	scenario.get(0).add(null);
//	
//	scenario.add(new ArrayList<Object>());
//	scenario.get(1).add("PV MODULE-");
//	scenario.get(1).add(null);
//	scenario.get(1).add(0);
//	scenario.get(1).add(null);
//	scenario.get(1).add(null);
//	scenario.get(1).add(null);
//	scenario.get(1).add(null);
//	
//	scenario.add(new ArrayList<Object>());
//	scenario.get(2).add("DC/DC CONVERTER-");
//	scenario.get(2).add(null);
//	scenario.get(2).add(0);
//	scenario.get(2).add(null);
//	scenario.get(2).add(null);
//	scenario.get(2).add(null);
//	scenario.get(2).add(null);
//	
//	scenario.add(new ArrayList<Object>());
//	scenario.get(3).add("JUNCTION BOX-");
//	scenario.get(3).add(null);
//	scenario.get(3).add(0);
//	scenario.get(3).add(null);
//	scenario.get(3).add(null);
//	scenario.get(3).add(null);
//	scenario.get(3).add(null);
//	
//	scenario.add(new ArrayList<Object>());
//	scenario.get(4).add("DC COMBINER-");
//	scenario.get(4).add(null);
//	scenario.get(4).add(0);
//	scenario.get(4).add(null);
//	scenario.get(4).add(null);
//	scenario.get(4).add(null);
//	scenario.get(4).add(null);
//	
//	scenario.add(new ArrayList<Object>());
//	scenario.get(5).add("DC DISCONNECT-");
//	scenario.get(5).add(null);
//	scenario.get(5).add(0);
//	scenario.get(5).add(null);
//	scenario.get(5).add(null);
//	scenario.get(5).add(null);
//	scenario.get(5).add(null);
//	
//	for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003String.mapAfterRevision(form, 1, (String) scenario.get(i).get(0),
//					(PermitConduitConductorSectionEntity) scenario.get(i).get(1), (int) scenario.get(i).get(2),
//					(PermitEntity) scenario.get(i).get(3), (ConduitConductorCircuitEntity) scenario.get(i).get(4),
//					(EditUserInformations) scenario.get(i).get(5),
//					(PermitArrayEntityResultSecond) scenario.get(i).get(6), 0);
//	}
//	}
//	
//	@Test
//	public void testMapExistingCircuits() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003String.mapExistingCircuits(form, 0, (String) scenario.get(i).get(0),
//					(PermitConduitConductorSectionEntity) scenario.get(i).get(1), (PermitEntity) scenario.get(i).get(2),
//					0,(PermitProjectSiteInfoEntityTwo) scenario.get(i).get(3));
//		}
//	}
//	
//
//	@Test
//	public void testGetAccircuitsAmpacity() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(1);
//		scenario.get(0).add(2);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		for(int i = 0; i < scenario.size(); i++) {
//		plansetServiceE003String.getAccircuitsAmpacity((int)scenario.get(i).get(0),(int)scenario.get(i).get(1),(Boolean)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4));
//		}
//	}
//	
//	@Test
//	public void testmapACcircuitsbeforeRevision() {
//		List<String> acTradeSize = new ArrayList<String>();
//		List<Integer> acNumberOfConductors = new ArrayList<Integer>();
//		List<String> acCircuitEnvironment = new ArrayList<String>();
//		
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
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
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("JUNCTION BOX-");
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
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add("AC DISCONNECT-");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add("AC DISCONNECTTwo-");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add("PRODUCTION METER-");
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
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003String.mapACcircuitsbeforeRevision(form, 1, (String) scenario.get(i).get(0),
//					(PermitConduitConductorSectionEntity) scenario.get(i).get(1), (EditUserInformations) scenario.get(i).get(2),
//					 (PermitEntity) scenario.get(i).get(3), 
//					acCircuitEnvironment, acTradeSize, acNumberOfConductors, 0,(PermitProjectSiteInfoEntityTwo) scenario.get(i).get(4));
//		}
//	}
//	
//	@Test
//	public void testmapACcircuitsafterRevision() {
//		
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
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
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("JUNCTION BOX-");
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
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add("AC DISCONNECT-");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add("AC DISCONNECTTwo-");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add("PRODUCTION METER-");
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
//		
//		for (int i = 0; i < scenario.size(); i++) {
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
//			plansetServiceE003String.getACAmpacityCorrectionB2aMultipleString(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetACAmpacityCorrectionB2aMultipleMicro() {
//		ArrayList<String> scenario = new ArrayList<String>();
//		scenario.add(null);
//		scenario.add("xx");
//		scenario.add("350.11");
//		scenario.add("33");
//		scenario.add("10000000");
//		scenario.add("350,11");
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE003String.getACAmpacityCorrectionB2aMultipleMicro(scenario.get(i));
//		}
//	}
//	@Test
//	public void testgetACAmpacityCorrectionB2aMultipleMicroDouble() {
//		List<Double> scenario = Arrays.asList(24.0);
//		for(int i = 0; i < scenario.size(); i++) {
//			 String ampacityCorrection =plansetServiceE003String.getACAmpacityCorrectionB2aMultipleMicro(scenario.get(i));
//		
//		}
//	}
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
//			 plansetServiceE003String.getDCAmpacityCorrectionB3aString((PermitConduitConductorSectionEntity)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//		}
//		
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
//			 plansetServiceE003String.getDCAmpacityCorrectionB3aMicro((PermitConduitConductorSectionEntity)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//		}
//		
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
//		plansetServiceE003String.getCorrectionB3aString(scenario.get(i));
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
//		plansetServiceE003String.getCorrectionB3aMicro(scenario.get(i));
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
//		plansetServiceE003String.getDCAmpacityCorrectionB2aMultiple(scenario.get(i));
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
//		plansetServiceE003String.getDCAmpacityCorrectionB2aString(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetDCAmpacityCorrectionB2aMicro() {
//		List<Double> scenario = Arrays.asList(24.0);
//		for(int i = 0; i < scenario.size(); i++) {
//			 String ampacityCorrection =plansetServiceE003String.getDCAmpacityCorrectionB2aMicro(scenario.get(i));
//		
//		}
//	}
//	
//	@Test
//	public void testgetACAmpacityCorrectionB2aMicroDouble() {
//	List<Double> scenario = Arrays.asList(24.0);
//	for(int i = 0; i < scenario.size(); i++) {
//		 String ampacityCorrection =plansetServiceE003String.getACAmpacityCorrectionB2aMicro(scenario.get(i));
//	
//	}
//	}
//	
//	@Test
//	public void testgetACAmpacityCorrectionB2aMicro() {
//	ArrayList<String> scenario = new ArrayList<String>();
//	scenario.add(null);
//	scenario.add("xx");
//	scenario.add("350.11");
//	scenario.add("33");
//	scenario.add("10000000");
//	scenario.add("350,11");
//	for(int i = 0; i < scenario.size(); i++) {
//	plansetServiceE003String.getACAmpacityCorrectionB2aMicro(scenario.get(i));
//	}
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
//		String r=plansetServiceE003String.getConduitSizeEMT((String)scenario.get(i).get(0),(int)scenario.get(i).get(1));
//		}
//	}
//	
//	@Test
//	public void testgetConduitSizePVC() {
//		
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(1);
//		for(int i = 0; i < scenario.size(); i++) {
//		plansetServiceE003String.getConduitSizePVC((String)scenario.get(i).get(0),(Integer)scenario.get(i).get(1));
//	}
//	}
//
//}
