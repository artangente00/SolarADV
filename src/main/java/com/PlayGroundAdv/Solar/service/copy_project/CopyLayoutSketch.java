package com.PlayGroundAdv.Solar.service.copy_project;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitLayoutEntity;
import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;
import com.PlayGroundAdv.Solar.repositories.PermitLayoutRepository;
import com.PlayGroundAdv.Solar.repositories.PermitSketchRepository;

@Service
@Transactional
public class CopyLayoutSketch {

	final PermitLayoutRepository layoutSketchRepo;
	final PermitSketchRepository sketchRepo;

	public CopyLayoutSketch(PermitLayoutRepository layoutSketchRepo,
			PermitSketchRepository sketchRepo) {
		super();
		this.layoutSketchRepo = layoutSketchRepo;
		this.sketchRepo = sketchRepo;
	}

	public void copyLayoutSketch(Long idPermit, PermitEntity newPermit) {
		try {

			// Copy Layout
			PermitLayoutEntity permitLayoutEntity = layoutSketchRepo.findByPermitEntityId(idPermit);
			PermitLayoutEntity newPermitLayoutEntity = layoutSketchRepo.findByPermitEntityId(newPermit.getId());
			newPermitLayoutEntity = clonePermitLayoutEntity(newPermitLayoutEntity, permitLayoutEntity);
			layoutSketchRepo.save(newPermitLayoutEntity);

			// Copy Sktech Layout
			List<PermitSketchEntity> permitSketchEntitys = sketchRepo.findByPermitEntityId(idPermit);
			List<PermitSketchEntity> newpermitSketchEntitys = sketchRepo.findByPermitEntityId(newPermit.getId());
			sketchRepo.deleteAll(newpermitSketchEntitys);
			if (permitSketchEntitys != null) {
				for (PermitSketchEntity permitSketchEntity : permitSketchEntitys) {
					PermitSketchEntity newPermitSketchEntity = clonePermitSketchEntity(permitSketchEntity);
					newPermitSketchEntity.setPermitEntity(newPermit);
					sketchRepo.save(newPermitSketchEntity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PermitLayoutEntity clonePermitLayoutEntity(PermitLayoutEntity newPermitLayoutEntity,
			PermitLayoutEntity permitLayoutEntity) {
		if (newPermitLayoutEntity != null && permitLayoutEntity != null) {
			newPermitLayoutEntity.setConduitRun(permitLayoutEntity.getConduitRun());
			newPermitLayoutEntity.setShowConduitRoofAsHeight(permitLayoutEntity.getShowConduitRoofAsHeight());
			newPermitLayoutEntity.setConduitRoofter(permitLayoutEntity.getConduitRoofter());
			newPermitLayoutEntity.setSketchNote(permitLayoutEntity.getSketchNote());
			newPermitLayoutEntity.setUploadCommentsLayout(permitLayoutEntity.getUploadCommentsAddInfo());
			newPermitLayoutEntity.setUploadCommentsAddInfo(permitLayoutEntity.getUploadCommentsAddInfo());
			newPermitLayoutEntity.setIgnoreVents(permitLayoutEntity.getIgnoreVents());
			newPermitLayoutEntity.setFiresetbacks(permitLayoutEntity.getFiresetbacks());
			newPermitLayoutEntity.setFiresetbacksNote(permitLayoutEntity.getFiresetbacksNote());
			newPermitLayoutEntity.setFireVariance(permitLayoutEntity.getFireVariance());
			newPermitLayoutEntity.setFireVarianceNote(permitLayoutEntity.getFireVarianceNote());
			newPermitLayoutEntity.setModulesEncroaching(permitLayoutEntity.getModulesEncroaching());
		}

		return newPermitLayoutEntity;
	}

	public PermitSketchEntity clonePermitSketchEntity(PermitSketchEntity permitSketchEntity) {
		PermitSketchEntity newPermitSketchEntity = new PermitSketchEntity();
		if (permitSketchEntity != null) {
			newPermitSketchEntity.setArraySketch(permitSketchEntity.getArraySketch());
			newPermitSketchEntity.setAzimuth(permitSketchEntity.getAzimuth());
			newPermitSketchEntity.setRoofPitch(permitSketchEntity.getRoofPitch());
			newPermitSketchEntity.setModuleTils(permitSketchEntity.getModuleTils());
			newPermitSketchEntity.setEaveOverHang(permitSketchEntity.getEaveOverHang());
			newPermitSketchEntity.setEaveOverHangOther(permitSketchEntity.getEaveOverHangOther());
			newPermitSketchEntity.setModelvalue(permitSketchEntity.getModelvalue());
			newPermitSketchEntity.setModuleQty(permitSketchEntity.getModuleQty());
			newPermitSketchEntity.setSquareFootage(permitSketchEntity.getSquareFootage());
		}
		return newPermitSketchEntity;
	}
}
