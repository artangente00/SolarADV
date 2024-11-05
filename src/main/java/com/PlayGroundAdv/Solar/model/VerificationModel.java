package com.PlayGroundAdv.Solar.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class VerificationModel {

	public Long equipmentId;
	public Long userId;
	public String library;
}
