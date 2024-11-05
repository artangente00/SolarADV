package com.PlayGroundAdv.Solar.entity.ahj_library;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "AHJ_ATTACHMENT_LOG")
public class AHJAttachmentLogEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "AHJ")
	@ManyToOne
	private AHJChecklistEntity ahj;
	
	@JoinColumn(name = "DELETED_BY")
	@ManyToOne
	private AuthentificationEntity deletedBy;

	@Column
	private Date deleteDate;
	@Column
	private String category;
	@Column
	private String cellTitle;
	@Column
	private String folder;
	@Column
	private String fileName;
	public AHJAttachmentLogEntity(AHJChecklistEntity ahj, AuthentificationEntity deletedBy, Date deleteDate,
			String category, String cellTitle, String folder, String fileName) {
		super();
		this.ahj = ahj;
		this.deletedBy = deletedBy;
		this.deleteDate = deleteDate;
		this.category = category;
		this.cellTitle = cellTitle;
		this.folder = folder;
		this.fileName = fileName;
	}

	
}
