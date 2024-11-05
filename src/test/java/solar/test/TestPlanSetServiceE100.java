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
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.Inverters;
//import com.PlayGroundAdv.Solar.Entity.LeasePPAMeter;
//import com.PlayGroundAdv.Solar.Entity.PermitDrafterDataEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PlansetCustomizeSheets;
//import com.PlayGroundAdv.Solar.Entity.UserLicSectionsEntity;
//import com.PlayGroundAdv.Solar.model.DCCombinerDisconnectRequest;
//import com.PlayGroundAdv.Solar.model.EditUserInformations;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
//import com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.PermitServiceEdit;
//import com.PlayGroundAdv.Solar.Services.PlanSetServiceE100;
//import com.PlayGroundAdv.Solar.Services.PlansetServiceE003String;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//public class TestPlanSetServiceE100 {
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
//    PlanSetServiceE100 planSetServiceE100 = new PlanSetServiceE100();
//    
//    PdfReader reader = null;
//   	File fileRe = null;
//   	PdfStamper stamper = null;
//   	AcroFields form = null;
//   	PdfReader readerOrigin= null;
//   	
//     @Before
//     public void setupMock() {
//    	 planSetServiceE100 = new PlanSetServiceE100();
//        MockitoAnnotations.initMocks(this);
// 		
// 		try {
// 		reader = new PdfReader(Constants.rapportPlansetFolderUrl +"NEC-PDF/" +"PDF-E100-MICRO"+".pdf" );
// 		fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E100-MICRO"+".pdf");
// 		stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
// 		form = stamper.getAcroFields();
// 		readerOrigin = new PdfReader( Constants.rapportPlansetFolderUrl +"NEC-PDF/" + "PDF-E100-MICRO.pdf" );
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
//		@Test
//		public void testmapTitleBlock() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit1 = new PermitEntity();
//			PermitHomeSiteEntityResult permitHomeSite1 =new PermitHomeSiteEntityResult();
//			scenario.get(0).add(permitHomeSite1);
//			scenario.get(0).add(null);
//			scenario.get(0).add(0);
//			scenario.get(0).add(permit1);
//			scenario.get(0).add(form);
//			scenario.get(0).add("");
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(0);
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
//			scenario.get(1).add(0);
//			scenario.get(1).add(permit2);
//			scenario.get(1).add(form);
//			scenario.get(1).add("");
//			scenario.get(1).add(null);
//			ElectricalUtilityEntity electricalCompany2 = new ElectricalUtilityEntity();
//			scenario.get(1).add(electricalCompany2);
//			scenario.get(1).add(0);
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
//			scenario.get(2).add(0);
//			scenario.get(2).add(permit3);
//			scenario.get(2).add(form);
//			scenario.get(2).add("");
//			scenario.get(2).add(null);
//			ElectricalUtilityEntity electricalCompany3 = new ElectricalUtilityEntity();
//			scenario.get(2).add(electricalCompany3);
//			scenario.get(2).add(0);
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
//			scenario.get(3).add(0);
//			scenario.get(3).add(permit4);
//			scenario.get(3).add(form);
//			scenario.get(3).add("");
//			PermitCompanyInfoEntityResult permitCompanyInfo4 = new PermitCompanyInfoEntityResult();
//			scenario.get(3).add(permitCompanyInfo4);
//			ElectricalUtilityEntity electricalCompany4 = new ElectricalUtilityEntity();
//			electricalCompany4.setUtilityCompanyName("Oncor Electric Delivery");
//			scenario.get(3).add(electricalCompany4);
//			scenario.get(3).add(0);
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
//			scenario.get(4).add(0);
//			scenario.get(4).add(permit5);
//			scenario.get(4).add(form);
//			scenario.get(4).add("");
//			PermitCompanyInfoEntityResult permitCompanyInfo5 = new PermitCompanyInfoEntityResult();
//			scenario.get(4).add(permitCompanyInfo5);
//			ElectricalUtilityEntity electricalCompany5 = new ElectricalUtilityEntity();
//			scenario.get(4).add(electricalCompany5);
//			scenario.get(4).add(0);
//			for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("testmapTitleBlock ["+i+"]");
//			 planSetServiceE100.mapTitleBlock((PermitHomeSiteEntityResult)scenario.get(i).get(0),(PermitArrayEntityResultSecond)scenario.get(i).get(1),(int)scenario.get(i).get(2),(PermitEntity)scenario.get(i).get(3),(AcroFields)scenario.get(i).get(4),(String)scenario.get(i).get(5),(PermitCompanyInfoEntityResult)scenario.get(i).get(6),(ElectricalUtilityEntity)scenario.get(i).get(7),(int)scenario.get(i).get(8),0);
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
//	    	planSetServiceE100.mapContractorInfo(editUserInformations2,form,permitHomeSite2,0);
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
//	    	planSetServiceE100.mapContractorInfo(editUserInformations2,form,permitHomeSite2,0);
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
//				 planSetServiceE100.mapContractorInfo((EditUserInformations)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),0);
//			}
//		}
//		
//		@Test
//		public void testmapProjectInfo() {
//			List <Integer> componentListQTY=new ArrayList<Integer>();
//			componentListQTY.add(0); // 0
//			componentListQTY.add(0); // 1
//			componentListQTY.add(0); // 2
//			componentListQTY.add(0); // 3
//			componentListQTY.add(0); // 4
//			componentListQTY.add(0); // 5
//			componentListQTY.add(0); // 6
//			componentListQTY.add(0); // 7
//			componentListQTY.add(0); // 8
//			componentListQTY.add(0); // 9
//			componentListQTY.add(0); // 10
//			componentListQTY.add(0); // 11
//			componentListQTY.add(0); // 12
//			componentListQTY.add(0); // 13
//			componentListQTY.add(0); // 14
//			componentListQTY.add(0); // 15
//			componentListQTY.add(0); // 16
//			componentListQTY.add(0); // 17
//			componentListQTY.add(0); // 18
//			componentListQTY.add(0); // 19
//			componentListQTY.add(0); // 20
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.add(new ArrayList<Object>());
//			scenario.get(1).add(form);
//			scenario.get(1).add(null);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo1 = new PermitProjectSiteInfoEntityTwo();
//			scenario.get(1).add(permitProjectSiteInfo1);
//			scenario.get(1).add(null);
//			scenario.get(1).add(componentListQTY);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			PermitDrafterDataEntity permitDrafterDatanfo1 = new PermitDrafterDataEntity();
//			scenario.get(1).add(permitDrafterDatanfo1);
//			scenario.add(new ArrayList<Object>());
//			scenario.get(2).add(form);
//			Inverters inverterInfo2 = new Inverters();
//			scenario.get(2).add(inverterInfo2);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2= new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo2.setProposedSubPanelModel("propModel");
//			permitProjectSiteInfo2.setUsedRevenue(true);
//			permitProjectSiteInfo2.setMainPanelUpgrade(false);
//			scenario.get(2).add(permitProjectSiteInfo2);
//			DCCombinerDisconnectRequest dcCombinerDisconnect2= new DCCombinerDisconnectRequest();
//			scenario.get(2).add(dcCombinerDisconnect2);
//			scenario.get(2).add(componentListQTY);
//			ACDisconnect acDisconnect2= new ACDisconnect();
//			scenario.get(2).add(acDisconnect2);
//			LeasePPAMeter leasePPAMeter2= new LeasePPAMeter();
//			scenario.get(2).add(leasePPAMeter2);
//			PermitDrafterDataEntity permitDrafterDatanfo2 = new PermitDrafterDataEntity();
//			permitDrafterDatanfo2.setScaleelectricalLayout("xx");
//			scenario.get(2).add(permitDrafterDatanfo2);
//			scenario.add(new ArrayList<Object>());
//			scenario.get(3).add(form);
//			Inverters inverterInfo3 = new Inverters();
//			inverterInfo3.setMake("");
//			scenario.get(3).add(inverterInfo3);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3= new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo3.setProposedSubPanelModel("propModel");
//			permitProjectSiteInfo3.setUsedRevenue(true);
//			permitProjectSiteInfo3.setMainPanelUpgrade(null);
//			permitProjectSiteInfo3.setExistingMainPanelManufac("");
//			scenario.get(3).add(permitProjectSiteInfo3);
//			DCCombinerDisconnectRequest dcCombinerDisconnect3= new DCCombinerDisconnectRequest();
//			dcCombinerDisconnect3.setTypeDc("");
//			scenario.get(3).add(dcCombinerDisconnect3);
//			scenario.get(3).add(componentListQTY);
//			ACDisconnect acDisconnect3= new ACDisconnect();
//			scenario.get(3).add(acDisconnect3);
//			LeasePPAMeter leasePPAMeter3= new LeasePPAMeter();
//			scenario.get(3).add(leasePPAMeter3);
//			PermitDrafterDataEntity permitDrafterDatanfo3 = new PermitDrafterDataEntity();
//			permitDrafterDatanfo3.setScaleelectricalLayout("xx");
//			scenario.get(3).add(permitDrafterDatanfo3);
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapProjectInfo ["+i+"]");
//				 planSetServiceE100.mapProjectInfo((AcroFields)scenario.get(i).get(0),(Inverters)scenario.get(i).get(1),(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(2),(DCCombinerDisconnectRequest)scenario.get(i).get(3),(List <Integer>)scenario.get(i).get(4),(ACDisconnect)scenario.get(i).get(5),(LeasePPAMeter)scenario.get(i).get(6),(PermitDrafterDataEntity)scenario.get(i).get(7),0);
//			}
//		}
//		
//		
//		@Test
//		public void testmapTitleBlockMicro() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			PermitEntity permit1 = new PermitEntity();
//			PermitHomeSiteEntityResult permitHomeSite1 =new PermitHomeSiteEntityResult();
//			scenario.get(0).add(permitHomeSite1);
//			scenario.get(0).add(null);
//			scenario.get(0).add(0);
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
//			scenario.get(1).add(0);
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
//			scenario.get(2).add(0);
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
//			scenario.get(3).add(0);
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
//			scenario.get(4).add(0);
//			scenario.get(4).add(permit5);
//			scenario.get(4).add(form);
//			scenario.get(4).add("");
//			PermitCompanyInfoEntityResult permitCompanyInfo5 = new PermitCompanyInfoEntityResult();
//			scenario.get(4).add(permitCompanyInfo5);
//			ElectricalUtilityEntity electricalCompany5 = new ElectricalUtilityEntity();
//			scenario.get(4).add(electricalCompany5);
//			for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("testmapTitleBlockMicro ["+i+"]");
//			 planSetServiceE100.mapTitleBlockMicro((PermitHomeSiteEntityResult)scenario.get(i).get(0),(PermitArrayEntityResultSecond)scenario.get(i).get(1),(int)scenario.get(i).get(2),(PermitEntity)scenario.get(i).get(3),(AcroFields)scenario.get(i).get(4),(String)scenario.get(i).get(5),(PermitCompanyInfoEntityResult)scenario.get(i).get(6),(ElectricalUtilityEntity)scenario.get(i).get(7), 0);
//			}
//		}
//		
//		@Test
//		public void testgetModuleQty() {
//			 PermitArrayEntityResultSecond scenario1=new PermitArrayEntityResultSecond() ;
//			 PermitArrayEntityResultSecond scenario2=new PermitArrayEntityResultSecond() ;
//			 scenario2.setNumberModulesACCircuitOne("16");
//			 PermitArrayEntityResultSecond scenario3=new PermitArrayEntityResultSecond() ;
//			 scenario3.setNumberModulesACCircuitOne("16");
//			 scenario3.setNumberModulesACCircuitTwo("16");
//			 PermitArrayEntityResultSecond scenario4=new PermitArrayEntityResultSecond() ;
//			 scenario4.setNumberModulesACCircuitOne("16");
//			 scenario4.setNumberModulesACCircuitTwo("16");
//			 PermitArrayEntityResultSecond scenario5=new PermitArrayEntityResultSecond() ;
//			 scenario5.setNumberModulesACCircuitOne("16");
//			 scenario5.setNumberModulesACCircuitTwo("16");
//			 scenario5.setNumberModulesACCircuitThree("16");
//			 PermitArrayEntityResultSecond scenario6=new PermitArrayEntityResultSecond() ;
//			 scenario6.setNumberModulesACCircuitOne("16");
//			 scenario6.setNumberModulesACCircuitTwo("16");
//			 scenario6.setNumberModulesACCircuitThree("16");
//			 scenario6.setNumberModulesACCircuitFour("16");
//			 ArrayList<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//		     scenario.add(null);
//			 scenario.add(scenario1);
//			 scenario.add(scenario2);
//			 scenario.add(scenario3);
//			 scenario.add(scenario4);
//			 scenario.add(scenario5);
//			 scenario.add(scenario6);
//			 for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testgetModuleQty ["+i+"]");
//				 planSetServiceE100.getModuleQty(scenario.get(i));
//			 }
//		}
//		
//		@Test
//		public void testmapProjectInfoMicro() {
//			List <Integer> componentListQTY=new ArrayList<Integer>();
//			componentListQTY.add(0); // 0
//			componentListQTY.add(0); // 1
//			componentListQTY.add(0); // 2
//			componentListQTY.add(0); // 3
//			componentListQTY.add(0); // 4
//			componentListQTY.add(0); // 5
//			componentListQTY.add(0); // 6
//			componentListQTY.add(0); // 7
//			componentListQTY.add(0); // 8
//			componentListQTY.add(0); // 9
//			componentListQTY.add(0); // 10
//			componentListQTY.add(0); // 11
//			componentListQTY.add(0); // 12
//			componentListQTY.add(0); // 13
//			componentListQTY.add(0); // 14
//			componentListQTY.add(0); // 15
//			componentListQTY.add(0); // 16
//			componentListQTY.add(0); // 17
//			componentListQTY.add(0); // 18
//			componentListQTY.add(0); // 19
//			componentListQTY.add(0); // 20
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(componentListQTY);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.add(new ArrayList<Object>());
//			scenario.get(1).add(form);
//			scenario.get(1).add(null);
//			scenario.get(1).add(componentListQTY);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo1 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo1.setACDisconnectSwitchModel("");
//			scenario.get(1).add(permitProjectSiteInfo1);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			PermitDrafterDataEntity permitDrafterDatanfo1 = new PermitDrafterDataEntity();
//			scenario.get(1).add(permitDrafterDatanfo1);
//			scenario.add(new ArrayList<Object>());
//			scenario.get(2).add(form);
//			ACDisconnect acDisconnect2= new ACDisconnect();
//			scenario.get(2).add(acDisconnect2);
//			scenario.get(2).add(componentListQTY);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2= new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo2.setProposedSubPanelModel("propModel");
//			permitProjectSiteInfo2.setUsedRevenue(true);
//			permitProjectSiteInfo2.setMainPanelUpgrade(false);
//			scenario.get(2).add(permitProjectSiteInfo2);
//			LeasePPAMeter leasePPAMeter2= new LeasePPAMeter();
//			scenario.get(2).add(leasePPAMeter2);
//			PermitDrafterDataEntity permitDrafterDatanfo2 = new PermitDrafterDataEntity();
//			permitDrafterDatanfo2.setScaleelectricalLayout("xx");
//			scenario.get(2).add(permitDrafterDatanfo2);
//			scenario.add(new ArrayList<Object>());
//			scenario.get(3).add(form);
//			ACDisconnect acDisconnect3= new ACDisconnect();
//			scenario.get(3).add(acDisconnect3);
//			scenario.get(3).add(componentListQTY);
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3= new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo3.setProposedSubPanelModel("propModel");
//			permitProjectSiteInfo3.setUsedRevenue(true);
//			permitProjectSiteInfo3.setMainPanelUpgrade(null);
//			permitProjectSiteInfo3.setExistingMainPanelManufac("");
//			scenario.get(3).add(permitProjectSiteInfo3);
//			LeasePPAMeter leasePPAMeter3= new LeasePPAMeter();
//			scenario.get(3).add(leasePPAMeter3);
//			PermitDrafterDataEntity permitDrafterDatanfo3 = new PermitDrafterDataEntity();
//			permitDrafterDatanfo3.setScaleelectricalLayout("xx");
//			scenario.get(3).add(permitDrafterDatanfo3);
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapProjectInfoMicro ["+i+"]");
//				 planSetServiceE100.mapProjectInfoMicro((AcroFields)scenario.get(i).get(0),(ACDisconnect)scenario.get(i).get(1),(List <Integer>)scenario.get(i).get(2),(PermitProjectSiteInfoEntityTwo)scenario.get(i).get(3),(LeasePPAMeter)scenario.get(i).get(4),(PermitDrafterDataEntity)scenario.get(i).get(5), 0);
//			}
//		}
//}
