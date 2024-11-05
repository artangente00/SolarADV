package com.PlayGroundAdv.Solar.service.generate_planset.shared;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class EMTConduitSize {

	final CheckValueTypesService checkValue;

	public EMTConduitSize(CheckValueTypesService checkValue) {
		super();
		this.checkValue = checkValue;
	}

	public String getConduitSizeEMT(String tradeSize, int conductorQty) {

		String conduitSize = "";
		try {
			if (checkValue.notContains(tradeSize, "/")) {
				if (checkValue.Equals(tradeSize, "EXISTING")) {
					conduitSize = "EXISTING";
				} else if (checkValue.Equals(tradeSize, "#14 AWG")) {
					conduitSize = awgConduitSize(conductorQty, Arrays.asList(12,22,35,61,84,138,241,364,476,608));
					
				} else if (checkValue.Equals(tradeSize, "#12 AWG")) {
					conduitSize = awgConduitSize(conductorQty, Arrays.asList(9,16,26,45,61,101,176,266,347,433));
					
				} else if (checkValue.Equals(tradeSize, "#10 AWG")) {
					conduitSize = awgConduitSize(conductorQty, Arrays.asList(5,10,16,28,38,63,111,167,219,279));
					
				} else if (checkValue.Equals(tradeSize, "#8 AWG")) {
					conduitSize = awgConduitSize(conductorQty, Arrays.asList(3,6,9,16,22,36,64,96,126,161));
					
				} else if (checkValue.Equals(tradeSize, "#6 AWG")) {
					conduitSize = awgConduitSize(conductorQty, Arrays.asList(2,4,7,12,16,26,46,69,91,116));
					
				} else if (checkValue.Equals(tradeSize, "#4 AWG")) {
					conduitSize = awgConduitSize(conductorQty, Arrays.asList(1,2,4,7,10,16,28,43,56,71));
					
				} else if (checkValue.Equals(tradeSize, "#3 AWG")) {
					conduitSize = awgConduitSize(conductorQty, Arrays.asList(1,1,3,6,8,13,24,36,47,60));
					
				} else if (checkValue.Equals(tradeSize, "#2 AWG")) {
					conduitSize = awgConduitSize(conductorQty, Arrays.asList(1,1,3,5,7,11,20,30,40,51));
					
				} else if (checkValue.Equals(tradeSize, "#1 AWG")) {
					conduitSize = awgConduitSize(conductorQty, Arrays.asList(1,1,1,4,5,8,15,22,29,37));
					
				} else if (checkValue.Equals(tradeSize, "250 kc")) {
					conduitSize = lowerkcConduitSize(conductorQty, Arrays.asList(0,1,3,6,9,11,15));

				} else if (checkValue.Equals(tradeSize, "300 kc")) {
					conduitSize = lowerkcConduitSize(conductorQty, Arrays.asList(0,1,3,5,7,10,13));

				} else if (checkValue.Equals(tradeSize, "350 kc")) {
					conduitSize = lowerkcConduitSize(conductorQty, Arrays.asList(0,1,2,4,6,9,11));
					
				} else if (checkValue.Equals(tradeSize, "400 kc")) {
					conduitSize = kcConduitSize(conductorQty, Arrays.asList(0,1,4,6,8,10));
					
				} else if (checkValue.Equals(tradeSize, "500 kc")) {
					conduitSize = kcConduitSize(conductorQty, Arrays.asList(0,1,3,5,6,8));
					
				} else if (checkValue.Equals(tradeSize, "600 kc")) {
					conduitSize = kcConduitSize(conductorQty, Arrays.asList(0,1,2,4,5,7));
					
				} else if (checkValue.Equals(tradeSize, "700 kc")) {
					conduitSize = kcConduitSize(conductorQty, Arrays.asList(0,1,2,3,4,6));
					
				} else if (checkValue.Equals(tradeSize, "750 kc") || checkValue.Equals(tradeSize, "800 kc")) {
					conduitSize = kcConduitSize(conductorQty, Arrays.asList(0,1,1,3,4,5));
					
				} else if (checkValue.Equals(tradeSize, "900 kc")) {
					conduitSize = kcConduitSize(conductorQty, Arrays.asList(0,1,1,3,3,4));
					
				} else if (checkValue.Equals(tradeSize, "1000 kc")) {
					conduitSize = kcConduitSize(conductorQty, Arrays.asList(0,1,1,2,4,4));
					
				}
			} else if (checkValue.Equals(tradeSize, "#1/0 AWG")) {
				conduitSize = awgConduitSize(conductorQty, Arrays.asList(1,1,1,3,4,7,12,19,25,32));
				
			} else if (checkValue.Equals(tradeSize, "#2/0 AWG")) {
				conduitSize = awgConduitSize(conductorQty, Arrays.asList(0,1,1,2,3,6,10,16,20,26));
				
			} else if (checkValue.Equals(tradeSize, "#3/0 AWG")) {
				conduitSize = awgConduitSize(conductorQty, Arrays.asList(0,1,1,1,3,5,8,13,17,22));
				
			} else if (checkValue.Equals(tradeSize, "#4/0 AWG")) {
				conduitSize = awgConduitSize(conductorQty, Arrays.asList(0,1,1,1,2,4,7,11,14,18));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conduitSize;

	}

	private String awgConduitSize(Integer conductorQty, List<Integer> qtyList) {
		
		if (conductorQty < qtyList.get(0)) {
			return "1/2 inch";
		} else if (conductorQty < qtyList.get(1)) {
			return "3/4 inch";
		} else if (conductorQty < qtyList.get(2)) {
			return "1 inch";
		} else if (conductorQty < qtyList.get(3)) {
			return "1-1/4 inch";
		} else if (conductorQty < qtyList.get(4)) {
			return "1-1/2 inch";
		} else if (conductorQty < qtyList.get(5)) {
			return "2 inch";
		} else if (conductorQty < qtyList.get(6)) {
			return "2-1/2 inch";
		} else if (conductorQty < qtyList.get(7)) {
			return "3 inch";
		} else if (conductorQty < qtyList.get(8)) {
			return "3-1/2 inch";
		} else if (conductorQty < qtyList.get(9)) {
			return "4 inch";
		}
		return "";
	}
	
	private String lowerkcConduitSize(Integer conductorQty, List<Integer> qtyList) {
		if (conductorQty < qtyList.get(0)) {
			return "1/2 inch";
		} else if (conductorQty < qtyList.get(1)) {
			return "1 inch";
		} else if (conductorQty < qtyList.get(2)) {
			return "2 inch";
		} else if (conductorQty < qtyList.get(3)) {
			return "2-1/2 inch";
		} else if (conductorQty < qtyList.get(4)) {
			return "3 inch";
		} else if (conductorQty < qtyList.get(5)) {
			return "3-1/2 inch";
		} else if (conductorQty < qtyList.get(6)) {
			return "4 inch";
		}
		return "";
	}

	private String kcConduitSize(Integer conductorQty, List<Integer> qtyList) {
		
		if (conductorQty < qtyList.get(0)) {
			return "1/2 inch";
		} else if (conductorQty < qtyList.get(1)) {
			return "1-1/4 inch";
		} else if (conductorQty < qtyList.get(2)) {
			return "2-1/2 inch";
		} else if (conductorQty < qtyList.get(3)) {
			return "3 inch";
		} else if (conductorQty < qtyList.get(4)) {
			return "3-1/2 inch";
		} else if (conductorQty < qtyList.get(5)) {
			return "4 inch";
		}
		return "";
	}

}
