package com.PlayGroundAdv.Solar.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TableDataPage {
	private List<Object> data;
	private Long totalElements;
	private Integer totalPages;
}
