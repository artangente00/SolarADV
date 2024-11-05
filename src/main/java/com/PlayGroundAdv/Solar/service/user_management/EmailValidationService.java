package com.PlayGroundAdv.Solar.service.user_management;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class EmailValidationService {

	final CheckValueTypesService checkValue;

	public EmailValidationService(CheckValueTypesService checkValue) {
		super();
		this.checkValue = checkValue;
	}

	// Check if this is an existing Valid email @
	public Boolean validateEmail(String email) throws Exception {
		Boolean isvalid = true;
		String status = "";
		try {

			String key = "0f8547513d46412d8b200e53134c5d3c";
			String targetURL = "https://api.zerobounce.net/v2/validate?api_key=" + key + "&email=" + email
					+ "&ip_address=";

			HttpURLConnection connection = null;
			final String USER_AGENT = "Mozilla/5.0";

			try {
				URL url = new URL(targetURL);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.addRequestProperty("User-Agent", USER_AGENT);
				connection.setUseCaches(false);
				connection.setDoOutput(true);

				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// print result

				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(response.toString());
				status = (String) json.get("status");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (connection != null) {
					connection.disconnect();
				}
			}

			if (checkValue.NotEquals(status, "valid")) {
				isvalid = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isvalid;

	}
}

