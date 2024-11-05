package com.PlayGroundAdv.Solar.model.planset_utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class VoltageDropTable {

	public String circuitOrigin;
	public String circuitDestination;
	public String acDc;
	public Float vd;
	@Override
	public String toString() {
		return "VoltageDropTable [circuitOrigin=" + circuitOrigin + ", circuitDestination=" + circuitDestination
				+ ", acDc=" + acDc + ", vd=" + vd + "]";
	}
	
}
