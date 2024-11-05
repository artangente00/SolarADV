package com.PlayGroundAdv.Solar.service.generate_planset.title_block;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.AccountBuildEntity;
import com.PlayGroundAdv.Solar.model.TitleBlockValues;
import com.PlayGroundAdv.Solar.repositories.sheets.AccountBuildRepository;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

@Service
@Transactional
public class UpdateTitlePlanSetBlock {

	final UpdateTitlePlanSetBlockCN updateTitlePlanSetBlockCN;
	final AccountBuildRepository accountBuildRepository;

	public UpdateTitlePlanSetBlock(UpdateTitlePlanSetBlockCN updateTitlePlanSetBlockCN, AccountBuildRepository accountBuildRepository) {
		super();
		this.updateTitlePlanSetBlockCN = updateTitlePlanSetBlockCN;
		this.accountBuildRepository= accountBuildRepository;
	}

	public PdfArray getLocation(String fieldName) throws Exception {

		try {
			PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-PV001.pdf");
			PdfDocument pdfDoc = new PdfDocument(reader);
			PdfAcroForm.getAcroForm(pdfDoc, true);
			PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
			PdfArray position = form.getField(fieldName).getWidgets().get(0).getRectangle();
			pdfDoc.close();
			return position;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void updateTitleBlock(String src, String reslt, String dest,
			TitleBlockValues titleBlockValues) throws Exception {

		try {
			PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(reslt));
			PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);

			Map<String, PdfFormField> fldNames = form.getFormFields();
			List<PdfArray> positions = new ArrayList<>();
			List<String> fieldsName = new ArrayList<>();
			List<String> fieldsValue = new ArrayList<>();
			List<String> fieldsNameToRemove = new ArrayList<>();

			String[] TitleBlockFieldsName = { "CONTRACTOR-CN", "CONTRACTOR-LN", "CONTRACTOR-AD1", "CONTRACTOR-AD2",
					"CONTRACTOR-PH", "kW-SYSTEM-SIZE", "HOMEOWNER-NAME1", "HOMEOWNER-AD1", "HOMEOWNER-AD2",
					"PROJECT-APN", "SUMBMITTALDATE", "SUBMITTALFORPERMIT", "REVISION-1", "REVISION-1-DATE",
					"REVISION-1-SUBMITTAL-NAME", "REVISION-2", "REVISION-2-DATE", "REVISION-2-SUBMITTAL-NAME" };

			PdfFont normal = PdfFontFactory.createFont("C:/windows/fonts/calibri.ttf");

			for (Map.Entry<String, PdfFormField> entry : fldNames.entrySet()) {

				boolean contains = Arrays.stream(TitleBlockFieldsName).anyMatch(entry.getKey()::contains);
				boolean equals = Arrays.stream(TitleBlockFieldsName).anyMatch(entry.getKey()::equals);

				if (entry.getKey() != null && equals) {

					PdfArray position = form.getField(entry.getKey()).getWidgets().get(0).getRectangle();
					positions.add(position);
					fieldsName.add(entry.getKey());
					fieldsValue.add(form.getField(entry.getKey()).getValueAsString());

				}
				if (entry.getKey() != null && contains && !entry.getKey().contains("CONTRACTOR-PHONENUMBER"))
					fieldsNameToRemove.add(entry.getKey());

			}

			for (int i = 0; i < fieldsNameToRemove.size(); i++) 
				form.removeField(fieldsNameToRemove.get(i));
			
			
				

			for (int i = 0; i < TitleBlockFieldsName.length; i++) {

				PdfTextFormField field = PdfFormField.createText(pdfDoc,
						getLocation(TitleBlockFieldsName[i]).toRectangle(), TitleBlockFieldsName[i], "");

				field.setRotation(90);
				field.setVisibility(4);
				field.setFont(normal);

				if (TitleBlockFieldsName[i].equals("CONTRACTOR-CN")) {

					field.setJustification(PdfFormField.ALIGN_CENTER);
					field.setFontSize(11);

				} else if (TitleBlockFieldsName[i].equals("CONTRACTOR-LN")) {

					field.setJustification(PdfFormField.ALIGN_CENTER);
					field.setFontSize(10);

				} else if (TitleBlockFieldsName[i].contains("CONTRACTOR-AD")
						|| TitleBlockFieldsName[i].contains("CONTRACTOR-PH")) {

					field.setJustification(PdfFormField.ALIGN_RIGHT);
					field.setFontSize(10);

				} else if (TitleBlockFieldsName[i].contains("SUMBMITTAL")
						|| TitleBlockFieldsName[i].contains("SUBMITTAL")
						|| TitleBlockFieldsName[i].contains("REVISION")) {

					field.setJustification(PdfFormField.ALIGN_LEFT);
					field.setFontSize(10);

				} else if (TitleBlockFieldsName[i].contains("kW-SYSTEM-SIZE")) {

					field.setJustification(PdfFormField.ALIGN_RIGHT);
					field.setFontSize(11);

				} else if (!TitleBlockFieldsName[i].equals("CONTRACTOR-CN")
						&& !TitleBlockFieldsName[i].equals("CONTRACTOR-LN")
						&& !TitleBlockFieldsName[i].contains("CONTRACTOR-AD")
						&& !TitleBlockFieldsName[i].contains("CONTRACTOR-PH")
						&& !TitleBlockFieldsName[i].contains("SUMBMITTAL")
						&& !TitleBlockFieldsName[i].contains("REVISION")) {

					field.setJustification(PdfFormField.ALIGN_LEFT);
					field.setFontSize(11);

				}

				List<AccountBuildEntity> accountBuildSheets = accountBuildRepository.findByAccountAndIsDeleted(titleBlockValues.getOwnerId(), false);
				int pageCount=0;
				if (!accountBuildSheets.isEmpty()) {
					for (AccountBuildEntity accountBuildS : accountBuildSheets) {
						PdfReader documentReader =  new PdfReader(Constants.rapportPlansetFolderUrl + "AccountBuildSheets/" + accountBuildS.getId() + ".pdf");
						PdfDocument doc = new PdfDocument(documentReader);
						pageCount = pageCount + doc.getNumberOfPages();
						doc.close();
					}
				}
				for (int j = pageCount + 1; j < pdfDoc.getNumberOfPages() + 1; j++)
					form.addField(field, pdfDoc.getPage(j));

			}

			pdfDoc.close();

			updateTitlePlanSetBlockCN.updateTitleBlockCN(TitleBlockFieldsName, reslt, dest, titleBlockValues);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
