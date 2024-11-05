package com.PlayGroundAdv.Solar.model.ahj_library;

import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AHJLibraryModel {
	
	private Long id;
	private String ahj;
	private String county;
	private String state;
	private String ibc;
	private String irc;
	private String ifc;
	private String nec;
	private String cbc;
	private String cec;
	private String cfc;
	private String crc;
}
