package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared;

import org.springframework.stereotype.Service;

@Service
public class NEC310Values {



	public String getNextTradeSize(String tradeSize) {
		if (tradeSize.equals("#12 AWG")) {
			return "#10 AWG";
		} else if (tradeSize.equals("#10 AWG")) {
			return "#8 AWG";
		} else if (tradeSize.equals("#8 AWG")) {
			return "#6 AWG";
		} else if (tradeSize.equals("#6 AWG")) {
			return "#4 AWG";
		} else if (tradeSize.equals("#4 AWG")) {
			return "#3 AWG";
		} else if (tradeSize.equals("#3 AWG")) {
			return "#2 AWG";
		} else if (tradeSize.equals("#2 AWG")) {
			return "#1/0 AWG";
		} else if (tradeSize.equals("#1 AWG")) {
			return "#1/0 AWG";
		} else if (tradeSize.equals("#1/0 AWG")) {
			return "#2/0 AWG";
		} else if (tradeSize.equals("#2/0 AWG")) {
			return "#4/0 AWG";
		} else if (tradeSize.equals("#3/0 AWG")) {
			return "#4/0 AWG";
		} else if (tradeSize.equals("#4/0 AWG")) {
			return "250 kc";
		} else if (tradeSize.equals("250 kc")) {
			return "300 kc";
		} else if (tradeSize.equals("300 kc")) {
			return "400 kc";
		} else if (tradeSize.equals("400 kc")) {
			return "500 kc";
		} else if (tradeSize.equals("500 kc")) {
			return "600 kc";
		} else if (tradeSize.equals("600 kc")) {
			return "700 kc";
		} else if (tradeSize.equals("700 kc")) {
			return "750 kc";
		} else if (tradeSize.equals("750 kc")) {
			return "800 kc";
		} else if (tradeSize.equals("800 kc")) {
			return "900 kc";
		} else if (tradeSize.equals("900 kc")) {
			return "1000 kc";
		} else {
			return tradeSize;
		}
	}
}
