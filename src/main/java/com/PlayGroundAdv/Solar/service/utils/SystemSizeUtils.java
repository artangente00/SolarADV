package com.PlayGroundAdv.Solar.service.utils;

import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.PermitArraysEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;

@Service
@Transactional
public class SystemSizeUtils {


	final CheckValueTypesService checkValue;
	final PermitArraysRepository projectArraysRepo;
	final PermitRepository permitRepo;

	public SystemSizeUtils(CheckValueTypesService checkValue, PermitArraysRepository projectArraysRepo,
			PermitRepository permitRepo) {
		super();
		this.checkValue = checkValue;
		this.projectArraysRepo = projectArraysRepo;
		this.permitRepo = permitRepo;
	}

	public void systemSizeReport() {
		try {
			FileOutputStream fileOut;
			HSSFWorkbook workbook = new HSSFWorkbook();

			HSSFCellStyle cellStyleOrange = workbook.createCellStyle();
			cellStyleOrange.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
			cellStyleOrange.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			HSSFSheet reportSheet = workbook.createSheet("Project System Size Report");
			Row header = reportSheet.createRow(0);
			reportSheet.setColumnWidth(0, 10000);
			reportSheet.setColumnWidth(1, 5000);
			header.createCell(0).setCellStyle(cellStyleOrange);
			header.createCell(1).setCellStyle(cellStyleOrange);
			header.getCell(0).setCellValue("Project Name");
			header.getCell(1).setCellValue("System Size");

			List<PermitEntity> projectList = permitRepo.getPermitSubmitted();
			for (int i = 1; i <= projectList.size(); i++) {
				PermitArraysEntity permitArraysEntity = projectArraysRepo
						.findByPermitEntityId(projectList.get(i - 1).getId());

				int totlaModule = 0;
				if (permitArraysEntity != null
						&& (checkValue.Equals(permitArraysEntity.getDeviceToIncorporate(), "Neither") || checkValue
								.Equals(permitArraysEntity.getDeviceToIncorporate(), "System Optimizer"))) {
					totlaModule = getTotalModuleString(permitArraysEntity);
				} else if (permitArraysEntity != null
						&& (checkValue.Equals(permitArraysEntity.getDeviceToIncorporate(), "Micro Inverter")
								|| checkValue.Equals(permitArraysEntity.getDeviceToIncorporate(), "AC Modules"))) {
					totlaModule = getTotalModuleMicro(permitArraysEntity);
				}
				String project = "";
				if (projectList.get(i - 1).getProjectName() == null
						|| checkValue.Equals(projectList.get(i - 1).getProjectName(), "")) {
					project = projectList.get(i - 1).getHomeOwnLastName() != null
							? projectList.get(i - 1).getHomeOwnLastName() + ", "
									+ projectList.get(i - 1).getHomeOwnName()
							: projectList.get(i - 1).getHomeOwnName();
				} else {
					project = projectList.get(i - 1).getProjectName();
				}
				if (project != null && !project.toUpperCase().contains("TEST") && !project.toUpperCase().contains("CEC") && !project.toUpperCase().contains("NEC")) {
						Row row = reportSheet.createRow(i);
						row.createCell(0).setCellValue(project);
						row.createCell(1).setCellValue(getPermitSystemSize(permitArraysEntity.getPvModule(), totlaModule));
				}
				
			}
			String filePath = "D:/Project System Size Report.xls";
			fileOut = new FileOutputStream(filePath);

			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public int getTotalModuleString(PermitArraysEntity permitArraysEntityResult) {
		int totlaModule = 0;
		try {
			if (permitArraysEntityResult.getStringOne() != null) {
				totlaModule = totlaModule + Integer.parseInt(permitArraysEntityResult.getStringOne());
				if (permitArraysEntityResult.getStringTwo() != null) {
					totlaModule = totlaModule + Integer.parseInt(permitArraysEntityResult.getStringTwo());
					if (permitArraysEntityResult.getStringThree() != null) {
						totlaModule = totlaModule + Integer.parseInt(permitArraysEntityResult.getStringThree());
						if (permitArraysEntityResult.getStringFour() != null) {
							totlaModule = totlaModule + Integer.parseInt(permitArraysEntityResult.getStringFour());
							if (permitArraysEntityResult.getStringFive() != null) {
								totlaModule = totlaModule + Integer.parseInt(permitArraysEntityResult.getStringFive());
								if (permitArraysEntityResult.getStringSix() != null) {
									totlaModule = totlaModule + permitArraysEntityResult.getStringSix();
									if (permitArraysEntityResult.getStringSeven() != null) {
										totlaModule = totlaModule + permitArraysEntityResult.getStringSeven();
										if (permitArraysEntityResult.getStringEight() != null) {
											totlaModule = totlaModule + permitArraysEntityResult.getStringEight();
											if (permitArraysEntityResult.getStringNine() != null) {
												totlaModule = totlaModule + permitArraysEntityResult.getStringNine();
												if (permitArraysEntityResult.getStringTen() != null) {
													totlaModule = totlaModule + permitArraysEntityResult.getStringTen();
													if (permitArraysEntityResult.getStringEleven() != null) {
														totlaModule = totlaModule
																+ permitArraysEntityResult.getStringEleven();
														if (permitArraysEntityResult.getStringTwelve() != null) {
															totlaModule = totlaModule
																	+ permitArraysEntityResult.getStringTwelve();
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if (permitArraysEntityResult.getSecondStringOne() != null) {
				totlaModule = totlaModule + Integer.parseInt(permitArraysEntityResult.getSecondStringOne());
				if (permitArraysEntityResult.getSecondStringTwo() != null) {
					totlaModule = totlaModule + Integer.parseInt(permitArraysEntityResult.getSecondStringTwo());
					if (permitArraysEntityResult.getSecondStringThree() != null) {
						totlaModule = totlaModule + Integer.parseInt(permitArraysEntityResult.getSecondStringThree());
						if (permitArraysEntityResult.getSecondStringFour() != null) {
							totlaModule = totlaModule
									+ Integer.parseInt(permitArraysEntityResult.getSecondStringFour());
							if (permitArraysEntityResult.getSecondStringFive() != null) {
								totlaModule = totlaModule
										+ Integer.parseInt(permitArraysEntityResult.getSecondStringFive());
								if (permitArraysEntityResult.getSecondStringSix() != null) {
									totlaModule = totlaModule + permitArraysEntityResult.getSecondStringSix();
									if (permitArraysEntityResult.getSecondStringSeven() != null) {
										totlaModule = totlaModule + permitArraysEntityResult.getSecondStringSeven();
										if (permitArraysEntityResult.getSecondStringEight() != null) {
											totlaModule = totlaModule + permitArraysEntityResult.getSecondStringEight();
											if (permitArraysEntityResult.getSecondStringNine() != null) {
												totlaModule = totlaModule
														+ permitArraysEntityResult.getSecondStringNine();
												if (permitArraysEntityResult.getSecondStringTen() != null) {
													totlaModule = totlaModule
															+ permitArraysEntityResult.getSecondStringTen();
													if (permitArraysEntityResult.getSecondStringEleven() != null) {
														totlaModule = totlaModule
																+ permitArraysEntityResult.getSecondStringEleven();
														if (permitArraysEntityResult.getSecondStringTwelve() != null) {
															totlaModule = totlaModule
																	+ permitArraysEntityResult.getSecondStringTwelve();
														}

													}
												}

											}

										}

									}

								}
							}
						}
					}
				}
			}
			if (permitArraysEntityResult.getThirdStringOne() != null) {
				totlaModule = totlaModule + permitArraysEntityResult.getThirdStringOne();
				if (permitArraysEntityResult.getThirdStringTwo() != null) {
					totlaModule = totlaModule + permitArraysEntityResult.getThirdStringTwo();
					if (permitArraysEntityResult.getThirdStringThree() != null) {
						totlaModule = totlaModule + permitArraysEntityResult.getThirdStringThree();
						if (permitArraysEntityResult.getThirdStringFour() != null) {
							totlaModule = totlaModule + permitArraysEntityResult.getThirdStringFour();
							if (permitArraysEntityResult.getThirdStringFive() != null) {
								totlaModule = totlaModule + permitArraysEntityResult.getThirdStringFive();
								if (permitArraysEntityResult.getThirdStringSix() != null) {
									totlaModule = totlaModule + permitArraysEntityResult.getThirdStringSix();
									if (permitArraysEntityResult.getThirdStringSeven() != null) {
										totlaModule = totlaModule + permitArraysEntityResult.getThirdStringSeven();
										if (permitArraysEntityResult.getThirdStringEight() != null) {
											totlaModule = totlaModule + permitArraysEntityResult.getThirdStringEight();
											if (permitArraysEntityResult.getThirdStringNine() != null) {
												totlaModule = totlaModule
														+ permitArraysEntityResult.getThirdStringNine();
												if (permitArraysEntityResult.getThirdStringTen() != null) {
													totlaModule = totlaModule
															+ permitArraysEntityResult.getThirdStringTen();
													if (permitArraysEntityResult.getThirdStringEleven() != null) {
														totlaModule = totlaModule
																+ permitArraysEntityResult.getThirdStringEleven();
														if (permitArraysEntityResult.getThirdStringTwelve() != null) {
															totlaModule = totlaModule
																	+ permitArraysEntityResult.getThirdStringTwelve();
														}
													}
												}

											}

										}

									}

								}
							}
						}
					}
				}
			}
			if (permitArraysEntityResult.getFourthStringOne() != null) {
				totlaModule = totlaModule + permitArraysEntityResult.getFourthStringOne();
				if (permitArraysEntityResult.getFourthStringTwo() != null) {
					totlaModule = totlaModule + permitArraysEntityResult.getFourthStringTwo();
					if (permitArraysEntityResult.getFourthStringThree() != null) {
						totlaModule = totlaModule + permitArraysEntityResult.getFourthStringThree();
						if (permitArraysEntityResult.getFourthStringFour() != null) {
							totlaModule = totlaModule + permitArraysEntityResult.getFourthStringFour();
							if (permitArraysEntityResult.getFourthStringFive() != null) {
								totlaModule = totlaModule + permitArraysEntityResult.getFourthStringFive();
								if (permitArraysEntityResult.getFourthStringSix() != null) {
									totlaModule = totlaModule + permitArraysEntityResult.getFourthStringSix();
									if (permitArraysEntityResult.getFourthStringSeven() != null) {
										totlaModule = totlaModule + permitArraysEntityResult.getFourthStringSeven();
										if (permitArraysEntityResult.getFourthStringEight() != null) {
											totlaModule = totlaModule + permitArraysEntityResult.getFourthStringEight();
											if (permitArraysEntityResult.getFourthStringNine() != null) {
												totlaModule = totlaModule
														+ permitArraysEntityResult.getFourthStringNine();
												if (permitArraysEntityResult.getFourthStringTen() != null) {
													totlaModule = totlaModule
															+ permitArraysEntityResult.getFourthStringTen();
													if (permitArraysEntityResult.getFourthStringEleven() != null) {
														totlaModule = totlaModule
																+ permitArraysEntityResult.getFourthStringEleven();
														if (permitArraysEntityResult.getFourthStringTwelve() != null) {
															totlaModule = totlaModule
																	+ permitArraysEntityResult.getFourthStringTwelve();
														}
													}
												}
											}

										}

									}

								}
							}
						}
					}
				}
			}
			if (permitArraysEntityResult.getFifthStringOne() != null) {
				totlaModule = totlaModule + permitArraysEntityResult.getFifthStringOne();
				if (permitArraysEntityResult.getFifthStringTwo() != null) {
					totlaModule = totlaModule + permitArraysEntityResult.getFifthStringTwo();
					if (permitArraysEntityResult.getFifthStringThree() != null) {
						totlaModule = totlaModule + permitArraysEntityResult.getFifthStringThree();
						if (permitArraysEntityResult.getFifthStringFour() != null) {
							totlaModule = totlaModule + permitArraysEntityResult.getFifthStringFour();
							if (permitArraysEntityResult.getFifthStringFive() != null) {
								totlaModule = totlaModule + permitArraysEntityResult.getFifthStringFive();
								if (permitArraysEntityResult.getFifthStringSix() != null) {
									totlaModule = totlaModule + permitArraysEntityResult.getFifthStringSix();
									if (permitArraysEntityResult.getFifthStringSeven() != null) {
										totlaModule = totlaModule + permitArraysEntityResult.getFifthStringSeven();
										if (permitArraysEntityResult.getFifthStringEight() != null) {
											totlaModule = totlaModule + permitArraysEntityResult.getFifthStringEight();
											if (permitArraysEntityResult.getFifthStringNine() != null) {
												totlaModule = totlaModule
														+ permitArraysEntityResult.getFifthStringNine();
												if (permitArraysEntityResult.getFifthStringTen() != null) {
													totlaModule = totlaModule
															+ permitArraysEntityResult.getFifthStringTen();
													if (permitArraysEntityResult.getFifthStringEleven() != null) {
														totlaModule = totlaModule
																+ permitArraysEntityResult.getFifthStringEleven();
														if (permitArraysEntityResult.getFifthStringTwelve() != null) {
															totlaModule = totlaModule
																	+ permitArraysEntityResult.getFifthStringTwelve();
														}
													}
												}
											}

										}

									}

								}
							}
						}
					}
				}
			}

		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return totlaModule;
	}

	public int getTotalModuleMicro(PermitArraysEntity permitArraysEntityResult) {
		int totlaModule = 0;
		try {

			if (checkValue.isNumeric(permitArraysEntityResult.getNumberModulesACCircuitOne())) {
				totlaModule = totlaModule + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitOne());
				if (checkValue.isNumeric(permitArraysEntityResult.getNumberModulesACCircuitTwo())) {
					totlaModule = totlaModule
							+ Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwo());
					if (checkValue.isNumeric(permitArraysEntityResult.getNumberModulesACCircuitThree())) {
						totlaModule = totlaModule
								+ Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitThree());
						if (checkValue.isNumeric(permitArraysEntityResult.getNumberModulesACCircuitFour())) {
							totlaModule = totlaModule
									+ Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFour());
							if (checkValue.isNumeric(permitArraysEntityResult.getNumberModulesACCircuitFive())) {
								totlaModule = totlaModule
										+ Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFive());
								if (checkValue.isNumeric(permitArraysEntityResult.getNumberModulesACCircuitSix())) {
									totlaModule = totlaModule
											+ Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSix());
									if (checkValue.isNumeric(permitArraysEntityResult.getNumberModulesACCircuitSeven())) {
										totlaModule = totlaModule + Integer
												.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSeven());
										if (checkValue
												.isNumeric(permitArraysEntityResult.getNumberModulesACCircuitEight())) {
											totlaModule = totlaModule + Integer.parseInt(
													permitArraysEntityResult.getNumberModulesACCircuitEight());
											if (checkValue.isNumeric(
													permitArraysEntityResult.getNumberModulesACCircuitNine())) {
												totlaModule = totlaModule + Integer.parseInt(
														permitArraysEntityResult.getNumberModulesACCircuitNine());
												if (checkValue.isNumeric(
														permitArraysEntityResult.getNumberModulesACCircuitTen())) {
													totlaModule = totlaModule + Integer.parseInt(
															permitArraysEntityResult.getNumberModulesACCircuitTen());
													if (checkValue.isNumeric(
															permitArraysEntityResult.getNumberModulesACCircuitEleven())) {
														totlaModule = totlaModule
																+ Integer.parseInt(permitArraysEntityResult
																		.getNumberModulesACCircuitEleven());
														if (checkValue.isNumeric(permitArraysEntityResult
																.getNumberModulesACCircuitTweleve())) {
															totlaModule = totlaModule
																	+ Integer.parseInt(permitArraysEntityResult
																			.getNumberModulesACCircuitTweleve());

															if (checkValue.isNumeric(permitArraysEntityResult
																	.getNumberModulesACCircuitThirteen())) {
																totlaModule = totlaModule
																		+ Integer.parseInt(permitArraysEntityResult
																				.getNumberModulesACCircuitThirteen());
																if (checkValue.isNumeric(
																		permitArraysEntityResult
																				.getNumberModulesACCircuitFourteen())) {
																	totlaModule = totlaModule
																			+ Integer.parseInt(permitArraysEntityResult
																					.getNumberModulesACCircuitFourteen());
																	if (checkValue.isNumeric(
																			permitArraysEntityResult
																					.getNumberModulesACCircuitFifteen())) {
																		totlaModule = totlaModule + Integer
																				.parseInt(permitArraysEntityResult
																						.getNumberModulesACCircuitFifteen());
																		if (checkValue.isNumeric(
																				permitArraysEntityResult
																						.getNumberModulesACCircuitSixteen())) {
																			totlaModule = totlaModule + Integer
																					.parseInt(permitArraysEntityResult
																							.getNumberModulesACCircuitSixteen());
																			if (checkValue.isNumeric(
																					permitArraysEntityResult
																							.getNumberModulesACCircuitSeventeen())) {
																				totlaModule = totlaModule
																						+ Integer.parseInt(
																								permitArraysEntityResult
																										.getNumberModulesACCircuitSeventeen());
																				if (checkValue.isNumeric(
																						permitArraysEntityResult
																								.getNumberModulesACCircuitEightteen())) {
																					totlaModule = totlaModule
																							+ Integer.parseInt(
																									permitArraysEntityResult
																											.getNumberModulesACCircuitEightteen());
																					if (checkValue.isNumeric(
																							permitArraysEntityResult
																									.getNumberModulesACCircuitNineteen())) {
																						totlaModule = totlaModule
																								+ Integer.parseInt(
																										permitArraysEntityResult
																												.getNumberModulesACCircuitNineteen());
																						if (checkValue.isNumeric(
																								permitArraysEntityResult
																										.getNumberModulesACCircuitTwenty())) {
																							totlaModule = totlaModule
																									+ Integer.parseInt(
																											permitArraysEntityResult
																													.getNumberModulesACCircuitTwenty());
																							if (checkValue.isNumeric(
																									permitArraysEntityResult
																											.getNumberModulesACCircuitTwentyOne())) {
																								totlaModule = totlaModule
																										+ Integer
																												.parseInt(
																														permitArraysEntityResult
																																.getNumberModulesACCircuitTwentyOne());
																								if (checkValue
																										.isNumeric(
																												permitArraysEntityResult
																														.getNumberModulesACCircuitTwentyTwo())) {
																									totlaModule = totlaModule
																											+ Integer
																													.parseInt(
																															permitArraysEntityResult
																																	.getNumberModulesACCircuitTwentyTwo());
																									if (checkValue
																											.isNumeric(
																													permitArraysEntityResult
																															.getNumberModulesACCircuitTwentyThree())) {
																										totlaModule = totlaModule
																												+ Integer
																														.parseInt(
																																permitArraysEntityResult
																																		.getNumberModulesACCircuitTwentyThree());
																										if (checkValue
																												.isNumeric(
																														permitArraysEntityResult
																																.getNumberModulesACCircuitTwentyFour())) {
																											totlaModule = totlaModule
																													+ Integer
																															.parseInt(
																																	permitArraysEntityResult
																																			.getNumberModulesACCircuitTwentyFour());

																										}

																									}

																								}

																							}

																						}

																					}

																				}

																			}

																		}

																	}

																}

															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totlaModule;

	}

	public String getPermitSystemSize(Cmodulev2 moduleInfo, int totlaModule) {
		String permitSusyemSize = "";
		try {

			Locale locale = Locale.ENGLISH;
			NumberFormat nf = NumberFormat.getNumberInstance(locale);
			// A.B for trailing zeros:
			nf.setMinimumFractionDigits(3);
			// A.B round to 3 digits:
			nf.setMaximumFractionDigits(3);
			if (moduleInfo != null && moduleInfo.getStcRounded() != null) {
				Float permitSusyemSizeInt = Float.parseFloat(moduleInfo.getStcRounded());
				permitSusyemSizeInt = permitSusyemSizeInt * totlaModule;
				permitSusyemSizeInt = permitSusyemSizeInt / 1000;

				permitSusyemSize = String.valueOf(nf.format(permitSusyemSizeInt));
				if (permitSusyemSize.contains(",")) {
					permitSusyemSize = permitSusyemSize.replace(",", ".");
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return permitSusyemSize;
	}

}
