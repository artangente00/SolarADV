package com.PlayGroundAdv.Solar.service.ahj_library;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJChecklistEntity;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJLogEntity;
import com.PlayGroundAdv.Solar.entity.ahj_library.MassUpdateLog;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ahj_library.MassUpdateModel;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJChecklistRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJLogRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.MassUpdateLogRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
@Service
public class AHJMassUpdateService {

	final AuthenticationRepository userRepo;
	final AHJChecklistRepository aHJChecklistRepo;
	final AHJLogRepository ahjLogRepo;
	final MassUpdateLogRepository massUpdateLogRepo;

	public AHJMassUpdateService(AuthenticationRepository userRepo, AHJChecklistRepository aHJChecklistRepo,
			AHJLogRepository ahjLogRepo, MassUpdateLogRepository massUpdateLogRepo) {
		super();
		this.userRepo = userRepo;
		this.aHJChecklistRepo = aHJChecklistRepo;
		this.ahjLogRepo = ahjLogRepo;
		this.massUpdateLogRepo = massUpdateLogRepo;
	}

	final static SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm");

	public void logMassUpdate(MassUpdateModel massUpdate) {
		try {
			List<AHJChecklistEntity> ahjs = aHJChecklistRepo.findByState(massUpdate.getState());
			AuthentificationEntity user = userRepo.findById(massUpdate.getDoneBy()).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" + massUpdate.getDoneBy()));
			MassUpdateLog massLog = new MassUpdateLog(user, new Date(), massUpdate.getCode(), massUpdate.getState(),
					massUpdate.getValue());
			massUpdateLogRepo.save(massLog);
			for (AHJChecklistEntity ahj : ahjs) {
				JSONObject obj = ahj.toJSON();
				String previousValue = obj.get(massUpdate.getCode()) != null ? obj.get(massUpdate.getCode()).toString()
						: null;
				AHJLogEntity updateLog = new AHJLogEntity(ahj, user, new Date(), "Coverning Codes",
						massUpdate.getCode(), previousValue, massUpdate.getValue(), true, massLog);
				ahjLogRepo.save(updateLog);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Boolean doMassUpdate(MassUpdateModel massUpdate) {
		try {
			List<AHJChecklistEntity> ahjs = aHJChecklistRepo.findByState(massUpdate.getState());
			AuthentificationEntity user = userRepo.findById(massUpdate.getDoneBy())
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + massUpdate.getDoneBy()));
			for (AHJChecklistEntity ahj : ahjs) {
				JSONObject obj = ahj.toJSON();
				obj.put(massUpdate.getCode(), massUpdate.getValue());
				ahj = fromJsonObj(obj, ahj);
				ahj.setLastUpdateBy(user);
				ahj.setLastUpdate(new Date());
				aHJChecklistRepo.save(ahj);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private MassUpdateLog getLastUpdate(MassUpdateModel massUpdate) {

		try {
			AuthentificationEntity user = userRepo.findById(massUpdate.getDoneBy())
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + massUpdate.getDoneBy()));
			MassUpdateLog log =  massUpdateLogRepo
					.findTopByStateAndGoverningCodeAndMassUpdateCancelledOrderByUpdateDateDesc(massUpdate.getState(),
							massUpdate.getCode(), false);
			if (log.getUpdatedBy().equals(user) && log.getUpdateDate().after( user.getUserLastLogin())) {
				return log; 
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}
	public String getLastMassUpdate(MassUpdateModel massUpdate) {
		try {
			MassUpdateLog log = getLastUpdate(massUpdate);
			if (log != null) {
				return "Are you sure you want to undo the last update made by " + log.getUpdatedBy().getFirstName() 
						+" "+log.getUpdatedBy().getLastName() + " on "+ format.format(log.getUpdateDate())
						+" where the  Governing code "+log.getGoverningCode().toUpperCase()+" for "+log.getState()+" was update to " + log.getNewValue() + "?";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean undoMassUpdate(MassUpdateModel massUpdate) {
		try {
			MassUpdateLog massLog = getLastUpdate(massUpdate);
			if (massLog != null) {
				List<AHJLogEntity> logs = ahjLogRepo.findByMassUpdateLogId(massLog.getId());
				for (AHJLogEntity log : logs) {
					Boolean isUpdated = ahjLogRepo.existsByAhjIdAndCellNameAndMassUpdateAndLastUpdateAfter(
							log.getAhj().getId(), massUpdate.getCode().toUpperCase(), false, log.getLastUpdate());
					if (Boolean.FALSE.equals(isUpdated)) {
						AHJChecklistEntity ahj = log.getAhj();
						JSONObject obj = ahj.toJSON();
						obj.put(massUpdate.getCode(), log.getPreviousValue());
						ahj = fromJsonObj(obj, ahj);
						aHJChecklistRepo.save(ahj);
					}
				}
			}

			logUndoMassUpdate(massLog, massUpdate.getDoneBy());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void logUndoMassUpdate(MassUpdateLog massLog, Long userId) {
		try {
			AuthentificationEntity user = userRepo.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + userId));
			massLog.setMassUpdateCancelled(true);
			massLog.setUndoDate(new Date());
			massLog.setUndoBy(user);
			massUpdateLogRepo.save(massLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AHJChecklistEntity fromJsonObj(JSONObject jo, AHJChecklistEntity ahj) {

		ahj.setIbc(jo.get("ibc") != null ? jo.get("ibc").toString() : null);
		ahj.setIrc(jo.get("irc") != null ? jo.get("irc").toString() : null);
		ahj.setIfc(jo.get("ifc") != null ? jo.get("ifc").toString() : null);
		ahj.setNec(jo.get("nec") != null ? jo.get("nec").toString() : null);
		ahj.setCbc(jo.get("cbc") != null ? jo.get("cbc").toString() : null);
		ahj.setCec(jo.get("cec") != null ? jo.get("cec").toString() : null);
		ahj.setCfc(jo.get("cfc") != null ? jo.get("cfc").toString() : null);
		ahj.setCrc(jo.get("crc") != null ? jo.get("crc").toString() : null);
        ahj.setCmc(jo.get("cgbsc") != null ? jo.get("cgbsc").toString() : null);
        ahj.setCgbsc(jo.get("cmc") != null ? jo.get("cmc").toString() : null);
        ahj.setCpc(jo.get("cpc") != null ? jo.get("cpc").toString() : null);
        ahj.setIecc(jo.get("iecc") != null ? jo.get("iecc").toString() : null);
        ahj.setImc(jo.get("imc") != null ? jo.get("imc").toString() : null);
        ahj.setIpc(jo.get("ipc") != null ? jo.get("ipc").toString() : null);
        ahj.setUmc(jo.get("umc") != null ? jo.get("umc").toString() : null);
		return ahj;
	}
}
