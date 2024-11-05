//package solar.test;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
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
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.PlayGroundAdv.Solar.Entity.ACCombinerFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.ACCombinerSLC;
//import com.PlayGroundAdv.Solar.Entity.ACDisconnect;
//import com.PlayGroundAdv.Solar.Entity.ACDisconnectFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.AdditionalInfoFiles;
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.Battery;
//import com.PlayGroundAdv.Solar.Entity.BatteryFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.CityEntity;
//import com.PlayGroundAdv.Solar.Entity.Cmodulev2;
//import com.PlayGroundAdv.Solar.Entity.DCCombinerDisconnectEntity;
//import com.PlayGroundAdv.Solar.Entity.DCOptimizerEntity;
//import com.PlayGroundAdv.Solar.Entity.DCOptimizerFavoritEntity;
//import com.PlayGroundAdv.Solar.Entity.DcCombinerorDiscFavoriteEntity;
//import com.PlayGroundAdv.Solar.Entity.DrafterInformationEntity;
//import com.PlayGroundAdv.Solar.Entity.ElectricalUtilityEntity;
//import com.PlayGroundAdv.Solar.Entity.Flashing;
//import com.PlayGroundAdv.Solar.Entity.FlashingFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.InverterLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.Inverters;
//import com.PlayGroundAdv.Solar.Entity.JunctionBoxFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.LeasePPAMeter;
//import com.PlayGroundAdv.Solar.Entity.LeasePPAMeterFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.ModuleLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.PathEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitArraysEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitCompanyInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitDrafterDataEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitLayoutEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitPlansetUploadEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitProjectSiteInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitSketchEntity;
//import com.PlayGroundAdv.Solar.Entity.ProjectsTrackerEntity;
//import com.PlayGroundAdv.Solar.Entity.ProposedSubPanel;
//import com.PlayGroundAdv.Solar.Entity.ProposedSubPanelFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.RFIQuestionEntity;
//import com.PlayGroundAdv.Solar.Entity.RailRacking;
//import com.PlayGroundAdv.Solar.Entity.RailRackingFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.RoofAttachmentFavLibraryEntity;
//import com.PlayGroundAdv.Solar.Entity.RoofAttachmentsEntity;
//import com.PlayGroundAdv.Solar.Entity.RoofTypeandAttachmentEntity;
//import com.PlayGroundAdv.Solar.Entity.TiltLegs;
//import com.PlayGroundAdv.Solar.Entity.TiltLegsFavLibraryEntity;
//import com.PlayGroundAdv.Solar.model.DcCombinerorDisconnectModel;
//import com.PlayGroundAdv.Solar.model.GetFileByIdResults;
//import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
//import com.PlayGroundAdv.Solar.model.GetPlansetByIdResults;
//import com.PlayGroundAdv.Solar.model.InverterFavRequest;
//import com.PlayGroundAdv.Solar.model.LayoutSketchArraysModel;
//import com.PlayGroundAdv.Solar.model.ModuleFavRequest;
//import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
//import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
//import com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResultPrime;
//import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
//import com.PlayGroundAdv.Solar.model.PermitResult;
//import com.PlayGroundAdv.Solar.model.PermitSketchResults;
//import com.PlayGroundAdv.Solar.model.ProjectContactsEmailModel;
//import com.PlayGroundAdv.Solar.model.ProjectEmailModel;
//import com.PlayGroundAdv.Solar.model.SetUserModelRequest;
//import com.PlayGroundAdv.Solar.model.StringModelResult;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.GetBatteryLibraryService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.MailingService;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//import com.PlayGroundAdv.Solar.Services.PermitService;
//import com.PlayGroundAdv.Solar.Services.UserInformationService;
//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
//
//public class TestPermitService {
//	
//	@Mock
//    private EntityManager em;
//	
//	@Spy
//	private CheckValueTypesService checkValueTypesService;
//	
//	@Mock
//	private MailingService mailingService;
//	
//	@Mock
//	private HistoryActivityService historyActivityService;
//	
//
//	@Mock
//	NotificationEntityService notificationEntityService;
//	
//	
//    @InjectMocks
//    PermitService permitService = new PermitService();
//    
//    @Before
//    public void setupMock() {
//    	permitService = new PermitService();
//       MockitoAnnotations.initMocks(this);
//    }
//    
//    @Test
//	public void checkIfProjectIsSubmitted() {
//    	
//    	 ArrayList<String> scenario = new ArrayList<String>();
//		 scenario.add("1");
//		 scenario.add("392075610");
//		 scenario.add("0");
//		 scenario.add("-3");
//		 
//		 for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("checkIfProjectIsSubmitted ["+i+"]");
//			 String idPermit=scenario.get(i);
//			 PermitEntity permit = new PermitEntity();
//			 //permit.setId(scenario.get(i));
//			 PermitEntity permit2 = new PermitEntity();
//			 permit2.setId(Integer.parseInt(scenario.get(i)));
//			 
//			 PermitEntity permit3 = new PermitEntity();
//			 permit3.setId(Integer.parseInt(scenario.get(i)));
//			 permit3.setAdvancement(13+"");
//			 permit3.setCanceled(false);
//			 permit3.setCreationDate("03/06/2019");
//			 permit3.setCreationPermitDate(new Date());
//			 permit3.setDateOfSubmitPermit(new Date());
//			 permit3.setDateOfSubmitPermitCanceled(new Date());
//			 permit3.setDateOfSubmitPermitOnHold(new Date());
//			 permit3.setDeleted(false);
//			 permit3.setExistAcCombiner(true);
//			 permit3.setExistAcDisconnect(false);
//			 permit3.setExistAcJunctionBox(true);
//			 permit3.setExistInverter(false);
//			 permit3.setExistModule(false);
//			 permit3.setHomeOwnName("test name");
//			 permit3.setTypeFile6("file6");
//			 Query mockedQuery = mock(Query.class);
//		    	when(em.createQuery("SELECT u from PermitEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter("p1", Integer.parseInt(idPermit))).thenReturn(mockedQuery);
//		    	when(mockedQuery.getSingleResult()).thenReturn(permit);
//		    	when(mockedQuery.getSingleResult()).thenReturn(permit2);
//		    	when(mockedQuery.getSingleResult()).thenReturn(permit3);
//			 permitService.checkIfProjectIsSubmitted(scenario.get(i));
//		 }
//    	
//    }
//    
//    @Test
//	public void getACDisconnectType() {
//    	
//    	 ArrayList<String> scenario = new ArrayList<String>();
//		 scenario.add("aaa:ccc:ddd");
//		 scenario.add("aaa:bbb:eee");
//		 scenario.add("aaa::eee");
////		 scenario.add("null");
//		 
//		 for(int i = 0; i < scenario.size(); i++) {
//			 
//			 Query mockedQuery = mock(Query.class);
//			 ACDisconnect acDisco=  new ACDisconnect();
//		    	when(em.createQuery("SELECT u from ACDisconnect u WHERE u.manufacturer = :p1 AND u.model =:p2 AND u.isDeleted = :p3")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter("p1", scenario.get(i).split(":")[1])).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter("p2", scenario.get(i).split(":")[2])).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter("p3", false)).thenReturn(mockedQuery);
//		    	when(mockedQuery.getSingleResult()).thenReturn(acDisco);
//		    	permitService.getACDisconnectType(scenario.get(i));
//		 }
//    	
//    }
//    
//    @Test
//	public void saveProject() {
//    	
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//    	scenario.add(new ArrayList<Object>());
//    	GetPermitByIdResult permitModel= new GetPermitByIdResult();
//    	String idUser="1111";
//    	String timeZone="";
//    	String ipUser="";
//    	String numTab="";
//    	String sessionId="";
//    	String openDate="";
//    	scenario.get(0).add(permitModel);
//    	scenario.get(0).add(idUser);
//    	scenario.get(0).add(timeZone);
//    	scenario.get(0).add(ipUser);
//    	scenario.get(0).add(numTab);
//    	scenario.get(0).add(sessionId);
//    	scenario.get(0).add(openDate);
//		 
//		 for(int i = 0; i < scenario.size(); i++) {
//			 
//		    //	permitService.saveProject((GetPermitByIdResult)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(String)scenario.get(i).get(5),(String)scenario.get(i).get(6));
//		 }
//    	
//    }
//    
//    @Test
//  	public void getListManagementFavorites() {
//
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(0).add(null);
//    	scenario.get(0).add(null);
//    	scenario.get(0).add(null);
//    	scenario.get(0).add(null);
//    	scenario.get(0).add(null);
//    	scenario.get(0).add(null);
//    	scenario.get(0).add(null);
//    	scenario.get(0).add(null);
//    	scenario.get(0).add(null);
//    	scenario.get(0).add(null);
//    	scenario.get(0).add(null);
//    	scenario.get(0).add(null);
//    	scenario.get(0).add(null);
//    	scenario.get(0).add(null);
//    	
//		 for(int i = 0; i < scenario.size(); i++) {
//			 
//		    	Query mockedQuery1 = mock(Query.class);
//		    	when(em.createQuery("SELECT v from DcCombinerorDiscFavoriteEntity u,DCCombinerDisconnectEntity v,PermitEntity w,AuthentificationEntity z where w.authentificationEntity.id = z.id AND u.dCCombinerDisconnectEntity.id=v.id AND v.isDeleted = :p2 AND u.authentificationEntity.id=z.id AND w.id= :p1 ORDER BY v.dropdownOption")).thenReturn(mockedQuery1);
//		    	when(mockedQuery1.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery1);
//		    	when(mockedQuery1.getResultList()).thenReturn((List<DCCombinerDisconnectEntity>)scenario.get(i).get(1));
//		    	
//		    	Query mockedQuery2 = mock(Query.class);
//		    	when(em.createQuery("SELECT v from DCOptimizerFavoritEntity u,DCOptimizerEntity v,PermitEntity w,AuthentificationEntity z where w.authentificationEntity.id = z.id AND u.optimizer.id=v.id AND v.isDeleted = :p2 AND u.user.id=z.id AND w.id= :p1 ORDER BY u.optimizer.manufacturer, u.optimizer.model ")).thenReturn(mockedQuery2);
//		    	when(mockedQuery2.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery2);
//		    	when(mockedQuery2.getResultList()).thenReturn((List<DCOptimizerEntity>)scenario.get(i).get(2));
//		    	
//		    	Query mockedQuery3 = mock(Query.class);
//		    	when(em.createQuery("SELECT v from RailRackingFavLibraryEntity u,RailRacking v,PermitEntity w,AuthentificationEntity z "
//						+ "where w.authentificationEntity.id = z.id AND" + " u.railRacking.id=v.id AND" +" v.isDeleted = :p2 AND"
//						+ " u.authentificationEntity.id=z.id AND" + " w.id= :p1 "
//						+ "ORDER BY u.railRacking.manufacturer, u.railRacking.model")).thenReturn(mockedQuery3);
//		    	when(mockedQuery3.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery3);
//		    	when(mockedQuery3.getResultList()).thenReturn((List<RailRacking>)scenario.get(i).get(3));
//		    	
//		    	Query mockedQuery4 = mock(Query.class);
//		    	when(em.createQuery("SELECT v from RoofAttachmentFavLibraryEntity u,RoofAttachmentsEntity v,PermitEntity w,AuthentificationEntity z where w.authentificationEntity.id = z.id AND u.roofAttachment.id=v.id AND v.isDeleted = :p2 AND u.authentificationEntity.id=z.id AND w.id= :p1 ORDER BY u.roofAttachment.manufacturer, u.roofAttachment.model")).thenReturn(mockedQuery4);
//		    	when(mockedQuery4.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery4);
//		    	when(mockedQuery4.getResultList()).thenReturn((List<RoofAttachmentsEntity>)scenario.get(i).get(4));
//		    	
//		    	Query mockedQuery5 = mock(Query.class);
//		    	when(em.createQuery("SELECT v from ACDisconnectFavLibraryEntity u,ACDisconnect v,PermitEntity w,AuthentificationEntity z where w.authentificationEntity.id = z.id AND u.aCDisconnect.id=v.id AND v.isDeleted = :p2 AND u.authentificationEntity.id=z.id AND w.id= :p1 ORDER BY v.dropdownOption")).thenReturn(mockedQuery5);
//		    	when(mockedQuery5.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery5);
//		    	when(mockedQuery5.getResultList()).thenReturn((List<ACDisconnect>)scenario.get(i).get(5));
//		    	
//		    	Query mockedQuery6 = mock(Query.class);
//		    	when(em.createQuery("SELECT v from InverterLibraryEntity u,Inverters v,PermitEntity w,AuthentificationEntity z where w.authentificationEntity.id = z.id AND u.inverters.id=v.id AND v.isDeleted = :p2 AND u.authentificationEntity.id=z.id AND w.id= :p1")).thenReturn(mockedQuery6);
//		    	when(mockedQuery6.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery6);
//		    	when(mockedQuery6.getResultList()).thenReturn((List<ACCombinerSLC>)scenario.get(i).get(6));
//		    	
//		    	Query mockedQuery7 = mock(Query.class);
//		    	when(em.createQuery("SELECT v from ModuleLibraryEntity u,Cmodulev2 v,PermitEntity w,AuthentificationEntity z where w.authentificationEntity.id = z.id AND v.isDeleted = :p2 AND u.cmodulev2.id=v.id AND u.authentificationEntity.id=z.id AND w.id= :p1")).thenReturn(mockedQuery7);
//		    	when(mockedQuery7.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery7);
//		    	when(mockedQuery7.getResultList()).thenReturn((List<Inverters>)scenario.get(i).get(7));
//		    	
//		    	Query mockedQuery8 = mock(Query.class);
//		    	when(em.createQuery("SELECT v from ACCombinerFavLibraryEntity u,ACCombinerSLC v,PermitEntity w,AuthentificationEntity z where w.authentificationEntity.id = z.id AND u.aCCombinerSLC.id=v.id AND v.isDeleted = :p2 AND u.authentificationEntity.id=z.id AND w.id= :p1 ORDER BY v.dropdownOption")).thenReturn(mockedQuery8);
//		    	when(mockedQuery8.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery8);
//		    	when(mockedQuery8.getResultList()).thenReturn((List<Cmodulev2>)scenario.get(i).get(8));
//		    	
//		    	Query mockedQuery9 = mock(Query.class);
//		    	when(em.createQuery("SELECT v from FlashingFavLibraryEntity u,Flashing v,PermitEntity w,AuthentificationEntity z where w.authentificationEntity.id = z.id AND v.isDeleted = :p2 AND u.flashing.id=v.id AND u.authentificationEntity.id=z.id AND w.id= :p1")).thenReturn(mockedQuery9);
//		    	when(mockedQuery9.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery9);
//		    	when(mockedQuery9.getResultList()).thenReturn((List<Flashing>)scenario.get(i).get(9));
//		    	
//		    	Query mockedQuery10 = mock(Query.class);
//		    	when(em.createQuery("SELECT v from LeasePPAMeterFavLibraryEntity u,LeasePPAMeter v,PermitEntity w,AuthentificationEntity z where w.authentificationEntity.id = z.id AND v.isDeleted = :p2 AND u.leasePPAMeter.id=v.id AND u.authentificationEntity.id=z.id AND w.id= :p1")).thenReturn(mockedQuery10);
//		    	when(mockedQuery10.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery10);
//		    	when(mockedQuery10.getResultList()).thenReturn((List<LeasePPAMeter>)scenario.get(i).get(10));
//		    	
//		    	Query mockedQuery11 = mock(Query.class);
//		    	when(em.createQuery("SELECT v from BatteryFavLibraryEntity u,Battery v,PermitEntity w,AuthentificationEntity z where w.authentificationEntity.id = z.id AND v.isDeleted = :p2 AND u.battery.id=v.id AND u.authentificationEntity.id=z.id AND w.id= :p1")).thenReturn(mockedQuery11);
//		    	when(mockedQuery11.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery11);
//		    	when(mockedQuery11.getResultList()).thenReturn((List<Battery>)scenario.get(i).get(11));
//		    	
//		    	Query mockedQuery12 = mock(Query.class);
//		    	when(em.createQuery("SELECT v from TiltLegsFavLibraryEntity u,TiltLegs v,PermitEntity w,AuthentificationEntity z where w.authentificationEntity.id = z.id AND v.isDeleted = :p2 AND u.tiltLegs.id=v.id AND u.authentificationEntity.id=z.id AND w.id= :p1")).thenReturn(mockedQuery12);
//		    	when(mockedQuery12.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery12);
//		    	when(mockedQuery12.getResultList()).thenReturn((List<TiltLegs>)scenario.get(i).get(12));
//		    	
//		    	Query mockedQuery13 = mock(Query.class);
//		    	when(em.createQuery("SELECT v from ProposedSubPanelFavLibraryEntity u,ProposedSubPanel v,PermitEntity w,AuthentificationEntity z where w.authentificationEntity.id = z.id AND v.isDeleted = :p2 AND u.proposedSubPanel.id=v.id AND u.authentificationEntity.id=z.id AND w.id= :p1")).thenReturn(mockedQuery13);
//		    	when(mockedQuery13.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery13);
//		    	when(mockedQuery13.getResultList()).thenReturn((List<ProposedSubPanel>)scenario.get(i).get(13));
//		    	
//		    	permitService.getListManagementFavorites((Integer)scenario.get(i).get(0));
//		    	
//		 }
//    }
//    
//    @Test
//  	public void addNewModule() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("151f");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("15");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		Cmodulev2 module = new Cmodulev2();
//		scenario.get(4).add(module);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add("15");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u.authentificationEntity from PermitEntity u WHERE u.id = :p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getSingleResult()).thenReturn((AuthentificationEntity) scenario.get(i).get(5));
//			when(mockedQuery2.getSingleResult()).thenReturn((AuthentificationEntity) scenario.get(i).get(5));
//			permitService.addNewModule((Cmodulev2) scenario.get(i).get(0), (String) scenario.get(i).get(1),
//					(String) scenario.get(i).get(2), (String) scenario.get(i).get(3), (Integer) scenario.get(i).get(4));
//		}
//	}
//    
//    @Test
//  	public void addNewInverter() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("");
//		InverterFavRequest inverterReq1 = new InverterFavRequest();
//		scenario.get(1).add(inverterReq1);
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("151f");
//		InverterFavRequest inverterReq2 = new InverterFavRequest();
//		scenario.get(2).add(inverterReq2);
//		scenario.get(2).add("151f");
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("15");
//		InverterFavRequest inverterReq3 = new InverterFavRequest();
//		scenario.get(3).add(inverterReq3);
//		scenario.get(3).add("15");
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add("15");
//		InverterFavRequest inverterReq4 = new InverterFavRequest();
//		scenario.get(4).add(inverterReq4);
//		scenario.get(4).add("15");
//		scenario.get(4).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add("15");
//		InverterFavRequest inverterReq5 = new InverterFavRequest();
//		scenario.get(5).add(inverterReq5);
//		scenario.get(5).add("15");
//		scenario.get(5).add(null);
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u.authentificationEntity from PermitEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getSingleResult()).thenReturn((AuthentificationEntity) scenario.get(i).get(3));
//			when(mockedQuery2.getSingleResult()).thenReturn((AuthentificationEntity) scenario.get(i).get(3));
//			permitService.addNewInverter((String) scenario.get(i).get(0), (InverterFavRequest) scenario.get(i).get(1),
//					(String) scenario.get(i).get(2));
//		}
//    }
//    
//    @Test
//  	public void refreshDcCombinerOrDisFavorites() {
//    	
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("ff415");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("7587945");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add("7587945");
//		scenario.get(4).add(null);
//		List<DcCombinerorDiscFavoriteEntity> dcCombinerorDiscFavoriteEntity = new ArrayList<DcCombinerorDiscFavoriteEntity> ();
//		scenario.get(4).add(dcCombinerorDiscFavoriteEntity);
//		
//
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add("7587945");
//		scenario.get(5).add(null);
//		List<DcCombinerorDiscFavoriteEntity> dcCombinerorDiscFavoriteEntity5 = new ArrayList<DcCombinerorDiscFavoriteEntity> ();
//		DcCombinerorDiscFavoriteEntity dcCombinerorDiscFavorite = new DcCombinerorDiscFavoriteEntity();
//		dcCombinerorDiscFavoriteEntity5.add(dcCombinerorDiscFavorite);
//		scenario.get(5).add(dcCombinerorDiscFavoriteEntity5);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add("7587945");
//		scenario.get(6).add(null);
//		List<DcCombinerorDiscFavoriteEntity> dcCombinerorDiscFavoriteEntity6 = new ArrayList<DcCombinerorDiscFavoriteEntity> ();
//		DcCombinerorDiscFavoriteEntity dcCombinerorDiscFavorite6 = new DcCombinerorDiscFavoriteEntity();
//		DCCombinerDisconnectEntity dCCombinerDisconnectEntity6 = new DCCombinerDisconnectEntity();
//		dCCombinerDisconnectEntity6.setManufacturer("manufacturer");
//		dCCombinerDisconnectEntity6.setModel("model");
//		dcCombinerorDiscFavorite6.setdCCombinerDisconnectEntity(dCCombinerDisconnectEntity6);
//		dcCombinerorDiscFavoriteEntity6.add(dcCombinerorDiscFavorite6);
//		scenario.get(6).add(dcCombinerorDiscFavoriteEntity6);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add("7587945");
//		scenario.get(7).add("manufacturer:model");
//		List<DcCombinerorDiscFavoriteEntity> dcCombinerorDiscFavoriteEntity7 = new ArrayList<DcCombinerorDiscFavoriteEntity> ();
//		DcCombinerorDiscFavoriteEntity dcCombinerorDiscFavorite7 = new DcCombinerorDiscFavoriteEntity();
//		DCCombinerDisconnectEntity dCCombinerDisconnectEntity7 = new DCCombinerDisconnectEntity();
//		dCCombinerDisconnectEntity7.setManufacturer("manufacturer");
//		dCCombinerDisconnectEntity7.setModel("model");
//		dcCombinerorDiscFavorite7.setdCCombinerDisconnectEntity(dCCombinerDisconnectEntity7);
//		dcCombinerorDiscFavoriteEntity7.add(dcCombinerorDiscFavorite7);
//		scenario.get(7).add(dcCombinerorDiscFavoriteEntity7);
//		
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u from DcCombinerorDiscFavoriteEntity u  where  u.authentificationEntity.id = :p1"))
//				.thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//		
//		 ArrayList<Boolean> expectedResult =  new ArrayList<Boolean>();
//		 expectedResult.add(false);
//		 expectedResult.add(false);
//		 expectedResult.add(false);
//		 expectedResult.add(false);
//		 expectedResult.add(true);
//		 expectedResult.add(false);
//		 expectedResult.add(true);
//		 expectedResult.add(false);
//		 
//		for (int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getResultList()).thenReturn((List<DcCombinerorDiscFavoriteEntity>) scenario.get(i).get(2));
//			boolean result = permitService.refreshDcCombinerOrDisFavorites((String) scenario.get(i).get(0),
//					(String) scenario.get(i).get(1));
//		}
//		
//    }
//    
//    @Test
//	public void addDcCombOrDisc() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		scenario.get(2).add("14ff");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(null);
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(null);
//		scenario.get(4).add("123");
//		scenario.get(4).add("");
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add(null);
//		scenario.get(5).add("123");
//		scenario.get(5).add("14ff");
//		scenario.get(5).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add(null);
//		scenario.get(6).add("123");
//		scenario.get(6).add("1");
//		scenario.get(6).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		DcCombinerorDisconnectModel dCCombinerDisconnect = new DcCombinerorDisconnectModel();
//		scenario.get(7).add(dCCombinerDisconnect);
//		scenario.get(7).add("123");
//		scenario.get(7).add("1");
//		scenario.get(7).add(null);
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u where u.id = :p1 ")).thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u.authentificationEntity from PermitEntity u WHERE u.id = :p1"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getSingleResult()).thenReturn((AuthentificationEntity) scenario.get(i).get(3));
//			when(mockedQuery2.getSingleResult()).thenReturn((AuthentificationEntity) scenario.get(i).get(3));
//			permitService.addDcCombOrDisc((DcCombinerorDisconnectModel) scenario.get(i).get(0),
//					(String) scenario.get(i).get(1), null, null, (String) scenario.get(i).get(2), null, null, null);
//		}
//	}
//    
//    @Test
//  	public void getDcCombDiscFavorite() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("145g2");
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("14");
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add("14");
//		List<DcCombinerorDiscFavoriteEntity> DcCombinerorDiscFavorite = new  ArrayList<DcCombinerorDiscFavoriteEntity> ();
//		scenario.get(4).add(DcCombinerorDiscFavorite);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add("14");
//		List<DcCombinerorDiscFavoriteEntity> DcCombinerorDiscFavorite5 = new  ArrayList<DcCombinerorDiscFavoriteEntity> ();
//		DcCombinerorDiscFavoriteEntity dcCombinerorDiscFavoriteEntity = new  DcCombinerorDiscFavoriteEntity ();
//		DcCombinerorDiscFavorite5.add(dcCombinerorDiscFavoriteEntity);
//		scenario.get(5).add(DcCombinerorDiscFavorite5);
//		
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from DcCombinerorDiscFavoriteEntity u WHERE u.authentificationEntity.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery2.getResultList()).thenReturn((List<DcCombinerorDiscFavoriteEntity>) scenario.get(i).get(1));
//			permitService.getDcCombDiscFavorite((String) scenario.get(i).get(0));
//		}
//	 
//    }
//    
//    @Test
//  	public void getmoduleFavorite() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("145g2");
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("14");
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add("14");
//		List<ModuleLibraryEntity> ModuleLibraryEntity = new  ArrayList<ModuleLibraryEntity> ();
//		scenario.get(4).add(ModuleLibraryEntity);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add("14");
//		List<ModuleLibraryEntity> moduleLibraryEntity = new  ArrayList<ModuleLibraryEntity> ();
//		ModuleLibraryEntity module = new  ModuleLibraryEntity ();
//		moduleLibraryEntity.add(module);
//		scenario.get(5).add(moduleLibraryEntity);
//		
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from ModuleLibraryEntity u WHERE u.authentificationEntity.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery2.getResultList()).thenReturn((List<ModuleLibraryEntity>) scenario.get(i).get(1));
//			permitService.getmoduleFavorite((String) scenario.get(i).get(0));
//		}
//	 
//	 	
//    }
//    
//    @Test
//  	public void checkdcComDiscExistent() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		DcCombinerorDisconnectModel NewdcCombOrDisc = new DcCombinerorDisconnectModel();
//		scenario.get(1).add(NewdcCombOrDisc);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		DcCombinerorDisconnectModel NewdcCombOrDisc2 = new DcCombinerorDisconnectModel();
//		scenario.get(2).add(NewdcCombOrDisc2);
//		List<DCCombinerDisconnectEntity> listdcComDisc = new ArrayList<DCCombinerDisconnectEntity>();
//		scenario.get(2).add(listdcComDisc);
//		scenario.get(2).add(listdcComDisc);
//		
//		scenario.add(new ArrayList<Object>());
//		DcCombinerorDisconnectModel NewdcCombOrDisc3 = new DcCombinerorDisconnectModel();
//		scenario.get(3).add(NewdcCombOrDisc3);
//		List<DCCombinerDisconnectEntity> listdcComDisc3 = new ArrayList<DCCombinerDisconnectEntity>();
//		DCCombinerDisconnectEntity dcComDisc3 = new DCCombinerDisconnectEntity();
//		listdcComDisc3.add(dcComDisc3);
//		scenario.get(3).add(listdcComDisc3);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		DcCombinerorDisconnectModel NewdcCombOrDisc4 = new DcCombinerorDisconnectModel();
//		scenario.get(4).add(NewdcCombOrDisc4);
//		List<DCCombinerDisconnectEntity> listdcComDisc4 = new ArrayList<DCCombinerDisconnectEntity>();
//		DCCombinerDisconnectEntity dcComDisc4 = new DCCombinerDisconnectEntity();
//		listdcComDisc4.add(dcComDisc4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(listdcComDisc4);
//		
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.model = :p1 AND u.manufacturer = :p2"))
//				.thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.model = :p1 AND u.manufacturer != :p2"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getResultList()).thenReturn((List<DCCombinerDisconnectEntity>) scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List<DCCombinerDisconnectEntity>) scenario.get(i).get(2));
//			permitService.checkdcComDiscExistent((DcCombinerorDisconnectModel) scenario.get(i).get(0),null);
//		}
//	 
//    }
//    
//    @Test
//  	public void checkModuleExistent() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		ModuleFavRequest newmodule = new ModuleFavRequest();
//		scenario.get(1).add(newmodule);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		ModuleFavRequest newmodule2 = new ModuleFavRequest();
//		scenario.get(2).add(newmodule2);
//		List<Cmodulev2> listModule = new ArrayList<Cmodulev2>();
//		scenario.get(2).add(listModule);
//		scenario.get(2).add(listModule);
//		
//		scenario.add(new ArrayList<Object>());
//		ModuleFavRequest newmodule3 = new ModuleFavRequest();
//		scenario.get(3).add(newmodule3);
//		List<Cmodulev2> listModule3 = new ArrayList<Cmodulev2>();
//		Cmodulev2 module3 = new Cmodulev2();
//		listModule3.add(module3);
//		scenario.get(3).add(listModule3);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		ModuleFavRequest newmodule4 = new ModuleFavRequest();
//		scenario.get(4).add(newmodule4);
//		List<Cmodulev2> listmodule4 = new ArrayList<Cmodulev2>();
//		Cmodulev2 module4 = new Cmodulev2();
//		listmodule4.add(module4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(listmodule4);
//		
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u from Cmodulev2 u WHERE u.model = :p1 AND u.make = :p2 AND u.isDeleted= :p3"))
//				.thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from Cmodulev2 u WHERE u.model = :p1 AND u.make != :p2 AND u.isDeleted= :p3"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getResultList()).thenReturn((List<Cmodulev2>) scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List<Cmodulev2>) scenario.get(i).get(2));
//			permitService.checkModuleExistent((ModuleFavRequest) scenario.get(i).get(0),null);
//		}
//    }
//    
//    @Test
//  	public void checkInverterExistent() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		InverterFavRequest NewInverters = new InverterFavRequest();
//		scenario.get(1).add(NewInverters);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		InverterFavRequest NewInverters2 = new InverterFavRequest();
//		scenario.get(2).add(NewInverters2);
//		List<Inverters> listdcComDisc = new ArrayList<Inverters>();
//		scenario.get(2).add(listdcComDisc);
//		scenario.get(2).add(listdcComDisc);
//		
//		scenario.add(new ArrayList<Object>());
//		InverterFavRequest NewInverters3 = new InverterFavRequest();
//		scenario.get(3).add(NewInverters3);
//		List<Inverters> listInverters3 = new ArrayList<Inverters>();
//		Inverters Inverters3 = new Inverters();
//		listInverters3.add(Inverters3);
//		scenario.get(3).add(listInverters3);
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		InverterFavRequest NewInverters4 = new InverterFavRequest();
//		scenario.get(4).add(NewInverters4);
//		List<Inverters> listInverters4 = new ArrayList<Inverters>();
//		Inverters Inverters4 = new Inverters();
//		listInverters4.add(Inverters4);
//		scenario.get(4).add(null);
//		scenario.get(4).add(listInverters4);
//		
//		
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u from Inverters u WHERE u.model = :p1 AND u.make = :p2"))
//				.thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from Inverters u WHERE u.model = :p1 AND u.make != :p2"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(mockedQuery.getResultList()).thenReturn((List<DCCombinerDisconnectEntity>) scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List<DCCombinerDisconnectEntity>) scenario.get(i).get(2));
//			permitService.checkInverterExistent((InverterFavRequest) scenario.get(i).get(0),null);
//		}
//    }
//    
//    @Test
//  	public void editDcCombOrDiscFavoriteList() {
//    	
//		Query mockedQuery = mock(Query.class);
//		when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.id = :p1"))
//				.thenReturn(mockedQuery);
//		when(mockedQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT u from DcCombinerorDiscFavoriteEntity u WHERE u.dCCombinerDisconnectEntity.id = :p1 and u.authentificationEntity.id = :p2"))
//				.thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//		
//		
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//    	
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		//Result of the Query1
//		scenario.get(0).add(null);
//		//Result of the Query2
//		scenario.get(0).add(null);
//		//Result of the Query3
//		scenario.get(0).add(null);
//		//The result expected
//		scenario.get(0).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		//Result of the Query1
//		scenario.get(1).add(null);
//		//Result of the Query2
//		scenario.get(1).add(null);
//		//Result of the Query3
//		scenario.get(1).add(null);
//		//The result expected
//		scenario.get(1).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("123");
//		//Result of the Query1
//		scenario.get(2).add(null);
//		//Result of the Query2
//		scenario.get(2).add(null);
//		//Result of the Query3
//		scenario.get(2).add(null);
//		//The result expected
//		scenario.get(2).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("123");
//		//Result of the Query1
//		scenario.get(3).add(null);
//		//Result of the Query2
//		scenario.get(3).add(null);
//		//Result of the Query3
//		DcCombinerorDiscFavoriteEntity dc = new DcCombinerorDiscFavoriteEntity();
//		dc.setdCCombinerDisconnectEntity(new DCCombinerDisconnectEntity());
//		scenario.get(3).add(dc);
//		//The result expected
//		scenario.get(3).add("Done");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add(null);
//		scenario.get(4).add(true);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add("123");
//		//Result of the Query1
//		scenario.get(4).add(null);
//		//Result of the Query2
//		scenario.get(4).add(null);
//		//Result of the Query3
//		scenario.get(4).add(dc);
//		//The result expected
//		scenario.get(4).add("error");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add(null);
//		scenario.get(5).add(true);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add("123");
//		//Result of the Query1
//		scenario.get(5).add(new DCCombinerDisconnectEntity());
//		//Result of the Query2
//		scenario.get(5).add(null);
//		//Result of the Query3
//		scenario.get(5).add(dc);
//		//The result expected
//		scenario.get(5).add("Done");
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("editDcCombOrDiscFavoriteList [ "+i+" ]");
//			when(mockedQuery.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(6));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			 permitService.editDcCombOrDiscFavoriteList((Integer) scenario.get(i).get(0),(Boolean) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3),(String) scenario.get(i).get(4));
//		}
//    }
//    
//    @Test
//  	public void deleteLayoutDoc() {
//    	Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u  from PathEntity u  where u.id = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//    	Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("DELETE from NoteSketchFiles u where u.permitEntity.id = :p1 and u.fileType=10"))
//		       .thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//		when(mockedQuery2.executeUpdate()).thenReturn(1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		//Result expected
//		scenario.get(0).add("Fail");
//		
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		//Result expected
//		scenario.get(1).add("Fail");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		//Result expected
//		scenario.get(2).add("Success");
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("deleteLayoutDoc [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			permitService.deleteLayoutDoc((String) scenario.get(i).get(0));
//		}
//    }
//    
//    @Test
//  	public void getAskAgainValue() {
//    	Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.askAgain from PermitLayoutEntity u  where u.permitEntity.id = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//    
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the Query1
//		scenario.get(0).add(null);
//		//Result expected
//		scenario.get(0).add(false);
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(1).add("");
//		// Result of the Query1
//		scenario.get(1).add(null);
//		//Result expected
//		scenario.get(1).add(false);
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(2).add("123");
//		// Result of the Query1
//		scenario.get(2).add(null);
//		//Result expected
//		scenario.get(2).add(false);
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(3).add("123");
//		// Result of the Query1
//		scenario.get(3).add(true);
//		//Result expected
//		scenario.get(3).add(true);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getAskAgainValue [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			permitService.getAskAgainValue((String) scenario.get(i).get(0));
//		}
//    }
//    
//    @Test
//  	public void updateAskAgainValue() {
//    	Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitLayoutEntity u  where u.permitEntity.id = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//    
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the Query1
//		scenario.get(0).add(null);
//		//Result expected
//		scenario.get(0).add(false);
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		// Result of the Query1
//		scenario.get(1).add(null);
//		//Result expected
//		scenario.get(1).add(false);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(2).add("123");
//		scenario.get(2).add(null);
//		// Result of the Query1
//		scenario.get(2).add(null);
//		//Result expected
//		scenario.get(2).add(false);
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		// Result of the Query1
//		scenario.get(3).add(new PermitLayoutEntity());
//		//Result expected
//		scenario.get(3).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(4).add("123");
//		scenario.get(4).add(true);
//		// Result of the Query1
//		scenario.get(4).add(new PermitLayoutEntity());
//		//Result expected
//		scenario.get(4).add(true);
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("updateAskAgainValue [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			permitService.updateAskAgainValue((String) scenario.get(i).get(0),(Boolean) scenario.get(i).get(1));
//		}
//    }
//    
//    
//    @Test
//  	public void getModuleDimensions() {
//    	Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from Cmodulev2 u  where u.make = :p1 AND u.model =:p2"))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//    
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the Query1
//		scenario.get(0).add(null);
//		//The result list of the Query1
//		scenario.get(0).add(null);
//		//Result expected
//		scenario.get(0).add("");
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the Query1
//		scenario.get(1).add(null);
//		//The result list of the Query1
//		ArrayList<Cmodulev2> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(1).add(list);
//		//Result expected
//		scenario.get(1).add("");
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the Query1
//		scenario.get(2).add(new Cmodulev2());
//		//The result list of the Query1
//		scenario.get(2).add(list);
//		//Result expected
//		scenario.get(2).add( "null" + '"' + " x " + "null" + '"' + " x " + "null"+ '"');
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getModuleDimensions [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(3));
//			permitService.getModuleDimensions((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//		}
//    }
//    
//    @Test
//  	public void saveDrafterInformation() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u where u.id = :p1"))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from DrafterInformationEntity u where u.idPermit.id = :p1"))
//		       .thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the Query2
//		scenario.get(0).add(null);
//		//Result expected
//		scenario.get(0).add("Success");
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		DrafterInformationEntity draf = new DrafterInformationEntity();
//		draf.setIdPermit(new PermitEntity());
//		scenario.get(1).add(draf);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the Query2
//		scenario.get(1).add(null);
//		//Result expected
//		scenario.get(1).add("Success");
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add(draf);
//		// Result of the query1
//		scenario.get(2).add(new PermitEntity());
//		// Result of the Query2
//		scenario.get(2).add(null);
//		//Result expected
//		scenario.get(2).add("Success");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(3).add(draf);
//		// Result of the query1
//		scenario.get(3).add(new PermitEntity());
//		// Result of the Query2
//		scenario.get(3).add(new DrafterInformationEntity());
//		//Result expected
//		scenario.get(3).add("Success");
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("saveDrafterInformation [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			permitService.saveDrafterInformation((DrafterInformationEntity) scenario.get(i).get(0));
//		}
//    }
//    
//    @Test
//  	public void getDrafterInformation() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from DrafterInformationEntity u where u.idPermit.id = :p1"))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u where u.id = :p1"))
//		       .thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result List of the query1
//		scenario.get(0).add(null);
//		// Result of the Query2
//		scenario.get(0).add(null);
//		//Result expected
//		scenario.get(0).add(new DrafterInformationEntity());
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result List of the query1
//		scenario.get(1).add(null);
//		// Result of the Query2
//		scenario.get(1).add(null);
//		//Result expected
//		scenario.get(1).add(new DrafterInformationEntity());
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add("1258");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result List of the query1
//		scenario.get(2).add(null);
//		// Result of the Query2
//		scenario.get(2).add(null);
//		//Result expected
//		DrafterInformationEntity exp = new DrafterInformationEntity();
//		exp.setDimenrequirOnArr(true);
//		exp.setDraftSepSitePlan(false);
//		exp.setIdPermit(null);
//		exp.setNeedForArackingLayD(true);
//		exp.setRoofFeatCallOut(false);
//		exp.setShowingTransfLocation(false);
//		scenario.get(2).add(exp);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(3).add("1258");
//		// Result of the query1
//		scenario.get(3).add(new DrafterInformationEntity());
//		// Result List of the query1
//		ArrayList<DrafterInformationEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(3).add(list);
//		// Result of the Query2
//		scenario.get(3).add(null);
//		//Result expected
//		scenario.get(3).add(new DrafterInformationEntity());
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getDrafterInformation [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			permitService.getDrafterInformation((String) scenario.get(i).get(0));
//		}
//    }
//    
//    
//    @Test
//  	public void addRFIQuestion() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//    	 scenario.add(new ArrayList<Object>());
//    	 //List of the parameter
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 scenario.get(0).add(null);
//		 //Expected result
//		 scenario.get(0).add("success");
//		 
//		 for(int i = 0; i < scenario.size(); i++) {
//		     System.out.println("addRFIQuestion [ "+i+" ]");
//		    permitService.addRFIQuestion((String)scenario.get(i).get(0), (String)scenario.get(i).get(1),(String)scenario.get(i).get(2));
//		 }
//    }
//    
//    @Test
//	public void typeInverter() {
//    	
//    	List<List<String>> scenario = new ArrayList<List<String>>();
//    	scenario.add(new ArrayList<String>());
//		 scenario.get(0).add("aaa");
//		 scenario.get(0).add("aaa");
//		 scenario.add(new ArrayList<String>());
//		 scenario.get(1).add("");
//		 scenario.get(1).add("bb");
//		 scenario.add(new ArrayList<String>());
//		 scenario.get(2).add("ggg");
//		 scenario.get(2).add("");
//		 scenario.add(new ArrayList<String>());
//		 scenario.get(3).add("testManufacturer:Test");
//		 scenario.get(3).add("testModel:Testtttttttt");
//		 scenario.add(new ArrayList<String>());
//		 scenario.get(4).add("testManufacturer:Test");
//		 scenario.get(4).add(null);
//		 scenario.add(new ArrayList<String>());
//		 scenario.get(5).add(null);
//		 scenario.get(5).add("inverterModelTest");
//		 scenario.add(new ArrayList<String>());
//		 scenario.get(6).add(null);
//		 scenario.get(6).add(null);
//		 scenario.add(new ArrayList<String>());
//		 scenario.get(7).add("");
//		 scenario.get(7).add("");
////		 scenario.add("null");
//		 
//		 for(int i = 0; i < scenario.size(); i++) {
//			 System.out.println("checkIfProjectIsSubmitted ["+scenario.get(i).get(0)+"]");
//			 
//			 Query mockedQuery = mock(Query.class);
//			 Inverters inverters = new Inverters();
//		    	when(em.createQuery("SELECT u" + " from Inverters u " + " where u.model = :p1 " + " and u.make = :p2")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter("p1", scenario.get(i).get(1))).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter("p2", scenario.get(i).get(0))).thenReturn(mockedQuery);
//		    	when(mockedQuery.getSingleResult()).thenReturn(inverters);
//		    	permitService.typeInverter(scenario.get(i).get(0), scenario.get(i).get(1));
//		 }
//    	
//    }
//    
//    @Test
//	public void reopenPermit() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from PermitEntity u " + " where u.id = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//    
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		//List of the Query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("fail");
//	    
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		//List of the Query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("fail");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add("1234");
//		// Result of the query1
//		scenario.get(2).add(null);
//		//List of the Query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("fail");
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(3).add("1234");
//		// Result of the query1
//		scenario.get(3).add(new PermitEntity());
//		//List of the Query1
//		ArrayList<PermitEntity> list = new ArrayList<>();
//		list.add(new PermitEntity());
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add("secces");
//		
//			for (int i = 0; i < scenario.size(); i++) {
//				System.out.println("reopenPermit [ " + i + " ]");
//				when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//				when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//				permitService.reopenPermit((String) scenario.get(i).get(0));
//			}
//    }
//    
//    @Test
//	public void testIntegrated() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from RoofAttachmentsEntity u "	+ " where u.id = :p1 "))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//    
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		//List of the Query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(1).add(123);
//		// Result of the query1
//		scenario.get(1).add(null);
//		//List of the Query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add(223);
//		// Result of the query1
//		scenario.get(2).add(new RoofAttachmentsEntity());
//		//List of the Query1
//		ArrayList<RoofAttachmentsEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(2).add(list);
//		// Result excpected
//		scenario.get(2).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(3).add(323);
//		// Result of the query1
//		RoofAttachmentsEntity r = new RoofAttachmentsEntity();
//		r.setIntegrated("Yes");
//		scenario.get(3).add(r);
//		//List of the Query1
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add("success");
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("testIntegrated [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			permitService.testIntegrated((Integer) scenario.get(i).get(0));
//		}
//    }
//    
//    @Test
//   	public void addPermit() {
//    	Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT (u) " + " from AuthentificationEntity u " + " where u.id = :p1"))
//		       .thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
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
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("It seems that there is a technical problem, please try again.");
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("It seems that there is a technical problem, please try again.");
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(2).add(null);
//		scenario.get(2).add("258");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("enter a name Permit Please ");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//list of the parameter
//		scenario.get(3).add("");
//		scenario.get(3).add("258");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("0");
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("addPermit [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//		//	assertEquals(scenario.get(i).get(8),permitService.addPermit((String) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3),(String) scenario.get(i).get(4),(String) scenario.get(i).get(5),(String) scenario.get(i).get(6)));
//		
//		}
//    }
//    
//    @Test
//   	public void getAllElectricalUtilityCompany() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u "
//				+ " from ElectricalUtilityEntity u where u.zip = :p1 and u.isDeleted = :p2 ORDER BY u.utilityCompanyName")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<ElectricalUtilityEntity> list = new ArrayList<>();
//		list.add(null);
//		list.add(new ElectricalUtilityEntity());
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//		//List of the parameter
//		scenario.get(1).add(null);
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getAllElectricalUtilityCompany [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			permitService.getAllElectricalUtilityCompany((String) scenario.get(i).get(2));
//		
//		} 	
//    }
//    
//    @Test
//   	public void deletPermit() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from PermitEntity u " + " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(" SELECT u  FROM AuthentificationEntity u WHERE u.id = :p1  ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// The result expected
//		scenario.get(0).add("It seems that there is a technical problem, please try again.");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// The result expected
//		scenario.get(1).add("It seems that there is a technical problem, please try again.");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add("");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// The result expected
//		scenario.get(2).add("It seems that there is a technical problem, please try again.");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add("125");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// The result expected
//		scenario.get(3).add("It seems that there is a technical problem, please try again.");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("123");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add("125");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add(new PermitEntity());
//		// Result of the query2
//		scenario.get(4).add(null);
//		// The result expected
//		scenario.get(4).add("It seems that there is a technical problem, please try again.");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add("123");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add("125");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		// Result of the query1
//		scenario.get(5).add(new PermitEntity());
//		// Result of the query2
//		scenario.get(5).add(new AuthentificationEntity());
//		// The result expected
//		scenario.get(5).add("succes");
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("deletPermit [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(8));
//			permitService.deletPermit((String) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3),(String) scenario.get(i).get(4),(String) scenario.get(i).get(5),(String) scenario.get(i).get(6));
//		
//		} 
//    }
//    
//    @Test
//   	public void changeCanceledPermit() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u  " + " from PermitEntity u  " + " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result List of the query1
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result List of the query1
//		scenario.get(1).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result List of the query1
//		scenario.get(2).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("1235");
//		// Result of the query1
//		scenario.get(3).add(new PermitEntity());
//		// Result List of the query1
//		ArrayList<PermitEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(3).add(list);
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("changeCanceledPermit [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			permitService.changeCanceledPermit((String) scenario.get(i).get(0));
//		
//		} 	
//		
//		
//    }
//    
//    @Test
//   	public void getPermitById() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(" Select new com.PlayGroundAdv.Solar.model.PermitLayoutSketchResult( "
//				+ " u.conduitRun, " + " u.showConduitRoofAsHeight, " + " u.conduitRoofter, " + " u.sketchNote, "
//				+ " u.uploadCommentsLayout, " + " u.uploadCommentsAddInfo, " + " u.ignoreVents, "+ " u.firesetbacks, "+ " u.firesetbacksNote, "+ " u.fireVariance, "+ " u.fireVarianceNote, "+ " u.modulesEncroaching "+ " )   "
//				+ " FROM PermitLayoutEntity u  " + "  Where u.permitEntity.id = :p1  ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(" SELECT new com.PlayGroundAdv.Solar.model.PermitDrafterDataResult("
//				+ "  u.scale, " + "  u.totalRoofSquareFootage,  " + "  u.totalArraySectionCount, "
//				+ "  u.scalerackingLayout, " + "  u.scaleelectricalLayout, " + "  u.uploadCommentsParcel,  "
//				+ "  u.uploadCommentsPV, " + "  u.uploadCommentsRacking, " + "  u.uploadCommentsElectrical, "
//				+ "  u.uploadCommentsPlacard, "+ "  u.uploadCommentsAutoCad, " + "  u.stanchionQuantity )  "
//				+ "  From PermitDrafterDataEntity u " + "  Where u.permitEntity.id = :p1  ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//		
//		
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitEngineerEntityResult  "
//				+ " (  u.applicablEngineering,  " + " u.name,  " + " u.email,  " + " u.mobile,  "
//				+ " u.phone,  " + " u.licenceNumber,  " + " u.licenceType,  " + " u.city,  "
//				+ " u.state,  " + " u.codePostale,  " + " u.engineeredBy,  "
//				+ " u.determineModification,  " + " u.isShingles,  " + " u.indicateLayers,  "
//				+ " u.mpptTrachers,  " + " u.NumberMpptTrachers,  "
//				+ " u.NumberStringFirstMpptTrachers,  " + " u.NumberStringSecondMpptTrachers,  "
//				+ " u.NumberModuleStringFirstMpptTrachers,  "
//				+ " u.NumberModuleStringSecondMpptTrachers,  " + " u.isTransformless,  "
//				+ " u.numberInputTransformless,  " + " u.isCombiner,  " + " u.numberInputCombiner,  "
//				+ " u.overhangArea,  " + " u.roofPitch, " + " u.adressIng   )  "
//				+ " FROM  PermitEngineerEntity u " + " WHERE u.permitEntity.id = :p1 ")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery3);
//		
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult  "
//				+ " ( u.costProject,    " + " u.costModules,      " + " u.costRacking,      "
//				+ " u.packagePayment,   " + " u.formatSize,       " + " u.batteryStorage,   "
//				+ " u.tiltLegs,  " + " u.MainServicePanelAMPRating," + " u.tiltLegsManufacturer , "
//				+ " u.tiltLegsModel , " + " u.batteryManufacturer , " + " u.batteryModel , "
//				+ " u.quantityBatteries , " + " u.voltageConfiguration , "
//				+ " u.batteryEnclosureManufacturer , " + " u.batteryEnclosureModel , "
//				+ " u.determineModificationsToRoofing , " + " u.lineSideTap, " + " u.typeConnection, "
//				+ " u.typeConnectionOther, " + " u.informationCovered, " + " u.uploadComments, " + " u.requiredElectricalStamp, " +" u.battery, "+" u.tiltLegsMod, "+" u.gridTiedOrStandalone " + " ) "
//				+ " from PermitAdditionalInfoEntity u " + " where u.permitEntity.id = :p1 ")).thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery4);
//		
//		
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitAdvEntityResult    "
//				+ " (u.windSpeed, " + " u.snowLoad, " + " u.googleImage, " + " u.mapImage, " + " u.pVRailQte, "
//				+ " u.pVRailLength, " + " u.stanchionQte, " + " u.stanchionLength, " + " u.spliceQte, "
//				+ " u.spliceLength, " + " u.s200Qte, " + " u.s200Length, " + " u.pv1, "
//				+ " u.customersServiceAgreementIDNumber, " + " u.customersRateSchedule, "
//				+ " u.engineeringFirm, " + " u.customersAccountNumber, " + " u.customerName, "
//				+ " u.windSpeedOther, " + " u.snowLoadOther," + " u.uploadCommentsGoogle, "
//				+ " u.uploadCommentsNearMap," + " u.moduleLayout," + " u.sizeOfPipe," 
//				+ " u.thicknessOfPipe," + " u.bracedUnbraced," + " u.footingDiameter," 
//				+ " u.moduleLayoutOther," + " u.sizeOfPipeOther," + " u.thicknessOfPipeOther," 
//				+ " u.footingDiameterOther," + " u.openTldLibrary," + " u.tldShortList," + " u.tldList," + " u.rSheetList," + " u.bracedUnbracedOther )" 
//				+ " from PermitAdvEntity u  "
//				+ " where u.permitEntity.id = :p1 ")).thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery5);
//		
//		
//		Query mockedQuery6 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond  "
//				+ " (u.systemType, " + " u.RequestQuote, " + " u.deviceToIncorporate, " + " u.pvModuleModEl, "
//				+ " u.inverterModel, " + " u.stringOne, " + " u.stringTwo, " + " u.stringThree, "
//				+ " u.stringFour, " + " u.stringFive,  " + " u.secondInverterModel, " + " u.secondStringOne, "
//				+ " u.secondStringTwo, " + " u.secondStringThree, " + " u.secondStringFour, "
//				+ " u.secondStringFive, " + " u.roofMounted, " + " u.GroundMounted, " + " u.PoleMounted, "
//				+ " u.otherMounted, " + " u.textOther,  " + " u.BatteryManufacturerTrojan,  "
//				+ " u.BatteryManufacturerMMK,  " + " u.BatteryManufacturerUPG,  "
//				+ " u.BatteryManufacturerRolls,  " + " u.BatteryManufacturerCrown,  "
//				+ " u.BatteryManufacturerTesla,  " + " u.BatteryManufacturerOutback,  "
//				+ " u.BatteryManufacturerFullriver,  " + " u.BatteryManufacturerConcord,  "
//				+ " u.BatteryManufacturerOther,  " + " u.textBatteryOther, " + " u.qteOfBattery, "
//				+ " u.systemOptimizerModel," + " u.thirdInverterModel, " + " u.thirdStringOne, "
//				+ " u.thirdStringTwo, " + " u.thirdStringThree, " + " u.thirdStringFour, "
//				+ " u.thirdStringFive, " + " u.fourthInverterModel, " + " u.fourthStringOne, "
//				+ " u.fourthStringTwo, " + " u.fourthStringThree, " + " u.fourthStringFour, "
//				+ " u.fourthStringFive, " + " u.fifthInverterModel, " + " u.fifthStringOne, "
//				+ " u.fifthStringTwo, " + " u.fifthStringThree, " + " u.fifthStringFour, "
//				+ " u.fifthStringFive, " + " u.microInverterManufacturer, " + " u.microInverterModel, "
//				+ " u.numberModulesACCircuitOne, " + " u.numberModulesACCircuitTwo, "
//				+ " u.numberModulesACCircuitThree, " + " u.numberModulesACCircuitFour, "
//				+ " u.numberModulesACCircuitFive, " + " u.numberModulesACCircuitSix, "
//				+ " u.numberModulesACCircuitSeven, " + " u.numberModulesACCircuitEight, "
//				+ " u.numberModulesACCircuitNine, " + " u.numberModulesACCircuitTen, "
//				+ " u.numberModulesACCircuitEleven, " + " u.numberModulesACCircuitTweleve,"
//				+ " u.carportMounted," + " u.carportMountedAttached," + " u.carportMountedFreestanding,"
//				+ " u.ocpdOne," + " u.ocpdTwo," + " u.ocpdThree," + " u.ocpdFour," + " u.ocpdFive,"
//				+ " u.ocpdSix," + " u.ocpdSeven," + " u.ocpdEight," + " u.ocpdNine," + " u.ocpdTen,"
//				+ " u.ocpdEleven, " + " u.ocpdTwelve, " 
//				+ " u.uploadCommentsLayout, "
//				+ " u.uploadCommentsAddInfo, "+ " u.inverterLocation, "+ " u.inverterLocationOther, "+ " u.inverterSameLocation, "
//				+ " u.numberModulesACCircuitThirteen," + " u.numberModulesACCircuitFourteen," + " u.numberModulesACCircuitFifteen," + " u.numberModulesACCircuitSixteen," + " u.numberModulesACCircuitSeventeen," 
//				+ " u.numberModulesACCircuitEightteen," + " u.numberModulesACCircuitNineteen,"  + " u.numberModulesACCircuitTwenty," + " u.numberModulesACCircuitTwentyOne," + " u.numberModulesACCircuitTwentyTwo,"
//				+ " u.numberModulesACCircuitTwentyThree," + " u.numberModulesACCircuitTwentyFour,"
//				+ " u.ocpdThirteen," + " u.ocpdFourteen," + " u.ocpdFifteen," + " u.ocpdSixteen," + " u.ocpdSeventeen,"
//				+ " u.ocpdEightteen," + " u.ocpdNineteen," + " u.ocpdTwenty," + " u.ocpdTwentyOne," + " u.ocpdTwentyTwo,"
//				+ " u.ocpdTwentyThree," + " u.ocpdTwentyFour,"
//				+ " u.stringSix,"+" u.stringSeven,"+" u.stringEight,"+" u.stringNine,"+" u.stringTen,"+" u.stringEleven,"+" u.stringTwelve,"
//				+" u.secondStringSix,"+" u.secondStringSeven,"+" u.secondStringEight,"+" u.secondStringNine,"+" u.secondStringTen,"+" u.secondStringEleven,"+" u.secondStringTwelve,"
//				+" u.thirdStringSix,"+" u.thirdStringSeven,"+" u.thirdStringEight,"+" u.thirdStringNine,"+" u.thirdStringTen,"+" u.thirdStringEleven,"+" u.thirdStringTwelve,"
//				+" u.fourthStringSix,"+" u.fourthStringSeven,"+" u.fourthStringEight,"+" u.fourthStringNine,"+" u.fourthStringTen,"+" u.fourthStringEleven,"+" u.fourthStringTwelve,"
//				+" u.fifthStringSix,"+" u.fifthStringSeven,"+" u.fifthStringEight,"+" u.fifthStringNine,"+" u.fifthStringTen,"+" u.fifthStringEleven,"+" u.fifthStringTwelve, u.microInverter, u.roofOrOpenFrame, u.circuitUnderGround, u.inverterInstalledOnRoof  "
//				+ ") " + " from PermitArraysEntity u  "
//				+ " where u.permitEntity.id = :p1 ")).thenReturn(mockedQuery6);
//		when(mockedQuery6.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery6);
//		
//		
//		Query mockedQuery7 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResultPrime  "
//				+ " ( u.meterNumber, " + " u.accountNumber,  " + " u.serviceAgreement, "
//				+ " u.existingRate, " + " u.newRate, " + " u.costPaid, " + " u.claimedFederal, "
//				+ " u.nameDeveloper, " + " u.checkApply, " + " u.kwhUsage, "
//				+ " u.authorizatingAdvanced, " + " u.contactHomeowner, " + " u.scir, "
//				+ " u.systemOwner, " + " u.paceFinanced, " + " u.meterAccess, " + " u.plannedAnnual, "
//				+ " u.newService, " + " u.newSubdivition, " + " u.nonProfileStatus, "
//				+ " u.systemMeetDIH, " + " u.contactType,   " + " u.meterDisco,     "
//				+ " u.meterBuilding,    " + " u.undertrainedAnimal,  " + " u.otherSystem,  "
//				+ " u.otherSystemText," + " u.jBoxUsedBetween," + " u.developersName, "
//				+ "	u.developmentName,  " + "	u.InterconnectionType, "
//				+ "	u.peaceFinancedByEntity, " + "	u.namePartyReceivData," + " u.textOtherRate,"
//				+ " u.textOtherNewRate," + " u.customerParticipated, "
//				+ " u.peaceFinancedByEntityOther, " + " u.typeCustomer, " + " u.typeCustomerOther, "
//				+ " u.typeCustomerOtherText, " + " u.newRateCommercial, "
//				+ " u.otherNewRateCommercial, " + " u.snemApplication, " + " u.thisIsNewService, "
//				+ " u.developerrsNam, " + " u.developersNameSecond, " + " u.theProjectIsLocated, "
//				+ " u.developemName, " + " u.projectIs, " + " u.otherProjectIs, "
//				+ " u.projectWasPace, " + " u.choosePaceFinanced, " + " u.textOtherChoosePace, "
//				+ " u.electriccvehichule1, " + " u.electricvehichule2, " + " u.electricvehichule3, "
//				+ " u.electricvehichule4, " + " u.electricvehichuleOther, " + " u.otherElectricVe, "
//				+ " u.applicationType, " + " u.theAcDisconnectIsMoreThan, " + " u.coverageamount, "
//				+ " u.insuringcompanyName, " + " u.expectedDate, " + " u.oneOrMoreOfTheAss, "
//				+ " u.theLocalPermitting, " + " u.requestPGToshutDown, "
//				+ " u.RequestPGaEToInstalNewS, " + " u.nameOfDevelopersLease, "
//				+ " u.clamedfederalIncomeTax, " + " u.whichProgram, " + " u.whatRuleOrRules, "
//				+ " u.deEnergizingOfTheService, " + " u.whatDayOfTheWeek, " + " u.whatTimeOfDay, "
//				+ " u.willYouNeedToObtain, " + " u.describeThePointInt,  " + " u.theScirOfTheMain, "
//				+ " u.policy, " + " u.howManyHours, " + " u.developerrrsName, "
//				+ " u.PGaEPersonnelWilleNeed, " + " u.mayOurRepresentativesContact, "
//				+ " u.iAuthorizeAdvanced, " + " u.meterOrACDisconnectInBuilding, "
//				+ " u.unrestrainedAnimal, " + " u.checkTheApplicableOther, "
//				+ " u.checkApplicableBoxesOther, " + " u.checkIfTheProposedSystemProduce, "
//				+ " u.performanceMonitAndRep, " + " u.anExistingSolarOrWind, "
//				+ " u.clamedfederalIncomeTaxStr, " + " u.listAnyVariations, "
//				+ " u.textOtherContractType, " + " u.newRateOthers, " + " u.newRateOthersText, "
//				+ " u.uploadCommentsUtility, " + " u.uploadFileExisting,"
//				+ " u.uploadFileJustification," + " u.uploadFileListAdreess,"
//				+ " u.uploadFileSwitchgear," + " u.uploadFileInterconnection,"
//				+ " u.opened, u.openedBy.id, u.hasEditRequest  ) "
//				+ " from PermitCompanyInfoEntity u " + " where u.permitEntity.id = :p1 ")).thenReturn(mockedQuery7);
//		when(mockedQuery7.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery7);
//		
//		Query mockedQuery8 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult  "
//				+ " ( u.UtilityCompanyName, " + " u.siteAddress, " + " u.addressLine2, " + " u.city, "
//				+ " u.state, " + " u.postalCode, " + " u.secondaryAddress, " + " u.secondaryAddressLine2, "
//				+ " u.secondaryCity, " + " u.secondaryState, " + " u.secondaryPostalCode, " + " u.homePhone, "
//				+ " u.cellPhone, " + " u.otherPhone, " + " u.emailPhone,  " + " u.propertyAPN,  "
//				+ " u.footageStructure,   " + " u.residenceBindingCategory,   " + " u.constructionType,  "
//				+ " u.roofRafter,   " + " u.buildingRisk,   " + " u.buildingOccupancy,   "
//				+ " u.permitEntity.homeOwnName, " + " u.textOtherConst, " + " u.textOtherExpo, "
//				+ " u.textOtherBuildOccup," + " u.textOtherBuild, " + " u.sameMailing, " + " u.serviceVoltage, "
//				+ " u.serviceVoltageOther, " + " u.ifServiceVoltage, " + " u.ridgeBeamDepthAtArrays, "
//				+ " u.maxHorizontalSpanAtArrays, " + " u.maxHorizontalSpanAtArraysHS, "
//				+ " u.maxHorizontalSpanAtArraysInches, " + " u.maxHorizontalSpanAtArraysHSInches, "
//				+ " u.buildingRiskOther, " + " u.stanchionMaxSpacing, " + " u.stanchionMaxSpacingOther, "
//				+ " u.ridgeBeamDepthAtArraysOther, " + " u.UtilityCompanyNameOther, " + " u.cityOther, "
//				+ " u.projectJurisdiction, " + " u.projectJurisOther, " + " u.secondaryCityOther, " + " u.secroofRafterOther, " + " u.roofRafterOther " + " ) "
//				+ " from PermitHomeSiteInfoEntity u " + " where u.permitEntity.id = :p1 ")).thenReturn(mockedQuery8);
//		when(mockedQuery8.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery8);
//		
//		Query mockedQuery9 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo  "
//				+ "(   u.railRakingModel, " + "   u.trackingSystemManufacturer, "
//				+ "   u.trackingSystemModel, " + "   u.inverterModel, " + "   u.rafterTrussSpacing, "
//				+ "   u.crossSectionSize, " + "   u.spanBetweenAttachment, " + "   u.roofMaterialType, "
//				+ "   u.roofMaterialTypeOther, " + "   u.rankingRoofManufacturer, "
//				+ "   u.rankingRoofModel, " + "   u.moduleGrounding, " + "   u.disconnectManufacturer, "
//				+ "   u.disconnectModel, " + "   u.quantityRooftop, " + "   u.solarLocation, "
//				+ "   u.mainPanelUpgrade, " + "   u.panelBusRating, " + "   u.solarInterconnection, "
//				+ "   u.secondSolarInterconnection, " + "   u.useDisconectSwith, "
//				+ "   u.usedByInverterManufacturer, " + "   u.usedRevenue, "
//				+ "   u.SubPanelMainBreakerRating, " + "   u.SubPanelBusRating, "
//				+ "   u.panelExistingProposed, " + "   u.quantityofCombinerBox, "
//				+ "   u.quantityofCombinerBoxOther, " + "   u.trackingSystemManufacturerOther, "
//				+ "   u.trackingSystemModelOther, " + "   u.rooftopACCombinerModel, "
//				+ "   u.textOtherSize, " + "   u.ACDisconnectSwitchManufacturer, "
//				+ "   u.ACDisconnectSwitchModel, " + "   u.ACDisconnectSwitchManufacturerOther, "
//				+ "   u.ACDisconnectSwitchModelOther, " + "   u.DCDisconnectSwitchManufacturer, "
//				+ "   u.DCDisconnectSwitchModel, " + "   u.LeasePPAMeterManufacturer, "
//				+ "   u.LeasePPAMeterModel, " + "   u.trackingSystemManufacturerForSecondTracker, "
//				+ "   u.trackingSystemManufacturerForSecondTrackerOther, "
//				+ "   u.trackingSystemModelForSecondTracker, "
//				+ "   u.trackingSystemModelForSecondTrackerOther, "
//				+ "   u.rankingRoofManufacturerOther, " + "   u.rankingRoofModelOther, "
//				+ "   u.moduleGroundingOther, " + "   u.disconnectManufacturerOther, "
//				+ "   u.disconnectModelOther, " + "   u.railConnectionModel, "
//				+ "   u.atticJBoxesbeUtilized, " + "   u.descriptionOfBackFeed, "
//				+ "   u.groundLevelACDisconnectEnclosure, " + "   u.panelBusRatingOther, "
//				+ "   u.panelMainBreakerRating, " + "   u.panelMainBreakerRatingOther, "
//				+ "   u.solarInterconnectionOther, " + "   u.secondSolarInterconnectionOther, "
//				+ "   u.combiningACCircuits, " + "   u.morInterconnectingBackFeed, "
//				+ "   u.railRakingModelforGroundMounted, " + "   u.railRakingModelforPoleMounted, "
//				+ "   u.sizeAndTypeAtticJBox, " + "   u.sizeAndTypeAtticJBoxOther, "
//				+ "   u.ifApplicableSubPanelMainBreakerRating, " + "   u.proposedSubPanelManufacturer, "
//				+ "   u.proposedSubPanelModel, " + "   u.proposedSubPanelModelOther, "
//				+ "   u.groundLevelACCombinerBoxModel, " + "   u.groundLevelACCombinerDisconnectModel, "
//				+ "   u.groundLevelACJunctionBoxManufacturer, " + "   u.groundLevelACJunctionBoxModel, "
//				+ "   u.equipmentRoofMountedACCombinerBox, "
//				+ "   u.equipmentRoofMountedACCombinerDisconnect, "
//				+ "   u.equipmentRoofMountedJunctionBox, " + "   u.equipmentRoofMountedSingleCircuit, "
//				+ "   u.equipmentGroundLevelACCombinerBox, "
//				+ "   u.equipmentGroundLevelACCombinerDisconnect, "
//				+ "   u.equipmentGroundLevelACSubPanel, " + "   u.equipmentGroundLevelACJunctionBox, "
//				+ "   u.equipmentCombiningInExistingSubPanel, "
//				+ "   u.equipmentCombiningInProposedSubPanel, "
//				+ "   u.equipmentCombiningInMainPanel,  " + "   u.equipmentisOther,   "
//				+ "   u.equipmentOther, " + "   u.roofMountedACCombinerBoxManufacturer, "
//				+ "   u.roofMountedACCombinerBoxManufacturerOther, "
//				+ "   u.roofMountedACCombinerBoxModel, " + "   u.roofMountedACCombinerBoxModelOther, "
//				+ "   u.roofMountedACCombiningDisconnectManufacturer, "
//				+ "   u.roofMountedACCombiningDisconnectManufacturerOther, "
//				+ "   u.roofMountedACCombiningDisconnectModel, "
//				+ "   u.roofMountedACCombiningDisconnectModelOther, "
//				+ "   u.roofMountedACJunctionBoxManufacturer, "
//				+ "   u.roofMountedACJunctionBoxManufacturerOther, "
//				+ "   u.roofMountedACJunctionBoxModel, " + "   u.roofMountedACJunctionBoxModelOther, "
//				+ "   u.roofMountedSingleCircuitACDisconnectManufacturer, "
//				+ "   u.roofMountedSingleCircuitACDisconnectManufacturerOther, "
//				+ "   u.roofMountedSingleCircuitACDisconnectModel, "
//				+ "   u.roofMountedSingleCircuitACDisconnectModelOther, " + "   u.equipmenModelOther, "
//				+ "   u.equipmenManufacturerOther, " + "   u.proposedMainPanelManufacturer, "
//				+ "   u.proposedMainPanelManufacturerOther, " + "   u.proposedMainPanelModel, "
//				+ "   u.proposedMainPanelModelOther, " + "   u.deratingthisPanelString,  "
//				+ "   u.groundLevelACJunctionBoxManufacturerOther, "
//				+ "   u.groundLevelACJunctionBoxModelOther, " + "   u.subPanelBreakerOCPD,  "
//				+ "   u.MainBreakerLocatedEndBusBar,  " + "   u.installationGuidelines,   "
//				+ "   u.textOtherRatfter,   " + "   u.disconnectManufacturerTwo,   "
//				+ "   u.disconnectManufacturerOtherTwo,   " + "   u.disconnectModelTwo,   "
//				+ "   u.disconnectModelTwoOther,   "
//
//				+ "   u.disconnectManufacturerThree,   " + "   u.disconnectManufacturerThreeOther,   "
//				+ "   u.disconnectModelThree,   " + "   u.disconnectModelThreeOther,   "
//
//				+ "   u.rooftopACCombinerModelTwo,   "
//
//				+ "   u.tallStructure, " + "   u.otherTallStructure, " + "   u.meanHeight, "
//				+ "   u.existingMainPanelManufac,   " + "   u.existingMainPanelManufacOther,   "
//				+ "   u.groundLevelACJunctionBoxManufacturerString,   "
//				+ "   u.groundLevelACJunctionBoxManufacturerStringOther,   "
//				+ "   u.groundLevelACJunctionBoxModelString,   "
//				+ "   u.groundLevelACJunctionBoxModelStringOther,  "
//				+ "   u.groundLevelACSubPanelManufacturer,  " + "   u.groundLevelACSubPanelModel,  "
//				+ "   u.groundLevelACJunctionBoxManufactuereOtherText,  "
//				+ "   u.groundLevelACJunctionBoxModelOtherText,  "
//				+ "   u.proposedSubPanelManufacturerOther,  " + "   u.solarLocationOther,  "
//				+ "   u.LeasePPAMeterModelOther,  " + "   u.LeasePPAMeterManufacturerOther,  "
//				+ "   u.SubPanelBusRatingOther,  " + "   u.subPanelBreakerOCPDOther,  "
//				+ "   u.location,  " + "   u.locationTwo,  " + "   u.locationThree,  "
//				+ "   u.installingDCBo,  " + "   u.dcJboxType,  " + "   u.locationDC,  "
//				+ "   u.locationFive,  " + "   u.locationSix,  " + "   u.locationFour,  "
//				+ "   u.proposedMainPanMan,  " + "   u.thirdSolarInterconnection,  "
//				+ "   u.fourthSolarInterconnection,  " + "   u.fifthSolarInterconnection,  "
//				+ "   u.thirdSolarInterconnectionOther,  " + "   u.fourthSolarInterconnectionOther,  "
//				+ "   u.fifthSolarInterconnectionOther,  " + "   u.thepontOfTheC,  "
//				+ "   u.connectionPoint,  " + "   u.thepontOfTheCOther,  " + "   u.dcJboxTypeOther,  "
//				+ "   u.panelLocation, " + "   u.disconnectLocation, " + "   u.uploadComments, "  
//				+ "   u.roofTopACCombinerDisconnect, "  + "   u.installRoofTopACDiscoCombiner, "  
//				+ "   u.installRoofTopDcJbox, "  + "   u.roofTopDcJboxType, " + " u.msphasNoBranchCircuitBreakers, "
//				+ " u.proposedACCombMainBreaker, "+" u.proposedACCombMainBreakerRating, "+"u.proposedACCombMainBreakerRatingOther, "
//				+"u.microInverterCabling,"+" u.roofTopJbox, "+" u.roofTopACDisco, "+" u.roofTopACCombiner, "+" u.transitioningPVWireIn, "
//				+" u.roofTopJboxDC, "+" u.roofTopDCDisco, "+" u.roofTopDCCombiner, "+" u.qtyIndependentACDisco, "+" u.flashing, "
//				+" u.leasePPAMeter, "+ "u.proposedSubPanel, u.installingACCombiner, u.aCCombinerInstalled, "
//				+ "u.northToShouthFin, u.northToShouthFinOther, u.heightOfSouth, u.subPanelConductorSizing, u.subPanelConductorSize, u.subPanelConductorSizeOther, u.subPanelConductorSizeNote, u.subPanelConductorSizeFiles ) "
//				+ " from PermitProjectSiteInfoEntity u " + " where u.permitEntity.id = :p1 ")).thenReturn(mockedQuery9);
//		when(mockedQuery9.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery9);
//		
//		Query mockedQuery10 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult  "
//				+ " (   u.elevation,  " + " u.quatrePourCentAverageHigh,  " + " u.deuxPourCentAverageHigh,  "
//				+ " u.extremeMinimum, "+ " u.quatrePourCentAvHighOther, "+ " u.deuxPourCentAverageHighOther, "+ " u.extremeMinimumOther ) " + " from PermitWeatherEntity u " + " where u.permitEntity.id = :p1 ")).thenReturn(mockedQuery10);
//		when(mockedQuery10.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery10);
//		
//		
//		Query mockedQuery11 = mock(Query.class);
//		when(em.createQuery("Select u  " + " from PermitEntity u  " + " where u.id = :p1 ")).thenReturn(mockedQuery11);
//		when(mockedQuery11.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery11);
//		
//		Query mockedQuery12 = mock(Query.class);
//		when(em.createQuery(
//				"SELECT new com.PlayGroundAdv.Solar.model.PermitConduitConductorSectionEntitieResult  "
//						+ " ( u.imgSegmentOne, " + " u.imgSegmentTwo, " + " u.imgSegmentThree, "
//						+ " u.imgSegmentFour, " + " u.imgSegmentFive, " + " u.imgSegmentSix, "
//						+ " u.imgSegmentSeven, " + " u.imgSegmentEight, " + " u.imgSegmentNine, "
//						+ " u.conductorSize, "
//
//						+ " u.conductorType, " + " u.conduitSize, " + " u.conduitType, "
//						+ " u.conductorSizeOther, " + " u.conductorTypeOther, " + " u.conduitSizeOther, "
//						+ " u.conduitTypeOther, " + " u.conductorSizeTwo, " + " u.conductorTypeTwo, "
//						+ " u.conduitSizeTwo, "
//
//						+ " u.conduitTypeTwo, " + " u.conductorSizeTwoOther, " + " u.conductorTypeTwoOther, "
//						+ " u.conduitSizeTwoOther," + " u.conduitTypeTwoOther, " + " u.conductorSizeThree, "
//						+ " u.conductorTypeThree, " + " u.conduitSizeThree, " + " u.conduitTypeThree, "
//						+ " u.conductorSizeThreeOther, "
//
//						+ " u.conductorTypeThreeOther, " + " u.conduitSizeThreeOther, "
//						+ " u.conduitTypeThreeOther, " + " u.conductorSizeFour,  " + " u.conductorTypeFour,  "
//						+ " u.conduitSizeFour,  " + " u.conduitTypeFour,  " + " u.conductorSizeFourOther,  "
//						+ " u.conductorTypeFourOther,  " + " u.conduitSizeFourOther,  "
//
//						+ " u.conduitTypeFourOther,  " + " u.conductorSizeFive, " + " u.conductorTypeFive,"
//						+ " u.conduitSizeFive," + " u.conduitTypeFive, " + " u.conductorSizeFiveOther, "
//						+ " u.conductorTypeFiveOther," + " u.conduitSizeFiveOther, "
//						+ " u.conduitTypeFiveOther," + " u.conductorSizeSix,"
//
//						+ " u.conductorTypeSix," + " u.conduitSizeSix," + " u.conduitTypeSix,"
//						+ " u.conductorSizeSixOther," + " u.conductorTypeSixOther, "
//						+ " u.conduitSizeSixOther, " + " u.conduitTypeSixOther, " + " u.conductorSizeSeven,"
//						+ " u.conductorTypeSeven,"
//
//						+ " u.conduitSizeSeven," + " u.conduitTypeSeven," + " u.conductorSizeSevenOther,"
//						+ " u.conductorTypeSevenOther, " + " u.conduitSizeSevenOther,"
//						+ " u.conduitTypeSevenOther, " + " u.conductorSizeEight," + " u.conductorTypeEight, "
//						+ " u.conduitSizeEight," + " u.conduitTypeEight, "
//
//						+ " u.conductorSizeEightOther," + " u.conductorTypeEightOther, "
//						+ " u.conduitSizeEightOther," + " u.conduitTypeEightOther, " + " u.qtySegmentOne,"
//						+ " u.qtySegmentTwo, " + " u.qtySegmentThree," + " u.qtySegmentFour, "
//						+ " u.qtySegmentFive," + " u.qtySegmentSix, "
//
//						+ " u.qtySegmentSeven," + " u.qtySegmentEight, " + " u.qtySegmentNine, "
//						+ " u.qtySegmentTen, " + " u.qtySegmentEleven, " + " u.conductorQty,"
//						+ " u.conductorQtyTwo, " + " u.conductorQtyThree," + " u.conductorQtyFour, "
//
//						+ " u.conductorQtyFive," + " u.conductorQtySix, " + " u.conductorQtySeven,"
//						+ " u.conductorQtyEight, " + " u.conductorTypeTen, " + " u.conductorSizeTen,"
//						+ " u.conduitTypeTen, " + " u.conduitSizeTen," + " u.conductorQtyTen, "
//
//						+ " u.conductorTypeNine, " + " u.conductorSizeNine," + " u.conduitTypeNine, "
//						+ " u.conduitSizeNine," + " u.conductorQtyNine, " + " u.conductorTypeTwelve, "
//						+ " u.conductorSizeTwelve," + " u.conduitTypeTwelve, " + " u.conduitSizeTwelve,"
//						+ " u.conductorQtyTwelve, " + " u.conductorTypeEleven, " + " u.conductorSizeEleven,"
//						+ " u.conduitTypeEleven, " + " u.conduitSizeEleven," + " u.conductorQtyEleven, "
//						+ " u.conductorTypeNineOther, " + " u.conductorSizeNineOther,"
//						+ " u.conduitTypeNineOther, " + " u.conduitSizeNineOther, "
//						+ " u.conductorTypeTenOther," + " u.conductorSizeTenOther, " + " u.conduitTypeTenOther,"
//						+ " u.conduitSizeTenOther, " + " u.conductorTypeElevenOther, "
//						+ " u.conductorSizeElevenOther," + " u.conduitTypeElevenOther, "
//						+ " u.conduitSizeElevenOther," + " u.conductorTypeTwelveOther, "
//						+ " u.conductorSizeTwelveOther, " + " u.conduitTypeTwelveOther, " + " u.conduitSizeTwelveOther, "
//						+ " u.conductorQtyOther, "+ " u.conductorQtyTwoOther, "+ " u.conductorQtyThreeOther, "+ " u.conductorQtyFourOther, "
//						+ " u.conductorQtyFiveOther, "+ " u.conductorQtySixOther, "+ " u.conductorQtySevenOther, "+ " u.conductorQtyEightOther, "
//						+ " u.conductorQtyNineOther, "+ " u.conductorQtyTenOther, "+ " u.conductorQtyElevenOther, "+ " u.componentOrder, "+ " u.mapFromUserInput, "
//						+ " u.qtySegmentNineTwo, "+ " u.conductorQtyNineTwo, "+ " u.conductorTypeNineTwo, "+ " u.conductorSizeNineTwo, "+ " u.conduitTypeNineTwo, "
//						+ " u.conduitSizeNineTwo, "+ " u.conductorQtyNineTwoOther, "+ " u.conductorTypeNineTwoOther, "+ " u.conductorSizeNineTwoOther, "
//						+ " u.conduitTypeNineTwoOther, "+ " u.conduitSizeNineTwoOther, "+ "u.imgSegmentNineTwo, "+ " u.conductorTypeExisting, "+ " u.conductorSizeExisting, "
//						+ " u.conduitTypeExisting, "+ " u.conduitSizeExisting, "+ " u.conductorTypeTwoExisting, "+ " u.conductorSizeTwoExisting, "+ " u.conduitTypeTwoExisting, "+ " u.conduitSizeTwoExisting, "
//						+ " u.conductorTypeThreeExisting, "+ " u.conductorSizeThreeExisting, "+ " u.conduitTypeThreeExisting, "+ " u.conduitSizeThreeExisting, "
//						+ " u.conductorTypeFourExisting, "+ " u.conductorSizeFourExisting, "+ " u.conduitTypeFourExisting, "+ " u.conduitSizeFourExisting, "
//						+ " u.conductorTypeFiveExisting, "+ " u.conductorSizeFiveExisting, "+ " u.conduitTypeFiveExisting, "+ " u.conduitSizeFiveExisting, "
//						+ " u.conductorTypeSixExisting, "+ " u.conductorSizeSixExisting, "+ " u.conduitTypeSixExisting, "+ " u.conduitSizeSixExisting, "
//						+ " u.conductorTypeSevenExisting, "+ " u.conductorSizeSevenExisting, "+ " u.conduitTypeSevenExisting, "+ " u.conduitSizeSevenExisting, "
//						+ " u.conductorTypeEightExisting, "+ " u.conductorSizeEightExisting, "+ " u.conduitTypeEightExisting, "+ " u.conduitSizeEightExisting, "
//						+ " u.conductorTypeNineExisting, "+ " u.conductorSizeNineExisting, "+ " u.conduitTypeNineExisting, "+ " u.conduitSizeNineExisting, "
//						+ " u.conductorTypeNineTwoExisting, "+ " u.conductorSizeNineTwoExisting, "+ " u.conduitTypeNineTwoExisting, "+ " u.conduitSizeNineTwoExisting, "
//						+ " u.conductorTypeTenExisting, "+ " u.conductorSizeTenExisting, "+ " u.conduitTypeTenExisting, "+ " u.conduitSizeTenExisting, "
//						+ " u.conductorTypeElevenExisting, "+ " u.conductorSizeElevenExisting, "+ " u.conduitTypeElevenExisting, "+ " u.conduitSizeElevenExisting)  from PermitConduitConductorSectionEntity u "
//						+ " where u.permitEntity.id = :p1 ")).thenReturn(mockedQuery12);
//		when(mockedQuery12.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery12);
//		
//		Query mockedQuery13 = mock(Query.class);
//		when(em.createQuery(" SELECT u FROM AuthentificationEntity u WHERE u.id = :p1  ")).thenReturn(mockedQuery13);
//		when(mockedQuery13.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery13);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result of the query4
//		scenario.get(0).add(null);
//		// Result of the query5
//		scenario.get(0).add(null);
//		// Result of the query6
//		scenario.get(0).add(null);
//		// Result of the query7
//		scenario.get(0).add(null);
//		// Result of the query8
//		scenario.get(0).add(null);
//		// Result of the query9
//		scenario.get(0).add(null);
//		// Result of the query10
//		scenario.get(0).add(null);
//		// Result of the query11
//		scenario.get(0).add(null);
//		// Result of the query12
//		scenario.get(0).add(null);
//		// Result of the query13
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add( new GetPermitByIdResult());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result of the query4
//		scenario.get(1).add(null);
//		// Result of the query5
//		scenario.get(1).add(null);
//		// Result of the query6
//		scenario.get(1).add(null);
//		// Result of the query7
//		scenario.get(1).add(null);
//		// Result of the query8
//		scenario.get(1).add(null);
//		// Result of the query9
//		scenario.get(1).add(null);
//		// Result of the query10
//		scenario.get(1).add(null);
//		// Result of the query11
//		scenario.get(1).add(null);
//		// Result of the query12
//		scenario.get(1).add(null);
//		// Result of the query13
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add( new GetPermitByIdResult());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("1234");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result of the query4
//		scenario.get(2).add(null);
//		// Result of the query5
//		scenario.get(2).add(null);
//		// Result of the query6
//		scenario.get(2).add(null);
//		// Result of the query7
//		scenario.get(2).add(null);
//		// Result of the query8
//		scenario.get(2).add(null);
//		// Result of the query9
//		scenario.get(2).add(null);
//		// Result of the query10
//		scenario.get(2).add(null);
//		// Result of the query11
//		scenario.get(2).add(null);
//		// Result of the query12
//		scenario.get(2).add(null);
//		// Result of the query13
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add( new GetPermitByIdResult());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("1234");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(null);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result of the query4
//		scenario.get(3).add(null);
//		// Result of the query5
//		scenario.get(3).add(null);
//		// Result of the query6
//		scenario.get(3).add(null);
//		// Result of the query7
//		scenario.get(3).add(null);
//		// Result of the query8
//		scenario.get(3).add(null);
//		// Result of the query9
//		scenario.get(3).add(null);
//		// Result of the query10
//		scenario.get(3).add(null);
//		// Result of the query11
//		PermitEntity per = new PermitEntity();
//		per.setAuthentificationEntity(new AuthentificationEntity());
//		scenario.get(3).add(per);
//		// Result of the query12
//		scenario.get(3).add(null);
//		// Result of the query13
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add( new GetPermitByIdResult());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("1234");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add(null);
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result of the query4
//		scenario.get(4).add(null);
//		// Result of the query5
//		scenario.get(4).add(null);
//		// Result of the query6
//		scenario.get(4).add(null);
//		// Result of the query7
//		scenario.get(4).add(null);
//		// Result of the query8
//		scenario.get(4).add(null);
//		// Result of the query9
//		scenario.get(4).add(new PermitProjectSiteInfoEntityTwo());
//		// Result of the query10
//		scenario.get(4).add(null);
//		// Result of the query11
//		scenario.get(4).add(per);
//		// Result of the query12
//		scenario.get(4).add(null);
//		// Result of the query13
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add( new GetPermitByIdResult());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add("1234");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		// Result of the query1
//		scenario.get(5).add(null);
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result of the query3
//		scenario.get(5).add(null);
//		// Result of the query4
//		scenario.get(5).add(null);
//		// Result of the query5
//		scenario.get(5).add(new PermitAdvEntityResult());
//		// Result of the query6
//		scenario.get(5).add(new PermitArrayEntityResultSecond());
//		// Result of the query7
//		scenario.get(5).add(null);
//		// Result of the query8
//		scenario.get(5).add(null);
//		// Result of the query9
//		scenario.get(5).add(new PermitProjectSiteInfoEntityTwo());
//		// Result of the query10
//		scenario.get(5).add(null);
//		// Result of the query11
//		scenario.get(5).add(per);
//		// Result of the query12
//		scenario.get(5).add(null);
//		// Result of the query13
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add( new GetPermitByIdResult());
//		
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getPermitById [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(8));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(9));
//			when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(10));
//			when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(11));
//			when(mockedQuery6.getSingleResult()).thenReturn(scenario.get(i).get(12));
//			when(mockedQuery7.getSingleResult()).thenReturn(scenario.get(i).get(13));
//			when(mockedQuery8.getSingleResult()).thenReturn(scenario.get(i).get(14));
//			when(mockedQuery9.getSingleResult()).thenReturn(scenario.get(i).get(15));
//			when(mockedQuery10.getSingleResult()).thenReturn(scenario.get(i).get(16));
//			when(mockedQuery11.getSingleResult()).thenReturn(scenario.get(i).get(17));
//			when(mockedQuery12.getSingleResult()).thenReturn(scenario.get(i).get(18));
//			when(mockedQuery13.getSingleResult()).thenReturn(scenario.get(i).get(19));
//			permitService.getPermitById((String) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3),(String) scenario.get(i).get(4),(String) scenario.get(i).get(5),(String) scenario.get(i).get(6));
//		
//		} 
//    }
//    
//    @Test
//   	public void getFileById() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from PermitEntity u " + " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add( new GetFileByIdResults());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add( new GetFileByIdResults());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(new PermitEntity());
//		// Result excpected
//		GetFileByIdResults exp = new GetFileByIdResults();
//		exp.setIdPermit(1235);
//		scenario.get(2).add(exp);
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getFileById [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			permitService.getFileById((String) scenario.get(i).get(0));
//		
//		} 	
//    }
//    
//    @Test
//   	public void getPlansetById() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from PermitPlansetUploadEntity u " + " where u.permitEntity.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add( new GetPlansetByIdResults());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add( new GetPlansetByIdResults());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("1235");
//		// Result of the query1
//		scenario.get(2).add(new PermitPlansetUploadEntity());
//		// Result excpected
//		GetPlansetByIdResults exp = new GetPlansetByIdResults();
//		exp.setIdPermit(1235);
//		scenario.get(2).add(exp);
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getPlansetById [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			permitService.getPlansetById((String) scenario.get(i).get(0));
//		
//		} 
//    }
//    
//    @Test
//   	public void urlImages() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.id from PermitEntity u where u.authentificationEntity.id=:p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from PathEntity u " + " where u.id = :p1 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//		
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u.firstName from AuthentificationEntity u where u.id=:p1")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery3);
//		
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u.lastName from AuthentificationEntity u where u.id=:p1")).thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery4);
//		
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT u.homeOwnName from PermitEntity u where u.id=:p1")).thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery5);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result of the query3
//		scenario.get(0).add(null);
//		// Result of the query4
//		scenario.get(0).add(null);
//		// Result of the query5
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new HashMap<Integer, HashMap<String, String>>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query3
//		scenario.get(1).add(null);
//		// Result of the query4
//		scenario.get(1).add(null);
//		// Result of the query5
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new HashMap<Integer, HashMap<String, String>>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("12345");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query3
//		scenario.get(2).add(null);
//		// Result of the query4
//		scenario.get(2).add(null);
//		// Result of the query5
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new HashMap<Integer, HashMap<String, String>>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("12345");
//		// Result of the query1
//		ArrayList<Integer> list = new ArrayList<>();
//		list.add(null);
//		list.add(25805050);
//		scenario.get(3).add(list);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query3
//		scenario.get(3).add(null);
//		// Result of the query4
//		scenario.get(3).add(null);
//		// Result of the query5
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add(new HashMap<Integer, HashMap<String, String>>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("12345");
//		// Result of the query1
//		scenario.get(4).add(list);
//		// Result of the query2
//		PathEntity path = new PathEntity();
//		path.setDir("C:/files");
//		scenario.get(4).add(path);
//		// Result of the query3
//		scenario.get(4).add(null);
//		// Result of the query4
//		scenario.get(4).add(null);
//		// Result of the query5
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(new HashMap<Integer, HashMap<String, String>>());
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("urlImages [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			
//			permitService.urlImages((String) scenario.get(i).get(0));
//		
//		} 
//    }
//    
//    @Test
//   	public void getNameOfThePermit() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from PermitEntity u " + " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		//The result list of the Query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		//The result list of the Query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("123");
//		// Result of the query1
//		scenario.get(2).add(null);
//		//The result list of the Query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("123");
//		// Result of the query1
//		PermitEntity per = new PermitEntity();
//		per.setHomeOwnName("test junit");
//		scenario.get(3).add(per);
//		//The result list of the Query1
//		ArrayList<PermitEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add("test junit");
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getNameOfThePermit [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			permitService.getNameOfThePermit((String) scenario.get(i).get(0));
//		
//		} 	
//    }
//    
//    @Test
//   	public void urlImagesDrafter() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.googleMapImageName from PermitDrafterDataEntity u where u.permitEntity.id=:p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u.nearMapImageName from PermitDrafterDataEntity u where u.permitEntity.id=:p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//		
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		//Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		List<String> exp = new ArrayList<String>();
//		exp.add("C:/files/null/drafterFiles/");
//		exp.add("C:/files/null/drafterFiles/");
//		scenario.get(0).add(exp);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		//Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		List<String> exp1 = new ArrayList<String>();
//		exp1.add("C:/files//drafterFiles/");
//		exp1.add("C:/files//drafterFiles/");
//		scenario.get(1).add(exp1);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("1258");
//		// Result of the query1
//		scenario.get(2).add(null);
//		//Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<String>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("1258");
//		// Result of the query1
//		scenario.get(3).add("test");
//		//Result of the query2
//		scenario.get(3).add("junit");
//		// Result excpected
//		List<String> exp2 = new ArrayList<String>();
//		exp2.add("C:/files/1258/drafterFiles/junit");
//		exp2.add("C:/files/1258/drafterFiles/test");
//		scenario.get(3).add(exp2);
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("urlImagesDrafter [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			permitService.urlImagesDrafter((String) scenario.get(i).get(0));
//		
//		} 
//    }
//   
//    @Test
//   	public void updatedDate() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u " + " from PermitEntity u " + " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		//The result list of the Query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		//The result list of the Query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("125");
//		// Result of the query1
//		scenario.get(2).add(null);
//		//The result list of the Query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("125");
//		// Result of the query1
//		scenario.get(3).add(new PermitEntity());
//		//The result list of the Query1
//		ArrayList<PermitEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(3).add(list);
//		// Result excpected
//		Date updateDate = new Date();
//		SimpleDateFormat FormattedDATE = new SimpleDateFormat("MM/dd/yyyy");
//		String exp = FormattedDATE.format(updateDate);
//		scenario.get(3).add(exp);
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("updatedDate [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			permitService.updatedDate((String) scenario.get(i).get(0));
//		
//		} 
//
//    }
//    
//    @Test
//   	public void getAllPermitForChart() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitResult "
//				+ "(u.id, u.homeOwnName, u.advancement, u.status,"
//				+ " u.creationDate, u.updatedDate, u.creationPermitDate,u.isPayed, u.submitted)"
//				+ " from PermitEntity u  " + " where u.authentificationEntity.id = :p1 ORDER BY u.creationPermitDate  ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();	
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(123);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<List<String>>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(123);
//		// Result of the query1
//		ArrayList<PermitResult> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<List<String>>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(123);
//		// Result of the query1
//		ArrayList<PermitResult> list2 = new ArrayList<>();
//		PermitResult per = new PermitResult();
//		per.setCreationPermitDate(new Date());
//		list2.add(per);
//		scenario.get(2).add(list2);
//		// Result excpected
//		ArrayList<List<String>> exp = new ArrayList<List<String>>();
//		ArrayList<String> y = new ArrayList<>();
//		y.add((new Date().getYear()+1900)+"");
//		exp.add(y);
//		ArrayList<String> m = new ArrayList<>();
//		m.add((new Date().getMonth()+1)+"");
//		exp.add(m);
//		ArrayList<String> d = new ArrayList<>();
//		d.add((new Date().getDate())+"");
//		exp.add(d);
//		ArrayList<String> size = new ArrayList<>();
//		size.add("1");
//		size.add("1");
//		exp.add(size);
//		scenario.get(2).add(exp);
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getAllPermitForChart [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			permitService.getAllPermitForChart((int) scenario.get(i).get(0));
//		
//		}
//    }
//    
//    @Test
//   	public void getAllPermitForCharttwo() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitResult "
//				+ "(u.id, u.homeOwnName, u.advancement, u.status,"
//				+ " u.creationDate, u.updatedDate, u.creationPermitDate,u.isPayed,u.submitted)"
//				+ " from PermitEntity u where u.isDeleted = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add( new ArrayList<Integer>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<PermitResult> list = new ArrayList<>();
//		list.add(null);
//		PermitResult per = new PermitResult();
//		per.setStatus("Created");
//		list.add(per);
//		PermitResult per2 = new PermitResult();
//		per2.setStatus("Submitted");
//		list.add(per2);
//		scenario.get(1).add(list);
//		// Result excpected
//		ArrayList<Integer> exp = new ArrayList<>();
//		exp.add(1);
//		exp.add(0);
//		exp.add(1);
//		exp.add(0);
//		scenario.get(1).add(exp);
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getAllPermitForCharttwo [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			permitService.getAllPermitForCharttwo();
//		
//		} 
//    }
//    
//    @Test
//	public void getSketchByPermit() {
//    	
//    	List<String> scenario = new ArrayList<String>();
//    	scenario.add("0");
//    	//scenario.add("");
//    	scenario.add("123");
//    	scenario.add("12387777");
//    	
//    	 Query mockedQuery = mock(Query.class);
//    	 List<PermitSketchResults> sketchPermit = new ArrayList<PermitSketchResults>();
//    	 List<PermitSketchResults> sketchPermit2 = null;
//		 for(int i = 0; i < scenario.size(); i++) {
//	    	when(em.createQuery(" SELECT new com.PlayGroundAdv.Solar.model.PermitSketchResults(  "
//					+ " u.arraySketch,  " + " u.azimuth,  " + " u.roofPitch,  " + " u.moduleTils,  " + " u.eaveOverHang,  "
//					+ " u.eaveOverHangOther,   " + "  u.modelvalue, " + " u.moduleQty" + "  ) "
//					+ " from PermitSketchEntity u " + " where u.permitEntity.id = :p1   " + " ORDER BY u.arraySketch  ")).thenReturn(mockedQuery);
//	    	when(mockedQuery.setParameter("p1", Integer.parseInt(scenario.get(i)))).thenReturn(mockedQuery);
//	    	when(mockedQuery.getSingleResult()).thenReturn(sketchPermit);
//	    	when(mockedQuery.getSingleResult()).thenReturn(sketchPermit2);
//	    	permitService.getSketchByPermit(scenario.get(i));
//		 }
//    }
//    
//    @Test
//   	public void getModulesMan() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.make " + " from Cmodulev2 u GROUP BY u.make ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<String>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<String> list = new ArrayList<>();
//		list.add(null);
//		list.add("test junit");
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getModulesMan [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			permitService.getModulesMan();
//		
//		} 
//    }
//    
//    @Test
//   	public void getModulesModels() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.model" + " from Cmodulev2 u where u.make = :p1 ORDER BY u.model")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<String>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		// Result of the query1
//		ArrayList<String> list = new ArrayList<>();
//		list.add(null);
//		list.add("test junit");
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getModulesModels [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			permitService.getModulesModels((String) scenario.get(i).get(0));
//		}
//    }
//    
//    @Test
//   	public void getInvertersMan() throws IOException {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.make " + " from Inverters u GROUP BY u.make ORDER BY u.make")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<String>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<String> list = new ArrayList<>();
//		list.add(null);
//		list.add("test junit");
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getInvertersMan [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			permitService.getInvertersMan();
//		} 
//    }
//    
//    @Test
//   	public void getMicroInverterManufacturer() throws IOException {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.make " + " from Inverters u Where u.microInverter = true GROUP BY u.make ORDER BY u.make")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<String>());
//		
//		scenario.add(new ArrayList<Object>());
//		// Result of the query1
//		ArrayList<String> list = new ArrayList<>();
//		list.add(null);
//		list.add("test junit");
//		scenario.get(1).add(list);
//		// Result excpected
//		scenario.get(1).add(list);
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getMicroInverterManufacturer [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(0));
//			permitService.getMicroInverterManufacturer();
//		} 
//    
//    }
//    
//    @Test
//   	public void saveSkecthLayoutArrays() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u  where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitSketchEntity u where u.permitEntity.id = :p1 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Fail");
//		//The result list of the Query1
//		scenario.get(0).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		LayoutSketchArraysModel parm1 = new LayoutSketchArraysModel();
//		parm1.arraysLength = 0;
//		scenario.get(1).add(parm1);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Success");
//		//The result list of the Query1
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(parm1);
//		// Result of the query1
//		scenario.get(2).add(new PermitEntity());
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Success");
//		//The result list of the Query1
//		ArrayList<PermitEntity> list = new ArrayList<>();
//		list.add(new PermitEntity());
//		scenario.get(2).add(list);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(parm1);
//		// Result of the query1
//		scenario.get(3).add(new PermitEntity());
//		// Result of the query2
//		ArrayList<PermitSketchEntity> list2 = new ArrayList<>();
//		list2.add(null);
//		list2.add(new PermitSketchEntity());
//		scenario.get(3).add(list2);
//		// Result excpected
//		scenario.get(3).add("Success");
//		//The result list of the Query1
//		scenario.get(3).add(list);
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("saveSkecthLayoutArrays [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(2));
//			
//			permitService.saveSkecthLayoutArrays((LayoutSketchArraysModel) scenario.get(i).get(0));
//		} 
//		
//    }
//    
//    @Test
//   	public void getPermitFiles() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u where u.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<String>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<String>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("1258");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<String>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("1258");
//		// Result of the query1
//		scenario.get(3).add(new PermitEntity());
//		// Result excpected
//		ArrayList<String> list = new ArrayList<>();
//		list.add("nullpermitFiles/");
//		scenario.get(3).add(list);
//		
//		
//		for(int i=0;i<scenario.size();i++) {
//			System.out.println("getPermitFiles [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			
//			permitService.getPermitFiles((String) scenario.get(i).get(0));
//		} 
//    }
//    
//    @Test
//	public void getDrafterDatafiles() throws IOException {
//    	
//    	List<String> scenario = new ArrayList<String>();
//    	scenario.add("0");
//    	scenario.add("14555");
//    	scenario.add("78999665");
//    	
//    	 Query mockedQuery = mock(Query.class);
//		 for(int i = 0; i < scenario.size(); i++) {
//			 PermitDrafterDataEntity permitDrafterDataEntity = new PermitDrafterDataEntity();
//			 PermitDrafterDataEntity permitDrafterDataEntity2 = new PermitDrafterDataEntity();
//			 permitDrafterDataEntity2.setParcelMapName("PM-PM-001 Parcel Map (1).pdf");
//			 permitDrafterDataEntity2.setPvArrayLayoutName("PV-RL-003 Racking (1).pdf");
//			 permitDrafterDataEntity2.setRackingLayoutName("RL-RL-003 Racking (1).pdf");
//			 permitDrafterDataEntity2.setElectricalLayoutName("EL-PM-001 Parcel Map (1).pdf");
//			 permitDrafterDataEntity2.setPlacardLayoutName("PL-PL-005 Placard (1).pdf");
//			 permitDrafterDataEntity2.setAutocadFileName("AUF-String Inverter NT.rar");
//	    	when(em.createQuery(" Select u FROM PermitDrafterDataEntity u WHERE u.permitEntity.id = :p1 ")).thenReturn(mockedQuery);
//	    	when(mockedQuery.setParameter("p1",  Integer.parseInt(scenario.get(i)))).thenReturn(mockedQuery);
//	    	when(mockedQuery.getSingleResult()).thenReturn(permitDrafterDataEntity);
//	    	when(mockedQuery.getSingleResult()).thenReturn(permitDrafterDataEntity2);
//	    	
//	    	permitService.getDrafterDatafiles(scenario.get(i));
//		 }
//    }
//    
//    @Test
//   	public void getAdditionalInfoFiles() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.fileName from AdditionalInfoFiles u where u.permitEntity.id = :p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<String>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<String>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("2589");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<String>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("2589");
//		// Result of the query1
//		ArrayList<String> list = new ArrayList<>();
//		list.add(null);
//		list.add("Junit test");
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add(list);
//		
//		for(int i =0;i<scenario.size();i++) {
//			System.out.println("getAdditionalInfoFiles [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			permitService.getAdditionalInfoFiles((String) scenario.get(i).get(0));
//		}
//    }
//    
//    @Test
//   	public void changePermitStatus() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u  " + " from PermitEntity u  " + " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from ProjectsTrackerEntity u WHERE u.permit.id = :p1 ")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result of the query2 the list result
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result of the query2 the list result
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("1258");
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result of the query2 the list result
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("1258");
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new PermitEntity());
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result of the query2 the list result
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("Done");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("1258");
//		scenario.get(4).add("Reopened");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add(new PermitEntity());
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result of the query2 the list result
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add("1258");
//		scenario.get(5).add("Reopened");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		// Result of the query1
//		scenario.get(5).add(new PermitEntity());
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result of the query2 the list result
//		ArrayList<ProjectsTrackerEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(5).add(list);
//		// Result excpected
//		scenario.get(5).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(6).add("1258");
//		scenario.get(6).add("Reopened");
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		// Result of the query1
//		scenario.get(6).add(new PermitEntity());
//		// Result of the query2
//		scenario.get(6).add(new ProjectsTrackerEntity());
//		// Result of the query2 the list result
//		scenario.get(6).add(list);
//		// Result excpected
//		scenario.get(6).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(7).add("1258");
//		scenario.get(7).add("Delivered");
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		scenario.get(7).add(null);
//		// Result of the query1
//		scenario.get(7).add(new PermitEntity());
//		// Result of the query2
//		scenario.get(7).add(new ProjectsTrackerEntity());
//		// Result of the query2 the list result
//		scenario.get(7).add(list);
//		// Result excpected
//		scenario.get(7).add("Fail");
//		
//		System.out.println(new Date());
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(8).add("1258");
//		scenario.get(8).add("Delivered");
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		scenario.get(8).add(null);
//		// Result of the query1
//		PermitEntity per = new PermitEntity();
//		per.setCreationPermitDate(new Date());
//		scenario.get(8).add(per);
//		// Result of the query2
//		scenario.get(8).add(new ProjectsTrackerEntity());
//		// Result of the query2 the list result
//		scenario.get(8).add(list);
//		// Result excpected
//		scenario.get(8).add("Done");
//		
//		for(int i =0;i<scenario.size();i++) {
//			System.out.println("getNoteSketchFilesTwo [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(8));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(9));
//			when(mockedQuery2.getResultList()).thenReturn((List) scenario.get(i).get(10));
//			permitService.changePermitStatus((String) scenario.get(i).get(0),(String) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3),(String) scenario.get(i).get(4),(String) scenario.get(i).get(5),(String) scenario.get(i).get(6),(String) scenario.get(i).get(7));
//		}
//    }
//    
//    @Test
//   	public void getNoteSketchFilesOne() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.fileName from NoteSketchFiles u where u.permitEntity.id = :p1 and u.fileType=20")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<String>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<String>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("1258");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<String>());
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("1258");
//		// Result of the query1
//		ArrayList<String> list = new ArrayList<>();
//		list.add(null);
//		list.add("");
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add(list);
//		
//		for(int i =0;i<scenario.size();i++) {
//			System.out.println("getNoteSketchFilesTwo [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			permitService.getNoteSketchFilesTwo((String) scenario.get(i).get(0));
//		}
//    }
//    
//    @Test
//	public void getBOSFiles() throws IOException {
//    	
//    	List<String> scenario = new ArrayList<String>();
//    	scenario.add("0");
//    	scenario.add("");
//    	scenario.add(null);
//    	scenario.add("123657");
//    	
//		 for(int i = 0; i < scenario.size(); i++) {
//	    	permitService.getBOSFiles(scenario.get(i));
//		 }
//    }
//    
//    @Test
//   	public void getUtilityBillFiles() throws IOException {
//       	
//       	List<String> scenario = new ArrayList<String>();
//       	scenario.add("0");
//       	scenario.add("");
//       	scenario.add(null);
//       	scenario.add("123657");
//       	scenario.add("12365789998865");
//       	
//   		 for(int i = 0; i < scenario.size(); i++) {
//   	    	permitService.getUtilityBillFiles(scenario.get(i));
//   		 }
//       }
//    
//    @Test
//   	public void getNoteSketchFilesTwo() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.fileName from NoteSketchFiles u where u.permitEntity.id = :p1 and u.fileType=20")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new ArrayList<String>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new ArrayList<String>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("2589");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new ArrayList<String>());
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("2589");
//		// Result of the query1
//		ArrayList<String> list = new ArrayList<>();
//		list.add(null);
//		list.add("Junit test");
//		scenario.get(3).add(list);
//		// Result excpected
//		scenario.get(3).add(list);
//		
//		for(int i =0;i<scenario.size();i++) {
//			System.out.println("getNoteSketchFilesTwo [ " + i + " ]");
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(1));
//			permitService.getNoteSketchFilesTwo((String) scenario.get(i).get(0));
//		}
//    }
//    
//    @Test
//   	public void getPermitForReopen() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u where u.id =:p1")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u where u.id =:p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery2);
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("1258");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Fail");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("1258");
//		// Result of the query1
//		PermitEntity per = new PermitEntity();
//		per.setAuthentificationEntity(new AuthentificationEntity());
//		scenario.get(3).add(per);
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add("1258");
//		// Result of the query1
//		scenario.get(4).add(per);
//		// Result of the query2
//		scenario.get(4).add(new AuthentificationEntity());
//		// Result excpected
//		scenario.get(4).add("null null requests to re-open null in utility category.");
//		
//		for(int i =0;i<scenario.size();i++) {
//			System.out.println("getPermitForReopen [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			permitService.getPermitForReopen((String) scenario.get(i).get(0));
//		}
//		
//    }
//    
//    @Test
//   	public void getPlansetImages() throws IOException {
//       	
//       	List<String> scenario = new ArrayList<String>();
//       	scenario.add("0");
//       	scenario.add("");
//       	scenario.add(null);
//       	scenario.add("123657");
//       	scenario.add("12365789998865");
//       	
//   		 for(int i = 0; i < scenario.size(); i++) {
//   	    	permitService.getPlansetImages(scenario.get(i));
//   		 }
//       }
//    
//    @Test
//   	public void saveCommentUpload() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u where u.id = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitPlansetUploadEntity u where u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("Fail");
//		
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(new GetFileByIdResults());
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("Fail");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(new GetFileByIdResults());
//		scenario.get(2).add(new GetPlansetByIdResults());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("Fail");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add(new GetFileByIdResults());
//		scenario.get(3).add(new GetPlansetByIdResults());
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add(new PermitEntity());
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("Fail");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add(new GetFileByIdResults());
//		scenario.get(4).add(new GetPlansetByIdResults());
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add(new PermitEntity());
//		// Result of the query2
//		scenario.get(4).add(new PermitPlansetUploadEntity());
//		// Result excpected
//		scenario.get(4).add("Done");
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("saveCommentUpload [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(8));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(9));
//			
//					permitService.saveCommentUpload((GetFileByIdResults) scenario.get(i).get(0),(GetPlansetByIdResults) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3),(String) scenario.get(i).get(4),(String) scenario.get(i).get(5),(String) scenario.get(i).get(6),(String) scenario.get(i).get(7));
//
//		}
//    }
//    
//    @Test
//   	public void onHoldProjectService() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u  " + " from PermitEntity u  " + " where u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(new PermitEntity());
//		//The result list of te Query
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(new PermitEntity());
//		//The result list of te Query
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("1235");
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(new PermitEntity());
//		//The result list of te Query
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("1235");
//		scenario.get(3).add(null);
//		// Result of the query1
//		PermitEntity per = new PermitEntity();
//		per.setUpdatedDate("09/01/2018");
//		scenario.get(3).add(per);
//		// Result excpected
//		PermitEntity exp = new PermitEntity();
//		exp.setUpdatedDate("09/01/2018");
//		exp.setStatus("OnHold");
//		exp.setIsOnHold(true);
//		scenario.get(3).add(exp);
//		//The result list of te Query
//		ArrayList<PermitEntity> list = new ArrayList<>();
//		list.add(null);
//		scenario.get(3).add(list);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("onHoldProjectService [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery1.getResultList()).thenReturn((List) scenario.get(i).get(4));
//			PermitEntity rslt = permitService.onHoldProjectService((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//
//		}
//    }
//    
//    @Test
//   	public void onHoldProjectUpdateToDService() {
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(false);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add("");
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add(false);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add("41235");
//		// Result of the query1
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add(false);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(3).add("41235");
//		// Result of the query1
//		PermitEntity per = new PermitEntity();
//		per.setDateOfSubmitPermitOnHold(new Date());
//		scenario.get(3).add(per);
//		// Result excpected
//		scenario.get(3).add(true);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("onHoldProjectUpdateToDService [ " + i + " ]");
//			when(em.find(PermitEntity.class, Integer.parseInt("41235"))).thenReturn((PermitEntity) scenario.get(i).get(1));
//			
//					permitService.onHoldProjectUpdateToDService((String) scenario.get(i).get(0));
//
//		}
//		
//    }
//    
//    @Test
//   	public void getProjectContacts() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery(" SELECT u.authentificationEntity.id FROM PermitEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery(" SELECT new com.PlayGroundAdv.Solar.model.ProjectContactsEmailModel("
//				+ "  u.contactEmail, "
//				+ "  u.secondContactEmail,  "
//				+ "  u.thirdContactEmail )  "
//				+ "  From AuthentificationEntity u"
//				+ "  Where u.id = :p1"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result of the query2
//		ProjectContactsEmailModel pr = new ProjectContactsEmailModel();
//		scenario.get(1).add(pr);
//		// Result excpected
//		scenario.get(1).add(pr);
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getProjectContacts [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//					permitService.getProjectContacts((Integer) scenario.get(i).get(0));
//
//		}
//    }
//    
//    @Test
//   	public void getPermitInformation() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.siteAddress from PermitHomeSiteInfoEntity u WHERE u.permitEntity.id = :p1"))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u.homeOwnName from PermitEntity u WHERE u.id = :p1"))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("null*null");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add("abc");
//		// Result of the query2
//		scenario.get(1).add("def");
//		// Result excpected
//		scenario.get(1).add("def*abc");
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("getPermitInformation [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			
//					permitService.getPermitInformation((Integer) scenario.get(i).get(0));
//
//		}
//    }
//    
// @Test
//   	public void sendProjectEmail() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u.contactEmail FROM AuthentificationEntity u WHERE u.id = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("Select u  "
//				+ " from PermitEntity u  "
//				+ " where u.id = :p1 "))
//				.thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//		
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
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
//		// Result of the query2
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("contact missing");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		// Result of the query1
//		scenario.get(1).add("malek@gmail.com");
//		// Result of the query2
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(new ProjectEmailModel());
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		// Result of the query1
//		scenario.get(2).add("malek@gmail.com");
//		// Result of the query2
//		scenario.get(2).add(null);
//		// Result excpected
//		scenario.get(2).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		ProjectEmailModel pr = new ProjectEmailModel();
//		pr.setEmailContent("malek@gmail.com");
//		scenario.get(3).add(pr);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		// Result of the query1
//		scenario.get(3).add("malek@gmail.com");
//		// Result of the query2
//		scenario.get(3).add(null);
//		// Result excpected
//		scenario.get(3).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(4).add(pr);
//		scenario.get(4).add(null);
//		scenario.get(4).add("");
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		// Result of the query1
//		scenario.get(4).add("malek@gmail.com");
//		// Result of the query2
//		scenario.get(4).add(null);
//		// Result excpected
//		scenario.get(4).add("error");
//		
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(5).add(pr);
//		scenario.get(5).add(null);
//		scenario.get(5).add("1234");
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		// Result of the query1
//		scenario.get(5).add("malek@gmail.com");
//		// Result of the query2
//		scenario.get(5).add(null);
//		// Result excpected
//		scenario.get(5).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(6).add(pr);
//		scenario.get(6).add(null);
//		scenario.get(6).add("1234");
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		scenario.get(6).add(null);
//		// Result of the query1
//		scenario.get(6).add("malek@gmail.com");
//		// Result of the query2
//		scenario.get(6).add(new PermitEntity());
//		// Result excpected
//		scenario.get(6).add("success");
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("sendProjectEmail [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(8));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(9));
//					permitService.sendProjectEmail((ProjectEmailModel) scenario.get(i).get(0),(Integer) scenario.get(i).get(1),(String) scenario.get(i).get(2),(String) scenario.get(i).get(3),(String) scenario.get(i).get(4),(String) scenario.get(i).get(5),(String) scenario.get(i).get(6),(String) scenario.get(i).get(7));
//
//		}
//    }
//    
//    @Test
//   	public void confShownAttachement() {
//
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("SELECT u FROM RFIQuestionEntity u WHERE u.id_RFIQuestion = :p1 "))
//				.thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//		
//
//		ArrayList<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(0).add(null);
//		// Result of the query1
//		scenario.get(0).add(null);
//		// Result excpected
//		scenario.get(0).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(1).add(1235);
//		// Result of the query1
//		scenario.get(1).add(null);
//		// Result excpected
//		scenario.get(1).add("error");
//		
//		scenario.add(new ArrayList<Object>());
//		//List of the parameter
//		scenario.get(2).add(1235);
//		// Result of the query1
//		scenario.get(2).add(new RFIQuestionEntity());
//		// Result excpected
//		scenario.get(2).add("success");
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("confShownAttachement [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(1));
//					permitService.confShownAttachement((Integer) scenario.get(i).get(0));
//
//		}
//	
//    }
//    
//    @Test
//   	public void removeFromUsersFavList() {
//       	
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(0).add("Module");
//    	String[] idModelsString= {"100","","220"};
//    	scenario.get(0).add(idModelsString);
//    	scenario.get(0).add(null);
//    	scenario.get(0).add("1999");
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(1).add("Inverter");
//    	String[] idModelsString2= {"1","0",""};
//    	scenario.get(1).add(idModelsString2);
//    	scenario.get(1).add(367777);
//    	scenario.get(1).add(null);
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(2).add("Optimizer");
//    	String[] idModelsString3= {"12000","255888","32111"};
//    	scenario.get(2).add(idModelsString3);
//    	scenario.get(2).add(3698577);
//    	scenario.get(2).add("123654446");
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(3).add("RailRacking");
//    	String[] idModelsString4= {"1477","52666","120000"};
//    	scenario.get(3).add(idModelsString4);
//    	scenario.get(3).add(0);
//    	scenario.get(3).add("4532222222");
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(4).add("RoofAttachment");
//    	String[] idModelsString5= {"14766667","0","120000"};
//    	scenario.get(4).add(idModelsString5);
//    	scenario.get(4).add(134444);
//    	scenario.get(4).add("4532222222");
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(5).add("ACDisconnect");
//    	String[] idModelsString6= {"14766667","11","120000"};
//    	scenario.get(5).add(idModelsString6);
//    	scenario.get(5).add(665444);
//    	scenario.get(5).add("4532222222");
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(6).add("ACCombiner");
//    	String[] idModelsString7= {"35444443","147","123"};
//    	scenario.get(6).add(idModelsString7);
//    	scenario.get(6).add(4665444);
//    	scenario.get(6).add("4532222222");
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(7).add("JBox");
//    	String[] idModelsString8= {"35444443","147","545"};
//    	scenario.get(7).add(idModelsString8);
//    	scenario.get(7).add(65444);
//    	scenario.get(7).add("4532222222");
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(8).add("DCDisconnect");
//    	String[] idModelsString9= {"554","147","78999999"};
//    	scenario.get(8).add(idModelsString9);
//    	scenario.get(8).add(65444);
//    	scenario.get(8).add("4532222222");
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(9).add("flashing");
//    	String[] idModelsString10= {"2123455","4333555","78999999"};
//    	scenario.get(9).add(idModelsString10);
//    	scenario.get(9).add(134444);
//    	scenario.get(9).add("4532222222");
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(10).add("Revenue or Performance Monitoring Meter");
//    	String[] idModelsString11= {"2123455","4333555","78999999"};
//    	scenario.get(10).add(idModelsString11);
//    	scenario.get(10).add(1344444);
//    	scenario.get(10).add("4532222222");
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(11).add("battery");
//    	String[] idModelsString12= {"2123455","4333555","78999999"};
//    	scenario.get(11).add(idModelsString12);
//    	scenario.get(11).add(135444);
//    	scenario.get(11).add("4532222222");
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(12).add("tiltLegs");
//    	String[] idModelsString13= {"2123455","4333555","78999999"};
//    	scenario.get(12).add(idModelsString13);
//    	scenario.get(12).add(1345444);
//    	scenario.get(12).add("4532222222");
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(13).add("Proposed Sub Panel");
//    	String[] idModelsString14= {"2123455","4333555","78999999"};
//    	scenario.get(13).add(idModelsString14);
//    	scenario.get(13).add(1665444);
//    	scenario.get(13).add("4532222222");
//    	scenario.add(new ArrayList<Object>());
//    	scenario.get(14).add("aaaaa bbbb");
//    	String[] idModelsString15= {"2123455","4333555","78999999"};
//    	scenario.get(14).add(idModelsString15);
//    	scenario.get(14).add(665444);
//    	scenario.get(14).add("4532222222");
//    	Query mockedQuery = mock(Query.class);
//    	AuthentificationEntity owner = new AuthentificationEntity();
//    	List<ModuleLibraryEntity> components =  new ArrayList<ModuleLibraryEntity>();
//    	List<PermitArraysEntity> permitArray = new ArrayList<PermitArraysEntity>();
//    	List<PermitProjectSiteInfoEntity> permitArray1= new ArrayList<PermitProjectSiteInfoEntity>();
//   		 for(int i = 0; i < scenario.size(); i++) {
//   			when(em.createQuery("SELECT u from AuthentificationEntity u WHERE u.id = :p1")).thenReturn(mockedQuery);
//   	    	when(mockedQuery.setParameter("p1",  scenario.get(i).get(2))).thenReturn(mockedQuery);
//   	    	when(mockedQuery.getSingleResult()).thenReturn(owner);
//   	    	
//   	    	if(scenario.get(i).get(0).equals("Module")) {
//   	    		when(em.createQuery("SELECT u from ModuleLibraryEntity u where u.authentificationEntity.id = :p1 AND u.cmodulev2.id IN :p2 ")).thenReturn(mockedQuery);
//   	    		when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.getResultList()).thenReturn(components);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.pvModuleModEl IN :p2 ")).thenReturn(mockedQuery);
//	    		when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//	   	    	when(mockedQuery.getResultList()).thenReturn(permitArray);
//   	    	}else if(scenario.get(i).get(0).equals("Inverter")) {
//   	    		List<InverterLibraryEntity> componentsInv =  new ArrayList<InverterLibraryEntity>();
//   	    		when(em.createQuery("SELECT u from InverterLibraryEntity u where u.authentificationEntity.id = :p1 AND u.inverters.id IN :p2 ")).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.setParameter("p1",  scenario.get(i).get(2))).thenReturn(mockedQuery);
//   	    		when(mockedQuery.setParameter("p2",  scenario.get(i).get(1))).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.getResultList()).thenReturn(componentsInv);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.inverterModel IN :p2 ")).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.getResultList()).thenReturn(permitArray);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.secondInverterModel IN :p2 ")).thenReturn(mockedQuery);
//	   	    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//	   	    	when(mockedQuery.getResultList()).thenReturn(permitArray);
//	   	    	
//	   	    	when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.thirdInverterModel IN :p2 ")).thenReturn(mockedQuery);
//	   	    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//	   	    	when(mockedQuery.getResultList()).thenReturn(permitArray);
//	   	    	
//	   	    	when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.fourthInverterModel IN :p2 ")).thenReturn(mockedQuery);
//	   	    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//	   	    	when(mockedQuery.getResultList()).thenReturn(permitArray);
//	   	    	
//	   	    	when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.fifthInverterModel IN :p2 ")).thenReturn(mockedQuery);
//	   	    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//	   	    	when(mockedQuery.getResultList()).thenReturn(permitArray);
//	   	    	
//	   	    	when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.microInverter IN :p2 ")).thenReturn(mockedQuery);
//	   	    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//	   	    	when(mockedQuery.getResultList()).thenReturn(permitArray);
//   	    	}else if(scenario.get(i).get(0).equals("Optimizer")) {
//   	    		List<DCOptimizerFavoritEntity> componentsOpt =  new ArrayList<DCOptimizerFavoritEntity>();
//   	    		when(em.createQuery("SELECT u from DCOptimizerFavoritEntity u where u.user.id = :p1 AND u.optimizer.id IN :p2 ")).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.setParameter("p1",  scenario.get(i).get(2))).thenReturn(mockedQuery);
//   	    		when(mockedQuery.setParameter("p2",  scenario.get(i).get(1))).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.getResultList()).thenReturn(componentsOpt);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitArraysEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.systemOptimizerModel IN :p2 ")).thenReturn(mockedQuery);
//	 	    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//	 	    	when(mockedQuery.getResultList()).thenReturn(permitArray);
//   	    	}else if(scenario.get(i).get(0).equals("RailRacking")) {
//   	    		List<RailRackingFavLibraryEntity> componentsRail =  new ArrayList<RailRackingFavLibraryEntity>();
//   	    		when(em.createQuery("SELECT u from RailRackingFavLibraryEntity u where u.authentificationEntity.id = :p1 AND u.railRacking.id IN :p2 ")).thenReturn(mockedQuery);
//   	    		when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.getResultList()).thenReturn(componentsRail);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.railRakingModel IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.railRakingModelforGroundMounted IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//		    	
//		    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.railRakingModelforPoleMounted IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//   	    	}else if(scenario.get(i).get(0).equals("RoofAttachment")) {
//   	    		List<RoofAttachmentFavLibraryEntity> componentsRoof =  new ArrayList<RoofAttachmentFavLibraryEntity>();
//   	    		when(em.createQuery("SELECT u from RoofAttachmentFavLibraryEntity u where u.authentificationEntity.id = :p1 AND u.roofAttachment.id IN :p2 ")).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.setParameter("p1",  scenario.get(i).get(2))).thenReturn(mockedQuery);
//   	    		when(mockedQuery.setParameter("p2",  scenario.get(i).get(1))).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.getResultList()).thenReturn(componentsRoof);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.railConnectionModel IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//   	    	}else if(scenario.get(i).get(0).equals("ACDisconnect")) {
//   	    		List<ACDisconnectFavLibraryEntity> componentsAcDisco =  new ArrayList<ACDisconnectFavLibraryEntity>();
//   	    		when(em.createQuery("SELECT u from ACDisconnectFavLibraryEntity u where u.authentificationEntity.id = :p1 AND u.aCDisconnect.id IN :p2 ")).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.setParameter("p1",  scenario.get(i).get(2))).thenReturn(mockedQuery);
//   	    		when(mockedQuery.setParameter("p2",  scenario.get(i).get(1))).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.getResultList()).thenReturn(componentsAcDisco);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.roofTopACCombinerDisconnect IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//		    	
//		    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.groundLevelACCombinerBoxModel IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//		    	
//		    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.groundLevelACCombinerDisconnectModel IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//		    	
//		    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.rooftopACCombinerModel IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//		    	
//		    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.rooftopACCombinerModelTwo IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//		    	
//		    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.roofTopACDisco IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//   	    	}else if(scenario.get(i).get(0).equals("ACCombiner")) {
//   	    		List<ACCombinerFavLibraryEntity> componentsAcComb =  new ArrayList<ACCombinerFavLibraryEntity>();
//   	    		when(em.createQuery("SELECT u from ACCombinerFavLibraryEntity u where u.authentificationEntity.id = :p1 AND u.aCCombinerSLC.id IN :p2 ")).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.setParameter("p1",  scenario.get(i).get(2))).thenReturn(mockedQuery);
//   	    		when(mockedQuery.setParameter("p2",  scenario.get(i).get(1))).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.getResultList()).thenReturn(componentsAcComb);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//                	+ "p.permitEntity.authentificationEntity.id = :p1 and p.roofTopACCombiner IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//		    	
//		    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.groundLevelACCombinerBoxModel IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//		    	
//		    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.aCCombinerInstalled IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//   	    	}else if(scenario.get(i).get(0).equals("JBox")) {
//   	    		List<JunctionBoxFavLibraryEntity> componentsJbox =  new ArrayList<JunctionBoxFavLibraryEntity>();
//   	    		when(em.createQuery("SELECT u from JunctionBoxFavLibraryEntity u where u.authentificationEntity.id = :p1 AND u.jBox.id IN :p2 ")).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.setParameter("p1",  scenario.get(i).get(2))).thenReturn(mockedQuery);
//   	    		when(mockedQuery.setParameter("p2",  scenario.get(i).get(1))).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.getResultList()).thenReturn(componentsJbox);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.roofTopDcJboxType IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//		    	
//		    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.dcJboxType IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//		    	
//		    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.roofTopJbox IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//		    	
//		    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.roofTopJboxDC IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//   	    	}else if(scenario.get(i).get(0).equals("DCDisconnect")) {
//   	    		List<DcCombinerorDiscFavoriteEntity> componentsDcDisco =  new ArrayList<DcCombinerorDiscFavoriteEntity>();
//   	    		when(em.createQuery("SELECT u from DcCombinerorDiscFavoriteEntity u where u.authentificationEntity.id = :p1 AND u.dCCombinerDisconnectEntity.id IN :p2 ")).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.setParameter("p1",  scenario.get(i).get(2))).thenReturn(mockedQuery);
//   	    		when(mockedQuery.setParameter("p2",  scenario.get(i).get(1))).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.getResultList()).thenReturn(componentsDcDisco);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.disconnectModel IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//		    	
//		    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.disconnectModelTwo IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//		    	
//		    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.disconnectModelThree IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//		    	
//		    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.roofTopDCDisco IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//		    	
//		    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.roofTopDCCombiner IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//   	    	}else if(scenario.get(i).get(0).equals("flashing")) {
//   	    		List<FlashingFavLibraryEntity> componentsFlash =  new ArrayList<FlashingFavLibraryEntity>();
//   	    		when(em.createQuery("SELECT u from FlashingFavLibraryEntity u where u.authentificationEntity.id = :p1 AND u.flashing.id IN :p2 ")).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.setParameter("p1",  scenario.get(i).get(2))).thenReturn(mockedQuery);
//   	    		when(mockedQuery.setParameter("p2",  scenario.get(i).get(1))).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.getResultList()).thenReturn(componentsFlash);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.flashing IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//   	    	}else if(scenario.get(i).get(0).equals("Revenue or Performance Monitoring Meter")) {
//   	    		List<LeasePPAMeterFavLibraryEntity> componentsRev =  new ArrayList<LeasePPAMeterFavLibraryEntity>();
//   	    		when(em.createQuery("SELECT u from LeasePPAMeterFavLibraryEntity u where u.authentificationEntity.id = :p1 AND u.leasePPAMeter.id IN :p2 ")).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.setParameter("p1",  scenario.get(i).get(2))).thenReturn(mockedQuery);
//   	    		when(mockedQuery.setParameter("p2",  scenario.get(i).get(1))).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.getResultList()).thenReturn(componentsRev);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//	                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.leasePPAMeter IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//   	    	}else if(scenario.get(i).get(0).equals("battery")) {
//   	    		List<BatteryFavLibraryEntity> componentsBatt =  new ArrayList<BatteryFavLibraryEntity>();
//   	    		when(em.createQuery("SELECT u from BatteryFavLibraryEntity u where u.authentificationEntity.id = :p1 AND u.battery.id IN :p2 ")).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.setParameter("p1",  scenario.get(i).get(2))).thenReturn(mockedQuery);
//   	    		when(mockedQuery.setParameter("p2",  scenario.get(i).get(1))).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.getResultList()).thenReturn(componentsBatt);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitAdditionalInfoEntity p Where "
//		                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.battery IN :p2 ")).thenReturn(mockedQuery);
//		    	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//		    	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//   	    	}else if(scenario.get(i).get(0).equals("tiltLegs")) {
//   	    		List<TiltLegsFavLibraryEntity> componentsTilt =  new ArrayList<TiltLegsFavLibraryEntity>();
//   	    		when(em.createQuery("SELECT u from TiltLegsFavLibraryEntity u where u.authentificationEntity.id = :p1 AND u.tiltLegs.id IN :p2 ")).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.setParameter("p1",  scenario.get(i).get(2))).thenReturn(mockedQuery);
//   	    		when(mockedQuery.setParameter("p2",  scenario.get(i).get(1))).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.getResultList()).thenReturn(componentsTilt);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitAdditionalInfoEntity p Where "
//		                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.tiltLegsMod IN :p2 ")).thenReturn(mockedQuery);
//			  	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//			  	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//   	    	}else if(scenario.get(i).get(0).equals("Proposed Sub Panel")) {
//   	    		List<ProposedSubPanelFavLibraryEntity> componentsSub =  new ArrayList<ProposedSubPanelFavLibraryEntity>();
//   	    		when(em.createQuery("SELECT u from ProposedSubPanelFavLibraryEntity u where u.authentificationEntity.id = :p1 AND u.proposedSubPanel.id IN :p2 ")).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.setParameter("p1",  scenario.get(i).get(2))).thenReturn(mockedQuery);
//   	    		when(mockedQuery.setParameter("p2",  scenario.get(i).get(1))).thenReturn(mockedQuery);
//   	   	    	when(mockedQuery.getResultList()).thenReturn(componentsSub);
//   	   	    	
//   	   	    	when(em.createQuery("SELECT p from PermitProjectSiteInfoEntity p Where "
//		                  	+ "p.permitEntity.authentificationEntity.id = :p1 and p.proposedSubPanel IN :p2 ")).thenReturn(mockedQuery);
//			  	when(mockedQuery.setParameter(Mockito.anyString(),  Mockito.any())).thenReturn(mockedQuery);
//			  	when(mockedQuery.getResultList()).thenReturn(permitArray1);
//   	    	}
//   	    	permitService.removeFromUsersFavList((String)scenario.get(i).get(0), (String[])scenario.get(i).get(1), (Integer)scenario.get(i).get(2), (String)scenario.get(i).get(3));
//   		 }
//       }
//
//    @Test
//   	public void checkComponentsSelec() {
//		List<List<String>> scenario = new ArrayList<List<String>>();
//		scenario.add(new ArrayList<String>());
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
//		
//		scenario.add(new ArrayList<String>());
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		
//		scenario.add(new ArrayList<String>());
//		scenario.get(2).add("");
//		scenario.get(2).add("");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("Removed");
//		scenario.get(2).add("");
//		
//		scenario.add(new ArrayList<String>());
//		scenario.get(3).add("");
//		scenario.get(3).add("");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("Fav Removed");
//		scenario.get(3).add("");
//		
//		scenario.add(new ArrayList<String>());
//		scenario.get(4).add("");
//		scenario.get(4).add("");
//		scenario.get(4).add("Cmodulev2");
//		scenario.get(4).add("Inverters");
//		scenario.get(4).add("DCOptimizerEntity");
//		scenario.get(4).add("RailRacking");
//		scenario.get(4).add("RailRacking");
//		scenario.get(4).add("RailRacking");
//		scenario.get(4).add("DCCombinerDisconnectEntity");
//		scenario.get(4).add("DCCombinerDisconnectEntity");
//		scenario.get(4).add("DCCombinerDisconnectEntity");
//		scenario.get(4).add("ACDisconnect");
//		scenario.get(4).add("ACDisconnect");
//		scenario.get(4).add("DCCombinerDisconnectEntity");
//		scenario.get(4).add("ACCombinerSLC");
//		scenario.get(4).add("ACDisconnect");
//		scenario.get(4).add("RoofAttachmentsEntity");
//		scenario.get(4).add("Inverters");
//		scenario.get(4).add("Inverters");
//		scenario.get(4).add("Inverters");
//		scenario.get(4).add("Inverters");
//		scenario.get(4).add("");
//		
//		Query Cmodulev2Query = mock(Query.class);
//		when(em.createQuery("SELECT u FROM Cmodulev2 u WHERE CONCAT(u.make, ':',u.model) =:p1 "))
//				.thenReturn(Cmodulev2Query);
//		when(Cmodulev2Query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(Cmodulev2Query);
//		when(Cmodulev2Query.getSingleResult()).thenReturn(null);
//
//		Query InvertersQuery = mock(Query.class);
//		when(em.createQuery("SELECT u FROM Inverters u WHERE CONCAT(u.make, ':',u.model) =:p1 "))
//				.thenReturn(InvertersQuery);
//		when(InvertersQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(InvertersQuery);
//		when(InvertersQuery.getSingleResult()).thenReturn(null);
//		
//		Query DCOptimizerEntityQuery = mock(Query.class);
//		when(em.createQuery("SELECT u FROM DCOptimizerEntity u WHERE CONCAT(u.manufacturer, ':',u.model) =:p1 "))
//				.thenReturn(DCOptimizerEntityQuery);
//		when(DCOptimizerEntityQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(DCOptimizerEntityQuery);
//		when(DCOptimizerEntityQuery.getSingleResult()).thenReturn(null);
//		
//		Query RailRackingQuery = mock(Query.class);
//		when(em.createQuery("SELECT u FROM RailRacking u WHERE u.id =:p1 "))
//				.thenReturn(RailRackingQuery);
//		when(RailRackingQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(RailRackingQuery);
//		when(RailRackingQuery.getSingleResult()).thenReturn(null);
//		
//		Query DCCombinerDisconnectQuery = mock(Query.class);
//		when(em.createQuery("SELECT u FROM DCCombinerDisconnectEntity u WHERE CONCAT(u.manufacturer, ':',u.model) =:p1 "))
//				.thenReturn(DCCombinerDisconnectQuery);
//		when(DCCombinerDisconnectQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(DCCombinerDisconnectQuery);
//		when(DCCombinerDisconnectQuery.getSingleResult()).thenReturn(null);
//		
//		Query ACDisconnectQuery = mock(Query.class);
//		when(em.createQuery("SELECT u FROM ACDisconnect u WHERE CONCAT(u.id, ':',u.manufacturer, ':',u.model) =:p1 "))
//				.thenReturn(ACDisconnectQuery);
//		when(ACDisconnectQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(ACDisconnectQuery);
//		when(ACDisconnectQuery.getSingleResult()).thenReturn(null);
//		
//		Query ACCombinerSLCQuery = mock(Query.class);
//		when(em.createQuery("SELECT u FROM ACCombinerSLC u WHERE CONCAT(u.id, ':',u.manufacturer, ':',u.model) =:p1 "))
//				.thenReturn(ACCombinerSLCQuery);
//		when(ACCombinerSLCQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(ACCombinerSLCQuery);
//		when(ACCombinerSLCQuery.getSingleResult()).thenReturn(null);
//		
//		Query RoofAttachmentQuery = mock(Query.class);
//		when(em.createQuery("SELECT u FROM RoofAttachmentsEntity u WHERE u.id =:p1 "))
//				.thenReturn(RoofAttachmentQuery);
//		when(RoofAttachmentQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(RoofAttachmentQuery);
//		when(RoofAttachmentQuery.getSingleResult()).thenReturn(null);
//		
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			permitService.checkComponentsSelec(scenario.get(i).get(0), scenario.get(i).get(1),
//					scenario.get(i).get(2), scenario.get(i).get(3),scenario.get(i).get(4), scenario.get(i).get(5),
//					scenario.get(i).get(6), scenario.get(i).get(7),scenario.get(i).get(8), scenario.get(i).get(9),
//					scenario.get(i).get(10), scenario.get(i).get(11),scenario.get(i).get(12), scenario.get(i).get(13),
//					scenario.get(i).get(14), scenario.get(i).get(15),scenario.get(i).get(16), scenario.get(i).get(17),
//					scenario.get(i).get(18), scenario.get(i).get(19),scenario.get(i).get(20), scenario.get(i).get(21)
//					);
//		}
//	}
//   
//
//    @Test
//   	public void AttachmentSendMail() {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		for (int i = 0; i < scenario.size(); i++) {
//			permitService.AttachmentSendMail((String)scenario.get(i).get(0),(String)scenario.get(i).get(1),(String)scenario.get(i).get(2),(String)scenario.get(i).get(3),(String)scenario.get(i).get(4),(MultipartFile[])scenario.get(i).get(5));
//		}
//    }
//    
//    @Test
//   	public void getRemovedListManufAndModel() throws IOException {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		String[] removedFavList1 = { "", null, "1233" };
//		scenario.get(1).add(removedFavList1);
//		List<DCCombinerDisconnectEntity> DCCombinerDisconnectList1 = new ArrayList<DCCombinerDisconnectEntity>();
//		DCCombinerDisconnectList1.add(null);
//		scenario.get(1).add(DCCombinerDisconnectList1);
//		
//		scenario.add(new ArrayList<Object>());
//		String[] removedFavList = {"1233" };
//		scenario.get(2).add(removedFavList);
//		List<DCCombinerDisconnectEntity> DCCombinerDisconnectList = new ArrayList<DCCombinerDisconnectEntity>();
//		DCCombinerDisconnectEntity dCCombinerDisconnect = new DCCombinerDisconnectEntity();
//		DCCombinerDisconnectList.add(dCCombinerDisconnect);
//		DCCombinerDisconnectEntity dCCombinerDisconnect2 = new DCCombinerDisconnectEntity();
//		dCCombinerDisconnect2.setManufacturer("testMake");
//		dCCombinerDisconnect2.setModel("testMod");
//		DCCombinerDisconnectList.add(dCCombinerDisconnect2);
//		scenario.get(2).add(DCCombinerDisconnectList);
//		
//		Query query = mock(Query.class);
//		when(em.createQuery("SELECT u from DCCombinerDisconnectEntity u WHERE u.id IN :p1")).thenReturn(query);
//		when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
//
//		List<List<String>> expectedResult = new ArrayList<List<String>>();
//		expectedResult.add(new ArrayList<String>());
//		expectedResult.add(new ArrayList<String>());
//		List<String> DCModel = new ArrayList<String>();
//		DCModel.add(null+":"+null);
//		DCModel.add("testMake:testMod");
//		expectedResult.add(DCModel);
//		for (int i = 0; i < scenario.size(); i++) {
//			when(query.getResultList()).thenReturn((List<DCCombinerDisconnectEntity>)scenario.get(i).get(1));
//			List<String> result = permitService.getRemovedListManufAndModel((String[]) scenario.get(i).get(0));
//
//		}
//    }
//    
//    @Test
//   	public void getRemovedInverterListManufModel() throws IOException {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		String[] removedFavList1 = { "", null, "1233" };
//		scenario.get(1).add(removedFavList1);
//		List<Inverters> InvertersList1 = new ArrayList<Inverters>();
//		InvertersList1.add(null);
//		scenario.get(1).add(InvertersList1);
//		
//		scenario.add(new ArrayList<Object>());
//		String[] removedFavList = { "1233" };
//		scenario.get(2).add(removedFavList);
//		List<Inverters> InvertersList = new ArrayList<Inverters>();
//		Inverters Inverters = new Inverters();
//		InvertersList.add(Inverters);
//		Inverters Inverters2 = new Inverters();
//		Inverters2.setMake("testMake");
//		Inverters2.setModel("testMod");
//		InvertersList.add(Inverters2);
//		scenario.get(2).add(InvertersList);
//		
//		Query query = mock(Query.class);
//		when(em.createQuery("SELECT u from Inverters u WHERE u.id IN :p1")).thenReturn(query);
//		when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
//
//		List<List<String>> expectedResult = new ArrayList<List<String>>();
//		expectedResult.add(new ArrayList<String>());
//		expectedResult.add(new ArrayList<String>());
//		List<String> DCModel = new ArrayList<String>();
//		DCModel.add(null+":"+null);
//		DCModel.add("testMake:testMod");
//		expectedResult.add(DCModel);
//		for (int i = 0; i < scenario.size(); i++) {
//			when(query.getResultList()).thenReturn((List<Inverters>)scenario.get(i).get(1));
//			List<String> result = permitService.getRemovedInverterListManufModel((String[]) scenario.get(i).get(0));
//
//		}
//		}
//    
//    @Test
//   	public void getUtilityCompanyInfo() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("1247454");
//		scenario.get(1).add("1254");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("1247454");
//		scenario.get(2).add("1254");
//		PermitCompanyInfoEntityResultPrime permitUtili = new PermitCompanyInfoEntityResultPrime();
//		scenario.get(2).add(permitUtili);
//		PermitCompanyInfoEntity permitUtiliEntity = new PermitCompanyInfoEntity();
//		scenario.get(2).add(permitUtiliEntity);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("1347454");
//		scenario.get(3).add("1354");
//		PermitCompanyInfoEntityResultPrime permitUtili3 = new PermitCompanyInfoEntityResultPrime();
//		scenario.get(3).add(permitUtili3);
//		PermitCompanyInfoEntity permitUtiliEntity3 = new PermitCompanyInfoEntity();
//		scenario.get(3).add(permitUtiliEntity3);
//		List<AuthentificationEntity> user = new ArrayList<AuthentificationEntity>();
//		scenario.get(3).add(user);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add("1447454");
//		scenario.get(4).add("1454");
//		PermitCompanyInfoEntityResultPrime permitUtili4 = new PermitCompanyInfoEntityResultPrime();
//		scenario.get(4).add(permitUtili4);
//		PermitCompanyInfoEntity permitUtiliEntity4 = new PermitCompanyInfoEntity();
//		scenario.get(4).add(permitUtiliEntity4);
//		List<AuthentificationEntity> listUser = new ArrayList<AuthentificationEntity>();
//		AuthentificationEntity user1 = new AuthentificationEntity();
//		listUser.add(user1);
//		scenario.get(4).add(listUser);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add("1547454");
//		scenario.get(5).add("1554");
//		PermitCompanyInfoEntityResultPrime permitUtili5 = new PermitCompanyInfoEntityResultPrime();
//		permitUtili5.setOpened(true);
//		scenario.get(5).add(permitUtili5);
//		PermitCompanyInfoEntity permitUtiliEntity5 = new PermitCompanyInfoEntity();
//		scenario.get(5).add(permitUtiliEntity5);
//		scenario.get(5).add(null);
//		
//		Query query = mock(Query.class);
//		when(em.createQuery("SELECT new com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResultPrime  "
//				+ " ( u.meterNumber, " + " u.accountNumber,  " + " u.serviceAgreement, "
//				+ " u.existingRate, " + " u.newRate, " + " u.costPaid, " + " u.claimedFederal, "
//				+ " u.nameDeveloper, " + " u.checkApply, " + " u.kwhUsage, "
//				+ " u.authorizatingAdvanced, " + " u.contactHomeowner, " + " u.scir, "
//				+ " u.systemOwner, " + " u.paceFinanced, " + " u.meterAccess, " + " u.plannedAnnual, "
//				+ " u.newService, " + " u.newSubdivition, " + " u.nonProfileStatus, "
//				+ " u.systemMeetDIH, " + " u.contactType,   " + " u.meterDisco,     "
//				+ " u.meterBuilding,    " + " u.undertrainedAnimal,  " + " u.otherSystem,  "
//				+ " u.otherSystemText," + " u.jBoxUsedBetween," + " u.developersName, "
//				+ "	u.developmentName,  " + "	u.InterconnectionType, "
//				+ "	u.peaceFinancedByEntity, " + "	u.namePartyReceivData," + " u.textOtherRate,"
//				+ " u.textOtherNewRate," + " u.customerParticipated, "
//				+ " u.peaceFinancedByEntityOther, " + " u.typeCustomer, " + " u.typeCustomerOther, "
//				+ " u.typeCustomerOtherText, " + " u.newRateCommercial, "
//				+ " u.otherNewRateCommercial, " + " u.snemApplication, " + " u.thisIsNewService, "
//				+ " u.developerrsNam, " + " u.developersNameSecond, " + " u.theProjectIsLocated, "
//				+ " u.developemName, " + " u.projectIs, " + " u.otherProjectIs, "
//				+ " u.projectWasPace, " + " u.choosePaceFinanced, " + " u.textOtherChoosePace, "
//				+ " u.electriccvehichule1, " + " u.electricvehichule2, " + " u.electricvehichule3, "
//				+ " u.electricvehichule4, " + " u.electricvehichuleOther, " + " u.otherElectricVe, "
//				+ " u.applicationType, " + " u.theAcDisconnectIsMoreThan, " + " u.coverageamount, "
//				+ " u.insuringcompanyName, " + " u.expectedDate, " + " u.oneOrMoreOfTheAss, "
//				+ " u.theLocalPermitting, " + " u.requestPGToshutDown, "
//				+ " u.RequestPGaEToInstalNewS, " + " u.nameOfDevelopersLease, "
//				+ " u.clamedfederalIncomeTax, " + " u.whichProgram, " + " u.whatRuleOrRules, "
//				+ " u.deEnergizingOfTheService, " + " u.whatDayOfTheWeek, " + " u.whatTimeOfDay, "
//				+ " u.willYouNeedToObtain, " + " u.describeThePointInt,  " + " u.theScirOfTheMain, "
//				+ " u.policy, " + " u.howManyHours, " + " u.developerrrsName, "
//				+ " u.PGaEPersonnelWilleNeed, " + " u.mayOurRepresentativesContact, "
//				+ " u.iAuthorizeAdvanced, " + " u.meterOrACDisconnectInBuilding, "
//				+ " u.unrestrainedAnimal, " + " u.checkTheApplicableOther, "
//				+ " u.checkApplicableBoxesOther, " + " u.checkIfTheProposedSystemProduce, "
//				+ " u.performanceMonitAndRep, " + " u.anExistingSolarOrWind, "
//				+ " u.clamedfederalIncomeTaxStr, " + " u.listAnyVariations, "
//				+ " u.textOtherContractType, " + " u.newRateOthers, " + " u.newRateOthersText, "
//				+ " u.uploadCommentsUtility, " + " u.uploadFileExisting,"
//				+ " u.uploadFileJustification," + " u.uploadFileListAdreess,"
//				+ " u.uploadFileSwitchgear," + " u.uploadFileInterconnection,"
//				+ " u.opened, u.openedBy.id, u.hasEditRequest  ) "
//				+ " from PermitCompanyInfoEntity u " + " where u.permitEntity.id = :p1 ")).thenReturn(query);
//		
//		Query query2 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitCompanyInfoEntity u WHERE u.permitEntity.id = :p1 ")).thenReturn(query2);
//		
//		Query userQuery = mock(Query.class);
//		when(em.createQuery(" SELECT u FROM AuthentificationEntity u WHERE u.id = :p1  ")).thenReturn(userQuery);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
//			when(query.getSingleResult()).thenReturn((PermitCompanyInfoEntityResultPrime)scenario.get(i).get(2));
//			
//			when(query2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query2);
//			when(query2.getSingleResult()).thenReturn((PermitCompanyInfoEntity)scenario.get(i).get(3));
//			
//			when(userQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(userQuery);
//			when(userQuery.getResultList()).thenReturn((List<AuthentificationEntity>)scenario.get(i).get(4));
//			 permitService.getUtilityCompanyInfo((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//		} 		
//    }
//    
//    @Test
//   	public void saveUtilityCompany() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		PermitCompanyInfoEntity permitCompanyInfoEntity = new PermitCompanyInfoEntity();
//		scenario.get(1).add(permitCompanyInfoEntity);
//		
//		String [] expectedresult = {"fail","fail"};
//		Query query = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitCompanyInfoEntity u WHERE u.permitEntity.id = :p1 ")).thenReturn(query);
//		for (int i = 0; i < scenario.size(); i++) {
//			when(query.setParameter("p1", (String)scenario.get(i).get(1))).thenReturn(query);
//			when(query.getSingleResult()).thenReturn((PermitCompanyInfoEntity)scenario.get(i).get(2));
//			String result = permitService.saveUtilityCompany((GetPermitByIdResult)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//		} 	
//    }
//    
//    @Test
//   	public void getModuleNameSpace() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("Fav Removed");
//		scenario.get(2).add("3.2");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("Removed");
//		scenario.get(3).add("5,4");
//		
//		Query query = mock(Query.class);
//		when(em.createQuery("Select u.stc  "
//				+ " from Cmodulev2 u  "
//				+ " where u.make = :p1 "
//				+ " AND u.model = :p2 ")).thenReturn(query);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
//			when(query.getSingleResult()).thenReturn((String)scenario.get(i).get(1));
//			 permitService.getModuleNameSpace((String)scenario.get(i).get(0));
//		} 		
//    }
//    
//    @Test
//   	public void getUtilityCompany() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("14f");
//		scenario.get(2).add(null);	
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("Other");
//		scenario.get(3).add(null);	
//		
//		Query query = mock(Query.class);
//		when(em.createQuery("SELECT u from ElectricalUtilityEntity u WHERE u.id = :p1 ")).thenReturn(query);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(query.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query);
//			when(query.getSingleResult()).thenReturn((PermitEntity)scenario.get(i).get(1));
//			 permitService.getUtilityCompany((String)scenario.get(i).get(0));
//		} 		
//    }
//    
//    @Test
//   	public void getPermit() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("14f");
//		scenario.get(2).add(null);	
//		
//		
//		Query query = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u WHERE u.id = :p1 ")).thenReturn(query);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(query.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query);
//			when(query.getSingleResult()).thenReturn((PermitEntity)scenario.get(i).get(1));
//			 permitService.getPermit((String)scenario.get(i).get(0));
//		} 		
//    }
//    
//    @Test
//   	public void getUtilityName() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("14f");
//		scenario.get(2).add(null);	
//		
//		
//		Query query = mock(Query.class);
//		when(em.createQuery("SELECT u.utilityCompanyName from ElectricalUtilityEntity u WHERE u.id = :p1 ")).thenReturn(query);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(query.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query);
//			when(query.getSingleResult()).thenReturn((PermitEntity)scenario.get(i).get(1));
//			 permitService.getUtilityName((String)scenario.get(i).get(0));
//		} 			
//    }
//    
//    @Test
//   	public void insetNotetoADV() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		Query query = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u WHERE u.id = :p1 ")).thenReturn(query);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(query.setParameter("p1", (String)scenario.get(i).get(1))).thenReturn(query);
//			when(query.getSingleResult()).thenReturn((PermitEntity)scenario.get(i).get(2));
//			 permitService.insetNotetoADV((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//		} 		
//    }
//    
//    @Test
//   	public void insertNoteFiletoADV() throws IOException {
//		List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//
//		for (int i = 0; i < scenario.size(); i++) {
//			permitService.insertNoteFiletoADV((MultipartFile) scenario.get(i).get(0), (String) scenario.get(i).get(1),
//					(String) scenario.get(i).get(2),"",
//					(PermitEntity) scenario.get(i).get(3));
//		}		
//    }
//    
//    @Test
//   	public void getRoofAttachment() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add(null);
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add(null);
//		scenario.get(2).add("1");
//		scenario.get(2).add(2);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		scenario.get(2).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add(null);
//		scenario.get(3).add("1");
//		scenario.get(3).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList = new ArrayList<RoofTypeandAttachmentEntity>();
//		scenario.get(3).add(RoofAttachmentList);
//		scenario.get(3).add(null);
//		scenario.get(3).add(null);
//		
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add(null);
//		scenario.get(4).add("1");
//		scenario.get(4).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList2 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList2.add(RoofAttachment);
//		scenario.get(4).add(RoofAttachmentList2);
//		scenario.get(4).add(null);
//		scenario.get(4).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add("2");
//		scenario.get(5).add("1");
//		scenario.get(5).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList3 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment3 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList3.add(RoofAttachment3);
//		scenario.get(5).add(RoofAttachmentList3);
//		scenario.get(5).add(null);
//		scenario.get(5).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add("2");
//		scenario.get(6).add("1");
//		scenario.get(6).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList6 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment6 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList6.add(RoofAttachment6);
//		scenario.get(6).add(RoofAttachmentList6);
//		scenario.get(6).add(null);
//		scenario.get(6).add("Comp Shingle");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add("2");
//		scenario.get(7).add("1");
//		scenario.get(7).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList7 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment7 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList7.add(RoofAttachment7);
//		scenario.get(7).add(RoofAttachmentList7);
//		scenario.get(7).add(RoofAttachment7);
//		scenario.get(7).add("Comp Shingle");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(8).add("2");
//		scenario.get(8).add("1");
//		scenario.get(8).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList8 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment8 = new RoofTypeandAttachmentEntity();
//		RoofAttachment8.setCompShingle("");
//		RoofAttachmentList8.add(RoofAttachment8);
//		scenario.get(8).add(RoofAttachmentList8);
//		scenario.get(8).add(RoofAttachment8);
//		scenario.get(8).add("Comp Shingle");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(9).add("2");
//		scenario.get(9).add("1");
//		scenario.get(9).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList9 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment9 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList9.add(RoofAttachment9);
//		scenario.get(9).add(RoofAttachmentList9);
//		scenario.get(9).add(RoofAttachment7);
//		scenario.get(9).add("Flat Concrete Tile");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(10).add("2");
//		scenario.get(10).add("1");
//		scenario.get(10).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList10 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment10 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList10.add(RoofAttachment10);
//		scenario.get(10).add(RoofAttachmentList10);
//		scenario.get(10).add(null);
//		scenario.get(10).add("Flat Concrete Tile");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(11).add("2");
//		scenario.get(11).add("1");
//		scenario.get(11).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList11 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment11 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList11.add(RoofAttachment11);
//		scenario.get(11).add(RoofAttachmentList11);
//		scenario.get(11).add(RoofAttachment11);
//		scenario.get(11).add("Roman Curved Tile (S or W)");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(12).add("2");
//		scenario.get(12).add("1");
//		scenario.get(12).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList12 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment12 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList12.add(RoofAttachment12);
//		scenario.get(12).add(RoofAttachmentList12);
//		scenario.get(12).add(null);
//		scenario.get(12).add("Roman Curved Tile (S or W)");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(13).add("2");
//		scenario.get(13).add("1");
//		scenario.get(13).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList13 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment13 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList13.add(RoofAttachment13);
//		scenario.get(13).add(RoofAttachmentList13);
//		scenario.get(13).add(RoofAttachment13);
//		scenario.get(13).add("Standing Metal Seam");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(14).add("2");
//		scenario.get(14).add("1");
//		scenario.get(14).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList14 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment14 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList14.add(RoofAttachment14);
//		scenario.get(14).add(RoofAttachmentList14);
//		scenario.get(14).add(null);
//		scenario.get(14).add("Standing Metal Seam");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(15).add("2");
//		scenario.get(15).add("1");
//		scenario.get(15).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList15 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment15 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList15.add(RoofAttachment15);
//		scenario.get(15).add(RoofAttachmentList15);
//		scenario.get(15).add(RoofAttachment15);
//		scenario.get(15).add("Barrel Curve Metal (Tile Look)");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(16).add("2");
//		scenario.get(16).add("1");
//		scenario.get(16).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList16 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment16 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList16.add(RoofAttachment16);
//		scenario.get(16).add(RoofAttachmentList16);
//		scenario.get(16).add(null);
//		scenario.get(16).add("Barrel Curve Metal (Tile Look)");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(17).add("2");
//		scenario.get(17).add("1");
//		scenario.get(17).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList17 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment17 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList17.add(RoofAttachment17);
//		scenario.get(17).add(RoofAttachmentList17);
//		scenario.get(17).add(RoofAttachment17);
//		scenario.get(17).add("Rolled Comp");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(18).add("2");
//		scenario.get(18).add("1");
//		scenario.get(18).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList18 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment18 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList18.add(RoofAttachment18);
//		scenario.get(18).add(RoofAttachmentList18);
//		scenario.get(18).add(null);
//		scenario.get(18).add("Rolled Comp");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(19).add("2");
//		scenario.get(19).add("1");
//		scenario.get(19).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList19 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment19 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList19.add(RoofAttachment19);
//		scenario.get(19).add(RoofAttachmentList19);
//		scenario.get(19).add(RoofAttachment19);
//		scenario.get(19).add("Trapezoidal Metal");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(20).add("2");
//		scenario.get(20).add("1");
//		scenario.get(20).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList20 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment20 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList20.add(RoofAttachment20);
//		scenario.get(20).add(RoofAttachmentList20);
//		scenario.get(20).add(null);
//		scenario.get(20).add("Trapezoidal Metal");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(21).add("2");
//		scenario.get(21).add("1");
//		scenario.get(21).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList21 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment21 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList21.add(RoofAttachment21);
//		scenario.get(21).add(RoofAttachmentList21);
//		scenario.get(21).add(RoofAttachment21);
//		scenario.get(21).add("Wood or Cedar Shake");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(22).add("2");
//		scenario.get(22).add("1");
//		scenario.get(22).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList22 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment22 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList22.add(RoofAttachment22);
//		scenario.get(22).add(RoofAttachmentList22);
//		scenario.get(22).add(null);
//		scenario.get(22).add("Wood or Cedar Shake");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(23).add("2");
//		scenario.get(23).add("1");
//		scenario.get(23).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList23 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment23 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList23.add(RoofAttachment23);
//		scenario.get(23).add(RoofAttachmentList23);
//		scenario.get(23).add(RoofAttachment23);
//		scenario.get(23).add("Fiber Cement");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(24).add("2");
//		scenario.get(24).add("1");
//		scenario.get(24).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList24 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment24 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList24.add(RoofAttachment24);
//		scenario.get(24).add(RoofAttachmentList24);
//		scenario.get(24).add(null);
//		scenario.get(24).add("Fiber Cement");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(25).add("2");
//		scenario.get(25).add("1");
//		scenario.get(25).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList25 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment25 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList25.add(RoofAttachment25);
//		scenario.get(25).add(RoofAttachmentList25);
//		scenario.get(25).add(RoofAttachment25);
//		scenario.get(25).add("Slate");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(26).add("2");
//		scenario.get(26).add("1");
//		scenario.get(26).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList26 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment26 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList26.add(RoofAttachment26);
//		scenario.get(26).add(RoofAttachmentList26);
//		scenario.get(26).add(null);
//		scenario.get(26).add("Slate");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(27).add("2");
//		scenario.get(27).add("1");
//		scenario.get(27).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList27 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment27 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList27.add(RoofAttachment27);
//		scenario.get(27).add(RoofAttachmentList27);
//		scenario.get(27).add(RoofAttachment27);
//		scenario.get(27).add("Membrane (TPO, EPDM, PVC)");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(28).add("2");
//		scenario.get(28).add("1");
//		scenario.get(28).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList28 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment28 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList28.add(RoofAttachment28);
//		scenario.get(28).add(RoofAttachmentList28);
//		scenario.get(28).add(null);
//		scenario.get(28).add("Membrane (TPO, EPDM, PVC)");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(29).add("2");
//		scenario.get(29).add("1");
//		scenario.get(29).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList29 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment29 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList29.add(RoofAttachment29);
//		scenario.get(29).add(RoofAttachmentList29);
//		scenario.get(29).add(RoofAttachment29);
//		scenario.get(29).add("Build Up (Tar & Gravel, Asphalt, Bit, etc.)");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(30).add("2");
//		scenario.get(30).add("1");
//		scenario.get(30).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList30 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment30 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList30.add(RoofAttachment30);
//		scenario.get(30).add(RoofAttachmentList30);
//		scenario.get(30).add(null);
//		scenario.get(30).add("Build Up (Tar & Gravel, Asphalt, Bit, etc.)");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(31).add("2");
//		scenario.get(31).add("1");
//		scenario.get(31).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList31 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment31 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList31.add(RoofAttachment31);
//		scenario.get(31).add(RoofAttachmentList31);
//		scenario.get(31).add(RoofAttachment31);
//		scenario.get(31).add("Rolled Ashpault");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(32).add("2");
//		scenario.get(32).add("1");
//		scenario.get(32).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList32 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment32 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList32.add(RoofAttachment32);
//		scenario.get(32).add(RoofAttachmentList32);
//		scenario.get(32).add(null);
//		scenario.get(32).add("Rolled Ashpault");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(33).add("2");
//		scenario.get(33).add("1");
//		scenario.get(33).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList33 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment33 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList33.add(RoofAttachment33);
//		scenario.get(33).add(RoofAttachmentList33);
//		scenario.get(33).add(RoofAttachment33);
//		scenario.get(33).add("Liquid Applied Membrane");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(34).add("2");
//		scenario.get(34).add("1");
//		scenario.get(34).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList34 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment34 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList34.add(RoofAttachment34);
//		scenario.get(34).add(RoofAttachmentList34);
//		scenario.get(34).add(null);
//		scenario.get(34).add("Liquid Applied Membrane");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(35).add("2");
//		scenario.get(35).add("1");
//		scenario.get(35).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList35 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment35 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList35.add(RoofAttachment35);
//		scenario.get(35).add(RoofAttachmentList35);
//		scenario.get(35).add(RoofAttachment35);
//		scenario.get(35).add("Corrugated PolyCarb");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(36).add("2");
//		scenario.get(36).add("1");
//		scenario.get(36).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList36 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment36 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList36.add(RoofAttachment36);
//		scenario.get(36).add(RoofAttachmentList36);
//		scenario.get(36).add(null);
//		scenario.get(36).add("Corrugated PolyCarb");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(37).add("2");
//		scenario.get(37).add("1");
//		scenario.get(37).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList37 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment37 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList37.add(RoofAttachment37);
//		scenario.get(37).add(RoofAttachmentList37);
//		scenario.get(37).add(RoofAttachment37);
//		scenario.get(37).add("Foam");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(38).add("2");
//		scenario.get(38).add("1");
//		scenario.get(38).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList38 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment38 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList38.add(RoofAttachment38);
//		scenario.get(38).add(RoofAttachmentList38);
//		scenario.get(38).add(null);
//		scenario.get(38).add("Foam");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(39).add("2");
//		scenario.get(39).add("1");
//		scenario.get(39).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList39 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment39 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList39.add(RoofAttachment39);
//		scenario.get(39).add(RoofAttachmentList39);
//		scenario.get(39).add(RoofAttachment39);
//		scenario.get(39).add("Corrugated Metal");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(40).add("2");
//		scenario.get(40).add("1");
//		scenario.get(40).add(2);
//		List<RoofTypeandAttachmentEntity>  RoofAttachmentList40 = new ArrayList<RoofTypeandAttachmentEntity>();
//		RoofTypeandAttachmentEntity  RoofAttachment40 = new RoofTypeandAttachmentEntity();
//		RoofAttachmentList40.add(RoofAttachment40);
//		scenario.get(40).add(RoofAttachmentList40);
//		scenario.get(40).add(null);
//		scenario.get(40).add("Corrugated Metal");
//		
//		
//		Query userQuery = mock(Query.class);
//		when(em.createQuery("SELECT u.authentificationEntity.id from PermitEntity u WHERE u.id = :p1 ")).thenReturn(userQuery);
//		
//		Query query = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofTypeandAttachmentEntity u WHERE u.authentificationEntity.id = :p1 ")).thenReturn(query);
//		
//		Query roofTypeQuery = mock(Query.class);
//		when(em.createQuery("SELECT u.typeRoof from RoofMaterialType u WHERE u.id = :p1 ")).thenReturn(roofTypeQuery);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			
//			when(userQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(userQuery);
//			when(userQuery.getSingleResult()).thenReturn((Integer)scenario.get(i).get(2));
//			
//			when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
//			when(query.getResultList()).thenReturn((List<RoofTypeandAttachmentEntity>)scenario.get(i).get(3));
//			when(query.getSingleResult()).thenReturn((RoofTypeandAttachmentEntity)scenario.get(i).get(4));
//			
//			when(roofTypeQuery.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(roofTypeQuery);
//			when(roofTypeQuery.getSingleResult()).thenReturn((String)scenario.get(i).get(5));
//			
//			 permitService.getRoofAttachment((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//		} 		
//    }
//    
//    @Test
//   	public void mapExistingConductor() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		Query query = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u WHERE u.id = :p1 ")).thenReturn(query);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(query.setParameter("p1", (String)scenario.get(i).get(1))).thenReturn(query);
//			when(query.getSingleResult()).thenReturn((PermitEntity)scenario.get(i).get(2));
//			 permitService.mapNoExistingConductor((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//		} 		
//    }
//    
//    @Test
//   	public void mapNoExistingConductor() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		Query query = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitEntity u WHERE u.id = :p1 ")).thenReturn(query);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(query.setParameter("p1", (String)scenario.get(i).get(1))).thenReturn(query);
//			when(query.getSingleResult()).thenReturn((PermitEntity)scenario.get(i).get(2));
//			 permitService.mapNoExistingConductor((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//		} 	
//    }
//    
//    @Test
//   	public void getRailConnectionInfo() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("12");
//		List<RoofAttachmentsEntity> listRoofAttachment = new ArrayList<RoofAttachmentsEntity>();
//		RoofAttachmentsEntity RoofAttachment = new RoofAttachmentsEntity();
//		listRoofAttachment.add(RoofAttachment);
//		scenario.get(2).add(listRoofAttachment);
//		scenario.get(2).add(RoofAttachment);
//		
//		
//		Query query = mock(Query.class);
//		when(em.createQuery("SELECT u from RoofAttachmentsEntity u WHERE u.id = :p1 ")).thenReturn(query);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
//			when(query.getResultList()).thenReturn((List<RoofAttachmentsEntity>)scenario.get(i).get(1));
//			when(query.getSingleResult()).thenReturn((RoofAttachmentsEntity)scenario.get(i).get(2));
//			 permitService.getRailConnectionInfo((String)scenario.get(i).get(0));
//		} 	
//    }
//    
//    @Test
//   	public void isComponentDeleted() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("1");
//		scenario.get(2).add("Module");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("1");
//		scenario.get(3).add("INV");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(4).add("1");
//		scenario.get(4).add("Optimizer");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(5).add("1");
//		scenario.get(5).add("Flashing");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(6).add("1");
//		scenario.get(6).add("RailRacking");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(7).add("1");
//		scenario.get(7).add("RoofAttachment");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(8).add("1");
//		scenario.get(8).add("ACDisconnect");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(9).add("1");
//		scenario.get(9).add("ACCombiner");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(10).add("1");
//		scenario.get(10).add("JBox");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(11).add("1");
//		scenario.get(11).add("DCDisconnect");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(12).add("1");
//		scenario.get(12).add("Revenue or Performance Monitoring Meter");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(13).add("1");
//		scenario.get(13).add("battery");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(14).add("1");
//		scenario.get(14).add("tiltLegs");
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(15).add("1");
//		scenario.get(15).add("Proposed Sub Panel");
//		
//		
//		Query query1 = mock(Query.class);
//		when(em.createQuery("Select u.isDeleted  " + " from Cmodulev2 u  " + " where u.id = :p1 ")).thenReturn(query1);
//		Query query2 = mock(Query.class);
//		when(em.createQuery("SELECT u.isDeleted from Inverters u WHERE u.id = :p1 ")).thenReturn(query2);
//		Query query3 = mock(Query.class);
//		when(em.createQuery("SELECT u.isDeleted FROM DCOptimizerEntity u WHERE  u.id = :p1 ")).thenReturn(query3);
//		Query query4 = mock(Query.class);
//		when(em.createQuery("SELECT u.isDeleted FROM Flashing u WHERE  u.id = :p1 ")).thenReturn(query4);
//		Query query5 = mock(Query.class);
//		when(em.createQuery("SELECT u.isDeleted from RailRacking u WHERE  u.id = :p1  ")).thenReturn(query5);
//		Query query6 = mock(Query.class);
//		when(em.createQuery("SELECT u.isDeleted from RoofAttachmentsEntity u WHERE  u.id = :p1 ")).thenReturn(query6);
//		Query query7 = mock(Query.class);
//		when(em.createQuery("SELECT u.isDeleted from ACDisconnect u WHERE  u.id = :p1")).thenReturn(query7);
//		Query query8 = mock(Query.class);
//		when(em.createQuery("SELECT u.isDeleted from ACCombinerSLC u WHERE  u.id = :p1 ")).thenReturn(query8);
//		Query query9 = mock(Query.class);
//		when(em.createQuery("SELECT u.isDeleted from DCCombinerDisconnectEntity u WHERE  u.id = :p1")).thenReturn(query9);
//		Query query10 = mock(Query.class);
//		when(em.createQuery("SELECT u.isDeleted from DCCombinerDisconnectEntity u WHERE  u.id = :p1 ")).thenReturn(query10);
//		Query query11 = mock(Query.class);
//		when(em.createQuery("SELECT u.isDeleted from LeasePPAMeter u WHERE  u.id = :p1 ")).thenReturn(query11);
//		Query query12 = mock(Query.class);
//		when(em.createQuery("SELECT u.isDeleted from Battery u WHERE  u.id = :p1 ")).thenReturn(query12);
//		Query query13 = mock(Query.class);
//		when(em.createQuery("SELECT u.isDeleted from TiltLegs u WHERE  u.id = :p1 ")).thenReturn(query13);
//		Query query14 = mock(Query.class);
//		when(em.createQuery("SELECT u.isDeleted from ProposedSubPanel u WHERE  u.id = :p1 ")).thenReturn(query14);
//		for (int i = 0; i < scenario.size(); i++) {
//			when(query1.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query1);
//			when(query2.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query2);
//			when(query3.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query3);
//			when(query4.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query4);
//			when(query5.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query5);
//			when(query6.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query6);
//			when(query7.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query7);
//			when(query8.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query8);
//			when(query9.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query9);
//			when(query10.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query10);
//			when(query11.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query11);
//			when(query12.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query12);
//			when(query13.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query13);
//			when(query14.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query14);
//			when(query1.getSingleResult()).thenReturn(null);
//			when(query2.getSingleResult()).thenReturn(null);
//			when(query3.getSingleResult()).thenReturn(null);
//			when(query4.getSingleResult()).thenReturn(null);
//			when(query5.getSingleResult()).thenReturn(null);
//			when(query6.getSingleResult()).thenReturn(null);
//			when(query7.getSingleResult()).thenReturn(null);
//			when(query8.getSingleResult()).thenReturn(null);
//			when(query9.getSingleResult()).thenReturn(null);
//			when(query10.getSingleResult()).thenReturn(null);
//			when(query11.getSingleResult()).thenReturn(null);
//			when(query12.getSingleResult()).thenReturn(null);
//			when(query13.getSingleResult()).thenReturn(null);
//			when(query14.getSingleResult()).thenReturn(null);
//			 permitService.isComponentDeleted((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//		}	
//    }
//    
//    @Test
//   	public void getModPerMicro() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("make:model");
//		scenario.get(1).add(null);
//		scenario.get(1).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("make:model");
//		List<Inverters> listInverter = new ArrayList<Inverters>();
//		Inverters inverter = new Inverters();
//		listInverter.add(inverter);
//		scenario.get(2).add(listInverter);
//		scenario.get(2).add(inverter);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(3).add("make:model");
//		List<Inverters> listInverter3 = new ArrayList<Inverters>();
//		Inverters inverter3 = new Inverters();
//		inverter3.setModPerMicro(3);
//		inverter3.setIacmax("3");
//		listInverter3.add(inverter3);
//		scenario.get(3).add(listInverter3);
//		scenario.get(3).add(inverter3);
//		
//		Query query = mock(Query.class);
//		when(em.createQuery("SELECT u from Inverters u WHERE u.make =:p1 AND u.model =:p2 ")).thenReturn(query);
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
//			when(query.getResultList()).thenReturn((List<Inverters>)scenario.get(i).get(1));
//			when(query.getSingleResult()).thenReturn((Inverters)scenario.get(i).get(2));
//			 permitService.getModPerMicro((String)scenario.get(i).get(0));
//		}
//    }
//    
//    @Test
//   	public void getOcpdMax() {
//    	List<List<Object>> scenario = new ArrayList<List<Object>>();
//		scenario.add(new ArrayList<Object>());
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(1).add("manufacturer");
//		scenario.get(1).add("model");
//		List<Inverters> listInverter = new ArrayList<Inverters>();
//		Inverters inverter = new Inverters();
//		listInverter.add(inverter);
//		scenario.get(1).add(listInverter);
//		scenario.get(1).add(inverter);
//		
//		scenario.add(new ArrayList<Object>());
//		scenario.get(2).add("manufacturer");
//		scenario.get(2).add("model");
//		List<Inverters> listInverter2 = new ArrayList<Inverters>();
//		Inverters inverter2 = new Inverters();
//		inverter2.setOcpd("5.5");
//		listInverter2.add(inverter2);
//		scenario.get(2).add(listInverter2);
//		scenario.get(2).add(inverter2);
//		
//		Query query = mock(Query.class);
//		when(em.createQuery("SELECT u from Inverters u WHERE u.make =:p1 AND u.model =:p2 ")).thenReturn(query);
//		List<String> expectedResult = new ArrayList<String>();
//		expectedResult.add("");
//		expectedResult.add(null);
//		expectedResult.add("5.5");
//		
//		for (int i = 0; i < scenario.size(); i++) {
//			when(query.setParameter("p1", (String)scenario.get(i).get(0))).thenReturn(query);
//			when(query.setParameter("p2", (String)scenario.get(i).get(1))).thenReturn(query);
//			when(query.getResultList()).thenReturn((List<Inverters>)scenario.get(i).get(2));
//			when(query.getSingleResult()).thenReturn((Inverters)scenario.get(i).get(3));
//			String result = permitService.getOcpdMax((String)scenario.get(i).get(0),(String)scenario.get(i).get(1));
//
//			
//
//		}
//    }
//}
