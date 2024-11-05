package com.PlayGroundAdv.Solar.service.monday_api;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.model.project.MondayAPIModel;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.service.project.GetProjectDetailsUtils;
import com.PlayGroundAdv.Solar.service.project.GetTotalModule;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class CreateAPIQuery {

	final GetProjectDetailsUtils projectUtils;
	final ContractorItem contractorItem;
	final ModuleRepository moduleRepo;
	final CheckValueTypesService checkValue;
	final GetTotalModule moduleQtyService;

	public CreateAPIQuery(GetProjectDetailsUtils projectUtils, ContractorItem contractorItem,
			ModuleRepository moduleRepo, CheckValueTypesService checkValue, GetTotalModule moduleQtyService) {
		super();
		this.projectUtils = projectUtils;
		this.contractorItem = contractorItem;
		this.moduleRepo = moduleRepo;
		this.checkValue = checkValue;
		this.moduleQtyService = moduleQtyService;
	}

	private static final SimpleDateFormat isoFormatter = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
	private static final TimeZone utc = TimeZone.getTimeZone("UTC");
	static {
		isoFormatter.setTimeZone(utc);
	}

	public String createQuery(PermitEntity permit, MondayAPIModel projectDto) {
		try {
			String projectName = projectUtils.getProjectName(permit);
			StringBuilder query = new StringBuilder(
					"mutation { " + "create_item (board_id: 285188538, group_id: \"new_group12\", item_name: \""
							+ projectName + "\", column_values:\"{");

			// Project Type
			query.append(" \\\"dropdown6\\\": { \\\"labels\\\": [\\\"Plan Set\\\"] },");
			query.append(" \\\"rating\\\" : {\\\"rating\\\" : 2},");

			// Contractor
//			if (permit.getAuthentificationEntity().getMondayId() != null) {
//				query.append(" \\\"link_to_item6\\\":  {\\\"item_ids\\\":["+permit.getAuthentificationEntity().getMondayId()+"] },");
//			}else {
			String contractorId = contractorItem.getContractorId(permit.getAuthentificationEntity());
			if (contractorId != null && !contractorId.equals("Contractor Not Found")) {
				query.append(" \\\"link_to_item6\\\":  {\\\"item_ids\\\":[" + contractorId + "] },");
			}
//			}

			// Project Address
			String address = projectDto.getFormattedAddress();
			if (!checkValue.isStringNotEmpty(address)) {
				String city = checkValue.Equals(projectDto.getCity(), "Other") ? projectDto.getCityOther()
						: projectDto.getCity();
				address = projectDto.getAddress() + ", " + city + "," + projectDto.getState() + " "
						+ projectDto.getPostalCode();
				projectDto.setLaltitude(0F);
				projectDto.setLongitude(0F);
			}
			if (projectDto.getLaltitude() != null && projectDto.getLongitude() != null) {
				query.append("  \\\"location\\\": {\\\"address\\\": \\\"" + address + "\\\", \\\"lat\\\": \\\""
						+ projectDto.getLaltitude() + "\\\", \\\"lng\\\": \\\"" + projectDto.getLongitude() + "\\\"},");
			} else {
				query.append("  \\\"location\\\": {\\\"address\\\": \\\"" + address + "\\\", \\\"lat\\\": \\\"" + 0
						+ "\\\", \\\"lng\\\": \\\"" + 0 + "\\\"},");
			}

			// System will be paired with Battery Storage
			if (Boolean.TRUE.equals(projectDto.getEss())) {
				query.append(" \\\"check1\\\": { \\\"checked\\\": \\\"true\\\" },");
			}

			// Grid-Tied Sys. Or Off-Grid/Standalone
			if (checkValue.Equals(projectDto.getOffGrid(), "Off-Grid/Standalone")) {
				query.append(" \\\"check14\\\": { \\\"checked\\\": \\\"true\\\" },");
			}

			// Generator
			if (Boolean.TRUE.equals(projectDto.getEss()) && Boolean.TRUE.equals(projectDto.getGenerator())) {
				query.append(" \\\"off_grid8\\\": { \\\"checked\\\": \\\"true\\\" },");
			}

			// There is an existing Solar system on the property
			if (Boolean.TRUE.equals(projectDto.getESolar())) {
				query.append(" \\\"off_grid\\\": { \\\"checked\\\": \\\"true\\\" },");
			}

			// Commercial
			if (checkValue.isStringNotEmpty(permit.getProjectName())) {
				query.append(" \\\"standard_system\\\": { \\\"checked\\\": \\\"true\\\" },");
			}
//			A.B 10/17/2022 REV-CR-283
			// Standard System
			if (!checkValue.isStringNotEmpty(permit.getProjectName()) && !Boolean.TRUE.equals(projectDto.getESolar())
					&& !checkValue.Equals(projectDto.getOffGrid(), "Off-Grid/Standalone")
					&& !Boolean.TRUE.equals(projectDto.getEss())) {
				query.append(" \\\"_e__solar\\\": { \\\"checked\\\": \\\"true\\\" },");
			}

			// Commercial
			if (Boolean.TRUE.equals(projectDto.getRoofMounted())
					&& Boolean.TRUE.equals(projectDto.getGroundMounted())) {
				query.append(" \\\"dropdown\\\": { \\\"labels\\\": [\\\"Roof\\\",\\\"Ground\\\"] },");
			} else if (Boolean.TRUE.equals(projectDto.getRoofMounted())
					|| Boolean.TRUE.equals(projectDto.getGroundMounted())) {
				String label = Boolean.TRUE.equals(projectDto.getRoofMounted()) ? "Roof" : "Ground";
				query.append(" \\\"dropdown\\\": { \\\"labels\\\": [\\\"" + label + "\\\"] },");
			}

			// System Size
			Float systemSize = getPermitSystemSize(projectDto.getModuleId(), permit.getId());
			if (systemSize != null) {
				query.append(" \\\"numbers\\\": " + systemSize);
			}

			query.append("}\") { id }}");
			return query.toString().replace(",}\") { id }}", "}\") { id }}");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String updateQuery(MondayAPIModel projectDto, Date submitDate, String itemId) {
		try {
			StringBuilder query = new StringBuilder("mutation { " + "change_multiple_column_values (item_id: " + itemId
					+ ", board_id: 285188538, column_values:\"{");
			String date = isoFormatter.format(submitDate).split(" ")[0];
			String time = isoFormatter.format(submitDate).split(" ")[1];
			// Date
			query.append(" \\\"date0\\\":  {\\\"date\\\":\\\"" + date + "\\\", \\\"time\\\":\\\"" + time + "\\\" },");

			// Structural Engineering Package Inquiry
			if (projectDto.getStrucEngi() != null && projectDto.getStrucEngi().equals("true")) {
				query.append(" \\\"structural___engineering\\\":  {\\\"label\\\":\\\"Required\\\" },");
			} else {
				query.append(" \\\"structural___engineering\\\":  {\\\"label\\\":\\\"Not Needed\\\" },");
			}

			// Please Provide the Required Electrical Stamp on this Plan Set
			if (Boolean.TRUE.equals(projectDto.getElecEngi())) {
				query.append(" \\\"struc_engi7\\\":  {\\\"label\\\":\\\"Required\\\" }");
			} else {
				query.append(" \\\"struc_engi7\\\":  {\\\"label\\\":\\\"Not Needed\\\" }");
			}

			query.append("}\") { id }}");
			return query.toString().replace(",}\") { id }}", "}\") { id }}");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public Float getPermitSystemSize(Long moduleId, Long projectId) {
		try {
			Cmodulev2 moduleInfo = moduleRepo.findById(moduleId).orElse(null);
			Integer totlaModule = moduleQtyService.getModuleQty(projectId);
			if (moduleInfo != null && totlaModule != null) {
				Float permitSusyemSize = 1F;
				Locale locale = Locale.ENGLISH;
				NumberFormat nf = NumberFormat.getNumberInstance(locale);
				// A.B for trailing zeros:
				nf.setMinimumFractionDigits(3);
				// A.B round to 3 digits:
				nf.setMaximumFractionDigits(3);
				if (moduleInfo != null && moduleInfo.getStcRounded() != null) {
					permitSusyemSize = Float.parseFloat(moduleInfo.getStcRounded());
					permitSusyemSize = permitSusyemSize * totlaModule;
					permitSusyemSize = permitSusyemSize / 1000;

				}
				return permitSusyemSize;
			} else
				return null;

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

}
