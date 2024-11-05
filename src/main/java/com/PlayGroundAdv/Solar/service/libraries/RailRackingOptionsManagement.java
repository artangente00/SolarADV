package com.PlayGroundAdv.Solar.service.libraries;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RailRackingOptionsEntity;
import com.PlayGroundAdv.Solar.model.RailRackingOptionsModel;
import com.PlayGroundAdv.Solar.repositories.RailRackingOptionsRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingRepository;

@Service
public class RailRackingOptionsManagement {
	
	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	final RailRackingOptionsRepository rackingOptionsRepo;
	final RailRackingRepository railRackingRepo;
	
	public RailRackingOptionsManagement(RailRackingOptionsRepository rackingOptionsRepo, RailRackingRepository railRackingRepo) {
		super();
		this.rackingOptionsRepo = rackingOptionsRepo;
		this.railRackingRepo = railRackingRepo;
	}
	
	public List<RailRackingOptionsEntity> getRailRackingOption() {
		try {
			return rackingOptionsRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public RailRackingOptionsModel addRailRackingOption(RailRackingOptionsModel op) {
		try {
			RailRackingOptionsEntity option = new RailRackingOptionsEntity(op.getValue().trim(), op.getTypeOption());
			rackingOptionsRepo.save(option);
			op.setId(option.getId());
			op.setValue(op.getValue().trim());
			return op;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String editRailRackingOption(RailRackingOptionsModel op) {
		try {
			RailRackingOptionsEntity option = rackingOptionsRepo.findById(op.getId()).orElse(null);
			if (option != null) {
				option.setValue(op.getValue().trim());
				rackingOptionsRepo.save(option);
				return SUCCESS;
			}
			return FAIL;
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}		
	}
	public String removePvRailTypeOptions(Long opId) {
		try {
			RailRackingOptionsEntity option = rackingOptionsRepo.findById(opId).orElse(null);
			if (option != null) {
				if (option.getTypeOption().equals("pvRailType")) {
					List<RailRacking> railRackings = railRackingRepo.findByPvRailTypeId(opId);
					railRackings.forEach(u -> u.setPvRailType(null));
				} else if (option.getTypeOption().equals("pvRailSpliceType")) {
					List<RailRacking> railRackings = railRackingRepo.findByPvRailSpliceTypeId(opId);
					railRackings.forEach(u -> u.setPvRailSpliceType(null));
				} else if (option.getTypeOption().equals("midClamp")) {
					List<RailRacking> railRackings = railRackingRepo.findByMidClampId(opId);
					railRackings.forEach(u -> u.setMidClamp(null));
				} else{
					List<RailRacking> railRackings = railRackingRepo.findByEndClampId(opId);
					railRackings.forEach(u -> u.setEndClamp(null));
				}
				rackingOptionsRepo.delete(option);
				return SUCCESS;
			}else {
				return FAIL;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
	}

}
