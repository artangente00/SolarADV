package com.PlayGroundAdv.Solar.service.project;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitPlansetUploadEntity;
import com.PlayGroundAdv.Solar.entity.projects.ProjectFiles;
import com.PlayGroundAdv.Solar.model.GetFileByIdResults;
import com.PlayGroundAdv.Solar.model.GetPlansetByIdResults;
import com.PlayGroundAdv.Solar.repositories.project.ProjectFilesRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.PermitPlansetUploadRepository;

@Service
public class ProjectFilesManagement {

	final ProjectFilesRepository projectFilesRepo;
	final PermitPlansetUploadRepository permitPlansetUploadRepo;

	public ProjectFilesManagement(ProjectFilesRepository projectFilesRepo,
			PermitPlansetUploadRepository permitPlansetUploadRepo) {
		super();
		this.projectFilesRepo = projectFilesRepo;
		this.permitPlansetUploadRepo = permitPlansetUploadRepo;
	}

	public GetFileByIdResults getFileById(Long idPermit) {
		GetFileByIdResults getFileByIdResults = new GetFileByIdResults();
		try {

			ProjectFiles files = projectFilesRepo.findByProjectId(idPermit);
			getFileByIdResults.setIdPermit(idPermit);
			if (files.getNameFile1() != null) {
				getFileByIdResults.setFile1(files.getNameFile1());
			}
			if (files.getNameFile2() != null) {
				getFileByIdResults.setFile2(files.getNameFile2());
			}
			if (files.getNameFile3() != null) {
				getFileByIdResults.setFile3(files.getNameFile3());
			}
			if (files.getNameFile4() != null) {
				getFileByIdResults.setFile4(files.getNameFile4());
			}
			if (files.getNameFile5() != null) {
				getFileByIdResults.setFile5(files.getNameFile5());
			}
			if (files.getNameFile6() != null) {
				getFileByIdResults.setFile6(files.getNameFile6());
			}
			if (files.getNameFile7() != null) {
				getFileByIdResults.setFile7(files.getNameFile7());
			}
			if (files.getNameFile8() != null) {
				getFileByIdResults.setFile8(files.getNameFile8());
			}
			if (files.getNameFile9() != null) {
				getFileByIdResults.setFile9(files.getNameFile9());
			}
			if (files.getNameFile10() != null) {
				getFileByIdResults.setFile10(files.getNameFile10());
			}
			if (files.getNameFile11() != null) {
				getFileByIdResults.setFile11(files.getNameFile11());
			}

			getFileByIdResults.setUploadFile1(files.getUploadFile1());
			getFileByIdResults.setUploadFile2(files.getUploadFile2());
			getFileByIdResults.setUploadFile3(files.getUploadFile3());
			getFileByIdResults.setUploadFile4(files.getUploadFile4());
			getFileByIdResults.setUploadFile5(files.getUploadFile5());
			getFileByIdResults.setUploadFile6(files.getUploadFile6());
			getFileByIdResults.setUploadFile7(files.getUploadFile7());
			getFileByIdResults.setUploadFile8(files.getUploadFile8());
			getFileByIdResults.setUploadFile9(files.getUploadFile9());
			getFileByIdResults.setUploadFile10(files.getUploadFile10());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getFileByIdResults;
	}

	// Save the textarea fields of the popup upload files
	public String saveCommentUpload(GetFileByIdResults cm, GetPlansetByIdResults cm2) {

		try {
			ProjectFiles file = projectFilesRepo.findByProjectId(cm.getIdPermit());
			PermitPlansetUploadEntity permitPlansetUploadEntity = permitPlansetUploadRepo
					.findByPermitEntityId(cm2.getIdPermit());

			file.setUploadFile1(cm.getUploadFile1());
			file.setUploadFile2(cm.getUploadFile2());
			file.setUploadFile3(cm.getUploadFile3());
			file.setUploadFile4(cm.getUploadFile4());
			file.setUploadFile5(cm.getUploadFile5());
			file.setUploadFile6(cm.getUploadFile6());
			file.setUploadFile7(cm.getUploadFile7());
			file.setUploadFile8(cm.getUploadFile8());
			file.setUploadFile9(cm.getUploadFile9());
			file.setUploadFile10(cm.getUploadFile10());
			projectFilesRepo.save(file);

			permitPlansetUploadEntity.setUploadPlanset1(cm2.getUploadPlanset1());
			permitPlansetUploadEntity.setUploadPlanset2(cm2.getUploadPlanset2());
			permitPlansetUploadEntity.setUploadPlanset3(cm2.getUploadPlanset3());
			permitPlansetUploadEntity.setUploadPlanset4(cm2.getUploadPlanset4());
			permitPlansetUploadEntity.setUploadPlanset5(cm2.getUploadPlanset5());
			permitPlansetUploadRepo.save(permitPlansetUploadEntity);
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

	public GetPlansetByIdResults getPlansetById(Long idPermit) {
		GetPlansetByIdResults getPlansetByIdResults = new GetPlansetByIdResults();
		try {

			PermitPlansetUploadEntity permitPlansetUploadEntity = permitPlansetUploadRepo
					.findByPermitEntityId(idPermit);
			getPlansetByIdResults.setIdPermit(idPermit);
			if (permitPlansetUploadEntity.getNameFile_1() != null) {
				getPlansetByIdResults.setPlanset1(permitPlansetUploadEntity.getNameFile_1());
			}
			if (permitPlansetUploadEntity.getNameFile_2() != null) {
				getPlansetByIdResults.setPlanset2(permitPlansetUploadEntity.getNameFile_2());
			}
			if (permitPlansetUploadEntity.getNameFile_3() != null) {
				getPlansetByIdResults.setPlanset3(permitPlansetUploadEntity.getNameFile_3());
			}
			if (permitPlansetUploadEntity.getNameFile_4() != null) {
				getPlansetByIdResults.setPlanset4(permitPlansetUploadEntity.getNameFile_4());
			}
			if (permitPlansetUploadEntity.getNameFile_5() != null) {
				getPlansetByIdResults.setPlanset5(permitPlansetUploadEntity.getNameFile_5());
			}

			getPlansetByIdResults.setUploadPlanset1(permitPlansetUploadEntity.getUploadPlanset1());
			getPlansetByIdResults.setUploadPlanset2(permitPlansetUploadEntity.getUploadPlanset2());
			getPlansetByIdResults.setUploadPlanset3(permitPlansetUploadEntity.getUploadPlanset3());
			getPlansetByIdResults.setUploadPlanset4(permitPlansetUploadEntity.getUploadPlanset4());
			getPlansetByIdResults.setUploadPlanset5(permitPlansetUploadEntity.getUploadPlanset5());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getPlansetByIdResults;
	}
}
