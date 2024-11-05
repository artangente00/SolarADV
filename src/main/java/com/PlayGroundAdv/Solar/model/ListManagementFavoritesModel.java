package com.PlayGroundAdv.Solar.model;

import java.util.List;

import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.Flashing;
import com.PlayGroundAdv.Solar.entity.LeasePPAMeter;
import com.PlayGroundAdv.Solar.entity.ProposedSubPanel;
import com.PlayGroundAdv.Solar.entity.TiltLegs;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.FavoriteListDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ListManagementFavoritesModel {

	private List<RailRackingModel> railRacking;
	private List<DCOptimizerEntity> dCOptimizerEntity;
	private List<FavoriteListDto> roofAttachment;
	private List<FavoriteListDto> dcDisconnect;
	private List<FavoriteListDto> dcCombiner;
	private List<FavoriteListDto> dcCombinerDisconnect;
	private List<ACDisconnect> aCDisconnect;
	private List<FavoriteListDto> inverter;
	private List<Cmodulev2> module;
	private List<ACCombinerSLC> aCCombinerSLC;
	private List<Flashing> flashing;
	private List<LeasePPAMeter> leasePPAMeter;
	private List<ComponentModel> battery;
	private List<TiltLegs> tiltLegs;
	private List<ProposedSubPanel> proposedSubPanel;
	private List<FavoriteListDto> ats;
	private List<FavoriteListDto> generator;

}
