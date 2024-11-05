package com.PlayGroundAdv.Solar.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class HistoriqModel {

	private Object objectOne;
	private Object objectTwo;
	private String ipAdress;
	private String timeZone;
	private Long idUser;
	private String numTab;
	private String sessionId;
	private String openDate;
	private Object objectThree;
	private Object objectFour;
	private String saveType;

	public HistoriqModel(Object objectOne, Object objectTwo, String ipAdress, String timeZone, Long idUser,
			String numTab, String sessionId, String openDate) {
		super();
		this.objectOne = objectOne;
		this.objectTwo = objectTwo;
		this.ipAdress = ipAdress;
		this.timeZone = timeZone;
		this.idUser = idUser;
		this.numTab = numTab;
		this.sessionId = sessionId;
		this.openDate = openDate;
	}

	public HistoriqModel(Object objectOne, Object objectTwo, Long idUser, String ipAdress, String timeZone,
			String numTab, String sessionId, String openDate, Object objectThree, Object objectFour) {
		super();
		this.objectOne = objectOne;
		this.ipAdress = ipAdress;
		this.timeZone = timeZone;
		this.idUser = idUser;
		this.numTab = numTab;
		this.sessionId = sessionId;
		this.openDate = openDate;
		this.objectThree = objectThree;
		this.objectFour = objectFour;
	}

	public HistoriqModel(Object objectOne, Long idUser, String ipAdress, String timeZone, String numTab,
			String sessionId, String openDate, Object objectThree, Object objectFour) {
		super();
		this.objectOne = objectOne;
		this.ipAdress = ipAdress;
		this.timeZone = timeZone;
		this.idUser = idUser;
		this.numTab = numTab;
		this.sessionId = sessionId;
		this.openDate = openDate;
		this.objectThree = objectThree;
		this.objectFour = objectFour;
	}

}
