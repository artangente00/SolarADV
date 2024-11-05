package com.PlayGroundAdv.Solar.service.restore_project;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.model.PermitLayoutSketchResult;
import com.PlayGroundAdv.Solar.model.PermitSketchResults;

@Service
public class GetLayoutSketchTab {

	final SharedUtils sharedUtils;

	public GetLayoutSketchTab(SharedUtils sharedUtils) {
		super();
		this.sharedUtils = sharedUtils;
	}
	
	public PermitLayoutSketchResult getLayoutSketch(HSSFSheet sheet, PermitLayoutSketchResult entity) {
		try {
			int rowTotal = sheet.getLastRowNum();
			if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
				entity.setConduitRun(sharedUtils.findStringValue(sheet, "conduit_run"));
				entity.setShowConduitRoofAsHeight(sharedUtils.findBooleanValue(sheet, "show_conduit_roof_as_height"));
				entity.setSketchNote(sharedUtils.findStringValue(sheet, "sketch_note"));
				entity.setUploadCommentsLayout(sharedUtils.findStringValue(sheet, "upload_comments_layout"));
				entity.setUploadCommentsAddInfo(sharedUtils.findStringValue(sheet, "upload_comments_add_info"));
				entity.setIgnoreVents(sharedUtils.findBooleanValue(sheet, "ignore_vents"));
				entity.setFiresetbacks(sharedUtils.findBooleanValue(sheet, "firesetbacks"));
				entity.setFiresetbacksNote(sharedUtils.findStringValue(sheet, "firesetbacks_note"));
				entity.setFireVariance(sharedUtils.findBooleanValue(sheet, "fire_variance"));
				entity.setFireVarianceNote(sharedUtils.findStringValue(sheet, "fire_variance_note"));
				entity.setModulesEncroaching(sharedUtils.findBooleanValue(sheet, "modules_encroaching"));
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return new PermitLayoutSketchResult();
		}
	}

	public List<PermitSketchResults> getSketchByPermit(HSSFSheet sheet) {
		
		List<PermitSketchResults> sketchPermit = new ArrayList<>();
		try {
				
				int rowTotal = sheet.getLastRowNum();
				int c = 2;
				if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0) && sheet.getRow(1).getCell(c) != null) {
					while (sheet.getRow(1).getCell(c) != null) {
						String q = sheet.getRow(1).getCell(c).getStringCellValue();
						Integer arraySketch = q != null && !q.equals("") ? Integer.valueOf(q) : null;
						String b = sheet.getRow(5).getCell(c).getStringCellValue();
						Boolean moduleTils = b != null ? b.equals("t") : null;
						
						String azimuth = sheet.getRow(2).getCell(c).getStringCellValue();
						String roofPitch = sheet.getRow(6).getCell(c).getStringCellValue();
						String eaveOverHang = sheet.getRow(3).getCell(c).getStringCellValue();
						String eaveOverHangOther = sheet.getRow(8).getCell(c).getStringCellValue();
						String modelvalue = sheet.getRow(4).getCell(c).getStringCellValue();
						String moduleQty = sheet.getRow(9).getCell(c).getStringCellValue();
						String squareFootage = sheet.getRow(10).getCell(c).getStringCellValue();

						 PermitSketchResults array = new PermitSketchResults(arraySketch, azimuth, roofPitch, moduleTils,eaveOverHang,
								eaveOverHangOther, true,  modelvalue, moduleQty, squareFootage != null && squareFootage.matches("-?\\d+(\\.\\d+)?") ? Integer.valueOf(squareFootage) : 0);
						 sketchPermit.add(array);
						 c = c+1;
					}
				}else {
					sketchPermit = new ArrayList<>();
					sketchPermit.add(new PermitSketchResults(false));
				}
		} catch (Exception e) {
			e.printStackTrace();
			sketchPermit = new ArrayList<>();
			sketchPermit.add(new PermitSketchResults(false));
		}
		return sketchPermit;

	}
}
