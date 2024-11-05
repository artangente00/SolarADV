package com.PlayGroundAdv.Solar.service.generate_planset.title_block;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.model.TitleBlockValues;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
@Transactional
public class UpdateTitlePlanSetBlockCN {

	final CheckValueTypesService checkValueTypesService;
	final GetPDFReaderService getPDFReaderService;

	public UpdateTitlePlanSetBlockCN(CheckValueTypesService checkValueTypesService, 
			GetPDFReaderService getPDFReaderService) {
		super();
		this.checkValueTypesService = checkValueTypesService;
		this.getPDFReaderService = getPDFReaderService;
	}

	public void updateTitleBlockCN(String[] fieldsName, String src, String dest, TitleBlockValues titleBlockValues)
			throws Exception {

		try {
			PdfReader reader = new PdfReader(src);
			java.io.File fil = new File(dest);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fil));
			AcroFields form = stamper.getAcroFields();
			BaseFont bold = BaseFont.createFont("C:/windows/fonts/calibrib.ttf", BaseFont.WINANSI, true);
			stamper.getAcroFields().setFieldProperty("CONTRACTOR-CN", "textfont", bold, null);
			for (int i = 0; i < fieldsName.length; i++) {

				if (fieldsName[i].equals("CONTRACTOR-CN")) {

					form.setField(fieldsName[i], titleBlockValues.getContractorCn());

				} else if (fieldsName[i].equals("CONTRACTOR-LN")) {

					form.setField(fieldsName[i], titleBlockValues.getContractorLn());

				} else if (fieldsName[i].equals("CONTRACTOR-AD1")) {

					form.setField(fieldsName[i], titleBlockValues.getContractorAd1());

				} else if (fieldsName[i].equals("CONTRACTOR-AD2")) {

					form.setField(fieldsName[i], titleBlockValues.getContractorAd2());

				} else if (fieldsName[i].equals("CONTRACTOR-PH")) {

					form.setField(fieldsName[i], titleBlockValues.getContractorPh());

				} else if (fieldsName[i].equals("kW-SYSTEM-SIZE")) {

					form.setField(fieldsName[i], titleBlockValues.getKwSystemSize());

				} else if (fieldsName[i].equals("HOMEOWNER-NAME1")) {

					form.setField(fieldsName[i], titleBlockValues.getHomeownerName1());

				} else if (fieldsName[i].equals("HOMEOWNER-AD1")) {

					form.setField(fieldsName[i], titleBlockValues.getHomeownerAd1());

				} else if (fieldsName[i].equals("HOMEOWNER-AD2")) {

					form.setField(fieldsName[i], titleBlockValues.getHomeownerAd2());

				} else if (fieldsName[i].equals("PROJECT-APN")) {

					form.setField(fieldsName[i], titleBlockValues.getProjectApn());

				} else if (fieldsName[i].equals("SUMBMITTALDATE")) {
					form.setField(fieldsName[i], titleBlockValues.getSumbmittaldate());

				} else if (fieldsName[i].equals("SUBMITTALFORPERMIT")) {

					form.setField(fieldsName[i], titleBlockValues.getSubmittalforpermit());

				} else if (fieldsName[i].equals("REVISION-1")) {

					form.setField(fieldsName[i], titleBlockValues.getRevision1());

				} else if (fieldsName[i].equals("REVISION-1-DATE")) {
					form.setField(fieldsName[i], titleBlockValues.getRevision1Date());

				} else if (fieldsName[i].equals("REVISION-1-SUBMITTAL-NAME")) {
					form.setField(fieldsName[i], titleBlockValues.getRevision1());

				} else if (fieldsName[i].equals("REVISION-2")) {
					form.setField(fieldsName[i], titleBlockValues.getRevision2());

				} else if (fieldsName[i].equals("REVISION-2-DATE")) {
					form.setField(fieldsName[i], titleBlockValues.getRevision2Date());

				} else if (fieldsName[i].equals("REVISION-2-SUBMITTAL-NAME")) {
					form.setField(fieldsName[i], titleBlockValues.getRevision2submittalName());

				}

			}

			stamper.close();
			reader.close();

		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}

	}

}
