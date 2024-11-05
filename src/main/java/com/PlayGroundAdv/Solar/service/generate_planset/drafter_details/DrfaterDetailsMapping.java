package com.PlayGroundAdv.Solar.service.generate_planset.drafter_details;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitDrafterDataEntity;
import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;
import com.PlayGroundAdv.Solar.entity.PermitTotalSectionEntity;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class DrfaterDetailsMapping {

	final CheckValueTypesService checkValue;

	public DrfaterDetailsMapping(CheckValueTypesService checkValue) {
		super();
		this.checkValue = checkValue;
	}
	//A.B 09-22 CR-3438-MOD-001
	// A.B 05-26-2022 CR-903
	public void squareFootageMapping(AcroFields form, int sheetIndex, PermitDrafterDataEntity permitDrafterDatanfo,
			 List<PermitSketchEntity> listPermitSketchEntity) {
		try {
			Float sum = 0f;
			cleanSquareFootage(form, sheetIndex);
			if (listPermitSketchEntity != null) {
				int i = 0;
				for (PermitSketchEntity totalSection : listPermitSketchEntity) {
					i++;
					if (totalSection != null &&  checkValue.isNumericPositive(totalSection.getSquareFootage())) {
						sum = sum + totalSection.getSquareFootage();
						form.setField(sheetIndex + "-" + "ROOF-SECTION-" + i, "SECTION-" + i);
						form.setField(sheetIndex + "-" + "SECTION-" + i + "-SQUARE-FOOTAGE",
								totalSection.getSquareFootage() + "" );
					}

				}

			}
			if (permitDrafterDatanfo != null && permitDrafterDatanfo.getTotalRoofSquareFootage() != null
					&& permitDrafterDatanfo.getTotalRoofSquareFootage() != 0) {
				form.setField(sheetIndex + "-" + "EXISTING-ROOF-SQUARE-FOOTAGE",
						permitDrafterDatanfo.getTotalRoofSquareFootage() + "");
				form.setField(sheetIndex + "-" + "TOTAL-PERTCENTAGE-OF-MODULE-COVERAGE",
						String.valueOf(new DecimalFormat("##.##")
								.format((sum / permitDrafterDatanfo.getTotalRoofSquareFootage()) * 100)) + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void cleanSquareFootage(AcroFields form, int sheetIndex) {
		try {
			for (int i = 1; i < 7; i++) {
				form.setField(sheetIndex + "-" + "ROOF-SECTION-" + i, "");
				form.setField(sheetIndex + "-" + "SECTION-" + i + "-SQUARE-FOOTAGE", "");
			}
			form.setField(sheetIndex + "-" + "EXISTING-ROOF-SQUARE-FOOTAGE", "");
			form.setField(sheetIndex + "-" + "TOTAL-PERTCENTAGE-OF-MODULE-COVERAGE", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//A.B 09-22 CR-3438-MOD-002
	public void pv100ScaleMapping(AcroFields form, int sheetIndex, String fieldName,
			PermitDrafterDataEntity permitDrafterDatanfo) {
		try {
			if (!Boolean.TRUE.equals(permitDrafterDatanfo.getCustomizeScale())) {
				scaleMapping(form, sheetIndex, fieldName, permitDrafterDatanfo.getScale(),
						permitDrafterDatanfo.getChooseScaleOther());
			} else {
				scaleMapping(form, sheetIndex, fieldName, permitDrafterDatanfo.getChooseScaleArrayLayout(),
						permitDrafterDatanfo.getChooseScaleArrayOther());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//S.B 03-17-2021 PR-010
		public void pv101ScaleMapping(AcroFields form, int sheetIndex, String fieldName,
				PermitDrafterDataEntity permitDrafterDatanfo) {
			try {
				scaleMapping(form, sheetIndex, fieldName, permitDrafterDatanfo.getScale(),
						permitDrafterDatanfo.getChooseScaleOther());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
	// F.B 05-25-2022 CR-915-MOD-002
		
		public void pv101XScaleMapping(AcroFields form, int sheetIndex, String fieldName,
				PermitDrafterDataEntity permitDrafterDatanfo) {
			try {
				scaleMapping(form, sheetIndex, fieldName, permitDrafterDatanfo.getScalePV101(),
						permitDrafterDatanfo.getScalePV101Other());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	//A.B 09-22 CR-3438-MOD-002
	public void s100ScaleMapping(AcroFields form, int sheetIndex, String fieldName,
			PermitDrafterDataEntity permitDrafterDatanfo) {
		if (!Boolean.TRUE.equals(permitDrafterDatanfo.getCustomizeScale())) {
			scaleMapping(form, sheetIndex, fieldName, permitDrafterDatanfo.getScale(),
					permitDrafterDatanfo.getChooseScaleOther());
		} else {
			scaleMapping(form, sheetIndex, fieldName, permitDrafterDatanfo.getScalerackingLayout(),
					permitDrafterDatanfo.getChooseScaleRackingOther());
		}
	}
	//A.B 09-22 CR-3438-MOD-002
	public void e100ScaleMapping(AcroFields form, int sheetIndex, String fieldName,
			PermitDrafterDataEntity permitDrafterDatanfo) {
		if (!Boolean.TRUE.equals(permitDrafterDatanfo.getCustomizeScale())) {
			scaleMapping(form, sheetIndex, fieldName, permitDrafterDatanfo.getScale(),
					permitDrafterDatanfo.getChooseScaleOther());
		} else {
			scaleMapping(form, sheetIndex, fieldName, permitDrafterDatanfo.getScaleelectricalLayout(),
					permitDrafterDatanfo.getChooseScaleElectricalOther());
		}
	}
	//A.B 09-22 CR-3438-MOD-002
	public void scaleMapping(AcroFields form, int sheetIndex, String fieldName, String scale, String otherScale) {
		try {
			
			if (checkValue.Equals(scale, "Other")) {
				if (checkValue.NotEquals(otherScale, "")
						&& checkValue.NotEquals(otherScale, "null")) {
					if (checkValue.Equals(otherScale, "10")) {
						form.setField(sheetIndex + "-" + fieldName, "SCALE: 1'-0\"=1'-0\" @ SHEET SIZE A3");
					} else {
						form.setField(sheetIndex + "-" + fieldName, "SCALE: " + otherScale + " @ SHEET SIZE A3");
					}
				} else {
					form.setField(sheetIndex + "-" + fieldName, "SCALE:(FRACTION)=1'-0\" @ SHEET SIZE A3");
				}
				

			} else if (checkValue.NotEquals(scale, "") && checkValue.NotEquals(scale, "null")) {
				if (checkValue.Equals(scale, "10")) {
					form.setField(sheetIndex + "-" + fieldName, "SCALE: 1'-0\"=1'-0\" @ SHEET SIZE A3");
				} else {
					form.setField(sheetIndex + "-" + fieldName, "SCALE: " + scale + " @ SHEET SIZE A3");
				}
			} else {
				form.setField(sheetIndex + "-" + fieldName, "SCALE:(FRACTION)=1'-0\" @ SHEET SIZE A3");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
