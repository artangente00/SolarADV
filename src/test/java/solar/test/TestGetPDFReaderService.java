//package solar.test;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//import com.PlayGroundAdv.Solar.Constants.Constants;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetPDFReaderService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//public class TestGetPDFReaderService {
//
//
//	@Mock
//    private EntityManager em;
//	
//	@Spy
//	CheckValueTypesService checkValueTypesService;
//	
//	@Mock
//	HistoryActivityService historyActivityService;
//	
//	@InjectMocks
//	GetPDFReaderService getPDFReaderService = new GetPDFReaderService();
//	
//    @Before
//	public void setupMock() {
//    	getPDFReaderService = new GetPDFReaderService();
//	       MockitoAnnotations.initMocks(this);
//	       try {
//	   		reader = new PdfReader(Constants.rapportPlansetFolderUrl +"NEC-PDF/" +"PDF-PV001"+".pdf" );
//	   		fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-PV001"+".pdf");
//	   		stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
//	   		form = stamper.getAcroFields();
//	   		readerOrigin = new PdfReader( Constants.rapportPlansetFolderUrl +"NEC-PDF/" + "PDF-PV001.pdf" );
//	   		} catch (IOException e) {
//	   			e.printStackTrace();
//	   		}
//	   		catch (DocumentException e){
//	   		}
//	}
//    
//    @Test
//	public void testcheckStateResevation() {
//    	
//    	List<PermitEntity> scenario = new ArrayList<PermitEntity>();
//    	PermitEntity permitEntity = new PermitEntity();
//		permitEntity.setId(1);
//    	permitEntity.setPlansetVersion(null);
//    	permitEntity.setPlansetState(null);
//		scenario.add(permitEntity);
//		
//		PermitEntity permitEntity2 = new PermitEntity();
//		permitEntity2.setId(1);
//		permitEntity2.setPlansetVersion(1);
//		permitEntity2.setPlansetState("CA");
//		scenario.add(permitEntity2);
//		
//		PermitEntity permitEntity3 = new PermitEntity();
//		permitEntity3.setId(1);
//		permitEntity3.setPlansetVersion(1);
//		permitEntity3.setPlansetState("SC");
//		scenario.add(permitEntity3);
//		
//		PermitEntity permitEntity4 = new PermitEntity();
//		permitEntity4.setId(1);
//		permitEntity4.setPlansetVersion(2);
//		permitEntity4.setPlansetState("CA");
//		scenario.add(permitEntity4);
//		
//		PermitEntity permitEntity5 = new PermitEntity();
//		permitEntity5.setId(1);
//		permitEntity5.setPlansetVersion(2);
//		permitEntity5.setPlansetState("SC");
//		scenario.add(permitEntity5);
//		
//		List<String> state = Arrays.asList("CA", "SC", "", null);
//		
//		List<Boolean> expectedresult = Arrays.asList(false,false,false,true,false,false,false,false,false,true,false,false,false,false,true,false,false,false,true,true);
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("update PermitEntity u set u.plansetVersion = :p1, u.releasev2 = :p2, u.releasev3= :p2 where u.id= :p3")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter( Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		
//		for (int i = 0; i < state.size(); i++) {
//			for (int j = 0; j < scenario.size(); j++) {
//				Boolean result = getPDFReaderService.checkStateResevation(state.get(i), (PermitEntity) scenario.get(j));
//				expectedresult.get(i*scenario.size()+j);
//			}	
//		}
//
//	}
//    
//    @Test
//   	public void testcheckInvTechnologyResevation() {
//       	
//       	List<PermitEntity> scenario = new ArrayList<PermitEntity>();
//       	PermitEntity permitEntity = new PermitEntity();
//   		permitEntity.setId(1);
//       	permitEntity.setPlansetVersion(null);
//       	permitEntity.setPlansetInverterTechnologies(null);
//   		scenario.add(permitEntity);
//   		
//   		PermitEntity permitEntity2 = new PermitEntity();
//   		permitEntity2.setId(1);
//   		permitEntity2.setPlansetVersion(1);
//   		permitEntity2.setPlansetInverterTechnologies("Neither");
//   		scenario.add(permitEntity2);
//   		
//   		PermitEntity permitEntity3 = new PermitEntity();
//   		permitEntity3.setId(1);
//   		permitEntity3.setPlansetVersion(1);
//   		permitEntity3.setPlansetInverterTechnologies("Micro Inverter");
//   		scenario.add(permitEntity3);
//   		
//   		PermitEntity permitEntity4 = new PermitEntity();
//   		permitEntity4.setId(1);
//   		permitEntity4.setPlansetVersion(2);
//   		permitEntity4.setPlansetInverterTechnologies("Neither");
//   		scenario.add(permitEntity4);
//   		
//   		PermitEntity permitEntity5 = new PermitEntity();
//   		permitEntity5.setId(1);
//   		permitEntity5.setPlansetVersion(2);
//   		permitEntity5.setPlansetInverterTechnologies("Micro Inverter");
//   		scenario.add(permitEntity5);
//   		
//   		List<String> state = Arrays.asList("Neither", "Micro Inverter", "", null);
//   		
//   		List<Boolean> expectedresult = Arrays.asList(false,false,false,true,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false);
//   		
//   		for (int i = 0; i < state.size(); i++) {
//   			for (int j = 0; j < scenario.size(); j++) {
//   				
//   				Boolean result = getPDFReaderService.checkInvTechnologyResevation(state.get(i), (PermitEntity) scenario.get(j));
//   				expectedresult.get(i*scenario.size()+j);
//   			}	
//   		}
//
//   	}
//    
//    @Test
//   	public void testInvTechnologyRemoveResevation() {
//       	
//       	List<PermitEntity> scenario = new ArrayList<PermitEntity>();
//       	PermitEntity permitEntity = new PermitEntity();
//   		permitEntity.setId(1);
//       	permitEntity.setPlansetVersion(null);
//       	permitEntity.setPlansetInverterTechnologies(null);
//   		scenario.add(permitEntity);
//   		
//   		PermitEntity permitEntity2 = new PermitEntity();
//   		permitEntity2.setId(1);
//   		permitEntity2.setPlansetVersion(1);
//   		permitEntity2.setPlansetInverterTechnologies("Neither");
//   		scenario.add(permitEntity2);
//   		
//   		PermitEntity permitEntity3 = new PermitEntity();
//   		permitEntity3.setId(1);
//   		permitEntity3.setPlansetVersion(1);
//   		permitEntity3.setPlansetInverterTechnologies("Micro Inverter");
//   		scenario.add(permitEntity3);
//   		
//   		PermitEntity permitEntity4 = new PermitEntity();
//   		permitEntity4.setId(1);
//   		permitEntity4.setPlansetVersion(2);
//   		permitEntity4.setPlansetInverterTechnologies("Neither");
//   		scenario.add(permitEntity4);
//   		
//   		PermitEntity permitEntity5 = new PermitEntity();
//   		permitEntity5.setId(1);
//   		permitEntity5.setPlansetVersion(2);
//   		permitEntity5.setPlansetInverterTechnologies("Micro Inverter");
//   		scenario.add(permitEntity5);
//   		
//   		List<String> state = Arrays.asList("Neither", "Micro Inverter", "", null);
//   		List<Boolean> expectedresult = Arrays.asList(false,false,false,false,true,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false);
//
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("update PermitEntity u set u.PlansetInverterTechnologies = :p1 where u.id= :p2")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter( Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		
//   		for (int i = 0; i < state.size(); i++) {
//   			for (int j = 0; j < scenario.size(); j++) {
//   				
//   				Boolean result = getPDFReaderService.checkInvTechnologyRemoveResevation(state.get(i), (PermitEntity) scenario.get(j));
//   				expectedresult.get(i*scenario.size()+j);
//   			}	
//   		}
//
//   	}
//    
//    @Test
//   	public void testcheckRoofRafterRemoveResevation() {
//       	
//       	List<PermitEntity> scenario = new ArrayList<PermitEntity>();
//       	PermitEntity permitEntity = new PermitEntity();
//   		permitEntity.setId(1);
//       	permitEntity.setPlansetVersion(null);
//       	permitEntity.setPlansetInverterTechnologies(null);
//   		scenario.add(permitEntity);
//   		
//   		PermitEntity permitEntity2 = new PermitEntity();
//   		permitEntity2.setId(1);
//   		permitEntity2.setPlansetVersion(1);
//   		permitEntity2.setPlansetRoofRafter("RAFTER");
//   		scenario.add(permitEntity2);
//   		
//   		PermitEntity permitEntity3 = new PermitEntity();
//   		permitEntity3.setId(1);
//   		permitEntity3.setPlansetVersion(1);
//   		permitEntity3.setPlansetRoofRafter("TRUSS");
//   		scenario.add(permitEntity3);
//   		
//   		PermitEntity permitEntity4 = new PermitEntity();
//   		permitEntity4.setId(1);
//   		permitEntity4.setPlansetVersion(2);
//   		permitEntity4.setPlansetRoofRafter("RAFTER");
//   		scenario.add(permitEntity4);
//   		
//   		PermitEntity permitEntity5 = new PermitEntity();
//   		permitEntity5.setId(1);
//   		permitEntity5.setPlansetVersion(2);
//   		permitEntity5.setPlansetRoofRafter("TRUSS");
//   		scenario.add(permitEntity5);
//   		
//   		List<String> state = Arrays.asList("RAFTER", "TRUSS", "", null);
//   		List<Boolean> expectedresult = Arrays.asList(false,false,false,false,true,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false);
//   		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("update PermitEntity u set u.PlansetRoofRafter = :p1 where u.id= :p2")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter( Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		
//   		for (int i = 0; i < state.size(); i++) {
//   			for (int j = 0; j < scenario.size(); j++) {
//   				
//   				Boolean result = getPDFReaderService.checkRoofRafterRemoveResevation(state.get(i), (PermitEntity) scenario.get(j));
//   				expectedresult.get(i*scenario.size()+j);
//   			}	
//   		}
//
//   	}
//    PdfReader reader = null;
//   	File fileRe = null;
//   	PdfStamper stamper = null;
//   	AcroFields form = null;
//   	PdfReader readerOrigin= null;
//    @Test
//	public void testapplyFontsRevision() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		PermitEntity permit1 = new PermitEntity();
//		scenario.get(0).add(permit1);
//		scenario.get(0).add(stamper);
//		scenario.get(0).add(readerOrigin);
//		scenario.get(0).add(null);
//		scenario.get(0).add(form);
//		scenario.get(0).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitEntity permit2 = new PermitEntity();
//		permit2.setPlansetVersion(2);
//		scenario.get(1).add(permit2);
//		scenario.get(1).add(stamper);
//		scenario.get(1).add(readerOrigin);
//		scenario.get(1).add(null);
//		scenario.get(1).add(form);
//		scenario.get(0).add(0);
//		scenario.add(new ArrayList<Object>());
//		PermitEntity permit3 = new PermitEntity();
//		permit3.setPlansetVersion(2);
//		permit3.setPlansetState("CA");
//		scenario.get(2).add(permit3);
//		scenario.get(2).add(stamper);
//		scenario.get(2).add(readerOrigin);
//		scenario.get(0).add(0);
//		PermitHomeSiteEntityResult permitHomeSite3 = new PermitHomeSiteEntityResult();
//		permitHomeSite3.setState("CA");
//		scenario.get(2).add(permitHomeSite3);
//		scenario.get(2).add(form);
//		for(int i = 0; i < scenario.size(); i++) {
//		
//		 getPDFReaderService.applyFontsRevision((PermitEntity)scenario.get(i).get(0),(PdfStamper)scenario.get(i).get(1),(PdfReader)scenario.get(i).get(2),
//				 (PermitHomeSiteEntityResult)scenario.get(i).get(3),(AcroFields)scenario.get(i).get(4),0,"","");
//		}
//	}
//
//}
