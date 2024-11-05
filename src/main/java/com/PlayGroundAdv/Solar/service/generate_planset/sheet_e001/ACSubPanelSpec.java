package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e001;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.ProposedSubPanel;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.repositories.libraries.ProposedSubPanelRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ACSubPanelSpec {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final ProposedSubPanelRepository proposedSubPanelRepo;

	public ACSubPanelSpec(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			ProposedSubPanelRepository proposedSubPanelRepo) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		this.proposedSubPanelRepo = proposedSubPanelRepo;
	}

	public void acSubPanelMapping(PermitProjectSiteInfoEntity siteInfo, AcroFields form, List<Inverters> inverters,
			int sheetIndex) {
		try {
			if (siteInfo != null && checkValue.Equals(siteInfo.getThepontOfTheC(), "Sub Panel")) {

				form.setField(sheetIndex + "-AC-SUB-PANEL-1-NEW-OR-EXISTING", siteInfo.getConnectionPoint());

				if (checkValue.isNumericNotZero(siteInfo.getProposedSubPanel())) {
					subPanelModelMapping(form, sheetIndex, Long.valueOf(siteInfo.getProposedSubPanel()));
				}
				
				subPanelBreakerMapping(form, sheetIndex, siteInfo);
				
				// ************************ ADV-PR-279 *******************//
				if (checkValue.NotEquals(siteInfo.getSolarLocation(), "")
						&& checkValue.Equals(siteInfo.getSolarLocation(), "Back-fed Breaker")
						&& (inverters != null && !inverters.isEmpty() && inverters.get(0) != null)) {
					
					if (checkValue.Equals(siteInfo.getSolarInterconnection(), "Other")) {
						form.setField(sheetIndex + "-STRING-INVERTER-1-MIN-OCPD-AMPS1111",
								siteInfo.getSolarInterconnectionOther());
					} else if (checkValue.NotEquals(siteInfo.getSolarInterconnection(), "")) {
						form.setField(sheetIndex + "-STRING-INVERTER-1-MIN-OCPD-AMPS1111",
								siteInfo.getSolarInterconnection());
					} else {
						form.setField(sheetIndex + "-STRING-INVERTER-1-MIN-OCPD-AMPS1111", "");
					}
					
					if (checkValue.NotEquals(siteInfo.getSecondSolarInterconnection(), "")
							&& checkValue.Equals(siteInfo.getSolarLocation(), "Back-fed Breaker")
							&& (checkValue.Equals(siteInfo.getCombiningACCircuits(),
									"An Existing Main or Sub Panel with More Than One Back-Fed Breaker")
									|| checkValue.Equals(siteInfo.getCombiningACCircuits(),
											"A Proposed Main or Sub Panel with More Than One Back-Fed Breaker"))) {
						if (checkValue.Equals(siteInfo.getSecondSolarInterconnection(), "Other")) {
							form.setField(sheetIndex + "-STRING-INVERTER-2-MIN-OCPD-AMPS2222",
									siteInfo.getSecondSolarInterconnectionOther());
						} else if (checkValue.NotEquals(siteInfo.getSecondSolarInterconnection(), "")) {
							form.setField(sheetIndex + "-STRING-INVERTER-2-MIN-OCPD-AMPS2222",
									siteInfo.getSecondSolarInterconnection());
						} else {
							form.setField(sheetIndex + "-STRING-INVERTER-2-MIN-OCPD-AMPS2222", "");
						}
					}
				}

			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void acSubPanelMico(PermitProjectSiteInfoEntity siteInfo, AcroFields form,
			PermitArrayEntityResultSecond arrays, int sheetIndex) {
		try {
			if (siteInfo != null && checkValue.Equals(siteInfo.getThepontOfTheC(), "Sub Panel")) {
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-NEW-OR-EXISTING", siteInfo.getConnectionPoint());

				if (checkValue.isNumericNotZero(siteInfo.getProposedSubPanel())) {
					subPanelModelMapping(form, sheetIndex, Long.valueOf(siteInfo.getProposedSubPanel()));
				}
				
				subPanelBreakerMapping(form, sheetIndex, siteInfo);
				
				if (arrays != null && checkValue.NotEquals(arrays.getOcpdOne(), "")
						&& checkValue.Equals(arrays.getDeviceToIncorporate(), "Micro Inverter")
						&& (checkValue.Equals(siteInfo.getCombiningACCircuits(),
								"An Existing Main or Sub Panel with More Than One Back-Fed Breaker")
								|| checkValue.Equals(siteInfo.getCombiningACCircuits(),
										"A Proposed Main or Sub Panel with More Than One Back-Fed Breaker"))) {
					
					form.setField(sheetIndex + "-MICRO-INVERTER-1-MIN-OCPD-AMPS4", arrays.getOcpdOne());
					form.setField(sheetIndex + "-MICRO-INVERTER-2-MIN-OCPD-AMPS4", arrays.getOcpdTwo());
				
				} else if (checkValue.NotEquals(siteInfo.getSolarInterconnection(), "")) {
					if (checkValue.NotEquals(siteInfo.getSolarInterconnection(), "Other")) {
						form.setField(sheetIndex + "-MICRO-INVERTER-1-MIN-OCPD-AMPS4",
								siteInfo.getSolarInterconnection());
					} else {
						form.setField(sheetIndex + "-MICRO-INVERTER-1-MIN-OCPD-AMPS4",
								siteInfo.getSolarInterconnectionOther());
					}
				} else {
					form.setField(sheetIndex + "-MICRO-INVERTER-1-MIN-OCPD-AMPS4", "");
					form.setField(sheetIndex + "-MICRO-INVERTER-2-MIN-OCPD-AMPS4", "");
				}

			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private void subPanelModelMapping(AcroFields form, int sheetIndex, Long id) {
		try {
			ProposedSubPanel proposedSubPanel = proposedSubPanelRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("No Found"));
			if (proposedSubPanel != null) {
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-MANUFACTURER-AND-MODULE",
						proposedSubPanel.getManufacturer() + "-" + proposedSubPanel.getModel());
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-TYPE-OF-SUB-PANEL", proposedSubPanel.getTypeSubPanel());
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-NUMBER-OF-POLES", proposedSubPanel.getPolesNumber());
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-NEMA-RATING", proposedSubPanel.getNemaRating());
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-RATED-CURRENT", proposedSubPanel.getRatedCurrent());
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-SUM-OF-EXISTING-PANEL-BREAKER-AMP-RATING",
						proposedSubPanel.getRatedCurrent());
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private void subPanelBreakerMapping(AcroFields form, int sheetIndex, PermitProjectSiteInfoEntity siteInfo) {
		try {
			float bussBarRating = 0f;
			if (checkValue.Equals(siteInfo.getSubPanelBusRating(), "Other")
					&& checkValue.isNumeric(siteInfo.getSubPanelBusRatingOther())) {
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-BUSS-BAR-RATING", siteInfo.getSubPanelBusRatingOther());
				bussBarRating = Float.parseFloat(siteInfo.getSubPanelBusRatingOther());
			} else if (checkValue.isNumeric(siteInfo.getSubPanelBusRating())) {
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-BUSS-BAR-RATING", siteInfo.getSubPanelBusRating());
				bussBarRating = Float.parseFloat(siteInfo.getSubPanelBusRating());
			} else
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-BUSS-BAR-RATING", "");

			float subPanelMainBreaker = -1f;
			if (checkValue.isNumeric(siteInfo.getIfApplicableSubPanelMainBreakerRating())) {
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-MAIN-BREAKER-RATING",
						siteInfo.getIfApplicableSubPanelMainBreakerRating());
				subPanelMainBreaker = Float.parseFloat(siteInfo.getIfApplicableSubPanelMainBreakerRating());
			} else
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-MAIN-BREAKER-RATING", "");

			float subPanelBreakerOCPD = -1f;
			if (checkValue.Equals(siteInfo.getSubPanelBreakerOCPD(), "Other")
					&& checkValue.isNumeric(siteInfo.getSubPanelBreakerOCPDOther())) {
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-POC-BREAKER-AMP-RATING",
						siteInfo.getSubPanelBreakerOCPDOther());
				subPanelBreakerOCPD = Float.parseFloat(siteInfo.getSubPanelBreakerOCPDOther());
			} else if (checkValue.isNumeric(siteInfo.getSubPanelBreakerOCPD())) {
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-POC-BREAKER-AMP-RATING", siteInfo.getSubPanelBreakerOCPD());
				subPanelBreakerOCPD = Float.parseFloat(siteInfo.getSubPanelBreakerOCPD());
			} else
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-POC-BREAKER-AMP-RATING", "");

			float minInverterOutputCurrent = checkValue.getMinFloat(subPanelBreakerOCPD, subPanelMainBreaker);

			if (bussBarRating > 0) {
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-MAX-ALLOWABLE-SOLAR-AMPS", String
						.valueOf(new DecimalFormat("##.##").format((bussBarRating * 1.2) - minInverterOutputCurrent)));
			} else {
				form.setField(sheetIndex + "-AC-SUB-PANEL-1-MAX-ALLOWABLE-SOLAR-AMPS", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}
}
