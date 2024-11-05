package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.NewRoofAttachmentModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.model.libraries.FavoriteListDto;
import com.PlayGroundAdv.Solar.model.libraries.RoofAttachmentModel;
import com.PlayGroundAdv.Solar.model.libraries.RoofAttachmentResult;
import com.PlayGroundAdv.Solar.service.libraries.RoofAttachmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/roofAttachmentsLibrary")
public class RoofAttachmentsLibraryController {

	final RoofAttachmentService roofAttachmentLibraryService;

	public RoofAttachmentsLibraryController(RoofAttachmentService roofAttachmentLibraryService) {
		super();
		this.roofAttachmentLibraryService = roofAttachmentLibraryService;
	}

	// Get All Roof Attachment
	@PostMapping("/filter")
	public Page<RoofAttachmentResult> filter(@RequestBody ComponentPageRequest request) {
		return roofAttachmentLibraryService.filter(request);
	}

	@PostMapping("/sendCorrectionRoofRequest")
	public String sendCorrectionRoofRequest(@RequestBody CorrectionRequest model) {
		return roofAttachmentLibraryService.sendCorrectionRequest(model);
	}

	/*
	 * Add Roof Attachment
	 */
	@PostMapping("/addRoofAttachment")
	public FavoriteListDto addRoofAttachment(@RequestBody HistoriqModel hm) {
		ObjectMapper mapper = new ObjectMapper();
		NewRoofAttachmentModel clle = null;

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectOne());
			clle = mapper.readValue(json, NewRoofAttachmentModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return roofAttachmentLibraryService.addRoofAttachment(clle, hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(),
				Long.valueOf(hm.getObjectTwo().toString()));
	}

	/*
	 * Edit Roof Attachment Favorite
	 */
	@PostMapping("/editRoofAttachmentFavoriteList")
	public String editRoofAttachmentFavoriteList(@RequestBody HistoriqModel hm) {
		return roofAttachmentLibraryService.editRoofAttachmentFavoriteList(Long.valueOf(hm.getObjectOne().toString()),
				(Boolean) hm.getObjectTwo(), hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser());
	}

	/*
	 * Edit roof Attachment Favorite for Other Users
	 */
	@PostMapping("/getUsersForFavList")
	public List<UsersEntityResult> getUsersForFavList(@RequestBody Long[] idsForUserUpdtFav) {
		return roofAttachmentLibraryService.getUsersForFavList(idsForUserUpdtFav[0], idsForUserUpdtFav[1]);
	}

	@PostMapping("/editUsersFavoriteList")
	public String editUsersFavoriteList(@RequestBody HistoriqModel hm) {

		ObjectMapper mapper = new ObjectMapper();
		Long[] clle = null;

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectTwo());
			clle = mapper.readValue(json, Long[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return roofAttachmentLibraryService.editUsersFavoriteList(Long.valueOf(hm.getObjectOne().toString()), clle,
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser());
	}

	/*
	 * Edit Roof Attachment
	 */
	@PostMapping("/checkExistent")
	public List<ComponentModel> checkExistent(@RequestBody HistoriqModel hm) {

		ObjectMapper mapper = new ObjectMapper();
		NewRoofAttachmentModel cm = new NewRoofAttachmentModel();
		byte[] json = null;

		try {
			json = mapper.writeValueAsBytes(hm.getObjectOne());
			cm = mapper.readValue(json, NewRoofAttachmentModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return roofAttachmentLibraryService.checkExistent(cm, hm.getIdUser());
	}

	@PostMapping("/editRoofAttachment")
	public String editRoofAttachment(@RequestBody HistoriqModel hm) {

		ObjectMapper mapper = new ObjectMapper();
		RoofAttachmentModel clle = new RoofAttachmentModel();

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectOne());
			clle = mapper.readValue(json, RoofAttachmentModel.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return roofAttachmentLibraryService.editRoofAttachment(clle, hm.getIpAdress(), hm.getTimeZone(),
				hm.getIdUser());
	}

	/*
	 * Delete Roof Attachment
	 */
	@PostMapping("/getRemoveRoofAttachmentConfirmation")
	public List<ProjectForLibrariesModel> getRemoveRoofAttachmentConfirmation(@RequestBody String roofAttachmentId) {
		return roofAttachmentLibraryService.getRemoveRoofAttachmentConfirmation(roofAttachmentId);
	}

	@PostMapping("/deleteRoofAttachment")
	public String deleteRoofAttachment(@RequestBody HistoriqModel hm) {
		return roofAttachmentLibraryService.deleteRoofAttachment(Long.valueOf(hm.getObjectOne().toString()),
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser());
	}

	/*
	 * Activate Roof Attachment
	 */
	@PostMapping("/activateRoofAttachment")
	public String activateRoofAttachment(@RequestBody HistoriqModel hm) {
		return roofAttachmentLibraryService.activateRoofAttachment(Long.valueOf(hm.getObjectOne().toString()),
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser());
	}

	/*
	 * Edit Other User Roof Attachment Faforite
	 */
	@PostMapping("/editOtherUserFavorite")
	public String editOtherUserFavorite(@RequestBody HistoriqModel hm) {

		ObjectMapper mapper = new ObjectMapper();
		Long[] cm = null;
		byte[] json = null;

		try {
			json = mapper.writeValueAsBytes(hm.getObjectOne());
			cm = mapper.readValue(json, Long[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return roofAttachmentLibraryService.editOtherUserFavorite(cm, (Boolean) hm.getObjectTwo(), hm.getIpAdress(),
				hm.getTimeZone(), hm.getIdUser());
	}

}
