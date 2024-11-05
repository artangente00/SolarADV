package com.PlayGroundAdv.Solar.service.generate_planset.ssheets;

import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.SsheetSpacingMappingEntity;
import com.PlayGroundAdv.Solar.repositories.sheets.SsheetSpacingMappingRepository;
import com.PlayGroundAdv.Solar.service.log.SheetsLogService;

@Service
public class GetSpacingSSheets {

	final SsheetSpacingMappingRepository sheetSpacingRepo;
	final FilterSpacingSheets filterSpacingSheets;
	final SheetsLogService missingSheetService;

	public GetSpacingSSheets(SsheetSpacingMappingRepository sheetSpacingRepo, FilterSpacingSheets filterSpacingSheets,
			SheetsLogService missingSheetService) {
		super();
		this.sheetSpacingRepo = sheetSpacingRepo;
		this.filterSpacingSheets = filterSpacingSheets;
		this.missingSheetService = missingSheetService;
	}

	public SsheetSpacingMappingEntity getSpacingFileName(PermitEntity permitEntity, Long submitId,
			AuthentificationEntity userConnectedEntity, String roofRafter, String rafterTrussSpacing,
			String stanchionMaxSpacing, String sheet, String quad, String stanchionType, Boolean hasFlashing) {

		try {
			List<SsheetSpacingMappingEntity> spacingSheets = sheetSpacingRepo
					.findBySheetNumberAndQuadNumberAndIsDeleted(sheet, quad, false);
			spacingSheets = filterSpacingSheets.filterByRoofType(spacingSheets, roofRafter);
			spacingSheets = filterSpacingSheets.filterByRafterTrussSpacing(spacingSheets, rafterTrussSpacing);
			spacingSheets = filterSpacingSheets.filterByStanchionMaxSpacing(spacingSheets, stanchionMaxSpacing);
			spacingSheets = filterSpacingSheets.filterByStanchionType(spacingSheets, stanchionType);
			spacingSheets = filterSpacingSheets.filterByFlashing(spacingSheets,hasFlashing);
			
			//filter 
			if (spacingSheets.isEmpty()) {
				missingSheetService.insertMissingSheet(sheet + " Detail-1", sheet + " Detail-" + quad + " S-sheet",
						submitId, permitEntity, userConnectedEntity);
				return null;
			} else {
				return spacingSheets.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
