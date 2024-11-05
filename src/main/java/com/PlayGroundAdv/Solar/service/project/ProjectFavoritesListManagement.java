package com.PlayGroundAdv.Solar.service.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.MountingTypeEntity;
import com.PlayGroundAdv.Solar.entity.RackingAllowedRoofMaterial;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.model.ListManagementFavoritesModel;
import com.PlayGroundAdv.Solar.model.RailRackingModel;
import com.PlayGroundAdv.Solar.model.libraries.FavoriteListDto;
import com.PlayGroundAdv.Solar.repositories.VoltageConfigurationRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ATSRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ConvertersRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.FlashingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.GeneratorRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.LeasePPAMeterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ProposedSubPanelRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofAttachmentsRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.TiltLegsRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ProjectFavoritesListManagement {

	final DcCombinerDiscoRepository dcComDiscoRepo;
	final ConvertersRepository dcOptimizerRepo;
	final RailRackingRepository railRackingRepo;
	final RoofAttachmentsRepository roofAttachmentsRepo;
	final ACDisconnectRepository aCDisconnectRepo;
	final AcCombinerSLCRepository acCombinerSLCRepo;
	final InverterRepository inverterRepo;
	final ModuleRepository moduleRepo;
	final FlashingRepository flashingRepo;
	final LeasePPAMeterRepository leasePPAMeterRepo;
	final BatteryRepository batteryRepo;
	final TiltLegsRepository tiltLegsRepo;
	final ProposedSubPanelRepository proposedSubPanelRepo;
	final VoltageConfigurationRepository voltageConfRepo;
	final ATSRepository atsRepo;
	final GeneratorRepository generatorRepo;
	final ModelMapper modelMapper;
	final CheckValueTypesService checkValue;
	
	public ProjectFavoritesListManagement(DcCombinerDiscoRepository dcComDiscoRepo,
			ConvertersRepository dcOptimizerRepo, RailRackingRepository railRackingRepo,
			RoofAttachmentsRepository roofAttachmentsRepo, ACDisconnectRepository aCDisconnectRepo,
			AcCombinerSLCRepository acCombinerSLCRepo, InverterRepository inverterRepo, ModuleRepository moduleRepo,
			FlashingRepository flashingRepo, LeasePPAMeterRepository leasePPAMeterRepo, BatteryRepository batteryRepo,
			TiltLegsRepository tiltLegsRepo, ProposedSubPanelRepository proposedSubPanelRepo,
			VoltageConfigurationRepository voltageConfRepo, ATSRepository atsRepo, GeneratorRepository generatorRepo,
			ModelMapper modelMapper, CheckValueTypesService checkValue) {
		super();
		this.dcComDiscoRepo = dcComDiscoRepo;
		this.dcOptimizerRepo = dcOptimizerRepo;
		this.railRackingRepo = railRackingRepo;
		this.roofAttachmentsRepo = roofAttachmentsRepo;
		this.aCDisconnectRepo = aCDisconnectRepo;
		this.acCombinerSLCRepo = acCombinerSLCRepo;
		this.inverterRepo = inverterRepo;
		this.moduleRepo = moduleRepo;
		this.flashingRepo = flashingRepo;
		this.leasePPAMeterRepo = leasePPAMeterRepo;
		this.batteryRepo = batteryRepo;
		this.tiltLegsRepo = tiltLegsRepo;
		this.proposedSubPanelRepo = proposedSubPanelRepo;
		this.voltageConfRepo = voltageConfRepo;
		this.atsRepo = atsRepo;
		this.generatorRepo = generatorRepo;
		this.modelMapper = modelMapper;
		this.checkValue = checkValue;
	}

	public ListManagementFavoritesModel getListManagementFavorites(Long idPermit, Long idUser) {
		ListManagementFavoritesModel listManagFavo = new ListManagementFavoritesModel();
		try {

			
			List<RailRackingModel> railRackingDTOs = new ArrayList<>();
			List<RailRacking> railRackings = railRackingRepo.findByProject(idPermit);
			for (RailRacking mod : railRackings) {
				RailRackingModel railRackingDto =new RailRackingModel();
				railRackingDto.setId(mod.getId());
				railRackingDto.setManufacturer(mod.getManufacturer());
				railRackingDto.setWeight(mod.getWeight());
				railRackingDto.setIsDeleted(mod.getIsDeleted());
				railRackingDto.setLastUpdate(mod.getLastUpdate());
				railRackingDto.setHasCorrectionRequest(mod.getHasCorrectionRequest());
				railRackingDto.setEroneousContent(mod.getEroneousContent());
				railRackingDto.setEroneousContentOther(mod.getEroneousContentOther());
				railRackingDto.setEroneousDescription(mod.getEroneousDescription());
				railRackingDto.setManufacturerMappingValue(mod.getManufacturerMappingValue());
				railRackingDto.setModelMappingValue(mod.getModelMappingValue());
				railRackingDto.setPvRailType(mod.getPvRailType() != null ? mod.getPvRailType().getId() : null);
				railRackingDto.setPvRailSpliceType(mod.getPvRailSpliceType() != null ? mod.getPvRailSpliceType().getId() : null);
				railRackingDto.setMidClamp(mod.getMidClamp() != null ? mod.getMidClamp().getId() : null);
				railRackingDto.setEndClamp(mod.getEndClamp() != null ? mod.getEndClamp().getId() : null);
				railRackingDto.setIntegratedStanchion(mod.getIntegratedStanchion());
				railRackingDto.setStanchionMfg(mod.getStanchionMfg());
				railRackingDto.setStanchionMfgMappingValue(mod.getStanchionMfgMappingValue());
				railRackingDto.setStanchionModel(mod.getStanchionModel());
				railRackingDto.setStanchionModelMappingValue(mod.getStanchionModelMappingValue());
				railRackingDto.setIntegratedFlashing(mod.getIntegratedFlashing());
				railRackingDto.setOwner(mod.getIdOwner().getFirstName() + " " + mod.getIdOwner().getLastName());
				railRackingDto.setModel(mod.getModel());
				railRackingDto.setIdOwner(mod.getIdOwner().getId() + "");
				List<String> types = new ArrayList<>();
	    		for(MountingTypeEntity type :mod.getMountType()) {
					types.add(type.getMountingType());
		    	}
	    		List<Long> allowedRoofMaterial = new ArrayList<>();
	    		for(RackingAllowedRoofMaterial type :mod.getRackingAllowedRoofMaterial()) {
	    			allowedRoofMaterial.add(type.getRoofMaterial().getId());
		    	}
	    		railRackingDto.setMountType(types);
	    		railRackingDto.setAllowedRoofMaterial(allowedRoofMaterial);
	    		railRackingDTOs.add(railRackingDto);
			}
			
			List<FavoriteListDto> inverter = inverterRepo.findByProject(idPermit).stream().
					map(c -> new FavoriteListDto(c.getId(), c.getMake(), c.getModel(), voltageConfRepo.getVoltageByInverter(c.getId()),
							c.getMicroInverter(), c.getOptimizer(), c.getVdcmax(), c.getWireQty())).collect(Collectors.toList());
			List<FavoriteListDto> ats = atsRepo.findByFavoriteIdUserIdAndDeletedIsFalse(idUser).stream().
					map(c -> new FavoriteListDto(c.getId(), c.getManufacturer(), c.getModel())).collect(Collectors.toList());
			List<FavoriteListDto> generator = generatorRepo.findByFavoriteIdUserIdAndDeletedIsFalse(idUser).stream().
					map(c -> new FavoriteListDto(c.getId(), c.getManufacturer(), c.getModel())).collect(Collectors.toList());
			List<FavoriteListDto> roofAttachment = roofAttachmentsRepo.findByProject(idPermit).stream().
					map(c -> new FavoriteListDto(c.getId(), c.getManufacturer(), c.getModel(), c.getIntegrated(), getAllawedRoof(c.getAllowedRoof()))).collect(Collectors.toList());
			List<FavoriteListDto> dcd = dcComDiscoRepo.findByProject(idPermit, Arrays.asList("DC Disconnect","Rapid Shutdown")).stream().
					map(c -> new FavoriteListDto(c.getId(), c.getManufacturer(), c.getModel(), c.getDropdownOption())).collect(Collectors.toList());
			List<FavoriteListDto> dcc = dcComDiscoRepo.findByProject(idPermit, Arrays.asList("DC Combiner","DC Combining Disconnect","Rapid Shutdown")).stream().
					map(c -> new FavoriteListDto(c.getId(), c.getManufacturer(), c.getModel(), c.getDropdownOption())).collect(Collectors.toList());
			List<FavoriteListDto> dccd = dcComDiscoRepo.findByProject(idPermit, Arrays.asList("DC Combiner","DC Disconnect","DC Combining Disconnect","Rapid Shutdown")).stream().
					map(c -> new FavoriteListDto(c.getId(), c.getManufacturer(), c.getModel(), c.getDropdownOption())).collect(Collectors.toList());
			

			listManagFavo.setDcDisconnect(dcd);
			listManagFavo.setDcCombiner(dcc);
			listManagFavo.setDcCombinerDisconnect(dccd);
			listManagFavo.setDCOptimizerEntity(dcOptimizerRepo.findByProject(idPermit));
			listManagFavo.setRailRacking(railRackingDTOs);
			listManagFavo.setRoofAttachment(roofAttachment);
			listManagFavo.setACDisconnect(aCDisconnectRepo.findByProject(idPermit));
			listManagFavo.setACCombinerSLC(acCombinerSLCRepo.findByProject(idPermit));
			listManagFavo.setInverter(inverter);
			listManagFavo.setModule(moduleRepo.findByProject(idPermit));
			listManagFavo.setFlashing(flashingRepo.findByProject(idPermit));
			listManagFavo.setLeasePPAMeter(leasePPAMeterRepo.findByProject(idPermit));
			listManagFavo.setBattery(batteryRepo.findByProject(idPermit));
			listManagFavo.setTiltLegs(tiltLegsRepo.findByProject(idPermit));
			listManagFavo.setProposedSubPanel(proposedSubPanelRepo.findByProject(idPermit));
			listManagFavo.setAts(ats);
			listManagFavo.setGenerator(generator);

			return listManagFavo;

		} catch (Exception e) {
			e.printStackTrace();
			return listManagFavo;

		}

	}
	private List<Long> getAllawedRoof(String allowedRoof) {
		try {
			List<Long> l = new ArrayList<>();
			if(checkValue.isStringNotEmpty(allowedRoof)) {
				String[] s = allowedRoof.split(":::");
				for (String r : s) {
					l.add(Long.valueOf(r));
				}
			}
			return l;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
}
