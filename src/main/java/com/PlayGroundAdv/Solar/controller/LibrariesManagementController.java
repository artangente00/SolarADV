package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.ComponentTypeRequest;
import com.PlayGroundAdv.Solar.model.ConfirmComponentRequest;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult;
import com.PlayGroundAdv.Solar.model.VerificationModel;
import com.PlayGroundAdv.Solar.service.libraries.ExportVerificationReport;
import com.PlayGroundAdv.Solar.service.libraries.LibariesManagementService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/librariesManagement")
public class LibrariesManagementController {

	final LibariesManagementService libariesManagementService;
	final ExportVerificationReport exportVerificationReport;
	public LibrariesManagementController(LibariesManagementService libariesManagementService,
			ExportVerificationReport exportVerificationReport) {
		super();
		this.libariesManagementService = libariesManagementService;
		this.exportVerificationReport = exportVerificationReport;
	}

	@PostMapping("/getComponentInfo")
	public ComponentTypeRequest getComponentInfo(@RequestBody String[] type) {
		return libariesManagementService.getComponentInfo(Long.valueOf(type[0]), type[1]);
	}

	@PostMapping("/verifyComponent")
	public Boolean verifyComponent(@RequestBody VerificationModel params) {
		return libariesManagementService.verifyComponent(params);
	}

	@PostMapping("/getVerificationReport")
	public ResponseEntity<byte[]> getVerificationReport(@RequestBody String title) {
		return exportVerificationReport.getVerificationReport(title);
	}

	@PostMapping("/confirmComponent")
	public String confirmComponent(HttpServletRequest request, @RequestBody HistoriqModel hm) {

		ObjectMapper mapper = new ObjectMapper();
		String[] clle = null;

		ConfirmComponentRequest cm = new ConfirmComponentRequest();
		byte[] json = null;

		try {
			byte[] jsonTab = mapper.writeValueAsBytes(hm.getObjectTwo());
			clle = mapper.readValue(jsonTab, String[].class);
			json = mapper.writeValueAsBytes(hm.getObjectOne());
			cm = mapper.readValue(json, ConfirmComponentRequest.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return libariesManagementService.confirmComponent(cm, Long.valueOf(clle[0]), clle[1], hm.getIdUser(), clle[2],
				clle[3]);
	}

	@GetMapping("/getComponent")
	public List<LibrariesManagementModelResult> getComponent() {
		return libariesManagementService.getAllNewComponent();
	}

}
