package com.PlayGroundAdv.Solar.model.project;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReviewRequest {

	private Long idProject;
	private Long idUser;
	private String version;
}
