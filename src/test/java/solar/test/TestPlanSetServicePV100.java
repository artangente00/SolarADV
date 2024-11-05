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
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitDrafterDataEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitSketchEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitTotalSectionEntity;
//import com.PlayGroundAdv.Solar.Entity.PlansetCustomizeSheets;
//import com.PlayGroundAdv.Solar.Entity.UserLicSectionsEntity;
//import com.PlayGroundAdv.Solar.model.EditUserInformations;
//import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
//import com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.PermitServiceEdit;
//import com.PlayGroundAdv.Solar.Services.PlanSetServicePV100;
//import com.PlayGroundAdv.Solar.Services.PlansetServicePV001;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//public class TestPlanSetServicePV100 {
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
//    PlanSetServicePV100 planSetServicePV100 = new PlanSetServicePV100();
//    
//    PdfReader reader = null;
//   	File fileRe = null;
//   	PdfStamper stamper = null;
//   	AcroFields form = null;
//   	PdfReader readerOrigin= null;
//   	
//     @Before
//     public void setupMock() {
//    	 planSetServicePV100 = new PlanSetServicePV100();
//        MockitoAnnotations.initMocks(this);
// 		
// 		try {
// 		reader = new PdfReader(Constants.rapportPlansetFolderUrl +"NEC-PDF/" +"PDF-PV100R"+".pdf" );
// 		fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-PV100R"+".pdf");
// 		stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
// 		form = stamper.getAcroFields();
// 		readerOrigin = new PdfReader( Constants.rapportPlansetFolderUrl +"NEC-PDF/" + "PDF-PV100R.pdf" );
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
//		@Test
//		public void testmapTitleBlock() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);	
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(0);
//			scenario.get(0).add(0);
//			scenario.get(0).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite1 = new PermitHomeSiteEntityResult();
//			scenario.get(1).add(permitHomeSite1);
//			scenario.get(1).add(form);	
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			PermitArrayEntityResultSecond permitArraysEntityResult1= new PermitArrayEntityResultSecond();
//			scenario.get(1).add(permitArraysEntityResult1);
//			scenario.get(1).add(1);
//			scenario.get(1).add(1);
//			scenario.get(1).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//			permitHomeSite2.setSiteAddress("siteAddress");
//			permitHomeSite2.setCity("city");
//			permitHomeSite2.setState("NY");
//			scenario.get(2).add(permitHomeSite2);
//			scenario.get(2).add(form);
//			PermitEntity permitEntity2 = new PermitEntity();
//			scenario.get(2).add(permitEntity2);
//			scenario.get(2).add(null);
//			scenario.get(2).add(null);
//			PermitArrayEntityResultSecond permitArraysEntityResult2= new PermitArrayEntityResultSecond();
//			permitArraysEntityResult2.setDeviceToIncorporate("Neither");
//			scenario.get(2).add(permitArraysEntityResult2);
//			scenario.get(2).add(2);
//			scenario.get(2).add(2);
//			scenario.get(2).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//			permitHomeSite3.setSiteAddress("siteAddress");
//			permitHomeSite3.setCity("city");
//			permitHomeSite3.setState("NY");
//			scenario.get(3).add(permitHomeSite3);
//			scenario.get(3).add(form);
//			PermitEntity permitEntity3 = new PermitEntity();
//			scenario.get(3).add(permitEntity3);
//			scenario.get(3).add(null);
//			scenario.get(3).add(null);
//			PermitArrayEntityResultSecond permitArraysEntityResult3= new PermitArrayEntityResultSecond();
//			permitArraysEntityResult3.setDeviceToIncorporate("Neither");
//			permitArraysEntityResult3.setPvModuleModEl("pvModuleModEl");
//			scenario.get(3).add(permitArraysEntityResult3);
//			scenario.get(3).add(3);
//			scenario.get(3).add(3);
//			ElectricalUtilityEntity electricalCompany3 = new ElectricalUtilityEntity();
//			scenario.get(3).add(electricalCompany3);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//			permitHomeSite4.setSiteAddress("siteAddress");
//			permitHomeSite4.setCity("city");
//			permitHomeSite4.setState("NY");
//			scenario.get(4).add(permitHomeSite4);
//			scenario.get(4).add(form);
//			PermitEntity permitEntity4 = new PermitEntity();
//			scenario.get(4).add(permitEntity4);
//			scenario.get(4).add(null);
//			scenario.get(4).add(null);
//			PermitArrayEntityResultSecond permitArraysEntityResult4= new PermitArrayEntityResultSecond();
//			permitArraysEntityResult4.setDeviceToIncorporate("Neither");
//			permitArraysEntityResult4.setPvModuleModEl("pvModule:ModEl");
//			scenario.get(4).add(permitArraysEntityResult4);
//			scenario.get(4).add(4);
//			scenario.get(4).add(4);
//			ElectricalUtilityEntity electricalCompany4 = new ElectricalUtilityEntity();
//			electricalCompany4.setUtilityCompanyName("Oncor Electric Delivery");
//			scenario.get(4).add(electricalCompany4);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//			permitHomeSite5.setSiteAddress("siteAddress");
//			permitHomeSite5.setCity("city");
//			permitHomeSite5.setState("NY");
//			scenario.get(5).add(permitHomeSite5);
//			scenario.get(5).add(form);
//			PermitEntity permitEntity5 = new PermitEntity();
//			scenario.get(5).add(permitEntity5);
//			PermitCompanyInfoEntityResult permitCompanyInfo5 = new PermitCompanyInfoEntityResult();
//			scenario.get(5).add(permitCompanyInfo5);
//			scenario.get(5).add(null);
//			PermitArrayEntityResultSecond permitArraysEntityResult5= new PermitArrayEntityResultSecond();
//			permitArraysEntityResult5.setDeviceToIncorporate("Neither");
//			permitArraysEntityResult5.setPvModuleModEl("pvModule:ModEl");
//			permitArraysEntityResult5.setInverterModel("InverterModel");
//			scenario.get(5).add(permitArraysEntityResult5);
//			scenario.get(5).add(5);
//			scenario.get(5).add(5);
//			ElectricalUtilityEntity electricalCompany5 = new ElectricalUtilityEntity();
//			electricalCompany5.setUtilityCompanyName("Oncor Electric Delivery");
//			scenario.get(5).add(electricalCompany5);
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapPermitHomeInfo ["+i+"]");
//				 planSetServicePV100.mapTitleBlock((PermitHomeSiteEntityResult)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitEntity)scenario.get(i).get(2),(PermitCompanyInfoEntityResult)scenario.get(i).get(3),(String)scenario.get(i).get(4),(PermitArrayEntityResultSecond)scenario.get(i).get(5),(int)scenario.get(i).get(6),(int)scenario.get(i).get(7),(ElectricalUtilityEntity)scenario.get(i).get(8),0);
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
//	    	planSetServicePV100.mapContractorInfo(editUserInformations2,form,permitHomeSite2,0);
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
//	    	planSetServicePV100.mapContractorInfo(editUserInformations2,form,permitHomeSite2,0);
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
//				 planSetServicePV100.mapContractorInfo((EditUserInformations)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),0);
//			}
//		}
//
//		@Test
//		public void testmapPermitSketchEntity() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			List <PermitSketchEntity> listPermitSketchEntity = new ArrayList<PermitSketchEntity>();
//			scenario.get(1).add(listPermitSketchEntity);
//			scenario.get(1).add(form);
//			scenario.get(1).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			List <PermitSketchEntity> listPermitSketchEntity2 = new ArrayList<PermitSketchEntity>();
//			PermitSketchEntity permitSketchEntity21 = new PermitSketchEntity();
//			listPermitSketchEntity2.add(permitSketchEntity21);
//			PermitSketchEntity permitSketchEntity22 = new PermitSketchEntity();
//			permitSketchEntity22.setRoofPitch("roofPitch");
//			listPermitSketchEntity2.add(permitSketchEntity22);
//			PermitSketchEntity permitSketchEntity23 = new PermitSketchEntity();
//			permitSketchEntity23.setAzimuth("azimuth");
//			listPermitSketchEntity2.add(permitSketchEntity23);
//			PermitSketchEntity permitSketchEntity24 = new PermitSketchEntity();
//			permitSketchEntity24.setAzimuth("azimuth");
//			permitSketchEntity24.setRoofPitch("roofPitch");
//			listPermitSketchEntity2.add(permitSketchEntity24);
//			scenario.get(2).add(listPermitSketchEntity2);
//			scenario.get(2).add(form);
//			scenario.get(2).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			List <PermitSketchEntity> listPermitSketchEntity3 = new ArrayList<PermitSketchEntity>();
//			PermitSketchEntity permitSketchEntity31 = new PermitSketchEntity();
//			listPermitSketchEntity3.add(permitSketchEntity31);
//			PermitSketchEntity permitSketchEntity32 = new PermitSketchEntity();
//			permitSketchEntity32.setRoofPitch("roofPitch");
//			listPermitSketchEntity3.add(permitSketchEntity32);
//			PermitSketchEntity permitSketchEntity33 = new PermitSketchEntity();
//			permitSketchEntity33.setAzimuth("azimuth");
//			listPermitSketchEntity2.add(permitSketchEntity33);
//			PermitSketchEntity permitSketchEntity34 = new PermitSketchEntity();
//			permitSketchEntity34.setAzimuth("azimuth");
//			permitSketchEntity34.setRoofPitch("roofPitch");
//			listPermitSketchEntity2.add(permitSketchEntity34);
//			scenario.get(3).add(listPermitSketchEntity3);
//			scenario.get(3).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult3 = new PermitArrayEntityResultSecond();
//			scenario.get(3).add(permitArraysEntityResult3);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//			  System.out.println("testmapPermitSketchEntity ["+i+"]");
//			  planSetServicePV100.mapPermitSketchEntity((List <PermitSketchEntity>)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitArrayEntityResultSecond)scenario.get(i).get(2),0);
//			}
//		}
//		
//		@Test
//		public void testmapDrafterInfo() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitDrafterDataEntity permitDrafterDatanfo1 = new PermitDrafterDataEntity();
//			scenario.get(1).add(permitDrafterDatanfo1);
//			scenario.get(1).add(form);
//			scenario.get(1).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitDrafterDataEntity permitDrafterDatanfo2 = new PermitDrafterDataEntity();
//			permitDrafterDatanfo2.setTotalRoofSquareFootage(0);
//			permitDrafterDatanfo2.setTotalArraySectionCount(1);
//			scenario.get(2).add(permitDrafterDatanfo2);
//			scenario.get(2).add(form);
//			scenario.get(2).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitDrafterDataEntity permitDrafterDatanfo3 = new PermitDrafterDataEntity();
//			permitDrafterDatanfo3.setTotalRoofSquareFootage(0);
//			permitDrafterDatanfo3.setTotalArraySectionCount(1);
//			scenario.get(3).add(permitDrafterDatanfo3);
//			scenario.get(3).add(form);
//			List<PermitTotalSectionEntity> listPermitSectionTotalEntity3 = new ArrayList<PermitTotalSectionEntity>();
//			PermitTotalSectionEntity permitTotalSectionEntity31 = new PermitTotalSectionEntity();
//			PermitTotalSectionEntity permitTotalSectionEntity32 = new PermitTotalSectionEntity();
//			permitTotalSectionEntity32.setSquareFootage(3);
//			listPermitSectionTotalEntity3.add(null);
//			listPermitSectionTotalEntity3.add(permitTotalSectionEntity31);
//			listPermitSectionTotalEntity3.add(permitTotalSectionEntity32);
//			scenario.get(3).add(listPermitSectionTotalEntity3);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//				  System.out.println("testmapDrafterInfo ["+i+"]");
//				  planSetServicePV100.mapDrafterInfo((PermitDrafterDataEntity)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(List<PermitTotalSectionEntity>)scenario.get(i).get(2),0);
//			}
//		}
//		
//		@Test
//		public void testmapExistingRoof() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(1).add(form);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo1 = new PermitProjectSiteInfoEntityTwo();
//			scenario.get(1).add(permitProjectSiteInfo1);
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(2).add(form);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo2.setRoofMaterialType("RoofMaterialType");
//			scenario.get(2).add(permitProjectSiteInfo2);
//			
//			Query mockedQuery = mock(Query.class);
//			when(em.createQuery("SELECT i from RoofMaterialType i Where i.id = :p1 " )).thenReturn(mockedQuery);
//			when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//			for(int i = 0; i < scenario.size(); i++) {
//			  System.out.println("testmapExistingRoof ["+i+"]");
//			  planSetServicePV100.mapExistingRoof((AcroFields)scenario.get(i).get(0),(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(1),0);
//			}
//		}
//		
//		@Test
//		public void testmapScale() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitDrafterDataEntity permitDrafterDatanfo1 = new PermitDrafterDataEntity();
//			scenario.get(1).add(permitDrafterDatanfo1);
//			scenario.get(1).add(form);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitDrafterDataEntity permitDrafterDatanfo2= new PermitDrafterDataEntity();
//			permitDrafterDatanfo2.setScale("null");
//			scenario.get(2).add(permitDrafterDatanfo2);
//			scenario.get(2).add(form);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitDrafterDataEntity permitDrafterDatanfo3= new PermitDrafterDataEntity();
//			permitDrafterDatanfo3.setScale("5");
//			scenario.get(3).add(permitDrafterDatanfo3);
//			scenario.get(3).add(form);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//				  System.out.println("testmapScale ["+i+"]");
//				  planSetServicePV100.mapScale((PermitDrafterDataEntity)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),0);
//			}
//		}
//		
//}
