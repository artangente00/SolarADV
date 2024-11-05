package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.ACCombinerSLCEntityModel;
import com.PlayGroundAdv.Solar.model.AcCombinerSLCModel;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.NewACCombinerSLCModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.service.libraries.AcCombinerService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/acCombinerSlcLibrary")
//@Api(value="Ac Combiner / Slc Library", description="Operations related to the Ac Combiner / Slc Library") 
public class AcCombinerSlcLibraryController {


	final AcCombinerService acCombinerSlcLibraryService;
	
	public AcCombinerSlcLibraryController(AcCombinerService acCombinerSlcLibraryService) {
		this.acCombinerSlcLibraryService = acCombinerSlcLibraryService;
	}

//	@ApiOperation(value = "Edit/Update Ac Conbiner's infos", response = String.class)
	@PostMapping("/editAcCombinerDisco")
	public String editAcCombinerDisco(HttpServletRequest request, @RequestBody HistoriqModel hm) {

		ObjectMapper mapper = new ObjectMapper();
		AcCombinerSLCModel acc = new AcCombinerSLCModel();
		hm.setSessionId(request.getSession().getId());

		byte[] json = null;

		try {
			json = mapper.writeValueAsBytes(hm.getObjectOne());
			acc = mapper.readValue(json, AcCombinerSLCModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return acCombinerSlcLibraryService.editAcCombinerDisco(acc, hm.getIpAdress(), hm.getTimeZone(),
				hm.getIdUser(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	/*
	 * Delete Roof Attachment
	 */
//	@ApiOperation(value = "Check that the componenet is not used in any permit", response = ProjectForLibrariesModel.class)
	@PostMapping("/getRemoveAcCombinerDiscoConfirmation")
	public List<ProjectForLibrariesModel> getRemoveAcCombinerDiscoConfirmation(
			@RequestBody AcCombinerSLCModel acc) {
		return acCombinerSlcLibraryService.getRemoveAcCombinerDiscoConfirmation(acc);
	}

//	@ApiOperation(value = "Delete the componenet", response = String.class)
	@PostMapping("/deleteAcCombinerDisco")
	public String deleteAcCombinerDisco(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		return acCombinerSlcLibraryService.deleteAcCombinerDisco((String) hm.getObjectOne(), hm.getIpAdress(),
				hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());

	}

	@PostMapping("/sendCorrectionACRequest")
	public String sendCorrectionACRequest(@RequestBody CorrectionRequest model) {
		return acCombinerSlcLibraryService.sendCorrectionRequest(model);

	}
	
	@PostMapping("/activateAcCombinerDisco")
	public String activateAcCombinerDisco(HttpServletRequest request,@RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		return acCombinerSlcLibraryService.activateAcCombinerDisco(Long.valueOf(hm.getObjectOne().toString()), hm.getIpAdress(),
					hm.getTimeZone(), hm.getIdUser(),hm.getNumTab(),hm.getSessionId(),hm.getOpenDate());
	}

	/*
	 * Edit Roof Attachment Favorite
	 */

	@PostMapping("/editAcCombinerDiscoFavoriteList/{idOwner}")
	public String editAcCombinerDiscoFavoriteList(HttpServletRequest request, @RequestBody HistoriqModel hm,
			@PathVariable("idOwner") Long idOwner) {
		hm.setSessionId(request.getSession().getId());

		return acCombinerSlcLibraryService.editAcCombinerDiscoFavoriteList(Long.valueOf(hm.getObjectTwo().toString()),
				(Boolean) hm.getObjectOne(), hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), idOwner,
				hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	/*
	 * Edit Junction Box Favorite for Other Users
	 */
//	@ApiOperation(value = "Return list of users that does not have the component added to their fav list", response = UsersEntityResult.class)
	@PostMapping("/getUsersForFavList")
	public List<UsersEntityResult> getUsersForFavList(@RequestBody Long[] idsForUserUpdtFav) {
		return acCombinerSlcLibraryService.getUsersForFavList(idsForUserUpdtFav[0],
				idsForUserUpdtFav[1]);
	}

	@PostMapping("/editUsersFavoriteList")
	public String editUsersFavoriteList(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		ObjectMapper mapper = new ObjectMapper();
		Long[] clle = null;
		hm.setSessionId(request.getSession().getId());

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectTwo());
			clle = mapper.readValue(json, Long[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return acCombinerSlcLibraryService.editUsersFavoriteList(Long.valueOf(hm.getObjectOne().toString()), clle, hm.getIpAdress(),
				hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	/*
	 * Test if the Roof Attachment existe
	 */

	@PostMapping("/checkACCombinerExistent/{idUser}")
	public List<AcCombinerSLCModel> checkACCombinerExistent(@RequestBody NewACCombinerSLCModel acc,
			@PathVariable Long idUser) {
		return acCombinerSlcLibraryService.checkACCombinerExistent(acc, idUser);
	}

	@PostMapping("/addAcCombinerDisco/{idPermit}")
	public ACCombinerSLCEntityModel addAcCombinerDisco(HttpServletRequest request, @RequestBody HistoriqModel hm,
			@PathVariable Long idPermit) {
		ObjectMapper mapper = new ObjectMapper();
		NewACCombinerSLCModel acCombinerDiscoRes = null;
		hm.setSessionId(request.getSession().getId());

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectOne());
			acCombinerDiscoRes = mapper.readValue(json, NewACCombinerSLCModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return acCombinerSlcLibraryService.addAcCombinerDisco(acCombinerDiscoRes, hm.getIpAdress(), hm.getTimeZone(),
				hm.getIdUser(), idPermit, hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	@PostMapping("/editACCombinerNotification")
	public String editACCombinerNotification(@RequestBody String[] inverterInfo) {
		return acCombinerSlcLibraryService.editACCombinerNotification(Long.valueOf(inverterInfo[0]), inverterInfo[1],
				inverterInfo[2]);
	}

	@PostMapping("/filter")
	public Page<AcCombinerSLCModel> filter(@RequestBody ComponentPageRequest request) {
		return acCombinerSlcLibraryService.filter(request);
	}

}
