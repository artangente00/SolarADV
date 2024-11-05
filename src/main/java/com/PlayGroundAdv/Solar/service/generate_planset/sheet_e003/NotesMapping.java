package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e003;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
public class NotesMapping {

	final CheckValueTypesService checkValue;

	public NotesMapping(CheckValueTypesService checkValue) {
		super();
		this.checkValue = checkValue;
	}

	public void noteMapping(AcroFields form, PermitProjectSiteInfoEntity permitProjectSiteInfo, int sheetIndex,
			String electNote, Boolean wireTapSetting) {
		try {
			if (permitProjectSiteInfo != null) {
				String busRating = "";
				String breakerRating = "";

				if (checkValue.Equals(permitProjectSiteInfo.getPanelBusRating(), "Other")) {
					busRating = permitProjectSiteInfo.getPanelBusRatingOther();
				} else
					busRating = permitProjectSiteInfo.getPanelBusRating();

				if (checkValue.Equals(permitProjectSiteInfo.getPanelMainBreakerRating(), "Other")) {
					breakerRating = permitProjectSiteInfo.getPanelMainBreakerRatingOther();
				} else
					breakerRating = permitProjectSiteInfo.getPanelMainBreakerRating();

				form.setField(sheetIndex + "-" + "E-MSP-DATA", busRating + "A BUS/" + breakerRating + "A MB");

//				 S.B 01/10/2020 CR-PM-3365-MOD-004
				form.setField(sheetIndex + "-" + "Text-Field", electNote);
				
				//F.B 04-12-2022 CR-PM-805
				if(Boolean.TRUE.equals(wireTapSetting) && permitProjectSiteInfo.getSolarLocation()!= null 
						&& (checkValue.Equals(permitProjectSiteInfo.getSolarLocation(), "Line-side tap") 
								|| checkValue.Equals(permitProjectSiteInfo.getSolarLocation(), "Load-side tap"))) {
					form.setField(sheetIndex + "-" + "Text-Field", "NO CENTER-FED MAIN BREAKER. PANEL CONFIGURED PER NEC 705.12(A) OR (B)");				
				}
			}
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}

	public void pointOfConnectionNoteMapping(PdfStamper stamper) {
		try {

			String[] EXTRA = { Constants.rapportPlansetFolderUrl + "Point of Connection Note.pdf" };
			PdfContentByte canvas = stamper.getUnderContent(1);
			PdfReader r;
			PdfImportedPage page;
			for (String path1 : EXTRA) {
				r = new PdfReader(path1);
				page = stamper.getImportedPage(r, 1);
				canvas.addTemplate(page, 877f, 280f);
				stamper.getWriter().freeReader(r);
				r.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void wiretapnoteMapping(AcroFields form, PermitProjectSiteInfoEntity permitProjectSiteInfo, int sheetIndex ) {
		try {
			if (permitProjectSiteInfo != null) {
				//W.A  14-07-2022 CR-859
				if  (checkValue.Equals(permitProjectSiteInfo.getSolarLocation(), "Line-side tap")) {
					form.setField(sheetIndex + "-" + "WIRE-TAP-NOTE", 
							"NOTE: ALL LINE SIDE PV CIRCUIT CONNECTIONS INVOLVING TAPS AND OR TRANSFORMERS SHALL INCORPORATE GROUNDING ELECTRODE CONDUCTORS (G-N BONDING) IN ALL LOCATIONS THAT ARE SEPARATELY DERIVED SYSTEMS AS REQUIRED BY CODE AND OR THE AHJ. GROUND BONDING JUMPERS (CONDUCTORS), TO CONNECT ANY APPLICABLE SEPARATELY DERIVED SYSTEMS SHALL BE FIELD VERIFIED BY QUALIFIED ELECTRICAL CONTRACTOR");				
				}
			}
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}

}
