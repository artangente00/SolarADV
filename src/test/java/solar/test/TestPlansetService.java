//package solar.test;
//
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//
//import com.PlayGroundAdv.Solar.Entity.ACCombinerSLC;
//import com.PlayGroundAdv.Solar.Entity.ACDisconnect;
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.Cmodulev2;
//import com.PlayGroundAdv.Solar.Entity.DCCombinerDisconnectEntity;
//import com.PlayGroundAdv.Solar.Entity.DCOptimizerEntity;
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.Flashing;
//import com.PlayGroundAdv.Solar.Entity.Inverters;
//import com.PlayGroundAdv.Solar.Entity.PathEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitConduitConductorSectionEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEngineerEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitLayoutEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitSketchEntity;
//import com.PlayGroundAdv.Solar.Entity.RailRacking;
//import com.PlayGroundAdv.Solar.Entity.RoofAttachmentsEntity;
//import com.PlayGroundAdv.Solar.Entity.RsheetsLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.SsheetLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.SsheetRackingMappingEntity;
//import com.PlayGroundAdv.Solar.Entity.SsheetSpacingMappingEntity;
//import com.PlayGroundAdv.Solar.model.AuthentificationEntityResult;
//import com.PlayGroundAdv.Solar.model.DCCombinerDisconnectRequest;
//import com.PlayGroundAdv.Solar.model.EditUserInformations;
//import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.PlanSetService;
//import com.PlayGroundAdv.Solar.Services.PlanSetServiceE300;
//
//
//public class TestPlansetService {
//	
//	@Mock
//    private EntityManager em;
//	
//	@Mock
//    private NotificationEntityService notificationEntityService;
//	
//	@Mock
//    private PlanSetServiceE300 plansetServiceE300;
//	
//	@Spy
//	CheckValueTypesService checkValueTypesService;
//	@InjectMocks
//	PlanSetService planSetService = new PlanSetService();
//    @Before
//	public void setupMock() {
//    	planSetService = new PlanSetService();
//	       MockitoAnnotations.initMocks(this);
//	}
//	@Test
//    public void groundRsheetMapping() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		RailRacking railRackingGround2 = new RailRacking();
//		railRackingGround2.setManufacturer("make2");
//		railRackingGround2.setModel("model2");
//		scenario.get(0).add(railRackingGround2); //1 RailRacking
//		scenario.get(0).add(null); //2 PermitAdvEntityResult
//		scenario.get(0).add(null); //3 PermitHomeSiteEntityResult
//		scenario.get(0).add("1478"); //4 idPermit
//		scenario.get(0).add(null); //5 projectState
//		scenario.get(0).add(null); //6 permitAdditionalInfo
//		scenario.get(0).add(null); //7 permitEntity
//		scenario.get(0).add(null); //8 permitSusyemSize
//		scenario.get(0).add(null); //10 userLogedInfo
//		scenario.get(0).add(null); //12 editUserInformations
//		scenario.get(0).add(null); //14 userConnectedEntity
//		scenario.get(0).add(null); //15 idUser
//		scenario.get(0).add(null); //16 indexSheet
//
//		scenario.add(new ArrayList<Object>());
//		RailRacking railRackingGround = new RailRacking();
//		railRackingGround.setManufacturer("make1");
//		railRackingGround.setModel("model1");
//		PermitAdvEntityResult permitAdvEntityInfo = new PermitAdvEntityResult();
//		PermitHomeSiteEntityResult permitHomeSite = new PermitHomeSiteEntityResult();
//		PermitAdditionalInfoEntityResult permitAdditionalInfo = new PermitAdditionalInfoEntityResult();
//		PermitEntity permitEntity = new PermitEntity();
//		PermitEngineerEntity permitNewInputs = new PermitEngineerEntity();
//		AuthentificationEntityResult userLogedInfo = new AuthentificationEntityResult();
//		EditUserInformations editUserInformations = new EditUserInformations();
//		PathEntity path = new PathEntity();
//		AuthentificationEntity userConnectedEntity = new AuthentificationEntity();
//		scenario.get(1).add(railRackingGround); //1 RailRacking
//		scenario.get(1).add(permitAdvEntityInfo); //2 PermitAdvEntityResult
//		scenario.get(1).add(permitHomeSite); //3 PermitHomeSiteEntityResult
//		scenario.get(1).add("12588"); //4 idPermit
//		scenario.get(1).add("CA"); //5 projectState
//		scenario.get(1).add(permitAdditionalInfo); //6 permitAdditionalInfo
//		scenario.get(1).add(permitEntity); //7 permitEntity
//		scenario.get(1).add("12"); //8 permitSusyemSize
//		scenario.get(1).add(userLogedInfo); //10 userLogedInfo
//		scenario.get(1).add(editUserInformations); //12 editUserInformations
//		scenario.get(1).add(userConnectedEntity); //14 userConnectedEntity
//		scenario.get(1).add("15896"); //15 idUser
//		scenario.get(1).add(1); //16 indexSheet
//		
//		scenario.add(new ArrayList<Object>());
//		RailRacking railRackingGround3 = new RailRacking();
//		railRackingGround3.setManufacturer("make3");
//		railRackingGround3.setModel("model3");
//		PermitAdvEntityResult permitAdvEntityInfo3 = new PermitAdvEntityResult();
//		permitAdvEntityInfo3.setBracedUnbraced("Not Factored");
//		permitAdvEntityInfo3.setFootingDiameter("16");
//		permitAdvEntityInfo3.setModuleLayout("5upL");
//		permitAdvEntityInfo3.setrSheetList("1914");
//		permitAdvEntityInfo3.setSizeOfPipe("Other");
//		permitAdvEntityInfo3.setSizeOfPipeOther("testtt pipe");
//		permitAdvEntityInfo3.setSnowLoad("10");
//		permitAdvEntityInfo3.setWindSpeed("110");
//		PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//		permitHomeSite3.setResidenceBindingCategory("Exposure A");
//		PermitAdditionalInfoEntityResult permitAdditionalInfo3 = new PermitAdditionalInfoEntityResult();
//		PermitEntity permitEntity3 = new PermitEntity();
//		PermitEngineerEntity permitNewInputs3 = new PermitEngineerEntity();
//		AuthentificationEntityResult userLogedInfo3 = new AuthentificationEntityResult();
//		EditUserInformations editUserInformations3 = new EditUserInformations();
//		PathEntity path3 = new PathEntity();
//		AuthentificationEntity userConnectedEntity3 = new AuthentificationEntity();
//		scenario.get(2).add(railRackingGround3); //1 RailRacking
//		scenario.get(2).add(permitAdvEntityInfo3); //2 PermitAdvEntityResult
//		scenario.get(2).add(permitHomeSite3); //3 PermitHomeSiteEntityResult
//		scenario.get(2).add("12588"); //4 idPermit
//		scenario.get(2).add("CA"); //5 projectState
//		scenario.get(2).add(permitAdditionalInfo3); //6 permitAdditionalInfo
//		scenario.get(2).add(permitEntity3); //7 permitEntity
//		scenario.get(2).add("12"); //8 permitSusyemSize
//		scenario.get(2).add(userLogedInfo3); //10 userLogedInfo
//		scenario.get(2).add(editUserInformations3); //12 editUserInformations
//		scenario.get(2).add(userConnectedEntity3); //14 userConnectedEntity
//		scenario.get(2).add("15896"); //15 idUser
//		scenario.get(2).add(1); //16 indexSheet
//		
//		 List<List<PermitSketchEntity>> sketch = new ArrayList<List<PermitSketchEntity>>();
//		 sketch.add(new ArrayList<PermitSketchEntity>());
//		 PermitSketchEntity element = new PermitSketchEntity();
//		 element.setArraySketch(1);
//		 element.setAzimuth("43");
//		 element.setEaveOverHang("");
//		 element.setModelvalue("7");
//		 element.setModuleTils(true);
//		 element.setRoofPitch("");
//		 element.setEaveOverHangOther("");
//		 element.setModuleQty("6");
//		 sketch.get(0).add(0, element);
//		 sketch.add(new ArrayList<PermitSketchEntity>());
//		 PermitSketchEntity element1 = new PermitSketchEntity();
//		 element1.setArraySketch(1);
//		 element1.setAzimuth("44");
//		 element1.setEaveOverHang("");
//		 element1.setModelvalue("18.4");
//		 element1.setModuleTils(false);
//		 element1.setRoofPitch("4:12");
//		 element1.setEaveOverHangOther("");
//		 element1.setModuleQty("4");
//		 sketch.get(1).add(0, element1);
//		 sketch.add(new ArrayList<PermitSketchEntity>());
//		 PermitSketchEntity element2 = new PermitSketchEntity();
//		 element2.setArraySketch(1);
//		 element2.setAzimuth("15");
//		 element2.setEaveOverHang("30");
//		 element2.setModelvalue("14");
//		 element2.setModuleTils(true);
//		 element2.setRoofPitch("3:12");
//		 element2.setEaveOverHangOther("");
//		 element2.setModuleQty("1");
//		 sketch.get(2).add(0, element2);
//		 
//		 
//		 List<List<RsheetsLibraryEntity>> rsheetList = new ArrayList<List<RsheetsLibraryEntity>>();
//		 rsheetList.add(null);
//		 List<RsheetsLibraryEntity> r1=new ArrayList <RsheetsLibraryEntity>();
//		 rsheetList.add(r1);
//		 List<RsheetsLibraryEntity> r2=new ArrayList <RsheetsLibraryEntity>();
//		 RsheetsLibraryEntity rSheet= new RsheetsLibraryEntity();
//		 rSheet.setIsDeleted(false);
//		 rSheet.setBracedOrUnbraced("BRC");
//		 rSheet.setComponentType("RACK");
//		 rSheet.setExposureCategory("A");
//		 rSheet.setFootingDiameter("18");
//		 rSheet.setGroundRailRacking(true);
//		 rSheet.setManufacturer("DPW Solar");
//		 rSheet.setModel("EZ Ground Mount System");
//		 rSheet.setModuleLayout("3upL");
//		 rSheet.setPipeSize("2-1⁄2");
//		 rSheet.setSnowLoad("20");
//		 rSheet.setState("CA");
//		 rSheet.setThicknessPipe("Not Factored");
//		 rSheet.setTiltRange("30.1-35");
//		 rSheet.setWindSpeed("111-120");
//		 r2.add(rSheet);
//		 rsheetList.add(r2);
//		 
//		 List<String> pdfNames = new ArrayList<String>();
//		 pdfNames.add(null);
//		 pdfNames.add("");
//		 pdfNames.add("RACK_make1_model1");
//		 pdfNames.add("RACK_make3_model3");
//		for(int i = 0; i < scenario.size(); i++) {
//			System.out.println("aaaa "+(String) scenario.get(i).get(3));
//			sketch.get(i).get(0).setPermitEntity((PermitEntity)scenario.get(i).get(6));
//				
//				CheckValueTypesService checkValueTypesService1 = Mockito.mock(CheckValueTypesService.class);
//			
//			 Query mockedQuery3 = mock(Query.class);
//				when(em.createQuery("Select u  " + " from PermitSketchEntity u  " + " where u.permitEntity.id = :p1 ")).thenReturn(mockedQuery3);
//				when(mockedQuery3.setParameter("p1", Integer.parseInt((String) scenario.get(i).get(3)))).thenReturn(mockedQuery3);
//				when(mockedQuery3.getResultList()).thenReturn(sketch.get(i));
//				
//			 
//			 Query mockedQuery4 = mock(Query.class);
//			when(em.createQuery("Select u " + " from RsheetsLibraryEntity u " + " where (u.pdfName = :p1 OR u.pdfName = :p2 OR pdfName = :p3) AND u.isDeleted = :p4 ")).thenReturn(mockedQuery4);
//			when(mockedQuery4.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery4);
//			when(mockedQuery4.getResultList()).thenReturn(rsheetList.get(i));
//			
//			Query mockedQuery5 = mock(Query.class);
//			when(em.createQuery("SELECT u.pdfName from RsheetsLibraryEntity u, PermitAdvEntity v where v.rSheetList = CONCAT(u.id,'') AND v.permitEntity.id = :p1 AND u.isDeleted =:p2 ")).thenReturn(mockedQuery5);
//			when(mockedQuery5.setParameter("p1",  Integer.parseInt((String) scenario.get(i).get(3)))).thenReturn(mockedQuery5);
//			when(mockedQuery5.setParameter("p2",  false)).thenReturn(mockedQuery5);
//			when(mockedQuery5.getSingleResult()).thenReturn(pdfNames.get(i));
//			
//			when(notificationEntityService.addNewNotif(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),
//					Mockito.any() ,Mockito.any())).thenReturn("Success");
//			planSetService.groundRsheetMapping((RailRacking)scenario.get(i).get(0), (PermitAdvEntityResult)scenario.get(i).get(1), (PermitHomeSiteEntityResult)scenario.get(i).get(2), (String)scenario.get(i).get(3), 
//					(String)scenario.get(i).get(4), (PermitAdditionalInfoEntityResult)scenario.get(i).get(5), (PermitEntity)scenario.get(i).get(6), (String)scenario.get(i).get(7), (AuthentificationEntityResult)scenario.get(i).get(8), (EditUserInformations)scenario.get(i).get(9), (AuthentificationEntity)scenario.get(i).get(10), (String)scenario.get(i).get(11));
//		 }
//    }
//    
//    @Test
//    public void sSheetSpacingMapping() {
//    	
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//    	scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite = new PermitHomeSiteEntityResult();
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo = new PermitProjectSiteInfoEntityTwo();
//		scenario.get(1).add(permitHomeSite);
//		scenario.get(1).add("");
//		scenario.get(1).add(permitProjectSiteInfo);
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo();
//		permitHomeSite2.setRoofRafter("Rafter-Simple Attic");
//		permitHomeSite2.setStanchionMaxSpacing("48");
//		permitProjectSiteInfo2.setRafterTrussSpacing("24");
//		scenario.get(2).add(permitHomeSite2);
//		scenario.get(2).add("Rafter - Simple Attic");
//		scenario.get(2).add(permitProjectSiteInfo2);
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo();
//		permitHomeSite3.setRoofRafter("Rafter - Strut to Walls Below");
//		permitHomeSite3.setStanchionMaxSpacing("Other");
//		permitHomeSite3.setStanchionMaxSpacingOther("maxSpacingOther");
//		permitProjectSiteInfo3.setRafterTrussSpacing("Other");
//		permitProjectSiteInfo3.setTextOtherRatfter("textOtherRatfter");
//		scenario.get(3).add(permitHomeSite3);
//		scenario.get(3).add("Rafter - Struts to wall(s) below");
//		scenario.get(3).add(permitProjectSiteInfo3);
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//		permitHomeSite4.setRoofRafter("Pre-Eng Roof Trusses");
//		permitHomeSite4.setStanchionMaxSpacing("Other");
//		permitHomeSite4.setStanchionMaxSpacingOther("50");
//		permitProjectSiteInfo4.setRafterTrussSpacing("12");
//		scenario.get(4).add(permitHomeSite4);
//		scenario.get(4).add("Truss - Manufactured Plate Truss");
//		scenario.get(4).add(permitProjectSiteInfo4);
//		
//		scenario.add(new ArrayList<Object>());
//		PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//		PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 = new PermitProjectSiteInfoEntityTwo();
//		permitHomeSite5.setRoofRafter("Rafter-Simple Attic");
//		permitHomeSite5.setStanchionMaxSpacing("24");
//		permitProjectSiteInfo5.setRafterTrussSpacing("16");
//		scenario.get(5).add(permitHomeSite5);
//		scenario.get(5).add("Rafter - Simple Attic");
//		scenario.get(5).add(permitProjectSiteInfo5);
//		
//		List<List<SsheetSpacingMappingEntity>> sheetSpacingMappingEntity= new ArrayList <List<SsheetSpacingMappingEntity>> ();
//		sheetSpacingMappingEntity.add(null);
//		
//		List<SsheetSpacingMappingEntity> spacingMap2 = new ArrayList <SsheetSpacingMappingEntity> ();
//		SsheetSpacingMappingEntity sheet1= new SsheetSpacingMappingEntity();
//		sheet1.setDetailsHeading("STANCHION SPACING DETIAL");
//		sheet1.setPdfname("16RAFTER-1_4FOOTMAX.pdf");
//		sheet1.setQuadNumber("4");
//		sheet1.setRafterTrussSpacing("16");
//		sheet1.setRoofType("Rafter - Simple Attic");
//		sheet1.setSheetNumber("S-200");
//		sheet1.setStanchionMaxSpacing("24\"");
//		sheet1.setIsDeleted(false);
//		
//		SsheetLibraryEntity sSheet1 = new SsheetLibraryEntity();
//		sSheet1.setPdfName("editttt1 2 - S200 DPW W-PFU FLASHING - D - LANDSCAPE - HORIZONTAL.pdf");
//		sSheet1.setIsDeleted(false);
//		sheet1.setsSheetFile(sSheet1);
//		spacingMap2.add(sheet1);
//		
//		SsheetSpacingMappingEntity sheet2= new SsheetSpacingMappingEntity();
//		sheet2.setDetailsHeading("test A");
//		sheet2.setPdfname("testaaa.pdf");
//		sheet2.setQuadNumber("3");
//		sheet2.setRafterTrussSpacing("16");
//		sheet2.setRoofType("Rafter - Cathedral Clg");
//		sheet2.setSheetNumber("S-201");
//		sheet2.setStanchionMaxSpacing("48\"");
//		sheet2.setIsDeleted(false);
//		
//		SsheetLibraryEntity sSheet2 = new SsheetLibraryEntity();
//		sSheet2.setPdfName("testaaa.pdf");
//		sSheet2.setIsDeleted(false);
//		sheet2.setsSheetFile(sSheet2);
//		spacingMap2.add(sheet2);
//		
//		sheetSpacingMappingEntity.add(spacingMap2);
//		
//		List<SsheetSpacingMappingEntity> spacingMap3 = new ArrayList <SsheetSpacingMappingEntity> ();
//		
//		SsheetSpacingMappingEntity sheet3= new SsheetSpacingMappingEntity();
//		sheet3.setDetailsHeading("test A");
//		sheet3.setPdfname("testbbb.pdf");
//		sheet3.setQuadNumber("3");
//		sheet3.setRafterTrussSpacing("16");
//		sheet3.setRoofType("Rafter - Cathedral Clg");
//		sheet3.setSheetNumber("S-201");
//		sheet3.setStanchionMaxSpacing("48\"");
//		sheet3.setIsDeleted(false);
//		
//		SsheetLibraryEntity sSheet3 = new SsheetLibraryEntity();
//		sSheet3.setPdfName("testbbb.pdf");
//		sSheet3.setIsDeleted(false);
//		sheet3.setsSheetFile(sSheet3);
//		spacingMap3.add(sheet3);
//		
//		sheetSpacingMappingEntity.add(spacingMap3);
//		
//		List<SsheetSpacingMappingEntity> spacingMap4 = new ArrayList <SsheetSpacingMappingEntity> ();
//		
//		SsheetSpacingMappingEntity sheet4= new SsheetSpacingMappingEntity();
//		sheet4.setDetailsHeading("test bbbbbbbbbbbbbbb");
//		sheet4.setPdfname("testbbb.pdf");
//		sheet4.setQuadNumber("4");
//		sheet4.setRafterTrussSpacing("Other");
//		sheet4.setRafterTrussSpacing("50");
//		sheet4.setRoofType("Rafter - Cathedral Clg");
//		sheet4.setSheetNumber("S-200");
//		sheet4.setStanchionMaxSpacing("12\"");
//		sheet4.setIsDeleted(true);
//		
//		sheet4.setsSheetFile(null);
//		spacingMap4.add(sheet4);
//		
//		sheetSpacingMappingEntity.add(spacingMap4);
//		
//		List<SsheetSpacingMappingEntity> spacingMap5 = new ArrayList <SsheetSpacingMappingEntity> ();
//		
//		SsheetSpacingMappingEntity sheet5= new SsheetSpacingMappingEntity();
//		sheet5.setDetailsHeading("test bbbbbbbbbbbbbbb");
//		sheet5.setPdfname("testbbb.pdf");
//		sheet5.setQuadNumber("4");
//		sheet5.setRafterTrussSpacing("Other");
//		sheet5.setRafterTrussSpacing("50");
//		sheet5.setRoofType("Rafter - Cathedral Clg");
//		sheet5.setSheetNumber("S-200");
//		sheet5.setStanchionMaxSpacing("12\"");
//		sheet5.setIsDeleted(false);
//		
//		sheet5.setsSheetFile(null);
//		spacingMap5.add(sheet5);
//		
//		sheetSpacingMappingEntity.add(spacingMap5);
//		
//		List<SsheetSpacingMappingEntity> spacingMap = new ArrayList <SsheetSpacingMappingEntity> ();
//		SsheetSpacingMappingEntity sheet= new SsheetSpacingMappingEntity();
//		sheet.setDetailsHeading("STANCHION SPACING DETIAL");
//		sheet.setPdfname("16RAFTER-1_4FOOTMAX.pdf");
//		sheet.setQuadNumber("4");
//		sheet.setRafterTrussSpacing("16\"");
//		sheet.setRoofType("Rafter - Simple Attic");
//		sheet.setSheetNumber("S-200");
//		sheet.setStanchionMaxSpacing("24\"");
//		sheet.setIsDeleted(false);
//		
//		SsheetLibraryEntity sSheet = new SsheetLibraryEntity();
//		sSheet.setPdfName("editttt1 2 - S200 DPW W-PFU FLASHING - D - LANDSCAPE - HORIZONTAL.pdf");
//		sSheet.setIsDeleted(false);
//		sheet.setsSheetFile(sSheet);
//		spacingMap.add(sheet);
//		sheetSpacingMappingEntity.add(spacingMap);
//		
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			
//			Query mockedQuery3 = mock(Query.class);
//			when(em.createQuery("SELECT u from SsheetSpacingMappingEntity u where u.isDeleted = :p1")).thenReturn(mockedQuery3);
//			when(mockedQuery3.setParameter("p1", false)).thenReturn(mockedQuery3);
//			when(mockedQuery3.getResultList()).thenReturn(sheetSpacingMappingEntity.get(i));
//			planSetService.sSheetSpacingMapping((PermitHomeSiteEntityResult)scenario.get(i).get(0), (String)scenario.get(i).get(1), (PermitProjectSiteInfoEntityTwo)scenario.get(i).get(2));
//		}
//	
//    }
//    
//    @Test
//    public void sSheetRackingMapping() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//    	
//    	scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		RoofAttachmentsEntity stanchionFoot = new RoofAttachmentsEntity();
//		RailRacking railRacking = new RailRacking();
//		Flashing flashing = new Flashing();
//		PermitHomeSiteEntityResult permitHomeSite = new PermitHomeSiteEntityResult();
//		ElectricalUtilityEntity electricalCompany = new ElectricalUtilityEntity();
//		scenario.get(1).add(stanchionFoot);
//		scenario.get(1).add("");
//		scenario.get(1).add(railRacking);
//		scenario.get(1).add(flashing);
//		scenario.get(1).add(permitHomeSite);
//		scenario.get(1).add(electricalCompany);
//		
//		scenario.add(new ArrayList<Object>());
//		RoofAttachmentsEntity stanchionFoot1 = new RoofAttachmentsEntity();
//		RailRacking railRacking1 = new RailRacking();
//		railRacking1.setManufacturer("makeRailRacking");
//		railRacking1.setModel("modelRailRacking");
//		Flashing flashing1 = new Flashing();
//		flashing1.setManufacturer("makeFlashing");
//		PermitHomeSiteEntityResult permitHomeSite1 = new PermitHomeSiteEntityResult();
//		permitHomeSite1.setProjectJurisdiction("county of California");
//		permitHomeSite1.setCity("CA");
//		ElectricalUtilityEntity electricalCompany1 = new ElectricalUtilityEntity();
//		electricalCompany1.setUtilityCompanyName("utilityCompanyName");
//		scenario.get(2).add(stanchionFoot1);
//		scenario.get(2).add("");
//		scenario.get(2).add(railRacking1);
//		scenario.get(2).add(flashing1);
//		scenario.get(2).add(permitHomeSite1);
//		scenario.get(2).add(electricalCompany1);
//		
//		scenario.add(new ArrayList<Object>());
//		RoofAttachmentsEntity stanchionFoot2 = new RoofAttachmentsEntity();
//		RailRacking railRacking2 = new RailRacking();
//		railRacking2.setManufacturer("DPW Solar");
//		railRacking2.setModel("modelRailRacking");
//		Flashing flashing2 = new Flashing();
//		flashing2.setManufacturer("makeFlashing");
//		PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//		permitHomeSite2.setProjectJurisdiction("county of California");
//		permitHomeSite2.setCity("CA");
//		permitHomeSite2.setRoofRafter("Rafter-Simple Attic");
//		ElectricalUtilityEntity electricalCompany2 = new ElectricalUtilityEntity();
//		electricalCompany2.setUtilityCompanyName("utilityCompanyName");
//		scenario.get(3).add(stanchionFoot2);
//		scenario.get(3).add("Rafter - Simple Attic");
//		scenario.get(3).add(railRacking2);
//		scenario.get(3).add(flashing2);
//		scenario.get(3).add(permitHomeSite2);
//		scenario.get(3).add(electricalCompany2);
//		
//		List<List<SsheetRackingMappingEntity>> sheetRackingMappingEntity= new ArrayList <List<SsheetRackingMappingEntity>> ();
//		sheetRackingMappingEntity.add(null);
//		
//		List<SsheetRackingMappingEntity> sheetMapList2= new ArrayList <SsheetRackingMappingEntity>();
//		SsheetRackingMappingEntity sheetMap2= new SsheetRackingMappingEntity();
//		sheetMap2.setId(8);
//		sheetMap2.setDetailsHeading("ROOF TYPE A / RAFTER - PORTRAIT");
//		sheetMap2.setFlashingManufacturer("any");
//		sheetMap2.setIsDeleted(true);
//		sheetMap2.setPdfname("");
//		sheetMap2.setQuadNumber("1");
//		sheetMap2.setRackingManufacturer("DPW Solar");
//		sheetMap2.setRackingModel("any");
//		sheetMap2.setRoofManufacturer("any");
//		sheetMap2.setRoofModel("");
//		sheetMap2.setRoofType("Rafter - Simple Attic");
//		sheetMap2.setUtilityCompany("utility");
//		sheetMap2.setSheetNumber("S-200");
//		
//		sheetMapList2.add(0, sheetMap2);
//		sheetRackingMappingEntity.add(sheetMapList2);
//		
//		List<SsheetRackingMappingEntity> sheetMapList3= new ArrayList <SsheetRackingMappingEntity>();
//		SsheetRackingMappingEntity sheetMap3= new SsheetRackingMappingEntity();
//		sheetMap3.setId(8);
//		sheetMap3.setDetailsHeading("ROOF TYPE A / RAFTER - PORTRAIT");
//		sheetMap3.setFlashingManufacturer("any");
//		sheetMap3.setIsDeleted(true);
//		sheetMap3.setPdfname("");
//		sheetMap3.setQuadNumber("1");
//		sheetMap3.setRackingManufacturer("DPW Solar");
//		sheetMap3.setRackingModel("any");
//		sheetMap3.setRoofManufacturer("any");
//		sheetMap3.setRoofModel("");
//		sheetMap3.setRoofType("Rafter - Simple Attic");
//		sheetMap3.setUtilityCompany("utility");
//		sheetMap3.setSheetNumber("S-200");
//		
//		SsheetRackingMappingEntity sheetMap4= new SsheetRackingMappingEntity();
//		sheetMap4.setId(2);
//		sheetMap4.setDetailsHeading("ROOF TYPE B / RAFTER - PORTRAIT");
//		sheetMap4.setFlashingManufacturer("any");
//		sheetMap4.setIsDeleted(false);
//		sheetMap4.setPdfname("1 2 - S200 DPW W-PFU FLASHING - B - LANDSCAPE - HORIZONTAL");
//		sheetMap4.setQuadNumber("1");
//		sheetMap4.setRackingManufacturer("DPW Solar");
//		sheetMap4.setRackingModel("any");
//		sheetMap4.setRoofManufacturer("any");
//		sheetMap4.setRoofModel("any");
//		sheetMap4.setRoofType("Rafter - Simple Attic");
//		sheetMap4.setUtilityCompany("utility");
//		sheetMap4.setSheetNumber("S-200");
//		
//		SsheetLibraryEntity sSheetLibrary2 = new SsheetLibraryEntity();
//		sSheetLibrary2.setId(142);
//		sSheetLibrary2.setIsDeleted(true);
//		sSheetLibrary2.setPdfName("1 2 - S200 DPW W-PFU FLASHING - B - LANDSCAPE - HORIZONTAL.pdf");
//		sheetMap4.setsSheetFile(sSheetLibrary2);
//		
//		sheetMapList3.add(0, sheetMap3);
//		sheetMapList3.add(1, sheetMap4);
//		sheetRackingMappingEntity.add(sheetMapList3);
//		
//		List<SsheetRackingMappingEntity> sheetMapList= new ArrayList <SsheetRackingMappingEntity>();
//		SsheetRackingMappingEntity sheetMap= new SsheetRackingMappingEntity();
//		sheetMap.setId(1);
//		sheetMap.setDetailsHeading("ROOF TYPE A / RAFTER - PORTRAIT");
//		sheetMap.setFlashingManufacturer("any");
//		sheetMap.setIsDeleted(false);
//		sheetMap.setPdfname("1 2 - S200 DPW W-PFU FLASHING - A - LANDSCAPE - HORIZONTAL");
//		sheetMap.setQuadNumber("1");
//		sheetMap.setRackingManufacturer("DPW Solar");
//		sheetMap.setRackingModel("any");
//		sheetMap.setRoofManufacturer("any");
//		sheetMap.setRoofModel("");
//		sheetMap.setRoofType("Rafter - Simple Attic");
//		sheetMap.setSheetNumber("S-200");
//		
//		SsheetLibraryEntity sSheetLibrary = new SsheetLibraryEntity();
//		sSheetLibrary.setId(141);
//		sSheetLibrary.setIsDeleted(false);
//		sSheetLibrary.setPdfName("edittt1 2 - S200 DPW W-PFU FLASHING - A - LANDSCAPE - HORIZONTAL.pdf");
//		sheetMap.setsSheetFile(sSheetLibrary);
//		sheetMapList.add(0, sheetMap);
//		sheetRackingMappingEntity.add(sheetMapList);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			Query mockedQuery3 = mock(Query.class);
//			when(em.createQuery("SELECT u from SsheetRackingMappingEntity u WHERE u.isDeleted =:p1 ")).thenReturn(mockedQuery3);
//			when(mockedQuery3.setParameter("p1", false)).thenReturn(mockedQuery3);
//			when(mockedQuery3.getResultList()).thenReturn(sheetRackingMappingEntity.get(i));
//			planSetService.sSheetRackingMapping((RoofAttachmentsEntity)scenario.get(i).get(0), (String)scenario.get(i).get(1), (RailRacking)scenario.get(i).get(2), (Flashing)scenario.get(i).get(3), (PermitHomeSiteEntityResult)scenario.get(i).get(4), (ElectricalUtilityEntity)scenario.get(i).get(5));
//		}
//		
//    }
//    
//    @Test
//    public void rSheetModule() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//    	
//    	scenario.add(new ArrayList<Object>());
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
//		scenario.add(new ArrayList<Object>());
//		Cmodulev2 moduleInfo = new Cmodulev2();
//		PermitHomeSiteEntityResult permitHomeSite= new PermitHomeSiteEntityResult();
//		PermitAdditionalInfoEntityResult permitAdditionalInfo = new PermitAdditionalInfoEntityResult();
//		String idPermit="";
//		PermitEntity permitEntity=new PermitEntity();
//		String permitSusyemSize="";
//		AuthentificationEntityResult userLogedInfo = new AuthentificationEntityResult();
//		EditUserInformations editUserInformations = new EditUserInformations();
//		Integer index = 0;
//		AuthentificationEntity userConnectedEntity=new AuthentificationEntity();
//		String idUser="";
//		scenario.get(1).add(moduleInfo);
//		scenario.get(1).add(permitHomeSite);
//		scenario.get(1).add(permitAdditionalInfo);
//		scenario.get(1).add(idPermit);
//		scenario.get(1).add(permitEntity);
//		scenario.get(1).add(permitSusyemSize);
//		scenario.get(1).add(userLogedInfo);
//		scenario.get(1).add(editUserInformations);
//		scenario.get(1).add(index);
//		scenario.get(1).add(userConnectedEntity);
//		scenario.get(1).add(idUser);
//		
//		scenario.add(new ArrayList<Object>());
//		Cmodulev2 moduleInfo1 = new Cmodulev2();
//		moduleInfo1.setMake("make1");
//		moduleInfo1.setModel("model1");
//		PermitEntity permitEntity1=new PermitEntity();
//		permitEntity1.setId(11462550);
//		PermitHomeSiteEntityResult permitHomeSite1= new PermitHomeSiteEntityResult();
//		permitHomeSite1.setState("CA");
//		permitHomeSite1.setUtilityCompanyName("4050");
//		permitHomeSite1.setBuildingOccupancy("R-3");
//		permitHomeSite1.setBuildingRisk("Risk Category II");
//		permitHomeSite1.setCity("PASADENA");
//		permitHomeSite1.setEmailPhone("arij@nuagetechnologies-tn.com");
//		permitHomeSite1.setIfServiceVoltage(true);
//		permitHomeSite1.setMaxHorizontalSpanAtArrays("3");
//		permitHomeSite1.setMaxHorizontalSpanAtArraysHS("13");
//		permitHomeSite1.setPostalCode("91109");
//		permitHomeSite1.setPropertyAPN("0120254102");
//		permitHomeSite1.setResidenceBindingCategory("Exposure A");
//		permitHomeSite1.setRidgeBeamDepthAtArrays("7 1/4");
//		permitHomeSite1.setRoofRafter("Rafter - Strut to Walls Below");
//		permitHomeSite1.setSameMailing(true);
//		permitHomeSite1.setSiteAddress("4800 Oak Grove Dr");
//		permitHomeSite1.setStanchionMaxSpacing("48");
//		permitHomeSite1.setMaxHorizontalSpanAtArraysHSInches("2");
//		permitHomeSite1.setMaxHorizontalSpanAtArraysInches("2");
//		permitHomeSite1.setProjectJurisdiction("city");
//		PermitAdditionalInfoEntityResult permitAdditionalInfo1 = new PermitAdditionalInfoEntityResult();
//		permitAdditionalInfo1.setFormatSize("11x17 (Min. Size Recommended)");
//		String idPermit1="11462550";
//		String permitSusyemSize1="20.12";
//		AuthentificationEntityResult userLogedInfo1 = new AuthentificationEntityResult();
//		userLogedInfo1.setId(21233);
//		EditUserInformations editUserInformations1 = new EditUserInformations();
//		editUserInformations1.setMailingAddress("Mezanine APT 1");
//		editUserInformations1.setSecondMailingAddress("BL2");
//		editUserInformations1.setMailingCity("MadD");
//		editUserInformations1.setMailingState("CA");
//		editUserInformations1.setMailingZipCode("5100");
//		editUserInformations1.setContactPhone("912-547-8522");
//		editUserInformations1.setContractorCode("6666");
//		editUserInformations1.setCompany("Nuage Technologies");
//		editUserInformations1.setId(114001);
//		editUserInformations1.setContractorLicenceState("IL");
//		editUserInformations1.setContractorLic(true);
//		editUserInformations1.setContractorLicC10(true);
//		Integer index1 = 3;
//		AuthentificationEntity userConnectedEntity1=new AuthentificationEntity();
//		userConnectedEntity1.setId(21233);
//		String idUser1="21233";
//		scenario.get(2).add(moduleInfo1);
//		scenario.get(2).add(permitHomeSite1);
//		scenario.get(2).add(permitAdditionalInfo1);
//		scenario.get(2).add(idPermit1);
//		scenario.get(2).add(permitEntity1);
//		scenario.get(2).add(permitSusyemSize1);
//		scenario.get(2).add(userLogedInfo1);
//		scenario.get(2).add(editUserInformations1);
//		scenario.get(2).add(index1);
//		scenario.get(2).add(userConnectedEntity1);
//		scenario.get(2).add(idUser1);
//		
//		for(int i = 0; i < scenario.size(); i++) {
//			planSetService.rSheetModule((Cmodulev2)scenario.get(i).get(0),  (PermitHomeSiteEntityResult)scenario.get(i).get(1), (PermitAdditionalInfoEntityResult)scenario.get(i).get(2), (String)scenario.get(i).get(3), (PermitEntity)scenario.get(i).get(4), (String)scenario.get(i).get(5),
//			(AuthentificationEntityResult)scenario.get(i).get(6), (EditUserInformations)scenario.get(i).get(7), (AuthentificationEntity)scenario.get(i).get(9),(String)scenario.get(i).get(10));
//		}
//    }
//    
//    @Test
//    public void getAmbiantId() {
//    	List<PermitLayoutEntity> scenario = new ArrayList<PermitLayoutEntity>();
//    	List<Integer> result = new ArrayList<Integer>();
//    	
//    	PermitLayoutEntity permitLayoutEntity = new PermitLayoutEntity();
//    	scenario.add(permitLayoutEntity);
//    	
//    	PermitLayoutEntity permitLayoutEntity1 = new PermitLayoutEntity();
//    	permitLayoutEntity1.setConduitRun("Roof Top");
//    	permitLayoutEntity1.setConduitMounted(true);
//    	permitLayoutEntity1.setConduitRoofter("3512");
//    	scenario.add(permitLayoutEntity1);
//    	result.add(3);
//    	
//    	PermitLayoutEntity permitLayoutEntity2 = new PermitLayoutEntity();
//    	permitLayoutEntity2.setConduitRun("Roof Top");
//    	permitLayoutEntity2.setConduitMounted(false);
//    	result.add(2);
//    	
//    	PermitLayoutEntity permitLayoutEntity3 = new PermitLayoutEntity();
//    	permitLayoutEntity3.setConduitRun("Roof Top");
//    	permitLayoutEntity3.setConduitMounted(true);
//    	permitLayoutEntity3.setConduitRoofter("1236");
//    	scenario.add(permitLayoutEntity3);
//    	result.add(4);
//    	
//    	PermitLayoutEntity permitLayoutEntity4 = new PermitLayoutEntity();
//    	permitLayoutEntity4.setConduitRun("Roof Top");
//    	permitLayoutEntity4.setConduitMounted(true);
//    	permitLayoutEntity4.setConduitRoofter("sup36");
//    	scenario.add(permitLayoutEntity4);
//    	result.add(5);
//    	
//    	PermitLayoutEntity permitLayoutEntity5 = new PermitLayoutEntity();
//    	permitLayoutEntity5.setConduitRun("Attic");
//    	permitLayoutEntity5.setConduitMounted(true);
//    	permitLayoutEntity5.setConduitRoofter("sup36");
//    	scenario.add(permitLayoutEntity5);
//    	result.add(3);
//    	
//    	for(int i = 0; i < scenario.size(); i++) {
//		planSetService.getAmbiantId(scenario.get(i));
//		}
//    }
//    
//    @Test
//    public void getModuleOcpdNumber() {
//    	List<String> scenario = new ArrayList<String>();
//    	List<String> getSingleResultList = new ArrayList<String>();
//    	
//    	scenario.add(null);
//    	getSingleResultList.add(null);
//    	
//    	scenario.add("");
//    	getSingleResultList.add(null);
//    	
//    	scenario.add("Module:test");
//    	getSingleResultList.add(null);
//    	
//    	scenario.add("Module:test");
//    	getSingleResultList.add("");
//    	
//    	scenario.add("Module:test");
//    	getSingleResultList.add("3fgr");
//    	
//    	scenario.add("Module:test");
//    	getSingleResultList.add("3,15");
//    	
//		Query mockedQuery = mock(Query.class);
//		   when(em.createQuery("Select u.iacmax  "
//					+ " from Cmodulev2 u  "
//					+ " where u.make = :p1 "
//					+ " AND u.model = :p2 ")).thenReturn(mockedQuery);
//		  when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//    	double [] expectedresult = {0,0,0,0,3.15};
//    	
//    	
//    	for(int i = 0; i < scenario.size(); i++) {
//    		when(mockedQuery.getSingleResult()).thenReturn(getSingleResultList.get(i));
//    		double result = planSetService.getModuleOcpdNumber(scenario.get(i));
//			   //assertEquals(expectedresult[i], result,0);
//		}
//    }
//    
//    @Test
//    public void getInverterOcpdNumber() {
//    	List<String> scenario = new ArrayList<String>();
//    	List<String> getSingleResultList = new ArrayList<String>();
//    	
//    	scenario.add(null);
//    	getSingleResultList.add(null);
//    	
//    	scenario.add("");
//    	getSingleResultList.add(null);
//    	
//    	scenario.add("Fav Removed");
//    	getSingleResultList.add(null);
//    	
//    	scenario.add("Removed");
//    	getSingleResultList.add(null);
//    	
//    	scenario.add("Module:test");
//    	getSingleResultList.add("");
//    	
//    	scenario.add("Module:test");
//    	getSingleResultList.add(null);
//    	
//    	scenario.add("Module:test");
//    	getSingleResultList.add("3fgr");
//    	
//    	scenario.add("Module:test");
//    	getSingleResultList.add("3,15");
//    	
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery(
//				"Select u.iacmax  " + " from Inverters u  " + " where u.make = :p1 " + " AND u.model = :p2 "))
//						.thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//		double[] expectedresult = { 0, 0, 0, 0, 0, 0, 0, 3.15 };
//
//		for (int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getSingleResult()).thenReturn(getSingleResultList.get(i));
//			double result = planSetService.getInverterOcpdNumber(scenario.get(i));
//			//assertEquals(expectedresult[i], result, 0);
//		}
//    }
//    
//    @Test
//    public void getWireQtyMicroInverter() {
//    	List<List<String>> scenario = new ArrayList<List<String>>();
//    	List<String> getSingleResultList = new ArrayList<String>();
//    	
//    	scenario.add(new ArrayList<String>());
//    	scenario.get(0).add(null);
//    	scenario.get(0).add(null);
//    	getSingleResultList.add(null);
//    	
//    	scenario.add(new ArrayList<String>());
//    	scenario.get(1).add("");
//    	scenario.get(1).add("");
//    	getSingleResultList.add(null);
//    	
//    	scenario.add(new ArrayList<String>());
//    	scenario.get(2).add("");
//    	scenario.get(2).add("Fav Removed");
//    	getSingleResultList.add(null);
//    	
//    	scenario.add(new ArrayList<String>());
//    	scenario.get(3).add("");
//    	scenario.get(3).add("Removed");
//    	getSingleResultList.add(null);
//    	
//    	scenario.add(new ArrayList<String>());
//    	scenario.get(4).add("");
//    	scenario.get(4).add("microInverterModel");
//    	getSingleResultList.add(null);
//    	
//    	scenario.add(new ArrayList<String>());
//    	scenario.get(5).add("");
//    	scenario.get(5).add("microInverterModel");
//    	getSingleResultList.add("");
//    	
//    	scenario.add(new ArrayList<String>());
//    	scenario.get(6).add("");
//    	scenario.get(6).add("microInverterModel");
//    	getSingleResultList.add("WireQty");
//    	
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("Select u.wireQty  " + " from Inverters u  " + " where u.model = :p1 and u.make = :p2"))
//				.thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//
//		List<String> expectedresult = Arrays.asList("","","","",null,"","WireQty");
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getSingleResult()).thenReturn(getSingleResultList.get(i));
//			String result = planSetService.getWireQtyMicroInverter(scenario.get(i).get(0), scenario.get(i).get(1));
//			assertEquals(expectedresult.get(i), result);
//		}
//    }
//
//
//@Test
//public void getOcpdNumberMicroInverterS() {
//	List<List<String>> scenario = new ArrayList<List<String>>();
//	List<String> getSingleResultList = new ArrayList<String>();
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(0).add(null);
//	scenario.get(0).add(null);
//	getSingleResultList.add(null);
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(1).add("");
//	scenario.get(1).add("");
//	getSingleResultList.add(null);
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(2).add("");
//	scenario.get(2).add("Fav Removed");
//	getSingleResultList.add(null);
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(3).add("");
//	scenario.get(3).add("Removed");
//	getSingleResultList.add(null);
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(4).add("");
//	scenario.get(4).add("microInverterModel");
//	getSingleResultList.add(null);
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(5).add("");
//	scenario.get(5).add("microInverterModel");
//	getSingleResultList.add("");
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(6).add("");
//	scenario.get(6).add("microInverterModel");
//	getSingleResultList.add("iacmax");
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(7).add("");
//	scenario.get(7).add("microInverterModel");
//	getSingleResultList.add("3,15");
//	
//	Query mockedQuery = mock(Query.class);
//	when(em.createQuery("Select u.iacmax  " + " from Inverters u  " + " where u.model = :p1 and u.make = :p2"))
//			.thenReturn(mockedQuery);
//	when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//
//	double[] expectedresult = { 0, 0, 0, 0, 0, 0, 3,9375};
//	
//	for (int i = 0; i < scenario.size(); i++) {
//		when(mockedQuery.getSingleResult()).thenReturn(getSingleResultList.get(i));
//		double result = planSetService.getOcpdNumberMicroInverterS(scenario.get(i).get(0), scenario.get(i).get(1));
//		//assertEquals(expectedresult[i], result, 0);
//	}
//}
//
//@Test
//public void getOcpdNumber() {
//	List<List<String>> scenario = new ArrayList<List<String>>();
//	List<String> getSingleResultList = new ArrayList<String>();
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(0).add(null);
//	scenario.get(0).add(null);
//	getSingleResultList.add(null);
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(1).add("");
//	scenario.get(1).add("");
//	getSingleResultList.add(null);
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(2).add("");
//	scenario.get(2).add("Fav Removed");
//	getSingleResultList.add(null);
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(3).add("");
//	scenario.get(3).add("Removed");
//	getSingleResultList.add(null);
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(4).add("");
//	scenario.get(4).add("InverterModel:InverterMicro");
//	getSingleResultList.add(null);
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(5).add("");
//	scenario.get(5).add("InverterModel:InverterMicro");
//	getSingleResultList.add("");
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(6).add("");
//	scenario.get(6).add("InverterModel:InverterMicro");
//	getSingleResultList.add("iacmax");
//	
//	scenario.add(new ArrayList<String>());
//	scenario.get(7).add("");
//	scenario.get(7).add("InverterModel:InverterMicro");
//	getSingleResultList.add("3,5");
//	
//	Query mockedQuery1 = mock(Query.class);
//	when(em.createQuery("Select u.iacmax  "
//			+ " from Inverters u  "
//			+ " where u.make = :p1 "
//			+ " AND u.model = :p2 "))
//			.thenReturn(mockedQuery1);
//	when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
////	Query mockedQuery2 = mock(Query.class);
////	when(em.createQuery("Select u.iacmax  "
////			+ " from Inverters u  "
////			+ " where u.make = :p1 "
////			+ " AND u.model = :p2 "))
////			.thenReturn(mockedQuery2);
////	when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
////	
////	Query mockedQuery3 = mock(Query.class);
////	when(em.createQuery("Select u.iacmax  " + " from Inverters u  " + " where u.model = :p1 and u.make = :p2"))
////			.thenReturn(mockedQuery3);
////	when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
////	
////	Query mockedQuery4 = mock(Query.class);
////	when(em.createQuery("Select u.iacmax  " + " from Inverters u  " + " where u.model = :p1 and u.make = :p2"))
////			.thenReturn(mockedQuery4);
////	when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
////	
////	Query mockedQuery5 = mock(Query.class);
////	when(em.createQuery("Select u.iacmax  " + " from Inverters u  " + " where u.model = :p1 and u.make = :p2"))
////			.thenReturn(mockedQuery5);
////	when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
//	double[] expectedresult = { 0, 0, 0, 0, 0, 0, 0 ,21,875};
//	
//	for (int i = 0; i < scenario.size(); i++) {
//		when(mockedQuery1.getSingleResult()).thenReturn(getSingleResultList.get(i));
////		when(mockedQuery2.getSingleResult()).thenReturn(getSingleResultList.get(i));
////		when(mockedQuery3.getSingleResult()).thenReturn(getSingleResultList.get(i));
////		when(mockedQuery4.getSingleResult()).thenReturn(getSingleResultList.get(i));
////		when(mockedQuery5.getSingleResult()).thenReturn(getSingleResultList.get(i));
//		double result = planSetService.getOcpdNumber(scenario.get(i).get(1), scenario.get(i).get(1), scenario.get(i).get(1), scenario.get(i).get(1), scenario.get(i).get(1));
//	//	assertEquals(expectedresult[i], result, 0);
//	}
//}
//
//@Test
//public void getRoofRafter() {
//	
//	List<PermitHomeSiteEntityResult> scenario = new ArrayList<PermitHomeSiteEntityResult>();
//	scenario.add(null);
//	
//	PermitHomeSiteEntityResult permitHomeSite = new PermitHomeSiteEntityResult();
//	scenario.add(permitHomeSite);
//	
//	PermitHomeSiteEntityResult permitHomeSite1 = new PermitHomeSiteEntityResult();
//	permitHomeSite1.setRoofRafter("roofRafter");
//	scenario.add(permitHomeSite1);
//	
//	PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//	permitHomeSite2.setRoofRafter("Rafter-Simple Attic");
//	scenario.add(permitHomeSite2);
//	
//	PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//	permitHomeSite3.setRoofRafter("Rafter - Cathedral Ceiling");
//	scenario.add(permitHomeSite3);
//	
//	PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//	permitHomeSite4.setRoofRafter("Rafter - Strut to Walls Below");
//	scenario.add(permitHomeSite4);
//	
//	PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//	permitHomeSite5.setRoofRafter("Pre-Eng Roof Trusses");
//	scenario.add(permitHomeSite5);
//	
//	PermitHomeSiteEntityResult permitHomeSite6 = new PermitHomeSiteEntityResult();
//	permitHomeSite6.setRoofRafter("Flat Roof With Trusses");
//	scenario.add(permitHomeSite6);
//	
//	PermitHomeSiteEntityResult permitHomeSite7 = new PermitHomeSiteEntityResult();
//	permitHomeSite7.setRoofRafter("Metal Building with I Beams and Purlins");
//	scenario.add(permitHomeSite7);
//	
//	PermitHomeSiteEntityResult permitHomeSite8 = new PermitHomeSiteEntityResult();
//	permitHomeSite8.setRoofRafter("Other Or we will provide images");
//	scenario.add(permitHomeSite8);
//	
//	//List<String> expectedresult = Arrays.asList("",null,"roofRafter","Rafter - Simple Attic","Rafter - Cathedral Clg","Rafter - Struts to wall(s) below","Truss - Manufactured Plate Truss","Flat Roof Truss","Typical Metal Bld","Typical Metal Bld");
//	for (int i = 0; i < scenario.size(); i++) {
//		String result = planSetService.getRoofRafter(scenario.get(i));
//		//assertEquals(expectedresult.get(i), result);
//	}
//}
//
//    
//@Test
//public void getTotalModuleString() {
//	
//	List<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//	scenario.add(null);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult = new PermitArrayEntityResultSecond();
//	scenario.add(permitArraysEntityResult);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult2.setDeviceToIncorporate("System Optimizer");
//	scenario.add(permitArraysEntityResult2);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult3 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult3.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult3.setStringOne("");
//	scenario.add(permitArraysEntityResult3);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult4 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult4.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult4.setStringOne("15A");
//	scenario.add(permitArraysEntityResult4);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult5 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult5.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult5.setStringOne("1");
//	scenario.add(permitArraysEntityResult5);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult6 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult6.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult6.setStringOne("1");
//	permitArraysEntityResult6.setStringTwo("");
//	scenario.add(permitArraysEntityResult6);
//	
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult7 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult7.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult7.setStringOne("1");
//	permitArraysEntityResult7.setStringTwo("15A");
//	scenario.add(permitArraysEntityResult7);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult8 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult8.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult8.setStringOne("1");
//	permitArraysEntityResult8.setStringTwo("1");
//	scenario.add(permitArraysEntityResult8);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult9 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult9.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult9.setStringOne("1");
//	permitArraysEntityResult9.setStringTwo("1");
//	permitArraysEntityResult9.setStringThree("");
//	scenario.add(permitArraysEntityResult9);
//	
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult10 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult10.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult10.setStringOne("1");
//	permitArraysEntityResult10.setStringTwo("1");
//	permitArraysEntityResult10.setStringThree("15A");
//	scenario.add(permitArraysEntityResult10);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult11 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult11.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult11.setStringOne("1");
//	permitArraysEntityResult11.setStringTwo("1");
//	permitArraysEntityResult11.setStringThree("1");
//	scenario.add(permitArraysEntityResult11);
//	
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult12 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult12.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult12.setStringOne("1");
//	permitArraysEntityResult12.setStringTwo("1");
//	permitArraysEntityResult12.setStringThree("1");
//	permitArraysEntityResult12.setStringFour("");
//	scenario.add(permitArraysEntityResult12);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult13 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult13.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult13.setStringOne("1");
//	permitArraysEntityResult13.setStringTwo("1");
//	permitArraysEntityResult13.setStringThree("1");
//	permitArraysEntityResult13.setStringFour("15A");
//	scenario.add(permitArraysEntityResult13);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult14 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult14.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult14.setStringOne("1");
//	permitArraysEntityResult14.setStringTwo("1");
//	permitArraysEntityResult14.setStringThree("1");
//	permitArraysEntityResult14.setStringFour("1");
//	scenario.add(permitArraysEntityResult14);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult15 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult15.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult15.setStringOne("1");
//	permitArraysEntityResult15.setStringTwo("1");
//	permitArraysEntityResult15.setStringThree("1");
//	permitArraysEntityResult15.setStringFour("1");
//	permitArraysEntityResult15.setStringFive("");
//	scenario.add(permitArraysEntityResult15);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult16 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult16.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult16.setStringOne("1");
//	permitArraysEntityResult16.setStringTwo("1");
//	permitArraysEntityResult16.setStringThree("1");
//	permitArraysEntityResult16.setStringFour("1");
//	permitArraysEntityResult16.setStringFive("15A");
//	scenario.add(permitArraysEntityResult16);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult17 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult17.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult17.setStringOne("1");
//	permitArraysEntityResult17.setStringTwo("1");
//	permitArraysEntityResult17.setStringThree("1");
//	permitArraysEntityResult17.setStringFour("1");
//	permitArraysEntityResult17.setStringFive("1");
//	scenario.add(permitArraysEntityResult17);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult18 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult18.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult18.setSecondStringOne("");
//	scenario.add(permitArraysEntityResult18);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult19 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult19.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult19.setSecondStringOne("15A");
//	scenario.add(permitArraysEntityResult19);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult20 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult20.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult20.setSecondStringOne("1");
//	scenario.add(permitArraysEntityResult20);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult21 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult21.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult21.setSecondStringOne("1");
//	permitArraysEntityResult21.setSecondStringTwo("");
//	scenario.add(permitArraysEntityResult21);
//	
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult22 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult22.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult22.setSecondStringOne("1");
//	permitArraysEntityResult22.setSecondStringTwo("15A");
//	scenario.add(permitArraysEntityResult22);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult23 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult23.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult23.setSecondStringOne("1");
//	permitArraysEntityResult23.setSecondStringTwo("1");
//	scenario.add(permitArraysEntityResult23);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult24 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult24.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult24.setSecondStringOne("1");
//	permitArraysEntityResult24.setSecondStringTwo("1");
//	permitArraysEntityResult24.setSecondStringThree("");
//	scenario.add(permitArraysEntityResult24);
//	
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult25 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult25.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult25.setSecondStringOne("1");
//	permitArraysEntityResult25.setSecondStringTwo("1");
//	permitArraysEntityResult25.setSecondStringThree("15A");
//	scenario.add(permitArraysEntityResult25);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult26 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult26.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult26.setSecondStringOne("1");
//	permitArraysEntityResult26.setSecondStringTwo("1");
//	permitArraysEntityResult26.setSecondStringThree("1");
//	scenario.add(permitArraysEntityResult26);
//	
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult27 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult27.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult27.setSecondStringOne("1");
//	permitArraysEntityResult27.setSecondStringTwo("1");
//	permitArraysEntityResult27.setSecondStringThree("1");
//	permitArraysEntityResult27.setSecondStringFour("");
//	scenario.add(permitArraysEntityResult27);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult28 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult28.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult28.setSecondStringOne("1");
//	permitArraysEntityResult28.setSecondStringTwo("1");
//	permitArraysEntityResult28.setSecondStringThree("1");
//	permitArraysEntityResult28.setSecondStringFour("15A");
//	scenario.add(permitArraysEntityResult28);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult29 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult29.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult29.setSecondStringOne("1");
//	permitArraysEntityResult29.setSecondStringTwo("1");
//	permitArraysEntityResult29.setSecondStringThree("1");
//	permitArraysEntityResult29.setSecondStringFour("1");
//	scenario.add(permitArraysEntityResult29);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult30 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult30.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult30.setSecondStringOne("1");
//	permitArraysEntityResult30.setSecondStringTwo("1");
//	permitArraysEntityResult30.setSecondStringThree("1");
//	permitArraysEntityResult30.setSecondStringFour("1");
//	permitArraysEntityResult30.setSecondStringFive("");
//	scenario.add(permitArraysEntityResult30);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult31 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult31.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult31.setSecondStringOne("1");
//	permitArraysEntityResult31.setSecondStringTwo("1");
//	permitArraysEntityResult31.setSecondStringThree("1");
//	permitArraysEntityResult31.setSecondStringFour("1");
//	permitArraysEntityResult31.setSecondStringFive("15A");
//	scenario.add(permitArraysEntityResult31);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult32 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult32.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult32.setSecondStringOne("1");
//	permitArraysEntityResult32.setSecondStringTwo("1");
//	permitArraysEntityResult32.setSecondStringThree("1");
//	permitArraysEntityResult32.setSecondStringFour("1");
//	permitArraysEntityResult32.setSecondStringFive("1");
//	scenario.add(permitArraysEntityResult32);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult33 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult33.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult33.setThirdStringOne(1);
//	scenario.add(permitArraysEntityResult33);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult34= new PermitArrayEntityResultSecond();
//	permitArraysEntityResult34.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult34.setThirdStringOne(1);
//	permitArraysEntityResult34.setThirdStringTwo(1);
//	scenario.add(permitArraysEntityResult34);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult35= new PermitArrayEntityResultSecond();
//	permitArraysEntityResult35.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult35.setThirdStringOne(1);
//	permitArraysEntityResult35.setThirdStringTwo(1);
//	permitArraysEntityResult35.setThirdStringThree(1);
//	scenario.add(permitArraysEntityResult35);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult36 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult36.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult36.setThirdStringOne(1);
//	permitArraysEntityResult36.setThirdStringTwo(1);
//	permitArraysEntityResult36.setThirdStringThree(1);
//	permitArraysEntityResult36.setThirdStringFour(1);
//	scenario.add(permitArraysEntityResult36);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult37 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult37.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult37.setThirdStringOne(1);
//	permitArraysEntityResult37.setThirdStringTwo(1);
//	permitArraysEntityResult37.setThirdStringThree(1);
//	permitArraysEntityResult37.setThirdStringFour(1);
//	permitArraysEntityResult37.setThirdStringFive(1);
//	scenario.add(permitArraysEntityResult37);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult38 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult38.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult38.setFourthStringOne(1);
//	scenario.add(permitArraysEntityResult38);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult39= new PermitArrayEntityResultSecond();
//	permitArraysEntityResult39.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult39.setFourthStringOne(1);
//	permitArraysEntityResult39.setFourthStringTwo(1);
//	scenario.add(permitArraysEntityResult39);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult40 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult40.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult40.setFourthStringOne(1);
//	permitArraysEntityResult40.setFourthStringTwo(1);
//	permitArraysEntityResult40.setFourthStringThree(1);
//	scenario.add(permitArraysEntityResult40);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult41= new PermitArrayEntityResultSecond();
//	permitArraysEntityResult41.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult41.setFourthStringOne(1);
//	permitArraysEntityResult41.setFourthStringTwo(1);
//	permitArraysEntityResult41.setFourthStringThree(1);
//	permitArraysEntityResult41.setFourthStringFour(1);
//	scenario.add(permitArraysEntityResult41);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult42 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult42.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult42.setFourthStringOne(1);
//	permitArraysEntityResult42.setFourthStringTwo(1);
//	permitArraysEntityResult42.setFourthStringThree(1);
//	permitArraysEntityResult42.setFourthStringFour(1);
//	permitArraysEntityResult42.setFourthStringFive(1);
//	scenario.add(permitArraysEntityResult42);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult43 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult43.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult43.setFifthStringOne(1);
//	scenario.add(permitArraysEntityResult43);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult44 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult44.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult44.setFifthStringOne(1);
//	permitArraysEntityResult44.setFifthStringTwo(1);
//	scenario.add(permitArraysEntityResult44);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult45= new PermitArrayEntityResultSecond();
//	permitArraysEntityResult45.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult45.setFifthStringOne(1);
//	permitArraysEntityResult45.setFifthStringTwo(1);
//	permitArraysEntityResult45.setFifthStringThree(1);
//	scenario.add(permitArraysEntityResult45);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult46 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult46.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult46.setFifthStringOne(1);
//	permitArraysEntityResult46.setFifthStringTwo(1);
//	permitArraysEntityResult46.setFifthStringThree(1);
//	permitArraysEntityResult46.setFifthStringFour(1);
//	scenario.add(permitArraysEntityResult46);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult47 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult47.setDeviceToIncorporate("System Optimizer");
//	permitArraysEntityResult47.setFifthStringOne(1);
//	permitArraysEntityResult47.setFifthStringTwo(1);
//	permitArraysEntityResult47.setFifthStringThree(1);
//	permitArraysEntityResult47.setFifthStringFour(1);
//	permitArraysEntityResult47.setFifthStringFive(1);
//	scenario.add(permitArraysEntityResult47);
//	
//	for (int i = 0; i < scenario.size(); i++) {
//		planSetService.getTotalModuleString(scenario.get(i));
//	}
//}
//
//@Test
//public void getTotalModuleMicro() {
//	
//	List<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//	scenario.add(null);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult = new PermitArrayEntityResultSecond();
//	scenario.add(permitArraysEntityResult);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult3 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult3.setNumberModulesACCircuitOne("");
//	scenario.add(permitArraysEntityResult3);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult4 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult4.setNumberModulesACCircuitOne("15A");
//	scenario.add(permitArraysEntityResult4);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult5 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult5.setNumberModulesACCircuitOne("1");
//	scenario.add(permitArraysEntityResult5);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult6 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult6.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult6.setNumberModulesACCircuitTwo("");
//	scenario.add(permitArraysEntityResult6);
//	
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult7 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult7.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult7.setNumberModulesACCircuitTwo("15A");
//	scenario.add(permitArraysEntityResult7);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult8 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult8.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult8.setNumberModulesACCircuitTwo("1");
//	scenario.add(permitArraysEntityResult8);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult9 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult9.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult9.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult9.setNumberModulesACCircuitThree("");
//	scenario.add(permitArraysEntityResult9);
//	
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult10 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult10.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult10.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult10.setNumberModulesACCircuitThree("15A");
//	scenario.add(permitArraysEntityResult10);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult11 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult11.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult11.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult11.setNumberModulesACCircuitThree("1");
//	scenario.add(permitArraysEntityResult11);
//	
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult12 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult12.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult12.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult12.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult12.setNumberModulesACCircuitFour("");
//	scenario.add(permitArraysEntityResult12);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult13 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult13.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult13.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult13.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult13.setNumberModulesACCircuitFour("15A");
//	scenario.add(permitArraysEntityResult13);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult14 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult14.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult14.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult14.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult14.setNumberModulesACCircuitFour("1");
//	scenario.add(permitArraysEntityResult14);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult15 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult15.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult15.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult15.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult15.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult15.setNumberModulesACCircuitFive("");
//	scenario.add(permitArraysEntityResult15);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult16 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult16.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult16.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult16.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult16.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult16.setNumberModulesACCircuitFive("15A");
//	scenario.add(permitArraysEntityResult16);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult17 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult17.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult17.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult17.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult17.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult17.setNumberModulesACCircuitFive("1");
//	scenario.add(permitArraysEntityResult17);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult18 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult18.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult18.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult18.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult18.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult18.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult18.setNumberModulesACCircuitSix("");
//	scenario.add(permitArraysEntityResult18);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult19 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult19.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult19.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult19.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult19.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult19.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult19.setNumberModulesACCircuitSix("15A");
//	scenario.add(permitArraysEntityResult19);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult20 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult20.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult20.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult20.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult20.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult20.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult20.setNumberModulesACCircuitSix("1");
//	scenario.add(permitArraysEntityResult20);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult21 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult21.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult21.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult21.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult21.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult21.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult21.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult21.setNumberModulesACCircuitSeven("");
//	scenario.add(permitArraysEntityResult21);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult22 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult22.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult22.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult22.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult22.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult22.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult22.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult22.setNumberModulesACCircuitSeven("15A");
//	scenario.add(permitArraysEntityResult22);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult23 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult23.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult23.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult23.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult23.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult23.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult23.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult23.setNumberModulesACCircuitSeven("1");
//	scenario.add(permitArraysEntityResult23);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult24 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult24.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult24.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult24.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult24.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult24.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult24.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult24.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult24.setNumberModulesACCircuitEight("");
//	scenario.add(permitArraysEntityResult24);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult25 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult25.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult25.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult25.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult25.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult25.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult25.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult25.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult25.setNumberModulesACCircuitEight("15A");
//	scenario.add(permitArraysEntityResult25);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult27 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult27.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult27.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult27.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult27.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult27.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult27.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult27.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult27.setNumberModulesACCircuitEight("1");
//	scenario.add(permitArraysEntityResult27);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult28 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult28.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult28.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult28.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult28.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult28.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult28.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult28.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult28.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult28.setNumberModulesACCircuitNine("");
//	scenario.add(permitArraysEntityResult28);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult29 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult29.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult29.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult29.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult29.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult29.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult29.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult29.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult29.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult29.setNumberModulesACCircuitNine("15A");
//	scenario.add(permitArraysEntityResult29);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult30 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult30.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult30.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult30.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult30.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult30.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult30.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult30.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult30.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult30.setNumberModulesACCircuitNine("1");
//	scenario.add(permitArraysEntityResult30);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult31 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult31.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult31.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult31.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult31.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult31.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult31.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult31.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult31.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult31.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult31.setNumberModulesACCircuitTen("");
//	scenario.add(permitArraysEntityResult31);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult32 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult32.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult32.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult32.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult32.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult32.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult32.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult32.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult32.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult32.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult32.setNumberModulesACCircuitTen("15A");
//	scenario.add(permitArraysEntityResult32);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult33 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult33.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult33.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult33.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult33.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult33.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult33.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult33.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult33.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult33.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult33.setNumberModulesACCircuitTen("1");
//	scenario.add(permitArraysEntityResult33);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult34 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult34.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult34.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult34.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult34.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult34.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult34.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult34.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult34.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult34.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult34.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult34.setNumberModulesACCircuitEleven("");
//	scenario.add(permitArraysEntityResult34);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult35 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult35.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult35.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult35.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult35.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult35.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult35.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult35.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult35.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult35.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult35.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult35.setNumberModulesACCircuitEleven("15A");
//	scenario.add(permitArraysEntityResult35);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult36 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult36.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult36.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult36.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult36.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult36.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult36.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult36.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult36.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult36.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult36.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult36.setNumberModulesACCircuitEleven("1");
//	scenario.add(permitArraysEntityResult36);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult37 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult37.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult37.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult37.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult37.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult37.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult37.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult37.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult37.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult37.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult37.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult37.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult37.setNumberModulesACCircuitTweleve("");
//	scenario.add(permitArraysEntityResult37);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult38 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult38.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult38.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult38.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult38.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult38.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult38.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult38.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult38.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult38.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult38.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult38.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult38.setNumberModulesACCircuitTweleve("15A");
//	scenario.add(permitArraysEntityResult38);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult39 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult39.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult39.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult39.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult39.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult39.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult39.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult39.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult39.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult39.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult39.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult39.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult39.setNumberModulesACCircuitTweleve("1");
//	scenario.add(permitArraysEntityResult39);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult40 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult40.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult40.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult40.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult40.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult40.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult40.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult40.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult40.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult40.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult40.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult40.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult40.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult40.setNumberModulesACCircuitThirteen("");
//	scenario.add(permitArraysEntityResult40);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult41 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult41.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult41.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult41.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult41.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult41.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult41.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult41.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult41.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult41.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult41.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult41.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult41.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult41.setNumberModulesACCircuitThirteen("15A");
//	scenario.add(permitArraysEntityResult41);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult42 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult42.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult42.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult42.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult42.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult42.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult42.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult42.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult42.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult42.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult42.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult42.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult42.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult42.setNumberModulesACCircuitThirteen("1");
//	scenario.add(permitArraysEntityResult42);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult43 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult43.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult43.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult43.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult43.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult43.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult43.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult43.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult43.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult43.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult43.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult43.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult43.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult43.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult43.setNumberModulesACCircuitFourteen("");
//	scenario.add(permitArraysEntityResult43);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult44 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult44.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult44.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult44.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult44.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult44.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult44.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult44.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult44.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult44.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult44.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult44.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult44.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult44.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult44.setNumberModulesACCircuitFourteen("15A");
//	scenario.add(permitArraysEntityResult44);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult45 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult45.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult45.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult45.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult45.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult45.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult45.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult45.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult45.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult45.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult45.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult45.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult45.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult45.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult45.setNumberModulesACCircuitFourteen("1");
//	scenario.add(permitArraysEntityResult45);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult46 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult46.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult46.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult46.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult46.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult46.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult46.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult46.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult46.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult46.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult46.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult46.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult46.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult46.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult46.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult46.setNumberModulesACCircuitFifteen("");
//	scenario.add(permitArraysEntityResult46);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult47 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult47.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult47.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult47.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult47.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult47.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult47.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult47.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult47.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult47.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult47.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult47.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult47.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult47.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult47.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult47.setNumberModulesACCircuitFifteen("15A");
//	scenario.add(permitArraysEntityResult47);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult48 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult48.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult48.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult48.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult48.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult48.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult48.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult48.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult48.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult48.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult48.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult48.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult48.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult48.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult48.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult48.setNumberModulesACCircuitFifteen("1");
//	scenario.add(permitArraysEntityResult48);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult49 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult49.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult49.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult49.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult49.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult49.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult49.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult49.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult49.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult49.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult49.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult49.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult49.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult49.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult49.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult49.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult49.setNumberModulesACCircuitSixteen("");
//	scenario.add(permitArraysEntityResult49);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult50= new PermitArrayEntityResultSecond();
//	permitArraysEntityResult50.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult50.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult50.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult50.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult50.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult50.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult50.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult50.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult50.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult50.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult50.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult50.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult50.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult50.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult50.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult50.setNumberModulesACCircuitSixteen("15A");
//	scenario.add(permitArraysEntityResult50);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult51 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult51.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult51.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult51.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult51.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult51.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult51.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult51.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult51.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult51.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult51.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult51.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult51.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult51.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult51.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult51.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult51.setNumberModulesACCircuitSixteen("1");
//	scenario.add(permitArraysEntityResult51);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult52 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult52.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult52.setNumberModulesACCircuitSeventeen("");
//	scenario.add(permitArraysEntityResult52);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult53 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult53.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult53.setNumberModulesACCircuitSeventeen("15A");
//	scenario.add(permitArraysEntityResult53);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult54 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult54.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult54.setNumberModulesACCircuitSeventeen("1");
//	scenario.add(permitArraysEntityResult54);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult55 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult55.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult55.setNumberModulesACCircuitEightteen("");
//	scenario.add(permitArraysEntityResult55);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult56 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult56.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult56.setNumberModulesACCircuitEightteen("15A");
//	scenario.add(permitArraysEntityResult56);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult57 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult57.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult57.setNumberModulesACCircuitEightteen("1");
//	scenario.add(permitArraysEntityResult57);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult58 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult58.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult58.setNumberModulesACCircuitNineteen("");
//	scenario.add(permitArraysEntityResult58);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult59 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult59.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult59.setNumberModulesACCircuitNineteen("15A");
//	scenario.add(permitArraysEntityResult59);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult60 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult60.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult60.setNumberModulesACCircuitNineteen("1");
//	scenario.add(permitArraysEntityResult60);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult61 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult61.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitNineteen("1");
//	permitArraysEntityResult61.setNumberModulesACCircuitTwenty("");
//	scenario.add(permitArraysEntityResult61);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult62 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult62.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitNineteen("1");
//	permitArraysEntityResult62.setNumberModulesACCircuitTwenty("15A");
//	scenario.add(permitArraysEntityResult62);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult63 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult63.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitNineteen("1");
//	permitArraysEntityResult63.setNumberModulesACCircuitTwenty("1");
//	scenario.add(permitArraysEntityResult63);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult64 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult64.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitNineteen("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitTwenty("1");
//	permitArraysEntityResult64.setNumberModulesACCircuitTwentyOne("");
//	scenario.add(permitArraysEntityResult64);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult65 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult65.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitNineteen("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitTwenty("1");
//	permitArraysEntityResult65.setNumberModulesACCircuitTwentyOne("15A");
//	scenario.add(permitArraysEntityResult65);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult66 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult66.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitNineteen("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitTwenty("1");
//	permitArraysEntityResult66.setNumberModulesACCircuitTwentyOne("1");
//	scenario.add(permitArraysEntityResult66);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult67= new PermitArrayEntityResultSecond();
//	permitArraysEntityResult67.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitNineteen("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitTwenty("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitTwentyOne("1");
//	permitArraysEntityResult67.setNumberModulesACCircuitTwentyTwo("");
//	scenario.add(permitArraysEntityResult67);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult68 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult68.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitNineteen("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitTwenty("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitTwentyOne("1");
//	permitArraysEntityResult68.setNumberModulesACCircuitTwentyTwo("15A");
//	scenario.add(permitArraysEntityResult68);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult69 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult69.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitNineteen("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitTwenty("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitTwentyOne("1");
//	permitArraysEntityResult69.setNumberModulesACCircuitTwentyTwo("1");
//	scenario.add(permitArraysEntityResult69);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult70 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult70.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitNineteen("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitTwenty("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitTwentyOne("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitTwentyTwo("1");
//	permitArraysEntityResult70.setNumberModulesACCircuitTwentyThree("");
//	scenario.add(permitArraysEntityResult70);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult71 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult71.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitNineteen("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitTwenty("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitTwentyOne("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitTwentyTwo("1");
//	permitArraysEntityResult71.setNumberModulesACCircuitTwentyThree("15A");
//	scenario.add(permitArraysEntityResult71);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult72 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult72.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitNineteen("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitTwenty("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitTwentyOne("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitTwentyTwo("1");
//	permitArraysEntityResult72.setNumberModulesACCircuitTwentyThree("1");
//	scenario.add(permitArraysEntityResult72);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult73 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult73.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitNineteen("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitTwenty("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitTwentyOne("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitTwentyTwo("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitTwentyThree("1");
//	permitArraysEntityResult73.setNumberModulesACCircuitTwentyFour("");
//	scenario.add(permitArraysEntityResult73);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult74 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult74.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitNineteen("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitTwenty("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitTwentyOne("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitTwentyTwo("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitTwentyThree("1");
//	permitArraysEntityResult74.setNumberModulesACCircuitTwentyFour("15A");
//	scenario.add(permitArraysEntityResult74);
//	
//	PermitArrayEntityResultSecond permitArraysEntityResult75 = new PermitArrayEntityResultSecond();
//	permitArraysEntityResult75.setNumberModulesACCircuitOne("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitTwo("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitThree("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitFour("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitFive("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitSix("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitSeven("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitEight("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitNine("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitTen("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitEleven("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitTweleve("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitThirteen("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitFourteen("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitFifteen("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitSixteen("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitSeventeen("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitEightteen("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitNineteen("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitTwenty("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitTwentyOne("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitTwentyTwo("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitTwentyThree("1");
//	permitArraysEntityResult75.setNumberModulesACCircuitTwentyFour("1");
//	scenario.add(permitArraysEntityResult75);
//	
//	for (int i = 0; i < scenario.size(); i++) {
//		planSetService.getTotalModuleMicro(scenario.get(i));
//	}
//}
//    @Test
//    public void getPermitSystemSize() {
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		Cmodulev2 cm = new Cmodulev2();
//		cm.setStcRounded("");
//		scenario.get(1).add(cm);
//		
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		Cmodulev2 cm2 = new Cmodulev2();
//		cm2.setStcRounded("-1258");
//		scenario.get(2).add(cm2);
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getPermitSystemSize [ " + i + " ]");
//			planSetService.getPermitSystemSize((Cmodulev2) scenario.get(i).get(0));
//		}
//    }  
//    
//    @Test
//    public void rSheetInverter() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u " + " from RsheetsLibraryEntity u " + " where (u.pdfName = :p1 OR u.pdfName = :p2) AND u.isDeleted = :p3 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
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
//		// Result of the query1
//		scenario.get(0).add(null);
//		
//
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		Inverters inv = new Inverters();
//		inv.setModel("test");
//		inv.setMake("junit");
//		scenario.get(1).add(inv);
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
//		// Result of the query1
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add(inv);
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
//		// Result of the query1
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<>();
//		list.add(new RsheetsLibraryEntity());
//		list.add(null);
//		scenario.get(2).add(list);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("rSheetInverter [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(11));
//
//			planSetService.rSheetInverter((Inverters) scenario.get(i).get(0),(PermitHomeSiteEntityResult) scenario.get(i).get(1),(PermitAdditionalInfoEntityResult) scenario.get(i).get(2),(String) scenario.get(i).get(3),(PermitEntity) scenario.get(i).get(4),(String) scenario.get(i).get(5),(AuthentificationEntityResult) scenario.get(i).get(6),(EditUserInformations) scenario.get(i).get(7),(AuthentificationEntity) scenario.get(i).get(8),(String) scenario.get(i).get(9));
//		}
//
//    }
//    @Test
//    public void rSheetOptimizer() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u " + " from RsheetsLibraryEntity u " + " where (u.pdfName = :p1 OR u.pdfName = :p2) AND u.isDeleted = :p3 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
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
//		// Result of the query1
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		PermitArrayEntityResultSecond par = new PermitArrayEntityResultSecond();
//		par.setDeviceToIncorporate("System Optimizer");
//		scenario.get(1).add(par);
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
//		// Result of the query1
//		scenario.get(1).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add(par);
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
//		// Result of the query1
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<>();
//		list.add(null);
//		list.add(new RsheetsLibraryEntity());
//		scenario.get(2).add(list);
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("rSheetOptimizer [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(12));
//
//			planSetService.rSheetOptimizer((PermitArrayEntityResultSecond) scenario.get(i).get(0),(DCOptimizerEntity) scenario.get(i).get(1),(PermitHomeSiteEntityResult) scenario.get(i).get(2),(PermitAdditionalInfoEntityResult) scenario.get(i).get(3),(String) scenario.get(i).get(4),(PermitEntity) scenario.get(i).get(5),(String) scenario.get(i).get(6),(AuthentificationEntityResult) scenario.get(i).get(7),(EditUserInformations) scenario.get(i).get(8),(AuthentificationEntity) scenario.get(i).get(9),(String) scenario.get(i).get(10));
//		}
//
//    }
//    
//    @Test
//    public void roofRsheetRailRacking() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u " + " from RsheetsLibraryEntity u " + " where (u.pdfName = :p1 OR u.pdfName = :p2) AND u.isDeleted = :p3 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
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
//		// Result of the query1
//		scenario.get(0).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		RailRacking ra = new RailRacking();
//		ra.setManufacturer("test");
//		ra.setModel("test junit");
//		scenario.get(1).add(ra);
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
//		// Result of the query1
//		scenario.get(1).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add(ra);
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
//		// Result of the query1
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(2).add(list);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(3).add(ra);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(1);
//		// Result of the query1
//		scenario.get(3).add(list);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(4).add(ra);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(new PermitEntity());
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(new AuthentificationEntity());
//		scenario.get(4).add(null);
//		scenario.get(4).add(1);
//		// Result of the query1
//		scenario.get(4).add(list);
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("roofRsheetRailRacking [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(11));
//			planSetService.roofRsheetRailRacking((RailRacking) scenario.get(i).get(0),(PermitHomeSiteEntityResult) scenario.get(i).get(1),(PermitAdditionalInfoEntityResult) scenario.get(i).get(2),(String) scenario.get(i).get(3),(PermitEntity) scenario.get(i).get(4),(String) scenario.get(i).get(5),(AuthentificationEntityResult) scenario.get(i).get(6),(EditUserInformations) scenario.get(i).get(7),(AuthentificationEntity) scenario.get(i).get(8),(String) scenario.get(i).get(9));
//		}
//    }
//    
//    @Test
//    public void getQtyModule() {
//    	
//    	ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult = new PermitArrayEntityResultSecond();
//    	Inverters inverterInfo = new Inverters();
//    	PermitConduitConductorSectionEntity circuit = new PermitConduitConductorSectionEntity();
//    	Inverters microInverterInfo= new Inverters();
//    	
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(0).add(permitArraysEntityResult);
//    	scenario.get(0).add(inverterInfo);
//    	scenario.get(0).add(circuit);
//    	scenario.get(0).add(microInverterInfo);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult1 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult1.setDeviceToIncorporate("System Optimizer");
//    	permitArraysEntityResult1.setNumberModulesACCircuitOne("8");
//    	permitArraysEntityResult1.setNumberModulesACCircuitTwo("12");
//    	Inverters inverterInfo1 = new Inverters();
//    	inverterInfo1.setIntegratedACDisco(true);
//    	PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//    	circuit1.setQtySegmentSix(2);
//    	Inverters microInverterInfo1= new Inverters();
//    	microInverterInfo1.setModPerMicro(2);
//    	
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(1).add(permitArraysEntityResult1);
//    	scenario.get(1).add(inverterInfo1);
//    	scenario.get(1).add(circuit1);
//    	scenario.get(1).add(microInverterInfo1);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult2.setDeviceToIncorporate("Micro Inverter");
//    	permitArraysEntityResult2.setNumberModulesACCircuitOne("3");
//    	permitArraysEntityResult2.setNumberModulesACCircuitTwo("24");
//    	Inverters inverterInfo2 = new Inverters();
//    	inverterInfo2.setIntegratedACDisco(false);
//    	PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//    	circuit2.setQtySegmentSix(1);
//    	Inverters microInverterInfo2= new Inverters();
//    	microInverterInfo2.setModPerMicro(null);
//    	
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(2).add(permitArraysEntityResult2);
//    	scenario.get(2).add(inverterInfo2);
//    	scenario.get(2).add(circuit2);
//    	scenario.get(2).add(microInverterInfo2);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult3 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult3.setDeviceToIncorporate("Neither");
//    	permitArraysEntityResult3.setNumberModulesACCircuitOne("3");
//    	permitArraysEntityResult3.setNumberModulesACCircuitTwo("24");
//    	permitArraysEntityResult3.setNumberModulesACCircuitThree("");
//    	Inverters inverterInfo3 = new Inverters();
//    	inverterInfo3.setIntegratedACDisco(true);
//    	PermitConduitConductorSectionEntity circuit3 = new PermitConduitConductorSectionEntity();
//    	circuit3.setQtySegmentSix(0);
//    	Inverters microInverterInfo3= new Inverters();
//    	microInverterInfo3.setModPerMicro(0);
//    	
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(3).add(permitArraysEntityResult3);
//    	scenario.get(3).add(inverterInfo3);
//    	scenario.get(3).add(circuit3);
//    	scenario.get(3).add(microInverterInfo3);
//    	
//    	for (int i = 0; i < scenario.size(); i++) {
//
//			planSetService.getQtyModule((PermitArrayEntityResultSecond) scenario.get(i).get(0),(Inverters) scenario.get(i).get(1),(PermitConduitConductorSectionEntity) scenario.get(i).get(2),(Inverters) scenario.get(i).get(3));
//		}
//    			
//    }
//    
//    @Test
//    public void getInverterIndex() {
//    	
//    	ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//    	
//    	PermitConduitConductorSectionEntity circuit = new PermitConduitConductorSectionEntity();
//    	DCCombinerDisconnectRequest dcCombinerDisconnect  = new DCCombinerDisconnectRequest();
//    	
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(0).add(circuit);
//    	scenario.get(0).add(dcCombinerDisconnect);
//    	
//    	PermitConduitConductorSectionEntity circuit1 = new PermitConduitConductorSectionEntity();
//    	circuit1.setQtySegmentTwo(6);
//    	circuit1.setQtySegmentThree(1);
//    	circuit1.setQtySegmentFour(2);
//    	DCCombinerDisconnectRequest dcCombinerDisconnect1  = new DCCombinerDisconnectRequest();
//    	dcCombinerDisconnect1.setTypeDc("Rapid Shutdown");
//    	
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(1).add(circuit1);
//    	scenario.get(1).add(dcCombinerDisconnect1);
//    	
//    	PermitConduitConductorSectionEntity circuit2 = new PermitConduitConductorSectionEntity();
//    	circuit2.setQtySegmentTwo(0);
//    	circuit2.setQtySegmentThree(3);
//    	circuit2.setQtySegmentFour(2);
//    	circuit2.setQtySegmentFive(1);
//    	DCCombinerDisconnectRequest dcCombinerDisconnect2  = new DCCombinerDisconnectRequest();
//    	dcCombinerDisconnect2.setTypeDc("DC Combiner");
//    	
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(2).add(circuit2);
//    	scenario.get(2).add(dcCombinerDisconnect2);
//    	
//    	for (int i = 0; i < scenario.size(); i++) {
//
//			planSetService.getInverterIndex((PermitConduitConductorSectionEntity) scenario.get(i).get(0),(DCCombinerDisconnectRequest) scenario.get(i).get(1),null);
//		}
//    }
//    public void getModuleNumberString() {
//    	
//    	List<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//    	scenario.add(null);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult = new PermitArrayEntityResultSecond();
//    	scenario.add(permitArraysEntityResult);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//    	scenario.add(permitArraysEntityResult2);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult3 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult3.setInverterModel("inverterModel");
//    	scenario.add(permitArraysEntityResult3);
//    	
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult4 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult4.setInverterModel("inverterModel");
//    	permitArraysEntityResult4.setSecondInverterModel("inverterModel");
//    	permitArraysEntityResult4.setThirdInverterModel("inverterModel");
//    	permitArraysEntityResult4.setFourthInverterModel("inverterModel");
//    	permitArraysEntityResult4.setFifthInverterModel("inverterModel");
//    	permitArraysEntityResult4.setStringOne("");
//    	permitArraysEntityResult4.setSecondStringOne("");
//    	scenario.add(permitArraysEntityResult4); 
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult5 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult5.setInverterModel("inverterModel");
//    	permitArraysEntityResult5.setSecondInverterModel("inverterModel");
//    	permitArraysEntityResult5.setThirdInverterModel("inverterModel");
//    	permitArraysEntityResult5.setFourthInverterModel("inverterModel");
//    	permitArraysEntityResult5.setFifthInverterModel("inverterModel");
//    	permitArraysEntityResult5.setStringOne("15A");
//    	permitArraysEntityResult5.setSecondStringOne("15A");
//    	scenario.add(permitArraysEntityResult5);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult6 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult6.setInverterModel("inverterModel");
//    	permitArraysEntityResult6.setSecondInverterModel("inverterModel");
//    	permitArraysEntityResult6.setThirdInverterModel("inverterModel");
//    	permitArraysEntityResult6.setFourthInverterModel("inverterModel");
//    	permitArraysEntityResult6.setFifthInverterModel("inverterModel");
//    	permitArraysEntityResult6.setStringOne("1");
//    	permitArraysEntityResult6.setSecondStringOne("1");
//    	scenario.add(permitArraysEntityResult6);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult7 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult7.setInverterModel("inverterModel");
//    	permitArraysEntityResult7.setSecondInverterModel("inverterModel");
//    	permitArraysEntityResult7.setThirdInverterModel("inverterModel");
//    	permitArraysEntityResult7.setFourthInverterModel("inverterModel");
//    	permitArraysEntityResult7.setFifthInverterModel("inverterModel");
//    	permitArraysEntityResult7.setStringOne("1");
//    	permitArraysEntityResult7.setStringTwo("");
//    	
//    	permitArraysEntityResult7.setSecondStringOne("1");
//    	permitArraysEntityResult7.setSecondStringTwo("");
//    	scenario.add(permitArraysEntityResult7);
//    	
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult8= new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult8.setInverterModel("inverterModel");
//    	permitArraysEntityResult8.setSecondInverterModel("inverterModel");
//    	permitArraysEntityResult8.setThirdInverterModel("inverterModel");
//    	permitArraysEntityResult8.setFourthInverterModel("inverterModel");
//    	permitArraysEntityResult8.setFifthInverterModel("inverterModel");
//    	permitArraysEntityResult8.setStringOne("1");
//    	permitArraysEntityResult8.setStringTwo("15A");
//    	
//    	permitArraysEntityResult8.setSecondStringOne("1");
//    	permitArraysEntityResult8.setSecondStringTwo("15A");
//    	
//    	permitArraysEntityResult8.setThirdStringOne(1);
//    	permitArraysEntityResult8.setFourthStringOne(1);
//    	permitArraysEntityResult8.setFifthStringOne(1);
//    	scenario.add(permitArraysEntityResult8);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult9 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult9.setInverterModel("inverterModel");
//    	permitArraysEntityResult9.setSecondInverterModel("inverterModel");
//    	permitArraysEntityResult9.setThirdInverterModel("inverterModel");
//    	permitArraysEntityResult9.setFourthInverterModel("inverterModel");
//    	permitArraysEntityResult9.setFifthInverterModel("inverterModel");
//    	permitArraysEntityResult9.setStringOne("1");
//    	permitArraysEntityResult9.setStringTwo("1");
//    	permitArraysEntityResult9.setSecondStringOne("1");
//    	permitArraysEntityResult9.setSecondStringTwo("1");
//    	permitArraysEntityResult9.setThirdStringOne(1);
//    	permitArraysEntityResult9.setThirdStringTwo(1);
//    	permitArraysEntityResult9.setFourthStringOne(1);
//    	permitArraysEntityResult9.setFourthStringTwo(1);
//    	permitArraysEntityResult9.setFifthStringOne(1);
//    	permitArraysEntityResult9.setFifthStringTwo(1);
//    	scenario.add(permitArraysEntityResult9);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult10 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult10.setInverterModel("inverterModel");
//    	permitArraysEntityResult10.setSecondInverterModel("inverterModel");
//    	permitArraysEntityResult10.setThirdInverterModel("inverterModel");
//    	permitArraysEntityResult10.setFourthInverterModel("inverterModel");
//    	permitArraysEntityResult10.setFifthInverterModel("inverterModel");
//    	permitArraysEntityResult10.setStringOne("1");
//    	permitArraysEntityResult10.setStringTwo("1");
//    	permitArraysEntityResult10.setStringThree("");
//    	permitArraysEntityResult10.setSecondStringOne("1");
//    	permitArraysEntityResult10.setSecondStringTwo("1");
//    	permitArraysEntityResult10.setSecondStringThree("");
//    	scenario.add(permitArraysEntityResult10);
//    	
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult11 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult11.setInverterModel("inverterModel");
//    	permitArraysEntityResult11.setSecondInverterModel("inverterModel");
//    	permitArraysEntityResult11.setThirdInverterModel("inverterModel");
//    	permitArraysEntityResult11.setFourthInverterModel("inverterModel");
//    	permitArraysEntityResult11.setFifthInverterModel("inverterModel");
//    	permitArraysEntityResult11.setStringOne("1");
//    	permitArraysEntityResult11.setStringTwo("1");
//    	permitArraysEntityResult11.setStringThree("15A");
//    	permitArraysEntityResult11.setSecondStringOne("1");
//    	permitArraysEntityResult11.setSecondStringTwo("1");
//    	permitArraysEntityResult11.setSecondStringThree("15A");
//    	scenario.add(permitArraysEntityResult11);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult12 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult12.setInverterModel("inverterModel");
//    	permitArraysEntityResult12.setSecondInverterModel("inverterModel");
//    	permitArraysEntityResult12.setThirdInverterModel("inverterModel");
//    	permitArraysEntityResult12.setFourthInverterModel("inverterModel");
//    	permitArraysEntityResult12.setFifthInverterModel("inverterModel");
//    	permitArraysEntityResult12.setStringOne("1");
//    	permitArraysEntityResult12.setStringTwo("1");
//    	permitArraysEntityResult12.setStringThree("1");
//    	permitArraysEntityResult12.setSecondStringOne("1");
//    	permitArraysEntityResult12.setSecondStringTwo("1");
//    	permitArraysEntityResult12.setSecondStringThree("1");
//    	permitArraysEntityResult12.setThirdStringOne(1);
//    	permitArraysEntityResult12.setThirdStringTwo(1);
//    	permitArraysEntityResult12.setThirdStringThree(1);
//    	permitArraysEntityResult12.setFourthStringOne(1);
//    	permitArraysEntityResult12.setFourthStringTwo(1);
//    	permitArraysEntityResult12.setFourthStringThree(1);
//    	permitArraysEntityResult12.setFifthStringOne(1);
//    	permitArraysEntityResult12.setFifthStringTwo(1);
//    	permitArraysEntityResult12.setFifthStringThree(1);
//    	scenario.add(permitArraysEntityResult12);
//    	
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult13 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult13.setInverterModel("inverterModel");
//    	permitArraysEntityResult13.setSecondInverterModel("inverterModel");
//    	permitArraysEntityResult13.setThirdInverterModel("inverterModel");
//    	permitArraysEntityResult13.setFourthInverterModel("inverterModel");
//    	permitArraysEntityResult13.setFifthInverterModel("inverterModel");
//    	permitArraysEntityResult13.setStringOne("1");
//    	permitArraysEntityResult13.setStringTwo("1");
//    	permitArraysEntityResult13.setStringThree("1");
//    	permitArraysEntityResult13.setStringFour("");
//    	
//    	permitArraysEntityResult13.setSecondStringOne("1");
//    	permitArraysEntityResult13.setSecondStringTwo("1");
//    	permitArraysEntityResult13.setSecondStringThree("1");
//    	permitArraysEntityResult13.setSecondStringFour("");
//    	scenario.add(permitArraysEntityResult13);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult14 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult14.setInverterModel("inverterModel");
//    	permitArraysEntityResult14.setSecondInverterModel("inverterModel");
//    	permitArraysEntityResult14.setThirdInverterModel("inverterModel");
//    	permitArraysEntityResult14.setFourthInverterModel("inverterModel");
//    	permitArraysEntityResult14.setFifthInverterModel("inverterModel");
//    	permitArraysEntityResult14.setStringOne("1");
//    	permitArraysEntityResult14.setStringTwo("1");
//    	permitArraysEntityResult14.setStringThree("1");
//    	permitArraysEntityResult14.setStringFour("15A");
//    	
//    	permitArraysEntityResult14.setSecondStringOne("1");
//    	permitArraysEntityResult14.setSecondStringTwo("1");
//    	permitArraysEntityResult14.setSecondStringThree("1");
//    	permitArraysEntityResult14.setSecondStringFour("15A");
//    	
//    	permitArraysEntityResult14.setThirdStringOne(1);
//    	permitArraysEntityResult14.setThirdStringTwo(1);
//    	permitArraysEntityResult14.setThirdStringThree(1);
//    	
//    	permitArraysEntityResult14.setFourthStringOne(1);
//    	permitArraysEntityResult14.setFourthStringTwo(1);
//    	permitArraysEntityResult14.setFourthStringThree(1);
//    	
//    	permitArraysEntityResult14.setFifthStringOne(1);
//    	permitArraysEntityResult14.setFifthStringTwo(1);
//    	permitArraysEntityResult14.setFifthStringThree(1);
//    	scenario.add(permitArraysEntityResult14);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult15 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult15.setInverterModel("inverterModel");
//    	permitArraysEntityResult15.setSecondInverterModel("inverterModel");
//    	permitArraysEntityResult15.setThirdInverterModel("inverterModel");
//    	permitArraysEntityResult15.setFourthInverterModel("inverterModel");
//    	permitArraysEntityResult15.setFifthInverterModel("inverterModel");
//    	permitArraysEntityResult15.setStringOne("1");
//    	permitArraysEntityResult15.setStringTwo("1");
//    	permitArraysEntityResult15.setStringThree("1");
//    	permitArraysEntityResult15.setStringFour("1");
//    	
//    	permitArraysEntityResult15.setSecondStringOne("1");
//    	permitArraysEntityResult15.setSecondStringTwo("1");
//    	permitArraysEntityResult15.setSecondStringThree("1");
//    	permitArraysEntityResult15.setSecondStringFour("1");
//    	
//    	permitArraysEntityResult15.setThirdStringOne(1);
//    	permitArraysEntityResult15.setThirdStringTwo(1);
//    	permitArraysEntityResult15.setThirdStringThree(1);
//    	permitArraysEntityResult15.setThirdStringFour(1);
//    	
//    	permitArraysEntityResult15.setFourthStringOne(1);
//    	permitArraysEntityResult15.setFourthStringTwo(1);
//    	permitArraysEntityResult15.setFourthStringThree(1);
//    	permitArraysEntityResult15.setFourthStringFour(1);
//    	
//    	permitArraysEntityResult15.setFifthStringOne(1);
//    	permitArraysEntityResult15.setFifthStringTwo(1);
//    	permitArraysEntityResult15.setFifthStringThree(1);
//    	permitArraysEntityResult15.setFifthStringFour(1);
//    	scenario.add(permitArraysEntityResult15);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult16 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult16.setInverterModel("inverterModel");
//    	permitArraysEntityResult16.setSecondInverterModel("inverterModel");
//    	permitArraysEntityResult16.setThirdInverterModel("inverterModel");
//    	permitArraysEntityResult16.setFourthInverterModel("inverterModel");
//    	permitArraysEntityResult16.setFifthInverterModel("inverterModel");
//    	permitArraysEntityResult16.setStringOne("1");
//    	permitArraysEntityResult16.setStringTwo("1");
//    	permitArraysEntityResult16.setStringThree("1");
//    	permitArraysEntityResult16.setStringFour("1");
//    	permitArraysEntityResult16.setStringFive("");
//    	
//    	permitArraysEntityResult16.setSecondStringOne("1");
//    	permitArraysEntityResult16.setSecondStringTwo("1");
//    	permitArraysEntityResult16.setSecondStringThree("1");
//    	permitArraysEntityResult16.setSecondStringFour("1");
//    	permitArraysEntityResult16.setSecondStringFive("");
//    	
//    	permitArraysEntityResult16.setThirdStringOne(1);
//    	permitArraysEntityResult16.setThirdStringTwo(1);
//    	permitArraysEntityResult16.setThirdStringThree(1);
//    	permitArraysEntityResult16.setThirdStringFour(1);
//    	
//    	permitArraysEntityResult16.setFourthStringOne(1);
//    	permitArraysEntityResult16.setFourthStringTwo(1);
//    	permitArraysEntityResult16.setFourthStringThree(1);
//    	permitArraysEntityResult16.setFourthStringFour(1);
//    	
//    	permitArraysEntityResult16.setFifthStringOne(1);
//    	permitArraysEntityResult16.setFifthStringTwo(1);
//    	permitArraysEntityResult16.setFifthStringThree(1);
//    	permitArraysEntityResult16.setFifthStringFour(1);
//    	scenario.add(permitArraysEntityResult16);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult17 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult17.setInverterModel("inverterModel");
//    	permitArraysEntityResult17.setSecondInverterModel("inverterModel");
//    	permitArraysEntityResult17.setThirdInverterModel("inverterModel");
//    	permitArraysEntityResult17.setFourthInverterModel("inverterModel");
//    	permitArraysEntityResult17.setFifthInverterModel("inverterModel");
//    	permitArraysEntityResult17.setStringOne("1");
//    	permitArraysEntityResult17.setStringTwo("1");
//    	permitArraysEntityResult17.setStringThree("1");
//    	permitArraysEntityResult17.setStringFour("1");
//    	permitArraysEntityResult17.setStringFive("15A");
//    	
//    	permitArraysEntityResult17.setSecondStringOne("1");
//    	permitArraysEntityResult17.setSecondStringTwo("1");
//    	permitArraysEntityResult17.setSecondStringThree("1");
//    	permitArraysEntityResult17.setSecondStringFour("1");
//    	permitArraysEntityResult17.setSecondStringFive("15A");
//    	
//    	permitArraysEntityResult17.setThirdStringOne(1);
//    	permitArraysEntityResult17.setThirdStringTwo(1);
//    	permitArraysEntityResult17.setThirdStringThree(1);
//    	permitArraysEntityResult17.setThirdStringFour(1);
//    	
//    	permitArraysEntityResult17.setFourthStringOne(1);
//    	permitArraysEntityResult17.setFourthStringTwo(1);
//    	permitArraysEntityResult17.setFourthStringThree(1);
//    	permitArraysEntityResult17.setFourthStringFour(1);
//    	
//    	
//    	
//    	permitArraysEntityResult17.setFifthStringOne(1);
//    	permitArraysEntityResult17.setFifthStringTwo(1);
//    	permitArraysEntityResult17.setFifthStringThree(1);
//    	permitArraysEntityResult17.setFifthStringFour(1);
//    	scenario.add(permitArraysEntityResult17);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult18 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult18.setInverterModel("inverterModel");
//    	permitArraysEntityResult18.setSecondInverterModel("inverterModel");
//    	permitArraysEntityResult18.setThirdInverterModel("inverterModel");
//    	permitArraysEntityResult18.setFourthInverterModel("inverterModel");
//    	permitArraysEntityResult18.setFifthInverterModel("inverterModel");
//    	permitArraysEntityResult18.setStringOne("1");
//    	permitArraysEntityResult18.setStringTwo("1");
//    	permitArraysEntityResult18.setStringThree("1");
//    	permitArraysEntityResult18.setStringFour("1");
//    	permitArraysEntityResult18.setStringFive("1");
//    	
//    	permitArraysEntityResult18.setSecondStringOne("1");
//    	permitArraysEntityResult18.setSecondStringTwo("1");
//    	permitArraysEntityResult18.setSecondStringThree("1");
//    	permitArraysEntityResult18.setSecondStringFour("1");
//    	permitArraysEntityResult18.setSecondStringFive("1");
//    	
//    	permitArraysEntityResult18.setThirdStringOne(1);
//    	permitArraysEntityResult18.setThirdStringTwo(1);
//    	permitArraysEntityResult18.setThirdStringThree(1);
//    	permitArraysEntityResult18.setThirdStringFour(1);
//    	permitArraysEntityResult18.setThirdStringFive(1);
//    	
//    	permitArraysEntityResult18.setFourthStringOne(1);
//    	permitArraysEntityResult18.setFourthStringTwo(1);
//    	permitArraysEntityResult18.setFourthStringThree(1);
//    	permitArraysEntityResult18.setFourthStringFour(1);
//    	permitArraysEntityResult18.setFourthStringFive(1);
//    	
//    	permitArraysEntityResult18.setFifthStringOne(1);
//    	permitArraysEntityResult18.setFifthStringTwo(1);
//    	permitArraysEntityResult18.setFifthStringThree(1);
//    	permitArraysEntityResult18.setFifthStringFour(1);
//    	permitArraysEntityResult18.setFifthStringFive(1);
//    	
//    	scenario.add(permitArraysEntityResult18);
//    	
//    	
//    	
//    	for (int i = 0; i < scenario.size(); i++) {
//    		planSetService.getModuleNumberString(scenario.get(i));
//    	}
//    }
//    
//    @Test
//    public void getModuleNumberMicro() {
//    	
//    	List<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//    	scenario.add(null);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult = new PermitArrayEntityResultSecond();
//    	scenario.add(permitArraysEntityResult);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult3 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult3.setNumberModulesACCircuitOne("");
//    	scenario.add(permitArraysEntityResult3);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult4 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult4.setNumberModulesACCircuitOne("15A");
//    	scenario.add(permitArraysEntityResult4);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult5 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult5.setNumberModulesACCircuitOne("1");
//    	scenario.add(permitArraysEntityResult5);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult6 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult6.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult6.setNumberModulesACCircuitTwo("");
//    	scenario.add(permitArraysEntityResult6);
//    	
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult7 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult7.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult7.setNumberModulesACCircuitTwo("15A");
//    	scenario.add(permitArraysEntityResult7);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult8 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult8.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult8.setNumberModulesACCircuitTwo("1");
//    	scenario.add(permitArraysEntityResult8);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult9 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult9.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult9.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult9.setNumberModulesACCircuitThree("");
//    	scenario.add(permitArraysEntityResult9);
//    	
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult10 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult10.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult10.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult10.setNumberModulesACCircuitThree("15A");
//    	scenario.add(permitArraysEntityResult10);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult11 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult11.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult11.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult11.setNumberModulesACCircuitThree("1");
//    	scenario.add(permitArraysEntityResult11);
//    	
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult12 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult12.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult12.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult12.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult12.setNumberModulesACCircuitFour("");
//    	scenario.add(permitArraysEntityResult12);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult13 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult13.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult13.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult13.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult13.setNumberModulesACCircuitFour("15A");
//    	scenario.add(permitArraysEntityResult13);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult14 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult14.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult14.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult14.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult14.setNumberModulesACCircuitFour("1");
//    	scenario.add(permitArraysEntityResult14);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult15 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult15.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult15.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult15.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult15.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult15.setNumberModulesACCircuitFive("");
//    	scenario.add(permitArraysEntityResult15);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult16 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult16.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult16.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult16.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult16.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult16.setNumberModulesACCircuitFive("15A");
//    	scenario.add(permitArraysEntityResult16);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult17 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult17.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult17.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult17.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult17.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult17.setNumberModulesACCircuitFive("1");
//    	scenario.add(permitArraysEntityResult17);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult18 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult18.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult18.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult18.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult18.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult18.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult18.setNumberModulesACCircuitSix("");
//    	scenario.add(permitArraysEntityResult18);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult19 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult19.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult19.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult19.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult19.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult19.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult19.setNumberModulesACCircuitSix("15A");
//    	scenario.add(permitArraysEntityResult19);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult20 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult20.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult20.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult20.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult20.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult20.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult20.setNumberModulesACCircuitSix("1");
//    	scenario.add(permitArraysEntityResult20);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult21 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult21.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult21.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult21.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult21.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult21.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult21.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult21.setNumberModulesACCircuitSeven("");
//    	scenario.add(permitArraysEntityResult21);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult22 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult22.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult22.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult22.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult22.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult22.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult22.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult22.setNumberModulesACCircuitSeven("15A");
//    	scenario.add(permitArraysEntityResult22);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult23 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult23.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult23.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult23.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult23.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult23.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult23.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult23.setNumberModulesACCircuitSeven("1");
//    	scenario.add(permitArraysEntityResult23);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult24 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult24.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult24.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult24.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult24.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult24.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult24.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult24.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult24.setNumberModulesACCircuitEight("");
//    	scenario.add(permitArraysEntityResult24);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult25 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult25.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult25.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult25.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult25.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult25.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult25.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult25.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult25.setNumberModulesACCircuitEight("15A");
//    	scenario.add(permitArraysEntityResult25);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult27 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult27.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult27.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult27.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult27.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult27.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult27.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult27.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult27.setNumberModulesACCircuitEight("1");
//    	scenario.add(permitArraysEntityResult27);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult28 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult28.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult28.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult28.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult28.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult28.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult28.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult28.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult28.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult28.setNumberModulesACCircuitNine("");
//    	scenario.add(permitArraysEntityResult28);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult29 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult29.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult29.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult29.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult29.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult29.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult29.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult29.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult29.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult29.setNumberModulesACCircuitNine("15A");
//    	scenario.add(permitArraysEntityResult29);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult30 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult30.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult30.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult30.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult30.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult30.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult30.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult30.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult30.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult30.setNumberModulesACCircuitNine("1");
//    	scenario.add(permitArraysEntityResult30);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult31 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult31.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult31.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult31.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult31.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult31.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult31.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult31.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult31.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult31.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult31.setNumberModulesACCircuitTen("");
//    	scenario.add(permitArraysEntityResult31);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult32 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult32.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult32.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult32.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult32.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult32.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult32.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult32.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult32.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult32.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult32.setNumberModulesACCircuitTen("15A");
//    	scenario.add(permitArraysEntityResult32);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult33 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult33.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult33.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult33.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult33.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult33.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult33.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult33.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult33.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult33.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult33.setNumberModulesACCircuitTen("1");
//    	scenario.add(permitArraysEntityResult33);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult34 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult34.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult34.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult34.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult34.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult34.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult34.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult34.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult34.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult34.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult34.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult34.setNumberModulesACCircuitEleven("");
//    	scenario.add(permitArraysEntityResult34);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult35 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult35.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult35.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult35.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult35.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult35.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult35.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult35.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult35.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult35.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult35.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult35.setNumberModulesACCircuitEleven("15A");
//    	scenario.add(permitArraysEntityResult35);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult36 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult36.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult36.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult36.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult36.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult36.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult36.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult36.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult36.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult36.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult36.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult36.setNumberModulesACCircuitEleven("1");
//    	scenario.add(permitArraysEntityResult36);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult37 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult37.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult37.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult37.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult37.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult37.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult37.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult37.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult37.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult37.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult37.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult37.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult37.setNumberModulesACCircuitTweleve("");
//    	scenario.add(permitArraysEntityResult37);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult38 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult38.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult38.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult38.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult38.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult38.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult38.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult38.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult38.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult38.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult38.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult38.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult38.setNumberModulesACCircuitTweleve("15A");
//    	scenario.add(permitArraysEntityResult38);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult39 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult39.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult39.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult39.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult39.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult39.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult39.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult39.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult39.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult39.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult39.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult39.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult39.setNumberModulesACCircuitTweleve("1");
//    	scenario.add(permitArraysEntityResult39);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult40 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult40.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult40.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult40.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult40.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult40.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult40.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult40.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult40.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult40.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult40.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult40.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult40.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult40.setNumberModulesACCircuitThirteen("");
//    	scenario.add(permitArraysEntityResult40);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult41 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult41.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult41.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult41.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult41.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult41.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult41.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult41.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult41.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult41.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult41.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult41.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult41.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult41.setNumberModulesACCircuitThirteen("15A");
//    	scenario.add(permitArraysEntityResult41);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult42 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult42.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult42.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult42.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult42.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult42.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult42.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult42.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult42.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult42.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult42.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult42.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult42.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult42.setNumberModulesACCircuitThirteen("1");
//    	scenario.add(permitArraysEntityResult42);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult43 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult43.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult43.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult43.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult43.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult43.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult43.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult43.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult43.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult43.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult43.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult43.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult43.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult43.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult43.setNumberModulesACCircuitFourteen("");
//    	scenario.add(permitArraysEntityResult43);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult44 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult44.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult44.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult44.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult44.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult44.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult44.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult44.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult44.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult44.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult44.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult44.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult44.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult44.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult44.setNumberModulesACCircuitFourteen("15A");
//    	scenario.add(permitArraysEntityResult44);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult45 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult45.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult45.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult45.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult45.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult45.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult45.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult45.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult45.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult45.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult45.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult45.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult45.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult45.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult45.setNumberModulesACCircuitFourteen("1");
//    	scenario.add(permitArraysEntityResult45);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult46 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult46.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult46.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult46.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult46.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult46.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult46.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult46.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult46.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult46.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult46.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult46.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult46.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult46.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult46.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult46.setNumberModulesACCircuitFifteen("");
//    	scenario.add(permitArraysEntityResult46);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult47 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult47.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult47.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult47.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult47.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult47.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult47.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult47.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult47.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult47.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult47.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult47.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult47.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult47.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult47.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult47.setNumberModulesACCircuitFifteen("15A");
//    	scenario.add(permitArraysEntityResult47);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult48 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult48.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult48.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult48.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult48.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult48.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult48.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult48.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult48.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult48.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult48.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult48.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult48.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult48.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult48.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult48.setNumberModulesACCircuitFifteen("1");
//    	scenario.add(permitArraysEntityResult48);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult49 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult49.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult49.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult49.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult49.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult49.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult49.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult49.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult49.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult49.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult49.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult49.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult49.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult49.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult49.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult49.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult49.setNumberModulesACCircuitSixteen("");
//    	scenario.add(permitArraysEntityResult49);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult50= new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult50.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult50.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult50.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult50.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult50.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult50.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult50.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult50.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult50.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult50.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult50.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult50.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult50.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult50.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult50.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult50.setNumberModulesACCircuitSixteen("15A");
//    	scenario.add(permitArraysEntityResult50);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult51 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult51.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult51.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult51.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult51.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult51.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult51.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult51.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult51.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult51.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult51.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult51.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult51.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult51.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult51.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult51.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult51.setNumberModulesACCircuitSixteen("1");
//    	scenario.add(permitArraysEntityResult51);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult52 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult52.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult52.setNumberModulesACCircuitSeventeen("");
//    	scenario.add(permitArraysEntityResult52);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult53 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult53.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult53.setNumberModulesACCircuitSeventeen("15A");
//    	scenario.add(permitArraysEntityResult53);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult54 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult54.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult54.setNumberModulesACCircuitSeventeen("1");
//    	scenario.add(permitArraysEntityResult54);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult55 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult55.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult55.setNumberModulesACCircuitEightteen("");
//    	scenario.add(permitArraysEntityResult55);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult56 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult56.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult56.setNumberModulesACCircuitEightteen("15A");
//    	scenario.add(permitArraysEntityResult56);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult57 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult57.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult57.setNumberModulesACCircuitEightteen("1");
//    	scenario.add(permitArraysEntityResult57);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult58 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult58.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult58.setNumberModulesACCircuitNineteen("");
//    	scenario.add(permitArraysEntityResult58);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult59 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult59.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult59.setNumberModulesACCircuitNineteen("15A");
//    	scenario.add(permitArraysEntityResult59);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult60 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult60.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult60.setNumberModulesACCircuitNineteen("1");
//    	scenario.add(permitArraysEntityResult60);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult61 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult61.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitNineteen("1");
//    	permitArraysEntityResult61.setNumberModulesACCircuitTwenty("");
//    	scenario.add(permitArraysEntityResult61);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult62 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult62.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitNineteen("1");
//    	permitArraysEntityResult62.setNumberModulesACCircuitTwenty("15A");
//    	scenario.add(permitArraysEntityResult62);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult63 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult63.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitNineteen("1");
//    	permitArraysEntityResult63.setNumberModulesACCircuitTwenty("1");
//    	scenario.add(permitArraysEntityResult63);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult64 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult64.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitNineteen("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitTwenty("1");
//    	permitArraysEntityResult64.setNumberModulesACCircuitTwentyOne("");
//    	scenario.add(permitArraysEntityResult64);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult65 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult65.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitNineteen("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitTwenty("1");
//    	permitArraysEntityResult65.setNumberModulesACCircuitTwentyOne("15A");
//    	scenario.add(permitArraysEntityResult65);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult66 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult66.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitNineteen("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitTwenty("1");
//    	permitArraysEntityResult66.setNumberModulesACCircuitTwentyOne("1");
//    	scenario.add(permitArraysEntityResult66);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult67= new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult67.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitNineteen("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitTwenty("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitTwentyOne("1");
//    	permitArraysEntityResult67.setNumberModulesACCircuitTwentyTwo("");
//    	scenario.add(permitArraysEntityResult67);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult68 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult68.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitNineteen("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitTwenty("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitTwentyOne("1");
//    	permitArraysEntityResult68.setNumberModulesACCircuitTwentyTwo("15A");
//    	scenario.add(permitArraysEntityResult68);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult69 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult69.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitNineteen("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitTwenty("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitTwentyOne("1");
//    	permitArraysEntityResult69.setNumberModulesACCircuitTwentyTwo("1");
//    	scenario.add(permitArraysEntityResult69);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult70 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult70.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitNineteen("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitTwenty("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitTwentyOne("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitTwentyTwo("1");
//    	permitArraysEntityResult70.setNumberModulesACCircuitTwentyThree("");
//    	scenario.add(permitArraysEntityResult70);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult71 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult71.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitNineteen("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitTwenty("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitTwentyOne("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitTwentyTwo("1");
//    	permitArraysEntityResult71.setNumberModulesACCircuitTwentyThree("15A");
//    	scenario.add(permitArraysEntityResult71);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult72 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult72.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitNineteen("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitTwenty("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitTwentyOne("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitTwentyTwo("1");
//    	permitArraysEntityResult72.setNumberModulesACCircuitTwentyThree("1");
//    	scenario.add(permitArraysEntityResult72);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult73 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult73.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitNineteen("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitTwenty("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitTwentyOne("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitTwentyTwo("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitTwentyThree("1");
//    	permitArraysEntityResult73.setNumberModulesACCircuitTwentyFour("");
//    	scenario.add(permitArraysEntityResult73);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult74 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult74.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitNineteen("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitTwenty("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitTwentyOne("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitTwentyTwo("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitTwentyThree("1");
//    	permitArraysEntityResult74.setNumberModulesACCircuitTwentyFour("15A");
//    	scenario.add(permitArraysEntityResult74);
//    	
//    	PermitArrayEntityResultSecond permitArraysEntityResult75 = new PermitArrayEntityResultSecond();
//    	permitArraysEntityResult75.setNumberModulesACCircuitOne("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitTwo("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitThree("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitFour("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitFive("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitSix("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitSeven("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitEight("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitNine("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitTen("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitEleven("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitTweleve("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitThirteen("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitFourteen("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitFifteen("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitSixteen("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitSeventeen("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitEightteen("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitNineteen("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitTwenty("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitTwentyOne("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitTwentyTwo("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitTwentyThree("1");
//    	permitArraysEntityResult75.setNumberModulesACCircuitTwentyFour("1");
//    	scenario.add(permitArraysEntityResult75);
//    	
//    	for (int i = 0; i < scenario.size(); i++) {
//    		planSetService.getModuleNumberMicro(scenario.get(i),null);
//    	}
//    	}
//    
//   
//    
//    @Test
//    public void poleRsheetRailRacking() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u " + " from RsheetsLibraryEntity u " + " where (u.pdfName = :p1 OR u.pdfName = :p2) AND u.isDeleted = :p3 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
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
//		// Result of the query1
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		RailRacking ra = new RailRacking();
//		ra.setManufacturer("test");
//		ra.setModel("test junit");
//		scenario.get(1).add(ra);
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
//		// Result of the query1
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add(ra);
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
//		// Result of the query1
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(2).add(list);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(3).add(ra);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(1);
//		// Result of the query1
//		scenario.get(3).add(list);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(4).add(ra);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(new PermitEntity());
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(new AuthentificationEntity());
//		scenario.get(4).add(null);
//		scenario.get(4).add(1);
//		// Result of the query1
//		scenario.get(4).add(list);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("poleRsheetRailRacking [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(11));
//			planSetService.poleRsheetRailRacking((RailRacking) scenario.get(i).get(0),(PermitHomeSiteEntityResult) scenario.get(i).get(1),(PermitAdditionalInfoEntityResult) scenario.get(i).get(2),(String) scenario.get(i).get(3),(PermitEntity) scenario.get(i).get(4),(String) scenario.get(i).get(5),(AuthentificationEntityResult) scenario.get(i).get(6),(EditUserInformations) scenario.get(i).get(7),(AuthentificationEntity) scenario.get(i).get(8),(String) scenario.get(i).get(9));
//		}
//    }
//    
//    @Test
//    public void rSheetMicroInverter() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u " + " from RsheetsLibraryEntity u " + " where (u.pdfName = :p1 OR u.pdfName = :p2) AND u.isDeleted = :p3 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
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
//		// Result of the query1
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		Inverters ra = new Inverters();
//		ra.setMake("test");
//		ra.setModel("test junit");
//		scenario.get(1).add(ra);
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
//		// Result of the query1
//		scenario.get(1).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add(ra);
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
//		// Result of the query1
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(2).add(list);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(3).add(ra);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(1);
//		// Result of the query1
//		scenario.get(3).add(list);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(4).add(ra);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(new PermitEntity());
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(new AuthentificationEntity());
//		scenario.get(4).add(null);
//		scenario.get(4).add(1);
//		// Result of the query1
//		scenario.get(4).add(list);
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("rSheetMicroInverter [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(11));
//			planSetService.rSheetMicroInverter((Inverters) scenario.get(i).get(0),(PermitHomeSiteEntityResult) scenario.get(i).get(1),(PermitAdditionalInfoEntityResult) scenario.get(i).get(2),(String) scenario.get(i).get(3),(PermitEntity) scenario.get(i).get(4),(String) scenario.get(i).get(5),(AuthentificationEntityResult) scenario.get(i).get(6),(EditUserInformations) scenario.get(i).get(7),(AuthentificationEntity) scenario.get(i).get(8),(String) scenario.get(i).get(9));
//		}
//    }
//    
//    @Test
//    public void stanchionRsheet() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u " + " from RsheetsLibraryEntity u " + " where (u.pdfName = :p1 OR u.pdfName = :p2) AND u.isDeleted = :p3 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
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
//		// Result of the query1
//		scenario.get(0).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		RoofAttachmentsEntity ra = new RoofAttachmentsEntity();
//		ra.setManufacturer("test");
//		ra.setModel("test junit");
//		scenario.get(1).add(ra);
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
//		// Result of the query1
//		scenario.get(1).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add(ra);
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
//		// Result of the query1
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(2).add(list);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(3).add(ra);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(1);
//		// Result of the query1
//		scenario.get(3).add(list);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(4).add(ra);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(new PermitEntity());
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(new AuthentificationEntity());
//		scenario.get(4).add(null);
//		scenario.get(4).add(1);
//		// Result of the query1
//		scenario.get(4).add(list);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("stanchionRsheet [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(11));
//			planSetService.stanchionRsheet((RoofAttachmentsEntity) scenario.get(i).get(0),(PermitHomeSiteEntityResult) scenario.get(i).get(1),(PermitAdditionalInfoEntityResult) scenario.get(i).get(2),(String) scenario.get(i).get(3),(PermitEntity) scenario.get(i).get(4),(String) scenario.get(i).get(5),(AuthentificationEntityResult) scenario.get(i).get(6),(EditUserInformations) scenario.get(i).get(7),(AuthentificationEntity) scenario.get(i).get(8),(String) scenario.get(i).get(9));
//		}
//    }
//    
//    @Test
//    public void dcCombinerDisconnectRsheet() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u " + " from RsheetsLibraryEntity u " + " where (u.pdfName = :p1 OR u.pdfName = :p2) AND u.isDeleted = :p3 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
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
//		// Result of the query1
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		PermitArrayEntityResultSecond per = new PermitArrayEntityResultSecond();
//		per.setDeviceToIncorporate("System Optimizer");
//		scenario.get(1).add(per);
//		PermitProjectSiteInfoEntityTwo par = new PermitProjectSiteInfoEntityTwo();
//		par.setDisconnectModel("test:");
//		scenario.get(1).add(par);
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
//		// Result of the query1
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add(per);
//		PermitProjectSiteInfoEntityTwo par1 = new PermitProjectSiteInfoEntityTwo();
//		par1.setDisconnectModel("test:junit");
//		scenario.get(2).add(par1);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(new AuthentificationEntity());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(2).add(list);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(3).add(per);
//		scenario.get(3).add(par1);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(new AuthentificationEntity());
//		scenario.get(3).add(null);
//		scenario.get(3).add(1);
//		// Result of the query1
//		scenario.get(3).add(list);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("dcCombinerDisconnectRsheet [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(12));
//			planSetService.dcCombinerDisconnectRsheet((PermitArrayEntityResultSecond) scenario.get(i).get(0),(PermitProjectSiteInfoEntityTwo) scenario.get(i).get(1),(PermitHomeSiteEntityResult) scenario.get(i).get(2),(PermitAdditionalInfoEntityResult) scenario.get(i).get(3),(String) scenario.get(i).get(4),(PermitEntity) scenario.get(i).get(5),(String) scenario.get(i).get(6),(AuthentificationEntityResult) scenario.get(i).get(7),(EditUserInformations) scenario.get(i).get(8),(AuthentificationEntity) scenario.get(i).get(9),(String) scenario.get(i).get(10));
//		}
//		
//    }
//    
//    @Test
//    public void acCombinerRsheet() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u " + " from RsheetsLibraryEntity u " + " where (u.pdfName = :p1 OR u.pdfName = :p2) AND u.isDeleted = :p3 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
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
//		// Result of the query1
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(1).add(new ACCombinerSLC());
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
//		// Result of the query1
//		scenario.get(1).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add(new ACCombinerSLC());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(1);
//		// Result of the query1
//		scenario.get(2).add(null);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("acCombinerRsheet [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(11));
//			planSetService.acCombinerRsheet((ACCombinerSLC) scenario.get(i).get(0),(PermitHomeSiteEntityResult) scenario.get(i).get(1),(PermitAdditionalInfoEntityResult) scenario.get(i).get(2),(String) scenario.get(i).get(3),(PermitEntity) scenario.get(i).get(4),(String) scenario.get(i).get(5),(AuthentificationEntityResult) scenario.get(i).get(6),(EditUserInformations) scenario.get(i).get(7),(AuthentificationEntity) scenario.get(i).get(8),(String) scenario.get(i).get(9));
//		}
//    }
//    
//    @Test
//    public void accdRsheets() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u " + " from RsheetsLibraryEntity u " + " where (u.pdfName = :p1 OR u.pdfName = :p2) AND u.isDeleted = :p3 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
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
//		// Result of the query1
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(1).add(new ACCombinerSLC());
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
//		// Result of the query1
//		scenario.get(1).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add(new ACCombinerSLC());
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
//		scenario.get(2).add(1);
//		// Result of the query1
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(3).add(new ACCombinerSLC());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(new PermitEntity());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(new AuthentificationEntity());
//		scenario.get(3).add(null);
//		scenario.get(3).add(1);
//		// Result of the query1
//		scenario.get(3).add(null);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("accdRsheets [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(12));
//			planSetService.accdRsheets((ACCombinerSLC) scenario.get(i).get(0),(ACCombinerSLC) scenario.get(i).get(1),(PermitHomeSiteEntityResult) scenario.get(i).get(2),(PermitAdditionalInfoEntityResult) scenario.get(i).get(3),(String) scenario.get(i).get(4),(PermitEntity) scenario.get(i).get(5),(String) scenario.get(i).get(6),(AuthentificationEntityResult) scenario.get(i).get(7),(EditUserInformations) scenario.get(i).get(8),(AuthentificationEntity) scenario.get(i).get(9),(String) scenario.get(i).get(10));
//		}
//    }
//    
//    
//    @Test
//    public void acDisconnectRsheets() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u " + " from RsheetsLibraryEntity u " + " where (u.pdfName = :p1 OR u.pdfName = :p2) AND u.isDeleted = :p3 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
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
//		// Result of the query1
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		PermitProjectSiteInfoEntityTwo per = new PermitProjectSiteInfoEntityTwo();
//		per.setUseDisconectSwith("true");
//		scenario.get(1).add(per);
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
//		// Result of the query1
//		scenario.get(1).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add(per);
//		scenario.get(2).add(new ACDisconnect());
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
//		// Result of the query1
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(3).add(per);
//		scenario.get(3).add(new ACDisconnect());
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
//		// Result of the query1
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(3).add(list);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(4).add(per);
//		ACDisconnect ac = new ACDisconnect();
//		ac.setManufacturer("junit");
//		ac.setModel("test");
//		ac.setId(1258);
//		scenario.get(4).add(ac);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(new PermitEntity());
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(new AuthentificationEntity());
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add(list);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(5).add(per);
//		scenario.get(5).add(ac);
//		ACDisconnect ac2 = new ACDisconnect();
//		ac2.setManufacturer("junit");
//		ac2.setModel("test");
//		ac2.setId(822585);
//		scenario.get(5).add(ac2);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(new PermitEntity());
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(new AuthentificationEntity());
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		// Result of the query1
//		scenario.get(5).add(list);
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("acDisconnectRsheets [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(13));
//			planSetService.acDisconnectRsheets((PermitProjectSiteInfoEntityTwo) scenario.get(i).get(0),(ACDisconnect) scenario.get(i).get(1),(ACDisconnect) scenario.get(i).get(2),(PermitHomeSiteEntityResult) scenario.get(i).get(3),(PermitAdditionalInfoEntityResult) scenario.get(i).get(4),(String) scenario.get(i).get(5),(PermitEntity) scenario.get(i).get(6),(String) scenario.get(i).get(7),(AuthentificationEntityResult) scenario.get(i).get(8),(EditUserInformations) scenario.get(i).get(9),(AuthentificationEntity) scenario.get(i).get(10),(String) scenario.get(i).get(11));
//		}
//    }
//    
//    @Test
//    public void jBoxRsheets() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u " + " from RsheetsLibraryEntity u " + " where (u.pdfName = :p1 OR u.pdfName = :p2) AND u.isDeleted = :p3 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
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
//		// Result of the query1
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		PermitProjectSiteInfoEntityTwo per = new PermitProjectSiteInfoEntityTwo();
//		per.setInstallingDCBo(true);
//		scenario.get(1).add(per);
//		DCCombinerDisconnectEntity dc = new DCCombinerDisconnectEntity();
//		dc.setManufacturer("test");
//		dc.setModel("junit");
//		scenario.get(1).add(dc);
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
//		// Result of the query1
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add(per);
//		scenario.get(2).add(dc);
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
//		// Result of the query1
//		ArrayList<RsheetsLibraryEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(2).add(list);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(3).add(per);
//		scenario.get(3).add(dc);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(1);
//		// Result of the query1
//		scenario.get(3).add(list);
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("jBoxRsheets [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(12));
//			planSetService.jBoxRsheets((PermitProjectSiteInfoEntityTwo) scenario.get(i).get(0),(DCCombinerDisconnectEntity) scenario.get(i).get(1),(PermitHomeSiteEntityResult) scenario.get(i).get(2),(PermitAdditionalInfoEntityResult) scenario.get(i).get(3),(String) scenario.get(i).get(4),(PermitEntity) scenario.get(i).get(5),(String) scenario.get(i).get(6),(AuthentificationEntityResult) scenario.get(i).get(7),(EditUserInformations) scenario.get(i).get(8),(AuthentificationEntity) scenario.get(i).get(9),(String) scenario.get(i).get(10));
//		}
//    }
//    
//    @Test
//    public void getComponentListQty() {
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		PermitConduitConductorSectionEntity per = new PermitConduitConductorSectionEntity();
//		per.setQtySegmentFour(10);
//		per.setQtySegmentFive(8);
//		per.setQtySegmentNine(3);
//		per.setQtySegmentSeven(5);
//		per.setQtySegmentEight(6);
//		per.setQtySegmentTen(25);
//		per.setQtySegmentTen(10);
//		scenario.get(1).add(per);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add(per);
//		DCCombinerDisconnectEntity dc = new DCCombinerDisconnectEntity();
//		dc.setTypeDc("Rapid Shutdown");
//		scenario.get(2).add(dc);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(3).add(per);
//		scenario.get(3).add(dc);
//		scenario.get(3).add(null);
//		ACDisconnect ac = new ACDisconnect();
//		ac.setDisconnectDeviceType("FUSIBLE DISCONNECT");
//		scenario.get(3).add(ac);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getComponentListQty [ " + i + " ]");
//			planSetService.getComponentListQty((PermitConduitConductorSectionEntity) scenario.get(i).get(0),(DCCombinerDisconnectEntity) scenario.get(i).get(1),(ACDisconnect) scenario.get(i).get(2),(ACDisconnect) scenario.get(i).get(3),(ACCombinerSLC) scenario.get(i).get(4),(PermitProjectSiteInfoEntityTwo) scenario.get(i).get(5));
//		}
//    }
//}
