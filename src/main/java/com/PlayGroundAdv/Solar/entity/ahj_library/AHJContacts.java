package com.PlayGroundAdv.Solar.entity.ahj_library;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.array.ListArrayType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class AHJContacts {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "AHJ")
	@ManyToOne
	private AHJChecklistEntity ahj;
	
	private String contact1Title;
	private String contact1Name;
	private String contact1Phone;
	private String contact1Email;
	private String contact1Description;
	private String contact2Title;
	private String contact2Name;
	private String contact2Phone;
	private String contact2Email;
	private String contact2Description;
	private String contact3Title;
	private String contact3Name;
	private String contact3Phone;
	private String contact3Email;
	private String contact3Description;
	
	@Type(type = "list-array")
    @Column(name = "NOTES",columnDefinition = "text[]")
    private List<String> notes;
	
	public AHJContacts(AHJChecklistEntity ahj) {
		super();
		this.ahj = ahj;
	}

}
