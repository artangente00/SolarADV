package com.PlayGroundAdv.Solar.service.project;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.RFIConfirmOfSubmitEntity;
import com.PlayGroundAdv.Solar.repositories.RFIConfirmOfSubmitRepository;

@Service
@Transactional
public class RFIConfirmOfSubmitService {

	final RFIConfirmOfSubmitRepository rfiConfirmOfSubmitRepo;
	public RFIConfirmOfSubmitService(RFIConfirmOfSubmitRepository rfiConfirmOfSubmitRepo) {
		super();
		this.rfiConfirmOfSubmitRepo = rfiConfirmOfSubmitRepo;
	}


	public RFIConfirmOfSubmitEntity addOrUpdateSubmitRFI (Long idPermit) {
		if (rfiConfirmOfSubmitRepo.existsById(idPermit)) {
			RFIConfirmOfSubmitEntity findQuery=rfiConfirmOfSubmitRepo.findById(idPermit).get();
			findQuery.setSubmittedAddedToRFI(true);
			findQuery.setSubmitAddedFromContractorRFI(false);
			return findQuery;
		}else {
			RFIConfirmOfSubmitEntity rFIConfirmOfSubmitEntity= new RFIConfirmOfSubmitEntity(idPermit, true, false);
			rfiConfirmOfSubmitRepo.save(rFIConfirmOfSubmitEntity);
			return rFIConfirmOfSubmitEntity;
		}
	}
	
	
	public RFIConfirmOfSubmitEntity findSubmitContractorRFI (Long idPermit) {
		if (rfiConfirmOfSubmitRepo.existsById(idPermit)) {
			RFIConfirmOfSubmitEntity findQuery=rfiConfirmOfSubmitRepo.findById(idPermit).get();
			findQuery.setSubmitAddedFromContractorRFI(true);
			return findQuery;
		}else return null;
	}
	
	public RFIConfirmOfSubmitEntity submitContractorRFI (Long idPermit) {
		if (rfiConfirmOfSubmitRepo.existsById(idPermit)) {
			RFIConfirmOfSubmitEntity findQuery=rfiConfirmOfSubmitRepo.findById(idPermit).get();
			findQuery.setSubmitAddedFromContractorRFI(true);
			return findQuery;
		}else return null;
	}
	
	

}
