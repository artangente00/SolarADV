package com.PlayGroundAdv.Solar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ProjectForLibrariesModel {

	private Long id;
	private String homeOwnLastName;
	private String homeOwnerName;
	private String projectName;
	private String status;
	private String firstName;
	private String lastName;
	
	public ProjectForLibrariesModel(String homeOwnLastName, String homeOwnerName, String projectName, String status,
			String firstName, String lastName) {
		super();
		this.homeOwnLastName = homeOwnLastName;
		this.homeOwnerName = homeOwnerName;
		this.projectName = projectName;
		this.status = status;
		this.firstName = firstName;
		this.lastName = lastName;
	}

}
