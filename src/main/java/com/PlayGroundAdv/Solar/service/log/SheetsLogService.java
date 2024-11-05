package com.PlayGroundAdv.Solar.service.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.MissingSheetsLogEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PlansetSheetsLog;
import com.PlayGroundAdv.Solar.entity.PlansetUsedSheetsLog;
import com.PlayGroundAdv.Solar.model.CustomizeSheetsLogModel;
import com.PlayGroundAdv.Solar.model.MissingSheetsModel;
import com.PlayGroundAdv.Solar.model.PlansetSheetsLogModel;
import com.PlayGroundAdv.Solar.model.PlansetUsedSheetsLogModel;
import com.PlayGroundAdv.Solar.repositories.MissingSheetsLogRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.PlansetSheetsLogRepository;
import com.PlayGroundAdv.Solar.repositories.PlansetUsedSheetsLogRepository;
import com.PlayGroundAdv.Solar.repositories.users.ContractorInformationRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class SheetsLogService {
	
	@PersistenceContext
	EntityManager em;
	
	final MissingSheetsLogRepository missingSheetRepo;
	final PlansetSheetsLogRepository plansetSheetsRepo;
	final CheckValueTypesService checkValueTypesService;
	final PermitRepository permitRepo;
	final ContractorInformationRepository userInfoRepo;
	final PlansetUsedSheetsLogRepository plansetUsedSheetRepo;
	
	public SheetsLogService(MissingSheetsLogRepository missingSheetRepo, PlansetSheetsLogRepository plansetSheetsRepo,
			CheckValueTypesService checkValueTypesService, PermitRepository permitRepo,
			ContractorInformationRepository userInfoRepo, PlansetUsedSheetsLogRepository plansetUsedSheetRepo) {
		super();
		this.missingSheetRepo = missingSheetRepo;
		this.plansetSheetsRepo = plansetSheetsRepo;
		this.checkValueTypesService = checkValueTypesService;
		this.permitRepo = permitRepo;
		this.userInfoRepo = userInfoRepo;
		this.plansetUsedSheetRepo = plansetUsedSheetRepo;
	}
	
	public void insertMissingSheet(String sheetName,String sheetType, Long submissionId ,PermitEntity project,AuthentificationEntity userInfo) {
		
		try {
			String server = (String) em.createQuery("SELECT u.urlPath from PathEntity u ").getSingleResult();
			if (checkValueTypesService.Equals(server, "production") && (checkValueTypesService.Equals(userInfo.getEmail(), "pm@nuagetechnologies-tn.com") || !checkValueTypesService.contains(userInfo.getEmail(), "nuagetechnologies-tn.com")) ) {
				MissingSheetsLogEntity missingSheet = new MissingSheetsLogEntity(sheetName, sheetType, new Date(), submissionId, project, userInfo);
				missingSheetRepo.save(missingSheet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Page<MissingSheetsModel> getMissingSheetsList(){
		try {
			List<MissingSheetsLogEntity> listSheets = new ArrayList<MissingSheetsLogEntity>();
			listSheets = missingSheetRepo.findAll();
			Pageable pageable = PageRequest.of(0, listSheets.size(), Sort.by("date"));
			Page<MissingSheetsModel> sheetsModel = new PageImpl<MissingSheetsModel>(listSheets.stream()
	                .map(sheet -> new MissingSheetsModel(
	                		sheet.getSheetName(),
	                		sheet.getSheetType(),
	                		sheet.getDate(),
	                		checkValueTypesService.isStringNotEmpty(sheet.getProject().getProjectName()) ? sheet.getProject().getProjectName() :  sheet.getProject().getHomeOwnLastName()+", "+sheet.getProject().getHomeOwnName(),
	                		sheet.getUserInfo().getFirstName()+" "+sheet.getUserInfo().getLastName()
	                		))
	                .collect(Collectors.toList()), pageable, listSheets.size());
			
			return sheetsModel;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public Page<PlansetSheetsLogModel> getPlansetSheetsList(){
		try {
			List<PlansetSheetsLog> listSheets = new ArrayList<PlansetSheetsLog>();
			listSheets = plansetSheetsRepo.findBySheetTypeNot("Customize");
			Pageable pageable = PageRequest.of(0, listSheets.size(), Sort.by("date"));
			Page<PlansetSheetsLogModel> sheetsModel = new PageImpl<PlansetSheetsLogModel>(listSheets.stream()
	                .map(sheet -> new PlansetSheetsLogModel(
	                		sheet.getSheetName(),
	                		sheet.getSheetType(),
	                		sheet.getComment(),
	                		sheet.getDate(),
	                		sheet.getUserInfo().getFirstName()+" "+sheet.getUserInfo().getLastName()
	                		))
	                .collect(Collectors.toList()), pageable, listSheets.size());
			
			return sheetsModel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Page<CustomizeSheetsLogModel> getCustomizeSheetsList(){
		try {
			List<PlansetSheetsLog> listSheets = new ArrayList<PlansetSheetsLog>();
			listSheets = plansetSheetsRepo.findBySheetType("Customize");
			Pageable pageable = PageRequest.of(0, listSheets.size(), Sort.by("date"));
			Page<CustomizeSheetsLogModel> sheetsModel = new PageImpl<CustomizeSheetsLogModel>(listSheets.stream()
	                .map(sheet -> new CustomizeSheetsLogModel(
	                		sheet.getSheetName(),
	                		sheet.getAction(),
	                		sheet.getDate(),
	                		sheet.getSheetId(),
	                		sheet.getUserInfo().getFirstName()+" "+sheet.getUserInfo().getLastName()
	                		))
	                .collect(Collectors.toList()), pageable, listSheets.size());
			
			return sheetsModel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
	public Page<PlansetUsedSheetsLogModel> getGetPlansetUsedSheets(){
		try {
			List<PlansetUsedSheetsLog> listSheets = new ArrayList<PlansetUsedSheetsLog>();
			listSheets = plansetUsedSheetRepo.findAll();
			int pageSize = listSheets.size() > 0 ? listSheets.size() : 1;
			Pageable pageable = PageRequest.of(0, pageSize, Sort.by("date"));
			Page<PlansetUsedSheetsLogModel> sheetsModel = new PageImpl<PlansetUsedSheetsLogModel>(listSheets.stream()
	                .map(sheet -> new PlansetUsedSheetsLogModel(
	                		sheet.getProject().getProjectName() == null
							|| checkValueTypesService.Equals(sheet.getProject().getProjectName(), "") ?
									sheet.getProject().getHomeOwnLastName() + ", " + sheet.getProject().getHomeOwnName() :
										sheet.getProject().getProjectName(),
	                		sheet.getDate(),
	                		sheet.getPv_001(),
	                		sheet.getN_001(),
	                		sheet.getPv_100R(),
	                		sheet.getPv_100G(),
	                		sheet.getPv_101G(),
	                		sheet.getS_100(),
	                		sheet.getS_200(),
	                		sheet.getS_201(),
	                		sheet.getS_300(),
	                		sheet.getE_001(),
	                		sheet.getE_002(),
	                		sheet.getE_003(),
	                		sheet.getE_100(),
	                		sheet.getP_001(),
	                		sheet.getP_002(),
	                		sheet.getR_sheet()
	                		))
	                .collect(Collectors.toList()), pageable, pageSize);
			
			return sheetsModel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public Page<PlansetUsedSheetsLogModel> getFiltredPlansetUsedSheets(String filtredValue){
		try {

			List<PlansetUsedSheetsLog> listSheets = plansetUsedSheetRepo.findFiltred(filtredValue.toLowerCase());
			int pageSize = !listSheets.isEmpty() ? listSheets.size() : 1;
			Pageable pageable = PageRequest.of(0, pageSize, Sort.by("date"));
			return new PageImpl<>(listSheets.stream()
	                .map(sheet -> new PlansetUsedSheetsLogModel(
	                		sheet.getProject().getProjectName() == null
							|| checkValueTypesService.Equals(sheet.getProject().getProjectName(), "") ?
									sheet.getProject().getHomeOwnLastName() + ", " + sheet.getProject().getHomeOwnName() :
										sheet.getProject().getProjectName(),
	                		sheet.getDate(),
	                		sheet.getPv_001(),
	                		sheet.getN_001(),
	                		sheet.getPv_100R(),
	                		sheet.getPv_100G(),
	                		sheet.getPv_101G(),
	                		sheet.getS_100(),
	                		sheet.getS_200(),
	                		sheet.getS_201(),
	                		sheet.getS_300(),
	                		sheet.getE_001(),
	                		sheet.getE_002(),
	                		sheet.getE_003(),
	                		sheet.getE_100(),
	                		sheet.getP_001(),
	                		sheet.getP_002(),
	                		sheet.getR_sheet()
	                		))
	                .collect(Collectors.toList()), pageable, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

	
}
