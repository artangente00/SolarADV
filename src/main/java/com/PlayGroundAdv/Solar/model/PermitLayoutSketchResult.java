package com.PlayGroundAdv.Solar.model;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PermitLayoutSketchResult {

	private String conduitRun;
	private Boolean showConduitRoofAsHeight;
	private String sketchNote;

	private String uploadCommentsLayout;
	private String uploadCommentsAddInfo;
	private Boolean ignoreVents;
	private Boolean firesetbacks;
	private String firesetbacksNote;
	private Boolean fireVariance;
	private String fireVarianceNote;
	private Boolean modulesEncroaching;
	private List<String> noteSketchFilesOne;
	private List<String> noteSketchFilesTwo;

	public PermitLayoutSketchResult(String conduitRun, Boolean showConduitRoofAsHeight, String sketchNote,
			String uploadCommentsLayout, String uploadCommentsAddInfo, Boolean ignoreVents, Boolean firesetbacks,
			String firesetbacksNote, Boolean fireVariance, String fireVarianceNote, Boolean modulesEncroaching,
			List<String> noteSketchFilesOne, List<String> noteSketchFilesTwo) {
		super();
		this.conduitRun = conduitRun;
		this.showConduitRoofAsHeight = showConduitRoofAsHeight;
		this.sketchNote = sketchNote;
		this.uploadCommentsLayout = uploadCommentsLayout;
		this.uploadCommentsAddInfo = uploadCommentsAddInfo;
		this.ignoreVents = ignoreVents;
		this.firesetbacks = firesetbacks;
		this.firesetbacksNote = firesetbacksNote;
		this.fireVariance = fireVariance;
		this.fireVarianceNote = fireVarianceNote;
		this.modulesEncroaching = modulesEncroaching;
		this.noteSketchFilesOne = noteSketchFilesOne;
		this.noteSketchFilesTwo = noteSketchFilesTwo;
	}



	public PermitLayoutSketchResult(String conduitRun, Boolean showConduitRoofAsHeight, String sketchNote,
			String uploadCommentsLayout, String uploadCommentsAddInfo, Boolean ignoreVents, Boolean firesetbacks,
			String firesetbacksNote, Boolean fireVariance, String fireVarianceNote, Boolean modulesEncroaching) {
		super();
		this.conduitRun = conduitRun;
		this.showConduitRoofAsHeight = showConduitRoofAsHeight;
		this.sketchNote = sketchNote;
		this.uploadCommentsLayout = uploadCommentsLayout;
		this.uploadCommentsAddInfo = uploadCommentsAddInfo;
		this.ignoreVents = ignoreVents;
		this.firesetbacks = firesetbacks;
		this.firesetbacksNote = firesetbacksNote;
		this.fireVariance = fireVariance;
		this.fireVarianceNote = fireVarianceNote;
		this.modulesEncroaching = modulesEncroaching;
	}

	public PermitLayoutSketchResult(String conduitRun, Boolean showConduitRoofAsHeight,
			String sketchNote) {
		super();
		this.conduitRun = conduitRun;
		this.showConduitRoofAsHeight = showConduitRoofAsHeight;
		this.sketchNote = sketchNote;
	}

	
}
