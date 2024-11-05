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
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//
//import com.PlayGroundAdv.Solar.Constants.Constants;
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.Cmodulev2;
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.Flashing;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitSketchEntity;
//import com.PlayGroundAdv.Solar.Entity.PlansetCustomizeSheets;
//import com.PlayGroundAdv.Solar.Entity.RailRacking;
//import com.PlayGroundAdv.Solar.Entity.RoofAttachmentsEntity;
//import com.PlayGroundAdv.Solar.Entity.RoofMaterialType;
//import com.PlayGroundAdv.Solar.Entity.UserLicSectionsEntity;
//import com.PlayGroundAdv.Solar.model.EditUserInformations;
//import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
//import com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.PermitServiceEdit;
//import com.PlayGroundAdv.Solar.Services.PlanSetServiceP002;
//import com.PlayGroundAdv.Solar.Services.PlanSetServiceS200;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//public class TestPlanSetServiceS200 {
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
//    PlanSetServiceS200 planSetServiceS200 = new PlanSetServiceS200();
//    
//    PdfReader reader = null;
//   	File fileRe = null;
//   	PdfStamper stamper = null;
//   	AcroFields form = null;
//   	PdfReader readerOrigin= null;
//   	
//     @Before
//     public void setupMock() {
//    	 planSetServiceS200 = new PlanSetServiceS200();
//        MockitoAnnotations.initMocks(this);
// 		
// 		try {
// 		reader = new PdfReader(Constants.rapportPlansetFolderUrl +"NEC-PDF/" +"PDF-S201"+".pdf" );
// 		fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-S201"+".pdf");
// 		stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
// 		form = stamper.getAcroFields();
// 		readerOrigin = new PdfReader( Constants.rapportPlansetFolderUrl +"NEC-PDF/" + "PDF-S201.pdf" );
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
//     		if(stamper != null) {
//     			stamper.close();
//     		}
// 			if(reader != null) {
// 				reader.close();
// 			}
// 			
// 		} catch (IOException e) {
// 				e.printStackTrace();
// 		}
// 		  catch (DocumentException e){
// 	    }
//     }
//    
//
//		@Test
//		public void testmapTitleBlock() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit1 = new PermitEntity();
//			PermitHomeSiteEntityResult permitHomeSite1 =new PermitHomeSiteEntityResult();
//			scenario.get(0).add(permitHomeSite1);
//			scenario.get(0).add(null);
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
//			PermitArrayEntityResultSecond permitArraysEntityResult2= new PermitArrayEntityResultSecond();
//			permitHomeSite2.setSiteAddress("siteAddress");
//			permitHomeSite2.setCity("city");
//			permitHomeSite2.setState("NY");
//			scenario.get(1).add(permitHomeSite2);
//			scenario.get(1).add(permitArraysEntityResult2);
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
//			scenario.get(2).add(permitArraysEntityResult3);
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
//			scenario.get(3).add(permitArraysEntityResult4);
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
//			scenario.get(4).add(permitArraysEntityResult5);
//			scenario.get(4).add(permit5);
//			scenario.get(4).add(form);
//			scenario.get(4).add("");
//			PermitCompanyInfoEntityResult permitCompanyInfo5 = new PermitCompanyInfoEntityResult();
//			scenario.get(4).add(permitCompanyInfo5);
//			ElectricalUtilityEntity electricalCompany5 = new ElectricalUtilityEntity();
//			scenario.get(4).add(electricalCompany5);
//			for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("testmapTitleBlock ["+i+"]");
//			 planSetServiceS200.mapTitleBlock((PermitHomeSiteEntityResult)scenario.get(i).get(0),(PermitArrayEntityResultSecond)scenario.get(i).get(1),(PermitEntity)scenario.get(i).get(2),(AcroFields)scenario.get(i).get(3),(String)scenario.get(i).get(4),(PermitCompanyInfoEntityResult)scenario.get(i).get(5),(ElectricalUtilityEntity)scenario.get(i).get(6),0);
//			}
//		}
//		
//		@Test
//		public void testmapContractorInfo() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			List<UserLicSectionsEntity> resultlist =null;
//			UserLicSectionsEntity userLicSection = new UserLicSectionsEntity();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(resultlist);
//			scenario.get(0).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations1 = new EditUserInformations() ;
//			scenario.get(1).add(editUserInformations1);
//			scenario.get(1).add(form);
//			scenario.get(1).add(null);
//			scenario.get(1).add(resultlist); 
//			scenario.get(1).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations2 = new EditUserInformations() ;
//			editUserInformations2.setContactFirstName("FirstName");
//			editUserInformations2.setContactOptions("firstContact");
//			editUserInformations2.setDesignBy("firstContact");
//			scenario.get(2).add(editUserInformations2);
//			scenario.get(2).add(form);
//			PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//			scenario.get(2).add(permitHomeSite2);
//			scenario.get(2).add(resultlist); 
//			scenario.get(2).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations3 = new EditUserInformations() ;
//			editUserInformations3.setContactFirstName("FirstName");
//			editUserInformations3.setContactLastName("LastName");
//			editUserInformations3.setContactOptions("secondContact");
//			editUserInformations3.setDesignBy("secondContact");
//			scenario.get(3).add(editUserInformations3);
//			scenario.get(3).add(form);
//			PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//			permitHomeSite3.setState("state");
//			scenario.get(3).add(permitHomeSite3);
//			scenario.get(3).add(resultlist); 
//			scenario.get(3).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations4 = new EditUserInformations() ;
//			editUserInformations4.setContactFirstName("FirstName");
//			editUserInformations4.setContactLastName("LastName");
//			editUserInformations4.setContactOptions("thirdContact");
//			editUserInformations4.setDesignBy("thirdContact");
//			editUserInformations4.setContractorLicenceState("state");
//			editUserInformations4.setContractorLic(false);
//			editUserInformations4.setContractorLicC10(false);
//			editUserInformations4.setContractorLicB(false);
//			scenario.get(4).add(editUserInformations4);
//			scenario.get(4).add(form);
//			PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//			permitHomeSite4.setState("state");
//			scenario.get(4).add(permitHomeSite4);
//			scenario.get(4).add(resultlist);
//			scenario.get(4).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations5 = new EditUserInformations() ;
//			editUserInformations5.setContactFirstName("FirstName");
//			editUserInformations5.setContactLastName("LastName");
//			editUserInformations5.setContactOptions("Other");
//			editUserInformations5.setDesignBy("OtherDesignBy");
//			editUserInformations5.setContractorLicenceState("state");
//			editUserInformations5.setContractorLic(false);
//			editUserInformations5.setContractorLicC10(false);
//			editUserInformations5.setContractorLicB(false);
//			scenario.get(5).add(editUserInformations5);
//			scenario.get(5).add(form);
//			PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//			permitHomeSite5.setState("state");
//			scenario.get(5).add(permitHomeSite5);
//			List<UserLicSectionsEntity> resultlist5 =new ArrayList<UserLicSectionsEntity>();
//			resultlist5.add(userLicSection);
//			scenario.get(5).add(resultlist5);
//			scenario.get(5).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations6 = new EditUserInformations() ;
//			editUserInformations6.setContactFirstName("FirstName");
//			editUserInformations6.setContactLastName("LastName");
//			editUserInformations6.setContactOptions("Other");
//			editUserInformations6.setDesignBy("");
//			editUserInformations6.setContractorLicenceState("state");
//			editUserInformations6.setContractorLic(false);
//			editUserInformations6.setContractorLicC10(false);
//			editUserInformations6.setContractorLicB(false);
//			scenario.get(6).add(editUserInformations6);
//			scenario.get(6).add(form);
//			PermitHomeSiteEntityResult permitHomeSite6 = new PermitHomeSiteEntityResult();
//			permitHomeSite6.setState("state");
//			scenario.get(6).add(permitHomeSite6);
//			List<UserLicSectionsEntity> resultlist6 =new ArrayList<UserLicSectionsEntity>();
//			UserLicSectionsEntity userLicSection6 = new UserLicSectionsEntity();
//			userLicSection6.setContractorLicenceState("CA");
//			resultlist6.add(userLicSection6);
//			scenario.get(6).add(resultlist6);
//			scenario.get(6).add(userLicSection6);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations7 = new EditUserInformations() ;
//			editUserInformations7.setContactFirstName("FirstName");
//			editUserInformations7.setContactLastName("LastName");
//			editUserInformations7.setContactOptions("Other");
//			editUserInformations7.setDesignBy("");
//			editUserInformations7.setContractorLicenceState("state");
//			editUserInformations7.setContractorLic(false);
//			editUserInformations7.setContractorLicC10(false);
//			editUserInformations7.setContractorLicB(false);
//			scenario.get(7).add(editUserInformations7);
//			scenario.get(7).add(form);
//			PermitHomeSiteEntityResult permitHomeSite7 = new PermitHomeSiteEntityResult();
//			permitHomeSite7.setState("state");
//			scenario.get(7).add(permitHomeSite7);
//			List<UserLicSectionsEntity> resultlist7 =new ArrayList<UserLicSectionsEntity>();
//			UserLicSectionsEntity userLicSection7 = new UserLicSectionsEntity();
//			String [] str = {};
//			userLicSection7.setLicTypeCode(str);
//			userLicSection7.setContractorLicenceState("CA");
//			resultlist7.add(userLicSection7);
//			scenario.get(7).add(resultlist7);
//			scenario.get(7).add(userLicSection7);
//			
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations8 = new EditUserInformations() ;
//			editUserInformations8.setContactFirstName("FirstName");
//			editUserInformations8.setContactLastName("LastName");
//			editUserInformations8.setContactOptions("Other");
//			editUserInformations8.setDesignBy("");
//			editUserInformations8.setContractorLicenceState("state");
//			editUserInformations8.setContractorLic(false);
//			editUserInformations8.setContractorLicC10(false);
//			editUserInformations8.setContractorLicB(false);
//			scenario.get(8).add(editUserInformations8);
//			scenario.get(8).add(form);
//			PermitHomeSiteEntityResult permitHomeSite8 = new PermitHomeSiteEntityResult();
//			permitHomeSite8.setState("state");
//			scenario.get(8).add(permitHomeSite8);
//			List<UserLicSectionsEntity> resultlist8 =new ArrayList<UserLicSectionsEntity>();
//			UserLicSectionsEntity userLicSection8 = new UserLicSectionsEntity();
//			String [] LicTypeCode = {null};
//			userLicSection8.setLicTypeCode(LicTypeCode);
//			userLicSection8.setContractorLicenceState("CA");
//			resultlist8.add(userLicSection8);
//			scenario.get(8).add(resultlist8);
//			scenario.get(8).add(userLicSection8);
//			
//			Query mockedQuery = mock(Query.class);
//			when(em.createQuery("SELECT u from UserLicSectionsEntity u WHERE u.authentificationEntity.id = :p1 AND u.contractorLicenceState= :p2")).thenReturn(mockedQuery);
//			when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapContractorInfo ["+i+"]");
//				 when(mockedQuery.getResultList()).thenReturn((List<UserLicSectionsEntity>)scenario.get(i).get(3));
//				 when(mockedQuery.getSingleResult()).thenReturn((UserLicSectionsEntity)scenario.get(i).get(4));
//				 planSetServiceS200.mapContractorInfo((EditUserInformations)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),0);
//			}
//		}
//		
//		@Test
//		public void testmapTableDimensions() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitAdditionalInfoEntityResult permitAdditionalInfo = new PermitAdditionalInfoEntityResult();
//			scenario.get(1).add(permitAdditionalInfo);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo= new PermitProjectSiteInfoEntityTwo();
//			scenario.get(1).add(permitProjectSiteInfo);
//			scenario.get(1).add(form);
//			List<PermitSketchEntity> listPermitSketchEntity = new ArrayList<PermitSketchEntity>();
//			scenario.get(1).add(listPermitSketchEntity);
//			PermitHomeSiteEntityResult permitHomeSite = new PermitHomeSiteEntityResult();
//			scenario.get(1).add(permitHomeSite);
//			Cmodulev2 moduleInfo= new Cmodulev2();
//			scenario.get(1).add(moduleInfo);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitAdditionalInfoEntityResult permitAdditionalInfo2 = new PermitAdditionalInfoEntityResult();
//			scenario.get(2).add(permitAdditionalInfo2);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2= new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo2.setCrossSectionSize("CrossSectionSize");
//			scenario.get(2).add(permitProjectSiteInfo2);
//			scenario.get(2).add(form);
//			List<PermitSketchEntity> listPermitSketchEntity2 = new ArrayList<PermitSketchEntity>();
//			listPermitSketchEntity2.add(null);
//			scenario.get(2).add(listPermitSketchEntity2);
//			PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//			permitHomeSite2.setRidgeBeamDepthAtArrays("Other");
//			permitHomeSite2.setMaxHorizontalSpanAtArrays("HS1");
//			permitHomeSite2.setMaxHorizontalSpanAtArraysHS("HS2");
//			scenario.get(2).add(permitHomeSite2);
//			Cmodulev2 moduleInfo2= new Cmodulev2();
//			moduleInfo2.setWidth("1,2");
//			moduleInfo2.setLength("1,2");
//			scenario.get(2).add(moduleInfo2);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitAdditionalInfoEntityResult permitAdditionalInfo3 = new PermitAdditionalInfoEntityResult();
//			permitAdditionalInfo3.setTiltLegs(false);
//			scenario.get(3).add(permitAdditionalInfo3);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3= new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo3.setCrossSectionSize("CrossxSectionSize");
//			scenario.get(3).add(permitProjectSiteInfo3);
//			scenario.get(3).add(form);
//			List<PermitSketchEntity> listPermitSketchEntity3 = new ArrayList<PermitSketchEntity>();
//			PermitSketchEntity permitSketchEntity3 = new PermitSketchEntity();
//			listPermitSketchEntity3.add(permitSketchEntity3);
//			scenario.get(3).add(listPermitSketchEntity3);
//			PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//			permitHomeSite3.setRidgeBeamDepthAtArrays("Other");
//			permitHomeSite3.setMaxHorizontalSpanAtArrays("HS1");
//			permitHomeSite3.setMaxHorizontalSpanAtArraysHS("HS2");
//			permitHomeSite3.setMaxHorizontalSpanAtArraysInches("HS1 inches");
//			permitHomeSite3.setMaxHorizontalSpanAtArraysHSInches("HS2 inches");
//			scenario.get(3).add(permitHomeSite3);
//			Cmodulev2 moduleInfo3= new Cmodulev2();
//			moduleInfo3.setWidth("Width");
//			moduleInfo3.setLength("Length");
//			scenario.get(3).add(moduleInfo3);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapTableDimensions ["+i+"]");
//				 planSetServiceS200.mapTableDimensions((PermitAdditionalInfoEntityResult)scenario.get(i).get(0),(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),( List<PermitSketchEntity>)scenario.get(i).get(3),(PermitHomeSiteEntityResult)scenario.get(i).get(4),(Cmodulev2)scenario.get(i).get(5),0);
//			}
//		}
//
//		@Test
//		public void testmappingUpdates() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(1).add(form);
//			PermitHomeSiteEntityResult permitHomeSite1 = new PermitHomeSiteEntityResult();
//			scenario.get(1).add(permitHomeSite1);
//			RoofMaterialType roofMaterialType1 = new RoofMaterialType();
//			scenario.get(1).add(roofMaterialType1);
//			scenario.get(1).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(2).add(form);
//			PermitHomeSiteEntityResult permitHomeSite2= new PermitHomeSiteEntityResult();
//			permitHomeSite2.setMaxHorizontalSpanAtArrays("MaxHorizontalSpanAtArrays");
//			scenario.get(2).add(permitHomeSite2);
//			RoofMaterialType roofMaterialType2 = new RoofMaterialType();
//			roofMaterialType2.setTypeRoof("Other");
//			scenario.get(2).add(roofMaterialType2);
//			scenario.get(2).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(3).add(form);
//			PermitHomeSiteEntityResult permitHomeSite3= new PermitHomeSiteEntityResult();
//			permitHomeSite3.setMaxHorizontalSpanAtArrays("MaxHorizontalSpanAtArrays");
//			scenario.get(3).add(permitHomeSite3);
//			RoofMaterialType roofMaterialType3 = new RoofMaterialType();
//			roofMaterialType3.setTypeRoof("Other");
//			scenario.get(3).add(roofMaterialType3);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo3.setRafterTrussSpacing("16");
//			scenario.get(3).add(permitProjectSiteInfo3);
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(4).add(form);
//			PermitHomeSiteEntityResult permitHomeSite4= new PermitHomeSiteEntityResult();
//			permitHomeSite4.setMaxHorizontalSpanAtArrays("MaxHorizontalSpanAtArrays");
//			scenario.get(4).add(permitHomeSite4);
//			RoofMaterialType roofMaterialType4 = new RoofMaterialType();
//			roofMaterialType4.setTypeRoof("Other");
//			scenario.get(4).add(roofMaterialType4);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo4.setRafterTrussSpacing("24");
//			scenario.get(4).add(permitProjectSiteInfo4);
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(5).add(form);
//			PermitHomeSiteEntityResult permitHomeSite5= new PermitHomeSiteEntityResult();
//			permitHomeSite5.setMaxHorizontalSpanAtArrays("MaxHorizontalSpanAtArrays");
//			scenario.get(5).add(permitHomeSite5);
//			RoofMaterialType roofMaterialType5 = new RoofMaterialType();
//			roofMaterialType5.setTypeRoof("Other");
//			scenario.get(5).add(roofMaterialType5);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo5.setRafterTrussSpacing("32");
//			scenario.get(5).add(permitProjectSiteInfo5);
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(6).add(form);
//			PermitHomeSiteEntityResult permitHomeSite6= new PermitHomeSiteEntityResult();
//			permitHomeSite6.setMaxHorizontalSpanAtArrays("MaxHorizontalSpanAtArrays");
//			scenario.get(6).add(permitHomeSite6);
//			RoofMaterialType roofMaterialType6 = new RoofMaterialType();
//			roofMaterialType6.setTypeRoof("Slate");
//			scenario.get(6).add(roofMaterialType6);
//			scenario.get(6).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(7).add(form);
//			PermitHomeSiteEntityResult permitHomeSite7= new PermitHomeSiteEntityResult();
//			permitHomeSite7.setMaxHorizontalSpanAtArrays("MaxHorizontalSpanAtArrays");
//			scenario.get(7).add(permitHomeSite7);
//			RoofMaterialType roofMaterialType7 = new RoofMaterialType();
//			roofMaterialType7.setTypeRoof("Slate");
//			scenario.get(7).add(roofMaterialType7);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo7 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo7.setRafterTrussSpacing("16");
//			scenario.get(7).add(permitProjectSiteInfo7);
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(8).add(form);
//			PermitHomeSiteEntityResult permitHomeSite8= new PermitHomeSiteEntityResult();
//			permitHomeSite8.setMaxHorizontalSpanAtArrays("MaxHorizontalSpanAtArrays");
//			scenario.get(8).add(permitHomeSite8);
//			RoofMaterialType roofMaterialType8 = new RoofMaterialType();
//			roofMaterialType8.setTypeRoof("Slate");
//			scenario.get(8).add(roofMaterialType8);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo8 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo8.setRafterTrussSpacing("24");
//			scenario.get(8).add(permitProjectSiteInfo8);
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(9).add(form);
//			PermitHomeSiteEntityResult permitHomeSite9= new PermitHomeSiteEntityResult();
//			permitHomeSite9.setMaxHorizontalSpanAtArrays("MaxHorizontalSpanAtArrays");
//			scenario.get(9).add(permitHomeSite9);
//			RoofMaterialType roofMaterialType9 = new RoofMaterialType();
//			roofMaterialType9.setTypeRoof("Slate");
//			scenario.get(9).add(roofMaterialType9);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo9 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo9.setRafterTrussSpacing("32");
//			scenario.get(9).add(permitProjectSiteInfo9);
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmappingUpdates ["+i+"]");
//				 planSetServiceS200.mappingUpdates((AcroFields)scenario.get(i).get(0),(PermitHomeSiteEntityResult)scenario.get(i).get(1),(RoofMaterialType)scenario.get(i).get(2),(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(3),0);
//			}
//		}
//		
//		@Test
//		public void testmapTableDimesionsOne() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			List<PermitSketchEntity> listPermitSketchEntity = new ArrayList<PermitSketchEntity>();
//			scenario.get(1).add(listPermitSketchEntity);
//			scenario.get(1).add(form);
//			PermitHomeSiteEntityResult permitHomeSite = new PermitHomeSiteEntityResult();
//			scenario.get(1).add(permitHomeSite);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo= new PermitProjectSiteInfoEntityTwo();
//			scenario.get(1).add(permitProjectSiteInfo);
//			
//			scenario.add(new ArrayList<Object>());
//			
//			
//			List<PermitSketchEntity> listPermitSketchEntity2 = new ArrayList<PermitSketchEntity>();
//			listPermitSketchEntity2.add(null);
//			scenario.get(2).add(listPermitSketchEntity2);
//			scenario.get(2).add(form);
//			PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//			permitHomeSite2.setRidgeBeamDepthAtArrays("Other");
//			permitHomeSite2.setMaxHorizontalSpanAtArrays("HS1");
//			permitHomeSite2.setMaxHorizontalSpanAtArraysHS("HS2");
//			scenario.get(2).add(permitHomeSite2);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2= new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo2.setCrossSectionSize("CrossSectionSize");
//			scenario.get(2).add(permitProjectSiteInfo2);
//			
//			scenario.add(new ArrayList<Object>());
//			List<PermitSketchEntity> listPermitSketchEntity3 = new ArrayList<PermitSketchEntity>();
//			PermitSketchEntity permitSketchEntity3 = new PermitSketchEntity();
//			listPermitSketchEntity3.add(permitSketchEntity3);
//			scenario.get(3).add(listPermitSketchEntity3);
//			scenario.get(3).add(form);
//			PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//			permitHomeSite3.setRidgeBeamDepthAtArrays("Other");
//			permitHomeSite3.setMaxHorizontalSpanAtArrays("HS1");
//			permitHomeSite3.setMaxHorizontalSpanAtArraysHS("HS2");
//			permitHomeSite3.setMaxHorizontalSpanAtArraysInches("HS1 inches");
//			permitHomeSite3.setMaxHorizontalSpanAtArraysHSInches("HS2 inches");
//			scenario.get(3).add(permitHomeSite3);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3= new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo3.setCrossSectionSize("CrossxSectionSize");
//			scenario.get(3).add(permitProjectSiteInfo3);
//			
//			scenario.add(new ArrayList<Object>());
//			List<PermitSketchEntity> listPermitSketchEntity4 = new ArrayList<PermitSketchEntity>();
//			PermitSketchEntity permitSketchEntity4 = new PermitSketchEntity();
//			permitSketchEntity4.setEaveOverHang("Other");
//			permitSketchEntity4.setRoofPitch("null");
//			listPermitSketchEntity4.add(permitSketchEntity4);
//			scenario.get(4).add(listPermitSketchEntity4);
//			scenario.get(4).add(form);
//			PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//			permitHomeSite4.setRidgeBeamDepthAtArrays("Other");
//			permitHomeSite4.setMaxHorizontalSpanAtArrays("HS1");
//			permitHomeSite4.setMaxHorizontalSpanAtArraysHS("HS2");
//			permitHomeSite4.setMaxHorizontalSpanAtArraysInches("HS1 inches");
//			permitHomeSite4.setMaxHorizontalSpanAtArraysHSInches("HS2 inches");
//			scenario.get(4).add(permitHomeSite4);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4= new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo4.setCrossSectionSize("CrossxSectionSize");
//			scenario.get(4).add(permitProjectSiteInfo4);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapTableDimesionsOne ["+i+"]");
//				 planSetServiceS200.mapTableDimesionsOne(( List<PermitSketchEntity>)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(3),0);
//			}
//		}
//		
//		@Test
//		public void testmapexistingRoof() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo1 = new PermitProjectSiteInfoEntityTwo();
//			scenario.get(1).add(permitProjectSiteInfo1);
//			scenario.get(1).add(form);
//			RoofMaterialType roofMaterialType1 = new RoofMaterialType();
//			scenario.get(1).add(roofMaterialType1);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo2.setTallStructure("OtheStory");
//			scenario.get(2).add(permitProjectSiteInfo2);
//			scenario.get(2).add(form);
//			RoofMaterialType roofMaterialType2 = new RoofMaterialType();
//			roofMaterialType2.setMappingValue("roofMaterial MappingValue");
//			scenario.get(2).add(roofMaterialType2);
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapexistingRoof ["+i+"]");
//				 planSetServiceS200.mapexistingRoof((PermitProjectSiteInfoEntityTwo)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(RoofMaterialType)scenario.get(i).get(2),0);
//			}
//		}
//		
//		@Test
//		public void testmapRailRacking() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			
//			scenario.add(new ArrayList<Object>());
//			RailRacking railRacking = new RailRacking();
//			scenario.get(1).add(railRacking);
//			scenario.get(1).add(form);
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapRailRacking ["+i+"]");
//				 planSetServiceS200.mapRailRacking((RailRacking)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),0);
//			}
//		}
//		
//		@Test
//		public void testmapStanchionFoot() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			RoofAttachmentsEntity stanchionFoot = new RoofAttachmentsEntity();
//			scenario.get(1).add(stanchionFoot);
//			scenario.get(1).add(form);
//			Flashing flashing = new Flashing();
//			scenario.get(1).add(flashing);
//			
//			scenario.add(new ArrayList<Object>());
//			RoofAttachmentsEntity stanchionFoot2 = new RoofAttachmentsEntity();
//			stanchionFoot2.setModel("model");
//			stanchionFoot2.setIntegrated("Yes");
//			scenario.get(2).add(stanchionFoot2);
//			scenario.get(2).add(form);
//			Flashing flashing2 = new Flashing();
//			scenario.get(2).add(flashing2);
//			
//			scenario.add(new ArrayList<Object>());
//			RoofAttachmentsEntity stanchionFoot3 = new RoofAttachmentsEntity();
//			stanchionFoot3.setModel("model");
//			stanchionFoot3.setModelMappingValue("modelMappingValue");
//			stanchionFoot3.setIntegrated("NO");
//			scenario.get(3).add(stanchionFoot3);
//			scenario.get(3).add(form);
//			Flashing flashing3 = new Flashing();
//			flashing3.setMappedValue("flashingMappedValue");
//			scenario.get(3).add(flashing3);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapStanchionFoot ["+i+"]");
//				 planSetServiceS200.mapStanchionFoot((RoofAttachmentsEntity)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(Flashing)scenario.get(i).get(2),0);
//			}
//		}
//
//		@Test
//		public void testmapTitleBlockTruss() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit1 = new PermitEntity();
//			PermitHomeSiteEntityResult permitHomeSite1 =new PermitHomeSiteEntityResult();
//			scenario.get(0).add(permitHomeSite1);
//			scenario.get(0).add(null);
//			scenario.get(0).add(permit1);
//			scenario.get(0).add(form);
//			scenario.get(0).add("");
//			scenario.get(0).add(null);
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit2 = new PermitEntity();
//			permit2.setReleasev3("rv3");
//			permit2.setReleasev2("rv2");
//			PermitHomeSiteEntityResult permitHomeSite2 =new PermitHomeSiteEntityResult();
//			PermitArrayEntityResultSecond permitArraysEntityResult2= new PermitArrayEntityResultSecond();
//			permitHomeSite2.setSiteAddress("siteAddress");
//			permitHomeSite2.setCity("city");
//			permitHomeSite2.setState("NY");
//			scenario.get(1).add(permitHomeSite2);
//			scenario.get(1).add(permitArraysEntityResult2);
//			scenario.get(1).add(permit2);
//			scenario.get(1).add(form);
//			scenario.get(1).add("");
//			scenario.get(1).add(null);
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
//			scenario.get(2).add(permitArraysEntityResult3);
//			scenario.get(2).add(permit3);
//			scenario.get(2).add(form);
//			scenario.get(2).add("");
//			scenario.get(2).add(null);
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
//			scenario.get(3).add(permitArraysEntityResult4);
//			scenario.get(3).add(permit4);
//			scenario.get(3).add(form);
//			scenario.get(3).add("");
//			PermitCompanyInfoEntityResult permitCompanyInfo4 = new PermitCompanyInfoEntityResult();
//			scenario.get(3).add(permitCompanyInfo4);
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
//			scenario.get(4).add(permitArraysEntityResult5);
//			scenario.get(4).add(permit5);
//			scenario.get(4).add(form);
//			scenario.get(4).add("");
//			PermitCompanyInfoEntityResult permitCompanyInfo5 = new PermitCompanyInfoEntityResult();
//			scenario.get(4).add(permitCompanyInfo5);
//			for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("testmapTitleBlockTruss ["+i+"]");
//			 planSetServiceS200.mapTitleBlockTruss((PermitHomeSiteEntityResult)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(3),(PermitEntity)scenario.get(i).get(2),(PermitCompanyInfoEntityResult)scenario.get(i).get(5),(String)scenario.get(i).get(4),(PermitArrayEntityResultSecond)scenario.get(i).get(1),0);
//			}
//		}
//		
//		@Test
//		public void testmapContractorInfoTruss() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			List<UserLicSectionsEntity> resultlist =null;
//			UserLicSectionsEntity userLicSection = new UserLicSectionsEntity();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(resultlist);
//			scenario.get(0).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations1 = new EditUserInformations() ;
//			scenario.get(1).add(editUserInformations1);
//			scenario.get(1).add(form);
//			scenario.get(1).add(null);
//			scenario.get(1).add(resultlist); 
//			scenario.get(1).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations2 = new EditUserInformations() ;
//			editUserInformations2.setContactFirstName("FirstName");
//			editUserInformations2.setContactOptions("firstContact");
//			editUserInformations2.setDesignBy("firstContact");
//			scenario.get(2).add(editUserInformations2);
//			scenario.get(2).add(form);
//			PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//			scenario.get(2).add(permitHomeSite2);
//			scenario.get(2).add(resultlist); 
//			scenario.get(2).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations3 = new EditUserInformations() ;
//			editUserInformations3.setContactFirstName("FirstName");
//			editUserInformations3.setContactLastName("LastName");
//			editUserInformations3.setContactOptions("secondContact");
//			editUserInformations3.setDesignBy("secondContact");
//			scenario.get(3).add(editUserInformations3);
//			scenario.get(3).add(form);
//			PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//			permitHomeSite3.setState("state");
//			scenario.get(3).add(permitHomeSite3);
//			scenario.get(3).add(resultlist); 
//			scenario.get(3).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations4 = new EditUserInformations() ;
//			editUserInformations4.setContactFirstName("FirstName");
//			editUserInformations4.setContactLastName("LastName");
//			editUserInformations4.setContactOptions("thirdContact");
//			editUserInformations4.setDesignBy("thirdContact");
//			editUserInformations4.setContractorLicenceState("state");
//			editUserInformations4.setContractorLic(false);
//			editUserInformations4.setContractorLicC10(false);
//			editUserInformations4.setContractorLicB(false);
//			scenario.get(4).add(editUserInformations4);
//			scenario.get(4).add(form);
//			PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//			permitHomeSite4.setState("state");
//			scenario.get(4).add(permitHomeSite4);
//			scenario.get(4).add(resultlist);
//			scenario.get(4).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations5 = new EditUserInformations() ;
//			editUserInformations5.setContactFirstName("FirstName");
//			editUserInformations5.setContactLastName("LastName");
//			editUserInformations5.setContactOptions("Other");
//			editUserInformations5.setDesignBy("OtherDesignBy");
//			editUserInformations5.setContractorLicenceState("state");
//			editUserInformations5.setContractorLic(false);
//			editUserInformations5.setContractorLicC10(false);
//			editUserInformations5.setContractorLicB(false);
//			scenario.get(5).add(editUserInformations5);
//			scenario.get(5).add(form);
//			PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//			permitHomeSite5.setState("state");
//			scenario.get(5).add(permitHomeSite5);
//			List<UserLicSectionsEntity> resultlist5 =new ArrayList<UserLicSectionsEntity>();
//			resultlist5.add(userLicSection);
//			scenario.get(5).add(resultlist5);
//			scenario.get(5).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations6 = new EditUserInformations() ;
//			editUserInformations6.setContactFirstName("FirstName");
//			editUserInformations6.setContactLastName("LastName");
//			editUserInformations6.setContactOptions("Other");
//			editUserInformations6.setDesignBy("");
//			editUserInformations6.setContractorLicenceState("state");
//			editUserInformations6.setContractorLic(false);
//			editUserInformations6.setContractorLicC10(false);
//			editUserInformations6.setContractorLicB(false);
//			scenario.get(6).add(editUserInformations6);
//			scenario.get(6).add(form);
//			PermitHomeSiteEntityResult permitHomeSite6 = new PermitHomeSiteEntityResult();
//			permitHomeSite6.setState("state");
//			scenario.get(6).add(permitHomeSite6);
//			List<UserLicSectionsEntity> resultlist6 =new ArrayList<UserLicSectionsEntity>();
//			UserLicSectionsEntity userLicSection6 = new UserLicSectionsEntity();
//			userLicSection6.setContractorLicenceState("CA");
//			resultlist6.add(userLicSection6);
//			scenario.get(6).add(resultlist6);
//			scenario.get(6).add(userLicSection6);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations7 = new EditUserInformations() ;
//			editUserInformations7.setContactFirstName("FirstName");
//			editUserInformations7.setContactLastName("LastName");
//			editUserInformations7.setContactOptions("Other");
//			editUserInformations7.setDesignBy("");
//			editUserInformations7.setContractorLicenceState("state");
//			editUserInformations7.setContractorLic(false);
//			editUserInformations7.setContractorLicC10(false);
//			editUserInformations7.setContractorLicB(false);
//			scenario.get(7).add(editUserInformations7);
//			scenario.get(7).add(form);
//			PermitHomeSiteEntityResult permitHomeSite7 = new PermitHomeSiteEntityResult();
//			permitHomeSite7.setState("state");
//			scenario.get(7).add(permitHomeSite7);
//			List<UserLicSectionsEntity> resultlist7 =new ArrayList<UserLicSectionsEntity>();
//			UserLicSectionsEntity userLicSection7 = new UserLicSectionsEntity();
//			String [] str = {};
//			userLicSection7.setLicTypeCode(str);
//			userLicSection7.setContractorLicenceState("CA");
//			resultlist7.add(userLicSection7);
//			scenario.get(7).add(resultlist7);
//			scenario.get(7).add(userLicSection7);
//			
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations8 = new EditUserInformations() ;
//			editUserInformations8.setContactFirstName("FirstName");
//			editUserInformations8.setContactLastName("LastName");
//			editUserInformations8.setContactOptions("Other");
//			editUserInformations8.setDesignBy("");
//			editUserInformations8.setContractorLicenceState("state");
//			editUserInformations8.setContractorLic(false);
//			editUserInformations8.setContractorLicC10(false);
//			editUserInformations8.setContractorLicB(false);
//			scenario.get(8).add(editUserInformations8);
//			scenario.get(8).add(form);
//			PermitHomeSiteEntityResult permitHomeSite8 = new PermitHomeSiteEntityResult();
//			permitHomeSite8.setState("state");
//			scenario.get(8).add(permitHomeSite8);
//			List<UserLicSectionsEntity> resultlist8 =new ArrayList<UserLicSectionsEntity>();
//			UserLicSectionsEntity userLicSection8 = new UserLicSectionsEntity();
//			String [] LicTypeCode = {null};
//			userLicSection8.setLicTypeCode(LicTypeCode);
//			userLicSection8.setContractorLicenceState("CA");
//			resultlist8.add(userLicSection8);
//			scenario.get(8).add(resultlist8);
//			scenario.get(8).add(userLicSection8);
//			
//			Query mockedQuery = mock(Query.class);
//			when(em.createQuery("SELECT u from UserLicSectionsEntity u WHERE u.authentificationEntity.id = :p1 AND u.contractorLicenceState= :p2")).thenReturn(mockedQuery);
//			when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapContractorInfoTruss ["+i+"]");
//				 when(mockedQuery.getResultList()).thenReturn((List<UserLicSectionsEntity>)scenario.get(i).get(3));
//				 when(mockedQuery.getSingleResult()).thenReturn((UserLicSectionsEntity)scenario.get(i).get(4));
//				 planSetServiceS200.mapContractorInfoTruss((EditUserInformations)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),0);
//			}
//		}
//		
//		@Test
//		public void testmapTableofDimensionsTruss() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitAdditionalInfoEntityResult permitAdditionalInfo = new PermitAdditionalInfoEntityResult();
//			scenario.get(1).add(permitAdditionalInfo);
//			scenario.get(1).add(form);
//			List<PermitSketchEntity> listPermitSketchEntity = new ArrayList<PermitSketchEntity>();
//			scenario.get(1).add(listPermitSketchEntity);
//			Cmodulev2 moduleInfo= new Cmodulev2();
//			scenario.get(1).add(moduleInfo);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitAdditionalInfoEntityResult permitAdditionalInfo2 = new PermitAdditionalInfoEntityResult();
//			scenario.get(2).add(permitAdditionalInfo2);
//			scenario.get(2).add(form);
//			List<PermitSketchEntity> listPermitSketchEntity2 = new ArrayList<PermitSketchEntity>();
//			listPermitSketchEntity2.add(null);
//			scenario.get(2).add(listPermitSketchEntity2);
//			Cmodulev2 moduleInfo2= new Cmodulev2();
//			moduleInfo2.setWidth("1,2");
//			moduleInfo2.setLength("1,2");
//			scenario.get(2).add(moduleInfo2);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitAdditionalInfoEntityResult permitAdditionalInfo3 = new PermitAdditionalInfoEntityResult();
//			permitAdditionalInfo3.setTiltLegs(true);
//			scenario.get(3).add(permitAdditionalInfo3);
//			scenario.get(3).add(form);
//			List<PermitSketchEntity> listPermitSketchEntity3 = new ArrayList<PermitSketchEntity>();
//			PermitSketchEntity permitSketchEntity3 = new PermitSketchEntity();
//			listPermitSketchEntity3.add(permitSketchEntity3);
//			scenario.get(3).add(listPermitSketchEntity3);
//			Cmodulev2 moduleInfo3= new Cmodulev2();
//			moduleInfo3.setWidth("Width");
//			moduleInfo3.setLength("Length");
//			scenario.get(3).add(moduleInfo3);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitAdditionalInfoEntityResult permitAdditionalInfo4 = new PermitAdditionalInfoEntityResult();
//			permitAdditionalInfo4.setTiltLegs(true);
//			scenario.get(4).add(permitAdditionalInfo4);
//			scenario.get(4).add(form);
//			List<PermitSketchEntity> listPermitSketchEntity4 = new ArrayList<PermitSketchEntity>();
//			PermitSketchEntity permitSketchEntity4 = new PermitSketchEntity();
//			permitSketchEntity4.setEaveOverHang("Other");
//			permitSketchEntity4.setRoofPitch("");
//			listPermitSketchEntity4.add(permitSketchEntity4);
//			scenario.get(4).add(listPermitSketchEntity4);
//			Cmodulev2 moduleInfo4= new Cmodulev2();
//			moduleInfo4.setWidth("0");
//			moduleInfo4.setLength("0");
//			scenario.get(4).add(moduleInfo4);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapTableofDimensionsTruss ["+i+"]");
//				 planSetServiceS200.mapTableofDimensionsTruss((PermitAdditionalInfoEntityResult)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),( List<PermitSketchEntity>)scenario.get(i).get(2),(Cmodulev2)scenario.get(i).get(3),0);
//			}
//		}
//		
//		@Test
//		public void testmapStanchionFootTruss() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			RoofAttachmentsEntity stanchionFoot = new RoofAttachmentsEntity();
//			scenario.get(1).add(stanchionFoot);
//			scenario.get(1).add(form);
//			Flashing flashing = new Flashing();
//			scenario.get(1).add(flashing);
//			
//			scenario.add(new ArrayList<Object>());
//			RoofAttachmentsEntity stanchionFoot2 = new RoofAttachmentsEntity();
//			stanchionFoot2.setModel("model");
//			stanchionFoot2.setIntegrated("Yes");
//			scenario.get(2).add(stanchionFoot2);
//			scenario.get(2).add(form);
//			Flashing flashing2 = new Flashing();
//			scenario.get(2).add(flashing2);
//			
//			scenario.add(new ArrayList<Object>());
//			RoofAttachmentsEntity stanchionFoot3 = new RoofAttachmentsEntity();
//			stanchionFoot3.setModel("model");
//			stanchionFoot3.setModelMappingValue("modelMappingValue");
//			stanchionFoot3.setIntegrated("NO");
//			scenario.get(3).add(stanchionFoot3);
//			scenario.get(3).add(form);
//			Flashing flashing3 = new Flashing();
//			flashing3.setMappedValue("flashingMappedValue");
//			scenario.get(3).add(flashing3);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapStanchionFootTruss ["+i+"]");
//				 planSetServiceS200.mapStanchionFootTruss((RoofAttachmentsEntity)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(Flashing)scenario.get(i).get(2),0);
//			}
//		}
//		
//		@Test
//		public void testmapTitleBlockOther() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit1 = new PermitEntity();
//			PermitHomeSiteEntityResult permitHomeSite1 =new PermitHomeSiteEntityResult();
//			scenario.get(0).add(permitHomeSite1);
//			scenario.get(0).add(permit1);
//			scenario.get(0).add("");
//			scenario.get(0).add(form);
//			
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
//			scenario.get(1).add("");
//			scenario.get(1).add(form);
//			
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
//			scenario.get(2).add("");
//			scenario.get(2).add(form);
//			
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
//			scenario.get(3).add("");
//			scenario.get(3).add(form);
//			
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
//			scenario.get(4).add("");
//			scenario.get(4).add(form);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("testmapTitleBlockOther ["+i+"]");
//			 planSetServiceS200.mapTitleBlockOther((PermitHomeSiteEntityResult)scenario.get(i).get(0),(PermitEntity)scenario.get(i).get(1),(String)scenario.get(i).get(2),(AcroFields)scenario.get(i).get(3),0);
//			}
//		}
//		
//		@Test
//		public void testmapContractorInfoOther() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			List<UserLicSectionsEntity> resultlist =null;
//			UserLicSectionsEntity userLicSection = new UserLicSectionsEntity();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(resultlist);
//			scenario.get(0).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations1 = new EditUserInformations() ;
//			scenario.get(1).add(editUserInformations1);
//			scenario.get(1).add(form);
//			scenario.get(1).add(null);
//			scenario.get(1).add(resultlist); 
//			scenario.get(1).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations2 = new EditUserInformations() ;
//			editUserInformations2.setContactFirstName("FirstName");
//			editUserInformations2.setContactOptions("firstContact");
//			editUserInformations2.setDesignBy("firstContact");
//			scenario.get(2).add(editUserInformations2);
//			scenario.get(2).add(form);
//			PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//			scenario.get(2).add(permitHomeSite2);
//			scenario.get(2).add(resultlist); 
//			scenario.get(2).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations3 = new EditUserInformations() ;
//			editUserInformations3.setContactFirstName("FirstName");
//			editUserInformations3.setContactLastName("LastName");
//			editUserInformations3.setContactOptions("secondContact");
//			editUserInformations3.setDesignBy("secondContact");
//			scenario.get(3).add(editUserInformations3);
//			scenario.get(3).add(form);
//			PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//			permitHomeSite3.setState("state");
//			scenario.get(3).add(permitHomeSite3);
//			scenario.get(3).add(resultlist); 
//			scenario.get(3).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations4 = new EditUserInformations() ;
//			editUserInformations4.setContactFirstName("FirstName");
//			editUserInformations4.setContactLastName("LastName");
//			editUserInformations4.setContactOptions("thirdContact");
//			editUserInformations4.setDesignBy("thirdContact");
//			editUserInformations4.setContractorLicenceState("state");
//			editUserInformations4.setContractorLic(false);
//			editUserInformations4.setContractorLicC10(false);
//			editUserInformations4.setContractorLicB(false);
//			scenario.get(4).add(editUserInformations4);
//			scenario.get(4).add(form);
//			PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//			permitHomeSite4.setState("state");
//			scenario.get(4).add(permitHomeSite4);
//			scenario.get(4).add(resultlist);
//			scenario.get(4).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations5 = new EditUserInformations() ;
//			editUserInformations5.setContactFirstName("FirstName");
//			editUserInformations5.setContactLastName("LastName");
//			editUserInformations5.setContactOptions("Other");
//			editUserInformations5.setDesignBy("OtherDesignBy");
//			editUserInformations5.setContractorLicenceState("state");
//			editUserInformations5.setContractorLic(false);
//			editUserInformations5.setContractorLicC10(false);
//			editUserInformations5.setContractorLicB(false);
//			scenario.get(5).add(editUserInformations5);
//			scenario.get(5).add(form);
//			PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//			permitHomeSite5.setState("state");
//			scenario.get(5).add(permitHomeSite5);
//			List<UserLicSectionsEntity> resultlist5 =new ArrayList<UserLicSectionsEntity>();
//			resultlist5.add(userLicSection);
//			scenario.get(5).add(resultlist5);
//			scenario.get(5).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations6 = new EditUserInformations() ;
//			editUserInformations6.setContactFirstName("FirstName");
//			editUserInformations6.setContactLastName("LastName");
//			editUserInformations6.setContactOptions("Other");
//			editUserInformations6.setDesignBy("");
//			editUserInformations6.setContractorLicenceState("state");
//			editUserInformations6.setContractorLic(false);
//			editUserInformations6.setContractorLicC10(false);
//			editUserInformations6.setContractorLicB(false);
//			scenario.get(6).add(editUserInformations6);
//			scenario.get(6).add(form);
//			PermitHomeSiteEntityResult permitHomeSite6 = new PermitHomeSiteEntityResult();
//			permitHomeSite6.setState("state");
//			scenario.get(6).add(permitHomeSite6);
//			List<UserLicSectionsEntity> resultlist6 =new ArrayList<UserLicSectionsEntity>();
//			UserLicSectionsEntity userLicSection6 = new UserLicSectionsEntity();
//			userLicSection6.setContractorLicenceState("CA");
//			resultlist6.add(userLicSection6);
//			scenario.get(6).add(resultlist6);
//			scenario.get(6).add(userLicSection6);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations7 = new EditUserInformations() ;
//			editUserInformations7.setContactFirstName("FirstName");
//			editUserInformations7.setContactLastName("LastName");
//			editUserInformations7.setContactOptions("Other");
//			editUserInformations7.setDesignBy("");
//			editUserInformations7.setContractorLicenceState("state");
//			editUserInformations7.setContractorLic(false);
//			editUserInformations7.setContractorLicC10(false);
//			editUserInformations7.setContractorLicB(false);
//			scenario.get(7).add(editUserInformations7);
//			scenario.get(7).add(form);
//			PermitHomeSiteEntityResult permitHomeSite7 = new PermitHomeSiteEntityResult();
//			permitHomeSite7.setState("state");
//			scenario.get(7).add(permitHomeSite7);
//			List<UserLicSectionsEntity> resultlist7 =new ArrayList<UserLicSectionsEntity>();
//			UserLicSectionsEntity userLicSection7 = new UserLicSectionsEntity();
//			String [] str = {};
//			userLicSection7.setLicTypeCode(str);
//			userLicSection7.setContractorLicenceState("CA");
//			resultlist7.add(userLicSection7);
//			scenario.get(7).add(resultlist7);
//			scenario.get(7).add(userLicSection7);
//			
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations8 = new EditUserInformations() ;
//			editUserInformations8.setContactFirstName("FirstName");
//			editUserInformations8.setContactLastName("LastName");
//			editUserInformations8.setContactOptions("Other");
//			editUserInformations8.setDesignBy("");
//			editUserInformations8.setContractorLicenceState("state");
//			editUserInformations8.setContractorLic(false);
//			editUserInformations8.setContractorLicC10(false);
//			editUserInformations8.setContractorLicB(false);
//			scenario.get(8).add(editUserInformations8);
//			scenario.get(8).add(form);
//			PermitHomeSiteEntityResult permitHomeSite8 = new PermitHomeSiteEntityResult();
//			permitHomeSite8.setState("state");
//			scenario.get(8).add(permitHomeSite8);
//			List<UserLicSectionsEntity> resultlist8 =new ArrayList<UserLicSectionsEntity>();
//			UserLicSectionsEntity userLicSection8 = new UserLicSectionsEntity();
//			String [] LicTypeCode = {null};
//			userLicSection8.setLicTypeCode(LicTypeCode);
//			userLicSection8.setContractorLicenceState("CA");
//			resultlist8.add(userLicSection8);
//			scenario.get(8).add(resultlist8);
//			scenario.get(8).add(userLicSection8);
//			
//			Query mockedQuery = mock(Query.class);
//			when(em.createQuery("SELECT u from UserLicSectionsEntity u WHERE u.authentificationEntity.id = :p1 AND u.contractorLicenceState= :p2")).thenReturn(mockedQuery);
//			when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapContractorInfoOther ["+i+"]");
//				 when(mockedQuery.getResultList()).thenReturn((List<UserLicSectionsEntity>)scenario.get(i).get(3));
//				 when(mockedQuery.getSingleResult()).thenReturn((UserLicSectionsEntity)scenario.get(i).get(4));
//				 planSetServiceS200.mapContractorInfoOther((EditUserInformations)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),0);
//			}
//		}
//		
//		@Test
//		public void testmapTitleBlockFourth() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit1 = new PermitEntity();
//			PermitHomeSiteEntityResult permitHomeSite1 =new PermitHomeSiteEntityResult();
//			scenario.get(0).add(permitHomeSite1);
//			scenario.get(0).add(permit1);
//			scenario.get(0).add(form);
//			scenario.get(0).add("");
//			
//			
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
//			
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
//			
//			
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
//			
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
//			
//			for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("testmapTitleBlockFourth ["+i+"]");
//			 planSetServiceS200.mapTitleBlockFourth((PermitHomeSiteEntityResult)scenario.get(i).get(0),(PermitEntity)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),(String)scenario.get(i).get(3),0);
//			}
//		}
//		
//		@Test
//		public void testmapContractorInfoFourth() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			List<UserLicSectionsEntity> resultlist =null;
//			UserLicSectionsEntity userLicSection = new UserLicSectionsEntity();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(resultlist);
//			scenario.get(0).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations1 = new EditUserInformations() ;
//			scenario.get(1).add(editUserInformations1);
//			scenario.get(1).add(form);
//			scenario.get(1).add(null);
//			scenario.get(1).add(resultlist); 
//			scenario.get(1).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations2 = new EditUserInformations() ;
//			editUserInformations2.setContactFirstName("FirstName");
//			editUserInformations2.setContactOptions("firstContact");
//			editUserInformations2.setDesignBy("firstContact");
//			scenario.get(2).add(editUserInformations2);
//			scenario.get(2).add(form);
//			PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//			scenario.get(2).add(permitHomeSite2);
//			scenario.get(2).add(resultlist); 
//			scenario.get(2).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations3 = new EditUserInformations() ;
//			editUserInformations3.setContactFirstName("FirstName");
//			editUserInformations3.setContactLastName("LastName");
//			editUserInformations3.setContactOptions("secondContact");
//			editUserInformations3.setDesignBy("secondContact");
//			scenario.get(3).add(editUserInformations3);
//			scenario.get(3).add(form);
//			PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//			permitHomeSite3.setState("state");
//			scenario.get(3).add(permitHomeSite3);
//			scenario.get(3).add(resultlist); 
//			scenario.get(3).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations4 = new EditUserInformations() ;
//			editUserInformations4.setContactFirstName("FirstName");
//			editUserInformations4.setContactLastName("LastName");
//			editUserInformations4.setContactOptions("thirdContact");
//			editUserInformations4.setDesignBy("thirdContact");
//			editUserInformations4.setContractorLicenceState("state");
//			editUserInformations4.setContractorLic(false);
//			editUserInformations4.setContractorLicC10(false);
//			editUserInformations4.setContractorLicB(false);
//			scenario.get(4).add(editUserInformations4);
//			scenario.get(4).add(form);
//			PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//			permitHomeSite4.setState("state");
//			scenario.get(4).add(permitHomeSite4);
//			scenario.get(4).add(resultlist);
//			scenario.get(4).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations5 = new EditUserInformations() ;
//			editUserInformations5.setContactFirstName("FirstName");
//			editUserInformations5.setContactLastName("LastName");
//			editUserInformations5.setContactOptions("Other");
//			editUserInformations5.setDesignBy("OtherDesignBy");
//			editUserInformations5.setContractorLicenceState("state");
//			editUserInformations5.setContractorLic(false);
//			editUserInformations5.setContractorLicC10(false);
//			editUserInformations5.setContractorLicB(false);
//			scenario.get(5).add(editUserInformations5);
//			scenario.get(5).add(form);
//			PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//			permitHomeSite5.setState("state");
//			scenario.get(5).add(permitHomeSite5);
//			List<UserLicSectionsEntity> resultlist5 =new ArrayList<UserLicSectionsEntity>();
//			resultlist5.add(userLicSection);
//			scenario.get(5).add(resultlist5);
//			scenario.get(5).add(userLicSection);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations6 = new EditUserInformations() ;
//			editUserInformations6.setContactFirstName("FirstName");
//			editUserInformations6.setContactLastName("LastName");
//			editUserInformations6.setContactOptions("Other");
//			editUserInformations6.setDesignBy("");
//			editUserInformations6.setContractorLicenceState("state");
//			editUserInformations6.setContractorLic(false);
//			editUserInformations6.setContractorLicC10(false);
//			editUserInformations6.setContractorLicB(false);
//			scenario.get(6).add(editUserInformations6);
//			scenario.get(6).add(form);
//			PermitHomeSiteEntityResult permitHomeSite6 = new PermitHomeSiteEntityResult();
//			permitHomeSite6.setState("state");
//			scenario.get(6).add(permitHomeSite6);
//			List<UserLicSectionsEntity> resultlist6 =new ArrayList<UserLicSectionsEntity>();
//			UserLicSectionsEntity userLicSection6 = new UserLicSectionsEntity();
//			userLicSection6.setContractorLicenceState("CA");
//			resultlist6.add(userLicSection6);
//			scenario.get(6).add(resultlist6);
//			scenario.get(6).add(userLicSection6);
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations7 = new EditUserInformations() ;
//			editUserInformations7.setContactFirstName("FirstName");
//			editUserInformations7.setContactLastName("LastName");
//			editUserInformations7.setContactOptions("Other");
//			editUserInformations7.setDesignBy("");
//			editUserInformations7.setContractorLicenceState("state");
//			editUserInformations7.setContractorLic(false);
//			editUserInformations7.setContractorLicC10(false);
//			editUserInformations7.setContractorLicB(false);
//			scenario.get(7).add(editUserInformations7);
//			scenario.get(7).add(form);
//			PermitHomeSiteEntityResult permitHomeSite7 = new PermitHomeSiteEntityResult();
//			permitHomeSite7.setState("state");
//			scenario.get(7).add(permitHomeSite7);
//			List<UserLicSectionsEntity> resultlist7 =new ArrayList<UserLicSectionsEntity>();
//			UserLicSectionsEntity userLicSection7 = new UserLicSectionsEntity();
//			String [] str = {};
//			userLicSection7.setLicTypeCode(str);
//			userLicSection7.setContractorLicenceState("CA");
//			resultlist7.add(userLicSection7);
//			scenario.get(7).add(resultlist7);
//			scenario.get(7).add(userLicSection7);
//			
//			
//			scenario.add(new ArrayList<Object>());
//			EditUserInformations editUserInformations8 = new EditUserInformations() ;
//			editUserInformations8.setContactFirstName("FirstName");
//			editUserInformations8.setContactLastName("LastName");
//			editUserInformations8.setContactOptions("Other");
//			editUserInformations8.setDesignBy("");
//			editUserInformations8.setContractorLicenceState("state");
//			editUserInformations8.setContractorLic(false);
//			editUserInformations8.setContractorLicC10(false);
//			editUserInformations8.setContractorLicB(false);
//			scenario.get(8).add(editUserInformations8);
//			scenario.get(8).add(form);
//			PermitHomeSiteEntityResult permitHomeSite8 = new PermitHomeSiteEntityResult();
//			permitHomeSite8.setState("state");
//			scenario.get(8).add(permitHomeSite8);
//			List<UserLicSectionsEntity> resultlist8 =new ArrayList<UserLicSectionsEntity>();
//			UserLicSectionsEntity userLicSection8 = new UserLicSectionsEntity();
//			String [] LicTypeCode = {null};
//			userLicSection8.setLicTypeCode(LicTypeCode);
//			userLicSection8.setContractorLicenceState("CA");
//			resultlist8.add(userLicSection8);
//			scenario.get(8).add(resultlist8);
//			scenario.get(8).add(userLicSection8);
//			
//			Query mockedQuery = mock(Query.class);
//			when(em.createQuery("SELECT u from UserLicSectionsEntity u WHERE u.authentificationEntity.id = :p1 AND u.contractorLicenceState= :p2")).thenReturn(mockedQuery);
//			when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapContractorInfoFourth ["+i+"]");
//				 when(mockedQuery.getResultList()).thenReturn((List<UserLicSectionsEntity>)scenario.get(i).get(3));
//				 when(mockedQuery.getSingleResult()).thenReturn((UserLicSectionsEntity)scenario.get(i).get(4));
//				 planSetServiceS200.mapContractorInfoFourth((EditUserInformations)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),0);
//			}
//		}
//}
