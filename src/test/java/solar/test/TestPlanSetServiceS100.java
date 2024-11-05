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
//import com.PlayGroundAdv.Solar.Entity.DCOptimizerEntity;
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.Inverters;
//import com.PlayGroundAdv.Solar.Entity.PermitDrafterDataEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PlansetCustomizeSheets;
//import com.PlayGroundAdv.Solar.Entity.RoofMaterialType;
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
//import com.PlayGroundAdv.Solar.Services.PlanSetServiceS100;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//public class TestPlanSetServiceS100 {
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
//    PlanSetServiceS100 planSetServiceS100 = new PlanSetServiceS100();
//    
//    PdfReader reader = null;
//   	File fileRe = null;
//   	PdfStamper stamper = null;
//   	AcroFields form = null;
//   	PdfReader readerOrigin= null;
//   	
//     @Before
//     public void setupMock() {
//    	 planSetServiceS100 = new PlanSetServiceS100();
//        MockitoAnnotations.initMocks(this);
// 		
// 		try {
// 		reader = new PdfReader(Constants.rapportPlansetFolderUrl +"NEC-PDF/" +"PDF-S100"+".pdf" );
// 		fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-S100"+".pdf");
// 		stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
// 		form = stamper.getAcroFields();
// 		readerOrigin = new PdfReader( Constants.rapportPlansetFolderUrl +"NEC-PDF/" + "PDF-S100.pdf" );
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
//		public void testmapModuleInfo() {
//			List <Integer> componentListQTY=new ArrayList<Integer>();
//			componentListQTY.add(0); // 0
//			componentListQTY.add(1); // 1
//			componentListQTY.add(0); // 2
//			componentListQTY.add(1); // 3
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.add(new ArrayList<Object>());
//			Cmodulev2 moduleInfo1 = new Cmodulev2();
//			scenario.get(1).add(moduleInfo1);
//			scenario.get(1).add(componentListQTY);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.add(new ArrayList<Object>());
//			Cmodulev2 moduleInfo2 = new Cmodulev2();
//			moduleInfo2.setWidth("1,2");
//			moduleInfo2.setLength("1,2");
//			moduleInfo2.setWeight("1,2");
//			scenario.get(2).add(moduleInfo2);
//			scenario.get(2).add(componentListQTY);
//			scenario.get(2).add(null);
//			scenario.get(2).add(null);
//			scenario.add(new ArrayList<Object>());
//			Cmodulev2 moduleInfo3 = new Cmodulev2();
//			moduleInfo3.setWidth("1,2");
//			moduleInfo3.setLength("1,2");
//			moduleInfo3.setWeight("1,2");
//			scenario.get(3).add(moduleInfo3);
//			scenario.get(3).add(componentListQTY);
//			Inverters microInverterInfo = new Inverters();
//			scenario.get(3).add(microInverterInfo);
//			scenario.get(3).add(null);
//			scenario.add(new ArrayList<Object>());
//			Cmodulev2 moduleInfo4 = new Cmodulev2();
//			moduleInfo4.setWidth("1,2");
//			moduleInfo4.setLength("1,2");
//			moduleInfo4.setWeight("1,2");
//			scenario.get(4).add(moduleInfo4);
//			scenario.get(4).add(componentListQTY);
//			Inverters microInverterInfo4 = new Inverters();
//			microInverterInfo4.setWeight("12,2");
//			scenario.get(4).add(microInverterInfo4);
//			DCOptimizerEntity dcOptimizer4 = new DCOptimizerEntity();
//			dcOptimizer4.setWeight("1,2");
//			scenario.get(4).add(dcOptimizer4);
//			scenario.add(new ArrayList<Object>());
//			Cmodulev2 moduleInfo5= new Cmodulev2();
//			moduleInfo5.setWidth("1,2");
//			moduleInfo5.setLength("1,2");
//			moduleInfo5.setWeight("1,2");
//			scenario.get(5).add(moduleInfo5);
//			scenario.get(5).add(componentListQTY);
//			Inverters microInverterInfo5 = new Inverters();
//			microInverterInfo5.setWeight("12,2");
//			scenario.get(5).add(microInverterInfo5);
//			DCOptimizerEntity dcOptimizer5 = new DCOptimizerEntity();
//			dcOptimizer5.setWeight("1,2");
//			scenario.get(5).add(dcOptimizer5);
//			for(int i = 0; i < scenario.size(); i++) {
//			  System.out.println("testmapModuleInfo ["+i+"]");
//			  planSetServiceS100.mapModuleInfo((Cmodulev2)scenario.get(i).get(0),(List<Integer>)scenario.get(i).get(1),(Inverters)scenario.get(i).get(2),(DCOptimizerEntity)scenario.get(i).get(3));
//			}
//		}
//
//		@Test
//		public void testmapHomeInfo() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(0);
//			scenario.get(0).add(0);
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite1 = new PermitHomeSiteEntityResult();
//			scenario.get(1).add(permitHomeSite1);
//			scenario.get(1).add(form);
//			PermitEntity permitEntity1 = new PermitEntity();
//			scenario.get(1).add(permitEntity1);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			PermitArrayEntityResultSecond permitArraysEntityResult1= new PermitArrayEntityResultSecond();
//			scenario.get(1).add(permitArraysEntityResult1);
//			scenario.get(1).add(0);
//			scenario.get(1).add(0);
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//			permitHomeSite2.setSiteAddress("siteAddress");
//			permitHomeSite2.setCity("city");
//			permitHomeSite2.setState("NC");
//			scenario.get(2).add(permitHomeSite2);
//			scenario.get(2).add(form);
//			PermitEntity permitEntity2 = new PermitEntity();
//			scenario.get(2).add(permitEntity2);
//			scenario.get(2).add(null);
//			scenario.get(2).add(null);
//			scenario.get(2).add(null);
//			PermitArrayEntityResultSecond permitArraysEntityResult2= new PermitArrayEntityResultSecond();
//			permitArraysEntityResult2.setDeviceToIncorporate("Neither");
//			scenario.get(2).add(permitArraysEntityResult2);
//			scenario.get(2).add(0);
//			scenario.get(2).add(0);
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//			permitHomeSite3.setSiteAddress("siteAddress");
//			permitHomeSite3.setCity("city");
//			permitHomeSite3.setState("NC");
//			scenario.get(3).add(permitHomeSite3);
//			scenario.get(3).add(form);
//			PermitEntity permitEntity3 = new PermitEntity();
//			scenario.get(3).add(permitEntity3);
//			scenario.get(3).add(null);
//			scenario.get(3).add(null);
//			ElectricalUtilityEntity electricalCompany3 = new ElectricalUtilityEntity();
//			electricalCompany3.setUtilityCompanyName("Oncor Electric Delivery");
//			scenario.get(3).add(electricalCompany3);
//			PermitArrayEntityResultSecond permitArraysEntityResult3= new PermitArrayEntityResultSecond();
//			permitArraysEntityResult3.setDeviceToIncorporate("Neither");
//			permitArraysEntityResult3.setPvModuleModEl("PvModuleModEl");
//			scenario.get(3).add(permitArraysEntityResult3);
//			scenario.get(3).add(0);
//			scenario.get(3).add(0);
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//			permitHomeSite4.setSiteAddress("siteAddress");
//			permitHomeSite4.setCity("city");
//			permitHomeSite4.setState("NC");
//			scenario.get(4).add(permitHomeSite4);
//			scenario.get(4).add(form);
//			PermitEntity permitEntity4 = new PermitEntity();
//			scenario.get(4).add(permitEntity4);
//			scenario.get(4).add(null);
//			PermitCompanyInfoEntityResult permitCompanyInfo4 = new PermitCompanyInfoEntityResult();
//			scenario.get(4).add(permitCompanyInfo4);
//			ElectricalUtilityEntity electricalCompany4 = new ElectricalUtilityEntity();
//			electricalCompany4.setUtilityCompanyName("Oncor Electric Delivery");
//			scenario.get(4).add(electricalCompany4);
//			PermitArrayEntityResultSecond permitArraysEntityResult4= new PermitArrayEntityResultSecond();
//			permitArraysEntityResult4.setDeviceToIncorporate("Neither");
//			permitArraysEntityResult4.setPvModuleModEl("PvModuleModEl:manufacturer");
//			scenario.get(4).add(permitArraysEntityResult4);
//			scenario.get(4).add(0);
//			scenario.get(4).add(0);
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//			permitHomeSite5.setSiteAddress("siteAddress");
//			permitHomeSite5.setCity("city");
//			permitHomeSite5.setState("NC");
//			permitHomeSite5.setUtilityCompanyName("Other");
//			permitHomeSite5.setUtilityCompanyNameOther("Oncor Electric Delivery");
//			scenario.get(5).add(permitHomeSite5);
//			scenario.get(5).add(form);
//			PermitEntity permitEntity5 = new PermitEntity();
//			scenario.get(5).add(permitEntity5);
//			scenario.get(5).add(null);
//			scenario.get(5).add(null);
//			ElectricalUtilityEntity electricalCompany5 = new ElectricalUtilityEntity();
//			scenario.get(5).add(electricalCompany5);
//			PermitArrayEntityResultSecond permitArraysEntityResult5= new PermitArrayEntityResultSecond();
//			permitArraysEntityResult5.setDeviceToIncorporate("Neither");
//			permitArraysEntityResult5.setPvModuleModEl("PvModuleModEl");
//			permitArraysEntityResult5.setInverterModel("inverterModel");
//			scenario.get(5).add(permitArraysEntityResult5);
//			scenario.get(5).add(0);
//			scenario.get(5).add(0);
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite6 = new PermitHomeSiteEntityResult();
//			permitHomeSite6.setSiteAddress("siteAddress");
//			permitHomeSite6.setCity("city");
//			permitHomeSite6.setState("NC");
//			permitHomeSite6.setUtilityCompanyName("Other");
//			permitHomeSite6.setUtilityCompanyNameOther("Oncor Electric Delivery");
//			scenario.get(6).add(permitHomeSite6);
//			scenario.get(6).add(form);
//			PermitEntity permitEntity6 = new PermitEntity();
//			scenario.get(6).add(permitEntity6);
//			scenario.get(6).add(null);
//			PermitCompanyInfoEntityResult permitCompanyInfo6 = new PermitCompanyInfoEntityResult();
//			scenario.get(6).add(permitCompanyInfo6);
//			ElectricalUtilityEntity electricalCompany6 = new ElectricalUtilityEntity();
//			electricalCompany6.setUtilityCompanyName("Oncor Electric Delivery");
//			scenario.get(6).add(electricalCompany6);
//			PermitArrayEntityResultSecond permitArraysEntityResult6= new PermitArrayEntityResultSecond();
//			permitArraysEntityResult6.setDeviceToIncorporate("Micro Inverter");
//			scenario.get(6).add(permitArraysEntityResult6);
//			scenario.get(6).add(0);
//			scenario.get(6).add(0);
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite7 = new PermitHomeSiteEntityResult();
//			permitHomeSite7.setSiteAddress("siteAddress");
//			permitHomeSite7.setCity("city");
//			permitHomeSite7.setState("NC");
//			permitHomeSite7.setUtilityCompanyName("Other");
//			permitHomeSite7.setUtilityCompanyNameOther("Oncor Electric Delivery");
//			scenario.get(7).add(permitHomeSite7);
//			scenario.get(7).add(form);
//			PermitEntity permitEntity7 = new PermitEntity();
//			scenario.get(7).add(permitEntity7);
//			scenario.get(7).add(null);
//			PermitCompanyInfoEntityResult permitCompanyInfo7 = new PermitCompanyInfoEntityResult();
//			scenario.get(7).add(permitCompanyInfo7);
//			ElectricalUtilityEntity electricalCompany7 = new ElectricalUtilityEntity();
//			electricalCompany7.setUtilityCompanyName("Oncor Electric Delivery");
//			scenario.get(7).add(electricalCompany7);
//			PermitArrayEntityResultSecond permitArraysEntityResult7= new PermitArrayEntityResultSecond();
//			permitArraysEntityResult7.setDeviceToIncorporate("Micro Inverter");
//			permitArraysEntityResult7.setPvModuleModEl("pvModuleModEl");
//			scenario.get(7).add(permitArraysEntityResult7);
//			scenario.get(7).add(0);
//			scenario.get(7).add(0);
//			for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("testmapHomeInfo ["+i+"]");
//			 planSetServiceS100.mapHomeInfo((PermitHomeSiteEntityResult)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitEntity)scenario.get(i).get(2),(String)scenario.get(i).get(3),(PermitCompanyInfoEntityResult)scenario.get(i).get(4),(ElectricalUtilityEntity)scenario.get(i).get(5),(PermitArrayEntityResultSecond)scenario.get(i).get(6),(int)scenario.get(i).get(7),(int)scenario.get(i).get(8),0);
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
//	    	planSetServiceS100.mapContractorInfo(editUserInformations2,form,permitHomeSite2,0);
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
//	    	planSetServiceS100.mapContractorInfo(editUserInformations2,form,permitHomeSite2,0);
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
//				 planSetServiceS100.mapContractorInfo((EditUserInformations)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),0);
//			}
//		}
//		
//		@Test
//		public void testmapRoofStructural() {
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
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo1 = new PermitProjectSiteInfoEntityTwo();
//			scenario.get(1).add(permitProjectSiteInfo1);
//			PermitHomeSiteEntityResult permitHomeSite1 = new PermitHomeSiteEntityResult();
//			scenario.get(1).add(permitHomeSite1);
//			scenario.get(1).add(form);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo2 = new PermitProjectSiteInfoEntityTwo();
//			scenario.get(2).add(permitProjectSiteInfo2);
//			PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//			scenario.get(2).add(permitHomeSite2);
//			scenario.get(2).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//			scenario.get(2).add(permitArraysEntityResult2);
//			scenario.get(2).add(null);
//			scenario.get(2).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo3 = new PermitProjectSiteInfoEntityTwo();
//			scenario.get(3).add(permitProjectSiteInfo3);
//			PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//			scenario.get(3).add(permitHomeSite3);
//			scenario.get(3).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult3 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult3.setRoofMounted(true);
//			scenario.get(3).add(permitArraysEntityResult3);
//			scenario.get(3).add(null);
//			scenario.get(3).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo4 = new PermitProjectSiteInfoEntityTwo();
//			scenario.get(4).add(permitProjectSiteInfo4);
//			PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//			scenario.get(4).add(permitHomeSite4);
//			scenario.get(4).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult4 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult4.setRoofMounted(true);
//			scenario.get(4).add(permitArraysEntityResult4);
//			scenario.get(4).add(null);
//			PermitAdvEntityResult PermitAdvEntityInfo4 = new PermitAdvEntityResult();
//			scenario.get(4).add(PermitAdvEntityInfo4);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo5 = new PermitProjectSiteInfoEntityTwo();
//			scenario.get(5).add(permitProjectSiteInfo5);
//			PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//			scenario.get(5).add(permitHomeSite5);
//			scenario.get(5).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult5 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult5.setRoofMounted(true);
//			scenario.get(5).add(permitArraysEntityResult5);
//			scenario.get(5).add(null);
//			PermitAdvEntityResult PermitAdvEntityInfo5 = new PermitAdvEntityResult();
//			PermitAdvEntityInfo5.setSnowLoad("Other");
//			scenario.get(5).add(PermitAdvEntityInfo5);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo6 = new PermitProjectSiteInfoEntityTwo();
//			scenario.get(6).add(permitProjectSiteInfo6);
//			PermitHomeSiteEntityResult permitHomeSite6 = new PermitHomeSiteEntityResult();
//			scenario.get(6).add(permitHomeSite6);
//			scenario.get(6).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult6 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult6.setRoofMounted(true);
//			scenario.get(6).add(permitArraysEntityResult6);
//			scenario.get(6).add(null);
//			PermitAdvEntityResult PermitAdvEntityInfo6 = new PermitAdvEntityResult();
//			PermitAdvEntityInfo6.setSnowLoad("Other");
//			PermitAdvEntityInfo6.setSnowLoadOther("snowLoadOther");
//			scenario.get(6).add(PermitAdvEntityInfo6);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo7 = new PermitProjectSiteInfoEntityTwo();
//			scenario.get(7).add(permitProjectSiteInfo7);
//			PermitHomeSiteEntityResult permitHomeSite7 = new PermitHomeSiteEntityResult();
//			scenario.get(7).add(permitHomeSite7);
//			scenario.get(7).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult7 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult7.setRoofMounted(true);
//			scenario.get(7).add(permitArraysEntityResult7);
//			scenario.get(7).add(null);
//			PermitAdvEntityResult PermitAdvEntityInfo7 = new PermitAdvEntityResult();
//			PermitAdvEntityInfo7.setSnowLoad("12");
//			scenario.get(7).add(PermitAdvEntityInfo7);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo8 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo8.setCrossSectionSize("gggg");
//			permitProjectSiteInfo8.setRafterTrussSpacing("Other");
//			scenario.get(8).add(permitProjectSiteInfo8);
//			PermitHomeSiteEntityResult permitHomeSite8 = new PermitHomeSiteEntityResult();
//			scenario.get(8).add(permitHomeSite8);
//			scenario.get(8).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult8 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult8.setRoofMounted(true);
//			scenario.get(8).add(permitArraysEntityResult8);
//			scenario.get(8).add(null);
//			PermitAdvEntityResult PermitAdvEntityInfo8 = new PermitAdvEntityResult();
//			PermitAdvEntityInfo8.setSnowLoad("12");
//			scenario.get(8).add(PermitAdvEntityInfo8);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo9 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo9.setRafterTrussSpacing("Other");
//			permitProjectSiteInfo9.setCrossSectionSize("OtherSize");
//			scenario.get(9).add(permitProjectSiteInfo9);
//			PermitHomeSiteEntityResult permitHomeSite9 = new PermitHomeSiteEntityResult();
//			permitHomeSite9.setRoofRafterOther("Other");
//			scenario.get(9).add(permitHomeSite9);
//			scenario.get(9).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult9 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult9.setRoofMounted(true);
//			scenario.get(9).add(permitArraysEntityResult9);
//			RoofMaterialType roofMaterialType9 = new RoofMaterialType();
//			scenario.get(9).add(roofMaterialType9);
//			PermitAdvEntityResult PermitAdvEntityInfo9 = new PermitAdvEntityResult();
//			PermitAdvEntityInfo9.setSnowLoad("12");
//			scenario.get(9).add(PermitAdvEntityInfo9);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo10 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo10.setRafterTrussSpacing("Other");
//			permitProjectSiteInfo10.setCrossSectionSize("OtherSize");
//			permitProjectSiteInfo10.setTextOtherSize("OtherSize");
//			permitProjectSiteInfo10.setTextOtherRatfter("");
//			scenario.get(10).add(permitProjectSiteInfo10);
//			PermitHomeSiteEntityResult permitHomeSite10 = new PermitHomeSiteEntityResult();
//			permitHomeSite10.setRoofRafterOther("Other");
//			scenario.get(10).add(permitHomeSite9);
//			scenario.get(10).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult10 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult10.setRoofMounted(true);
//			scenario.get(10).add(permitArraysEntityResult10);
//			RoofMaterialType roofMaterialType10 = new RoofMaterialType();
//			scenario.get(10).add(roofMaterialType10);
//			PermitAdvEntityResult PermitAdvEntityInfo10= new PermitAdvEntityResult();
//			PermitAdvEntityInfo10.setSnowLoad("12");
//			scenario.get(10).add(PermitAdvEntityInfo10);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo11 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo11.setRafterTrussSpacing("Other");
//			permitProjectSiteInfo11.setCrossSectionSize("OtherSize");
//			permitProjectSiteInfo11.setTextOtherSize("2xSize");
//			permitProjectSiteInfo11.setTextOtherRatfter("cc\"");
//			scenario.get(11).add(permitProjectSiteInfo11);
//			PermitHomeSiteEntityResult permitHomeSite11 = new PermitHomeSiteEntityResult();
//			permitHomeSite11.setRoofRafterOther("Other");
//			scenario.get(11).add(permitHomeSite11);
//			scenario.get(11).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult11 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult11.setRoofMounted(true);
//			scenario.get(11).add(permitArraysEntityResult11);
//			RoofMaterialType roofMaterialType11 = new RoofMaterialType();
//			scenario.get(11).add(roofMaterialType11);
//			PermitAdvEntityResult PermitAdvEntityInfo11 = new PermitAdvEntityResult();
//			PermitAdvEntityInfo11.setSnowLoad("12");
//			scenario.get(11).add(PermitAdvEntityInfo11);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo12 = new PermitProjectSiteInfoEntityTwo();
//			scenario.get(12).add(permitProjectSiteInfo12);
//			PermitHomeSiteEntityResult permitHomeSite12 = new PermitHomeSiteEntityResult();
//			scenario.get(12).add(permitHomeSite12);
//			scenario.get(12).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult12 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult12.setRoofMounted(true);
//			scenario.get(12).add(permitArraysEntityResult12);
//			scenario.get(12).add(null);
//			PermitAdvEntityResult PermitAdvEntityInfo12 = new PermitAdvEntityResult();
//			PermitAdvEntityInfo12.setSnowLoad("15");
//			scenario.get(12).add(PermitAdvEntityInfo12);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo13 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo13.setCrossSectionSize("gggg");
//			permitProjectSiteInfo13.setRafterTrussSpacing("Other");
//			scenario.get(13).add(permitProjectSiteInfo13);
//			PermitHomeSiteEntityResult permitHomeSite13 = new PermitHomeSiteEntityResult();
//			scenario.get(13).add(permitHomeSite13);
//			scenario.get(13).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult13 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult13.setRoofMounted(true);
//			scenario.get(13).add(permitArraysEntityResult13);
//			scenario.get(13).add(null);
//			PermitAdvEntityResult PermitAdvEntityInfo13 = new PermitAdvEntityResult();
//			PermitAdvEntityInfo13.setSnowLoad("15");
//			scenario.get(13).add(PermitAdvEntityInfo13);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo14 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo14.setRafterTrussSpacing("Other");
//			permitProjectSiteInfo14.setCrossSectionSize("OtherSize");
//			scenario.get(14).add(permitProjectSiteInfo14);
//			PermitHomeSiteEntityResult permitHomeSite14 = new PermitHomeSiteEntityResult();
//			permitHomeSite14.setRoofRafterOther("Other");
//			scenario.get(14).add(permitHomeSite14);
//			scenario.get(14).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult14 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult14.setRoofMounted(true);
//			scenario.get(14).add(permitArraysEntityResult14);
//			RoofMaterialType roofMaterialType14 = new RoofMaterialType();
//			scenario.get(14).add(roofMaterialType14);
//			PermitAdvEntityResult PermitAdvEntityInfo14 = new PermitAdvEntityResult();
//			PermitAdvEntityInfo14.setSnowLoad("15");
//			scenario.get(14).add(PermitAdvEntityInfo14);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo15 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo15.setRafterTrussSpacing("Other");
//			permitProjectSiteInfo15.setCrossSectionSize("OtherSize");
//			permitProjectSiteInfo15.setTextOtherSize("OtherSize");
//			permitProjectSiteInfo15.setTextOtherRatfter("");
//			scenario.get(15).add(permitProjectSiteInfo15);
//			PermitHomeSiteEntityResult permitHomeSite15 = new PermitHomeSiteEntityResult();
//			permitHomeSite15.setRoofRafterOther("Other");
//			scenario.get(15).add(permitHomeSite9);
//			scenario.get(15).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult15 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult15.setRoofMounted(true);
//			scenario.get(15).add(permitArraysEntityResult15);
//			RoofMaterialType roofMaterialType15 = new RoofMaterialType();
//			scenario.get(15).add(roofMaterialType15);
//			PermitAdvEntityResult PermitAdvEntityInfo15= new PermitAdvEntityResult();
//			PermitAdvEntityInfo15.setSnowLoad("16");
//			scenario.get(15).add(PermitAdvEntityInfo15);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo16 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo16.setRafterTrussSpacing("Other");
//			permitProjectSiteInfo16.setCrossSectionSize("OtherSize");
//			permitProjectSiteInfo16.setTextOtherSize("2xSize");
//			permitProjectSiteInfo16.setTextOtherRatfter("cc\"");
//			scenario.get(16).add(permitProjectSiteInfo16);
//			PermitHomeSiteEntityResult permitHomeSite16 = new PermitHomeSiteEntityResult();
//			permitHomeSite16.setRoofRafterOther("Other");
//			scenario.get(16).add(permitHomeSite16);
//			scenario.get(16).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult16 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult16.setRoofMounted(true);
//			scenario.get(16).add(permitArraysEntityResult16);
//			RoofMaterialType roofMaterialType16 = new RoofMaterialType();
//			scenario.get(16).add(roofMaterialType16);
//			PermitAdvEntityResult PermitAdvEntityInfo16 = new PermitAdvEntityResult();
//			PermitAdvEntityInfo16.setSnowLoad("90");
//			scenario.get(16).add(PermitAdvEntityInfo16);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo17 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo17.setRafterTrussSpacing("Other");
//			permitProjectSiteInfo17.setCrossSectionSize("OtherSize");
//			permitProjectSiteInfo17.setTextOtherSize("2xSize");
//			permitProjectSiteInfo17.setTextOtherRatfter("cc\"");
//			scenario.get(17).add(permitProjectSiteInfo17);
//			PermitHomeSiteEntityResult permitHomeSite17 = new PermitHomeSiteEntityResult();
//			permitHomeSite17.setRoofRafterOther("Flat Roof With Trusses");
//			scenario.get(17).add(permitHomeSite17);
//			scenario.get(17).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult17 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult17.setRoofMounted(true);
//			scenario.get(17).add(permitArraysEntityResult17);
//			RoofMaterialType roofMaterialType17 = new RoofMaterialType();
//			scenario.get(17).add(roofMaterialType17);
//			PermitAdvEntityResult PermitAdvEntityInfo17 = new PermitAdvEntityResult();
//			PermitAdvEntityInfo17.setSnowLoad("90");
//			scenario.get(17).add(PermitAdvEntityInfo17);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitProjectSiteInfoEntityTwo permitProjectSiteInfo18 = new PermitProjectSiteInfoEntityTwo();
//			permitProjectSiteInfo18.setRafterTrussSpacing("Other");
//			permitProjectSiteInfo18.setCrossSectionSize("OtherSize");
//			permitProjectSiteInfo18.setTextOtherSize("2xSize");
//			permitProjectSiteInfo18.setTextOtherRatfter("cc\"");
//			scenario.get(18).add(permitProjectSiteInfo18);
//			PermitHomeSiteEntityResult permitHomeSite18 = new PermitHomeSiteEntityResult();
//			permitHomeSite18.setRoofRafter("Pre-Eng Roof Trusses");
//			scenario.get(18).add(permitHomeSite18);
//			scenario.get(18).add(form);
//			PermitArrayEntityResultSecond permitArraysEntityResult18 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult18.setRoofMounted(true);
//			scenario.get(18).add(permitArraysEntityResult18);
//			RoofMaterialType roofMaterialType18 = new RoofMaterialType();
//			scenario.get(18).add(roofMaterialType18);
//			PermitAdvEntityResult PermitAdvEntityInfo18 = new PermitAdvEntityResult();
//			PermitAdvEntityInfo18.setSnowLoad("90");
//			scenario.get(18).add(PermitAdvEntityInfo18);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapRoofStructural ["+i+"]");
//				 planSetServiceS100.mapRoofStructural((PermitProjectSiteInfoEntityTwo)scenario.get(i).get(0), (PermitHomeSiteEntityResult)scenario.get(i).get(1), form, (PermitArrayEntityResultSecond)scenario.get(i).get(3), (RoofMaterialType)scenario.get(i).get(4), (PermitAdvEntityResult)scenario.get(i).get(5), null, 0);
//			}
//		}
//		
//		@Test
//		public void testmapScale() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.add(new ArrayList<Object>());
//			PermitDrafterDataEntity permitDrafterDatanfo1 = new PermitDrafterDataEntity();
//			scenario.get(1).add(permitDrafterDatanfo1);
//			scenario.get(1).add(form);
//			scenario.add(new ArrayList<Object>());
//			PermitDrafterDataEntity permitDrafterDatanfo2 = new PermitDrafterDataEntity();
//			permitDrafterDatanfo2.setScalerackingLayout("null");
//			scenario.get(2).add(permitDrafterDatanfo2);
//			scenario.get(2).add(form);
//			scenario.add(new ArrayList<Object>());
//			PermitDrafterDataEntity permitDrafterDatanfo3 = new PermitDrafterDataEntity();
//			permitDrafterDatanfo3.setScalerackingLayout("11");
//			scenario.get(3).add(permitDrafterDatanfo3);
//			scenario.get(3).add(form);
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapScale ["+i+"]");
//				 planSetServiceS100.mapScale((PermitDrafterDataEntity)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),0);
//			}
//		}
//		
//		
//		
//		
//}
