package com.PlayGroundAdv.Solar.service.drafter;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitToolsEntity;
import com.PlayGroundAdv.Solar.model.PermitToolsEntityRequest;
import com.PlayGroundAdv.Solar.repositories.PermitToolsRepository;

@Service
@Transactional
public class PermitToolsDrafterService {

	final PermitToolsRepository permitToolsRepo;

	public PermitToolsDrafterService(PermitToolsRepository permitToolsRepo) {
		super();
		this.permitToolsRepo = permitToolsRepo;
	}

	public String EditToolsEntity(PermitToolsEntityRequest permitToolsEntityRequest, Long idPermit) {

		try {

			PermitToolsEntity permitToolsEntity = permitToolsRepo.findByPermitEntityId(idPermit);
			permitToolsEntity.setMspleft(permitToolsEntityRequest.getMspleft());
			permitToolsEntity.setMsptop(permitToolsEntityRequest.getMsptop());
			permitToolsEntity.setJboxleft(permitToolsEntityRequest.getJboxleft());
			permitToolsEntity.setJboxtop(permitToolsEntityRequest.getJboxtop());
			permitToolsEntity.setCombinerleft(permitToolsEntityRequest.getCombinerleft());
			permitToolsEntity.setCombinertop(permitToolsEntityRequest.getCombinertop());
			permitToolsEntity.setDcdiscoleft(permitToolsEntityRequest.getDcdiscoleft());
			permitToolsEntity.setDcdiscotop(permitToolsEntityRequest.getDcdiscotop());
			permitToolsEntity.setInvleft(permitToolsEntityRequest.getInvleft());
			permitToolsEntity.setInvtop(permitToolsEntityRequest.getInvtop());
			permitToolsEntity.setAcdiscoleft(permitToolsEntityRequest.getAcdiscoleft());
			permitToolsEntity.setAcdiscotop(permitToolsEntityRequest.getAcdiscotop());
			permitToolsEntity.setOtherleft(permitToolsEntityRequest.getOtherleft());
			permitToolsEntity.setOthertop(permitToolsEntityRequest.getOthertop());
			permitToolsEntity.setModuleleft(permitToolsEntityRequest.getModuleleft());
			permitToolsEntity.setModuletop(permitToolsEntityRequest.getModuletop());
			permitToolsEntity.setMspLeftTwo(permitToolsEntityRequest.getMspLeftTwo());
			permitToolsEntity.setMspTwoTop(permitToolsEntityRequest.getMspTwoTop());
			permitToolsEntity.setJboxTwoLeft(permitToolsEntityRequest.getJboxTwoLeft());
			permitToolsEntity.setJboxTwoTop(permitToolsEntityRequest.getJboxTwoTop());
			permitToolsEntity.setCombinerTwoLeft(permitToolsEntityRequest.getCombinerTwoLeft());
			permitToolsEntity.setCombinerTwoTop(permitToolsEntityRequest.getCombinerTwoTop());
			permitToolsEntity.setDcdiscoTwoLeft(permitToolsEntityRequest.getDcdiscoTwoLeft());
			permitToolsEntity.setDcdiscoTwoTop(permitToolsEntityRequest.getDcdiscoTwoTop());
			permitToolsEntity.setInvTwoLeft(permitToolsEntityRequest.getInvTwoLeft());
			permitToolsEntity.setInvTwoTop(permitToolsEntityRequest.getInvTwoTop());
			permitToolsEntity.setAcdiscoTwoLeft(permitToolsEntityRequest.getAcdiscoTwoLeft());
			permitToolsEntity.setAcdiscoTwoTop(permitToolsEntityRequest.getAcdiscoTwoTop());
			permitToolsEntity.setOtherTwoLeft(permitToolsEntityRequest.getOtherTwoLeft());
			permitToolsEntity.setOtherTwoTop(permitToolsEntityRequest.getOtherTwoTop());
			permitToolsEntity.setModuleTwoLeft(permitToolsEntityRequest.getModuleTwoLeft());
			permitToolsEntity.setModuleTwoTop(permitToolsEntityRequest.getModuleTwoTop());
			permitToolsRepo.save(permitToolsEntity);
			return " succes ";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

}
