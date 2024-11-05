package com.PlayGroundAdv.Solar.model.libraries;

import java.util.Date;
import java.util.List;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Battery;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RoofMaterialType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanSetCustomizedRequest {

	private Integer size; 
	private Integer page;
	private Boolean isDeleted;
}
