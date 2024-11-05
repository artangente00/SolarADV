package com.PlayGroundAdv.Solar.service.user_management;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.RoofTypeandAttachmentEntity;
import com.PlayGroundAdv.Solar.model.RoofTypeAttachementModel;
import com.PlayGroundAdv.Solar.model.libraries.RoofAttachmentModel;
import com.PlayGroundAdv.Solar.repositories.RoofTypeandAttachmentRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofAttachmentFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofAttachmentsRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;

@Service
public class RailtoRoofConnectionSetting {

	final RoofAttachmentFavRepository roofAttFavRepo;
	final RoofAttachmentsRepository roofAttRepo;
	final AuthenticationRepository authRepo;
	final RoofTypeandAttachmentRepository roofTypeandAttRepo;

	public RailtoRoofConnectionSetting(RoofAttachmentFavRepository roofAttFavRepo,
			RoofAttachmentsRepository roofAttRepo, AuthenticationRepository authRepo,
			RoofTypeandAttachmentRepository roofTypeandAttRepo) {
		super();
		this.roofAttFavRepo = roofAttFavRepo;
		this.roofAttRepo = roofAttRepo;
		this.authRepo = authRepo;
		this.roofTypeandAttRepo = roofTypeandAttRepo;
	}

	public List<RoofAttachmentModel> getRoofModels(Long idUser) {
		try {
			return roofAttFavRepo.findAllByUser(idUser);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	public RoofAttachmentModel getRoofAttachment(Long idUser) {

		try {
			return roofTypeandAttRepo.findModelsByUser(idUser);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public String saveRoofAttachment(RoofTypeAttachementModel roofAttachement, Long idUser) {

		try {

			RoofTypeandAttachmentEntity roof = roofTypeandAttRepo.findByAuthentificationEntityId(idUser);
			
			if (roof == null) {
				roof = new RoofTypeandAttachmentEntity();
				AuthentificationEntity authentificationEntity = authRepo.findById(idUser).orElse(null);
				roof.setAuthentificationEntity(authentificationEntity);
			}
			roof.setBarrelCurve(roofAttachement.getBarrelCurve());
			roof.setBuildUp(roofAttachement.getBuildUp());
			roof.setClayTile(roofAttachement.getClayTile());
			roof.setCompShingle(roofAttachement.getCompShingle());
			roof.setCorrugatedMetal(roofAttachement.getCorrugatedMetal());
			roof.setFiberCement(roofAttachement.getFiberCement());
			roof.setFlatConcrete(roofAttachement.getFlatConcrete());
			roof.setFoam(roofAttachement.getFoam());
			roof.setMembraneTpo(roofAttachement.getMembraneTpo());
			roof.setRolledAsphalt(roofAttachement.getRolledAsphalt());
			roof.setRolledComp(roofAttachement.getRolledComp());
			roof.setRomanCurved(roofAttachement.getRomanCurved());
			roof.setSlate(roofAttachement.getSlate());
			roof.setStandingMetal(roofAttachement.getStandingMetal());
			roof.setTrapezoidalMetal(roofAttachement.getTrapezoidalMetal());
			roof.setWoodorCedar(roofAttachement.getWoodorCedar());
			roof.setLiquidApplied(roofAttachement.getLiquidApplied());
			roof.setCorrugatedPolyCarb(roofAttachement.getCorrugatedPolyCarb());
			roofTypeandAttRepo.save(roof);
			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

}
