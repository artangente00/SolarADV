package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e001;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class PVModuleSpec {

	final CheckValueTypesService checkValue;

	public PVModuleSpec(CheckValueTypesService checkValue) {
		super();
		this.checkValue = checkValue;
	}

	static final String MODULE = "-PV-MODULE-1-";

	public void pvModuleMapping(Cmodulev2 moduleInfo, AcroFields form, int sheetIndex,
			PermitArrayEntityResultSecond permitArrays) {
		try {
			if (moduleInfo != null) {
				String pvModuleManufacturer = moduleInfo.getManufacturerMappingValue();
				String pvModuleModEl = moduleInfo.getModelMappingValue();

				if (pvModuleManufacturer != null && pvModuleManufacturer.length() > 25) {
					form.setField(sheetIndex + MODULE + "MANUFACTURER-Auto", pvModuleManufacturer);
				} else {
					form.setField(sheetIndex + MODULE + "MANUFACTURER", pvModuleManufacturer);
				}
				if (pvModuleModEl != null && pvModuleModEl.length() > 30) {
					form.setField(sheetIndex + MODULE + "MODEL-NUMBER-Auto", pvModuleModEl);
				} else {
					form.setField(sheetIndex + MODULE + "MODEL-NUMBER", pvModuleModEl);
				}

				if (checkValue.NotEquals(moduleInfo.getWeight(), "") && moduleInfo.getWeight().contains(",")) {
					form.setField(sheetIndex + MODULE + "WEIGHT", moduleInfo.getWeight().replace(",", "."));
				} else
					form.setField(sheetIndex + MODULE + "WEIGHT", moduleInfo.getWeight());

				String width = getModuleInfo(moduleInfo.getWidth());
				String depth = getModuleInfo(moduleInfo.getDepth());
				String length = getModuleInfo(moduleInfo.getLength());
				String iScRef = getModuleInfo(moduleInfo.getiScRef());
				form.setField(sheetIndex + MODULE + "DIMENSIONS", length + " x " + width + " x " + depth);
				form.setField(sheetIndex + MODULE + "PEAK-POWER-@-STC-(PMAX)", moduleInfo.getStcRounded());
				form.setField(sheetIndex + MODULE + "Voc", getModuleInfo(moduleInfo.getvOcRef()));
				form.setField(sheetIndex + MODULE + "Vmp", getModuleInfo(moduleInfo.getvMpRef()));
				form.setField(sheetIndex + MODULE + "Isc", iScRef);
				form.setField(sheetIndex + MODULE + "imp", getModuleInfo(moduleInfo.getiMpRef()));
				if (checkValue.NotEquals(moduleInfo.getGammaR(), "")) {
					form.setField(sheetIndex + MODULE + "MFG-Voc-TEMP-COEFFICIENT",
							String.valueOf(new DecimalFormat("##.##")
									.format(Float.parseFloat(getModuleInfo(moduleInfo.getGammaR())))));
				} else {
					form.setField(sheetIndex + MODULE + "MFG-Voc-TEMP-COEFFICIENT", "");
				}
				String maxFuseRating = moduleInfo.getMaxSeriesFuseRating();
				if (checkValue.NotEquals(iScRef, "")) {
					if ((checkValue.Equals(permitArrays.getDeviceToIncorporate(), "Neither")
							|| checkValue.Equals(permitArrays.getDeviceToIncorporate(), "System Optimizer"))) {
						getFuseRating(iScRef, form, sheetIndex, maxFuseRating);
					} else {
						getMicroFuseRating(iScRef, form, sheetIndex, permitArrays, maxFuseRating);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getFuseRating(String iScRef, AcroFields form, int sheetIndex, String maxFuseRating) {
		try {
			float fuseRating = (float) (Float.parseFloat(iScRef) * 1.25 * 1.25);
			if(checkValue.isNumeric(maxFuseRating)) {
			form.setField(sheetIndex + MODULE + "MAX-SERIES-FUSE-RATING", maxFuseRating);
			}else if (fuseRating > 0) {				
					float theNumber = getFuseRating(fuseRating);
					form.setField(sheetIndex + MODULE + "MAX-SERIES-FUSE-RATING", theNumber + "");
				} else
					form.setField(sheetIndex + MODULE + "MAX-SERIES-FUSE-RATING", "N/A");		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getMicroFuseRating(String iScRef, AcroFields form, int sheetIndex,
			PermitArrayEntityResultSecond permitArrays, String maxFuseRating) {
		try {
			float fuseRating = (float) (Float.parseFloat(iScRef) * 1.25 * 1.25);
			if(checkValue.isNumeric(maxFuseRating)) {
				form.setField(sheetIndex + MODULE + "MAX-SERIES-FUSE-RATING", maxFuseRating);
			}else if (fuseRating > 0) {
				Float theNumber = getFuseRating(fuseRating);
				form.setField(sheetIndex + MODULE + "MAX-SERIES-FUSE-RATING", theNumber + "");				
			}else
				form.setField(sheetIndex + MODULE + "MAX-SERIES-FUSE-RATING", "N/A");
				
			if (fuseRating > 0) {
				Float theNumber = getFuseRating(fuseRating);
				if (checkValue.NotEquals(permitArrays.getMicroInverter(), "")
						&& checkValue.NotEquals(permitArrays.getMicroInverter(), "Fav Removed")
						&& checkValue.NotEquals(permitArrays.getMicroInverter(), "Removed")
						&& checkValue.Equals(permitArrays.getDeviceToIncorporate(), "Micro Inverter")) {
					form.setField(sheetIndex + "-" + "MICRO-INVERTER-1-MAX-INPUT-CURRENT", theNumber + "");
				}
			} else {
				if (checkValue.NotEquals(permitArrays.getMicroInverter(), "")
						&& checkValue.NotEquals(permitArrays.getMicroInverter(), "Fav Removed")
						&& checkValue.NotEquals(permitArrays.getMicroInverter(), "Removed")
						&& checkValue.Equals(permitArrays.getDeviceToIncorporate(), "Micro Inverter")) {
					form.setField(sheetIndex + "-" + "MICRO-INVERTER-1-MAX-INPUT-CURRENT", "N/A");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Float getFuseRating(Float fuseRating) {
		List<Float> list = Arrays
				.asList(1f, 3f, 6f, 10f, 15f, 20f, 25f, 30f, 35f, 40f, 45f, 50f, 60f, 70f, 80f, 90f, 100f, 110f, 125f,
						150f, 175f, 200f, 225f, 250f, 300f, 350f, 400f, 450f, 500f)
				.stream().filter(x -> x >= fuseRating).collect(Collectors.toList());
		return !list.isEmpty() ? list.get(0) : 1f;
	}

	public void moduleMicroMapping(Cmodulev2 moduleInfo, AcroFields form, PermitArrayEntityResultSecond permitArrays,
			int sheetIndex) {
		try {
			if (moduleInfo != null) {
				form.setField(sheetIndex + MODULE + "MANUFACTURER", moduleInfo.getManufacturerMappingValue());
				form.setField(sheetIndex + MODULE + "MODEL-NUMBER", moduleInfo.getModelMappingValue());

				String width = getModuleInfo(moduleInfo.getWidth());
				String depth = getModuleInfo(moduleInfo.getDepth());
				String length = getModuleInfo(moduleInfo.getLength());
				String iScRef = getModuleInfo(moduleInfo.getiScRef());
				form.setField(sheetIndex + MODULE + "WEIGHT", getModuleInfo(moduleInfo.getWeight()));
				form.setField(sheetIndex + MODULE + "DIMENSIONS", length + " x " + width + " x " + depth);
				form.setField(sheetIndex + MODULE + "PEAK-POWER-@-STC-(PMAX)", moduleInfo.getStcRounded());
				form.setField(sheetIndex + MODULE + "Voc", getModuleInfo(moduleInfo.getvOcRef()));
				form.setField(sheetIndex + MODULE + "Vmp", getModuleInfo(moduleInfo.getvMpRef()));
				form.setField(sheetIndex + MODULE + "Isc", iScRef);
				form.setField(sheetIndex + MODULE + "imp", getModuleInfo(moduleInfo.getiMpRef()));

				if (checkValue.NotEquals(moduleInfo.getGammaR(), "")) {
					form.setField(sheetIndex + MODULE + "MFG-Voc-TEMP-COEFFICIENT",
							String.valueOf(new DecimalFormat("##.##")
									.format(Float.parseFloat(getModuleInfo(moduleInfo.getGammaR())))));
				} else {
					form.setField(sheetIndex + MODULE + "MFG-Voc-TEMP-COEFFICIENT", "");

				}
				if (checkValue.NotEquals(iScRef, "")) {

					float fuseRating = (float) (Float.parseFloat(iScRef) * 1.25 * 1.25);

					if (fuseRating > 0) {

						float theNumber = getFuseRating(fuseRating);
						form.setField(sheetIndex + MODULE + "MAX-SERIES-FUSE-RATING", theNumber + "");
						if (checkValue.NotEquals(permitArrays.getMicroInverter(), "")
								&& checkValue.NotEquals(permitArrays.getMicroInverter(), "Fav Removed")
								&& checkValue.NotEquals(permitArrays.getMicroInverter(), "Removed")
								&& checkValue.Equals(permitArrays.getDeviceToIncorporate(), "Micro Inverter")) {
							form.setField(sheetIndex + "-" + "MICRO-INVERTER-1-MAX-INPUT-CURRENT", theNumber + "");
						}
					} else {
						form.setField(sheetIndex + MODULE + "MAX-SERIES-FUSE-RATING", "N/A");
						if (checkValue.NotEquals(permitArrays.getMicroInverter(), "")
								&& checkValue.NotEquals(permitArrays.getMicroInverter(), "Fav Removed")
								&& checkValue.NotEquals(permitArrays.getMicroInverter(), "Removed")
								&& checkValue.Equals(permitArrays.getDeviceToIncorporate(), "Micro Inverter")) {
							form.setField(sheetIndex + "-" + "MICRO-INVERTER-1-MAX-INPUT-CURRENT", "N/A");
						}
					}

				}

			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
		}
	}

	public String getModuleInfo(String detail) {
		if (detail != null && checkValue.NotEquals(detail, "")) {
			if (detail.contains(",")) {
				return detail.replace(',', '.');
			} else
				return detail;
		} else
			return "";
	}

}
