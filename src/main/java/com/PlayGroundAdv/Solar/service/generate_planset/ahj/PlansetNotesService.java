package com.PlayGroundAdv.Solar.service.generate_planset.ahj;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJChecklistEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.AHJNotesModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJAddRemoveFromPlansetModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJDesignCriteriaModel;
import com.PlayGroundAdv.Solar.model.ahj_library.GoverningCodesModel;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJACDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJAddRemoveFromPlansetRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJChecklistRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJDesignCriteriaRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJElectricalRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class PlansetNotesService {
	
	final AHJChecklistRepository aHJChecklistRepo;
	final AHJACDiscoRepository acDiscoRepo;
	final AHJElectricalRepository electricalRepo;
	final AHJAddRemoveFromPlansetRepository addRemoveFromPlansetRepo;
	final AHJDesignCriteriaRepository designCriteriaRepo;
	final ModelMapper modelMapperAHJ;
	final CheckValueTypesService checkValue;

	public PlansetNotesService(AHJChecklistRepository aHJChecklistRepo, AHJACDiscoRepository acDiscoRepo,
			AHJElectricalRepository electricalRepo, AHJAddRemoveFromPlansetRepository addRemoveFromPlansetRepo,
			AHJDesignCriteriaRepository designCriteriaRepo, ModelMapper modelMapperAHJ,
			CheckValueTypesService checkValue) {
		super();
		this.aHJChecklistRepo = aHJChecklistRepo;
		this.acDiscoRepo = acDiscoRepo;
		this.electricalRepo = electricalRepo;
		this.addRemoveFromPlansetRepo = addRemoveFromPlansetRepo;
		this.designCriteriaRepo = designCriteriaRepo;
		this.modelMapperAHJ = modelMapperAHJ;
		this.checkValue = checkValue;
	}
	
	public Long getAHJId(String state, String city, String cityOther, String ahjJur, String ahjJurOther) {
		try {
			List<String> ahjNames = new ArrayList<>();
			List<String> ahjCounty = new ArrayList<>();
			AHJChecklistEntity ahj =  new AHJChecklistEntity();
			if (checkValue.Equals(ahjJur, "Other") && ahjJurOther != null) {
				ahjNames.add(ahjJurOther);
				ahjCounty.add(ahjJurOther);
				if (ahjJurOther.toUpperCase().contains("CITY OF ")){
					ahjNames.add(ahjJurOther.replaceAll("(?i)City Of ", ""));
					ahjNames.add(ahjJurOther.replaceAll("(?i)City Of ", "") + " City");
				}else if(ahjJurOther.toUpperCase().contains("COUNTY OF ")){
					ahjCounty.add(ahjJurOther.replaceAll("(?i)County Of ", ""));
					ahjCounty.add(ahjJurOther.replaceAll("(?i)County Of ", "") + " county");
				}else if (ahjJurOther.toUpperCase().contains("CITY")){
					ahjNames.add(ahjJurOther.replaceAll("(?i)City", ""));
					ahjNames.add(ahjJurOther.replaceAll("(?i)City", "") + " City");
				}else if(ahjJurOther.toUpperCase().contains("COUNTY")){
					ahjCounty.add(ahjJurOther.replaceAll("(?i)County", ""));
					ahjCounty.add(ahjJurOther.replaceAll("(?i)County", "") + " county");
				}
				ahj = aHJChecklistRepo.findFirstByStateAndAhjInIgnoreCaseOrStateAndCountyInIgnoreCase(state, ahjNames, state, ahjCounty);
			}else if (checkValue.Equals(ahjJur, "city")){
				
				if (checkValue.NotEquals(city, "Other")){
					ahjNames.add(city);
					ahjNames.add(city + " City");
				}else {
					ahjNames.add(ahjJurOther);
					ahjNames.add(ahjJurOther + " City");
					ahjNames.add(cityOther);
					ahjNames.add(cityOther + " City");
				}
				ahj = aHJChecklistRepo.findFirstByStateAndAhjInIgnoreCaseOrStateAndCountyInIgnoreCase(state, ahjNames, state, ahjCounty);
			}else if(ahjJur != null && ahjJur.contains("County")) {
				if (checkValue.Equals(ahjJur, "County of ")) {
					ahjCounty.add(ahjJurOther);
					ahjCounty.add(ahjJurOther + " county");
					ahjNames.add(ahjJurOther + " county");
				}else {
					ahjNames.add((ahjJur.replace("County of ", "") )+ " county");
					ahjCounty.add(ahjJur);
					ahjCounty.add(ahjJur.replace("County of ", ""));
					ahjCounty.add(ahjJur + " county");
				}
				ahj = aHJChecklistRepo.findFirstByStateAndAhjInIgnoreCaseOrStateAndCountyInIgnoreCase(state, ahjNames, state, ahjCounty);
				if (ahj == null) {
					ahjNames.add(city);
					ahj = aHJChecklistRepo.findFirstByStateAndAhjInIgnoreCaseOrStateAndCountyInIgnoreCase(state, ahjNames, state, ahjCounty);
				}
			}
			if (ahj != null) {
				return ahj.getId();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public GoverningCodesModel getGoverningCodes(Long ahjId) {
		try {
			AHJChecklistEntity ahj = aHJChecklistRepo.findById(ahjId)
					.orElseThrow(() -> new ResourceNotFoundException("AHJ not found for this id :" + ahjId));
			return modelMapperAHJ.map(ahj, GoverningCodesModel.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new GoverningCodesModel();
		}
	}
	
	public AHJChecklistEntity getAHJ(Long ahjId) {
		try {
			if(ahjId != null) {
				return aHJChecklistRepo.findById(ahjId)
						.orElseThrow(() -> new ResourceNotFoundException("AHJ not found for this id :" + ahjId));
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public AHJDesignCriteriaModel getDesignCriteria(Long ahjId) {
		try {
			return modelMapperAHJ.map(designCriteriaRepo.findByAhjId(ahjId), AHJDesignCriteriaModel.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new AHJDesignCriteriaModel();
		}
	}
	public AHJNotesModel getAhjPlansetNotes(Long ahjId) {
		try {
			
			AHJNotesModel notes = new AHJNotesModel();
			AHJAddRemoveFromPlansetModel addRemoveFromPlanset =  modelMapperAHJ.map(addRemoveFromPlansetRepo.findByAhjId(ahjId), AHJAddRemoveFromPlansetModel.class);
			notes.setPV001Note(addRemoveFromPlanset.getSpecialNoteOrContentOnPV001());
			notes.setPV100RNote(addRemoveFromPlanset.getSpecialNoteOrContentOnPV100());
			notes.setPV100GNote(addRemoveFromPlanset.getSpecialNoteOrContentOnPV100G());
			notes.setPV101GNote(addRemoveFromPlanset.getSpecialNoteOrContentOnPV101G());
			notes.setS100Note(addRemoveFromPlanset.getSpecialNoteOrContentOnS100());
			notes.setS200Note(addRemoveFromPlanset.getSpecialNoteOrContentOnS200());
			notes.setS201Note(addRemoveFromPlanset.getSpecialNoteOrContentOnS201());
			notes.setS300Note(addRemoveFromPlanset.getSpecialNoteOrContentOnS300());
			notes.setE002Note(addRemoveFromPlanset.getSpecialNoteOrContentOnE002());
			notes.setE003Note(addRemoveFromPlanset.getSpecialNoteOrContentOnE003());
			notes.setE100Note(addRemoveFromPlanset.getSpecialNoteOrContentOnE100());
			notes.setShowPLDimensions(addRemoveFromPlanset.getShowPLDimensions());
			notes.setTldNote(electricalRepo.findTLDNoteforACD(ahjId));
			notes.setPV001ACRequirement(acDiscoRepo.findACOtherRequirements(ahjId));
			notes.setMinGroundWireSize(electricalRepo.findMinGroundWireSize(ahjId));
			return notes;
		} catch (Exception e) {
			e.printStackTrace();
			return new AHJNotesModel();
		}
	}
}
