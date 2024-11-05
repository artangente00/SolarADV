//package solar.test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
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
//
//import com.PlayGroundAdv.Solar.Entity.AuthentificationEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitAdvEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitCompanyInfoEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitEntity;
//import com.PlayGroundAdv.Solar.Entity.PermitHomeSiteInfoEntity;
//import com.PlayGroundAdv.Solar.model.ConverterCorrectionModel;
//import com.PlayGroundAdv.Solar.Services.CheckValueTypesService;
//import com.PlayGroundAdv.Solar.Services.HistoryActivityService;
//import com.PlayGroundAdv.Solar.Services.InterConnectionMaps;
//import com.PlayGroundAdv.Solar.Services.NotificationEntityService;
//
//public class TestInterConnectionMaps {
//
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
//	@Mock
//	NotificationEntityService notificationEntityService;
//	 @Mock
//	 private CriteriaQuery criteriaQuery;
//
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
//	InterConnectionMaps interConnectionMaps = new InterConnectionMaps();
//
//
//    @Before
//	public void setupMock() {
//    	interConnectionMaps = new InterConnectionMaps();
//	       MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void testgetValues() {
//		Query mockedQuery1 = mock(Query.class);
//		when(em.createQuery("Select u  "
//				+ " from PermitEntity u  "
//				+ " where u.id = :p1 ")).thenReturn(mockedQuery1);
//		when(mockedQuery1.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery1);
//
//		Query mockedQuery2 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitCompanyInfoEntity u where u.permitEntity.id=:p1")).thenReturn(mockedQuery2);
//		when(mockedQuery2.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery2);
//
//		Query mockedQuery3 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitHomeSiteInfoEntity u where u.permitEntity.id=:p1")).thenReturn(mockedQuery3);
//		when(mockedQuery3.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery3);
//
//		Query mockedQuery4 = mock(Query.class);
//		when(em.createQuery("SELECT u from PermitAdvEntity u where u.permitEntity.id=:p1")).thenReturn(mockedQuery4);
//		when(mockedQuery4.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery4);
//
//		Query mockedQuery5 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u where u.id=:p1")).thenReturn(mockedQuery5);
//		when(mockedQuery5.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery5);
//
//		Query mockedQuery6 = mock(Query.class);
//		when(em.createQuery("SELECT u from AuthentificationEntity u where u.id=:p1")).thenReturn(mockedQuery6);
//		when(mockedQuery6.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(mockedQuery6);
//
//		List<ArrayList<Object>> scenario = new ArrayList<ArrayList<Object>>();
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(0).add(null);
//		scenario.get(0).add(null);
//		// Result of query1
//		scenario.get(0).add(null);
//		// Result of query2
//		scenario.get(0).add(null);
//		// Result of query3
//		scenario.get(0).add(null);
//		// Result of query4
//		scenario.get(0).add(null);
//		// Result of query5
//		scenario.get(0).add(null);
//		// Result of query6
//		scenario.get(0).add(null);
//		//Expectedb Result
//		scenario.get(0).add(new HashMap<String,String>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(1).add("");
//		scenario.get(1).add("");
//		// Result of query1
//		scenario.get(1).add(null);
//		// Result of query2
//		scenario.get(1).add(null);
//		// Result of query3
//		scenario.get(1).add(null);
//		// Result of query4
//		scenario.get(1).add(null);
//		// Result of query5
//		scenario.get(1).add(null);
//		// Result of query6
//		scenario.get(1).add(null);
//		//Expectedb Result
//		scenario.get(1).add(new HashMap<String,String>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(2).add("125");
//		scenario.get(2).add("1589");
//		// Result of query1
//		scenario.get(2).add(null);
//		// Result of query2
//		scenario.get(2).add(null);
//		// Result of query3
//		scenario.get(2).add(null);
//		// Result of query4
//		scenario.get(2).add(null);
//		// Result of query5
//		scenario.get(2).add(null);
//		// Result of query6
//		scenario.get(2).add(null);
//		//Expectedb Result
//		scenario.get(2).add(new HashMap<String,String>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(3).add("125");
//		scenario.get(3).add("1589");
//		// Result of query1
//		PermitEntity pr = new PermitEntity();
//		AuthentificationEntity auth = new AuthentificationEntity();
//		pr.setAuthentificationEntity(auth);
//		scenario.get(3).add(pr);
//		// Result of query2
//		scenario.get(3).add(null);
//		// Result of query3
//		scenario.get(3).add(null);
//		// Result of query4
//		scenario.get(3).add(null);
//		// Result of query5
//		scenario.get(3).add(null);
//		// Result of query6
//		scenario.get(3).add(null);
//		//Expectedb Result
//		scenario.get(3).add(new HashMap<String,String>());
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(4).add("125");
//		scenario.get(4).add("1589");
//		// Result of query1
//		scenario.get(4).add(pr);
//		// Result of query2
//		PermitCompanyInfoEntity comp = new PermitCompanyInfoEntity();
//		comp.setSnemApplication("yes");
//		scenario.get(4).add(comp);
//		// Result of query3
//		scenario.get(4).add(null);
//		// Result of query4
//		scenario.get(4).add(null);
//		// Result of query5
//		scenario.get(4).add(null);
//		// Result of query6
//		scenario.get(4).add(null);
//		//Expectedb Result
//		HashMap<String,String> exp1 = new HashMap<String,String>();
//		exp1.put("agreement_type", "S");
//		scenario.get(4).add(exp1);
//
//		scenario.add(new ArrayList<Object>());
//		// List of the parameter
//		scenario.get(5).add("125");
//		scenario.get(5).add("1589");
//		// Result of query1
//		scenario.get(5).add(pr);
//		// Result of query2
//		scenario.get(5).add(comp);
//		// Result of query3
//		scenario.get(5).add(new PermitHomeSiteInfoEntity());
//		// Result of query4
//		scenario.get(5).add(new PermitAdvEntity());
//		// Result of query5
//		scenario.get(5).add(new AuthentificationEntity());
//		// Result of query6
//		scenario.get(5).add(new AuthentificationEntity());
//		//Expectedb Result
//		HashMap<String,String> exp2 = new HashMap<String,String>();
//		exp2.put("agreement_type", "S");
//		exp2.put("last_name","null null");
//		exp2.put("contractor_company_name", null);
//		exp2.put("badge_number", "");
//		exp2.put("service_agreement_id", "");
//		exp2.put("ma_contact_name", "null null");
//		exp2.put("sp_zip", null);
//		exp2.put("ma_contact_phone", "");
//		exp2.put("contractor_company_name", "");
//		exp2.put("contractor_last_name", null);
//		exp2.put("contractor_business_phone", "");
//		exp2.put("contractor_email", "");
//		exp2.put("sp_address", null);
//		exp2.put("sp_city", null);
//		exp2.put("home_phone", "");
//		exp2.put("email", null);
//		exp2.put("authorized_to_act", "On");
//		exp2.put("system_type", "S");
//		exp2.put("usage", "");
//		exp2.put("plan_increase", "");
//		exp2.put("ev_charging", "N");
//		Date d = new Date();
//		SimpleDateFormat FormattedDATE = new SimpleDateFormat("MM/dd/yyyy 'at' hh:mm");
//		FormattedDATE.setTimeZone(TimeZone.getTimeZone("Canada/Pacific"));
//		exp2.put("customer_name", "null null");
//		exp2.put("signature_date", FormattedDATE.format(d));
//		exp2.put("pv_cecac", "Missing Information");
//		exp2.put("size", "Missing Information");
//		scenario.get(5).add(exp2);
//
//
//		for (int i = 0; i < scenario.size(); i++) {
//			System.out.println("sendCorrectionBatteryRequest [ " + i + " ]");
//			when(mockedQuery1.getSingleResult()).thenReturn(scenario.get(i).get(2));
//			when(mockedQuery2.getSingleResult()).thenReturn(scenario.get(i).get(3));
//			when(mockedQuery3.getSingleResult()).thenReturn(scenario.get(i).get(4));
//			when(mockedQuery4.getSingleResult()).thenReturn(scenario.get(i).get(5));
//			when(mockedQuery5.getSingleResult()).thenReturn(scenario.get(i).get(6));
//			when(mockedQuery6.getSingleResult()).thenReturn(scenario.get(i).get(7));
//			interConnectionMaps
//					.getValues((String) scenario.get(i).get(0),(String) scenario.get(i).get(1));
//
//		}
//
//	}
//
//
//
//}
