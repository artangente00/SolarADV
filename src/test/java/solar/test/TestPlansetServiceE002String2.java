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
//import com.PlayGroundAdv.Solar.Entity.Inverters;
//import com.PlayGroundAdv.Solar.Entity.NEC_310_16_B_16;
//import com.PlayGroundAdv.Solar.Entity.PermitConduitConductorSectionEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitLayoutEntity;
//import com.PlayGroundAdv.Solar.Entity.UserLicSectionsEntity;
//import com.PlayGroundAdv.Solar.model.EditUserInformations;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
//import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.PermitService;
//import com.PlayGroundAdv.Solar.Services.PermitServiceEdit;
//import com.PlayGroundAdv.Solar.Services.PlansetServiceE002String2;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//public class TestPlansetServiceE002String2 {
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
//    PlansetServiceE002String2 plansetServiceE002String2 = new PlansetServiceE002String2();
//    
//    PdfReader reader = null;
//   	File fileRe = null;
//   	PdfStamper stamper = null;
//   	AcroFields form = null;
//   	PdfReader readerOrigin= null;
//   	
//     @Before
//     public void setupMock() {
//    	plansetServiceE002String2 = new PlansetServiceE002String2();
//        MockitoAnnotations.initMocks(this);
// 		
// 		try {
// 		reader = new PdfReader(Constants.rapportPlansetFolderUrl +"NEC-PDF/" +"PDF-E002-STRING.pdf" );
// 		fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E002-STRING.pdf"+".pdf");
// 		stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
// 		form = stamper.getAcroFields();
// 		readerOrigin = new PdfReader( Constants.rapportPlansetFolderUrl +"NEC-PDF/" + "PDF-E002-STRING.pdf" );
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
//	public void testgetiacMax() {
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
//		 plansetServiceE002String2.getiacMax(scenario.get(i));
//		}
//	}
//
//	@Test
//	public void testgetiacMax2() {
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
//		 plansetServiceE002String2.getiacMax(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetThirdInverterInfo() {
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
//   		plansetServiceE002String2.getThirdInverterInfo(scenario.get(i));
//   		}
//	}
//	
//	@Test
//	public void testgetFourthInverterInfo() {
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
//   		plansetServiceE002String2.getFourthInverterInfo(scenario.get(i));
//   		}
//	}
//	
//	@Test
//	public void testgetFifthInverterInfo() {
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
//   		plansetServiceE002String2.getFifthInverterInfo(scenario.get(i));
//   		}
//	}
//
//	@Test
//	public void testgetTitleBlockMapping() {
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
//			plansetServiceE002String2.getTitleBlockMapping(form,(PermitHomeSiteEntityResult)scenario.get(i).get(0),(PermitEntity)scenario.get(i).get(1),(String)scenario.get(i).get(2),0);
//		}
//		
//	}
//	
//	@Test
//	public void testgetContractorInfoMapping() {
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
//			  plansetServiceE002String2.getContractorInfoMapping(form,(EditUserInformations)scenario.get(i).get(0),(PermitHomeSiteEntityResult)scenario.get(i).get(2),0);
//		}
//	}
//	@Test
//	public void testgetCircuitEnvironment() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult1 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult1.setRoofMounted(true);
//		scenario.get(1).add(permitArraysEntityResult1);
//		scenario.get(1).add(1);
//		PermitLayoutEntity permitLayoutEntity1 =  new PermitLayoutEntity();
//		scenario.get(1).add(permitLayoutEntity1);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult2.setRoofMounted(true);
//		scenario.get(2).add(permitArraysEntityResult1);
//		PermitLayoutEntity permitLayoutEntity2=  new PermitLayoutEntity();
//		scenario.get(2).add(4);
//		permitLayoutEntity2.setConduitMounted(true);
//		scenario.get(2).add(permitLayoutEntity2);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//		  plansetServiceE002String2.getCircuitEnvironment((PermitArrayEntityResultSecond)scenario.get(i).get(0),(Integer)scenario.get(i).get(1),(PermitLayoutEntity)scenario.get(i).get(2),0);
//		}
//	}
//	
//	@Test
//	public void testmapCircuitOriginandDestination() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(0);
//		scenario.get(1).add("DCcircuit");
//		scenario.get(1).add(0);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//		 plansetServiceE002String2.mapCircuitOriginandDestination((int)scenario.get(i).get(0),form,(String)scenario.get(i).get(1),(int)scenario.get(i).get(2),0);
//		}
//	}
//	
//	@Test
//	public void testmapRequiredAmpacity() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(0);
//	    scenario.get(0).add(null);
//	    scenario.get(0).add(null);
//	    scenario.get(0).add(0);
//	    scenario.get(0).add(0);
//	    
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(0);
//	    scenario.get(1).add("1");
//	    scenario.get(1).add(null);
//	    scenario.get(1).add(0);
//	    scenario.get(1).add(0);
//	    
//	    scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(2);
//	    scenario.get(2).add("1");
//	    scenario.get(2).add(null);
//	    scenario.get(2).add(0);
//	    scenario.get(2).add(2);
//	    
//	    String [] expectedresult = {"","1,56","0"};
//		for(int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002String2.mapRequiredAmpacity((int)scenario.get(i).get(0),form,(String)scenario.get(i).get(1),(Cmodulev2)scenario.get(i).get(2),(int)scenario.get(i).get(3),(int)scenario.get(i).get(4),0);
//		}
//	}
//	
//	@Test
//	public void testmapSystemOptimizerRequiredAmpacity() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(0);
//	    scenario.get(0).add(null);
//	    scenario.get(0).add(null);
//	    scenario.get(0).add(0);
//	    scenario.get(0).add(0);
//	    
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(0);
//	    scenario.get(1).add("1");
//	    scenario.get(1).add("3");
//	    scenario.get(1).add(0);
//	    scenario.get(1).add(0);
//	    
//	    scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(2);
//	    scenario.get(2).add("1");
//	    scenario.get(2).add(null);
//	    scenario.get(2).add(0);
//	    scenario.get(2).add(2);
//	    
//	    scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(2);
//	    scenario.get(3).add("1");
//	    scenario.get(3).add("2");
//	    scenario.get(3).add(1);
//	    scenario.get(3).add(2);
//	    
//	    
//	    scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(1);
//	    scenario.get(4).add(null);
//	    scenario.get(4).add(null);
//	    scenario.get(4).add(0);
//	    scenario.get(4).add(2);
//	    
//	    
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(1);
//		scenario.get(5).add("3");
//		scenario.get(5).add("3");
//		scenario.get(5).add(0);
//		scenario.get(5).add(2);
//		
//	    String [] expectedresult = {"","3,75","","2,5","","4,69"};
//		for(int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002String2.mapSystemOptimizerRequiredAmpacity((int)scenario.get(i).get(0),form,(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(int)scenario.get(i).get(3),(int)scenario.get(i).get(4),0);
//		}
//		
//	}
//	
//	@Test
//	public void testmapTemperatureDerating() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//	    scenario.get(0).add(null);
//	    scenario.get(0).add(null);
//	    scenario.get(0).add(null);
//	    scenario.get(0).add(null);
//	    scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		List<String> dcCircuitEnvironment1 = new ArrayList<String>();
//	    scenario.get(0).add(dcCircuitEnvironment1);
//	    scenario.get(0).add(null);
//	    
//		scenario.add(new ArrayList<Object>());
//	    scenario.get(1).add("DC/DC CONVERTER-");
//	    scenario.get(1).add(null);
//	    scenario.get(1).add(null);
//	    scenario.get(1).add(null);
//	    scenario.get(1).add(null);
//		scenario.get(1).add(null);
//	    scenario.get(1).add(dcCircuitEnvironment1);
//	    scenario.get(1).add(null);
//	    
//	    
//	    String [] expectedresult = {"0","0"};
//		for(int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002String2.mapTemperatureDerating(1,form,(String)scenario.get(i).get(0),(Integer)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(List<String>)scenario.get(i).get(6),(String)scenario.get(i).get(7),0);
//		}
//	}
//	
//	@Test
//	public void testremapTemperatureDerating() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//	    scenario.get(0).add(null);
//	    scenario.get(0).add(null);
//	    scenario.get(0).add(null);
//	    scenario.get(0).add(null);
//	    scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		List<String> dcCircuitEnvironment1 = new ArrayList<String>();
//	    scenario.get(0).add(dcCircuitEnvironment1);
//	    scenario.get(0).add(null);
//	    for(int i = 0; i < scenario.size(); i++) {
//			 plansetServiceE002String2.remapTemperatureDerating(1,form,(String)scenario.get(i).get(0),(Integer)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(List<String>)scenario.get(i).get(6),(String)scenario.get(i).get(7),0);
//		}
//	}
//	
//	@Test
//	public void testmapFillDeratingbeforeRevision() {
//		List<String> dcTradeSize = new ArrayList<String>();
//		List<Integer> dcNumberOfConductors = new ArrayList<Integer>();
//		
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(1);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(true);
//		scenario.get(1).add("PV MODULE-");
//		scenario.get(1).add(null);
//		scenario.get(1).add(1);
//		scenario.get(1).add(1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(1);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(true);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(1);
//		scenario.get(2).add(1);
//		scenario.get(2).add(null);
//		scenario.get(2).add(1);
//		NEC_310_16_B_16 nEC_310_16_B_162 = new NEC_310_16_B_16 ();
//		nEC_310_16_B_162.setNinetyInsulation(12);
//		scenario.get(2).add(nEC_310_16_B_162);
//		scenario.get(2).add(2);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(true);
//		scenario.get(3).add("PV MODULE-");
//		scenario.get(3).add(null);
//		scenario.get(3).add(1);
//		scenario.get(3).add(1);
//		scenario.get(3).add(null);
//		scenario.get(3).add(1);
//		NEC_310_16_B_16 nEC_310_16_B_16 = new NEC_310_16_B_16 ();
//		scenario.get(3).add(nEC_310_16_B_16);
//		scenario.get(3).add(2);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(true);
//		scenario.get(4).add("DC/DC CONVERTER-");
//		scenario.get(4).add(null);
//		scenario.get(4).add(1);
//		scenario.get(4).add(1);
//		scenario.get(4).add(null);
//		scenario.get(4).add(1);
//		NEC_310_16_B_16 nEC_310_16_B_164 = new NEC_310_16_B_16 ();
//		nEC_310_16_B_164.setNinetyInsulation(12);
//		scenario.get(4).add(nEC_310_16_B_164);
//		scenario.get(4).add(2);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(true);
//		scenario.get(5).add("JUNCTION BOX-");
//		scenario.get(5).add(null);
//		scenario.get(5).add(1);
//		scenario.get(5).add(1);
//		scenario.get(5).add(null);
//		scenario.get(5).add(1);
//		scenario.get(5).add(nEC_310_16_B_164);
//		scenario.get(5).add(2);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add(true);
//		scenario.get(6).add("DC COMBINER-");
//		scenario.get(6).add(null);
//		scenario.get(6).add(1);
//		scenario.get(6).add(1);
//		scenario.get(6).add(null);
//		scenario.get(6).add(1);
//		scenario.get(6).add(nEC_310_16_B_164);
//		scenario.get(6).add(2);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add(true);
//		scenario.get(7).add("DC DISCONNECT-");
//		scenario.get(7).add(null);
//		scenario.get(7).add(1);
//		scenario.get(7).add(1);
//		scenario.get(7).add(null);
//		scenario.get(7).add(1);
//		scenario.get(7).add(nEC_310_16_B_164);
//		scenario.get(7).add(2);
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u" + " from NEC_310_16_B_16 u " + " where u.ninetyInsulation > :p1 "))
//				.thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//		when(mockedQuery.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery);
//		
//		for(int i = 0; i < scenario.size(); i++) {
////			plansetServiceE002String2.mapFillDeratingbeforeRevision((Boolean) scenario.get(i).get(0), form, (int) scenario.get(i).get(8),
////					(String) scenario.get(i).get(1), (PermitConduitConductorSectionEntity) scenario.get(i).get(2), (int) scenario.get(i).get(3),
////					(int) scenario.get(i).get(4), (PermitEntity) scenario.get(i).get(5),(int) scenario.get(i).get(6),
////					dcTradeSize,dcNumberOfConductors, (NEC_310_16_B_16) scenario.get(i).get(7), 0);
//		}
//	}
//	
//	@Test
//	public void testmapPVmoduleFillDerating() {
//		List<String> acTradeSize = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//		circuit2.setConductorQty("Other");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//		circuit3.setConductorQty("ConductorQty");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit4 = new PermitConduitConductorSectionEntity();
//		circuit4.setConductorSize("Other");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit5 = new PermitConduitConductorSectionEntity();
//		circuit5.setConductorSize("Per Manufacturer");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(null);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002String2.mapPVmoduleFillDerating(form,1,(PermitConduitConductorSectionEntity)scenario.get(i).get(0),(String)scenario.get(i).get(1),acTradeSize,0);
//		}	
//	}
//	
//	@Test
//	public void testmapOptimizerFillDerating() {
//		List<String> acTradeSize = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//		circuit2.setConductorQtyTwo("Other");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//		circuit3.setConductorQtyTwo("ConductorQtyTwo");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit4 = new PermitConduitConductorSectionEntity();
//		circuit4.setConductorSizeTwo("Other");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit5 = new PermitConduitConductorSectionEntity();
//		circuit5.setConductorSizeTwo("Per Manufacturer");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(null);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002String2.mapOptimizerFillDerating(form,1,(PermitConduitConductorSectionEntity)scenario.get(i).get(0),(String)scenario.get(i).get(1),acTradeSize,0);
//		}	
//	}
//	
//	@Test
//	public void testmapJunctionFillDerating() {
//		List<String> acTradeSize = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//		circuit2.setConductorQtyThree("Other");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//		circuit3.setConductorQtyThree("ConductorQtyThree");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit4 = new PermitConduitConductorSectionEntity();
//		circuit4.setConductorSizeThree("Other");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit5 = new PermitConduitConductorSectionEntity();
//		circuit5.setConductorSizeThree("Per Manufacturer");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(null);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002String2.mapJunctionFillDerating(form,1,(PermitConduitConductorSectionEntity)scenario.get(i).get(0),(String)scenario.get(i).get(1),acTradeSize,0);
//		}	
//	}
//	
//	@Test
//	public void testmapDCcombinerFillDerating() {
//		List<String> acTradeSize = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//		circuit2.setConductorQtyFour("Other");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//		circuit3.setConductorQtyFour("ConductorQtyFour");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit4 = new PermitConduitConductorSectionEntity();
//		circuit4.setConductorSizeFour("Other");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit5 = new PermitConduitConductorSectionEntity();
//		circuit5.setConductorSizeFour("Per Manufacturer");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(null);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002String2.mapDCcombinerFillDerating(form,1,(PermitConduitConductorSectionEntity)scenario.get(i).get(0),(String)scenario.get(i).get(1),acTradeSize,0);
//		}	
//	}
//	
//	@Test
//	public void testmapDCdisconnectFillDerating() {
//		List<String> acTradeSize = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//		circuit2.setConductorQtyFive("Other");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//		circuit3.setConductorQtyFive("ConductorQtyFive");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit4 = new PermitConduitConductorSectionEntity();
//		circuit4.setConductorSizeFive("Other");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit5 = new PermitConduitConductorSectionEntity();
//		circuit5.setConductorSizeFive("Per Manufacturer");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(null);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002String2.mapDCdisconnectFillDerating(form,1,(PermitConduitConductorSectionEntity)scenario.get(i).get(0),(String)scenario.get(i).get(1),acTradeSize,0);
//		}	
//	}
//	
//	@Test
//	public void testampacityMapping() {
//		 List<Integer> dcNumberOfConductors = new  ArrayList<Integer>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("#12 AWG");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		NEC_310_16_B_16 nEC_310_16_B_16 = new NEC_310_16_B_16();
//		scenario.get(2).add(nEC_310_16_B_16);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		NEC_310_16_B_16 nEC_310_16_B_162 = new NEC_310_16_B_16();
//		nEC_310_16_B_162.setNinetyInsulation(45);
//		scenario.get(3).add(nEC_310_16_B_162);
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u"
//				+ " from NEC_310_16_B_16 u "
//				+ " where u.tradeSze = :p1 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		when(mockedQuery.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery);
//		for(int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getSingleResult()).thenReturn((NEC_310_16_B_16)scenario.get(i).get(4));
//			plansetServiceE002String2.ampacityMapping(form, 1, (String) scenario.get(i).get(0),
//					(String) scenario.get(i).get(1), (PermitConduitConductorSectionEntity) scenario.get(i).get(2),
//					(PermitEntity) scenario.get(i).get(3), new ArrayList<Integer>(),
//					 0);
//		}	
//	}
//	
//	@Test
//	public void testmapACrequiredAmpacity() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(0);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo = new PermitProjectSiteInfoEntityTwo ();
//		permitProjectSiteInfo.setIfApplicableSubPanelMainBreakerRating(null);
//		scenario.get(0).add(permitProjectSiteInfo);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(0);
//		scenario.get(1).add(0);
//		scenario.get(1).add(true);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo ();
//		permitProjectSiteInfo2.setIfApplicableSubPanelMainBreakerRating("100");
//		scenario.get(1).add(permitProjectSiteInfo2);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002String2.mapACrequiredAmpacity(form, (int) scenario.get(i).get(0),
//					(int) scenario.get(i).get(1), (Boolean) scenario.get(i).get(2), (String) scenario.get(i).get(3),
//					(String) scenario.get(i).get(4), 0, (PermitProjectSiteInfoEntityTwo) scenario.get(i).get(5));
//		}
//		
//	}
//	@Test
//	public void testmapACcircuitEnvironment() {
//		List<String> acCircuitEnvironment = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(0);
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
//		scenario.get(0).add(acCircuitEnvironment);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(0);
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
//		scenario.get(1).add(acCircuitEnvironment);
//		scenario.get(1).add(0);
//		scenario.get(1).add("ROOFTOP");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(0);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(1);
//		scenario.get(2).add("Test Mount Array");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(acCircuitEnvironment);
//		scenario.get(2).add(0);
//		scenario.get(2).add("ROOFTOP");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(0);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(1);
//		scenario.get(3).add("Test Mount Array");
//		scenario.get(3).add("");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(acCircuitEnvironment);
//		scenario.get(3).add(0);
//		scenario.get(3).add("ROOFTOP");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(0);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(3);
//		scenario.get(4).add("Test Mount Array");
//		scenario.get(4).add("");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(acCircuitEnvironment);
//		scenario.get(4).add(0);
//		scenario.get(4).add("ROOFTOP");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(0);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(3);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(acCircuitEnvironment);
//		scenario.get(5).add(0);
//		scenario.get(5).add("ROOFTOP");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add(0);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(3);
//		scenario.get(6).add("Test Mount Array");
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(acCircuitEnvironment);
//		scenario.get(6).add(0);
//		scenario.get(6).add("ROOFTOP");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add(0);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(3);
//		scenario.get(7).add("Test Mount Array");
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add("MOUNTED AT THE ARRAY");
//		scenario.get(7).add(null);
//		scenario.get(7).add(acCircuitEnvironment);
//		scenario.get(7).add(0);
//		scenario.get(7).add("ROOFTOP");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(8).add(0);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.get(8).add(3);
//		scenario.get(8).add("Test Mount Array");
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.get(8).add("A Proposed AC Combiner Panel (Solar Load Center) MOUNTED AT THE MAIN SERVICE PANEL");
//		scenario.get(8).add(null);
//		scenario.get(8).add(acCircuitEnvironment);
//		scenario.get(8).add(0);
//		scenario.get(8).add("ROOFTOP");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(9).add(0);
//		scenario.get(9).add(null);
//		scenario.get(9).add(null);
//		scenario.get(9).add(null);
//		scenario.get(9).add(null);
//		scenario.get(9).add(3);
//		scenario.get(9).add("Test Mount Array");
//		scenario.get(9).add("");
//		scenario.get(9).add(null);
//		scenario.get(9).add(null);
//		scenario.get(9).add("MOUNTED AT THE ARRAY");
//		scenario.get(9).add(null);
//		scenario.get(9).add(acCircuitEnvironment);
//		scenario.get(9).add(0);
//		scenario.get(9).add("ROOFTOP");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(10).add(0);
//		scenario.get(10).add(null);
//		scenario.get(10).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(10).add(permitProjectSiteInfo);
//		scenario.get(10).add(null);
//		scenario.get(10).add(3);
//		scenario.get(10).add("Test Mount Array");
//		scenario.get(10).add("");
//		scenario.get(10).add(null);
//		scenario.get(10).add(null);
//		scenario.get(10).add("MOUNTED AT THE ARRAY");
//		scenario.get(10).add(null);
//		scenario.get(10).add(acCircuitEnvironment);
//		scenario.get(10).add(0);
//		scenario.get(10).add("ROOFTOP");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(11).add(0);
//		scenario.get(11).add(null);
//		scenario.get(11).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo2.setRooftopACCombinerModel("rooftopACCombinerModel");
//		permitProjectSiteInfo2.setRooftopACCombinerModelTwo("rooftopACCombinerModelTwo");
//		scenario.get(11).add(permitProjectSiteInfo2);
//		scenario.get(11).add(null);
//		scenario.get(11).add(3);
//		scenario.get(11).add("Test Mount Array");
//		scenario.get(11).add("");
//		scenario.get(11).add(null);
//		scenario.get(11).add(null);
//		scenario.get(11).add("MOUNTED AT THE ARRAY");
//		scenario.get(11).add(null);
//		scenario.get(11).add(acCircuitEnvironment);
//		scenario.get(11).add(0);
//		scenario.get(11).add("ROOFTOP");
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002String2.mapACcircuitEnvironment(form,0,(String)scenario.get(i).get(1),(PermitArrayEntityResultSecond)scenario.get(i).get(2),(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(3),(String)scenario.get(i).get(4),0,(String)scenario.get(i).get(6),(String)scenario.get(i).get(7),(String)scenario.get(i).get(8),(String)scenario.get(i).get(9),(String)scenario.get(i).get(10),0,(List<String>)scenario.get(i).get(12),0,(String)scenario.get(i).get(14),0);
//		}
//	}
//	
//	@Test
//	public void testmapACExistingbeforeRevision() {
//		List<Integer> acNumberOfConductors = new ArrayList<Integer>();
//		List<String> acTradeSize = new ArrayList<String>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(0);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		NEC_310_16_B_16 nEC_310_16_B_167 = new NEC_310_16_B_16();
//		nEC_310_16_B_167.setSeventyFiveInsulation(12);
//		scenario.get(1).add(nEC_310_16_B_167);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo ();
//		scenario.get(1).add(permitProjectSiteInfo2);
//		
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u"
//				+ " from NEC_310_16_B_16 u "
//				+ " where u.seventyFiveInsulation > :p1 and u.tradeSze != :p2 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		when(mockedQuery.setMaxResults(Mockito.anyInt())).thenReturn(mockedQuery);
//		for(int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getSingleResult()).thenReturn((NEC_310_16_B_16)scenario.get(i).get(2));
////			plansetServiceE002String2.mapACExistingbeforeRevision(form, (int) scenario.get(i).get(0),
////					 (String) scenario.get(i).get(1),
////					(PermitConduitConductorSectionEntity) scenario.get(i).get(3), (PermitEntity) scenario.get(i).get(3),
////					acTradeSize, acNumberOfConductors, (NEC_310_16_B_16) scenario.get(i).get(4), 0, (PermitProjectSiteInfoEntityTwo) scenario.get(i).get(5));
//		}
//	}
//	
//	@Test
//	public void testmapACcircuitInverter() {
//		List<Integer> acNumberOfConductors = new ArrayList<Integer>();
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
//			plansetServiceE002String2.mapACcircuitInverter(form, (int)scenario.get(i).get(1), (PermitConduitConductorSectionEntity)scenario.get(i).get(2), (String)scenario.get(i).get(0), acTradeSize, acNumberOfConductors, 0);
//		}
//	}
//	@Test
//	public void testmapACcircuitJunction() {
//		List<String> acTradeSize = new ArrayList<String>();
//		List<Integer> acNumberOfConductors = new ArrayList<Integer>();
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
//			plansetServiceE002String2.mapACcircuitJunction(form, (int)scenario.get(i).get(1), (PermitConduitConductorSectionEntity)scenario.get(i).get(2), (String)scenario.get(i).get(0), acTradeSize, acNumberOfConductors, 0);
//		}
//	}
//	@Test
//	public void testmapACcircuitACcombiner() {
//		List<Integer> acNumberOfConductors = new ArrayList<Integer>();
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
//			plansetServiceE002String2.mapACcircuitACcombiner(form, (int)scenario.get(i).get(1), (PermitConduitConductorSectionEntity)scenario.get(i).get(2), (String)scenario.get(i).get(0), acTradeSize, acNumberOfConductors, 0);
//		}
//	}
//	@Test
//	public void testmapACcircuitACdisconnect() {
//		List<Integer> acNumberOfConductors = new ArrayList<Integer>();
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
//			plansetServiceE002String2.mapACcircuitACdisconnect(form, (int)scenario.get(i).get(1), (PermitConduitConductorSectionEntity)scenario.get(i).get(2), (String)scenario.get(i).get(0), acTradeSize, acNumberOfConductors, 0);
//		}
//	}
//	@Test
//	public void testmapACcircuitACdisconnectTwo() {
//		List<Integer> acNumberOfConductors = new ArrayList<Integer>();
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
//			plansetServiceE002String2.mapACcircuitACdisconnect(form, (int)scenario.get(i).get(1), (PermitConduitConductorSectionEntity)scenario.get(i).get(2), (String)scenario.get(i).get(0), acTradeSize, acNumberOfConductors, 0);
//		}
//	}
//	@Test
//	public void testmapACcircuitProduction() {
//		List<Integer> acNumberOfConductors = new ArrayList<Integer>();
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
//			plansetServiceE002String2.mapACcircuitProduction(form, (int)scenario.get(i).get(1), (PermitConduitConductorSectionEntity)scenario.get(i).get(2), (String)scenario.get(i).get(0), acTradeSize, acNumberOfConductors, 0);
//		}
//	}
//	@Test
//	public void testmapACcircuitSubpanel() {
//		List<Integer> acNumberOfConductors = new ArrayList<Integer>();
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
//		scenario.get(2).add(null);
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
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo ();
//		scenario.get(7).add(permitProjectSiteInfo2);
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
//			plansetServiceE002String2.mapACcircuitSubpanel(form, (int) scenario.get(i).get(1), (PermitConduitConductorSectionEntity) scenario.get(i).get(2), (String) scenario.get(i).get(0), acTradeSize, acNumberOfConductors, 0,(PermitProjectSiteInfoEntityTwo) scenario.get(i).get(4));
//		}
//	}
//	@Test
//	public void testmapACcircuitAmpacity() {
//		List<String> acTradeSize = new ArrayList<String>();
//		acTradeSize.add("#10 AWG");
//		acTradeSize.add("#12 AWG");
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
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(5).add(permitProjectSiteInfo);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002String2.mapACcircuitAmpacity(form, (int) scenario.get(i).get(1), (PermitConduitConductorSectionEntity) scenario.get(i).get(2), (String) scenario.get(i).get(0), (PermitEntity) scenario.get(i).get(3), 0, permitProjectSiteInfo);
//		}
//	}
//	@Test
//	public void testmapACexistingafterRevision() {
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
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(5).add(permitProjectSiteInfo);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002String2.mapACexistingafterRevision(form, (int) scenario.get(i).get(1), (String) scenario.get(i).get(0), (PermitConduitConductorSectionEntity) scenario.get(i).get(2), (PermitEntity) scenario.get(i).get(3), (String) scenario.get(i).get(0), acTradeSize, acNumberOfConductors, 0, permitProjectSiteInfo);
//		}
//	}
//	
//	@Test
//	public void testmapDCCircuits() {
//		List<Integer> acNumberOfConductors = new ArrayList<Integer>();
//		List<String> acCircuitEnvironment = new ArrayList<String>();
//		List<String> acTradeSize = new ArrayList<String>();
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
//		scenario.get(0).add(1);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(1);
//		scenario.get(0).add(1);
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
//		scenario.get(0).add(1);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			plansetServiceE002String2.mapDCCircuits(i, form, (String) scenario.get(i).get(1), (String) scenario.get(i).get(2), (int) scenario.get(i).get(3), (int) scenario.get(i).get(4), (String) scenario.get(i).get(5), (Integer) scenario.get(i).get(6), (String) scenario.get(i).get(7), (String) scenario.get(i).get(8), (String) scenario.get(i).get(9), (String) scenario.get(i).get(10), (List<String>) scenario.get(i).get(11), (String) scenario.get(i).get(12), (Boolean) scenario.get(i).get(13), (PermitConduitConductorSectionEntity) scenario.get(i).get(14), (int) scenario.get(i).get(15), (PermitEntity) scenario.get(i).get(16), acTradeSize, acNumberOfConductors, (PermitArrayEntityResultSecond) scenario.get(i).get(17), (AuthentificationEntity) scenario.get(i).get(18), (Cmodulev2) scenario.get(i).get(19), 0);
//		}
//	}
//	
//	@Test
//	public void testmapACCircuits() {
//		List<Integer> acNumberOfConductors = new ArrayList<Integer>();
//		List<String> acCircuitEnvironment = new ArrayList<String>();
//		List<String> acTradeSize = new ArrayList<String>();
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
//		scenario.get(0).add(1);
//		scenario.get(0).add(1);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(1);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(1);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		
//		for(int i = 0; i < scenario.size(); i++) {
//		//	plansetServiceE002String2.mapACCircuits((int) scenario.get(i).get(0), form, (int) scenario.get(i).get(1), (Boolean) scenario.get(i).get(2), (String) scenario.get(i).get(3), (String) scenario.get(i).get(4), (String) scenario.get(i).get(5), (PermitArrayEntityResultSecond) scenario.get(i).get(6), (PermitProjectSiteInfoEntityTwo) scenario.get(i).get(7), (String) scenario.get(i).get(8), (int) scenario.get(i).get(9), (String) scenario.get(i).get(10), (Boolean) scenario.get(i).get(11), (String) scenario.get(i).get(12), (String) scenario.get(i).get(13), (String) scenario.get(i).get(14), (Integer) scenario.get(i).get(15), acCircuitEnvironment,(int) scenario.get(i).get(16), (String) scenario.get(i).get(17), (PermitConduitConductorSectionEntity) scenario.get(i).get(18), (PermitEntity) scenario.get(i).get(19), acTradeSize, acNumberOfConductors, (String) scenario.get(i).get(21), componentListQTY, (AuthentificationEntity) scenario.get(i).get(22), 0);
//		}
//	}
//	
//	
//	@Test
//	public void testgetHightAboveRoof() {
//    List<List<String>> scenario = new ArrayList<List<String>>();
//		
//		scenario.add(new ArrayList<String>());
//		scenario.get(0).add(null);
//	    scenario.get(0).add(null);
//	    
//	    scenario.add(new ArrayList<String>());
//		scenario.get(1).add("OPEN AIR");
//	    scenario.get(1).add(null);
//	    
//	    scenario.add(new ArrayList<String>());
//		scenario.get(2).add("ATTIC");
//	    scenario.get(2).add(null);
//	    
//	    scenario.add(new ArrayList<String>());
//		scenario.get(3).add("ROOFTOP");
//	    scenario.get(3).add(null);
//	    
//	    scenario.add(new ArrayList<String>());
//		scenario.get(4).add("ROOFTOP");
//	    scenario.get(4).add("Trunk Cable");
//	    
//	    
//	    scenario.add(new ArrayList<String>());
//		scenario.get(5).add("ROOFTOP");
//	    scenario.get(5).add("THWN-2");
//	    
//	    String [] expectedresult = {"N/A","N/A","IN ATTIC","N/A","0.5\" – 3.5\"",""};
//		for(int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002String2.getHightAboveRoof(scenario.get(i).get(0),scenario.get(i).get(1));
//		}
//	}
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
//			String result = plansetServiceE002String2.getTempAdder(scenario.get(i));
//		}
//	}
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
//			String result = plansetServiceE002String2.getOperatingTemperature((Integer)scenario.get(i).get(0),(String)scenario.get(i).get(1));
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
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit1= new PermitConduitConductorSectionEntity();
//		scenario.get(1).add(circuit1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit2= new PermitConduitConductorSectionEntity();
//		circuit2.setConduitSize("2");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add("PV MODULE");
//		scenario.get(2).add("3");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit3= new PermitConduitConductorSectionEntity();
//		circuit3.setConduitSize("N/A");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add("PV MODULE");
//		scenario.get(3).add(null);
//		scenario.get(3).add(false);
//		scenario.get(3).add(true);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit4= new PermitConduitConductorSectionEntity();
//		circuit4.setConduitSizeTwo("2");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add("DC/DC CONVERTER");
//		scenario.get(4).add("3");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit5= new PermitConduitConductorSectionEntity();
//		circuit5.setConduitSizeTwo("N/A");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add("DC/DC CONVERTER");
//		scenario.get(5).add(null);
//		scenario.get(5).add(false);
//		scenario.get(5).add(true);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit6= new PermitConduitConductorSectionEntity();
//		circuit6.setConduitSizeThree("2");
//		scenario.get(6).add(circuit6);
//		scenario.get(6).add("JUNCTION BOX");
//		scenario.get(6).add("3");
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit7= new PermitConduitConductorSectionEntity();
//		circuit7.setConduitSizeThree("N/A");
//		scenario.get(7).add(circuit7);
//		scenario.get(7).add("JUNCTION BOX");
//		scenario.get(7).add(null);
//		scenario.get(7).add(false);
//		scenario.get(7).add(true);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit8= new PermitConduitConductorSectionEntity();
//		circuit8.setConduitSizeFour("2");
//		scenario.get(8).add(circuit8);
//		scenario.get(8).add("DC COMBINER");
//		scenario.get(8).add("3");
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit9= new PermitConduitConductorSectionEntity();
//		circuit9.setConduitSizeFour("N/A");
//		scenario.get(9).add(circuit9);
//		scenario.get(9).add("DC COMBINER");
//		scenario.get(9).add(null);
//		scenario.get(9).add(false);
//		scenario.get(9).add(true);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit10= new PermitConduitConductorSectionEntity();
//		circuit10.setConduitSizeFive("2");
//		scenario.get(10).add(circuit10);
//		scenario.get(10).add("DC DISCONNECT");
//		scenario.get(10).add("3");
//		scenario.get(10).add(null);
//		scenario.get(10).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit11= new PermitConduitConductorSectionEntity();
//		circuit11.setConduitSizeFive("N/A");
//		scenario.get(11).add(circuit11);
//		scenario.get(11).add("DC DISCONNECT");
//		scenario.get(11).add(null);
//		scenario.get(11).add(false);
//		scenario.get(11).add(true);
//		
//		
//		List<String> expectedresult = Arrays.asList("","","1.0","N/A","1.0","N/A","1.0","N/A","1.0","N/A","1.0","N/A");
//		for(int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002String2.getDCAmpacityCorrectionB3a((PermitConduitConductorSectionEntity)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(Boolean)scenario.get(i).get(3),(Boolean)scenario.get(i).get(4));
//		}
//	}
//	
//	@Test
//	public void testgetCorrectionB3a() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("0");
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("3");
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("5");
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add("9");
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add("10");
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add("27");
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add("40");
//		scenario.add(new ArrayList<Object>());
//		scenario.get(8).add("782");
//		List<String> expectedresult = Arrays.asList("", "N/A", "1.0", ".80", ".70", ".50", ".45", ".40", ".35");
//		for (int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002String2.getCorrectionB3a((String) scenario.get(i).get(0));
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
//		circuit2.setConduitTypeSix("2");
//		scenario.get(2).add(circuit2);
//		scenario.get(2).add("INVERTER");
//		scenario.get(2).add("3");
//		scenario.get(2).add(1);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit3= new PermitConduitConductorSectionEntity();
//		circuit3.setConduitTypeSix("N/A");
//		scenario.get(3).add(circuit3);
//		scenario.get(3).add("INVERTER");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(false);
//		scenario.get(3).add(true);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit4= new PermitConduitConductorSectionEntity();
//		circuit4.setConduitTypeSeven("2");
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add("JUNCTION BOX");
//		scenario.get(4).add("3");
//		scenario.get(4).add(1);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit5= new PermitConduitConductorSectionEntity();
//		circuit5.setConduitTypeSeven("N/A");
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add("JUNCTION BOX");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(false);
//		scenario.get(5).add(true);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit6= new PermitConduitConductorSectionEntity();
//		circuit6.setConduitTypeEight("2");
//		scenario.get(6).add(circuit6);
//		scenario.get(6).add("AC COMBINER/LOAD CENTER");
//		scenario.get(6).add("3");
//		scenario.get(6).add(1);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit7= new PermitConduitConductorSectionEntity();
//		circuit7.setConduitTypeEight("N/A");
//		scenario.get(7).add(circuit7);
//		scenario.get(7).add("AC COMBINER/LOAD CENTER");
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(false);
//		scenario.get(7).add(true);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit8= new PermitConduitConductorSectionEntity();
//		circuit8.setConduitTypeNine("2");
//		scenario.get(8).add(circuit8);
//		scenario.get(8).add("AC DISCONNECT");
//		scenario.get(8).add("3");
//		scenario.get(8).add(1);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit9= new PermitConduitConductorSectionEntity();
//		circuit9.setConduitTypeNine("N/A");
//		scenario.get(9).add(circuit9);
//		scenario.get(9).add("AC DISCONNECT");
//		scenario.get(9).add(null);
//		scenario.get(9).add(null);
//		scenario.get(9).add(false);
//		scenario.get(9).add(true);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit10= new PermitConduitConductorSectionEntity();
//		circuit10.setConduitTypeTen("2");
//		scenario.get(10).add(circuit10);
//		scenario.get(10).add("PRODUCTION METER");
//		scenario.get(10).add("3");
//		scenario.get(10).add(1);
//		scenario.get(10).add(null);
//		scenario.get(10).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit11= new PermitConduitConductorSectionEntity();
//		circuit11.setConduitTypeTen("N/A");
//		scenario.get(11).add(circuit11);
//		scenario.get(11).add("PRODUCTION METER");
//		scenario.get(11).add(null);
//		scenario.get(11).add(null);
//		scenario.get(11).add(false);
//		scenario.get(11).add(true);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit12= new PermitConduitConductorSectionEntity();
//		circuit12.setConduitTypeEleven("2");
//		scenario.get(12).add(circuit12);
//		scenario.get(12).add("SUB PANEL");
//		scenario.get(12).add("3");
//		scenario.get(12).add(1);
//		scenario.get(12).add(null);
//		scenario.get(12).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitConduitConductorSectionEntity circuit13= new PermitConduitConductorSectionEntity();
//		circuit13.setConduitTypeEleven("N/A");
//		scenario.get(13).add(circuit13);
//		scenario.get(13).add("SUB PANEL");
//		scenario.get(13).add(null);
//		scenario.get(13).add(null);
//		scenario.get(13).add(false);
//		scenario.get(13).add(true);
//		
//		List<String> expectedresult = Arrays.asList("","","1.0","N/A","1.0","N/A","1.0","N/A","1.0","N/A","1.0","N/A","1.0","N/A");
//		for(int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002String2.getACAmpacityCorrectionB3a((PermitConduitConductorSectionEntity)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(Integer)scenario.get(i).get(3),(Boolean)scenario.get(i).get(4),(Boolean)scenario.get(i).get(5));
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
//			String result = plansetServiceE002String2.getACCorrectionB3a((String) scenario.get(i).get(0), (Integer) scenario.get(i).get(1));
//		}
//	}
//	@Test
//	public void testgetDCAmpacityCorrectionB2a() {
//		List<String> scenario = Arrays.asList("24.0");
//		for(int i = 0; i < scenario.size(); i++) {
//			 String ampacityCorrection =plansetServiceE002String2.getDCAmpacityCorrectionB2a(scenario.get(i));
//		
//		}
//	}
//	
//	@Test
//	public void testgetDCAmpacityCorrectionB2aMultiple() {
//		List<String> scenario = Arrays.asList("",null,"22","29","35","36","41","50","51","59","64","67","72","76","85","100");
//		List<String> expectedresult = Arrays.asList("1.00","1.00","1.04","1.00","0.96","0.91","0.87","0.82","0.76","0.71","0.65","0.58","0.50","0.41","0.29","1.00");
//		for(int i = 0; i < scenario.size(); i++) {
//			 String ampacityCorrection =plansetServiceE002String2.getDCAmpacityCorrectionB2aMultiple(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetACAmpacityCorrectionB2a() {
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
//		List<String> expectedresult = Arrays.asList("0.00","0.00","1.05","1.00","0.94","0.88","0.82","0.75","0.67","0.58","0.47","0.33","0.00","0.00");
//		for (int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002String2.getACAmpacityCorrectionB2a((String) scenario.get(i).get(0));
//		}
//		
//	}
//	
//	@Test
//	public void testgetACAmpacityCorrectionB2aMultiple() {
//		List<String> scenario = Arrays.asList("24.0");
//		for(int i = 0; i < scenario.size(); i++) {
//			 String ampacityCorrection =plansetServiceE002String2.getACAmpacityCorrectionB2aMultiple(scenario.get(i));
//		}	
//	}
//	
//	@Test
//	public void testgetTraseZiseDC() {
//		List<Double> scenario = Arrays.asList(0.0,27.0,31.1,44.5,60.0,80.4,120.0,150.6,170.1,199.22);
//		List<String> expectedresult = Arrays.asList("10", "10", "10", "8", "6", "4","2","1/0","2/0","4/0");
//		for (int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002String2.getTraseZiseDC(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetTraseZiseAC() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(0);
//
//		List<String> expectedresult = Arrays.asList("10", "10", "10", "10", "N/A", "1.0");
//		for (int i = 0; i < scenario.size(); i++) {
//			String result = plansetServiceE002String2.getTraseZiseAC((Integer) scenario.get(i).get(0));
//		}
//	}
//	
//}
