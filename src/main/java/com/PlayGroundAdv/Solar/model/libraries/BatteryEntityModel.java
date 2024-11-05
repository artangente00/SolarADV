package com.PlayGroundAdv.Solar.model.libraries;

import com.PlayGroundAdv.Solar.entity.Battery;

public class BatteryEntityModel {

	private Battery battery;

	public BatteryEntityModel() {
		super();
	}

	public BatteryEntityModel(Battery battery) {
		super();
		this.battery = battery;
	}

	public Battery getBattery() {
		return battery;
	}

	public void setBattery(Battery battery) {
		this.battery = battery;
	}

}
