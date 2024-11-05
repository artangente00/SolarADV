//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import java.io.File;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.TimeZone;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.PlayGroundAdv.Solar.Entity.*;
//import com.PlayGroundAdv.Solar.Entity.Battery;
//import com.PlayGroundAdv.Solar.Entity.Cmodulev2;
//import com.PlayGroundAdv.Solar.Entity.PermitArraysEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitCompanyInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitHomeSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.RoleEntity;
//import com.PlayGroundAdv.Solar.Entity.SiteSurveyEntity;
//import com.PlayGroundAdv.Solar.Entity.SiteSurveyReqFieldLogicEntity;
//import com.PlayGroundAdv.Solar.Entity.SiteSurveyReqFieldSettingEntity;
//import com.PlayGroundAdv.Solar.model.BatteryCorrectionRequest;
//import com.PlayGroundAdv.Solar.model.NewSiteSurveyModel;
//import com.PlayGroundAdv.Solar.model.PermitResponse;
//import com.PlayGroundAdv.Solar.model.SiteSurveyFieldSetting;
//import com.PlayGroundAdv.Solar.model.SiteSurveyModel;
//import com.PlayGroundAdv.Solar.model.SiteSurveyResult;
//import com.PlayGroundAdv.Solar.model.UserInformationResult;
//import com.*;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.ConvertersManagementService;
//import com.PlayGroundAdv.Solar.Services.GetLibraryService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.PermitService;
//import com.PlayGroundAdv.Solar.Services.SiteSurveyService;
//import com.google.api.client.util.DateTime;
//
//public class TestSiteSurveyService {
//
//	@Mock
//	EntityManager em;
//
//	@Mock
//	private Query query;
//
//	@Mock
//	HistoryActivityService historicActivity;
//
//	@Mock
//	HistoryActivityService historyActivityService;
//
//	 @Mock
//	 private CriteriaQuery criteriaQuery;
//	 @Mock
//	 private PermitService permitService;
//	 @Mock
//	 private CriteriaQuery criteriaQueryAll;
//
//	 @Mock
//	 private CriteriaBuilder criteriaBuilder;
//
//	@Mock
//    private Root root;
//
//	@Spy
//	CheckValueTypesService checkValueTypesService;
//	@InjectMocks
//	SiteSurveyService siteSurveyService = new SiteSurveyService();
//
//
//    @Before
//	public void setupMock() {
//    	siteSurveyService = new SiteSurveyService();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testaddSiteSurvey() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u where u.isCanceled = :p1 and u.id = :p2")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		SiteSurveyService siteSurveyService2 = Mockito.spy(siteSurveyService);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		//The result that expected
//		scenario.get(0).add(new SiteSurveyModel());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		NewSiteSurveyModel sc = new NewSiteSurveyModel();
//		sc.setOwnerID("123");
//		scenario.get(1).add(sc);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		//The result that expected
//		TimeZone.setDefault (  TimeZone.getTimeZone ("PST")) ;
//		DateTime dt = new DateTime(new Date(),TimeZone.getTimeZone("PST"));
//		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//		isoFormat.setTimeZone(TimeZone.getTimeZone("PST"));
//		Date date = null;
//		try {
//			date = isoFormat.parse(dt+"");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		SiteSurveyModel exp = new SiteSurveyModel();
//		exp.setSubmitted(false);
//		exp.setCreationDate(date.toString());
//		exp.setStatus("Draft");
//		scenario.get(1).add(exp);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(sc);
//		// Result of the query1
//		scenario.get(2).add(new AuthentificationEntity());
//		// Result of the query2
//		scenario.get(2).add(null);
//		//The result that expected
//		SiteSurveyModel exp2 = new SiteSurveyModel();
//		exp2.setSubmitted(false);
//		exp2.setCreationDate(date.toString());
//		exp2.setStatus("Draft");
//		exp2.setOwnerID(0);
//		scenario.get(2).add(exp2);
//
//
//		 for(int i=0;i<scenario.size();i++) {
//		   System.out.println("addSiteSurvey [ "+i+" ]");
//			Mockito.doReturn(new UserInformationResult()).when(siteSurveyService2)
//			.getUserInformation(Mockito.anyInt());
//		 //  when(permitService.addPermit(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn("123");
//           when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//           when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//            siteSurveyService2.addSiteSurvey( (NewSiteSurveyModel) scenario.get(i).get(0));
//		 }
//	}
//
//	@Test
//	public void testgetUserInformation() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.UserInformationResult ( "
//				+ "   u.id,  " + "   u.firstName,  " + "   u.lastName,  " + "   u.password,  "  + "   u.email ,"
//				+ "   u.country,  " + "   u.company,  " + "   u.contractorCode, " + "   u.active,  " + "   u.address,  " + "   u.secondAddressLine,"
//				+ "   city,  " + "   u.state,  " + "   u.postalCode,  " + "   u.roleEntity.id ) "
//				+ " from AuthentificationEntity u  " + " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(new UserInformationResult());
//		// Result excpected
//		scenario.get(1).add(new UserInformationResult());
//
//		 for(int i=0;i<scenario.size();i++) {
//			System.out.println("getSiteSurvey [ "+i+" ]");
//           when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//           UserInformationResult rslt =  siteSurveyService.getUserInformation( (Integer) scenario.get(i).get(0));
//
//		 }
//	}
//
//	@Test
//	public void testgetAllSiteSurvey() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from SiteSurveyEntity u where u.isCanceled = :p1  ORDER BY u.creationDate DESC ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from SiteSurveyEntity u where u.isCanceled = :p1 and u.createdBy.id = :p2  ORDER BY u.creationDate DESC ")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//		SiteSurveyService siteSurveyService2 = Mockito.spy(siteSurveyService);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<SiteSurveyModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<SiteSurveyModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("125");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<SiteSurveyModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("125");
//		// Result of the query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		RoleEntity role = new RoleEntity();
//		role.setId(3);
//		auth.setRoleEntity(role);
//		scenario.get(3).add(auth);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new ArrayList<SiteSurveyModel>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("125");
//		// Result of the query1
//		scenario.get(4).add(auth);
//		// Result of the query2
//		ArrayList<SiteSurveyEntity> list = new ArrayList<>();
//		list.add(null);
//		SiteSurveyEntity site = new SiteSurveyEntity();
//		site.setCreatedBy(new AuthentificationEntity());
//		list.add(site);
//		scenario.get(4).add(list);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result excpected
//		ArrayList<SiteSurveyModel> listExp = new ArrayList<SiteSurveyModel>();
//		SiteSurveyModel s = new SiteSurveyModel();
//		s.setOwnerID(0);
//		listExp.add(s);
//		scenario.get(4).add(listExp);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("125");
//		// Result of the query1
//		AuthentificationEntity auth1 = new AuthentificationEntity();
//		RoleEntity role1 = new RoleEntity();
//		role1.setId(2);
//		auth1.setRoleEntity(role1);
//		scenario.get(5).add(auth1);
//		// Result of the query2
//		scenario.get(5).add(list);
//		// Result of the query3
//		scenario.get(5).add(list);
//		// Result excpected
//		scenario.get(5).add(listExp);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAllSiteSurvey [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			Mockito.doReturn(new UserInformationResult()).when(siteSurveyService2)
//			.getUserInformation(Mockito.anyInt());
//			ArrayList<SiteSurveyModel> result = ((ArrayList<SiteSurveyModel>) siteSurveyService2
//					.getAllSiteSurvey((String) scenario.get(i).get(0)));
//
//
//		}
//	}
//
//	@Test
//	public void testgetSiteSurvey() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.SiteSurveyResult ( "
//				+ "u.id, u.homeOwnName, u.createdBy.id, u.portalProject.id, u.status," +
//				"  u.hasProject, u.isCanceled, u.submitted, u.creationDate, u.lastUpdatedDate," +
//				"  u.dateOfSubmit, u.basicTypeOfSystem, u.hasProjectSiteImage, u.contactName," +
//				"  u.streetAddress, u.city, u.state, u.zIP, u.hoa, u.permittingAuthority," +
//				"  u.legalOwnerName, u.parcelNumber, u.roofMaterialType, u.widthSeams, u.riskCategory," +
//				"  u.otherRiskCategory, u.buildingOccupancy, u.otherBuildingOccupancy, u.numberOfStories," +
//				"  u.projectEquipmentStagingLocation, u.ownerPreferredRoofAccessLocation," +
//				"  u.accessIssuesWith_MeterOrProposedACDisco, u.utilityMeterOrProposedACDisco," +
//				"  u.unrestrainedAnimal, u.otheraccessissue, u.describeAccessIssues," +
//				"  u.contactpersonforutility, u.othercontactperson, u.contactphone, u.othercontactphone," +
//				"  u.hasImageCapturesatRafterTrusses, u.centerFed, u.mainPanelMake, u.mainPanelModel," +
//				"  u.upgradingMainServicePanel, u.voltage, u.otherVoltage, u.mspbusbarRating," +
//				"  u.otherMSPBusbarRating, u.mSPMainBreakerRating, u.otherMSPMainBreakerRating," +
//				"  u.openBreakerSlots, u.sCIR, u.mainBreakerFeedGauge, u.utilityFeederGauge," +
//				"  u.copperWiresSameAsMainContact, u.copperWiresDifferentNumber," +
//				"  u.aluminumWiresSameAsMainContact, u.aluminumWiresDifferentNumber, u.groundAccessible," +
//				"  u.undergroundOrOverheadFeed, u.hasImageAtMSP, u.existingSubpanel," +
//				"  u.tieInPOCIsAtSubpanel, u.subpanelMake, u.subpanelModel, u.subpanelVoltage," +
//				"  u.otherSubpanelVoltage, u.subpanel3PhaseType, u.subpanelNumberWires," +
//				"  u.subpanelBusbarRating, u.otherSubpanelBusbarRating, u.subpanelMainBreakerRating," +
//				"  u.otherSubpanelMainBreakerRating, u.openBreakerSlotsinSubpanel, u.subpanelFeederGauge," +
//				"  u.hasImageCapturesAtSupPanel, u.siteNotes, u.utilityCoName, u.nEMType," +
//				"  u.otherNEMType, u.postSolarRate, u.usageHistoryOffset, u.paceEntity," +
//				"  u.aCDiscoWillbemorethan10FromUtilityMeter, u.atLeast4MonthsOfelectricbillinghistory," +
//				"  u.squareFeetOfLivingArea, u.recentAnnualUsage, u.numberOfElectricVehicles," +
//				"  u.existingPVSystemAtSite, u.pVSystemMake, u.pVSystemModel, u.pVQuantity," +
//				"  u.gridTiedInverterMake, u.gridTiedInverterModel, u.gridTiedInverterQuantity," +
//				"  u.existingACDisconnect, u.aCDiscoMake, u.aCDiscoModel, u.integerACDiscoRating," +
//				"  u.connectionType, u.pVBreaker1, u.pVBreaker2, u.pVBreaker3, u.pVBreaker4," +
//				"  u.pVBreaker5, u.existingBattery, u.batteryInverterIsGridTiedInverter," +
//				"  u.exBatteryInverter1Manufacturer, u.exBatteryInverter1Model, u.exBatteryQuantityOfInverter1," +
//				"  u.exBatteryInverter2Manufacturer, u.exBatteryInverter2Model, u.exBatteryQuantityOfInverter2," +
//				"  u.exBatteryInverter3Manufacturer, u.exBatteryInverter3Model, u.exBatteryQuantityOfInverter3," +
//				"  u.hasImageOfExistingSolarEquipLocations, u.proposedBattery, u.hoursOfAutonomy," +
//				"  u.theBatteryInverterwillBe, u.offGridInverter1Manufacturer, u.offGridInverter1Model," +
//				"  u.quantityofOffGridInverter1, u.offGridInverter2Manufacturer, u.offGridInverter2Model," +
//				"  u.quantityofOffGridInverter2, u.circuitstoRelocatetoCriticalLoadsPanel," +
//				"  u.criticalLoadPanelLocation, u.equipmentLocation, u.inverter1Manufacturer," +
//				"  u.inverter1Model, u.quantityofInverter1, u.inverter2Manufacturer, u.inverter2Model," +
//				"  u.quantityofInverter2, u.inverter3Manufacturer, u.inverter3Model," +
//				"  u.quantityofInverter3, u.productionMonitor, u.activeInternetConnection," +
//				"  u.modemLocation, u.connectTheMonitor, u.hasImageCapturesOfRoof," +
//				"  u.verifyRoofMeasurementsPreRoofLayoutProvided, u.hasImageCapturesOfApplicableElevationsViews," +
//				"  u.arraySection1, u.wireRunOnRoof, u.wireRunInAttic, u.tiltupModules," +
//				"  u.roofMaterial, u.numberOfLayers, u.roofAge, u.pVMake, u.pVModel," +
//				"  u.roofAzimuth, u.roofPitchTilt, u.eaveOverhang, u.gableRakeOverhang," +
//				"  u.heightAtGutter, u.tiltupAzimuth, u.tiltupModuleAngle, u.roofStructureChart," +
//				"  u.stanchionMaxSpacing, u.ridgeBeamDepthAtArrays, u.maxSpanAtArraysHS1," +
//				"  u.maxSpanAtArraysHS2, u.rafterTrussSpacing, u.otherRafterTrussSpacing," +
//				"  u.nonRoofGroundMount, u.nonRoofPoleMount, u.nonRoofCarport, u.nonRoofPatioCover," +
//				"  u.nonRoofContourSlope, u.nonRoofPathPoint, u.nonRoofGradingGrubbing," +
//				"  u.nonRoofSiteComposition, u.nonRoofElevationStructure, u.nonRoofExistingSecurity," +
//				"  u.nonRoofPatioCoverValue, u.patioCoverAttachedTypeBeam, u.patioCoverAttachedTypePosts," +
//				"  u.patioCoverFreestandingTypeBeam, u.patioCoverFreestandingTypePosts," +
//				"  u.patioCoverFreestandingExtendOver, u.patioCoverFreestandingPastEave, "+
//				"  u.otherVoltageOther," +
//				"  u.widthSeamsOther, u.roofMaterialTypeOther, u.postSolarRateOther," +
//				"  u.otherPatioCoverAttachedTypeBeam, u.otherPatioCoverAttachedTypePosts," +
//				"  u.otherPatioCoverFreestandingTypeBeam, u.otherPatioCoverFreestandingTypePosts,"+
//				"  u.imageOfSiteInformationRafter, u.imageOfExistingMainPanel,"+
//				"  u.imageOfExistingSubPanel, u.imageBatteryInfo,"+
//				"  u.imageOfInternetConnectionRoof, u.imageOfInternetConnectionElevation, u.existingACDisco) " +
//				"  from SiteSurveyEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(new SiteSurveyResult());
//		// Result excpected
//		scenario.get(1).add(new SiteSurveyResult());
//
//		 for(int i=0;i<scenario.size();i++) {
//			System.out.println("getSiteSurvey [ "+i+" ]");
//           when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//           SiteSurveyResult rslt =  siteSurveyService.getSiteSurvey( (Integer) scenario.get(i).get(0));
//
//		 }
//	}
//
//	@Test
//	public void testsaveAsDraft() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SiteSurveyEntity u where u.isCanceled = :p1 and u.id = :p2")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Fail");
//		// List Result of the query1
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(new SiteSurveyResult());
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//		// List Result of the query1
//		scenario.get(1).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(new SiteSurveyResult());
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Fail");
//		// List Result of the query1
//		ArrayList<SiteSurveyEntity> list = new ArrayList<SiteSurveyEntity>();
//		list.add(null);
//		list.add(new SiteSurveyEntity());
//		scenario.get(2).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(new SiteSurveyResult());
//		// Result of the query1
//		scenario.get(3).add(new SiteSurveyEntity());
//		// Result excpected
//		scenario.get(3).add("Done");
//		// List Result of the query1
//		scenario.get(3).add(list);
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			System.out.println("saveAsDraft [ "+i+" ]");
//            when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//            siteSurveyService.saveAsDraft( (SiteSurveyResult) scenario.get(i).get(0));
//		 }
//	}
//
//	@Test
//	public void testsubmitSiteSurvey() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SiteSurveyEntity u where u.isCanceled = :p1 and u.id = :p2")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Done");
//		// List Result of the query1
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//		// List Result of the query1
//		ArrayList<SiteSurveyEntity> list = new ArrayList<SiteSurveyEntity>();
//		list.add(null);
//		list.add(new SiteSurveyEntity());
//		scenario.get(1).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(new SiteSurveyEntity());
//		// Result excpected
//		scenario.get(2).add("Done");
//		// List Result of the query1
//		scenario.get(2).add(list);
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("cancelSiteSurvey [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//             siteSurveyService.cancelSiteSurvey( (Integer) scenario.get(i).get(0));
//		 }
//	}
//
//	@Test
//	public void testSynchronizationproject() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u where u.isCanceled = :p1 and u.id = :p2")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitHomeSiteInfoEntity u WHERE u.permitEntity.id = :p1 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitProjectSiteInfoEntity u WHERE u.permitEntity.id = :p1 ")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitCompanyInfoEntity u WHERE u.permitEntity.id = :p1 ")).thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitArraysEntity u WHERE u.permitEntity.id = :p1 ")).thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result of the query4
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("");
//		// Result List of the query2
//		scenario.get(0).add(null);
//		// Result List of the query3
//		scenario.get(0).add(null);
//		// Result List of the query4
//		scenario.get(0).add(null);
//		//The result list of Query5
//		scenario.get(0).add(null);
//		//the result of the Query 5
//		scenario.get(0).add(null);
//		//The Single result of the Query 1
//		scenario.get(0).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		SiteSurveyResult s = new SiteSurveyResult();
//		s.setHomeOwnName("Junit test");
//		s.setStreetAddress("junit");
//		s.setPortalProject(125);
//		scenario.get(1).add(s);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result of the query4
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("");
//		// Result List of the query2
//		scenario.get(1).add(null);
//		// Result List of the query3
//		scenario.get(1).add(null);
//		// Result List of the query4
//		scenario.get(1).add(null);
//		//The result list of Query5
//		scenario.get(1).add(null);
//		//the result of the Query 5
//		scenario.get(1).add(null);
//		//The Single result of the Query 1
//		scenario.get(1).add(null);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(s);
//		// Result of the query1
//		ArrayList<PermitEntity> list = new ArrayList<PermitEntity>();
//		list.add(null);
//		list.add(new PermitEntity());
//		scenario.get(2).add(list);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result of the query4
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("");
//		// Result List of the query2
//		scenario.get(2).add(null);
//		// Result List of the query3
//		scenario.get(2).add(null);
//		// Result List of the query4
//		scenario.get(2).add(null);
//		//The result list of Query5
//		scenario.get(2).add(null);
//		//the result of the Query 5
//		scenario.get(2).add(null);
//		//The Single result of the Query 1
//		scenario.get(2).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(s);
//		// Result of the query1
//		scenario.get(3).add(list);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result of the query4
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("");
//		// Result List of the query2
//		scenario.get(3).add(null);
//		// Result List of the query3
//		scenario.get(3).add(null);
//		// Result List of the query4
//		scenario.get(3).add(null);
//		//The result list of Query5
//		scenario.get(3).add(null);
//		//the result of the Query 5
//		scenario.get(3).add(null);
//		//The Single result of the Query 1
//		scenario.get(3).add(new PermitEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(s);
//		// Result of the query1
//		scenario.get(4).add(list);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result of the query4
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("");
//		// Result List of the query2
//		ArrayList<PermitHomeSiteInfoEntity> list2 = new ArrayList<PermitHomeSiteInfoEntity>();
//		list2.add(null);
//		list2.add(new PermitHomeSiteInfoEntity());
//		scenario.get(4).add(list2);
//		// Result List of the query3
//		scenario.get(4).add(null);
//		// Result List of the query4
//		scenario.get(4).add(null);
//		//The result list of Query5
//		scenario.get(4).add(null);
//		//the result of the Query 5
//		scenario.get(4).add(null);
//		//The Single result of the Query 1
//		scenario.get(4).add(new PermitEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add(s);
//		// Result of the query1
//		scenario.get(5).add(list);
//		// Result of the query2
//		scenario.get(5).add(new PermitHomeSiteInfoEntity());
//		// Result of the query3
//		scenario.get(5).add(null);
//		// Result of the query4
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("");
//		// Result List of the query2
//		scenario.get(5).add(list2);
//		// Result List of the query3
//		scenario.get(5).add(null);
//		// Result List of the query4
//		scenario.get(5).add(null);
//		//The result list of Query5
//		scenario.get(5).add(null);
//		//the result of the Query 5
//		scenario.get(5).add(null);
//		//The Single result of the Query 1
//		scenario.get(5).add(new PermitEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(6).add(s);
//		// Result of the query1
//		scenario.get(6).add(list);
//		// Result of the query2
//		scenario.get(6).add(new PermitHomeSiteInfoEntity());
//		// Result of the query3
//		scenario.get(6).add(null);
//		// Result of the query4
//		scenario.get(6).add(null);
//		// Result excpected
//		scenario.get(6).add("");
//		// Result List of the query2
//		scenario.get(6).add(list2);
//		// Result List of the query3
//		ArrayList<PermitProjectSiteInfoEntity> list3 = new ArrayList<PermitProjectSiteInfoEntity>();
//		list3.add(null);
//		list3.add(new PermitProjectSiteInfoEntity());
//		scenario.get(6).add(list3);
//		// Result List of the query4
//		scenario.get(6).add(null);
//		//The result list of Query5
//		scenario.get(6).add(null);
//		//the result of the Query 5
//		scenario.get(6).add(null);
//		//The Single result of the Query 1
//		scenario.get(6).add(new PermitEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(7).add(s);
//		// Result of the query1
//		scenario.get(7).add(list);
//		// Result of the query2
//		scenario.get(7).add(new PermitHomeSiteInfoEntity());
//		// Result of the query3
//		scenario.get(7).add(new PermitProjectSiteInfoEntity());
//		// Result of the query4
//		scenario.get(7).add(null);
//		// Result excpected
//		scenario.get(7).add("");
//		// Result List of the query2
//		scenario.get(7).add(list2);
//		// Result List of the query3
//		scenario.get(7).add(list3);
//		// Result List of the query4
//		scenario.get(7).add(null);
//		//The result list of Query5
//		scenario.get(7).add(null);
//		//the result of the Query 5
//		scenario.get(7).add(null);
//		//The Single result of the Query 1
//		scenario.get(7).add(new PermitEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(8).add(s);
//		// Result of the query1
//		scenario.get(8).add(list);
//		// Result of the query2
//		scenario.get(8).add(new PermitHomeSiteInfoEntity());
//		// Result of the query3
//		scenario.get(8).add(new PermitProjectSiteInfoEntity());
//		// Result of the query4
//		scenario.get(8).add(null);
//		// Result excpected
//		scenario.get(8).add("");
//		// Result List of the query2
//		scenario.get(8).add(list2);
//		// Result List of the query3
//		scenario.get(8).add(list3);
//		// Result List of the query4
//		ArrayList<PermitCompanyInfoEntity> list4 = new ArrayList<PermitCompanyInfoEntity>();
//		list4.add(null);
//		list4.add(new PermitCompanyInfoEntity());
//		scenario.get(8).add(list4);
//		//The result list of Query5
//		scenario.get(8).add(null);
//		//the result of the Query 5
//		scenario.get(8).add(null);
//		//The Single result of the Query 1
//		scenario.get(8).add(new PermitEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(9).add(s);
//		// Result of the query1
//		scenario.get(9).add(list);
//		// Result of the query2
//		scenario.get(9).add(new PermitHomeSiteInfoEntity());
//		// Result of the query3
//		scenario.get(9).add(new PermitProjectSiteInfoEntity());
//		// Result of the query4
//		scenario.get(9).add(new PermitCompanyInfoEntity());
//		// Result excpected
//		scenario.get(9).add("");
//		// Result List of the query2
//		scenario.get(9).add(list2);
//		// Result List of the query3
//		scenario.get(9).add(list3);
//		// Result List of the query4
//		scenario.get(9).add(list4);
//		//The result list of Query5
//		scenario.get(9).add(null);
//		//the result of the Query 5
//		scenario.get(9).add(null);
//		//The Single result of the Query 1
//		scenario.get(9).add(new PermitEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(10).add(s);
//		// Result of the query1
//		scenario.get(10).add(list);
//		// Result of the query2
//		scenario.get(10).add(new PermitHomeSiteInfoEntity());
//		// Result of the query3
//		scenario.get(10).add(new PermitProjectSiteInfoEntity());
//		// Result of the query4
//		scenario.get(10).add(new PermitCompanyInfoEntity());
//		// Result excpected
//		scenario.get(10).add("");
//		// Result List of the query2
//		scenario.get(10).add(list2);
//		// Result List of the query3
//		scenario.get(10).add(list3);
//		// Result List of the query4
//		scenario.get(10).add(list4);
//		//The result list of Query5
//		ArrayList<PermitArraysEntity> list5 = new ArrayList<PermitArraysEntity>();
//		list5.add(null);
//		list5.add(new PermitArraysEntity());
//		scenario.get(10).add(list5);
//		//the result of the Query 5
//		scenario.get(10).add(null);
//		//The Single result of the Query 1
//		scenario.get(10).add(new PermitEntity());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(11).add(s);
//		// Result of the query1
//		scenario.get(11).add(list);
//		// Result of the query2
//		scenario.get(11).add(new PermitHomeSiteInfoEntity());
//		// Result of the query3
//		scenario.get(11).add(new PermitProjectSiteInfoEntity());
//		// Result of the query4
//		scenario.get(11).add(new PermitCompanyInfoEntity());
//		// Result excpected
//		scenario.get(11).add("");
//		// Result List of the query2
//		scenario.get(11).add(list2);
//		// Result List of the query3
//		scenario.get(11).add(list3);
//		// Result List of the query4
//		scenario.get(11).add(list4);
//		//The result list of Query5
//		scenario.get(11).add(list5);
//		//the result of the Query 5
//		scenario.get(11).add(new PermitArraysEntity());
//		//The Single result of the Query 1
//		scenario.get(11).add(new PermitEntity());
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("Synchronizationproject [ "+i+" ]");
//            when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(11));
//            when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//            when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(3));
//            when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(4));
//            when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(10));
//
//            when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//            when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(6));
//            when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(7));
//            when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(8));
//            when(mockedQuery5.getResultList()).thenReturn((List) scenario.get(i).get(9));
//            siteSurveyService.Synchronizationproject( (SiteSurveyResult) scenario.get(i).get(0));
//		 }
//	}
//
//	@Test
//	public void testSynchronizationSurvey() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitHomeSiteInfoEntity u WHERE u.permitEntity.id = :p1 ")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitProjectSiteInfoEntity u WHERE u.permitEntity.id = :p1 ")).thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitCompanyInfoEntity u WHERE u.permitEntity.id = :p1 ")).thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
//
//		Query mockedQuery6 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitArraysEntity u WHERE u.permitEntity.id = :p1 ")).thenReturn(mockedQuery6);
//		when(mockedQuery6.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery6);
//
//		SiteSurveyService siteSurveyService2 = Mockito.spy(siteSurveyService);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		//Result of the Query2
//		scenario.get(0).add(null);
//		//The Result List of the Query3
//		scenario.get(0).add(null);
//		//The Result Single of the Query3
//		scenario.get(0).add(null);
//		//The Result List of the Query4
//		scenario.get(0).add(null);
//		//The Result Single of the Query4
//		scenario.get(0).add(null);
//		//The Result List of the Query5
//		scenario.get(0).add(null);
//		//The Result Single of the Query5
//		scenario.get(0).add(null);
//		//The Result List of the Query6
//		scenario.get(0).add(null);
//		//The Result Single of the Query6
//		scenario.get(0).add(null);
//		//The result that expected
//		scenario.get(0).add(new SiteSurveyModel());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		//Result of the Query2
//		scenario.get(1).add(null);
//		//The Result List of the Query3
//		scenario.get(1).add(null);
//		//The Result Single of the Query3
//		scenario.get(1).add(null);
//		//The Result List of the Query4
//		scenario.get(1).add(null);
//		//The Result Single of the Query4
//		scenario.get(1).add(null);
//		//The Result List of the Query5
//		scenario.get(1).add(null);
//		//The Result Single of the Query5
//		scenario.get(1).add(null);
//		//The Result List of the Query6
//		scenario.get(1).add(null);
//		//The Result Single of the Query6
//		scenario.get(1).add(null);
//		//The result that expected
//		scenario.get(1).add(new SiteSurveyModel());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		//Result of the Query2
//		scenario.get(2).add(null);
//		//The Result List of the Query3
//		scenario.get(2).add(null);
//		//The Result Single of the Query3
//		scenario.get(2).add(null);
//		//The Result List of the Query4
//		scenario.get(2).add(null);
//		//The Result Single of the Query4
//		scenario.get(2).add(null);
//		//The Result List of the Query5
//		scenario.get(2).add(null);
//		//The Result Single of the Query5
//		scenario.get(2).add(null);
//		//The Result List of the Query6
//		scenario.get(2).add(null);
//		//The Result Single of the Query6
//		scenario.get(2).add(null);
//		//The result that expected
//		scenario.get(2).add(new SiteSurveyModel());
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(null);
//		//Result of the Query2
//		scenario.get(3).add(new PermitEntity());
//		//The Result List of the Query3
//		scenario.get(3).add(null);
//		//The Result Single of the Query3
//		scenario.get(3).add(null);
//		//The Result List of the Query4
//		scenario.get(3).add(null);
//		//The Result Single of the Query4
//		scenario.get(3).add(null);
//		//The Result List of the Query5
//		scenario.get(3).add(null);
//		//The Result Single of the Query5
//		scenario.get(3).add(null);
//		//The Result List of the Query6
//		scenario.get(3).add(null);
//		//The Result Single of the Query6
//		scenario.get(3).add(null);
//		//The result that expected
//		SiteSurveyModel exp1 = new SiteSurveyModel();
//		TimeZone.setDefault (  TimeZone.getTimeZone ("PST")) ;
//		DateTime dt = new DateTime(new Date(),TimeZone.getTimeZone("PST"));
//		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//		isoFormat.setTimeZone(TimeZone.getTimeZone("PST"));
//		Date date = null;
//		try {
//			date = isoFormat.parse(dt+"");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		exp1.setSubmitted(false);
//		exp1.setStatus("Draft");
//		exp1.setCreationDate(date.toString());
//		scenario.get(3).add(exp1);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(new AuthentificationEntity());
//		//Result of the Query2
//		scenario.get(4).add(new PermitEntity());
//		//The Result List of the Query3
//		scenario.get(4).add(null);
//		//The Result Single of the Query3
//		scenario.get(4).add(null);
//		//The Result List of the Query4
//		scenario.get(4).add(null);
//		//The Result Single of the Query4
//		scenario.get(4).add(null);
//		//The Result List of the Query5
//		scenario.get(4).add(null);
//		//The Result Single of the Query5
//		scenario.get(4).add(null);
//		//The Result List of the Query6
//		scenario.get(4).add(null);
//		//The Result Single of the Query6
//		scenario.get(4).add(null);
//		//The result that expected
//		SiteSurveyModel exp2 = new SiteSurveyModel();
//		exp2.setSubmitted(false);
//		exp2.setStatus("Draft");
//		exp2.setCreationDate(date.toString());
//		exp2.setOwnerID(0);
//		scenario.get(4).add(exp2);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("123");
//		scenario.get(5).add("123");
//		// Result of the query1
//		scenario.get(5).add(new AuthentificationEntity());
//		//Result of the Query2
//		scenario.get(5).add(new PermitEntity());
//		//The Result List of the Query3
//		ArrayList<PermitHomeSiteInfoEntity> list1 = new ArrayList<PermitHomeSiteInfoEntity>();
//		list1.add(null);
//		list1.add(new PermitHomeSiteInfoEntity());
//		scenario.get(5).add(list1);
//		//The Result Single of the Query3
//		scenario.get(5).add(null);
//		//The Result List of the Query4
//		scenario.get(5).add(null);
//		//The Result Single of the Query4
//		scenario.get(5).add(null);
//		//The Result List of the Query5
//		scenario.get(5).add(null);
//		//The Result Single of the Query5
//		scenario.get(5).add(null);
//		//The Result List of the Query6
//		scenario.get(5).add(null);
//		//The Result Single of the Query6
//		scenario.get(5).add(null);
//		//The result that expected
//		scenario.get(5).add(exp2);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(6).add("123");
//		scenario.get(6).add("123");
//		// Result of the query1
//		scenario.get(6).add(new AuthentificationEntity());
//		//Result of the Query2
//		scenario.get(6).add(new PermitEntity());
//		//The Result List of the Query3
//		scenario.get(6).add(list1);
//		//The Result Single of the Query3
//		scenario.get(6).add(new PermitHomeSiteInfoEntity());
//		//The Result List of the Query4
//		scenario.get(6).add(null);
//		//The Result Single of the Query4
//		scenario.get(6).add(null);
//		//The Result List of the Query5
//		scenario.get(6).add(null);
//		//The Result Single of the Query5
//		scenario.get(6).add(null);
//		//The Result List of the Query6
//		scenario.get(6).add(null);
//		//The Result Single of the Query6
//		scenario.get(6).add(null);
//		//The result that expected
//		scenario.get(6).add(exp2);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(7).add("123");
//		scenario.get(7).add("123");
//		// Result of the query1
//		scenario.get(7).add(new AuthentificationEntity());
//		//Result of the Query2
//		scenario.get(7).add(new PermitEntity());
//		//The Result List of the Query3
//		scenario.get(7).add(list1);
//		//The Result Single of the Query3
//		scenario.get(7).add(new PermitHomeSiteInfoEntity());
//		//The Result List of the Query4
//		ArrayList<PermitProjectSiteInfoEntity> list2 = new ArrayList<>();
//		list2.add(null);
//		list2.add(new PermitProjectSiteInfoEntity());
//		scenario.get(7).add(list2);
//		//The Result Single of the Query4
//		scenario.get(7).add(null);
//		//The Result List of the Query5
//		scenario.get(7).add(null);
//		//The Result Single of the Query5
//		scenario.get(7).add(null);
//		//The Result List of the Query6
//		scenario.get(7).add(null);
//		//The Result Single of the Query6
//		scenario.get(7).add(null);
//		//The result that expected
//		scenario.get(7).add(exp2);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(8).add("123");
//		scenario.get(8).add("123");
//		// Result of the query1
//		scenario.get(8).add(new AuthentificationEntity());
//		//Result of the Query2
//		scenario.get(8).add(new PermitEntity());
//		//The Result List of the Query3
//		scenario.get(8).add(list1);
//		//The Result Single of the Query3
//		scenario.get(8).add(new PermitHomeSiteInfoEntity());
//		//The Result List of the Query4
//		scenario.get(8).add(list2);
//		//The Result Single of the Query4
//		scenario.get(8).add(new PermitProjectSiteInfoEntity());
//		//The Result List of the Query5
//		scenario.get(8).add(null);
//		//The Result Single of the Query5
//		scenario.get(8).add(null);
//		//The Result List of the Query6
//		scenario.get(8).add(null);
//		//The Result Single of the Query6
//		scenario.get(8).add(null);
//		//The result that expected
//		scenario.get(8).add(exp2);
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("SynchronizationSurvey [ "+i+" ]");
//				Mockito.doReturn(new UserInformationResult()).when(siteSurveyService2)
//				.getUserInformation(Mockito.anyInt());
//            when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//            when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//
//            when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(4));
//            when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(5));
//
//            when(mockedQuery4.getResultList()).thenReturn((List) scenario.get(i).get(6));
//            when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(7));
//
//            when(mockedQuery5.getResultList()).thenReturn((List) scenario.get(i).get(8));
//            when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(9));
//
//            when(mockedQuery6.getResultList()).thenReturn((List) scenario.get(i).get(10));
//            when(mockedQuery6.getSingleResult()).thenReturn(scenario.get(i).get(11));
//         siteSurveyService2.SynchronizationSurvey((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		 }
//
//	}
//
//	@Test
//	public void testcancelSiteSurvey() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SiteSurveyEntity u where u.isCanceled = :p1 and u.id = :p2")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Done");
//		// List Result of the query1
//		scenario.get(0).add(null);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//		// List Result of the query1
//		ArrayList<SiteSurveyEntity> list = new ArrayList<SiteSurveyEntity>();
//		list.add(null);
//		list.add(new SiteSurveyEntity());
//		scenario.get(1).add(list);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(new SiteSurveyEntity());
//		// Result excpected
//		scenario.get(2).add("Done");
//		// List Result of the query1
//		scenario.get(2).add(list);
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("cancelSiteSurvey [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//             siteSurveyService.cancelSiteSurvey( (Integer) scenario.get(i).get(0));
//		 }
//
//	}
//
//    @Test
//	public void testuploadHomePicture()  throws IOException{
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SiteSurveyEntity u where u.id=:p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Fail");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("123");
//		// Result of the query1
//		scenario.get(3).add(new SiteSurveyEntity());
//		// Result excpected
//		scenario.get(3).add("Fail");
//
//
//		scenario.add(new ArrayList<Object>());
//		if(!new File("C:\\files\\orig.png").exists()) {
//			new File("C:\\files\\orig.png").createNewFile();
//		}
//		FileInputStream inputFile = new FileInputStream("C:\\files\\orig.png");
//		// List of the parameter
//		MockMultipartFile file = new MockMultipartFile("file", "NameOfTheFile", "multipart/form-data", inputFile);
//		scenario.get(4).add(file);
//		scenario.get(4).add(null);
//		scenario.get(4).add("123");
//		// Result of the query1
//		scenario.get(4).add(new SiteSurveyEntity());
//		// Result excpected
//		scenario.get(4).add("Done");
//
//
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("uploadHomePicture [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(3));
//             siteSurveyService.uploadHomePicture( (MultipartFile) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2));
//
//		 }
//
//	}
//
//    @Test
//	public void testuploadFormPicture() throws IOException{
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SiteSurveyEntity u where u.id=:p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("1258");
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("1258");
//		scenario.get(3).add("");
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add("1258");
//		scenario.get(4).add("SiteInformation");
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		if(!new File("C:\\files\\orig.png").exists()) {
//			new File("C:\\files\\orig.png").createNewFile();
//		}
//		FileInputStream inputFile = new FileInputStream("C:\\files\\orig.png");
//
//		MockMultipartFile file[] = {null};
//		file[0]= new MockMultipartFile("file", "NameOfTheFile", "multipart/form-data", inputFile);
//		scenario.get(5).add(file);
//		scenario.get(5).add(null);
//		scenario.get(5).add("1258");
//		scenario.get(5).add("SiteInformation");
//		// Result of the query1
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("error");
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(6).add(file);
//		scenario.get(6).add(null);
//		scenario.get(6).add("1258");
//		scenario.get(6).add("SiteInformation");
//		// Result of the query1
//		scenario.get(6).add(new SiteSurveyEntity());
//		// Result excpected
//		 LocalDateTime currentTime = LocalDateTime.now();
//		 String date ="";
//		 if (currentTime.getMonthValue()<10) {
//			   date = "0"+currentTime.getMonthValue()+"-"+currentTime.getYear();
//		 }else  date = currentTime.getMonthValue()+"-"+currentTime.getYear();
//		String exp = "Roof Trusses_0_null_"+date+".png";
//		scenario.get(6).add(exp);
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("uploadHomePicture [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(4));
//            siteSurveyService.uploadFormPicture( (MultipartFile[]) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3));
//
//		 }
//	}
//
//	@Test
//	public void testgetAllProject() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitResponse " +
//				" (u.id, u.homeOwnName, u.advancement, u.status," +
//				" u.creationDate, u.updatedDate, u.submitted) " +
//				" from PermitEntity u where u.isCanceled = :p1 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitResponse " +
//				" (u.id, u.homeOwnName, u.advancement, u.status," +
//				" u.creationDate, u.updatedDate, u.submitted) " +
//				" from PermitEntity u where u.isCanceled = :p1 and u.authentificationEntity.id = :p2")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<PermitResponse>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<PermitResponse>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("1236");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<PermitResponse>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("1236");
//		// Result of the query1
//		AuthentificationEntity auth = new AuthentificationEntity();
//		RoleEntity role = new RoleEntity();
//		role.setId(3);
//		auth.setRoleEntity(role);
//		scenario.get(3).add(auth);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new ArrayList<PermitResponse>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("1236");
//		// Result of the query1
//		scenario.get(4).add(auth);
//		// Result of the query2
//		ArrayList<PermitResponse> list = new ArrayList<PermitResponse>();
//		list.add(null);
//		list.add(new PermitResponse());
//		scenario.get(4).add(list);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(list);
//
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("1236");
//		// Result of the query1
//		AuthentificationEntity auth1 = new AuthentificationEntity();
//		RoleEntity role1 = new RoleEntity();
//		role1.setId(2);
//		auth1.setRoleEntity(role1);
//		scenario.get(5).add(auth1);
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result of the query3
//		scenario.get(5).add(list);
//		// Result excpected
//		scenario.get(5).add(list);
//
//
//		  for(int i=0;i<scenario.size();i++) {
//				 System.out.println("getContractorBatteryFav [ "+i+" ]");
//				 when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//				 when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//				 when(mockedQuery3.getResultList()).thenReturn((List) scenario.get(i).get(3));
//
//	             ArrayList<PermitResponse> result = ((ArrayList<PermitResponse>) siteSurveyService.getAllProject((String) scenario.get(i).get(0)));
//
//
//			 }
//	}
//
//	@Test
//	public void testgetSiteSurveyReqField() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT s FROM SiteSurveyReqFieldSettingEntity s where statereqfield = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(null);
//		 //Result of query1
//		 scenario.get(1).add(new SiteSurveyReqFieldSettingEntity());
//		//Excpected Result
//		 scenario.get(1).add(new SiteSurveyReqFieldSettingEntity());
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getSiteSurveyReqField [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//             SiteSurveyReqFieldSettingEntity rslt = siteSurveyService.getSiteSurveyReqField( (String) scenario.get(i).get(0));
//
//		 }
//	}
//
//
//	@Test
//	public void testgetFiles() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SiteSurveyEntity u where u.id=:p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(null);
//		 scenario.get(1).add("");
//		 //Result of query1
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add("");
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(null);
//		 scenario.get(2).add("125");
//		 //Result of query1
//		 scenario.get(2).add(null);
//		//Excpected Result
//		 scenario.get(2).add("");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add("SubPanel");
//		 scenario.get(3).add("125");
//		 //Result of query1
//		 scenario.get(3).add(null);
//		//Excpected Result
//		 scenario.get(3).add("");
//
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(4).add("SubPanel");
//		 scenario.get(4).add("125");
//		 //Result of query1
//		 scenario.get(4).add(new SiteSurveyEntity());
//		//Excpected Result
//		 scenario.get(4).add(null);
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getFiles [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//             siteSurveyService.getFiles( (String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//
//		 }
//	}
//
//	@Test
//	public void testsaveSiteSurveyReqField() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SiteSurveyReqFieldSettingEntity u where u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("Fail");
//		 //The result list of the Query1
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(new SiteSurveyReqFieldSettingEntity());
//		 //Result of query1
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add("Fail");
//		 //The result list of the Query1
//		 scenario.get(1).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(new SiteSurveyReqFieldSettingEntity());
//		 //Result of query1
//		 scenario.get(2).add(null);
//		//Excpected Result
//		 scenario.get(2).add("Fail");
//		 //The result list of the Query1
//		 ArrayList<SiteSurveyReqFieldSettingEntity> list = new ArrayList<>();
//		 list.add(null);
//		 list.add(new SiteSurveyReqFieldSettingEntity());
//		 scenario.get(2).add(list);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(new SiteSurveyReqFieldSettingEntity());
//		 //Result of query1
//		 scenario.get(3).add(new SiteSurveyReqFieldSettingEntity());
//		//Excpected Result
//		 scenario.get(3).add("Done");
//		 //The result list of the Query1
//		 scenario.get(3).add(list);
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("saveSiteSurveyReqField [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//            siteSurveyService.saveSiteSurveyReqField( (SiteSurveyReqFieldSettingEntity) scenario.get(i).get(0));
//
//		 }
//
//	}
//
//	@Test
//	public void testsaveSiteSurveyReqFieldLogic() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from SiteSurveyReqFieldLogicEntity u where u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add("Fail");
//		 //The result list of the Query1
//		 scenario.get(0).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(new SiteSurveyReqFieldLogicEntity());
//		 //Result of query1
//		 scenario.get(1).add(null);
//		//Excpected Result
//		 scenario.get(1).add("Fail");
//		 //The result list of the Query1
//		 scenario.get(1).add(null);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(2).add(new SiteSurveyReqFieldLogicEntity());
//		 //Result of query1
//		 scenario.get(2).add(null);
//		//Excpected Result
//		 scenario.get(2).add("Fail");
//		 //The result list of the Query1
//		 ArrayList<SiteSurveyReqFieldLogicEntity> list = new ArrayList<>();
//		 list.add(null);
//		 list.add(new SiteSurveyReqFieldLogicEntity());
//		 scenario.get(2).add(list);
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(3).add(new SiteSurveyReqFieldLogicEntity());
//		 //Result of query1
//		 scenario.get(3).add(new SiteSurveyReqFieldLogicEntity());
//		//Excpected Result
//		 scenario.get(3).add("Done");
//		 //The result list of the Query1
//		 scenario.get(3).add(list);
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getSiteSurveyReqLogField [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//             when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//             siteSurveyService.saveSiteSurveyReqFieldLogic( (SiteSurveyReqFieldLogicEntity) scenario.get(i).get(0));
//
//		 }
//	}
//
//	@Test
//	public void testgetSiteSurveyReqLogField() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT s FROM SiteSurveyReqFieldSettingEntity s where statereqfield = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT s FROM SiteSurveyReqFieldLogicEntity s where statereqfield = :p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(0).add(null);
//		 //Result of query1
//		 scenario.get(0).add(null);
//		 //Result of query2
//		 scenario.get(0).add(null);
//		//Excpected Result
//		 scenario.get(0).add(new SiteSurveyFieldSetting());
//
//		 scenario.add(new ArrayList<Object>());
//		 //List of the parameter
//		 scenario.get(1).add(null);
//		 //Result of query1
//		 SiteSurveyReqFieldSettingEntity sc = new SiteSurveyReqFieldSettingEntity();
//		 scenario.get(1).add(sc);
//		 //Result of query2
//		 SiteSurveyReqFieldLogicEntity sc2 = new SiteSurveyReqFieldLogicEntity();
//		 scenario.get(1).add(sc2);
//		//Excpected Result
//		 SiteSurveyFieldSetting exp = new SiteSurveyFieldSetting();
//		 exp.setSiteSurveyReqFieldSettingEntity(sc);
//		 exp.setSiteSurveyReqFieldLogicEntity(sc2);
//		 scenario.get(1).add(exp);
//
//		 for(int i=0;i<scenario.size();i++) {
//			 System.out.println("getSiteSurveyReqLogField [ "+i+" ]");
//             when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//             when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//             siteSurveyService.getSiteSurveyReqLogField( (String) scenario.get(i).get(0));
//
//		 }
//	}
//}
