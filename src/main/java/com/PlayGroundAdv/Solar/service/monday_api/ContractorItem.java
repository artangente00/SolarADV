package com.PlayGroundAdv.Solar.service.monday_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.ContractorInformationRepository;
import com.PlayGroundAdv.Solar.service.mailing.MailingService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class ContractorItem {
	
	final Authenticate auth;
	final CheckValueTypesService checkValue;
	final AuthenticationRepository userRepo;
	final ContractorInformationRepository userInfoRepo;
	final MailingService mailingService;
	
	public ContractorItem(Authenticate auth, CheckValueTypesService checkValue, 
			AuthenticationRepository userRepo, ContractorInformationRepository userInfoRepo, MailingService mailingService) {
		super();
		this.auth = auth;
		this.checkValue = checkValue;
		this.userRepo = userRepo;
		this.userInfoRepo = userInfoRepo;
		this.mailingService = mailingService;
	}

	final static String NOT_FOUND = "Contractor Not Found";

	public String getContractorId(AuthentificationEntity user) throws ClientProtocolException, IOException {

		try {
			if (checkValue.isStringNotEmpty(user.getCompany())) {
				String query = "query {   items_by_column_values(board_id: 354269996, column_id: \"name\", column_value: \""
						+ user.getCompany() + "\") { id name   }}";
				JSONObject json = new JSONObject();
				json.put("query", query);
				StringEntity params = new StringEntity(json.toString());

				// Execute and get the response.
				HttpClient httpclient = HttpClients.createDefault();
				HttpPost httppost = auth.createHeader(params);
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == 200 && entity != null) {
					try (InputStream instream = entity.getContent()) {
						BufferedReader reader = new BufferedReader(new InputStreamReader(instream, "iso-8859-1"), 8);
						StringBuilder sb = new StringBuilder();
						String line = null;
						while ((line = reader.readLine()) != null) // Read line by line
							sb.append(line + "\n");
						instream.close();
						String resString = sb.toString();
						if (resString.contains("\"id\":\"") && resString.contains("\",\"name\""))
							return resString.split("\"id\":\"")[1].split("\",\"name\"")[0];
						else {
							return createContractor(user);
						}
					}
				}return NOT_FOUND;
			}else {
				return NOT_FOUND;
			}
		} catch (Exception e) {
			return NOT_FOUND;
		}
		
	}
	
	public String createContractor(AuthentificationEntity user) throws IOException {

		try {
			StringBuilder query = new StringBuilder("mutation { " + "create_item (board_id: 354269996, item_name: \""+user.getCompany()+"\", column_values:\"{");
			query.append(" \\\"text13\\\": \\\""+user.getFirstName()+" "+user.getLastName()+"\\\" ,");
			String phone = userInfoRepo.findPhoneNumber(user.getId());
			if(phone != null && checkValue.isStringNotEmpty(phone.replaceAll("\\D+",""))) {
				query.append(" \\\"phone\\\": {\\\"phone\\\" : \\\""+phone.replaceAll("\\D+","")+"\\\", \\\"countryShortName\\\" : \\\"US\\\"} ,");
			}
			query.append(" \\\"text2\\\": \\\""+user.getEmail()+"\\\" }\") { id }}");
			JSONObject json = new JSONObject();
			json.put("query", query.toString());
			StringEntity params = new StringEntity(json.toString());

			// Execute and get the response.
			HttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = auth.createHeader(params);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				try (InputStream instream = entity.getContent()) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(instream, "iso-8859-1"), 8);
					StringBuilder sb = new StringBuilder();
					String line = null;
					while ((line = reader.readLine()) != null) // Read line by line
						sb.append(line + "\n");

					String resString = sb.toString();
					instream.close();
					if(resString.contains("create_item")) {
						JSONParser parser = new JSONParser();  
						JSONObject result = (JSONObject) parser.parse(resString);    
						result = (JSONObject) parser.parse(result.get("data").toString());  
						result = (JSONObject) parser.parse(result.get("create_item").toString());  
						String itemId = result.get("id").toString();

						//Set Monday ID to the project.
						try {
							user.setMondayId(Integer.getInteger(itemId));
							user.setAddedToMonday(new Date());
							userRepo.save(user);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						return itemId;
					}else {
						//send error message
						mailingService.mondayExportError(user.getCompany(), resString, "company");
						return NOT_FOUND;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					return NOT_FOUND;
				}
			}else {
				return NOT_FOUND;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return NOT_FOUND;
		}
		

	}
	public void getContractorIDs() {
		List<String> list = Arrays.asList("Consolidated Services"
				,"Sust. Living Holdings Inc"
				,"Clear Sky Solar, Inc."
				,"TestKelso Inc"
				,"Dynamic Power Systems"
				,"Ohm Solar Solutions"
				,"Anatoli-Electric"
				,"OUR WORLD ENERGY LLC"
				,"Cornerstone Construction"
				,"Mountain Aire Heating And Air Conditioning Inc"
				,"Sourdough Electric"
				,"Greenday Electric & Solar, Inc"
				,"Channel Islands Electric"
				,"NEXUS ENERGY SYSTEMS"
				,"U.S. Energy Solutions"
				,"Redline Electric & Solar "
				,"Carolina Solar And Security"
				,"Light Harvest Solar"
				,"United Solar"
				,"US Energy Savers"
				,"Daybreak Install LLC"
				,"Smart Builders Group"
				,"Advanced Solar Solutions"
				,"Pell Solar"
				,"Array Of Solar"
				,"Phoenix Solar"
				,"Solar Power Of Oklahoma"
				,"NRG Clean Power"
				,"Ground Up Builders"
				,"Heartland Solar"
				,"Fusion Energy Services LLC."
				,"mit electrical"
				,"True North Builders LLC"
				,"Portal Testing Solution"
				,"Cabrera Duston Inc DBA Powercity"
				,"Black Diamond Electric LLC."
				,"PV Solutions, LLC"
				,"ZOI Solar"
				,"IES"
				,"JC Electric"
				,"Fuzion Energy"
				,"CITADEL ROOFING & SOLAR"
				,"Skyline Solar LLC."
				,"Citadel roofing and Solar"
				,"American Sheet Metal"
				,"Barrett Solar LLC"
				,"SunSolarUS"
				,"Abundant Energy Inc"
				,"Black Diamond Electric"
				,"Global Solar Supply"
				,"New Day Solar"
				,"Texas Direct Solar"
				,"US Power Savers"
				,"Ecogen Services, LLC"
				,"Current Solar LLC."
				,"Soleeva Energy Inc."
				,"Electrical Power Source Inc."
				,"Owner Builder - Van Aken"
				,"4th Day Energy"
				,"Clean Earth Builders"
				,"PBCD Inc."
				,"Alpha Omega Renewable Power"
				,"SunPro Solar"
				,"Premier Solar Energy"
				,"Infinity Energy"
				,"Edmonds Energy Solutions"
				,"Lonestar Solar Power"
				,"Highlite Electric"
				,"Sparrow Electric"
				,"Green Ag Solutions, Inc."
				,"Northern California Solar LLC"
				,"Accept Solar, LLC"
				,"USA Solar Power"
				,"Sunquote"
				,"Southern Solar LLC"
				,"Dutchmen Construction Inc."
				,"Nova West Solar"
				,"Ishak Mohammad (Drafting)"
				,"California Solar Const"
				,"Prime Energy"
				,"Greco Electric"
				,"Natural Energy"
				,"My Generation Energy"
				,"Texas Elite Roofing And Solar"
				,"Fastrac Energy Services"
				,"Omega Electric Inc"
				,"Houston Solar Energy"
				,"A1 Green Power Construction"
				," Advanced Solar Solutions"
				,"Offline Solar Electric"
				,"Your Power, Inc."
				,"Rise Energy"
				,"CAM Solar"
				,"All Phase Systems Integration"
				,"Allegiance Solar"
				,"NorthStar Solar"
				,"Boldt Contracting Inc dba GB Electric Inc."
				,"Sundance Power Systems"
				,"Economic Energy Solutions"
				,"West Coast Green Electric"
				,"Teal City Roofing"
				,"Green Tech Solar"
				,"Northern California Roofing Co."
				,"Integrated Solar"
				,"Nuance Energy Group Inc."
				,"LegaSea Energy"
				,"Stars Energy Construction"
				,"Central Valley Energy Solution"
				,"Icon Power"
				,"Southern Solar"
				,"Clark Electrical Contractors"
				,"R&R Construction"
				,"SolArmy LLC"
				,"Integrated Solar Solutions"
				,"KAT Electric Services Inc."
				,"Valley Solar"
				,"Axis Solar"
				,"PNW Solar"
				,"Energize With Sunrise"
				,"Net 0 Energy"
				,"Kayo Energy LLC"
				,"RAVEN CUSTOM HOMES INC"
				,"Highlands Energy"
				,"Solar Luxury LLC."
				,"Fusion Electric Inc."
				,"ADV Solar"
				,"Alex Frankel"
				,"Interconnection Systems Incorporated"
				,"SCREC");
	 }
}
