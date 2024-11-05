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
//import com.PlayGroundAdv.Solar.Entity.ACCombinerSLC;
//import com.PlayGroundAdv.Solar.Entity.ACDisconnect;
//import com.PlayGroundAdv.Solar.Entity.ACSubPanelEntity;
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.Cmodulev2;
//import com.PlayGroundAdv.Solar.Entity.DCOptimizerEntity;
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.Inverters;
//import com.PlayGroundAdv.Solar.Entity.PermitConduitConductorSectionEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PlansetCustomizeSheets;
//import com.PlayGroundAdv.Solar.Entity.ProposedSubPanel;
//import com.PlayGroundAdv.Solar.Entity.UserLicSectionsEntity;
//import com.PlayGroundAdv.Solar.model.DCCombinerDisconnectRequest;
//import com.PlayGroundAdv.Solar.model.EditUserInformations;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
//import com.PlayGroundAdv.Solar.model.PermitArraysEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
//import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.PermitServiceEdit;
//import com.PlayGroundAdv.Solar.Services.PlanSetServiceE001;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//public class TestPlanSetServiceE001 {
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
//    PlanSetServiceE001 planSetServiceE001 = new PlanSetServiceE001();
//    
//    PdfReader reader = null;
//   	File fileRe = null;
//   	PdfStamper stamper = null;
//   	AcroFields form = null;
//   	PdfReader readerOrigin= null;
//   	
//     @Before
//     public void setupMock() {
//     	planSetServiceE001 = new PlanSetServiceE001();
//        MockitoAnnotations.initMocks(this);
// 		
// 		try {
// 		reader = new PdfReader(Constants.rapportPlansetFolderUrl +"NEC-PDF/" +"PDF-E001-"+"STRING"+".pdf" );
// 		fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E001-STRING"+".pdf");
// 		stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
// 		form = stamper.getAcroFields();
// 		readerOrigin = new PdfReader( Constants.rapportPlansetFolderUrl +"NEC-PDF/" + "PDF-E001-STRING.pdf" );
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
//	public void testGetminSerieFuseRating() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult1 = new PermitArrayEntityResultSecond();
//		scenario.get(1).add(permitArraysEntityResult1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult2.setDeviceToIncorporate("Neither");
//		scenario.get(2).add(permitArraysEntityResult2);
//		Cmodulev2 moduleInfo=new Cmodulev2();
//		scenario.get(2).add(moduleInfo);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult3 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult3.setDeviceToIncorporate("Neither");
//		scenario.get(3).add(permitArraysEntityResult3);
//		Cmodulev2 moduleInfo3=new Cmodulev2();
//		moduleInfo3.setGammaR("11");
//		scenario.get(3).add(moduleInfo3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult4 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult4.setDeviceToIncorporate("Neither");
//		scenario.get(4).add(permitArraysEntityResult4);
//		Cmodulev2 moduleInfo4=new Cmodulev2();
//		moduleInfo4.setGammaR("11");
//		scenario.get(4).add(moduleInfo4);
//		PermtiWeatherEntityResult permtiWeather4= new PermtiWeatherEntityResult();
//		scenario.get(4).add(permtiWeather4);
//		scenario.get(4).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult5 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult5.setDeviceToIncorporate("Neither");
//		scenario.get(5).add(permitArraysEntityResult5);
//		Cmodulev2 moduleInfo5=new Cmodulev2();
//		moduleInfo5.setGammaR("11");
//		scenario.get(5).add(moduleInfo5);
//		PermtiWeatherEntityResult permtiWeather5= new PermtiWeatherEntityResult();
//		permtiWeather5.setExtremeMinimum("13");
//		scenario.get(5).add(permtiWeather5);
//		scenario.get(5).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult6 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult6.setDeviceToIncorporate("Neither");
//		scenario.get(6).add(permitArraysEntityResult6);
//		Cmodulev2 moduleInfo6=new Cmodulev2();
//		moduleInfo6.setGammaR("11");
//		scenario.get(6).add(moduleInfo6);
//		PermtiWeatherEntityResult permtiWeather6= new PermtiWeatherEntityResult();
//		permtiWeather6.setExtremeMinimum("13");
//		scenario.get(6).add(permtiWeather6);
//		scenario.get(6).add("5");
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult7 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult7.setDeviceToIncorporate("Neither");
//		scenario.get(7).add(permitArraysEntityResult7);
//		Cmodulev2 moduleInfo7=new Cmodulev2();
//		moduleInfo7.setGammaR("11");
//		scenario.get(7).add(moduleInfo7);
//		PermtiWeatherEntityResult permtiWeather7 =new PermtiWeatherEntityResult();
//		permtiWeather7.setExtremeMinimum("Other");
//		scenario.get(7).add(permtiWeather7);
//		scenario.get(7).add("5");
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult8 = new PermitArrayEntityResultSecond();
//		scenario.get(8).add(permitArraysEntityResult8);
//		Cmodulev2 moduleInfo8=new Cmodulev2();
//		scenario.get(8).add(moduleInfo8);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult9 = new PermitArrayEntityResultSecond();
//		scenario.get(9).add(permitArraysEntityResult9);
//		Cmodulev2 moduleInfo9=new Cmodulev2();
//		moduleInfo9.setGammaR("11");
//		scenario.get(9).add(moduleInfo9);
//		scenario.get(9).add(null);
//		scenario.get(9).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult10 = new PermitArrayEntityResultSecond();
//		scenario.get(10).add(permitArraysEntityResult10);
//		Cmodulev2 moduleInfo10=new Cmodulev2();
//		moduleInfo10.setGammaR("11");
//		scenario.get(10).add(moduleInfo10);
//		PermtiWeatherEntityResult permtiWeather10= new PermtiWeatherEntityResult();
//		scenario.get(10).add(permtiWeather10);
//		scenario.get(10).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult11 = new PermitArrayEntityResultSecond();
//		scenario.get(11).add(permitArraysEntityResult11);
//		Cmodulev2 moduleInfo11=new Cmodulev2();
//		moduleInfo11.setGammaR("11");
//		scenario.get(11).add(moduleInfo11);
//		PermtiWeatherEntityResult permtiWeather11= new PermtiWeatherEntityResult();
//		permtiWeather11.setExtremeMinimum("13");
//		scenario.get(11).add(permtiWeather11);
//		scenario.get(11).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult12 = new PermitArrayEntityResultSecond();
//		scenario.get(12).add(permitArraysEntityResult12);
//		Cmodulev2 moduleInfo12=new Cmodulev2();
//		moduleInfo12.setGammaR("11");
//		scenario.get(12).add(moduleInfo12);
//		PermtiWeatherEntityResult permtiWeather12= new PermtiWeatherEntityResult();
//		permtiWeather12.setExtremeMinimum("13");
//		scenario.get(12).add(permtiWeather12);
//		scenario.get(12).add("5");
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult13 = new PermitArrayEntityResultSecond();
//		scenario.get(13).add(permitArraysEntityResult13);
//		Cmodulev2 moduleInfo13=new Cmodulev2();
//		moduleInfo13.setGammaR("11");
//		scenario.get(13).add(moduleInfo13);
//		PermtiWeatherEntityResult permtiWeather13 =new PermtiWeatherEntityResult();
//		permtiWeather13.setExtremeMinimum("Other");
//		scenario.get(13).add(permtiWeather13);
//		scenario.get(13).add("5");
//		for(int i = 0; i < scenario.size(); i++) {
//			System.out.println("testGetminSerieFuseRating ["+i+"]");
//			planSetServiceE001.getminSerieFuseRating((PermitArrayEntityResultSecond)scenario.get(i).get(0),(Cmodulev2)scenario.get(i).get(1),(PermtiWeatherEntityResult)scenario.get(i).get(2),(String)scenario.get(i).get(3));
//		}
//	}
//
//	@Test
//	public void testgetModuleIfo() {
//		ArrayList<Cmodulev2> scenario = new ArrayList<Cmodulev2>();
//	    scenario.add(null);
//	    Cmodulev2 scenario1 = new Cmodulev2();
//	    scenario.add(scenario1);
//	    for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("testgetModuleIfo ["+i+"]");
//			 planSetServiceE001.getModuleIfo(scenario.get(i));
//			}
//	}
//	
//	@Test
//	public void testgetMicroInverterInfo() {
//		ArrayList<Inverters> scenario = new ArrayList<Inverters>();
//	    scenario.add(null);
//	    Inverters scenario1 = new Inverters();
//	    scenario.add(scenario1);
//	    for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("testgetMicroInverterInfo ["+i+"]");
//			 planSetServiceE001.getMicroInverterInfo(scenario.get(i));
//		}
//		
//	}
//
//	@Test
//	public void testtitleBlockMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		PermitEntity permit1 = new PermitEntity();
//		PermitHomeSiteEntityResult permitHomeSite1 =new PermitHomeSiteEntityResult();
//		scenario.get(0).add(permitHomeSite1);
//		scenario.get(0).add(permit1);
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(permit1);
//		scenario.add(new ArrayList<Object>());
//		PermitEntity permit2 = new PermitEntity();
//		PermitHomeSiteEntityResult permitHomeSite2 =new PermitHomeSiteEntityResult();
//		permitHomeSite2.setSiteAddress("siteAddress");
//		permitHomeSite2.setCity("city");
//		scenario.get(1).add(permitHomeSite2);
//		scenario.get(1).add(permit2);
//		scenario.get(1).add(form);
//		scenario.get(1).add(null);
//		scenario.get(1).add(permit2);
//		for(int i = 0; i < scenario.size(); i++) {
//		 System.out.println("testtitleBlockMapping ["+i+"]");
//		 planSetServiceE001.titleBlockMapping((PermitHomeSiteEntityResult)scenario.get(i).get(0),(PermitEntity)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),(String)scenario.get(i).get(3),(PermitEntity)scenario.get(i).get(4),0);
//		}
//	}
//	
//	@Test
//	public void testcontractorInfoMappingNullQueryResult() {
//		PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//		permitHomeSite2.setState("CA");
//		EditUserInformations editUserInformations2 = new EditUserInformations();
//		editUserInformations2.setId(1);
//		List<UserLicSectionsEntity> resultlist=null;
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u from UserLicSectionsEntity u WHERE u.authentificationEntity.id = :p1 AND u.contractorLicenceState= :p2")).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p1", editUserInformations2.getId())).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p2", permitHomeSite2.getState() )).thenReturn(mockedQuery);
//    	when(mockedQuery.getResultList()).thenReturn(resultlist);
//    	System.out.println("testcontractorInfoMappingNullQueryResult");
//		 planSetServiceE001.contractorInfoMapping(editUserInformations2,form,permitHomeSite2,0);
//	}
//	@Test
//	public void testcontractorInfoMappingSingleQueryResult() {
//		PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//		permitHomeSite2.setState("CA");
//		EditUserInformations editUserInformations2 = new EditUserInformations();
//		editUserInformations2.setId(1);
//		UserLicSectionsEntity UserLicSectionsEntity1 = new UserLicSectionsEntity();
//		List<UserLicSectionsEntity> resultlist=new ArrayList<UserLicSectionsEntity>();
//		resultlist.add(UserLicSectionsEntity1);
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u from UserLicSectionsEntity u WHERE u.authentificationEntity.id = :p1 AND u.contractorLicenceState= :p2")).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p1", editUserInformations2.getId())).thenReturn(mockedQuery);
//    	when(mockedQuery.setParameter("p2", permitHomeSite2.getState() )).thenReturn(mockedQuery);
//    	when(mockedQuery.getResultList()).thenReturn(resultlist);
//    	when(mockedQuery.getSingleResult()).thenReturn(UserLicSectionsEntity1);
//    	System.out.println("testcontractorInfoMappingSingleQueryResult");
//		 planSetServiceE001.contractorInfoMapping(editUserInformations2,form,permitHomeSite2,0);
//	}
//	@Test
//	public void testcontractorInfoMappingMultipleQueryResult() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite1 = new PermitHomeSiteEntityResult();
//		EditUserInformations editUserInformations1 = new EditUserInformations();
//		scenario.get(0).add(editUserInformations1);
//		scenario.get(0).add(form);
//		scenario.get(0).add(permitHomeSite1);
//    	scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//		permitHomeSite3.setState("CA");
//		EditUserInformations editUserInformations3 = new EditUserInformations();
//		editUserInformations3.setId(1);
//		scenario.get(1).add(editUserInformations3);
//		scenario.get(1).add(form);
//		scenario.get(1).add(permitHomeSite3);
//		UserLicSectionsEntity UserLicSectionsEntity1 = new UserLicSectionsEntity();
//		UserLicSectionsEntity UserLicSectionsEntity2 = new UserLicSectionsEntity();
//		List<UserLicSectionsEntity> resultlist3=new ArrayList<UserLicSectionsEntity>();
//		resultlist3.add(UserLicSectionsEntity1);
//		resultlist3.add(UserLicSectionsEntity2);
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from UserLicSectionsEntity u WHERE u.authentificationEntity.id = :p1 AND u.contractorLicenceState= :p2")).thenReturn(mockedQuery1);
//    	when(mockedQuery1.setParameter("p1", editUserInformations3.getId())).thenReturn(mockedQuery1);
//    	when(mockedQuery1.setParameter("p2", permitHomeSite3.getState() )).thenReturn(mockedQuery1);
//    	when(mockedQuery1.getResultList()).thenReturn(resultlist3);
//    	scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//		permitHomeSite4.setState("CA");
//		EditUserInformations editUserInformations4 = new EditUserInformations();
//		editUserInformations4.setId(1);
//		editUserInformations4.setContractorLicenceState("CA");
//		scenario.get(2).add(editUserInformations4);
//		scenario.get(2).add(form);
//		scenario.get(2).add(permitHomeSite4);
//		for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("testcontractorInfoMappingMultipleQueryResult ["+i+"]");
//			 planSetServiceE001.contractorInfoMapping((EditUserInformations)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),0);
//			}
//	}
//	
//	@Test
//	public void testpvModuleMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		Cmodulev2 moduleInfo1 = new Cmodulev2();
//		PermitArrayEntityResultSecond permitArraysEntityResult1 = new PermitArrayEntityResultSecond();
//		scenario.get(0).add(moduleInfo1);
//		scenario.get(0).add(form);
//		scenario.get(0).add(permitArraysEntityResult1);
//		scenario.add(new ArrayList<Object>());
//		Cmodulev2 moduleInfo2 = new Cmodulev2();
//		PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult2.setPvModuleModEl("xx:xx");
//		scenario.get(1).add(moduleInfo2);
//		scenario.get(1).add(form);
//		scenario.get(1).add(permitArraysEntityResult2);
//		for(int i = 0; i < scenario.size(); i++) {
//		 System.out.println("testpvModuleMapping ["+i+"]");
//		   planSetServiceE001.pvModuleMapping((Cmodulev2)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitArrayEntityResultSecond)scenario.get(i).get(2),0);
//		}
//	}
//	
//	@Test
//	public void testoptimizerMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		DCOptimizerEntity dcOptimizer1 = new DCOptimizerEntity();
//		PermitArrayEntityResultSecond permitArraysEntityResult1 = new PermitArrayEntityResultSecond();
//		scenario.get(0).add(permitArraysEntityResult1);
//		scenario.get(0).add(dcOptimizer1);
//		scenario.get(0).add(form);
//		scenario.add(new ArrayList<Object>());
//		DCOptimizerEntity dcOptimizer2 = new DCOptimizerEntity();
//		PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//		scenario.get(1).add(permitArraysEntityResult2);
//		scenario.get(1).add(dcOptimizer2);
//		scenario.get(1).add(form);
//		scenario.add(new ArrayList<Object>());
//		DCOptimizerEntity dcOptimizer3 = new DCOptimizerEntity();
//		PermitArrayEntityResultSecond permitArraysEntityResult3 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult3.setSystemOptimizerModel("ff:");
//		scenario.get(2).add(permitArraysEntityResult3);
//		scenario.get(2).add(dcOptimizer3);
//		scenario.get(2).add(form);
//		scenario.add(new ArrayList<Object>());
//		DCOptimizerEntity dcOptimizer4 = new DCOptimizerEntity();
//		PermitArrayEntityResultSecond permitArraysEntityResult4= new PermitArrayEntityResultSecond();
//		permitArraysEntityResult4.setSystemOptimizerModel("ff:");
//		permitArraysEntityResult4.setDeviceToIncorporate("System Optimizer");
//		scenario.get(3).add(permitArraysEntityResult4);
//		scenario.get(3).add(dcOptimizer4);
//		scenario.get(3).add(form);
//		for(int i = 0; i < scenario.size(); i++) {
//			System.out.println("testoptimizerMapping ["+i+"]");
//			planSetServiceE001.optimizerMapping((PermitArrayEntityResultSecond)scenario.get(i).get(0),(DCOptimizerEntity)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),0);
//	    }
//	}
//	
//	@Test
//	public void testdisconnectOneMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//		DCCombinerDisconnectRequest dcCombinerDisconnect1= new DCCombinerDisconnectRequest();
//		scenario.get(1).add(dcCombinerDisconnect1);
//		scenario.get(1).add(form);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		scenario.get(1).add(0);
//		DCCombinerDisconnectRequest roofTopDCDisconnect1 = new DCCombinerDisconnectRequest();
//		scenario.get(1).add(roofTopDCDisconnect1);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		DCCombinerDisconnectRequest dcCombinerDisconnect2= new DCCombinerDisconnectRequest();
//		dcCombinerDisconnect2.setId(1);
//		scenario.get(2).add(dcCombinerDisconnect2);
//		scenario.get(2).add(form);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		scenario.get(2).add(0);
//		DCCombinerDisconnectRequest roofTopDCDisconnect2 = new DCCombinerDisconnectRequest();
//		scenario.get(2).add(roofTopDCDisconnect2);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(3).add(permitProjectSiteInfo3);
//		DCCombinerDisconnectRequest dcCombinerDisconnect3= new DCCombinerDisconnectRequest();
//		scenario.get(3).add(dcCombinerDisconnect3);
//		scenario.get(3).add(form);
//		List<Inverters> inverters3 = new ArrayList<Inverters>();
//		scenario.get(3).add(inverters3);
//		PermitArrayEntityResultSecond permitArraysEntityResult3 = new PermitArrayEntityResultSecond();
//		scenario.get(3).add(permitArraysEntityResult3);
//		scenario.get(3).add(0);
//		scenario.get(3).add(0);
//		DCCombinerDisconnectRequest roofTopDCDisconnect3 = new DCCombinerDisconnectRequest();
//		roofTopDCDisconnect3.setId(1);
//		scenario.get(3).add(roofTopDCDisconnect3);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(4).add(permitProjectSiteInfo4);
//		DCCombinerDisconnectRequest dcCombinerDisconnect4= new DCCombinerDisconnectRequest();
//		scenario.get(4).add(dcCombinerDisconnect4);
//		scenario.get(4).add(form);
//		List<Inverters> inverters4 = new ArrayList<Inverters>();
//		Inverters inverter41 = new Inverters();
//		inverters4.add(inverter41);
//		scenario.get(4).add(inverters4);
//		PermitArrayEntityResultSecond permitArraysEntityResult4 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult4.setRoofMounted(true);
//		scenario.get(4).add(permitArraysEntityResult4);
//		scenario.get(4).add(0);
//		scenario.get(4).add(0);
//		DCCombinerDisconnectRequest roofTopDCDisconnect4 = new DCCombinerDisconnectRequest();
//		roofTopDCDisconnect4.setId(1);
//		scenario.get(4).add(roofTopDCDisconnect4);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(5).add(permitProjectSiteInfo5);
//		DCCombinerDisconnectRequest dcCombinerDisconnect5= new DCCombinerDisconnectRequest();
//		scenario.get(5).add(dcCombinerDisconnect5);
//		scenario.get(5).add(form);
//		List<Inverters> inverters5 = new ArrayList<Inverters>();
//		Inverters inverter51 = new Inverters();
//		inverters5.add(inverter51);
//		scenario.get(5).add(inverters5);
//		PermitArrayEntityResultSecond permitArraysEntityResult5 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult5.setRoofMounted(true);
//		scenario.get(5).add(permitArraysEntityResult5);
//		scenario.get(5).add(0);
//		scenario.get(5).add(0);
//		DCCombinerDisconnectRequest roofTopDCDisconnect5 = new DCCombinerDisconnectRequest();
//		roofTopDCDisconnect5.setId(1);
//		scenario.get(5).add(roofTopDCDisconnect5);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			System.out.println("testdisconnectOneMapping ["+i+"]");
//			planSetServiceE001.disconnectOneMapping((PermitProjectSiteInfoEntityTwo)scenario.get(i).get(0),(DCCombinerDisconnectRequest)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),(List<Inverters>)scenario.get(i).get(3),(PermitArrayEntityResultSecond)scenario.get(i).get(4),(int)scenario.get(i).get(5),(int)scenario.get(i).get(6),(DCCombinerDisconnectRequest)scenario.get(i).get(7),0);
//	    }	
//	}
//	
//	@Test
//	public void testdisconnectTwoMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo1 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(1).add(permitProjectSiteInfo1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(form);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(1);
//		scenario.get(1).add(1);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo2.setDisconnectModelTwo("disconnect:Model");
//		scenario.get(2).add(permitProjectSiteInfo2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(form);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(1);
//		scenario.get(2).add(1);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo3.setDisconnectModelTwo("disconnect:Model");
//		List<Inverters> inverters = new ArrayList <Inverters>();
//		PermitArrayEntityResultSecond permitArraysEntityResult1 = new PermitArrayEntityResultSecond();
//		scenario.get(3).add(permitProjectSiteInfo3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(form);
//		scenario.get(3).add(inverters);
//		scenario.get(3).add(permitArraysEntityResult1);
//		scenario.get(3).add(1);
//		scenario.get(3).add(1);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo4.setDisconnectModelTwo("disconnect:Model");
//		List<Inverters> inverters4 = new ArrayList <Inverters>();
//		Inverters inverter1 = new Inverters();
//		inverters4.add(inverter1);
//		PermitArrayEntityResultSecond permitArraysEntityResult4 = new PermitArrayEntityResultSecond();
//		scenario.get(4).add(permitProjectSiteInfo4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(form);
//		scenario.get(4).add(inverters4);
//		scenario.get(4).add(permitArraysEntityResult4);
//		scenario.get(4).add(1);
//		scenario.get(4).add(1);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo5.setDisconnectModelTwo("disconnect:Model");
//		List<Inverters> inverters5 = new ArrayList <Inverters>();
//		Inverters inverter11 = new Inverters();
//		inverter11.setVdcmax("11");
//		inverters5.add(inverter11);
//		PermitArrayEntityResultSecond permitArraysEntityResult5 = new PermitArrayEntityResultSecond();
//		scenario.get(5).add(permitProjectSiteInfo5);
//		scenario.get(5).add(null);
//		scenario.get(5).add(form);
//		scenario.get(5).add(inverters5);
//		scenario.get(5).add(permitArraysEntityResult5);
//		scenario.get(5).add(1);
//		scenario.get(5).add(1);
//		for(int i = 0; i < scenario.size(); i++) {
//			System.out.println("testdisconnectTwoMapping ["+i+"]");
//			planSetServiceE001.disconnectTwoMapping((PermitProjectSiteInfoEntityTwo)scenario.get(i).get(0),(PermitArrayEntityResultSecond)scenario.get(i).get(4),(AcroFields)scenario.get(i).get(2),(DCCombinerDisconnectRequest)scenario.get(i).get(1),(List<Inverters>)scenario.get(i).get(3),(int)scenario.get(i).get(5),(int)scenario.get(i).get(6),0);
//	    }	
//	}
//	
//	@Test
//	public void testdisconnectThreeMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo1 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(1).add(permitProjectSiteInfo1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(form);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(1);
//		scenario.get(1).add(1);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo2.setDisconnectModelThree("disconnect:Model");
//		scenario.get(2).add(permitProjectSiteInfo2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(form);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(1);
//		scenario.get(2).add(1);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo3.setDisconnectModelThree("disconnect:Model");
//		List<Inverters> inverters = new ArrayList <Inverters>();
//		PermitArrayEntityResultSecond permitArraysEntityResult1 = new PermitArrayEntityResultSecond();
//		scenario.get(3).add(permitProjectSiteInfo3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(form);
//		scenario.get(3).add(inverters);
//		scenario.get(3).add(permitArraysEntityResult1);
//		scenario.get(3).add(1);
//		scenario.get(3).add(1);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo4.setDisconnectModelThree("disconnect:Model");
//		List<Inverters> inverters4 = new ArrayList <Inverters>();
//		Inverters inverter1 = new Inverters();
//		inverters4.add(inverter1);
//		PermitArrayEntityResultSecond permitArraysEntityResult4 = new PermitArrayEntityResultSecond();
//		scenario.get(4).add(permitProjectSiteInfo4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(form);
//		scenario.get(4).add(inverters4);
//		scenario.get(4).add(permitArraysEntityResult4);
//		scenario.get(4).add(1);
//		scenario.get(4).add(1);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo5.setDisconnectModelThree("disconnect:Model");
//		List<Inverters> inverters5 = new ArrayList <Inverters>();
//		Inverters inverter11 = new Inverters();
//		inverter11.setVdcmax("11");
//		inverters5.add(inverter11);
//		PermitArrayEntityResultSecond permitArraysEntityResult5 = new PermitArrayEntityResultSecond();
//		scenario.get(5).add(permitProjectSiteInfo5);
//		scenario.get(5).add(null);
//		scenario.get(5).add(form);
//		scenario.get(5).add(inverters5);
//		scenario.get(5).add(permitArraysEntityResult5);
//		scenario.get(5).add(1);
//		scenario.get(5).add(1);
//		for(int i = 0; i < scenario.size(); i++) {
//			System.out.println("testdisconnectThreeMapping ["+i+"]");
//			planSetServiceE001.disconnectThreeMapping((PermitProjectSiteInfoEntityTwo)scenario.get(i).get(0),(DCCombinerDisconnectRequest)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),(PermitArrayEntityResultSecond)scenario.get(i).get(4),(List<Inverters>)scenario.get(i).get(3),(int)scenario.get(i).get(5),(int)scenario.get(i).get(6),0);
//	    }	
//	}
//	
//	@Test
//	public void testinverterOneMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		List<Inverters> inverters = new ArrayList <Inverters>();
//		Inverters inverter1 = new Inverters();
//		inverters.add(inverter1);
//		scenario.get(1).add(form);
//		scenario.get(1).add(inverters);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		for(int i = 0; i < scenario.size(); i++) {
//		 System.out.println("testinverterOneMapping ["+i+"]");
//		 planSetServiceE001.inverterOneMapping((AcroFields)scenario.get(i).get(0),( List<Inverters> )scenario.get(i).get(1),(Integer)scenario.get(i).get(2),(PermitArrayEntityResultSecond)scenario.get(i).get(3),0);
//	    }	
//	}
//	
//	@Test
//	public void testinverterTwoMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		List<Inverters> inverters = new ArrayList <Inverters>();
//		Inverters inverter1 = new Inverters();
//		inverters.add(inverter1);
//		inverters.add(inverter1);
//		scenario.get(1).add(form);
//		scenario.get(1).add(inverters);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		for(int i = 0; i < scenario.size(); i++) {
//		 System.out.println("testinverterTwoMapping ["+i+"]");
//		 planSetServiceE001.inverterTwoMapping((AcroFields)scenario.get(i).get(0),( List<Inverters> )scenario.get(i).get(1),(Integer)scenario.get(i).get(2),(PermitArrayEntityResultSecond)scenario.get(i).get(3),0);
//	    }	
//	}
//	
//	@Test
//	public void testacCombinerMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(form);
//		scenario.get(1).add(null);
//		ACCombinerSLC acCombiner1 = new ACCombinerSLC();
//		scenario.get(1).add(acCombiner1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		ACCombinerSLC SLCacCombiner1 = new ACCombinerSLC();
//		scenario.get(1).add(SLCacCombiner1);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(form);
//		scenario.get(2).add(null);
//		ACCombinerSLC acCombiner2 = new ACCombinerSLC();
//		scenario.get(2).add(acCombiner2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		ACCombinerSLC SLCacCombiner2 = new ACCombinerSLC();
//		SLCacCombiner2.setId(1);
//		scenario.get(2).add(SLCacCombiner2);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(form);
//		scenario.get(3).add(null);
//		ACCombinerSLC acCombiner3 = new ACCombinerSLC();
//		scenario.get(3).add(acCombiner3);
//		List<Inverters> inverters3 = new ArrayList <Inverters>();
//		scenario.get(3).add(inverters3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(0);
//		ACCombinerSLC SLCacCombiner3 = new ACCombinerSLC();
//		SLCacCombiner3.setId(1);
//		SLCacCombiner3.setCombinerDeviceType("With Main Breaker");
//		scenario.get(3).add(SLCacCombiner3);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(4).add(permitProjectSiteInfo4);
//		ACCombinerSLC acCombiner4 = new ACCombinerSLC();
//		scenario.get(4).add(acCombiner4);
//		List<Inverters> inverters4 = new ArrayList <Inverters>();
//		Inverters inverter = new Inverters();
//		inverters4.add(inverter);
//		inverters4.add(inverter);
//		scenario.get(4).add(inverters4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(0);
//		ACCombinerSLC SLCacCombiner4 = new ACCombinerSLC();
//		SLCacCombiner4.setId(1);
//		SLCacCombiner4.setCombinerDeviceType("With Main Breaker");
//		scenario.get(4).add(SLCacCombiner4);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(5).add(permitProjectSiteInfo5);
//		ACCombinerSLC acCombiner5 = new ACCombinerSLC();
//		scenario.get(5).add(acCombiner5);
//		List<Inverters> inverters5 = new ArrayList <Inverters>();
//		Inverters inverter5 = new Inverters();
//		inverter5.setIacmax("1,1");
//		inverters5.add(inverter5);
//		inverters5.add(inverter5);
//		scenario.get(5).add(inverters5);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(0);
//		ACCombinerSLC SLCacCombiner5 = new ACCombinerSLC();
//		SLCacCombiner5.setId(1);
//		SLCacCombiner5.setCombinerDeviceType("With Main Breaker");
//		scenario.get(5).add(SLCacCombiner5);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo6 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(6).add(permitProjectSiteInfo6);
//		ACCombinerSLC acCombiner6 = new ACCombinerSLC();
//		scenario.get(6).add(acCombiner6);
//		List<Inverters> inverters6 = new ArrayList <Inverters>();
//		Inverters inverter6 = new Inverters();
//		inverter6.setIacmax("1.1");
//		inverters6.add(inverter6);
//		inverters6.add(inverter6);
//		scenario.get(6).add(inverters6);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(0);
//		ACCombinerSLC SLCacCombiner6 = new ACCombinerSLC();
//		SLCacCombiner6.setId(1);
//		SLCacCombiner6.setCombinerDeviceType("With Main Breaker");
//		scenario.get(6).add(SLCacCombiner6);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo7 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(7).add(permitProjectSiteInfo7);
//		ACCombinerSLC acCombiner7 = new ACCombinerSLC();
//		scenario.get(7).add(acCombiner7);
//		List<Inverters> inverters7 = new ArrayList <Inverters>();
//		Inverters inverter7 = new Inverters();
//		inverter7.setIacmax("1.1");
//		inverters7.add(inverter7);
//		inverters7.add(inverter7);
//		scenario.get(7).add(inverters7);
//		scenario.get(7).add(1);
//		scenario.get(7).add(1);
//		scenario.get(7).add(0);
//		ACCombinerSLC SLCacCombiner7 = new ACCombinerSLC();
//		SLCacCombiner7.setId(1);
//		SLCacCombiner7.setCombinerDeviceType("With Main Breaker");
//		scenario.get(7).add(SLCacCombiner7);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(8).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo8 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(8).add(permitProjectSiteInfo8);
//		ACCombinerSLC acCombiner8 = new ACCombinerSLC();
//		scenario.get(8).add(acCombiner8);
//		List<Inverters> inverters8 = new ArrayList <Inverters>();
//		Inverters inverter8 = new Inverters();
//		inverter8.setIacmax("1.1");
//		inverters8.add(inverter8);
//		inverters8.add(inverter8);
//		inverters8.add(null);
//		scenario.get(8).add(inverters8);
//		scenario.get(8).add(1);
//		scenario.get(8).add(1);
//		scenario.get(8).add(0);
//		ACCombinerSLC SLCacCombiner8 = new ACCombinerSLC();
//		SLCacCombiner8.setId(1);
//		SLCacCombiner8.setCombinerDeviceType("With Main Breaker");
//		scenario.get(8).add(SLCacCombiner8);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(9).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo9 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(9).add(permitProjectSiteInfo9);
//		ACCombinerSLC acCombiner9 = new ACCombinerSLC();
//		scenario.get(9).add(acCombiner9);
//		List<Inverters> inverters9 = new ArrayList <Inverters>();
//		inverters9.add(null);
//		inverters9.add(null);
//		scenario.get(9).add(inverters9);
//		scenario.get(9).add(null);
//		scenario.get(9).add(null);
//		scenario.get(9).add(0);
//		ACCombinerSLC SLCacCombiner9 = new ACCombinerSLC();
//		SLCacCombiner9.setId(1);
//		SLCacCombiner9.setCombinerDeviceType("With Main Breaker");
//		scenario.get(9).add(SLCacCombiner9);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(10).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo10 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(10).add(permitProjectSiteInfo10);
//		ACCombinerSLC acCombiner10 = new ACCombinerSLC();
//		scenario.get(10).add(acCombiner10);
//		List<Inverters> inverters10 = new ArrayList <Inverters>();
//		Inverters inverter10 = new Inverters();
//		Inverters inverter101 = new Inverters();
//		inverter101.setIacmax("1,2");
//		inverter10.setIacmax("1.1");
//		inverters10.add(inverter10);
//		inverters10.add(inverter10);
//		inverters10.add(inverter101);
//		scenario.get(10).add(inverters10);
//		scenario.get(10).add(1);
//		scenario.get(10).add(1);
//		scenario.get(10).add(0);
//		ACCombinerSLC SLCacCombiner10 = new ACCombinerSLC();
//		SLCacCombiner10.setId(1);
//		SLCacCombiner10.setCombinerDeviceType("With Main Breaker");
//		scenario.get(10).add(SLCacCombiner10);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(11).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo11 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(11).add(permitProjectSiteInfo11);
//		ACCombinerSLC acCombiner11 = new ACCombinerSLC();
//		scenario.get(11).add(acCombiner11);
//		List<Inverters> inverters11 = new ArrayList <Inverters>();
//		Inverters inverter11 = new Inverters();
//		inverter11.setIacmax("1.1");
//		inverters11.add(inverter11);
//		inverters11.add(inverter11);
//		inverters11.add(inverter11);
//		inverters11.add(null);
//		scenario.get(11).add(inverters11);
//		scenario.get(11).add(1);
//		scenario.get(11).add(1);
//		scenario.get(11).add(0);
//		ACCombinerSLC SLCacCombiner11 = new ACCombinerSLC();
//		SLCacCombiner11.setId(1);
//		SLCacCombiner11.setCombinerDeviceType("With Main Breaker");
//		scenario.get(11).add(SLCacCombiner11);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(12).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo12 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(12).add(permitProjectSiteInfo12);
//		ACCombinerSLC acCombiner12 = new ACCombinerSLC();
//		scenario.get(12).add(acCombiner12);
//		List<Inverters> inverters12 = new ArrayList <Inverters>();
//		Inverters inverter12 = new Inverters();
//		inverter12.setIacmax("1.1");
//		Inverters inverter122 = new Inverters();
//		inverter122.setIacmax("1,1");
//		inverters12.add(inverter12);
//		inverters12.add(inverter12);
//		inverters12.add(inverter12);
//		inverters12.add(inverter122);
//		scenario.get(12).add(inverters12);
//		scenario.get(12).add(1);
//		scenario.get(12).add(1);
//		scenario.get(12).add(0);
//		ACCombinerSLC SLCacCombiner12 = new ACCombinerSLC();
//		SLCacCombiner12.setId(1);
//		SLCacCombiner12.setCombinerDeviceType("With Main Breaker");
//		scenario.get(12).add(SLCacCombiner12);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(13).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo13 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(13).add(permitProjectSiteInfo13);
//		ACCombinerSLC acCombiner13 = new ACCombinerSLC();
//		scenario.get(13).add(acCombiner13);
//		List<Inverters> inverters13 = new ArrayList <Inverters>();
//		Inverters inverter13 = new Inverters();
//		inverter13.setIacmax("1.1");
//		inverters13.add(inverter13);
//		inverters13.add(inverter13);
//		inverters13.add(inverter13);
//		inverters13.add(inverter13);
//		inverters13.add(null);
//		scenario.get(13).add(inverters13);
//		scenario.get(13).add(1);
//		scenario.get(13).add(1);
//		scenario.get(13).add(0);
//		ACCombinerSLC SLCacCombiner13 = new ACCombinerSLC();
//		SLCacCombiner13.setId(1);
//		SLCacCombiner13.setCombinerDeviceType("With Main Breaker");
//		scenario.get(13).add(SLCacCombiner13);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(14).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo14 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(14).add(permitProjectSiteInfo14);
//		ACCombinerSLC acCombiner14 = new ACCombinerSLC();
//		scenario.get(14).add(acCombiner14);
//		List<Inverters> inverters14 = new ArrayList <Inverters>();
//		Inverters inverter14 = new Inverters();
//		inverter14.setIacmax("1.1");
//		Inverters inverter142 = new Inverters();
//		inverter142.setIacmax("1,1");
//		inverters14.add(inverter14);
//		inverters14.add(inverter14);
//		inverters14.add(inverter14);
//		inverters14.add(inverter14);
//		inverters14.add(inverter142);
//		scenario.get(14).add(inverters14);
//		scenario.get(14).add(1);
//		scenario.get(14).add(1);
//		scenario.get(14).add(0);
//		ACCombinerSLC SLCacCombiner14 = new ACCombinerSLC();
//		SLCacCombiner14.setId(1);
//		SLCacCombiner14.setCombinerDeviceType("With Main Breaker");
//		scenario.get(14).add(SLCacCombiner14);
//
//		scenario.add(new ArrayList<Object>());
//		scenario.get(15).add(form);
//		scenario.get(15).add(null);
//		ACCombinerSLC acCombiner15 = new ACCombinerSLC();
//		acCombiner15.setId(1);
//		scenario.get(15).add(acCombiner15);
//		scenario.get(15).add(null);
//		scenario.get(15).add(null);
//		scenario.get(15).add(null);
//		scenario.get(15).add(0);
//		ACCombinerSLC SLCacCombiner15 = new ACCombinerSLC();
//		scenario.get(15).add(SLCacCombiner15);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(16).add(form);
//		scenario.get(16).add(null);
//		ACCombinerSLC acCombiner16 = new ACCombinerSLC();
//		acCombiner16.setId(1);
//		scenario.get(16).add(acCombiner16);
//		List<Inverters> inverters16 = new ArrayList <Inverters>();
//		scenario.get(16).add(inverters16);
//		scenario.get(16).add(null);
//		scenario.get(16).add(null);
//		scenario.get(16).add(0);
//		ACCombinerSLC SLCacCombiner16 = new ACCombinerSLC();
//		SLCacCombiner16.setCombinerDeviceType("With Main Breaker");
//		scenario.get(16).add(SLCacCombiner16);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(17).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo17 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(17).add(permitProjectSiteInfo17);
//		ACCombinerSLC acCombiner17 = new ACCombinerSLC();
//		acCombiner17.setId(1);
//		scenario.get(17).add(acCombiner17);
//		List<Inverters> inverters17 = new ArrayList <Inverters>();
//		Inverters inverter17 = new Inverters();
//		inverters17.add(inverter17);
//		inverters17.add(inverter17);
//		scenario.get(17).add(inverters17);
//		scenario.get(17).add(null);
//		scenario.get(17).add(null);
//		scenario.get(17).add(0);
//		ACCombinerSLC SLCacCombiner17 = new ACCombinerSLC();
//		SLCacCombiner17.setCombinerDeviceType("With Main Breaker");
//		scenario.get(17).add(SLCacCombiner17);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(18).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo18 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(18).add(permitProjectSiteInfo18);
//		ACCombinerSLC acCombiner18 = new ACCombinerSLC();
//		acCombiner18.setId(1);
//		scenario.get(18).add(acCombiner18);
//		List<Inverters> inverters18 = new ArrayList <Inverters>();
//		Inverters inverter18 = new Inverters();
//		inverter18.setIacmax("1,1");
//		inverters18.add(inverter18);
//		inverters18.add(inverter18);
//		scenario.get(18).add(inverters18);
//		scenario.get(18).add(null);
//		scenario.get(18).add(null);
//		scenario.get(18).add(0);
//		ACCombinerSLC SLCacCombiner18 = new ACCombinerSLC();
//		SLCacCombiner18.setCombinerDeviceType("With Main Breaker");
//		scenario.get(18).add(SLCacCombiner18);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(19).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo19 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(19).add(permitProjectSiteInfo19);
//		ACCombinerSLC acCombiner19 = new ACCombinerSLC();
//		acCombiner19.setId(1);
//		scenario.get(19).add(acCombiner19);
//		List<Inverters> inverters19 = new ArrayList <Inverters>();
//		Inverters inverter19 = new Inverters();
//		inverter19.setIacmax("1.1");
//		inverters19.add(inverter19);
//		inverters19.add(inverter19);
//		scenario.get(19).add(inverters19);
//		scenario.get(19).add(null);
//		scenario.get(19).add(null);
//		scenario.get(19).add(0);
//		ACCombinerSLC SLCacCombiner19 = new ACCombinerSLC();
//		SLCacCombiner19.setCombinerDeviceType("With Main Breaker");
//		scenario.get(19).add(SLCacCombiner19);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(20).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo20 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(20).add(permitProjectSiteInfo20);
//		ACCombinerSLC acCombiner20 = new ACCombinerSLC();
//		acCombiner20.setId(1);
//		scenario.get(20).add(acCombiner20);
//		List<Inverters> inverters20 = new ArrayList <Inverters>();
//		Inverters inverter20 = new Inverters();
//		inverter20.setIacmax("1.1");
//		inverters20.add(inverter20);
//		inverters20.add(inverter20);
//		scenario.get(20).add(inverters20);
//		scenario.get(20).add(1);
//		scenario.get(20).add(1);
//		scenario.get(20).add(0);
//		ACCombinerSLC SLCacCombiner20 = new ACCombinerSLC();
//		SLCacCombiner20.setCombinerDeviceType("With Main Breaker");
//		scenario.get(20).add(SLCacCombiner20);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(21).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo21 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(21).add(permitProjectSiteInfo21);
//		ACCombinerSLC acCombiner21 = new ACCombinerSLC();
//		acCombiner21.setId(1);
//		scenario.get(21).add(acCombiner21);
//		List<Inverters> inverters21 = new ArrayList <Inverters>();
//		Inverters inverter21 = new Inverters();
//		inverter21.setIacmax("1.1");
//		inverters21.add(inverter21);
//		inverters21.add(inverter21);
//		inverters21.add(null);
//		scenario.get(21).add(inverters21);
//		scenario.get(21).add(1);
//		scenario.get(21).add(1);
//		scenario.get(21).add(0);
//		ACCombinerSLC SLCacCombiner21 = new ACCombinerSLC();
//		SLCacCombiner21.setCombinerDeviceType("With Main Breaker");
//		scenario.get(21).add(SLCacCombiner21);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(22).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo22 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(22).add(permitProjectSiteInfo22);
//		ACCombinerSLC acCombiner22 = new ACCombinerSLC();
//		acCombiner22.setId(1);
//		scenario.get(22).add(acCombiner22);
//		List<Inverters> inverters22 = new ArrayList <Inverters>();
//		inverters22.add(null);
//		inverters22.add(null);
//		scenario.get(22).add(inverters22);
//		scenario.get(22).add(null);
//		scenario.get(22).add(null);
//		scenario.get(22).add(0);
//		ACCombinerSLC SLCacCombiner22 = new ACCombinerSLC();
//		SLCacCombiner22.setCombinerDeviceType("With Main Breaker");
//		scenario.get(22).add(SLCacCombiner22);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(23).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo23 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(23).add(permitProjectSiteInfo23);
//		ACCombinerSLC acCombiner23 = new ACCombinerSLC();
//		acCombiner23.setId(1);
//		scenario.get(23).add(acCombiner23);
//		List<Inverters> inverters23 = new ArrayList <Inverters>();
//		Inverters inverter23 = new Inverters();
//		Inverters inverter231 = new Inverters();
//		inverter231.setIacmax("1,2");
//		inverter23.setIacmax("1.1");
//		inverters23.add(inverter23);
//		inverters23.add(inverter23);
//		inverters23.add(inverter231);
//		scenario.get(23).add(inverters23);
//		scenario.get(23).add(1);
//		scenario.get(23).add(1);
//		scenario.get(23).add(0);
//		ACCombinerSLC SLCacCombiner23 = new ACCombinerSLC();
//		SLCacCombiner23.setCombinerDeviceType("With Main Breaker");
//		scenario.get(23).add(SLCacCombiner23);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(24).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo24 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(24).add(permitProjectSiteInfo24);
//		ACCombinerSLC acCombiner24 = new ACCombinerSLC();
//		acCombiner24.setId(1);
//		scenario.get(24).add(acCombiner24);
//		List<Inverters> inverters24 = new ArrayList <Inverters>();
//		Inverters inverter24 = new Inverters();
//		inverter24.setIacmax("1.1");
//		inverters24.add(inverter24);
//		inverters24.add(inverter24);
//		inverters24.add(inverter24);
//		inverters24.add(null);
//		scenario.get(24).add(inverters24);
//		scenario.get(24).add(1);
//		scenario.get(24).add(1);
//		scenario.get(24).add(0);
//		ACCombinerSLC SLCacCombiner24 = new ACCombinerSLC();
//		SLCacCombiner24.setCombinerDeviceType("With Main Breaker");
//		scenario.get(24).add(SLCacCombiner24);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(25).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo25 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(25).add(permitProjectSiteInfo25);
//		ACCombinerSLC acCombiner25 = new ACCombinerSLC();
//		acCombiner25.setId(1);
//		scenario.get(25).add(acCombiner25);
//		List<Inverters> inverters25 = new ArrayList <Inverters>();
//		Inverters inverter25 = new Inverters();
//		inverter25.setIacmax("1.1");
//		Inverters inverter252 = new Inverters();
//		inverter252.setIacmax("1,1");
//		inverters25.add(inverter25);
//		inverters25.add(inverter25);
//		inverters25.add(inverter25);
//		inverters25.add(inverter252);
//		scenario.get(25).add(inverters25);
//		scenario.get(25).add(1);
//		scenario.get(25).add(1);
//		scenario.get(25).add(0);
//		ACCombinerSLC SLCacCombiner25 = new ACCombinerSLC();
//		SLCacCombiner25.setCombinerDeviceType("With Main Breaker");
//		scenario.get(25).add(SLCacCombiner25);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(26).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo26 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(26).add(permitProjectSiteInfo26);
//		ACCombinerSLC acCombiner26 = new ACCombinerSLC();
//		acCombiner26.setId(1);
//		scenario.get(26).add(acCombiner26);
//		List<Inverters> inverters26 = new ArrayList <Inverters>();
//		Inverters inverter26 = new Inverters();
//		inverter26.setIacmax("1.1");
//		inverters26.add(inverter26);
//		inverters26.add(inverter26);
//		inverters26.add(inverter26);
//		inverters26.add(inverter26);
//		inverters26.add(null);
//		scenario.get(26).add(inverters26);
//		scenario.get(26).add(1);
//		scenario.get(26).add(1);
//		scenario.get(26).add(0);
//		ACCombinerSLC SLCacCombiner26 = new ACCombinerSLC();
//		SLCacCombiner26.setCombinerDeviceType("With Main Breaker");
//		scenario.get(26).add(SLCacCombiner26);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(27).add(form);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo27 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(27).add(permitProjectSiteInfo27);
//		ACCombinerSLC acCombiner27 = new ACCombinerSLC();
//		acCombiner27.setId(1);
//		scenario.get(27).add(acCombiner27);
//		List<Inverters> inverters27 = new ArrayList <Inverters>();
//		Inverters inverter27 = new Inverters();
//		inverter27.setIacmax("1.1");
//		Inverters inverter272 = new Inverters();
//		inverter272.setIacmax("1,1");
//		inverters27.add(inverter27);
//		inverters27.add(inverter27);
//		inverters27.add(inverter27);
//		inverters27.add(inverter27);
//		inverters27.add(inverter272);
//		scenario.get(27).add(inverters27);
//		scenario.get(27).add(1);
//		scenario.get(27).add(1);
//		scenario.get(27).add(0);
//		ACCombinerSLC SLCacCombiner27 = new ACCombinerSLC();
//		SLCacCombiner27.setCombinerDeviceType("With Main Breaker");
//		scenario.get(27).add(SLCacCombiner27);
//		for(int i = 0; i < scenario.size(); i++) {
//		 System.out.println("testacCombinerMapping ["+i+"]");
//		 planSetServiceE001.acCombinerMapping((AcroFields)scenario.get(i).get(0),( PermitProjectSiteInfoEntityTwo )scenario.get(i).get(1),(ACCombinerSLC)scenario.get(i).get(2),( List<Inverters> )scenario.get(i).get(3),(Integer)scenario.get(i).get(4),(Integer)scenario.get(i).get(5),(int)scenario.get(i).get(6),(ACCombinerSLC)scenario.get(i).get(7),0);
//	    }
//	}
//	
//	@Test
//	public void testacDisconnectOneMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo1 =new PermitProjectSiteInfoEntityTwo();
//		scenario.get(1).add(form);
//		scenario.get(1).add(permitProjectSiteInfo1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 =new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo2.setUseDisconectSwith("Yes");
//		scenario.get(2).add(form);
//		scenario.get(2).add(permitProjectSiteInfo2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 =new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo3.setUseDisconectSwith("Yes");
//		ACDisconnect acDisconnect3 = new ACDisconnect(); 
//		scenario.get(3).add(form);
//		scenario.get(3).add(permitProjectSiteInfo3);
//		scenario.get(3).add(acDisconnect3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 =new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo4.setUseDisconectSwith("Yes");
//		ACDisconnect acDisconnect4 = new ACDisconnect(); 
//		List<Inverters> inverters = new ArrayList <Inverters>();
//		Inverters inverter1 = new Inverters();
//		inverters.add(inverter1);
//		inverters.add(inverter1);
//		inverters.add(inverter1);
//		inverters.add(inverter1);
//		inverters.add(inverter1);
//		 PermitConduitConductorSectionEntity circuit4 =new PermitConduitConductorSectionEntity();
//		scenario.get(4).add(form);
//		scenario.get(4).add(permitProjectSiteInfo4);
//		scenario.get(4).add(acDisconnect4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(inverters);
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 =new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo5.setUseDisconectSwith("Yes");
//		ACDisconnect acDisconnect5 = new ACDisconnect(); 
//		ACDisconnect acDisconnect52 = new ACDisconnect(); 
//		acDisconnect52.setId(1);
//		List<Inverters> inverters2 = new ArrayList <Inverters>();
//		Inverters inverter11 = new Inverters();
//		inverter11.setIacmax("1,1");
//		inverters2.add(inverter11);
//		inverters2.add(inverter11);
//		inverters2.add(inverter11);
//		inverters2.add(inverter11);
//		inverters2.add(inverter11);
//		scenario.get(5).add(form);
//		scenario.get(5).add(permitProjectSiteInfo5);
//		scenario.get(5).add(acDisconnect5);
//		scenario.get(5).add(acDisconnect52);
//		scenario.get(5).add(inverters2);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo6=new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo6.setUseDisconectSwith("Yes");
//		ACDisconnect acDisconnect6 = new ACDisconnect(); 
//		ACDisconnect acDisconnect62 = new ACDisconnect(); 
//		acDisconnect62.setId(1);
//		PermitConduitConductorSectionEntity circuit6 = new PermitConduitConductorSectionEntity();
//		List<Inverters> inverters3 = new ArrayList <Inverters>();
//		Inverters inverter12 = new Inverters();
//		inverter12.setIacmax("1,1");
//		inverters3.add(inverter12);
//		inverters3.add(inverter12);
//		inverters3.add(inverter12);
//		inverters3.add(inverter12);
//		inverters3.add(inverter12);
//		scenario.get(6).add(form);
//		scenario.get(6).add(permitProjectSiteInfo6);
//		scenario.get(6).add(acDisconnect6);
//		scenario.get(6).add(acDisconnect62);
//		scenario.get(6).add(inverters3);
//		scenario.get(6).add(circuit6);
//		scenario.get(6).add(2);
//		scenario.get(6).add(2);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo7=new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo7.setUseDisconectSwith("Yes");
//		ACDisconnect acDisconnect7 = new ACDisconnect(); 
//		acDisconnect7.setDisconnectDeviceType("FUSIBLE DISCONNECT");
//		ACDisconnect acDisconnect72 = new ACDisconnect(); 
//		PermitConduitConductorSectionEntity circuit7 = new PermitConduitConductorSectionEntity();
//		acDisconnect72.setId(1);
//		List<Inverters> inverters7 = new ArrayList <Inverters>();
//		Inverters inverter7 = new Inverters();
//		Inverters inverter72 = new Inverters();
//		inverter7.setIacmax("1.1");
//		inverter72.setIacmax("1,1");
//		inverters7.add(inverter7);
//		inverters7.add(inverter7);
//		inverters7.add(inverter72);
//		inverters7.add(inverter7);
//		inverters7.add(inverter7);
//		scenario.get(7).add(form);
//		scenario.get(7).add(permitProjectSiteInfo7);
//		scenario.get(7).add(acDisconnect7);
//		scenario.get(7).add(acDisconnect72);
//		scenario.get(7).add(inverters7);
//		scenario.get(7).add(circuit7);
//		scenario.get(7).add(2);
//		scenario.get(7).add(2);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo8=new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo8.setUseDisconectSwith("Yes");
//		ACDisconnect acDisconnect8 = new ACDisconnect(); 
//		acDisconnect8.setDisconnectDeviceType("FUSIBLE DISCONNECT");
//		ACDisconnect acDisconnect82 = new ACDisconnect(); 
//		acDisconnect82.setId(1);
//		PermitConduitConductorSectionEntity circuit8 = new PermitConduitConductorSectionEntity();
//		List<Inverters> inverters8 = new ArrayList <Inverters>();
//		Inverters inverter8 = new Inverters();
//		Inverters inverter83 = new Inverters();
//		inverter8.setIacmax("1.1");
//		inverter83.setIacmax("1,1");
//		inverters8.add(inverter8);
//		inverters8.add(inverter8);
//		inverters8.add(inverter8);
//		inverters8.add(inverter83);
//		inverters8.add(inverter8);
//		scenario.get(8).add(form);
//		scenario.get(8).add(permitProjectSiteInfo8);
//		scenario.get(8).add(acDisconnect8);
//		scenario.get(8).add(acDisconnect82);
//		scenario.get(8).add(inverters8);
//		scenario.get(8).add(circuit8);
//		scenario.get(8).add(2);
//		scenario.get(8).add(2);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo9=new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo9.setUseDisconectSwith("Yes");
//		ACDisconnect acDisconnect9 = new ACDisconnect(); 
//		acDisconnect9.setDisconnectDeviceType("FUSIBLE DISCONNECT");
//		ACDisconnect acDisconnect92 = new ACDisconnect(); 
//		acDisconnect92.setId(1);
//		PermitConduitConductorSectionEntity circuit9 = new PermitConduitConductorSectionEntity();
//		List<Inverters> inverters9 = new ArrayList <Inverters>();
//		Inverters inverter9 = new Inverters();
//		Inverters inverter94 = new Inverters();
//		inverter9.setIacmax("1.1");
//		inverter94.setIacmax("1,1");
//		inverters9.add(inverter9);
//		inverters9.add(inverter9);
//		inverters9.add(inverter9);
//		inverters9.add(inverter9);
//		inverters9.add(inverter94);
//		scenario.get(9).add(form);
//		scenario.get(9).add(permitProjectSiteInfo9);
//		scenario.get(9).add(acDisconnect9);
//		scenario.get(9).add(acDisconnect92);
//		scenario.get(9).add(inverters9);
//		scenario.get(9).add(circuit9);
//		scenario.get(9).add(2);
//		scenario.get(9).add(2);
//		for(int i = 0; i < scenario.size(); i++) {
//		 System.out.println("testacDisconnectOneMapping ["+i+"]");
//		 planSetServiceE001.acDisconnectOneMapping((AcroFields)scenario.get(i).get(0),( PermitProjectSiteInfoEntityTwo )scenario.get(i).get(1),(ACDisconnect)scenario.get(i).get(2),(ACDisconnect)scenario.get(i).get(3),( List<Inverters> )scenario.get(i).get(4),(PermitConduitConductorSectionEntity)scenario.get(i).get(5),(Integer)scenario.get(i).get(6),(Integer)scenario.get(i).get(7),0);
//	    }
//	}
//	
//	@Test
//	public void testacDisconnectTwoMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo1 =new PermitProjectSiteInfoEntityTwo();
//		scenario.get(1).add(form);
//		scenario.get(1).add(permitProjectSiteInfo1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 =new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo2.setUseDisconectSwith("Yes");
//		scenario.get(2).add(form);
//		scenario.get(2).add(permitProjectSiteInfo2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 =new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo3.setUseDisconectSwith("Yes");
//		scenario.get(3).add(form);
//		scenario.get(3).add(permitProjectSiteInfo3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 =new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo4.setUseDisconectSwith("Yes");
//		List<Inverters> inverters = new ArrayList <Inverters>();
//		Inverters inverter1 = new Inverters();
//		inverters.add(inverter1);
//		inverters.add(inverter1);
//		inverters.add(inverter1);
//		inverters.add(inverter1);
//		inverters.add(inverter1);
//		 PermitConduitConductorSectionEntity circuit4 =new PermitConduitConductorSectionEntity();
//		scenario.get(4).add(form);
//		scenario.get(4).add(permitProjectSiteInfo4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(inverters);
//		scenario.get(4).add(circuit4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 =new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo5.setUseDisconectSwith("Yes");
//		ACDisconnect acDisconnect52 = new ACDisconnect(); 
//		acDisconnect52.setId(1);
//		List<Inverters> inverters2 = new ArrayList <Inverters>();
//		Inverters inverter11 = new Inverters();
//		inverter11.setIacmax("1,1");
//		inverters2.add(inverter11);
//		inverters2.add(inverter11);
//		inverters2.add(inverter11);
//		inverters2.add(inverter11);
//		inverters2.add(inverter11);
//		scenario.get(5).add(form);
//		scenario.get(5).add(permitProjectSiteInfo5);
//		scenario.get(5).add(acDisconnect52);
//		scenario.get(5).add(inverters2);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo6=new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo6.setUseDisconectSwith("Yes");
//		ACDisconnect acDisconnect62 = new ACDisconnect(); 
//		acDisconnect62.setId(1);
//		PermitConduitConductorSectionEntity circuit6 = new PermitConduitConductorSectionEntity();
//		List<Inverters> inverters3 = new ArrayList <Inverters>();
//		Inverters inverter12 = new Inverters();
//		inverter12.setIacmax("1,1");
//		inverters3.add(inverter12);
//		inverters3.add(inverter12);
//		inverters3.add(inverter12);
//		inverters3.add(inverter12);
//		inverters3.add(inverter12);
//		scenario.get(6).add(form);
//		scenario.get(6).add(permitProjectSiteInfo6);
//		scenario.get(6).add(acDisconnect62);
//		scenario.get(6).add(inverters3);
//		scenario.get(6).add(circuit6);
//		scenario.get(6).add(2);
//		scenario.get(6).add(2);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo7=new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo7.setUseDisconectSwith("Yes");
//		ACDisconnect acDisconnect72 = new ACDisconnect(); 
//		acDisconnect72.setDisconnectDeviceType("FUSIBLE DISCONNECT");
//		PermitConduitConductorSectionEntity circuit7 = new PermitConduitConductorSectionEntity();
//		acDisconnect72.setId(1);
//		List<Inverters> inverters7 = new ArrayList <Inverters>();
//		Inverters inverter7 = new Inverters();
//		Inverters inverter72 = new Inverters();
//		inverter7.setIacmax("1.1");
//		inverter72.setIacmax("1,1");
//		inverters7.add(inverter7);
//		inverters7.add(inverter7);
//		inverters7.add(inverter72);
//		inverters7.add(inverter7);
//		inverters7.add(inverter7);
//		scenario.get(7).add(form);
//		scenario.get(7).add(permitProjectSiteInfo7);
//		scenario.get(7).add(acDisconnect72);
//		scenario.get(7).add(inverters7);
//		scenario.get(7).add(circuit7);
//		scenario.get(7).add(2);
//		scenario.get(7).add(2);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo8=new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo8.setUseDisconectSwith("Yes");
//		ACDisconnect acDisconnect82 = new ACDisconnect(); 
//		acDisconnect82.setDisconnectDeviceType("FUSIBLE DISCONNECT");
//		acDisconnect82.setId(1);
//		PermitConduitConductorSectionEntity circuit8 = new PermitConduitConductorSectionEntity();
//		List<Inverters> inverters8 = new ArrayList <Inverters>();
//		Inverters inverter8 = new Inverters();
//		Inverters inverter83 = new Inverters();
//		inverter8.setIacmax("1.1");
//		inverter83.setIacmax("1,1");
//		inverters8.add(inverter8);
//		inverters8.add(inverter8);
//		inverters8.add(inverter8);
//		inverters8.add(inverter83);
//		inverters8.add(inverter8);
//		scenario.get(8).add(form);
//		scenario.get(8).add(permitProjectSiteInfo8);
//		scenario.get(8).add(acDisconnect82);
//		scenario.get(8).add(inverters8);
//		scenario.get(8).add(circuit8);
//		scenario.get(8).add(2);
//		scenario.get(8).add(2);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo9=new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo9.setUseDisconectSwith("Yes");
//		ACDisconnect acDisconnect92 = new ACDisconnect(); 
//		acDisconnect92.setDisconnectDeviceType("FUSIBLE DISCONNECT");
//		acDisconnect92.setId(1);
//		PermitConduitConductorSectionEntity circuit9 = new PermitConduitConductorSectionEntity();
//		List<Inverters> inverters9 = new ArrayList <Inverters>();
//		Inverters inverter9 = new Inverters();
//		Inverters inverter94 = new Inverters();
//		inverter9.setIacmax("1.1");
//		inverter94.setIacmax("1,1");
//		inverters9.add(inverter9);
//		inverters9.add(inverter9);
//		inverters9.add(inverter9);
//		inverters9.add(inverter9);
//		inverters9.add(inverter94);
//		scenario.get(9).add(form);
//		scenario.get(9).add(permitProjectSiteInfo9);
//		scenario.get(9).add(acDisconnect92);
//		scenario.get(9).add(inverters9);
//		scenario.get(9).add(circuit9);
//		scenario.get(9).add(2);
//		scenario.get(9).add(2);
//		for(int i = 0; i < scenario.size(); i++) {
//		 System.out.println("testacDisconnectTwoMapping ["+i+"]");
//		 planSetServiceE001.acDisconnectTwoMapping(( PermitProjectSiteInfoEntityTwo )scenario.get(i).get(1),(ACDisconnect)scenario.get(i).get(2),(PermitConduitConductorSectionEntity)scenario.get(i).get(4),( List<Inverters> )scenario.get(i).get(3),(AcroFields)scenario.get(i).get(0),(Integer)scenario.get(i).get(5),(Integer)scenario.get(i).get(6),0);
//	    }
//	}
//	
//	@Test
//	public void testacSubPanelMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo1 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(1).add(permitProjectSiteInfo1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(form);
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo2.setThepontOfTheC("Sub Panel");
//		scenario.get(2).add(permitProjectSiteInfo2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(form);
//		scenario.get(2).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo3.setThepontOfTheC("Sub Panel");
//		permitProjectSiteInfo3.setProposedSubPanel("142536");
//		scenario.get(3).add(permitProjectSiteInfo3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(form);
//		scenario.get(3).add(null);
//		List<ProposedSubPanel> resultlist=new ArrayList<ProposedSubPanel>();
//		ProposedSubPanel result = new ProposedSubPanel();
//		resultlist.add(result);
//		 Query mockedQuery = mock(Query.class);
//	     when(em.createQuery("SELECT u from ProposedSubPanel u WHERE u.id = :p1")).thenReturn(mockedQuery);
//	     when(mockedQuery.setParameter("p1", Integer.parseInt(permitProjectSiteInfo3.getProposedSubPanel()))).thenReturn(mockedQuery);
//	     when(mockedQuery.getResultList()).thenReturn(resultlist);
//	     when(mockedQuery.getSingleResult()).thenReturn(result);
//	     scenario.add(new ArrayList<Object>());
//		 PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//		 permitProjectSiteInfo4.setThepontOfTheC("Sub Panel");
//		 permitProjectSiteInfo4.setSubPanelBusRating("120");
//		 permitProjectSiteInfo4.setSolarLocation("Back-fed Breaker");
//		 scenario.get(4).add(permitProjectSiteInfo4);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(form);
//		 scenario.get(4).add(null);
//		 scenario.add(new ArrayList<Object>());
//		 List<Inverters> inverters5 = new ArrayList <Inverters>();
//		 PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 = new PermitProjectSiteInfoEntityTwo();
//		 permitProjectSiteInfo5.setThepontOfTheC("Sub Panel");
//		 permitProjectSiteInfo5.setSubPanelBusRating("120");
//		 permitProjectSiteInfo5.setSolarLocation("Back-fed Breaker");
//		 scenario.get(5).add(permitProjectSiteInfo5);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(form);
//		 scenario.get(5).add(inverters5);
//		 scenario.add(new ArrayList<Object>());
//		 Inverters inverter = new Inverters();
//		 List<Inverters> inverters6 = new ArrayList <Inverters>();
//		 inverters6.add(inverter);
//		 PermitProjectSiteInfoEntityTwo permitProjectSiteInfo6 = new PermitProjectSiteInfoEntityTwo();
//		 permitProjectSiteInfo6.setThepontOfTheC("Sub Panel");
//		 permitProjectSiteInfo6.setSubPanelBusRating("120");
//		 permitProjectSiteInfo6.setSolarLocation("Back-fed Breaker");
//		 scenario.get(6).add(permitProjectSiteInfo6);
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(form);
//		 scenario.get(6).add(inverters6);
//		for(int i = 0; i < scenario.size(); i++) {
//			System.out.println("testacSubPanelMapping ["+i+"]");
//			planSetServiceE001.acSubPanelMapping((PermitProjectSiteInfoEntityTwo)scenario.get(i).get(0),(ACSubPanelEntity)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),(List<Inverters>)scenario.get(i).get(3),0);
//		}
//	}
//	
//	@Test
//	public void testmainServicePanelMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo1 = new PermitProjectSiteInfoEntityTwo();
//		PermitHomeSiteEntityResult permitHomeSite1 = new PermitHomeSiteEntityResult();
//		List<Inverters> inverters = new ArrayList <Inverters>();
//		scenario.get(1).add(permitProjectSiteInfo1);
//		scenario.get(1).add(form);
//		scenario.get(1).add(permitHomeSite1);
//		scenario.get(1).add(inverters);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo2.setPanelBusRating("100");
//		permitProjectSiteInfo2.setPanelMainBreakerRating("125");
//		permitProjectSiteInfo2.setSolarLocation("Back-fed Breaker");
//		PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//		permitHomeSite2.setServiceVoltage("120");
//		scenario.get(2).add(permitProjectSiteInfo2);
//		scenario.get(2).add(form);
//		scenario.get(2).add(permitHomeSite2);
//		scenario.get(2).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo3.setPanelBusRating("100");
//		permitProjectSiteInfo3.setPanelMainBreakerRating("125");
//		permitProjectSiteInfo3.setSolarLocation("Back-fed Breaker");
//		PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//		permitHomeSite3.setServiceVoltage("120");
//		List<Inverters> inverters3 = new ArrayList <Inverters>();
//		scenario.get(3).add(permitProjectSiteInfo3);
//		scenario.get(3).add(form);
//		scenario.get(3).add(permitHomeSite3);
//		scenario.get(3).add(inverters3);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo4.setPanelBusRating("100");
//		permitProjectSiteInfo4.setPanelMainBreakerRating("125");
//		permitProjectSiteInfo4.setSolarLocation("Back-fed Breaker");
//		PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//		permitHomeSite4.setServiceVoltage("120");
//		Inverters inverter = new Inverters();
//		List<Inverters> inverters4 = new ArrayList <Inverters>();
//		inverters4.add(inverter);
//		scenario.get(4).add(permitProjectSiteInfo4);
//		scenario.get(4).add(form);
//		scenario.get(4).add(permitHomeSite4);
//		scenario.get(4).add(inverters4);
//		for(int i = 0; i < scenario.size(); i++) {
//			System.out.println("testmainServicePanelMapping ["+i+"]");
//			planSetServiceE001.mainServicePanelMapping((PermitProjectSiteInfoEntityTwo)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),(List<Inverters>)scenario.get(i).get(3),0);
//		}
//	}
//	
//	@Test
//	public void testextremeMinimumMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermtiWeatherEntityResult permtiWeather1 = new PermtiWeatherEntityResult();
//		scenario.get(1).add(permtiWeather1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(form);
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermtiWeatherEntityResult permtiWeather2 = new PermtiWeatherEntityResult();
//		permtiWeather2.setExtremeMinimum("Other");
//		permtiWeather2.setExtremeMinimumOther("11");
//		scenario.get(2).add(permtiWeather2);
//		Cmodulev2 moduleInfo2 = new Cmodulev2();
//		scenario.get(2).add(moduleInfo2);
//		scenario.get(2).add(form);
//		scenario.get(2).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermtiWeatherEntityResult permtiWeather3 = new PermtiWeatherEntityResult();
//		permtiWeather3.setExtremeMinimum("Other");
//		permtiWeather3.setExtremeMinimumOther("11");
//		scenario.get(3).add(permtiWeather3);
//		Cmodulev2 moduleInfo3= new Cmodulev2();
//		moduleInfo3.setGammaR("GammaR");
//		scenario.get(3).add(moduleInfo3);
//		scenario.get(3).add(form);
//		scenario.get(3).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermtiWeatherEntityResult permtiWeather4 = new PermtiWeatherEntityResult();
//		permtiWeather4.setExtremeMinimum("Other");
//		permtiWeather4.setExtremeMinimumOther("11");
//		scenario.get(4).add(permtiWeather4);
//		Cmodulev2 moduleInfo4= new Cmodulev2();
//		moduleInfo4.setGammaR("11");
//		scenario.get(4).add(moduleInfo4);
//		scenario.get(4).add(form);
//		scenario.get(4).add(null);
//		// How to mock VOCREF ????????????
//		for(int i = 0; i < scenario.size(); i++) {
//		  System.out.println("testextremeMinimumMapping ["+i+"]");
//		  planSetServiceE001.extremeMinimumMapping((PermtiWeatherEntityResult)scenario.get(i).get(0),(Cmodulev2)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),(List<Inverters>)scenario.get(i).get(3),0);
//		}
//	}
//	
//	@Test
//	public void testtitleBlockMicroMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitEntity permit1 = new PermitEntity();
//		PermitHomeSiteEntityResult permitHomeSite1 =new PermitHomeSiteEntityResult();
//		scenario.get(1).add(permitHomeSite1);
//		scenario.get(1).add(permit1);
//		scenario.get(1).add(form);
//		scenario.get(1).add(null);
//		scenario.get(1).add(permit1);
//		scenario.add(new ArrayList<Object>());
//		PermitEntity permit2 = new PermitEntity();
//		PermitHomeSiteEntityResult permitHomeSite2 =new PermitHomeSiteEntityResult();
//		permitHomeSite2.setSiteAddress("siteAddress");
//		permitHomeSite2.setCity("city");
//		scenario.get(2).add(permitHomeSite2);
//		scenario.get(2).add(permit2);
//		scenario.get(2).add(form);
//		scenario.get(2).add(null);
//		scenario.get(2).add(permit2);
//		for(int i = 0; i < scenario.size(); i++) {
//		 System.out.println("testtitleBlockMicroMapping ["+i+"]");
//		 planSetServiceE001.titleBlockMicroMapping((PermitHomeSiteEntityResult)scenario.get(i).get(0),(PermitEntity)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),(PermitEntity)scenario.get(i).get(4),(String)scenario.get(i).get(3),0);
//		}
//	}
//	
//	@Test
//	public void testcontractorInfoMicroMapping() {
//		PdfReader reader = null;
//		File fileRe = null;
//		PdfStamper stamper = null;
//		AcroFields form = null;
//		try {
//		reader = new PdfReader(Constants.rapportPlansetFolderUrl +"NEC-PDF/" +"PDF-E001-"+"STRING"+".pdf" );
//		fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E001-STRING"+".pdf");
//		stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
//		form = stamper.getAcroFields();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		catch (DocumentException e){
//		}
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite1 =new PermitHomeSiteEntityResult();
//		EditUserInformations userInfo = new EditUserInformations();
//		
//		scenario.get(0).add(userInfo);
//		scenario.get(0).add(form);
//		scenario.get(0).add(permitHomeSite1);
//		scenario.get(0).add(0);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		EditUserInformations userInfo2 = new EditUserInformations();
//		userInfo2.setCity("city 123");
//		
//		PermitHomeSiteEntityResult permitHomeSite2 =new PermitHomeSiteEntityResult();
//		permitHomeSite2.setSiteAddress("siteAddress");
//		permitHomeSite2.setCity("city");
//		
//		scenario.get(1).add(userInfo2);
//		scenario.get(1).add(form);
//		scenario.get(1).add(permitHomeSite2);
//		scenario.get(1).add(0);
//		for(int i = 0; i < scenario.size(); i++) {
//		 System.out.println("testcontractorInfoMicroMapping ["+i+"]");
//		 planSetServiceE001.contractorInfoMapping((EditUserInformations)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),(int)scenario.get(i).get(3));
//		}
//	}
//	
//	@Test
//	public void testmoduleMicroMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		Cmodulev2 moduleInfo1 = new Cmodulev2();
//		PermitArrayEntityResultSecond permitArraysEntityResult1 = new PermitArrayEntityResultSecond();
//		scenario.get(0).add(moduleInfo1);
//		scenario.get(0).add(form);
//		scenario.get(0).add(permitArraysEntityResult1);
//		for(int i = 0; i < scenario.size(); i++) {
//		 System.out.println("testmoduleMicroMapping ["+i+"]");
//		   planSetServiceE001.moduleMicroMapping((Cmodulev2)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitArrayEntityResultSecond)scenario.get(i).get(2),0);
//		}
//	}
//	
//	@Test
//	public void testmicroInverterMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult1 = new PermitArrayEntityResultSecond();
//		scenario.get(1).add(permitArraysEntityResult1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(form);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult2.setMicroInverter("MicroInverter");
//		scenario.get(2).add(permitArraysEntityResult2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(form);
//		scenario.add(new ArrayList<Object>());
//		PermitArrayEntityResultSecond permitArraysEntityResult3 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult3.setMicroInverter("MicroInverter");
//		scenario.get(3).add(permitArraysEntityResult3);
//		Inverters microInverterInfo3 = new Inverters();
//		scenario.get(3).add(microInverterInfo3);
//		scenario.get(3).add(form);
//		for(int i = 0; i < scenario.size(); i++) {
//		 System.out.println("testmicroInverterMapping ["+i+"]");
//		   planSetServiceE001.microInverterMapping((PermitArrayEntityResultSecond)scenario.get(i).get(0),(Inverters)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),0);
//		}
//	}
//	
//	@Test
//	public void testacCombinerMappingMicro() {
//	List<List<Object>> scenario = new ArrayList<List<Object>>();
//	scenario.add(new ArrayList<Object>());
//	scenario.get(0).add(null);
//	scenario.get(0).add(null);
//	scenario.get(0).add(null);
//	scenario.get(0).add(form);
//	scenario.get(0).add(0);
//	scenario.get(0).add(null);
//	scenario.get(0).add(null);
//	
//	scenario.add(new ArrayList<Object>());
//	scenario.get(1).add(null);
//	scenario.get(1).add(null);
//	ACCombinerSLC acCombiner = new ACCombinerSLC();
//	scenario.get(1).add(acCombiner);
//	scenario.get(1).add(form);
//	scenario.get(1).add(1);
//	scenario.get(1).add(null);
//	ACCombinerSLC SLCacCombiner = new ACCombinerSLC();
//	scenario.get(1).add(SLCacCombiner);
//	
//	scenario.add(new ArrayList<Object>());
//	scenario.get(2).add(null);
//	scenario.get(2).add(null);
//	ACCombinerSLC acCombiner2 = new ACCombinerSLC();
//	scenario.get(2).add(acCombiner2);
//	scenario.get(2).add(form);
//	scenario.get(2).add(2);
//	scenario.get(2).add(null);
//	ACCombinerSLC SLCacCombiner2 = new ACCombinerSLC();
//	SLCacCombiner2.setId(1);
//	scenario.get(2).add(SLCacCombiner2);
//	
//	scenario.add(new ArrayList<Object>());
//	scenario.get(3).add(null);
//	Inverters microInverterInfo = new Inverters();
//	scenario.get(3).add(microInverterInfo);
//	ACCombinerSLC acCombiner3 = new ACCombinerSLC();
//	scenario.get(3).add(acCombiner3);
//	scenario.get(3).add(form);
//	scenario.get(3).add(3);
//	scenario.get(3).add(null);
//	ACCombinerSLC SLCacCombiner3 = new ACCombinerSLC();
//	SLCacCombiner3.setId(1);
//	SLCacCombiner3.setCombinerDeviceType("With Main Breaker");
//	scenario.get(3).add(SLCacCombiner3);
//	
//	scenario.add(new ArrayList<Object>());
//	PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//	scenario.get(4).add(permitProjectSiteInfo4);
//	Inverters microInverterInfo4 = new Inverters();
//	microInverterInfo4.setIacmax("1,1");
//	scenario.get(4).add(microInverterInfo4);
//	ACCombinerSLC acCombiner4 = new ACCombinerSLC();
//	scenario.get(4).add(acCombiner4);
//	scenario.get(4).add(form);
//	scenario.get(4).add(4);
//	scenario.get(4).add(null);
//	ACCombinerSLC SLCacCombiner4 = new ACCombinerSLC();
//	SLCacCombiner4.setId(1);
//	SLCacCombiner4.setCombinerDeviceType("With Main Breaker");
//	scenario.get(4).add(SLCacCombiner4);
//	
//	scenario.add(new ArrayList<Object>());
//	PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 = new PermitProjectSiteInfoEntityTwo();
//	scenario.get(5).add(permitProjectSiteInfo5);
//	Inverters microInverterInfo5 = new Inverters();
//	microInverterInfo5.setIacmax("1.1");
//	scenario.get(5).add(microInverterInfo5);
//	ACCombinerSLC acCombiner5 = new ACCombinerSLC();
//	scenario.get(5).add(acCombiner5);
//	scenario.get(5).add(form);
//	scenario.get(5).add(5);
//	scenario.get(5).add(null);
//	ACCombinerSLC SLCacCombiner5 = new ACCombinerSLC();
//	SLCacCombiner5.setId(1);
//	SLCacCombiner5.setCombinerDeviceType("With Main Breaker");
//	scenario.get(5).add(SLCacCombiner5);
//	
//	scenario.add(new ArrayList<Object>());
//	scenario.get(6).add(null);
//	scenario.get(6).add(null);
//	ACCombinerSLC acCombiner6 = new ACCombinerSLC();
//	acCombiner6.setId(1);
//	scenario.get(6).add(acCombiner6);
//	scenario.get(6).add(form);
//	scenario.get(6).add(6);
//	scenario.get(6).add(null);
//	ACCombinerSLC SLCacCombiner6 = new ACCombinerSLC();
//	scenario.get(6).add(SLCacCombiner6);
//	
//	scenario.add(new ArrayList<Object>());
//	scenario.get(7).add(null);
//	Inverters microInverterInfo7 = new Inverters();
//	scenario.get(7).add(microInverterInfo7);
//	ACCombinerSLC acCombiner7 = new ACCombinerSLC();
//	acCombiner7.setId(7);
//	scenario.get(7).add(acCombiner7);
//	scenario.get(7).add(form);
//	scenario.get(7).add(7);
//	scenario.get(7).add(null);
//	ACCombinerSLC SLCacCombiner7 = new ACCombinerSLC();
//	SLCacCombiner7.setCombinerDeviceType("With Main Breaker");
//	scenario.get(7).add(SLCacCombiner7);
//	
//	scenario.add(new ArrayList<Object>());
//	PermitProjectSiteInfoEntityTwo permitProjectSiteInfo8 = new PermitProjectSiteInfoEntityTwo();
//	scenario.get(8).add(permitProjectSiteInfo8);
//	Inverters microInverterInfo8 = new Inverters();
//	microInverterInfo8.setIacmax("1,1");
//	scenario.get(8).add(microInverterInfo8);
//	ACCombinerSLC acCombiner8 = new ACCombinerSLC();
//	acCombiner8.setId(8);
//	scenario.get(8).add(acCombiner8);
//	scenario.get(8).add(form);
//	scenario.get(8).add(8);
//	scenario.get(8).add(null);
//	ACCombinerSLC SLCacCombiner8 = new ACCombinerSLC();
//	SLCacCombiner8.setCombinerDeviceType("With Main Breaker");
//	scenario.get(8).add(SLCacCombiner8);
//	
//	scenario.add(new ArrayList<Object>());
//	PermitProjectSiteInfoEntityTwo permitProjectSiteInfo9 = new PermitProjectSiteInfoEntityTwo();
//	scenario.get(9).add(permitProjectSiteInfo9);
//	Inverters microInverterInfo9 = new Inverters();
//	microInverterInfo9.setIacmax("1.1");
//	scenario.get(9).add(microInverterInfo9);
//	ACCombinerSLC acCombiner9 = new ACCombinerSLC();
//	acCombiner9.setId(9);
//	scenario.get(9).add(acCombiner9);
//	scenario.get(9).add(form);
//	scenario.get(9).add(9);
//	scenario.get(9).add(null);
//	ACCombinerSLC SLCacCombiner9 = new ACCombinerSLC();
//	SLCacCombiner9.setId(1);
//	SLCacCombiner9.setCombinerDeviceType("With Main Breaker");
//	scenario.get(9).add(SLCacCombiner9);
//	
//	for(int i = 0; i < scenario.size(); i++) {
//	 System.out.println("testacCombinerMappingMicro ["+i+"]");
//	  planSetServiceE001.acCombinerMapping((PermitProjectSiteInfoEntityTwo)scenario.get(i).get(0),(Inverters)scenario.get(i).get(1),(ACCombinerSLC)scenario.get(i).get(2),(AcroFields)scenario.get(i).get(3),(int)scenario.get(i).get(4),(Integer)scenario.get(i).get(5),(ACCombinerSLC)scenario.get(i).get(6),0);
//	}	
//	}
//	
//	@Test
//	public void testacDiscoOneMicroMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo1 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(1).add(permitProjectSiteInfo1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(form);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo2.setUseDisconectSwith("Yes");
//		scenario.get(2).add(permitProjectSiteInfo2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(form);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo3.setUseDisconectSwith("Yes");
//		ACDisconnect acDisconnect3 = new ACDisconnect();
//		Inverters microInverterInfo3 = new Inverters();
//		scenario.get(3).add(permitProjectSiteInfo3);
//		scenario.get(3).add(acDisconnect3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(form);
//		scenario.get(3).add(null);
//		scenario.get(3).add(microInverterInfo3);
//		scenario.get(3).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo4.setUseDisconectSwith("Yes");
//		ACDisconnect acDisconnect4 = new ACDisconnect();
//		Inverters microInverterInfo4 = new Inverters();
//		microInverterInfo4.setIacmax("1,1");
//		scenario.get(4).add(permitProjectSiteInfo4);
//		scenario.get(4).add(acDisconnect4);
//		ACDisconnect secondacDisconnect4 = new ACDisconnect();
//		secondacDisconnect4.setId(1);
//		scenario.get(4).add(secondacDisconnect4);
//		scenario.get(4).add(form);
//		scenario.get(4).add(null);
//		scenario.get(4).add(microInverterInfo4);
//		scenario.get(4).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo5.setUseDisconectSwith("Yes");
//		ACDisconnect acDisconnect5 = new ACDisconnect();
//		Inverters microInverterInfo5 = new Inverters();
//		microInverterInfo5.setIacmax("1.1");
//		scenario.get(5).add(permitProjectSiteInfo5);
//		scenario.get(5).add(acDisconnect5);
//		ACDisconnect secondacDisconnect5 = new ACDisconnect();
//		secondacDisconnect5.setId(1);
//		scenario.get(5).add(secondacDisconnect5);
//		scenario.get(5).add(form);
//		PermitConduitConductorSectionEntity circuit5 = new PermitConduitConductorSectionEntity();
//		scenario.get(5).add(circuit5);
//		scenario.get(5).add(microInverterInfo5);
//		scenario.get(5).add(0);
//		for(int i = 0; i < scenario.size(); i++) {
//		 System.out.println("testacDiscoOneMicroMapping ["+i+"]");
//		   planSetServiceE001.acDiscoOneMicroMapping((PermitProjectSiteInfoEntityTwo)scenario.get(i).get(0),(ACDisconnect)scenario.get(i).get(1),(ACDisconnect)scenario.get(i).get(2),(AcroFields)scenario.get(i).get(3),(PermitConduitConductorSectionEntity)scenario.get(i).get(4),(Inverters)scenario.get(i).get(5),(int)scenario.get(i).get(6),0);
//		}
//	}
//	
//	@Test
//	public void testacDiscoMicroTwoMapping() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo1 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(1).add(permitProjectSiteInfo1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(form);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo2.setUseDisconectSwith("Yes");
//		scenario.get(2).add(permitProjectSiteInfo2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(form);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo3.setUseDisconectSwith("Yes");
//		ACDisconnect secondacDisconnect3 = new ACDisconnect();
//		Inverters microInverterInfo3 = new Inverters();
//		scenario.get(3).add(permitProjectSiteInfo3);
//		scenario.get(3).add(secondacDisconnect3);
//		scenario.get(3).add(form);
//		scenario.get(3).add(null);
//		scenario.get(3).add(microInverterInfo3);
//		scenario.get(3).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo4.setUseDisconectSwith("Yes");
//		ACDisconnect secondacDisconnect4 = new ACDisconnect();
//		secondacDisconnect4.setId(1);
//		scenario.get(4).add(permitProjectSiteInfo4);
//		scenario.get(4).add(secondacDisconnect4);
//		scenario.get(4).add(form);
//		scenario.get(4).add(null);
//		Inverters microInverterInfo4 = new Inverters();
//		scenario.get(4).add(microInverterInfo4);
//		scenario.get(4).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo5.setUseDisconectSwith("Yes");
//		ACDisconnect secondacDisconnect5 = new ACDisconnect();
//		secondacDisconnect5.setId(1);
//		PermitConduitConductorSectionEntity circuit5 = new PermitConduitConductorSectionEntity();
//		scenario.get(5).add(permitProjectSiteInfo5);
//		scenario.get(5).add(secondacDisconnect5);
//		scenario.get(5).add(form);
//		scenario.get(5).add(circuit5);
//		Inverters microInverterInfo5 = new Inverters();
//		microInverterInfo5.setIacmax("1,1");
//		scenario.get(5).add(microInverterInfo5);
//		scenario.get(5).add(0);
//		for(int i = 0; i < scenario.size(); i++) {
//		 System.out.println("testacDiscoMicroTwoMapping ["+i+"]");
//		   planSetServiceE001.acDiscoMicroTwoMapping((PermitProjectSiteInfoEntityTwo)scenario.get(i).get(0),(ACDisconnect)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),(PermitConduitConductorSectionEntity)scenario.get(i).get(3),(Inverters)scenario.get(i).get(4),(int)scenario.get(i).get(5),0);
//		}
//	}
//	
//	@Test
//	public void testacSubPanelOneMico() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo1 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(1).add(permitProjectSiteInfo1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(form);
//		scenario.get(1).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo();
//		 PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//		permitProjectSiteInfo2.setThepontOfTheC("Sub Panel");
//		scenario.get(2).add(permitProjectSiteInfo2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(form);
//		scenario.get(2).add(permitArraysEntityResult2);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo3.setThepontOfTheC("Sub Panel");
//		permitProjectSiteInfo3.setProposedSubPanel("142536");
//		scenario.get(3).add(permitProjectSiteInfo3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(form);
//		scenario.get(3).add(null);
//		List<ProposedSubPanel> resultlist=new ArrayList<ProposedSubPanel>();
//		ProposedSubPanel result = new ProposedSubPanel();
//		resultlist.add(result);
//		 Query mockedQuery = mock(Query.class);
//	     when(em.createQuery("SELECT u from ProposedSubPanel u WHERE u.id = :p1")).thenReturn(mockedQuery);
//	     when(mockedQuery.setParameter("p1", Integer.parseInt(permitProjectSiteInfo3.getProposedSubPanel()))).thenReturn(mockedQuery);
//	     when(mockedQuery.getResultList()).thenReturn(resultlist);
//	     when(mockedQuery.getSingleResult()).thenReturn(result);
//	     scenario.add(new ArrayList<Object>());
//		 PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//		 PermitArrayEntityResultSecond permitArraysEntityResult4 = new PermitArrayEntityResultSecond();
//		 permitProjectSiteInfo4.setThepontOfTheC("Sub Panel");
//		 permitProjectSiteInfo4.setGroundLevelACSubPanelManufacturer("manufacturer");
//		 permitProjectSiteInfo4.setSubPanelBusRating("100");
//		 permitProjectSiteInfo4.setSubPanelBreakerOCPD("120");
//		 permitProjectSiteInfo4.setSolarInterconnection("120");
//		 permitProjectSiteInfo4.setSolarInterconnection("120");
//		 permitProjectSiteInfo4.setSubPanelBusRating("120");
//		 permitArraysEntityResult4.setOcpdOne("17");
//		 scenario.get(4).add(permitProjectSiteInfo4);
//		 scenario.get(4).add(null);
//		 scenario.get(4).add(form);
//		 scenario.get(4).add(permitArraysEntityResult4);
//		 scenario.add(new ArrayList<Object>());
//		 PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 = new PermitProjectSiteInfoEntityTwo();
//		 PermitArrayEntityResultSecond permitArraysEntityResult5 = new PermitArrayEntityResultSecond();
//		 permitProjectSiteInfo5.setThepontOfTheC("Sub Panel");
//		 permitProjectSiteInfo5.setGroundLevelACSubPanelManufacturer("manufacturer");
//		 permitProjectSiteInfo5.setSubPanelBusRating("100");
//		 permitProjectSiteInfo5.setSubPanelBreakerOCPD("120");
//		 permitProjectSiteInfo5.setSolarInterconnection("120");
//		 permitProjectSiteInfo5.setSolarInterconnection("120");
//		 permitProjectSiteInfo5.setSubPanelBusRating("120");
//		 permitArraysEntityResult5.setOcpdOne("17");
//		 permitArraysEntityResult5.setDeviceToIncorporate("Micro Inverter");
//		 scenario.get(5).add(permitProjectSiteInfo5);
//		 scenario.get(5).add(null);
//		 scenario.get(5).add(form);
//		 scenario.get(5).add(permitArraysEntityResult5);
//		 scenario.add(new ArrayList<Object>());
//		 PermitProjectSiteInfoEntityTwo permitProjectSiteInfo6 = new PermitProjectSiteInfoEntityTwo();
//		 PermitArrayEntityResultSecond permitArraysEntityResult6 = new PermitArrayEntityResultSecond();
//		 permitProjectSiteInfo6.setThepontOfTheC("Sub Panel");
//		 permitProjectSiteInfo6.setGroundLevelACSubPanelManufacturer("manufacturer");
//		 permitProjectSiteInfo6.setSubPanelBusRating("100");
//		 permitProjectSiteInfo6.setSubPanelBreakerOCPD("120");
//		 permitProjectSiteInfo6.setSolarInterconnection("120");
//		 permitProjectSiteInfo6.setSolarInterconnection("120");
//		 permitProjectSiteInfo6.setSubPanelBusRating("120");
//		 permitProjectSiteInfo6.setCombiningACCircuits("A Proposed AC Combiner Panel (Solar Load Center)");
//		 permitArraysEntityResult6.setOcpdOne("17");
//		 permitArraysEntityResult6.setDeviceToIncorporate("Micro Inverter");
//		 scenario.get(6).add(permitProjectSiteInfo6);
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(form);
//		 scenario.get(6).add(permitArraysEntityResult6);
//		for(int i = 0; i < scenario.size(); i++) {
//			System.out.println("testacSubPanelOneMico ["+i+"]");
//			planSetServiceE001.acSubPanelOneMico((PermitProjectSiteInfoEntityTwo)scenario.get(i).get(0),(ACSubPanelEntity)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),(PermitArrayEntityResultSecond)scenario.get(i).get(3),0);
//		}
//	}
//	
//	@Test
//	public void testmainServicePanelMicro() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo1 = new PermitProjectSiteInfoEntityTwo();
//		PermitHomeSiteEntityResult permitHomeSite1 = new PermitHomeSiteEntityResult();
//		PermitArrayEntityResultSecond permitArraysEntityResult1 = new PermitArrayEntityResultSecond();
//		scenario.get(1).add(permitProjectSiteInfo1);
//		scenario.get(1).add(form);
//		scenario.get(1).add(permitHomeSite1);
//		scenario.get(1).add(permitArraysEntityResult1);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo2.setThepontOfTheC("Main Panel");
//		PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//		PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//		scenario.get(2).add(permitProjectSiteInfo2);
//		scenario.get(2).add(form);
//		scenario.get(2).add(permitHomeSite2);
//		scenario.get(2).add(permitArraysEntityResult2);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo3.setThepontOfTheC("Main Panel");
//		permitProjectSiteInfo3.setPanelBusRating("Other");
//		PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//		PermitArrayEntityResultSecond permitArraysEntityResult3 = new PermitArrayEntityResultSecond();
//		permitArraysEntityResult3.setOcpdOne("10");
//		scenario.get(3).add(permitProjectSiteInfo3);
//		scenario.get(3).add(form);
//		scenario.get(3).add(permitHomeSite3);
//		scenario.get(3).add(permitArraysEntityResult3);
//		scenario.add(new ArrayList<Object>());
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo4.setThepontOfTheC("Main Panel");
//		permitProjectSiteInfo4.setPanelBusRating("");
//		permitProjectSiteInfo4.setSolarInterconnection("100");
//		PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//		PermitArrayEntityResultSecond permitArraysEntityResult4 = new PermitArrayEntityResultSecond();
//		scenario.get(4).add(permitProjectSiteInfo4);
//		scenario.get(4).add(form);
//		scenario.get(4).add(permitHomeSite4);
//		scenario.get(4).add(permitArraysEntityResult4);
//		for(int i = 0; i < scenario.size(); i++) {
//			System.out.println("testmainServicePanelMicro ["+i+"]");
//			planSetServiceE001.mainServicePanelMicro((PermitProjectSiteInfoEntityTwo)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),(PermitArrayEntityResultSecond)scenario.get(i).get(3),0);
//		}
//	}
//	
//	@Test
//	public void testextremeMinimumMicro() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);
//		scenario.add(new ArrayList<Object>());
//		PermtiWeatherEntityResult permtiWeather1 = new PermtiWeatherEntityResult();
//		scenario.get(1).add(permtiWeather1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(form);
//		scenario.add(new ArrayList<Object>());
//		PermtiWeatherEntityResult permtiWeather2 = new PermtiWeatherEntityResult();
//		permtiWeather2.setExtremeMinimum("Other");
//		scenario.get(2).add(permtiWeather2);
//		Cmodulev2 moduleInfo2 = new Cmodulev2();
//		scenario.get(2).add(moduleInfo2);
//		scenario.get(2).add(form);
//		scenario.add(new ArrayList<Object>());
//		PermtiWeatherEntityResult permtiWeather3 = new PermtiWeatherEntityResult();
//		permtiWeather3.setExtremeMinimum("Other");
//		permtiWeather3.setExtremeMinimumOther("11");
//		scenario.get(3).add(permtiWeather3);
//		Cmodulev2 moduleInfo3= new Cmodulev2();
//		moduleInfo3.setGammaR("GammaR");
//		scenario.get(3).add(moduleInfo3);
//		scenario.get(3).add(form);
//		scenario.add(new ArrayList<Object>());
//		PermtiWeatherEntityResult permtiWeather4 = new PermtiWeatherEntityResult();
//		permtiWeather4.setExtremeMinimum("Other");
//		permtiWeather4.setExtremeMinimumOther("11");
//		scenario.get(4).add(permtiWeather4);
//		Cmodulev2 moduleInfo4= new Cmodulev2();
//		moduleInfo4.setGammaR("11");
//		scenario.get(4).add(moduleInfo4);
//		scenario.get(4).add(form);
//		for(int i = 0; i < scenario.size(); i++) {
//		  System.out.println("testextremeMinimumMicro ["+i+"]");
//		  planSetServiceE001.extremeMinimumMicro((PermtiWeatherEntityResult)scenario.get(i).get(0),(Cmodulev2)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),0);
//		}
//	}
//	
//	
//}
