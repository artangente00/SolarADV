package com.PlayGroundAdv.Solar.model;

import java.util.List;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PermitEnergyBatterySystemDto {
	
	private Long id;
	private Long idAts;
	private Long idSecondAts;
	private Long idGenerator;
	private Long idDcDisconnect;
	private Long idAcDisconnect;
	private Long idSecondAcDisconnect;
	private String typeGridTied;
	private List<ProjectBatteryDto> batteries;
	private Boolean atsIncluded;
	private Boolean dcDisconnectIncluded;
	private Boolean acDisconnectIncluded;
	private Boolean generatorIncluded;
	private Boolean rsdConnected;
	private String generatorStatus;
	private String fuelType;
	private String fuelDistributionPipeType;
	private String fuelDistributionPipeTypeOther;
	private String pipeSize;
	private String pipeSizeOther;
	private List<String> essSpecificationDetails;
	private String essSpecificationComment;
	private Integer qtyAts;
	private Integer qtySecondAts;
	private Integer qtyAcd;
	private Integer qtySecondAcd;

}
