package com.PlayGroundAdv.Solar.model.ahj_library;

import java.util.List;

import com.PlayGroundAdv.Solar.model.DropdownOptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class AHJFilterList {

	private List<DropdownOptions> ahj;
	private List<String> state;
	private List<DropdownOptions> county;
}
