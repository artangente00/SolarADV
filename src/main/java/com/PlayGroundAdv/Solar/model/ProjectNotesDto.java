package com.PlayGroundAdv.Solar.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ProjectNotesDto {

	private Long id;
	private Long project;
	private String floridaRafterDesign;
	private String floridaRafterDesignFileName;
	
	public ProjectNotesDto(Long project, String floridaRafterDesign, String floridaRafterDesignFileName) {
		super();
		this.project = project;
		this.floridaRafterDesign = floridaRafterDesign;
		this.floridaRafterDesignFileName = floridaRafterDesignFileName;
	}
	
	public ProjectNotesDto(String floridaRafterDesign, String floridaRafterDesignFileName, Long id) {
		super();
		this.id = id;
		this.floridaRafterDesign = floridaRafterDesign;
		this.floridaRafterDesignFileName = floridaRafterDesignFileName;
	}
	

}
