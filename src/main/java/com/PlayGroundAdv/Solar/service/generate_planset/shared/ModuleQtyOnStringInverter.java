package com.PlayGroundAdv.Solar.service.generate_planset.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class ModuleQtyOnStringInverter {

	final CheckValueTypesService checkValue;

	public ModuleQtyOnStringInverter(CheckValueTypesService checkValue) {
		super();
		this.checkValue = checkValue;
	}

	public PlansetUtils getModuleQty(PermitArrayEntityResultSecond permitArraysEntityResult) {

		try {
			PlansetUtils plansetUtils = new PlansetUtils();
			List<Integer> stringOneQty = new ArrayList<>();
			List<Integer> stringTwoQty = new ArrayList<>();
			List<Integer> stringThreeQty = new ArrayList<>();
			List<Integer> stringFourQty = new ArrayList<>();
			List<Integer> stringFiveQty = new ArrayList<>();
			
			if (checkValue.isLongPositive(permitArraysEntityResult.getInverterModel())) {
				if (checkValue.isNumericNotZero(permitArraysEntityResult.getStringOne())) {
					stringOneQty.add(Integer.parseInt(permitArraysEntityResult.getStringOne()));
					if (checkValue.isNumericNotZero(permitArraysEntityResult.getStringTwo())) {
						stringOneQty.add(Integer.parseInt(permitArraysEntityResult.getStringTwo()));
						if (checkValue.isNumericNotZero(permitArraysEntityResult.getStringThree())) {
							stringOneQty.add(Integer.parseInt(permitArraysEntityResult.getStringThree()));
							if (checkValue.isNumericNotZero(permitArraysEntityResult.getStringFour())) {
								stringOneQty.add(Integer.parseInt(permitArraysEntityResult.getStringFour()));
								if (checkValue.isNumericNotZero(permitArraysEntityResult.getStringFive())) {
									stringOneQty.add(Integer.parseInt(permitArraysEntityResult.getStringFive()));
									if (checkValue.isNumericPositive(permitArraysEntityResult.getStringSix())) {
										stringOneQty.add(permitArraysEntityResult.getStringSix());
										if (checkValue.isNumericPositive(permitArraysEntityResult.getStringSeven())) {
											stringOneQty.add(permitArraysEntityResult.getStringSeven());
											if (checkValue.isNumericPositive(permitArraysEntityResult.getStringEight())) {
												stringOneQty.add(permitArraysEntityResult.getStringEight());
												if (checkValue.isNumericPositive(permitArraysEntityResult.getStringNine())) {
													stringOneQty.add(permitArraysEntityResult.getStringNine());
													if (checkValue.isNumericPositive(permitArraysEntityResult.getStringTen())) {
														stringOneQty.add(permitArraysEntityResult.getStringTen());
														if (checkValue.isNumericPositive(permitArraysEntityResult.getStringEleven())) {
															stringOneQty.add(permitArraysEntityResult.getStringEleven());
															if (checkValue.isNumericPositive(permitArraysEntityResult.getStringTwelve())) {
																stringOneQty.add(permitArraysEntityResult.getStringTwelve());
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
				for (int i = 0; i < stringOneQty.size(); i++) {
					plansetUtils.getStringStringE003().add("INV 1");
				}
				if (checkValue.isLongPositive(permitArraysEntityResult.getSecondInverterModel())) {
					
					if (checkValue.isNumericNotZero(permitArraysEntityResult.getSecondStringOne())) {
						stringTwoQty.add(Integer.parseInt(permitArraysEntityResult.getSecondStringOne()));
						if (checkValue.isNumericNotZero(permitArraysEntityResult.getSecondStringTwo())) {
							stringTwoQty.add(Integer.parseInt(permitArraysEntityResult.getSecondStringTwo()));
							if (checkValue.isNumericNotZero(permitArraysEntityResult.getSecondStringThree())) {
								stringTwoQty.add(Integer.parseInt(permitArraysEntityResult.getSecondStringThree()));
								if (checkValue.isNumericNotZero(permitArraysEntityResult.getSecondStringFour())) {
									stringTwoQty.add(Integer.parseInt(permitArraysEntityResult.getSecondStringFour()));
									if (checkValue.isNumericNotZero(permitArraysEntityResult.getSecondStringFive())) {
										stringTwoQty.add(Integer.parseInt(permitArraysEntityResult.getSecondStringFive()));
										if (checkValue.isNumericPositive(permitArraysEntityResult.getSecondStringSix())) {
											stringTwoQty.add(permitArraysEntityResult.getSecondStringSix());
											if (checkValue.isNumericPositive(permitArraysEntityResult.getSecondStringSeven())) {
												stringTwoQty.add(permitArraysEntityResult.getSecondStringSeven());
												if (checkValue.isNumericPositive(permitArraysEntityResult.getSecondStringEight())) {
													stringTwoQty.add(permitArraysEntityResult.getSecondStringEight());
													if (checkValue.isNumericPositive(permitArraysEntityResult.getSecondStringNine())) {
														stringTwoQty.add(permitArraysEntityResult.getSecondStringNine());
														if (checkValue.isNumericPositive(permitArraysEntityResult.getSecondStringTen())) {
															stringTwoQty.add(permitArraysEntityResult.getSecondStringTen());
															if (checkValue.isNumericPositive(permitArraysEntityResult.getSecondStringEleven())) {
																stringTwoQty.add(permitArraysEntityResult.getSecondStringEleven());
																if (checkValue.isNumericPositive(permitArraysEntityResult.getSecondStringTwelve())) {
																	stringTwoQty.add(permitArraysEntityResult.getSecondStringTwelve());
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
					for (int i = 0; i < stringTwoQty.size(); i++) {
						plansetUtils.getStringStringE003().add("INV 2");
					}
					if (checkValue.isLongPositive(permitArraysEntityResult.getThirdInverterModel())) {
					
						if (checkValue.isNumericPositive(permitArraysEntityResult.getThirdStringOne())) {
							stringThreeQty.add(permitArraysEntityResult.getThirdStringOne());
							if (checkValue.isNumericPositive(permitArraysEntityResult.getThirdStringTwo())) {
								stringThreeQty.add(permitArraysEntityResult.getThirdStringTwo());
								if (checkValue.isNumericPositive(permitArraysEntityResult.getThirdStringThree())) {
									stringThreeQty.add(permitArraysEntityResult.getThirdStringThree());
									if (checkValue.isNumericPositive(permitArraysEntityResult.getThirdStringFour())) {
										stringThreeQty.add(permitArraysEntityResult.getThirdStringFour());
										if (checkValue.isNumericPositive(permitArraysEntityResult.getThirdStringFive())) {
											stringThreeQty.add(permitArraysEntityResult.getThirdStringFive());
											if (checkValue.isNumericPositive(permitArraysEntityResult.getThirdStringSix())) {
												stringThreeQty.add(permitArraysEntityResult.getThirdStringSix());
												if (checkValue.isNumericPositive(permitArraysEntityResult.getThirdStringSeven())) {
													stringThreeQty.add(permitArraysEntityResult.getThirdStringSeven());
													if (checkValue.isNumericPositive(permitArraysEntityResult.getThirdStringEight())) {
														stringThreeQty.add(permitArraysEntityResult.getThirdStringEight());
														if (checkValue.isNumericPositive(permitArraysEntityResult.getThirdStringNine())) {
															stringThreeQty.add(permitArraysEntityResult.getThirdStringNine());
															if (checkValue.isNumericPositive(permitArraysEntityResult.getThirdStringTen())) {
																stringThreeQty.add(permitArraysEntityResult.getThirdStringTen());
																if (checkValue.isNumericPositive(permitArraysEntityResult.getThirdStringEleven())) {
																	stringThreeQty.add(permitArraysEntityResult.getThirdStringEleven());
																	if (checkValue.isNumericPositive(permitArraysEntityResult.getThirdStringTwelve())) {
																		stringThreeQty.add(permitArraysEntityResult.getThirdStringTwelve());
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
						for (int i = 0; i < stringThreeQty.size(); i++) {
							plansetUtils.getStringStringE003().add("INV 3");
						}
						if (checkValue.isLongPositive(permitArraysEntityResult.getFourthInverterModel())) {
						
							if (checkValue.isNumericPositive(permitArraysEntityResult.getFourthStringOne())) {
								stringFourQty.add(permitArraysEntityResult.getFourthStringOne());
								if (checkValue.isNumericPositive(permitArraysEntityResult.getFourthStringTwo())) {
									stringFourQty.add(permitArraysEntityResult.getFourthStringTwo());
									if (checkValue.isNumericPositive(permitArraysEntityResult.getFourthStringThree())) {
										stringFourQty.add(permitArraysEntityResult.getFourthStringThree());
										if (checkValue.isNumericPositive(permitArraysEntityResult.getFourthStringFour())) {
											stringFourQty.add(permitArraysEntityResult.getFourthStringFour());
											if (checkValue.isNumericPositive(permitArraysEntityResult.getFourthStringFive())) {
												stringFourQty.add(permitArraysEntityResult.getFourthStringFive());
												if (checkValue.isNumericPositive(permitArraysEntityResult.getFourthStringSix())) {
													stringFourQty.add(permitArraysEntityResult.getFourthStringSix());
													if (checkValue.isNumericPositive(permitArraysEntityResult.getFourthStringSeven())) {
														stringFourQty.add(permitArraysEntityResult.getFourthStringSeven());
														if (checkValue.isNumericPositive(permitArraysEntityResult.getFourthStringEight())) {
															stringFourQty.add(permitArraysEntityResult.getFourthStringEight());
															if (checkValue.isNumericPositive(permitArraysEntityResult.getFourthStringNine())) {
																stringFourQty.add(permitArraysEntityResult.getFourthStringNine());
																if (checkValue.isNumericPositive(permitArraysEntityResult.getFourthStringTen())) {
																	stringFourQty.add(permitArraysEntityResult.getFourthStringTen());
																	if (checkValue.isNumericPositive(permitArraysEntityResult.getFourthStringEleven())) {
																		stringFourQty.add(permitArraysEntityResult.getFourthStringEleven());
																		if (checkValue.isNumericPositive(permitArraysEntityResult.getFourthStringTwelve())) {
																			stringFourQty.add(permitArraysEntityResult.getFourthStringTwelve());
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
							for (int i = 0; i < stringFourQty.size(); i++) {
								plansetUtils.getStringStringE003().add("INV 4");
							}
							if (checkValue.isLongPositive(permitArraysEntityResult.getFifthInverterModel())) {
								if (checkValue.isNumericPositive(permitArraysEntityResult.getFifthStringOne())) {
									stringFiveQty.add(permitArraysEntityResult.getFifthStringOne());
									if (checkValue.isNumericPositive(permitArraysEntityResult.getFifthStringTwo())) {
										stringFiveQty.add(permitArraysEntityResult.getFifthStringTwo());
										if (checkValue.isNumericPositive(permitArraysEntityResult.getFifthStringThree())) {
											stringFiveQty.add(permitArraysEntityResult.getFifthStringThree());
											if (checkValue.isNumericPositive(permitArraysEntityResult.getFifthStringFour())) {
												stringFiveQty.add(permitArraysEntityResult.getFifthStringFour());
												if (checkValue.isNumericPositive(permitArraysEntityResult.getFifthStringFive())) {
													stringFiveQty.add(permitArraysEntityResult.getFifthStringFive());
													if (checkValue.isNumericPositive(permitArraysEntityResult.getFifthStringSix())) {
														stringFiveQty.add(permitArraysEntityResult.getFifthStringSix());
														if (checkValue.isNumericPositive(permitArraysEntityResult.getFifthStringSeven())) {
															stringFiveQty.add(permitArraysEntityResult.getFifthStringSeven());
															if (checkValue.isNumericPositive(permitArraysEntityResult.getFifthStringEight())) {
																stringFiveQty.add(permitArraysEntityResult.getFifthStringEight());
																if (checkValue.isNumericPositive(permitArraysEntityResult.getFifthStringNine())) {
																	stringFiveQty.add(permitArraysEntityResult.getFifthStringNine());
																	if (checkValue.isNumericPositive(permitArraysEntityResult.getFifthStringTen())) {
																		stringFiveQty.add(permitArraysEntityResult.getFifthStringTen());
																		if (checkValue.isNumericPositive(permitArraysEntityResult.getFifthStringEleven())) {
																			stringFiveQty.add(permitArraysEntityResult.getFifthStringEleven());
																			if (checkValue.isNumericPositive(permitArraysEntityResult.getFifthStringTwelve())) {
																				stringFiveQty.add(permitArraysEntityResult.getFifthStringTwelve());
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
								for (int i = 0; i < stringFiveQty.size(); i++) {
									plansetUtils.getStringStringE003().add("INV 5");
								}
							}
						}
					}
				}
			}
			
			plansetUtils.setStringQty(new ArrayList<>(stringOneQty));
			plansetUtils.getStringQty().addAll(stringTwoQty);
			plansetUtils.getStringQty().addAll(stringThreeQty);
			plansetUtils.getStringQty().addAll(stringFourQty);
			plansetUtils.getStringQty().addAll(stringFiveQty);
			
			plansetUtils.setNumberOfStringsOne(stringOneQty.size());
			plansetUtils.setNumberOfStringsTwo(stringTwoQty.size());
			plansetUtils.setNumberOfStringsThree(stringThreeQty.size());
			plansetUtils.setNumberOfStringsFour(stringFourQty.size());
			plansetUtils.setNumberOfStringsFive(stringFiveQty.size());
			plansetUtils.setTotalNumberOfStrings(stringOneQty.size() + stringTwoQty.size() + stringThreeQty.size() + stringFourQty.size() + stringFiveQty.size());
			plansetUtils.setMaxNumberOfStrings(Collections.max(Arrays.asList(stringOneQty.size(), stringTwoQty.size(), stringThreeQty.size(), stringFourQty.size(), stringFiveQty.size())));
			
			if (!plansetUtils.getStringQty().isEmpty()) {
				plansetUtils.setMaxNumModule(Collections.max(new ArrayList<>(plansetUtils.getStringQty())));
				plansetUtils.setMinNumModule(Collections.min(new ArrayList<>(plansetUtils.getStringQty())));
				plansetUtils.setModuleQty(plansetUtils.getStringQty().stream().mapToInt(a -> a).sum());
			}			
			
			plansetUtils.setDcNumbUngroundCond(2 * plansetUtils.getStringQty().size());
			plansetUtils.setNumModule1(stringOneQty.stream().mapToInt(a -> a).sum());
			plansetUtils.setNumModule2(stringTwoQty.stream().mapToInt(a -> a).sum());
			plansetUtils.setNumModule3(stringThreeQty.stream().mapToInt(a -> a).sum());
			plansetUtils.setNumModule4(stringFourQty.stream().mapToInt(a -> a).sum());
			plansetUtils.setNumModule5(stringFiveQty.stream().mapToInt(a -> a).sum());
			plansetUtils.setInverterQty((Arrays.asList(stringOneQty.size(), stringTwoQty.size(), stringThreeQty.size(), stringFourQty.size(), stringFiveQty.size())
					.stream().filter(a -> a > 0 ).collect(Collectors.toList())).size());
			
			return plansetUtils;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new PlansetUtils();
		}

	}
	
}
