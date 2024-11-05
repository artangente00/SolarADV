package com.PlayGroundAdv.Solar.service.export_project;

import java.util.LinkedHashMap;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.repositories.libraries.ConvertersRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.service.project.GetProjectDetailsUtils;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ExportArraysInfo {

	final CheckValueTypesService checkValueTypes;
	final InverterRepository inverterRepo;
	final ModuleRepository moduleRepo;
	final ConvertersRepository convertersRepo;
	final GetProjectDetailsUtils getProjectDetailsUtils;

	public ExportArraysInfo(CheckValueTypesService checkValueTypes, InverterRepository inverterRepo,
			ModuleRepository moduleRepo, ConvertersRepository convertersRepo,
			GetProjectDetailsUtils getProjectDetailsUtils) {
		super();
		this.checkValueTypes = checkValueTypes;
		this.inverterRepo = inverterRepo;
		this.moduleRepo = moduleRepo;
		this.convertersRepo = convertersRepo;
		this.getProjectDetailsUtils = getProjectDetailsUtils;
	}

	public LinkedHashMap<String, String> arraysMap(GetPermitByIdResult permit) {

		LinkedHashMap<String, String> arrays = new LinkedHashMap<>();

		try {

			if (checkValueTypes.Equals(permit.getPermitArraysEntity().getDeviceToIncorporate(), "Neither")) {
				arrays.put("Inverter Technology", "String Inverters");

			} else if (checkValueTypes.Equals(permit.getPermitArraysEntity().getDeviceToIncorporate(),
					"System Optimizer")) {
				arrays.put("Inverter Technology", "System Optimizers or Module Level RSD’s with String Inverter(s)");

			} else {
				arrays.put("Inverter Technology",
						checkValueTypes.convert(permit.getPermitArraysEntity().getDeviceToIncorporate()));
			}

			if (Boolean.TRUE.equals(getProjectDetailsUtils
					.isStringOrOptimizerProject(permit.getPermitArraysEntity().getDeviceToIncorporate()))) {
				arrays.putAll(mapInverterLocations(permit));
			}

			if (checkValueTypes.isLongPositive(permit.getPermitArraysEntity().getPvModuleModEl())) {
				Cmodulev2 moduleInfo = moduleRepo.findById(permit.getPermitArraysEntity().getPvModuleModEl())
						.orElse(new Cmodulev2());
				arrays.put("PV Module Model",
						!Boolean.TRUE.equals(moduleInfo.isDeleted()) ? moduleInfo.getMake() + " " + moduleInfo.getModel()
								: "PV Module Deleted");
			} else {
				arrays.put("PV Module Model", "");
			}

			if (Boolean.TRUE.equals(getProjectDetailsUtils
					.isStringOrOptimizerProject(permit.getPermitArraysEntity().getDeviceToIncorporate()))) {
				arrays.putAll(mapInverterSystem(permit));
			} else {
				arrays.putAll(mapMicroInverter(permit));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrays;

	}

	public LinkedHashMap<String, String> mapInverterLocations(GetPermitByIdResult permit) {
		LinkedHashMap<String, String> arrays = new LinkedHashMap<>();
		try {
			if (Boolean.TRUE.equals(permit.getPermitArraysEntity().getRoofMounted()) && checkValueTypes
					.Equals(permit.getPermitHomeSiteEntityResult().getRoofRafterOther(), "Flat Roof With Trusses")) {
				arrays.put("Inverter(s) will be installed on the Roof",
						Boolean.TRUE.equals(permit.getPermitArraysEntity().getInverterInstalledOnRoof()) ? "Yes"
								: "No");
			}
			if (Boolean.TRUE.equals(permit.getPermitArraysEntity().getGroundMounted())
					|| Boolean.TRUE.equals(permit.getPermitArraysEntity().getCarportMounted())) {
				arrays.put("Inverter(s) Location",
						checkValueTypes.convert(permit.getPermitArraysEntity().getInverterLocation()));

				if (permit.getPermitArraysEntity().getSecondInverterModel() != null) {
					arrays.put("Will all Inverters be Located in the same place?",
							checkValueTypes.convert(permit.getPermitArraysEntity().getInverterSameLocation()));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrays;
	}

	public LinkedHashMap<String, String> mapInverterSystem(GetPermitByIdResult permit) {
		LinkedHashMap<String, String> arrays = new LinkedHashMap<>();
		try {

			if (permit.getPermitArraysEntity().getInverterModel() != null) {
				arrays.putAll(mapFirstInverter(permit));

				if (permit.getPermitArraysEntity().getSecondInverterModel() != null) {
					arrays.putAll(mapSecondInverter(permit));

					if (permit.getPermitArraysEntity().getThirdInverterModel() != null) {
						arrays.putAll(mapThirdInverter(permit));

						if (permit.getPermitArraysEntity().getFourthInverterModel() != null) {
							arrays.putAll(mapFourthInverter(permit));

							if (permit.getPermitArraysEntity().getFifthInverterModel() != null) {
								arrays.putAll(mapFifthInverter(permit));

							} else {
								arrays.put("Inverter #5 Model", "");
							}

						} else {
							arrays.put("Inverter #4 Model", "");
						}
					} else {
						arrays.put("Inverter #3 Model", "");
					}
				} else {
					arrays.put("Inverter #2 Model", "");
				}
			} else {
				arrays.put("Inverter Model", "");
			}
			
			//R.G 27-01-2022 PP-688
			if (checkValueTypes.isStringNotEmpty(permit.getPermitEntity().getInsertRoofNote())) {
				arrays.put("Non-Eligible Inverters notes", checkValueTypes
						.convert(permit.getPermitArraysEntity().getEnteranyTransformer()));
			}
			
			
			if (checkValueTypes.Equals(permit.getPermitArraysEntity().getDeviceToIncorporate(), "System Optimizer")) {
				if (checkValueTypes.Equals(permit.getPermitArraysEntity().getSystemOptimizerModel(), 0)) {
					arrays.put("DC - DC Optimizer or Module Level RSD Device Model", "Deleted From Favorite List");

				} else if (checkValueTypes.Equals(permit.getPermitArraysEntity().getSystemOptimizerModel(), -1)) {
					arrays.put("DC - DC Optimizer or Module Level RSD Device Model", "Deleted");

				} else if (checkValueTypes.Equals(permit.getPermitArraysEntity().getDeviceToIncorporate(),
						"System Optimizer") && permit.getPermitArraysEntity().getSystemOptimizerModel() != null
						&& checkValueTypes.isLongPositive(permit.getPermitArraysEntity().getSystemOptimizerModel())) {
					DCOptimizerEntity dc = convertersRepo.findById(permit.getPermitArraysEntity().getSystemOptimizerModel())
							.orElse(new DCOptimizerEntity());
					arrays.put("DC - DC Optimizer or Module Level RSD Device Model",
							checkValueTypes.convert(dc.getManufacturer() + " " + dc.getModel()));
				} else {
					arrays.put("DC - DC Optimizer or Module Level RSD Device Model", "");
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrays;
	}

	public LinkedHashMap<String, String> mapMicroInverter(GetPermitByIdResult permit) {
		LinkedHashMap<String, String> arrays = new LinkedHashMap<>();
		try {
			if (checkValueTypes.Equals(permit.getPermitArraysEntity().getDeviceToIncorporate(), "Micro Inverter")) {
				if (checkValueTypes.Equals(permit.getPermitArraysEntity().getMicroInverter(), 0)) {
					arrays.put("Microinverter Manufacturer", "Deleted From Favorite List");

				} else if (checkValueTypes.Equals(permit.getPermitArraysEntity().getMicroInverter(), -1)) {
					arrays.put("Microinverter Manufacturer", "Deleted");

				} else if (checkValueTypes.isLongPositive(permit.getPermitArraysEntity().getMicroInverter())) {
					Inverters microInverterInfo = inverterRepo.findById(permit.getPermitArraysEntity().getMicroInverter()).orElse(new Inverters());
					arrays.put("Microinverter", checkValueTypes.convert(!Boolean.TRUE.equals(microInverterInfo.isDeleted()) ? microInverterInfo.getMake()+" "+microInverterInfo.getModel()
							: "Deleted"));
				} else {
					arrays.put("Microinverter Model", "");
				}
			}
			
			
			if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitOne())) {
				arrays.put("Number of Modules on AC Circuit #1", permit.getPermitArraysEntity().getNumberModulesACCircuitOne());
				arrays.put("Branch Circuit #1 OCPD", permit.getPermitArraysEntity().getOcpdOne());
				
				if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitTwo())) {
					arrays.put("Number of Modules on AC Circuit #2", permit.getPermitArraysEntity().getNumberModulesACCircuitTwo());
					arrays.put("Branch Circuit #2 OCPD", permit.getPermitArraysEntity().getOcpdTwo());
					
					if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitThree())) {
						arrays.put("Number of Modules on AC Circuit #3", permit.getPermitArraysEntity().getNumberModulesACCircuitThree());
						arrays.put("Branch Circuit #3 OCPD", permit.getPermitArraysEntity().getOcpdThree());
						
						if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitFour())) {
							arrays.put("Number of Modules on AC Circuit #4", permit.getPermitArraysEntity().getNumberModulesACCircuitFour());
							arrays.put("Branch Circuit #4 OCPD", permit.getPermitArraysEntity().getOcpdFour());
							
							if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitFive())) {
								arrays.put("Number of Modules on AC Circuit #5", permit.getPermitArraysEntity().getNumberModulesACCircuitFive());
								arrays.put("Branch Circuit #5 OCPD", permit.getPermitArraysEntity().getOcpdFive());
								
								if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitSix())) {
									arrays.put("Number of Modules on AC Circuit #6", permit.getPermitArraysEntity().getNumberModulesACCircuitSix());
									arrays.put("Branch Circuit #6 OCPD", permit.getPermitArraysEntity().getOcpdSix());
									
									if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitSeven())) {
										arrays.put("Number of Modules on AC Circuit #7", permit.getPermitArraysEntity().getNumberModulesACCircuitSeven());
										arrays.put("Branch Circuit #7 OCPD", permit.getPermitArraysEntity().getOcpdSeven());
										
										if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitEight())) {
											arrays.put("Number of Modules on AC Circuit #8", permit.getPermitArraysEntity().getNumberModulesACCircuitEight());
											arrays.put("Branch Circuit #8 OCPD", permit.getPermitArraysEntity().getOcpdEight());
											
											if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitNine())) {
												arrays.put("Number of Modules on AC Circuit #9", permit.getPermitArraysEntity().getNumberModulesACCircuitNine());
												arrays.put("Branch Circuit #9 OCPD", permit.getPermitArraysEntity().getOcpdNine());
												
												if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitTen())) {
													arrays.put("Number of Modules on AC Circuit #10", permit.getPermitArraysEntity().getNumberModulesACCircuitTen());
													arrays.put("Branch Circuit #10 OCPD", permit.getPermitArraysEntity().getOcpdTen());
													
													if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitEleven())) {
														arrays.put("Number of Modules on AC Circuit #11", permit.getPermitArraysEntity().getNumberModulesACCircuitEleven());
														arrays.put("Branch Circuit #11 OCPD", permit.getPermitArraysEntity().getOcpdEleven());
														
														if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitTweleve())) {
															arrays.put("Number of Modules on AC Circuit #12", permit.getPermitArraysEntity().getNumberModulesACCircuitTweleve());
															arrays.put("Branch Circuit #12 OCPD", permit.getPermitArraysEntity().getOcpdTwelve());
															
															if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitThirteen())) {
																arrays.put("Number of Modules on AC Circuit #13", permit.getPermitArraysEntity().getNumberModulesACCircuitThirteen());
																arrays.put("Branch Circuit #13 OCPD", permit.getPermitArraysEntity().getOcpdThirteen());
																
																if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitFourteen())) {
																	arrays.put("Number of Modules on AC Circuit #14", permit.getPermitArraysEntity().getNumberModulesACCircuitFourteen());
																	arrays.put("Branch Circuit #14 OCPD", permit.getPermitArraysEntity().getOcpdFourteen());
																	
																	if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitFifteen())) {
																		arrays.put("Number of Modules on AC Circuit #15", permit.getPermitArraysEntity().getNumberModulesACCircuitFifteen());
																		arrays.put("Branch Circuit #15 OCPD", permit.getPermitArraysEntity().getOcpdFifteen());
																		
																		if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitSixteen())) {
																			arrays.put("Number of Modules on AC Circuit #16", permit.getPermitArraysEntity().getNumberModulesACCircuitSixteen());
																			arrays.put("Branch Circuit #16 OCPD", permit.getPermitArraysEntity().getOcpdSixteen());
																			
																			if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitSeventeen())) {
																				arrays.put("Number of Modules on AC Circuit #17", permit.getPermitArraysEntity().getNumberModulesACCircuitSeventeen());
																				arrays.put("Branch Circuit #17 OCPD", permit.getPermitArraysEntity().getOcpdSeventeen());
																				
																				if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitEightteen())) {
																					arrays.put("Number of Modules on AC Circuit #18", permit.getPermitArraysEntity().getNumberModulesACCircuitEightteen());
																					arrays.put("Branch Circuit #18 OCPD", permit.getPermitArraysEntity().getOcpdEightteen());
																					
																					if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitNineteen())) {
																						arrays.put("Number of Modules on AC Circuit #19", permit.getPermitArraysEntity().getNumberModulesACCircuitNineteen());
																						arrays.put("Branch Circuit #19 OCPD", permit.getPermitArraysEntity().getOcpdNineteen());
																						
																						if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitTwenty())) {
																							arrays.put("Number of Modules on AC Circuit #20", permit.getPermitArraysEntity().getNumberModulesACCircuitTwenty());
																							arrays.put("Branch Circuit #20 OCPD", permit.getPermitArraysEntity().getOcpdTwenty());
																							
																							if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitTwentyOne())) {
																								arrays.put("Number of Modules on AC Circuit #21", permit.getPermitArraysEntity().getNumberModulesACCircuitTwentyOne());
																								arrays.put("Branch Circuit #21 OCPD", permit.getPermitArraysEntity().getOcpdTwentyOne());
																								
																								if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitTwentyTwo())) {
																									arrays.put("Number of Modules on AC Circuit #22", permit.getPermitArraysEntity().getNumberModulesACCircuitTwentyTwo());
																									arrays.put("Branch Circuit #22 OCPD", permit.getPermitArraysEntity().getOcpdTwentyTwo());
																									
																									if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitTwentyThree())) {
																										arrays.put("Number of Modules on AC Circuit #23", permit.getPermitArraysEntity().getNumberModulesACCircuitTwentyThree());
																										arrays.put("Branch Circuit #23 OCPD", permit.getPermitArraysEntity().getOcpdTwentyThree());
																										
																										if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getNumberModulesACCircuitTwentyFour())) {
																											arrays.put("Number of Modules on AC Circuit #24", permit.getPermitArraysEntity().getNumberModulesACCircuitTwentyFour());
																											arrays.put("Branch Circuit #24 OCPD", permit.getPermitArraysEntity().getOcpdTwentyFour());
																											
																										} else {
																											arrays.put("Number of Modules on AC Circuit #24", "");
																											arrays.put("Branch Circuit #24 OCPD", "");
																										}
																										
																									} else {
																										arrays.put("Number of Modules on AC Circuit #23", "");
																										arrays.put("Branch Circuit #23 OCPD", "");
																									}
																									
																								} else {
																									arrays.put("Number of Modules on AC Circuit #22", "");
																									arrays.put("Branch Circuit #22 OCPD", "");
																								}
																								
																							} else {
																								arrays.put("Number of Modules on AC Circuit #21", "");
																								arrays.put("Branch Circuit #21 OCPD", "");
																							}
																							
																						} else {
																							arrays.put("Number of Modules on AC Circuit #20", "");
																							arrays.put("Branch Circuit #20 OCPD", "");
																						}
																						
																					} else {
																						arrays.put("Number of Modules on AC Circuit #19", "");
																						arrays.put("Branch Circuit #19 OCPD", "");
																					}
																					
																				} else {
																					arrays.put("Number of Modules on AC Circuit #18", "");
																					arrays.put("Branch Circuit #18 OCPD", "");
																				}
																				
																			} else {
																				arrays.put("Number of Modules on AC Circuit #17", "");
																				arrays.put("Branch Circuit #17 OCPD", "");
																			}
																			
																		} else {
																			arrays.put("Number of Modules on AC Circuit #16", "");
																			arrays.put("Branch Circuit #16 OCPD", "");
																		}
																		
																	} else {
																		arrays.put("Number of Modules on AC Circuit #15", "");
																		arrays.put("Branch Circuit #15 OCPD", "");
																	}
																	
																} else {
																	arrays.put("Number of Modules on AC Circuit #14", "");
																	arrays.put("Branch Circuit #14 OCPD", "");
																}
																
															} else {
																arrays.put("Number of Modules on AC Circuit #13", "");
																arrays.put("Branch Circuit #13 OCPD", "");
															}
															
														} else {
															arrays.put("Number of Modules on AC Circuit #12", "");
															arrays.put("Branch Circuit #12 OCPD", "");
														}
														
													} else {
														arrays.put("Number of Modules on AC Circuit #11", "");
														arrays.put("Branch Circuit #11 OCPD", "");
													}
													
												} else {
													arrays.put("Number of Modules on AC Circuit #10", "");
													arrays.put("Branch Circuit #10 OCPD", "");
												}
												
											} else {
												arrays.put("Number of Modules on AC Circuit #9", "");
												arrays.put("Branch Circuit #9 OCPD", "");
											}
											
										} else {
											arrays.put("Number of Modules on AC Circuit #8", "");
											arrays.put("Branch Circuit #8 OCPD", "");
										}
										
									} else {
										arrays.put("Number of Modules on AC Circuit #7", "");
										arrays.put("Branch Circuit #7 OCPD", "");
									}
									
								} else {
									arrays.put("Number of Modules on AC Circuit #6", "");
									arrays.put("Branch Circuit #6 OCPD", "");
								}
								
							} else {
								arrays.put("Number of Modules on AC Circuit #5", "");
								arrays.put("Branch Circuit #5 OCPD", "");
							}
							
						} else {
							arrays.put("Number of Modules on AC Circuit #4", "");
							arrays.put("Branch Circuit #4 OCPD", "");
						}
						
					} else {
						arrays.put("Number of Modules on AC Circuit #3", "");
						arrays.put("Branch Circuit #3 OCPD", "");
					}
					
				} else {
					arrays.put("Number of Modules on AC Circuit #2", "");
					arrays.put("Branch Circuit #2 OCPD", "");
				}
			} else {
				arrays.put("Number of Modules on AC Circuit #1", "");
				arrays.put("Branch Circuit #1 OCPD", "");
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrays;
	}

	public LinkedHashMap<String, String> mapFirstInverter(GetPermitByIdResult permit) {
		LinkedHashMap<String, String> arrays = new LinkedHashMap<>();
		try {
			if (checkValueTypes.Equals(permit.getPermitArraysEntity().getInverterModel(), 0)) {
				arrays.put("Inverter Model", "Inverter Deleted From Favorite List");

			} else if (checkValueTypes.Equals(permit.getPermitArraysEntity().getInverterModel(), -1)) {
				arrays.put("Inverter Model", "Inverter Deleted");

			} else if (checkValueTypes.isLongPositive(permit.getPermitArraysEntity().getInverterModel())) {

				Inverters inverterInfo = inverterRepo.findById(permit.getPermitArraysEntity().getInverterModel()).orElse(new Inverters());
				arrays.put("Inverter Model",
						checkValueTypes.convert(!Boolean.TRUE.equals(inverterInfo.isDeleted())
								? inverterInfo.getMake() + " " + inverterInfo.getModel()
								: "Inverter Deleted"));

				if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getStringOne())) {
					arrays.put("Number of Modules in Each String1",
							checkValueTypes.convert(permit.getPermitArraysEntity().getStringOne()));

					if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getStringTwo())) {
						arrays.put("Number of Modules in Each String2",
								checkValueTypes.convert(permit.getPermitArraysEntity().getStringTwo()));

						if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getStringThree())) {
							arrays.put("Number of Modules in Each String3",
									checkValueTypes.convert(permit.getPermitArraysEntity().getStringThree()));

							if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getStringFour())) {
								arrays.put("Number of Modules in Each String4",
										checkValueTypes.convert(permit.getPermitArraysEntity().getStringFour()));

								if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getStringFive())) {
									arrays.put("Number of Modules in Each String5",
											checkValueTypes.convert(permit.getPermitArraysEntity().getStringOne()));

									if (checkValueTypes
											.isNumericPositive(permit.getPermitArraysEntity().getStringSix())) {
										arrays.put("Number of Modules in Each String6", checkValueTypes
												.convert(permit.getPermitArraysEntity().getStringSix() + ""));

										if (checkValueTypes
												.isNumericPositive(permit.getPermitArraysEntity().getStringSeven())) {
											arrays.put("Number of Modules in Each String7", checkValueTypes
													.convert(permit.getPermitArraysEntity().getStringSeven() + ""));

											if (checkValueTypes.isNumericPositive(
													permit.getPermitArraysEntity().getStringEight())) {
												arrays.put("Number of Modules in Each String8", checkValueTypes
														.convert(permit.getPermitArraysEntity().getStringEight() + ""));

												if (checkValueTypes.isNumericPositive(
														permit.getPermitArraysEntity().getStringNine())) {
													arrays.put("Number of Modules in Each String9",
															checkValueTypes.convert(
																	permit.getPermitArraysEntity().getStringNine()
																			+ ""));

													if (checkValueTypes.isNumericPositive(
															permit.getPermitArraysEntity().getStringTen())) {
														arrays.put("Number of Modules in Each String10",
																checkValueTypes.convert(
																		permit.getPermitArraysEntity().getStringTen()
																				+ ""));

														if (checkValueTypes.isNumericPositive(
																permit.getPermitArraysEntity().getStringEleven())) {
															arrays.put("Number of Modules in Each String11",
																	checkValueTypes
																			.convert(permit.getPermitArraysEntity()
																					.getStringEleven() + ""));

															if (checkValueTypes.isNumericPositive(
																	permit.getPermitArraysEntity().getStringTwelve())) {
																arrays.put("Number of Modules in Each String12",
																		checkValueTypes
																				.convert(permit.getPermitArraysEntity()
																						.getStringTwelve() + ""));
															} else {
																arrays.put("Number of Modules in Each String12", "");
															}
														} else {
															arrays.put("Number of Modules in Each String11", "");
														}
													} else {
														arrays.put("Number of Modules in Each String10", "");
													}
												} else {
													arrays.put("Number of Modules in Each String9", "");
												}
											} else {
												arrays.put("Number of Modules in Each String8", "");
											}
										} else {
											arrays.put("Number of Modules in Each String7", "");
										}
									} else {
										arrays.put("Number of Modules in Each String6", "");
									}
								} else {
									arrays.put("Number of Modules in Each String5", "");
								}
							} else {
								arrays.put("Number of Modules in Each String4", "");
							}
						} else {
							arrays.put("Number of Modules in Each String3", "");
						}
					} else {
						arrays.put("Number of Modules in Each String2", "");
					}
				} else {
					arrays.put("Number of Modules in Each String1", "");
				}

			} else {
				arrays.put("Inverter Model", "");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrays;
	}

	public LinkedHashMap<String, String> mapSecondInverter(GetPermitByIdResult permit) {
		LinkedHashMap<String, String> arrays = new LinkedHashMap<>();
		try {
			if (checkValueTypes.Equals(permit.getPermitArraysEntity().getSecondInverterModel(), 0)) {
				arrays.put("Inverter #2 Model", "Inverter Deleted From Favorite List");

			} else if (checkValueTypes.Equals(permit.getPermitArraysEntity().getSecondInverterModel(), -1)) {
				arrays.put("Inverter #2 Model", "Inverter Deleted");

			} else if (checkValueTypes.isLongPositive(permit.getPermitArraysEntity().getSecondInverterModel())) {

				Inverters inverterInfo = inverterRepo.findById(permit.getPermitArraysEntity().getSecondInverterModel())
						.orElse(new Inverters());
				arrays.put("Inverter #2 Model",
						checkValueTypes.convert(!Boolean.TRUE.equals(inverterInfo.isDeleted())
								? inverterInfo.getMake() + " " + inverterInfo.getModel()
								: "Inverter #2 Model"));
				
				if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getSecondStringOne())) {
					arrays.put("Number of Modules in Each Second String1",
							checkValueTypes.convert(permit.getPermitArraysEntity().getSecondStringOne()));

					if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getSecondStringTwo())) {
						arrays.put("Number of Modules in Each Second String2",
								checkValueTypes.convert(permit.getPermitArraysEntity().getSecondStringTwo()));

						if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getSecondStringThree())) {
							arrays.put("Number of Modules in Each Second String3",
									checkValueTypes.convert(permit.getPermitArraysEntity().getSecondStringThree()));

							if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getSecondStringFour())) {
								arrays.put("Number of Modules in Each Second String4",
										checkValueTypes.convert(permit.getPermitArraysEntity().getSecondStringFour()));

								if (checkValueTypes.isNumeric(permit.getPermitArraysEntity().getSecondStringOne())) {
									arrays.put("Number of Modules in Each Second String5", checkValueTypes
											.convert(permit.getPermitArraysEntity().getSecondStringOne()));

									if (checkValueTypes
											.isNumericPositive(permit.getPermitArraysEntity().getSecondStringSix())) {
										arrays.put("Number of Modules in Each Second String6", checkValueTypes
												.convert(permit.getPermitArraysEntity().getSecondStringSix() + ""));

										if (checkValueTypes.isNumericPositive(
												permit.getPermitArraysEntity().getSecondStringSeven())) {
											arrays.put("Number of Modules in Each Second String7", checkValueTypes.convert(
													permit.getPermitArraysEntity().getSecondStringSeven() + ""));

											if (checkValueTypes.isNumericPositive(
													permit.getPermitArraysEntity().getSecondStringEight())) {
												arrays.put("Number of Modules in Each Second String8", checkValueTypes.convert(
														permit.getPermitArraysEntity().getSecondStringEight() + ""));

												if (checkValueTypes.isNumericPositive(
														permit.getPermitArraysEntity().getSecondStringNine())) {
													arrays.put("Number of Modules in Each Second String9",
															checkValueTypes.convert(
																	permit.getPermitArraysEntity().getSecondStringNine()
																			+ ""));

													if (checkValueTypes.isNumericPositive(
															permit.getPermitArraysEntity().getSecondStringTen())) {
														arrays.put("Number of Modules in Each Second String10",
																checkValueTypes.convert(permit.getPermitArraysEntity()
																		.getSecondStringTen() + ""));

														if (checkValueTypes.isNumericPositive(permit
																.getPermitArraysEntity().getSecondStringEleven())) {
															arrays.put("Number of Modules in Each Second String11",
																	checkValueTypes
																			.convert(permit.getPermitArraysEntity()
																					.getSecondStringEleven() + ""));

															if (checkValueTypes.isNumericPositive(permit
																	.getPermitArraysEntity().getSecondStringTwelve())) {
																arrays.put("Number of Modules in Each Second String12",
																		checkValueTypes
																				.convert(permit.getPermitArraysEntity()
																						.getSecondStringTwelve() + ""));
															} else {
																arrays.put("Number of Modules in Each Second String12", "");
															}
														} else {
															arrays.put("Number of Modules in Each Second String11", "");
														}
													} else {
														arrays.put("Number of Modules in Each Second String10", "");
													}
												} else {
													arrays.put("Number of Modules in Each Second String9", "");
												}
											} else {
												arrays.put("Number of Modules in Each Second String8", "");
											}
										} else {
											arrays.put("Number of Modules in Each Second String7", "");
										}
									} else {
										arrays.put("Number of Modules in Each Second String6", "");
									}
								} else {
									arrays.put("Number of Modules in Each Second String5", "");
								}
							} else {
								arrays.put("Number of Modules in Each Second String4", "");
							}
						} else {
							arrays.put("Number of Modules in Each Second String3", "");
						}
					} else {
						arrays.put("Number of Modules in Each Second String2", "");
					}
				} else {
					arrays.put("Number of Modules in Each Second String1", "");
				}

			} else {
				arrays.put("Inverter #2 Model", "");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrays;
	}

	public LinkedHashMap<String, String> mapThirdInverter(GetPermitByIdResult permit) {
		LinkedHashMap<String, String> arrays = new LinkedHashMap<>();
		try {

			if (checkValueTypes.Equals(permit.getPermitArraysEntity().getThirdInverterModel(), 0)) {
				arrays.put("Inverter #3 Model", "Inverter Deleted From Favorite List");

			} else if (checkValueTypes.Equals(permit.getPermitArraysEntity().getThirdInverterModel(), -1)) {
				arrays.put("Inverter #3 Model", "Inverter Deleted");

			} else if (checkValueTypes.isLongPositive(permit.getPermitArraysEntity().getThirdInverterModel())) {

				Inverters inverterInfo = inverterRepo.findById(permit.getPermitArraysEntity().getThirdInverterModel())
						.orElse(new Inverters());
				arrays.put("Inverter #3 Model",
						checkValueTypes.convert(!Boolean.TRUE.equals(inverterInfo.isDeleted())
								? inverterInfo.getMake() + " " + inverterInfo.getModel()
								: "Inverter #3 Model"));

				if (checkValueTypes.isNumericPositive(permit.getPermitArraysEntity().getThirdStringOne())) {
					arrays.put("Number of Modules in Each Third String1",
							checkValueTypes.convert(permit.getPermitArraysEntity().getThirdStringOne() + ""));

					if (checkValueTypes.isNumericPositive(permit.getPermitArraysEntity().getThirdStringTwo())) {
						arrays.put("Number of Modules in Each Third String2",
								checkValueTypes.convert(permit.getPermitArraysEntity().getThirdStringTwo() + ""));

						if (checkValueTypes.isNumericPositive(permit.getPermitArraysEntity().getThirdStringThree())) {
							arrays.put("Number of Modules in Each Third String3",
									checkValueTypes.convert(permit.getPermitArraysEntity().getThirdStringThree() + ""));

							if (checkValueTypes
									.isNumericPositive(permit.getPermitArraysEntity().getThirdStringFour())) {
								arrays.put("Number of Modules in Each Third String4", checkValueTypes
										.convert(permit.getPermitArraysEntity().getThirdStringFour() + ""));

								if (checkValueTypes
										.isNumericPositive(permit.getPermitArraysEntity().getThirdStringOne())) {
									arrays.put("Number of Modules in Each Third String5", checkValueTypes
											.convert(permit.getPermitArraysEntity().getThirdStringOne() + ""));

									if (checkValueTypes
											.isNumericPositive(permit.getPermitArraysEntity().getThirdStringSix())) {
										arrays.put("Number of Modules in Each Third String6", checkValueTypes
												.convert(permit.getPermitArraysEntity().getThirdStringSix() + ""));

										if (checkValueTypes.isNumericPositive(
												permit.getPermitArraysEntity().getThirdStringSeven())) {
											arrays.put("Number of Modules in Each Third String7", checkValueTypes.convert(
													permit.getPermitArraysEntity().getThirdStringSeven() + ""));

											if (checkValueTypes.isNumericPositive(
													permit.getPermitArraysEntity().getThirdStringEight())) {
												arrays.put("Number of Modules in Each Third String8", checkValueTypes.convert(
														permit.getPermitArraysEntity().getThirdStringEight() + ""));

												if (checkValueTypes.isNumericPositive(
														permit.getPermitArraysEntity().getThirdStringNine())) {
													arrays.put("Number of Modules in Each Third String9",
															checkValueTypes.convert(
																	permit.getPermitArraysEntity().getThirdStringNine()
																			+ ""));

													if (checkValueTypes.isNumericPositive(
															permit.getPermitArraysEntity().getThirdStringTen())) {
														arrays.put("Number of Modules in Each Third String10",
																checkValueTypes.convert(permit.getPermitArraysEntity()
																		.getThirdStringTen() + ""));

														if (checkValueTypes.isNumericPositive(permit
																.getPermitArraysEntity().getThirdStringEleven())) {
															arrays.put("Number of Modules in Each Third String11",
																	checkValueTypes
																			.convert(permit.getPermitArraysEntity()
																					.getThirdStringEleven() + ""));

															if (checkValueTypes.isNumericPositive(permit
																	.getPermitArraysEntity().getThirdStringTwelve())) {
																arrays.put("Number of Modules in Each Third String12",
																		checkValueTypes
																				.convert(permit.getPermitArraysEntity()
																						.getThirdStringTwelve() + ""));
															} else {
																arrays.put("Number of Modules in Each Third String12", "");
															}
														} else {
															arrays.put("Number of Modules in Each Third String11", "");
														}
													} else {
														arrays.put("Number of Modules in Each Third String10", "");
													}
												} else {
													arrays.put("Number of Modules in Each Third String9", "");
												}
											} else {
												arrays.put("Number of Modules in Each Third String8", "");
											}
										} else {
											arrays.put("Number of Modules in Each Third String7", "");
										}
									} else {
										arrays.put("Number of Modules in Each Third String6", "");
									}
								} else {
									arrays.put("Number of Modules in Each Third String5", "");
								}
							} else {
								arrays.put("Number of Modules in Each Third String4", "");
							}
						} else {
							arrays.put("Number of Modules in Each Third String3", "");
						}
					} else {
						arrays.put("Number of Modules in Each Third String2", "");
					}
				} else {
					arrays.put("Number of Modules in Each Third String1", "");
				}

			} else {
				arrays.put("Inverter #3 Model", "");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrays;
	}

	public LinkedHashMap<String, String> mapFourthInverter(GetPermitByIdResult permit) {
		LinkedHashMap<String, String> arrays = new LinkedHashMap<>();
		try {
			if (checkValueTypes.Equals(permit.getPermitArraysEntity().getFourthInverterModel(), 0)) {
				arrays.put("Inverter #4 Model", "Inverter Deleted From Favorite List");

			} else if (checkValueTypes.Equals(permit.getPermitArraysEntity().getFourthInverterModel(), -1)) {
				arrays.put("Inverter #4 Model", "Inverter Deleted");

			} else if (checkValueTypes.isLongPositive(permit.getPermitArraysEntity().getFourthInverterModel())) {

				Inverters inverterInfo = inverterRepo.findById(permit.getPermitArraysEntity().getFourthInverterModel())
						.orElse(new Inverters());
				arrays.put("Inverter #4 Model",
						checkValueTypes.convert(!Boolean.TRUE.equals(inverterInfo.isDeleted())
								? inverterInfo.getMake() + " " + inverterInfo.getModel()
								: "Inverter #4 Model"));

				if (checkValueTypes.isNumericPositive(permit.getPermitArraysEntity().getFourthStringOne())) {
					arrays.put("Number of Modules in Each Fourth String1",
							checkValueTypes.convert(permit.getPermitArraysEntity().getFourthStringOne() + ""));

					if (checkValueTypes.isNumericPositive(permit.getPermitArraysEntity().getFourthStringTwo())) {
						arrays.put("Number of Modules in Each Fourth String2",
								checkValueTypes.convert(permit.getPermitArraysEntity().getFourthStringTwo() + ""));

						if (checkValueTypes.isNumericPositive(permit.getPermitArraysEntity().getFourthStringThree())) {
							arrays.put("Number of Modules in Each Fourth String3", checkValueTypes
									.convert(permit.getPermitArraysEntity().getFourthStringThree() + ""));

							if (checkValueTypes
									.isNumericPositive(permit.getPermitArraysEntity().getFourthStringFour())) {
								arrays.put("Number of Modules in Each Fourth String4", checkValueTypes
										.convert(permit.getPermitArraysEntity().getFourthStringFour() + ""));

								if (checkValueTypes
										.isNumericPositive(permit.getPermitArraysEntity().getFourthStringOne())) {
									arrays.put("Number of Modules in Each Fourth String5", checkValueTypes
											.convert(permit.getPermitArraysEntity().getFourthStringOne() + ""));

									if (checkValueTypes
											.isNumericPositive(permit.getPermitArraysEntity().getFourthStringSix())) {
										arrays.put("Number of Modules in Each Fourth String6", checkValueTypes
												.convert(permit.getPermitArraysEntity().getFourthStringSix() + ""));

										if (checkValueTypes.isNumericPositive(
												permit.getPermitArraysEntity().getFourthStringSeven())) {
											arrays.put("Number of Modules in Each Fourth String7", checkValueTypes.convert(
													permit.getPermitArraysEntity().getFourthStringSeven() + ""));

											if (checkValueTypes.isNumericPositive(
													permit.getPermitArraysEntity().getFourthStringEight())) {
												arrays.put("Number of Modules in Each Fourth String8", checkValueTypes.convert(
														permit.getPermitArraysEntity().getFourthStringEight() + ""));

												if (checkValueTypes.isNumericPositive(
														permit.getPermitArraysEntity().getFourthStringNine())) {
													arrays.put("Number of Modules in Each Fourth String9",
															checkValueTypes.convert(
																	permit.getPermitArraysEntity().getFourthStringNine()
																			+ ""));

													if (checkValueTypes.isNumericPositive(
															permit.getPermitArraysEntity().getFourthStringTen())) {
														arrays.put("Number of Modules in Each Fourth String10",
																checkValueTypes.convert(permit.getPermitArraysEntity()
																		.getFourthStringTen() + ""));

														if (checkValueTypes.isNumericPositive(permit
																.getPermitArraysEntity().getFourthStringEleven())) {
															arrays.put("Number of Modules in Each Fourth String11",
																	checkValueTypes
																			.convert(permit.getPermitArraysEntity()
																					.getFourthStringEleven() + ""));

															if (checkValueTypes.isNumericPositive(permit
																	.getPermitArraysEntity().getFourthStringTwelve())) {
																arrays.put("Number of Modules in Each Fourth String12",
																		checkValueTypes
																				.convert(permit.getPermitArraysEntity()
																						.getFourthStringTwelve() + ""));
															} else {
																arrays.put("Number of Modules in Each Fourth String12", "");
															}
														} else {
															arrays.put("Number of Modules in Each Fourth String11", "");
														}
													} else {
														arrays.put("Number of Modules in Each Fourth String10", "");
													}
												} else {
													arrays.put("Number of Modules in Each Fourth String9", "");
												}
											} else {
												arrays.put("Number of Modules in Each Fourth String8", "");
											}
										} else {
											arrays.put("Number of Modules in Each Fourth String7", "");
										}
									} else {
										arrays.put("Number of Modules in Each Fourth String6", "");
									}
								} else {
									arrays.put("Number of Modules in Each Fourth String5", "");
								}
							} else {
								arrays.put("Number of Modules in Each Fourth String4", "");
							}
						} else {
							arrays.put("Number of Modules in Each Fourth String3", "");
						}
					} else {
						arrays.put("Number of Modules in Each Fourth String2", "");
					}
				} else {
					arrays.put("Number of Modules in Each Fourth String1", "");
				}

			} else {
				arrays.put("Inverter #4 Model", "");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrays;
	}

	public LinkedHashMap<String, String> mapFifthInverter(GetPermitByIdResult permit) {
		LinkedHashMap<String, String> arrays = new LinkedHashMap<>();
		try {
			if (checkValueTypes.Equals(permit.getPermitArraysEntity().getFifthInverterModel(), 0)) {
				arrays.put("Inverter #5 Model", "Inverter Deleted From Favorite List");

			} else if (checkValueTypes.Equals(permit.getPermitArraysEntity().getFifthInverterModel(), -1)) {
				arrays.put("Inverter #5 Model", "Inverter Deleted");

			} else if (checkValueTypes.isLongPositive(permit.getPermitArraysEntity().getFifthInverterModel())) {

				Inverters inverterInfo = inverterRepo.findById(permit.getPermitArraysEntity().getFifthInverterModel())
						.orElse(new Inverters());
				arrays.put("Inverter #5 Model",
						checkValueTypes.convert(!Boolean.TRUE.equals(inverterInfo.isDeleted())
								? inverterInfo.getMake() + " " + inverterInfo.getModel()
								: "Inverter #5 Model"));

				if (checkValueTypes.isNumericPositive(permit.getPermitArraysEntity().getFifthStringOne())) {
					arrays.put("Number of Modules in Each Fifth String1",
							checkValueTypes.convert(permit.getPermitArraysEntity().getFifthStringOne() + ""));

					if (checkValueTypes.isNumericPositive(permit.getPermitArraysEntity().getFifthStringTwo())) {
						arrays.put("Number of Modules in Each Fifth String2",
								checkValueTypes.convert(permit.getPermitArraysEntity().getFifthStringTwo() + ""));

						if (checkValueTypes.isNumericPositive(permit.getPermitArraysEntity().getFifthStringThree())) {
							arrays.put("Number of Modules in Each Fifth String3",
									checkValueTypes.convert(permit.getPermitArraysEntity().getFifthStringThree() + ""));

							if (checkValueTypes
									.isNumericPositive(permit.getPermitArraysEntity().getFifthStringFour())) {
								arrays.put("Number of Modules in Each Fifth String4", checkValueTypes
										.convert(permit.getPermitArraysEntity().getFifthStringFour() + ""));

								if (checkValueTypes
										.isNumericPositive(permit.getPermitArraysEntity().getFifthStringOne())) {
									arrays.put("Number of Modules in Each Fifth String5", checkValueTypes
											.convert(permit.getPermitArraysEntity().getFifthStringOne() + ""));

									if (checkValueTypes
											.isNumericPositive(permit.getPermitArraysEntity().getFifthStringSix())) {
										arrays.put("Number of Modules in Each Fifth String6", checkValueTypes
												.convert(permit.getPermitArraysEntity().getFifthStringSix() + ""));

										if (checkValueTypes.isNumericPositive(
												permit.getPermitArraysEntity().getFifthStringSeven())) {
											arrays.put("Number of Modules in Each Fifth String7", checkValueTypes.convert(
													permit.getPermitArraysEntity().getFifthStringSeven() + ""));

											if (checkValueTypes.isNumericPositive(
													permit.getPermitArraysEntity().getFifthStringEight())) {
												arrays.put("Number of Modules in Each Fifth String8", checkValueTypes.convert(
														permit.getPermitArraysEntity().getFifthStringEight() + ""));

												if (checkValueTypes.isNumericPositive(
														permit.getPermitArraysEntity().getFifthStringNine())) {
													arrays.put("Number of Modules in Each Fifth String9",
															checkValueTypes.convert(
																	permit.getPermitArraysEntity().getFifthStringNine()
																			+ ""));

													if (checkValueTypes.isNumericPositive(
															permit.getPermitArraysEntity().getFifthStringTen())) {
														arrays.put("Number of Modules in Each Fifth String10",
																checkValueTypes.convert(permit.getPermitArraysEntity()
																		.getFifthStringTen() + ""));

														if (checkValueTypes.isNumericPositive(permit
																.getPermitArraysEntity().getFifthStringEleven())) {
															arrays.put("Number of Modules in Each Fifth String11",
																	checkValueTypes
																			.convert(permit.getPermitArraysEntity()
																					.getFifthStringEleven() + ""));

															if (checkValueTypes.isNumericPositive(permit
																	.getPermitArraysEntity().getFifthStringTwelve())) {
																arrays.put("Number of Modules in Each Fifth String12",
																		checkValueTypes
																				.convert(permit.getPermitArraysEntity()
																						.getFifthStringTwelve() + ""));
															} else {
																arrays.put("Number of Modules in Each Fifth String12", "");
															}
														} else {
															arrays.put("Number of Modules in Each Fifth String11", "");
														}
													} else {
														arrays.put("Number of Modules in Each Fifth String10", "");
													}
												} else {
													arrays.put("Number of Modules in Each Fifth String9", "");
												}
											} else {
												arrays.put("Number of Modules in Each Fifth String8", "");
											}
										} else {
											arrays.put("Number of Modules in Each Fifth String7", "");
										}
									} else {
										arrays.put("Number of Modules in Each Fifth String6", "");
									}
								} else {
									arrays.put("Number of Modules in Each Fifth String5", "");
								}
							} else {
								arrays.put("Number of Modules in Each Fifth String4", "");
							}
						} else {
							arrays.put("Number of Modules in Each Fifth String3", "");
						}
					} else {
						arrays.put("Number of Modules in Each Fifth String2", "");
					}
				} else {
					arrays.put("Number of Modules in Each Fifth String1", "");
				}

			} else {
				arrays.put("Inverter #5 Model", "");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrays;
	}

}
