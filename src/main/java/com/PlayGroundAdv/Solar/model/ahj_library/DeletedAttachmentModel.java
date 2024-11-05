package com.PlayGroundAdv.Solar.model.ahj_library;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DeletedAttachmentModel {
	private Long ahjId;
	private Long userId;
	private String deletedBy;
	private String cellId;
	private String cellTitle;
	private String category;
	private String fileName;
	private Date deleteDate;
}
