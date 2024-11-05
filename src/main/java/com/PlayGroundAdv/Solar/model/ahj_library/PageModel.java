package com.PlayGroundAdv.Solar.model.ahj_library;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class PageModel {

	int page;
	int size;
	String ahj;
	String state;
	String county;
}
