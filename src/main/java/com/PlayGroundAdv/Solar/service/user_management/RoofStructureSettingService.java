package com.PlayGroundAdv.Solar.service.user_management;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.users.RoofStructureSetting;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.users.RoofStructureSettingDto;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.RoofStructureSettingRepository;

@Service
public class RoofStructureSettingService {

	final RoofStructureSettingRepository roofStructureSettingRepo;
	final AuthenticationRepository userRepo;

	public RoofStructureSettingService(RoofStructureSettingRepository roofStructureSettingRepo,
			AuthenticationRepository userRepo) {
		super();
		this.roofStructureSettingRepo = roofStructureSettingRepo;
		this.userRepo = userRepo;
	}

	public List<RoofStructureSettingDto> getRoofStructure(Long id) {
		try {
			return roofStructureSettingRepo.findByUser(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public RoofStructureSettingDto getRoofStructureByState(RoofStructureSettingDto param) {
		try {
			return roofStructureSettingRepo.findByUserAndState(param.getUserId(), param.getState());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResponseEntity<?> addRoofStructure(RoofStructureSettingDto dto) {
		try {
			Boolean exist = roofStructureSettingRepo.existsByStateAndUserIdId(dto.getState(), dto.getUserId());
			if (Boolean.TRUE.equals(exist)) {
				return new ResponseEntity<>(
						"Roof structure setting for " + dto.getState()
								+ " already exist, please refresh to make sure you have the last updates!",
						HttpStatus.OK);
			}
			AuthentificationEntity user = userRepo.findById(dto.getUserId()).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" + dto.getUserId()));
			RoofStructureSetting setting = new RoofStructureSetting(dto.getState(), dto.getRafterTrussDesign(),
					dto.getCrossSectionSize(), dto.getRafterTrussDesignOther(), dto.getCrossSectionSizeOther(), user);
			roofStructureSettingRepo.save(setting);
			dto.setId(setting.getId());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Technical Problem!", HttpStatus.OK);
		}
	}

	public String editRoofStructure(RoofStructureSettingDto dto) {
		try {
			Boolean exist = roofStructureSettingRepo.existsByStateAndUserIdIdAndIdNotIn(dto.getState(), dto.getUserId(),
					new ArrayList<Long>(Arrays.asList(dto.getId())));
			if (Boolean.TRUE.equals(exist)) {
				return "Roof structure setting for " + dto.getState()
						+ " already exist, please refresh to make sure you have the last updates!";
			}
			RoofStructureSetting setting = roofStructureSettingRepo.findById(dto.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + dto.getId()));
			setting.setState(dto.getState());
			setting.setRafterTrussDesign(dto.getRafterTrussDesign());
			setting.setCrossSectionSize(dto.getCrossSectionSize());
			setting.setRafterTrussDesignOther(dto.getRafterTrussDesignOther());
			setting.setCrossSectionSizeOther(dto.getCrossSectionSizeOther());
			roofStructureSettingRepo.save(setting);
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Technical Problem!";
		}
	}

	public String removeRoofStructure(Long id) {
		try {
			RoofStructureSetting setting = roofStructureSettingRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + id));
			roofStructureSettingRepo.delete(setting);
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Technical Problem!";
		}
	}
}
