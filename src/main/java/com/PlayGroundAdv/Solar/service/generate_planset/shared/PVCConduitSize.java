package com.PlayGroundAdv.Solar.service.generate_planset.shared;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class PVCConduitSize {

	final CheckValueTypesService checkValue;

	public PVCConduitSize(CheckValueTypesService checkValue) {
		super();
		this.checkValue = checkValue;
	}
	public String getStringConduitSizePVC(String tradeSize, int conductorQty) {
		String conduitSize = "";
		try {
			if (checkValue.contains(tradeSize, "AWG")) {
				return getConduitSizePVC(tradeSize, conductorQty);
			}else {
				if (checkValue.Equals(tradeSize, "300 kc")) {
					conduitSize = lowerkcConduitSize(conductorQty, Arrays.asList(0,1,3,5,7,10,13));

				} else if (checkValue.Equals(tradeSize, "350 kc")) {
					conduitSize = lowerkcConduitSize(conductorQty, Arrays.asList(0,1,2,4,6,9,11));

				} else if (checkValue.Equals(tradeSize, "400 kc")) {
					conduitSize = lowerkcConduitSize(conductorQty, Arrays.asList(0,1,3,6,9,11,15));

				} else if (checkValue.Equals(tradeSize, "500 kc")) {
					conduitSize = lowerkcConduitSize(conductorQty, Arrays.asList(0,1,4,6,8,10));

				} else if (checkValue.Equals(tradeSize, "600 kc")) {
					conduitSize = lowerkcConduitSize(conductorQty, Arrays.asList(0,1,3,5,6,8));

				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conduitSize;
	}
	public String getConduitSizePVC(String tradeSize, int conductorQty) {
		String conduitSize = "";
		try {
			if (checkValue.notContains(tradeSize, "/")) {
				if (checkValue.Equals(tradeSize, "EXISTING")) {
					conduitSize = "EXISTING";
				} else if (checkValue.Equals(tradeSize, "#14 AWG")) {
					conduitSize = awgConduitSize(conductorQty,
							Arrays.asList(11, 21, 34, 60, 82, 135, 193, 299, 401, 517, 815, 1178));

				} else if (checkValue.Equals(tradeSize, "#12 AWG")) {
					conduitSize = awgConduitSize(conductorQty,
							Arrays.asList(8, 15, 25, 43, 59, 99, 141, 218, 293, 377, 495, 859));

				} else if (checkValue.Equals(tradeSize, "#10 AWG")) {
					conduitSize = awgConduitSize(conductorQty,
							Arrays.asList(5, 9, 15, 27, 37, 62, 89, 137, 184, 238, 374, 541));

				} else if (checkValue.Equals(tradeSize, "#8 AWG")) {
					conduitSize = awgConduitSize(conductorQty,
							Arrays.asList(5, 9, 16, 21, 36, 51, 79, 106, 137, 216, 312));

				} else if (checkValue.Equals(tradeSize, "#6 AWG")) {
					conduitSize = awgConduitSize(conductorQty,
							Arrays.asList(1, 4, 6, 11, 15, 26, 37, 57, 77, 99, 156, 225));

				} else if (checkValue.Equals(tradeSize, "#4 AWG")) {
					conduitSize = awgConduitSize(conductorQty,
							Arrays.asList(1, 2, 4, 7, 9, 16, 22, 35, 47, 61, 96, 138));

				} else if (checkValue.Equals(tradeSize, "#3 AWG")) {
					conduitSize = awgConduitSize(conductorQty,
							Arrays.asList(1, 1, 3, 6, 8, 13, 19, 30, 40, 51, 81, 117));

				} else if (checkValue.Equals(tradeSize, "#2 AWG")) {
					conduitSize = awgConduitSize(conductorQty,
							Arrays.asList(1, 1, 3, 5, 7, 11, 16, 25, 33, 43, 68, 98));

				} else if (checkValue.Equals(tradeSize, "#1 AWG")) {
					conduitSize = awgConduitSize(conductorQty, Arrays.asList(1, 1, 1, 3, 5, 8, 12, 18, 25, 32, 50, 73));

				} else if (checkValue.Equals(tradeSize, "250 kc")) {
					conduitSize = lowerkcConduitSize(conductorQty, Arrays.asList(0, 1, 3, 4, 7, 10, 12, 20, 28));

				} else if (checkValue.Equals(tradeSize, "300 kc")) {
					conduitSize = lowerkcConduitSize(conductorQty, Arrays.asList(0, 1, 3, 4, 6, 8, 11, 17, 24));

				} else if (checkValue.Equals(tradeSize, "350 kc")) {
					conduitSize = lowerkcConduitSize(conductorQty, Arrays.asList(0, 1, 2, 3, 5, 7, 9, 15, 21));

				} else if (checkValue.Equals(tradeSize, "400 kc")) {
					conduitSize = kcConduitSize(conductorQty, Arrays.asList(0, 1, 3, 5, 6, 8, 13, 19));

				} else if (checkValue.Equals(tradeSize, "500 kc")) {
					conduitSize = kcConduitSize(conductorQty, Arrays.asList(0, 1, 2, 4, 5, 7, 11, 16));

				} else if (checkValue.Equals(tradeSize, "600 kc")) {
					conduitSize = kcConduitSize(conductorQty, Arrays.asList(0, 1, 3, 4, 5, 9, 13));

				}
			} else if (checkValue.Equals(tradeSize, "#1/0 AWG")) {
				conduitSize = awgConduitSize(conductorQty, Arrays.asList(1, 1, 1, 3, 4, 7, 10, 15, 21, 27, 42, 61));

			} else if (checkValue.Equals(tradeSize, "#2/0 AWG")) {
				conduitSize = awgConduitSize(conductorQty, Arrays.asList(0, 1, 1, 2, 3, 6, 8, 13, 17, 22, 35, 51));

			} else if (checkValue.Equals(tradeSize, "#3/0 AWG")) {
				conduitSize = awgConduitSize(conductorQty, Arrays.asList(0, 1, 1, 1, 3, 5, 7, 11, 14, 18, 29, 42));

			} else if (checkValue.Equals(tradeSize, "#4/0 AWG")) {
				conduitSize = awgConduitSize(conductorQty, Arrays.asList(0, 1, 1, 1, 2, 4, 6, 9, 12, 15, 24, 35));
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
		} else if (conductorQty < qtyList.get(10)) {
			return "5 inch";
		} else if (conductorQty < qtyList.get(11)) {
			return "6 inch";
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
		} else if (conductorQty < qtyList.get(7)) {
			return "5 inch";
		} else if (conductorQty < qtyList.get(8)) {
			return "6 inch";
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
		} else if (conductorQty < qtyList.get(6)) {
			return "5 inch";
		} else if (conductorQty < qtyList.get(7)) {
			return "6 inch";
		}
		return "";
	}
}
