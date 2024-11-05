package com.PlayGroundAdv.Solar.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.GetRFIRequest;
import com.PlayGroundAdv.Solar.model.RFIQuestionFavRequest;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.service.libraries.RFIQuestionService;

@RestController
@RequestMapping("/rfiLibrary")
public class RFILibraryController {

	final RFIQuestionService getQuestionLibraryService;

	public RFILibraryController(RFIQuestionService getQuestionLibraryService) {
		super();
		this.getQuestionLibraryService = getQuestionLibraryService;
	}

	@PostMapping("/editRfi/{idUser}")
	public String editQuestion(@RequestBody RFIQuestionFavRequest question, @PathVariable Long idUser)
			throws Exception {
		return getQuestionLibraryService.editRFIQuestion(question, idUser);
	}

	@PostMapping("/editRfiFav/{idUser}")
	public String editQuestionFfav(@RequestBody RFIQuestionFavRequest question, @PathVariable Long idUser)
			throws Exception {
		return getQuestionLibraryService.updateRFIQuestionFavorite(question, idUser);
	}

	@GetMapping("/getAllFieldName")
	public List<String> getAllFieldName() throws Exception {
		return getQuestionLibraryService.getAllFieldName();
	}

	@PostMapping("/filterRFI")
	public Page<RFIQuestionFavRequest> filterRFI(@RequestBody ComponentPageRequest request) throws Exception {
		return getQuestionLibraryService.filter(request);
	}

	@PostMapping("/addRfiQuestion/{idUser}")
	public String addRfiQuestion(@RequestBody RFIQuestionFavRequest question, @PathVariable Long idUser)
			throws Exception {
		return getQuestionLibraryService.addRFIQuestion(question, idUser);
	}

	@PostMapping("/updateExistingRFIDocument/{idQuestion}")
	public String updateExistingRFIDocument(@PathVariable Long idQuestion) throws Exception {
		return getQuestionLibraryService.updateExistingOfDocument(idQuestion);
	}

}
