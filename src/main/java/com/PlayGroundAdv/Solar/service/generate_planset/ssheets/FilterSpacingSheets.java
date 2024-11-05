package com.PlayGroundAdv.Solar.service.generate_planset.ssheets;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.SsheetSpacingMappingEntity;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class FilterSpacingSheets {
	
	final CheckValueTypesService checkValue;
	
	public FilterSpacingSheets(CheckValueTypesService checkValue) {
		super();
		this.checkValue = checkValue;
	}
	
	//A.B Filter By Roof Type
	public List<SsheetSpacingMappingEntity> filterByRoofType(List<SsheetSpacingMappingEntity> spacingSheets, String roofType){
		try {
			List<SsheetSpacingMappingEntity> spacingSheetsFiltered = spacingSheets.stream()
				    .filter(p -> checkValue.Equals(p.getRoofType(), roofType) || checkValue.Equals(p.getRoofTypeOther(), roofType)).collect(Collectors.toList());
			spacingSheetsFiltered.addAll(spacingSheets.stream()
					    .filter(p -> isAnyValue(p.getRoofType()) || checkValue.Equals(p.getRoofTypeOther(), "any")).collect(Collectors.toList()));
			return spacingSheetsFiltered;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	//A.B Filter By Rafter or Truss Spacing
	public List<SsheetSpacingMappingEntity> filterByRafterTrussSpacing(List<SsheetSpacingMappingEntity> spacingSheets, String rafterTrussSpacing){
		try {
			List<SsheetSpacingMappingEntity> spacingSheetsFiltered = spacingSheets.stream()
				    .filter(p -> checkValue.Equals(p.getRafterTrussSpacing(), rafterTrussSpacing+"\"")).collect(Collectors.toList());
			spacingSheetsFiltered.addAll(spacingSheets.stream()
					    .filter(p -> isAnyValue(p.getRafterTrussSpacing())).collect(Collectors.toList()));
			return spacingSheetsFiltered;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	//A.B Filter By Roof to Rail Anchor/Stanchion Spacing
	public List<SsheetSpacingMappingEntity> filterByStanchionMaxSpacing(List<SsheetSpacingMappingEntity> spacingSheets, String stanchionMaxSpacing){
		try {
			List<SsheetSpacingMappingEntity> spacingSheetsFiltered = spacingSheets.stream()
				    .filter(p -> checkValue.Equals(p.getStanchionMaxSpacing(), stanchionMaxSpacing+"\"")).collect(Collectors.toList());
			spacingSheetsFiltered.addAll(spacingSheets.stream()
					    .filter(p -> isAnyValue(p.getStanchionMaxSpacing())).collect(Collectors.toList()));
			return spacingSheetsFiltered;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	//S.B Filter By Stanchion Type CR-PM-3411
	public List<SsheetSpacingMappingEntity> filterByStanchionType(List<SsheetSpacingMappingEntity> spacingSheets, String stanchionType){
		try {
			List<SsheetSpacingMappingEntity> spacingSheetsFiltered = spacingSheets.stream()
					.filter(s -> 
						checkValue.Equals(s.getStanchionType(), stanchionType)
				    ).collect(Collectors.toList());
			return spacingSheetsFiltered;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	//FS Filter By Flashing Type CR-PM-3786
		public List<SsheetSpacingMappingEntity> filterByFlashing(List<SsheetSpacingMappingEntity> spacingSheets, Boolean hasFlashing){
			try {
				List<SsheetSpacingMappingEntity> spacingSheetsFiltered = spacingSheets.stream()
						.filter(s -> checkValue.Equals(s.getFlashing(), hasFlashing)
					    ).collect(Collectors.toList());
				if (spacingSheetsFiltered != null && spacingSheetsFiltered.isEmpty()) {
					spacingSheetsFiltered = spacingSheets.stream()
						    .filter(p -> p.getFlashing() == null ).collect(Collectors.toList());
				}
				return spacingSheetsFiltered;
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<>();
			}
		}
	private Boolean isAnyValue(String s) {
		return s == null || !checkValue.isStringNotEmpty(s) || checkValue.Equals(s, "any");
	}
}
