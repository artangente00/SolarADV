package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e001;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class MainServicePanelSpec {
	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;

	public MainServicePanelSpec(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
	}

	public void mainServicePanelString(PermitProjectSiteInfoEntity siteInfo, AcroFields form,
			PermitHomeSiteInfoEntity permitHomeSite, List<Inverters> inverters, int sheetIndex) {
		try {
			if (siteInfo != null && permitHomeSite != null) {

				mainServicePanelMapping(siteInfo, form, permitHomeSite, sheetIndex);

				if (checkValue.Equals(siteInfo.getThepontOfTheC(), "Main Panel")
						&& checkValue.NotEquals(siteInfo.getSolarLocation(), "")
						&& checkValue.Equals(siteInfo.getSolarLocation(), "Back-fed Breaker")
						&& (inverters != null && inverters.size() > 0 && inverters.get(0) != null)) {
					if (checkValue.Equals(siteInfo.getSolarInterconnection(), "Other")) {
						form.setField(sheetIndex + "-STRING-INVERTER-1-MIN-OCPD-AMPS111",
								siteInfo.getSolarInterconnectionOther());
					} else if (checkValue.NotEquals(siteInfo.getSolarInterconnection(), "")) {
						form.setField(sheetIndex + "-STRING-INVERTER-1-MIN-OCPD-AMPS111",
								siteInfo.getSolarInterconnection());
					} else {
						form.setField(sheetIndex + "-STRING-INVERTER-1-MIN-OCPD-AMPS111", "");

					}
					if (checkValue.NotEquals(siteInfo.getSecondSolarInterconnection(), "")
							&& checkValue.Equals(siteInfo.getSolarLocation(), "Back-fed Breaker")
							&& (checkValue.Equals(siteInfo.getCombiningACCircuits(),
									"An Existing Main or Sub Panel with More Than One Back-Fed Breaker")
									|| checkValue.Equals(siteInfo.getCombiningACCircuits(),
											"A Proposed Main or Sub Panel with More Than One Back-Fed Breaker"))) {
						if (checkValue.Equals(siteInfo.getSecondSolarInterconnection(), "Other")) {
							form.setField(sheetIndex + "-STRING-INVERTER-2-MIN-OCPD-AMPS222",
									siteInfo.getSecondSolarInterconnectionOther());
						} else if (checkValue.NotEquals(siteInfo.getSecondSolarInterconnection(), "")) {
							form.setField(sheetIndex + "-STRING-INVERTER-2-MIN-OCPD-AMPS222",
									siteInfo.getSecondSolarInterconnection());
						} else {
							form.setField(sheetIndex + "-STRING-INVERTER-2-MIN-OCPD-AMPS222", "");
						}
					}
				}
			}

		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mainServicePanelMicro(PermitProjectSiteInfoEntity siteInfo, AcroFields form,
			PermitHomeSiteInfoEntity permitHomeSite, PermitArrayEntityResultSecond permitArraysEntityResult,
			int sheetIndex) {
		try {
			if (siteInfo != null && permitHomeSite != null) {

				mainServicePanelMapping(siteInfo, form, permitHomeSite, sheetIndex);

				if (checkValue.Equals(siteInfo.getThepontOfTheC(), "Main Panel")) {

					if (checkValue.NotEquals(siteInfo.getSolarInterconnection(), "") && (checkValue.Equals(
							siteInfo.getCombiningACCircuits(), "A Proposed AC Combiner Panel (Solar Load Center)")
							|| checkValue.Equals(siteInfo.getCombiningACCircuits(),
									"A Proposed AC Combiner Panel (Solar Load Center) MOUNTED AT THE ARRAY")
							|| checkValue.Equals(siteInfo.getCombiningACCircuits(),
									"A Proposed AC Combiner Panel (Solar Load Center) MOUNTED AT THE MAIN SERVICE PANEL"))) {
						
						if (checkValue.Equals(siteInfo.getSolarLocation(), "Back-fed Breaker")
								&& checkValue.NotEquals(permitArraysEntityResult.getDeviceToIncorporate(), "")) {
							if (checkValue.NotEquals(siteInfo.getSolarInterconnection(), "Other")) {
								form.setField(sheetIndex + "-MICRO-INVERTER-1-MIN-OCPD-AMPS6",
										siteInfo.getSolarInterconnection());
							} else {
								form.setField(sheetIndex + "-MICRO-INVERTER-1-MIN-OCPD-AMPS6",
										siteInfo.getSolarInterconnectionOther());
							}
						}
					} else if (checkValue.NotEquals(permitArraysEntityResult.getOcpdOne(), "")
							&& (checkValue.Equals(siteInfo.getCombiningACCircuits(),
									"An Existing Main or Sub Panel with More Than One Back-Fed Breaker")
									|| checkValue.Equals(siteInfo.getCombiningACCircuits(),
											"A Proposed Main or Sub Panel with More Than One Back-Fed Breaker"))) {
						form.setField(sheetIndex + "-MICRO-INVERTER-1-MIN-OCPD-AMPS6",
								permitArraysEntityResult.getOcpdOne());
						form.setField(sheetIndex + "-MICRO-INVERTER-2-MIN-OCPD-AMPS6",
								permitArraysEntityResult.getOcpdTwo());
					} else if (checkValue.NotEquals(siteInfo.getSolarInterconnection(), "")) {
						if (checkValue.Equals(siteInfo.getSolarLocation(), "Back-fed Breaker")
								&& checkValue.NotEquals(permitArraysEntityResult.getDeviceToIncorporate(), "")) {
							if (checkValue.NotEquals(siteInfo.getSolarInterconnection(), "Other")) {
								form.setField(sheetIndex + "-MICRO-INVERTER-1-MIN-OCPD-AMPS6",
										siteInfo.getSolarInterconnection());
							} else {
								form.setField(sheetIndex + "-MICRO-INVERTER-1-MIN-OCPD-AMPS6",
										siteInfo.getSolarInterconnectionOther());
							}
						}
					} else {
						form.setField(sheetIndex + "-MICRO-INVERTER-1-MIN-OCPD-AMPS6", "");
						form.setField(sheetIndex + "-MICRO-INVERTER-2-MIN-OCPD-AMPS6", "");

					}

				}
			}

		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private void mainServicePanelMapping(PermitProjectSiteInfoEntity siteInfo, AcroFields form,
			PermitHomeSiteInfoEntity permitHomeSite, int sheetIndex) {
		try {

			if (Boolean.TRUE.equals(siteInfo.getMainPanelUpgrade())) {
				form.setField(sheetIndex + "-MAIN-SERVICE-PANEL-1-NEW-OR-EXISTING", "NEW");
			} else
				form.setField(sheetIndex + "-MAIN-SERVICE-PANEL-1-NEW-OR-EXISTING", "EXISTING");

			if (Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())) {
				form.setField(sheetIndex + "-MAIN-SERVICE-PANEL-1-ELECTRICAL-SERVICE", "120/240V Single Phase");
			} else {
				if (checkValue.Equals(permitHomeSite.getServiceVoltage(), "Other")) {
					form.setField(sheetIndex + "-MAIN-SERVICE-PANEL-1-ELECTRICAL-SERVICE",
							permitHomeSite.getServiceVoltageOther());
				} else {
					form.setField(sheetIndex + "-MAIN-SERVICE-PANEL-1-ELECTRICAL-SERVICE",
							permitHomeSite.getServiceVoltage());
				}
			}
			if (checkValue.Equals(siteInfo.getPanelBusRating(), "Other")) {
				form.setField(sheetIndex + "-MAIN-SERVICE-PANEL-1-BUSS-BAR-RATING", siteInfo.getPanelBusRatingOther());
			} else if (checkValue.isStringNotEmpty(siteInfo.getPanelBusRating())) {
				form.setField(sheetIndex + "-MAIN-SERVICE-PANEL-1-BUSS-BAR-RATING", siteInfo.getPanelBusRating());
			}
			if (checkValue.Equals(siteInfo.getPanelMainBreakerRating(), "Other")) {
				form.setField(sheetIndex + "-MAIN-SERVICE-PANEL-1-MAIN-BREAKER-RATING",
						siteInfo.getPanelMainBreakerRatingOther());
			} else if (checkValue.isStringNotEmpty(siteInfo.getPanelMainBreakerRating())) {
				form.setField(sheetIndex + "-MAIN-SERVICE-PANEL-1-MAIN-BREAKER-RATING",
						siteInfo.getPanelMainBreakerRating());
			}

			Float panelBusRating = checkValue.Equals(siteInfo.getPanelBusRating(), "Other")
					? checkValue.getFloatValue(siteInfo.getPanelBusRatingOther())
					: checkValue.getFloatValue(siteInfo.getPanelBusRating());
			Float panelMainBreakerRating = checkValue.Equals(siteInfo.getPanelMainBreakerRating(), "Other")
					? checkValue.getFloatValue(siteInfo.getPanelMainBreakerRatingOther())
					: checkValue.getFloatValue(siteInfo.getPanelMainBreakerRating());

			if (panelBusRating > 0) {
				form.setField(sheetIndex + "-MAIN-SERVICE-PANEL-1-MAX-ALLOWABLE-SOLAR-AMPS-100-PERCENT-RULE", String
						.valueOf(new DecimalFormat("##.##").format((panelBusRating * 1.0) - panelMainBreakerRating)));
				form.setField(sheetIndex + "-MAIN-SERVICE-PANEL-1-MAX-ALLOWABLE-SOLAR-AMPS-120-PERCENT-RULE", String
						.valueOf(new DecimalFormat("##.##").format((panelBusRating * 1.2) - panelMainBreakerRating)));
			} else {
				form.setField(sheetIndex + "-MAIN-SERVICE-PANEL-1-MAX-ALLOWABLE-SOLAR-AMPS-100-PERCENT-RULE", "");
				form.setField(sheetIndex + "-MAIN-SERVICE-PANEL-1-MAX-ALLOWABLE-SOLAR-AMPS-120-PERCENT-RULE", "");
			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}
}
