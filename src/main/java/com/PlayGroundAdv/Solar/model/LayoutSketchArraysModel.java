package com.PlayGroundAdv.Solar.model;

import com.PlayGroundAdv.Solar.entity.PermitEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LayoutSketchArraysModel {

	private String[] azimuth;
	private String[] roofPitch;
	private boolean[] moduleTils;
	private String[] modelvalue;
	private String[] eaveOverHang;
	private String[] eaveOverHangOther;
	private Integer[] moduleQty;
	private Integer[] squareFootage;
	private Long permitId;
	private Integer arraysLength;

}
