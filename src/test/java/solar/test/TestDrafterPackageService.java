//package solar.test;
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
//import com.PlayGroundAdv.Solar.Entity.PermitDrafterDataEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitSketchEntity;
//import com.PlayGroundAdv.Solar.Entity.ProjectsTrackerEntity;
//import com.PlayGroundAdv.Solar.Entity.RoofMaterialType;
//import com.PlayGroundAdv.Solar.model.InfoPdfPackageDrafterResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.DrafterPackageService;
//import com.PlayGroundAdv.Solar.Services.PermitService;
//import com.PlayGroundAdv.Solar.Services.PermitServiceEdit;
//
//public class TestDrafterPackageService {
//	@Mock
//	EntityManager em;
//
//	@Mock
//    private PermitService permitService;
//
//	@Spy
//	private CheckValueTypesService checkValueTypesService;
//
//	@Spy
//	private PermitServiceEdit permitServiceEdit;
//
//	 @InjectMocks
//	 DrafterPackageService drafterPackageService = new DrafterPackageService();
//
//	 @Before
//		public void setupMock() {
//		 drafterPackageService = new DrafterPackageService();
//		       MockitoAnnotations.initMocks(this);
//		}
//
//	@Test
//	public void testdownloadPackageTracker() {
//		 Query querytracker = mock(Query.class);
//		 when(em.createQuery("SELECT u from ProjectsTrackerEntity u WHERE u.permit.id = :p1 ")).thenReturn(querytracker);
//		 when(querytracker.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(querytracker);
//
//		 Query querypermit = mock(Query.class);
//		 when(em.createQuery("SELECT u from PermitEntity u WHERE u.id = :p1 ")).thenReturn(querypermit);
//		 when(querypermit.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(querypermit);
//		 when(querypermit.getSingleResult()).thenReturn(null);
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(1).add("");
//		 scenario.get(1).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(2).add("12345");
//		 scenario.get(2).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(3).add("12345");
//		 List<ProjectsTrackerEntity> resultList = new ArrayList<ProjectsTrackerEntity>();
//		 scenario.get(3).add(resultList);
//
//		 for(int i=0;i<scenario.size();i++) {
//             when(querytracker.getResultList()).thenReturn((List<ProjectsTrackerEntity>) scenario.get(i).get(1));
//              String result = drafterPackageService.downloadPackageTracker((String)scenario.get(i).get(0));
//		 }
//	}
//
//	@Test
//	public void testdownloadPackage() {
//		 Query query = mock(Query.class);
//		 when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.InfoPdfPackageDrafterResult( "
//					+ " u.homeOwnName, "
//					+ " v.siteAddress, "
//					+ " v.addressLine2, "
//					+ " v.city, "
//					+ " v.state, "
//					+ " v.postalCode, "
//					+ " v.propertyAPN, "
//					+ " v.cityOther, "
//					+ " w.tallStructure, "
//					+ " w.otherTallStructure, "
//					+ " w.roofMaterialType, "
//					+ " w.roofMaterialTypeOther, "
//					+ " w.crossSectionSize, "
//					+ " w.textOtherSize, "
//					+ " w.rafterTrussSpacing, "
//					+ " w.textOtherRatfter, "
//					+ " s.pvModuleModEl, "
//					+ " s.stringOne, "
//					+ " s.stringTwo, "
//					+ " s.stringThree, "
//					+ " s.stringFour, "
//					+ " s.stringFive, "
//					+ " s.secondStringOne, "
//					+ " s.secondStringTwo, "
//					+ " s.secondStringThree, "
//					+ " s.secondStringFour, "
//					+ " s.secondStringFive, "
//					+ " s.thirdStringOne, "
//					+ " s.thirdStringTwo, "
//					+ " s.thirdStringThree, "
//					+ " s.thirdStringFour, "
//					+ " s.thirdStringFive, "
//					+ " s.fourthStringOne, "
//					+ " s.fourthStringTwo, "
//					+ " s.fourthStringThree, "
//					+ " s.fourthStringFour, "
//					+ " s.fourthStringFive, "
//					+ " s.fifthStringOne, "
//					+ " s.fifthStringTwo, "
//					+ " s.fifthStringThree, "
//					+ " s.fifthStringFour, "
//					+ " s.fifthStringFive, "
//					+ " s.stringSix, "
//					+ " s.stringSeven, "
//					+ " s.stringEight, "
//					+ " s.stringNine, "
//					+ " s.stringTen, "
//					+ " s.stringEleven, "
//					+ " s.stringTwelve, "
//					+ " s.secondStringSix, "
//					+ " s.secondStringSeven, "
//					+ " s.secondStringEight, "
//					+ " s.secondStringNine, "
//					+ " s.secondStringTen, "
//					+ " s.secondStringEleven, "
//					+ " s.secondStringTwelve, "
//					+ " s.thirdStringSix, "
//					+ " s.thirdStringSeven, "
//					+ " s.thirdStringEight, "
//					+ " s.thirdStringNine, "
//					+ " s.thirdStringTen, "
//					+ " s.thirdStringEleven, "
//					+ " s.thirdStringTwelve, "
//					+ " s.fourthStringSix, "
//					+ " s.fourthStringSeven, "
//					+ " s.fourthStringEight, "
//					+ " s.fourthStringNine, "
//					+ " s.fourthStringTen, "
//					+ " s.fourthStringEleven, "
//					+ " s.fourthStringTwelve, "
//					+ " s.fifthStringSix, "
//					+ " s.fifthStringSeven, "
//					+ " s.fifthStringEight, "
//					+ " s.fifthStringNine, "
//					+ " s.fifthStringTen, "
//					+ " s.fifthStringEleven, "
//					+ " s.fifthStringTwelve, "
//					+ " w.inverterModel, "
//					+ " s.roofMounted, "
//					+ " t.qtySegmentOne, "
//					+ " t.qtySegmentSix, "
//					+ " x.formatSize, "
//					+ " x.tiltLegs, "
//					+ " y.sketchNote, "
//					+ " v.stanchionMaxSpacing, "
//					+ " v.stanchionMaxSpacingOther )"
//					+ " from PermitEntity u,"
//					+ "		 PermitHomeSiteInfoEntity v,"
//					+ "		 PermitProjectSiteInfoEntity w,"
//					+ "      PermitArraysEntity s,"
//					+ "		 PermitConduitConductorSectionEntity t, "
//					+ " 	 PermitAdditionalInfoEntity x, "
//					+ "		 PermitLayoutEntity y"
//					+ " where u.id = :p1 "
//					+ " and   v.permitEntity.id = :p1 "
//					+ " and   w.permitEntity.id = :p1 "
//					+ " and   s.permitEntity.id = :p1 "
//					+ " and   t.permitEntity.id = :p1 "
//					+ " and   x.permitEntity.id = :p1 "
//					+ " and   y.permitEntity.id = :p1 ")).thenReturn(query);
//		 when(query.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(query);
//
//		 Query query2 = mock(Query.class);
//		 when(em.createQuery(" SELECT u "
//					+ " from PermitSketchEntity u "
//					+ " where u.permitEntity.id = :p1 "
//					+ " ORDER BY u.id ASC")).thenReturn(query2);
//		 when(query2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(query2);
//
//
//		 Query query1 = mock(Query.class);
//		 when(em.createQuery(" SELECT u "
//					+ " from PermitDrafterDataEntity u "
//					+ " where u.permitEntity.id = :p1  ")).thenReturn(query1);
//		 when(query1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(query1);
//
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(1).add("");
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//		 scenario.get(1).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(2).add("12345");
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//		 scenario.get(2).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(3).add("12345");
//		 List<PermitSketchEntity> resultList = new ArrayList<PermitSketchEntity>();
//		 scenario.get(3).add(resultList);
//		 InfoPdfPackageDrafterResult infoPdfPackageDrafterResult = new InfoPdfPackageDrafterResult();
//		 scenario.get(3).add(infoPdfPackageDrafterResult);
//		 PermitDrafterDataEntity permitDrafterDataEntity = new PermitDrafterDataEntity();
//		 scenario.get(3).add(permitDrafterDataEntity);
//
//		 for(int i=0;i<scenario.size();i++) {
//             when(query.getSingleResult()).thenReturn((InfoPdfPackageDrafterResult) scenario.get(i).get(2));
//             when(query2.getResultList()).thenReturn((List<PermitSketchEntity>)scenario.get(i).get(1));
//             when(query1.getSingleResult()).thenReturn((PermitDrafterDataEntity)scenario.get(i).get(3));
//              drafterPackageService.downloadPackage((String)scenario.get(i).get(0),"1");
//		 }
//	}
//
//	@Test
//	public void testgetRoofMaterialType() {
//		 Query queryRoofMaterialType = mock(Query.class);
//		 when(em.createQuery("SELECT i from RoofMaterialType i Where i.id = :p1 ")).thenReturn(queryRoofMaterialType);
//		 when(queryRoofMaterialType.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(queryRoofMaterialType);
//
//
//		 ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(1).add("");
//		 scenario.get(1).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(2).add("12345");
//		 scenario.get(2).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(3).add("12345");
//		 List<RoofMaterialType> resultList = new ArrayList<RoofMaterialType>();
//		 scenario.get(3).add(resultList);
//
//		 scenario.add(new ArrayList<Object>());
//		 scenario.get(4).add("Other");
//		 scenario.get(4).add(null);
//
//		 List<String> expectedresult = Arrays.asList("","","","","Other");
//		 for(int i=0;i<scenario.size();i++) {
//             when(queryRoofMaterialType.getResultList()).thenReturn((List<RoofMaterialType>) scenario.get(i).get(1));
//              String result = drafterPackageService.getRoofMaterialType((String)scenario.get(i).get(0));
//		 }
//	}
//
//	@Test
//	public void testbuildingDrafterPDF() {
//		 ArrayList<InfoPdfPackageDrafterResult> scenarios = new ArrayList<InfoPdfPackageDrafterResult>();
//		 scenarios.add(null);
//		 InfoPdfPackageDrafterResult drafterResult = new InfoPdfPackageDrafterResult();
//		 scenarios.add(drafterResult);
//		 drafterResult.setRoofMounted(true);
//		 drafterResult.setStanchionMaxSpacing("");
//		 drafterResult.setPvModuleModEl("");
//		 List<PermitSketchEntity> permitSketchEntity= new  ArrayList<PermitSketchEntity>();
//		 drafterResult.setPermitSketchEntity(permitSketchEntity);
//		 scenarios.add(drafterResult);
//		 for(int i=0;i<scenarios.size();i++) {
//             drafterPackageService.buildingDrafterPDF(scenarios.get(i),null,null);
//		 }
//	}
//}
