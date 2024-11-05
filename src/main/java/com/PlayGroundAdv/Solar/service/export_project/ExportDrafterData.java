package com.PlayGroundAdv.Solar.service.export_project;

import java.util.LinkedHashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.SelectDrafterSheet;
import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.repositories.SelectDrafterSheetRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ExportDrafterData {
	final CheckValueTypesService checkValueTypes;
	final SelectDrafterSheetRepository selectDrafterSheetRepo;
	public ExportDrafterData(CheckValueTypesService checkValueTypes, 
			SelectDrafterSheetRepository selectDrafterSheetRepo) {
		super();
		this.checkValueTypes = checkValueTypes;
		this.selectDrafterSheetRepo = selectDrafterSheetRepo;
	}
	public LinkedHashMap<String, String> drafterData(GetPermitByIdResult permit) {

		LinkedHashMap<String, String> drafter = new LinkedHashMap<>();

		try {

			// 09-05-2019: M.A: Correction CR-2387
			if (permit.getPermitDrafterData().getCustomizeScale() != null
					&& Boolean.TRUE.equals(permit.getPermitDrafterData().getCustomizeScale())) {
				drafter.put("Choose Scale", "");
				drafter.put("Choose Scale Other", "");
			} else {
				drafter.put("Choose Scale", checkValueTypes.convert(permit.getPermitDrafterData().getChooseScale()));
				if (checkValueTypes.Equals(permit.getPermitDrafterData().getChooseScale(), "Other")) {
					drafter.put("Choose Scale Other", checkValueTypes.convert(permit.getPermitDrafterData().getChooseScaleOther()));
				} else {
					drafter.put("Choose Scale Other", "");
				}
			}

			drafter.put("Customize Scale on Each Sheet", Boolean.TRUE.equals(permit.getPermitDrafterData().getCustomizeScale()) ? "Yes" : "No");
			if (permit.getPermitDrafterData().getCustomizeScale() != null
					&& Boolean.TRUE.equals(permit.getPermitDrafterData().getCustomizeScale())) {

				drafter.put("Choose Scale for PV Array Layout – PV-100X", checkValueTypes.convert(permit.getPermitDrafterData().getChooseScaleArrayLayout()));
				drafter.put("Choose Scale for PV Array Layout – PV-100X Other", checkValueTypes.convert(permit.getPermitDrafterData().getChooseScaleArrayOther()));
				drafter.put("Choose Scale for Racking Layout – S-100", checkValueTypes.convert(permit.getPermitDrafterData().getChooseScaleRackingLayout()));
				drafter.put("Choose Scale for Racking Layout – S-100 Other", checkValueTypes.convert(permit.getPermitDrafterData().getChooseScaleRackingOther()));
				drafter.put("Choose Scale for Electrical Layout – E-100", checkValueTypes.convert(permit.getPermitDrafterData().getChooseScaleElectricalLayout()));
				drafter.put("Choose Scale for Electrical Layout – E-100 Other", checkValueTypes.convert(permit.getPermitDrafterData().getChooseScaleElectricalOther()));

			} else {
				drafter.put("Choose Scale for PV Array Layout – PV-100X", "");
				drafter.put("Choose Scale for PV Array Layout – PV-100X Other", "");
				drafter.put("Choose Scale for Racking Layout – S-100", "");
				drafter.put("Choose Scale for Racking Layout – S-100 Other", "");
				drafter.put("Choose Scale for Electrical Layout – E-100", "");
				drafter.put("Choose Scale for Electrical Layout – E-100 Other", "");

			}

			List<SelectDrafterSheet> list = selectDrafterSheetRepo.findByIdPermitId(permit.getPermitEntity().getId());
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					drafter.put("Select Plan set sheet for page(s)" + list.get(i).getPageNumber(), checkValueTypes.convert(list.get(i).getPageSheet()));
				}
			}
			drafter.put("Total Roof Square Footage",  checkValueTypes.convert(permit.getPermitDrafterData().getTotalRoofSquareFootage()+""));
			drafter.put("Total Array Section Count", checkValueTypes.convert(permit.getPermitDrafterData().getTotalArraySectionCount()+""));
			drafter.put("Scale Racking Layout", checkValueTypes.convert(permit.getPermitDrafterData().getChooseScaleRackingLayout()));
			drafter.put("Scale Electrical Layout", checkValueTypes.convert(permit.getPermitDrafterData().getChooseScaleElectricalLayout()));
			drafter.put("Stanchion Quantity", checkValueTypes.convert(permit.getPermitDrafterData().getStanchionQuantity()+""));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return drafter;
	}

}
