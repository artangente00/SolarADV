package com.PlayGroundAdv.Solar.service.monday_api;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;

@Service
public class Authenticate {

	final static String AUTHORIZATION = "eyJhbGciOiJIUzI1NiJ9.eyJ0aWQiOjEyNjMxMjMwNSwidWlkIjo5MjEyNzI1LCJpYWQiOiIyMDIxLTA5LTI3VDIzOjMwOjQ2LjM5NloiLCJwZXIiOiJtZTp3cml0ZSIsImFjdGlkIjo0MTk2NTA0LCJyZ24iOiJ1c2UxIn0.lM6PMmf4lncsiT5pnEZ-7ZKZPzKpakyGQoCRWA3mKM8";
	
	public HttpPost createHeader(StringEntity params) {
		
		HttpPost httppost = new HttpPost("https://api.monday.com/v2");
		httppost.addHeader("content-type", "application/json");
		httppost.addHeader("Authorization", AUTHORIZATION);		
		httppost.setEntity(params);

		return httppost;
	}
}
