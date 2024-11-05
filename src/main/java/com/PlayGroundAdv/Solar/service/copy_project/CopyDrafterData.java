package com.PlayGroundAdv.Solar.service.copy_project;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitDrafterDataEntity;
import com.PlayGroundAdv.Solar.repositories.PermitDrafterDataRepository;

@Service
@Transactional
public class CopyDrafterData {
	
	final PermitDrafterDataRepository drafterDataRepo;

	public CopyDrafterData(PermitDrafterDataRepository drafterDataRepo) {
		super();
		this.drafterDataRepo = drafterDataRepo;
	}

	public void copyDrafterData(Long idPermit, Long idNewPermit) {
		try {
			PermitDrafterDataEntity permitDrafterDataEntity = drafterDataRepo.findByPermitEntityId(idPermit);
			PermitDrafterDataEntity newPermitDrafterDataEntity = drafterDataRepo.findByPermitEntityId(idNewPermit);
			newPermitDrafterDataEntity = clonePermitDrafterDataEntity(newPermitDrafterDataEntity,
					permitDrafterDataEntity);
			drafterDataRepo.save(newPermitDrafterDataEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PermitDrafterDataEntity clonePermitDrafterDataEntity(PermitDrafterDataEntity newPermitDrafterDataEntity,
			PermitDrafterDataEntity permitDrafterDataEntity) {

		if (newPermitDrafterDataEntity != null && permitDrafterDataEntity != null) {
			newPermitDrafterDataEntity.setScale(permitDrafterDataEntity.getScale());
			// 09-05-2019: M.A: Correction CR-2387
			if (permitDrafterDataEntity.getScale() != null && permitDrafterDataEntity.getScale().equals("Other")) {
				newPermitDrafterDataEntity.setChooseScaleOther(permitDrafterDataEntity.getChooseScaleOther());
			}
			newPermitDrafterDataEntity.setCustomizeScale(permitDrafterDataEntity.getCustomizeScale());

			if (permitDrafterDataEntity.getCustomizeScale() != null
					&& Boolean.TRUE.equals(permitDrafterDataEntity.getCustomizeScale())) {
				newPermitDrafterDataEntity
						.setChooseScaleArrayLayout(permitDrafterDataEntity.getChooseScaleArrayLayout());
				newPermitDrafterDataEntity.setChooseScaleArrayOther(permitDrafterDataEntity.getChooseScaleArrayOther());

				newPermitDrafterDataEntity.setScaleelectricalLayout(permitDrafterDataEntity.getScaleelectricalLayout());
				newPermitDrafterDataEntity
						.setChooseScaleElectricalOther(permitDrafterDataEntity.getChooseScaleElectricalOther());

				newPermitDrafterDataEntity.setScalerackingLayout(permitDrafterDataEntity.getScalerackingLayout());
				newPermitDrafterDataEntity
						.setChooseScaleRackingOther(permitDrafterDataEntity.getChooseScaleRackingOther());
			}
			newPermitDrafterDataEntity.setScalerackingLayout(permitDrafterDataEntity.getScalerackingLayout());
			newPermitDrafterDataEntity.setScaleelectricalLayout(permitDrafterDataEntity.getScaleelectricalLayout());
			newPermitDrafterDataEntity.setTotalRoofSquareFootage(permitDrafterDataEntity.getTotalRoofSquareFootage());
			newPermitDrafterDataEntity.setTotalArraySectionCount(permitDrafterDataEntity.getTotalArraySectionCount());
			newPermitDrafterDataEntity.setStanchionQuantity(permitDrafterDataEntity.getStanchionQuantity());
		}
		return newPermitDrafterDataEntity;
	}
}
