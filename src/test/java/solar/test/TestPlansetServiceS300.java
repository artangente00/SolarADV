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
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.Cmodulev2;
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitSketchEntity;
//import com.PlayGroundAdv.Solar.Entity.PlansetCustomizeSheets;
//import com.PlayGroundAdv.Solar.Entity.RailRacking;
//import com.PlayGroundAdv.Solar.Entity.UserLicSectionsEntity;
//import com.PlayGroundAdv.Solar.model.EditUserInformations;
//import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.PermitServiceEdit;
//import com.PlayGroundAdv.Solar.Services.PlanSetServiceS201;
//import com.PlayGroundAdv.Solar.Services.PlansetServiceS300;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//public class TestPlansetServiceS300 {
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
//    PlansetServiceS300 plansetServiceS300 = new PlansetServiceS300();
//    
//    PdfReader reader = null;
//   	File fileRe = null;
//   	PdfStamper stamper = null;
//   	AcroFields form = null;
//   	PdfReader readerOrigin= null;
//   	
//     @Before
//     public void setupMock() {
//    	 plansetServiceS300 = new PlansetServiceS300();
//        MockitoAnnotations.initMocks(this);
// 		
// 		try {
// 		reader = new PdfReader(Constants.rapportPlansetFolderUrl +"NEC-PDF/" +"PDF-S300"+".pdf" );
// 		fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-S300"+".pdf");
// 		stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
// 		form = stamper.getAcroFields();
// 		readerOrigin = new PdfReader( Constants.rapportPlansetFolderUrl +"NEC-PDF/" + "PDF-S300.pdf" );
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
//	public void testmapTitleBlock() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		PermitEntity permit1 = new PermitEntity();
//		PermitHomeSiteEntityResult permitHomeSite1 =new PermitHomeSiteEntityResult();
//		scenario.get(0).add(permitHomeSite1);
//		scenario.get(0).add(permit1);
//		scenario.get(0).add(form);
//		scenario.get(0).add("");
//		scenario.add(new ArrayList<Object>());
//		PermitEntity permit2 = new PermitEntity();
//		permit2.setReleasev3("rv3");
//		permit2.setReleasev2("rv2");
//		PermitHomeSiteEntityResult permitHomeSite2 =new PermitHomeSiteEntityResult();
//		permitHomeSite2.setSiteAddress("siteAddress");
//		permitHomeSite2.setCity("city");
//		permitHomeSite2.setState("NY");
//		scenario.get(1).add(permitHomeSite2);
//		scenario.get(1).add(permit2);
//		scenario.get(1).add(form);
//		scenario.get(1).add("");
//		scenario.add(new ArrayList<Object>());
//		PermitEntity permit3 = new PermitEntity();
//		permit3.setReleasev3("rv3");
//		permit3.setReleasev2("rv2");
//		PermitHomeSiteEntityResult permitHomeSite3 =new PermitHomeSiteEntityResult();
//		PermitArrayEntityResultSecond permitArraysEntityResult3= new PermitArrayEntityResultSecond();
//		permitArraysEntityResult3.setPvModuleModEl("pvModuleModEl");
//		permitHomeSite3.setSiteAddress("siteAddress");
//		permitHomeSite3.setCity("city");
//		permitHomeSite3.setState("NY");
//		permitHomeSite3.setUtilityCompanyName("Other");
//		scenario.get(2).add(permitHomeSite3);
//		scenario.get(2).add(permit3);
//		scenario.get(2).add(form);
//		scenario.get(2).add("");
//		scenario.add(new ArrayList<Object>());
//		PermitEntity permit4 = new PermitEntity();
//		permit4.setReleasev3("rv3");
//		permit4.setReleasev2("rv2");
//		PermitHomeSiteEntityResult permitHomeSite4 =new PermitHomeSiteEntityResult();
//		PermitArrayEntityResultSecond permitArraysEntityResult4= new PermitArrayEntityResultSecond();
//		permitArraysEntityResult4.setPvModuleModEl("manufacturer:model");
//		permitHomeSite4.setSiteAddress("siteAddress");
//		permitHomeSite4.setCity("city");
//		permitHomeSite4.setState("NY");
//		scenario.get(3).add(permitHomeSite4);
//		scenario.get(3).add(permit4);
//		scenario.get(3).add(form);
//		scenario.get(3).add("");
//		scenario.add(new ArrayList<Object>());
//		PermitEntity permit5 = new PermitEntity();
//		permit5.setReleasev3("rv3");
//		permit5.setReleasev2("rv2");
//		PermitHomeSiteEntityResult permitHomeSite5 =new PermitHomeSiteEntityResult();
//		PermitArrayEntityResultSecond permitArraysEntityResult5= new PermitArrayEntityResultSecond();
//		permitArraysEntityResult5.setPvModuleModEl("manufacturer:model");
//		permitArraysEntityResult5.setInverterModel("inverterModel");
//		permitHomeSite5.setSiteAddress("siteAddress");
//		permitHomeSite5.setCity("city");
//		permitHomeSite5.setState("NY");
//		permitHomeSite5.setUtilityCompanyName("Other");
//		permitHomeSite5.setUtilityCompanyNameOther("Oncor Electric Delivery");
//		scenario.get(4).add(permitHomeSite5);
//		scenario.get(4).add(permit5);
//		scenario.get(4).add(form);
//		scenario.get(4).add("");
//		for(int i = 0; i < scenario.size(); i++) {
//		 System.out.println("testmapTitleBlock ["+i+"]");
//		 plansetServiceS300.mapTitleBlock((PermitHomeSiteEntityResult)scenario.get(i).get(0),(PermitEntity)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),(String)scenario.get(i).get(3),0);
//		}
//	}
//	
//	@Test
//	public void testmapContractorInfoNullQueryResult() {
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
//    	System.out.println("testmapContractorInfoNullQueryResult");
//    	plansetServiceS300.mapContractorInfo(editUserInformations2,form,permitHomeSite2,0);
//	}
//	@Test
//	public void testmapContractorInfoSingleQueryResult() {
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
//    	System.out.println("testmapContractorInfoSingleQueryResult");
//    	plansetServiceS300.mapContractorInfo(editUserInformations2,form,permitHomeSite2,0);
//	}
//	@Test
//	public void testmapContractorInfoMultipleQueryResult() {
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
//			 System.out.println("testmapContractorInfoMultipleQueryResult ["+i+"]");
//			 plansetServiceS300.mapContractorInfo((EditUserInformations)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),0);
//		}
//	}
//	
//	@Test
//	public void testmapS300Sheet() {
//		String idPermit ="12457";
//		List<PermitSketchEntity> sketch = new ArrayList<PermitSketchEntity>();
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(stamper);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(sketch);
//		
//		scenario.add(new ArrayList<Object>());
//		RailRacking railRackingGround1 = new RailRacking();
//		scenario.get(1).add(railRackingGround1);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(stamper);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(sketch);
//		
//		scenario.add(new ArrayList<Object>());
//		RailRacking railRackingGround2 = new RailRacking();
//		railRackingGround2.setManufacturer("manufacturer");
//		scenario.get(2).add(railRackingGround2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(stamper);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(sketch);
//		
//		scenario.add(new ArrayList<Object>());
//		RailRacking railRackingGround3 = new RailRacking();
//		railRackingGround3.setManufacturer("manufacturer");
//		railRackingGround3.setModel("model");
//		scenario.get(3).add(railRackingGround3);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(stamper);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(sketch);
//		
//		scenario.add(new ArrayList<Object>());
//		RailRacking railRackingGround4 = new RailRacking();
//		railRackingGround4.setManufacturer("IronRidge");
//		railRackingGround4.setModel("model");
//		scenario.get(4).add(railRackingGround4);
//		PermitAdvEntityResult PermitAdvEntityInfo4 = new PermitAdvEntityResult();
//		PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//		scenario.get(4).add(PermitAdvEntityInfo4);
//		scenario.get(4).add(permitHomeSite4);
//		scenario.get(4).add(stamper);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(sketch);
//		
//		scenario.add(new ArrayList<Object>());
//		RailRacking railRackingGround5 = new RailRacking();
//		railRackingGround5.setManufacturer("IronRidge");
//		railRackingGround5.setModel("model");
//		scenario.get(5).add(railRackingGround5);
//		PermitAdvEntityResult PermitAdvEntityInfo5 = new PermitAdvEntityResult();
//		PermitAdvEntityInfo5.setModuleLayout("Other");
//		PermitAdvEntityInfo5.setSizeOfPipe("Other");
//		PermitAdvEntityInfo5.setFootingDiameter("Other");
//		PermitAdvEntityInfo5.setWindSpeed("Other");
//		PermitAdvEntityInfo5.setSnowLoad("Other");
//		PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//		permitHomeSite5.setResidenceBindingCategory("ResidenceBindingCategory");
//		scenario.get(5).add(PermitAdvEntityInfo5);
//		scenario.get(5).add(permitHomeSite5);
//		scenario.get(5).add(stamper);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(sketch);
//		
//		scenario.add(new ArrayList<Object>());
//		RailRacking railRackingGround6 = new RailRacking();
//		railRackingGround6.setManufacturer("IronRidge");
//		railRackingGround6.setModel("model");
//		scenario.get(6).add(railRackingGround6);
//		PermitAdvEntityResult PermitAdvEntityInfo6 = new PermitAdvEntityResult();
//		PermitAdvEntityInfo6.setModuleLayout("4upL");
//		PermitAdvEntityInfo6.setSizeOfPipe("Other");
//		PermitAdvEntityInfo6.setSizeOfPipeOther("12");
//		PermitAdvEntityInfo6.setFootingDiameter("Other");
//		PermitAdvEntityInfo6.setFootingDiameterOther("5");
//		PermitAdvEntityInfo6.setWindSpeed("Other");
//		PermitAdvEntityInfo6.setSnowLoad("Other");
//		PermitHomeSiteEntityResult permitHomeSite6 = new PermitHomeSiteEntityResult();
//		permitHomeSite6.setResidenceBindingCategory("Exposure B");
//		scenario.get(6).add(PermitAdvEntityInfo6);
//		scenario.get(6).add(permitHomeSite6);
//		scenario.get(6).add(stamper);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(sketch);
//		
//		scenario.add(new ArrayList<Object>());
//		RailRacking railRackingGround7 = new RailRacking();
//		railRackingGround7.setManufacturer("IronRidge");
//		railRackingGround7.setModel("model");
//		scenario.get(7).add(railRackingGround7);
//		PermitAdvEntityResult PermitAdvEntityInfo7 = new PermitAdvEntityResult();
//		PermitAdvEntityInfo7.setModuleLayout("4upL");
//		PermitAdvEntityInfo7.setSizeOfPipe("Other");
//		PermitAdvEntityInfo7.setSizeOfPipeOther("2");
//		PermitAdvEntityInfo7.setFootingDiameter("Other");
//		PermitAdvEntityInfo7.setFootingDiameterOther("12");
//		PermitAdvEntityInfo7.setWindSpeed("110");
//		PermitAdvEntityInfo7.setSnowLoad("0");
//		PermitHomeSiteEntityResult permitHomeSite7 = new PermitHomeSiteEntityResult();
//		permitHomeSite7.setResidenceBindingCategory("Exposure B");
//		scenario.get(7).add(PermitAdvEntityInfo7);
//		scenario.get(7).add(permitHomeSite7);
//		scenario.get(7).add(stamper);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(sketch);
//		
//		scenario.add(new ArrayList<Object>());
//		RailRacking railRackingGround8 = new RailRacking();
//		railRackingGround8.setManufacturer("IronRidge");
//		railRackingGround8.setModel("model");
//		scenario.get(8).add(railRackingGround8);
//		PermitAdvEntityResult PermitAdvEntityInfo8 = new PermitAdvEntityResult();
//		PermitAdvEntityInfo8.setModuleLayout("4upL");
//		PermitAdvEntityInfo8.setSizeOfPipe("Other");
//		PermitAdvEntityInfo8.setSizeOfPipeOther("2");
//		PermitAdvEntityInfo8.setFootingDiameter("Other");
//		PermitAdvEntityInfo8.setFootingDiameterOther("12");
//		PermitAdvEntityInfo8.setWindSpeed("110");
//		PermitAdvEntityInfo8.setSnowLoad("0");
//		PermitHomeSiteEntityResult permitHomeSite8 = new PermitHomeSiteEntityResult();
//		permitHomeSite8.setResidenceBindingCategory("Exposure B");
//		scenario.get(8).add(PermitAdvEntityInfo8);
//		scenario.get(8).add(permitHomeSite8);
//		scenario.get(8).add(stamper);
//		scenario.get(8).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo8 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(8).add(permitProjectSiteInfo8);
//		scenario.get(8).add(null);
//		scenario.get(8).add(sketch);
//		
//		scenario.add(new ArrayList<Object>());
//		RailRacking railRackingGround9 = new RailRacking();
//		railRackingGround9.setManufacturer("IronRidge");
//		railRackingGround9.setModel("model");
//		scenario.get(9).add(railRackingGround9);
//		PermitAdvEntityResult PermitAdvEntityInfo9 = new PermitAdvEntityResult();
//		PermitAdvEntityInfo9.setModuleLayout("Other");
//		PermitAdvEntityInfo9.setModuleLayoutOther("4upp");
//		PermitAdvEntityInfo9.setSizeOfPipe("Other");
//		PermitAdvEntityInfo9.setSizeOfPipeOther("2");
//		PermitAdvEntityInfo9.setFootingDiameter("Other");
//		PermitAdvEntityInfo9.setFootingDiameterOther("12");
//		PermitAdvEntityInfo9.setWindSpeed("110");
//		PermitAdvEntityInfo9.setSnowLoad("0");
//		PermitHomeSiteEntityResult permitHomeSite9 = new PermitHomeSiteEntityResult();
//		permitHomeSite9.setResidenceBindingCategory("Exposure B");
//		scenario.get(9).add(PermitAdvEntityInfo9);
//		scenario.get(9).add(permitHomeSite9);
//		scenario.get(9).add(stamper);
//		scenario.get(9).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo9 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(9).add(permitProjectSiteInfo9);
//		scenario.get(9).add(null);
//		List<PermitSketchEntity> sketch2 = new ArrayList<PermitSketchEntity>();
//		PermitSketchEntity sketch21 = new PermitSketchEntity();
//		PermitSketchEntity sketch22 = new PermitSketchEntity();
//		sketch2.add(sketch21);
//		sketch2.add(sketch22);
//		scenario.get(9).add(sketch2);
//		
//		scenario.add(new ArrayList<Object>());
//		RailRacking railRackingGround10 = new RailRacking();
//		railRackingGround10.setManufacturer("IronRidge");
//		railRackingGround10.setModel("model");
//		scenario.get(10).add(railRackingGround10);
//		PermitAdvEntityResult PermitAdvEntityInfo10 = new PermitAdvEntityResult();
//		PermitAdvEntityInfo10.setModuleLayout("Other");
//		PermitAdvEntityInfo10.setModuleLayoutOther("4upp");
//		PermitAdvEntityInfo10.setSizeOfPipe("Other");
//		PermitAdvEntityInfo10.setSizeOfPipeOther("2");
//		PermitAdvEntityInfo10.setFootingDiameter("Other");
//		PermitAdvEntityInfo10.setFootingDiameterOther("12");
//		PermitAdvEntityInfo10.setWindSpeed("110");
//		PermitAdvEntityInfo10.setSnowLoad("0");
//		PermitHomeSiteEntityResult permitHomeSite10 = new PermitHomeSiteEntityResult();
//		permitHomeSite10.setResidenceBindingCategory("Exposure B");
//		scenario.get(10).add(PermitAdvEntityInfo10);
//		scenario.get(10).add(permitHomeSite10);
//		scenario.get(10).add(stamper);
//		scenario.get(10).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo10 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(10).add(permitProjectSiteInfo10);
//		Cmodulev2 moduleInfo = new Cmodulev2();
//		scenario.get(10).add(moduleInfo);
//		List<PermitSketchEntity> sketch3 = new ArrayList<PermitSketchEntity>();
//		PermitSketchEntity sketch31 = new PermitSketchEntity();
//		sketch31.setModelvalue("45");
//		PermitSketchEntity sketch32 = new PermitSketchEntity();
//		sketch3.add(sketch31);
//		sketch3.add(sketch32);
//		scenario.get(10).add(sketch3);
//		
//		scenario.add(new ArrayList<Object>());
//		RailRacking railRackingGround11 = new RailRacking();
//		railRackingGround11.setManufacturer("IronRidge");
//		railRackingGround11.setModel("model");
//		scenario.get(11).add(railRackingGround11);
//		PermitAdvEntityResult PermitAdvEntityInfo11 = new PermitAdvEntityResult();
//		PermitAdvEntityInfo11.setModuleLayout("4upP");
//		PermitAdvEntityInfo11.setSizeOfPipe("Other");
//		PermitAdvEntityInfo11.setSizeOfPipeOther("2");
//		PermitAdvEntityInfo11.setFootingDiameter("Other");
//		PermitAdvEntityInfo11.setFootingDiameterOther("12");
//		PermitAdvEntityInfo11.setWindSpeed("110");
//		PermitAdvEntityInfo11.setSnowLoad("0");
//		PermitHomeSiteEntityResult permitHomeSite11 = new PermitHomeSiteEntityResult();
//		permitHomeSite11.setResidenceBindingCategory("Exposure B");
//		scenario.get(11).add(PermitAdvEntityInfo11);
//		scenario.get(11).add(permitHomeSite11);
//		scenario.get(11).add(stamper);
//		scenario.get(11).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo11 = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(11).add(permitProjectSiteInfo11);
//		Cmodulev2 moduleInfo11 = new Cmodulev2();
//		scenario.get(11).add(moduleInfo11);
//		List<PermitSketchEntity> sketch4 = new ArrayList<PermitSketchEntity>();
//		PermitSketchEntity sketch41 = new PermitSketchEntity();
//		sketch41.setModelvalue("45");
//		PermitSketchEntity sketch42 = new PermitSketchEntity();
//		sketch4.add(sketch41);
//		sketch4.add(sketch42);
//		scenario.get(11).add(sketch3);
//		
//		scenario.add(new ArrayList<Object>());
//		RailRacking railRackingGround12 = new RailRacking();
//		railRackingGround12.setManufacturer("IronRidge");
//		railRackingGround12.setModel("model");
//		scenario.get(12).add(railRackingGround12);
//		PermitAdvEntityResult PermitAdvEntityInfo12 = new PermitAdvEntityResult();
//		PermitAdvEntityInfo12.setModuleLayout("4upP");
//		PermitAdvEntityInfo12.setSizeOfPipe("Other");
//		PermitAdvEntityInfo12.setSizeOfPipeOther("2");
//		PermitAdvEntityInfo12.setFootingDiameter("Other");
//		PermitAdvEntityInfo12.setFootingDiameterOther("12");
//		PermitAdvEntityInfo12.setWindSpeed("110");
//		PermitAdvEntityInfo12.setSnowLoad("0");
//		PermitHomeSiteEntityResult permitHomeSite12 = new PermitHomeSiteEntityResult();
//		permitHomeSite12.setResidenceBindingCategory("Exposure B");
//		scenario.get(12).add(PermitAdvEntityInfo12);
//		scenario.get(12).add(permitHomeSite12);
//		scenario.get(12).add(stamper);
//		scenario.get(12).add(null);
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo12 = new PermitProjectSiteInfoEntityTwo();
//		permitProjectSiteInfo12.setHeightOfSouth(2);
//		scenario.get(12).add(permitProjectSiteInfo12);
//		Cmodulev2 moduleInfo12 = new Cmodulev2();
//		moduleInfo12.setLength("2");
//		scenario.get(12).add(moduleInfo12);
//		List<PermitSketchEntity> sketch5 = new ArrayList<PermitSketchEntity>();
//		PermitSketchEntity sketch51 = new PermitSketchEntity();
//		sketch51.setModelvalue("45");
//		PermitSketchEntity sketch52 = new PermitSketchEntity();
//		sketch5.add(sketch51);
//		sketch5.add(sketch52);
//		scenario.get(12).add(sketch3);
//		//Mock the query
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("Select u  " + " from PermitSketchEntity u  " + " where u.permitEntity.id = :p1 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter("p1", Integer.parseInt(idPermit))).thenReturn(mockedQuery);
//	  
//		for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("testmapS300Sheet ["+i+"]");
//			  when(mockedQuery.getResultList()).thenReturn((List<PermitSketchEntity>)scenario.get(i).get(7));
//			 plansetServiceS300.mapS300Sheet((RailRacking)scenario.get(i).get(0),(PermitAdvEntityResult)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),(PdfStamper)scenario.get(i).get(3),idPermit,(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(5),(Cmodulev2)scenario.get(i).get(6));
//		}	
//	}
//	
//	@Test
//	public void testbuildingPDFS300() {
//		
//	}
//}
