package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.micro;

import java.io.IOException;
import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSCircuitSpec;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.AmpacityCorrectionChart;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class DCCorrectedAmpacityMicro {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final AmpacityCorrectionChart ampCorrection;
	final NEC3106B16Repository nec3106B16Repo;

	public DCCorrectedAmpacityMicro(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			AmpacityCorrectionChart ampCorrection, NEC3106B16Repository nec3106b16Repo) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		this.ampCorrection = ampCorrection;
		this.nec3106B16Repo = nec3106b16Repo;
	}

	public String mapByLogic(AcroFields form, int sheetIndex, ESSCircuitSpec circuit, PermitEntity permitEntity,
			String ampacityCorrectionB2a) {
		try {
			form.setField(sheetIndex + "-" + "DC1-NUMBER-OF-UNGROUNDED-CONDUCTORS", "2");
			form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "N/A");
			form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "1.0");
			String correctedAmpacity = "";
			if ((circuit != null && checkValue.Equals(circuit.getExisting(), true))
					|| (permitEntity != null && checkValue.Equals(permitEntity.getExistModule(), true))) {
				form.setField(sheetIndex + "-" + "DC1-TRADE-SIZE", "EXISTING");
			} else if (circuit != null && checkValue.Equals(circuit.getConductorSize(), "EXIST")) {
				form.setField(sheetIndex + "-" + "DC1-TRADE-SIZE", "EXISTING");
			} else {
				form.setField(sheetIndex + "-" + "DC1-TRADE-SIZE", "#12 AWG");
			}

			if ((circuit != null && checkValue.Equals(circuit.getExisting(), true))
					|| (permitEntity != null && checkValue.Equals(permitEntity.getExistModule(), true))) {
				form.setField(sheetIndex + "-" + "DC1-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
			} else {
				form.setField(sheetIndex + "-" + "DC1-CONDUCTOR-AMPACTIY-RATING", "30");
			}

			form.setField(sheetIndex + "-" + "DC1-MATERIAL", "(1) CU");
			form.setField(sheetIndex + "-" + "DC1-CONDUCTOR-AMPACTIY-RATING1", "30");
			if (checkValue.isNumeric(ampacityCorrectionB2a)) {
				form.setField(sheetIndex + "-" + "DC1-CORRECTED-AMPACITY",
						String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(ampacityCorrectionB2a) * 30)));
				form.setField(sheetIndex + "-" + "DC1-CORRECTED-AMPACITY1",
						String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(ampacityCorrectionB2a) * 30)));
				correctedAmpacity = String
						.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(ampacityCorrectionB2a) * 30));
			}
			return correctedAmpacity;
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return "";
		}
	}

	public String mapFromPortal(AcroFields form, int sheetIndex, ESSCircuitSpec circuit, PermitEntity permitEntity,
			String ampacityCorrectionB2a, E002Model params) {
		String correctedAmpacity = "";

		try {
			String conductorSize = "";
			if (circuit != null && checkValue.NotEquals(circuit.getConductorSize(), "")) {
				if (checkValue.Equals(circuit.getConductorSize(), "Other")) {
					form.setField(sheetIndex + "-DC1-TRADE-SIZE", "#" + circuit.getConductorSizeOther() + " AWG");
					conductorSize = circuit.getConductorSizeOther();
				} else if (checkValue.Equals(circuit.getConductorSize(), "Per Manufacturer")) {
					form.setField(sheetIndex + "-DC1-TRADE-SIZE", "#12 AWG");
					conductorSize = "#12 AWG";
				} else {
					form.setField(sheetIndex + "-DC1-TRADE-SIZE", circuit.getConductorSize());
					conductorSize = circuit.getConductorSize();
				}
			}

			if (checkValue.Equals(conductorSize, "#12 AWG")) {
				if (checkValue.Equals(circuit.getConductorQty(), "Other")) {
					form.setField(sheetIndex + "-DC1-NUMBER-OF-UNGROUNDED-CONDUCTORS",
							circuit.getConductorQtyOther() + "");
					form.setField(sheetIndex + "-DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
							ampCorrection.dcAmpacityCorrectionB3a(circuit, circuit.getConductorQtyOther(), 1, false));
					form.setField(sheetIndex + "-DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
							ampCorrection.dcAmpacityCorrectionB3a(circuit, circuit.getConductorQtyOther(), 1, true));
					params.setConduitFillDerating(
							ampCorrection.dcAmpacityCorrectionB3a(circuit, circuit.getConductorQtyOther(), 1, true));
				} else if (checkValue.NotEquals(circuit.getConductorQty(), "")) {
					form.setField(sheetIndex + "-DC1-NUMBER-OF-UNGROUNDED-CONDUCTORS", circuit.getConductorQty());
					form.setField(sheetIndex + "-DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", ampCorrection
							.dcAmpacityCorrectionB3a(circuit, Integer.parseInt(circuit.getConductorQty()), 1, false));
					form.setField(sheetIndex + "-DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", ampCorrection
							.dcAmpacityCorrectionB3a(circuit, Integer.parseInt(circuit.getConductorQty()), 1, true));
					params.setConduitFillDerating(ampCorrection.dcAmpacityCorrectionB3a(circuit,
							Integer.parseInt(circuit.getConductorQty()), 1, true));
				}

				if ((checkValue.Equals(circuit.getExisting(), true))
						|| (permitEntity != null && checkValue.Equals(permitEntity.getExistModule(), true))) {
					form.setField(sheetIndex + "-DC1-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
				} else {
					form.setField(sheetIndex + "-DC1-CONDUCTOR-AMPACTIY-RATING", "30");
				}

				form.setField(sheetIndex + "-DC1-CONDUCTOR-AMPACTIY-RATING1", "30");
				if (checkValue.isNumeric(ampacityCorrectionB2a)) {
					form.setField(sheetIndex + "-DC1-CORRECTED-AMPACITY",
							String.valueOf(new DecimalFormat("#.0")
									.format(Float.parseFloat(ampacityCorrectionB2a) * Float.parseFloat("30")
											* Float.parseFloat(ampCorrection.dcAmpacityCorrectionB3a(circuit,
													Integer.parseInt(circuit.getConductorQty()), 1, true)))));
					form.setField(sheetIndex + "-DC1-CORRECTED-AMPACITY1",
							String.valueOf(new DecimalFormat("#.0")
									.format(Float.parseFloat(ampacityCorrectionB2a) * Float.parseFloat("30")
											* Float.parseFloat(ampCorrection.dcAmpacityCorrectionB3a(circuit,
													Integer.parseInt(circuit.getConductorQty()), 1, true)))));
					correctedAmpacity = String.valueOf(new DecimalFormat("#.0")
							.format(Float.parseFloat(ampacityCorrectionB2a) * Float.parseFloat("30")
									* Float.parseFloat(ampCorrection.dcAmpacityCorrectionB3a(circuit,
											Integer.parseInt(circuit.getConductorQty()), 1, true))));
				}

			} else {
				params.setNEC310(nec3106B16Repo.findFirstBytradeSze(conductorSize));
				if (params.getNEC310() != null && params.getNEC310().getNinetyInsulation() != null) {
					form.setField(sheetIndex + "-DC1-MATERIAL",
							"(" + params.getNEC310().getNumberOfConductors() + ") CU");
					if (checkValue.Equals(circuit.getConductorQty(), "Other")) {
						form.setField(sheetIndex + "-DC1-NUMBER-OF-UNGROUNDED-CONDUCTORS",
								circuit.getConductorQtyOther() + "");
						form.setField(sheetIndex + "-DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								ampCorrection.dcAmpacityCorrectionB3a(circuit, circuit.getConductorQtyOther(),
										params.getNEC310().getNumberOfConductors(), false));
						form.setField(sheetIndex + "-DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								ampCorrection.dcAmpacityCorrectionB3a(circuit, circuit.getConductorQtyOther(),
										params.getNEC310().getNumberOfConductors(), true));
						params.setConduitFillDerating(ampCorrection.dcAmpacityCorrectionB3a(circuit,
								circuit.getConductorQtyOther(), params.getNEC310().getNumberOfConductors(), true));
					} else if (checkValue.NotEquals(circuit.getConductorQty(), "")) {
						form.setField(sheetIndex + "-DC1-NUMBER-OF-UNGROUNDED-CONDUCTORS", circuit.getConductorQty());
						form.setField(sheetIndex + "-DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								ampCorrection.dcAmpacityCorrectionB3a(circuit,
										Integer.parseInt(circuit.getConductorQty()),
										params.getNEC310().getNumberOfConductors(), false));
						form.setField(sheetIndex + "-DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								ampCorrection.dcAmpacityCorrectionB3a(circuit,
										Integer.parseInt(circuit.getConductorQty()),
										params.getNEC310().getNumberOfConductors(), true));
						params.setConduitFillDerating(ampCorrection.dcAmpacityCorrectionB3a(circuit,
								Integer.parseInt(circuit.getConductorQty()), params.getNEC310().getNumberOfConductors(),
								true));
					} else {
						form.setField(sheetIndex + "-DC1-NUMBER-OF-UNGROUNDED-CONDUCTORS", "");
						form.setField(sheetIndex + "-DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "");
						form.setField(sheetIndex + "-DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "");
					}
				} else {
					form.setField(sheetIndex + "-DC1-NUMBER-OF-UNGROUNDED-CONDUCTORS", "");
					form.setField(sheetIndex + "-DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "");
					form.setField(sheetIndex + "-DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "");
				}

				if ((circuit != null && checkValue.Equals(circuit.getExisting(), true))
						|| (permitEntity != null && checkValue.Equals(permitEntity.getExistModule(), true))) {
					form.setField(sheetIndex + "-DC1-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
				} else if (params.getNEC310() != null && params.getNEC310().getNinetyInsulation() != null) {
					form.setField(sheetIndex + "-DC1-CONDUCTOR-AMPACTIY-RATING",
							String.valueOf(params.getNEC310().getNinetyInsulation()));
				}
				if (params.getNEC310() != null && params.getNEC310().getNinetyInsulation() != null) {
					form.setField(sheetIndex + "-DC1-CONDUCTOR-AMPACTIY-RATING1",
							String.valueOf(params.getNEC310().getNinetyInsulation()));
					if (checkValue.isNumeric(ampacityCorrectionB2a)) {
						form.setField(sheetIndex + "-DC1-CORRECTED-AMPACITY",
								String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(ampacityCorrectionB2a)
										* Float.parseFloat(String.valueOf(params.getNEC310().getNinetyInsulation())
												.replace(',', '.'))
										* Float.parseFloat(ampCorrection.dcAmpacityCorrectionB3a(circuit,
												Integer.parseInt(circuit.getConductorQty()),
												params.getNEC310().getNumberOfConductors(), true)))));
						form.setField(sheetIndex + "-DC1-CORRECTED-AMPACITY1",
								String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(ampacityCorrectionB2a)
										* Float.parseFloat(String.valueOf(params.getNEC310().getNinetyInsulation())
												.replace(',', '.'))
										* Float.parseFloat(ampCorrection.dcAmpacityCorrectionB3a(circuit,
												Integer.parseInt(circuit.getConductorQty()),
												params.getNEC310().getNumberOfConductors(), true)))));
						correctedAmpacity = String
								.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(ampacityCorrectionB2a)
										* Float.parseFloat(String.valueOf(params.getNEC310().getNinetyInsulation())
												.replace(',', '.'))
										* Float.parseFloat(ampCorrection.dcAmpacityCorrectionB3a(circuit,
												Integer.parseInt(circuit.getConductorQty()),
												params.getNEC310().getNumberOfConductors(), true))));
					}
				} else {
					form.setField(sheetIndex + "-DC1-CONDUCTOR-AMPACTIY-RATING1", "");
					form.setField(sheetIndex + "-DC1-CORRECTED-AMPACITY", "");
					form.setField(sheetIndex + "-DC1-CORRECTED-AMPACITY1", "");
				}

			}

		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return correctedAmpacity;
	}
}
