package com.PlayGroundAdv.Solar.service.generate_planset.rsheets;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RsheetsLibraryEntity;
import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import com.PlayGroundAdv.Solar.repositories.PermitSketchRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.RsheetsLibraryRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class CreateGroundSheetName {

	final CheckValueTypesService checkValue;
	final PermitSketchRepository permitSketchRepo;
	final RsheetsLibraryRepository rSheetsLibraryRepo;
	final MissingSheetService missingSheetService;

	public CreateGroundSheetName(CheckValueTypesService checkValue, PermitSketchRepository permitSketchRepo,
			RsheetsLibraryRepository rSheetsLibraryRepo, MissingSheetService missingSheetService) {
		super();
		this.checkValue = checkValue;
		this.permitSketchRepo = permitSketchRepo;
		this.rSheetsLibraryRepo = rSheetsLibraryRepo;
		this.missingSheetService = missingSheetService;
	}

	public String groundRaiRacking(RailRacking railRackingGround, PermitAdvEntityResult permitAdvEntityInfo,
			PermitHomeSiteInfoEntity permitHomeSite, PermitEntity permitEntity,
			AuthentificationEntity userConnectedEntity, Long idUser, Long submitId) {

		try {

			String railModel = railRackingGround.getModel().trim();
			String railMake = railRackingGround.getManufacturer().trim();
			String projectState = permitHomeSite.getState();
			String moduleLayout = "";
			String sizeOfPipe = "";
			String thiknessOfPipe = "";
			String footingDiameter = "";
			String exposureCategory = "";
			String winSpeedItem = "";
			String snowLoadItem = "";
			String bracedUnbraced = "";
			String tiltRange = "";

			if (permitAdvEntityInfo != null && checkValue.NotEquals(permitAdvEntityInfo.getSizeOfPipe(), "")) {
				if (permitAdvEntityInfo != null && checkValue.Equals(permitAdvEntityInfo.getSizeOfPipe(), "Other")) {
					sizeOfPipe = permitAdvEntityInfo.getSizeOfPipeOther();
				} else {
					sizeOfPipe = permitAdvEntityInfo.getSizeOfPipe();
				}
			}
			if (permitAdvEntityInfo != null && checkValue.NotEquals(permitAdvEntityInfo.getThicknessOfPipe(), "")) {

				if (permitAdvEntityInfo != null
						&& checkValue.Equals(permitAdvEntityInfo.getThicknessOfPipe(), "Other")) {
					thiknessOfPipe = permitAdvEntityInfo.getThicknessOfPipeOther();
				} else if (permitAdvEntityInfo != null
						&& checkValue.Equals(permitAdvEntityInfo.getThicknessOfPipe(), "Not Factored")) {
					thiknessOfPipe = permitAdvEntityInfo.getThicknessOfPipe();
				} else {
					thiknessOfPipe = permitAdvEntityInfo.getThicknessOfPipe().split(" ")[1];
				}
			}
			if (permitAdvEntityInfo != null && checkValue.NotEquals(permitAdvEntityInfo.getModuleLayout(), "")) {
				if (permitAdvEntityInfo != null && checkValue.Equals(permitAdvEntityInfo.getModuleLayout(), "Other")) {
					moduleLayout = permitAdvEntityInfo.getModuleLayoutOther();
				} else {
					moduleLayout = permitAdvEntityInfo.getModuleLayout();
				}
			}
			if (permitAdvEntityInfo != null && checkValue.NotEquals(permitAdvEntityInfo.getBracedUnbraced(), "")) {
				if (permitAdvEntityInfo != null
						&& checkValue.Equals(permitAdvEntityInfo.getBracedUnbraced(), "Unbraced")) {
					bracedUnbraced = "UBRC";
				}
				if (permitAdvEntityInfo != null
						&& checkValue.Equals(permitAdvEntityInfo.getBracedUnbraced(), "Braced")) {
					bracedUnbraced = "BRC";
				}
				if (permitAdvEntityInfo != null
						&& checkValue.Equals(permitAdvEntityInfo.getBracedUnbraced(), "Other")) {
					bracedUnbraced = permitAdvEntityInfo.getBracedUnbracedOther();
				}
				if (permitAdvEntityInfo != null
						&& checkValue.Equals(permitAdvEntityInfo.getBracedUnbraced(), "Not Factored")) {
					bracedUnbraced = "Not Factored";
				}
			}
			if (permitAdvEntityInfo != null && checkValue.NotEquals(permitAdvEntityInfo.getFootingDiameter(), "")) {
				if (permitAdvEntityInfo != null
						&& checkValue.Equals(permitAdvEntityInfo.getFootingDiameter(), "Other")) {
					footingDiameter = permitAdvEntityInfo.getFootingDiameterOther();
				} else {
					footingDiameter = permitAdvEntityInfo.getFootingDiameter();
				}
			}
			if (permitHomeSite != null && checkValue.NotEquals(permitHomeSite.getResidenceBindingCategory(), "")) {
				if (permitHomeSite != null
						&& checkValue.Equals(permitHomeSite.getResidenceBindingCategory(), "Other")) {
					exposureCategory = permitHomeSite.getTextOtherExpo();
				} else if (permitHomeSite != null
						&& checkValue.Equals(permitHomeSite.getResidenceBindingCategory(), "Not Factored")) {
					exposureCategory = "Not Factored";
				} else {
					exposureCategory = permitHomeSite.getResidenceBindingCategory().split(" ")[1];
				}
			}
			if (permitAdvEntityInfo != null && checkValue.NotEquals(permitAdvEntityInfo.getWindSpeed(), "")) {
				if (permitAdvEntityInfo != null && checkValue.Equals(permitAdvEntityInfo.getWindSpeed(), "Other")) {
					winSpeedItem = permitAdvEntityInfo.getWindSpeedOther();
				} else {
					winSpeedItem = permitAdvEntityInfo.getWindSpeed();
				}
			}

			if (permitAdvEntityInfo != null && checkValue.NotEquals(permitAdvEntityInfo.getSnowLoad(), "")) {
				if (permitAdvEntityInfo != null && checkValue.Equals(permitAdvEntityInfo.getSnowLoad(), "Other")) {
					snowLoadItem = permitAdvEntityInfo.getSnowLoadOther();
				} else {
					snowLoadItem = permitAdvEntityInfo.getSnowLoad();
				}
			}
			List<PermitSketchEntity> sketch = permitSketchRepo.findByPermitEntityId(permitEntity.getId());
			if (!sketch.isEmpty()) {
				tiltRange = sketch.get(0).getModelvalue();
			}
		
			List<RsheetsLibraryEntity> groundRacking = rSheetsLibraryRepo.findGroundRacking(projectState, railMake,
					railModel, sizeOfPipe, thiknessOfPipe, moduleLayout, bracedUnbraced, footingDiameter,
					exposureCategory);

			final String ws = winSpeedItem;
			final String sl = snowLoadItem;
			final String tr = tiltRange;
			if (!groundRacking.isEmpty()) {
				// A.B 11-30-2022 CR1272-MOD-001 Matching Rail/Racking R-sheet
				List<RsheetsLibraryEntity> fullMatchWithState = groundRacking.stream()
						.filter(s -> checkValue.Equals(s.getState(), projectState)
								&& checkAverage(s.getWindSpeed(), s.getWindSpeedOther(), ws)
								&& checkAverage(s.getSnowLoad(), s.getSnowLoadOther(), sl)
								&& checkAverage(s.getTiltRange(), null, tr))
						.collect(Collectors.toList());
				if (!fullMatchWithState.isEmpty()) {
					return returnSheet(fullMatchWithState, projectState);
				} else {

					// A.B 11-30-2022 CR1272-MOD-001 Matching Rail/Racking R-sheet
					List<RsheetsLibraryEntity> fullMatch = groundRacking.stream()
							.filter(s -> !checkValue.isStringNotEmpty(s.getState())
									&& checkAverage(s.getWindSpeed(), s.getWindSpeedOther(), ws)
									&& checkAverage(s.getSnowLoad(), s.getSnowLoadOther(), sl)
									&& checkAverage(s.getTiltRange(), null, tr))
							.collect(Collectors.toList());
					if (!fullMatch.isEmpty()) {
						return returnSheet(fullMatch, projectState);
					} else {
						// A.B 11-30-2022 CR1272-MOD-001 Not Matching WS [OR] SL [OR] TR values
						List<RsheetsLibraryEntity> demiMatch = groundRacking.stream()
								.filter(s -> checkValue.isStringNotEmpty(s.getWindSpeed())
										&& checkValue.isStringNotEmpty(s.getSnowLoad())
										&& checkValue.isStringNotEmpty(s.getTiltRange())
										&& (checkAverage(s.getWindSpeed(), s.getWindSpeedOther(), ws)
												|| checkAverage(s.getSnowLoad(), s.getSnowLoadOther(), sl)
												|| checkAverage(s.getTiltRange(), null, tr)))
								.collect(Collectors.toList());
						if (!demiMatch.isEmpty()) {
							return returnSheet(demiMatch, projectState);
						} else {

							// A.B 11-30-2022 CR1272-MOD-001 Excludes the Tilt Range value
							List<RsheetsLibraryEntity> exTiltRange = groundRacking.stream()
									.filter(s -> checkAverage(s.getWindSpeed(), s.getWindSpeedOther(), ws)
											&& checkAverage(s.getSnowLoad(), s.getSnowLoadOther(), sl)
											&& !checkValue.isStringNotEmpty(s.getTiltRange()))
									.collect(Collectors.toList());
							if (!exTiltRange.isEmpty()) {
								return returnSheet(exTiltRange, projectState);
							} else {
								// A.B 11-30-2022 CR1272-MOD-001 Excludes the Wind speed and Snow load values
								List<RsheetsLibraryEntity> exWS_SL = groundRacking.stream()
										.filter(s -> !checkValue.isStringNotEmpty(s.getWindSpeed())
												&& !checkValue.isStringNotEmpty(s.getSnowLoad())
												&& checkAverage(s.getTiltRange(), null, tr))
										.collect(Collectors.toList());
								if (!exWS_SL.isEmpty()) {
									return returnSheet(exWS_SL, projectState);
								} else {
									// A.B 11-30-2022 CR1272-MOD-001 Excludes the Wind speed and Snow load and Tilt
									// Range values
									List<RsheetsLibraryEntity> exWS_SL_TR = groundRacking.stream()
											.filter(s -> !checkValue.isStringNotEmpty(s.getWindSpeed())
													&& !checkValue.isStringNotEmpty(s.getSnowLoad())
													&& !checkValue.isStringNotEmpty(s.getTiltRange()))
											.collect(Collectors.toList());
									if (!exWS_SL_TR.isEmpty()) {
										return returnSheet(exWS_SL_TR, projectState);
									} else {
										// A.B 11-30-2022 CR1272-MOD-001 Not Matching WS [AND] SL [AND] TR values
										return returnSheet(groundRacking, projectState);
									}
								}
							}
						}
					}
				}
			}
			// Log Missing Ground R sheet
			String missingSheet = projectState + "_" + "RACK_" + railMake + "_" + railModel + " (Intg. Gnd" + "_"
					+ sizeOfPipe + "_Sch" + thiknessOfPipe + "_" + moduleLayout + "upL_" + bracedUnbraced + "_"
					+ footingDiameter + "_EX-" + exposureCategory + "_WS" + winSpeedItem + "_SL" + snowLoadItem + "_TR"
					+ tiltRange + ").pdf";
			String notifMsg = missingSheetService.createRsheetNotifMessage(railRackingGround.getManufacturer(),
					railRackingGround.getModel(), userConnectedEntity.getFirstName(), userConnectedEntity.getLastName(),
					permitEntity.getProjectName(), permitEntity.getHomeOwnName(), permitEntity.getHomeOwnLastName());
			missingSheetService.missingRSheet(idUser, "Missing Racking & Rail R-sheet", notifMsg, true, missingSheet,
					"Ground Rail Racking R-sheet", submitId, permitEntity, userConnectedEntity);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private String returnSheet(List<RsheetsLibraryEntity> array, String state) {
		if (array.stream().anyMatch(s -> checkValue.Equals(s.getState(), state))) {
			return array.stream().filter(s -> checkValue.Equals(s.getState(), state)).collect(Collectors.toList())
					.get(0).getPdfName();
		} else {
			return array.get(0).getPdfName();
		}
	}

	private Boolean checkAverage(String interval, String other, String v) {
		return checkValue.isStringDouble(v) && ((checkValue.Equals(interval, "Other") && checkValue.Equals(other, v))
				|| (checkValue.Equals(interval, "0") && checkValue.Equals(v, "0"))
				|| checkValue.contains(interval, "-")
						&& (Double.parseDouble(interval.split("-")[0]) <= Double.parseDouble(v)
								&& Double.parseDouble(v) <= Double.parseDouble(interval.split("-")[1])));
	}

}
