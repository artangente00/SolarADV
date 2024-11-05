package com.PlayGroundAdv.Solar.service.equipment_utils;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ProjectNumberOfMicroInverter {
	
	final CheckValueTypesService checkValueTypes;
	
	public ProjectNumberOfMicroInverter(CheckValueTypesService checkValueTypes) {
		super();
		this.checkValueTypes = checkValueTypes;
	}

	// A.B CR-2627
	public Integer getNumberMicroInverter(PermitArrayEntityResultSecond permitArraysEntityResult,Inverters microInverterInfo) {
		try {
			
			double calcNumberMicroInv =0;
			if (microInverterInfo != null) {
				Integer numberModPerMicro = microInverterInfo.getModPerMicro();
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitOne(), "")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitOne()) / numberModPerMicro);
				    }else {
				        calcNumberMicroInv= Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitOne());
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTwo(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwo()) / numberModPerMicro); 
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwo()); 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitThree(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitThree()) / numberModPerMicro); 
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitThree());
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitFour(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitFour()) / numberModPerMicro); 
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFour()); 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitFive(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitFive()) / numberModPerMicro);
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFive());
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitSix(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitSix()) / numberModPerMicro);
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSix());
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitSeven(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitSeven()) / numberModPerMicro);
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSeven()); 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitEight(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitEight()) / numberModPerMicro);
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitEight());
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitNine(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitNine()) / numberModPerMicro);
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitNine());
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTen()) / numberModPerMicro);
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTen());
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitEleven(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitEleven()) / numberModPerMicro); 
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitEleven());
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTweleve(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTweleve()) / numberModPerMicro); 
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTweleve());
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitThirteen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitThirteen()) / numberModPerMicro);
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitThirteen());
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitFourteen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitFourteen()) / numberModPerMicro);
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFourteen());
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitFifteen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitFifteen()) / numberModPerMicro);
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFifteen());
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitSixteen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitSixteen()) / numberModPerMicro);
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSixteen());
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitSeventeen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				      calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitSeventeen()) / numberModPerMicro);  
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSeventeen()); 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitEightteen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitEightteen()) / numberModPerMicro); 
				    }else {
				      calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitEightteen());  
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitNineteen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitNineteen()) / numberModPerMicro);
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitNineteen()); 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTwenty(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwenty()) / numberModPerMicro); 
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwenty()); 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTwentyOne(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwentyOne()) / numberModPerMicro); 
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwentyOne()); 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTwentyTwo(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwentyTwo()) / numberModPerMicro); 
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwentyTwo()); 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTwentyThree(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwentyThree()) / numberModPerMicro); 
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwentyThree()); 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTwentyFour(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv + Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwentyFour()) / numberModPerMicro);
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv + Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwentyFour()); 
				    }
				}
			
			}
			
			return (int) calcNumberMicroInv;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	// A.B CR-2627
	public Integer getMAXNumberMicroInverter(PermitArrayEntityResultSecond permitArraysEntityResult,Inverters microInverterInfo) {
		try {
			double calcNumberMicroInv =0;
			if (microInverterInfo != null) {
				Integer numberModPerMicro = microInverterInfo.getModPerMicro();
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitOne(), "")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitOne()) / numberModPerMicro);
				    }else {
				        calcNumberMicroInv= Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitOne());
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTwo(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwo()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwo()) / numberModPerMicro) : calcNumberMicroInv; 
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwo()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwo()) : calcNumberMicroInv; 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitThree(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitThree()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitThree()) / numberModPerMicro) : calcNumberMicroInv; 
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitThree()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitThree()) : calcNumberMicroInv;
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitFour(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitFour()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitFour()) / numberModPerMicro) : calcNumberMicroInv; 
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFour()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFour()) : calcNumberMicroInv; 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitFive(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitFive()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitFive()) / numberModPerMicro) : calcNumberMicroInv;
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFive()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFive()) : calcNumberMicroInv;
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitSix(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitSix()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitSix()) / numberModPerMicro) : calcNumberMicroInv;
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSix()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSix()) : calcNumberMicroInv;
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitSeven(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitSeven()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitSeven()) / numberModPerMicro) : calcNumberMicroInv;
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSeven()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSeven()) : calcNumberMicroInv; 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitEight(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitEight()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitEight()) / numberModPerMicro) : calcNumberMicroInv;
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitEight()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitEight()) : calcNumberMicroInv;
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitNine(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitNine()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitNine()) / numberModPerMicro) : calcNumberMicroInv;
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitNine()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitNine()) : calcNumberMicroInv;
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTen()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTen()) / numberModPerMicro) : calcNumberMicroInv;
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTen()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTen()) : calcNumberMicroInv;
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitEleven(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitEleven()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitEleven()) / numberModPerMicro) : calcNumberMicroInv; 
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitEleven()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitEleven()) : calcNumberMicroInv;
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTweleve(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTweleve()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTweleve()) / numberModPerMicro) : calcNumberMicroInv; 
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTweleve()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTweleve()) : calcNumberMicroInv;
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitThirteen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitThirteen()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitThirteen()) / numberModPerMicro) : calcNumberMicroInv;
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitThirteen()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitThirteen()) : calcNumberMicroInv;
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitFourteen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitFourteen()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitFourteen()) / numberModPerMicro) : calcNumberMicroInv;
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFourteen()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFourteen()) : calcNumberMicroInv;
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitFifteen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitFifteen()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitFifteen()) / numberModPerMicro) : calcNumberMicroInv;
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFifteen()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFifteen()) : calcNumberMicroInv;
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitSixteen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitSixteen()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitSixteen()) / numberModPerMicro) : calcNumberMicroInv;
				    }else {
				        calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSixteen()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSixteen()) : calcNumberMicroInv;
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitSeventeen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				      calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitSeventeen()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitSeventeen()) / numberModPerMicro) : calcNumberMicroInv;  
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSeventeen()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSeventeen()) : calcNumberMicroInv; 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitEightteen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitEightteen()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitEightteen()) / numberModPerMicro) : calcNumberMicroInv; 
				    }else {
				      calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitEightteen()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitEightteen()) : calcNumberMicroInv;  
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitNineteen(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitNineteen()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitNineteen()) / numberModPerMicro) : calcNumberMicroInv;
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitNineteen()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitNineteen()) : calcNumberMicroInv; 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTwenty(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwenty()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwenty()) / numberModPerMicro) : calcNumberMicroInv; 
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwenty()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwenty()) : calcNumberMicroInv; 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTwentyOne(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwentyOne()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwentyOne()) / numberModPerMicro) : calcNumberMicroInv; 
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwentyOne()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwentyOne()) : calcNumberMicroInv; 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTwentyTwo(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwentyTwo()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwentyTwo()) / numberModPerMicro) : calcNumberMicroInv; 
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwentyTwo()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwentyTwo()) : calcNumberMicroInv; 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTwentyThree(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				       calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwentyThree()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwentyThree()) / numberModPerMicro) : calcNumberMicroInv; 
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwentyThree()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwentyThree()) : calcNumberMicroInv; 
				    }
				}
				if(checkValueTypes.NotEquals(permitArraysEntityResult.getNumberModulesACCircuitTwentyFour(),"")) {
				    if(numberModPerMicro != null && numberModPerMicro !=0) {
				        calcNumberMicroInv= calcNumberMicroInv < Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwentyFour()) / numberModPerMicro) ? Math.ceil(Double.parseDouble(permitArraysEntityResult.getNumberModulesACCircuitTwentyFour()) / numberModPerMicro) : calcNumberMicroInv;
				    }else {
				       calcNumberMicroInv= calcNumberMicroInv < Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwentyFour()) ? Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwentyFour()) : calcNumberMicroInv; 
				    }
				}
			}
			
			return (int) calcNumberMicroInv;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	

}
