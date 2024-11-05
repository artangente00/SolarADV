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
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PlansetCustomizeSheets;
//import com.PlayGroundAdv.Solar.Entity.UserLicSectionsEntity;
//import com.PlayGroundAdv.Solar.model.EditUserInformations;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
//import com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.PermitServiceEdit;
//import com.PlayGroundAdv.Solar.Services.PlanSetServiceS200;
//import com.PlayGroundAdv.Solar.Services.PlanSetServiceS201;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//public class TestPlanSetServiceS201 {
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
//    PlanSetServiceS201 planSetServiceS201 = new PlanSetServiceS201();
//    
//    PdfReader reader = null;
//   	File fileRe = null;
//   	PdfStamper stamper = null;
//   	AcroFields form = null;
//   	PdfReader readerOrigin= null;
//   	
//     @Before
//     public void setupMock() {
//    	 planSetServiceS201 = new PlanSetServiceS201();
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
//			scenario.get(0).add(permit1);
//			scenario.get(0).add(form);
//			scenario.get(0).add("");
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
//			for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("testmapTitleBlock ["+i+"]");
//			 planSetServiceS201.mapTitleBlock((PermitHomeSiteEntityResult)scenario.get(i).get(0),(PermitEntity)scenario.get(i).get(1),(AcroFields)scenario.get(i).get(2),(String)scenario.get(i).get(3),0);
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
//	    	planSetServiceS201.mapContractorInfo(editUserInformations2,form,permitHomeSite2,0);
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
//	    	planSetServiceS201.mapContractorInfo(editUserInformations2,form,permitHomeSite2,0);
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
//				 planSetServiceS201.mapContractorInfo((EditUserInformations)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(PermitHomeSiteEntityResult)scenario.get(i).get(2),0);
//			}
//		}
//		
//		@Test
//		public void testmapDetailsS201() {
//			PdfReader reader = null;
//			File fileRe = null;
//			PdfStamper stamper = null;
//			AcroFields form = null;
//			try {
//			reader = new PdfReader(Constants.rapportPlansetFolderUrl +"NEC-PDF/" +"PDF-E001-"+"STRING"+".pdf" );
//			fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E001-STRING"+".pdf");
//			stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
//			form = stamper.getAcroFields();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			catch (DocumentException e){
//			}
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapDetailsS201 ["+i+"]");
//				 planSetServiceS201.mapDetailsS201((AcroFields)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),0);
//			}
//		}
//		
//}
