package com.PlayGroundAdv.Solar.service.export_project;

import java.util.LinkedHashMap;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.Flashing;
import com.PlayGroundAdv.Solar.entity.LeasePPAMeter;
import com.PlayGroundAdv.Solar.entity.ProposedSubPanel;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcComDiscoFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.FlashingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.LeasePPAMeterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ProposedSubPanelRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofAttachmentsRepository;
import com.PlayGroundAdv.Solar.service.project.GetProjectDetailsUtils;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ExportProjectInfo {

	final CheckValueTypesService checkValueTypes;
	final RailRackingRepository railRackingRepo;
	final RoofAttachmentsRepository roofAttachementRepo;
	final FlashingRepository flashingRepo;
	final DcComDiscoFavoriteRepository dcdFavoriteRepo;
	final DcCombinerDiscoRepository dcdRepo;
	final ACDisconnectRepository acdRepo;
	final AcCombinerSLCRepository accRepo;
	final ProposedSubPanelRepository proposedSubPanelRepo;
	final LeasePPAMeterRepository leasePPAMeterRepo;
	final GetProjectDetailsUtils getProjectDetailsUtils;

	public ExportProjectInfo(CheckValueTypesService checkValueTypes, RailRackingRepository railRackingRepo,
			RoofAttachmentsRepository roofAttachementRepo, FlashingRepository flashingRepo,
			DcComDiscoFavoriteRepository dcdFavoriteRepo, DcCombinerDiscoRepository dcdRepo,
			ACDisconnectRepository acdRepo, AcCombinerSLCRepository accRepo,
			ProposedSubPanelRepository proposedSubPanelRepo, LeasePPAMeterRepository leasePPAMeterRepo,  GetProjectDetailsUtils getProjectDetailsUtils) {
		super();
		this.checkValueTypes = checkValueTypes;
		this.railRackingRepo = railRackingRepo;
		this.roofAttachementRepo = roofAttachementRepo;
		this.flashingRepo = flashingRepo;
		this.dcdFavoriteRepo = dcdFavoriteRepo;
		this.dcdRepo = dcdRepo;
		this.acdRepo = acdRepo;
		this.accRepo = accRepo;
		this.proposedSubPanelRepo = proposedSubPanelRepo;
		this.leasePPAMeterRepo = leasePPAMeterRepo;
		this.getProjectDetailsUtils = getProjectDetailsUtils;
	}

	public LinkedHashMap<String, String> balanceOfSystem(GetPermitByIdResult permit) {

		LinkedHashMap<String, String> bos = new LinkedHashMap<>();

		try {

			if (permit.getPermitProjectSiteInfoEntityTwo().getRailRakingModel() != null && checkValueTypes
					.isLongPositive(permit.getPermitProjectSiteInfoEntityTwo().getRailRakingModel())) {
				RailRacking railRacking = railRackingRepo
						.findById(permit.getPermitProjectSiteInfoEntityTwo().getRailRakingModel())
						.orElse(new RailRacking());
				bos.put("Racking Model",
						checkValueTypes.convert(railRacking.getManufacturer() + " " + railRacking.getModel()));

			} else {
				bos.put("Racking Model", "");
			}

			if (checkValueTypes.isNumericNotZero(permit.getPermitProjectSiteInfoEntityTwo().getRailConnectionModel())) {

				RoofAttachmentsEntity roofAttachment = roofAttachementRepo
						.findById(Long.valueOf(permit.getPermitProjectSiteInfoEntityTwo().getRailConnectionModel()))
						.orElse(new RoofAttachmentsEntity());
				bos.put("Rail to Roof Connection Model",
						checkValueTypes.convert(roofAttachment.getManufacturer() + " " + roofAttachment.getModel()));

			} else {
				bos.put("Rail to Roof Connection Model", "");
			}

			//R.G 27-01-2022 PP-688
			if (checkValueTypes.isStringNotEmpty(permit.getPermitEntity().getInsertRoofNote())) {
				bos.put("Not allowed roof material note", checkValueTypes
						.convert(permit.getPermitEntity().getInsertRoofNote()));
			}
			
			
			// 08-28-2019: M.A: CR-2879
			bos.put("Roof to Rail Anchor/Stanchion Spacing",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getStanchionMaxSpacing()));
			bos.put("Other Roof to Rail Anchor/Stanchion Spacing",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getStanchionMaxSpacingOther()));

			
			
			// Cr-2230
			if (checkValueTypes.isNumericNotZero(permit.getPermitProjectSiteInfoEntityTwo().getFlashing())) {

				Flashing flashing = flashingRepo
						.findById(Long.valueOf(permit.getPermitProjectSiteInfoEntityTwo().getFlashing()))
						.orElse(new Flashing());
				bos.put("Flashing", checkValueTypes.convert(flashing.getManufacturer() + " " + flashing.getModel()));

			} else {
				bos.put("Flashing", "");
			}
			// CR-1273
			if (Boolean.TRUE.equals(permit.getPermitArraysEntity().getGroundMounted())
					&& permit.getPermitProjectSiteInfoEntityTwo().getRailRakingModelforGroundMounted() != null
					&& checkValueTypes.isLongPositive(
							permit.getPermitProjectSiteInfoEntityTwo().getRailRakingModelforGroundMounted())) {
				RailRacking railRacking = railRackingRepo
						.findById(permit.getPermitProjectSiteInfoEntityTwo().getRailRakingModelforGroundMounted())
						.orElse(new RailRacking());
				bos.put("Rail / Racking Model (GroundMounted)",
						checkValueTypes.convert(railRacking.getManufacturer() + " " + railRacking.getModel()));

			} else {
				bos.put("Rail / Racking Model (GroundMounted)", "");
			}

			if (Boolean.TRUE.equals(permit.getPermitArraysEntity().getGroundMounted())
					&& checkValueTypes.NotEquals(permit.getPermitAdvEntityResult().getSizeOfPipe(), "")) {
				if (checkValueTypes.Equals(permit.getPermitAdvEntityResult().getSizeOfPipe(), "Other")) {
					if (checkValueTypes.NotEquals(permit.getPermitAdvEntityResult().getSizeOfPipeOther(), "")) {
						bos.put("Size of pipe",
								checkValueTypes.convert(permit.getPermitAdvEntityResult().getSizeOfPipeOther()));
					}
				} else {
					bos.put("Size of pipe", checkValueTypes.convert(permit.getPermitAdvEntityResult().getSizeOfPipe()));
				}
			} else {
				bos.put("Size of pipe", "");
			}

			if (Boolean.TRUE.equals(permit.getPermitArraysEntity().getGroundMounted())
					&& checkValueTypes.NotEquals(permit.getPermitAdvEntityResult().getThicknessOfPipe(), "")) {
				if (checkValueTypes.Equals(permit.getPermitAdvEntityResult().getThicknessOfPipe(), "Other")) {
					if (checkValueTypes.NotEquals(permit.getPermitAdvEntityResult().getThicknessOfPipeOther(), "")) {
						bos.put("Thickness of pipe",
								checkValueTypes.convert(permit.getPermitAdvEntityResult().getThicknessOfPipeOther()));
					}
				} else {
					bos.put("Thickness of pipe",
							checkValueTypes.convert(permit.getPermitAdvEntityResult().getThicknessOfPipe()));
				}
			} else {
				bos.put("Thickness of pipe", "");
			}

			if (Boolean.TRUE.equals(permit.getPermitArraysEntity().getGroundMounted())
					&& checkValueTypes.NotEquals(permit.getPermitAdvEntityResult().getBracedUnbraced(), "")) {
				bos.put("Braced or Unbraced",
						checkValueTypes.convert(permit.getPermitAdvEntityResult().getBracedUnbraced()));
			} else {
				bos.put("Braced or Unbraced", "");
			}

			if (checkValueTypes.NotEquals(permit.getPermitAdvEntityResult().getFootingDiameter(), "")) {
				if (checkValueTypes.Equals(permit.getPermitAdvEntityResult().getFootingDiameter(), "Other")) {
					if (checkValueTypes.NotEquals(permit.getPermitAdvEntityResult().getFootingDiameterOther(), "")) {
						bos.put("Footing Diameter",
								checkValueTypes.convert(permit.getPermitAdvEntityResult().getFootingDiameterOther()));
					}
				} else {
					bos.put("Footing Diameter",
							checkValueTypes.convert(permit.getPermitAdvEntityResult().getFootingDiameter()));
				}
			} else {
				bos.put("Footing Diameter", "");
			}

			if (Boolean.TRUE.equals(permit.getPermitArraysEntity().getPatioMounted())
					&& permit.getPermitProjectSiteInfoEntityTwo().getRailRakingforPatioMounted() != null
					&& checkValueTypes.isLongPositive(
							permit.getPermitProjectSiteInfoEntityTwo().getRailRakingforPatioMounted())) {

				RailRacking railRacking = railRackingRepo
						.findById(permit.getPermitProjectSiteInfoEntityTwo().getRailRakingforPatioMounted())
						.orElse(new RailRacking());
				bos.put("Rail / Racking Model (PatioMounted)",
						checkValueTypes.convert(railRacking.getManufacturer() + " " + railRacking.getModel()));

			} else {
				bos.put("Rail / Racking Model (PatioMounted)", "");
			}
			
			if (Boolean.TRUE.equals(permit.getPermitArraysEntity().getCarportMounted())
					&& permit.getPermitProjectSiteInfoEntityTwo().getRailRakingforCarport() != null
					&& checkValueTypes.isLongPositive(
							permit.getPermitProjectSiteInfoEntityTwo().getRailRakingforCarport())) {

				RailRacking railRacking = railRackingRepo
						.findById(permit.getPermitProjectSiteInfoEntityTwo().getRailRakingforCarport())
						.orElse(new RailRacking());
				bos.put("Rail / Racking Model (CarportMounted)",
						checkValueTypes.convert(railRacking.getManufacturer() + " " + railRacking.getModel()));

			} else {
				bos.put("Rail / Racking Model (CarportMounted)", "");
			}
			// end

			if (checkValueTypes.NotEquals(permit.getPermitProjectSiteInfoEntityTwo().getMicroInverterCabling(), "")) {
				bos.put("Transitioning from Microinverter cabling in...",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getMicroInverterCabling()));

				if (checkValueTypes.isNumericNotZero(permit.getPermitProjectSiteInfoEntityTwo().getRoofTopJbox())
						&& checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getMicroInverterCabling(),
								"Rooftop Junction box(es)")) {
					DCCombinerDisconnectEntity jboxEntity = dcdRepo
							.findById(Long.valueOf(permit.getPermitProjectSiteInfoEntityTwo().getRoofTopJbox()))
							.orElse(new DCCombinerDisconnectEntity());
					bos.put("Rooftop Junction box(es) from Microinverter",
							checkValueTypes.convert(jboxEntity.getDropdownOption()));
				} else {
					bos.put("Rooftop Junction box(es) from Microinverter", "");
				}
				
				bos.put("Qty of the Junction Box", permit.getPermitProjectSiteInfoEntityTwo().getQtyJunctionBox()+"");

				if (checkValueTypes.contains(permit.getPermitProjectSiteInfoEntityTwo().getRoofTopACDisco(), ":")
						&& checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getMicroInverterCabling(),
								"Rooftop AC Disconnect(s)")) {

					ACDisconnect acDisconnect = acdRepo
							.findById(Long.valueOf(
									permit.getPermitProjectSiteInfoEntityTwo().getRoofTopACDisco().split(":")[0]))
							.orElse(new ACDisconnect());
					if (checkValueTypes.NotEquals(acDisconnect.getDropdownOption(), "")) {
						bos.put("Rooftop AC Disconnect(s)", checkValueTypes.convert(acDisconnect.getDropdownOption()));
					} else {
						bos.put("Rooftop AC Disconnect(s)", checkValueTypes.convert(acDisconnect.getModel()));
					}
				} else {
					bos.put("Rooftop AC Disconnect(s)", "");
				}
				if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getMicroInverterCabling(),
						"Rooftop AC Combiner")
						&& checkValueTypes
								.isLongPositive(permit.getPermitProjectSiteInfoEntityTwo().getRoofTopACCombiner())) {

					ACCombinerSLC acDisconnect = accRepo
							.findById(permit.getPermitProjectSiteInfoEntityTwo().getRoofTopACCombiner())
							.orElse(new ACCombinerSLC());

					if (checkValueTypes.NotEquals(acDisconnect.getDropdownOption(), "")) {
						bos.put("Rooftop AC combiner", checkValueTypes.convert(acDisconnect.getDropdownOption()));
					} else {
						bos.put("Rooftop AC combiner", checkValueTypes.convert(acDisconnect.getModel()));
					}
				} else {
					bos.put("Rooftop AC combiner", "");
				}

			} else {
				bos.put("Transitioning from Microinverter cabling in...",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getMicroInverterCabling()));
				bos.put("Rooftop Junction box(es) from Microinverter", "");
				bos.put("Rooftop AC Disconnect(s)", "");
				bos.put("Rooftop AC combiner", "");

			}
			bos.put("Installing AC Combiner",
					Boolean.TRUE.equals(permit.getPermitProjectSiteInfoEntityTwo().getInstallingACCombiner()) ? "Yes"
							: "No");
			if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getInstallingACCombiner(), true)
					&& checkValueTypes
							.isLongPositive(permit.getPermitProjectSiteInfoEntityTwo().getaCCombinerInstalled())) {

				ACCombinerSLC acCombiner = accRepo
						.findById(permit.getPermitProjectSiteInfoEntityTwo().getaCCombinerInstalled())
						.orElse(new ACCombinerSLC());

				if (checkValueTypes.NotEquals(acCombiner.getDropdownOption(), "")) {
					bos.put("AC combiner", checkValueTypes.convert(acCombiner.getDropdownOption()));
				} else {
					bos.put("AC combiner",
							checkValueTypes.convert(acCombiner.getManufacturer() + " " + acCombiner.getModel()));
				}

			} else {
				bos.put("AC combiner", "");
			}

			if (checkValueTypes.NotEquals(permit.getPermitProjectSiteInfoEntityTwo().getTransitioningPVWireIn(), "")) {
				bos.put("Transitioning from PV Wire in...",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getTransitioningPVWireIn()));

				if (checkValueTypes.isNumericNotZero(permit.getPermitProjectSiteInfoEntityTwo().getRoofTopJboxDC())
						&& checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getTransitioningPVWireIn(),
								"Rooftop Junction box(es)")) {
					DCCombinerDisconnectEntity jboxEntity = dcdRepo
							.findById(Long.valueOf(permit.getPermitProjectSiteInfoEntityTwo().getRoofTopJboxDC()))
							.orElse(new DCCombinerDisconnectEntity());
					bos.put("Rooftop Junction box(es)", checkValueTypes.convert(jboxEntity.getDropdownOption()));
				} else {
					bos.put("Rooftop Junction box(es)", "");
				}
				
				bos.put("Qty of the Junction Box", permit.getPermitProjectSiteInfoEntityTwo().getQtyJunctionBox()+"");

				if (checkValueTypes.isLongPositive(permit.getPermitProjectSiteInfoEntityTwo().getRoofTopDCCombiner())
						&& checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getTransitioningPVWireIn(),
								"Rooftop DC Combiner")) {

					DCCombinerDisconnectEntity dcCombinerDisconnect = dcdRepo
							.findById(permit.getPermitProjectSiteInfoEntityTwo().getRoofTopDCCombiner())
							.orElse(new DCCombinerDisconnectEntity());
					bos.put("Rooftop DC Combiner", checkValueTypes.convert(dcCombinerDisconnect.getDropdownOption()));

				} else {
					bos.put("Rooftop DC Combiner", "");
				}
				if (permit.getPermitProjectSiteInfoEntityTwo().getRoofTopDCDisco() != null
						&& checkValueTypes
								.isLongPositive(permit.getPermitProjectSiteInfoEntityTwo().getRoofTopDCDisco())
						&& checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getTransitioningPVWireIn(),
								"Rooftop DC Disconnect(s)")
						&& dcdRepo.existsByIdAndIsDeleted(
								permit.getPermitProjectSiteInfoEntityTwo().getRoofTopDCDisco(), false)
						&& dcdFavoriteRepo.existsByAuthentificationEntityIdAndDcCombinerDisconnectEntityId(
								permit.getPermitEntity().getAuthentificationEntity().getId(),
								permit.getPermitProjectSiteInfoEntityTwo().getRoofTopDCDisco())) {

					DCCombinerDisconnectEntity dcCombinerDisconnect = dcdRepo
							.findById(permit.getPermitProjectSiteInfoEntityTwo().getRoofTopDCDisco())
							.orElse(new DCCombinerDisconnectEntity());
					bos.put("Rooftop DC Disconnect(s)",
							checkValueTypes.convert(dcCombinerDisconnect.getDropdownOption()));
				} else {
					bos.put("Rooftop DC Disconnect(s)", "");
				}

			} else {
				bos.put("Transitioning from PV Wire in...", "");
				bos.put("Rooftop Junction box(es)", "");
				bos.put("Rooftop DC Combiner", "");
				bos.put("Rooftop DC Disconnect(s)", "");
			}

			bos.put("Installing Roof Top AC Disco or Combiner",
					Boolean.TRUE.equals(permit.getPermitProjectSiteInfoEntityTwo().getInstallRoofTopACDiscoCombiner())
							? "Yes"
							: "No");
			if (permit.getPermitProjectSiteInfoEntityTwo().getInstallRoofTopACDiscoCombiner() != null
					&& permit.getPermitProjectSiteInfoEntityTwo().getInstallRoofTopACDiscoCombiner()
					&& permit.getPermitProjectSiteInfoEntityTwo().getRoofTopACCombinerDisconnect() != null
					&& permit.getPermitProjectSiteInfoEntityTwo().getRoofTopACCombinerDisconnect().contains(":")) {

				ACDisconnect acDisconnect = acdRepo.findById(Long.valueOf(
						permit.getPermitProjectSiteInfoEntityTwo().getRoofTopACCombinerDisconnect().split(":")[0]))
						.orElse(new ACDisconnect());
				bos.put("Roof Top AC Disco or Combiner", checkValueTypes.convert(acDisconnect.getDropdownOption()));
			} else {
				bos.put("Roof Top AC Disco or Combiner", "");
			}

			bos.put("Installing a Roof Top DC Combiner or Disco (other than the one integrated in the inverter)",
					Boolean.TRUE.equals(permit.getPermitProjectSiteInfoEntityTwo().getUsedByInverterManufacturer())
							? "Yes"
							: "No");

			if (Boolean.TRUE.equals(permit.getPermitProjectSiteInfoEntityTwo().getUsedByInverterManufacturer())
					&& checkValueTypes
							.isLongPositive(permit.getPermitProjectSiteInfoEntityTwo().getDisconnectModel())) {

				DCCombinerDisconnectEntity dcCombinerDisconnect = dcdRepo
						.findById(permit.getPermitProjectSiteInfoEntityTwo().getDisconnectModel())
						.orElse(new DCCombinerDisconnectEntity());
				bos.put("DC Combiner or Disconnect Manufacturer",
						Boolean.TRUE.equals(dcCombinerDisconnect.getManufacturer()) ? "Yes" : "No");
				bos.put("DC Combiner or Disconnect Model",
						Boolean.TRUE.equals(dcCombinerDisconnect.getModel()) ? "Yes" : "No");

			} else {
				bos.put("DC Combiner or Disconnect Manufacturer", "");
				bos.put("DC Combiner or Disconnect Model", "");
			}
			bos.put("Location", checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getLocation()));
			if (permit.getPermitProjectSiteInfoEntityTwo().getUsedByInverterManufacturer() != null
					&& permit.getPermitProjectSiteInfoEntityTwo().getUsedByInverterManufacturer() == true
					&& permit.getPermitProjectSiteInfoEntityTwo().getDisconnectModelTwo() != null
					&& dcdRepo.existsByIdAndIsDeleted(
							permit.getPermitProjectSiteInfoEntityTwo().getDisconnectModelTwo(), false)
					&& dcdFavoriteRepo.existsByAuthentificationEntityIdAndDcCombinerDisconnectEntityId(
							permit.getPermitEntity().getAuthentificationEntity().getId(),
							permit.getPermitProjectSiteInfoEntityTwo().getDisconnectModelTwo())) {

				DCCombinerDisconnectEntity dcCombinerDisconnect = dcdRepo
						.findById(permit.getPermitProjectSiteInfoEntityTwo().getDisconnectModelTwo())
						.orElse(new DCCombinerDisconnectEntity());
				bos.put("DC Combiner or Disconnect #2 Manufacturer",
						checkValueTypes.convert(dcCombinerDisconnect.getManufacturer()));
				bos.put("DC Combiner or Disconnect #2 Model", checkValueTypes.convert(dcCombinerDisconnect.getModel()));

			} else {
				bos.put("DC Combiner or Disconnect #2 Manufacturer", "");
				bos.put("DC Combiner or Disconnect #2 Model", "");
			}
			bos.put("Location Two",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getLocationTwo()));
			if (permit.getPermitProjectSiteInfoEntityTwo().getUsedByInverterManufacturer() != null
					&& permit.getPermitProjectSiteInfoEntityTwo().getUsedByInverterManufacturer() == true
					&& permit.getPermitProjectSiteInfoEntityTwo().getDisconnectModelThree() != null
					&& dcdRepo.existsByIdAndIsDeleted(
							permit.getPermitProjectSiteInfoEntityTwo().getDisconnectModelThree(), false)
					&& dcdFavoriteRepo.existsByAuthentificationEntityIdAndDcCombinerDisconnectEntityId(
							permit.getPermitEntity().getAuthentificationEntity().getId(),
							permit.getPermitProjectSiteInfoEntityTwo().getDisconnectModelThree())) {

				DCCombinerDisconnectEntity dcCombinerDisconnect = dcdRepo
						.findById(permit.getPermitProjectSiteInfoEntityTwo().getDisconnectModelThree())
						.orElse(new DCCombinerDisconnectEntity());

				bos.put("DC Combiner or Disconnect #3 Manufacturer",
						checkValueTypes.convert(dcCombinerDisconnect.getManufacturer()));
				bos.put("DC Combiner or Disconnect #3 Model", checkValueTypes.convert(dcCombinerDisconnect.getModel()));

			} else {
				bos.put("DC Combiner or Disconnect #3 Manufacturer", "");
				bos.put("DC Combiner or Disconnect #3 Model", "");
			}

			bos.put("Location Three",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getLocationThree()));
			bos.put("Installing DC J-Box(es)",
					Boolean.TRUE.equals(permit.getPermitProjectSiteInfoEntityTwo().getInstallingDCBo()) ? "Yes" : "No");
			bos.put("Combining AC Circuits in...",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getCombiningACCircuits()));

			bos.put("Enter Sub Panel Specification",
					Boolean.TRUE.equals(permit.getPermitProjectSiteInfoEntityTwo().getSubPanelSpecification()) ? "Yes" : "No");
			if (Boolean.TRUE.equals(permit.getPermitProjectSiteInfoEntityTwo().getSubPanelSpecification())) {
				bos.put("Sub Panel Bus Rating", checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getSubPanelBusRatingCombining(), "Other")
						? permit.getPermitProjectSiteInfoEntityTwo().getSubPanelBusRatingCombiningOther()+"A" : permit.getPermitProjectSiteInfoEntityTwo().getSubPanelBusRatingCombining());
				
				bos.put("Sub Panel Main Breaker Rating", checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getSubPanelMainBreakerRatingCombining(), "Other")
						? permit.getPermitProjectSiteInfoEntityTwo().getSubPanelMainBreakerRatingCombiningOther()+"A" : permit.getPermitProjectSiteInfoEntityTwo().getSubPanelMainBreakerRatingCombining());
				
				bos.put("Sub Panel Breaker (OCPD) at Main Service", checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getSubPanelBreakerAtMainServiceCombining(), "Other")
						? permit.getPermitProjectSiteInfoEntityTwo().getSubPanelBreakerAtMainServiceCombiningOther()+"A" : permit.getPermitProjectSiteInfoEntityTwo().getSubPanelBreakerAtMainServiceCombining());
			}
			if (permit.getPermitProjectSiteInfoEntityTwo().getProposedACCombMainBreaker() != null) {
				bos.put("The Proposed AC Combiner has a Main Breaker",
						Boolean.TRUE.equals(permit.getPermitProjectSiteInfoEntityTwo().getProposedACCombMainBreaker())
								? "Yes"
								: "No");
			} else {
				bos.put("The Proposed AC Combiner has a Main Breaker", "");
			}

			if (checkValueTypes
					.NotEquals(permit.getPermitProjectSiteInfoEntityTwo().getProposedACCombMainBreakerRating(), "")) {
				if (checkValueTypes.Equals(
						permit.getPermitProjectSiteInfoEntityTwo().getProposedACCombMainBreakerRating(), "Other")) {
					bos.put("Proposed AC Combiner Main Breaker Rating", checkValueTypes.convert(
							permit.getPermitProjectSiteInfoEntityTwo().getProposedACCombMainBreakerRatingOther()));
				} else {
					bos.put("Proposed AC Combiner Main Breaker Rating", checkValueTypes
							.convert(permit.getPermitProjectSiteInfoEntityTwo().getProposedACCombMainBreakerRating()));
				}
			} else {
				bos.put("Proposed AC Combiner Main Breaker Rating", "");
			}

			if ((permit.getPermitArraysEntity() != null
					&& permit.getPermitArraysEntity().getDeviceToIncorporate() != null)
					&& getProjectDetailsUtils.isMicroOrAcModuleProject(permit.getPermitArraysEntity().getDeviceToIncorporate())
					&& (Boolean.TRUE.equals(permit.getPermitArraysEntity().getRoofMounted())
							|| Boolean.TRUE.equals(permit.getPermitArraysEntity().getCarportMounted()))) {
				bos.put("Installing Independent AC Disco(s)", "");
				bos.put("Installing Ground Level Independent AC Disco(s)",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getUseDisconectSwith()));
			} else {
				bos.put("Installing Independent AC Disco(s)",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getUseDisconectSwith()));
				bos.put("Installing Ground Level Independent AC Disco(s)", "");
			}

			if (permit.getPermitProjectSiteInfoEntityTwo().getUseDisconectSwith() != null
					&& checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getUseDisconectSwith(), "Yes")
					&& permit.getPermitProjectSiteInfoEntityTwo().getRooftopACCombinerModel() != null
					&& permit.getPermitProjectSiteInfoEntityTwo().getRooftopACCombinerModel().contains(":")) {

				ACDisconnect acDisconnect = acdRepo
						.findById(Long.valueOf(
								permit.getPermitProjectSiteInfoEntityTwo().getRooftopACCombinerModel().split(":")[0]))
						.orElse(new ACDisconnect());
				if (checkValueTypes.NotEquals(acDisconnect.getDropdownOption(), "")) {
					bos.put("AC Disconnect Model", checkValueTypes.convert(acDisconnect.getDropdownOption()));
				} else {
					bos.put("AC Disconnect Model", checkValueTypes.convert(acDisconnect.getModel()));
				}
			} else {
				bos.put("AC Disconnect Model", "");
			}

			bos.put("Location Four",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getLocationFour()));
			bos.put("Qty of THIS AC Disco Qty", checkValueTypes
					.convert(permit.getPermitProjectSiteInfoEntityTwo().getQtyIndependentACDisco() + ""));
			
			String groundLEvel = "";
			if ((permit.getPermitArraysEntity() != null
					&& permit.getPermitArraysEntity().getDeviceToIncorporate() != null)
					&& getProjectDetailsUtils.isMicroOrAcModuleProject(permit.getPermitArraysEntity().getDeviceToIncorporate())
					&& (Boolean.TRUE.equals(permit.getPermitArraysEntity().getRoofMounted())
							|| Boolean.TRUE.equals(permit.getPermitArraysEntity().getCarportMounted()))) {
				groundLEvel = "Ground Level ";
			}
			
			if (permit.getPermitProjectSiteInfoEntityTwo().getUseDisconectSwith() != null
					&& checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getUseDisconectSwith(), "Yes")
					&& permit.getPermitProjectSiteInfoEntityTwo().getRooftopACCombinerModelTwo() != null
					&& permit.getPermitProjectSiteInfoEntityTwo().getRooftopACCombinerModelTwo().contains(":")) {

				ACDisconnect acDisconnect = acdRepo.findById(Long.valueOf(
						permit.getPermitProjectSiteInfoEntityTwo().getRooftopACCombinerModelTwo().split(":")[0]))
						.orElse(new ACDisconnect());

				if (checkValueTypes.NotEquals(acDisconnect.getDropdownOption(), "")) {
					bos.put(groundLEvel+"AC Disconnect #2 Model", checkValueTypes.convert(acDisconnect.getDropdownOption()));
				} else {
					bos.put(groundLEvel+"AC Disconnect #2 Model", checkValueTypes.convert(acDisconnect.getModel()));
				}
				if (checkValueTypes.isLongPositive(permit.getPermitProjectSiteInfoEntityTwo().getAcDisconnectThree())) {
					ACDisconnect acDisconnectThree = acdRepo.findById(Long.valueOf(permit.getPermitProjectSiteInfoEntityTwo().getAcDisconnectThree()))
							.orElse(null);
					if (acDisconnectThree != null && checkValueTypes.NotEquals(acDisconnectThree.getDropdownOption(), "")) {
						bos.put(groundLEvel+"AC Disconnect #3 Model", checkValueTypes.convert(acDisconnectThree.getDropdownOption()));
					}else if(acDisconnectThree != null) {
						bos.put(groundLEvel+"AC Disconnect #3 Model", checkValueTypes.convert(acDisconnectThree.getModel()));
					}else {
						bos.put(groundLEvel+"AC Disconnect #3 Model", "");
					}

					if (checkValueTypes.isLongPositive(permit.getPermitProjectSiteInfoEntityTwo().getAcDisconnectFour())) {
						ACDisconnect acDisconnectFour = acdRepo.findById(Long.valueOf(permit.getPermitProjectSiteInfoEntityTwo().getAcDisconnectFour()))
								.orElse(null);
						if (acDisconnectFour != null && checkValueTypes.NotEquals(acDisconnectFour.getDropdownOption(), "")) {
							bos.put(groundLEvel+"AC Disconnect #4 Model", checkValueTypes.convert(acDisconnectFour.getDropdownOption()));
						}else if(acDisconnectFour != null) {
							bos.put(groundLEvel+"AC Disconnect #4 Model", checkValueTypes.convert(acDisconnectFour.getModel()));
						}else {
							bos.put(groundLEvel+"AC Disconnect #4 Model", "");
						}
					}
				}

			} else {
				bos.put(groundLEvel+"AC Disconnect #2 Model", "");
			}
			if (permit.getPermitProjectSiteInfoEntityTwo().getGroundLevelACCombinerBoxModel() != null && checkValueTypes
					.isLongPositive(permit.getPermitProjectSiteInfoEntityTwo().getGroundLevelACCombinerBoxModel())) {

				ACCombinerSLC aCCombiner = accRepo
						.findById(permit.getPermitProjectSiteInfoEntityTwo().getGroundLevelACCombinerBoxModel())
						.orElse(new ACCombinerSLC());
				if (checkValueTypes.NotEquals(aCCombiner.getDropdownOption(), "")) {
					bos.put("AC Combiner Panel", checkValueTypes.convert(aCCombiner.getDropdownOption()));
				} else {
					bos.put("AC Combiner Panel", checkValueTypes.convert(aCCombiner.getModel()));
				}

			} else {
				bos.put("AC Combiner Panel", "");
			}
			bos.put("Location five",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getLocationFive()));
			bos.put("The Point of Connection (POC) Type Will Be...",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getSolarLocation()));
			if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getSolarLocation(), "Other")) {
				bos.put("Other The Point of Connection (POC) Type Will Be...",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getSolarLocationOther()));
			} else {
				bos.put("Other The Point of Connection (POC) Type Will Be...", "");
			}

			bos.put("The Point Of The Connection Will Be at",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getThepontOfTheC()));
			if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getThepontOfTheC(), "Other")) {
				bos.put("Other The Point of the Connection will be ",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getThepontOfTheCOther()));
			} else {
				bos.put("Other The Point of the Connection will be ", "");
			}

			bos.put("Upload Comments",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getUploadComments()));

			if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getThepontOfTheC(), "Sub Panel")) {
				bos.put("Subpanel is...",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getConnectionPoint()));
				bos.put("Sub Panel Bus Rating",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getSubPanelBusRating()));
				if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getSubPanelBusRating(),
						"Other")) {
					bos.put("Sub Panel Bus Rating Other", checkValueTypes
							.convert(permit.getPermitProjectSiteInfoEntityTwo().getSubPanelBusRatingOther()));
				} else {
					bos.put("Sub Panel Bus Rating Other", "");
				}
				bos.put("Sub Panel Main Breaker Rating", checkValueTypes.convert(
						permit.getPermitProjectSiteInfoEntityTwo().getIfApplicableSubPanelMainBreakerRating()));
				bos.put("Sub Panel Breaker (OCPD) at Main Service",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getSubPanelBreakerOCPD()));
				if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getSubPanelBreakerOCPD(),
						"Other")) {
					bos.put("Sub Panel Breaker (OCPD) at Main Service Other", checkValueTypes
							.convert(permit.getPermitProjectSiteInfoEntityTwo().getSubPanelBreakerOCPDOther()));
				} else {
					bos.put("Sub Panel Breaker (OCPD) at Main Service Other", "");
				}
				if (checkValueTypes
						.isNumericNotZero(permit.getPermitProjectSiteInfoEntityTwo().getProposedSubPanel())) {

					ProposedSubPanel proposedSubPanel = proposedSubPanelRepo
							.findById(Long.valueOf(permit.getPermitProjectSiteInfoEntityTwo().getProposedSubPanel()))
							.orElse(new ProposedSubPanel());
					bos.put("ProposedSubPanel", checkValueTypes
							.convert(proposedSubPanel.getManufacturer() + " " + proposedSubPanel.getModel()));

				} else {
					bos.put("proposedSubPanel", "");
				}

			} else {
				bos.put("Subpanel is...", "");
				bos.put("Sub Panel Bus Rating", "");
				bos.put("Sub Panel Bus Rating Other", "");
				bos.put("Sub Panel Main Breaker Rating", "");
				bos.put("Sub Panel Breaker (OCPD) at Main Service", "");
				bos.put("Sub Panel Breaker (OCPD) at Main Service Other", "");
				bos.put("Proposed Sub Panel ", "");
			}

			if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getMainPanelUpgrade(), true)) {
				bos.put("A Proposed Main Panel Upgrade will be a part of this Project", "true");
				if (checkValueTypes.NotEquals(permit.getPermitProjectSiteInfoEntityTwo().getProposedMainPanMan(), "")) {

					if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getProposedMainPanMan(),
							"Siemens W/Alt Energy Input")) {
						bos.put("STANDARD MSP", "");
						bos.put("ALT ENERGY with SEPARATE INPUT MSP", checkValueTypes
								.convert(permit.getPermitProjectSiteInfoEntityTwo().getProposedMainPanMan()));
					} else {
						bos.put("STANDARD MSP", checkValueTypes
								.convert(permit.getPermitProjectSiteInfoEntityTwo().getProposedMainPanMan()));
						bos.put("ALT ENERGY with SEPARATE INPUT MSP", "");
					}

				} else {
					bos.put("ALT ENERGY with SEPARATE INPUT MSP", "");
					bos.put("STANDARD MSP", "");
				}
			} else {
				bos.put("A Proposed Main Panel Upgrade will be a part of this Project", "false");
				if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getExistingMainPanelManufac(),
						"Other")) {
					bos.put("STANDARD MSP", checkValueTypes
							.convert(permit.getPermitProjectSiteInfoEntityTwo().getExistingMainPanelManufacOther()));
					bos.put("ALT ENERGY with SEPARATE INPUT MSP", "");
				} else if (checkValueTypes
						.NotEquals(permit.getPermitProjectSiteInfoEntityTwo().getExistingMainPanelManufac(), "")) {
					if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getExistingMainPanelManufac(),
							"Siemens W/Alt Energy Input")) {
						bos.put("STANDARD MSP", "");
						bos.put("ALT ENERGY with SEPARATE INPUT MSP", checkValueTypes
								.convert(permit.getPermitProjectSiteInfoEntityTwo().getExistingMainPanelManufac()));
					} else {
						bos.put("STANDARD MSP", checkValueTypes
								.convert(permit.getPermitProjectSiteInfoEntityTwo().getExistingMainPanelManufac()));
						bos.put("ALT ENERGY with SEPARATE INPUT MSP", "");
					}
				} else {
					bos.put("STANDARD MSP", "");
					bos.put("ALT ENERGY with SEPARATE INPUT MSP", "");
				}
			}
			bos.put("Main (Service Entrance) Panel Bus Rating",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getPanelBusRating()));
			if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getPanelBusRating(), "Other")) {
				bos.put("Main (Service Entrance) Panel Bus Rating Other",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getPanelBusRatingOther()));
			} else {
				bos.put("Main (Service Entrance) Panel Bus Rating Other", "");
			}

			bos.put("Main (Service Entrance) Panel Main Breaker Rating",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getPanelMainBreakerRating()));
			if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getPanelMainBreakerRating(),
					"Other")) {
				bos.put("Main (Service Entrance) Panel Main Breaker Rating Other", checkValueTypes
						.convert(permit.getPermitProjectSiteInfoEntityTwo().getPanelMainBreakerRatingOther()));
			} else {
				bos.put("Main (Service Entrance) Panel Main Breaker Rating Other", "");
			}

			bos.put("Click HERE if MSP has no Branch Circuit Breakers (Dedicated Main to Feed Sub Panel)",
					Boolean.TRUE.equals(permit.getPermitProjectSiteInfoEntityTwo().getMsphasNoBranchCircuitBreakers())
							? "Yes"
							: "No");
			bos.put("The Main Breaker is Located at the end of the Bus Bar",
					Boolean.TRUE.equals(permit.getPermitProjectSiteInfoEntityTwo().getMainBreakerLocatedEndBusBar())
							? "Yes"
							: "No");
			bos.put("The Main Breaker De-rate will be",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getDeratingthisPanelString()));
			if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getSolarLocation(),
					"Back-fed Breaker")) {
				bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getSolarInterconnection()));
				if (permit.getPermitArraysEntity().getDeviceToIncorporate() != null
						&& permit.getPermitProjectSiteInfoEntityTwo().getCombiningACCircuits() != null
						&& (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getCombiningACCircuits(),
								"An Existing Main or Sub Panel with More Than One Back-Fed Breaker")
								|| checkValueTypes.Equals(
										permit.getPermitProjectSiteInfoEntityTwo().getCombiningACCircuits(),
										"A Proposed Main or Sub Panel with More Than One Back-Fed Breaker"))) {

					if (permit.getPermitArraysEntity().getDeviceToIncorporate() != null && ((getProjectDetailsUtils.isStringOrOptimizerProject(permit.getPermitArraysEntity().getDeviceToIncorporate())
							&& permit.getPermitArraysEntity().getSecondInverterModel() != null)
							|| (getProjectDetailsUtils.isMicroOrAcModuleProject(permit.getPermitArraysEntity().getDeviceToIncorporate())
									&& permit.getPermitArraysEntity().getOcpdTwo() != null))) {
						bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #2", checkValueTypes
								.convert(permit.getPermitProjectSiteInfoEntityTwo().getSecondSolarInterconnection()));
					} else {
						bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #2", "");
					}
					if (permit.getPermitArraysEntity().getDeviceToIncorporate() != null && ((getProjectDetailsUtils.isStringOrOptimizerProject(permit.getPermitArraysEntity().getDeviceToIncorporate())
							&& permit.getPermitArraysEntity().getThirdInverterModel() != null)
							|| (getProjectDetailsUtils.isMicroOrAcModuleProject(permit.getPermitArraysEntity().getDeviceToIncorporate())
									&& permit.getPermitArraysEntity().getOcpdThree() != null))) {
						bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #3", checkValueTypes
								.convert(permit.getPermitProjectSiteInfoEntityTwo().getThirdSolarInterconnection()));

					} else {
						bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #3", "");
					}
					if (permit.getPermitArraysEntity().getDeviceToIncorporate() != null && ((getProjectDetailsUtils.isStringOrOptimizerProject(permit.getPermitArraysEntity().getDeviceToIncorporate())
							&& permit.getPermitArraysEntity().getFourthInverterModel() != null)
							|| (getProjectDetailsUtils.isMicroOrAcModuleProject(permit.getPermitArraysEntity().getDeviceToIncorporate())
									&& permit.getPermitArraysEntity().getOcpdFour() != null))) {

						bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #4", checkValueTypes
								.convert(permit.getPermitProjectSiteInfoEntityTwo().getFourthSolarInterconnection()));
					} else {
						bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #4", "");
					}
					if (permit.getPermitArraysEntity().getDeviceToIncorporate() != null && ((getProjectDetailsUtils.isStringOrOptimizerProject(permit.getPermitArraysEntity().getDeviceToIncorporate())
							&& permit.getPermitArraysEntity().getFifthInverterModel() != null)
							|| (getProjectDetailsUtils.isMicroOrAcModuleProject(permit.getPermitArraysEntity().getDeviceToIncorporate())
									&& permit.getPermitArraysEntity().getOcpdFive() != null))) {

						bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #5", checkValueTypes
								.convert(permit.getPermitProjectSiteInfoEntityTwo().getFifthSolarInterconnection()));
					} else {
						bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #5", "");
					}

				} else {
					bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #2", "");
					bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #3", "");
					bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #4", "");
					bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #5", "");
				}

			} else {
				bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating", "");
				bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #2", "");
				bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #3", "");
				bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #4", "");
				bos.put("Back-Feed Solar Interconnecting Circuit Breaker/OCPD Rating #5", "");
			}

			bos.put("A Performance Monitoring and Reporting System will be Used",
					Boolean.TRUE.equals(permit.getPermitProjectSiteInfoEntityTwo().getUsedRevenue()) ? "Yes" : "No");

			if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getUsedRevenue(), true)) {

				if (checkValueTypes.isNumericNotZero(permit.getPermitProjectSiteInfoEntityTwo().getLeasePPAMeter())) {

					LeasePPAMeter leasePPAMeter = leasePPAMeterRepo
							.findById(Long.valueOf(permit.getPermitProjectSiteInfoEntityTwo().getLeasePPAMeter()))
							.orElse(new LeasePPAMeter());
					bos.put("Revenue or Performance Monitoring Meter",
							checkValueTypes.convert(leasePPAMeter.getManufacturer() + " " + leasePPAMeter.getModel()));

				} else {
					bos.put("Revenue or Performance Monitoring Meter", "");
				}

			} else {
				bos.put("Revenue or Performance Monitoring Meter", "");
			}

			if (checkValueTypes.Equals(permit.getPermitArraysEntity().getGroundMounted(), true)) {
				bos.put("North to South Finished Grade Slope at Array in Degrees?",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getNorthToShouthFin()));
				bos.put("North to South Finished Grade Slope at Array in Degrees Other", checkValueTypes
						.convert(permit.getPermitProjectSiteInfoEntityTwo().getNorthToShouthFinOther() + ""));
				bos.put("Height of South (Lower) Horz. Pipe",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getHeightOfSouth() + ""));
			}

			bos.put("Choose Sizing for Conductors between SubPanel and Main Service Panel",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getSubPanelConductorSizing()));
			bos.put("Conductor Size",
					checkValueTypes.convert(checkValueTypes
							.Equals(permit.getPermitProjectSiteInfoEntityTwo().getSubPanelConductorSize(), "Other")
									? permit.getPermitProjectSiteInfoEntityTwo().getSubPanelConductorSizeOther()
									: permit.getPermitProjectSiteInfoEntityTwo().getSubPanelConductorSize()));
			bos.put("Conductor Size Note",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getSubPanelConductorSizeNote()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bos;

	}

}
