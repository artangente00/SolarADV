package com.PlayGroundAdv.Solar.service.export_project;

import java.util.LinkedHashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;
import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.repositories.PermitSketchRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ExportLayoutSketch {

	final CheckValueTypesService checkValueTypes;
	final PermitSketchRepository sketchRepo;

	public ExportLayoutSketch(CheckValueTypesService checkValueTypes, PermitSketchRepository sketchRepo) {
		super();
		this.checkValueTypes = checkValueTypes;
		this.sketchRepo = sketchRepo;
	}

	public LinkedHashMap<String, String> layoutSketch(GetPermitByIdResult permit) {

		LinkedHashMap<String, String> layout = new LinkedHashMap<>();

		try {

			layout.put("Upload Comments",
					checkValueTypes.convert(permit.getPermitArraysEntity().getUploadCommentsLayout()));
			if (permit.getLayoutSketch().getIgnoreVents() != null) {
				layout.put(
						"Ignore Vents in layout.  We are moving them, able to work around them or allowed to cover them",
						Boolean.TRUE.equals(permit.getLayoutSketch().getIgnoreVents()) ? "Yes" : "No");
			} else {
				layout.put(
						"Ignore Vents in layout.  We are moving them, able to work around them or allowed to cover them",
						"No");
			}

			if (permit.getLayoutSketch().getFiresetbacks() != null) {
				layout.put("Fire setbacks do not apply to this structure",
						Boolean.TRUE.equals(permit.getLayoutSketch().getFiresetbacks()) ? "Yes" : "No");
			} else {
				layout.put("Fire setbacks do not apply to this structure", "No");
			}

			if (checkValueTypes.NotEquals(permit.getLayoutSketch().getFiresetbacksNote(), "")) {
				layout.put(
						"Insert or enter the note if any you wish to display on your plan set sheet that indicates all Fire Setbacks",
						checkValueTypes.convert(permit.getLayoutSketch().getFiresetbacksNote()));

			} else {
				layout.put(
						"Insert or enter the note if any you wish to display on your plan set sheet that indicates all Fire Setbacks",
						"");

			}

			if (permit.getLayoutSketch().getFireVariance() != null) {
				layout.put(
						"We will be requesting a fire variance.  Please show layout encroaching on setbacks as drawn",
						Boolean.TRUE.equals(permit.getLayoutSketch().getFireVariance()) ? "Yes" : "No");
			} else {
				layout.put(
						"We will be requesting a fire variance.  Please show layout encroaching on setbacks as drawn",
						"No");
			}

			if (checkValueTypes.NotEquals(permit.getLayoutSketch().getFireVarianceNote(), "")) {
				layout.put("What note do you want displayed on the layout sheet for fire variance?",
						checkValueTypes.convert(permit.getLayoutSketch().getFireVarianceNote()));
			} else {
				layout.put("What note do you want displayed on the layout sheet for fire variance?", "");
			}

			if (permit.getLayoutSketch().getModulesEncroaching() != null) {
				layout.put(
						"My sketch shows Modules encroaching into the Fire Setback areas, but they do fit.  Size or scale them to fit without violating Fire Setbacks.",
						Boolean.TRUE.equals(permit.getLayoutSketch().getModulesEncroaching()) ? "Yes" : "No");
			} else {
				layout.put(
						"My sketch shows Modules encroaching into the Fire Setback areas, but they do fit.  Size or scale them to fit without violating Fire Setbacks.",
						"No");

			}
			List<PermitSketchEntity> permitSketchEntity = sketchRepo
					.findByPermitEntityId(permit.getPermitEntity().getId());
			if (permitSketchEntity != null) {

				int i = 1;

				for (PermitSketchEntity sketch : permitSketchEntity) {
					layout.put("Azimuth " + i, checkValueTypes.convert(sketch.getAzimuth()));
					layout.put("Roof Pitch " + i, checkValueTypes.convert(sketch.getRoofPitch()));
					layout.put("Tilt Kit Used " + i, Boolean.TRUE.equals(sketch.getModuleTils()) ? "Yes" : "No");
					layout.put("Module Degree Tilt " + i, checkValueTypes.convert(sketch.getModelvalue()));
					layout.put("Eave Overhang " + i, checkValueTypes.convert(sketch.getEaveOverHang()));
					layout.put("Eave Overhang Other " + i, checkValueTypes.convert(sketch.getEaveOverHangOther()));
					layout.put("Module Qty " + i, checkValueTypes.convert(sketch.getModuleQty()));
					layout.put("Square Footage " + i, checkValueTypes.convert(sketch.getSquareFootage()+""));

					i++;
				}

			}
			layout.put("Sketch Note",
					checkValueTypes.convert(permit.getLayoutSketch().getSketchNote()));
			layout.put("Additional Information Upload Comments",
					checkValueTypes.convert(permit.getPermitArraysEntity().getUploadCommentsAddInfo()));
			
			layout.put("Will Conduit be run in attic or on roof",
					checkValueTypes.convert(permit.getLayoutSketch().getConduitRun()));
			layout.put("Please show Conduit on Roof as Height to avoid NEC Temperature Adder",
					Boolean.TRUE.equals(permit.getLayoutSketch().getShowConduitRoofAsHeight()) ? "Yes" : "No");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return layout;
	}

}
