package com.PlayGroundAdv.Solar.service.libraries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.MountingTypeEntity;
import com.PlayGroundAdv.Solar.entity.RackingAllowedRoofMaterial;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ComponentTypeRequest;
import com.PlayGroundAdv.Solar.model.ConfirmComponentRequest;
import com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult;
import com.PlayGroundAdv.Solar.model.RailRackingModel;
import com.PlayGroundAdv.Solar.model.VerificationModel;
import com.PlayGroundAdv.Solar.repositories.MountTypeRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ConvertersRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofAttachmentsRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.mailing.MailingService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class LibariesManagementService {

	@PersistenceContext
	EntityManager em;
	
	final HistoryActivityService historyActivityService;
	final MailingService mailingService;
	final CheckValueTypesService checkValueTypesService;
	final MountTypeRepository mountingTypeRepo;
	final InverterRepository inverterRepo;
	final ModuleRepository moduleRepo;
	final ConvertersRepository converterRepo;
	final RailRackingRepository railRackingRepo;
	final RoofAttachmentsRepository roofAttRepo;
	final DcCombinerDiscoRepository dcCombinerDiscoRepo;
	final ACDisconnectRepository acDiscoRepo;
	final AcCombinerSLCRepository acComSLCRepo;
	final AuthenticationRepository userRepo;

	public LibariesManagementService(HistoryActivityService historyActivityService, MailingService mailingService,
			CheckValueTypesService checkValueTypesService, MountTypeRepository mountingTypeRepo,
			InverterRepository inverterRepo, ModuleRepository moduleRepo, ConvertersRepository converterRepo,
			RailRackingRepository railRackingRepo, RoofAttachmentsRepository roofAttRepo,
			DcCombinerDiscoRepository dcCombinerDiscoRepo, ACDisconnectRepository acDiscoRepo,
			AcCombinerSLCRepository acComSLCRepo, AuthenticationRepository userRepo) {
		super();
		this.historyActivityService = historyActivityService;
		this.mailingService = mailingService;
		this.checkValueTypesService = checkValueTypesService;
		this.mountingTypeRepo = mountingTypeRepo;
		this.inverterRepo = inverterRepo;
		this.moduleRepo = moduleRepo;
		this.converterRepo = converterRepo;
		this.railRackingRepo = railRackingRepo;
		this.roofAttRepo = roofAttRepo;
		this.dcCombinerDiscoRepo = dcCombinerDiscoRepo;
		this.acDiscoRepo = acDiscoRepo;
		this.acComSLCRepo = acComSLCRepo;
		this.userRepo = userRepo;
	}

	public List<LibrariesManagementModelResult> getAllNewComponent() {
		List<LibrariesManagementModelResult> composent = new ArrayList<>();
		try {

			// Inverter
			List<LibrariesManagementModelResult> invcomposent = inverterRepo.getLibModel(false);

			if (invcomposent != null && !invcomposent.isEmpty()) {
				for (LibrariesManagementModelResult comp : invcomposent) {
					if (comp != null) {
						comp.setType("Inverter");
					}
				}
				composent.addAll(invcomposent);
			}

			// Module
			List<LibrariesManagementModelResult> modcomposent = moduleRepo.getLibModel(false);

			if (modcomposent != null && !modcomposent.isEmpty()) {
				for (LibrariesManagementModelResult comp : modcomposent) {
					if (comp != null) {
						comp.setType("Module");
					}
				}
				composent.addAll(modcomposent);
			}

			// Optimizer
			List<LibrariesManagementModelResult> optcomposent = converterRepo.getLibModel(false);

			if (optcomposent != null && !optcomposent.isEmpty()) {
				for (LibrariesManagementModelResult comp : optcomposent) {
					if (comp != null) {
						comp.setType("DC - DC System Converter");
					}
				}
				composent.addAll(optcomposent);
			}

			// Rail Racking
			List<LibrariesManagementModelResult> rrcomposent = railRackingRepo.getLibModel(false);

			if (rrcomposent != null && !rrcomposent.isEmpty()) {
				for (LibrariesManagementModelResult comp : rrcomposent) {
					if (comp != null) {
						comp.setType("Rail / Racking");
					}
				}
				composent.addAll(rrcomposent);
			}

			// Roof Attachement
			List<LibrariesManagementModelResult> rfcomposent = roofAttRepo.getLibModel(false);

			if (rfcomposent != null && !rfcomposent.isEmpty()) {
				for (LibrariesManagementModelResult comp : rfcomposent) {
					if (comp != null) {
						comp.setType("Rail to Roof Connection");
					}
				}
				composent.addAll(rfcomposent);
			}

			// DC Combiner Disconnect
			List<LibrariesManagementModelResult> cdcomposent = dcCombinerDiscoRepo.getLibModel(false);

			if (cdcomposent != null && !cdcomposent.isEmpty()) {
				for (LibrariesManagementModelResult comp : cdcomposent) {

					if (comp != null) {
						if (checkValueTypesService.NotEquals(comp.getType(), "J Box")) {
							comp.setType("DC Combiner / Disconnect");
						} else {
							comp.setType("Junction Box");
						}
					}
				}
				composent.addAll(cdcomposent);
			}

			// AC Disconnect
			List<LibrariesManagementModelResult> adcomposent = acDiscoRepo.getLibModel(false);

			if (adcomposent != null && !adcomposent.isEmpty()) {
				for (LibrariesManagementModelResult comp : adcomposent) {

					if (comp != null) {
						if (checkValueTypesService.Equals(comp.getType(), "COMBINER")) {
							comp.setType("AC Combiner");
						} else if (checkValueTypesService.Equals(comp.getType(), "COMBINING DISCONNECT")) {
							comp.setType("AC Combining Disconnect");
						} else {
							comp.setType("AC Disconnect");
						}
					}

				}
				composent.addAll(adcomposent);
			}

			// AC Combiner SLC
			List<LibrariesManagementModelResult> slccomposent = acComSLCRepo.getLibModel(false);
			if (slccomposent != null && !slccomposent.isEmpty()) {
				for (LibrariesManagementModelResult comp : slccomposent) {
					if (comp != null) {
						comp.setType("AC Combiner / SLC");
					}
				}
				composent.addAll(slccomposent);
			}

			return composent;

		} catch (Exception e) {
			e.printStackTrace();
			return composent;
		}

	}

	public String confirmComponent(ConfirmComponentRequest editComponent, Long id, String type, Long idUser,
			String manufacturerComp, String modelComp) {
		Boolean moduleorInverter = type != null && (type.equals("Module") || type.equals("Inverter"));
		RailRacking railRacking = null;
		DCOptimizerEntity dcOptimizer = null;
		RoofAttachmentsEntity roofAttachmentsEntity = null;
		DCCombinerDisconnectEntity dCCombinerDisconnectEntity = null;
		ACDisconnect aCDisconnect = null;
		Inverters inverter = new Inverters();
		Cmodulev2 module = new Cmodulev2();
		ACCombinerSLC aCCombinerSLC = null;
		String retour = "";
		try {

			int index = 0;
			if (Boolean.FALSE.equals(moduleorInverter)
					&& (checkValueTypesService.NotEquals(manufacturerComp, editComponent.getManufacturer())
							|| checkValueTypesService.NotEquals(modelComp, editComponent.getModel()))) {
				List<RailRacking> rr = railRackingRepo.findByManufacturerAndModel(editComponent.getManufacturer(),
						editComponent.getModel());

				List<DCOptimizerEntity> dcOp = converterRepo
						.findAllByManufacturerAndModel(editComponent.getManufacturer(), editComponent.getModel());

				List<RoofAttachmentsEntity> roofAtt = roofAttRepo
						.findByManufacturerAndModel(editComponent.getManufacturer(), editComponent.getModel());

				List<DCCombinerDisconnectEntity> dcComDisc = dcCombinerDiscoRepo
						.findAllByManufacturerAndModel(editComponent.getManufacturer(), editComponent.getModel());

				List<ACDisconnect> acDisc = acDiscoRepo.findByManufacturerAndModel(editComponent.getManufacturer(),
						editComponent.getModel());

				List<ACCombinerSLC> acComSLC = acComSLCRepo.findByManufacturerAndModel(editComponent.getManufacturer(),
						editComponent.getModel());

				if ((rr != null && !rr.isEmpty()) || (dcOp != null && !dcOp.isEmpty())
						|| (roofAtt != null && !roofAtt.isEmpty()) || (dcComDisc != null && !dcComDisc.isEmpty())
						|| (acDisc != null && !acDisc.isEmpty()) || (acComSLC != null && !acComSLC.isEmpty()))
					index = 1;

			} else if (Boolean.TRUE.equals(moduleorInverter)
					&& (checkValueTypesService.NotEquals(manufacturerComp, editComponent.getMake())
							|| checkValueTypesService.NotEquals(modelComp, editComponent.getModel()))) {
				List<Inverters> inv = inverterRepo.findAllByMakeAndModel(editComponent.getMake(),
						editComponent.getModel());
				List<Cmodulev2> mo = moduleRepo.findByMakeAndModel(editComponent.getMake(), editComponent.getModel());

				if ((inv != null && !inv.isEmpty()) || (mo != null && !mo.isEmpty()))
					index = 1;
			}
			if (index == 0 && type != null) {
				if (type.equals("Rail / Racking")) {
					railRacking = railRackingRepo.findById(id).orElse(null);
					if (railRacking != null) {
						railRacking.setHasSuperUserEdit(true);
						railRacking.setManufacturer(editComponent.getManufacturer());
						railRacking.setModel(editComponent.getModel());
						railRacking.setWeight(editComponent.getWeight());
						railRackingRepo.save(railRacking);

						List<MountingTypeEntity> mountingType = mountingTypeRepo.findByIdRailId(railRacking.getId());
						if (mountingType != null && !mountingType.isEmpty()) {
							for (MountingTypeEntity typeM : mountingType) {
								mountingTypeRepo.delete(typeM);
							}
						}
						for (String typeMounting : editComponent.getMountType()) {
							MountingTypeEntity mount = new MountingTypeEntity();
							mount.setIdRail(railRacking);
							mount.setMountingType(typeMounting);
							mountingTypeRepo.save(mount);
						}
					}
				} else if (type.equals("DC - DC System Converter")) {
					dcOptimizer = converterRepo.findById(id).orElse(null);
					if (dcOptimizer != null) {
						dcOptimizer.setHasSuperUserEdit(true);
						dcOptimizer.setManufacturer(editComponent.getManufacturer());
						dcOptimizer.setModel(editComponent.getModel());
						dcOptimizer.setRatedOutputIsc(editComponent.getRatedOutputIsc());
						dcOptimizer.setWeight(editComponent.getWeight());
						dcOptimizer.setMaxInputVoltage(editComponent.getMaxInputVoltage());
						dcOptimizer.setMaxSeriesFuseRating(editComponent.getMaxSeriesFuseRating());
						dcOptimizer.setPhase(editComponent.getPhase());
						dcOptimizer.setPvModulePower(editComponent.getPvModulePower());
						dcOptimizer.setMinString(editComponent.getMinString());
						dcOptimizer.setMaxString(editComponent.getMaxString());
						dcOptimizer.setMaxPowerString(editComponent.getMaxPowerString());
						dcOptimizer.setMaxOutputVoltage(editComponent.getMaxOutputVoltage());
						converterRepo.save(dcOptimizer);
					}
				} else if (type.equals("Rail to Roof Connection")) {
					roofAttachmentsEntity = roofAttRepo.findById(id).orElse(null);
					if (roofAttachmentsEntity != null) {
						roofAttachmentsEntity.setHasSuperUserEdit(true);
						roofAttachmentsEntity.setManufacturer(editComponent.getManufacturer());
						roofAttachmentsEntity.setModel(editComponent.getModel());
						roofAttachmentsEntity.setRoofType(editComponent.getRoofType());
						roofAttachmentsEntity.setWeight(editComponent.getWeight());
						roofAttachmentsEntity.setIntegrated(editComponent.getIntegrated());
						roofAttachmentsEntity.setNumberOfRoof(editComponent.getNumberOfRoof());
						roofAttachmentsEntity.setUtilizeS100(editComponent.getUtilizeS100());
						roofAttRepo.save(roofAttachmentsEntity);
					}
				} else if (type.equals("DC Combiner / Disconnect") || type.equals("Junction Box")) {
					dCCombinerDisconnectEntity = dcCombinerDiscoRepo.findById(id).orElse(null);
					if (dCCombinerDisconnectEntity != null) {
						dCCombinerDisconnectEntity.setHasSuperUserEdit(true);
						dCCombinerDisconnectEntity.setManufacturer(editComponent.getManufacturer());
						dCCombinerDisconnectEntity.setModel(editComponent.getModel());
						dCCombinerDisconnectEntity.setOcpd(editComponent.getOcpd());
						dCCombinerDisconnectEntity.setMaxInput(editComponent.getMaxInput());
						dCCombinerDisconnectEntity.setMaxContiOutputCurrent(editComponent.getMaxContiOutputCurrent());
						dCCombinerDisconnectEntity.setMaxOutputCurrent(editComponent.getMaxOutputCurrent());
						dCCombinerDisconnectEntity.setNemaRating(editComponent.getNemaRating());
						dCCombinerDisconnectEntity.setWeight(editComponent.getWeight());
						dCCombinerDisconnectEntity.setDropdownOption(editComponent.getDropdownOption());
						dCCombinerDisconnectEntity.setTypeDc(editComponent.getTypeDc());
						dcCombinerDiscoRepo.save(dCCombinerDisconnectEntity);
					}
				} else if (type.equals("AC Disconnect") || type.equals("AC Combining Disconnect")) {
					aCDisconnect = acDiscoRepo.findById(id).orElse(null);
					if (aCDisconnect != null) {
						aCDisconnect.setHasSuperUserEdit(true);
						aCDisconnect.setManufacturer(editComponent.getManufacturer());
						aCDisconnect.setModel(editComponent.getModel());
						aCDisconnect.setDisconnectDeviceType(editComponent.getDisconnectDeviceType());
						aCDisconnect.setDropdownOption(editComponent.getDropdownOption());
						aCDisconnect.setMaxInput(editComponent.getMaxInput());
						aCDisconnect.setNemaRating(editComponent.getNemaRating());
						aCDisconnect.setNumberOfPoles(editComponent.getNumberOfPoles());
						aCDisconnect.setQtyOfFuse(editComponent.getQtyOfFuse());
						aCDisconnect.setRatedCurrent(editComponent.getRatedCurrent());
						aCDisconnect.setRatedOperationalVoltage(editComponent.getRatedOperationalVoltage());
						aCDisconnect.setType(editComponent.getType());
						acDiscoRepo.save(aCDisconnect);
					}
				} else if (type.equals("AC Combiner / SLC")) {
					aCCombinerSLC = acComSLCRepo.findById(id).orElse(null);
					if (aCCombinerSLC != null) {
						aCCombinerSLC.setHasSuperUserEdit(true);
						aCCombinerSLC.setManufacturer(editComponent.getManufacturer());
						aCCombinerSLC.setModel(editComponent.getModel());
						aCCombinerSLC.setCombinerDeviceType(editComponent.getDisconnectDeviceType());
						aCCombinerSLC.setDropdownOption(editComponent.getDropdownOption());
						aCCombinerSLC.setMaxInput(editComponent.getMaxInput());
						aCCombinerSLC.setNemaRating(editComponent.getNemaRating());
						aCCombinerSLC.setNumberOfPoles(editComponent.getNumberOfPoles());
						aCCombinerSLC.setRatedCurrent(editComponent.getRatedCurrent());
						aCCombinerSLC.setRatedOperationalVoltage(editComponent.getRatedOperationalVoltage());
						aCCombinerSLC.setType(editComponent.getType());
						aCCombinerSLC.setNumberOfSpaces(editComponent.getNumberOfSpaces());
						aCCombinerSLC.setOtherNumberOfSpaces(editComponent.getOtherNumberOfSpaces());
						acComSLCRepo.save(aCCombinerSLC);
					}
				} else if (type.equals("Inverter")) {
					inverter = inverterRepo.findById(id).orElse(null);
					if (inverter != null) {
						inverter.setMake(editComponent.getMake());
						inverter.setModel(editComponent.getModel());
						inverter.setVac(editComponent.getVac());
						inverter.setPaco(editComponent.getPaco());
						inverter.setPdco(editComponent.getPdco());
						inverter.setVdco(editComponent.getVdco());
						inverter.setPso(editComponent.getPso());
						inverter.setC0(editComponent.getC0());
						inverter.setC1(editComponent.getC1());
						inverter.setC2(editComponent.getC2());
						inverter.setC3(editComponent.getC3());
						inverter.setPnt(editComponent.getPnt());
						inverter.setVdcmax(editComponent.getVdcmax());
						inverter.setIdcmax(editComponent.getIdcmax());
						inverter.setMpptLow(editComponent.getMpptLow());
						inverter.setMpptHigh(editComponent.getMpptHigh());
						inverter.setPowerRating(editComponent.getPowerRating());
						inverter.setWeightedEfficiency(editComponent.getWeightedEfficiency());
						inverter.setIacmax(editComponent.getIacmax());
						inverter.setWeight(editComponent.getWeight());
						inverter.setMicroInverter(
								checkValueTypesService.Equals(editComponent.getMicroInverter(), "Yes"));
						inverter.setIntegratedDCDisco(
								checkValueTypesService.Equals(editComponent.getIntegratedDCDisco(), "Yes"));
						inverter.setIntegratedACDisco(
								checkValueTypesService.Equals(editComponent.getIntegratedACDisco(), "Yes"));
						// Add Save column optimizer in the edit inverter after CR 523
						inverter.setOptimizer(checkValueTypesService.Equals(editComponent.getOptimizer(), "Yes"));
						// End Save column optimizer in the edit inverter after CR 523
						inverter.setHasSuperUserEdit(true);
						inverterRepo.save(inverter);
					}
				} else if (type.equals("Module")) {
					module = moduleRepo.findById(id).orElse(null);
					if (module != null) {
						module.setMake(editComponent.getMake());
						module.setModel(editComponent.getModel());
						module.setStcRounded(editComponent.getStcRounded());
						module.setTechnology(editComponent.getTechnology());
						module.setVersion(editComponent.getVersion());
						module.setvMpRef(editComponent.getVMpRef());
						module.setvOcRef(editComponent.getVOcRef());
						module.setiScRef(editComponent.getIScRef());
						module.setiMpRef(editComponent.getIMpRef());
						module.setLength(editComponent.getLength());
						module.setWidth(editComponent.getWidth());
						module.setDepth(editComponent.getDepth());
						module.setWeight(editComponent.getWeight());
						module.setGammaR(editComponent.getGammaR());
						module.setAlphaSc(editComponent.getAlphaSc());
						module.setBetaOc(editComponent.getBetaOc());
						module.setaC(editComponent.getAC());
						module.setaRef(editComponent.getARef());
						module.setAdjust(editComponent.getAdjust());
						module.setBipv(editComponent.getBipv());
						module.setiIRef(editComponent.getIIRef());
						module.setiORef(editComponent.getIORef());
						module.setnS(editComponent.getNS());
						module.setPtc(editComponent.getPtc());
						module.setrS(editComponent.getRS());
						module.setrShRef(editComponent.getRShRef());
						module.setStc(editComponent.getStc());
						module.settNoct(editComponent.getTNoct());
						module.setHasSuperUserEdit(true);
						moduleRepo.save(module);
					}
				}

				AuthentificationEntity user = userRepo.findById(idUser).orElse(null);

				if (user != null && user.getRoleEntity().getId() == 3) {

					String userMail = user.getEmail();
					List<AuthentificationEntity> superUsers = new ArrayList<>();
					if (userMail != null && userMail.contains("nuagetechnologies-tn")) {
						superUsers = userRepo.getActivatedSpecial(false, true);
					} else {
						superUsers = userRepo.getActivated(false, true);
					}
					List<String> susers = new ArrayList<String>();
					for (AuthentificationEntity su : superUsers) {
						if (su != null) {
							susers.add(su.getEmail());
						}

					}
					if (Boolean.FALSE.equals(moduleorInverter)) {
						mailingService.sendingMailMultipleRecieversLibraries(susers, "ADV - Confirming Library Updates",
								"Hello,\n\nThe Component " + type + " with the (manufacturer, model) = ("
										+ editComponent.getManufacturer() + ", " + editComponent.getModel()
										+ ") is confirmed by the ADV team member editor " + user.getFirstName() + " "
										+ user.getLastName() + ".\nThank you.\n\nYour ADV Solar Team.");
					} else {
						mailingService.sendingMailMultipleRecieversLibraries(susers, "ADV - Confirming Library Updates",
								"Hello,\n\nThe Component " + type + " with the (manufacturer, model) = ("
										+ editComponent.getMake() + ", " + editComponent.getModel()
										+ ") is confirmed by the ADV team member editor " + user.getFirstName() + " "
										+ user.getLastName() + ".\nThank you.\n\nYour ADV Solar Team.");
					}

				}
				retour = "Success";

			} else {
				retour = "Exist";
			}
			return retour;

		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";

		}
	}

	public ComponentTypeRequest getComponentInfo(Long id, String type) {

		ComponentTypeRequest componentInfo = new ComponentTypeRequest();
		try {

			switch (type) {
			case "Rail / Racking":
				componentInfo.setRailRacking(getRaickingDto(railRackingRepo.findById(id).orElse(null)));
				break;
			case "DC - DC System Converter":
				componentInfo.setDCOptimizerEntity(converterRepo.findById(id).orElse(null));
				break;
			case "Rail to Roof Connection":
				componentInfo.setRoofAttachmentsEntity(roofAttRepo.findById(id).orElse(null));
				break;
			case "DC Combiner / Disconnect":
				componentInfo.setDCCombinerDisconnectEntity(dcCombinerDiscoRepo.findById(id).orElse(null));
				break;
			case "Junction Box":
				componentInfo.setDCCombinerDisconnectEntity(dcCombinerDiscoRepo.findById(id).orElse(null));
				break;
			case "AC Combiner":
				componentInfo.setACDisconnect(acDiscoRepo.findById(id).orElse(null));
				break;
			case "AC Combining Disconnect":
				componentInfo.setACDisconnect(acDiscoRepo.findById(id).orElse(null));
				break;
			case "AC Disconnect":
				componentInfo.setACDisconnect(acDiscoRepo.findById(id).orElse(null));
				break;
			case "Inverter":
				componentInfo.setInverter(inverterRepo.findById(id).orElse(null));
				break;
			case "Module":
				componentInfo.setModule(moduleRepo.findById(id).orElse(null));
				break;
			case "AC Combiner / SLC":
				componentInfo.setACCombinerSLC(acComSLCRepo.findById(id).orElse(null));
				break;
			}

			return componentInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return componentInfo;

		}
	}
	
	private RailRackingModel getRaickingDto(RailRacking racking) {
		
		try {
			RailRackingModel railRackingDto = new RailRackingModel();
			
			if (racking != null) {
				railRackingDto.setId(racking.getId());
				railRackingDto.setManufacturer(racking.getManufacturer());
				railRackingDto.setWeight(racking.getWeight());
				railRackingDto.setIsDeleted(racking.getIsDeleted());
				railRackingDto.setLastUpdate(racking.getLastUpdate());
				railRackingDto.setHasCorrectionRequest(racking.getHasCorrectionRequest());
				railRackingDto.setEroneousContent(racking.getEroneousContent());
				railRackingDto.setEroneousContentOther(racking.getEroneousContentOther());
				railRackingDto.setEroneousDescription(racking.getEroneousDescription());
				railRackingDto.setManufacturerMappingValue(racking.getManufacturerMappingValue());
				railRackingDto.setModelMappingValue(racking.getModelMappingValue());
				railRackingDto.setPvRailType(racking.getPvRailType() != null ? racking.getPvRailType().getId() : null);
				railRackingDto.setPvRailSpliceType(racking.getPvRailSpliceType() != null ? racking.getPvRailSpliceType().getId() : null);
				railRackingDto.setMidClamp(racking.getMidClamp() != null ? racking.getMidClamp().getId() : null);
				railRackingDto.setEndClamp(racking.getEndClamp() != null ? racking.getEndClamp().getId() : null);
				railRackingDto.setIntegratedStanchion(racking.getIntegratedStanchion());
				railRackingDto.setStanchionMfg(racking.getStanchionMfg());
				railRackingDto.setStanchionMfgMappingValue(racking.getStanchionMfgMappingValue());
				railRackingDto.setStanchionModel(racking.getStanchionModel());
				railRackingDto.setStanchionModelMappingValue(racking.getStanchionModelMappingValue());
				railRackingDto.setIntegratedFlashing(racking.getIntegratedFlashing());
				railRackingDto.setOwner(racking.getIdOwner().getFirstName() + " " + racking.getIdOwner().getLastName());
				railRackingDto.setModel(racking.getModel());
				railRackingDto.setIdOwner(racking.getIdOwner().getId() + "");
				List<String> types = new ArrayList<>();
	    		for(MountingTypeEntity type :racking.getMountType()) {
					types.add(type.getMountingType());
		    	}
	    		List<Long> allowedRoofMaterial = new ArrayList<>();
	    		for(RackingAllowedRoofMaterial type :racking.getRackingAllowedRoofMaterial()) {
	    			allowedRoofMaterial.add(type.getRoofMaterial().getId());
		    	}
	    		railRackingDto.setMountType(types);
	    		railRackingDto.setAllowedRoofMaterial(allowedRoofMaterial);
			}

    		return railRackingDto;
		} catch (Exception e) {
			e.printStackTrace();
			return new RailRackingModel();
		}
	}

	public Boolean verifyComponent(VerificationModel params) {
    	
    	try {
    		if (params != null && params.getEquipmentId() != null) {
    			AuthentificationEntity user = userRepo.findById(params.userId).orElseThrow(
    					() -> new ResourceNotFoundException("User not found for this id :" +params.userId));
    			em.createQuery("update "+params.getLibrary()+" u set u.isVerified = :p1, u.verifiedBy = :p2, u.dateVerification = :p3 where u.id= :p4")
					.setParameter("p1", true)
					.setParameter("p2", user)
					.setParameter("p3", new Date())
					.setParameter("p4", params.getEquipmentId())
					.executeUpdate();
    		}
    		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    	
	}

}
