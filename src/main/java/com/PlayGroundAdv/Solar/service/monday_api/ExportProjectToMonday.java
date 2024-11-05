package com.PlayGroundAdv.Solar.service.monday_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.model.project.MondayAPIModel;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.service.mailing.MailingService;
import com.PlayGroundAdv.Solar.service.project.GetProjectDetailsUtils;

@Service
public class ExportProjectToMonday {

	final Authenticate auth;
	final CreateAPIQuery apiQuery;
	final PermitRepository permitRepo;
	final MailingService mailingService;
	final GetProjectDetailsUtils projectUtils;
	public ExportProjectToMonday(Authenticate auth, CreateAPIQuery apiQuery,
			PermitRepository permitRepo, MailingService mailingService,
			GetProjectDetailsUtils projectUtils) {
		super();
		this.auth = auth;
		this.apiQuery = apiQuery;
		this.permitRepo = permitRepo;
		this.mailingService = mailingService;
		this.projectUtils = projectUtils;
	}

	public void sendProjectToMonday(PermitEntity permit) throws IOException {

		try {
			MondayAPIModel projectDto  = permitRepo.findMondayModel(permit.getId());
			String query = apiQuery.createQuery(permit, projectDto);
			JSONObject json = new JSONObject();
			json.put("query", query);
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

						//Set Monday ID to the project
						try {
							permit.setMondayId(Integer.getInteger(itemId));
							permitRepo.save(permit);
						}catch (Exception e) {
							e.printStackTrace();
						}
						
						//Update Submit Date & Engineer/Electrical Structure.
						Thread.sleep(30000);
						String queryUpdate = apiQuery.updateQuery(projectDto, permit.getDateOfSubmitPermit(), itemId);
						updateProjectInMonday(queryUpdate);
					}else {
						//send error message
						String projectName = projectUtils.getProjectName(permit);
						mailingService.mondayExportError(projectName, resString, "project");
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	public void updateProjectInMonday(String query) throws IOException {

		try {
			
			JSONObject json = new JSONObject();
			json.put("query", query);
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
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
