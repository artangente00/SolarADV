package com.PlayGroundAdv.Solar.service.copy_project;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ACCombinerFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.ACDisconnectFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.DcCombinerorDiscFavoriteEntity;
import com.PlayGroundAdv.Solar.entity.Flashing;
import com.PlayGroundAdv.Solar.entity.FlashingFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.JunctionBoxFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.LeasePPAMeter;
import com.PlayGroundAdv.Solar.entity.LeasePPAMeterFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.ProposedSubPanel;
import com.PlayGroundAdv.Solar.entity.ProposedSubPanelFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RailRackingFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCsFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcComDiscoFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.FlashingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.FlashingsFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.JunctionBoxFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.LeasePPAMeterFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.LeasePPAMeterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ProposedSubPanelFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ProposedSubPanelRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofAttachmentFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofAttachmentsRepository;

@Service
@Transactional
public class CopyBOSEquipmentFavorite {

	final RailRackingFavoriteRepository railRackingFavoritesRepo;
	final RoofAttachmentsRepository roofAttachmentRepo;
	final RoofAttachmentFavRepository roofAttachmentFavRepo;
	final FlashingRepository flashingRepo;
	final FlashingsFavoritesRepository flashingFavoriteRepo;
	final DcComDiscoFavoriteRepository dcdFavoriteRepo;
	final DcCombinerDiscoRepository dcdRepo;
	final JunctionBoxFavRepository jbFavoriteRepo;
	final ACDisconnectRepository acdRepo;
	final ACDisconnectFavRepository acdFavoriteRepo;
	final AcCombinerSLCsFavoritesRepository accFavoriteRepo;
	final LeasePPAMeterRepository leasePPAMeterRepo;
	final LeasePPAMeterFavoritesRepository leasePPAMeterFavoriteRepo;
	final ProposedSubPanelRepository proposedSubPanelRepo;
	final ProposedSubPanelFavoritesRepository proposedSubPanelFavoriteRepo;

	public CopyBOSEquipmentFavorite(RailRackingFavoriteRepository railRackingFavoritesRepo,
			RoofAttachmentsRepository roofAttachmentRepo, RoofAttachmentFavRepository roofAttachmentFavRepo,
			FlashingRepository flashingRepo, FlashingsFavoritesRepository flashingFavoriteRepo,
			DcComDiscoFavoriteRepository dcdFavoriteRepo, DcCombinerDiscoRepository dcdRepo,
			JunctionBoxFavRepository jbFavoriteRepo, ACDisconnectRepository acdRepo,
			ACDisconnectFavRepository acdFavoriteRepo, AcCombinerSLCsFavoritesRepository accFavoriteRepo,
			LeasePPAMeterRepository leasePPAMeterRepo, LeasePPAMeterFavoritesRepository leasePPAMeterFavoriteRepo,
			ProposedSubPanelRepository proposedSubPanelRepo,
			ProposedSubPanelFavoritesRepository proposedSubPanelFavoriteRepo) {
		super();
		this.railRackingFavoritesRepo = railRackingFavoritesRepo;
		this.roofAttachmentRepo = roofAttachmentRepo;
		this.roofAttachmentFavRepo = roofAttachmentFavRepo;
		this.flashingRepo = flashingRepo;
		this.flashingFavoriteRepo = flashingFavoriteRepo;
		this.dcdFavoriteRepo = dcdFavoriteRepo;
		this.dcdRepo = dcdRepo;
		this.jbFavoriteRepo = jbFavoriteRepo;
		this.acdRepo = acdRepo;
		this.acdFavoriteRepo = acdFavoriteRepo;
		this.accFavoriteRepo = accFavoriteRepo;
		this.leasePPAMeterRepo = leasePPAMeterRepo;
		this.leasePPAMeterFavoriteRepo = leasePPAMeterFavoriteRepo;
		this.proposedSubPanelRepo = proposedSubPanelRepo;
		this.proposedSubPanelFavoriteRepo = proposedSubPanelFavoriteRepo;
	}

	public void copyRailRackingFav(RailRacking equipment, AuthentificationEntity user) {

		try {
			if (equipment != null && !Boolean.TRUE.equals(equipment.getIsDeleted())
					&& Boolean.FALSE.equals(railRackingFavoritesRepo
							.existsByRailRackingIdAndAuthentificationEntityId(equipment.getId(), user.getId()))) {
				RailRackingFavLibraryEntity newFav = new RailRackingFavLibraryEntity(user, equipment);
				railRackingFavoritesRepo.save(newFav);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void copyRoofAttachementFav(Long idRoof, AuthentificationEntity user) {
		try {
			if (Boolean.FALSE.equals(
					roofAttachmentFavRepo.existsByAuthentificationEntityIdAndRoofAttachmentId(user.getId(), idRoof))) {
				RoofAttachmentsEntity equipment = roofAttachmentRepo.findById(idRoof)
						.orElse(new RoofAttachmentsEntity());
				RoofAttachmentFavLibraryEntity newFav = new RoofAttachmentFavLibraryEntity(user, equipment);
				roofAttachmentFavRepo.save(newFav);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copyFlashingFav(Long idFlashing, AuthentificationEntity user) {
		try {
			if (Boolean.FALSE.equals(
					flashingFavoriteRepo.existsByAuthentificationEntityIdAndFlashingId(user.getId(), idFlashing))) {
				Flashing equipment = flashingRepo.findById(idFlashing).orElse(new Flashing());
				FlashingFavLibraryEntity newFav = new FlashingFavLibraryEntity(user, equipment);
				flashingFavoriteRepo.save(newFav);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copyACCFav(ACCombinerSLC equipment, AuthentificationEntity user) {
		try {
			if (equipment != null && !Boolean.TRUE.equals(equipment.getIsDeleted())
					&& Boolean.FALSE.equals(accFavoriteRepo
							.existsByAcCombinerSlcIdAndAuthentificationEntityId(equipment.getId(), user.getId()))) {
				ACCombinerFavLibraryEntity newFav = new ACCombinerFavLibraryEntity(user, equipment);
				accFavoriteRepo.save(newFav);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copyACDFav(String model, AuthentificationEntity user) {
		try {
			Long idACD = Long.valueOf(model.split(":")[0]);
			copyACDFavById(idACD, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copyACDFavById(Long id, AuthentificationEntity user) {
		try {
			if (Boolean.FALSE
					.equals(acdFavoriteRepo.existsByAuthentificationEntityIdAndACDisconnectId(user.getId(), id))) {
				ACDisconnect equipment = acdRepo.findById(id).orElse(new ACDisconnect());
				ACDisconnectFavLibraryEntity newFav = new ACDisconnectFavLibraryEntity(user, equipment);
				acdFavoriteRepo.save(newFav);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copyDCDFav(DCCombinerDisconnectEntity equipment, AuthentificationEntity user) {
		try {
			if (equipment != null && !Boolean.TRUE.equals(equipment.getIsDeleted())
					&& Boolean.FALSE
							.equals(dcdFavoriteRepo.existsByAuthentificationEntityIdAndDcCombinerDisconnectEntityId(
									user.getId(), equipment.getId()))) {
				DcCombinerorDiscFavoriteEntity newFav = new DcCombinerorDiscFavoriteEntity(user, equipment);
				dcdFavoriteRepo.save(newFav);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copyJBFav(Long idJB, AuthentificationEntity user) {
		try {
			if (Boolean.FALSE.equals(jbFavoriteRepo.existsByJboxIdAndAuthentificationEntityId(idJB, user.getId()))) {
				DCCombinerDisconnectEntity equipment = dcdRepo.findById(idJB).orElse(new DCCombinerDisconnectEntity());
				JunctionBoxFavLibraryEntity newFav = new JunctionBoxFavLibraryEntity(user, equipment);
				jbFavoriteRepo.save(newFav);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void copyLeasePPAMeter(Long idMeter, AuthentificationEntity user) {
		try {
			if (Boolean.FALSE.equals(leasePPAMeterFavoriteRepo
					.existsByAuthentificationEntityIdAndLeasePPAMeterId(user.getId(), idMeter))) {
				LeasePPAMeter equipment = leasePPAMeterRepo.findById(idMeter).orElse(new LeasePPAMeter());
				LeasePPAMeterFavLibraryEntity newFav = new LeasePPAMeterFavLibraryEntity(user, equipment);
				leasePPAMeterFavoriteRepo.save(newFav);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void copyProposedSubPanel(Long idSubPanel, AuthentificationEntity user) {
		try {
			if (Boolean.FALSE.equals(proposedSubPanelFavoriteRepo
					.existsByAuthentificationEntityIdAndProposedSubPanelId(user.getId(), idSubPanel))) {
				ProposedSubPanel equipment = proposedSubPanelRepo.findById(idSubPanel).orElse(new ProposedSubPanel());
				ProposedSubPanelFavLibraryEntity newFav = new ProposedSubPanelFavLibraryEntity(user, equipment);
				proposedSubPanelFavoriteRepo.save(newFav);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
