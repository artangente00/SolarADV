package com.PlayGroundAdv.Solar.service.generate_planset.ssheets;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.MountingTypeEntity;
import com.PlayGroundAdv.Solar.entity.SsheetRackingMappingEntity;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class FilterRackingSheets {

	final CheckValueTypesService checkValue;
	
	public FilterRackingSheets(CheckValueTypesService checkValue) {
		super();
		this.checkValue = checkValue;
	}

	//A.B Filter By Roof Type
	public List<SsheetRackingMappingEntity> filterByRoofType(List<SsheetRackingMappingEntity> rackingSheets, String roofType){
		try {
			List<SsheetRackingMappingEntity> rackingSheetsFiltered = rackingSheets.stream()
				    .filter(p -> 
				    checkValue.Equals(p.getRoofType(), roofType) || checkValue.Equals(p.getRoofTypeOther(), roofType)).collect(Collectors.toList());
			rackingSheetsFiltered.addAll(rackingSheets.stream()
					    .filter(p -> isAnyValue(p.getRoofType()) || checkValue.Equals(p.getRoofTypeOther(), "any")).collect(Collectors.toList()));
			return rackingSheetsFiltered;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	//F.S Filter By State
		public List<SsheetRackingMappingEntity> filterByState(List<SsheetRackingMappingEntity> rackingSheets, String state){
			try {
				List<SsheetRackingMappingEntity> rackingSheetsFiltered = rackingSheets.stream()
					    .filter(p -> checkValue.Equals(p.getState(), state)).collect(Collectors.toList());
				rackingSheetsFiltered.addAll(rackingSheets.stream()
						    .filter(p -> isAnyValue(p.getState())).collect(Collectors.toList()));
				return rackingSheetsFiltered;
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<>();
			}
		}
	
	//A.B Filter By Roof Material Type
	public List<SsheetRackingMappingEntity> filterByRoofMaterialType(List<SsheetRackingMappingEntity> rackingSheets, String roofTMaterialype){
		try {
			List<SsheetRackingMappingEntity> rackingSheetsFiltered = rackingSheets.stream()
				    .filter(p -> p.getRoofMaterialTypes() != null && !p.getRoofMaterialTypes().isEmpty() &&
				    (new ArrayList<>(p.getRoofMaterialTypes().stream().map(o -> o.getRoofMaterialId().getTypeRoof()).collect(Collectors.toList()))).contains(roofTMaterialype)
				    ).collect(Collectors.toList());
			rackingSheetsFiltered.addAll(rackingSheets.stream()
					    .filter(p -> p.getRoofMaterialTypes() == null || p.getRoofMaterialTypes().isEmpty()).collect(Collectors.toList()));
			return rackingSheetsFiltered;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	//A.B Filter By Racking Manufacturer
	public List<SsheetRackingMappingEntity> filterByRackingMake(List<SsheetRackingMappingEntity> rackingSheets, String rackingManufacturer){
		try {
			List<SsheetRackingMappingEntity> rackingSheetsFiltered = rackingSheets.stream()
				    .filter(p -> checkValue.Equals(p.getRackingManufacturer(), rackingManufacturer)).collect(Collectors.toList());
			rackingSheetsFiltered.addAll(rackingSheets.stream()
					    .filter(p -> isAnyValue(p.getRackingManufacturer())).collect(Collectors.toList()));
			return rackingSheetsFiltered;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	//A.B Filter By Racking Model
	public List<SsheetRackingMappingEntity> filterByRackingModel(List<SsheetRackingMappingEntity> rackingSheets, String rackingModel){
		try {
			List<SsheetRackingMappingEntity> rackingSheetsFiltered = rackingSheets.stream()
				    .filter(p -> checkValue.Equals(p.getRackingModel(), rackingModel)).collect(Collectors.toList());
			rackingSheetsFiltered.addAll(rackingSheets.stream()
					    .filter(p ->isAnyValue(p.getRackingModel())).collect(Collectors.toList()));
			return rackingSheetsFiltered;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	//A.B Filter By Racking/Mounting Type
	public List<SsheetRackingMappingEntity> filterByRackingMountingType(List<SsheetRackingMappingEntity> rackingSheets, List<MountingTypeEntity> mountingTypes){
		try {
			List<SsheetRackingMappingEntity> rackingSheetsFiltered = rackingSheets.stream()
				    .filter(p -> mountingTypes != null && isMountingExist(mountingTypes, p.getMountingType())).collect(Collectors.toList());
			rackingSheetsFiltered.addAll(rackingSheets.stream()
					    .filter(p -> isAnyValue(p.getMountingType())).collect(Collectors.toList()));
			return rackingSheetsFiltered;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	//A.B Filter By Rail Connect Make
	public List<SsheetRackingMappingEntity> filterByRailConnectMake(List<SsheetRackingMappingEntity> rackingSheets, String roofManufacturer){
		try {
			
			List<SsheetRackingMappingEntity> rackingSheetsFiltered = rackingSheets.stream()
				    .filter(p -> checkValue.Equals(p.getRoofManufacturer(), roofManufacturer)).collect(Collectors.toList());
			rackingSheetsFiltered.addAll(rackingSheets.stream()
					    .filter(p -> isAnyValue(p.getRoofManufacturer())).collect(Collectors.toList()));
			return rackingSheetsFiltered;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	//A.B Filter By Rail Connect Model
	public List<SsheetRackingMappingEntity> filterByRailConnectModel(List<SsheetRackingMappingEntity> rackingSheets, String roofModel){
		try {
			List<SsheetRackingMappingEntity> rackingSheetsFiltered = rackingSheets.stream()
				    .filter(p -> checkValue.Equals(p.getRoofModel(), roofModel)).collect(Collectors.toList());
			rackingSheetsFiltered.addAll(rackingSheets.stream()
					    .filter(p -> isAnyValue(p.getRoofModel())).collect(Collectors.toList()));
			return rackingSheetsFiltered;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	//A.B Filter By Flashing
	public List<SsheetRackingMappingEntity> filterByFlashing(List<SsheetRackingMappingEntity> rackingSheets, String flashing){
		try {
			List<SsheetRackingMappingEntity> rackingSheetsFiltered = rackingSheets.stream()
				    .filter(p -> checkValue.Equals(p.getFlashingManufacturer(), flashing)).collect(Collectors.toList());
			rackingSheetsFiltered.addAll(rackingSheets.stream()
					    .filter(p -> isAnyValue(p.getFlashingManufacturer())).collect(Collectors.toList()));
			return rackingSheetsFiltered;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	//A.B CR-274 09-23-2021 Filter By Stanchion Type
	public List<SsheetRackingMappingEntity> filterByStanchionType(List<SsheetRackingMappingEntity> rackingSheets, String stanchionType){
		try {
			List<SsheetRackingMappingEntity> rackingSheetsFiltered = rackingSheets.stream()
					.filter(s -> checkValue.Equals(s.getStanchionType(), stanchionType)
				    ).collect(Collectors.toList());
			rackingSheetsFiltered.addAll(rackingSheets.stream()
				    .filter(p -> isAnyValue(p.getStanchionType())).collect(Collectors.toList()));
			return rackingSheetsFiltered;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	//A.B Filter By AHJ
	public List<SsheetRackingMappingEntity> filterByAhj(List<SsheetRackingMappingEntity> rackingSheets, String ahj){
		try {
			List<SsheetRackingMappingEntity> rackingSheetsFiltered = rackingSheets.stream()
				    .filter(p -> checkValue.Equals(p.getAhj(), ahj)).collect(Collectors.toList());
			rackingSheetsFiltered.addAll(rackingSheets.stream()
					    .filter(p -> isAnyValue(p.getAhj())).collect(Collectors.toList()));
			return rackingSheetsFiltered;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	//FS Filter By Flashing
		public List<SsheetRackingMappingEntity> filterByHasFlashing(List<SsheetRackingMappingEntity> rackingSheets, Boolean hasFlashing){
			try {
				List<SsheetRackingMappingEntity> rackingSheetsFiltered = rackingSheets.stream()
					    .filter(p -> checkValue.Equals(p.getFlashing() ,hasFlashing ) ).collect(Collectors.toList());
				if (rackingSheetsFiltered != null && rackingSheetsFiltered.isEmpty()) {
					rackingSheetsFiltered = rackingSheets.stream()
						    .filter(p -> p.getFlashing() == null ).collect(Collectors.toList());
				}
				return rackingSheetsFiltered;
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<>();
			}
		}
	//A.B Filter By Utility Company
	public List<SsheetRackingMappingEntity> filterByUtilityCompany(List<SsheetRackingMappingEntity> rackingSheets, String utilityCompany){
		try {
			List<SsheetRackingMappingEntity> rackingSheetsFiltered = rackingSheets.stream()
				    .filter(p -> checkValue.Equals(p.getUtilityCompany(), utilityCompany)).collect(Collectors.toList());
			rackingSheetsFiltered.addAll(rackingSheets.stream()
					    .filter(p -> isAnyValue(p.getUtilityCompany())).collect(Collectors.toList()));
			return rackingSheetsFiltered;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	private Boolean isAnyValue(String s) {
		return s == null || !checkValue.isStringNotEmpty(s) || checkValue.Equals(s, "any");
	}
	
	private Boolean isMountingExist(List<MountingTypeEntity> s, String v) {
		return s.stream()
			    .filter(p -> p.getMountingType().equals(v)).collect(Collectors.toList()).size() > 0;
	}
}
