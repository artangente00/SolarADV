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
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//
//import com.PlayGroundAdv.Solar.Constants.Constants;
//import com.PlayGroundAdv.Solar.Entity.ACDisconnect;
//import com.PlayGroundAdv.Solar.Entity.ACSubPanelEntity;
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.Cmodulev2;
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.Inverters;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PlansetCustomizeSheets;
//import com.PlayGroundAdv.Solar.Entity.ProposedSubPanel;
//import com.PlayGroundAdv.Solar.Entity.UserLicSectionsEntity;
//import com.PlayGroundAdv.Solar.model.EditUserInformations;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
//import com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
//import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.PermitServiceEdit;
//import com.PlayGroundAdv.Solar.Services.PlanSetServiceP002;
//import com.PlayGroundAdv.Solar.Services.PlansetServiceE003String;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//public class TestPlanSetServiceP002 {
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
//    PlanSetServiceP002 planSetServiceP002 = new PlanSetServiceP002();
//    
//    PdfReader reader = null;
//   	File fileRe = null;
//   	PdfStamper stamper = null;
//   	AcroFields form = null;
//   	PdfReader readerOrigin= null;
//   	
//     @Before
//     public void setupMock() {
//    	 planSetServiceP002 = new PlanSetServiceP002();
//        MockitoAnnotations.initMocks(this);
// 		
// 		try {
// 		reader = new PdfReader(Constants.rapportPlansetFolderUrl +"NEC-PDF/" +"PDF-P002-MICRO"+".pdf" );
// 		fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-P002-MICRO"+".pdf");
// 		stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
// 		form = stamper.getAcroFields();
// 		readerOrigin = new PdfReader( Constants.rapportPlansetFolderUrl +"NEC-PDF/" + "PDF-P002-MICRO.pdf" );
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
//    @Test
//	public void testgetModuleInfo() {
//		ArrayList<Cmodulev2> scenario = new ArrayList<Cmodulev2>();
//	    scenario.add(null);
//	    Cmodulev2 scenario1 = new Cmodulev2();
//	    scenario.add(scenario1);
//	    for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("testgetModuleInfo ["+i+"]");
//			 planSetServiceP002.getModuleInfo(scenario.get(i));
//			}
//	}
//    
//    @Test
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
//			System.out.println("testiacMax ["+i+"]");
//			planSetServiceP002.getiacmax(scenario.get(i));
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
//			System.out.println("testiacMax2 ["+i+"]");
//			planSetServiceP002.getiacmax2(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetsumIacMax() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		List<Inverters> inverters1 = new ArrayList<Inverters>();
//		scenario.get(1).add(inverters1);
//		scenario.get(1).add(1);
//		scenario.get(1).add(0);
//		scenario.add(new ArrayList<Object>());
//		Inverters inverter21 = new Inverters();
//		Inverters inverter22 = new Inverters();
//		Inverters inverter23 = new Inverters();
//		List<Inverters> inverters2 = new ArrayList<Inverters>();
//		inverters2.add(inverter21);
//		inverters2.add(inverter22);
//		inverters2.add(inverter23);
//		scenario.get(2).add(inverters2);
//		scenario.get(2).add(1);
//		scenario.get(2).add(0);
//		scenario.add(new ArrayList<Object>());
//		Inverters inverter31 = new Inverters();
//		inverter31.setIacmax("5");
//		Inverters inverter32 = new Inverters();
//		inverter32.setIacmax("5");
//		Inverters inverter33 = new Inverters();
//		inverter33.setIacmax("5");
//		List<Inverters> inverters3 = new ArrayList<Inverters>();
//		inverters3.add(inverter31);
//		inverters3.add(inverter32);
//		inverters3.add(inverter33);
//		scenario.get(3).add(inverters3);
//		scenario.get(3).add(1);
//		scenario.get(3).add(0);
//		scenario.add(new ArrayList<Object>());
//		Inverters inverter41 = new Inverters();
//		inverter41.setIacmax("5,5");
//		Inverters inverter42 = new Inverters();
//		inverter42.setIacmax("5,5");
//		Inverters inverter43 = new Inverters();
//		inverter43.setIacmax("5,4");
//		List<Inverters> inverters4 = new ArrayList<Inverters>();
//		inverters4.add(inverter41);
//		inverters4.add(inverter42);
//		inverters4.add(inverter43);
//		scenario.get(4).add(inverters4);
//		scenario.get(4).add(1);
//		scenario.get(4).add(0);
//		for(int i = 0; i < scenario.size(); i++) {
//			System.out.println("testiacMax2 ["+i+"]");
//			planSetServiceP002.getSumIacmax((List<Inverters>)scenario.get(i).get(0),(Integer)scenario.get(i).get(1),(Integer)scenario.get(i).get(2));
//		}
//	}
//	
//	@Test
//	public void testgetpaco() {
//		List<Inverters> scenario = new ArrayList<Inverters>();
//		scenario.add(null);
//		Inverters inverter1 = new Inverters();
//		scenario.add(inverter1);
//		Inverters inverter2 = new Inverters();
//		inverter2.setPaco("11");
//		scenario.add(inverter2);
//		Inverters inverter3 = new Inverters();
//		inverter3.setPaco("11,2");
//		scenario.add(inverter3);
//		for(int i = 0; i < scenario.size(); i++) {
//			System.out.println("testgetpaco ["+i+"]");
//			planSetServiceP002.getpaco(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetpaco2() {
//		List<Inverters> scenario = new ArrayList<Inverters>();
//		scenario.add(null);
//		Inverters inverter1 = new Inverters();
//		scenario.add(inverter1);
//		Inverters inverter2 = new Inverters();
//		inverter2.setPaco("11");
//		scenario.add(inverter2);
//		Inverters inverter3 = new Inverters();
//		inverter3.setPaco("11,2");
//		scenario.add(inverter3);
//		for(int i = 0; i < scenario.size(); i++) {
//			System.out.println("testgetpaco2 ["+i+"]");
//			planSetServiceP002.getpaco2(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testgetmicroinverterpaco() {
//		List<Inverters> scenario = new ArrayList<Inverters>();
//		scenario.add(null);
//		Inverters inverter1 = new Inverters();
//		scenario.add(inverter1);
//		Inverters inverter2 = new Inverters();
//		inverter2.setPaco("11");
//		scenario.add(inverter2);
//		Inverters inverter3 = new Inverters();
//		inverter3.setPaco("11,2");
//		scenario.add(inverter3);
//		for(int i = 0; i < scenario.size(); i++) {
//			System.out.println("testgetmicroinverterpaco ["+i+"]");
//			planSetServiceP002.getmicroinverterpaco(scenario.get(i));
//		}
//	}
//	
//	@Test
//	public void testpermitWeatherInfo() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermtiWeatherEntityResult permtiWeather1 = new PermtiWeatherEntityResult();
//		scenario.get(1).add(permtiWeather1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermtiWeatherEntityResult permtiWeather2 = new PermtiWeatherEntityResult();
//		permtiWeather2.setExtremeMinimum("Other");
//		permtiWeather2.setExtremeMinimumOther("11");
//		scenario.get(2).add(permtiWeather2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermtiWeatherEntityResult permtiWeather3 = new PermtiWeatherEntityResult();
//		permtiWeather3.setExtremeMinimum("Other");
//		permtiWeather3.setExtremeMinimumOther("11");
//		scenario.get(3).add(permtiWeather3);
//		Cmodulev2 moduleInfo3= new Cmodulev2();
//		scenario.get(3).add(moduleInfo3);
//		scenario.get(3).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermtiWeatherEntityResult permtiWeather4 = new PermtiWeatherEntityResult();
//		permtiWeather4.setExtremeMinimum("Other");
//		permtiWeather4.setExtremeMinimumOther("11");
//		scenario.get(4).add(permtiWeather4);
//		Cmodulev2 moduleInfo4= new Cmodulev2();
//		moduleInfo4.setGammaR("11");
//		scenario.get(4).add(moduleInfo4);
//		scenario.get(4).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermtiWeatherEntityResult permtiWeather5 = new PermtiWeatherEntityResult();
//		permtiWeather5.setExtremeMinimum("30");
//		scenario.get(5).add(permtiWeather5);
//		Cmodulev2 moduleInfo5= new Cmodulev2();
//		moduleInfo5.setGammaR("11,5");
//		scenario.get(5).add(moduleInfo5);
//		scenario.get(5).add("12.2");
//		for(int i = 0; i < scenario.size(); i++) {
//		  System.out.println("testpermitWeatherInfo ["+i+"]");
//		  planSetServiceP002.permitWeatherInfo((PermtiWeatherEntityResult)scenario.get(i).get(0),(Cmodulev2)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//		}
//	}
//		
//		@Test
//		public void testmapTitleBlock() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit1 = new PermitEntity();
//			PermitHomeSiteEntityResult permitHomeSite1 =new PermitHomeSiteEntityResult();
//			scenario.get(0).add(permitHomeSite1);
//			scenario.get(0).add(permit1);
//			scenario.get(0).add(form);
//			scenario.get(0).add("");
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit2 = new PermitEntity();
//			permit2.setReleasev3("rv3");
//			permit2.setReleasev2("rv2");
//			PermitHomeSiteEntityResult permitHomeSite2 =new PermitHomeSiteEntityResult();
//			permitHomeSite2.setSiteAddress("siteAddress");
//			permitHomeSite2.setCity("city");
//			permitHomeSite2.setState("NY");
//			scenario.get(1).add(permitHomeSite2);
//			scenario.get(1).add(permit2);
//			scenario.get(1).add(form);
//			scenario.get(1).add("");
//			scenario.get(1).add(null);
//			ElectricalUtilityEntity electricalCompany2 = new ElectricalUtilityEntity();
//			scenario.get(1).add(electricalCompany2);
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit3 = new PermitEntity();
//			permit3.setReleasev3("rv3");
//			permit3.setReleasev2("rv2");
//			PermitHomeSiteEntityResult permitHomeSite3 =new PermitHomeSiteEntityResult();
//			PermitArrayEntityResultSecond permitArraysEntityResult3= new PermitArrayEntityResultSecond();
//			permitArraysEntityResult3.setPvModuleModEl("pvModuleModEl");
//			permitHomeSite3.setSiteAddress("siteAddress");
//			permitHomeSite3.setCity("city");
//			permitHomeSite3.setState("NY");
//			permitHomeSite3.setUtilityCompanyName("Other");
//			scenario.get(2).add(permitHomeSite3);
//			scenario.get(2).add(permit3);
//			scenario.get(2).add(form);
//			scenario.get(2).add("");
//			scenario.get(2).add(null);
//			ElectricalUtilityEntity electricalCompany3 = new ElectricalUtilityEntity();
//			scenario.get(2).add(electricalCompany3);
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit4 = new PermitEntity();
//			permit4.setReleasev3("rv3");
//			permit4.setReleasev2("rv2");
//			PermitHomeSiteEntityResult permitHomeSite4 =new PermitHomeSiteEntityResult();
//			PermitArrayEntityResultSecond permitArraysEntityResult4= new PermitArrayEntityResultSecond();
//			permitArraysEntityResult4.setPvModuleModEl("manufacturer:model");
//			permitHomeSite4.setSiteAddress("siteAddress");
//			permitHomeSite4.setCity("city");
//			permitHomeSite4.setState("NY");
//			scenario.get(3).add(permitHomeSite4);
//			scenario.get(3).add(permit4);
//			scenario.get(3).add(form);
//			scenario.get(3).add("");
//			PermitCompanyInfoEntityResult permitCompanyInfo4 = new PermitCompanyInfoEntityResult();
//			scenario.get(3).add(permitCompanyInfo4);
//			ElectricalUtilityEntity electricalCompany4 = new ElectricalUtilityEntity();
//			electricalCompany4.setUtilityCompanyName("Oncor Electric Delivery");
//			scenario.get(3).add(electricalCompany4);
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit5 = new PermitEntity();
//			permit5.setReleasev3("rv3");
//			permit5.setReleasev2("rv2");
//			PermitHomeSiteEntityResult permitHomeSite5 =new PermitHomeSiteEntityResult();
//			PermitArrayEntityResultSecond permitArraysEntityResult5= new PermitArrayEntityResultSecond();
//			permitArraysEntityResult5.setPvModuleModEl("manufacturer:model");
//			permitArraysEntityResult5.setInverterModel("inverterModel");
//			permitHomeSite5.setSiteAddress("siteAddress");
//			permitHomeSite5.setCity("city");
//			permitHomeSite5.setState("NY");
//			permitHomeSite5.setUtilityCompanyName("Other");
//			permitHomeSite5.setUtilityCompanyNameOther("Oncor Electric Delivery");
//			scenario.get(4).add(permitHomeSite5);
//			scenario.get(4).add(permit5);
//			scenario.get(4).add(form);
//			scenario.get(4).add("");
//			PermitCompanyInfoEntityResult permitCompanyInfo5 = new PermitCompanyInfoEntityResult();
//			scenario.get(4).add(permitCompanyInfo5);
//			ElectricalUtilityEntity electricalCompany5 = new ElectricalUtilityEntity();
//			scenario.get(4).add(electricalCompany5);
//			for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("testmapTitleBlock ["+i+"]");
//			  planSetServiceP002.mapTitleBlock((PermitHomeSiteEntityResult)scenario.get(i).get(0),(PermitEntity)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),(String)scenario.get(i).get(3),(PermitCompanyInfoEntityResult)scenario.get(i).get(4),(ElectricalUtilityEntity)scenario.get(i).get(5),0);
//			}
//		}
//		
//		@Test
//		public void testmapContractorInfoNullQueryResult() {
//			PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//			permitHomeSite2.setState("CA");
//			EditUserInformations editUserInformations2 = new EditUserInformations();
//			editUserInformations2.setId(1);
//			List<UserLicSectionsEntity> resultlist=null;
//			Query mockedQuery = mock(Query.class);
//			when(em.createQuery("SELECT u from UserLicSectionsEntity u WHERE u.authentificationEntity.id = :p1 AND u.contractorLicenceState= :p2")).thenReturn(mockedQuery);
//	    	when(mockedQuery.setParameter("p1", editUserInformations2.getId())).thenReturn(mockedQuery);
//	    	when(mockedQuery.setParameter("p2", permitHomeSite2.getState() )).thenReturn(mockedQuery);
//	    	when(mockedQuery.getResultList()).thenReturn(resultlist);
//	    	System.out.println("testmapContractorInfoNullQueryResult");
//	    	planSetServiceP002.mapContractorInfo(editUserInformations2,form,permitHomeSite2,0);
//		}
//		@Test
//		public void testmapContractorInfoSingleQueryResult() {
//			PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//			permitHomeSite2.setState("CA");
//			EditUserInformations editUserInformations2 = new EditUserInformations();
//			editUserInformations2.setId(1);
//			UserLicSectionsEntity UserLicSectionsEntity1 = new UserLicSectionsEntity();
//			List<UserLicSectionsEntity> resultlist=new ArrayList<UserLicSectionsEntity>();
//			resultlist.add(UserLicSectionsEntity1);
//			Query mockedQuery = mock(Query.class);
//			when(em.createQuery("SELECT u from UserLicSectionsEntity u WHERE u.authentificationEntity.id = :p1 AND u.contractorLicenceState= :p2")).thenReturn(mockedQuery);
//	    	when(mockedQuery.setParameter("p1", editUserInformations2.getId())).thenReturn(mockedQuery);
//	    	when(mockedQuery.setParameter("p2", permitHomeSite2.getState() )).thenReturn(mockedQuery);
//	    	when(mockedQuery.getResultList()).thenReturn(resultlist);
//	    	when(mockedQuery.getSingleResult()).thenReturn(UserLicSectionsEntity1);
//	    	System.out.println("testmapContractorInfoSingleQueryResult");
//	    	planSetServiceP002.mapContractorInfo(editUserInformations2,form,permitHomeSite2,0);
//		}
//		@Test
//		public void testmapContractorInfoMultipleQueryResult() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite1 = new PermitHomeSiteEntityResult();
//			EditUserInformations editUserInformations1 = new EditUserInformations();
//			scenario.get(0).add(editUserInformations1);
//			scenario.get(0).add(form);
//			scenario.get(0).add(permitHomeSite1);
//	    	scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//			permitHomeSite3.setState("CA");
//			EditUserInformations editUserInformations3 = new EditUserInformations();
//			editUserInformations3.setId(1);
//			scenario.get(1).add(editUserInformations3);
//			scenario.get(1).add(form);
//			scenario.get(1).add(permitHomeSite3);
//			UserLicSectionsEntity UserLicSectionsEntity1 = new UserLicSectionsEntity();
//			UserLicSectionsEntity UserLicSectionsEntity2 = new UserLicSectionsEntity();
//			List<UserLicSectionsEntity> resultlist3=new ArrayList<UserLicSectionsEntity>();
//			resultlist3.add(UserLicSectionsEntity1);
//			resultlist3.add(UserLicSectionsEntity2);
//			Query mockedQuery1 = mock(Query.class);
//			when(em.createQuery("SELECT u from UserLicSectionsEntity u WHERE u.authentificationEntity.id = :p1 AND u.contractorLicenceState= :p2")).thenReturn(mockedQuery1);
//	    	when(mockedQuery1.setParameter("p1", editUserInformations3.getId())).thenReturn(mockedQuery1);
//	    	when(mockedQuery1.setParameter("p2", permitHomeSite3.getState() )).thenReturn(mockedQuery1);
//	    	when(mockedQuery1.getResultList()).thenReturn(resultlist3);
//	    	scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//			permitHomeSite4.setState("CA");
//			EditUserInformations editUserInformations4 = new EditUserInformations();
//			editUserInformations4.setId(1);
//			editUserInformations4.setContractorLicenceState("CA");
//			scenario.get(2).add(editUserInformations4);
//			scenario.get(2).add(form);
//			scenario.get(2).add(permitHomeSite4);
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapContractorInfoMultipleQueryResult ["+i+"]");
//				 planSetServiceP002.mapContractorInfo((EditUserInformations)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),0);
//			}
//		}
//		@Test
//		public void testmapInverterInfo() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(0);
//			scenario.get(0).add(null);
//			scenario.get(0).add(0);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.add(new ArrayList<Object>());
//			scenario.get(1).add(form);
//			List<Inverters> inverters1 = new ArrayList<Inverters>();
//			scenario.get(1).add(inverters1);
//			scenario.get(1).add(0);
//			scenario.get(1).add(null);
//			scenario.get(1).add(0);
//			scenario.get(1).add(1f);
//			scenario.get(1).add("1,2");
//			scenario.get(1).add("1,2");
//			scenario.get(1).add("1,2");
//			scenario.add(new ArrayList<Object>());
//			scenario.get(2).add(form);
//			List<Inverters> inverters2 = new ArrayList<Inverters>();
//			Inverters inverters21 = new Inverters();
//			Inverters inverters22 = new Inverters();
//			Inverters inverters23 = new Inverters();
//			inverters2.add(inverters21);
//			inverters2.add(inverters22);
//			inverters2.add(inverters23);
//			scenario.get(2).add(inverters2);
//			scenario.get(2).add(0);
//			scenario.get(2).add(null);
//			scenario.get(2).add(0);
//			scenario.get(2).add(1f);
//			scenario.get(2).add("1,2");
//			scenario.get(2).add("1,2");
//			scenario.get(2).add("1,2");
//			scenario.add(new ArrayList<Object>());
//			scenario.get(3).add(form);
//			List<Inverters> inverters3 = new ArrayList<Inverters>();
//			Inverters inverters31 = new Inverters();
//			inverters31.setMake("SolarEdge");
//			inverters3.add(inverters31);
//			Inverters inverters32 = new Inverters();
//			inverters32.setMake("SolarEdge");
//			inverters3.add(inverters32);
//			Inverters inverters33 = new Inverters();
//			inverters33.setMake("SolarEdge");
//			inverters3.add(inverters33);
//			scenario.get(3).add(inverters3);
//			scenario.get(3).add(0);
//			scenario.get(3).add(null);
//			scenario.get(3).add(0);
//			scenario.get(3).add(1f);
//			scenario.get(3).add("1,2");
//			scenario.get(3).add("1,2");
//			scenario.get(3).add("1,2");
//			scenario.add(new ArrayList<Object>());
//			scenario.get(4).add(form);
//			List<Inverters> inverters4 = new ArrayList<Inverters>();
//			Inverters inverters41 = new Inverters();
//			inverters41.setMake("SolarEdge");
//			inverters41.setMpptHigh("0,1");
//			inverters4.add(inverters41);
//			Inverters inverters42 = new Inverters();
//			inverters42.setMake("SolarEdge");
//			inverters42.setMpptHigh("0,1");
//			inverters4.add(inverters42);
//			Inverters inverters43 = new Inverters();
//			inverters43.setMake("SolarEdge");
//			inverters43.setMpptHigh("0,1");
//			inverters4.add(inverters43);
//			scenario.get(4).add(inverters4);
//			scenario.get(4).add(0);
//			scenario.get(4).add(null);
//			scenario.get(4).add(0);
//			scenario.get(4).add(1f);
//			scenario.get(4).add("1,2");
//			scenario.get(4).add("1,2");
//			scenario.get(4).add("1,2");
//			scenario.add(new ArrayList<Object>());
//			scenario.get(5).add(form);
//			List<Inverters> inverters5 = new ArrayList<Inverters>();
//			Inverters inverters51 = new Inverters();
//			inverters51.setMake("SolarEdge");
//			inverters51.setMpptHigh("0,1");
//			inverters51.setMpptLow("0,3");
//			inverters5.add(inverters51);
//			Inverters inverters52 = new Inverters();
//			inverters52.setMake("SolarEdge");
//			inverters52.setMpptHigh("0,1");
//			inverters52.setMpptLow("0,3");
//			inverters5.add(inverters52);
//			Inverters inverters53 = new Inverters();
//			inverters53.setMake("SolarEdge");
//			inverters53.setMpptHigh("0,1");
//			inverters52.setMpptLow("0,3");
//			inverters5.add(inverters53);
//			scenario.get(5).add(inverters5);
//			scenario.get(5).add(0);
//			scenario.get(5).add(null);
//			scenario.get(5).add(0);
//			scenario.get(5).add(1f);
//			scenario.get(5).add("1,2");
//			scenario.get(5).add("1,2");
//			scenario.get(5).add("1,2");
//			
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapInverterInfo ["+i+"]");
//				 planSetServiceP002.mapInverterInfo((AcroFields)scenario.get(i).get(0), (List<Inverters>)scenario.get(i).get(1), (int)scenario.get(i).get(2), (Float)scenario.get(i).get(3), (int)scenario.get(i).get(4),( Float)scenario.get(i).get(5), (String)scenario.get(i).get(6), (String)scenario.get(i).get(7), (String)scenario.get(i).get(8),0);
//			}	
//		}
//		
//		@Test
//		public void testgetOCPD() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.add(new ArrayList<Object>());
//			Inverters inverterInfo = new Inverters();
//			Inverters secondInverterInfo = new Inverters();
//			scenario.get(1).add(inverterInfo);
//			scenario.get(1).add(secondInverterInfo);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.add(new ArrayList<Object>());
//			Inverters inverterInfo2 = new Inverters();
//			inverterInfo2.setVac("vac");
//			Inverters secondInverterInfo2 = new Inverters();
//			secondInverterInfo2.setVac("vac");
//			scenario.get(2).add(inverterInfo2);
//			scenario.get(2).add(secondInverterInfo2);
//			scenario.get(2).add(null);
//			scenario.get(2).add(null);
//			scenario.add(new ArrayList<Object>());
//			Inverters inverterInfo3 = new Inverters();
//			inverterInfo3.setVac("vac");
//			Inverters secondInverterInfo3 = new Inverters();
//			secondInverterInfo3.setVac("vac");
//			scenario.get(3).add(inverterInfo3);
//			scenario.get(3).add(secondInverterInfo3);
//			scenario.get(3).add("0");
//			scenario.get(3).add("0");
//			scenario.add(new ArrayList<Object>());
//			Inverters inverterInfo4 = new Inverters();
//			inverterInfo4.setVac("0");
//			Inverters secondInverterInfo4 = new Inverters();
//			secondInverterInfo4.setVac("0");
//			scenario.get(4).add(inverterInfo4);
//			scenario.get(4).add(secondInverterInfo4);
//			scenario.get(4).add("1");
//			scenario.get(4).add("1");
//			for(int i = 0; i < scenario.size(); i++) {
//			  System.out.println("testgetOCPD ["+i+"]");
//			  planSetServiceP002.getOCPD((Inverters)scenario.get(i).get(0),(Inverters)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),0);
//			}
//		}
//		
//		@Test
//		public void testmapiacmax() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.add(new ArrayList<Object>());
//			scenario.get(1).add(null);
//			scenario.get(1).add(form);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.get(1).add("5");
//			scenario.get(1).add("10");
//			scenario.get(1).add(null);
//			scenario.add(new ArrayList<Object>());
//			scenario.get(2).add(null);
//			scenario.get(2).add(form);
//			scenario.get(2).add(null);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo = new PermitProjectSiteInfoEntityTwo();
//			scenario.get(2).add(permitProjectSiteInfo);
//			scenario.get(2).add(null);
//			scenario.get(2).add("5");
//			scenario.get(2).add("10");
//			scenario.get(2).add(null);
//			for(int i = 0; i < scenario.size(); i++) {
//				System.out.println("testmapiacmax ["+i+"]");
//				planSetServiceP002.mapiacmax((ACDisconnect)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(Inverters)scenario.get(i).get(2),(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(3),(Inverters)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6),(String)scenario.get(i).get(7),0);
//			}
//		}
//		
//		@Test
//		public void testmapOperatingVoltage() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.add(new ArrayList<Object>());
//			ACDisconnect acDisconnect1 = new ACDisconnect();
//			Inverters inverterInfo = new Inverters();
//			Inverters secondInverterInfo = new Inverters();
//			ACSubPanelEntity acSubPanel = new ACSubPanelEntity();
//			scenario.get(1).add(acDisconnect1);
//			scenario.get(1).add(form);
//			scenario.get(1).add(inverterInfo);
//			scenario.get(1).add(secondInverterInfo);
//			scenario.get(1).add(acSubPanel);
//			scenario.add(new ArrayList<Object>());
//			ACDisconnect acDisconnect2 = new ACDisconnect();
//			acDisconnect2.setRatedOperationalVoltage("ratedOperationalVoltage");
//			Inverters inverterInfo2 = new Inverters();
//			inverterInfo2.setVac("");
//			Inverters secondInverterInfo2 = new Inverters();
//			secondInverterInfo2.setVac("");
//			ACSubPanelEntity acSubPanel2 = new ACSubPanelEntity();
//			scenario.get(2).add(acDisconnect2);
//			scenario.get(2).add(form);
//			scenario.get(2).add(inverterInfo2);
//			scenario.get(2).add(secondInverterInfo2);
//			scenario.get(2).add(acSubPanel2);
//			scenario.add(new ArrayList<Object>());
//			ACDisconnect acDisconnect3 = new ACDisconnect();
//			acDisconnect3.setRatedOperationalVoltage("/ratedOperationalVoltage");
//			Inverters inverterInfo3 = new Inverters();
//			inverterInfo3.setVac("");
//			Inverters secondInverterInfo3 = new Inverters();
//			secondInverterInfo3.setVac("");
//			ACSubPanelEntity acSubPanel3 = new ACSubPanelEntity();
//			scenario.get(3).add(acDisconnect3);
//			scenario.get(3).add(form);
//			scenario.get(3).add(inverterInfo3);
//			scenario.get(3).add(secondInverterInfo3);
//			scenario.get(3).add(acSubPanel3);
//			for(int i = 0; i < scenario.size(); i++) {
//				System.out.println("testmapOperatingVoltage ["+i+"]");
//				planSetServiceP002.mapOperatingVoltage((ACDisconnect)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(Inverters)scenario.get(i).get(2),(Inverters)scenario.get(i).get(3),(ACSubPanelEntity)scenario.get(i).get(4),0);
//			}
//		}
//		
//		@Test
//		public void testmapTitleBlockMicro() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit1 = new PermitEntity();
//			PermitHomeSiteEntityResult permitHomeSite1 =new PermitHomeSiteEntityResult();
//			scenario.get(0).add(permitHomeSite1);
//			scenario.get(0).add(permit1);
//			scenario.get(0).add(form);
//			scenario.get(0).add("");
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit2 = new PermitEntity();
//			permit2.setReleasev3("rv3");
//			permit2.setReleasev2("rv2");
//			PermitHomeSiteEntityResult permitHomeSite2 =new PermitHomeSiteEntityResult();
//			permitHomeSite2.setSiteAddress("siteAddress");
//			permitHomeSite2.setCity("city");
//			permitHomeSite2.setState("NY");
//			scenario.get(1).add(permitHomeSite2);
//			scenario.get(1).add(permit2);
//			scenario.get(1).add(form);
//			scenario.get(1).add("");
//			scenario.get(1).add(null);
//			ElectricalUtilityEntity electricalCompany2 = new ElectricalUtilityEntity();
//			scenario.get(1).add(electricalCompany2);
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit3 = new PermitEntity();
//			permit3.setReleasev3("rv3");
//			permit3.setReleasev2("rv2");
//			PermitHomeSiteEntityResult permitHomeSite3 =new PermitHomeSiteEntityResult();
//			permitHomeSite3.setSiteAddress("siteAddress");
//			permitHomeSite3.setCity("city");
//			permitHomeSite3.setState("NY");
//			permitHomeSite3.setUtilityCompanyName("Other");
//			scenario.get(2).add(permitHomeSite3);
//			scenario.get(2).add(permit3);
//			scenario.get(2).add(form);
//			scenario.get(2).add("");
//			scenario.get(2).add(null);
//			ElectricalUtilityEntity electricalCompany3 = new ElectricalUtilityEntity();
//			scenario.get(2).add(electricalCompany3);
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit4 = new PermitEntity();
//			permit4.setReleasev3("rv3");
//			permit4.setReleasev2("rv2");
//			PermitHomeSiteEntityResult permitHomeSite4 =new PermitHomeSiteEntityResult();
//			permitHomeSite4.setSiteAddress("siteAddress");
//			permitHomeSite4.setCity("city");
//			permitHomeSite4.setState("NY");
//			scenario.get(3).add(permitHomeSite4);
//			scenario.get(3).add(permit4);
//			scenario.get(3).add(form);
//			scenario.get(3).add("");
//			PermitCompanyInfoEntityResult permitCompanyInfo4 = new PermitCompanyInfoEntityResult();
//			scenario.get(3).add(permitCompanyInfo4);
//			ElectricalUtilityEntity electricalCompany4 = new ElectricalUtilityEntity();
//			electricalCompany4.setUtilityCompanyName("Oncor Electric Delivery");
//			scenario.get(3).add(electricalCompany4);
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit5 = new PermitEntity();
//			permit5.setReleasev3("rv3");
//			permit5.setReleasev2("rv2");
//			PermitHomeSiteEntityResult permitHomeSite5 =new PermitHomeSiteEntityResult();
//			permitHomeSite5.setSiteAddress("siteAddress");
//			permitHomeSite5.setCity("city");
//			permitHomeSite5.setState("NY");
//			permitHomeSite5.setUtilityCompanyName("Other");
//			permitHomeSite5.setUtilityCompanyNameOther("Oncor Electric Delivery");
//			scenario.get(4).add(permitHomeSite5);
//			scenario.get(4).add(permit5);
//			scenario.get(4).add(form);
//			scenario.get(4).add("");
//			PermitCompanyInfoEntityResult permitCompanyInfo5 = new PermitCompanyInfoEntityResult();
//			scenario.get(4).add(permitCompanyInfo5);
//			ElectricalUtilityEntity electricalCompany5 = new ElectricalUtilityEntity();
//			scenario.get(4).add(electricalCompany5);
//			for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("testmapTitleBlockMicro ["+i+"]");
//			 planSetServiceP002.mapTitleBlockMicro((PermitHomeSiteEntityResult)scenario.get(i).get(0),(PermitEntity)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),(String)scenario.get(i).get(3),(PermitCompanyInfoEntityResult)scenario.get(i).get(4),(ElectricalUtilityEntity)scenario.get(i).get(5),0);
//			}
//		}
//		
//		
//		@Test
//		public void testgetiacmaxMicro() {
//			List<Inverters> scenario = new ArrayList<Inverters>();	
//			scenario.add(null);
//			Inverters microInverterInfo1 = new Inverters();
//			scenario.add(microInverterInfo1);
//			Inverters microInverterInfo2 = new Inverters();
//			microInverterInfo2.setIacmax("1,1");
//			scenario.add(microInverterInfo2);
//			Inverters microInverterInfo3 = new Inverters();
//			microInverterInfo3.setIacmax("10");
//			scenario.add(microInverterInfo3);
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testgetiacmaxMicro ["+i+"]");
//				 planSetServiceP002.getiacmaxMicro(scenario.get(i));
//			}
//		}
//		
//		@Test
//		public void testoperatingVoltageMapping() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(0);
//			scenario.get(0).add(null);
//			scenario.add(new ArrayList<Object>());
//			ACDisconnect acDisconnect = new ACDisconnect();
//			PermitHomeSiteEntityResult permitHomeSite = new PermitHomeSiteEntityResult();
//			scenario.get(1).add(acDisconnect);
//			scenario.get(1).add(permitHomeSite);
//			scenario.get(1).add(form);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.get(1).add(0);
//			scenario.get(1).add("null");
//			scenario.add(new ArrayList<Object>());
//			ACDisconnect acDisconnect2 = new ACDisconnect();
//			PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//			permitHomeSite2.setIfServiceVoltage(true);
//			scenario.get(2).add(acDisconnect2);
//			scenario.get(2).add(permitHomeSite2);
//			scenario.get(2).add(form);
//			scenario.get(2).add(null);
//			scenario.get(2).add(null);
//			scenario.get(2).add(0);
//			scenario.get(2).add("11,1");
//			scenario.add(new ArrayList<Object>());
//			ACDisconnect acDisconnect3 = new ACDisconnect();
//			acDisconnect3.setRatedOperationalVoltage("RatedOperational/Voltage");
//			PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//			scenario.get(3).add(acDisconnect3);
//			scenario.get(3).add(permitHomeSite3);
//			scenario.get(3).add(form);
//			scenario.get(3).add(null);
//			scenario.get(3).add(null);
//			scenario.get(3).add(0);
//			scenario.get(3).add("11");
//			scenario.add(new ArrayList<Object>());
//			ACDisconnect acDisconnect4 = new ACDisconnect();
//			acDisconnect4.setRatedOperationalVoltage("RatedOperational/Voltage");
//			PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//			scenario.get(4).add(acDisconnect4);
//			scenario.get(4).add(permitHomeSite4);
//			scenario.get(4).add(form);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4= new PermitProjectSiteInfoEntityTwo(); 
//			ACDisconnect secondacDisconnect4 = new ACDisconnect() ;
//			scenario.get(4).add(permitProjectSiteInfo4);
//			scenario.get(4).add(secondacDisconnect4);
//			scenario.get(4).add(0);
//			scenario.get(4).add("11");
//			scenario.add(new ArrayList<Object>());
//			ACDisconnect acDisconnect5 = new ACDisconnect();
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5= new PermitProjectSiteInfoEntityTwo(); 
//			PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//			permitHomeSite5.setIfServiceVoltage(true);
//			scenario.get(5).add(acDisconnect5);
//			scenario.get(5).add(permitHomeSite5);
//			scenario.get(5).add(form);
//			scenario.get(5).add(permitProjectSiteInfo5);
//			scenario.get(5).add(null);
//			scenario.get(5).add(0);
//			scenario.get(5).add("11,1");
//			scenario.add(new ArrayList<Object>());
//			ACDisconnect acDisconnect6 = new ACDisconnect();
//			acDisconnect6.setRatedOperationalVoltage("RatedOperational/Voltage");
//			PermitHomeSiteEntityResult permitHomeSite6= new PermitHomeSiteEntityResult();
//			scenario.get(6).add(acDisconnect6);
//			scenario.get(6).add(permitHomeSite6);
//			scenario.get(6).add(form);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo6= new PermitProjectSiteInfoEntityTwo(); 
//			permitProjectSiteInfo6.setRooftopACCombinerModelTwo("RooftopACCombinerModelTwo");
//			ACDisconnect secondacDisconnect6 = new ACDisconnect() ;
//			secondacDisconnect6.setRatedOperationalVoltage("RatedOperational/Voltage");
//			scenario.get(6).add(permitProjectSiteInfo6);
//			scenario.get(6).add(secondacDisconnect6);
//			scenario.get(6).add(0);
//			scenario.get(6).add("11,1");
//			for(int i = 0; i < scenario.size(); i++) {
//				System.out.println("testoperatingVoltageMapping ["+i+"]");
//				planSetServiceP002.operatingVoltageMapping((ACDisconnect)scenario.get(i).get(0),(PermitHomeSiteEntityResult)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(3),(ACDisconnect)scenario.get(i).get(4),(int)scenario.get(i).get(5),(String)scenario.get(i).get(6),0);
//			}
//		}
//		
//}
