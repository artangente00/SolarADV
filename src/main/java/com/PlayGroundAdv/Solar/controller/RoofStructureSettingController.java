package com.PlayGroundAdv.Solar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.users.RoofStructureSettingDto;
import com.PlayGroundAdv.Solar.service.user_management.RoofStructureSettingService;

@RestController
@RequestMapping("/roofStructureSetting")
public class RoofStructureSettingController {

	final RoofStructureSettingService roofStructureSetting;

	public RoofStructureSettingController(RoofStructureSettingService roofStructureSetting) {
		super();
		this.roofStructureSetting = roofStructureSetting;
	}

	@PostMapping("/getRoofStructure")
	public List<RoofStructureSettingDto> getRoofStructure(@RequestBody Long id) {
		return roofStructureSetting.getRoofStructure(id);
	}
	@PostMapping("/getRoofStructureByState")
	public RoofStructureSettingDto getRoofStructureByState(@RequestBody RoofStructureSettingDto param) {
		return roofStructureSetting.getRoofStructureByState(param);
	}
	@PostMapping("/addRoofStructure")
	public ResponseEntity<?> addRoofStructure(@RequestBody RoofStructureSettingDto dto) {
		return roofStructureSetting.addRoofStructure(dto);
	}

	@PostMapping("/editRoofStructure")
	public String editRoofStructure(@RequestBody RoofStructureSettingDto dto) {
		return roofStructureSetting.editRoofStructure(dto);
	}

	@PostMapping("/removeRoofStructure")
	public String removeRoofStructure(@RequestBody Long id) {
		return roofStructureSetting.removeRoofStructure(id);
	}
}
