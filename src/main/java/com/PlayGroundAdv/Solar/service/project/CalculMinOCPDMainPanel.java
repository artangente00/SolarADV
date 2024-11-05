package com.PlayGroundAdv.Solar.service.project;

import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.model.BackFeedSolarOCPDEntityModel;
import com.PlayGroundAdv.Solar.model.OCPDMainPanelModel;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BackFeedSolarOCPDRepository;
import com.PlayGroundAdv.Solar.service.equipment_utils.GetInverterOCPD;
import com.PlayGroundAdv.Solar.service.equipment_utils.GetModuleOCPD;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class CalculMinOCPDMainPanel {

	final CheckValueTypesService checkValue;
	final GetInverterOCPD getInverterOCPD;
	final BackFeedSolarOCPDRepository backFeedSolarOCPDRepo;
	final PermitProjectSiteInfoRepository projectSiteInfoRepos;
	final GetModuleOCPD getModuleOCPD;

	public CalculMinOCPDMainPanel(CheckValueTypesService checkValue, GetInverterOCPD getInverterOCPD,
			BackFeedSolarOCPDRepository backFeedSolarOCPDRepo, PermitProjectSiteInfoRepository projectSiteInfoRepos,
			GetModuleOCPD getModuleOCPD) {
		super();
		this.checkValue = checkValue;
		this.getInverterOCPD = getInverterOCPD;
		this.backFeedSolarOCPDRepo = backFeedSolarOCPDRepo;
		this.projectSiteInfoRepos = projectSiteInfoRepos;
		this.getModuleOCPD = getModuleOCPD;
	}

	public OCPDMainPanelModel stringMinOCPDMainPanel(Long idProject, PermitArrayEntityResultSecond permitArrays) {

		OCPDMainPanelModel ocpdMainPanelModel = new OCPDMainPanelModel(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		try {
			PermitProjectSiteInfoEntity projectSiteInfo = projectSiteInfoRepos.findByPermitEntityId(idProject);

			List<BackFeedSolarOCPDEntityModel> backFeedList = backFeedSolarOCPDRepo.getListOfBackFeedSolarOCPD(false);
			if (checkValue.Equals(projectSiteInfo.getSolarLocation(), "Back-fed Breaker")
					&& (checkValue.Equals(projectSiteInfo.getCombiningACCircuits(),
							"An Existing Main or Sub Panel with More Than One Back-Fed Breaker")
							|| checkValue.Equals(projectSiteInfo.getCombiningACCircuits(),
									"A Proposed Main or Sub Panel with More Than One Back-Fed Breaker"))) {
				
				if (checkValue.isLongPositive(permitArrays.getInverterModel())) {
					ocpdMainPanelModel = calculOCPD(permitArrays.getInverterModel(), backFeedList, ocpdMainPanelModel,
							1, projectSiteInfo);
				}
			
				if (checkValue.isLongPositive(permitArrays.getSecondInverterModel())) {
					ocpdMainPanelModel = calculOCPD(permitArrays.getSecondInverterModel(), backFeedList, ocpdMainPanelModel,
							2, projectSiteInfo);
				}
			
				if (checkValue.isLongPositive(permitArrays.getThirdInverterModel())) {
					ocpdMainPanelModel = calculOCPD(permitArrays.getThirdInverterModel(), backFeedList, ocpdMainPanelModel,
							3, projectSiteInfo);
				}

				if (checkValue.isLongPositive(permitArrays.getFourthInverterModel())) {
					ocpdMainPanelModel = calculOCPD(permitArrays.getFourthInverterModel(), backFeedList, ocpdMainPanelModel,
							4, projectSiteInfo);
				}

				if (checkValue.isLongPositive(permitArrays.getFifthInverterModel())) {
					ocpdMainPanelModel = calculOCPD(permitArrays.getFifthInverterModel(), backFeedList, ocpdMainPanelModel,
							5, projectSiteInfo);
				}

			} else {
				if (checkValue.Equals(projectSiteInfo.getSolarLocation(), "Back-fed Breaker")) {

					double ocpdNum = getInverterOCPD.getOcpdNumber(permitArrays.getInverterModel(),
							permitArrays.getSecondInverterModel(), permitArrays.getThirdInverterModel(),
							permitArrays.getFourthInverterModel(), permitArrays.getFifthInverterModel());
					if (ocpdNum != 0) {

						double ocpdNumber = ocpdNum;
						double odcpdUp = Math.round(ocpdNum / 5) * 5.0;

						if (ocpdNumber > odcpdUp) {
							odcpdUp = odcpdUp + 5;
						}
						double odcpdUpLastDigit = odcpdUp % 10;

						if (odcpdUp < backFeedList.get(0).getBackFeed()) {
							ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, 1,
									Double.valueOf(backFeedList.get(0).getBackFeed()),
									Double.valueOf(backFeedList.get(1).getBackFeed()));
						} else if (odcpdUp >= backFeedList.get(0).getBackFeed()
								&& odcpdUp <= backFeedList.get(backFeedList.size() - 1).getBackFeed()) {
							if (odcpdUpLastDigit == 5 || odcpdUpLastDigit == 2) {
								ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, 1, odcpdUp,
										odcpdUp + 5);
							} else {
								ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, 1, ocpdNumber,
										odcpdUp);
							}
							// CI :31/05/2019 : If there is no option which verify the calculation should
							// change solarInterconnectionDown and solarInterconnectionUp to the nearest
							// value from back fed library
							Boolean verified = false;
							double firstbackFeedBeforeDown = 0;
							double firstbackFeedAfterup = 0;
							for (int i = 0; i < backFeedList.size(); i++) {
								if (backFeedList.get(i).getBackFeed() >= getSolarInterconnectionDown(ocpdMainPanelModel,
										1)
										&& backFeedList.get(i)
												.getBackFeed() <= getSolarInterconnectionUp(ocpdMainPanelModel, 1)) {
									verified = true;
								}
							}
							if (Boolean.FALSE.equals(verified)) {

								int i = 0;
								while (i < backFeedList.size() && backFeedList.get(i)
										.getBackFeed() <= getSolarInterconnectionDown(ocpdMainPanelModel, 1)) {
									i++;
								}
								firstbackFeedBeforeDown = backFeedList.get(i - 1).getBackFeed();

								int j = 0;
								while (j < backFeedList.size() && backFeedList.get(j)
										.getBackFeed() < getSolarInterconnectionUp(ocpdMainPanelModel, 1)) {
									j++;
								}
								firstbackFeedAfterup = backFeedList.get(j).getBackFeed();
								ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, 1,
										firstbackFeedBeforeDown, firstbackFeedAfterup);
							}
							//
						} else if (odcpdUp > backFeedList.get(backFeedList.size() - 1).getBackFeed()) {
							ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, 1,
									Double.valueOf(backFeedList.get(0).getBackFeed()),
									Double.valueOf(backFeedList.get(backFeedList.size() - 1).getBackFeed()));
						}
						if (!checkValue.isNumeric(projectSiteInfo.getSolarInterconnection())
								|| getSolarInterconnectionDown(ocpdMainPanelModel, 1) > Double
										.valueOf(projectSiteInfo.getSolarInterconnection())
								|| getSolarInterconnectionUp(ocpdMainPanelModel, 1) < Double
										.valueOf(projectSiteInfo.getSolarInterconnection())) {
							projectSiteInfo.setSolarInterconnection(ocpdMainPanelModel.getOcpdCalculation1() + "");
							projectSiteInfoRepos.save(projectSiteInfo);
						}
						ocpdMainPanelModel.setMinOCPDMainPanelOptions(ocpdNumber);
					} else {
						ocpdMainPanelModel.setOcpdCalculation1(0.0);
						if (!checkValue.isNumeric(projectSiteInfo.getSolarInterconnection())
								|| getSolarInterconnectionDown(ocpdMainPanelModel, 1) > Double
										.valueOf(projectSiteInfo.getSolarInterconnection())
								|| getSolarInterconnectionUp(ocpdMainPanelModel, 1) < Double
										.valueOf(projectSiteInfo.getSolarInterconnection())) {
							projectSiteInfo.setSolarInterconnection("20");
							projectSiteInfoRepos.save(projectSiteInfo);
						}
						ocpdMainPanelModel.setMinOCPDMainPanelOptions(0.0);
					}
					if (projectSiteInfo.getSubPanelBreakerOCPD() != null && checkValue.isNumeric(projectSiteInfo.getSubPanelBreakerOCPD())
							&& ocpdMainPanelModel.getMinOCPDMainPanelOptions() > Double
									.valueOf(projectSiteInfo.getSubPanelBreakerOCPD())
							&& !Boolean.TRUE.equals(projectSiteInfo.getCheckSiteSurveyOCPDValidity())) {
						projectSiteInfo.setSubPanelBreakerOCPD(null);
						projectSiteInfoRepos.save(projectSiteInfo);
					}
					// 30/05/2019 : CI :
					if (projectSiteInfo.getProposedACCombMainBreakerRating() != null
							&& ocpdMainPanelModel.getMinOCPDMainPanelOptions() > Double
									.valueOf(projectSiteInfo.getProposedACCombMainBreakerRating().split("A")[0])) {
						projectSiteInfo.setProposedACCombMainBreakerRating(null);
						projectSiteInfoRepos.save(projectSiteInfo);
					}
					//
					Double panelBusRating = checkValue.isNumeric(projectSiteInfo.getPanelBusRating())
							? Double.valueOf(projectSiteInfo.getPanelBusRating())
							: 0.0;
					Double panelMainBreakerRating = checkValue.isNumeric(projectSiteInfo.getPanelMainBreakerRating())
							? Double.valueOf(projectSiteInfo.getPanelMainBreakerRating())
							: 0.0;
					Double solarInterconnection = checkValue.isNumeric(projectSiteInfo.getSolarInterconnection())
							? Double.valueOf(projectSiteInfo.getSolarInterconnection())
							: 0.0;
							
					if (!checkValue.Equals(projectSiteInfo.getExistingMainPanelManufac(),
							"Siemens W/Alt Energy Input") && (panelBusRating * 1.2) - solarInterconnection < panelMainBreakerRating
							&& panelMainBreakerRating != 0.0
							&& !Boolean.TRUE.equals(projectSiteInfo.getCheckSiteSurveyOCPDValidity())) {
						projectSiteInfo.setPanelMainBreakerRating(null);
						projectSiteInfoRepos.save(projectSiteInfo);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ocpdMainPanelModel;
	}

	public OCPDMainPanelModel acModuleMinOCPDMainPanel(Long idProject, PermitArrayEntityResultSecond permitArrays) {

		OCPDMainPanelModel ocpdMainPanelModel = new OCPDMainPanelModel(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		try {
			PermitProjectSiteInfoEntity projectSiteInfo = projectSiteInfoRepos.findByPermitEntityId(idProject);

			List<BackFeedSolarOCPDEntityModel> backFeedList = backFeedSolarOCPDRepo.getListOfBackFeedSolarOCPD(false);
			if (checkValue.Equals(projectSiteInfo.getSolarLocation(), "Back-fed Breaker")
					&& (checkValue.Equals(projectSiteInfo.getCombiningACCircuits(),
							"An Existing Main or Sub Panel with More Than One Back-Fed Breaker")
							|| checkValue.Equals(projectSiteInfo.getCombiningACCircuits(),
									"A Proposed Main or Sub Panel with More Than One Back-Fed Breaker"))) {

				if (checkValue.isLongPositive(permitArrays.getPvModuleModEl())) {
					ocpdMainPanelModel = calculOCPDMicro(backFeedList, ocpdMainPanelModel, projectSiteInfo,
							permitArrays);
				}

			} else {
				if (checkValue.Equals(projectSiteInfo.getSolarLocation(), "Back-fed Breaker")) {

					double ocpdNum = getInverterOCPD.getOcpdNumber(permitArrays.getInverterModel(),
							permitArrays.getSecondInverterModel(), permitArrays.getThirdInverterModel(),
							permitArrays.getFourthInverterModel(), permitArrays.getFifthInverterModel());
					if (ocpdNum != 0) {

						double ocpdNumber = ocpdNum;
						double odcpdUp = Math.round(ocpdNum / 5) * 5.0;

						if (ocpdNumber > odcpdUp) {
							odcpdUp = odcpdUp + 5;
						}
						double odcpdUpLastDigit = odcpdUp % 10;

						if (odcpdUp < backFeedList.get(0).getBackFeed()) {
							ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, 1,
									Double.valueOf(backFeedList.get(0).getBackFeed()),
									Double.valueOf(backFeedList.get(1).getBackFeed()));
						} else if (odcpdUp >= backFeedList.get(0).getBackFeed()
								&& odcpdUp <= backFeedList.get(backFeedList.size() - 1).getBackFeed()) {
							if (odcpdUpLastDigit == 5 || odcpdUpLastDigit == 2) {
								ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, 1, odcpdUp,
										odcpdUp + 5);
							} else {
								ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, 1, ocpdNumber,
										odcpdUp);
							}
							// CI :31/05/2019 : If there is no option which verify the calculation should
							// change solarInterconnectionDown and solarInterconnectionUp to the nearest
							// value from back fed library
							Boolean verified = false;
							double firstbackFeedBeforeDown = 0;
							double firstbackFeedAfterup = 0;
							for (int i = 0; i < backFeedList.size(); i++) {
								if (backFeedList.get(i).getBackFeed() >= getSolarInterconnectionDown(ocpdMainPanelModel,
										1)
										&& backFeedList.get(i)
												.getBackFeed() <= getSolarInterconnectionUp(ocpdMainPanelModel, 1)) {
									verified = true;
								}
							}
							if (Boolean.FALSE.equals(verified)) {

								int i = 0;
								while (i < backFeedList.size() && backFeedList.get(i)
										.getBackFeed() <= getSolarInterconnectionDown(ocpdMainPanelModel, 1)) {
									i++;
								}
								firstbackFeedBeforeDown = backFeedList.get(i - 1).getBackFeed();

								int j = 0;
								while (j < backFeedList.size() && backFeedList.get(j)
										.getBackFeed() < getSolarInterconnectionUp(ocpdMainPanelModel, 1)) {
									j++;
								}
								firstbackFeedAfterup = backFeedList.get(j).getBackFeed();
								ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, 1,
										firstbackFeedBeforeDown, firstbackFeedAfterup);
							}
							//
						} else if (odcpdUp > backFeedList.get(backFeedList.size() - 1).getBackFeed()) {
							ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, 1,
									Double.valueOf(backFeedList.get(0).getBackFeed()),
									Double.valueOf(backFeedList.get(backFeedList.size() - 1).getBackFeed()));
						}
						if (!checkValue.isNumeric(projectSiteInfo.getSolarInterconnection())
								|| getSolarInterconnectionDown(ocpdMainPanelModel, 1) > Double
										.valueOf(projectSiteInfo.getSolarInterconnection())
								|| getSolarInterconnectionUp(ocpdMainPanelModel, 1) < Double
										.valueOf(projectSiteInfo.getSolarInterconnection())) {
							projectSiteInfo.setSolarInterconnection(ocpdMainPanelModel.getOcpdCalculation1() + "");
							projectSiteInfoRepos.save(projectSiteInfo);
						}
						ocpdMainPanelModel.setMinOCPDMainPanelOptions(ocpdNumber);
					} else {
						ocpdMainPanelModel.setOcpdCalculation1(0.0);
						if (!checkValue.isNumeric(projectSiteInfo.getSolarInterconnection())
								|| getSolarInterconnectionDown(ocpdMainPanelModel, 1) > Double
										.valueOf(projectSiteInfo.getSolarInterconnection())
								|| getSolarInterconnectionUp(ocpdMainPanelModel, 1) < Double
										.valueOf(projectSiteInfo.getSolarInterconnection())) {
							projectSiteInfo.setSolarInterconnection("20");
							projectSiteInfoRepos.save(projectSiteInfo);
						}
						ocpdMainPanelModel.setMinOCPDMainPanelOptions(0.0);
					}
					if (projectSiteInfo.getSubPanelBreakerOCPD() != null && checkValue.isNumeric(projectSiteInfo.getSubPanelBreakerOCPD())
							&& ocpdMainPanelModel.getMinOCPDMainPanelOptions() > Double
									.valueOf(projectSiteInfo.getSubPanelBreakerOCPD())
							&& !Boolean.TRUE.equals(projectSiteInfo.getCheckSiteSurveyOCPDValidity())) {
						projectSiteInfo.setSubPanelBreakerOCPD(null);
						projectSiteInfo.setSubPanelBreakerOCPD(null);
						projectSiteInfoRepos.save(projectSiteInfo);
					}
					// 30/05/2019 : CI :
					if (projectSiteInfo.getProposedACCombMainBreakerRating() != null
							&& ocpdMainPanelModel.getMinOCPDMainPanelOptions() > Double
									.valueOf(projectSiteInfo.getProposedACCombMainBreakerRating().split("A")[0])) {
						projectSiteInfo.setProposedACCombMainBreakerRating(null);
						projectSiteInfo.setProposedACCombMainBreakerRating(null);
						projectSiteInfoRepos.save(projectSiteInfo);
					}
					//
					Double panelBusRating = checkValue.isNumeric(projectSiteInfo.getPanelBusRating())
							? Double.valueOf(projectSiteInfo.getPanelBusRating())
							: 0.0;
					Double panelMainBreakerRating = checkValue.isNumeric(projectSiteInfo.getPanelMainBreakerRating())
							? Double.valueOf(projectSiteInfo.getPanelMainBreakerRating())
							: 0.0;
					Double solarInterconnection = checkValue.isNumeric(projectSiteInfo.getSolarInterconnection())
							? Double.valueOf(projectSiteInfo.getSolarInterconnection())
							: 0.0;
					if (!checkValue.Equals(projectSiteInfo.getExistingMainPanelManufac(),
							"Siemens W/Alt Energy Input") && (panelBusRating * 1.2) - solarInterconnection < panelMainBreakerRating
							&& panelMainBreakerRating != 0.0
							&& !Boolean.TRUE.equals(projectSiteInfo.getCheckSiteSurveyOCPDValidity())) {
						projectSiteInfo.setPanelMainBreakerRating(null);
						projectSiteInfo.setPanelMainBreakerRating(null);
						projectSiteInfoRepos.save(projectSiteInfo);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ocpdMainPanelModel;
	}

	private OCPDMainPanelModel calculOCPD(Long inverterId, List<BackFeedSolarOCPDEntityModel> backFeedList,
			OCPDMainPanelModel ocpdMainPanelModel, int inverterNumber, PermitProjectSiteInfoEntity projectSiteInfo) {

		double minOCPDMainPanel = 0.0;
		double ocpdNumber = 0.0;
		try {
			double ocpdNum = getInverterOCPD.getInverterOcpdNumber(inverterId);
			minOCPDMainPanel = ocpdMainPanelModel.getMinOCPDMainPanelOptions() + ocpdNum;

			if (ocpdNum != 0) {

				ocpdMainPanelModel = setOcpdCalculation(ocpdMainPanelModel, ocpdNum, inverterNumber);
				ocpdNumber = ocpdNum;
				double odcpdUp = Math.round(ocpdNum / 5) * 5.0;

				if (ocpdNumber > odcpdUp) {
					odcpdUp = odcpdUp + 5;
				}

				double odcpdUpLastDigit = odcpdUp % 10;

				if (odcpdUp < backFeedList.get(0).getBackFeed()) {
					ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, inverterNumber,
							Double.valueOf(backFeedList.get(0).getBackFeed()),
							Double.valueOf(backFeedList.get(1).getBackFeed()));

				} else if (odcpdUp >= backFeedList.get(0).getBackFeed()
						&& odcpdUp <= backFeedList.get(backFeedList.size() - 1).getBackFeed()) {

					if (odcpdUpLastDigit == 5 || odcpdUpLastDigit == 2) {
						ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, inverterNumber, odcpdUp,
								odcpdUp + 5);
					} else {
						ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, inverterNumber, ocpdNumber,
								odcpdUp);
					}
				
					// CI :31/05/2019 : If there is no option which verify the calculation should
					// change solarInterconnectionDown and solarInterconnectionUp to the nearest
					// value from back fed library
					Boolean verified = false;
					double firstbackFeedBeforeDown = 0;
					double firstbackFeedAfterup = 0;
					for (int i = 0; i < backFeedList.size(); i++) {
						if (backFeedList.get(i).getBackFeed() >= getSolarInterconnectionDown(ocpdMainPanelModel,
								inverterNumber)
								&& backFeedList.get(i).getBackFeed() <= getSolarInterconnectionUp(ocpdMainPanelModel,
										inverterNumber)) {
							verified = true;
						}
					}
					if (Boolean.FALSE.equals(verified)) {

						int i = 0;
						while (i < backFeedList.size() && backFeedList.get(i)
								.getBackFeed() <= getSolarInterconnectionDown(ocpdMainPanelModel, inverterNumber)) {
							i++;
						}
						firstbackFeedBeforeDown = backFeedList.get(i - 1).getBackFeed();

						int j = 0;
						while (j < backFeedList.size() && backFeedList.get(j)
								.getBackFeed() < getSolarInterconnectionUp(ocpdMainPanelModel, inverterNumber)) {
							j++;
						}
						firstbackFeedAfterup = backFeedList.get(j).getBackFeed();
						ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, inverterNumber,
								firstbackFeedBeforeDown, firstbackFeedAfterup);
					}
				} else if (odcpdUp > backFeedList.get(backFeedList.size() - 1).getBackFeed()) {
					ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, inverterNumber,
							Double.valueOf(backFeedList.get(0).getBackFeed()),
							Double.valueOf(backFeedList.get(backFeedList.size() - 1).getBackFeed()));
				}
				if (getSolarInterconnectionTest(projectSiteInfo, ocpdMainPanelModel, inverterNumber)) {
					setSolarInterconnection(projectSiteInfo, getOcpdCalculation(ocpdMainPanelModel, inverterNumber) + "",
							inverterNumber);
				}
			} else {
				ocpdMainPanelModel.setOcpdCalculation1(0.0);
				if (!checkValue.isNumeric(projectSiteInfo.getSolarInterconnection())
						|| getSolarInterconnectionDown(ocpdMainPanelModel, inverterNumber) > Double
								.valueOf(projectSiteInfo.getSolarInterconnection())
						|| getSolarInterconnectionUp(ocpdMainPanelModel, inverterNumber) < Double
								.valueOf(projectSiteInfo.getSolarInterconnection())) {
					projectSiteInfo.setSolarInterconnection("20");
					projectSiteInfoRepos.save(projectSiteInfo);
				}
			}

			ocpdMainPanelModel.setMinOCPDMainPanelOptions(minOCPDMainPanel);
			if (projectSiteInfo.getSubPanelBreakerOCPD() != null && checkValue.isNumeric(projectSiteInfo.getSubPanelBreakerOCPD())
					&& ocpdMainPanelModel.getMinOCPDMainPanelOptions() > Double
							.valueOf(projectSiteInfo.getSubPanelBreakerOCPD())
					&& !Boolean.TRUE.equals(projectSiteInfo.getCheckSiteSurveyOCPDValidity())) {
				projectSiteInfo.setSubPanelBreakerOCPD(null);
				projectSiteInfo.setSubPanelBreakerOCPD(null);
				projectSiteInfoRepos.save(projectSiteInfo);
			}
			// 30/05/2019 : CI :
			if (projectSiteInfo.getProposedACCombMainBreakerRating() != null
					&& ocpdMainPanelModel.getMinOCPDMainPanelOptions() > Double
							.valueOf(projectSiteInfo.getProposedACCombMainBreakerRating().split("A")[0])) {
				projectSiteInfo.setProposedACCombMainBreakerRating(null);
				projectSiteInfo.setProposedACCombMainBreakerRating(null);
				projectSiteInfoRepos.save(projectSiteInfo);
			}
			//
			Double panelBusRating = checkValue.isNumeric(projectSiteInfo.getPanelBusRating())
					? Double.valueOf(projectSiteInfo.getPanelBusRating())
					: 0.0;
			Double panelMainBreakerRating = checkValue.isNumeric(projectSiteInfo.getPanelMainBreakerRating())
					? Double.valueOf(projectSiteInfo.getPanelMainBreakerRating())
					: 0.0;
					
			if (!checkValue.Equals(projectSiteInfo.getExistingMainPanelManufac(), "Siemens W/Alt Energy Input") && (panelBusRating * 1.2) - ocpdMainPanelModel.getMinOCPDMainPanelOptions() < panelMainBreakerRating
					&& panelMainBreakerRating > 0
					&& !Boolean.TRUE.equals(projectSiteInfo.getCheckSiteSurveyOCPDValidity())) {
				projectSiteInfo.setPanelMainBreakerRating(null);
				projectSiteInfo.setPanelMainBreakerRating(null);
				projectSiteInfoRepos.save(projectSiteInfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ocpdMainPanelModel;
	}


	private OCPDMainPanelModel calculOCPDMicro(List<BackFeedSolarOCPDEntityModel> backFeedList,
			OCPDMainPanelModel ocpdMainPanelModel, PermitProjectSiteInfoEntity projectSiteInfo,
			PermitArrayEntityResultSecond permitArrays) {

		double minOCPDMainPanel = 0.0;
		try {
			double ocpdNum = getModuleOCPD.getModuleOcpdNumber(permitArrays.getPvModuleModEl());
			minOCPDMainPanel = ocpdMainPanelModel.getMinOCPDMainPanelOptions() + ocpdNum;
			if (ocpdNum == 0) {

				ocpdMainPanelModel.setOcpdCalculation1(0.0);
				if (!checkValue.isNumeric(projectSiteInfo.getSolarInterconnection())
						|| ocpdMainPanelModel.getSolarInterconnectionDown1() > Double
								.valueOf(projectSiteInfo.getSolarInterconnection())
						|| ocpdMainPanelModel.getSolarInterconnectionUp1() < Double
								.valueOf(projectSiteInfo.getSolarInterconnection())) {
					setSolarInterconnection(projectSiteInfo, "20", 1);
				}
				if (permitArrays.getOcpdTwo() != null && permitArrays.getNumberModulesACCircuitTwo() != null) {
					ocpdMainPanelModel.setOcpdCalculation2(0.0);
					if (!checkValue.isNumeric(projectSiteInfo.getSecondSolarInterconnection())
							|| ocpdMainPanelModel.getSolarInterconnectionDown2() > Double
									.valueOf(projectSiteInfo.getSecondSolarInterconnection())
							|| ocpdMainPanelModel.getSolarInterconnectionUp2() < Double
									.valueOf(projectSiteInfo.getSecondSolarInterconnection())) {
						setSolarInterconnection(projectSiteInfo, "20", 2);
					}
				}

				if (permitArrays.getOcpdThree() != null && permitArrays.getNumberModulesACCircuitThree() != null) {
					ocpdMainPanelModel.setOcpdCalculation3(0.0);
					if (!checkValue.isNumeric(projectSiteInfo.getThirdSolarInterconnection())
							|| ocpdMainPanelModel.getSolarInterconnectionDown3() > Double
									.valueOf(projectSiteInfo.getThirdSolarInterconnection())
							|| ocpdMainPanelModel.getSolarInterconnectionUp3() < Double
									.valueOf(projectSiteInfo.getThirdSolarInterconnection())) {
						setSolarInterconnection(projectSiteInfo, "20", 3);
					}
				}

				if (permitArrays.getOcpdFour() != null && permitArrays.getNumberModulesACCircuitFour() != null) {
					ocpdMainPanelModel.setOcpdCalculation4(0.0);
					if (!checkValue.isNumeric(projectSiteInfo.getFourthSolarInterconnection())
							|| ocpdMainPanelModel.getSolarInterconnectionDown4() > Double
									.valueOf(projectSiteInfo.getFourthSolarInterconnection())
							|| ocpdMainPanelModel.getSolarInterconnectionUp4() < Double
									.valueOf(projectSiteInfo.getFourthSolarInterconnection())) {
						setSolarInterconnection(projectSiteInfo, "20", 4);
					}
				}

				if (permitArrays.getOcpdFive() != null && permitArrays.getNumberModulesACCircuitFive() != null) {
					ocpdMainPanelModel.setOcpdCalculation2(0.0);
					if (!checkValue.isNumeric(projectSiteInfo.getFifthSolarInterconnection())
							|| ocpdMainPanelModel.getSolarInterconnectionDown5() > Double
									.valueOf(projectSiteInfo.getFifthSolarInterconnection())
							|| ocpdMainPanelModel.getSolarInterconnectionUp5() < Double
									.valueOf(projectSiteInfo.getFifthSolarInterconnection())) {
						setSolarInterconnection(projectSiteInfo, "20", 5);
					}
				}

			} else {
				Double qtyModule = 0.0;
				if (checkValue.isNumeric(permitArrays.getNumberModulesACCircuitOne())) {
					ocpdMainPanelModel = setMicroSolarInterconnection(ocpdMainPanelModel, 1, projectSiteInfo,
							permitArrays, ocpdNum, backFeedList,
							Double.valueOf(permitArrays.getNumberModulesACCircuitOne()));
					qtyModule = Double.valueOf(permitArrays.getNumberModulesACCircuitOne());
					ocpdMainPanelModel = seProjectSolarInterconnection(ocpdMainPanelModel, qtyModule, projectSiteInfo,
							backFeedList, minOCPDMainPanel);
				}
				if (permitArrays.getOcpdTwo() != null
						&& checkValue.isNumeric(permitArrays.getNumberModulesACCircuitTwo())) {
					ocpdMainPanelModel = setMicroSolarInterconnection(ocpdMainPanelModel, 2, projectSiteInfo,
							permitArrays, ocpdNum, backFeedList,
							Double.valueOf(permitArrays.getNumberModulesACCircuitTwo()));
					qtyModule = qtyModule + Double.valueOf(permitArrays.getNumberModulesACCircuitTwo());
					ocpdMainPanelModel = seProjectSolarInterconnection(ocpdMainPanelModel, qtyModule, projectSiteInfo,
							backFeedList, minOCPDMainPanel);
				}

				if (permitArrays.getOcpdThree() != null
						&& checkValue.isNumeric(permitArrays.getNumberModulesACCircuitThree())) {
					ocpdMainPanelModel = setMicroSolarInterconnection(ocpdMainPanelModel, 3, projectSiteInfo,
							permitArrays, ocpdNum, backFeedList,
							Double.valueOf(permitArrays.getNumberModulesACCircuitThree()));
					qtyModule = qtyModule + Double.valueOf(permitArrays.getNumberModulesACCircuitThree());
					ocpdMainPanelModel = seProjectSolarInterconnection(ocpdMainPanelModel, qtyModule, projectSiteInfo,
							backFeedList, minOCPDMainPanel);
				}

				if (permitArrays.getOcpdFour() != null
						&& checkValue.isNumeric(permitArrays.getNumberModulesACCircuitFour())) {
					ocpdMainPanelModel = setMicroSolarInterconnection(ocpdMainPanelModel, 4, projectSiteInfo,
							permitArrays, ocpdNum, backFeedList,
							Double.valueOf(permitArrays.getNumberModulesACCircuitFour()));
					qtyModule = qtyModule + Double.valueOf(permitArrays.getNumberModulesACCircuitFour());
					ocpdMainPanelModel = seProjectSolarInterconnection(ocpdMainPanelModel, qtyModule, projectSiteInfo,
							backFeedList, minOCPDMainPanel);
				}

				if (permitArrays.getOcpdFive() != null
						&& checkValue.isNumeric(permitArrays.getNumberModulesACCircuitFive())) {
					ocpdMainPanelModel = setMicroSolarInterconnection(ocpdMainPanelModel, 5, projectSiteInfo,
							permitArrays, ocpdNum, backFeedList,
							Double.valueOf(permitArrays.getNumberModulesACCircuitFive()));
					qtyModule = qtyModule + Double.valueOf(permitArrays.getNumberModulesACCircuitFive());
					ocpdMainPanelModel = seProjectSolarInterconnection(ocpdMainPanelModel, qtyModule, projectSiteInfo,
							backFeedList, minOCPDMainPanel);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ocpdMainPanelModel;
	}

	public OCPDMainPanelModel seProjectSolarInterconnection(OCPDMainPanelModel ocpdMainPanelModel, double qtyOCPD,
			PermitProjectSiteInfoEntity projectSiteInfo, List<BackFeedSolarOCPDEntityModel> backFeedList,
			double minOCPDMainPanel) {

		try {
			ocpdMainPanelModel.setMinOCPDMainPanelOptions(minOCPDMainPanel * qtyOCPD);

			// 30/05/2019 : CI :
			if (projectSiteInfo.getProposedACCombMainBreakerRating() != null
					&& ocpdMainPanelModel.getMinOCPDMainPanelOptions() > Double
							.valueOf(projectSiteInfo.getProposedACCombMainBreakerRating().split("A")[0])) {
				projectSiteInfo.setProposedACCombMainBreakerRating(null);
				projectSiteInfoRepos.save(projectSiteInfo);
			}

			if (projectSiteInfo.getSubPanelBreakerOCPD() != null && checkValue.isNumeric(projectSiteInfo.getSubPanelBreakerOCPD())
					&& ocpdMainPanelModel.getMinOCPDMainPanelOptions() > Double
							.valueOf(projectSiteInfo.getSubPanelBreakerOCPD())
					&& !Boolean.TRUE.equals(projectSiteInfo.getCheckSiteSurveyOCPDValidity())) {
				projectSiteInfo.setSubPanelBreakerOCPD(null);
				projectSiteInfoRepos.save(projectSiteInfo);
			}

			Double panelBusRating = checkValue.isNumeric(projectSiteInfo.getPanelBusRating())
					? Double.valueOf(projectSiteInfo.getPanelBusRating())
					: 0.0;
			Double panelMainBreakerRating = checkValue.isNumeric(projectSiteInfo.getPanelMainBreakerRating())
					? Double.valueOf(projectSiteInfo.getPanelMainBreakerRating())
					: 0.0;
			
			if (!checkValue.Equals(projectSiteInfo.getExistingMainPanelManufac(), "Siemens W/Alt Energy Input") && (panelBusRating * 1.2) - ocpdMainPanelModel.getMinOCPDMainPanelOptions() < panelMainBreakerRating
					&& panelMainBreakerRating > 0
					&& !Boolean.TRUE.equals(projectSiteInfo.getCheckSiteSurveyOCPDValidity())) {
				projectSiteInfo.setPanelMainBreakerRating(null);
				projectSiteInfoRepos.save(projectSiteInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ocpdMainPanelModel;

	}

	public OCPDMainPanelModel setMicroSolarInterconnection(OCPDMainPanelModel ocpdMainPanelModel, int inverterNumber,
			PermitProjectSiteInfoEntity projectSiteInfo, PermitArrayEntityResultSecond permitArrays, double ocpdNum,
			List<BackFeedSolarOCPDEntityModel> backFeedList, double numberModulesACCircuit) {
		try {

			double ocpdNumber = ocpdNum * numberModulesACCircuit;
			double odcpdUp = Math.round(ocpdNumber / 5) * 5.0;
			if (ocpdNumber > odcpdUp) {
				odcpdUp = odcpdUp + 5;
			}
			double odcpdUpLastDigit = odcpdUp % 10;

			if (odcpdUp < backFeedList.get(0).getBackFeed()) {
				ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, inverterNumber,
						Double.valueOf(backFeedList.get(0).getBackFeed()),
						Double.valueOf(backFeedList.get(1).getBackFeed()));

			} else if (odcpdUp >= backFeedList.get(0).getBackFeed()
					&& odcpdUp <= backFeedList.get(backFeedList.size() - 1).getBackFeed()) {

				if (odcpdUpLastDigit == 5 || odcpdUpLastDigit == 2) {
					ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, inverterNumber, odcpdUp,
							odcpdUp + 5);
				} else {
					ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, inverterNumber, ocpdNumber,
							odcpdUp);
				}

				// CI :31/05/2019 : If there is no option which verify the calculation should
				// change solarInterconnectionDown and solarInterconnectionUp to the nearest
				// value from back fed library
				Boolean verified = false;
				double firstbackFeedBeforeDown = 0;
				double firstbackFeedAfterup = 0;
				for (int i = 0; i < backFeedList.size(); i++) {
					if (backFeedList.get(i).getBackFeed() >= getSolarInterconnectionDown(ocpdMainPanelModel,
							inverterNumber)
							&& backFeedList.get(i).getBackFeed() <= getSolarInterconnectionUp(ocpdMainPanelModel,
									inverterNumber)) {
						verified = true;
					}
				}
				if (Boolean.FALSE.equals(verified)) {

					int i = 0;
					while (i < backFeedList.size() && backFeedList.get(i)
							.getBackFeed() <= getSolarInterconnectionDown(ocpdMainPanelModel, inverterNumber)) {
						i++;
					}
					firstbackFeedBeforeDown = backFeedList.get(i - 1).getBackFeed();

					int j = 0;
					while (j < backFeedList.size() && backFeedList.get(j)
							.getBackFeed() < getSolarInterconnectionUp(ocpdMainPanelModel, inverterNumber)) {
						j++;
					}
					firstbackFeedAfterup = backFeedList.get(j).getBackFeed();
					ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, inverterNumber,
							firstbackFeedBeforeDown, firstbackFeedAfterup);
				}
			} else if (odcpdUp > backFeedList.get(backFeedList.size() - 1).getBackFeed()) {
				ocpdMainPanelModel = setOCPDSolarInterconnection(ocpdMainPanelModel, inverterNumber,
						Double.valueOf(backFeedList.get(0).getBackFeed()),
						Double.valueOf(backFeedList.get(backFeedList.size() - inverterNumber).getBackFeed()));
			}

			if (getSolarInterconnectionTest(projectSiteInfo, ocpdMainPanelModel, inverterNumber)) {
				setSolarInterconnection(projectSiteInfo, getOcpdCalculation(ocpdMainPanelModel, inverterNumber) + "",
						inverterNumber);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ocpdMainPanelModel;
	}

	private void setSolarInterconnection(PermitProjectSiteInfoEntity projectSiteInfo, String v, int inverterNumber) {

		try {
			switch (inverterNumber) {
			case 1:
				projectSiteInfo.setSolarInterconnection(v);
				break;
			case 2:
				projectSiteInfo.setSecondSolarInterconnection(v);
				break;
			case 3:
				projectSiteInfo.setThirdSolarInterconnection(v);
				break;
			case 4:
				projectSiteInfo.setFourthSolarInterconnection(v);
				break;
			case 5:
				projectSiteInfo.setFifthSolarInterconnection(v);
				break;
			default:
				break;
			}
			projectSiteInfoRepos.save(projectSiteInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Boolean getSolarInterconnectionTest(PermitProjectSiteInfoEntity projectSiteInfo,
			OCPDMainPanelModel ocpdMainPanelModel, int inverterNumber) {

		try {
			switch (inverterNumber) {
			case 1:
				return !checkValue.isNumeric(projectSiteInfo.getSolarInterconnection())
						|| getSolarInterconnectionDown(ocpdMainPanelModel, inverterNumber) > Double
								.valueOf(projectSiteInfo.getSolarInterconnection())
						|| getSolarInterconnectionUp(ocpdMainPanelModel, inverterNumber) < Double
								.valueOf(projectSiteInfo.getSolarInterconnection());
			case 2:
				return !checkValue.isNumeric(projectSiteInfo.getSecondSolarInterconnection())
						|| getSolarInterconnectionDown(ocpdMainPanelModel, inverterNumber) > Double
								.valueOf(projectSiteInfo.getSecondSolarInterconnection())
						|| getSolarInterconnectionUp(ocpdMainPanelModel, inverterNumber) < Double
								.valueOf(projectSiteInfo.getSecondSolarInterconnection());
			case 3:
				return !checkValue.isNumeric(projectSiteInfo.getThirdSolarInterconnection())
						|| getSolarInterconnectionDown(ocpdMainPanelModel, inverterNumber) > Double
								.valueOf(projectSiteInfo.getThirdSolarInterconnection())
						|| getSolarInterconnectionUp(ocpdMainPanelModel, inverterNumber) < Double
								.valueOf(projectSiteInfo.getThirdSolarInterconnection());
			case 4:
				return !checkValue.isNumeric(projectSiteInfo.getFourthSolarInterconnection())
						|| getSolarInterconnectionDown(ocpdMainPanelModel, inverterNumber) > Double
								.valueOf(projectSiteInfo.getFourthSolarInterconnection())
						|| getSolarInterconnectionUp(ocpdMainPanelModel, inverterNumber) < Double
								.valueOf(projectSiteInfo.getFourthSolarInterconnection());
			case 5:
				return !checkValue.isNumeric(projectSiteInfo.getFifthSolarInterconnection())
						|| getSolarInterconnectionDown(ocpdMainPanelModel, inverterNumber) > Double
								.valueOf(projectSiteInfo.getFifthSolarInterconnection())
						|| getSolarInterconnectionUp(ocpdMainPanelModel, inverterNumber) < Double
								.valueOf(projectSiteInfo.getFifthSolarInterconnection());
			default:
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public OCPDMainPanelModel setOCPDSolarInterconnection(OCPDMainPanelModel ocpdMainPanelModel, int inverterNumber,
			Double down, Double up) {

		try {
		
			switch (inverterNumber) {
			case 1:
				ocpdMainPanelModel.setSolarInterconnectionDown1(down);
				ocpdMainPanelModel.setSolarInterconnectionUp1(up);
				ocpdMainPanelModel.setOcpdCalculation1(up);
				break;
			case 2:
				ocpdMainPanelModel.setSolarInterconnectionDown2(down);
				ocpdMainPanelModel.setSolarInterconnectionUp2(up);
				ocpdMainPanelModel.setOcpdCalculation2(up);
				break;
			case 3:
				ocpdMainPanelModel.setSolarInterconnectionDown3(down);
				ocpdMainPanelModel.setSolarInterconnectionUp3(up);
				ocpdMainPanelModel.setOcpdCalculation3(up);
				break;
			case 4:
				ocpdMainPanelModel.setSolarInterconnectionDown4(down);
				ocpdMainPanelModel.setSolarInterconnectionUp4(up);
				ocpdMainPanelModel.setOcpdCalculation4(up);
				break;
			case 5:
				ocpdMainPanelModel.setSolarInterconnectionDown5(down);
				ocpdMainPanelModel.setSolarInterconnectionUp5(up);
				ocpdMainPanelModel.setOcpdCalculation5(up);
				break;
			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ocpdMainPanelModel;
	}

	public Double getSolarInterconnectionDown(OCPDMainPanelModel ocpdMainPanelModel, int inverterNumber) {

		try {
			switch (inverterNumber) {
			case 1:
				return ocpdMainPanelModel.getSolarInterconnectionDown1();
			case 2:
				return ocpdMainPanelModel.getSolarInterconnectionDown2();
			case 3:
				return ocpdMainPanelModel.getSolarInterconnectionDown3();
			case 4:
				return ocpdMainPanelModel.getSolarInterconnectionDown4();
			case 5:
				return ocpdMainPanelModel.getSolarInterconnectionDown5();
			default:
				return 0.0;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0.0;
		}
	}

	public Double getSolarInterconnectionUp(OCPDMainPanelModel ocpdMainPanelModel, int inverterNumber) {

		try {
			switch (inverterNumber) {
			case 1:
				return ocpdMainPanelModel.getSolarInterconnectionUp1();
			case 2:
				return ocpdMainPanelModel.getSolarInterconnectionUp2();
			case 3:
				return ocpdMainPanelModel.getSolarInterconnectionUp3();
			case 4:
				return ocpdMainPanelModel.getSolarInterconnectionUp4();
			case 5:
				return ocpdMainPanelModel.getSolarInterconnectionUp5();
			default:
				return 0.0;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0.0;
		}
	}

	public Double getOcpdCalculation(OCPDMainPanelModel ocpdMainPanelModel, int inverterNumber) {

		try {
			switch (inverterNumber) {
			case 1:
				return ocpdMainPanelModel.getOcpdCalculation1();
			case 2:
				return ocpdMainPanelModel.getOcpdCalculation2();
			case 3:
				return ocpdMainPanelModel.getOcpdCalculation3();
			case 4:
				return ocpdMainPanelModel.getOcpdCalculation4();
			case 5:
				return ocpdMainPanelModel.getOcpdCalculation5();
			default:
				return 0.0;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0.0;
		}
	}

	public OCPDMainPanelModel setOcpdCalculation(OCPDMainPanelModel ocpdMainPanelModel, double v, int inverterNumber) {

		try {
			switch (inverterNumber) {
			case 1:
				ocpdMainPanelModel.setOcpdCalculation1(v);
				break;
			case 2:
				ocpdMainPanelModel.setOcpdCalculation2(v);
				break;
			case 3:
				ocpdMainPanelModel.setOcpdCalculation3(v);
				break;
			case 4:
				ocpdMainPanelModel.setOcpdCalculation4(v);
				break;
			case 5:
				ocpdMainPanelModel.setOcpdCalculation5(v);
				break;
			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ocpdMainPanelModel;
	}

	public Double microMinOCPDMainPanel() {
		Double minOCPDMainPanelOptions = 0.0;
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return minOCPDMainPanelOptions;
	}
}
