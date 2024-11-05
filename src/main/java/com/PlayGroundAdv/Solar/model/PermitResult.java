package com.PlayGroundAdv.Solar.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PermitResult {

	private Long id;
	private String homeName;
	private String avancement;
	private String status;
	private Date updatedDate;
	private Date creationPermitDate;
	private Boolean submitted;

}
