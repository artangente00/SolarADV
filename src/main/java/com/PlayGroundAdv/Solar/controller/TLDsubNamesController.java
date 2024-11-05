package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.entity.TldSubNamesEntity;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.ComponentTLDRequest;
import com.PlayGroundAdv.Solar.model.libraries.TLDSubNamesDto;
import com.PlayGroundAdv.Solar.repositories.sheets.TLDsubNamesRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.sheets.TLDSubNamesServices;


@RestController
@RequestMapping("/tldSubNames")
public class TLDsubNamesController {

	final TLDsubNamesRepository subNameRepository;
	final AuthenticationRepository userRepository;
	final TLDSubNamesServices tldSubNamesServices;

	public TLDsubNamesController(TLDsubNamesRepository subNameRepository, AuthenticationRepository userRepository,
			TLDSubNamesServices tldSubNamesServices) {
		super();
		this.subNameRepository = subNameRepository;
		this.userRepository = userRepository;
		this.tldSubNamesServices = tldSubNamesServices;
	}

	// S.B 20-05-2020 Add New Sub Name
	@PostMapping("/addSubName/{idUser}")
	public String addSubName(@RequestBody TldSubNamesEntity newSubName, @PathVariable Long idUser) throws IOException {
		return tldSubNamesServices.addSubName(newSubName, idUser);
	}

	// S.B 20-05-2020 Get Non Deleted Sub Names
	@PostMapping("/getAllSubNames")
	public Page<TLDSubNamesDto> getAllSubNames(@RequestBody ComponentPageRequest request) throws IOException {
		return tldSubNamesServices.getAllSubNames(request);
	}

	// S.B 03-06-2020 Search Sub Name
	@PostMapping("/searchSubName")
	public Page<TLDSubNamesDto> searchSubName(@RequestBody ComponentTLDRequest request) throws IOException {
		return tldSubNamesServices.searchSubName(request);
	}

	// S.B 20-05-2020 Get Deleted Sub Names
	@PostMapping("/getDeletedSubNames")
	public Page<TLDSubNamesDto> getDeletedSubNames(@RequestBody ComponentPageRequest request) throws IOException {
		return tldSubNamesServices.getDeletedSubNames(request);
	}

	// S.B 03-06-2020 Search Deleted Sub Name
	@PostMapping("/searchDeleted")
	public Page<TLDSubNamesDto> searchDeleted(@RequestBody ComponentTLDRequest request) throws IOException {
		return tldSubNamesServices.searchDeleted(request);
	}

	// S.B 20-05-2020 Edit Sub Name
	@PostMapping("/editSubName/{idUser}")
	public String editSubName(@RequestBody TldSubNamesEntity editSubName, @PathVariable Long idUser)
			throws IOException {
		return tldSubNamesServices.editSubName(editSubName, idUser);
	}

	// S.B 20-05-2020 Delete Sub Name
	@PostMapping("/deleteSubName/{idUser}")
	public String deleteSubName(@RequestBody Long row, @PathVariable Long idUser) throws IOException {
		return tldSubNamesServices.deleteSubName(row, idUser);
	}

	// S.B 20-05-2020 Restore Sub Name
	@PostMapping("/restore")
	public String restore(@RequestBody Long row) throws IOException {
		return tldSubNamesServices.restore(row);
	}

}
