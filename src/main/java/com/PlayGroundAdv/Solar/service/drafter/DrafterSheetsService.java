package com.PlayGroundAdv.Solar.service.drafter;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.SelectDrafterSheet;
import com.PlayGroundAdv.Solar.model.SelectDrafterSheetModel;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.SelectDrafterSheetRepository;

@Service
@Transactional
public class DrafterSheetsService {

	final PermitRepository projectRepo;
	final SelectDrafterSheetRepository selectDrafterSheetRepo;

	public DrafterSheetsService(PermitRepository projectRepo,
			SelectDrafterSheetRepository selectDrafterSheetRepo) {
		super();
		this.projectRepo = projectRepo;
		this.selectDrafterSheetRepo = selectDrafterSheetRepo;
	}

	public String saveDrafterSheets(List<SelectDrafterSheetModel> drafterSheets, Long idPermit) {

		try {
			PermitEntity permitEntity = projectRepo.findById(idPermit).get();
			List<SelectDrafterSheet> selectDrafterSheets = selectDrafterSheetRepo.findByIdPermitId(idPermit);

			if (!selectDrafterSheets.isEmpty()) {

				for (SelectDrafterSheet drafterSheet : selectDrafterSheets) {
					selectDrafterSheetRepo.delete(drafterSheet);
				}
			}
			if (drafterSheets != null && !drafterSheets.isEmpty()) {
				for (SelectDrafterSheetModel drafterSheet : drafterSheets) {
					SelectDrafterSheet selectDrafterSheet = new SelectDrafterSheet();
					selectDrafterSheet.setPageNumber(drafterSheet.getPageNumber());
					selectDrafterSheet.setPageSheet(drafterSheet.getPageSheet());
					selectDrafterSheet.setIdPermit(permitEntity);
					selectDrafterSheetRepo.save(selectDrafterSheet);
				}
			}
			return "success";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "error";
		}

	}

	public List<SelectDrafterSheet> getDrafterSheets(Long idPermit) {
		try {
			return selectDrafterSheetRepo.findByIdPermitId(idPermit);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

}
