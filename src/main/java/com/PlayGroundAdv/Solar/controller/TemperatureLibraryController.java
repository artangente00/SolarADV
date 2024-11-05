package com.PlayGroundAdv.Solar.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.entity.TemperatureLibraryEntity;
import com.PlayGroundAdv.Solar.model.AllPostalCodeModel;
import com.PlayGroundAdv.Solar.model.TemperatureListModel;
import com.PlayGroundAdv.Solar.model.TemperatureModel;
import com.PlayGroundAdv.Solar.service.libraries.TemperatureLibraryService;

@RestController
@RequestMapping("/temperatureLibrary")
public class TemperatureLibraryController {

	final TemperatureLibraryService tempLibraryService;

	public TemperatureLibraryController(TemperatureLibraryService tempLibraryService) {
		super();
		this.tempLibraryService = tempLibraryService;
	}

	// A.B Add Temperature
	@PostMapping("/addTemperature")
	public TemperatureLibraryEntity addTemperature(@RequestBody TemperatureModel newTemp) {
		try {
			return tempLibraryService.addTemperature(newTemp.getTemperature(), newTemp.getUserID(),
					newTemp.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// A.B Upload Temperature List
	@PostMapping("/addTemperatureList")
	public String addTemperatureList(@RequestBody TemperatureListModel newTemp) {
		try {
			for (TemperatureLibraryEntity temp : newTemp.getTemperatureList()) {
				tempLibraryService.addTemperature(temp, newTemp.getUserID(), newTemp.getUserName());
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	// A.B edit Temperature
	@PostMapping("/editTemperature")
	public String editTemperature(@RequestBody TemperatureModel editTemp) {
		try {
			return tempLibraryService.editTemperature(editTemp.getTemperature(), editTemp.getUserID(),
					editTemp.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	// A.B remove Temperature
	@PostMapping("/removeTemperature")
	public String removeTemperature(@RequestBody TemperatureModel editTemp) {
		try {
			return tempLibraryService.removeTemperature(editTemp.getTemperature(), editTemp.getUserID(),
					editTemp.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	// A.B Get All Temperature By Potal code
	@PostMapping("/getTemperature")
	public Page<TemperatureLibraryEntity> getTemperature(@RequestBody String[] params) {
		try {
			if (params[0] != null && params[0] != "") {
				return tempLibraryService.searchTemperature(params[0], Integer.parseInt(params[1]),
						Integer.parseInt(params[2]));
			} else
				return tempLibraryService.getAllTemperature(Integer.parseInt(params[1]), Integer.parseInt(params[2]));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// A.B Add Temperature
	@GetMapping("/getAllTemperature")
	public List<TemperatureLibraryEntity> getAllTemperature() {
		try {
			return tempLibraryService.getAllTemperature();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// A.B Add Temperature
	@GetMapping("/getAllPostalCodes")
	public List<AllPostalCodeModel> getAllPostalCodes() {
		try {
			return tempLibraryService.getAllPostalCodes();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
