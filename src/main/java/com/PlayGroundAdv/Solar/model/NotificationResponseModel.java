package com.PlayGroundAdv.Solar.model;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class NotificationResponseModel {
	
	private List<NotificationRequest> projectsRequest;
	private List<NotificationRequest> librariesRequest;
	private List<NotificationRequest> accountsRequest;

	private Long projectsRequestSize;
	private Long librariesRequestSize;
	private Long accountsRequestSize;

}
