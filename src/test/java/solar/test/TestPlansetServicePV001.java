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
//import com.PlayGroundAdv.Solar.Entity.CityEntity;
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.Engineers;
//import com.PlayGroundAdv.Solar.Entity.PermitEngineerEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PlansetCustomizeSheets;
//import com.PlayGroundAdv.Solar.Entity.UserLicSectionsEntity;
//import com.PlayGroundAdv.Solar.model.EditUserInformations;
//import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
//import com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.PermitServiceEdit;
//import com.PlayGroundAdv.Solar.Services.PlanSetServiceP002;
//import com.PlayGroundAdv.Solar.Services.PlansetServicePV001;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//public class TestPlansetServicePV001 {
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
//    PlansetServicePV001 plansetServicePV001 = new PlansetServicePV001();
//    
//    PdfReader reader = null;
//   	File fileRe = null;
//   	PdfStamper stamper = null;
//   	AcroFields form = null;
//   	PdfReader readerOrigin= null;
//   	
//     @Before
//     public void setupMock() {
//    	 plansetServicePV001 = new PlansetServicePV001();
//        MockitoAnnotations.initMocks(this);
// 		
// 		try {
// 		reader = new PdfReader(Constants.rapportPlansetFolderUrl +"NEC-PDF/" +"PDF-PV001"+".pdf" );
// 		fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-PV001"+".pdf");
// 		stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
// 		form = stamper.getAcroFields();
// 		readerOrigin = new PdfReader( Constants.rapportPlansetFolderUrl +"NEC-PDF/" + "PDF-PV001.pdf" );
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
//	public void testgetpdfReader() throws DocumentException {
//		 ArrayList<PlansetCustomizeSheets> resultlist = new ArrayList<PlansetCustomizeSheets>();
//		 PlansetCustomizeSheets plansetCustomizeSheets1 =new PlansetCustomizeSheets();
//		 resultlist.add(plansetCustomizeSheets1);
//		 PlansetCustomizeSheets plansetCustomizeSheets2 =new PlansetCustomizeSheets();
//		 plansetCustomizeSheets2.setUtilityCompany("12451");
//		 resultlist.add(plansetCustomizeSheets2);
//		 PlansetCustomizeSheets plansetCustomizeSheets3 =new PlansetCustomizeSheets();
//		 plansetCustomizeSheets3.setUtilityCompany("");
//		 plansetCustomizeSheets3.setIndividualAHJ("");
//		 plansetCustomizeSheets3.setUsers("xx");
//		 resultlist.add(plansetCustomizeSheets3);
//		 PlansetCustomizeSheets plansetCustomizeSheets4 =new PlansetCustomizeSheets();
//		 plansetCustomizeSheets4.setUtilityCompany("");
//		 plansetCustomizeSheets4.setIndividualAHJ("");
//		 plansetCustomizeSheets4.setUsers("FirstName LastName");
//		 resultlist.add(plansetCustomizeSheets4);
//		 PlansetCustomizeSheets plansetCustomizeSheets5=new PlansetCustomizeSheets();
//		 plansetCustomizeSheets5.setUtilityCompany("1245");
//		 plansetCustomizeSheets5.setIndividualAHJ("City of CA");
//		 plansetCustomizeSheets5.setUsers("xx");
//		 resultlist.add(plansetCustomizeSheets5);
//		 PlansetCustomizeSheets plansetCustomizeSheets6=new PlansetCustomizeSheets();
//		 plansetCustomizeSheets6.setUtilityCompany("1245");
//		 plansetCustomizeSheets6.setIndividualAHJ("");
//		 plansetCustomizeSheets6.setUsers("xx");
//		 resultlist.add(plansetCustomizeSheets6);
//		 PlansetCustomizeSheets plansetCustomizeSheets7=new PlansetCustomizeSheets();
//		 plansetCustomizeSheets7.setUtilityCompany("1245");
//		 plansetCustomizeSheets7.setIndividualAHJ("");
//		 resultlist.add(plansetCustomizeSheets7);
//		 PlansetCustomizeSheets plansetCustomizeSheets8=new PlansetCustomizeSheets();
//		 plansetCustomizeSheets8.setUtilityCompany("1245");
//		 plansetCustomizeSheets8.setIndividualAHJ("City of CA");
//		 plansetCustomizeSheets8.setUsers("FirstName LastName");
//		 resultlist.add(plansetCustomizeSheets8);
//		 PlansetCustomizeSheets plansetCustomizeSheets9=new PlansetCustomizeSheets();
//		 plansetCustomizeSheets9.setIndividualAHJ("City of CA");
//		 resultlist.add(plansetCustomizeSheets9);
//		 Query mockedQuery = mock(Query.class);
//	     when(em.createQuery("SELECT u from PlansetCustomizeSheets u where (u.pdfName =:p1 OR u.pdfName =:p3) AND u.isDeleted=:p2")).thenReturn(mockedQuery);
//	     when(mockedQuery.setParameter("p1", "PDF-PV001.pdf")).thenReturn(mockedQuery);
//	     when(mockedQuery.setParameter("p3", "PDF-PV001")).thenReturn(mockedQuery);
//	     when(mockedQuery.setParameter("p2", false)).thenReturn(mockedQuery);
//	     List<List<Object>> scenario = new ArrayList<List<Object>>();
//	     
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add("");
//		scenario.get(0).add(resultlist);
//			
//		 scenario.add(new ArrayList<Object>());
//		 PermitEntity permitEntity1 =new PermitEntity();
//		 AuthentificationEntity user1 =new AuthentificationEntity();
//		 user1.setFirstName("FirstName");
//		 user1.setLastName("LastName");
//		 permitEntity1.setAuthentificationEntity(user1);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(permitEntity1);
//		 scenario.get(1).add("");
//		 scenario.get(1).add(resultlist);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 PermitEntity permitEntity2 =new PermitEntity();
//		 PermitHomeSiteEntityResult permitHomeSite2 =new PermitHomeSiteEntityResult();
//		 scenario.get(2).add(permitHomeSite2);
//		 scenario.get(2).add(null);
//		 ElectricalUtilityEntity electricalCompany2 =new ElectricalUtilityEntity();
//		 scenario.get(2).add(electricalCompany2);
//		 scenario.get(2).add(permitEntity2);
//		 scenario.get(2).add("");
//		 scenario.get(2).add(resultlist);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 PermitEntity permitEntity3 =new PermitEntity();
//		 PermitHomeSiteEntityResult permitHomeSite3 =new PermitHomeSiteEntityResult();
//		 scenario.get(3).add(permitHomeSite3);
//		 scenario.get(3).add(null);
//		 ElectricalUtilityEntity electricalCompany3 =new ElectricalUtilityEntity();
//		 electricalCompany3.setUtilityCompanyName("1245");
//		 scenario.get(3).add(electricalCompany3);
//		 scenario.get(3).add(permitEntity3);
//		 scenario.get(3).add("");
//		 scenario.get(3).add(resultlist);
//		 
//		 
//		 scenario.add(new ArrayList<Object>());
//		 PermitEntity permitEntity4 =new PermitEntity();
//		 PermitHomeSiteEntityResult permitHomeSite4 =new PermitHomeSiteEntityResult();
//		 permitHomeSite4.setUtilityCompanyName("Other");
//		 permitHomeSite4.setUtilityCompanyNameOther("1245");
//		 permitHomeSite4.setProjectJurisdiction("city");
//		 scenario.get(4).add(permitHomeSite4);
//		 scenario.get(4).add(null);
//		 ElectricalUtilityEntity electricalCompany4 =new ElectricalUtilityEntity();
//		 scenario.get(4).add(electricalCompany4);
//		 scenario.get(4).add(permitEntity4);
//		 scenario.get(4).add("");
//		 scenario.get(4).add(resultlist);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 PermitEntity permitEntity5 =new PermitEntity();
//		 PermitHomeSiteEntityResult permitHomeSite5 =new PermitHomeSiteEntityResult();
//		 permitHomeSite5.setUtilityCompanyName("Other");
//		 permitHomeSite5.setUtilityCompanyNameOther("1245");
//		 permitHomeSite5.setProjectJurisdiction("County");
//		 scenario.get(5).add(permitHomeSite5);
//		 scenario.get(5).add(null);
//		 ElectricalUtilityEntity electricalCompany5 =new ElectricalUtilityEntity();
//		 scenario.get(5).add(electricalCompany5);
//		 scenario.get(5).add(permitEntity5);
//		 scenario.get(5).add("");
//		 scenario.get(5).add(resultlist);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 PermitEntity permitEntity6 =new PermitEntity();
//		 PermitHomeSiteEntityResult permitHomeSite6 =new PermitHomeSiteEntityResult();
//		 permitHomeSite6.setUtilityCompanyName("Other");
//		 permitHomeSite6.setUtilityCompanyNameOther("1245");
//		 permitHomeSite6.setProjectJurisdiction("city");
//		 permitHomeSite6.setCity("CA");
//		 scenario.get(6).add(permitHomeSite6);
//		 scenario.get(6).add(null);
//		 ElectricalUtilityEntity electricalCompany6 =new ElectricalUtilityEntity();
//		 scenario.get(6).add(electricalCompany6);
//		 scenario.get(6).add(permitEntity6);
//		 scenario.get(6).add("");
//		 scenario.get(6).add(resultlist);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 PermitHomeSiteEntityResult permitHomeSite7 =new PermitHomeSiteEntityResult();
//		 permitHomeSite7.setUtilityCompanyName("Other");
//		 permitHomeSite7.setUtilityCompanyNameOther("1245");
//		 permitHomeSite7.setProjectJurisdiction("city");
//		 permitHomeSite7.setCity("CA");
//		 scenario.get(7).add(permitHomeSite7);
//		 scenario.get(7).add(null);
//		 ElectricalUtilityEntity electricalCompany7 =new ElectricalUtilityEntity();
//		 scenario.get(7).add(electricalCompany7);
//		 scenario.get(7).add(null);
//		 scenario.get(7).add("");
//		 scenario.get(7).add(null);
//		 
//	     scenario.add(new ArrayList<Object>());
//		 PermitEntity permitEntity8 =new PermitEntity();
//		 PermitHomeSiteEntityResult permitHomeSite8 =new PermitHomeSiteEntityResult();
//		 permitHomeSite8.setUtilityCompanyName("Other");
//		 permitHomeSite8.setUtilityCompanyNameOther("1245");
//		 permitHomeSite8.setProjectJurisdiction("city");
//		 permitHomeSite8.setCity("CA");
//		 scenario.get(8).add(permitHomeSite8);
//		 scenario.get(8).add(null);
//		 ElectricalUtilityEntity electricalCompany8 =new ElectricalUtilityEntity();
//		 scenario.get(8).add(electricalCompany8);
//		 scenario.get(8).add(permitEntity8);
//		 scenario.get(8).add("");
//		 scenario.get(8).add(null);
//		 
//	     scenario.add(new ArrayList<Object>());
//		 PermitEntity permitEntity9 =new PermitEntity();
//		 permitEntity9.setPlansetVersion(2);
//		 PermitHomeSiteEntityResult permitHomeSite9 =new PermitHomeSiteEntityResult();
//		 scenario.get(9).add(permitHomeSite9);
//		 scenario.get(9).add(null);
//		 ElectricalUtilityEntity electricalCompany9 =new ElectricalUtilityEntity();
//		 scenario.get(9).add(electricalCompany9);
//		 scenario.get(9).add(permitEntity9);
//		 scenario.get(9).add("");
//		 scenario.get(9).add(null);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 PermitHomeSiteEntityResult permitHomeSite10 =new PermitHomeSiteEntityResult();
//		 permitHomeSite10.setProjectJurisdiction("city");
//		 scenario.get(10).add(permitHomeSite10);
//		 scenario.get(10).add(null);
//		 ElectricalUtilityEntity electricalCompany10 =new ElectricalUtilityEntity();
//		 scenario.get(10).add(electricalCompany10);
//		 scenario.get(10).add(null);
//		 scenario.get(10).add("");
//		 scenario.get(10).add(resultlist);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 PermitEntity permitEntity11 =new PermitEntity();
//		 PermitHomeSiteEntityResult permitHomeSite11 =new PermitHomeSiteEntityResult();
//		 permitHomeSite11.setProjectJurisdiction("County");
//		 scenario.get(11).add(permitHomeSite11);
//		 scenario.get(11).add(null);
//		 ElectricalUtilityEntity electricalCompany11 =new ElectricalUtilityEntity();
//		 scenario.get(11).add(electricalCompany11);
//		 scenario.get(11).add(permitEntity11);
//		 scenario.get(11).add("");
//		 scenario.get(11).add(resultlist);
//		 
//		 scenario.add(new ArrayList<Object>());
//		 PermitEntity permitEntity12 =new PermitEntity();
//		 permitEntity12.setPlansetVersion(2);
//		 PermitHomeSiteEntityResult permitHomeSite12 =new PermitHomeSiteEntityResult();
//		 permitHomeSite12.setProjectJurisdiction("city");
//		 scenario.get(12).add(permitHomeSite12);
//		 scenario.get(12).add(null);
//		 ElectricalUtilityEntity electricalCompany12 =new ElectricalUtilityEntity();
//		 scenario.get(12).add(electricalCompany12);
//		 scenario.get(12).add(permitEntity12);
//		 scenario.get(12).add("");
//		 scenario.get(12).add(resultlist);
//			for(int i = 0; i < scenario.size(); i++) {
//				when(mockedQuery.getResultList()).thenReturn((ArrayList<PlansetCustomizeSheets>)scenario.get(i).get(5));
//				System.out.println("testgetPdfReader["+i+"]");
//				//Arij
//				//plansetServicePV001.getPdfReader((PermitHomeSiteEntityResult)scenario.get(i).get(0),(String)scenario.get(i).get(1),(ElectricalUtilityEntity)scenario.get(i).get(2),(PermitEntity)scenario.get(i).get(3),(String)scenario.get(i).get(4));
//			}
//     	}
//		
//
//		@Test
//		public void testmapTitleBlock() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			List<CityEntity> resultlist =null;
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(resultlist);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite1 = new PermitHomeSiteEntityResult();
//			scenario.get(1).add(permitHomeSite1);
//			scenario.get(1).add(null);
//			scenario.get(1).add(form);
//			scenario.get(1).add(resultlist);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//			permitHomeSite2.setCity("city");
//			scenario.get(2).add(permitHomeSite2);
//			scenario.get(2).add(null);
//			scenario.get(2).add(form);
//			scenario.get(2).add(resultlist);
//			
//			scenario.add(new ArrayList<Object>());
//			List<CityEntity> resultlist3 = new ArrayList<CityEntity>();
//			PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//			permitHomeSite3.setCity("city");
//			scenario.get(3).add(permitHomeSite3);
//			scenario.get(3).add(null);
//			scenario.get(3).add(form);
//			scenario.get(3).add(resultlist3);
//			
//			scenario.add(new ArrayList<Object>());
//			List<CityEntity> resultlist4 = new ArrayList<CityEntity>();
//			CityEntity city = new CityEntity();
//			resultlist4.add(city);
//			resultlist4.add(city);
//			PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//			permitHomeSite4.setCity("city");
//			scenario.get(4).add(permitHomeSite4);
//			scenario.get(4).add(null);
//			scenario.get(4).add(form);
//			scenario.get(4).add(resultlist4);
//			
//			scenario.add(new ArrayList<Object>());
//			List<CityEntity> resultlist5 = new ArrayList<CityEntity>();
//			CityEntity city1 = new CityEntity();
//			city1.setName("city name");
//			resultlist5.add(city1);
//			PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//			permitHomeSite5.setCity("city");
//			scenario.get(5).add(permitHomeSite5);
//			scenario.get(5).add(null);
//			scenario.get(5).add(form);
//			scenario.get(5).add(resultlist5);
//			
//			Query mockedQuery = mock(Query.class);
//			   when(em.createQuery("SELECT u"
//						+ " from CityEntity u "
//						+ " where u.name = :p1")).thenReturn(mockedQuery);
//			   when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapTitleBlock ["+i+"]");
//				 when(mockedQuery.getResultList()).thenReturn((List<CityEntity>)scenario.get(i).get(3));
//				 when(mockedQuery.getSingleResult()).thenReturn(city1);
//				 plansetServicePV001.mapTitleBlock((PermitHomeSiteEntityResult)scenario.get(i).get(0), (PermitEntity)scenario.get(i).get(1), form, 0, null);
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
//				 plansetServicePV001.mapContractorInfo((EditUserInformations)scenario.get(i).get(0), form, (PermitHomeSiteEntityResult)scenario.get(i).get(2), 0, null);
//			}
//		}
//		
//		@Test
//		public void testmapPermitHomeInfo() {
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
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite = new PermitHomeSiteEntityResult();
//			scenario.get(1).add(permitHomeSite);
//			scenario.get(1).add(form);	
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			scenario.get(1).add(0);
//			scenario.get(1).add(0);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//			permitHomeSite2.setSiteAddress("SiteAddress");
//			permitHomeSite2.setCity("City") ;
//			permitHomeSite2.setResidenceBindingCategory("") ;
//			permitHomeSite2.setState("NY");
//			scenario.get(2).add(permitHomeSite2);
//			scenario.get(2).add(form);	
//			PermitEntity permitEntity2 = new PermitEntity();
//			scenario.get(2).add(permitEntity2);
//			PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//			scenario.get(2).add(permitArraysEntityResult2);
//			PermitAdvEntityResult PermitAdvEntityInfo2 = new PermitAdvEntityResult();
//			scenario.get(2).add(PermitAdvEntityInfo2);
//			PermitCompanyInfoEntityResult permitCompanyInfo2 =new PermitCompanyInfoEntityResult();
//			scenario.get(2).add(permitCompanyInfo2);
//			scenario.get(2).add(null);
//			scenario.get(2).add(0);
//			scenario.get(2).add(0);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//			permitHomeSite3.setSiteAddress("SiteAddress");
//			permitHomeSite3.setCity("City") ;
//			permitHomeSite3.setHomeOwnName("HomeOwnName");
//			permitHomeSite3.setResidenceBindingCategory("Other") ;
//			permitHomeSite3.setState("NY");
//			scenario.get(3).add(permitHomeSite3);
//			scenario.get(3).add(form);	
//			PermitEntity permitEntity3 = new PermitEntity();
//			scenario.get(3).add(permitEntity3);
//			PermitArrayEntityResultSecond permitArraysEntityResult3 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult3.setDeviceToIncorporate("System Optimizer");
//			permitArraysEntityResult3.setRoofMounted(true);
//			scenario.get(3).add(permitArraysEntityResult3);
//			PermitAdvEntityResult PermitAdvEntityInfo3 = new PermitAdvEntityResult();
//			PermitAdvEntityInfo3.setSnowLoad("");
//			scenario.get(3).add(PermitAdvEntityInfo3);
//			scenario.get(3).add(null);
//			ElectricalUtilityEntity electricalCompany3 = new ElectricalUtilityEntity();
//			scenario.get(3).add(electricalCompany3);
//			scenario.get(3).add(0);
//			scenario.get(3).add(0);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite4 = new PermitHomeSiteEntityResult();
//			permitHomeSite4.setSiteAddress("SiteAddress");
//			permitHomeSite4.setCity("City") ;
//			permitHomeSite4.setResidenceBindingCategory("") ;
//			permitHomeSite4.setState("NY");
//			permitHomeSite4.setUtilityCompanyName("Other");
//			scenario.get(4).add(permitHomeSite4);
//			scenario.get(4).add(form);	
//			PermitEntity permitEntity4 = new PermitEntity();
//			scenario.get(4).add(permitEntity4);
//			PermitArrayEntityResultSecond permitArraysEntityResult4 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult4.setDeviceToIncorporate("System Optimizer");
//			permitArraysEntityResult4.setPvModuleModEl("pvModuleModEl");
//			scenario.get(4).add(permitArraysEntityResult4);
//			scenario.get(4).add(null);
//			PermitCompanyInfoEntityResult permitCompanyInfo4 =new PermitCompanyInfoEntityResult();
//			scenario.get(4).add(permitCompanyInfo4);
//			scenario.get(4).add(null);
//			scenario.get(4).add(0);
//			scenario.get(4).add(0);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite5 = new PermitHomeSiteEntityResult();
//			permitHomeSite5.setSiteAddress("SiteAddress");
//			permitHomeSite5.setCity("City") ;
//			permitHomeSite5.setResidenceBindingCategory("") ;
//			permitHomeSite5.setState("NY");
//			permitHomeSite5.setUtilityCompanyName("Other");
//			scenario.get(5).add(permitHomeSite5);
//			scenario.get(5).add(form);	
//			PermitEntity permitEntity5 = new PermitEntity();
//			scenario.get(5).add(permitEntity5);
//			PermitArrayEntityResultSecond permitArraysEntityResult5 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult5.setDeviceToIncorporate("System Optimizer");
//			permitArraysEntityResult5.setPvModuleModEl("pvModule:ModEl");
//			scenario.get(5).add(permitArraysEntityResult5);
//			scenario.get(5).add(null);
//			PermitCompanyInfoEntityResult permitCompanyInfo5 =new PermitCompanyInfoEntityResult();
//			scenario.get(5).add(permitCompanyInfo5);
//			scenario.get(5).add(null);
//			scenario.get(5).add(0);
//			scenario.get(5).add(0);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite6 = new PermitHomeSiteEntityResult();
//			permitHomeSite6.setSiteAddress("SiteAddress");
//			permitHomeSite6.setCity("City") ;
//			permitHomeSite6.setResidenceBindingCategory("") ;
//			permitHomeSite6.setState("NY");
//			permitHomeSite6.setUtilityCompanyName("Other");
//			scenario.get(6).add(permitHomeSite6);
//			scenario.get(6).add(form);	
//			PermitEntity permitEntity6 = new PermitEntity();
//			scenario.get(6).add(permitEntity6);
//			PermitArrayEntityResultSecond permitArraysEntityResult6 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult6.setDeviceToIncorporate("System Optimizer");
//			permitArraysEntityResult6.setPvModuleModEl("pvModule:ModEl");
//			permitArraysEntityResult6.setInverterModel("inverterModel");
//			scenario.get(6).add(permitArraysEntityResult6);
//			scenario.get(6).add(null);
//			PermitCompanyInfoEntityResult permitCompanyInfo6 =new PermitCompanyInfoEntityResult();
//			scenario.get(6).add(permitCompanyInfo6);
//			scenario.get(6).add(null);
//			scenario.get(6).add(0);
//			scenario.get(6).add(0);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite7 = new PermitHomeSiteEntityResult();
//			permitHomeSite7.setSiteAddress("SiteAddress");
//			permitHomeSite7.setCity("City") ;
//			permitHomeSite7.setHomeOwnName("HomeOwnName");
//			permitHomeSite7.setResidenceBindingCategory("Other") ;
//			permitHomeSite7.setState("NY");
//			scenario.get(7).add(permitHomeSite7);
//			scenario.get(7).add(form);	
//			PermitEntity permitEntity7 = new PermitEntity();
//			scenario.get(7).add(permitEntity7);
//			PermitArrayEntityResultSecond permitArraysEntityResult7 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult7.setDeviceToIncorporate("AC Modules");
//			permitArraysEntityResult7.setRoofMounted(true);
//			scenario.get(7).add(permitArraysEntityResult7);
//			PermitAdvEntityResult PermitAdvEntityInfo7 = new PermitAdvEntityResult();
//			PermitAdvEntityInfo7.setSnowLoad("");
//			scenario.get(7).add(PermitAdvEntityInfo7);
//			scenario.get(7).add(null);
//			scenario.get(7).add(null);
//			scenario.get(7).add(0);
//			scenario.get(7).add(0);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite8 = new PermitHomeSiteEntityResult();
//			permitHomeSite8.setSiteAddress("SiteAddress");
//			permitHomeSite8.setCity("City") ;
//			permitHomeSite8.setResidenceBindingCategory("") ;
//			permitHomeSite8.setState("NY");
//			permitHomeSite8.setUtilityCompanyName("Other");
//			scenario.get(8).add(permitHomeSite8);
//			scenario.get(8).add(form);	
//			PermitEntity permitEntity8 = new PermitEntity();
//			scenario.get(8).add(permitEntity8);
//			PermitArrayEntityResultSecond permitArraysEntityResult8 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult8.setDeviceToIncorporate("AC Modules");
//			permitArraysEntityResult8.setPvModuleModEl("pvModuleModEl");
//			scenario.get(8).add(permitArraysEntityResult8);
//			scenario.get(8).add(null);
//			PermitCompanyInfoEntityResult permitCompanyInfo8 =new PermitCompanyInfoEntityResult();
//			scenario.get(8).add(permitCompanyInfo8);
//			scenario.get(8).add(null);
//			scenario.get(8).add(0);
//			scenario.get(8).add(0);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitHomeSiteEntityResult permitHomeSite9 = new PermitHomeSiteEntityResult();
//			permitHomeSite9.setSiteAddress("SiteAddress");
//			permitHomeSite9.setCity("City") ;
//			permitHomeSite9.setResidenceBindingCategory("") ;
//			permitHomeSite9.setState("NY");
//			permitHomeSite9.setUtilityCompanyName("Other");
//			scenario.get(9).add(permitHomeSite9);
//			scenario.get(9).add(form);	
//			PermitEntity permitEntity9 = new PermitEntity();
//			scenario.get(9).add(permitEntity9);
//			PermitArrayEntityResultSecond permitArraysEntityResult9 = new PermitArrayEntityResultSecond();
//			permitArraysEntityResult9.setDeviceToIncorporate("AC Modules");
//			permitArraysEntityResult9.setPvModuleModEl("pvModule:ModEl");
//			scenario.get(9).add(permitArraysEntityResult9);
//			scenario.get(9).add(null);
//			PermitCompanyInfoEntityResult permitCompanyInfo9 =new PermitCompanyInfoEntityResult();
//			scenario.get(9).add(permitCompanyInfo9);
//			scenario.get(9).add(null);
//			scenario.get(9).add(0);
//			scenario.get(9).add(0);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//				 System.out.println("testmapPermitHomeInfo ["+i+"]");
//				 plansetServicePV001.mapPermitHomeInfo((PermitHomeSiteEntityResult)scenario.get(i).get(0), form, (PermitEntity)scenario.get(i).get(2), (PermitArrayEntityResultSecond)scenario.get(i).get(3), (PermitAdvEntityResult)scenario.get(i).get(4), (PermitCompanyInfoEntityResult)scenario.get(i).get(5), (ElectricalUtilityEntity)scenario.get(i).get(6),(int)scenario.get(i).get(7),(int)scenario.get(i).get(8), 0, null);
//			}
//		}
//		
//		@Test
//		public void testgetMountType() {
//			ArrayList<PermitArrayEntityResultSecond> scenario = new ArrayList<PermitArrayEntityResultSecond>();
//			scenario.add(null);
//			PermitArrayEntityResultSecond scenario1=new PermitArrayEntityResultSecond() ;
//			scenario.add(scenario1);
//			PermitArrayEntityResultSecond scenario2=new PermitArrayEntityResultSecond() ;
//			scenario2.setGroundMounted(true);
//			scenario2.setPoleMounted(true);
//			scenario2.setCarportMounted(true);
//			scenario2.setOtherMounted(true);
//			scenario.add(scenario2);
//			for(int i = 0; i < scenario.size(); i++) {
//			  System.out.println("testgetMountType ["+i+"]");
//			  plansetServicePV001.getMountType(scenario.get(i));
//			}
//		}
//		
//		@Test
//		public void testengineerMapping() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(null);
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitEngineerEntity PermitNewInputs = new PermitEngineerEntity() ; 
//			scenario.get(1).add(PermitNewInputs);
//			scenario.get(1).add(form);
//			Engineers engineer = new Engineers();
//			scenario.get(1).add(engineer);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitEngineerEntity PermitNewInputs2 = new PermitEngineerEntity() ; 
//			PermitNewInputs2.setApplicablEngineering("EngineeringPacketmyself");
//			scenario.get(2).add(PermitNewInputs2);
//			scenario.get(2).add(form);
//			Engineers engineer2 = new Engineers();
//			scenario.get(2).add(engineer2);
//			
//			scenario.add(new ArrayList<Object>());
//			PermitEngineerEntity PermitNewInputs3 = new PermitEngineerEntity() ; 
//			PermitNewInputs3.setApplicablEngineering("AdvanceSolarSolutionsEngineeringPacket");
//			scenario.get(3).add(PermitNewInputs3);
//			scenario.get(3).add(form);
//			Engineers engineer3 = new Engineers();
//			scenario.get(3).add(engineer3);
//			
//			for(int i = 0; i < scenario.size(); i++) {
//			  System.out.println("testengineerMapping ["+i+"]");
//			  plansetServicePV001.engineerMapping((PermitEngineerEntity)scenario.get(i).get(0),(AcroFields)scenario.get(i).get(1),(Engineers)scenario.get(i).get(2),0);
//			}
//		}
//		
//		@Test
//		public void testsheetIndexmapping() {
//			List<List<Object>> scenario = new ArrayList<List<Object>>();
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(0).add(form);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			scenario.get(0).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(1).add(form);
//			PermitEntity permitEntity1 = new PermitEntity();
//			scenario.get(1).add(permitEntity1);
//			scenario.get(1).add(null);
//			scenario.get(1).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(2).add(form);
//			PermitEntity permitEntity2 = new PermitEntity();
//			scenario.get(2).add(permitEntity2);
//			PermitHomeSiteEntityResult permitHomeSite2 = new PermitHomeSiteEntityResult();
//			scenario.get(2).add(permitHomeSite2);
//			PermitArrayEntityResultSecond permitArraysEntityResult2 = new PermitArrayEntityResultSecond();
//			scenario.get(2).add(permitArraysEntityResult2);
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(3).add(form);
//			PermitEntity permitEntity3 = new PermitEntity();
//			scenario.get(3).add(permitEntity3);
//			PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//			permitHomeSite3.setState("CA");
//			scenario.get(3).add(permitHomeSite3);
//			scenario.get(3).add(null);
//			
//			scenario.add(new ArrayList<Object>());
//			scenario.get(4).add(form);
//			PermitEntity permitEntity4 = new PermitEntity();
//			permitEntity4.setPlansetVersion(2);
//			scenario.get(4).add(permitEntity4);
//			scenario.get(4).add(null);
//			scenario.get(4).add(null);
//			
//		    for(int i = 0; i < scenario.size(); i++) {
//			  System.out.println("testsheetIndexmapping ["+i+"]");
//			  plansetServicePV001.sheetIndexmapping((AcroFields)scenario.get(i).get(0),(PermitEntity)scenario.get(i).get(1), (PermitHomeSiteEntityResult)scenario.get(i).get(2), (PermitArrayEntityResultSecond)scenario.get(i).get(3), 0, null);
//		    }
//		}
//
//}
