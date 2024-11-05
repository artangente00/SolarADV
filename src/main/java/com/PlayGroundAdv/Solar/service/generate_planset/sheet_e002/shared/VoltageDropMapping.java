package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.model.planset_utils.VoltageDropTable;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
public class VoltageDropMapping {

	public void mapVoltageDrop(int sheetIndex, Long projectId, List<VoltageDropTable> voltageDrop) {
		try {
//			 A.B 12-01-2022 REV-CR-PM-3227-0011 Sort Higher VD value first
			voltageDrop.sort((p1, p2) -> p2.getVd().compareTo(p1.getVd()));
			for (VoltageDropTable v : voltageDrop) {
				System.out.println(v.acDc+" - "+v.circuitOrigin+" ==> "+v.circuitDestination+" = "+v.vd);
			}
			File fileRe = new File(
					Constants.rapportPlansetFolderUrl + "PDF-Voltage-Drop-" + projectId + "-" + sheetIndex + ".pdf");
			if (fileRe.exists()) {
				fileRe.delete();
				fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-Voltage-Drop-" + projectId + "-" + sheetIndex
						+ ".pdf");
			}
			PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + "Voltage Drop Table.pdf");
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
			AcroFields form = stamper.getAcroFields();

			form.setField("VD_ORG_TO_DEST",
					voltageDrop.get(0).getCircuitOrigin() + " TO " + voltageDrop.get(0).getCircuitDestination());
			form.setField("VD_AC/DC", voltageDrop.get(0).getAcDc());
			form.setField("VD_Value",
					String.valueOf(new DecimalFormat("##.##").format(voltageDrop.get(0).getVd())) + "%");
			
//			 A.B 12-01-2022 REV-CR-PM-3227-0011: Map Values to Voltage Drop Table
			if (voltageDrop.size() > 1) {
				form.setField("VD_ORG_TO_DEST2",
						voltageDrop.get(1).getCircuitOrigin() + " TO " + voltageDrop.get(1).getCircuitDestination());
				form.setField("VD_AC/DC2", voltageDrop.get(1).getAcDc());
				form.setField("VD_Value2",
						String.valueOf(new DecimalFormat("##.##").format(voltageDrop.get(1).getVd())) + "%");
				if (voltageDrop.get(0).getAcDc().equals(voltageDrop.get(1).getAcDc())) {
					form.setField("VD_AC/DC_Total", voltageDrop.get(1).getAcDc());
					form.setField("VD_Value_Total", String.valueOf(
							new DecimalFormat("##.##").format(voltageDrop.get(0).getVd() + voltageDrop.get(1).getVd()))
							+ "%");
				}
			} else {
				form.setField("VD_AC/DC_Total", voltageDrop.get(0).getAcDc());
				form.setField("VD_Value_Total",
						String.valueOf(new DecimalFormat("##.##").format(voltageDrop.get(0).getVd())) + "%");
			}

			stamper.setFormFlattening(true);
			stamper.close();
			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertVoltageDropTable(int sheetIndex, Long projectId, PdfStamper stamper, Float x, Float y) {
		try {
			String[] EXTRA = {
					Constants.rapportPlansetFolderUrl + "PDF-Voltage-Drop-" + projectId + "-" + sheetIndex + ".pdf" };
			PdfContentByte canvas = stamper.getUnderContent(1);
			PdfReader r;
			PdfImportedPage page;
			for (String path1 : EXTRA) {
				r = new PdfReader(path1);
				page = stamper.getImportedPage(r, 1);
				canvas.addTemplate(page, x, y);
				stamper.getWriter().freeReader(r);
				r.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
