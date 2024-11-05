package com.PlayGroundAdv.Solar.service.generate_planset.ssheets;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.Flashing;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.entity.SsheetRackingMappingEntity;
import com.PlayGroundAdv.Solar.repositories.sheets.SsheetRackingMappingRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class GetRackingSSheets {

	final SsheetRackingMappingRepository sheetRackingRepo;
	final FilterRackingSheets filterRackingSheets;
	final CheckValueTypesService checkValue;

	public GetRackingSSheets(SsheetRackingMappingRepository sheetRackingRepo, FilterRackingSheets filterRackingSheets,
			CheckValueTypesService checkValue) {
		super();
		this.sheetRackingRepo = sheetRackingRepo;
		this.filterRackingSheets = filterRackingSheets;
		this.checkValue = checkValue;
	}

	public SsheetRackingMappingEntity getRackingFileName(RoofAttachmentsEntity stanchionFoot, String roofRafter,
			RailRacking railRacking, Flashing flashing, ElectricalUtilityEntity electricalCompany,
			String roofMaterialType, String ahj, String sheet, String quad, Boolean hasFlashing, String state, String stanchionType) {

		try {
			List<SsheetRackingMappingEntity> rackingSheets = sheetRackingRepo
					.findBySheetNumberAndQuadNumberAndIsDeleted(sheet, quad, false);
			rackingSheets = filterRackingSheets.filterByRoofType(rackingSheets, roofRafter);
			rackingSheets = filterRackingSheets.filterByState(rackingSheets, state);
			rackingSheets = filterRackingSheets.filterByRoofMaterialType(rackingSheets, roofMaterialType);
			rackingSheets = filterRackingSheets.filterByRackingMake(rackingSheets, railRacking != null ? railRacking.getManufacturer() : "");
			rackingSheets = filterRackingSheets.filterByRackingModel(rackingSheets, railRacking != null ? railRacking.getModel() : "");
			rackingSheets = filterRackingSheets.filterByRackingMountingType(rackingSheets,
					 railRacking != null ? railRacking.getMountType() : null);
			rackingSheets = filterRackingSheets.filterByRailConnectMake(rackingSheets,
					stanchionFoot != null ? stanchionFoot.getManufacturer() : Boolean.TRUE.equals(railRacking != null && Boolean.TRUE.equals(railRacking.getIntegratedStanchion())) ? railRacking.getStanchionMfg() : "");
			rackingSheets = filterRackingSheets.filterByRailConnectModel(rackingSheets,
					stanchionFoot != null ? stanchionFoot.getModel() : Boolean.TRUE.equals(railRacking != null && Boolean.TRUE.equals(railRacking.getIntegratedStanchion())) ? railRacking.getStanchionModel() : "");
			rackingSheets = filterRackingSheets.filterByFlashing(rackingSheets, flashing != null ? flashing.getManufacturer() : "");
			rackingSheets = filterRackingSheets.filterByStanchionType(rackingSheets, stanchionType);
			rackingSheets = filterRackingSheets.filterByHasFlashing(rackingSheets, hasFlashing);
			rackingSheets = filterRackingSheets.filterByAhj(rackingSheets, ahj);
			rackingSheets = filterRackingSheets.filterByUtilityCompany(rackingSheets,
					electricalCompany != null ? electricalCompany.getUtilityCompanyName() : "");
			if (rackingSheets.isEmpty()) {
				return null;
			} else {
				return rackingSheets.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getPDFPAth(String pdfname) {

		if (!pdfname.contains(".pdf")) {
			pdfname = pdfname + ".pdf";
		}
		if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/" + pdfname).exists()) {
			return "PlansetS200S201/New S200 S201 PDFs/" + pdfname;
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/DPW SOLAR/" + pdfname).exists()) {
			return "PlansetS200S201/DPW SOLAR/" + pdfname;
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR ROOFTRAC/" + pdfname).exists()) {
			return "PlansetS200S201/PROSOLAR ROOFTRAC/" + pdfname;
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/" + pdfname).exists()) {
			return "PlansetS200S201/PROSOLAR TILETRAC/" + pdfname;
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/Stanchion Spacing/" + pdfname).exists()) {
			return "PlansetS200S201/Stanchion Spacing/" + pdfname;
		} else {
			return pdfname;
		}
	}

	public String getRoofRafter(String projectRoofRafter) {
		String roofRafter = "";
		try {
			if (checkValue.Equals(projectRoofRafter, "Rafter-Simple Attic")) {
				roofRafter = "Rafter - Simple Attic";
			} else if (checkValue.Equals(projectRoofRafter, "Rafter - Cathedral Ceiling")) {
				roofRafter = "Rafter - Cathedral Clg";
			} else if (checkValue.Equals(projectRoofRafter, "Rafter - Strut to Walls Below")) {
				roofRafter = "Rafter - Struts to wall(s) below";
			} else if (checkValue.Equals(projectRoofRafter, "Pre-Eng Roof Trusses")) {
				roofRafter = "Truss - Manufactured Plate Truss";
			} else if (checkValue.Equals(projectRoofRafter, "Flat Roof With Trusses")) {
				roofRafter = "Flat Roof Truss";
			} else if ((checkValue.Equals(projectRoofRafter, "Metal Building with I Beams and Purlins")
					|| checkValue.Equals(projectRoofRafter, "Other Or we will provide images"))) {
				roofRafter = "Typical Metal Bld";
			} else {
				roofRafter = projectRoofRafter;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return roofRafter;
	}

}
