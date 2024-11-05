package com.PlayGroundAdv.Solar.model.libraries;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompoentFavDto {

	private Long userId;
	private List<Long> listUsers;
	private Long compId;
	private Long updatedBy;
	public CompoentFavDto(Long userId, Long compId, Long updatedBy) {
		super();
		this.userId = userId;
		this.compId = compId;
		this.updatedBy = updatedBy;
	}
	
}
